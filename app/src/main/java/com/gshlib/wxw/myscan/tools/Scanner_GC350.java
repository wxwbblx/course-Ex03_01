package com.gshlib.wxw.myscan.tools;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by ZCL on 2015/8/3.
 */
public class Scanner_GC350 extends ScannerDevice {
    private BarcodeDataBroadcastReceiver intentBarcodeDataReceiver = new BarcodeDataBroadcastReceiver();
    @Override
    public boolean addListenerEx(ScannerDeviceListenerEx listenerEx) {
        this.listenerEx = listenerEx;
        return false;
    }

    @Override
    public String getLastError() {
        return strLastError;
    }

    @Override
    public boolean init(Activity activity) {
        this.activity = activity;
        try {
            IntentFilter intentFilter = new IntentFilter("DATA_SCAN");
            activity.registerReceiver(intentBarcodeDataReceiver, intentFilter);



            Intent i = new Intent();
            i.setAction(datawedge.SCANNERINPUTPLUGIN);
            i.putExtra(datawedge.EXTRA_PARAMETER, "ENABLE_PLUGIN");
            activity.sendBroadcast(i);

            Intent TriggerButtonIntent = new Intent();
            TriggerButtonIntent.setAction(datawedge.SOFTSCANTRIGGER);
            TriggerButtonIntent.putExtra(datawedge.EXTRA_PARAMETER, datawedge.ENABLE_TRIGGERBUTTON);
            activity.sendBroadcast(TriggerButtonIntent);

            Intent BeepSoundIntent = new Intent();
            BeepSoundIntent.setAction(datawedge.SOFTSCANTRIGGER);
            BeepSoundIntent.putExtra(datawedge.EXTRA_PARAMETER, datawedge.ENABLE_BEEP);
            activity.sendBroadcast(BeepSoundIntent);

            Intent VibrateIntent = new Intent();
            VibrateIntent.setAction(datawedge.SOFTSCANTRIGGER);
            VibrateIntent.putExtra(datawedge.EXTRA_PARAMETER, datawedge.ENABLE_VIBRATE);
            activity.sendBroadcast(VibrateIntent);
        }catch (Exception ex)
        {
            strLastError = "初始化扫描头异常:"+ex.getMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean setCodeConfig(int codeType, boolean enable) {

        String configString;
        if(codeType!=0) {
            configString = getCodeConfigString(codeType, enable);
            if (configString != "") {
                Intent intent = new Intent();
                intent.setAction(datawedge.SCANNERINPUTPLUGIN);
                intent.putExtra(datawedge.EXTRA_PARAMETER, configString);
                activity.sendBroadcast(intent);
                return true;
            }
            return false;
        }else
        {
            for(int i=1;i<maxCodeType;i++)
            {
                configString = getCodeConfigString(i, enable);
                if (configString != "") {
                    Intent intent = new Intent();
                    intent.setAction(datawedge.SCANNERINPUTPLUGIN);
                    intent.putExtra(datawedge.EXTRA_PARAMETER, configString);
                    activity.sendBroadcast(intent);
                }
            }
            return true;
        }
    }

    @Override
    public boolean start() {
        Intent scannerIntent = new Intent();
        scannerIntent.setAction(datawedge.SOFTSCANTRIGGER);
        scannerIntent.putExtra(datawedge.EXTRA_PARAMETER,datawedge.START_SCANNING);
        activity.sendBroadcast(scannerIntent);
        return false;
    }

    @Override
    public boolean stop() {
        Intent scannerIntent = new Intent();
        scannerIntent.setAction(datawedge.SOFTSCANTRIGGER);
        scannerIntent.putExtra(datawedge.EXTRA_PARAMETER, datawedge.STOP_SCANNING);
        activity.sendBroadcast(scannerIntent);
        return false;
    }

    @Override
    public boolean release() {
        try {
            Intent TriggerButtonIntent = new Intent();
            TriggerButtonIntent.setAction(datawedge.SOFTSCANTRIGGER);
            TriggerButtonIntent.putExtra(datawedge.EXTRA_PARAMETER, datawedge.DISABLE_TRIGGERBUTTON);
            activity.sendBroadcast(TriggerButtonIntent);

            Intent BeepSoundIntent = new Intent();
            BeepSoundIntent.setAction(datawedge.SOFTSCANTRIGGER);
            BeepSoundIntent.putExtra(datawedge.EXTRA_PARAMETER,datawedge.DISABLE_BEEP);
            activity.sendBroadcast(BeepSoundIntent);

            Intent i = new Intent();
            i.setAction(datawedge.SCANNERINPUTPLUGIN);
            i.putExtra(datawedge.EXTRA_PARAMETER, "DISABLE_PLUGIN");
            activity.sendBroadcast(i);
        }catch (Exception ex) {
            strLastError = "释放扫描头异常:"+ex.getMessage();
            return false;
        }
        return true;
    }

    @Override
    public boolean addListener(ScannerDeviceListener listener) {
        this.listener = listener;
        return false;
    }

    private class BarcodeDataBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            String Barcode = arg1.getStringExtra(datawedge.DATA_STRING);
            int type = arg1.getIntExtra(datawedge.DATA_TYPE, 0);
            int length = arg1.getIntExtra(datawedge.DATA_LENGTH, 0);
            if(length>0) {
                if (listener != null) {
                    listener.ScanCode(Barcode);
                }
                if (listenerEx != null) {
                    listenerEx.ScanCode(Barcode, Integer.toString(type), 0);
                }
            }
        }
    }
    private static String getCodeConfigString(int codeType, boolean enable)
    {
        switch (codeType)
        {
            case ScannerDevice.EAN8:
                if(enable)
                {
                    return datawedge.EAN8_ENABLED;
                }else
                {
                    return datawedge.DISABLE_EAN8;
                }
            case ScannerDevice.UPCE:
                if(enable)
                {
                    return datawedge.ENABLE_UPCE;
                }else
                {
                    return datawedge.DISABLE_UPCE;
                }
            case ScannerDevice.ISBN10:
                if(enable)
                {
                    return datawedge.ENABLE_BOOKLAND_ISBN;
                }else
                {
                    return datawedge.DISABLE_BOOKLAND_ISBN;
                }
            case ScannerDevice.ISBN13:
                if(enable)
                {
                    return datawedge.ENABLE_BOOKLAND_ISBN;
                }else
                {
                    return datawedge.DISABLE_BOOKLAND_ISBN;
                }
            case ScannerDevice.EAN13:
                if(enable)
                {
                    return datawedge.ENABLE_EAN13;
                }else
                {
                    return datawedge.DISABLE_EAN13;
                }
            case ScannerDevice.I25:
                if(enable)
                {
                    return datawedge.ENABLE_I25;
                }else
                {
                    return datawedge.DISABLE_I25;
                }
            case ScannerDevice.D25:
                if(enable)
                {
                    return datawedge.ENABLE_D25;
                }else
                {
                    return datawedge.DISABLE_D25;
                }
            case ScannerDevice.CODABAR:
                if(enable)
                {
                    return datawedge.CODABAR_ENABLED;
                }else
                {
                    return datawedge.DISABLE_CODABAR;
                }
            case ScannerDevice.CODE39:
                if(enable)
                {
                    return datawedge.ENABLE_CODE39;
                }else
                {
                    return datawedge.DISABLE_CODE39;
                }
            case ScannerDevice.MSI:
                if(enable)
                {
                    return datawedge.ENABLE_MSI;
                }else
                {
                    return datawedge.DISABLE_MSI;
                }
            case ScannerDevice.Code11:
                if(enable)
                {
                    return datawedge.ENABLE_CODE11;
                }else
                {
                    return datawedge.DISABLE_CODE11;
                }
            case ScannerDevice.Code93:
                if(enable)
                {
                    return datawedge.ENABLE_CODE93;
                }else
                {
                    return datawedge.DISABLE_CODE93;
                }
            default:
                return  "";
        }
    }

}
