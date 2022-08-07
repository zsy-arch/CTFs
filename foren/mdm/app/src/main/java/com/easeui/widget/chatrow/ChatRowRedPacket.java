package com.easeui.widget.chatrow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.MyHttpClient;
import com.cdlinglu.utils.ComUtil;
import com.easeui.utils.RedPacketUtil;
import com.easeui.widget.chatrow.EaseChatRow;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.http.IMyHttpListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.packet.RedPacketInfoBean;
import com.vsf2f.f2f.ui.dialog.RedPacketDialog;
import com.vsf2f.f2f.ui.packet.RedPacketDetailActivity;
import com.vsf2f.f2f.ui.utils.GameUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChatRowRedPacket extends EaseChatRow {
    private Holder h;
    private ViewHolder vh;

    public ChatRowRedPacket(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_red_packet : R.layout.ease_row_sent_red_packet, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.h = new Holder();
        this.vh = new ViewHolder();
        this.vh.mTvGreeting = (TextView) findViewById(R.id.tv_money_greeting);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onSetUpView() {
        int bgm;
        try {
            this.message.getStringAttribute("type");
            this.h.redPacketInfo = new RedPacketInfoBean();
            this.h.redPacketInfo.setRedpacketKeyRedpacketGreeting(this.message.getStringAttribute("RedpacketKeyRedpacketGreeting", ""));
            this.h.redPacketInfo.setSingleAmount(this.message.getStringAttribute("singleAmount", ""));
            this.h.redPacketInfo.setTotalAmount(this.message.getStringAttribute("totalAmount", ""));
            this.h.redPacketInfo.setUserName(this.message.getStringAttribute("userName", ""));
            this.h.redPacketInfo.setHeadURL(this.message.getStringAttribute("headURL", ""));
            this.h.redPacketInfo.setRedPacketId(this.message.getStringAttribute("redPacketId", ""));
            this.h.redPacketInfo.setMoneyType(this.message.getIntAttribute("moneyType", 0));
            this.h.redPacketInfo.setTotalNum(this.message.getIntAttribute("totalNum", 0));
            this.h.redPacketInfo.setModule(this.message.getIntAttribute(f.aj, 0));
            this.h.redPacketInfo.setReceived(this.message.getBooleanAttribute("received", false));
            this.vh.mTvGreeting.setText(this.h.redPacketInfo.getRedpacketKeyRedpacketGreeting());
            String str = "查看红包";
            if (this.h.redPacketInfo.isReceived()) {
                if (this.message.direct() == EMMessage.Direct.RECEIVE) {
                    bgm = R.drawable.ease_chatfrom_bg_redpacket2;
                } else {
                    bgm = R.drawable.ease_chatto_bg_redpacket2;
                }
                int status = this.message.getIntAttribute("receivedStatus", 0);
                if (status == 3) {
                    str = "红包已过期";
                } else if (status != 2) {
                    str = "红包已领取";
                } else if (this.message.getChatType() == EMMessage.ChatType.GroupChat) {
                    str = "红包已被领完";
                } else {
                    str = "红包已领取";
                }
            } else {
                this.h.isNormalStatus = true;
                if ((this.message.getChatType() == EMMessage.ChatType.Chat && this.message.direct() == EMMessage.Direct.RECEIVE) || this.message.getChatType() == EMMessage.ChatType.GroupChat) {
                    str = "领取红包";
                }
                if (this.message.direct() == EMMessage.Direct.RECEIVE) {
                    bgm = R.drawable.ease_chatfrom_bg_redpacket;
                } else {
                    bgm = R.drawable.ease_chatto_bg_redpacket;
                }
            }
            this.bubbleLayout.setBackgroundResource(bgm);
            ((TextView) findViewById(R.id.tv_status_str)).setText(str);
        } catch (HyphenateException e) {
            e.printStackTrace();
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
        if (this.h.redPacketInfo.isReceived()) {
            toRedPacketDetail();
        } else if ((this.message.getChatType() == EMMessage.ChatType.Chat && this.message.direct() == EMMessage.Direct.RECEIVE) || this.message.getChatType() == EMMessage.ChatType.GroupChat) {
            toOpenRedPacket();
        } else {
            toRedPacketDetail();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toRedPacketDetail() {
        toRedPacketDetail(false, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toRedPacketDetail(boolean isGet, String amount) {
        this.h.redPacketInfo.setSingleAmount(amount);
        Bundle bundle = new Bundle();
        bundle.putBoolean("get", isGet);
        bundle.putBoolean("normal", this.h.isNormalStatus);
        bundle.putSerializable("red", this.h.redPacketInfo);
        bundle.putParcelable("msg", this.message);
        Intent intent = new Intent(this.context, RedPacketDetailActivity.class);
        intent.putExtra(Constant.BUNDLE, bundle);
        this.context.startActivity(intent);
    }

    private void toOpenRedPacket() {
        if (this.vh.redPacketDialog == null) {
            this.vh.redPacketDialog = new RedPacketDialog(this.context, this.message.getChatType() == EMMessage.ChatType.Chat, this.h.redPacketInfo);
            this.vh.redPacketDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.easeui.widget.chatrow.ChatRowRedPacket.1
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    if (flag == 0) {
                        JSONObject jsonContent = new JSONObject();
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonContent.put("baseId", ChatRowRedPacket.this.h.redPacketInfo.redPacketId);
                            jsonObject.put("method", "vsf2f.account.cash.redpacket.open");
                            jsonObject.put("bizContent", ComUtil.UTF(jsonContent.toString()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        new MyHttpClient(ChatRowRedPacket.this.context, new IMyHttpListener() { // from class: com.easeui.widget.chatrow.ChatRowRedPacket.1.1
                            @Override // com.hy.http.IMyHttpListener
                            public void onRequestSuccess(ResultInfo result) {
                                RedPacketUtil.sendRedPacketAckMessage(ChatRowRedPacket.this.message, 1);
                                ChatRowRedPacket.this.listener.refreshMsgList(true);
                                String amount = "";
                                try {
                                    amount = new JSONObject((String) result.getObj()).optString("amount");
                                } catch (JSONException e2) {
                                    e2.printStackTrace();
                                }
                                ChatRowRedPacket.this.vh.redPacketDialog.dismiss();
                                ChatRowRedPacket.this.toRedPacketDetail(true, amount);
                            }

                            @Override // com.hy.http.IMyHttpListener
                            public void onRequestError(ResultInfo result) {
                                String error = result.getError();
                                int type = 0;
                                if (error.equals("take_again_error")) {
                                    type = 1;
                                } else if (error.equals("redpacket_finished") || error.equals("redpacket_empty")) {
                                    type = 2;
                                } else if (error.equals("redpacket_expired")) {
                                    type = 3;
                                }
                                if (type != 0) {
                                    RedPacketUtil.sendRedPacketAckMessage(ChatRowRedPacket.this.message, type);
                                    ChatRowRedPacket.this.listener.refreshMsgList(false);
                                }
                                if (ChatRowRedPacket.this.message.getChatType() == EMMessage.ChatType.Chat) {
                                    ChatRowRedPacket.this.vh.redPacketDialog.dismiss();
                                    ChatRowRedPacket.this.toRedPacketDetail();
                                    return;
                                }
                                ChatRowRedPacket.this.vh.redPacketDialog.setError(result.getMsg());
                            }
                        }).post(22, ComUtil.getZCApi(ChatRowRedPacket.this.context, ChatRowRedPacket.this.context.getString(R.string.API_RED_PACKET)), GameUtil.getVsSign(jsonObject.toString()), null, String.class, false);
                        return;
                    }
                    ChatRowRedPacket.this.vh.redPacketDialog.dismiss();
                    ChatRowRedPacket.this.toRedPacketDetail();
                }
            });
        }
        this.vh.redPacketDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewHolder {
        TextView mTvGreeting;
        RedPacketDialog redPacketDialog;

        ViewHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Holder {
        boolean isNormalStatus;
        RedPacketInfoBean redPacketInfo;

        Holder() {
        }
    }
}
