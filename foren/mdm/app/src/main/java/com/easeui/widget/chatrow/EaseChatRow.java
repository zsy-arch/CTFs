package com.easeui.widget.chatrow;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.cdlinglu.utils.TimeUtil;
import com.easeui.adapter.EaseMessageAdapter;
import com.easeui.domain.EaseUser;
import com.easeui.utils.EaseUserUtils;
import com.easeui.widget.EaseChatMessageList;
import com.em.db.DemoDBManager;
import com.hy.frame.util.HyUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.DateUtils;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ChatImageBean;

/* loaded from: classes.dex */
public abstract class EaseChatRow extends LinearLayout {
    protected TextView ackedView;
    protected Activity activity;
    protected BaseAdapter adapter;
    protected View bubbleLayout;
    protected Context context;
    protected TextView deliveredView;
    protected LayoutInflater inflater;
    protected EaseChatMessageList.MessageListItemClickListener itemClickListener;
    protected GetClientListener listener;
    protected EMMessage message;
    protected EMCallBack messageReceiveCallback;
    protected EMCallBack messageSendCallback;
    protected TextView percentageView;
    protected int position;
    protected ProgressBar progressBar;
    protected ImageView statusView;
    protected TextView timeStampView;
    protected ImageView userAvatarView;
    protected TextView usernickView;

    /* loaded from: classes.dex */
    public interface GetClientListener {
        void getUserClient(String str);

        void refreshMsgList(boolean z);
    }

    protected abstract void onBubbleClick();

    protected abstract void onFindViewById();

    protected abstract void onInflateView();

    protected abstract void onSetUpView();

    protected abstract void onUpdateView();

