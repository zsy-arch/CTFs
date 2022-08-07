package com.vsf2f.f2f.ui.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import com.cdlinglu.common.MyHttpClient;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.MyLog;
import com.hy.http.IMyHttpListener;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.ImgWarnDialog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FingerUtil {
    private FingerprintManagerCompat fingerprintManager;
    private CancellationSignal signal = new CancellationSignal();

    /* loaded from: classes2.dex */
    public interface overListener {
        void over();
    }

    public FingerUtil(Context activity) {
        this.fingerprintManager = FingerprintManagerCompat.from(activity);
    }

    public void startFingerListen(FingerprintManagerCompat.AuthenticationCallback callback) {
        this.fingerprintManager.authenticate(null, 0, this.signal, callback, null);
    }

    public void stopsFingerListen() {
        if (this.signal != null) {
            this.signal.cancel();
            this.signal = null;
        }
    }

    public static boolean checkFingerModule(Context context) {
        FingerprintManager fingerManager;
        try {
            if (Build.VERSION.SDK_INT < 23 || (fingerManager = (FingerprintManager) context.getSystemService("fingerprint")) == null) {
                return false;
            }
            return fingerManager.isHardwareDetected();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean hasFingerPrints(Context context) {
        FingerprintManager fingerManager;
        try {
            if (Build.VERSION.SDK_INT < 23 || (fingerManager = (FingerprintManager) context.getSystemService("fingerprint")) == null) {
                return false;
            }
            return fingerManager.hasEnrolledFingerprints();
        } catch (Exception e) {
            return false;
        }
    }

    public static void showOpenFinger(final Context context, final overListener listener) {
        if (checkFingerModule(context)) {
            final int prompt = AppShare.get(context).getInt("toast_finger_num");
            if (prompt < 3 && !ComUtil.isFinger(context)) {
                new EaseAlertDialog(context, "", "开启指纹支付，支付时可通过验证指纹快速完成付款.", (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.utils.FingerUtil.1
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            FingerUtil.requestFinger(context, ComUtil.getPaypwd(context), listener);
                            return;
                        }
                        AppShare.get(context).putInt("toast_finger_num", prompt + 1);
                        if (listener != null) {
                            listener.over();
                        }
                    }
                }, true).setOkBtn("开启指纹支付").show();
            } else if (listener != null) {
                listener.over();
            }
        } else if (listener != null) {
            listener.over();
        }
    }

    public static void requestFinger(final Context context, String pwdMd5, final overListener listener) {
        JSONObject jsonObj = new JSONObject();
        try {
            JSONObject jsonDatas = new JSONObject();
            JSONObject jsonCheck = new JSONObject();
            jsonDatas.put("fingerprintPayment", 1);
            jsonCheck.put("payPwd", pwdMd5);
            jsonObj.put("type", "PAY_PWD");
            jsonObj.put("datas", jsonDatas);
            jsonObj.put("check", jsonCheck);
            MyLog.e(jsonObj.toString() + "");
            MyHttpClient httpClient = new MyHttpClient(context, new IMyHttpListener() { // from class: com.vsf2f.f2f.ui.utils.FingerUtil.2
                @Override // com.hy.http.IMyHttpListener
                public void onRequestSuccess(ResultInfo result) {
                    ComUtil.setFinger(context, true);
                    new ImgWarnDialog(context, R.drawable.icon_ok2, context.getString(R.string.already_open_finger), "完成").setDisListener(new DialogInterface.OnDismissListener() { // from class: com.vsf2f.f2f.ui.utils.FingerUtil.2.1
                        @Override // android.content.DialogInterface.OnDismissListener
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (listener != null) {
                                listener.over();
                            }
                        }
                    }).show();
                }

                @Override // com.hy.http.IMyHttpListener
                public void onRequestError(ResultInfo result) {
                    if (listener != null) {
                        listener.over();
                    }
                }
            });
            httpClient.setShowDialog(true);
            httpClient.post(R.string.API_USER_CHANGE_DATA_EXT, ComUtil.getZCApi(context, context.getString(R.string.API_USER_CHANGE_DATA_EXT)), jsonObj.toString() + "", null, Boolean.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
