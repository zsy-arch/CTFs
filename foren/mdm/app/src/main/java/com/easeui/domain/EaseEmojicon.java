package com.easeui.domain;

/* loaded from: classes.dex */
public class EaseEmojicon {
    private int bigIcon;
    private String bigIconPath;
    private String emojiText;
    private int icon;
    private String iconPath;
    private String identityCode;
    private String name;
    private Type type;

    /* loaded from: classes.dex */
    public enum Type {
        NORMAL,
        BIG_EXPRESSION
    }

    public EaseEmojicon() {
    }

    public EaseEmojicon(int icon, String emojiText) {
        this.icon = icon;
        this.emojiText = emojiText;
        this.type = Type.NORMAL;
    }

    public EaseEmojicon(int icon, String emojiText, Type type) {
        this.icon = icon;
        this.emojiText = emojiText;
        this.type = type;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getBigIcon() {
        return this.bigIcon;
    }

    public void setBigIcon(int dynamicIcon) {
        this.bigIcon = dynamicIcon;
    }

    public String getEmojiText() {
        return this.emojiText;
    }

    public void setEmojiText(String emojiText) {
        this.emojiText = emojiText;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getIconPath() {
        return this.iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getBigIconPath() {
        return this.bigIconPath;
    }

    public void setBigIconPath(String bigIconPath) {
        this.bigIconPath = bigIconPath;
    }

    public String getIdentityCode() {
        return this.identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public static String newEmojiText(int codePoint) {
        return Character.charCount(codePoint) == 1 ? String.valueOf(codePoint) : new String(Character.toChars(codePoint));
    }
}
