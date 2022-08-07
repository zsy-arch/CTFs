package com.easeui.widget.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.easeui.EaseConstant;
import com.easeui.controller.EaseUI;
import com.easeui.domain.EaseEmojicon;
import com.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseChatRowBigExpression extends EaseChatRowText {
    private ImageView imageView;

    public EaseChatRowBigExpression(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowText, com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_bigexpression : R.layout.ease_row_sent_bigexpression, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowText, com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.percentageView = (TextView) findViewById(R.id.percentage);
        this.imageView = (ImageView) findViewById(R.id.image);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRowText, com.easeui.widget.chatrow.EaseChatRow
    public void onSetUpView() {
        String emojiconId = this.message.getStringAttribute(EaseConstant.MESSAGE_ATTR_EXPRESSION_ID, null);
        EaseEmojicon emojicon = null;
        if (EaseUI.getInstance().getEmojiconInfoProvider() != null) {
            emojicon = EaseUI.getInstance().getEmojiconInfoProvider().getEmojiconInfo(emojiconId);
        }
        if (emojicon != null) {
            if (emojicon.getBigIcon() != 0) {
                Glide.with(this.activity).load(Integer.valueOf(emojicon.getBigIcon())).error((int) R.drawable.ease_default_expression).into(this.imageView);
            } else if (emojicon.getBigIconPath() != null) {
                Glide.with(this.activity).load(emojicon.getBigIconPath()).error((int) R.drawable.ease_default_expression).into(this.imageView);
            } else {
                this.imageView.setImageResource(R.drawable.ease_default_expression);
            }
        }
        handleTextMessage();
    }
}
