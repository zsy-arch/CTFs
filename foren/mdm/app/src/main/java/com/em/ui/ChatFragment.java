package com.em.ui;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;
import com.cdlinglu.utils.PermissionUtil;
import com.easeui.EaseConstant;
import com.easeui.ui.EaseChatFragment;
import com.easeui.widget.chatrow.EaseChatRowVoiceCall;
import com.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.em.DemoHelper;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.util.PathUtil;
import com.tencent.smtt.sdk.WebView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.packet.RedPacketSend2Activity;
import com.vsf2f.f2f.ui.packet.RedPacketSendActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import java.io.File;
import java.io.FileOutputStream;

/* loaded from: classes.dex */
public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {
    @Override // com.hy.frame.common.BaseFragment, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void refreshTitle(String s) {
        setTitle(s);
    }

    @Override // com.easeui.ui.EaseChatFragment
    protected void setUpView() {
        setChatFragmentListener(this);
        super.setUpView();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.easeui.ui.EaseChatFragment, android.support.v4.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri;
        String sendto;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 14) {
            switch (resultCode) {
                case 1:
                    this.clipboard.setPrimaryClip(ClipData.newPlainText(null, ((EMTextMessageBody) this.contextMenuMessage.getBody()).getMessage()));
                    break;
                case 2:
                    this.conversation.removeMessage(this.contextMenuMessage.getMsgId());
                    this.messageList.refresh();
                    break;
                case 3:
                    Intent intent = new Intent(getActivity(), ForwardMessageActivity.class);
                    intent.putExtra(EaseConstant.EXTRA_MSG_ID, this.contextMenuMessage.getMsgId());
                    startActivity(intent);
                    break;
                case 4:
                    MyToast.show(this.context, "功能未开放");
                    break;
                case 5:
                    String str = data.getStringExtra("str");
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.FLAG, str);
                    bundle.putBoolean(Constant.FLAG2, true);
                    startAct(WebKitLocalActivity.class, bundle);
                    break;
                case 6:
                    if (ActivityCompat.checkSelfPermission(this.context, "android.permission.CALL_PHONE") == 0) {
                        String tel = data.getStringExtra("str");
                        Intent intentTel = new Intent("android.intent.action.CALL");
                        intentTel.setData(Uri.parse(WebView.SCHEME_TEL + tel));
                        startActivity(intentTel);
                        break;
                    } else {
                        return;
                    }
                case 7:
                    if (System.currentTimeMillis() - this.contextMenuMessage.getMsgTime() <= 120000) {
                        EMMessage cmdMsg = EMMessage.createTxtSendMessage("撤回了一条消息", this.contextMenuMessage.getTo());
                        if (this.contextMenuMessage.getChatType() == EMMessage.ChatType.GroupChat) {
                            sendto = this.contextMenuMessage.getTo();
                        } else {
                            sendto = this.contextMenuMessage.getFrom();
                        }
                        cmdMsg.setUnread(false);
                        cmdMsg.setChatType(this.contextMenuMessage.getChatType());
                        cmdMsg.setDirection(EMMessage.Direct.SEND);
                        cmdMsg.setAttribute("type", "send_recall");
                        cmdMsg.setAttribute("messageId", this.contextMenuMessage.getMsgId());
                        cmdMsg.setAttribute("recall_conversationId", sendto);
                        cmdMsg.setAttribute("userName", DemoHelper.getInstance().getCurrentUserNick());
                        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
                        this.conversation.removeMessage(this.contextMenuMessage.getMsgId());
                        this.messageList.refresh();
                        break;
                    } else {
                        MyToast.show(this.context, "消息已超过两分钟，不能撤回");
                        return;
                    }
            }
        }
        if (resultCode == -1) {
            switch (requestCode) {
                case 11:
                    if (data != null) {
                        int duration = data.getIntExtra("dur", 0);
                        String videoPath = data.getStringExtra("path");
                        File file = new File(PathUtil.getInstance().getImagePath(), "thvideo" + System.currentTimeMillis());
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            ThumbnailUtils.createVideoThumbnail(videoPath, 3).compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            fos.close();
                            sendVideoMessage(videoPath, file.getAbsolutePath(), duration);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    } else {
                        return;
                    }
                case 12:
                    if (data != null && (uri = data.getData()) != null) {
                        sendFileByUri(uri);
                        return;
                    }
                    return;
                case 13:
                case 14:
                case 15:
                default:
                    return;
                case 16:
                    if (data != null) {
                    }
                    return;
            }
        }
    }

    @Override // com.easeui.ui.EaseChatFragment.EaseChatFragmentHelper
    public void onSetMessageAttributes(EMMessage message) {
    }

    @Override // com.easeui.ui.EaseChatFragment.EaseChatFragmentHelper
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return new CustomChatRowProvider();
    }

    @Override // com.easeui.ui.EaseChatFragment, com.cdlinglu.common.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.easeui.ui.EaseChatFragment.EaseChatFragmentHelper
    public void onEnterToChatDetails() {
    }

    @Override // com.hy.frame.common.BaseFragment, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        toDetails();
    }

    @Override // com.easeui.ui.EaseChatFragment.EaseChatFragmentHelper
    public void onAvatarClick(String username) {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        startAct(UserProfileActivity.class, bundle);
    }

    @Override // com.easeui.ui.EaseChatFragment.EaseChatFragmentHelper
    public void onAvatarLongClick(String username) {
        inputAtUsername(username);
    }

    @Override // com.easeui.ui.EaseChatFragment.EaseChatFragmentHelper
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override // com.easeui.ui.EaseChatFragment.EaseChatFragmentHelper
    public void onMessageBubbleLongClick(EMMessage message) {
        startActivityForResult(new Intent(getActivity(), ContextMenuActivity.class).putExtra("message", message), 14);
    }

    @Override // com.easeui.ui.EaseChatFragment.EaseChatFragmentHelper
    public boolean onExtendMenuItemClick(int itemId, View view) {
        switch (itemId) {
            case 11:
                if (!PermissionUtil.getExternalStoragePermissions(getActivity(), 111)) {
                    return false;
                }
                startActivityForResult(new Intent(getActivity(), ImageGridActivity.class), 11);
                return false;
            case 12:
                selectFileFromLocal();
                return false;
            case 13:
                startVoiceCall();
                return false;
            case 14:
                startVideoCall();
                return false;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            default:
                return false;
            case 21:
                Bundle bundle = new Bundle();
                bundle.putString("username", this.toChatUsername);
                if (this.chatType == 2) {
                    startActForResult(RedPacketSend2Activity.class, bundle, 16);
                    return false;
                }
                startActForResult(RedPacketSendActivity.class, bundle, 16);
                return false;
        }
    }

    protected void selectFileFromLocal() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("file/*");
        startActivityForResult(intent, 12);
    }

    protected void startVoiceCall() {
        if (!EMClient.getInstance().isConnected()) {
            Toast.makeText(getActivity(), (int) R.string.not_connect_to_server, 0).show();
            return;
        }
        startActivity(new Intent(getActivity(), VoiceCallActivity.class).putExtra("username", this.toChatUsername).putExtra("isComingCall", false));
        this.inputMenu.hideExtendMenuContainer();
    }

    protected void startVideoCall() {
        if (!EMClient.getInstance().isConnected()) {
            Toast.makeText(getActivity(), (int) R.string.not_connect_to_server, 0).show();
            return;
        }
        Intent intent = new Intent(getActivity(), VideoCallActivity.class);
        intent.putExtra("username", this.toChatUsername);
        intent.putExtra("isComingCall", false);
        startActivity(intent);
        this.inputMenu.hideExtendMenuContainer();
    }

    /* loaded from: classes.dex */
    private final class CustomChatRowProvider implements EaseCustomChatRowProvider {
        private CustomChatRowProvider() {
        }

        @Override // com.easeui.widget.chatrow.EaseCustomChatRowProvider
        public int getCustomChatRowTypeCount() {
            return 8;
        }

        @Override // com.easeui.widget.chatrow.EaseCustomChatRowProvider
        public int getCustomChatRowType(EMMessage message) {
            if (message.getType() != EMMessage.Type.TXT) {
                return 0;
            }
            if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VOICE_CALL, false)) {
                return message.direct() == EMMessage.Direct.RECEIVE ? 2 : 1;
            }
            if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VIDEO_CALL, false)) {
                return message.direct() == EMMessage.Direct.RECEIVE ? 4 : 3;
            }
            return 0;
        }

        @Override // com.easeui.widget.chatrow.EaseCustomChatRowProvider
        public EaseChatRowVoiceCall getCustomChatRow(EMMessage message, int position, BaseAdapter adapter) {
            if (message.getType() != EMMessage.Type.TXT || (!message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VOICE_CALL, false) && !message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VIDEO_CALL, false))) {
                return null;
            }
            return new EaseChatRowVoiceCall(ChatFragment.this.getActivity(), null, message, position, adapter);
        }
    }
}
