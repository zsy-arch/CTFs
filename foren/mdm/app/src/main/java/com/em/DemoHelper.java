package com.em;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.services.core.AMapException;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.common.MyHttpClient;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.DeviceTool;
import com.cdlinglu.utils.TimeUtil;
import com.easeui.EaseConstant;
import com.easeui.controller.EaseUI;
import com.easeui.domain.EaseEmojicon;
import com.easeui.domain.EaseUser;
import com.easeui.model.EaseAtMessageHelper;
import com.easeui.model.EaseNotifier;
import com.easeui.utils.EaseCommonUtils;
import com.em.db.DemoDBManager;
import com.em.db.InviteMessgeDao;
import com.em.domain.EmojiconExampleGroupData;
import com.em.domain.InviteMessage;
import com.em.domain.RobotUser;
import com.em.receiver.CallReceiver;
import com.em.ui.ChatActivity;
import com.em.ui.VideoCallActivity;
import com.em.ui.VoiceCallActivity;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.IMyHttpListener;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMGroupChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.EMPrivateConstant;
import com.hyphenate.util.HanziToPinyin;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.AccountBean;
import com.vsf2f.f2f.bean.ChatImageBean;
import com.vsf2f.f2f.bean.ConfigBean;
import com.vsf2f.f2f.bean.FriendsListBean;
import com.vsf2f.f2f.bean.GroupBean;
import com.vsf2f.f2f.bean.PhoneBean;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.bean.UserPicBean;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.dialog.SysMsgDialog;
import com.vsf2f.f2f.ui.otay.ManorTreeDialog;
import com.vsf2f.f2f.ui.otay.OtayActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DemoHelper {
    protected static final String TAG = "DemoHelper";
    private static DemoHelper instance = null;
    private Context appContext;
    private LocalBroadcastManager broadcastManager;
    private CallReceiver callReceiver;
    private ConfigBean configBean;
    private EMConnectionListener connectionListener;
    private Map<String, EaseUser> contactsList;
    private Context context;
    private String deviceId;
    private String deviceModel;
    private EaseUI easeUI;
    private Map<String, GroupBean> groupsList;
    private InviteMessgeDao inviteMessgeDao;
    private boolean isGroupAndContactListenerRegisted;
    private int isOpenStore;
    public boolean isVideoCalling;
    public boolean isVoiceCalling;
    private String nickname;
    private Map<String, RobotUser> robotList;
    private List<DataSyncListener> syncBlackListListeners;
    private List<DataSyncListener> syncContactInfosListeners;
    private List<DataSyncListener> syncContactsListeners;
    private List<DataSyncListener> syncGroupsListeners;
    private String username;
    private Map<String, EaseUser> usersList;
    private String version;
    public EMMessageListener messageListener = null;
    private DemoModel demoModel = null;
    private boolean isSyncingGroupsWithServer = false;
    private boolean isSyncingContactsWithServer = false;
    private boolean isSyncingBlackListWithServer = false;
    private boolean isSyncingContactInfosWithServer = false;
    private boolean isGroupsSyncedWithServer = false;
    private boolean isContactsSyncedWithServer = false;
    private boolean isBlackListSyncedWithServer = false;
    public boolean isConflict = false;

    /* loaded from: classes.dex */
    public interface DataSyncListener {
        void onSyncComplete(boolean z);
    }

    private DemoHelper() {
    }

    public static synchronized DemoHelper getInstance() {
        DemoHelper demoHelper;
        synchronized (DemoHelper.class) {
            if (instance == null) {
                instance = new DemoHelper();
            }
            demoHelper = instance;
        }
        return demoHelper;
    }

    public void init(Context context) {
        this.context = context;
        this.demoModel = new DemoModel(context);
        if (EaseUI.getInstance().init(context, initChatOptions())) {
            this.appContext = context;
            this.easeUI = EaseUI.getInstance();
            setEaseUIProviders();
            UserShared.init(context);
            int minBitRate = UserShared.getInstance().getCallMinVideoKbps();
            if (minBitRate != -1) {
                EMClient.getInstance().callManager().getCallOptions().setMinVideoKbps(minBitRate);
            }
            int maxBitRate = UserShared.getInstance().getCallMaxVideoKbps();
            if (maxBitRate != -1) {
                EMClient.getInstance().callManager().getCallOptions().setMaxVideoKbps(maxBitRate);
            }
            int maxFrameRate = UserShared.getInstance().getCallMaxFrameRate();
            if (maxFrameRate != -1) {
                EMClient.getInstance().callManager().getCallOptions().setMaxVideoFrameRate(maxFrameRate);
            }
            int audioSampleRate = UserShared.getInstance().getCallAudioSampleRate();
            if (audioSampleRate != -1) {
                EMClient.getInstance().callManager().getCallOptions().setAudioSampleRate(audioSampleRate);
            }
            String resolution = UserShared.getInstance().getCallBackCameraResolution();
            if (resolution.equals("")) {
                resolution = UserShared.getInstance().getCallFrontCameraResolution();
            }
            String[] wh = resolution.split(EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME);
            if (wh.length == 2) {
                try {
                    EMClient.getInstance().callManager().getCallOptions().setVideoResolution(new Integer(wh[0]).intValue(), new Integer(wh[1]).intValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            EMClient.getInstance().callManager().getCallOptions().enableFixedVideoResolution(UserShared.getInstance().isCallFixedVideoResolution());
            EMClient.getInstance().callManager().getCallOptions().setIsSendPushIfOffline(getModel().isPushCall());
            setGlobalListeners();
            this.broadcastManager = LocalBroadcastManager.getInstance(this.appContext);
            initDbDao();
        }
    }

    private EMOptions initChatOptions() {
        Log.d(TAG, "init HuanXin Options");
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);
        options.setRequireAck(true);
        options.setRequireDeliveryAck(false);
        options.setGCMNumber("324169311137");
        options.setMipushConfig("2882303761517426801", "5381742660801");
        options.setHuaweiPushAppId("10492024");
        if (!(!this.demoModel.isCustomServerEnable() || this.demoModel.getRestServer() == null || this.demoModel.getIMServer() == null)) {
            options.setRestServer(this.demoModel.getRestServer());
            options.setIMServer(this.demoModel.getIMServer());
            if (this.demoModel.getIMServer().contains(":")) {
                options.setIMServer(this.demoModel.getIMServer().split(":")[0]);
                options.setImPort(Integer.valueOf(this.demoModel.getIMServer().split(":")[1]).intValue());
            }
        }
        options.allowChatroomOwnerLeave(getModel().isChatroomOwnerLeaveAllowed());
        options.setDeleteMessagesAsExitGroup(getModel().isDeleteMessagesAsExitGroup());
        options.setAutoAcceptGroupInvitation(getModel().isAutoAcceptGroupInvitation());
        return options;
    }

    private void setEaseUIProviders() {
        this.easeUI.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() { // from class: com.em.DemoHelper.1
            @Override // com.easeui.controller.EaseUI.EaseUserProfileProvider
            public EaseUser getUser(String username) {
                return DemoHelper.this.getUserInfo(username);
            }
        });
        this.easeUI.setContactsProfileProvider(new EaseUI.EaseContactsProfileProvider() { // from class: com.em.DemoHelper.2
            @Override // com.easeui.controller.EaseUI.EaseContactsProfileProvider
            public EaseUser getContacts(String contactName) {
                return DemoHelper.this.getContactInfo(contactName);
            }
        });
        this.easeUI.setGroupProfileProvider(new EaseUI.EaseGroupProfileProvider() { // from class: com.em.DemoHelper.3
            @Override // com.easeui.controller.EaseUI.EaseGroupProfileProvider
            public GroupBean getGroup(String groupId) {
                return DemoHelper.this.getGroupInfo(groupId);
            }
        });
        this.easeUI.setSettingsProvider(new EaseUI.EaseSettingsProvider() { // from class: com.em.DemoHelper.4
            @Override // com.easeui.controller.EaseUI.EaseSettingsProvider
            public boolean isSpeakerOpened() {
                return DemoHelper.this.demoModel.getSettingMsgSpeaker();
            }

            @Override // com.easeui.controller.EaseUI.EaseSettingsProvider
            public boolean isMsgVibrateAllowed(EMMessage message) {
                return DemoHelper.this.demoModel.getSettingMsgVibrate();
            }

            @Override // com.easeui.controller.EaseUI.EaseSettingsProvider
            public boolean isMsgSoundAllowed(EMMessage message) {
                return DemoHelper.this.demoModel.getSettingMsgSound();
            }

            @Override // com.easeui.controller.EaseUI.EaseSettingsProvider
            public boolean isMsgNotifyAllowed(EMMessage message) {
                String chatUsename;
                List<String> notNotifyIds;
                if (message == null) {
                    return DemoHelper.this.demoModel.getSettingMsgNotification();
                }
                if (!DemoHelper.this.demoModel.getSettingMsgNotification()) {
                    return false;
                }
                if (message.getChatType() == EMMessage.ChatType.Chat) {
                    chatUsename = message.getFrom();
                    notNotifyIds = DemoHelper.this.demoModel.getDisabledIds();
                } else if (!DemoHelper.this.demoModel.getSettingGroupMsgSound()) {
                    return false;
                } else {
                    chatUsename = message.getTo();
                    notNotifyIds = DemoHelper.this.demoModel.getDisabledGroups();
                }
                return notNotifyIds == null || !notNotifyIds.contains(chatUsename);
            }
        });
        this.easeUI.setEmojiconInfoProvider(new EaseUI.EaseEmojiconInfoProvider() { // from class: com.em.DemoHelper.5
            @Override // com.easeui.controller.EaseUI.EaseEmojiconInfoProvider
            public EaseEmojicon getEmojiconInfo(String emojiconIdentityCode) {
                for (EaseEmojicon emojicon : EmojiconExampleGroupData.getData().getEmojiconList()) {
                    if (emojicon.getIdentityCode().equals(emojiconIdentityCode)) {
                        return emojicon;
                    }
                }
                return null;
            }

            @Override // com.easeui.controller.EaseUI.EaseEmojiconInfoProvider
            public Map<String, Object> getTextEmojiconMapping() {
                return null;
            }
        });
        this.easeUI.getNotifier().setNotificationInfoProvider(new EaseNotifier.EaseNotificationInfoProvider() { // from class: com.em.DemoHelper.6
            @Override // com.easeui.model.EaseNotifier.EaseNotificationInfoProvider
            public String getTitle(EMMessage message) {
                return null;
            }

            @Override // com.easeui.model.EaseNotifier.EaseNotificationInfoProvider
            public int getSmallIcon(EMMessage message) {
                return 0;
            }

            @Override // com.easeui.model.EaseNotifier.EaseNotificationInfoProvider
            public String getDisplayedText(EMMessage message) {
                String ticker = EaseCommonUtils.getMessageDigest(message, DemoHelper.this.appContext);
                if (message.getType() == EMMessage.Type.TXT) {
                    ticker = ticker.replaceAll("\\[.{2,3}\\]", "[表情]");
                }
                EaseUser user = DemoHelper.this.getContactInfo(message.getFrom());
                String name = user != null ? user.getNick() : message.getFrom();
                return EaseAtMessageHelper.get().isAtMeMsg(message) ? String.format(DemoHelper.this.appContext.getString(R.string.at_your_in_group), name) : name + ": " + ticker;
            }

            @Override // com.easeui.model.EaseNotifier.EaseNotificationInfoProvider
            public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
                return null;
            }

            @Override // com.easeui.model.EaseNotifier.EaseNotificationInfoProvider
            public Intent getLaunchIntent(EMMessage message) {
                Intent intent = new Intent(DemoHelper.this.appContext, ChatActivity.class);
                if (DemoHelper.this.isVideoCalling) {
                    return new Intent(DemoHelper.this.appContext, VideoCallActivity.class);
                }
                if (DemoHelper.this.isVoiceCalling) {
                    return new Intent(DemoHelper.this.appContext, VoiceCallActivity.class);
                }
                Bundle bundle = new Bundle();
                EMMessage.ChatType chatType = message.getChatType();
                if (chatType == EMMessage.ChatType.Chat) {
                    bundle.putString("username", message.getFrom());
                    bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, 1);
                } else {
                    bundle.putString("username", message.getTo());
                    if (chatType == EMMessage.ChatType.GroupChat) {
                        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, 2);
                    } else {
                        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, 3);
                    }
                }
                intent.putExtra(Constant.BUNDLE, bundle);
                return intent;
            }
        });
    }

    protected void setGlobalListeners() {
        this.syncGroupsListeners = new ArrayList();
        this.syncContactsListeners = new ArrayList();
        this.syncBlackListListeners = new ArrayList();
        this.syncContactInfosListeners = new ArrayList();
        this.isGroupsSyncedWithServer = this.demoModel.isGroupsSynced();
        this.isContactsSyncedWithServer = this.demoModel.isContactSynced();
        this.isBlackListSyncedWithServer = this.demoModel.isBacklistSynced();
        this.connectionListener = new EMConnectionListener() { // from class: com.em.DemoHelper.7
            @Override // com.hyphenate.EMConnectionListener
            public void onDisconnected(int error) {
                if (error != 207) {
                    if (error == 206) {
                        DemoHelper.this.onConnectionConflict();
                    } else {
                        if (error == 300) {
                        }
                    }
                }
            }

            @Override // com.hyphenate.EMConnectionListener
            public void onConnected() {
                if (DemoHelper.this.isGroupsSyncedWithServer && DemoHelper.this.isContactsSyncedWithServer) {
                    EMLog.d(DemoHelper.TAG, "group and contact already synced with server");
                }
            }
        };
        IntentFilter callFilter = new IntentFilter(EMClient.getInstance().callManager().getIncomingCallBroadcastAction());
        if (this.callReceiver == null) {
            this.callReceiver = new CallReceiver();
        }
        this.appContext.registerReceiver(this.callReceiver, callFilter);
        EMClient.getInstance().addConnectionListener(this.connectionListener);
        registerGroupAndContactListener();
        registerMessageListener();
    }

    private void initDbDao() {
        this.inviteMessgeDao = new InviteMessgeDao(this.appContext);
    }

    public void registerGroupAndContactListener() {
        if (!this.isGroupAndContactListenerRegisted) {
            EMClient.getInstance().groupManager().addGroupChangeListener(new MyGroupChangeListener());
            EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
            this.isGroupAndContactListenerRegisted = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MyGroupChangeListener implements EMGroupChangeListener {
        MyGroupChangeListener() {
        }

        @Override // com.hyphenate.EMGroupChangeListener
        public void onInvitationReceived(String groupId, String groupName, String inviter, String reason) {
            new InviteMessgeDao(DemoHelper.this.appContext).deleteMessage(groupId);
            InviteMessage msg = new InviteMessage();
            msg.setFrom(groupId);
            msg.setTime(System.currentTimeMillis());
            msg.setGroupId(groupId);
            msg.setGroupName(groupName);
            msg.setReason(reason);
            msg.setGroupInviter(inviter);
            Log.d(DemoHelper.TAG, "receive invitation to join the group：" + groupName);
            msg.setStatus(InviteMessage.InviteMesageStatus.GROUPINVITATION);
            DemoHelper.this.notifyNewInviteMessage(msg);
            DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
        }

        @Override // com.hyphenate.EMGroupChangeListener
        public void onInvitationAccepted(String groupId, String invitee, String reason) {
            new InviteMessgeDao(DemoHelper.this.appContext).deleteMessage(groupId);
            boolean hasGroup = false;
            EMGroup _group = null;
            Iterator<EMGroup> it = EMClient.getInstance().groupManager().getAllGroups().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EMGroup group = it.next();
                if (group.getGroupId().equals(groupId)) {
                    hasGroup = true;
                    _group = group;
                    break;
                }
            }
            if (hasGroup) {
                InviteMessage msg = new InviteMessage();
                msg.setFrom(groupId);
                msg.setTime(System.currentTimeMillis());
                msg.setGroupId(groupId);
                if (_group != null) {
                    groupId = _group.getGroupName();
                }
                msg.setGroupName(groupId);
                msg.setReason(reason);
                msg.setGroupInviter(invitee);
                Log.d(DemoHelper.TAG, invitee + "Accept to join the group：" + _group.getGroupName());
                msg.setStatus(InviteMessage.InviteMesageStatus.GROUPINVITATION_ACCEPTED);
                DemoHelper.this.notifyNewInviteMessage(msg);
                DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
            }
        }

        @Override // com.hyphenate.EMGroupChangeListener
        public void onInvitationDeclined(String groupId, String invitee, String reason) {
            new InviteMessgeDao(DemoHelper.this.appContext).deleteMessage(groupId);
            EMGroup group = null;
            Iterator<EMGroup> it = EMClient.getInstance().groupManager().getAllGroups().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EMGroup _group = it.next();
                if (_group.getGroupId().equals(groupId)) {
                    group = _group;
                    break;
                }
            }
            if (group != null) {
                InviteMessage msg = new InviteMessage();
                msg.setFrom(groupId);
                msg.setTime(System.currentTimeMillis());
                msg.setGroupId(groupId);
                msg.setGroupName(group.getGroupName());
                msg.setReason(reason);
                msg.setGroupInviter(invitee);
                Log.d(DemoHelper.TAG, invitee + "Declined to join the group：" + group.getGroupName());
                msg.setStatus(InviteMessage.InviteMesageStatus.GROUPINVITATION_DECLINED);
                DemoHelper.this.notifyNewInviteMessage(msg);
                DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
            }
        }

        @Override // com.hyphenate.EMGroupChangeListener
        public void onUserRemoved(String groupId, String groupName) {
            DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
        }

        @Override // com.hyphenate.EMGroupChangeListener
        public void onGroupDestroyed(String groupId, String groupName) {
            DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
        }

        @Override // com.hyphenate.EMGroupChangeListener
        public void onRequestToJoinReceived(String groupId, String groupName, String applyer, String reason) {
            InviteMessage msg = new InviteMessage();
            msg.setFrom(applyer);
            msg.setTime(System.currentTimeMillis());
            msg.setGroupId(groupId);
            msg.setGroupName(groupName);
            msg.setReason(reason);
            Log.d(DemoHelper.TAG, applyer + " Apply to join group：" + groupName);
            msg.setStatus(InviteMessage.InviteMesageStatus.BEAPPLYED);
            DemoHelper.this.notifyNewInviteMessage(msg);
            DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
        }

        @Override // com.hyphenate.EMGroupChangeListener
        public void onRequestToJoinAccepted(String groupId, String groupName, String accepter) {
            String st4 = DemoHelper.this.appContext.getString(R.string.agreed_to_your_group_chat_application);
            EMMessage msg = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
            msg.setChatType(EMMessage.ChatType.GroupChat);
            msg.setFrom(accepter);
            msg.setTo(groupId);
            msg.setMsgId(UUID.randomUUID().toString());
            msg.addBody(new EMTextMessageBody(accepter + HanziToPinyin.Token.SEPARATOR + st4));
            msg.setStatus(EMMessage.Status.SUCCESS);
            EMClient.getInstance().chatManager().saveMessage(msg);
            DemoHelper.this.getNotifier().vibrateAndPlayTone(msg);
            DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
        }

        @Override // com.hyphenate.EMGroupChangeListener
        public void onRequestToJoinDeclined(String groupId, String groupName, String decliner, String reason) {
        }

        @Override // com.hyphenate.EMGroupChangeListener
        public void onAutoAcceptInvitationFromGroup(String groupId, String inviter, String inviteMessage) {
            String st3 = DemoHelper.this.appContext.getString(R.string.Invite_you_to_join_a_group_chat);
            EMMessage msg = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
            msg.setChatType(EMMessage.ChatType.GroupChat);
            msg.setFrom(inviter);
            msg.setTo(groupId);
            msg.setMsgId(UUID.randomUUID().toString());
            msg.addBody(new EMTextMessageBody(inviter + HanziToPinyin.Token.SEPARATOR + st3));
            msg.setStatus(EMMessage.Status.SUCCESS);
            EMClient.getInstance().chatManager().saveMessage(msg);
            DemoHelper.this.getNotifier().vibrateAndPlayTone(msg);
            EMLog.d(DemoHelper.TAG, "onAutoAcceptInvitationFromGroup groupId:" + groupId);
            DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_GROUP_CHANAGED));
        }
    }

    /* loaded from: classes.dex */
    public class MyContactListener implements EMContactListener {
        public MyContactListener() {
        }

        @Override // com.hyphenate.EMContactListener
        public void onContactAdded(String username) {
            Map<String, EaseUser> localUsers = DemoHelper.this.getContactList();
            Map<String, EaseUser> toAddUsers = new HashMap<>();
            EaseUser user = new EaseUser(username);
            if (!localUsers.containsKey(username)) {
                DemoHelper.this.saveContact(user);
            }
            toAddUsers.put(username, user);
            localUsers.putAll(toAddUsers);
            DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_CONTACT_CHANAGED));
        }

        @Override // com.hyphenate.EMContactListener
        public void onContactDeleted(String username) {
            DemoHelper.getInstance().getContactList().remove(username);
            DemoHelper.this.deleteContact(username);
            DemoHelper.this.inviteMessgeDao.deleteMessage(username);
            DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_CONTACT_CHANAGED));
        }

        @Override // com.hyphenate.EMContactListener
        public void onContactInvited(String username, String reason) {
            for (InviteMessage inviteMessage : DemoHelper.this.inviteMessgeDao.getMessagesList()) {
                if (inviteMessage.getGroupId() == null && inviteMessage.getFrom().equals(username)) {
                    DemoHelper.this.inviteMessgeDao.deleteMessage(username);
                }
            }
            InviteMessage msg = new InviteMessage();
            msg.setFrom(username);
            msg.setTime(System.currentTimeMillis());
            msg.setReason(reason);
            Log.d(DemoHelper.TAG, username + "apply to be your friend,reason: " + reason);
            msg.setStatus(InviteMessage.InviteMesageStatus.BEINVITEED);
            DemoHelper.this.notifyNewInviteMessage(msg);
            DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_CONTACT_CHANAGED));
        }

        @Override // com.hyphenate.EMContactListener
        public void onFriendRequestAccepted(String username) {
            for (InviteMessage inviteMessage : DemoHelper.this.inviteMessgeDao.getMessagesList()) {
                if (inviteMessage.getFrom().equals(username)) {
                    return;
                }
            }
            InviteMessage msg = new InviteMessage();
            msg.setFrom(username);
            msg.setTime(System.currentTimeMillis());
            Log.d(DemoHelper.TAG, username + "accept your request");
            msg.setStatus(InviteMessage.InviteMesageStatus.BEAGREED);
            DemoHelper.this.notifyNewInviteMessage(msg);
            DemoHelper.this.broadcastManager.sendBroadcast(new Intent(EaseConstant.ACTION_CONTACT_CHANAGED));
        }

        @Override // com.hyphenate.EMContactListener
        public void onFriendRequestDeclined(String username) {
            Log.d(username, username + " refused to your request");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNewInviteMessage(InviteMessage msg) {
        if (this.inviteMessgeDao == null) {
            this.inviteMessgeDao = new InviteMessgeDao(this.appContext);
        }
        getNotifier().vibrateAndPlayTone(null);
        this.inviteMessgeDao.saveMessage(msg);
    }

    public void clearNewInviteMessage() {
        if (this.inviteMessgeDao == null) {
            this.inviteMessgeDao = new InviteMessgeDao(this.appContext);
        }
        this.inviteMessgeDao.setReadAllNotifyCount();
    }

    public void getServiceTime(final Context context) {
        new MyHttpClient(context, new IMyHttpListener() { // from class: com.em.DemoHelper.8
            @Override // com.hy.http.IMyHttpListener
            public void onRequestSuccess(ResultInfo result) {
                try {
                    long serviceTime = TimeUtil.getLongTime(new JSONObject((String) result.getObj()).optString("currentTime"), null);
                    long cusTime = System.currentTimeMillis();
                    long diff = cusTime - serviceTime;
                    MyLog.e("diffTime=" + cusTime + "-" + serviceTime + "=" + diff);
                    AppShare.get(context).putLong("diffTime", diff);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.hy.http.IMyHttpListener
            public void onRequestError(ResultInfo result) {
            }
        }).get(R.string.GET_SERVER_TIME, ComUtil.getXDDApi(context, context.getString(R.string.GET_SERVER_TIME)));
    }

    private boolean isRunningForeground(Context context) {
        String packageName = context.getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName) && appProcess.importance == 100) {
                return true;
            }
        }
        return false;
    }

    public String getVersion() {
        if (this.version == null) {
            this.version = ComUtil.getVersion(this.context);
        }
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    protected void registerMessageListener() {
        this.messageListener = new EMMessageListener() { // from class: com.em.DemoHelper.9
            private BroadcastReceiver broadCastReceiver = null;

            @Override // com.hyphenate.EMMessageListener
            public void onMessageReceived(List<EMMessage> messages) {
                EMConversation.EMConversationType var2;
                for (EMMessage message : messages) {
                    EMLog.d(DemoHelper.TAG, "onMessageReceived id : " + message.getMsgId());
                    if (!DemoHelper.this.easeUI.hasForegroundActivies()) {
                        DemoHelper.this.getNotifier().onNewMsg(message);
                    }
                    if (message.getType() == EMMessage.Type.IMAGE) {
                        EMImageMessageBody imgBody = (EMImageMessageBody) message.getBody();
                        ChatImageBean imageBean = new ChatImageBean();
                        imageBean.setMsgid(message.getMsgId());
                        imageBean.setUsername(DemoHelper.this.username);
                        imageBean.setChatname(message.getChatType() == EMMessage.ChatType.Chat ? message.getFrom() : message.getTo());
                        imageBean.setHttpurl(imgBody.getRemoteUrl());
                        imageBean.setLocalpath(imgBody.getLocalUrl());
                        DemoDBManager.getInstance().saveImage(imageBean);
                    }
                    if (message.getStringAttribute("type", "").equals("send_recall")) {
                        try {
                            message.setUnread(false);
                            String messageId = message.getStringAttribute("messageId", "");
                            String from = message.getStringAttribute("recall_conversationId", "");
                            if (message.getChatType() == EMMessage.ChatType.GroupChat) {
                                var2 = EMConversation.EMConversationType.GroupChat;
                            } else {
                                var2 = EMConversation.EMConversationType.Chat;
                            }
                            EMClient.getInstance().chatManager().getConversation(from, var2).removeMessage(messageId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            JSONObject extJson = new JSONObject(message.getStringAttribute(MessageEncoder.ATTR_EXT));
                            String extType = extJson.optString("type", "");
                            if (extType.equals("temp") || extType.equals("verify")) {
                                if (extJson.optString("bizType", "").equals("verify")) {
                                    int isVerify = extJson.optJSONObject("tempDatas").optInt("isVerify", 2);
                                    if (isVerify == 1) {
                                        UserShared.getInstance().setIsVerify(1);
                                    } else if (isVerify == 0) {
                                        UserShared.getInstance().setIsVerify(0);
                                    }
                                    MyLog.e("isVerify", isVerify + "");
                                }
                            }
                        } catch (HyphenateException | JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }

            @Override // com.hyphenate.EMMessageListener
            public void onCmdMessageReceived(List<EMMessage> messages) {
                MyLog.e("onCmdMessageReceived");
                for (EMMessage message : messages) {
                    String action = "";
                    try {
                        action = ((EMCmdMessageBody) message.getBody()).action();
                        MyLog.e(DemoHelper.TAG, "cmd action=" + action);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        JSONObject jsonObject = new JSONObject(action);
                        String type = jsonObject.optString("type");
                        JSONObject datas = jsonObject.optJSONObject("datas");
                        char c = 65535;
                        switch (type.hashCode()) {
                            case -1971524193:
                                if (type.equals("gainCofferObj")) {
                                    c = 0;
                                    break;
                                }
                                break;
                        }
                        switch (c) {
                            case 0:
                                DemoHelper.this.showGetBeans(datas.optInt("cofferNum"));
                                continue;
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    MyLog.e(DemoHelper.TAG, String.format("Command：action:%s,message:%s", action, message.toString()));
                }
            }

            @Override // com.hyphenate.EMMessageListener
            public void onMessageRead(List<EMMessage> messages) {
            }

            @Override // com.hyphenate.EMMessageListener
            public void onMessageDelivered(List<EMMessage> message) {
            }

            @Override // com.hyphenate.EMMessageListener
            public void onMessageChanged(EMMessage message, Object change) {
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(this.messageListener);
    }

    public void onConnectionConflict() {
        this.isConflict = true;
        if (isRunningForeground(this.appContext)) {
            showConflictDialog();
        }
    }

    public void checkConflict() {
        if (this.isConflict) {
            showConflictDialog();
        }
    }

    private void showConflictDialog() {
        Intent intent = new Intent(this.appContext, MainActivity.class);
        intent.putExtra(com.vsf2f.f2f.ui.utils.Constant.FLAG_INTENT, 2);
        intent.putExtra(EaseConstant.ACCOUNT_CONFLICT, true);
        intent.addFlags(268435456);
        this.appContext.startActivity(intent);
    }

    public void showGetBeans(final int num) {
        MyLog.e("OtayDialog:" + num);
        new Handler(this.appContext.getMainLooper()).postDelayed(new Runnable() { // from class: com.em.DemoHelper.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Activity act = MyApplication.getInstance().getTopActivity();
                    MyLog.e("GetBeans", "TopActivity:" + act);
                    Intent intent = new Intent(act, OtayActivity.class);
                    intent.putExtra("num", num);
                    act.startActivity(intent);
                } catch (Exception e) {
                    HyUtil.printException(e);
                }
            }
        }, 1000L);
    }

    public void showJPushAlert(String msg) {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.SYSTEM_ALERT_WINDOW") == 0) {
            try {
                JSONObject object = new JSONObject(msg);
                object.optString("vs_type", "sysnotice");
                SysMsgDialog sysMsgDialog = new SysMsgDialog(this.appContext, object.optString("vs_title", "通知"), object.optString("vs_explains"), object.optString("vs_href"), true);
                sysMsgDialog.getWindow().setType(AMapException.CODE_AMAP_ENGINE_TABLEID_NOT_EXIST);
                sysMsgDialog.show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void showOpenManor() {
        MyToast.show(this.appContext, "激活成功");
        MyLog.e(TAG, "openManor");
        new Handler(this.appContext.getMainLooper()).post(new Runnable() { // from class: com.em.DemoHelper.11
            @Override // java.lang.Runnable
            public void run() {
                ManorTreeDialog treeDialog = new ManorTreeDialog(DemoHelper.this.appContext);
                treeDialog.getWindow().setType(AMapException.CODE_AMAP_ENGINE_TABLEID_NOT_EXIST);
                treeDialog.show();
            }
        });
    }

    public void logout(final EMCallBack callback) {
        this.isConflict = false;
        endCall();
        exitShared();
        ComUtil.loginOut(this.context);
        EMClient.getInstance().logout(true, new EMCallBack() { // from class: com.em.DemoHelper.12
            @Override // com.hyphenate.EMCallBack
            public void onSuccess() {
                Log.d(DemoHelper.TAG, "logout: onSuccess");
                DemoHelper.this.reset();
                if (callback != null) {
                    callback.onSuccess();
                }
            }

            @Override // com.hyphenate.EMCallBack
            public void onProgress(int progress, String status) {
                if (callback != null) {
                    callback.onProgress(progress, status);
                }
            }

            @Override // com.hyphenate.EMCallBack
            public void onError(int code, String error) {
                Log.d(DemoHelper.TAG, "logout: onSuccess");
                DemoHelper.this.reset();
                if (callback != null) {
                    callback.onError(code, error);
                }
            }
        });
    }

    public boolean isLoggedIn() {
        return EMClient.getInstance().isLoggedInBefore();
    }

    public EaseNotifier getNotifier() {
        return this.easeUI.getNotifier();
    }

    public DemoModel getModel() {
        return this.demoModel;
    }

    public void saveCurrentUserInfo(UserInfo userInfo) {
        this.demoModel.saveCurrentUserInfo(userInfo);
        this.username = userInfo.getUserName();
    }

    public UserInfo getCurrentUserInfo(boolean userPic, boolean thirdParty) {
        return this.demoModel.getCurrentUserInfo(userPic, thirdParty);
    }

    public UserPicBean getCurrentUserPic() {
        return this.demoModel.getCurrentUserPic();
    }

    public int getCurrentUserId() {
        return this.demoModel.getCurrentUserId();
    }

    public String getCurrentUserName() {
        if (TextUtils.isEmpty(this.username)) {
            this.username = this.demoModel.getCurrentUserName();
        }
        return this.username;
    }

    public String getCurrentUserNick() {
        return TextUtils.isEmpty(getCurrentNickname()) ? getCurrentUserName() : this.nickname;
    }

    public String getCurrentNickname() {
        if (TextUtils.isEmpty(this.nickname)) {
            this.nickname = this.demoModel.getCurrentUserNick();
        }
        return this.nickname;
    }

    public void setCurrentNickName(String userNick) {
        this.nickname = userNick;
        this.demoModel.setCurrentUserNick(userNick);
    }

    public String getDeviceUni() {
        if (this.deviceId == null) {
            this.deviceId = DeviceTool.getDeviceUni(this.context);
        }
        return this.deviceId;
    }

    public String getDeviceModel() {
        if (this.deviceModel == null) {
            this.deviceModel = DeviceTool.getModel();
        }
        return this.deviceModel;
    }

    public int isOpenStore() {
        if (this.isOpenStore == 0) {
            this.isOpenStore = this.demoModel.isOpenStore();
        }
        return this.isOpenStore;
    }

    public void setOpenStore(int openStore) {
        this.isOpenStore = openStore;
        this.demoModel.setOpenStore(openStore);
    }

    public void exitShared() {
        this.demoModel.exitShared();
    }

    public void updateContactList(List<EaseUser> contactInfoList) {
        if (!HyUtil.isEmpty(contactInfoList)) {
            if (this.contactsList == null) {
                this.contactsList = getContactList();
            }
            for (EaseUser user : contactInfoList) {
                if (!TextUtils.isEmpty(user.getUsername())) {
                    this.contactsList.put(user.getUsername(), user);
                }
            }
            this.demoModel.updateContactList(contactInfoList);
        }
    }

    public void saveContact(EaseUser user) {
        if (this.contactsList == null) {
            this.contactsList = getContactList();
        }
        this.contactsList.put(user.getUsername(), user);
        this.demoModel.saveContact(user);
    }

    public void upContactNick(String username, String nick) {
        if (this.contactsList == null) {
            this.contactsList = getContactList();
        }
        EaseUser user = this.contactsList.get(username);
        user.setNickname(nick);
        saveContact(user);
    }

    public void upContactAvatar(String username, String avatar) {
        if (this.contactsList == null) {
            this.contactsList = getContactList();
        }
        EaseUser user = this.contactsList.get(username);
        user.setAvatar(avatar);
        saveContact(user);
    }

    public void deleteContact(String username) {
        if (!TextUtils.isEmpty(username)) {
            this.contactsList.remove(username);
            this.demoModel.deleteContact(username);
        }
    }

    public void deleteContact(List<FriendsListBean.RowsBean> list) {
        for (FriendsListBean.RowsBean bean : list) {
            String userName = bean.getFriendsUser();
            if (!TextUtils.isEmpty(userName)) {
                if (this.contactsList != null) {
                    this.contactsList.remove(userName);
                }
                this.demoModel.deleteContact(userName);
            }
        }
    }

    public long getFriendLastTime() {
        return AppShare.get(this.context).getLong(com.vsf2f.f2f.ui.utils.Constant.FRIEND_LAST_TIME + getCurrentUserName());
    }

    public void putFriendLastTime(long lastTime) {
        AppShare.get(this.context).putLong(com.vsf2f.f2f.ui.utils.Constant.FRIEND_LAST_TIME + getCurrentUserName(), lastTime);
    }

    public Map<String, EaseUser> getContactList() {
        if (this.contactsList == null && ComUtil.isLogin(this.context)) {
            this.contactsList = this.demoModel.getContactList();
            if (this.contactsList == null || this.contactsList.size() == 0) {
                putFriendLastTime(0L);
            }
        }
        if (this.contactsList == null) {
            return new Hashtable();
        }
        MyLog.e("好友数量：", Integer.valueOf(this.contactsList.size()));
        return this.contactsList;
    }

    public Boolean isContact(String username) {
        return Boolean.valueOf(getContactList().containsKey(username));
    }

    public void saveUserList(List<EaseUser> ausersList) {
        if (this.usersList == null) {
            this.usersList = new Hashtable();
        }
        for (EaseUser u2 : ausersList) {
            if (!TextUtils.isEmpty(u2.getUsername())) {
                this.usersList.put(u2.getUsername(), u2);
            }
        }
        ArrayList<EaseUser> mList = new ArrayList<>();
        mList.addAll(this.usersList.values());
        this.demoModel.saveUsersList(mList);
    }

    public void saveUser(EaseUser user) {
        if (this.usersList == null) {
            this.usersList = new Hashtable();
        }
        this.usersList.put(user.getUsername(), user);
        this.demoModel.saveUser(user);
    }

    public Map<String, EaseUser> getUsersList() {
        if (this.usersList == null) {
            this.usersList = this.demoModel.getUsersList();
        }
        return this.usersList == null ? new Hashtable() : this.usersList;
    }

    public Map<String, GroupBean> getGroupsList() {
        if (this.groupsList == null && ComUtil.isLogin(this.context)) {
            this.groupsList = this.demoModel.getGroupsList();
        }
        return this.groupsList == null ? new HashMap() : this.groupsList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GroupBean getGroupInfo(String groupId) {
        GroupBean group = getGroupsList().get(groupId);
        if (group != null) {
            return group;
        }
        this.groupsList = this.demoModel.getGroupsList();
        return this.groupsList.get(groupId);
    }

    public void updateGroupsList(List<GroupBean> newGroupList) {
        this.groupsList = new Hashtable();
        for (GroupBean groupBean : newGroupList) {
            if (!TextUtils.isEmpty(groupBean.getGroupId())) {
                this.groupsList.put(groupBean.getGroupId(), groupBean);
            }
        }
        this.demoModel.saveGroupsList(new ArrayList<>(this.groupsList.values()));
    }

    public void saveGroup(GroupBean group) {
        if (this.groupsList == null) {
            this.groupsList = new Hashtable();
        }
        this.groupsList.put(group.getGroupId(), group);
        this.demoModel.saveGroup(group);
    }

    public void upGroupAvatar(String groupId, String avatar) {
        if (this.groupsList == null) {
            this.groupsList = getGroupsList();
        }
        GroupBean group = this.groupsList.get(groupId);
        group.setGroupPicture(avatar);
        saveGroup(group);
    }

    public void saveConfig(ConfigBean configBean) {
        this.configBean = configBean;
    }

    public ConfigBean readConfig() {
        if (this.configBean == null) {
            this.configBean = new ConfigBean();
        }
        return this.configBean;
    }

    public void deleteGroup(String groupId) {
        if (!TextUtils.isEmpty(groupId)) {
            this.groupsList.remove(groupId);
            this.demoModel.deleteGroup(groupId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EaseUser getUserInfo(String username) {
        EaseUser user = getContactInfo(username);
        if (user == null || user.getNickname() == null) {
            user = getUsersList().get(username);
        }
        if (user != null) {
            return user;
        }
        EaseUser user2 = new EaseUser(username);
        user2.setInitialLetter(EaseCommonUtils.setUserInitialLetter(user2.getNick()));
        return user2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EaseUser getContactInfo(String username) {
        if (username.equals(getCurrentUserName())) {
            return new EaseUser(getCurrentUserInfo(false, false));
        }
        return getContactList().get(username);
    }

    public void savePhonesList(List<PhoneBean> list) {
        this.demoModel.savePhonesList(list);
    }

    public Map<String, PhoneBean> getPhonesList() {
        return this.demoModel.getPhonesList();
    }

    public Map<String, AccountBean> getAccountList() {
        return this.demoModel.getAccountList();
    }

    public void saveAccount(AccountBean account) {
        this.demoModel.saveAccount(account);
    }

    public void deleteAccount(String account) {
        this.demoModel.deleteAccount(account);
    }

    public void setRobotList(Map<String, RobotUser> robotList) {
        this.robotList = robotList;
    }

    public Map<String, RobotUser> getRobotList() {
        if (isLoggedIn() && this.robotList == null) {
            this.robotList = this.demoModel.getRobotList();
        }
        return this.robotList;
    }

    private void endCall() {
        try {
            EMClient.getInstance().callManager().endCall();
        } catch (EMNoActiveCallException e) {
        } catch (Exception e2) {
        }
    }

    public void addSyncGroupListener(DataSyncListener listener) {
        if (listener != null && !this.syncGroupsListeners.contains(listener)) {
            this.syncGroupsListeners.add(listener);
        }
    }

    public void removeSyncGroupListener(DataSyncListener listener) {
        if (listener != null && this.syncGroupsListeners.contains(listener)) {
            this.syncGroupsListeners.remove(listener);
        }
    }

    public void addSyncContactListener(DataSyncListener listener) {
        if (listener != null && !this.syncContactsListeners.contains(listener)) {
            this.syncContactsListeners.add(listener);
        }
    }

    public void removeSyncContactListener(DataSyncListener listener) {
        if (listener != null && this.syncContactsListeners.contains(listener)) {
            this.syncContactsListeners.remove(listener);
        }
    }

    public void addSyncBlackListListener(DataSyncListener listener) {
        if (listener != null && !this.syncBlackListListeners.contains(listener)) {
            this.syncBlackListListeners.add(listener);
        }
    }

    public void removeSyncBlackListListener(DataSyncListener listener) {
        if (listener != null && this.syncBlackListListeners.contains(listener)) {
            this.syncBlackListListeners.remove(listener);
        }
    }

    public void addSyncContactInfoListener(DataSyncListener listener) {
        if (listener != null && !this.syncContactInfosListeners.contains(listener)) {
            this.syncContactInfosListeners.add(listener);
        }
    }

    public void removeSyncContactInfoListener(DataSyncListener listener) {
        if (listener != null && this.syncContactInfosListeners.contains(listener)) {
            this.syncContactInfosListeners.remove(listener);
        }
    }

    public void noitifyGroupSyncListeners(boolean success) {
        for (DataSyncListener listener : this.syncGroupsListeners) {
            listener.onSyncComplete(success);
        }
    }

    public void notifyContactsSyncListener(boolean success) {
        for (DataSyncListener listener : this.syncContactsListeners) {
            listener.onSyncComplete(success);
        }
    }

    public void notifyBlackListSyncListener(boolean success) {
        for (DataSyncListener listener : this.syncBlackListListeners) {
            listener.onSyncComplete(success);
        }
    }

    public boolean isSyncingGroupsWithServer() {
        return this.isSyncingGroupsWithServer;
    }

    public boolean isSyncingContactsWithServer() {
        return this.isSyncingContactsWithServer;
    }

    public boolean isSyncingBlackListWithServer() {
        return this.isSyncingBlackListWithServer;
    }

    public boolean isSyncingContactInfoWithServer() {
        return this.isSyncingContactInfosWithServer;
    }

    public boolean isGroupsSyncedWithServer() {
        return this.isGroupsSyncedWithServer;
    }

    public boolean isContactsSyncedWithServer() {
        return this.isContactsSyncedWithServer;
    }

    public boolean isBlackListSyncedWithServer() {
        return this.isBlackListSyncedWithServer;
    }

    synchronized void reset() {
        this.username = null;
        this.isOpenStore = 0;
        this.isSyncingGroupsWithServer = false;
        this.isSyncingContactsWithServer = false;
        this.isSyncingBlackListWithServer = false;
        this.isSyncingContactInfosWithServer = false;
        this.isGroupsSyncedWithServer = false;
        this.isContactsSyncedWithServer = false;
        this.isBlackListSyncedWithServer = false;
        this.isGroupAndContactListenerRegisted = false;
        this.demoModel.setGroupsSynced(false);
        this.demoModel.setContactSynced(false);
        this.demoModel.setBlacklistSynced(false);
        this.contactsList = null;
        this.groupsList = null;
        this.robotList = null;
        DemoDBManager.getInstance().closeDB();
    }

    public void pushActivity(Activity activity) {
        this.easeUI.pushActivity(activity);
    }

    public void popActivity(Activity activity) {
        this.easeUI.popActivity(activity);
    }
}
