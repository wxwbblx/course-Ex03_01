package com.gshlib.wxw.myscan.tools;

import android.app.Activity;

/**
 * Created by ZCL on 2015/8/3.
 */
public abstract class ScannerDevice {
//    public static final String Barcode_Type_All ="Barcode_Type_All";
//    public static final String EAN8 = "EAN8";
//    public static final String UPCE =  "UPCE";
//    public static final String ISBN10 =  "ISBN10";
//    public static final String UPCA =  "UPCA";
//    public static final String EAN13 =  "EAN13";
//    public static final String ISBN13 = "ISBN13";
//    public static final String I25 = "I25";
//    public static final String DATABAR =  "DATABAR";
//    public static final String DATABAR_EXP = "DATABAR_EXP";
//    public static final String CODABAR = "CODABAR";
//    public static final String CODE39 =  "CODE39";
//    public static final String PDF417 = "PDF417";
//    public static final String QRCODE =  "QRCODE";
//    public static final String CODE93 =  "CODE93";
//    public static final String CODE128 = "CODE128";
//
//    public static final String Value_True="true";
//    public static final String Value_False="false";

    public static final int Barcode_Type_All =0;
    public static final int EAN8 = 1;
    public static final int UPCE =  2;
    public static final int ISBN10 = 3;
    public static final int UPCA = 4;
    public static final int EAN13 = 5;
    public static final int ISBN13 = 6;
    public static final int I25 = 7;
    public static final int DATABAR =  8;
    public static final int DATABAR_EXP = 9;
    public static final int CODABAR = 10;
    public static final int CODE39 = 11;
    public static final int PDF417 = 12;
    public static final int QRCODE =  13;
    public static final int CODE93 =  14;
    public static final int CODE128 = 15;
    public static final int D25 = 16;
    public static final int MSI = 17;
    public static final int Code11 = 18;
    public static final int Code93 = 19;
    public  static final int maxCodeType = 20;
    protected String strLastError="";

    public abstract boolean init(Activity activity);
    public abstract boolean setCodeConfig(int codeType,boolean enable);
    public abstract boolean start();
    public abstract boolean stop();
    public abstract boolean release();
    public abstract boolean addListener(ScannerDeviceListener listener);
    public abstract boolean addListenerEx(ScannerDeviceListenerEx listenerEx);
    public Activity activity =null;
    protected ScannerDeviceListener listener=null;
    protected ScannerDeviceListenerEx listenerEx = null;
    public String getLastError()
    {
        return strLastError;
    }
}
