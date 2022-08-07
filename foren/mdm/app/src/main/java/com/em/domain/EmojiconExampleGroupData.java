package com.em.domain;

import com.cdlinglu.common.MyApplication;
import com.easeui.domain.EaseEmojicon;
import com.easeui.domain.EaseEmojiconGroupEntity;
import com.vsf2f.f2f.R;
import java.util.Arrays;

/* loaded from: classes.dex */
public class EmojiconExampleGroupData {
    private static int[] icons = {R.drawable.icon_002_cover, R.drawable.icon_007_cover, R.drawable.icon_010_cover, R.drawable.icon_012_cover, R.drawable.icon_013_cover, R.drawable.icon_018_cover, R.drawable.icon_019_cover, R.drawable.icon_020_cover, R.drawable.icon_021_cover, R.drawable.icon_022_cover, R.drawable.icon_024_cover, R.drawable.icon_027_cover, R.drawable.icon_029_cover, R.drawable.icon_030_cover, R.drawable.icon_035_cover, R.drawable.icon_040_cover};
    private static int[] bigIcons = {R.drawable.icon_002, R.drawable.icon_007, R.drawable.icon_010, R.drawable.icon_012, R.drawable.icon_013, R.drawable.icon_018, R.drawable.icon_019, R.drawable.icon_020, R.drawable.icon_021, R.drawable.icon_022, R.drawable.icon_024, R.drawable.icon_027, R.drawable.icon_029, R.drawable.icon_030, R.drawable.icon_035, R.drawable.icon_040};
    private static final EaseEmojiconGroupEntity DATA = createData();

    private static EaseEmojiconGroupEntity createData() {
        EaseEmojiconGroupEntity emojiconGroupEntity = new EaseEmojiconGroupEntity();
        EaseEmojicon[] datas = new EaseEmojicon[icons.length];
        for (int i = 0; i < icons.length; i++) {
            datas[i] = new EaseEmojicon(icons[i], null, EaseEmojicon.Type.BIG_EXPRESSION);
            datas[i].setBigIcon(bigIcons[i]);
            datas[i].setName(MyApplication.getInstance().getApplicationContext().getString(R.string.emojicon_test_name));
            datas[i].setIdentityCode("em" + (i + 1000 + 1));
        }
        emojiconGroupEntity.setEmojiconList(Arrays.asList(datas));
        emojiconGroupEntity.setIcon(R.drawable.icon_gif1);
        emojiconGroupEntity.setType(EaseEmojicon.Type.BIG_EXPRESSION);
        return emojiconGroupEntity;
    }

    public static EaseEmojiconGroupEntity getData() {
        return DATA;
    }
}
