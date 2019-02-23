package com.gshlib.wxw.myscan.tools;

import java.util.EventListener;

/**
 * Created by ZCL on 2015/8/3.
 */
public interface ScannerDeviceListener extends EventListener {
    public boolean ScanCode(String code);
}
