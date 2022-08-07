package com.easeui.model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import com.easeui.controller.EaseUI;
import com.easeui.utils.EaseCommonUtils;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.EasyUtils;
import com.hyphenate.util.HanziToPinyin;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class EaseNotifier {
    private static final String TAG = "notify";
    protected Context appContext;
    protected AudioManager audioManager;
    protected long lastNotifiyTime;
    protected String[] msgs;
    protected EaseNotificationInfoProvider notificationInfoProvider;
    protected String packageName;
    protected Vibrator vibrator;
    protected static final String[] msg_eng = {"sent a message", "sent a picture", "sent a voice", "sent location message", "sent a video", "sent a file", "%1 contacts sent %2 messages"};
    protected static final String[] msg_ch = {"发来一条消息", "发来一张图片", "发来一段语音", "发来位置信息", "发来一个视频", "发来一个文件", "%1个联系人发来%2条消息"};
    protected static int notifyID = 341;
    protected static int foregroundNotifyID = 365;
    Ringtone ringtone = null;
    protected NotificationManager notificationManager = null;
    protected HashSet<String> fromUsers = new HashSet<>();
    protected int notificationNum = 0;

    /* loaded from: classes.dex */
    public interface EaseNotificationInfoProvider {
        String getDisplayedText(EMMessage eMMessage);

        String getLatestText(EMMessage eMMessage, int i, int i2);

        Intent getLaunchIntent(EMMessage eMMessage);

        int getSmallIcon(EMMessage eMMessage);

        String getTitle(EMMessage eMMessage);
    }

    public EaseNotifier init(Context context) {
        this.appContext = context;
        this.notificationManager = (NotificationManager) context.getSystemService("notification");
        this.packageName = this.appContext.getApplicationInfo().packageName;
        if (Locale.getDefault().getLanguage().equals("zh")) {
            this.msgs = msg_ch;
        } else {
            this.msgs = msg_eng;
        }
        this.audioManager = (AudioManager) this.appContext.getSystemService("audio");
        this.vibrator = (Vibrator) this.appContext.getSystemService("vibrator");
        return this;
    }

    public void reset() {
        resetNotificationCount();
        cancelNotificaton();
    }

    void resetNotificationCount() {
        this.notificationNum = 0;
        this.fromUsers.clear();
    }

    void cancelNotificaton() {
        if (this.notificationManager != null) {
            this.notificationManager.cancel(notifyID);
        }
    }

    public synchronized void onNewMsg(EMMessage message) {
        if (!EaseCommonUtils.isSilentMessage(message) && EaseUI.getInstance().getSettingsProvider().isMsgNotifyAllowed(message)) {
            if (!EasyUtils.isAppRunningForeground(this.appContext)) {
                EMLog.d(TAG, "app is running in backgroud");
                sendNotification(message, false);
            } else {
                sendNotification(message, true);
            }
            vibrateAndPlayTone(message);
        }
    }

    public synchronized void onNewMesg(List<EMMessage> messages) {
        if (!EaseCommonUtils.isSilentMessage(messages.get(messages.size() - 1)) && EaseUI.getInstance().getSettingsProvider().isMsgNotifyAllowed(null)) {
            if (!EasyUtils.isAppRunningForeground(this.appContext)) {
                EMLog.d(TAG, "app is running in backgroud");
                sendNotification(messages, false);
            } else {
                sendNotification(messages, true);
            }
            vibrateAndPlayTone(messages.get(messages.size() - 1));
        }
    }

    protected void sendNotification(List<EMMessage> messages, boolean isForeground) {
        for (EMMessage message : messages) {
            if (!isForeground) {
                this.notificationNum++;
                this.fromUsers.add(message.getFrom());
            }
        }
        sendNotification(messages.get(messages.size() - 1), isForeground, false);
    }

    protected void sendNotification(EMMessage message, boolean isForeground) {
        sendNotification(message, isForeground, true);
    }

    protected void sendNotification(EMMessage message, boolean isForeground, boolean numIncrease) {
        try {
            String notifyText = message.getFrom() + HanziToPinyin.Token.SEPARATOR;
            switch (message.getType()) {
                case TXT:
                    notifyText = notifyText + this.msgs[0];
                    break;
                case IMAGE:
                    notifyText = notifyText + this.msgs[1];
                    break;
                case VOICE:
                    notifyText = notifyText + this.msgs[2];
                    break;
                case LOCATION:
                    notifyText = notifyText + this.msgs[3];
                    break;
                case VIDEO:
                    notifyText = notifyText + this.msgs[4];
                    break;
                case FILE:
                    notifyText = notifyText + this.msgs[5];
                    break;
            }
            String contentTitle = (String) this.appContext.getPackageManager().getApplicationLabel(this.appContext.getApplicationInfo());
            if (this.notificationInfoProvider != null) {
                String customNotifyText = this.notificationInfoProvider.getDisplayedText(message);
                String customCotentTitle = this.notificationInfoProvider.getTitle(message);
                if (customNotifyText != null) {
                    notifyText = customNotifyText;
                }
                if (customCotentTitle != null) {
                    contentTitle = customCotentTitle;
                }
            }
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this.appContext).setSmallIcon(this.appContext.getApplicationInfo().icon).setWhen(System.currentTimeMillis()).setAutoCancel(true);
            Intent msgIntent = this.appContext.getPackageManager().getLaunchIntentForPackage(this.packageName);
            if (this.notificationInfoProvider != null) {
                msgIntent = this.notificationInfoProvider.getLaunchIntent(message);
            }
            PendingIntent pendingIntent = PendingIntent.getActivity(this.appContext, notifyID, msgIntent, 134217728);
            if (numIncrease && !isForeground) {
                this.notificationNum++;
                this.fromUsers.add(message.getFrom());
            }
            int fromUsersNum = this.fromUsers.size();
            String summaryBody = this.msgs[6].replaceFirst("%1", Integer.toString(fromUsersNum)).replaceFirst("%2", Integer.toString(this.notificationNum));
            if (this.notificationInfoProvider != null) {
                String customSummaryBody = this.notificationInfoProvider.getLatestText(message, fromUsersNum, this.notificationNum);
                if (customSummaryBody != null) {
                    summaryBody = customSummaryBody;
                }
                int smallIcon = this.notificationInfoProvider.getSmallIcon(message);
                if (smallIcon != 0) {
                    mBuilder.setSmallIcon(smallIcon);
                }
            }
            mBuilder.setContentTitle(contentTitle);
            mBuilder.setTicker(notifyText);
            mBuilder.setContentText(summaryBody);
            mBuilder.setContentIntent(pendingIntent);
            Notification notification = mBuilder.build();
            if (isForeground) {
                this.notificationManager.notify(foregroundNotifyID, notification);
                this.notificationManager.cancel(foregroundNotifyID);
                return;
            }
            this.notificationManager.notify(notifyID, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void vibrateAndPlayTone(EMMessage message) {
        if ((message == null || !EaseCommonUtils.isSilentMessage(message)) && System.currentTimeMillis() - this.lastNotifiyTime >= 1000) {
            try {
                this.lastNotifiyTime = System.currentTimeMillis();
                if (this.audioManager.getRingerMode() == 0) {
                    EMLog.e(TAG, "in slient mode now");
                    return;
                }
                EaseUI.EaseSettingsProvider settingsProvider = EaseUI.getInstance().getSettingsProvider();
                if (settingsProvider.isMsgVibrateAllowed(message)) {
                    this.vibrator.vibrate(new long[]{0, 180, 80, 120}, -1);
                }
                if (settingsProvider.isMsgSoundAllowed(message)) {
                    if (this.ringtone == null) {
                        Uri notificationUri = RingtoneManager.getDefaultUri(2);
                        this.ringtone = RingtoneManager.getRingtone(this.appContext, notificationUri);
                        if (this.ringtone == null) {
                            EMLog.d(TAG, "cant find ringtone at:" + notificationUri.getPath());
                            return;
                        }
                    }
                    if (!this.ringtone.isPlaying()) {
                        String vendor = Build.MANUFACTURER;
                        this.ringtone.play();
                        if (vendor != null && vendor.toLowerCase().contains("samsung")) {
                            ThreadPool.newThreadPool(new Runnable() { // from class: com.easeui.model.EaseNotifier.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        Thread.sleep(3000L);
                                        if (EaseNotifier.this.ringtone.isPlaying()) {
                                            EaseNotifier.this.ringtone.stop();
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            });
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setNotificationInfoProvider(EaseNotificationInfoProvider provider) {
        this.notificationInfoProvider = provider;
    }
}
