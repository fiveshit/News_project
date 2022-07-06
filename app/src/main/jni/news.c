//
// Created by d1004 on 2022/5/3.
//
#include <jni.h>
#include <stdio.h>
#include <Python.h>


/* typedef stuct {
*         const char* name;      // Java method name in Native
*         const char* signature; // signature
*         void* fnPtr;           // C/C++ pointer
*        } JNINativeMethod;
*        rule in methods array:
*           (parameter)retrun ; (jstring)jint
*           ex: "(Ljava/lang/String;)I"
*
*/
static const JNINativeMethod methods[] = {
{"GetTestJni","()Ljava/lang/String;",(void*)GetTestJni},
};
jint JNI_OnLoad(JavaVM *vm, void *reserved)
{
    LOGD("JNI on load ...");
    JNIEnv *env = NULL;
    int result = -1;
    LOGD("reserved = %p",reserved);
    if ((*vm)->GetEnv(vm,(void **)&env, JNI_VERSION_1_4) == JNI_OK) {
        if (registerNativeMethods(env) == JNI_OK) {
            result = JNI_VERSION_1_4;
        }
        return result;
    }
    return JNI_ERR;

}

/* register dynamic method */
static int registerNativeMethods(JNIEnv* env)
{
	jclass clazz;
	clazz = (*env)->FindClass(env,jnireg_class);
	if(clazz == NULL)
	{
		return JNI_FALSE;
	}
	LOGD("register methods ...");
	if((*env)->RegisterNatives(env ,clazz ,methods, sizeof(methods)/sizeof(methods[0])) < 0)
	{
		return JNI_ERR;
	}
	return JNI_OK;
}

//return String
JNIEXPORT jstring JNICALL GetTestJni( JNIEnv* env,jobject obj)
{
        char version[16] = "JniTest";
        LOGD("obj : %p OS versiob = %s",obj,version);
        return (*env)->NewStringUTF(env, version);
}
/*
JNIEXPORT jstring JNICALL Java_com_factory_news_JNIMethod_getFromJNI(JNIEnv *env,jobject obj){
        return (*env) -> NewStringUTF(env,"Hello i am from JNI!!");

    }
*/