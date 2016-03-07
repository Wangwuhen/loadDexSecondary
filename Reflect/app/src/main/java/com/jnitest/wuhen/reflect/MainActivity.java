package com.jnitest.wuhen.reflect;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.wuhen.common.IFunc;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends Activity {
    private Button ReflactButton;
    private Button InterfeceButton;
    private String TAG = "wangbin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File optimizedDexPath = new File(Environment.getDataDirectory().getAbsolutePath()
                                +File.separator+"wbTest.dex");
        Log.i(TAG,"optimizedDexPath: "+optimizedDexPath.getAbsolutePath());
        File dexOutputDir = this.getDir("dex",0);
        Log.i(TAG,"dexOutputDir: "+dexOutputDir.getAbsolutePath());
        DexClassLoader dexClassLoader = new DexClassLoader(optimizedDexPath.getAbsolutePath(),
                dexOutputDir.getAbsolutePath(),null,getClassLoader());

        //use reflact to invoke dex file dynamically
        Class clazz = null;
        try{
            Log.i(TAG,"+++FuncForReflect+++");
            clazz = dexClassLoader.loadClass("com.jnitest.wuhen.dynamicjni.FuncForReflect");
            Method[] methods= clazz.getMethods();
            for (Method m:methods){
                Log.i(TAG,"**********"+m.getName()+"**********");
            }
            Method method = clazz.getDeclaredMethod("funcForRecflect");
            Log.i(TAG,method.getName());
            method.setAccessible(true);
            String retValue = (String)method.invoke(clazz.newInstance());
            Toast.makeText(getApplicationContext(),retValue,Toast.LENGTH_LONG).show();
        }catch (Exception exception){
            exception.printStackTrace();
        }


        //use interface to invoke dex file dynamically
        Class clazz_interfece = null;
        try{
            Log.i(TAG,"+++FuncForReflect+++");
            clazz_interfece = dexClassLoader.loadClass("com.jnitest.wuhen.dynamicjni.Func");
            IFunc iFunc = (IFunc)clazz_interfece.newInstance();
            String retValue = iFunc.funcForInterfece();
            Toast.makeText(getApplicationContext(),retValue,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
