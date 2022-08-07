package com.easeui.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import com.easeui.EaseConstant;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.HanziToPinyin;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class EaseCommonUtils {
    private static final String TAG = "CommonUtils";

    public static boolean isNetWorkConnected(Context context) {
        NetworkInfo mNetworkInfo;
        return context != null && (mNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null && mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
    }

    public static boolean isSdcardExist() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static EMMessage createExpressionMessage(String toChatUsername, String expressioName, String identityCode) {
        EMMessage message = EMMessage.createTxtSendMessage("[" + expressioName + "]", toChatUsername);
        if (identityCode != null) {
            message.setAttribute(EaseConstant.MESSAGE_ATTR_EXPRESSION_ID, identityCode);
        }
        message.setAttribute(EaseConstant.MESSAGE_ATTR_IS_BIG_EXPRESSION, true);
        return message;
    }

    public static String getMessageDigest(EMMessage message, Context context) {
        String digest;
        switch (message.getType()) {
            case LOCATION:
                digest = getString(context, R.string.location);
                break;
            case IMAGE:
                digest = getString(context, R.string.picture);
                break;
            case VOICE:
                digest = getString(context, R.string.voice_prefix);
                break;
            case VIDEO:
                digest = getString(context, R.string.video);
                break;
            case TXT:
                EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
                if (!message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VOICE_CALL, false)) {
                    if (!message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VIDEO_CALL, false)) {
                        if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_BIG_EXPRESSION, false)) {
                            if (!TextUtils.isEmpty(txtBody.getMessage())) {
                                if (!txtBody.getMessage().equals("[]")) {
                                    digest = txtBody.getMessage();
                                    break;
                                } else {
                                    digest = getString(context, R.string.dynamic_expression);
                                    break;
                                }
                            } else {
                                digest = getString(context, R.string.dynamic_expression);
                                break;
                            }
                        } else {
                            digest = txtBody.getMessage();
                            break;
                        }
                    } else {
                        digest = getString(context, R.string.video_call) + txtBody.getMessage();
                        break;
                    }
                } else {
                    digest = getString(context, R.string.voice_call) + txtBody.getMessage();
                    break;
                }
            case FILE:
                try {
                    if (message.getStringAttribute("type").equals("Location")) {
                        digest = getString(context, R.string.location);
                        break;
                    }
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
                try {
                    if (new JSONObject(message.getStringAttribute(MessageEncoder.ATTR_EXT)).optString("type").equals("Location")) {
                        digest = getString(context, R.string.location);
                        break;
                    }
                } catch (Exception e2) {
                }
                digest = getString(context, R.string.file);
                break;
            default:
                EMLog.e(TAG, "error, unknow type");
                return "";
        }
        return digest;
    }

    static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    public static String getTopActivity(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return runningTaskInfos != null ? runningTaskInfos.get(0).topActivity.getClassName() : "";
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.easeui.utils.EaseCommonUtils$1GetInitialLetter] */
    public static String setUserInitialLetter(String str) {
        if (!TextUtils.isEmpty(str)) {
            return new Object() { // from class: com.easeui.utils.EaseCommonUtils.1GetInitialLetter
                String getLetter(String name) {
                    ArrayList<HanziToPinyin.Token> l;
                    String letter;
                    char c;
                    if (!TextUtils.isEmpty(name) && !Character.isDigit(name.toLowerCase().charAt(0)) && (l = HanziToPinyin.getInstance().get(name.substring(0, 1))) != null && l.size() > 0 && l.get(0).target.length() > 0 && (c = (letter = l.get(0).target.substring(0, 1).toUpperCase()).charAt(0)) >= 'A' && c <= 'Z') {
                        return letter;
                    }
                    return "#";
                }
            }.getLetter(str);
        }
        return "#";
    }

    public static EMConversation.EMConversationType getConversationType(int chatType) {
        if (chatType == 1) {
            return EMConversation.EMConversationType.Chat;
        }
        if (chatType == 2) {
            return EMConversation.EMConversationType.GroupChat;
        }
        return EMConversation.EMConversationType.ChatRoom;
    }

    public static boolean isSilentMessage(EMMessage message) {
        return message.getBooleanAttribute("em_ignore_notification", false);
    }
}
