package com.easeui.widget.chatrow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.easeui.widget.chatrow.EaseChatRow;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import org.apache.http.HttpHost;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class EaseChatRowSubscription extends EaseChatRow {
    private Holder h;
    private ViewHolder vh;

    public EaseChatRowSubscription(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(R.layout.ease_row_received_subscription, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.vh = new ViewHolder();
        this.vh.txtTitle = (TextView) findViewById(R.id.temp_txtTitle);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    public void onSetUpView() {
        this.h = new Holder();
        try {
            JSONObject extJson = new JSONObject(this.message.getStringAttribute(MessageEncoder.ATTR_EXT));
            extJson.optString("type");
            this.h.time = extJson.optString(f.az);
            this.h.title = extJson.optString("title");
            this.h.cover = extJson.optString("cover");
            this.h.url = extJson.optString("url");
            this.vh.txtTitle.setText(this.h.title);
        } catch (Exception e) {
            HyUtil.printException(e);
        }
        handleTextMessage();
    }

    protected void handleTextMessage() {
        if (this.message.direct() == EMMessage.Direct.SEND) {
            setMessageSendCallback();
            switch (this.message.status()) {
                case CREATE:
                    this.progressBar.setVisibility(8);
                    this.statusView.setVisibility(0);
                    return;
                case SUCCESS:
                    this.progressBar.setVisibility(8);
                    this.statusView.setVisibility(8);
                    return;
                case FAIL:
                    this.progressBar.setVisibility(8);
                    this.statusView.setVisibility(0);
                    return;
                case INPROGRESS:
                    this.progressBar.setVisibility(0);
                    this.statusView.setVisibility(8);
                    return;
                default:
                    return;
            }
        } else if (!this.message.isAcked() && this.message.getChatType() == EMMessage.ChatType.Chat) {
            try {
                EMClient.getInstance().chatManager().ackMessageRead(this.message.getFrom(), this.message.getMsgId());
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onUpdateView() {
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onBubbleClick() {
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        if (!TextUtils.isEmpty(this.h.url)) {
            if (this.h.url.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                intent = new Intent(this.context, WebKitLocalActivity.class);
                bundle.putString(Constant.FLAG, this.h.url);
            }
            this.activity.startActivity(intent);
        }
    }

    /* loaded from: classes.dex */
    static class ViewHolder {
        TextView imgCover;
        TextView txtTitle;

        ViewHolder() {
        }
    }

    /* loaded from: classes.dex */
    static class Holder {
        String cover;
        String id;
        String time;
        String title;
        String url;

        Holder() {
        }
    }
}
