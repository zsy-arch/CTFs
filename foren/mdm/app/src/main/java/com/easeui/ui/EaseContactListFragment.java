package com.easeui.ui;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import com.cdlinglu.common.BaseFragment;
import com.cdlinglu.utils.DeviceTool;
import com.easeui.domain.EaseUser;
import com.easeui.utils.EaseCommonUtils;
import com.easeui.widget.EaseContactList;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.chat.EMClient;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class EaseContactListFragment extends BaseFragment {
    protected ImageButton clearSearch;
    protected EaseContactList contactListLayout;
    protected Map<String, EaseUser> contactsMap;
    protected boolean isConflict;
    private EaseContactListItemClickListener listItemClickListener;
    protected ListView listView;
    protected EditText query;
    protected String toBeProcessUsername;
    protected ArrayList<EaseUser> contactList = new ArrayList<>();
    protected EMConnectionListener connectionListener = new EMConnectionListener() { // from class: com.easeui.ui.EaseContactListFragment.6
        @Override // com.hyphenate.EMConnectionListener
        public void onDisconnected(int error) {
            if (error == 207 || error == 206) {
                EaseContactListFragment.this.isConflict = true;
            } else {
                EaseContactListFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseContactListFragment.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        EaseContactListFragment.this.onConnectionDisconnected();
                    }
                });
            }
        }

        @Override // com.hyphenate.EMConnectionListener
        public void onConnected() {
            EaseContactListFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseContactListFragment.6.2
                @Override // java.lang.Runnable
                public void run() {
                    EaseContactListFragment.this.onConnectionConnected();
                }
            });
        }
    };

    /* loaded from: classes.dex */
    public interface EaseContactListItemClickListener {
        void onListItemClicked(EaseUser easeUser);
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState == null || !savedInstanceState.getBoolean("isConflict", false)) {
            super.onActivityCreated(savedInstanceState);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_main_friend_fragment;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        this.contactListLayout = (EaseContactList) getView(R.id.fragment_contacts_list);
        this.listView = this.contactListLayout.getListView();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        setUpView();
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

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUpView() {
        EMClient.getInstance().addConnectionListener(this.connectionListener);
        this.contactListLayout.init(this.contactList);
        if (this.listItemClickListener != null) {
            this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.easeui.ui.EaseContactListFragment.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    EaseContactListFragment.this.listItemClickListener.onListItemClicked((EaseUser) EaseContactListFragment.this.listView.getItemAtPosition(position));
                }
            });
        }
        this.listView.setOnTouchListener(new View.OnTouchListener() { // from class: com.easeui.ui.EaseContactListFragment.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                DeviceTool.hideSoftKeyboard(EaseContactListFragment.this.context);
                return false;
            }
        });
        ThreadPool.newThreadPool(new Runnable() { // from class: com.easeui.ui.EaseContactListFragment.3
            @Override // java.lang.Runnable
            public void run() {
                EaseContactListFragment.this.sortContactList();
                EaseContactListFragment.this.contactListLayout.post(new Runnable() { // from class: com.easeui.ui.EaseContactListFragment.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        EaseContactListFragment.this.contactListLayout.init(EaseContactListFragment.this.contactList);
                    }
                });
            }
        });
    }

    public void refresh() {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.easeui.ui.EaseContactListFragment.4
            @Override // java.lang.Runnable
            public void run() {
                EaseContactListFragment.this.sortContactList();
                EaseContactListFragment.this.contactListLayout.post(new Runnable() { // from class: com.easeui.ui.EaseContactListFragment.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        EaseContactListFragment.this.contactListLayout.setContactList(EaseContactListFragment.this.contactList);
                    }
                });
            }
        });
    }

    @Override // com.hy.frame.common.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        EMClient.getInstance().removeConnectionListener(this.connectionListener);
        super.onDestroy();
    }

    protected synchronized void sortContactList() {
        this.contactList.clear();
        if (this.contactsMap != null) {
            this.contactList = new ArrayList<>(this.contactsMap.values());
            if (this.contactList.size() < 2000) {
                Iterator<EaseUser> it = this.contactList.iterator();
                while (it.hasNext()) {
                    EaseUser user = it.next();
                    user.setInitialLetter(EaseCommonUtils.setUserInitialLetter(user.getNick()));
                }
                Collections.sort(this.contactList, new Comparator<EaseUser>() { // from class: com.easeui.ui.EaseContactListFragment.5
                    public int compare(EaseUser lhs, EaseUser rhs) {
                        if (lhs.getInitialLetter().equals(rhs.getInitialLetter())) {
                            return lhs.getNick().compareTo(rhs.getNick());
                        }
                        if ("#".equals(lhs.getInitialLetter())) {
                            return 1;
                        }
                        if ("#".equals(rhs.getInitialLetter())) {
                            return -1;
                        }
                        return lhs.getInitialLetter().compareTo(rhs.getInitialLetter());
                    }
                });
            }
        }
    }

    protected void onConnectionDisconnected() {
    }

    protected void onConnectionConnected() {
    }

    public void setContactsMap(Map<String, EaseUser> contactsMap) {
        this.contactsMap = contactsMap;
    }

    @Override // com.hy.frame.common.IFragmentListener
    public void sendMsg(int flag, Object obj) {
    }

    public void setContactListItemClickListener(EaseContactListItemClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
    }
}
