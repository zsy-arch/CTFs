package com.cdlinglu.utils;

/* loaded from: classes.dex */
public enum PriceUnitEmue {
    tim("次"),
    sig("单"),
    sin("个"),
    wid("幅"),
    fan("扇"),
    min("分钟"),
    hou("小时"),
    day("天"),
    wek("周"),
    mon("月"),
    par("份"),
    clz("课时"),
    neg("面议"),
    oth("其他");
    
    private String unit;

    public String getUnit() {
        return this.unit;
    }

    PriceUnitEmue(String unit) {
        this.unit = unit;
    }
}
