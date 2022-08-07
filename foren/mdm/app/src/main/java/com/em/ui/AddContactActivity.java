package com.em.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.EMClient;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.AddContactAdapter;
import com.vsf2f.f2f.bean.MultipleFindBean;
import com.vsf2f.f2f.ui.user.SendVerifyActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AddContactActivity extends BaseActivity implements IAdapterListener {
    private AddContactAdapter adapter;
    private List<MultipleFindBean.RowsBean> data;
    private EditText editText;
    private ListView listView;
    private int position;
    private String toAddFriendName;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_add_contact;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.add_friend, R.string.button_search);
        this.listView = (ListView) getView(R.id.listview_addcontact);
        this.editText = (EditText) getView(R.id.edit_note);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.data = new ArrayList();
        this.adapter = new AddContactAdapter(this, this.data, this);
        this.adapter.setListener(this);
        this.listView.setAdapter((ListAdapter) this.adapter);
        if (getBundle() != null) {
            String name = getBundle().getString("username");
            if (!TextUtils.isEmpty(name)) {
                this.editText.setText(name);
                multipleFind(name, 1);
            }
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        searchContact();
    }

    public void searchContact() {
        String name = this.editText.getText().toString();
        if (TextUtils.isEmpty(name)) {
            new EaseAlertDialog(this, (int) R.string.please_enter_a_username).show();
        } else {
            multipleFind(name, 1);
        }
    }

    private void multipleFind(String name, int page) {
        getClient().setShowDialog(R.string.searching);
        getClient().get(R.string.API_MULTIPLE_FIND, ComUtil.getF2FApi(this.context, getString(R.string.API_MULTIPLE_FIND) + "?name=" + ComUtil.UTF(name) + "&page=" + page), MultipleFindBean.class);
    }

    public void checkAddFriend(String friendName) {
        String username = DemoHelper.getInstance().getCurrentUserName();
        getClient().get(R.string.API_CHECK_FRIEND, ComUtil.getF2FApi(this.context, getString(R.string.API_CHECK_FRIEND, new Object[]{username, friendName}) + "?checkRole=1"), new AjaxParams(), String.class, false);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_CHECK_FRIEND /* 2131296302 */:
                if (result.getObj() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("username", this.toAddFriendName);
                    startActForResult(SendVerifyActivity.class, bundle, 234);
                    return;
                }
                return;
            case R.string.API_MULTIPLE_FIND /* 2131296372 */:
                if (result.getObj() != null) {
                    MultipleFindBean bean = (MultipleFindBean) result.getObj();
                    if (bean.getResults() == 0) {
                        MyToast.show(this.context, (int) R.string.not_find_friend);
                        return;
                    } else if (bean.getRows().size() != 0) {
                        this.data = bean.getRows();
                        this.adapter.setData(this.data);
                        return;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    public void addContact(String userName) {
        if (DemoHelper.getInstance().getCurrentUserName().equals(userName)) {
            new EaseAlertDialog(this, (int) R.string.not_add_myself).show();
        } else if (!DemoHelper.getInstance().getContactList().containsKey(userName)) {
            this.toAddFriendName = userName;
            checkAddFriend(userName);
        } else if (EMClient.getInstance().contactManager().getBlackListUsernames().contains(userName)) {
            new EaseAlertDialog(this, (int) R.string.user_already_in_blacklist).show();
        } else {
            new EaseAlertDialog(this, (int) R.string.This_user_is_already_your_friend).show();
        }
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

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int id, Object obj, int position) {
        MultipleFindBean.RowsBean item = (MultipleFindBean.RowsBean) obj;
        switch (id) {
            case R.id.item_add_rlyUser /* 2131756706 */:
                Bundle bundle = new Bundle();
                bundle.putString("username", item.getUserName());
                startAct(UserProfileActivity.class, bundle);
                return;
            case R.id.item_add_imgAvatar /* 2131756707 */:
            case R.id.item_add_txtName /* 2131756708 */:
            default:
                return;
            case R.id.item_add_btnAdd /* 2131756709 */:
                this.position = position;
                addContact(item.getUserName());
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 234 && resultCode == -1) {
            try {
                this.data.get(this.position).setCheck(true);
                this.adapter.setData(this.data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
