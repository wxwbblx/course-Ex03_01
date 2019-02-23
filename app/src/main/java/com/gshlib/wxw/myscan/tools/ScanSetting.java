package com.gshlib.wxw.myscan.tools;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
import java.util.ArrayList;
import java.util.Iterator;

public class ScanSetting {
    public static final int EAN8 = 8;
    public static final int UPCE = 9;
    public static final int ISBN10 = 10;
    public static final int UPCA = 12;
    public static final int EAN13 = 13;
    public static final int ISBN13 = 14;
    public static final int I25 = 25;
    public static final int DATABAR = 34;
    public static final int DATABAR_EXP = 35;
    public static final int CODABAR = 38;
    public static final int CODE39 = 39;
    public static final int PDF417 = 57;
    public static final int QRCODE = 64;
    public static final int CODE93 = 93;
    public static final int CODE128 = 128;
    public static final int FinderViewMode_Full = 0;
    public static final int FinderViewMode_HalfW_HalfH = 1;
    public static final int FinderViewMode_FullW_HalfH = 2;
    public static final int FinderViewMode_FullW_QuarterH = 3;
    public static final int PreviewSizeMode_Default = 0;
    public static final int PreviewSizeMode_Min = 1;
    public static final int PreviewSizeMode_Max = 2;
    private static int finderViewMode = 0;
    private static int quality = 1;
    private static int x_Density = 3;
    private static int y_Density = 3;
    private static boolean playPromptBeep = true;
    private static boolean playPromptVibrator = true;
    private static int finderViewMaskColor = 520093951;
    private static int focusTime = 1000;
    private static boolean alwaysPreview = false;
    private static int previewSizeMode = 0;
    private static boolean zoomEnable = false;
    private static boolean focusEnable = true;
    private static int finderCrossColor = -16711936;
    private static boolean showFinderCross = true;
    private static boolean asyncDecode = false;
    private static boolean openTorch = false;
    private static boolean fitPreviewRatio = false;
    private static ArrayList<CodeType> codeTyepList = new ArrayList();

    public ScanSetting() {
    }

    public static int getFinderViewMode() {
        return finderViewMode;
    }

    public static void setFinderViewMode(int mode) {
        if (mode >= 0 && mode <= 3) {
            finderViewMode = mode;
        }

    }

    public static int getQuality() {
        return quality;
    }

    public static void setQuality(int qualityValue) {
        if (quality >= 0) {
            quality = qualityValue;
        }

    }

    public static int getX_Density() {
        return x_Density;
    }

    public static void setX_Density(int density) {
        x_Density = density;
    }

    public static int getY_Density() {
        return y_Density;
    }

    public static void setY_Density(int density) {
        y_Density = density;
    }

    public static boolean getPlayPromptBeep() {
        return playPromptBeep;
    }

    public static void setPlayPromptBeep(boolean enable) {
        playPromptBeep = enable;
    }

    public static boolean getPlayPromptVibrator() {
        return playPromptVibrator;
    }

    public static void setPlayPromptVibrator(boolean enable) {
        playPromptVibrator = enable;
    }

    public static int getFinderViewMaskColor() {
        return finderViewMaskColor;
    }

    public static void setFinderViewMaskColor(int color) {
        finderViewMaskColor = color;
    }

    public static int getFocusTime() {
        return focusTime;
    }

    public static void setFocusTime(int timeValue) {
        if (timeValue < 200) {
            timeValue = 200;
        }

        if (timeValue > 10000) {
            timeValue = 10000;
        }

        focusTime = timeValue;
    }

    public static boolean getAlwaysPreview() {
        return alwaysPreview;
    }

    public static void setAlwaysPreview(boolean enable) {
        alwaysPreview = enable;
    }

    public static int getPreviewSizeMode() {
        return previewSizeMode;
    }

    public static void setPreviewSizeMode(int mode) {
        if (mode < 0) {
            mode = 0;
        }

        if (mode > 2) {
            mode = 2;
        }

        previewSizeMode = mode;
    }

