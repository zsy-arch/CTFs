package com.easeui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.easeui.adapter.EaseMessageAdapter;
import com.easeui.utils.EaseCommonUtils;
import com.easeui.widget.chatrow.EaseChatRow;
import com.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseChatMessageList extends RelativeLayout {
    protected static final String TAG = "EaseChatMessageList";
    protected int chatType;
    protected Context context;
    protected EMConversation conversation;
    protected ListView listView;
    protected EaseMessageAdapter messageAdapter;
    protected Drawable myBubbleBg;
    protected Drawable otherBuddleBg;
    protected boolean showAvatar;
    protected boolean showUserNick;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected String toChatUsername;

    /* loaded from: classes.dex */
    public interface MessageListItemClickListener {
        boolean onBubbleClick(EMMessage eMMessage);

        void onBubbleLongClick(EMMessage eMMessage);

        void onResendClick(EMMessage eMMessage);

        void onUserAvatarClick(String str);

        void onUserAvatarLongClick(String str);
    }

    public EaseChatMessageList(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public EaseChatMessageList(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseStyle(context, attrs);
        init(context);
    }

    public EaseChatMessageList(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.ease_chat_message_list, this);
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.chat_swipe_layout);
        this.listView = (ListView) findViewById(R.id.list);
    }

    public void init(Context context, EaseChatRow.GetClientListener listener, String toChatUsername, int chatType, EaseCustomChatRowProvider customChatRowProvider) {
        this.context = context;
        this.chatType = chatType;
        this.toChatUsername = toChatUsername;
        this.conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername, EaseCommonUtils.getConversationType(chatType), true);
        this.messageAdapter = new EaseMessageAdapter(context, listener, toChatUsername, chatType, this.listView);
        this.messageAdapter.setShowAvatar(this.showAvatar);
        this.messageAdapter.setShowUserNick(this.showUserNick);
        this.messageAdapter.setMyBubbleBg(this.myBubbleBg);
        this.messageAdapter.setOtherBuddleBg(this.otherBuddleBg);
        this.messageAdapter.setCustomChatRowProvider(customChatRowProvider);
        this.listView.setAdapter((ListAdapter) this.messageAdapter);
        refreshSelectLast();
    }

    protected void parseStyle(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseChatMessageList);
        this.showAvatar = ta.getBoolean(2, true);
        this.myBubbleBg = ta.getDrawable(0);
        this.otherBuddleBg = ta.getDrawable(0);
        this.showUserNick = ta.getBoolean(3, false);
        ta.recycle();
    }

    public void refresh() {
        if (this.messageAdapter != null) {
            this.messageAdapter.refresh();
        }
    }

    public void refreshSelectLast() {
        if (this.messageAdapter != null) {
            this.messageAdapter.refreshSelectLast();
        }
    }

    public void refreshSeekTo(int position) {
        if (this.messageAdapter != null) {
            this.messageAdapter.refreshSeekTo(position);
        }
    }

    public ListView getListView() {
        return this.listView;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return this.swipeRefreshLayout;
    }

    public EMMessage getItem(int position) {
        return this.messageAdapter.getItem(position);
    }

    public void setShowUserNick(boolean showUserNick) {
        this.showUserNick = showUserNick;
    }

    public boolean isShowUserNick() {
        return this.showUserNick;
    }

    public void setItemClickListener(MessageListItemClickListener listener) {
        if (this.messageAdapter != null) {
            this.messageAdapter.setItemClickListener(listener);
        }
    }

    public void setCustomChatRowProvider(EaseCustomChatRowProvider rowProvider) {
        if (this.messageAdapter != null) {
            this.messageAdapter.setCustomChatRowProvider(rowProvider);
        }
    }
}
