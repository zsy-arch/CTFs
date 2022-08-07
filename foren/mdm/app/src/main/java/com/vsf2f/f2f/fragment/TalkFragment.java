package com.vsf2f.f2f.fragment;

import android.view.View;
import com.cdlinglu.common.MyApplication;
import com.easeui.ui.EaseConversationListFragment;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes2.dex */
public class TalkFragment extends EaseConversationListFragment {
    @Override // com.hy.frame.common.BaseFragment, android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        refreshUIWithMessage();
    }

    private void refreshUIWithMessage() {
        getActivity().runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.fragment.TalkFragment.1
            @Override // java.lang.Runnable
            public void run() {
                int unreadMsgCount = 0;
                for (EMConversation conversation : EMClient.getInstance().chatManager().getAllConversations().values()) {
                    unreadMsgCount = conversation.getUnreadMsgCount();
                }
                MyApplication.unreadMsgCount = unreadMsgCount;
                EventBus.getDefault().post(1);
            }
        });
    }

    @Override // com.easeui.ui.EaseConversationListFragment, com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.easeui.ui.EaseConversationListFragment, com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.easeui.ui.EaseConversationListFragment, com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    @Override // com.easeui.ui.EaseConversationListFragment, com.hy.frame.common.IFragmentListener
    public void sendMsg(int flag, Object obj) {
    }

    @Override // com.easeui.ui.EaseConversationListFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
