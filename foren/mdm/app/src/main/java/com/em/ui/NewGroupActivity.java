package com.em.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.amap.api.services.core.PoiItem;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseSwitchButton;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GroupBean;
import com.vsf2f.f2f.bean.GroupInfoBean;
import com.vsf2f.f2f.ui.dialog.PictureDialog;
import com.vsf2f.f2f.ui.needs.demand.ChoiceLocationActivity;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class NewGroupActivity extends BaseActivity {
    private PictureDialog PictureDlg;
    private final int REQUEST_CODE_ADD_USER = 0;
    private EditText ed_content;
    private EditText ed_name;
    private ImageView name_clear;
    private EaseSwitchButton switch1;
    private EaseSwitchButton switch2;
    private TextView tv_address;
    private TextView tv_public;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.em_activity_new_group;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.The_new_group_chat, 0);
        setOnClickListener(R.id.create_group);
        setOnClickListener(R.id.lly_group_address);
        this.ed_name = (EditText) getView(R.id.edit_group_name);
        this.ed_content = (EditText) getView(R.id.edit_group_content);
        this.tv_public = (TextView) getView(R.id.tv_group_public);
        this.tv_address = (TextView) getView(R.id.tv_group_address);
        this.name_clear = (ImageView) getViewAndClick(R.id.edit_group_name_clear);
        this.switch1 = (EaseSwitchButton) getViewAndClick(R.id.cb_public);
        this.switch2 = (EaseSwitchButton) getViewAndClick(R.id.cb_member_inviter);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.name_clear.setVisibility(4);
        this.ed_name.addTextChangedListener(new TextWatcher() { // from class: com.em.ui.NewGroupActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NewGroupActivity.this.name_clear.setVisibility(charSequence.length() > 0 ? 0 : 4);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
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
        switch (v.getId()) {
            case R.id.edit_group_name_clear /* 2131756564 */:
                this.ed_name.setText("");
                return;
            case R.id.lly_group_address /* 2131756565 */:
                startActForResult(ChoiceLocationActivity.class, 111);
                return;
            case R.id.tv_group_address /* 2131756566 */:
            case R.id.edit_group_content /* 2131756567 */:
            case R.id.tv_group_public /* 2131756569 */:
            case R.id.ll_open_invite /* 2131756570 */:
            default:
                return;
            case R.id.cb_public /* 2131756568 */:
                this.switch1.change();
                this.switch1.post(new Runnable() { // from class: com.em.ui.NewGroupActivity.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (NewGroupActivity.this.switch1.isSwitchOpen()) {
                            NewGroupActivity.this.tv_public.setText(R.string.group_public);
                        } else {
                            NewGroupActivity.this.tv_public.setText(R.string.group_private);
                        }
                    }
                });
                return;
            case R.id.cb_member_inviter /* 2131756571 */:
                this.switch2.change();
                return;
            case R.id.create_group /* 2131756572 */:
                if (TextUtils.isEmpty(this.ed_name.getText().toString())) {
                    MyToast.show(this, (int) R.string.group_name_cant_empty);
                    return;
                }
                getClient().showDialogNow();
                getGroup();
                return;
        }
    }

    public void getGroup() {
        String name = this.ed_name.getText().toString();
        String content = this.ed_content.getText().toString();
        if (content.isEmpty()) {
            content = "";
        }
        int isOpen = 0;
        int invite = 0;
        int approval = 0;
        if (this.switch1.isSwitchOpen()) {
            isOpen = 1;
        }
        if (isOpen == 0 && this.switch2.isSwitchOpen()) {
            invite = 1;
        } else if (isOpen == 1 && this.switch2.isSwitchOpen()) {
            approval = 1;
        }
        addGroup(name, content, isOpen, invite, approval);
    }

    public void addGroup(String groupName, String content, int isOpen, int invite, int approval) {
        try {
            AjaxParams params = new AjaxParams();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("groupName", groupName);
            jsonObject.put("groupType", "1");
            jsonObject.put(EditActivity.CONTENT, content);
            JSONObject object = new JSONObject();
            if (getUserInfo().getLv() == 0) {
                object.put("limit", "200");
            } else {
                object.put("limit", "2000");
            }
            object.put("isOpen", isOpen);
            object.put("invita", invite);
            object.put("approval", approval);
            jsonObject.put("config", object);
            getClient().post(R.string.API_ADD_GROUP, ComUtil.getF2FApi(this.context, getString(R.string.API_ADD_GROUP)), jsonObject.toString(), params, GroupInfoBean.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        int requestCode = result.getRequestCode();
        int errorCode = result.getErrorCode();
        switch (requestCode) {
            case R.string.API_ADD_GROUP /* 2131296285 */:
                if (errorCode == 0) {
                    MyToast.show(this, "创建群组成功");
                    GroupInfoBean groupInfo = (GroupInfoBean) result.getObj();
                    DemoHelper.getInstance().saveGroup(new GroupBean(groupInfo));
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.FLAG_TYPE, f.bf);
                    bundle.putString(Constant.FLAG, groupInfo.getBizId() + "");
                    bundle.putString(Constant.FLAG2, groupInfo.getImGroupid());
                    startActForResult(GroupPickContactsActivity.class, bundle, 0);
                    finish();
                    return;
                }
                MyToast.show(this, "创建群组失败");
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        PoiItem poiItem;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 111:
                    if (data.getBundleExtra("bund") != null && (poiItem = (PoiItem) data.getBundleExtra("bund").getParcelable("key")) != null) {
                        this.tv_address.setText((poiItem.getCityName() + poiItem.getAdName() + poiItem.getBusinessArea()) + poiItem.getSnippet());
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        InputMethodManager imm;
        if (ev.getAction() == 0) {
            View v = getCurrentFocus();
            if (!(!isShouldHideInput(v, ev) || (imm = (InputMethodManager) getSystemService("input_method")) == null || v == null)) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            return super.dispatchTouchEvent(ev);
        } else if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        } else {
            return onTouchEvent(ev);
        }
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v == null || !(v instanceof EditText)) {
            return false;
        }
        int[] leftTop = {0, 0};
        v.getLocationInWindow(leftTop);
        int top = leftTop[1];
        int left = leftTop[0];
        return event.getX() <= ((float) left) || event.getX() >= ((float) (left + v.getWidth())) || event.getY() <= ((float) top) || event.getY() >= ((float) (top + v.getHeight()));
    }
}
