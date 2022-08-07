package com.easeui.domain;

import com.easeui.domain.EaseEmojicon;
import java.util.List;

/* loaded from: classes.dex */
public class EaseEmojiconGroupEntity {
    private List<EaseEmojicon> emojiconList;
    private int icon;
    private String name;
    private EaseEmojicon.Type type;

    public EaseEmojiconGroupEntity() {
    }

    public EaseEmojiconGroupEntity(int icon, List<EaseEmojicon> emojiconList) {
        this.icon = icon;
        this.emojiconList = emojiconList;
        this.type = EaseEmojicon.Type.NORMAL;
    }

    public EaseEmojiconGroupEntity(int icon, List<EaseEmojicon> emojiconList, EaseEmojicon.Type type) {
        this.icon = icon;
        this.emojiconList = emojiconList;
        this.type = type;
    }

    public List<EaseEmojicon> getEmojiconList() {
        return this.emojiconList;
    }

    public void setEmojiconList(List<EaseEmojicon> emojiconList) {
        this.emojiconList = emojiconList;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EaseEmojicon.Type getType() {
        return this.type;
    }

    public void setType(EaseEmojicon.Type type) {
        this.type = type;
    }
}
