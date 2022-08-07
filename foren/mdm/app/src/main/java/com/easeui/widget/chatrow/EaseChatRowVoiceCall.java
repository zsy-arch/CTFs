package com.easeui.widget.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.easeui.EaseConstant;
import com.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseChatRowVoiceCall extends EaseChatRow {
    private TextView contentvView;

    public EaseChatRowVoiceCall(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        if (this.message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VOICE_CALL, false)) {
            this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_voice_call : R.layout.ease_row_sent_voice_call, this);
        } else if (this.message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VIDEO_CALL, false)) {
            this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_video_call : R.layout.ease_row_sent_video_call, this);
        }
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.contentvView = (TextView) findViewById(R.id.tv_chatcontent);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onSetUpView() {
        this.contentvView.setText(((EMTextMessageBody) this.message.getBody()).getMessage());
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onUpdateView() {
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onBubbleClick() {
    }
}
