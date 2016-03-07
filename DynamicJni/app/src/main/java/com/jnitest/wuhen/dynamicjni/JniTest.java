package com.jnitest.wuhen.dynamicjni;

/**
 * Created by wuhen on 16-3-4.
 */
public class JniTest {
    static{
        System.loadLibrary("DynamicJni");
    }
    public native String testDynamicJni();
}
