package com.easeui.widget.chatrow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.easeui.ui.EaseShowNormalFileActivity;
import com.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMNormalFileMessageBody;
import com.hyphenate.chat.a.c;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.FileUtils;
import com.hyphenate.util.TextFormater;
import com.vsf2f.f2f.R;
import java.io.File;

/* loaded from: classes.dex */
public class EaseChatRowFile extends EaseChatRow {
    private EMNormalFileMessageBody fileMessageBody;
    protected TextView fileNameView;
    protected TextView fileSizeView;
    protected TextView fileStateView;
    protected boolean isNotifyProcessed;
    protected EMCallBack sendfileCallBack;

    public EaseChatRowFile(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_file : R.layout.ease_row_sent_file, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.fileNameView = (TextView) findViewById(R.id.tv_file_name);
        this.fileSizeView = (TextView) findViewById(R.id.tv_file_size);
        this.fileStateView = (TextView) findViewById(R.id.tv_file_state);
        this.percentageView = (TextView) findViewById(R.id.percentage);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onSetUpView() {
        this.fileMessageBody = (EMNormalFileMessageBody) this.message.getBody();
        this.fileNameView.setText(this.fileMessageBody.getFileName());
        this.fileSizeView.setText(TextFormater.getDataSize(this.fileMessageBody.getFileSize()));
        if (this.message.direct() != EMMessage.Direct.RECEIVE) {
            handleSendMessage();
        } else if (new File(this.fileMessageBody.getLocalUrl()).exists()) {
            this.fileStateView.setText(R.string.have_downloaded);
        } else {
            this.fileStateView.setText(R.string.not_download);
        }
    }

    protected void handleSendMessage() {
        setMessageSendCallback();
        switch (this.message.status()) {
            case SUCCESS:
                this.progressBar.setVisibility(4);
                if (this.percentageView != null) {
                    this.percentageView.setVisibility(4);
                }
                this.statusView.setVisibility(4);
                return;
            case FAIL:
                this.progressBar.setVisibility(4);
                if (this.percentageView != null) {
                    this.percentageView.setVisibility(4);
                }
                this.statusView.setVisibility(0);
                return;
            case INPROGRESS:
                this.progressBar.setVisibility(0);
                if (this.percentageView != null) {
                    this.percentageView.setVisibility(0);
                    this.percentageView.setText(this.message.progress() + "%");
                }
                this.statusView.setVisibility(4);
                return;
            default:
                this.progressBar.setVisibility(4);
                if (this.percentageView != null) {
                    this.percentageView.setVisibility(4);
                }
                this.statusView.setVisibility(0);
                return;
        }
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onUpdateView() {
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onBubbleClick() {
        File file = new File(this.fileMessageBody.getLocalUrl());
        if (file.exists()) {
            FileUtils.openFile(file, (Activity) this.context);
        } else {
            this.context.startActivity(new Intent(this.context, EaseShowNormalFileActivity.class).putExtra(c.b, this.message.getBody()));
        }
        if (this.message.direct() == EMMessage.Direct.RECEIVE && !this.message.isAcked() && this.message.getChatType() == EMMessage.ChatType.Chat) {
            try {
                EMClient.getInstance().chatManager().ackMessageRead(this.message.getFrom(), this.message.getMsgId());
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
    }
}
