package com.easeui.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.easeui.widget.EaseChatMessageList;
import com.em.DemoHelper;
import com.em.RedPacketConstant;
import com.em.ui.EditActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.packet.RedPacketInfoBean;
import java.util.UUID;

/* loaded from: classes.dex */
public class RedPacketUtil {
    public static void openRedPacket(FragmentActivity activity, int chatType, EMMessage message, String toChatUsername, EaseChatMessageList messageList) {
        new ProgressDialog(activity).setCanceledOnTouchOutside(false);
    }

    public static EMMessage createRPMessage(Context context, RedPacketInfoBean redPacketInfo, EMMessage.ChatType chatType) {
        EMMessage message = EMMessage.createTxtSendMessage("[" + context.getResources().getString(R.string.mdm_redpacket) + "]" + redPacketInfo.RedpacketKeyRedpacketGreeting, redPacketInfo.toUserId);
        message.setChatType(chatType);
        message.setAttribute("userName", DemoHelper.getInstance().getCurrentUserNick());
        message.setAttribute("headURL", DemoHelper.getInstance().getCurrentUserPic().getSpath());
        message.setAttribute("type", redPacketInfo.type);
        message.setAttribute(f.aj, redPacketInfo.module);
        message.setAttribute("moneyType", redPacketInfo.moneyType);
        message.setAttribute("singleAmount", redPacketInfo.singleAmount);
        message.setAttribute("totalAmount", redPacketInfo.totalAmount);
        message.setAttribute("totalNum", redPacketInfo.totalNum);
        message.setAttribute("redPacketId", redPacketInfo.redPacketId);
        message.setAttribute("RedpacketKeyRedpacketGreeting", redPacketInfo.RedpacketKeyRedpacketGreeting);
        return message;
    }

    public static void sendRedPacketAckMessage(EMMessage message, int type) {
        String sendto;
        if (type == 1) {
            if (message.getChatType() == EMMessage.ChatType.GroupChat) {
                sendto = message.getTo();
            } else {
                sendto = message.getFrom();
            }
            EMMessage cmdMsg = EMMessage.createTxtSendMessage("领取了红包", sendto);
            cmdMsg.setUnread(false);
            cmdMsg.setChatType(message.getChatType());
            cmdMsg.setDirection(EMMessage.Direct.SEND);
            cmdMsg.setAttribute("type", "showRobRedPacket");
            cmdMsg.setAttribute("robNickName", DemoHelper.getInstance().getCurrentUserNick());
            cmdMsg.setAttribute("sendNickName", message.getStringAttribute("userName", ""));
            cmdMsg.setAttribute("senduserName", message.getFrom());
            cmdMsg.setAttribute("userName", DemoHelper.getInstance().getCurrentUserNick());
            cmdMsg.setAttribute("headURL", DemoHelper.getInstance().getCurrentUserPic().getSpath());
            EMClient.getInstance().chatManager().sendMessage(cmdMsg);
        }
        message.setAttribute("received", true);
        message.setAttribute("receivedStatus", type);
        EMClient.getInstance().chatManager().updateMessage(message);
    }

    public static void receiveRedPacketAckMessage(EMMessage message) {
        String senderNickname = message.getStringAttribute(RedPacketConstant.EXTRA_RED_PACKET_SENDER_NAME, "");
        String receiverNickname = message.getStringAttribute(RedPacketConstant.EXTRA_RED_PACKET_RECEIVER_NAME, "");
        String senderId = message.getStringAttribute(RedPacketConstant.EXTRA_RED_PACKET_SENDER_ID, "");
        String receiverId = message.getStringAttribute(RedPacketConstant.EXTRA_RED_PACKET_RECEIVER_ID, "");
        String groupId = message.getStringAttribute(RedPacketConstant.EXTRA_RED_PACKET_GROUP_ID, "");
        if (EMClient.getInstance().getCurrentUser().equals(senderId) && !receiverId.equals(senderId)) {
            EMMessage msg = EMMessage.createTxtSendMessage(EditActivity.CONTENT, groupId);
            msg.setChatType(EMMessage.ChatType.GroupChat);
            msg.setFrom(message.getFrom());
            if (TextUtils.isEmpty(groupId)) {
                msg.setTo(message.getTo());
            } else {
                msg.setTo(groupId);
            }
            msg.setMsgId(UUID.randomUUID().toString());
            msg.setMsgTime(message.getMsgTime());
            msg.setDirection(EMMessage.Direct.RECEIVE);
            msg.setUnread(false);
            msg.setAttribute(RedPacketConstant.MESSAGE_ATTR_IS_RED_PACKET_ACK_MESSAGE, true);
            msg.setAttribute(RedPacketConstant.EXTRA_RED_PACKET_SENDER_NAME, senderNickname);
            msg.setAttribute(RedPacketConstant.EXTRA_RED_PACKET_RECEIVER_NAME, receiverNickname);
            msg.setAttribute(RedPacketConstant.EXTRA_RED_PACKET_SENDER_ID, senderId);
            EMClient.getInstance().chatManager().saveMessage(msg);
        }
    }

    public static boolean isRandomRedPacket(EMMessage message) {
        String redPacketType = message.getStringAttribute(RedPacketConstant.MESSAGE_ATTR_RED_PACKET_TYPE, "");
        return !TextUtils.isEmpty(redPacketType) && redPacketType.equals(RedPacketConstant.RED_PACKET_TYPE_RANDOM);
    }
}
