package com.easeui.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.easeui.controller.EaseUI;
import com.easeui.domain.EaseUser;
import com.em.DemoHelper;
import com.hy.frame.util.FolderUtil;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GroupBean;
import java.io.File;

/* loaded from: classes.dex */
public class EaseUserUtils {
    static EaseUI.EaseUserProfileProvider userProvider = EaseUI.getInstance().getUsersProfileProvider();
    static EaseUI.EaseGroupProfileProvider groupProvider = EaseUI.getInstance().getGroupsProfileProvider();
    static EaseUI.EaseContactsProfileProvider contactProvider = EaseUI.getInstance().getContactsProfileProvider();

    public static EaseUser getContactInfo(String username) {
        if (contactProvider != null) {
            return contactProvider.getContacts(username);
        }
        return null;
    }

    public static EaseUser getUserInfo(String username) {
        if (userProvider != null) {
            return userProvider.getUser(username);
        }
        return null;
    }

    public static GroupBean getGroupInfo(String groupId) {
        if (groupProvider != null) {
            return groupProvider.getGroup(groupId);
        }
        return null;
    }

    public static String getGroupBizId(String groupId) {
        GroupBean user;
        if (groupId == null || (user = getGroupInfo(groupId)) == null || user.getBizId() == null) {
            return null;
        }
        return user.getBizId();
    }

    public static void setUserNick(String username, TextView textView) {
        if (textView != null) {
            EaseUser user = getContactInfo(username);
            if (user == null) {
                user = getUserInfo(username);
            }
            if (user != null) {
                textView.setText(user.getNick());
            } else {
                textView.setText(username);
            }
        }
    }

    public static void setGroupName(GroupBean group, TextView textView) {
        if (textView == null) {
            return;
        }
        if (group == null || group.getGroupName() == null) {
            textView.setText(R.string.new_group_chat);
        } else {
            textView.setText(group.getGroupName());
        }
    }

    public static void upGroupName(String groupId, String groupName) {
        GroupBean group;
        if (groupId != null && groupName != null && (group = getGroupInfo(groupId)) != null) {
            group.setGroupName(groupName);
            DemoHelper.getInstance().saveGroup(group);
        }
    }

    public static void setMineAvatar(Context context, ImageView imageView) {
        if (imageView != null) {
            String path = DemoHelper.getInstance().getCurrentUserPic().getSpath();
            try {
                Glide.with(context).load(Integer.valueOf(Integer.parseInt(path))).into(imageView);
            } catch (Exception e) {
                Glide.with(context).load(path).error((int) R.mipmap.def_head).into(imageView);
            }
        }
    }

    public static void setUserAvatar(Context context, String username, ImageView imageView) {
        if (imageView != null) {
            EaseUser user = getContactInfo(username);
            if (user == null || user.getAvatar() == null) {
                user = getUserInfo(username);
            }
            if (user == null || user.getAvatar() == null) {
                Glide.with(context).load(Integer.valueOf((int) R.mipmap.def_head)).into(imageView);
                return;
            }
            String avater = user.getAvatar();
            if (!TextUtils.isEmpty(avater)) {
                Glide.with(context).load(avater).error((int) R.mipmap.def_head).into(imageView);
            } else {
                Glide.with(context).load(Integer.valueOf((int) R.mipmap.def_head)).into(imageView);
            }
        }
    }

    public static void setContactAvatar(Context context, String username, ImageView imageView) {
        EaseUser user = getContactInfo(username);
        if (user == null || user.getAvatar() == null) {
            Glide.with(context).load(Integer.valueOf((int) R.mipmap.def_head)).error((int) R.mipmap.def_head).into(imageView);
            return;
        }
        try {
            Glide.with(context).load(Integer.valueOf(Integer.parseInt(user.getAvatar()))).into(imageView);
        } catch (Exception e) {
            Glide.with(context).load(user.getAvatar()).error((int) R.mipmap.def_head).into(imageView);
        }
    }

    public static String getGroupPicPath(String groupId) {
        return FolderUtil.getCachePathHead() + File.separator + groupId;
    }

    public static void setGroupPic(GroupBean group, ImageView imageView) {
        if (group != null) {
            String headPath = getGroupPicPath(group.getGroupId());
            if (new File(headPath).exists()) {
                imageView.setImageURI(Uri.fromFile(new File(headPath)));
                return;
            }
        }
        imageView.setImageResource(R.drawable.ease_group_icon);
    }

    public static void setAvatarByPath(Context context, String urlPath, ImageView imageView) {
        if (urlPath != null) {
            try {
                Glide.with(context).load(Integer.valueOf(Integer.parseInt(urlPath))).into(imageView);
            } catch (Exception e) {
                Glide.with(context).load(urlPath).error((int) R.mipmap.def_head).into(imageView);
            }
        } else {
            Glide.with(context).load(Integer.valueOf((int) R.mipmap.def_head)).error((int) R.mipmap.def_head).into(imageView);
        }
    }

    public static String getSexStr(int gender) {
        switch (gender) {
            case 0:
                return "女";
            case 1:
                return "男";
            default:
                return "";
        }
    }
}
