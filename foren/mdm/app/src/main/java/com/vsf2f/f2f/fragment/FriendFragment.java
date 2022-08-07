package com.vsf2f.f2f.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.DeviceTool;
import com.easeui.domain.EaseUser;
import com.easeui.ui.EaseContactListFragment;
import com.em.DemoHelper;
import com.em.db.InviteMessgeDao;
import com.em.ui.ChatActivity;
import com.em.ui.GroupsActivity;
import com.em.ui.NewFriendsMsgActivity;
import com.em.ui.UserProfileActivity;
import com.em.widget.ContactItemView;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.bean.result.FriendNexusBean;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.user.HelpCenterActivity;
import com.vsf2f.f2f.ui.user.InvitedActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/* loaded from: classes2.dex */
public class FriendFragment extends EaseContactListFragment implements SwipeRefreshLayout.OnRefreshListener {
    private ContactItemView applicationItem;
    private BlackListSyncListener blackListSyncListener;
    private ContactInfoSyncListener contactInfoSyncListener;
    private ContactSyncListener contactSyncListener;
    private FrameLayout fly_contact_list;
    private InviteMessgeDao inviteMessgeDao;
    private boolean isCanRefresh;
    private boolean isInitData;
    private boolean isShowLoading;
    private View loadingView;
    private boolean needRefresh;
    private boolean readRefresh;
    private SwipeRefreshLayout swipeRefresh;
    private Map<String, EaseUser> userMap = new HashMap();

