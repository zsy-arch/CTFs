package com.easeui.widget.chatrow;

import android.content.Context;
import android.text.Spannable;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.easeui.utils.EaseSmileUtils;
import com.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseChatRowText extends EaseChatRow {
    private TextView contentView;

    public EaseChatRowText(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_message : R.layout.ease_row_sent_message, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.contentView = (TextView) findViewById(R.id.tv_chatcontent);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    public void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) this.message.getBody();
        if (txtBody != null) {
            if (this.message.getBooleanAttribute("un_type", false)) {
                this.contentView.setText("此消息请更新最新版查看");
            } else {
                Spannable span = EaseSmileUtils.getSmiledText(this.context, txtBody.getMessage());
                this.contentView.setVisibility(0);
                this.contentView.setText(span, TextView.BufferType.SPANNABLE);
            }
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
    }
}
