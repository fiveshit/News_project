package com.factory.news;

public class JNIMethod {
    static {
        System.loadLibrary("news");
    }
    public native static String getFromJNI();
}
