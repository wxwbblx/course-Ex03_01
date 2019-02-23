package com.gshlib.wxw.myscan.tools;

import android.app.Activity;
import android.os.Handler;
import android.view.View;


/**
 * Created by ZCL on 2015/8/3.
 */
public class ScannerFactory{

    private  static ScannerDevice instance=null;
    private static String strLastError="";
    private static String current_device_name =null;
    private static int iCreateBase = 0;
    private int iCreate = 0;

    private static Object lockobj = new Object();

    boolean isTaskRun = true;
    Handler mHandler ;

    private View view = null;
    public static String getLastError()
    {
        if(instance!=null)
        {
            return instance.getLastError();
        }else
        {
            return strLastError;
        }
    }
    private ScannerFactory()
    {
        iCreateBase++;
        iCreate=iCreateBase;
        isTaskRun = true;
        mHandler = new Handler();
        keepScreenOnThread.start();
    }
    public static ScannerFactory getInstance(String deviceName)
    {
        synchronized (lockobj) {
            if (instance != null) {
                if (!deviceName.equals(current_device_name)) {
                    instance.release();
                    current_device_name = null;
                    instance = null;
                }
            }
            if (instance == null) {
                current_device_name = deviceName;
                if (deviceName.equals("GC350")) {
                    instance = new Scanner_GC350();
                }
               /* else if (deviceName.equals("GC451")) {
                    instance = new Scanner_GC451();
                }*/
                else {
                    strLastError = "本应用不支持<" + deviceName + ">扫描设备";
                    current_device_name = null;
                    instance = null;
                }
            }
            if (instance != null) {

                return new ScannerFactory();
            }
            return null;
        }
    }

    public boolean init(Activity activity) {
        if(instance!=null)
        {
            return instance.init(activity);
        }
        return false;
    }

    public boolean setCodeConfig(int codeType, boolean enable) {
        if(instance!=null)
        {
            return instance.setCodeConfig(codeType, enable);
        }
        return false;
    }

    public boolean start() {
        if(instance!=null)
        {
            return instance.start();
        }
        return false;
    }

    public boolean stop() {
        if(instance!=null)
        {
            return instance.stop();
        }
        return false;
    }

    public boolean release() {
        synchronized (lockobj) {
            isTaskRun = false;
            view = null;
            if (instance != null) {
                if (iCreate == iCreateBase) {
                    boolean bRet = instance.release();
                    instance = null;
                    current_device_name = null;
                    return bRet;
                }
            }
            return true;
        }
    }

    public boolean addListener(ScannerDeviceListener listener) {
        if(instance!=null)
        {
            return instance.addListener(listener);
        }
        return false;
    }

    public boolean addListenerEx(ScannerDeviceListenerEx listenerEx) {
        if(instance!=null)
        {
            return instance.addListenerEx(listenerEx);
        }
        return false;
    }
    long endtime = 0;
    Thread keepScreenOnThread = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("keepScreenOnThread start");
            while (isTaskRun)
            {
                try {
                    Thread.sleep(100);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(System.currentTimeMillis() >= endtime && endtime!=0)
                {
                    System.out.println("keepScreenOnThread enter do false");
                    endtime = 0;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("keepScreenOnThread mHandler do false");
                            if(view!=null && instance!=null)
                            {
                                view.setKeepScreenOn(false);
                            }
                        }
                    });
                }
            }
            System.out.println("keepScreenOnThread stop");
        }
    });
    public void keepScreenOnDelay(int second,View view)
    {
        this.view = view;
        if(instance!=null) {
            view.setKeepScreenOn(true);
            endtime = System.currentTimeMillis() + second * 1000;
            System.out.println("new keepSceenOnDelay:" + String.valueOf(endtime));
        }
    }
}
