package com.jnitest.wuhen.dynamicjni;
import com.wuhen.common.IFunc;

/**
 * Created by wuhen on 16-3-7.
 */
public class Func implements IFunc{
    @Override
    public String funcForInterfece() {
        return "hello from funcForInterfece";
    }
}
