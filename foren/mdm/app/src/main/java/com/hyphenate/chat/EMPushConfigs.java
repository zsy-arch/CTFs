package com.hyphenate.chat;

import com.hyphenate.chat.adapter.EMAPushConfigs;

/* loaded from: classes2.dex */
public class EMPushConfigs extends EMBase<EMAPushConfigs> {
    public EMPushConfigs(EMAPushConfigs eMAPushConfigs) {
        this.emaObject = eMAPushConfigs;
    }

    public String getDisplayNickname() {
        return ((EMAPushConfigs) this.emaObject).getDisplayNickname();
    }

    public int getNoDisturbEndHour() {
        return ((EMAPushConfigs) this.emaObject).getNoDisturbEndHour();
    }

    public int getNoDisturbStartHour() {
        return ((EMAPushConfigs) this.emaObject).getNoDisturbStartHour();
    }

    public boolean isNoDisturbOn() {
        return ((EMAPushConfigs) this.emaObject).isNoDisturbOn();
    }
}