    public FriendFragment() {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.fragment.FriendFragment.1
            @Override // java.lang.Runnable
            public void run() {
                FriendFragment.this.initDataMap();
                FriendFragment.this.isInitData = true;
            }
        });
    }

    @Override // com.easeui.ui.EaseContactListFragment, com.hy.frame.common.IBaseActivity
    public void initView() {
        super.initView();
        this.fly_contact_list = (FrameLayout) getView(R.id.fragment_contacts_container);
        initLoadView();
        initContactsHead();
        registerForContextMenu(this.listView);
        if (!this.isInitData) {
            return;
        }
        if (this.userMap.size() == 0) {
            refresh();
            return;
        }
        super.refresh();
        refreshUnRead();
        this.loadingView.setVisibility(this.isShowLoading ? 0 : 8);
    }

    private void initLoadView() {
        this.loadingView = LayoutInflater.from(getActivity()).inflate(R.layout.em_layout_loading_data, (ViewGroup) null);
        this.loadingView.setVisibility(this.isShowLoading ? 0 : 8);
        this.fly_contact_list.addView(this.loadingView);
        this.swipeRefresh = (SwipeRefreshLayout) getView(R.id.contact_swipeRefresh);
        this.swipeRefresh.setColorSchemeResources(R.color.holo_blue_light, R.color.holo_green_light, R.color.holo_orange_light, R.color.holo_red_light);
        this.swipeRefresh.setOnRefreshListener(this);
    }

    private void initContactsHead() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.em_contacts_header, (ViewGroup) null);
        HeaderItemClickListener clickListener = new HeaderItemClickListener();
        this.applicationItem = (ContactItemView) headerView.findViewById(R.id.item_application);
        this.applicationItem.setOnClickListener(clickListener);
        headerView.findViewById(R.id.item_group).setOnClickListener(clickListener);
        headerView.findViewById(R.id.item_system).setOnClickListener(clickListener);
        headerView.findViewById(R.id.item_invited).setOnClickListener(clickListener);
        headerView.findViewById(R.id.item_feedback).setOnClickListener(clickListener);
        headerView.findViewById(R.id.item_subscription).setOnClickListener(clickListener);
        this.listView.addHeaderView(headerView);
        final LinearLayout ll_parent = (LinearLayout) headerView.findViewById(R.id.ll_parent);
        this.query = (EditText) getView(R.id.query);
        this.query.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.fragment.FriendFragment.2
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FriendFragment.this.contactListLayout.filter(s);
                if (s.length() > 0) {
                    ll_parent.setVisibility(8);
                    FriendFragment.this.clearSearch.setVisibility(0);
                    return;
                }
                FriendFragment.this.clearSearch.setVisibility(8);
                ll_parent.setVisibility(0);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        this.clearSearch = (ImageButton) getView(R.id.search_clear);
        this.clearSearch.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.fragment.FriendFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FriendFragment.this.query.getText().clear();
                DeviceTool.hideSoftKeyboard(FriendFragment.this.getContext());
            }
        });
    }

    @Override // com.easeui.ui.EaseContactListFragment
    public void refresh() {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.fragment.FriendFragment.4
            @Override // java.lang.Runnable
            public void run() {
                FriendFragment.this.initDataMap();
                FriendFragment.this.loadingView.post(new Runnable() { // from class: com.vsf2f.f2f.fragment.FriendFragment.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        FriendFragment.this.parentRefresh();
                        FriendFragment.this.refreshUnRead();
                        FriendFragment.this.loadingView.setVisibility(FriendFragment.this.isShowLoading ? 0 : 8);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parentRefresh() {
        super.refresh();
    }

    public void initDataMap() {
        this.userMap.clear();
        this.userMap = DemoHelper.getInstance().getContactList();
        if (this.userMap instanceof Hashtable) {
            this.userMap = (Map) ((Hashtable) this.userMap).clone();
        }
        setContactsMap(this.userMap);
    }

    @Override // com.hy.frame.common.BaseFragment, android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        refreshUnRead();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUnRead() {
        try {
            if (this.inviteMessgeDao == null) {
                this.inviteMessgeDao = new InviteMessgeDao(getActivity());
            }
            if (this.inviteMessgeDao.getUnreadMessagesCount() > 0) {
                this.applicationItem.showUnreadMsgView();
                this.applicationItem.setUnreadCount(this.inviteMessgeDao.getUnreadMessagesCount());
                return;
            }
            this.applicationItem.hideUnreadMsgView();
        } catch (Exception e) {
            MyLog.e("子线程调用UI");
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override // com.easeui.ui.EaseContactListFragment
    protected void setUpView() {
        super.setUpView();
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.fragment.FriendFragment.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EaseUser user = (EaseUser) FriendFragment.this.listView.getItemAtPosition(position);
                if (user != null && user.getUsername() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("username", user.getUsername());
                    FriendFragment.this.startAct(UserProfileActivity.class, bundle);
                }
            }
        });
        this.contactSyncListener = new ContactSyncListener();
        DemoHelper.getInstance().addSyncContactListener(this.contactSyncListener);
        this.blackListSyncListener = new BlackListSyncListener();
        DemoHelper.getInstance().addSyncBlackListListener(this.blackListSyncListener);
        this.contactInfoSyncListener = new ContactInfoSyncListener();
        DemoHelper.getInstance().addSyncContactInfoListener(this.contactInfoSyncListener);
        if (DemoHelper.getInstance().isContactsSyncedWithServer()) {
            this.loadingView.setVisibility(8);
        } else if (DemoHelper.getInstance().isSyncingContactsWithServer()) {
            this.loadingView.setVisibility(0);
        }
    }

    @Override // com.easeui.ui.EaseContactListFragment, com.hy.frame.common.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.isInitData = false;
        if (this.contactSyncListener != null) {
            DemoHelper.getInstance().removeSyncContactListener(this.contactSyncListener);
            this.contactSyncListener = null;
        }
        if (this.blackListSyncListener != null) {
            DemoHelper.getInstance().removeSyncBlackListListener(this.blackListSyncListener);
        }
        if (this.contactInfoSyncListener != null) {
            DemoHelper.getInstance().removeSyncContactInfoListener(this.contactInfoSyncListener);
        }
    }

    @Override // com.cdlinglu.common.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        int i;
        super.onResume();
        this.isCanRefresh = true;
        View view = this.loadingView;
        if (this.isShowLoading) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        if (this.needRefresh) {
            this.needRefresh = false;
            refresh();
        } else if (this.readRefresh) {
            this.readRefresh = false;
            refreshUnRead();
        }
    }

    public void needRefresh() {
        if (isResumed()) {
            refresh();
        } else {
            this.needRefresh = true;
        }
    }

    public void readRefresh() {
        if (isResumed()) {
            refreshUnRead();
        } else {
            this.readRefresh = true;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override // com.easeui.ui.EaseContactListFragment, com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public class HeaderItemClickListener implements View.OnClickListener {
        protected HeaderItemClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_application /* 2131756637 */:
                    FriendFragment.this.startAct(NewFriendsMsgActivity.class);
                    return;
                case R.id.item_group /* 2131756638 */:
                    FriendFragment.this.startAct(GroupsActivity.class);
                    return;
                case R.id.item_subscription /* 2131756639 */:
                    MyToast.show(FriendFragment.this.context, "暂未开放");
                    return;
                case R.id.item_system /* 2131756640 */:
                    FriendFragment.this.startAct(HelpCenterActivity.class);
                    return;
                case R.id.item_feedback /* 2131756641 */:
                    Bundle bun = new Bundle();
                    bun.putSerializable("username", Constant.COM_SOURCE_USER);
                    FriendFragment.this.startAct(ChatActivity.class, bun);
                    return;
                case R.id.item_invited /* 2131756642 */:
                    FriendFragment.this.startAct(InvitedActivity.class);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
    public void onRefresh() {
        if (this.isCanRefresh) {
            if (this.isShowLoading) {
                MyToast.show(getApp(), "好友加载中。。。");
                this.swipeRefresh.setRefreshing(false);
                return;
            }
            this.isCanRefresh = false;
            setShowLoad(true);
            MainActivity.getInstance().getFriendsList();
        }
        this.swipeRefresh.setRefreshing(false);
    }

    public void setShowLoad(boolean isShow) {
        this.isShowLoading = isShow;
        if (this.loadingView != null) {
            this.loadingView.setVisibility(this.isShowLoading ? 0 : 8);
        }
    }

    @Override // android.support.v4.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        EaseUser toBeProcessUser = (EaseUser) this.listView.getItemAtPosition(((AdapterView.AdapterContextMenuInfo) menuInfo).position);
        if (toBeProcessUser != null) {
            this.toBeProcessUsername = toBeProcessUser.getUsername();
            getActivity().getMenuInflater().inflate(R.menu.em_context_contact_list, menu);
        }
    }

    @Override // android.support.v4.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_contact) {
            deleteFriend(DemoHelper.getInstance().getCurrentUserName(), this.toBeProcessUsername);
            return true;
        } else if (item.getItemId() != R.id.add_to_blacklist) {
            return super.onContextItemSelected(item);
        } else {
            moveToBlack(this.toBeProcessUsername);
            return true;
        }
    }

    private void deleteFriend(String username, String friendName) {
        getClient().setShowDialog(true);
        getClient().post(R.string.API_DELETE_FRIEND, ComUtil.getF2FApi(this.context, getString(R.string.API_DELETE_FRIEND, username, friendName)), FriendNexusBean.class);
    }

    protected void moveToBlack(String friendName) {
        getClient().post(R.string.API_BLACK_FRIEND, ComUtil.getF2FApi(this.context, getString(R.string.API_BLACK_FRIEND, DemoHelper.getInstance().getCurrentUserName(), friendName)), FriendNexusBean.class);
    }

    private void updateLocation(MyLocation myLocation) {
        AjaxParams params = new AjaxParams();
        params.put("lat", myLocation.getLatitude());
        params.put("lng", myLocation.getLongitude());
        getClient().post(R.string.API_USER_CHANGE_DATA, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_DATA)), params, Boolean.class);
    }

    @Override // com.cdlinglu.common.BaseFragment, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_BLACK_FRIEND /* 2131296297 */:
                if (((FriendNexusBean) result.getObj()).getSeccuse().size() > 0) {
                    deleteLocContact(this.toBeProcessUsername);
                    MyToast.show(this.context, (int) R.string.move_into_blacklist_success);
                    return;
                }
                MyToast.show(this.context, (int) R.string.move_into_blacklist_failure);
                return;
            case R.string.API_DELETE_FRIEND /* 2131296320 */:
                if (result.getObj() != null) {
                    if (!((FriendNexusBean) result.getObj()).getSeccuse().isEmpty()) {
                        MyLog.e("删除好友:非好友");
                    }
                    deleteLocContact(this.toBeProcessUsername);
                    new InviteMessgeDao(getActivity()).deleteMessage(this.toBeProcessUsername);
                    return;
                }
                return;
            case R.string.API_USER_CHANGE_DATA /* 2131296461 */:
                if (((Boolean) result.getObj()).booleanValue()) {
                    MyLog.e("更新位置");
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void deleteLocContact(final String tobeDeleteUsername) {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.fragment.FriendFragment.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DemoHelper.getInstance().deleteContact(tobeDeleteUsername);
                    FriendFragment.this.contactsMap.remove(tobeDeleteUsername);
                    FriendFragment.this.refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void moveToBlacklist(final String username) {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        String st1 = getResources().getString(R.string.moved_into_blacklist);
        final String st3 = getResources().getString(R.string.move_into_blacklist_failure);
        pd.setMessage(st1);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.fragment.FriendFragment.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMClient.getInstance().contactManager().addUserToBlackList(username, false);
                    FriendFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.fragment.FriendFragment.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            pd.dismiss();
                            FriendFragment.this.moveToBlack(username);
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    FriendFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.fragment.FriendFragment.7.2
                        @Override // java.lang.Runnable
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(FriendFragment.this.getActivity(), st3, 0).show();
                        }
                    });
                }
            }
        });
    }

    /* loaded from: classes2.dex */
    class ContactSyncListener implements DemoHelper.DataSyncListener {
        ContactSyncListener() {
        }

        @Override // com.em.DemoHelper.DataSyncListener
        public void onSyncComplete(final boolean success) {
            MyLog.d("on contact list sync success:" + success);
            FriendFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.fragment.FriendFragment.ContactSyncListener.1
                @Override // java.lang.Runnable
                public void run() {
                    FriendFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.fragment.FriendFragment.ContactSyncListener.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (success) {
                                FriendFragment.this.loadingView.setVisibility(8);
                                FriendFragment.this.refresh();
                                return;
                            }
                            Toast.makeText(FriendFragment.this.getActivity(), FriendFragment.this.getResources().getString(R.string.get_failed_please_check), 0).show();
                            FriendFragment.this.loadingView.setVisibility(8);
                        }
                    });
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    class BlackListSyncListener implements DemoHelper.DataSyncListener {
        BlackListSyncListener() {
        }

        @Override // com.em.DemoHelper.DataSyncListener
        public void onSyncComplete(boolean success) {
            FriendFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.fragment.FriendFragment.BlackListSyncListener.1
                @Override // java.lang.Runnable
                public void run() {
                    FriendFragment.this.refresh();
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    class ContactInfoSyncListener implements DemoHelper.DataSyncListener {
        ContactInfoSyncListener() {
        }

        @Override // com.em.DemoHelper.DataSyncListener
        public void onSyncComplete(final boolean success) {
            MyLog.d("on contactinfo list sync success:" + success);
            FriendFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.fragment.FriendFragment.ContactInfoSyncListener.1
                @Override // java.lang.Runnable
                public void run() {
                    FriendFragment.this.loadingView.setVisibility(8);
                    if (success) {
                        FriendFragment.this.refresh();
                    }
                }
            });
        }
    }
}
