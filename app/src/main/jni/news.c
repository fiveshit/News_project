/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
#include <string.h>
#include <jni.h>

jbyteArray getByteArrayData(JNIEnv *env) ;

jbyte gs_raw_data[256];
jbyte gr_raw_data[256];

#define JNIREG_CLASS "com/factory/inxfac/MainActivity"

/* This is a trivial JNI example where we use a native method
 * to return a new VM String. See the corresponding Java source
 * file located at:
 *
 *   com/inx_factory/in_fac/HelloJni.java
 */
 
//return String
jstring Java_com_factory_inxfac_MainActivity_stringFromJNI( JNIEnv* env, jobject thiz )
{
    return (*env)->NewStringUTF(env, "Hello from JNI !");
}

//return Int
jint Java_com_factory_inxfac_MainActivity_intFromJNI(JNIEnv *env , jobject thiz /* this */) {
    int n = 1023;
    return n;
}

//return Char
jchar Java_com_factory_inxfac_MainActivity_charFromJNI(JNIEnv *env , jobject thiz /* this */) {
    char x = 'G';
    return x;
}

// send string , char , int , then return string
jstring Java_com_factory_inxfac_MainActivity_passDataJNI(
        JNIEnv *env,
        jobject thiz /* this */,
        jstring s,
        jchar c,
        jint i) 
{
        jstring str = s;
        
        return (*env)->NewStringUTF(env, str);
}

//return Byte Array
JNIEXPORT jbyteArray JNICALL Java_com_factory_inxfac_MainActivity_returnArray(JNIEnv *env, jobject This)
{
    jbyte byteUrl[] = {1,2,3,3,4};
    int sizeByteUrl = 5;

    jbyteArray data = (*env)->NewByteArray(env, sizeByteUrl);
    if (data == NULL) {
        return NULL; //  out of memory error thrown
    }

    // creat bytes from byteUrl (read the array:)
    jbyte *bytes = (*env)->GetByteArrayElements(env, data, 0);
    int i;
    for (i = 0; i < sizeByteUrl; i++) {
        bytes[i] = byteUrl[i];
    }

    // move from the temp structure to the java structure
    (*env)->SetByteArrayRegion(env, data, 0, sizeByteUrl, bytes);

    return data;
}

jobject Java_com_factory_inxfac_MainActivity_returnObjectWithByteArrayJNI(JNIEnv *env, jobject callingObject , jbyteArray data) 
{
    jclass helloClass = (*env)->FindClass(env,"com/factory/inxfac/Hello");
    
    // Get java function type
    //jmethodID midConstructor =  (*env)->GetMethodID(env , helloClass, "<init>", "()V");
    //jmethodID midConstructor1 = (*env)->GetMethodID(env , helloClass, "<init>", "(I)V");
    //jmethodID midConstructor2 = (*env)->GetMethodID(env , helloClass, "<init>", "(CI)V");
    //jmethodID midConstructor3 = (*env)->GetMethodID(env , helloClass, "<init>", "(Ljava/lang/String;)V");
    //jmethodID midConstructor4 = (*env)->GetMethodID(env , helloClass, "<init>", "(Ljava/lang/String;CI)V");
    jmethodID midConstructor10 = (*env)->GetMethodID(env , helloClass, "<init>", "([B)V");

    jbyte *bytedata = (*env)->GetByteArrayElements(env , data, 0);

    // FYI
    //jobject helloObject = (*env)->NewObject(env , helloClass, midConstructor);
    //jobject helloObject = (*env)->NewObject(env , helloClass, midConstructor1, 2000);
    //jobject helloObject = (*env)->NewObject(env , helloClass, midConstructor2, 'A', 2000);
    //jobject helloObject = (*env)->NewObject(env , helloClass, midConstructor3, javaStr);
    //jobject helloObject = (*env)->NewObject(env , helloClass, midConstructor4, javaStr, 'A', 2000);
    jobject helloObject = (*env)->NewObject(env , helloClass, midConstructor10, bytedata);

    return helloObject;
    
}

//Send Byte array
jbyteArray Java_com_factory_inxfac_MainActivity_NativeProcessDataSend(JNIEnv *env, jobject clazz, jbyteArray data,jint len)
{

    jclass cls;
    int i;
    cls = (*env)->FindClass(env,JNIREG_CLASS);
    jbyte *bytedata =(*env)->GetByteArrayElements(env,data, 0);

    memset(&gs_raw_data,0,255);
    memcpy(&gs_raw_data,bytedata,len);

    
    // parse the data
    // below is the return 's bytearray lens
    jbyteArray jarrRV =(*env)->NewByteArray(env , len+1);

    (*env)->SetByteArrayRegion(env , jarrRV, 0,len,gs_raw_data);

    return jarrRV;
}

//Receiver Byte Array
jbyteArray Java_com_factory_inxfac_MainActivity_NativeProcessDataReceive(JNIEnv *env, jobject clazz, jbyteArray data,jint len)
{
     jclass cls;
     int i;
     cls = (*env)->FindClass(env,JNIREG_CLASS);
     jbyte *bytedata =(*env)->GetByteArrayElements(env,data, 0);

     memset(&gr_raw_data,0,255);
     memcpy(&gr_raw_data,bytedata,len);
     
     // parse the data and process your data
     // try to process the data
     for(i=0;i<len;i++)
     {
         gr_raw_data[i]=bytedata[i]+1;
     }
     
     // after proces the data, you can return the processed data lens which may be different the raw len
     // note below is the return 's bytearray lens
     jbyteArray jarrRV =(*env)->NewByteArray(env , len+1);

     (*env)->SetByteArrayRegion(env , jarrRV, 0,len,gs_raw_data);

     return jarrRV;
}