package com.vsf2f.f2f.ui;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.BaseFragment;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.PermissionUtil;
import com.cdlinglu.utils.TimeUtil;
import com.easeui.EaseConstant;
import com.easeui.domain.EaseUser;
import com.easeui.utils.EaseCommonUtils;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.RedPacketConstant;
import com.em.db.DemoDBManager;
import com.em.db.InviteMessgeDao;
import com.em.ui.AddContactActivity;
import com.em.ui.ChatActivity;
import com.em.ui.GroupsActivity;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.hy.http.AjaxParams;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.tencent.tauth.Tencent;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.AppVersion;
import com.vsf2f.f2f.bean.FriendsListBean;
import com.vsf2f.f2f.bean.ShopSellerCheckBean;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.bean.UserShareBean;
import com.vsf2f.f2f.bean.VerifyBean;
import com.vsf2f.f2f.fragment.DiscoverFragment;
import com.vsf2f.f2f.fragment.FriendFragment;
import com.vsf2f.f2f.fragment.MineFragment;
import com.vsf2f.f2f.fragment.PlatformFragment;
import com.vsf2f.f2f.fragment.TalkFragment;
import com.vsf2f.f2f.jpush.JPushUtil;
import com.vsf2f.f2f.ui.dialog.BasePopupMenu;
import com.vsf2f.f2f.ui.dialog.MainReleaseDialog;
import com.vsf2f.f2f.ui.dialog.MenuMainDialog;
import com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity;
import com.vsf2f.f2f.ui.other.BaseUiListener;
import com.vsf2f.f2f.ui.qrcode.QrcodeActivity;
import com.vsf2f.f2f.ui.user.SearchActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.AmapUtils;
import com.vsf2f.f2f.ui.utils.BadgeUtil;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.upload.UpAppUtils;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class MainActivity extends BaseActivity implements NavGroup.OnCheckedChangeListener, BasePopupMenu.PopupListener, AMapLocationListener {
    private static int FRIEND_PAGE_SIZE = 300;
    public static final String KEY_EXTRAS = "extras";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TITLE = "title";
    public static final String MESSAGE_RECEIVED_ACTION = "com.vsf2f.f2f.ui.MESSAGE_RECEIVED_ACTION";
    public static final int PERMISSION_REQUEST_CODE = 11;
    private static MainActivity instance;
    private LocalBroadcastManager broadcastManager;
    private BroadcastReceiver broadcastReceiver;
    private AlertDialog.Builder conflictBuilder;
    private DiscoverFragment discoverFragment;
    private long exitTime;
    private List<BaseFragment> fragments;
    private FriendFragment friendFragment;
    private NavGroup groupFooter;
    private InviteMessgeDao inviteMessgeDao;
    private boolean isConflictDialogShow;
    private ImageView ivCirclesDot;
    private MessageReceiver mMessageReceiver;
    private MineFragment mineFragment;
    private PlatformFragment platformFragment;
    private TalkFragment talkFragment;
    private TextView unreadAddressLabel;
    private TextView unreadLabel;
    private int pager = 2;
    private boolean isRefreshFriend = false;
    private boolean isCurrentAccountRemoved = false;
    private long lastTime = 0;
    EMMessageListener messageListener = new EMMessageListener() { // from class: com.vsf2f.f2f.ui.MainActivity.5
        @Override // com.hyphenate.EMMessageListener
        public void onMessageReceived(List<EMMessage> messages) {
            for (EMMessage message : messages) {
                String currentClickUser = MyApplication.getCurrentMapClickUserId();
                String fromuser = message.getFrom();
                if (!TextUtils.isEmpty(currentClickUser) && currentClickUser.equals(fromuser) && MainActivity.this.friendFragment != null) {
                    AmapUtils.startJumpAnimation(fromuser, 0);
                }
                DemoHelper.getInstance().getNotifier().onNewMsg(message);
            }
            MainActivity.this.refreshUIWithMessage();
        }

        @Override // com.hyphenate.EMMessageListener
        public void onCmdMessageReceived(List<EMMessage> messages) {
            for (EMMessage message : messages) {
                ((EMCmdMessageBody) message.getBody()).action();
            }
            MainActivity.this.refreshUIWithMessage();
        }

        @Override // com.hyphenate.EMMessageListener
        public void onMessageRead(List<EMMessage> messages) {
        }

        @Override // com.hyphenate.EMMessageListener
        public void onMessageDelivered(List<EMMessage> message) {
        }

        @Override // com.hyphenate.EMMessageListener
        public void onMessageChanged(EMMessage message, Object change) {
        }
    };

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_main;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        hideHeader();
        instance = this;
        this.inviteMessgeDao = new InviteMessgeDao(this);
        this.unreadLabel = (TextView) getView(R.id.unread_msg_number);
        this.unreadAddressLabel = (TextView) getView(R.id.unread_address_number);
        this.ivCirclesDot = (ImageView) getView(R.id.iv_circles_dot);
        this.groupFooter = (NavGroup) getView(R.id.main_groupFooter);
        this.groupFooter.setOnCheckedChangeListener(this);
        this.groupFooter.setCanClickCurrent();
        registerBroadcastReceiver();
        EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
        registerMessageReceiver();
        initPush();
        EventBus.getDefault().register(this);
        getScreenSizeOfDevice2();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.talkFragment = new TalkFragment();
        this.friendFragment = new FriendFragment();
        this.discoverFragment = new DiscoverFragment();
        this.platformFragment = new PlatformFragment();
        this.mineFragment = new MineFragment();
        if (this.fragments == null) {
            this.fragments = new ArrayList();
            this.fragments.add(this.talkFragment);
            this.fragments.add(this.friendFragment);
            this.fragments.add(this.platformFragment);
            this.fragments.add(this.discoverFragment);
            this.fragments.add(this.mineFragment);
        }
        if (!getIntent().hasExtra(Constant.FLAG_INTENT)) {
            this.groupFooter.setCheckedChildByPosition(this.pager);
            getSupportFragmentManager().beginTransaction().add(R.id.main_flyContainer, this.fragments.get(this.pager)).commit();
        }
        initLocation();
        initPager(getIntent());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005d, code lost:
        if (r5.equals(com.vsf2f.f2f.ui.utils.Constant.ACT_LOGIN) != false) goto L_0x0043;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initPager(android.content.Intent r8) {
        /*
            r7 = this;
            r4 = 2
            r2 = 0
            java.lang.String r3 = "conflict"
            boolean r3 = r8.getBooleanExtra(r3, r2)
            if (r3 == 0) goto L_0x0011
            boolean r3 = r7.isConflictDialogShow
            if (r3 != 0) goto L_0x0011
            r7.showConflictDialog()
        L_0x0011:
            java.lang.String r3 = "flag_intent"
            boolean r3 = r8.hasExtra(r3)
            if (r3 == 0) goto L_0x002c
            java.lang.String r3 = "flag_intent"
            int r0 = r8.getIntExtra(r3, r4)
            com.hy.frame.view.NavGroup r3 = r7.groupFooter
            r3.setCheckedChildByPosition(r0)
            r7.changePager(r0)
            java.lang.String r3 = "flag_intent"
            r8.removeExtra(r3)
        L_0x002c:
            java.lang.String r3 = "flag_act"
            boolean r3 = r8.hasExtra(r3)
            if (r3 == 0) goto L_0x0056
            java.lang.String r3 = "flag_act"
            java.lang.String r5 = r8.getStringExtra(r3)
            r3 = -1
            int r6 = r5.hashCode()
            switch(r6) {
                case -95166193: goto L_0x006a;
                case -90729092: goto L_0x0057;
                case 1469857792: goto L_0x0074;
                case 1644905411: goto L_0x0060;
                default: goto L_0x0042;
            }
        L_0x0042:
            r2 = r3
        L_0x0043:
            switch(r2) {
                case 0: goto L_0x007e;
                case 1: goto L_0x007e;
                case 2: goto L_0x008b;
                case 3: goto L_0x0094;
                default: goto L_0x0046;
            }
        L_0x0046:
            com.em.utils.UserShared r2 = com.em.utils.UserShared.getInstance()
            int r1 = r2.getIsVerify()
            switch(r1) {
                case 0: goto L_0x0098;
                case 1: goto L_0x00a1;
                default: goto L_0x0051;
            }
        L_0x0051:
            java.lang.String r2 = "flag_act"
            r8.removeExtra(r2)
        L_0x0056:
            return
        L_0x0057:
            java.lang.String r4 = "act_login"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0042
            goto L_0x0043
        L_0x0060:
            java.lang.String r2 = "act_regist"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0042
            r2 = 1
            goto L_0x0043
        L_0x006a:
            java.lang.String r2 = "act_guide"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0042
            r2 = r4
            goto L_0x0043
        L_0x0074:
            java.lang.String r2 = "act_launch"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0042
            r2 = 3
            goto L_0x0043
        L_0x007e:
            r7.initPush()
            r7.refreshMine()
            r7.getShopPrev()
            r7.getFriendsList()
            goto L_0x0046
        L_0x008b:
            boolean r2 = r7.isLogin()
            if (r2 == 0) goto L_0x0094
            r7.getShopPrev()
        L_0x0094:
            r7.checkVersion()
            goto L_0x0046
        L_0x0098:
            com.vsf2f.f2f.ui.dialog.ActivateDialog r2 = new com.vsf2f.f2f.ui.dialog.ActivateDialog
            r2.<init>(r7)
            r2.show()
            goto L_0x0051
        L_0x00a1:
            android.content.Context r2 = r7.context
            com.hy.frame.util.AppShare r2 = com.hy.frame.util.AppShare.get(r2)
            java.lang.String r3 = "tree"
            boolean r2 = r2.getBoolean(r3)
            if (r2 == 0) goto L_0x0051
            com.em.utils.UserShared r2 = com.em.utils.UserShared.getInstance()
            boolean r2 = r2.isOpenManor()
            if (r2 != 0) goto L_0x0051
            com.vsf2f.f2f.ui.otay.ManorTreeDialog r2 = new com.vsf2f.f2f.ui.otay.ManorTreeDialog
            android.content.Context r3 = r7.context
            r2.<init>(r3)
            r2.show()
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vsf2f.f2f.ui.MainActivity.initPager(android.content.Intent):void");
    }

    private void initPush() {
        if (TextUtils.isEmpty(JPushInterface.getRegistrationID(getApplicationContext()))) {
            JPushInterface.init(getApplicationContext());
        }
        if (isLogin()) {
            JPushInterface.setAliasAndTags(getApplicationContext(), DemoHelper.getInstance().getCurrentUserName(), null, null);
        }
    }

    private void initLocation() {
        if (MyApplication.getMyLocation() == null && PermissionUtil.getLocationPermissions(this, 11)) {
            AmapUtils.getLocation(this.context, this);
        }
    }

    private void getScreenSizeOfDevice2() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int yPx = dm.heightPixels;
        int xPx = dm.widthPixels;
        MyApplication.setHeightCm(yPx);
        MyApplication.setWidthCm(xPx);
    }

    private void checkOpen() {
        getClient().get(R.string.API_SELLER_CHECK, ComUtil.getZCApi(this.context, getString(R.string.API_SELLER_CHECK)), ShopSellerCheckBean.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        v.getId();
    }

    public void getUserVerify() {
        getClient().get(R.string.API_USER_PERSONAL_DATA, ComUtil.getZCApi(this.context, getString(R.string.API_USER_PERSONAL_DATA, new Object[]{"verify"})), VerifyBean.class);
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup group, NavView nav, @IdRes int checkedId) {
        if (nav.getTag() != null) {
            changePager(Integer.parseInt(nav.getTag().toString()));
        }
    }

    private void changePager(int position) {
        if (position != this.pager) {
            if (position == 2 || !isNoLogin()) {
                if (position == 4 && UserShared.getInstance().getIsVerify() != 1) {
                    getUserVerify();
                }
                this.pager = position;
                switchFragment(this.fragments.get(this.pager), this.fragments.get(position));
                return;
            }
            this.groupFooter.setCheckedChildByPosition(2);
            startAct(LoginActivity.class);
        }
    }

    public void switchFragment(Fragment from, Fragment to) {
        if (from != null && to != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {
                transaction.hide(from).add(R.id.main_flyContainer, to).commitAllowingStateLoss();
                return;
            }
            transaction.hide(from).show(to).commitAllowingStateLoss();
            try {
                ((BaseFragment) to).onStartData();
            } catch (Exception e) {
                MyLog.e("Fragment not extends BaseFragment!");
            }
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (HyUtil.isNoEmpty(this.fragments)) {
            try {
                BaseFragment bf = this.fragments.get(this.pager);
                if (bf != null && bf.isAdded()) {
                    MyLog.d(bf.getClass(), "onRestart | onStartData");
                    bf.onStartData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (isNoLogin()) {
            this.groupFooter.setCheckedChildByPosition(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (HyUtil.isNoEmpty(this.fragments)) {
            try {
                BaseFragment bf = this.fragments.get(this.pager);
                if (bf != null && bf.isAdded()) {
                    bf.onStartData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!DemoHelper.getInstance().isConflict) {
            updateUnreadLabel();
            updateUnreadAddressLabel();
        }
        if (this.isRefreshFriend) {
            getFriendsList();
        }
        DemoHelper.getInstance().pushActivity(this);
        EMClient.getInstance().chatManager().addMessageListener(this.messageListener);
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        EMClient.getInstance().chatManager().removeMessageListener(this.messageListener);
        DemoHelper.getInstance().popActivity(this);
        super.onStop();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MyLog.d("onNewIntent");
        initPager(intent);
    }

    public void getUserClient(String name) {
        List<String> list = new ArrayList<>();
        list.add(name);
        getUserClient(list);
    }

    public void getUserClient(List<String> nameList) {
        getClient().post(R.string.API_GET_EXCHANGE_USERINFO, ComUtil.getF2FApi(this.context, getString(R.string.API_GET_EXCHANGE_USERINFO, new Object[]{DemoHelper.getInstance().getCurrentUserName()})), new JSONArray((Collection) nameList).toString(), new AjaxParams(), UserInfo.class, true);
    }

    public void getFriendsList() {
        MyLog.e("好友列表更新");
        if (DemoDBManager.getInstance().haveFriend()) {
            this.lastTime = DemoHelper.getInstance().getFriendLastTime();
        } else {
            this.lastTime = 0L;
        }
        this.isRefreshFriend = false;
        getFriendsList(1);
    }

    private void getFriendsList(int page_friend) {
        MyLog.e("getFriendsList-请求第" + page_friend + "页");
        this.friendFragment.setShowLoad(true);
        String userName = DemoHelper.getInstance().getCurrentUserName();
        if (!TextUtils.isEmpty(userName)) {
            getClient().get(R.string.API_FRIENDS_LIST, ComUtil.getF2FApi(this.context, getString(R.string.API_FRIENDS_LIST, new Object[]{userName})) + "?last=" + this.lastTime + "&page=" + page_friend + "&limit=" + FRIEND_PAGE_SIZE, new AjaxParams(), FriendsListBean.class, false);
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        int i = 1;
        switch (result.getRequestCode()) {
            case R.string.API_CHECK_REGISTER /* 2131296304 */:
                if (result.getObj() != null) {
                    DemoHelper.getInstance().savePhonesList((List) result.getObj());
                }
                AppShare.get(this.context).putString("up_phone_state", TimeUtil.getNowTime());
                return;
            case R.string.API_CHECK_VERSION /* 2131296305 */:
                AppVersion appVersion = (AppVersion) result.getObj();
                if (appVersion != null) {
                    new UpAppUtils(this.context, appVersion);
                    return;
                }
                return;
            case R.string.API_FRIENDS_LIST /* 2131296344 */:
                loadLocal(result);
                return;
            case R.string.API_GET_EXCHANGE_USERINFO /* 2131296350 */:
                if (result.getObj() != null) {
                    List<UserInfo> list = (List) result.getObj();
                    if (list.size() == 1) {
                        DemoHelper.getInstance().saveUser(new EaseUser(list.get(0)));
                        return;
                    }
                    return;
                }
                return;
            case R.string.API_SELLER_CHECK /* 2131296400 */:
                DemoHelper instance2 = DemoHelper.getInstance();
                if (!((ShopSellerCheckBean) result.getObj()).getCheck()) {
                    i = -1;
                }
                instance2.setOpenStore(i);
                return;
            case R.string.API_SHOP_TYPE /* 2131296440 */:
                List list2 = (List) result.getObj();
                UserShared.getInstance().savePrevList(result.getObjStr());
                return;
            case R.string.API_USER_PERSONAL_DATA /* 2131296471 */:
                VerifyBean verify = (VerifyBean) result.getObj();
                if (verify != null) {
                    int isVerify = verify.getIsVerify();
                    UserShared.getInstance().setIsVerify(isVerify);
                    if (isVerify == 0) {
                        UserShared.getInstance().getIsVerifyState(this.context);
                        if (!TextUtils.isEmpty(verify.getFailed())) {
                            Toast.makeText(this, verify.getFailed(), 1).show();
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    private synchronized void loadLocal(ResultInfo result) {
        boolean hasNext = true;
        synchronized (this) {
            if (result.getObj() != null) {
                final FriendsListBean listBean = (FriendsListBean) result.getObj();
                this.lastTime = listBean.getLastTime();
                String str = "getFriendsList-完成第" + (listBean.getNextPage() - 1) + "/" + listBean.getTotalPage() + "页";
                if (listBean.getNextPage() <= 1 || listBean.getNextPage() > listBean.getTotalPage()) {
                    hasNext = false;
                }
                if (hasNext) {
                    getFriendsList(listBean.getNextPage());
                }
                if (listBean.getNormal() != null) {
                    final List<FriendsListBean.RowsBean> rowsBeans = listBean.getNormal();
                    str = str + rowsBeans.size() + "条";
                    if (rowsBeans.size() < FRIEND_PAGE_SIZE) {
                        hasNext = false;
                    }
                    ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.MainActivity.1
                        @Override // java.lang.Runnable
                        public void run() {
                            List<EaseUser> contactInfoList = new ArrayList<>();
                            for (int i = 0; i < rowsBeans.size(); i++) {
                                contactInfoList.add(new EaseUser((FriendsListBean.RowsBean) rowsBeans.get(i)));
                            }
                            DemoHelper.getInstance().updateContactList(contactInfoList);
                        }
                    });
                } else {
                    hasNext = false;
                }
                MyLog.e(str);
                if (listBean.getDelete() != null) {
                    ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.MainActivity.2
                        @Override // java.lang.Runnable
                        public void run() {
                            DemoHelper.getInstance().deleteContact(listBean.getDelete());
                        }
                    });
                }
                if (!hasNext) {
                    MyLog.e("getFriendsList-完成共" + listBean.getTotalPage() + "页");
                    DemoHelper.getInstance().putFriendLastTime(listBean.getLastTime());
                    if (this.friendFragment != null) {
                        refreshFriendList();
                        this.friendFragment.setShowLoad(false);
                    }
                }
            } else {
                MyLog.e("getFriendsList-没有更新");
                this.isRefreshFriend = false;
                if (this.friendFragment != null) {
                    refreshFriendList();
                    this.friendFragment.setShowLoad(false);
                }
            }
        }
    }

    public void setRefreshFriend() {
        this.isRefreshFriend = true;
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_FRIENDS_LIST /* 2131296344 */:
                MyLog.e("getFriendsList-onRequestError");
                this.isRefreshFriend = false;
                if (this.friendFragment != null) {
                    this.friendFragment.setShowLoad(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void checkVersion() {
        String url = ComUtil.getZCApi(this.context, getString(R.string.API_CHECK_VERSION));
        getClient().setShowDialog(false);
        getClient().get(R.string.API_CHECK_VERSION, url, AppVersion.class);
    }

    private void registerBroadcastReceiver() {
        this.broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(EaseConstant.ACTION_CONTACT_CHANAGED);
        intentFilter.addAction(EaseConstant.ACTION_GROUP_CHANAGED);
        intentFilter.addAction(RedPacketConstant.REFRESH_GROUP_RED_PACKET_ACTION);
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.vsf2f.f2f.ui.MainActivity.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                MainActivity.this.updateUnreadLabel();
                MainActivity.this.updateUnreadAddressLabel();
                if (MainActivity.this.pager == 0) {
                    if (MainActivity.this.talkFragment != null) {
                        MainActivity.this.talkFragment.refreshMsg();
                    }
                } else if (MainActivity.this.pager == 1) {
                    MainActivity.this.refreshFriendList();
                }
                String action = intent.getAction();
                if (action.equals(EaseConstant.ACTION_GROUP_CHANAGED) && EaseCommonUtils.getTopActivity(MainActivity.this).equals(GroupsActivity.class.getName())) {
                    GroupsActivity.instance.onResume();
                }
                if (action.equals(RedPacketConstant.REFRESH_GROUP_RED_PACKET_ACTION) && MainActivity.this.talkFragment != null) {
                    MainActivity.this.talkFragment.refreshMsg();
                }
            }
        };
        this.broadcastManager.registerReceiver(this.broadcastReceiver, intentFilter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserEvent(Integer action) {
        if (action.intValue() == 1) {
            updateUnreadLabel();
        } else if (action.intValue() == 12) {
            int count = getUnreadAddressCountTotal();
            if (count > 0) {
                this.unreadAddressLabel.setText(String.valueOf(count));
                this.unreadAddressLabel.setVisibility(0);
                return;
            }
            this.unreadAddressLabel.setVisibility(4);
        }
    }

    public void updateUnreadLabel() {
        int count = getUnreadMsgCountTotal();
        if (count > 0) {
            if (count > 99) {
                this.unreadLabel.setText("99+");
            } else {
                this.unreadLabel.setText(String.valueOf(count));
            }
            this.unreadLabel.setVisibility(0);
            BadgeUtil.setBadgeCount(this.context, count);
            return;
        }
        this.unreadLabel.setVisibility(4);
    }

    public int getUnreadMsgCountTotal() {
        int chatroomUnreadMsgCount = 0;
        int unreadMsgCountTotal = EMClient.getInstance().chatManager().getUnreadMsgsCount();
        for (EMConversation conversation : EMClient.getInstance().chatManager().getAllConversations().values()) {
            if (conversation.getType() == EMConversation.EMConversationType.ChatRoom) {
                chatroomUnreadMsgCount += conversation.getUnreadMsgCount();
            }
        }
        return unreadMsgCountTotal - chatroomUnreadMsgCount;
    }

    public void updateUnreadAddressLabel() {
        runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.ui.MainActivity.4
            @Override // java.lang.Runnable
            public void run() {
                int count = MainActivity.this.getUnreadAddressCountTotal();
                if (count > 0) {
                    MainActivity.this.unreadAddressLabel.setText(String.valueOf(count));
                    MainActivity.this.unreadAddressLabel.setVisibility(0);
                    MainActivity.this.refreshFriendUnread();
                    return;
                }
                MainActivity.this.unreadAddressLabel.setVisibility(4);
            }
        });
    }

    public int getUnreadAddressCountTotal() {
        return this.inviteMessgeDao.getUnreadMessagesCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUIWithMessage() {
        runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.ui.MainActivity.6
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.updateUnreadLabel();
                if (MainActivity.this.pager == 0 && MainActivity.this.talkFragment != null) {
                    MainActivity.this.talkFragment.refreshMsg();
                }
            }
        });
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu.PopupListener
    public void onClickPopup(View v) {
        switch (v.getId()) {
            case R.id.txtAddGroup /* 2131756277 */:
                if (isNoLogin()) {
                    MyToast.show(this.context, (int) R.string.login_hint);
                    startAct(LoginActivity.class);
                    return;
                }
                startAct(GroupsActivity.class);
                return;
            case R.id.txtAddCircle /* 2131756278 */:
                if (isNoLogin()) {
                    MyToast.show(this.context, (int) R.string.login_hint);
                    startAct(LoginActivity.class);
                    return;
                }
                new MainReleaseDialog(this.context).show();
                return;
            case R.id.txtAddfriend /* 2131756279 */:
                if (isNoLogin()) {
                    MyToast.show(this.context, (int) R.string.login_hint);
                    startAct(LoginActivity.class);
                    return;
                }
                startAct(AddContactActivity.class);
                return;
            case R.id.txtScanner /* 2131756280 */:
                startAct(QrcodeActivity.class);
                return;
            case R.id.txtHelpFeed /* 2131756281 */:
                if (isNoLogin()) {
                    MyToast.show(this.context, (int) R.string.login_hint);
                    startAct(LoginActivity.class);
                    return;
                }
                Bundle bun = new Bundle();
                bun.putString(com.hy.frame.util.Constant.FLAG_TITLE, getString(R.string.settings_help));
                bun.putString(com.hy.frame.util.Constant.FLAG, getString(R.string.API_HOST) + getString(R.string.HELP_URL));
                startAct(WebKitLocalActivity.class, bun);
                return;
            default:
                return;
        }
    }

    @Override // com.amap.api.location.AMapLocationListener
    public void onLocationChanged(AMapLocation aMapLocation) {
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 11 && grantResults.length > 0 && grantResults[0] == 0) {
            initLocation();
        }
    }

    /* loaded from: classes2.dex */
    public class MyContactListener implements EMContactListener {
        public MyContactListener() {
        }

        @Override // com.hyphenate.EMContactListener
        public void onContactAdded(String username) {
            MainActivity.this.getUserClient(username);
        }

        @Override // com.hyphenate.EMContactListener
        public void onContactDeleted(final String username) {
            DemoHelper.getInstance().deleteContact(username);
            MainActivity.this.setRefreshFriend();
            MainActivity.this.runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.ui.MainActivity.MyContactListener.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ChatActivity.activityInstance != null && ChatActivity.activityInstance.getToChatUsername() != null && username.equals(ChatActivity.activityInstance.getToChatUsername())) {
                        MyToast.show(MainActivity.this, ChatActivity.activityInstance.getToChatUsername() + MainActivity.this.getResources().getString(R.string.have_you_removed));
                        ChatActivity.activityInstance.finish();
                    }
                }
            });
        }

        @Override // com.hyphenate.EMContactListener
        public void onContactInvited(String username, String reason) {
        }

        @Override // com.hyphenate.EMContactListener
        public void onFriendRequestAccepted(String username) {
        }

        @Override // com.hyphenate.EMContactListener
        public void onFriendRequestDeclined(String username) {
        }
    }

    private void unregisterBroadcastReceiver() {
        this.broadcastManager.unregisterReceiver(this.broadcastReceiver);
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || event.getAction() != 0) {
            return super.onKeyDown(keyCode, event);
        }
        if (System.currentTimeMillis() - this.exitTime > 2000) {
            this.exitTime = System.currentTimeMillis();
            MyToast.show(this.context, (int) R.string.back_desktop);
        } else {
            MyToast.cancelToast();
            getApp().toDesktop();
        }
        return true;
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (this.conflictBuilder != null) {
            this.conflictBuilder.create().dismiss();
            this.conflictBuilder = null;
        }
        unregister();
        MyLog.e("mainactivity:onDestroy");
        super.onDestroy();
    }

    private void unregister() {
        try {
            unregisterBroadcastReceiver();
            unregisterReceiver(this.mMessageReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showConflictDialog() {
        this.isConflictDialogShow = true;
        DemoHelper.getInstance().logout(null);
        if (!isFinishing()) {
            EaseAlertDialog dialog = new EaseAlertDialog((Context) this, (int) R.string.app_name, (int) R.string.connect_conflict, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.MainActivity.7
                @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                public void onResult(boolean confirmed, Bundle bundle) {
                    if (confirmed) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(268468224);
                        MainActivity.this.startActivity(intent);
                        MainActivity.this.finish();
                    }
                }
            }, false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    public void hideCircleDot() {
        this.ivCirclesDot.setVisibility(8);
        new Handler(this.context.getMainLooper()).post(new Runnable() { // from class: com.vsf2f.f2f.ui.MainActivity.8
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10103 || requestCode == 10104) {
            Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener(this));
        }
    }

    public void refreshMine() {
        if (this.mineFragment != null) {
            this.mineFragment.setRefreshInfo();
        }
    }

    public void refreshFriendList() {
        if (this.friendFragment != null) {
            this.friendFragment.needRefresh();
        }
    }

    public void refreshFriendUnread() {
        if (this.friendFragment != null) {
            this.friendFragment.readRefresh();
        }
    }

    private void showMenu(View v) {
        new MenuMainDialog(this.context, this).showAsDropDown(v);
    }

    public void registerMessageReceiver() {
        this.mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(1000);
        filter.addAction("com.vsf2f.f2f.ui.MESSAGE_RECEIVED_ACTION");
        registerReceiver(this.mMessageReceiver, filter);
    }

    /* loaded from: classes2.dex */
    public class MessageReceiver extends BroadcastReceiver {
        public MessageReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.vsf2f.f2f.ui.MESSAGE_RECEIVED_ACTION".equals(intent.getAction())) {
                String messge = intent.getStringExtra("message");
                String extras = intent.getStringExtra("extras");
                StringBuilder showMsg = new StringBuilder();
                showMsg.append("message : " + messge + "\n");
                if (!JPushUtil.isEmpty(extras)) {
                    showMsg.append("extras : " + extras + "\n");
                }
                MainActivity.this.setCostomMsg(showMsg.toString());
            }
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isConflict", DemoHelper.getInstance().isConflict);
        outState.putBoolean(EaseConstant.ACCOUNT_REMOVED, this.isCurrentAccountRemoved);
        super.onSaveInstanceState(outState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCostomMsg(String msg) {
        MyToast.show(this.context, "CostomMsg:" + msg);
    }

    public static synchronized MainActivity getInstance() {
        MainActivity mainActivity;
        synchronized (MainActivity.class) {
            if (instance == null) {
                instance = new MainActivity();
            }
            mainActivity = instance;
        }
        return mainActivity;
    }

    private void toShop(UserShareBean shareBean) {
        String url = null;
        if (!(shareBean == null || shareBean.getShareType() == null)) {
            String urlType = shareBean.getShareType();
            if (urlType.equals("shop")) {
                url = shareBean.getShareHref();
            } else if (urlType.equals("share")) {
                url = shareBean.getShareHref();
            }
        }
        if (url != null) {
            Bundle bundle = new Bundle();
            bundle.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(this.context, url));
            bundle.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
            startAct(WebKitLocalActivity.class, bundle);
        }
    }

    private void getShopPrev() {
        getClient().get(R.string.API_SHOP_TYPE, ComUtil.getZCApi(this.context, getString(R.string.API_SHOP_TYPE) + "?level=3"), null, UserShareBean.class, true);
    }

    public void publish(View view) {
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
            return;
        }
        startAct(DemandPublishActivity.class);
    }

    public void guide(View v) {
    }

    public void top_add(View v) {
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
        }
        startAct(AddContactActivity.class);
    }

    public void top_home(View v) {
        if (isLogin()) {
            toShop(UserShared.getInstance().readShare());
        } else {
            startAct(LoginActivity.class);
        }
    }

    public void top_more(View v) {
        showMenu(v);
    }

    public void top_search(View v) {
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
            return;
        }
        startAct(SearchActivity.class);
    }
}
