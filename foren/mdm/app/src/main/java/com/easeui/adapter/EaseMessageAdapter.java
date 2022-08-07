package com.easeui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.easeui.EaseConstant;
import com.easeui.utils.EaseCommonUtils;
import com.easeui.widget.EaseChatMessageList;
import com.easeui.widget.chatrow.ChatRowRedPacket;
import com.easeui.widget.chatrow.ChatRowRedPacketAck;
import com.easeui.widget.chatrow.EaseChatRow;
import com.easeui.widget.chatrow.EaseChatRowBigExpression;
import com.easeui.widget.chatrow.EaseChatRowExt;
import com.easeui.widget.chatrow.EaseChatRowFile;
import com.easeui.widget.chatrow.EaseChatRowImage;
import com.easeui.widget.chatrow.EaseChatRowLocation;
import com.easeui.widget.chatrow.EaseChatRowSubscription;
import com.easeui.widget.chatrow.EaseChatRowTemp;
import com.easeui.widget.chatrow.EaseChatRowText;
import com.easeui.widget.chatrow.EaseChatRowVideo;
import com.easeui.widget.chatrow.EaseChatRowVoice;
import com.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.MyLog;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.ui.utils.Constant;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class EaseMessageAdapter extends BaseAdapter {
    private static final int HANDLER_MESSAGE_REFRESH_LIST = 0;
    private static final int HANDLER_MESSAGE_SEEK_TO = 2;
    private static final int HANDLER_MESSAGE_SELECT_LAST = 1;
    private static final int MESSAGE_TYPE_RECV_EXPRESSION = 13;
    private static final int MESSAGE_TYPE_RECV_FILE = 11;
    private static final int MESSAGE_TYPE_RECV_IMAGE = 5;
    private static final int MESSAGE_TYPE_RECV_LOCATION = 4;
    private static final int MESSAGE_TYPE_RECV_TXT = 0;
    private static final int MESSAGE_TYPE_RECV_VIDEO = 9;
    private static final int MESSAGE_TYPE_RECV_VOICE = 7;
    private static final int MESSAGE_TYPE_SENT_EXPRESSION = 12;
    private static final int MESSAGE_TYPE_SENT_FILE = 10;
    private static final int MESSAGE_TYPE_SENT_IMAGE = 2;
    private static final int MESSAGE_TYPE_SENT_LOCATION = 3;
    private static final int MESSAGE_TYPE_SENT_TXT = 1;
    private static final int MESSAGE_TYPE_SENT_VIDEO = 8;
    private static final int MESSAGE_TYPE_SENT_VOICE = 6;
    private int chatType;
    private Context context;
    private EMConversation conversation;
    private EaseCustomChatRowProvider customRowProvider;
    private EaseChatMessageList.MessageListItemClickListener itemClickListener;
    private ListView listView;
    private EaseChatRow.GetClientListener listener;
    private Drawable myBubbleBg;
    private Drawable otherBuddleBg;
    private boolean showAvatar;
    private boolean showUserNick;
    private String toChatUsername;
    private EMMessage[] messages = null;
    private Handler handler = new Handler() { // from class: com.easeui.adapter.EaseMessageAdapter.1
        private void refreshList() {
            List<EMMessage> var = EaseMessageAdapter.this.conversation.getAllMessages();
            EaseMessageAdapter.this.messages = (EMMessage[]) var.toArray(new EMMessage[var.size()]);
            EaseMessageAdapter.this.conversation.markAllMessagesAsRead();
            EaseMessageAdapter.this.notifyDataSetChanged();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    refreshList();
                    return;
                case 1:
                    if (EaseMessageAdapter.this.messages.length > 0) {
                        EaseMessageAdapter.this.listView.setSelection(EaseMessageAdapter.this.messages.length - 1);
                        return;
                    }
                    return;
                case 2:
                    EaseMessageAdapter.this.listView.setSelection(message.arg1);
                    return;
                default:
                    return;
            }
        }
    };

    public EaseMessageAdapter(Context context, EaseChatRow.GetClientListener listener, String username, int chatType, ListView listView) {
        this.context = context;
        this.listView = listView;
        this.chatType = chatType;
        this.listener = listener;
        this.toChatUsername = username;
        this.conversation = EMClient.getInstance().chatManager().getConversation(username, EaseCommonUtils.getConversationType(chatType), true);
    }

    public void refresh() {
        if (!this.handler.hasMessages(0)) {
            this.handler.sendMessage(this.handler.obtainMessage(0));
        }
    }

    public void refreshSelectLast() {
        this.handler.removeMessages(0);
        this.handler.removeMessages(1);
        this.handler.sendEmptyMessageDelayed(0, 100L);
        this.handler.sendEmptyMessageDelayed(1, 100L);
    }

    public void refreshSeekTo(int position) {
        this.handler.sendMessage(this.handler.obtainMessage(0));
        Message msg = this.handler.obtainMessage(2);
        msg.arg1 = position;
        this.handler.sendMessage(msg);
    }

    @Override // android.widget.Adapter
    public EMMessage getItem(int position) {
        if (this.messages == null || position >= this.messages.length) {
            return null;
        }
        return this.messages[position];
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.messages == null) {
            return 0;
        }
        return this.messages.length;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        if (this.customRowProvider == null || this.customRowProvider.getCustomChatRowTypeCount() <= 0) {
            return 14;
        }
        return this.customRowProvider.getCustomChatRowTypeCount() + 14;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int position) {
        EMMessage message = getItem(position);
        if (message == null) {
            return -1;
        }
        if (this.customRowProvider != null && this.customRowProvider.getCustomChatRowType(message) > 0) {
            return this.customRowProvider.getCustomChatRowType(message) + 13;
        }
        if (message.getType() == EMMessage.Type.TXT) {
            return message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_BIG_EXPRESSION, false) ? message.direct() == EMMessage.Direct.RECEIVE ? 13 : 12 : message.direct() != EMMessage.Direct.RECEIVE ? 1 : 0;
        }
        if (message.getType() == EMMessage.Type.IMAGE) {
            return message.direct() == EMMessage.Direct.RECEIVE ? 5 : 2;
        }
        if (message.getType() == EMMessage.Type.LOCATION) {
            return message.direct() == EMMessage.Direct.RECEIVE ? 4 : 3;
        }
        if (message.getType() == EMMessage.Type.VOICE) {
            return message.direct() == EMMessage.Direct.RECEIVE ? 7 : 6;
        }
        if (message.getType() == EMMessage.Type.VIDEO) {
            return message.direct() == EMMessage.Direct.RECEIVE ? 9 : 8;
        }
        if (message.getType() == EMMessage.Type.FILE) {
            return message.direct() == EMMessage.Direct.RECEIVE ? 11 : 10;
        }
        return -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private EaseChatRow createChatRow(Context context, EMMessage message, int position) {
        char c = 0;
        EaseChatRow chatRow = null;
        if (this.customRowProvider != null && this.customRowProvider.getCustomChatRow(message, position, this) != null) {
            return this.customRowProvider.getCustomChatRow(message, position, this);
        }
        switch (message.getType()) {
            case TXT:
                if (!message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_BIG_EXPRESSION, false)) {
                    try {
                        String type = message.getStringAttribute("type", "");
                        if (TextUtils.isEmpty(type)) {
                            type = new JSONObject(message.getStringAttribute(MessageEncoder.ATTR_EXT)).getString("type");
                        }
                        switch (type.hashCode()) {
                            case -1335432629:
                                if (type.equals("demand")) {
                                    c = 4;
                                    break;
                                }
                                c = 65535;
                                break;
                            case -1249780219:
                                if (type.equals("opt_gxb")) {
                                    c = 14;
                                    break;
                                }
                                c = 65535;
                                break;
                            case -819951495:
                                if (type.equals("verify")) {
                                    c = 7;
                                    break;
                                }
                                c = 65535;
                                break;
                            case -700099527:
                                if (type.equals("openauthority")) {
                                    c = '\t';
                                    break;
                                }
                                c = 65535;
                                break;
                            case -542855215:
                                if (type.equals("showRobRedPacket")) {
                                    c = 18;
                                    break;
                                }
                                c = 65535;
                                break;
                            case -309474065:
                                if (type.equals(Constant.PRODUCT)) {
                                    c = 3;
                                    break;
                                }
                                c = 65535;
                                break;
                            case -154575960:
                                if (type.equals("send_recall")) {
                                    c = 17;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 3046160:
                                if (type.equals("card")) {
                                    break;
                                }
                                c = 65535;
                                break;
                            case 3165170:
                                if (type.equals("game")) {
                                    c = 1;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 3556308:
                                if (type.equals("temp")) {
                                    c = 6;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 17950486:
                                if (type.equals("gxb_service_order")) {
                                    c = '\r';
                                    break;
                                }
                                c = 65535;
                                break;
                            case 106006350:
                                if (type.equals("order")) {
                                    c = 2;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 341203229:
                                if (type.equals("subscription")) {
                                    c = 15;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 668936792:
                                if (type.equals("certify")) {
                                    c = '\b';
                                    break;
                                }
                                c = 65535;
                                break;
                            case 796319239:
                                if (type.equals("gxb_service")) {
                                    c = '\f';
                                    break;
                                }
                                c = 65535;
                                break;
                            case 977830009:
                                if (type.equals("redPacket")) {
                                    c = 16;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1104810929:
                                if (type.equals("gxb_share")) {
                                    c = '\n';
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1214035264:
                                if (type.equals("gxb_share_order")) {
                                    c = 11;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1984153269:
                                if (type.equals(NotificationCompat.CATEGORY_SERVICE)) {
                                    c = 5;
                                    break;
                                }
                                c = 65535;
                                break;
                            default:
                                c = 65535;
                                break;
                        }
                        switch (c) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                chatRow = new EaseChatRowExt(context, this.listener, message, position, this);
                                break;
                            case 6:
                            case 7:
                            case '\b':
                            case '\t':
                            case '\n':
                            case 11:
                            case '\f':
                            case '\r':
                            case 14:
                                chatRow = new EaseChatRowTemp(context, this.listener, message, position, this);
                                break;
                            case 15:
                                chatRow = new EaseChatRowSubscription(context, this.listener, message, position, this);
                                break;
                            case 16:
                                chatRow = new ChatRowRedPacket(context, this.listener, message, position, this);
                                break;
                            case 17:
                            case 18:
                                chatRow = new ChatRowRedPacketAck(context, this.listener, message, position, this);
                                break;
                            default:
                                MyLog.e("未处理的系统通知类型：'" + type + "'");
                                message.setAttribute("un_type", true);
                                chatRow = new EaseChatRowText(context, this.listener, message, position, this);
                                break;
                        }
                    } catch (HyphenateException | JSONException e) {
                        chatRow = new EaseChatRowText(context, this.listener, message, position, this);
                        break;
                    }
                } else {
                    chatRow = new EaseChatRowBigExpression(context, this.listener, message, position, this);
                    break;
                }
            case LOCATION:
                chatRow = new EaseChatRowLocation(context, this.listener, message, position, this);
                break;
            case FILE:
                try {
                    String type2 = message.getStringAttribute("type", "");
                    if (TextUtils.isEmpty(type2)) {
                        type2 = new JSONObject(message.getStringAttribute(MessageEncoder.ATTR_EXT)).optString("type");
                    }
                    if (type2.equals("Location")) {
                        chatRow = new EaseChatRowLocation(context, this.listener, message, position, this);
                        break;
                    }
                } catch (HyphenateException | JSONException e2) {
                    e2.printStackTrace();
                }
                chatRow = new EaseChatRowFile(context, this.listener, message, position, this);
                break;
            case IMAGE:
                chatRow = new EaseChatRowImage(context, this.listener, message, position, this);
                break;
            case VOICE:
                chatRow = new EaseChatRowVoice(context, this.listener, message, position, this);
                break;
            case VIDEO:
                chatRow = new EaseChatRowVideo(context, this.listener, message, position, this);
                break;
            case CMD:
                MyLog.e("cmd");
                break;
            default:
                MyLog.e(AppShare.SHARE_DEFAULT);
                break;
        }
        return chatRow;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"NewApi"})
    public View getView(int position, View convertView, ViewGroup parent) {
        EMMessage message = getItem(position);
        View convertView2 = createChatRow(this.context, message, position);
        if (convertView2 != null) {
            ((EaseChatRow) convertView2).setUpView(message, position, this.itemClickListener);
        }
        return convertView2;
    }

    public String getToChatUsername() {
        return this.toChatUsername;
    }

    public int getChatType() {
        return this.chatType;
    }

    public void setShowUserNick(boolean showUserNick) {
        this.showUserNick = showUserNick;
    }

    public void setShowAvatar(boolean showAvatar) {
        this.showAvatar = showAvatar;
    }

    public void setMyBubbleBg(Drawable myBubbleBg) {
        this.myBubbleBg = myBubbleBg;
    }

    public void setOtherBuddleBg(Drawable otherBuddleBg) {
        this.otherBuddleBg = otherBuddleBg;
    }

    public void setItemClickListener(EaseChatMessageList.MessageListItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setCustomChatRowProvider(EaseCustomChatRowProvider rowProvider) {
        this.customRowProvider = rowProvider;
    }

    public boolean isShowUserNick() {
        return this.showUserNick;
    }

    public boolean isShowAvatar() {
        return this.showAvatar;
    }

    public Drawable getMyBubbleBg() {
        return this.myBubbleBg;
    }

    public Drawable getOtherBuddleBg() {
        return this.otherBuddleBg;
    }
}