    public static boolean getZoomEnable() {
        return zoomEnable;
    }

    public static void setZoomEnable(boolean enable) {
        zoomEnable = enable;
    }

    public static boolean getFocusEnable() {
        return focusEnable;
    }

    public static void setFocusEnable(boolean enable) {
        focusEnable = enable;
    }

    public static int getFinderCrossColor() {
        return finderCrossColor;
    }

    public static void setFinderCrossColor(int color) {
        finderCrossColor = color;
    }

    public static boolean getShowFinderCross() {
        return showFinderCross;
    }

    public static void setShowFinderCross(boolean enable) {
        showFinderCross = enable;
    }

    public static boolean getAsyncDecode() {
        return asyncDecode;
    }

    public static void setAsyncDecode(boolean enable) {
        asyncDecode = enable;
    }

    public static boolean getOpenTorch() {
        return openTorch;
    }

    public static void setOpenTorch(boolean enable) {
        openTorch = enable;
    }

    public static boolean getFitPreviewRatio() {
        return fitPreviewRatio;
    }

    public static void setFitPreviewRatio(boolean enable) {
        fitPreviewRatio = enable;
    }

    public static void setCodeTypeEnable(int codeTypeID, boolean enable) {
        Iterator var2 = codeTyepList.iterator();

        while(var2.hasNext()) {
            CodeType codeType = (CodeType)var2.next();
            if (codeType.getCodeTypeID() == codeTypeID) {
                codeType.setEnable(enable);
                break;
            }
        }

    }

    public static boolean isCodeTypeEnable(int codeTypeID) {
        Iterator var1 = codeTyepList.iterator();

        CodeType codeType;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            codeType = (CodeType)var1.next();
        } while(codeType.getCodeTypeID() != codeTypeID);

        return codeType.isEnable();
    }

    public static void setCodeTypeEnable(boolean enable) {
        Iterator var1 = codeTyepList.iterator();

        while(var1.hasNext()) {
            CodeType codeType = (CodeType)var1.next();
            codeType.setEnable(enable);
        }

    }

    public static ArrayList<CodeType> getAllCodeType() {
        return codeTyepList;
    }

    public static String getOrientationString(int orientation) {
        switch(orientation) {
            case 0:
                return "上下↓";
            case 1:
                return "右左←";
            case 2:
                return "下上↑";
            case 3:
                return "左右→";
            default:
                return "未定义";
        }
    }

    public static String getCodeTypeName(int codeTypeID) {
        String CodeTypeName = "未知";
        switch(codeTypeID) {
            case 8:
                CodeTypeName = "EAN8";
                break;
            case 9:
                CodeTypeName = "UPCE";
                break;
            case 10:
                CodeTypeName = "ISBN10";
                break;
            case 12:
                CodeTypeName = "UPCA";
                break;
            case 13:
                CodeTypeName = "EAN13";
                break;
            case 14:
                CodeTypeName = "ISBN13";
                break;
            case 25:
                CodeTypeName = "I25";
                break;
            case 34:
                CodeTypeName = "DATABAR";
                break;
            case 35:
                CodeTypeName = "DATABAR_EXP";
                break;
            case 38:
                CodeTypeName = "CODABAR";
                break;
            case 39:
                CodeTypeName = "CODE39";
                break;
            case 57:
                CodeTypeName = "PDF417";
                break;
            case 64:
                CodeTypeName = "QRCODE";
                break;
            case 93:
                CodeTypeName = "CODE93";
                break;
            case 128:
                CodeTypeName = "CODE128";
        }

        return CodeTypeName;
    }

    static {
        CodeType codeType = new CodeType();
        codeType.setCodeTypeID(93);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(128);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(38);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(39);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(34);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(35);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(8);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(13);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(10);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(14);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(25);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(57);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(64);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(12);
        codeTyepList.add(codeType);
        codeType = new CodeType();
        codeType.setCodeTypeID(9);
        codeTyepList.add(codeType);
    }
}
