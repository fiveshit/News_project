package com.factory.news;

public class JNIMethod {
    static {
        System.loadLibrary("news");
    }
    public static native int getFromJNI();
}
