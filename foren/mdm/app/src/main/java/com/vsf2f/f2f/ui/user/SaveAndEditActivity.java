package com.vsf2f.f2f.ui.user;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.RegexUtils;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ShareBean;

/* loaded from: classes2.dex */
public class SaveAndEditActivity extends BaseActivity {
    private EditText editname;
    private EditText editpicurl;
    private EditText editshortname;
    private EditText editurl;
    private ImageView img_adddelete;
    private ImageView img_adddelete_picurl;
    private ImageView img_adddelete_save;
    private ImageView img_adddelete_short;
    private ShareBean shareBean;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_add_save;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.save_edit, R.string.ensure);
        this.editname = (EditText) getView(R.id.edit_addname);
        this.editshortname = (EditText) getView(R.id.edit_add_short_name);
        this.editurl = (EditText) getView(R.id.edit_add_save);
        this.editpicurl = (EditText) getView(R.id.edit_addname_picurl);
        this.img_adddelete = (ImageView) getViewAndClick(R.id.img_adddelete);
        this.img_adddelete_short = (ImageView) getViewAndClick(R.id.img_adddelete_short);
        this.img_adddelete_save = (ImageView) getViewAndClick(R.id.img_adddelete_save);
        this.img_adddelete_picurl = (ImageView) getViewAndClick(R.id.img_adddelete_picurl);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.shareBean = (ShareBean) getBundle().getSerializable(Constant.FLAG);
        if (this.shareBean != null) {
            this.editname.setText(this.shareBean.getName());
            if (this.shareBean.getSortName() != null) {
                this.editshortname.setText(this.shareBean.getSortName());
            }
            this.editurl.setText(this.shareBean.getHref());
            this.editpicurl.setText(this.shareBean.getIcon());
        }
        this.editname.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.SaveAndEditActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    SaveAndEditActivity.this.img_adddelete.setVisibility(4);
                } else {
                    SaveAndEditActivity.this.img_adddelete.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.editshortname.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.SaveAndEditActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    SaveAndEditActivity.this.img_adddelete_short.setVisibility(4);
                } else {
                    SaveAndEditActivity.this.img_adddelete_short.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.editurl.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.SaveAndEditActivity.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    SaveAndEditActivity.this.img_adddelete_save.setVisibility(4);
                } else {
                    SaveAndEditActivity.this.img_adddelete_save.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.editpicurl.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.SaveAndEditActivity.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    SaveAndEditActivity.this.img_adddelete_picurl.setVisibility(4);
                } else {
                    SaveAndEditActivity.this.img_adddelete_picurl.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        super.onRightClick();
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(true);
        params.put("id", this.shareBean.getId());
        params.put("name", this.editname.getText().toString());
        params.put("sortName", this.editshortname.getText().toString());
        String url = this.editurl.getText().toString();
        if (HyUtil.isEmpty(url)) {
            MyToast.show(this.context, "请输入地址");
        } else if (!RegexUtils.isUrl(url)) {
            MyToast.show(this.context, "请输入正确的地址");
        } else {
            params.put("href", url);
            String picurl = this.editpicurl.getText().toString();
            if (!HyUtil.isEmpty(picurl)) {
                if (HyUtil.isEmpty(picurl)) {
                    MyToast.show(this.context, "请输入地址");
                    return;
                } else if (!RegexUtils.isUrl(picurl)) {
                    MyToast.show(this.context, "请输入正确的地址");
                    return;
                }
            }
            params.put(f.aY, picurl);
            params.put("orderNum", 999);
            getClient().post(R.string.API_EDIT_UPDATE, ComUtil.getZCApi(this.context, getString(R.string.API_EDIT_UPDATE)), params);
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        MyToast.show(this.context, getString(R.string.toast_change_success));
        setResult(-1);
        finish();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.img_adddelete /* 2131755159 */:
                this.editname.setText("");
                return;
            case R.id.edit_add_short_name /* 2131755160 */:
            case R.id.edit_add_save /* 2131755162 */:
            case R.id.edit_addname_picurl /* 2131755164 */:
            default:
                return;
            case R.id.img_adddelete_short /* 2131755161 */:
                this.editshortname.setText("");
                return;
            case R.id.img_adddelete_save /* 2131755163 */:
                this.editurl.setText("");
                return;
            case R.id.img_adddelete_picurl /* 2131755165 */:
                this.editpicurl.setText("");
                return;
        }
    }
}
