package com.vsf2f.f2f.bean;

import com.easeui.utils.EaseCommonUtils;

/* loaded from: classes2.dex */
public class PhoneBean {
    private boolean check;
    private String initial;
    private String name;
    private String phone;
    private int type;

    public PhoneBean() {
    }

    public PhoneBean(String phone, String name, int type) {
        this.name = name;
        this.type = type;
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isCheck() {
        return this.check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getInitial() {
        if (this.initial == null) {
            this.initial = EaseCommonUtils.setUserInitialLetter(getName());
        }
        return this.initial;
    }

    public String toString() {
        return "PhoneInfo{name='" + this.name + "', number='" + this.phone + "'}";
    }
}
