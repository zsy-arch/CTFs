package com.vsf2f.f2f.ui.user;

import android.content.Intent;
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
public class AddSaveActivity extends BaseActivity {
    private EditText editname;
    private EditText editpicurl;
    private EditText editshortname;
    private EditText editurl;
    private int favType;
    private int flag;
    private ImageView img_adddelete;
    private ImageView img_adddelete_picurl;
    private ImageView img_adddelete_save;
    private ImageView img_adddelete_short;
    private ShareBean share;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_add_save;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        this.flag = getBundle().getInt(Constant.FLAG);
        if (this.flag == 0) {
            initHeaderBackTxt(R.string.add_share, R.string.ensure);
            this.favType = 0;
        } else if (this.flag == 1) {
            initHeaderBackTxt(R.string.add_href, R.string.ensure);
            this.favType = 4;
        }
        this.editname = (EditText) getView(R.id.edit_addname);
        this.editurl = (EditText) getView(R.id.edit_add_save);
        this.editpicurl = (EditText) getView(R.id.edit_addname_picurl);
        this.editshortname = (EditText) getView(R.id.edit_add_short_name);
        this.img_adddelete = (ImageView) getViewAndClick(R.id.img_adddelete);
        this.img_adddelete_short = (ImageView) getViewAndClick(R.id.img_adddelete_short);
        this.img_adddelete_save = (ImageView) getViewAndClick(R.id.img_adddelete_save);
        this.img_adddelete_picurl = (ImageView) getViewAndClick(R.id.img_adddelete_picurl);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.editname.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.AddSaveActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    AddSaveActivity.this.img_adddelete.setVisibility(4);
                } else {
                    AddSaveActivity.this.img_adddelete.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.editshortname.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.AddSaveActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    AddSaveActivity.this.img_adddelete_short.setVisibility(4);
                } else {
                    AddSaveActivity.this.img_adddelete_short.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.editurl.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.AddSaveActivity.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    AddSaveActivity.this.img_adddelete_save.setVisibility(4);
                } else {
                    AddSaveActivity.this.img_adddelete_save.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.editpicurl.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.user.AddSaveActivity.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    AddSaveActivity.this.img_adddelete_picurl.setVisibility(4);
                } else {
                    AddSaveActivity.this.img_adddelete_picurl.setVisibility(0);
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
        String name = this.editname.getText().toString();
        if (HyUtil.isEmpty(name)) {
            MyToast.show(this, getString(R.string.input_name));
        } else if (name.length() > 10) {
            MyToast.show(this, getString(R.string.input_long_name));
        } else {
            String shortname = this.editshortname.getText().toString();
            if (shortname.length() > 10) {
                MyToast.show(this, getString(R.string.input_short_name));
                return;
            }
            String herf = this.editurl.getText().toString().trim();
            if (HyUtil.isEmpty(herf)) {
                MyToast.show(this, getString(R.string.input_url));
            } else if (!RegexUtils.isUrl(herf)) {
                MyToast.show(this, "链接不符合规范");
            } else if (herf.length() > 300) {
                MyToast.show(this, getString(R.string.input_long_url));
            } else {
                String picHerf = this.editpicurl.getText().toString();
                if (HyUtil.isNoEmpty(picHerf)) {
                    if (!RegexUtils.isUrl(picHerf)) {
                        MyToast.show(this, "链接不符合规范");
                        return;
                    } else if (picHerf.length() > 300) {
                        MyToast.show(this, getString(R.string.input_long_url));
                        return;
                    }
                }
                this.share = new ShareBean(name, herf);
                AjaxParams params = new AjaxParams();
                getClient().setShowDialog(true);
                params.put("name", ComUtil.UTF(name));
                params.put("sortName", ComUtil.UTF(shortname));
                params.put("href", herf);
                params.put(f.aY, picHerf);
                params.put("type", this.flag);
                params.put("favType", this.favType);
                params.put("orderNum", 999);
                getClient().post(R.string.API_ADD_FAVARITE, ComUtil.getZCApi(this.context, getString(R.string.API_ADD_FAVARITE)), params, String.class, false);
            }
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.API_ADD_FAVARITE /* 2131296282 */:
                MyToast.show(this, getString(R.string.add_success));
                Intent intent = new Intent();
                intent.putExtra("name", this.share.getName());
                intent.putExtra("url", this.share.getHref());
                setResult(-1, intent);
                finish();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.img_adddelete /* 2131755159 */:
                this.editname.setText(getString(R.string.empty));
                return;
            case R.id.edit_add_short_name /* 2131755160 */:
            case R.id.edit_add_save /* 2131755162 */:
            case R.id.edit_addname_picurl /* 2131755164 */:
            default:
                return;
            case R.id.img_adddelete_short /* 2131755161 */:
                this.editshortname.setText(getString(R.string.empty));
                return;
            case R.id.img_adddelete_save /* 2131755163 */:
                this.editurl.setText(getString(R.string.empty));
                return;
            case R.id.img_adddelete_picurl /* 2131755165 */:
                this.editpicurl.setText(getString(R.string.empty));
                return;
        }
    }
}
