package com.easeui.widget.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.easeui.widget.chatrow.EaseChatRow;
import com.em.DemoHelper;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class ChatRowRedPacketAck extends EaseChatRow {
    private ViewHolder vh;

    public ChatRowRedPacketAck(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(R.layout.ease_row_ack_red_packet_message, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.vh = new ViewHolder();
        this.vh.mTvMessage = (TextView) findViewById(R.id.ease_msg_tv_ack);
        this.vh.mIvMessage = (ImageView) findViewById(R.id.ease_msg_iv_ack);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onUpdateView() {
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onSetUpView() {
        String type = this.message.getStringAttribute("type", "");
        String currentUser = DemoHelper.getInstance().getCurrentUserName();
        if (type.equals("showRobRedPacket")) {
            this.vh.mIvMessage.setVisibility(0);
            String toUser = this.message.getStringAttribute("robNickName", "");
            String senderId = this.message.getStringAttribute("senduserName", "");
            String senderNick = this.message.getStringAttribute("sendNickName", "");
            if (this.message.direct() == EMMessage.Direct.SEND) {
                if (!this.message.getChatType().equals(EMMessage.ChatType.GroupChat)) {
                    this.vh.mTvMessage.setText(String.format(getResources().getString(R.string.msg_take_someone_red_packet), senderNick));
                } else if (senderId.equals(currentUser)) {
                    this.vh.mTvMessage.setText(R.string.msg_take_red_packet);
                } else {
                    this.vh.mTvMessage.setText(String.format(getResources().getString(R.string.msg_take_someone_red_packet), senderNick));
                }
            } else if (!this.message.getChatType().equals(EMMessage.ChatType.GroupChat)) {
                this.vh.mTvMessage.setText(String.format(getResources().getString(R.string.msg_someone_take_red_packet), toUser));
            } else if (senderId.equals(this.message.getFrom())) {
                this.vh.mTvMessage.setText(String.format(getResources().getString(R.string.msg_someone_take_self_red_packet), toUser));
            } else {
                this.vh.mTvMessage.setText(String.format(getResources().getString(R.string.msg_someone_take_someone_red_packet), toUser, senderNick));
            }
        } else if (type.equals("send_recall")) {
            this.vh.mIvMessage.setVisibility(8);
            String userName = this.message.getStringAttribute("userName", "");
            if (currentUser.equals(this.message.getFrom())) {
                this.vh.mTvMessage.setText("你撤回了一条消息");
            } else {
                this.vh.mTvMessage.setText(String.format("“%s”撤回了一条消息", userName));
            }
        }
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onBubbleClick() {
    }

    /* loaded from: classes.dex */
    class ViewHolder {
        ImageView mIvMessage;
        TextView mTvMessage;

        ViewHolder() {
        }
    }
}
