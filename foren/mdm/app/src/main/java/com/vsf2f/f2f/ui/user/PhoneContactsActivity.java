package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.alipay.sdk.util.h;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.PermissionUtil;
import com.cdlinglu.utils.TimeUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.ui.UserProfileActivity;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.PhoneContactsAdapter;
import com.vsf2f.f2f.bean.PhoneBean;
import com.vsf2f.f2f.ui.utils.phone.PhoneUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: classes2.dex */
public class PhoneContactsActivity extends BaseActivity implements PhoneContactsAdapter.OnPhoneListener {
    private PhoneContactsAdapter adapter;
    private Map<String, PhoneBean> dbMap;
    private EditText et_search;
    private boolean isFirstReq;
    private boolean isRequest;
    private ImageView iv_search_clear;
    private String lastTime;
    private ListView listView;
    private LinearLayout lly_edit;
    private LinearLayout lly_select;
    private TextView phone_ensure;
    private int size;
    private int page = 1;
    private int pointsDataLimit = 100;
    private Map<String, String> selectMap = new HashMap();
    private List<PhoneBean> mobileList = new ArrayList();
    private List<PhoneBean> searchList = new ArrayList();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_phone_contacts;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.phone_contacts, 0);
        this.listView = (ListView) getView(R.id.phone_listView);
        this.et_search = (EditText) getView(R.id.et_search);
        this.lly_select = (LinearLayout) getView(R.id.phone_llySelect);
        this.lly_edit = (LinearLayout) getViewAndClick(R.id.phone_llyEdit);
        this.phone_ensure = (TextView) getViewAndClick(R.id.phone_ensure);
        this.iv_search_clear = (ImageView) getViewAndClick(R.id.iv_search_clear);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.lastTime = AppShare.get(this.context).getString("up_phone_state");
        if (TextUtils.isEmpty(this.lastTime)) {
            showFirstDialog();
        }
        initListView();
        setHeaderRightTxt(R.string.update);
        this.et_search.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.PhoneContactsActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    PhoneContactsActivity.this.adapter.setMobileMap(PhoneContactsActivity.this.mobileList);
                    PhoneContactsActivity.this.iv_search_clear.setVisibility(4);
                    return;
                }
                PhoneContactsActivity.this.iv_search_clear.setVisibility(0);
                PhoneContactsActivity.this.searchList.clear();
                if (HyUtil.isChinese(editable.toString())) {
                    for (int p = 0; p < PhoneContactsActivity.this.mobileList.size(); p++) {
                        if (((PhoneBean) PhoneContactsActivity.this.mobileList.get(p)).getName().contains(editable)) {
                            PhoneContactsActivity.this.searchList.add(PhoneContactsActivity.this.mobileList.get(p));
                        }
                    }
                } else {
                    PhoneContactsActivity.this.searchList.clear();
                    for (int p2 = 0; p2 < PhoneContactsActivity.this.mobileList.size(); p2++) {
                        if (((PhoneBean) PhoneContactsActivity.this.mobileList.get(p2)).getPhone().contains(editable) || ((PhoneBean) PhoneContactsActivity.this.mobileList.get(p2)).getName().contains(editable)) {
                            PhoneContactsActivity.this.searchList.add(PhoneContactsActivity.this.mobileList.get(p2));
                        }
                    }
                }
                PhoneContactsActivity.this.adapter.setMobileMap(PhoneContactsActivity.this.searchList);
            }
        });
    }

    private void initListView() {
        this.mobileList = PhoneUtil.getPhoneNumberFromMobile(this);
        pageSize();
        this.dbMap = DemoHelper.getInstance().getPhonesList();
        this.adapter = new PhoneContactsAdapter(this, this.mobileList, this.dbMap);
        this.adapter.setListener(this);
        this.listView.setAdapter((ListAdapter) this.adapter);
    }

    public void setRequest(boolean request) {
        this.isRequest = request;
        if (!request) {
            setHeaderRightTxt(R.string.update_list);
        }
    }

    private void pageSize() {
        int sum = this.mobileList.size();
        this.size = (sum / this.pointsDataLimit) + 1;
        MyLog.e("共有：" + sum + "条！ 分为 ：" + this.size + "批");
    }

    public void pageRequest(int pager) {
        if (this.size > 1) {
            reCheckMobile(this.mobileList.subList(this.pointsDataLimit * (pager - 1), pager == this.size ? this.mobileList.size() - 1 : this.pointsDataLimit * pager));
        } else {
            reCheckMobile(this.mobileList);
        }
    }

    private void reCheckMobile(List<PhoneBean> mobileList) {
        if (!HyUtil.isEmpty(mobileList)) {
            setRequest(true);
            String userName = DemoHelper.getInstance().getCurrentUserName();
            List<String> phoneList = new ArrayList<>();
            for (PhoneBean phoneBean : mobileList) {
                phoneList.add(phoneBean.getPhone());
            }
            String listStr = new JSONArray((Collection) phoneList).toString();
            MyLog.e("checkRegisterList-->" + listStr);
            getClient().post(R.string.API_CHECK_REGISTER, ComUtil.getF2FApi(this.context, getString(R.string.API_CHECK_REGISTER, new Object[]{userName})), listStr, new AjaxParams(), PhoneBean.class, true);
            setHeaderRightTxt(((this.page * 100) / this.size) + "%");
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        int requestCode = result.getRequestCode();
        int errorCode = result.getErrorCode();
        switch (requestCode) {
            case R.string.API_CHECK_REGISTER /* 2131296304 */:
                if (errorCode == 0 && result.getObj() != null) {
                    List<PhoneBean> list = (List) result.getObj();
                    if (this.page == 1) {
                        this.dbMap = new HashMap();
                    }
                    if (HyUtil.isNoEmpty(list)) {
                        DemoHelper.getInstance().savePhonesList(list);
                        for (PhoneBean phoneBean : list) {
                            this.dbMap.put(phoneBean.getPhone(), phoneBean);
                        }
                        if (this.page == this.size) {
                            setRequest(false);
                            this.adapter.setDbMap(this.dbMap);
                            MyToast.show(this.context, (int) R.string.update_success);
                            this.lastTime = TimeUtil.getNowTime();
                            AppShare.get(this.context).putString("up_phone_state", TimeUtil.getNowTime());
                            if (this.isFirstReq) {
                                this.isFirstReq = false;
                            }
                            this.et_search.setText("");
                            return;
                        }
                        this.page++;
                        pageRequest(this.page);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        setRequest(false);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_clear /* 2131756104 */:
                this.et_search.setText("");
                hideSoftKeyboard();
                return;
            case R.id.phone_llyEdit /* 2131756105 */:
                if (this.isFirstReq) {
                    MyToast.show(this.context, (int) R.string.please_wait);
                    return;
                } else if (TextUtils.isEmpty(this.lastTime)) {
                    showFirstDialog();
                    return;
                } else {
                    toSelectMode();
                    return;
                }
            case R.id.phone_listView /* 2131756106 */:
            case R.id.phone_llySelect /* 2131756107 */:
            default:
                return;
            case R.id.phone_ensure /* 2131756108 */:
                if (PermissionUtil.getSendSMSPermissions(this, 111)) {
                    sendMessage();
                    return;
                }
                return;
        }
    }

    private void showFirstDialog() {
        new EaseAlertDialog(this, R.string.prompt_phone_first, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.user.PhoneContactsActivity.2
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed) {
                    PhoneContactsActivity.this.isFirstReq = true;
                    PhoneContactsActivity.this.reUpLoad();
                }
            }
        }, true).show();
    }

    private void toSelectMode() {
        this.lly_edit.setVisibility(8);
        this.lly_select.setVisibility(0);
        this.adapter.setSelect(true);
    }

    @Override // com.vsf2f.f2f.adapter.PhoneContactsAdapter.OnPhoneListener
    public void onItemClick(PhoneBean phoneBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("username", phoneBean.getPhone());
        startAct(UserProfileActivity.class, bundle);
    }

    @Override // com.vsf2f.f2f.adapter.PhoneContactsAdapter.OnPhoneListener
    public void onSelect(PhoneBean phoneBean) {
        String phone = phoneBean.getPhone();
        this.selectMap.put(phone, "");
        if (this.dbMap.containsKey(phone)) {
            this.dbMap.get(phone).setCheck(true);
        } else {
            phoneBean.setCheck(true);
            this.dbMap.put(phone, phoneBean);
        }
        refreshSelectNum();
    }

    @Override // com.vsf2f.f2f.adapter.PhoneContactsAdapter.OnPhoneListener
    public void unSelect(PhoneBean phoneBean) {
        String phone = phoneBean.getPhone();
        this.selectMap.remove(phone);
        if (this.dbMap.containsKey(phone)) {
            this.dbMap.get(phone).setCheck(false);
        } else {
            phoneBean.setCheck(false);
            this.dbMap.put(phone, phoneBean);
        }
        refreshSelectNum();
    }

    private void refreshSelectNum() {
        this.phone_ensure.setText(String.format("%s(%s/50)", getString(R.string.ensure), this.selectMap.size() + ""));
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        reUpLoad();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reUpLoad() {
        if (HyUtil.isEmpty(this.mobileList)) {
            MyToast.show(this.context, "未读取到有效联系人!");
        } else if (!this.isRequest) {
            this.page = 1;
            pageRequest(this.page);
        } else {
            MyToast.show(this.context, (int) R.string.please_wait);
        }
    }

    public void sendMessage() {
        StringBuilder numbers = new StringBuilder();
        for (String key : this.selectMap.keySet()) {
            if (numbers.length() > 0) {
                numbers.append(h.b);
            }
            numbers.append(key);
        }
        String guId = getUserInfo().getGuid();
        if (guId != null) {
            Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + ((Object) numbers)));
            intent.putExtra("sms_body", getString(R.string.invite_ems, new Object[]{guId}));
            startActivity(intent);
        }
    }
}
