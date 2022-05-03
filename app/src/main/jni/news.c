//
// Created by d1004 on 2022/5/3.
//
#include <jni.h>
#include <stdio.h>
#include <Python.h>
JNIEXPORT jstring JNICALL Java_com_factory_news_JNIMethod_getFromJNI(JNIEnv *env,jobject obj){
        return (*env) -> NewStringUTF(env,"Hello i am from JNI!!");

    }