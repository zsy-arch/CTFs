package com.vsf2f.f2f.ui.user;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.adapter.EaseContactAdapter;
import com.easeui.domain.EaseUser;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.FriendsListBean;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class BlacklistActivity extends BaseActivity {
    private List<EaseUser> blacklist = new ArrayList();
    private ListView listView;
    private int position;
    private EaseUser toBeNormalUser;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_black_list;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.black_list, 0);
        this.listView = (ListView) findViewById(R.id.list);
        registerForContextMenu(this.listView);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        getBlackList();
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.em_remove_from_blacklist, menu);
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.remove) {
            return super.onContextItemSelected(item);
        }
        int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
        this.toBeNormalUser = this.blacklist.get(position);
        removeOutBlack(this.toBeNormalUser.getUsername());
        this.position = position;
        return true;
    }

    private void getBlackList() {
        String userName = DemoHelper.getInstance().getCurrentUserName();
        if (!TextUtils.isEmpty(userName)) {
            getClient().get(R.string.API_FRIENDS_LIST, ComUtil.getF2FApi(this.context, getString(R.string.API_FRIENDS_LIST, new Object[]{userName})) + "?last=0&page=1&limit=200", FriendsListBean.class);
        }
    }

    protected void removeOutBlack(String friendName) {
        getClient().post(R.string.API_NOT_BLACK_FRIEND, ComUtil.getF2FApi(this.context, getString(R.string.API_NOT_BLACK_FRIEND, new Object[]{DemoHelper.getInstance().getCurrentUserName(), friendName})), Boolean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        List<FriendsListBean.RowsBean> blackRowList;
        switch (result.getRequestCode()) {
            case R.string.API_FRIENDS_LIST /* 2131296344 */:
                FriendsListBean friendsListBean = (FriendsListBean) result.getObj();
                if (!(friendsListBean == null || (blackRowList = friendsListBean.getBlack()) == null)) {
                    this.blacklist = new ArrayList();
                    for (int i = 0; i < blackRowList.size(); i++) {
                        this.blacklist.add(new EaseUser(blackRowList.get(i)));
                    }
                    updateUI();
                    return;
                }
                return;
            case R.string.API_NOT_BLACK_FRIEND /* 2131296381 */:
                addLocContact(this.toBeNormalUser);
                this.blacklist.remove(this.position);
                updateUI();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        this.listView.setAdapter((ListAdapter) new EaseContactAdapter(this, R.layout.item_row_contact, this.blacklist));
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    public void addLocContact(final EaseUser tobeDeleteUser) {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.user.BlacklistActivity.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DemoHelper.getInstance().saveContact(tobeDeleteUser);
                    MainActivity.getInstance().refreshFriendList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void removeOutBlacklist(final String tobeRemoveUsername) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage(getString(R.string.be_removing));
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.user.BlacklistActivity.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EMClient.getInstance().contactManager().removeUserFromBlackList(tobeRemoveUsername);
                    BlacklistActivity.this.runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.ui.user.BlacklistActivity.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            pd.dismiss();
                            BlacklistActivity.this.removeOutBlack(tobeRemoveUsername);
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    BlacklistActivity.this.runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.ui.user.BlacklistActivity.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(BlacklistActivity.this.getApplicationContext(), (int) R.string.Removed_from_the_failure, 0).show();
                        }
                    });
                }
            }
        });
    }
}
