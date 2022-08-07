package com.easeui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.easeui.adapter.EaseConversationAdapter;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EaseConversationList extends ListView {
    protected EaseConversationAdapter adapter;
    protected Context context;
    private EaseConversationListHelper conversationListHelper;
    protected int primaryColor;
    protected int primarySize;
    protected int secondaryColor;
    protected int secondarySize;
    protected int timeColor;
    protected float timeSize;
    protected final int MSG_REFRESH_ADAPTER_DATA = 0;
    protected List<EMConversation> conversations = new ArrayList();
    Handler handler = new Handler() { // from class: com.easeui.widget.EaseConversationList.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (EaseConversationList.this.adapter != null) {
                        EaseConversationList.this.adapter.setDataResouce(EaseConversationList.this.conversations);
                        EaseConversationList.this.adapter.notifyDataSetChanged();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    /* loaded from: classes.dex */
    public interface EaseConversationListHelper {
        String onSetItemSecondaryText(EMMessage eMMessage);
    }

    public List<EMConversation> getConversations() {
        return this.conversations;
    }

    public void setConversations(List<EMConversation> conversations) {
        this.conversations = conversations;
    }

    public EaseConversationList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EaseConversationList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseConversationList);
        this.primaryColor = ta.getColor(0, getResources().getColor(R.color.gray_a));
        this.secondaryColor = ta.getColor(1, getResources().getColor(R.color.gray_blue));
        this.timeColor = ta.getColor(2, getResources().getColor(R.color.gray_b));
        this.primarySize = ta.getDimensionPixelSize(3, 0);
        this.secondarySize = ta.getDimensionPixelSize(4, 0);
        this.timeSize = ta.getDimension(5, 0.0f);
        ta.recycle();
    }

    public void init(List<EMConversation> conversationList) {
        init(conversationList, (EaseConversationListHelper) null);
    }

    public void init(List<EMConversation> conversationList, EaseConversationListHelper helper) {
        this.conversations = conversationList;
        if (helper != null) {
            this.conversationListHelper = helper;
        }
        this.adapter = new EaseConversationAdapter(this.context, 0, conversationList);
        this.adapter.setCvsListHelper(this.conversationListHelper);
        this.adapter.setPrimaryColor(this.primaryColor);
        this.adapter.setPrimarySize(this.primarySize);
        this.adapter.setSecondaryColor(this.secondaryColor);
        this.adapter.setSecondarySize(this.secondarySize);
        this.adapter.setTimeColor(this.timeColor);
        this.adapter.setTimeSize(this.timeSize);
        setAdapter((ListAdapter) this.adapter);
    }

    public EMConversation getItem(int position) {
        return this.adapter.getItem(position);
    }

    public void refresh() {
        if (!this.handler.hasMessages(0)) {
            this.handler.sendEmptyMessage(0);
        }
    }

    public void filter(CharSequence str) {
        this.adapter.getFilter().filter(str);
    }

    public void setConversationListHelper(EaseConversationListHelper helper) {
        this.conversationListHelper = helper;
    }
}
