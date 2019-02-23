package com.gshlib.wxw.myscan.tools;

public class CodeType {
    private int codeTypeID = 0;
    private boolean bEnable = true;

    public CodeType(int codeTypeID) {
        this.codeTypeID = codeTypeID;
    }

    public CodeType() {
    }

    public boolean isEnable() {
        return this.bEnable;
    }

    public void setEnable(boolean enable) {
        this.bEnable = enable;
    }

    public void setCodeTypeID(int codeTypeID) {
        this.codeTypeID = codeTypeID;
    }

    public int getCodeTypeID() {
        return this.codeTypeID;
    }

    public String getCodeTypeName() {
        return ScanSetting.getCodeTypeName(this.codeTypeID);
    }
}