    public EaseChatRow(Context context, GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context);
        this.context = context;
        this.activity = (Activity) context;
        this.message = message;
        this.position = position;
        this.listener = listener;
        this.adapter = adapter;
        this.inflater = LayoutInflater.from(context);
        initView();
    }

    private void initView() {
        onInflateView();
        this.bubbleLayout = findViewById(R.id.bubble);
        this.usernickView = (TextView) findViewById(R.id.tv_userid);
        this.timeStampView = (TextView) findViewById(R.id.timestamp);
        this.userAvatarView = (ImageView) findViewById(R.id.iv_userhead);
        this.deliveredView = (TextView) findViewById(R.id.tv_delivered);
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.statusView = (ImageView) findViewById(R.id.msg_status);
        this.ackedView = (TextView) findViewById(R.id.tv_ack);
        onFindViewById();
    }

    public void setUpView(EMMessage message, int position, EaseChatMessageList.MessageListItemClickListener itemClickListener) {
        this.message = message;
        this.position = position;
        this.itemClickListener = itemClickListener;
        setUpBaseView();
        onSetUpView();
        setClickListener();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    private void setUpBaseView() {
        TextView timestamp = (TextView) findViewById(R.id.timestamp);
        if (timestamp != null) {
            if (this.position == 0) {
                timestamp.setText(TimeUtil.formatChatTime(this.message.getMsgTime()));
                timestamp.setVisibility(0);
            } else {
                EMMessage prevMessage = (EMMessage) this.adapter.getItem(this.position - 1);
                if (prevMessage == null || !DateUtils.isCloseEnough(this.message.getMsgTime(), prevMessage.getMsgTime())) {
                    timestamp.setText(TimeUtil.formatChatTime(this.message.getMsgTime()));
                    timestamp.setVisibility(0);
                } else {
                    timestamp.setVisibility(8);
                }
            }
        }
        if (this.message.direct() == EMMessage.Direct.SEND) {
            EaseUserUtils.setMineAvatar(this.context, this.userAvatarView);
        } else {
            try {
                String userName = this.message.getStringAttribute("userName");
                if (HyUtil.isNoEmpty(userName)) {
                    this.usernickView.setText(userName);
                } else {
                    this.usernickView.setText(this.message.getFrom());
                }
                String headURL = this.message.getStringAttribute("headURL");
                if (HyUtil.isNoEmpty(headURL)) {
                    Glide.with(this.context).load(headURL).error((int) R.mipmap.def_head).into(this.userAvatarView);
                } else {
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.def_head)).into(this.userAvatarView);
                }
            } catch (HyphenateException e) {
                e.printStackTrace();
                getLocUser();
            }
        }
        if (this.deliveredView != null) {
            if (this.message.isDelivered()) {
                this.deliveredView.setVisibility(0);
            } else {
                this.deliveredView.setVisibility(4);
            }
        }
        if (this.ackedView != null) {
            if (this.message.isAcked()) {
                if (this.deliveredView != null) {
                    this.deliveredView.setVisibility(4);
                }
                this.ackedView.setVisibility(0);
            } else {
                this.ackedView.setVisibility(4);
            }
        }
        if (this.adapter instanceof EaseMessageAdapter) {
            if (((EaseMessageAdapter) this.adapter).isShowAvatar()) {
                this.userAvatarView.setVisibility(0);
            } else {
                this.userAvatarView.setVisibility(8);
            }
            if (this.usernickView != null) {
                if (((EaseMessageAdapter) this.adapter).isShowUserNick()) {
                    this.usernickView.setVisibility(0);
                } else {
                    this.usernickView.setVisibility(8);
                }
            }
            if (this.message.direct() == EMMessage.Direct.SEND) {
                if (((EaseMessageAdapter) this.adapter).getMyBubbleBg() != null) {
                    this.bubbleLayout.setBackgroundDrawable(((EaseMessageAdapter) this.adapter).getMyBubbleBg());
                }
            } else if (this.message.direct() == EMMessage.Direct.RECEIVE && ((EaseMessageAdapter) this.adapter).getOtherBuddleBg() != null) {
                this.bubbleLayout.setBackgroundDrawable(((EaseMessageAdapter) this.adapter).getOtherBuddleBg());
            }
        }
    }

    public void getLocUser() {
        String chatname = this.message.getFrom();
        EaseUser user = EaseUserUtils.getContactInfo(chatname);
        if (user == null) {
            user = EaseUserUtils.getUserInfo(chatname);
        }
        if (user != null) {
            try {
                this.usernickView.setText(user.getNick());
                String avater = user.getAvatar();
                if (!TextUtils.isEmpty(avater)) {
                    Glide.with(this.context).load(avater).error((int) R.mipmap.def_head).into(this.userAvatarView);
                } else {
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.def_head)).into(this.userAvatarView);
                }
            } catch (Exception e) {
                Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.def_head)).into(this.userAvatarView);
            }
        } else if (this.listener != null) {
            this.listener.getUserClient(chatname);
        } else {
            this.usernickView.setText(chatname);
            Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.def_head)).into(this.userAvatarView);
        }
    }

    protected void setMessageSendCallback() {
        if (this.messageSendCallback == null) {
            this.messageSendCallback = new EMCallBack() { // from class: com.easeui.widget.chatrow.EaseChatRow.1
                @Override // com.hyphenate.EMCallBack
                public void onSuccess() {
                    try {
                        EMImageMessageBody imgBody = (EMImageMessageBody) EaseChatRow.this.message.getBody();
                        ChatImageBean imageBean = new ChatImageBean();
                        imageBean.setMsgid(EaseChatRow.this.message.getMsgId());
                        imageBean.setUsername(EaseChatRow.this.message.getFrom());
                        imageBean.setChatname(EaseChatRow.this.message.getTo());
                        imageBean.setHttpurl(imgBody.getRemoteUrl());
                        imageBean.setLocalpath(imgBody.getLocalUrl());
                        DemoDBManager.getInstance().saveImage(imageBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    EaseChatRow.this.updateView();
                }

                @Override // com.hyphenate.EMCallBack
                public void onProgress(final int progress, String status) {
                    EaseChatRow.this.activity.runOnUiThread(new Runnable() { // from class: com.easeui.widget.chatrow.EaseChatRow.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (EaseChatRow.this.percentageView != null) {
                                EaseChatRow.this.percentageView.setText(progress + "%");
                            }
                        }
                    });
                }

                @Override // com.hyphenate.EMCallBack
                public void onError(int code, String error) {
                    EaseChatRow.this.updateView(code, error);
                }
            };
        }
        this.message.setMessageStatusCallback(this.messageSendCallback);
    }

    protected void setMessageReceiveCallback() {
        if (this.messageReceiveCallback == null) {
            this.messageReceiveCallback = new EMCallBack() { // from class: com.easeui.widget.chatrow.EaseChatRow.2
                @Override // com.hyphenate.EMCallBack
                public void onSuccess() {
                    EaseChatRow.this.updateView();
                }

                @Override // com.hyphenate.EMCallBack
                public void onProgress(final int progress, String status) {
                    EaseChatRow.this.activity.runOnUiThread(new Runnable() { // from class: com.easeui.widget.chatrow.EaseChatRow.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (EaseChatRow.this.percentageView != null) {
                                EaseChatRow.this.percentageView.setText(progress + "%");
                            }
                        }
                    });
                }

                @Override // com.hyphenate.EMCallBack
                public void onError(int code, String error) {
                    EaseChatRow.this.updateView(code, error);
                }
            };
        }
        this.message.setMessageStatusCallback(this.messageReceiveCallback);
    }

    private void setClickListener() {
        if (this.bubbleLayout != null) {
            this.bubbleLayout.setOnClickListener(new View.OnClickListener() { // from class: com.easeui.widget.chatrow.EaseChatRow.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (EaseChatRow.this.itemClickListener != null && !EaseChatRow.this.itemClickListener.onBubbleClick(EaseChatRow.this.message)) {
                        EaseChatRow.this.onBubbleClick();
                    }
                }
            });
            this.bubbleLayout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.easeui.widget.chatrow.EaseChatRow.4
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View v) {
                    if (EaseChatRow.this.itemClickListener == null) {
                        return true;
                    }
                    EaseChatRow.this.itemClickListener.onBubbleLongClick(EaseChatRow.this.message);
                    return true;
                }
            });
        }
        if (this.statusView != null) {
            this.statusView.setOnClickListener(new View.OnClickListener() { // from class: com.easeui.widget.chatrow.EaseChatRow.5
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (EaseChatRow.this.itemClickListener != null) {
                        EaseChatRow.this.itemClickListener.onResendClick(EaseChatRow.this.message);
                    }
                }
            });
        }
        if (this.userAvatarView != null) {
            this.userAvatarView.setOnClickListener(new View.OnClickListener() { // from class: com.easeui.widget.chatrow.EaseChatRow.6
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (EaseChatRow.this.itemClickListener == null) {
                        return;
                    }
                    if (EaseChatRow.this.message.direct() == EMMessage.Direct.SEND) {
                        EaseChatRow.this.itemClickListener.onUserAvatarClick(EMClient.getInstance().getCurrentUser());
                    } else {
                        EaseChatRow.this.itemClickListener.onUserAvatarClick(EaseChatRow.this.message.getFrom());
                    }
                }
            });
            this.userAvatarView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.easeui.widget.chatrow.EaseChatRow.7
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View v) {
                    if (EaseChatRow.this.itemClickListener == null) {
                        return false;
                    }
                    if (EaseChatRow.this.message.direct() == EMMessage.Direct.SEND) {
                        EaseChatRow.this.itemClickListener.onUserAvatarLongClick(EMClient.getInstance().getCurrentUser());
                    } else {
                        EaseChatRow.this.itemClickListener.onUserAvatarLongClick(EaseChatRow.this.message.getFrom());
                    }
                    return true;
                }
            });
        }
    }

    protected void updateView() {
        this.activity.runOnUiThread(new Runnable() { // from class: com.easeui.widget.chatrow.EaseChatRow.8
            @Override // java.lang.Runnable
            public void run() {
                if (EaseChatRow.this.message.status() == EMMessage.Status.FAIL) {
                    Toast.makeText(EaseChatRow.this.activity, EaseChatRow.this.activity.getString(R.string.send_fail) + EaseChatRow.this.activity.getString(R.string.connect_fail_toast), 0).show();
                }
                EaseChatRow.this.onUpdateView();
            }
        });
    }

    protected void updateView(final int errorCode, String desc) {
        this.activity.runOnUiThread(new Runnable() { // from class: com.easeui.widget.chatrow.EaseChatRow.9
            @Override // java.lang.Runnable
            public void run() {
                if (errorCode == 501) {
                    Toast.makeText(EaseChatRow.this.activity, EaseChatRow.this.activity.getString(R.string.send_fail) + EaseChatRow.this.activity.getString(R.string.error_send_invalid_content), 0).show();
                } else if (errorCode == 602 || errorCode == 303) {
                    Toast.makeText(EaseChatRow.this.activity, EaseChatRow.this.activity.getString(R.string.send_fail) + EaseChatRow.this.activity.getString(R.string.error_send_not_in_the_group), 0).show();
                } else if (errorCode == 210) {
                    Toast.makeText(EaseChatRow.this.activity, EaseChatRow.this.activity.getString(R.string.send_fail) + "对方不想和你说话", 0).show();
                } else {
                    Toast.makeText(EaseChatRow.this.activity, EaseChatRow.this.activity.getString(R.string.send_fail) + EaseChatRow.this.activity.getString(R.string.connect_fail_toast), 0).show();
                }
                EaseChatRow.this.onUpdateView();
            }
        });
    }
}
