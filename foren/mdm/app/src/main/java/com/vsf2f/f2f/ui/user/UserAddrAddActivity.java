package com.vsf2f.f2f.ui.user;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseSwitchButton;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.MessageEncoder;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserAddrBean;
import com.vsf2f.f2f.ui.utils.area.AddressInitTask;

/* loaded from: classes2.dex */
public class UserAddrAddActivity extends BaseActivity {
    private String city;
    private String county;
    private String countyId;
    private EditText editDetail;
    private EditText editName;
    private EditText editPhone;
    private boolean isChange = false;
    private LinearLayout llyDel;
    private String province;
    private EaseSwitchButton switch1;
    private TextView txtCity;
    private UserAddrBean userAddr;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_user_address_add;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        this.isChange = getBundle().getBoolean(Constant.FLAG);
        if (this.isChange) {
            initHeaderBackTxt(R.string.address_change, R.string.save);
            this.userAddr = (UserAddrBean) getBundle().getParcelable(Constant.FLAG2);
        } else {
            initHeaderBackTxt(R.string.address_add, R.string.save);
        }
        this.txtCity = (TextView) getView(R.id.user_addr_txtCity);
        this.editName = (EditText) getView(R.id.user_addr_editName);
        this.editPhone = (EditText) getView(R.id.user_addr_editPhone);
        this.editDetail = (EditText) getView(R.id.user_addr_editDetail);
        this.switch1 = (EaseSwitchButton) getViewAndClick(R.id.user_addr_switch1);
        this.llyDel = (LinearLayout) getViewAndClick(R.id.user_addr_llyDel);
        setOnClickListener(R.id.user_addr_llyCity);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (!this.isChange) {
            this.llyDel.setVisibility(8);
        } else if (this.userAddr != null) {
            if (this.userAddr.getIsDefault() == 1) {
                this.switch1.openSwitch();
            } else {
                this.switch1.closeSwitch();
            }
            this.editName.setText(this.userAddr.getName());
            this.editPhone.setText(this.userAddr.getPhone());
            this.editDetail.setText(this.userAddr.getAddr());
            String areaName = this.userAddr.getAreaName();
            this.txtCity.setText(areaName);
            this.province = charStr(areaName, 0);
            this.city = getString(R.string.empty);
            this.county = charStr(areaName, areaName.length() - 2);
        } else {
            MyToast.show(this.context, (int) R.string.get_data_failed);
        }
    }

    private String charStr(String str, int index) {
        return String.valueOf(str.charAt(index));
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    private void addAddress() {
        AjaxParams params = new AjaxParams();
        String name = this.editName.getText().toString().trim();
        if (HyUtil.isEmpty(name)) {
            MyToast.show(this, getString(R.string.input_name));
            return;
        }
        String phone = this.editPhone.getText().toString().trim();
        if (HyUtil.isEmpty(phone)) {
            MyToast.show(this, getString(R.string.toast_enter_phone_number));
            return;
        }
        String areas = this.txtCity.getText().toString().trim();
        if (HyUtil.isEmpty(areas)) {
            MyToast.show(this, getString(R.string.selected_area));
            return;
        }
        String detail = this.editDetail.getText().toString().trim();
        if (HyUtil.isEmpty(detail)) {
            MyToast.show(this, getString(R.string.shop_detail_area));
        } else if (detail.length() < 5 || detail.length() > 60) {
            MyToast.show(this, "收货人地址最少要5个字，最多不能超过60个字");
        } else {
            getClient().showDialogNow();
            params.put("phone", phone);
            params.put("areaId", this.countyId);
            params.put("isDefault", this.switch1.isSwitchOpen() ? 1 : 0);
            params.put("name", ComUtil.UTF(name));
            params.put(MessageEncoder.ATTR_ADDRESS, ComUtil.UTF(detail));
            params.put("areaName", ComUtil.UTF(areas));
            if (this.isChange) {
                params.put("id", this.userAddr.getId());
                getClient().post(R.string.API_CHANGE_ADDR, ComUtil.getZCApi(this.context, getString(R.string.API_CHANGE_ADDR)), params);
                return;
            }
            getClient().post(R.string.API_ADD_ADDR, ComUtil.getZCApi(this.context, getString(R.string.API_ADD_ADDR)), params);
        }
    }

    private void delAddress() {
        getClient().post(R.string.API_DELETE_ADDR, ComUtil.getZCApi(this.context, getString(R.string.API_DELETE_ADDR, new Object[]{this.userAddr.getId()})));
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.API_ADD_ADDR /* 2131296281 */:
                MyToast.show(this.context, getString(R.string.add_success));
                setResult(-1);
                finish();
                return;
            case R.string.API_CHANGE_ADDR /* 2131296298 */:
                MyToast.show(this.context, getString(R.string.toast_change_success));
                setResult(-1);
                finish();
                return;
            case R.string.API_DELETE_ADDR /* 2131296319 */:
                MyToast.show(this, (int) R.string.delete_success);
                setResult(-1);
                finish();
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.user_addr_llyCity /* 2131755713 */:
                onAddressPicker();
                return;
            case R.id.user_addr_txtCity /* 2131755714 */:
            case R.id.img_jump /* 2131755715 */:
            case R.id.user_addr_editDetail /* 2131755716 */:
            default:
                return;
            case R.id.user_addr_switch1 /* 2131755717 */:
                this.switch1.change();
                return;
            case R.id.user_addr_llyDel /* 2131755718 */:
                delAddress();
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        addAddress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAreas(String province, String city, String county) {
        this.province = province;
        this.city = city;
        this.county = county;
    }

    public void onAddressPicker() {
        new AddressInitTask(this, new AddressInitTask.ICallBack() { // from class: com.vsf2f.f2f.ui.user.UserAddrAddActivity.1
            @Override // com.vsf2f.f2f.ui.utils.area.AddressInitTask.ICallBack
            public void setStr(String id, String[] str) {
                UserAddrAddActivity.this.countyId = id;
                UserAddrAddActivity.this.setAreas(str[0], str[1], str[2]);
                if (str[0].equals(str[2])) {
                    UserAddrAddActivity.this.txtCity.setText(str[2]);
                } else {
                    UserAddrAddActivity.this.txtCity.setText(str[0] + str[1] + str[2]);
                }
            }
        }).execute("北京", "市辖", "东城");
    }
}
