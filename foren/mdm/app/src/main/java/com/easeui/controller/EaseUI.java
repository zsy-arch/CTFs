package com.easeui.controller;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.util.Log;
import com.easeui.domain.EaseEmojicon;
import com.easeui.domain.EaseUser;
import com.easeui.model.EaseAtMessageHelper;
import com.easeui.model.EaseNotifier;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.vsf2f.f2f.bean.GroupBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class EaseUI {
    private static final String TAG = EaseUI.class.getSimpleName();
    private static EaseUI instance = null;
    private EaseContactsProfileProvider contactsProvider;
    private EaseEmojiconInfoProvider emojiconInfoProvider;
    private EaseGroupProfileProvider groupsProvider;
    private EaseSettingsProvider settingsProvider;
    private EaseUserProfileProvider usersProvider;
    private Context appContext = null;
    private boolean sdkInited = false;
    private EaseNotifier notifier = null;
    private List<Activity> activityList = new ArrayList();

    /* loaded from: classes.dex */
    public interface EaseContactsProfileProvider {
        EaseUser getContacts(String str);
    }

    /* loaded from: classes.dex */
    public interface EaseEmojiconInfoProvider {
        EaseEmojicon getEmojiconInfo(String str);

        Map<String, Object> getTextEmojiconMapping();
    }

    /* loaded from: classes.dex */
    public interface EaseGroupProfileProvider {
        GroupBean getGroup(String str);
    }

    /* loaded from: classes.dex */
    public interface EaseSettingsProvider {
        boolean isMsgNotifyAllowed(EMMessage eMMessage);

        boolean isMsgSoundAllowed(EMMessage eMMessage);

        boolean isMsgVibrateAllowed(EMMessage eMMessage);

        boolean isSpeakerOpened();
    }

    /* loaded from: classes.dex */
    public interface EaseUserProfileProvider {
        EaseUser getUser(String str);
    }

    public void pushActivity(Activity activity) {
        if (!this.activityList.contains(activity)) {
            this.activityList.add(0, activity);
        }
    }

    public void popActivity(Activity activity) {
        this.activityList.remove(activity);
    }

    private EaseUI() {
    }

    public static synchronized EaseUI getInstance() {
        EaseUI easeUI;
        synchronized (EaseUI.class) {
            if (instance == null) {
                instance = new EaseUI();
            }
            easeUI = instance;
        }
        return easeUI;
    }

    public synchronized boolean init(Context context, EMOptions options) {
        boolean z = true;
        synchronized (this) {
            if (!this.sdkInited) {
                this.appContext = context;
                String processAppName = getAppName(Process.myPid());
                Log.d(TAG, "process app name : " + processAppName);
                if (processAppName == null || !processAppName.equalsIgnoreCase(this.appContext.getPackageName())) {
                    Log.e(TAG, "enter the service process!");
                    z = false;
                } else {
                    if (options == null) {
                        EMClient.getInstance().init(context, initChatOptions());
                    } else {
                        EMClient.getInstance().init(context, options);
                    }
                    initNotifier();
                    registerMessageListener();
                    if (this.settingsProvider == null) {
                        this.settingsProvider = new DefaultSettingsProvider();
                    }
                    this.sdkInited = true;
                }
            }
        }
        return z;
    }

    protected EMOptions initChatOptions() {
        Log.d(TAG, "init HuanXin Options");
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);
        options.setRequireAck(true);
        options.setRequireDeliveryAck(false);
        return options;
    }

    void initNotifier() {
        this.notifier = createNotifier();
        this.notifier.init(this.appContext);
    }

    private void registerMessageListener() {
        EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() { // from class: com.easeui.controller.EaseUI.1
            @Override // com.hyphenate.EMMessageListener
            public void onMessageReceived(List<EMMessage> messages) {
                EaseAtMessageHelper.get().parseMessages(messages);
            }

            @Override // com.hyphenate.EMMessageListener
            public void onMessageRead(List<EMMessage> messages) {
            }

            @Override // com.hyphenate.EMMessageListener
            public void onMessageDelivered(List<EMMessage> messages) {
            }

            @Override // com.hyphenate.EMMessageListener
            public void onMessageChanged(EMMessage message, Object change) {
            }

            @Override // com.hyphenate.EMMessageListener
            public void onCmdMessageReceived(List<EMMessage> messages) {
            }
        });
    }

    protected EaseNotifier createNotifier() {
        return new EaseNotifier();
    }

    public EaseNotifier getNotifier() {
        return this.notifier;
    }

    public boolean hasForegroundActivies() {
        return this.activityList.size() != 0;
    }

    public EaseUserProfileProvider getUsersProfileProvider() {
        return this.usersProvider;
    }

    public EaseGroupProfileProvider getGroupsProfileProvider() {
        return this.groupsProvider;
    }

    public EaseContactsProfileProvider getContactsProfileProvider() {
        return this.contactsProvider;
    }

    public void setUserProfileProvider(EaseUserProfileProvider usersProvider) {
        this.usersProvider = usersProvider;
    }

    public void setContactsProfileProvider(EaseContactsProfileProvider contactsProvider) {
        this.contactsProvider = contactsProvider;
    }

    public void setGroupProfileProvider(EaseGroupProfileProvider groupsProvider) {
        this.groupsProvider = groupsProvider;
    }

    public void setSettingsProvider(EaseSettingsProvider settingsProvider) {
        this.settingsProvider = settingsProvider;
    }

    public EaseSettingsProvider getSettingsProvider() {
        return this.settingsProvider;
    }

    private String getAppName(int pID) {
        String processName = null;
        PackageManager pm = this.appContext.getPackageManager();
        for (ActivityManager.RunningAppProcessInfo info : ((ActivityManager) this.appContext.getSystemService("activity")).getRunningAppProcesses()) {
            if (info.pid == pID) {
                pm.getApplicationLabel(pm.getApplicationInfo(info.processName, 128));
                return info.processName;
            }
            continue;
        }
        return processName;
    }

    public EaseEmojiconInfoProvider getEmojiconInfoProvider() {
        return this.emojiconInfoProvider;
    }

    public void setEmojiconInfoProvider(EaseEmojiconInfoProvider emojiconInfoProvider) {
        this.emojiconInfoProvider = emojiconInfoProvider;
    }

    /* loaded from: classes.dex */
    public class DefaultSettingsProvider implements EaseSettingsProvider {
        protected DefaultSettingsProvider() {
            EaseUI.this = this$0;
        }

        @Override // com.easeui.controller.EaseUI.EaseSettingsProvider
        public boolean isMsgNotifyAllowed(EMMessage message) {
            return true;
        }

        @Override // com.easeui.controller.EaseUI.EaseSettingsProvider
        public boolean isMsgSoundAllowed(EMMessage message) {
            return true;
        }

        @Override // com.easeui.controller.EaseUI.EaseSettingsProvider
        public boolean isMsgVibrateAllowed(EMMessage message) {
            return true;
        }

        @Override // com.easeui.controller.EaseUI.EaseSettingsProvider
        public boolean isSpeakerOpened() {
            return true;
        }
    }

    public Context getContext() {
        return this.appContext;
    }
}
