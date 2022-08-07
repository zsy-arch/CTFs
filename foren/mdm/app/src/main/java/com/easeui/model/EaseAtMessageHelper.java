package com.easeui.model;

import android.text.TextUtils;
import com.easeui.EaseConstant;
import com.easeui.controller.EaseUI;
import com.easeui.domain.EaseUser;
import com.easeui.utils.EaseUserUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class EaseAtMessageHelper {
    private static EaseAtMessageHelper instance = null;
    private Set<String> atMeGroupList;
    private List<String> toAtUserList = new ArrayList();

    public static synchronized EaseAtMessageHelper get() {
        EaseAtMessageHelper easeAtMessageHelper;
        synchronized (EaseAtMessageHelper.class) {
            if (instance == null) {
                instance = new EaseAtMessageHelper();
            }
            easeAtMessageHelper = instance;
        }
        return easeAtMessageHelper;
    }

    private EaseAtMessageHelper() {
        this.atMeGroupList = null;
        this.atMeGroupList = EasePreferenceManager.getInstance().getAtMeGroups();
        if (this.atMeGroupList == null) {
            this.atMeGroupList = new HashSet();
        }
    }

    public void addAtUser(String username) {
        synchronized (this.toAtUserList) {
            if (!this.toAtUserList.contains(username)) {
                this.toAtUserList.add(username);
            }
        }
    }

    public boolean containsAtUsername(String content) {
        boolean z = false;
        if (!TextUtils.isEmpty(content)) {
            synchronized (this.toAtUserList) {
                Iterator<String> it = this.toAtUserList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String username = it.next();
                    String nick = username;
                    EaseUser user = EaseUserUtils.getUserInfo(username);
                    if (user != null) {
                        nick = user.getNick();
                    }
                    if (content.contains(nick)) {
                        z = true;
                        break;
                    }
                }
            }
        }
        return z;
    }

    public boolean containsAtAll(String content) {
        return content.contains(new StringBuilder().append("@").append(EaseUI.getInstance().getContext().getString(R.string.all_members)).toString());
    }

    public List<String> getAtMessageUsernames(String content) {
        EaseUser user;
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        synchronized (this.toAtUserList) {
            List<String> list = null;
            try {
                for (String username : this.toAtUserList) {
                    try {
                        String nick = username;
                        if (!(EaseUserUtils.getUserInfo(username) == null || (user = EaseUserUtils.getUserInfo(username)) == null)) {
                            nick = user.getNick();
                        }
                        if (content.contains(nick)) {
                            if (list == null) {
                                list = new ArrayList<>();
                            } else {
                                list = list;
                            }
                            list.add(username);
                        } else {
                            list = list;
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                return list;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public void parseMessages(List<EMMessage> messages) {
        int size = this.atMeGroupList.size();
        EMMessage[] msgs = (EMMessage[]) messages.toArray(new EMMessage[messages.size()]);
        for (EMMessage msg : msgs) {
            if (msg.getChatType() == EMMessage.ChatType.GroupChat) {
                String groupId = msg.getTo();
                try {
                    JSONArray jsonArray = msg.getJSONArrayAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG);
                    int i = 0;
                    while (true) {
                        if (i >= jsonArray.length()) {
                            break;
                        }
                        if (EMClient.getInstance().getCurrentUser().equals(jsonArray.getString(i)) && !this.atMeGroupList.contains(groupId)) {
                            this.atMeGroupList.add(groupId);
                            break;
                        }
                        i++;
                    }
                } catch (Exception e) {
                    String usernameStr = msg.getStringAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG, null);
                    if (usernameStr != null && usernameStr.toUpperCase().equals(EaseConstant.MESSAGE_ATTR_VALUE_AT_MSG_ALL) && !this.atMeGroupList.contains(groupId)) {
                        this.atMeGroupList.add(groupId);
                    }
                }
                if (this.atMeGroupList.size() != size) {
                    EasePreferenceManager.getInstance().setAtMeGroups(this.atMeGroupList);
                }
            }
        }
    }

    public Set<String> getAtMeGroups() {
        return this.atMeGroupList;
    }

    public void removeAtMeGroup(String groupId) {
        if (this.atMeGroupList.contains(groupId)) {
            this.atMeGroupList.remove(groupId);
            EasePreferenceManager.getInstance().setAtMeGroups(this.atMeGroupList);
        }
    }

    public boolean hasAtMeMsg(String groupId) {
        return this.atMeGroupList.contains(groupId);
    }

    public boolean isAtMeMsg(EMMessage message) {
        if (EaseUserUtils.getUserInfo(message.getFrom()) != null) {
            try {
                JSONArray jsonArray = message.getJSONArrayAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG);
                for (int i = 0; i < jsonArray.length(); i++) {
                    if (jsonArray.getString(i).equals(EMClient.getInstance().getCurrentUser())) {
                        return true;
                    }
                }
            } catch (Exception e) {
                String atUsername = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG, null);
                return atUsername != null && atUsername.toUpperCase().equals(EaseConstant.MESSAGE_ATTR_VALUE_AT_MSG_ALL);
            }
        }
        return false;
    }

    public JSONArray atListToJsonArray(List<String> atList) {
        JSONArray jArray = new JSONArray();
        int size = atList.size();
        for (int i = 0; i < size; i++) {
            jArray.put(atList.get(i));
        }
        return jArray;
    }

    public void cleanToAtUserList() {
        synchronized (this.toAtUserList) {
            this.toAtUserList.clear();
        }
    }
}
