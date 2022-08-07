package com.vsf2f.f2f.ui.utils.captcha;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import com.vsf2f.f2f.R;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class Captcha {
    public static final int INITTIMEOUT = 1;
    public static final int NONETWROK = 0;
    public static final String SDKVER = "2.4.2";
    public static final String TAG = "myCaptcha";
    public static final int VALIDATETIMEOUT = 2;
    public static final String baseURL = "https://cstaticdun.126.net/api/v2/mobile_2_4_2.html";
    private CaptchaDialog captchaDialog;
    private Context context;
    private boolean debug;
    private boolean isAlreadySendNetMsg;
    private String deviceId = "";
    private String captchaId = "";
    private CaptchaListener caListener = null;
    private Handler handler = null;
    private int mTimeout = 10000;
    private CaptchaProgressDialog progressDialog = null;
    private Timer timer = null;
    private boolean isProgressDialogCanceledOnTouchOutside = true;
    private boolean isEnglishLanguage = false;
    private int mPositionX = -1;
    private int mPositionY = -1;
    private int mPositionW = -1;
    private int mPositionH = -1;
    private boolean backgroundDimEnabled = true;
    private boolean isCanceledOnTouchOutside = true;

    public Captcha(Context context) {
        this.context = context;
    }

    private static boolean isValid(String param) {
        return param != null && param.length() > 0;
    }

    public static boolean IsNetWorkEnable(Context context) {
        NetworkInfo info;
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivity == null || (info = connectivity.getActiveNetworkInfo()) == null || !info.isConnected()) {
                return false;
            }
            if (info.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCaptchaId() {
        return this.captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public CaptchaListener getCaListener() {
        return this.caListener;
    }

    public void setCaListener(CaptchaListener caListener) {
        this.caListener = caListener;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setTimeout(int timeout) {
        this.mTimeout = timeout;
    }

    public int getTimeout() {
        return this.mTimeout;
    }

    public boolean checkParams() {
        boolean ret = isValid(this.captchaId) && this.caListener != null;
        if (!isValid(this.captchaId)) {
            Log.d(TAG, "captchaId is wrong");
        }
        if (this.caListener == null) {
            Log.d(TAG, "never set caListener");
        }
        return ret;
    }

    public void setPosition(int left, int top, int w, int h) {
        this.mPositionX = left;
        this.mPositionY = top;
        this.mPositionW = w;
        this.mPositionH = h;
    }

    public void setEnglishLanguage() {
        this.isEnglishLanguage = true;
        Resources resources = this.context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.locale = Locale.ENGLISH;
        resources.updateConfiguration(config, dm);
    }

    public void setBackgroundDimEnabled(boolean dimEnabled) {
        this.backgroundDimEnabled = dimEnabled;
    }

    public void setCanceledOnTouchOutside(boolean canceled) {
        this.isCanceledOnTouchOutside = canceled;
    }

    public void setProgressDialogCanceledOnTouchOutside(boolean canceled) {
        this.isProgressDialogCanceledOnTouchOutside = canceled;
    }

    private boolean initDialog() {
        try {
            if (this.backgroundDimEnabled) {
                this.captchaDialog = new CaptchaDialog(this.context, this.isEnglishLanguage);
            } else {
                this.captchaDialog = new CaptchaDialog(this.context, (int) R.style.DialogTheme);
            }
            this.captchaDialog.setPosition(this.mPositionX, this.mPositionY, this.mPositionW, this.mPositionH);
            this.captchaDialog.setDebug(this.debug);
            this.captchaDialog.setDeviceId(this.deviceId);
            this.captchaDialog.setCaptchaId(this.captchaId);
            this.captchaDialog.setCaListener(this.caListener);
            this.captchaDialog.setProgressDialog(this.progressDialog);
            this.captchaDialog.setCanceledOnTouchOutside(this.isCanceledOnTouchOutside);
            this.captchaDialog.initDialog();
            this.captchaDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.vsf2f.f2f.ui.utils.captcha.Captcha.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialog) {
                    Captcha.this.caListener.onCancel();
                    if (Captcha.this.progressDialog != null) {
                        Captcha.this.progressDialog.dismiss();
                    }
                    Log.d(Captcha.TAG, "用户取消验证");
                }
            });
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    private void setSchedule(int type, ProgressDialog p, int timeout) {
        Log.d(TAG, "setSchedule start");
        MyTask timerTask = new MyTask(type, p);
        this.timer = new Timer();
        this.timer.schedule(timerTask, timeout);
    }

    public void start() {
        if (checkParams()) {
            Log.d(TAG, "start");
            if (!((Activity) this.context).isFinishing()) {
                if (this.progressDialog == null) {
                    this.progressDialog = new CaptchaProgressDialog(this.context);
                }
                this.progressDialog.setPosition(this.mPositionX, this.mPositionY, this.mPositionW, this.mPositionH);
                this.progressDialog.setCancelable(true);
                this.progressDialog.setIndeterminate(true);
                this.progressDialog.setCanceledOnTouchOutside(this.isProgressDialogCanceledOnTouchOutside);
                this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.vsf2f.f2f.ui.utils.captcha.Captcha.2
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialog) {
                        if (Captcha.this.timer != null) {
                            Captcha.this.timer.cancel();
                            Captcha.this.timer.purge();
                            Captcha.this.progressDialog.isCancelLoading = true;
                        }
                    }
                });
                this.progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.vsf2f.f2f.ui.utils.captcha.Captcha.3
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (Captcha.this.timer != null) {
                            Captcha.this.timer.cancel();
                            Captcha.this.timer.purge();
                        }
                    }
                });
                this.progressDialog.show();
                if (this.handler == null) {
                    this.handler = new MyHandler((Activity) this.context, this.progressDialog);
                }
                this.isAlreadySendNetMsg = false;
                setSchedule(1, this.progressDialog, this.mTimeout);
            }
        }
    }

    public void Validate() {
        try {
            Log.d(TAG, "validate start");
            if (!((Activity) this.context).isFinishing()) {
                if (!IsNetWorkEnable(this.context)) {
                    this.caListener.onError("no network!");
                    setSchedule(0, this.progressDialog, 500);
                } else {
                    initDialog();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Captcha SDK Validate Error:" + e.toString());
        }
    }

    /* loaded from: classes2.dex */
    private static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;
        CaptchaProgressDialog mp;

        MyHandler(Activity activity, CaptchaProgressDialog p) {
            this.mActivityReference = new WeakReference<>(activity);
            this.mp = p;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.mp != null && this.mp.isShowing()) {
                switch (msg.what) {
                    case 0:
                        this.mp.setCanceledOnTouchOutside(true);
                        this.mp.setProgressTips(R.string.tip_no_network);
                        this.mp.isCanClickDisappear = true;
                        break;
                    case 1:
                        this.mp.setCanceledOnTouchOutside(true);
                        this.mp.setProgressTips(R.string.tip_init_timeout);
                        this.mp.isCanClickDisappear = true;
                        break;
                    case 2:
                        this.mp.setCanceledOnTouchOutside(true);
                        this.mp.setProgressTips(R.string.tip_validate_timeout);
                        this.mp.isCanClickDisappear = true;
                        break;
                }
                this.mp.show();
                Log.d(Captcha.TAG, "handleMessage end");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class MyTask extends TimerTask {
        private ProgressDialog p;
        private int type;

        public MyTask(int type, ProgressDialog p) {
            this.type = type;
            this.p = p;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Log.d(Captcha.TAG, "MyTask start");
            Message message = new Message();
            switch (this.type) {
                case 0:
                    message.what = 0;
                    break;
                case 1:
                    message.what = 1;
                    break;
                case 2:
                    message.what = 2;
                    break;
                default:
                    return;
            }
            if (!Captcha.this.isAlreadySendNetMsg) {
                Captcha.this.handler.sendMessage(message);
                Captcha.this.isAlreadySendNetMsg = true;
            }
            Log.d(Captcha.TAG, "MyTask end");
        }
    }
}
