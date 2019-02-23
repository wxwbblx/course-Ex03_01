package com.gshlib.wxw.myscan.tools;


import java.util.EventListener;

public interface ScanListener extends EventListener {
    boolean ScanCode(String var1, int var2);
}
