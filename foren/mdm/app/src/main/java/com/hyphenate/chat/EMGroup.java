package com.hyphenate.chat;

import android.graphics.Bitmap;
import com.hyphenate.chat.adapter.EMAGroup;
import com.hyphenate.chat.adapter.EMAGroupSetting;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EMGroup extends EMBase<EMAGroup> {
    public EMGroup(EMAGroup eMAGroup) {
        this.emaObject = eMAGroup;
    }

    void copyGroup(EMGroup eMGroup) {
    }

    @Deprecated
    public int getAffiliationsCount() {
        return ((EMAGroup) this.emaObject).getAffiliationsCount();
    }

    public String getDescription() {
        return ((EMAGroup) this.emaObject).getDescription();
    }

    Bitmap getGroupAvator() {
        new Exception("group avator not supported yet").printStackTrace();
        return null;
    }

    public String getGroupId() {
        return ((EMAGroup) this.emaObject).groupId();
    }

    public String getGroupName() {
        return ((EMAGroup) this.emaObject).groupSubject();
    }

    public int getMemberCount() {
        return ((EMAGroup) this.emaObject).getAffiliationsCount();
    }

    public List<String> getMembers() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(((EMAGroup) this.emaObject).getOwner());
        arrayList.addAll(((EMAGroup) this.emaObject).getMembers());
        return arrayList;
    }

    public String getOwner() {
        return ((EMAGroup) this.emaObject).getOwner();
    }

    public String groupSubject() {
        return ((EMAGroup) this.emaObject).groupSubject();
    }

    @Deprecated
    public boolean isAllowInvites() {
        return isMemberAllowToInvite();
    }

    public boolean isMemberAllowToInvite() {
        EMAGroupSetting groupSetting = ((EMAGroup) this.emaObject).groupSetting();
        return groupSetting == null || groupSetting.style() == 1;
    }

    public boolean isMemberOnly() {
        EMAGroupSetting groupSetting = ((EMAGroup) this.emaObject).groupSetting();
        if (groupSetting == null) {
            return true;
        }
        return groupSetting.style() == 0 || groupSetting.style() == 1 || groupSetting.style() == 2;
    }

    @Deprecated
    public boolean isMembersOnly() {
        return isMemberOnly();
    }

    public boolean isMsgBlocked() {
        return ((EMAGroup) this.emaObject).isMsgBlocked();
    }

    public boolean isPublic() {
        EMAGroupSetting groupSetting = ((EMAGroup) this.emaObject).groupSetting();
        if (groupSetting == null) {
            return true;
        }
        switch (groupSetting.style()) {
            case 0:
            case 1:
                return false;
            default:
                return true;
        }
    }

    public void setStyle(int i) {
        EMAGroupSetting groupSetting = ((EMAGroup) this.emaObject).groupSetting();
        if (groupSetting != null) {
            groupSetting.setStyle(i);
        }
    }

    public String toString() {
        String groupName = getGroupName();
        return groupName != null ? groupName : getGroupId();
    }
}
