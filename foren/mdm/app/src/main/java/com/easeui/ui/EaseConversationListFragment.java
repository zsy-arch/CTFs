package com.easeui.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.common.BaseFragment;
import com.cdlinglu.utils.DeviceTool;
import com.easeui.EaseConstant;
import com.easeui.model.EaseAtMessageHelper;
import com.easeui.widget.EaseConversationList;
import com.em.DemoHelper;
import com.em.RedPacketConstant;
import com.em.db.InviteMessgeDao;
import com.em.ui.ChatActivity;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.MyLog;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMConversationListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.NetUtils;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class EaseConversationListFragment extends BaseFragment {
    private static final int CONNECTED = 1;
    private static final int DISCONNECTED = 0;
    private static final int MSG_LOG = 4;
    private static final int MSG_PROMPT = 3;
    private static final int MSG_REFRESH = 2;
    protected ImageButton clearSearch;
    protected EaseConversationList conversationListView;
    protected FrameLayout errorItemContainer;
    private TextView errorText;
    protected boolean hidden;
    protected boolean isConflict;
    private EaseConversationListItemClickListener listItemClickListener;
    protected EditText query;
    protected List<EMConversation> conversationList = new ArrayList();
    private boolean isConnecting = false;
    protected EMConversationListener convListener = new EMConversationListener() { // from class: com.easeui.ui.EaseConversationListFragment.1
        @Override // com.hyphenate.EMConversationListener
        public void onCoversationUpdate() {
            EaseConversationListFragment.this.refreshMsg();
        }
    };
    protected EMConnectionListener connectionListener = new EMConnectionListener() { // from class: com.easeui.ui.EaseConversationListFragment.9
        @Override // com.hyphenate.EMConnectionListener
        public void onDisconnected(int error) {
            if (error == 207 || error == 206) {
                EaseConversationListFragment.this.isConflict = true;
            } else {
                EaseConversationListFragment.this.handler.sendEmptyMessage(0);
            }
        }

        @Override // com.hyphenate.EMConnectionListener
        public void onConnected() {
            EaseConversationListFragment.this.handler.sendEmptyMessage(1);
        }
    };
    protected Handler handler = new Handler() { // from class: com.easeui.ui.EaseConversationListFragment.10
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (!EaseConversationListFragment.this.isDetached()) {
                switch (msg.what) {
                    case 0:
                        EaseConversationListFragment.this.onConnectionDisconnected();
                        return;
                    case 1:
                        EaseConversationListFragment.this.onConnectionConnected();
                        return;
                    case 2:
                        EaseConversationListFragment.this.conversationList.clear();
                        EaseConversationListFragment.this.conversationList.addAll(EaseConversationListFragment.this.loadConversationList());
                        EaseConversationListFragment.this.conversationListView.setConversations(EaseConversationListFragment.this.conversationList);
                        EaseConversationListFragment.this.conversationListView.refresh();
                        MyLog.d("聊天列表更新");
                        return;
                    case 3:
                        EaseConversationListFragment.this.errorText.setText(EaseConversationListFragment.this.getString(msg.arg1));
                        return;
                    case 4:
                        MyLog.e(EaseConversationListFragment.this.getString(msg.arg1));
                        return;
                    default:
                        return;
                }
            }
        }
    };

    /* loaded from: classes.dex */
    public interface EaseConversationListItemClickListener {
        void onListItemClicked(EMConversation eMConversation);
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState == null || !savedInstanceState.getBoolean("isConflict", false)) {
            super.onActivityCreated(savedInstanceState);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_main_talk_fragment;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        this.conversationListView = (EaseConversationList) getView(R.id.list);
        this.query = (EditText) getView(R.id.query);
        this.clearSearch = (ImageButton) getView(R.id.search_clear);
        this.errorItemContainer = (FrameLayout) getView(R.id.fl_error_item);
        View errorView = View.inflate(getActivity(), R.layout.em_chat_neterror_item, null);
        this.errorItemContainer.addView(errorView);
        this.errorText = (TextView) errorView.findViewById(R.id.tv_connect_errormsg);
        ((LinearLayout) errorView.findViewById(R.id.ll_error_container)).setOnClickListener(new View.OnClickListener() { // from class: com.easeui.ui.EaseConversationListFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                EaseConversationListFragment.this.loginHX();
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.conversationList.addAll(loadConversationList());
        this.conversationListView.init(this.conversationList);
        if (this.listItemClickListener != null) {
            this.conversationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.easeui.ui.EaseConversationListFragment.3
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    EaseConversationListFragment.this.listItemClickListener.onListItemClicked(EaseConversationListFragment.this.conversationListView.getItem(position));
                }
            });
        }
        EMClient.getInstance().addConnectionListener(this.connectionListener);
        this.query.addTextChangedListener(new TextWatcher() { // from class: com.easeui.ui.EaseConversationListFragment.4
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EaseConversationListFragment.this.conversationListView.filter(s);
                if (s.length() > 0) {
                    EaseConversationListFragment.this.clearSearch.setVisibility(0);
                } else {
                    EaseConversationListFragment.this.clearSearch.setVisibility(4);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        this.clearSearch.setOnClickListener(new View.OnClickListener() { // from class: com.easeui.ui.EaseConversationListFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                EaseConversationListFragment.this.query.getText().clear();
                DeviceTool.hideSoftKeyboard(EaseConversationListFragment.this.getContext());
            }
        });
        this.conversationListView.setOnTouchListener(new View.OnTouchListener() { // from class: com.easeui.ui.EaseConversationListFragment.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                DeviceTool.hideSoftKeyboard(EaseConversationListFragment.this.getContext());
                return false;
            }
        });
        registerForContextMenu(this.conversationListView);
        this.conversationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.easeui.ui.EaseConversationListFragment.7
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = EaseConversationListFragment.this.conversationListView.getItem(position);
                String username = conversation.conversationId();
                if (username.equals(EMClient.getInstance().getCurrentUser())) {
                    Toast.makeText(EaseConversationListFragment.this.getActivity(), (int) R.string.cant_chat_with_yourself, 0).show();
                    return;
                }
                Bundle bundle = new Bundle();
                if (conversation.isGroup()) {
                    if (conversation.getType() == EMConversation.EMConversationType.ChatRoom) {
                        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, 3);
                    } else {
                        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, 2);
                    }
                }
                bundle.putString("username", username);
                EaseConversationListFragment.this.startAct(ChatActivity.class, bundle);
            }
        });
        this.conversationListView.setConversationListHelper(new EaseConversationList.EaseConversationListHelper() { // from class: com.easeui.ui.EaseConversationListFragment.8
            @Override // com.easeui.widget.EaseConversationList.EaseConversationListHelper
            public String onSetItemSecondaryText(EMMessage lastMessage) {
                if (!lastMessage.getBooleanAttribute(RedPacketConstant.MESSAGE_ATTR_IS_RED_PACKET_ACK_MESSAGE, false)) {
                    return null;
                }
                String sendNick = lastMessage.getStringAttribute(RedPacketConstant.EXTRA_RED_PACKET_SENDER_NAME, "");
                String receiveNick = lastMessage.getStringAttribute(RedPacketConstant.EXTRA_RED_PACKET_RECEIVER_NAME, "");
                return lastMessage.direct() == EMMessage.Direct.RECEIVE ? String.format(EaseConversationListFragment.this.getResources().getString(R.string.msg_someone_take_red_packet), receiveNick) : sendNick.equals(receiveNick) ? EaseConversationListFragment.this.getResources().getString(R.string.msg_take_red_packet) : String.format(EaseConversationListFragment.this.getResources().getString(R.string.msg_take_someone_red_packet), sendNick);
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    protected void onConnectionConnected() {
        this.errorItemContainer.setVisibility(8);
    }

    protected void onConnectionDisconnected() {
        this.errorItemContainer.setVisibility(0);
        if (NetUtils.hasNetwork(getActivity())) {
            MyLog.e(Integer.valueOf((int) R.string.is_logging_hx));
            loginHX();
            return;
        }
        this.errorText.setText(R.string.no_network_to_check);
    }

    public void refreshMsg() {
        if (!this.handler.hasMessages(2)) {
            this.handler.sendEmptyMessage(2);
        }
    }

    public void upPrompt(int promptId) {
        if (!this.handler.hasMessages(3)) {
            Message msg = this.handler.obtainMessage(3);
            msg.arg1 = promptId;
            this.handler.sendMessage(msg);
        }
    }

    public void showLog(int logId) {
        if (!this.handler.hasMessages(4)) {
            Message msg = this.handler.obtainMessage(4);
            msg.arg1 = logId;
            this.handler.sendMessage(msg);
        }
    }

    protected List<EMConversation> loadConversationList() {
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        List<Pair<Long, EMConversation>> sortList = new ArrayList<>();
        synchronized (conversations) {
            for (EMConversation conversation : conversations.values()) {
                if (conversation.getAllMessages().size() != 0) {
                    sortList.add(new Pair<>(Long.valueOf(conversation.getLastMessage().getMsgTime()), conversation));
                }
            }
        }
        try {
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> username = new ArrayList<>();
        ArrayList arrayList = new ArrayList();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            if (!username.contains(((EMConversation) sortItem.second).conversationId())) {
                username.add(((EMConversation) sortItem.second).conversationId());
                arrayList.add(sortItem.second);
            }
        }
        return arrayList;
    }

    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() { // from class: com.easeui.ui.EaseConversationListFragment.11
            public int compare(Pair<Long, EMConversation> con1, Pair<Long, EMConversation> con2) {
                if (((Long) con1.first).equals(con2.first)) {
                    return 0;
                }
                if (((Long) con2.first).longValue() > ((Long) con1.first).longValue()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    @Override // android.support.v4.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.hidden = hidden;
        if (!hidden) {
            this.query.setText("");
            if (!this.isConflict) {
                refreshMsg();
            }
        }
    }

    @Override // com.cdlinglu.common.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (!this.hidden) {
            refreshMsg();
        }
        if (this.errorItemContainer.getVisibility() == 0 && NetUtils.hasNetwork(getActivity())) {
            this.errorText.setText(R.string.try_connect);
            MyLog.e(Integer.valueOf((int) R.string.is_logging_hx));
            loginHX();
        }
    }

    @Override // android.support.v4.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.em_delete_message, menu);
    }

    @Override // android.support.v4.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        boolean deleteMessage = false;
        if (item.getItemId() == R.id.delete_message) {
            deleteMessage = true;
        } else if (item.getItemId() == R.id.delete_conversation) {
            deleteMessage = false;
        }
        EMConversation tobeDeleteCons = this.conversationListView.getItem(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
        if (tobeDeleteCons != null) {
            if (tobeDeleteCons.getType() == EMConversation.EMConversationType.GroupChat) {
                EaseAtMessageHelper.get().removeAtMeGroup(tobeDeleteCons.conversationId());
            }
            try {
                EMClient.getInstance().chatManager().deleteConversation(tobeDeleteCons.conversationId(), deleteMessage);
                new InviteMessgeDao(getActivity()).deleteMessage(tobeDeleteCons.conversationId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            refreshMsg();
            ((MainActivity) getActivity()).updateUnreadLabel();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginHX() {
        if (NetUtils.hasNetwork(getActivity()) && !this.isConnecting && isLogin()) {
            String userName = DemoHelper.getInstance().getCurrentUserName();
            String password = new AppShare(getActivity()).getString(Constant.PASSWORD);
            if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                this.isConnecting = true;
                this.errorText.setText(R.string.try_connect);
                EMClient.getInstance().login(userName, password, new EMCallBack() { // from class: com.easeui.ui.EaseConversationListFragment.12
                    @Override // com.hyphenate.EMCallBack
                    public void onSuccess() {
                        EMClient.getInstance().chatManager().loadAllConversations();
                        MyLog.e(Integer.valueOf((int) R.string.login_hx_success));
                        EaseConversationListFragment.this.isConnecting = false;
                        EaseConversationListFragment.this.refreshMsg();
                    }

                    @Override // com.hyphenate.EMCallBack
                    public void onError(int i, String s) {
                        MyLog.e(Integer.valueOf((int) R.string.login_hx_failed));
                        MyLog.e(s);
                        EaseConversationListFragment.this.isConnecting = false;
                        EaseConversationListFragment.this.upPrompt(R.string.not_connect_need_click);
                    }

                    @Override // com.hyphenate.EMCallBack
                    public void onProgress(int i, String s) {
                        EaseConversationListFragment.this.isConnecting = false;
                    }
                });
            }
        }
    }

    @Override // com.hy.frame.common.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().removeConnectionListener(this.connectionListener);
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.isConflict) {
            outState.putBoolean("isConflict", true);
        }
    }

    @Override // com.hy.frame.common.IFragmentListener
    public void sendMsg(int flag, Object obj) {
    }

    public void setConversationListItemClickListener(EaseConversationListItemClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
    }
}
