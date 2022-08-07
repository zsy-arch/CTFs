package com.easeui.widget.chatrow;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.easeui.utils.RedPacketUtil;
import com.easeui.widget.chatrow.EaseChatRow;
import com.em.RedPacketConstant;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class ChatRowRandomPacket extends EaseChatRow {
    private TextView mTvGreeting;

    public ChatRowRandomPacket(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        keepFontSize();
        if (RedPacketUtil.isRandomRedPacket(this.message)) {
            this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.em_row_received_random_packet : R.layout.em_row_sent_random_packet, this);
        }
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.mTvGreeting = (TextView) findViewById(R.id.tv_money_greeting);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onUpdateView() {
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onSetUpView() {
        this.mTvGreeting.setText(this.message.getStringAttribute(RedPacketConstant.EXTRA_RED_PACKET_GREETING, ""));
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onBubbleClick() {
    }

    public void keepFontSize() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
