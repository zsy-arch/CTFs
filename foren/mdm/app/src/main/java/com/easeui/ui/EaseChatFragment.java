package com.easeui.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseFragment;
import com.cdlinglu.utils.CameraUtil;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.PermissionUtil;
import com.easeui.EaseConstant;
import com.easeui.controller.EaseUI;
import com.easeui.domain.EaseEmojicon;
import com.easeui.domain.EaseUser;
import com.easeui.model.EaseAtMessageHelper;
import com.easeui.utils.EaseCommonUtils;
import com.easeui.utils.EaseUserUtils;
import com.easeui.widget.EaseAlertDialog;
import com.easeui.widget.EaseChatExtendMenu;
import com.easeui.widget.EaseChatInputMenu;
import com.easeui.widget.EaseChatMessageList;
import com.easeui.widget.EaseVoiceRecorderView;
import com.easeui.widget.chatrow.EaseChatRow;
import com.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.easeui.widget.emojicon.EaseEmojiconMenu;
import com.em.DemoHelper;
import com.em.db.InviteMessgeDao;
import com.em.domain.EmojiconExampleGroupData;
import com.em.ui.EditActivity;
import com.em.ui.ForwardCardActivity;
import com.em.ui.GroupDetailsActivity;
import com.em.ui.PickMemberActivity;
import com.em.ui.UserProfileActivity;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.CameraDocument;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.EMVideoMessageBody;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.util.HanziToPinyin;
import com.hyphenate.util.NetUtils;
import com.tencent.open.SocialConstants;
import com.tencent.smtt.sdk.TbsReaderView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GroupBean;
import com.vsf2f.f2f.bean.GroupInfoBean;
import com.vsf2f.f2f.bean.GroupMembersBean;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.bean.result.GetGoldsBean;
import com.vsf2f.f2f.ui.map.MapLocationActivity;
import com.vsf2f.f2f.ui.otay.OtayDialog;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import com.vsf2f.f2f.ui.view.TeamHeadUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class EaseChatFragment extends BaseFragment implements EMMessageListener, EaseChatRow.GetClientListener {
    protected static final int ITEM_FILE = 12;
    protected static final int ITEM_LOCATION = 3;
    protected static final int ITEM_PICTURE = 2;
    protected static final int ITEM_RED_PACKET = 21;
    protected static final int ITEM_TAKE_PICTURE = 1;
    protected static final int ITEM_USERCARD = 4;
    protected static final int ITEM_VIDEO = 11;
    protected static final int ITEM_VIDEO_CALL = 14;
    protected static final int ITEM_VOICE_CALL = 13;
    protected static final int MESSAGE_TYPE_RECV_VIDEO_CALL = 4;
    protected static final int MESSAGE_TYPE_RECV_VOICE_CALL = 2;
    protected static final int MESSAGE_TYPE_SENT_VIDEO_CALL = 3;
    protected static final int MESSAGE_TYPE_SENT_VOICE_CALL = 1;
    protected static final int REQUEST_CODE_CAMERA = 2;
    protected static final int REQUEST_CODE_CARD = 5;
    protected static final int REQUEST_CODE_CONTEXT_MENU = 14;
    protected static final int REQUEST_CODE_GROUP_DETAIL = 13;
    protected static final int REQUEST_CODE_LOCAL = 3;
    protected static final int REQUEST_CODE_MAP = 1;
    protected static final int REQUEST_CODE_REDATA = 4;
    protected static final int REQUEST_CODE_SELECT_AT_USER = 15;
    protected static final int REQUEST_CODE_SELECT_FILE = 12;
    protected static final int REQUEST_CODE_SELECT_VIDEO = 11;
    protected static final int REQUEST_CODE_SEND_RED_PACKET = 16;
    protected static final String TAG = "EaseChatFragment";
    protected EaseChatFragmentHelper chatFragmentHelper;
    private EMChatRoomChangeListener chatRoomChangeListener;
    protected int chatType;
    protected ClipboardManager clipboard;
    protected EMMessage contextMenuMessage;
    protected EMConversation conversation;
    protected MyItemClickListener extendMenuItemClickListener;
    protected Bundle fragmentArgs;
    protected GroupListener groupListener;
    protected InputMethodManager inputManager;
    protected EaseChatInputMenu inputMenu;
    protected boolean isEnough;
    private boolean isMessageListInited;
    protected boolean isloading;
    protected ListView listView;
    private LinearLayout llyGood;
    private LinearLayout llyNeed;
    protected EaseChatMessageList messageList;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected String toChatUsername;
    protected EaseVoiceRecorderView voiceRecorderView;
    protected Handler handler = new Handler();
    protected int pagesize = 20;
    protected boolean haveMoreData = true;

    /* loaded from: classes.dex */
    public interface EaseChatFragmentHelper {
        void onAvatarClick(String str);

        void onAvatarLongClick(String str);

        void onEnterToChatDetails();

        boolean onExtendMenuItemClick(int i, View view);

        boolean onMessageBubbleClick(EMMessage eMMessage);

        void onMessageBubbleLongClick(EMMessage eMMessage);

        EaseCustomChatRowProvider onSetCustomChatRowProvider();

        void onSetMessageAttributes(EMMessage eMMessage);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.ease_fragment_chat;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.tab_talk, 0);
        this.fragmentArgs = getArguments();
        this.chatType = this.fragmentArgs.getInt(EaseConstant.EXTRA_CHAT_TYPE, 1);
        this.toChatUsername = this.fragmentArgs.getString("username");
        this.voiceRecorderView = (EaseVoiceRecorderView) getView().findViewById(R.id.voice_recorder);
        this.messageList = (EaseChatMessageList) getView().findViewById(R.id.message_list);
        if (this.chatType != 1) {
            this.messageList.setShowUserNick(true);
        }
        this.listView = this.messageList.getListView();
        this.extendMenuItemClickListener = new MyItemClickListener();
        this.inputMenu = (EaseChatInputMenu) getView().findViewById(R.id.input_menu);
        registerExtendMenuItem();
        this.inputMenu.init(null);
        this.inputMenu.setChatInputMenuListener(new EaseChatInputMenu.ChatInputMenuListener() { // from class: com.easeui.ui.EaseChatFragment.1
            @Override // com.easeui.widget.EaseChatInputMenu.ChatInputMenuListener
            public void onSendMessage(String content) {
                EaseChatFragment.this.sendTextMessage(content);
            }

            @Override // com.easeui.widget.EaseChatInputMenu.ChatInputMenuListener
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {
                return EaseChatFragment.this.voiceRecorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderView.EaseVoiceRecorderCallback() { // from class: com.easeui.ui.EaseChatFragment.1.1
                    @Override // com.easeui.widget.EaseVoiceRecorderView.EaseVoiceRecorderCallback
                    public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                        EaseChatFragment.this.sendVoiceMessage(voiceFilePath, voiceTimeLength);
                    }
                }, 3600, R.string.max_recording_time_is_too_short2);
            }

            @Override // com.easeui.widget.EaseChatInputMenu.ChatInputMenuListener
            public void onBigExpressionClicked(EaseEmojicon emojicon) {
                EaseChatFragment.this.sendBigExpressionMessage(emojicon.getName(), emojicon.getIdentityCode());
            }
        });
        this.swipeRefreshLayout = this.messageList.getSwipeRefreshLayout();
        this.swipeRefreshLayout.setColorSchemeResources(R.color.holo_blue_light, R.color.holo_green_light, R.color.holo_orange_light, R.color.holo_red_light);
        this.inputManager = (InputMethodManager) getActivity().getSystemService("input_method");
        this.clipboard = (ClipboardManager) getActivity().getSystemService("clipboard");
        getActivity().getWindow().setSoftInputMode(3);
        String ext = this.fragmentArgs.getString(MessageEncoder.ATTR_EXT);
        if (!TextUtils.isEmpty(ext)) {
            try {
                String type = new JSONObject(ext).optString("type");
                if (type.equals(Constant.PRODUCT)) {
                    initGoods();
                } else if (type.equals("demand") || type.equals(NotificationCompat.CATEGORY_SERVICE)) {
                    initNeeds();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (!EMClient.getInstance().isConnected()) {
            loginHX();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        ((EaseEmojiconMenu) this.inputMenu.getEmojiconMenu()).addEmojiconGroup(EmojiconExampleGroupData.getData());
        if (this.chatType == 2) {
            this.inputMenu.getPrimaryMenu().getEditText().addTextChangedListener(new TextWatcher() { // from class: com.easeui.ui.EaseChatFragment.2
                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count == 1 && "@".equals(String.valueOf(s.charAt(start)))) {
                        Intent intent = new Intent(EaseChatFragment.this.getActivity(), PickMemberActivity.class);
                        intent.putExtra(EaseConstant.EXTRA_GROUP_ID, EaseChatFragment.this.toChatUsername);
                        EaseChatFragment.this.startActivityForResult(intent, 15);
                    }
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                }
            });
        }
        setUpView();
    }

    public void initGoods() {
        this.llyGood = (LinearLayout) getView(R.id.chat_llyGood);
        ImageView imgGoodpic = (ImageView) getView(R.id.chat_imgGoodpic);
        TextView txtGoodname = (TextView) getView(R.id.chat_txtGoodname);
        TextView txtGoodprice = (TextView) getView(R.id.chat_txtGoodprice);
        try {
            JSONObject json = new JSONObject(this.fragmentArgs.getString(MessageEncoder.ATTR_EXT));
            txtGoodname.setText(json.optString("title"));
            txtGoodprice.setText("￥ " + json.optString(f.aS));
            Glide.with(this.context).load(json.optString(f.aY)).error((int) R.drawable.img_empty).into(imgGoodpic);
            setOnClickListener(R.id.chat_txtGoodSend);
            this.llyGood.setVisibility(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setOnClickListener(R.id.chat_imgGoodClose);
    }

    public void initNeeds() {
        this.llyNeed = (LinearLayout) getView(R.id.chat_llyNeed);
        ImageView imgNeedaddr = (ImageView) getView(R.id.chat_imgNeedaddr);
        TextView txtNeedtitle = (TextView) getView(R.id.chat_txtNeedtitle);
        TextView txtNeeddesc = (TextView) getView(R.id.chat_txtNeeddesc);
        TextView txtNeedprice = (TextView) getView(R.id.chat_txtNeedprice);
        TextView txtNeedunit = (TextView) getView(R.id.chat_txtNeedunit);
        TextView txtNeedaddr = (TextView) getView(R.id.chat_txtNeedaddr);
        TextView txtNeedmode = (TextView) getView(R.id.chat_txtNeedmode);
        TextView txtNeedbtn = (TextView) getViewAndClick(R.id.chat_txtNeedbtn);
        try {
            JSONObject json = new JSONObject(this.fragmentArgs.getString(MessageEncoder.ATTR_EXT));
            boolean isService = NotificationCompat.CATEGORY_SERVICE.equals(json.optString("type"));
            boolean serviceMode = "1".equals(json.optString("serviceMode"));
            txtNeedtitle.setText(json.optString("title"));
            txtNeeddesc.setText(json.optString(SocialConstants.PARAM_APP_DESC));
            txtNeedprice.setText(json.optString("reward"));
            txtNeedunit.setText("/ " + json.optString("unit"));
            txtNeedaddr.setText(json.optString("address"));
            txtNeedmode.setText(serviceMode ? "线下服务" : "线上服务");
            txtNeedbtn.setSelected(isService);
            txtNeedbtn.setText(isService ? "预约" : "抢单");
            imgNeedaddr.setSelected(isService);
            ((TextView) getViewAndClick(R.id.chat_txtNeedSend)).setText(isService ? "发送服务" : "发送需求");
            this.llyNeed.setVisibility(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setOnClickListener(R.id.chat_imgNeedClose);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow.GetClientListener
    public void getUserClient(String name) {
        List<String> list = new ArrayList<>();
        list.add(name);
        getUserClient(list);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow.GetClientListener
    public void refreshMsgList(boolean last) {
        if (last) {
            this.messageList.refreshSelectLast();
        } else {
            this.messageList.refresh();
        }
    }

    public void getUserClient(List<String> nameList) {
        getClient().post(R.string.API_GET_EXCHANGE_USERINFO, ComUtil.getF2FApi(this.context, getString(R.string.API_GET_EXCHANGE_USERINFO, DemoHelper.getInstance().getCurrentUserName())), new JSONArray((Collection) nameList).toString(), new AjaxParams(), UserInfo.class, true);
    }

    public void getGroupClient(String IMGroupId) {
        getClient().get(R.string.API_GET_IMGROUP_INFO, ComUtil.getF2FApi(this.context, getString(R.string.API_GET_IMGROUP_INFO, IMGroupId) + "?loadfriends=1"), new AjaxParams(), GroupInfoBean.class, false);
    }

    public void getGroupMembers9() {
        getClient().get(R.string.API_GET_GROUP_MEMBER9, ComUtil.getF2FApi(this.context, getString(R.string.API_GET_GROUP_MEMBER9, this.toChatUsername)), new AjaxParams(), String.class, true);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.chat_txtGoodSend /* 2131756407 */:
                if (this.llyGood != null) {
                    this.llyGood.setVisibility(8);
                }
                sendGoodMessage("[产品信息]", this.fragmentArgs.getString(MessageEncoder.ATTR_EXT));
                return;
            case R.id.chat_imgGoodClose /* 2131756408 */:
                if (this.llyGood != null) {
                    this.llyGood.setVisibility(8);
                    return;
                }
                return;
            case R.id.chat_txtNeedSend /* 2131756418 */:
                if (this.llyNeed != null) {
                    this.llyNeed.setVisibility(8);
                }
                sendGoodMessage("[需求服务信息]", this.fragmentArgs.getString(MessageEncoder.ATTR_EXT));
                return;
            case R.id.chat_imgNeedClose /* 2131756419 */:
                if (this.llyNeed != null) {
                    this.llyNeed.setVisibility(8);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseFragment, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        hideKeyboard();
        getActivity().onBackPressed();
    }

    @Override // com.cdlinglu.common.BaseFragment, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_GAME_POST /* 2131296348 */:
                GetGoldsBean getGoldsBean = (GetGoldsBean) result.getObj();
                this.isEnough = getGoldsBean.isIsEnough();
                if (getGoldsBean.isGainCoffer()) {
                    new OtayDialog(this.context, getGoldsBean.getNum()).show();
                    return;
                }
                return;
            case R.string.API_GET_EXCHANGE_USERINFO /* 2131296350 */:
                if (result.getObj() != null) {
                    List<UserInfo> list = (List) result.getObj();
                    if (list.size() == 1) {
                        EaseUser user = new EaseUser(list.get(0));
                        DemoHelper.getInstance().saveUser(user);
                        setTitle(user.getNick());
                    } else if (list.size() > 1) {
                        List<EaseUser> users = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            users.add(new EaseUser(list.get(i)));
                        }
                        DemoHelper.getInstance().saveUserList(users);
                    }
                    MyLog.e("保存" + this.toChatUsername + "到本地用户表");
                    this.messageList.refresh();
                    return;
                }
                return;
            case R.string.API_GET_GROUP_MEMBER9 /* 2131296357 */:
                List<String> paths = (List) result.getObj();
                String headPath = EaseUserUtils.getGroupPicPath(this.toChatUsername);
                try {
                    TeamHeadUtil headUtil = new TeamHeadUtil(this.context);
                    headUtil.getMultiImageData().setImageUrls(paths);
                    headUtil.getMultiImageData().setSavePath(headPath);
                    headUtil.setMaxWidthHeight(100, 100);
                    headUtil.setGap(1);
                    headUtil.load();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case R.string.API_GET_IMGROUP_INFO /* 2131296358 */:
                GroupInfoBean groupInfo = (GroupInfoBean) result.getObj();
                if (groupInfo != null) {
                    GroupBean group = new GroupBean(groupInfo);
                    if (HyUtil.isNoEmpty(group.getGroupName())) {
                        setTitle(group.getGroupName());
                    }
                    MyLog.e("保存" + this.toChatUsername + "到本地群组表");
                    DemoHelper.getInstance().saveGroup(group);
                    if (groupInfo.getFriends() != null) {
                        List<EaseUser> userList = new ArrayList<>();
                        for (GroupMembersBean member : groupInfo.getFriends()) {
                            userList.add(new EaseUser(member));
                        }
                        DemoHelper.getInstance().saveUserList(userList);
                        return;
                    }
                    return;
                }
                MyToast.show(getActivity(), (int) R.string.the_current_group);
                try {
                    EMClient.getInstance().chatManager().deleteConversation(this.toChatUsername, true);
                    new InviteMessgeDao(getActivity()).deleteMessage(this.toChatUsername);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                getActivity().finish();
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseFragment, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        MyLog.e("获取" + this.toChatUsername + "失败：" + result.getMsg());
        switch (result.getRequestCode()) {
            case R.string.API_GAME_POST /* 2131296348 */:
                MyLog.e(result.getMsg());
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUpView() {
        setTitle(this.toChatUsername);
        if (this.chatType == 1) {
            if (this.toChatUsername.equals("admin")) {
                setTitle(R.string.system_notice);
                this.inputMenu.setVisibility(8);
            } else {
                setHeaderRight(R.drawable.icon_chat_detail);
                EaseUser user = EaseUserUtils.getUserInfo(this.toChatUsername);
                if (user == null || user.getNickname() == null) {
                    getUserClient(this.toChatUsername);
                } else {
                    setTitle(user.getNick());
                }
            }
        } else if (this.chatType == 2) {
            setGroupListener();
            getGroupMembers9();
            GroupBean group = EaseUserUtils.getGroupInfo(this.toChatUsername);
            if (group == null) {
                getGroupClient(this.toChatUsername);
            } else if (HyUtil.isNoEmpty(group.getGroupName())) {
                setTitle(group.getGroupName());
            }
        } else if (this.chatType == 4) {
            this.inputMenu.getPrimaryMenu().showMenu(true);
        } else {
            onChatRoomViewCreation();
        }
        if (this.chatType != 3) {
            onConversationInit();
            onMessageListInit();
        }
        setRefreshLayoutListener();
        String forward_msg_id = getArguments().getString(EaseConstant.EXTRA_MSG_ID);
        if (forward_msg_id != null) {
            forwardMessage(forward_msg_id);
        }
    }

    private void setGroupListener() {
        this.groupListener = new GroupListener();
        setHeaderRight(R.drawable.icon_group_detail);
    }

    protected void registerExtendMenuItem() {
        this.inputMenu.registerExtendMenuItem(R.string.attach_take_pic, R.drawable.icon_chat_bottom_more_camera, 1, this.extendMenuItemClickListener);
        this.inputMenu.registerExtendMenuItem(R.string.attach_picture, R.drawable.icon_chat_bottom_more_photo, 2, this.extendMenuItemClickListener);
        if (this.chatType == 1) {
            this.inputMenu.registerExtendMenuItem(R.string.attach_voice_call, R.drawable.icon_chat_bottom_more_tellcall, 13, this.extendMenuItemClickListener);
            this.inputMenu.registerExtendMenuItem(R.string.attach_video_call, R.drawable.icon_chat_bottom_more_videocall, 14, this.extendMenuItemClickListener);
        }
        this.inputMenu.registerExtendMenuItem(R.string.attach_location, R.drawable.icon_chat_bottom_more_point, 3, this.extendMenuItemClickListener);
        this.inputMenu.registerExtendMenuItem(R.string.user_card, R.drawable.icon_chat_bottom_more_card, 4, this.extendMenuItemClickListener);
        this.inputMenu.registerExtendMenuItem(R.string.attach_red_packet, R.drawable.icon_chat_bottom_more_redpacket, 21, this.extendMenuItemClickListener);
        this.inputMenu.registerExtendMenuItem(R.string.attach_video, R.drawable.icon_chat_bottom_more_video, 11, this.extendMenuItemClickListener);
        this.inputMenu.registerExtendMenuItem(R.string.attach_file, R.drawable.icon_chat_bottom_more_file, 12, this.extendMenuItemClickListener);
    }

    protected void onConversationInit() {
        this.conversation = EMClient.getInstance().chatManager().getConversation(this.toChatUsername, EaseCommonUtils.getConversationType(this.chatType), true);
        this.conversation.markAllMessagesAsRead();
        List<EMMessage> msgs = this.conversation.getAllMessages();
        int msgCount = msgs != null ? msgs.size() : 0;
        if (msgCount < this.conversation.getAllMsgCount() && msgCount < this.pagesize) {
            String msgId = null;
            if (msgs != null && msgs.size() > 0) {
                msgId = msgs.get(0).getMsgId();
            }
            this.conversation.loadMoreMsgFromDB(msgId, this.pagesize - msgCount);
        }
    }

    protected void onMessageListInit() {
        this.messageList.init(this.context, this, this.toChatUsername, this.chatType, this.chatFragmentHelper != null ? this.chatFragmentHelper.onSetCustomChatRowProvider() : null);
        setListItemClickListener();
        this.messageList.getListView().setOnTouchListener(new View.OnTouchListener() { // from class: com.easeui.ui.EaseChatFragment.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                EaseChatFragment.this.hideKeyboard();
                EaseChatFragment.this.inputMenu.hideExtendMenuContainer();
                return false;
            }
        });
        this.isMessageListInited = true;
    }

    protected void setListItemClickListener() {
        this.messageList.setItemClickListener(new EaseChatMessageList.MessageListItemClickListener() { // from class: com.easeui.ui.EaseChatFragment.4
            @Override // com.easeui.widget.EaseChatMessageList.MessageListItemClickListener
            public void onUserAvatarClick(String username) {
                if (!EMClient.getInstance().getCurrentUser().equals(username) && EaseChatFragment.this.chatFragmentHelper != null) {
                    EaseChatFragment.this.chatFragmentHelper.onAvatarClick(username);
                }
            }

            @Override // com.easeui.widget.EaseChatMessageList.MessageListItemClickListener
            public void onUserAvatarLongClick(String username) {
                if (EaseChatFragment.this.chatFragmentHelper != null) {
                    EaseChatFragment.this.chatFragmentHelper.onAvatarLongClick(username);
                }
            }

            @Override // com.easeui.widget.EaseChatMessageList.MessageListItemClickListener
            public void onResendClick(final EMMessage message) {
                new EaseAlertDialog((Context) EaseChatFragment.this.getActivity(), (int) R.string.resend, (int) R.string.confirm_resend, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.easeui.ui.EaseChatFragment.4.1
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            EaseChatFragment.this.resendMessage(message);
                        }
                    }
                }, true).show();
            }

            @Override // com.easeui.widget.EaseChatMessageList.MessageListItemClickListener
            public void onBubbleLongClick(EMMessage message) {
                EaseChatFragment.this.contextMenuMessage = message;
                if (EaseChatFragment.this.chatFragmentHelper != null) {
                    EaseChatFragment.this.chatFragmentHelper.onMessageBubbleLongClick(message);
                }
            }

            @Override // com.easeui.widget.EaseChatMessageList.MessageListItemClickListener
            public boolean onBubbleClick(EMMessage message) {
                if (EaseChatFragment.this.chatFragmentHelper == null) {
                    return false;
                }
                return EaseChatFragment.this.chatFragmentHelper.onMessageBubbleClick(message);
            }
        });
    }

    protected void setRefreshLayoutListener() {
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.easeui.ui.EaseChatFragment.5
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() { // from class: com.easeui.ui.EaseChatFragment.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        List<EMMessage> messages;
                        if (EaseChatFragment.this.listView.getFirstVisiblePosition() != 0 || EaseChatFragment.this.isloading || !EaseChatFragment.this.haveMoreData) {
                            MyToast.show(EaseChatFragment.this.getActivity(), EaseChatFragment.this.getResources().getString(R.string.no_more_messages));
                        } else {
                            try {
                                if (EaseChatFragment.this.chatType == 1) {
                                    messages = EaseChatFragment.this.conversation.loadMoreMsgFromDB(EaseChatFragment.this.messageList.getItem(0).getMsgId(), EaseChatFragment.this.pagesize);
                                } else {
                                    messages = EaseChatFragment.this.conversation.loadMoreMsgFromDB(EaseChatFragment.this.messageList.getItem(0).getMsgId(), EaseChatFragment.this.pagesize);
                                }
                                if (messages.size() > 0) {
                                    EaseChatFragment.this.messageList.refreshSeekTo(messages.size() - 1);
                                    if (messages.size() != EaseChatFragment.this.pagesize) {
                                        EaseChatFragment.this.haveMoreData = false;
                                    }
                                } else {
                                    EaseChatFragment.this.haveMoreData = false;
                                }
                                EaseChatFragment.this.isloading = false;
                            } catch (Exception e) {
                                EaseChatFragment.this.swipeRefreshLayout.setRefreshing(false);
                                return;
                            }
                        }
                        EaseChatFragment.this.swipeRefreshLayout.setRefreshing(false);
                    }
                }, 600L);
            }
        });
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 1:
                    double latitude = data.getDoubleExtra("loc_latitude", 0.0d);
                    double longitude = data.getDoubleExtra("loc_longitude", 0.0d);
                    String address = data.getStringExtra("cus_address");
                    String filepath = data.getStringExtra(TbsReaderView.KEY_FILE_PATH);
                    String zoomLevel = data.getStringExtra("zoomLevel");
                    String scale = data.getStringExtra("scale");
                    if (!TextUtils.isEmpty(address)) {
                        sendLocationFileMessage(latitude, longitude, zoomLevel, scale, address, filepath);
                        return;
                    } else {
                        MyToast.show(this.context, getString(R.string.unable_to_get_loaction));
                        return;
                    }
                case 3:
                    if (data != null) {
                        for (String path2 : data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT)) {
                            Uri selectedImage = Uri.fromFile(new File(path2));
                            if (selectedImage != null) {
                                sendPicByUri(selectedImage);
                            }
                        }
                        return;
                    }
                    return;
                case 4:
                    getActivity().setResult(-1);
                    setUpView();
                    return;
                case 5:
                    String username = data.getStringExtra("username");
                    String nickname = data.getStringExtra(EaseConstant.EXTRA_NICK_NAME);
                    String avatarUrl = data.getStringExtra(EaseConstant.EXTRA_USER_AVATAR);
                    if (TextUtils.isEmpty(nickname)) {
                        nickname = username;
                    }
                    sendCardMessage(username, nickname, avatarUrl);
                    return;
                case 15:
                    if (data != null) {
                        inputAtUsername(data.getStringExtra("username"), false);
                        return;
                    }
                    return;
                case com.hy.frame.util.Constant.FLAG_UPLOAD_TAKE_PICTURE /* 1041 */:
                    if (data == null || data.getData() == null) {
                        path = CameraDocument.getPath(getContext(), CameraUtil.getCacheUri(this.context));
                    } else {
                        path = CameraDocument.getPath(getContext(), data.getData());
                    }
                    sendImageMessage(path);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.cdlinglu.common.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isMessageListInited) {
            this.messageList.refresh();
        }
        EaseUI.getInstance().pushActivity(getActivity());
        EMClient.getInstance().chatManager().addMessageListener(this);
        if (this.chatType == 2) {
            EaseAtMessageHelper.get().removeAtMeGroup(this.toChatUsername);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        EMClient.getInstance().chatManager().removeMessageListener(this);
        EaseUI.getInstance().popActivity(getActivity());
    }

    @Override // com.hy.frame.common.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.groupListener != null) {
            EMClient.getInstance().groupManager().removeGroupChangeListener(this.groupListener);
        }
        if (this.chatType == 3) {
            EMClient.getInstance().chatroomManager().leaveChatRoom(this.toChatUsername);
        }
        if (this.chatRoomChangeListener != null) {
            EMClient.getInstance().chatroomManager().removeChatRoomChangeListener(this.chatRoomChangeListener);
        }
    }

    public void onBackPressed() {
        if (this.inputMenu.onBackPressed()) {
            if (this.chatType == 2) {
                EaseAtMessageHelper.get().removeAtMeGroup(this.toChatUsername);
                EaseAtMessageHelper.get().cleanToAtUserList();
            }
            if (this.chatType == 3) {
                EMClient.getInstance().chatroomManager().leaveChatRoom(this.toChatUsername);
            }
        }
    }

    protected void onChatRoomViewCreation() {
        final ProgressDialog pd = ProgressDialog.show(getActivity(), "", "Joining......");
        EMClient.getInstance().chatroomManager().joinChatRoom(this.toChatUsername, new EMValueCallBack<EMChatRoom>() { // from class: com.easeui.ui.EaseChatFragment.6
            public void onSuccess(final EMChatRoom value) {
                EaseChatFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseChatFragment.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!EaseChatFragment.this.getActivity().isFinishing() && EaseChatFragment.this.toChatUsername.equals(value.getId())) {
                            pd.dismiss();
                            EMChatRoom room = EMClient.getInstance().chatroomManager().getChatRoom(EaseChatFragment.this.toChatUsername);
                            if (room != null) {
                                MyLog.d(EaseChatFragment.TAG, "join room success : " + room.getName());
                            }
                            EaseChatFragment.this.addChatRoomChangeListenr();
                            EaseChatFragment.this.onConversationInit();
                            EaseChatFragment.this.onMessageListInit();
                        }
                    }
                });
            }

            @Override // com.hyphenate.EMValueCallBack
            public void onError(int error, String errorMsg) {
                MyLog.d(EaseChatFragment.TAG, "join room failure : " + error);
                EaseChatFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseChatFragment.6.2
                    @Override // java.lang.Runnable
                    public void run() {
                        pd.dismiss();
                    }
                });
                EaseChatFragment.this.getActivity().finish();
            }
        });
    }

    protected void addChatRoomChangeListenr() {
        this.chatRoomChangeListener = new EMChatRoomChangeListener() { // from class: com.easeui.ui.EaseChatFragment.7
            @Override // com.hyphenate.EMChatRoomChangeListener
            public void onChatRoomDestroyed(String roomId, String roomName) {
                if (roomId.equals(EaseChatFragment.this.toChatUsername)) {
                    EaseChatFragment.this.showChatroomToast(" room : " + roomId + " with room name : " + roomName + " was destroyed");
                    EaseChatFragment.this.getActivity().finish();
                }
            }

            @Override // com.hyphenate.EMChatRoomChangeListener
            public void onMemberJoined(String roomId, String participant) {
                EaseChatFragment.this.showChatroomToast("member : " + participant + " join the room : " + roomId);
            }

            @Override // com.hyphenate.EMChatRoomChangeListener
            public void onMemberExited(String roomId, String roomName, String participant) {
                EaseChatFragment.this.showChatroomToast("member : " + participant + " leave the room : " + roomId + " room name : " + roomName);
            }

            @Override // com.hyphenate.EMChatRoomChangeListener
            public void onRemovedFromChatRoom(String roomId, String roomName, String participant) {
                if (!roomId.equals(EaseChatFragment.this.toChatUsername)) {
                    return;
                }
                if (EMClient.getInstance().getCurrentUser().equals(participant)) {
                    EMClient.getInstance().chatroomManager().leaveChatRoom(EaseChatFragment.this.toChatUsername);
                    EaseChatFragment.this.getActivity().finish();
                    return;
                }
                EaseChatFragment.this.showChatroomToast("member : " + participant + " was kicked from the room : " + roomId + " room name : " + roomName);
            }
        };
        EMClient.getInstance().chatroomManager().addChatRoomChangeListener(this.chatRoomChangeListener);
    }

    protected void showChatroomToast(final String toastContent) {
        getActivity().runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseChatFragment.8
            @Override // java.lang.Runnable
            public void run() {
                MyToast.show(EaseChatFragment.this.getActivity(), toastContent);
            }
        });
    }

    @Override // com.hyphenate.EMMessageListener
    public void onMessageReceived(List<EMMessage> messages) {
        String username;
        for (EMMessage message : messages) {
            if (message.getChatType() == EMMessage.ChatType.GroupChat || message.getChatType() == EMMessage.ChatType.ChatRoom) {
                username = message.getTo();
            } else {
                username = message.getFrom();
            }
            if (username.equals(this.toChatUsername)) {
                this.messageList.refresh();
                EaseUI.getInstance().getNotifier().vibrateAndPlayTone(message);
            } else {
                EaseUI.getInstance().getNotifier().onNewMsg(message);
            }
        }
    }

    @Override // com.hyphenate.EMMessageListener
    public void onCmdMessageReceived(List<EMMessage> messages) {
    }

    @Override // com.hyphenate.EMMessageListener
    public void onMessageRead(List<EMMessage> messages) {
        if (this.isMessageListInited) {
            this.messageList.refresh();
        }
    }

    @Override // com.hyphenate.EMMessageListener
    public void onMessageDelivered(List<EMMessage> messages) {
        if (this.isMessageListInited) {
            this.messageList.refresh();
        }
    }

    @Override // com.hyphenate.EMMessageListener
    public void onMessageChanged(EMMessage emMessage, Object change) {
        if (this.isMessageListInited) {
            this.messageList.refresh();
        }
    }

    @Override // com.hy.frame.common.IFragmentListener
    public void sendMsg(int flag, Object obj) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MyItemClickListener implements EaseChatExtendMenu.EaseChatExtendMenuItemClickListener {
        MyItemClickListener() {
        }

        @Override // com.easeui.widget.EaseChatExtendMenu.EaseChatExtendMenuItemClickListener
        public void onClick(int itemId, View view) {
            try {
                if (EaseChatFragment.this.chatFragmentHelper == null || !EaseChatFragment.this.chatFragmentHelper.onExtendMenuItemClick(itemId, view)) {
                    switch (itemId) {
                        case 1:
                            EaseChatFragment.this.selectPicFromCamera();
                            break;
                        case 2:
                            EaseChatFragment.this.selectPicFromLocal();
                            break;
                        case 3:
                            EaseChatFragment.this.startActivityForResult(new Intent(EaseChatFragment.this.getActivity(), MapLocationActivity.class), 1);
                            break;
                        case 4:
                            EaseChatFragment.this.startActivityForResult(new Intent(EaseChatFragment.this.getActivity(), ForwardCardActivity.class), 5);
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void inputAtUsername(String username, boolean autoAddAtSymbol) {
        if (!EMClient.getInstance().getCurrentUser().equals(username) && this.chatType == 2) {
            EaseAtMessageHelper.get().addAtUser(username);
            EaseUser user = EaseUserUtils.getUserInfo(username);
            if (user != null) {
                username = user.getNick();
            }
            if (autoAddAtSymbol) {
                this.inputMenu.insertText("@" + username + HanziToPinyin.Token.SEPARATOR);
            } else {
                this.inputMenu.insertText(username + HanziToPinyin.Token.SEPARATOR);
            }
        }
    }

    protected void inputAtUsername(String username) {
        inputAtUsername(username, true);
    }

    protected void sendTextMessage(String content) {
        if (EaseAtMessageHelper.get().containsAtUsername(content)) {
            sendAtMessage(content);
        } else {
            sendMessage(EMMessage.createTxtSendMessage(content, this.toChatUsername));
        }
    }

    private void sendAtMessage(String content) {
        if (this.chatType != 2) {
            MyLog.e(TAG, "only support group chat message");
            return;
        }
        EMMessage message = EMMessage.createTxtSendMessage(content, this.toChatUsername);
        EMGroup group = EMClient.getInstance().groupManager().getGroup(this.toChatUsername);
        if (group != null) {
            if (!EMClient.getInstance().getCurrentUser().equals(group.getOwner()) || !EaseAtMessageHelper.get().containsAtAll(content)) {
                message.setAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG, EaseAtMessageHelper.get().atListToJsonArray(EaseAtMessageHelper.get().getAtMessageUsernames(content)));
            } else {
                message.setAttribute(EaseConstant.MESSAGE_ATTR_AT_MSG, EaseConstant.MESSAGE_ATTR_VALUE_AT_MSG_ALL);
            }
        }
        sendMessage(message);
    }

    protected void sendBigExpressionMessage(String name, String identityCode) {
        sendMessage(EaseCommonUtils.createExpressionMessage(this.toChatUsername, name, identityCode));
    }

    protected void sendVoiceMessage(String filePath, int length) {
        sendMessage(EMMessage.createVoiceSendMessage(filePath, length, this.toChatUsername));
    }

    protected void sendImageMessage(String imagePath) {
        sendMessage(EMMessage.createImageSendMessage(imagePath, false, this.toChatUsername));
    }

    protected void sendLocationMessage(double latitude, double longitude, String locationAddress) {
        sendMessage(EMMessage.createLocationSendMessage(latitude, longitude, locationAddress, this.toChatUsername));
    }

    protected void sendLocationFileMessage(double latitude, double longitude, String zoomLevel, String scale, String locationAddress, String filePath) {
        EMMessage message = EMMessage.createFileSendMessage(filePath, this.toChatUsername);
        message.setAttribute("scale", scale + "");
        message.setAttribute("zoomLevel", zoomLevel + "");
        message.setAttribute("latitude", latitude + "");
        message.setAttribute("longitude", longitude + "");
        message.setAttribute("name", locationAddress);
        message.setAttribute("address", locationAddress);
        message.setAttribute("type", "Location");
        sendMessage(message);
    }

    protected void sendVideoMessage(String videoPath, String thumbPath, int videoLength) {
        sendMessage(EMMessage.createVideoSendMessage(videoPath, thumbPath, videoLength, this.toChatUsername));
    }

    protected void sendFileMessage(String filePath) {
        sendMessage(EMMessage.createFileSendMessage(filePath, this.toChatUsername));
    }

    protected void sendCardMessage(String username, String nickname, String avatarUrl) {
        JSONObject ext = new JSONObject();
        try {
            ext.put("username", username);
            ext.put(EaseConstant.EXTRA_NICK_NAME, nickname);
            ext.put(EaseConstant.EXTRA_USER_AVATAR, avatarUrl);
            ext.put("type", "card");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        EMMessage message = EMMessage.createSendMessage(EMMessage.Type.CMD);
        message.addBody(new EMTextMessageBody("[名片信息]"));
        message.setTo(this.toChatUsername);
        message.setAttribute("type", "card");
        message.setAttribute(MessageEncoder.ATTR_EXT, ext);
        sendMessage(message);
    }

    protected void sendGoodMessage(String msg, String ext) {
        try {
            JSONObject json = new JSONObject(ext);
            EMMessage message = EMMessage.createSendMessage(EMMessage.Type.CMD);
            EMTextMessageBody txtBody = new EMTextMessageBody(msg);
            message.setTo(this.toChatUsername);
            message.addBody(txtBody);
            message.setAttribute(MessageEncoder.ATTR_EXT, json);
            message.setAttribute("type", json.optString("type"));
            sendMessage(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void sendMessage(EMMessage message) {
        if (message != null) {
            if (!this.isEnough) {
                try {
                    if (new Random().nextInt(10) % 3 == 0) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("method", "vsf2f.game.zqly.manor.gift.otayonii");
                        jsonObject.put("bizContent", ComUtil.UTF(String.format("{\"userName\":\"%s\",\"type\":\"2050\"}", DemoHelper.getInstance().getCurrentUserName())));
                        getClient().post(R.string.API_GAME_POST, ComUtil.getGAMEApi(this.context, getString(R.string.API_GAME_POST)), GameUtil.getVsSign(jsonObject.toString(), true), null, GetGoldsBean.class, false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (this.chatFragmentHelper != null) {
                this.chatFragmentHelper.onSetMessageAttributes(message);
            }
            if (this.chatType == 2) {
                message.setChatType(EMMessage.ChatType.GroupChat);
            } else if (this.chatType == 3) {
                message.setChatType(EMMessage.ChatType.ChatRoom);
            }
            message.setAttribute("userName", DemoHelper.getInstance().getCurrentUserNick());
            message.setAttribute("headURL", DemoHelper.getInstance().getCurrentUserPic().getSpath());
            EMClient.getInstance().chatManager().sendMessage(message);
            if (this.isMessageListInited) {
                this.messageList.refreshSelectLast();
            }
        }
    }

    public void resendMessage(EMMessage message) {
        message.setStatus(EMMessage.Status.CREATE);
        EMClient.getInstance().chatManager().sendMessage(message);
        this.messageList.refresh();
    }

    protected void sendPicByUri(Uri selectedImage) {
        MyLog.d("sendPicByUri():Uri=>" + selectedImage + "|map.put(\"type\",\"card\");|path=>" + selectedImage.getPath());
        String[] filePathColumn = {"_data"};
        Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String picturePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();
            MyLog.d("picturePath=>" + picturePath);
            if (TextUtils.isEmpty(picturePath) || !new File(picturePath).exists()) {
                MyToast.show(getActivity(), (int) R.string.cant_find_pictures);
            } else {
                sendImageMessage(picturePath);
            }
        } else {
            File file = new File(selectedImage.getPath());
            MyLog.d("sendPicByUri():file.getAbsolutePath()=>" + file.getAbsolutePath());
            if (!file.exists()) {
                MyToast.show(getActivity(), (int) R.string.cant_find_pictures);
            } else {
                sendImageMessage(file.getAbsolutePath());
            }
        }
    }

    protected void sendFileByUri(Uri uri) {
        String filePath = null;
        if (EditActivity.CONTENT.equalsIgnoreCase(uri.getScheme())) {
            Cursor cursor = null;
            try {
                try {
                    cursor = getActivity().getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
                    int column_index = cursor.getColumnIndexOrThrow("_data");
                    if (cursor.moveToFirst()) {
                        filePath = cursor.getString(column_index);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            filePath = uri.getPath();
        }
        if (filePath != null) {
            File file = new File(filePath);
            if (!file.exists()) {
                MyToast.show(getActivity(), (int) R.string.file_does_not_exist);
            } else if (file.length() > 10485760) {
                MyToast.show(getActivity(), (int) R.string.file_is_not_greater_than_10_m);
            } else {
                sendFileMessage(filePath);
            }
        }
    }

    protected void selectPicFromCamera() {
        if (PermissionUtil.getCameraPermissions(getActivity(), 111)) {
            try {
                startActivityForResult(CameraUtil.getCameraIntent(this.context), com.hy.frame.util.Constant.FLAG_UPLOAD_TAKE_PICTURE);
            } catch (Exception e) {
                MyToast.show(this.context, getString(R.string.msg_no_camera));
                e.printStackTrace();
            }
        }
    }

    protected void selectPicFromLocal() {
        PhotoPickerIntent intent = new PhotoPickerIntent(this.context);
        intent.setSelectModel(SelectModel.MULTI);
        intent.setShowCamera(true);
        intent.setMaxTotal(3);
        startActivityForResult(intent, 3);
    }

    protected void emptyHistory() {
        new EaseAlertDialog((Context) getActivity(), (String) null, getResources().getString(R.string.whether_to_empty_all_chats), (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.easeui.ui.EaseChatFragment.9
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed) {
                    EMClient.getInstance().chatManager().deleteConversation(EaseChatFragment.this.toChatUsername, true);
                    EaseChatFragment.this.messageList.refresh();
                }
            }
        }, true).show();
    }

    protected void toDetails() {
        if (this.chatType == 1) {
            Bundle bundle = new Bundle();
            bundle.putString("username", this.toChatUsername);
            startActForResult(UserProfileActivity.class, bundle, 4);
        } else if (EaseUserUtils.getGroupInfo(this.toChatUsername) == null) {
            MyToast.show(getActivity(), (int) R.string.group_not_found);
        } else {
            Bundle bundle2 = new Bundle();
            bundle2.putString(EaseConstant.EXTRA_GROUP_ID, this.toChatUsername);
            startActForResult(GroupDetailsActivity.class, bundle2, 4);
        }
    }

    protected void hideKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != 2 && getActivity().getCurrentFocus() != null) {
            this.inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 2);
        }
    }

    protected void forwardMessage(String forward_msg_id) {
        EMMessage forward_msg = EMClient.getInstance().chatManager().getMessage(forward_msg_id);
        switch (forward_msg.getType()) {
            case TXT:
                if (!forward_msg.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_BIG_EXPRESSION, false)) {
                    String content = ((EMTextMessageBody) forward_msg.getBody()).getMessage();
                    try {
                        String extType = forward_msg.getStringAttribute("type", "");
                        if (TextUtils.isEmpty(extType)) {
                            JSONObject extJson = new JSONObject(forward_msg.getStringAttribute(MessageEncoder.ATTR_EXT));
                            if ("card".equals(extJson.optString("type"))) {
                                sendCardMessage(extJson.optString("username"), extJson.optString(EaseConstant.EXTRA_NICK_NAME), extJson.optString(EaseConstant.EXTRA_USER_AVATAR));
                            } else {
                                sendTextMessage(content);
                            }
                        } else if ("card".equals(extType)) {
                            sendCardMessage(forward_msg.getStringAttribute("username", ""), forward_msg.getStringAttribute(EaseConstant.EXTRA_NICK_NAME, ""), forward_msg.getStringAttribute(EaseConstant.EXTRA_USER_AVATAR, ""));
                        } else {
                            sendTextMessage(content);
                        }
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendTextMessage(content);
                        break;
                    }
                } else {
                    sendBigExpressionMessage(((EMTextMessageBody) forward_msg.getBody()).getMessage(), forward_msg.getStringAttribute(EaseConstant.MESSAGE_ATTR_EXPRESSION_ID, null));
                    break;
                }
            case IMAGE:
                String filePath = ((EMImageMessageBody) forward_msg.getBody()).getLocalUrl();
                if (filePath != null) {
                    if (!new File(filePath).exists()) {
                        filePath = ((EMImageMessageBody) forward_msg.getBody()).thumbnailLocalPath();
                    }
                    sendImageMessage(filePath);
                    break;
                }
                break;
            case VIDEO:
                String videoPath = ((EMVideoMessageBody) forward_msg.getBody()).getLocalUrl();
                if (videoPath != null) {
                    if (new File(videoPath).exists()) {
                        sendVideoMessage(videoPath, ((EMVideoMessageBody) forward_msg.getBody()).getLocalThumb(), ((EMVideoMessageBody) forward_msg.getBody()).getDuration());
                        break;
                    } else {
                        MyToast.show(this.context, "视频未下载，无法转发");
                        return;
                    }
                }
                break;
        }
        if (forward_msg.getChatType() == EMMessage.ChatType.ChatRoom) {
            EMClient.getInstance().chatroomManager().leaveChatRoom(forward_msg.getTo());
        }
    }

    private void loginHX() {
        if (NetUtils.hasNetwork(getActivity()) && isLogin()) {
            String userName = DemoHelper.getInstance().getCurrentUserName();
            String password = new AppShare(getActivity()).getString(Constant.PASSWORD);
            if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                EMClient.getInstance().login(userName, password, new EMCallBack() { // from class: com.easeui.ui.EaseChatFragment.10
                    @Override // com.hyphenate.EMCallBack
                    public void onSuccess() {
                        EMClient.getInstance().chatManager().loadAllConversations();
                        MyLog.e(Integer.valueOf((int) R.string.login_hx_success));
                    }

                    @Override // com.hyphenate.EMCallBack
                    public void onError(int i, String s) {
                        MyLog.e(s, Integer.valueOf((int) R.string.login_hx_failed));
                    }

                    @Override // com.hyphenate.EMCallBack
                    public void onProgress(int i, String s) {
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class GroupListener extends EaseGroupRemoveListener {
        GroupListener() {
        }

        @Override // com.hyphenate.EMGroupChangeListener
        public void onUserRemoved(final String groupId, String groupName) {
            EaseChatFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseChatFragment.GroupListener.1
                @Override // java.lang.Runnable
                public void run() {
                    if (EaseChatFragment.this.toChatUsername.equals(groupId)) {
                        MyToast.show(EaseChatFragment.this.getActivity(), (int) R.string.you_are_group);
                        Activity activity = EaseChatFragment.this.getActivity();
                        if (activity != null && !activity.isFinishing()) {
                            activity.finish();
                        }
                    }
                }
            });
        }

        @Override // com.hyphenate.EMGroupChangeListener
        public void onGroupDestroyed(final String groupId, String groupName) {
            EaseChatFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseChatFragment.GroupListener.2
                @Override // java.lang.Runnable
                public void run() {
                    if (EaseChatFragment.this.toChatUsername.equals(groupId)) {
                        MyToast.show(EaseChatFragment.this.getActivity(), (int) R.string.the_current_group);
                        Activity activity = EaseChatFragment.this.getActivity();
                        if (activity != null && !activity.isFinishing()) {
                            activity.finish();
                        }
                    }
                }
            });
        }
    }

    public void setChatFragmentListener(EaseChatFragmentHelper chatFragmentHelper) {
        this.chatFragmentHelper = chatFragmentHelper;
    }
}
