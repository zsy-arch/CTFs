package com.easeui.widget.chatrow;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.easeui.widget.chatrow.EaseChatRow;
import com.hy.frame.util.MyLog;
import com.hyphenate.chat.EMFileMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMVoiceMessageBody;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseChatRowVoice extends EaseChatRowFile {
    private ImageView readStatusView;
    private ImageView voiceImageView;
    private TextView voiceLengthView;

    public EaseChatRowVoice(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_voice : R.layout.ease_row_sent_voice, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.voiceImageView = (ImageView) findViewById(R.id.iv_voice);
        this.voiceLengthView = (TextView) findViewById(R.id.tv_length);
        this.readStatusView = (ImageView) findViewById(R.id.iv_unread_voice);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onSetUpView() {
        EMVoiceMessageBody voiceBody = (EMVoiceMessageBody) this.message.getBody();
        if (voiceBody.getLength() > 0) {
            this.voiceLengthView.setText(voiceBody.getLength() + "\"");
            this.voiceLengthView.setVisibility(0);
        } else {
            this.voiceLengthView.setVisibility(4);
        }
        if (EaseChatRowVoicePlayClickListener.playMsgId != null && EaseChatRowVoicePlayClickListener.playMsgId.equals(this.message.getMsgId()) && EaseChatRowVoicePlayClickListener.isPlaying) {
            if (this.message.direct() == EMMessage.Direct.RECEIVE) {
                this.voiceImageView.setImageResource(R.drawable.voice_from_icon);
            } else {
                this.voiceImageView.setImageResource(R.drawable.voice_to_icon);
            }
            ((AnimationDrawable) this.voiceImageView.getDrawable()).start();
        } else if (this.message.direct() == EMMessage.Direct.RECEIVE) {
            this.voiceImageView.setImageResource(R.drawable.ease_chatfrom_voice_playing_f3);
        } else {
            this.voiceImageView.setImageResource(R.drawable.ease_chatto_voice_playing_f3);
        }
        if (this.message.direct() == EMMessage.Direct.RECEIVE) {
            if (this.message.isListened()) {
                this.readStatusView.setVisibility(4);
            } else {
                this.readStatusView.setVisibility(0);
            }
            MyLog.d("it is receive msg");
            if (voiceBody.downloadStatus() == EMFileMessageBody.EMDownloadStatus.DOWNLOADING || voiceBody.downloadStatus() == EMFileMessageBody.EMDownloadStatus.PENDING) {
                this.progressBar.setVisibility(0);
                setMessageReceiveCallback();
                return;
            }
            this.progressBar.setVisibility(4);
            return;
        }
        handleSendMessage();
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowFile, com.easeui.widget.chatrow.EaseChatRow
    protected void onBubbleClick() {
        new EaseChatRowVoicePlayClickListener(this.message, this.voiceImageView, this.readStatusView, this.adapter, this.activity).onClick(this.bubbleLayout);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
