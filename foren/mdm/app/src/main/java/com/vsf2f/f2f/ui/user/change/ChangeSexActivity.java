package com.vsf2f.f2f.ui.user.change;

import android.view.View;
import android.widget.ImageView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.db.UserDao;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserInfo;
import java.util.List;

/* loaded from: classes2.dex */
public class ChangeSexActivity extends BaseActivity {
    private UserInfo detail;
    private String gender;
    private ImageView img_man;
    private ImageView img_secret;
    private ImageView img_women;
    private List<UserInfo> infolist;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_change_sex;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.sex_select, R.string.ensure);
        setOnClickListener(R.id.ll_man);
        setOnClickListener(R.id.ll_women);
        setOnClickListener(R.id.ll_secrect);
        this.img_man = (ImageView) getView(R.id.img_man);
        this.img_women = (ImageView) getView(R.id.img_women);
        this.img_secret = (ImageView) getView(R.id.img_secret);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        super.onRightClick();
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.detail = getUserInfo();
        this.gender = this.detail.getGender();
        draw(this.gender);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        AjaxParams params = new AjaxParams();
        params.put("id", this.detail.getId());
        params.put(UserDao.CONTACT_COLUMN_NAME_GENDER, this.gender);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_USER_CHANGE_DATA, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_DATA)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        UserShared.getInstance().save(UserDao.CONTACT_COLUMN_NAME_GENDER, this.gender);
        setResult(-1);
        finish();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        initdraw();
        switch (view.getId()) {
            case R.id.ll_secrect /* 2131755192 */:
                this.gender = "-1";
                draw(this.gender);
                return;
            case R.id.img_secret /* 2131755193 */:
            case R.id.img_man /* 2131755195 */:
            default:
                return;
            case R.id.ll_man /* 2131755194 */:
                this.gender = "1";
                draw(this.gender);
                return;
            case R.id.ll_women /* 2131755196 */:
                this.gender = "0";
                draw(this.gender);
                return;
        }
    }

    public void initdraw() {
        this.img_man.setVisibility(4);
        this.img_women.setVisibility(4);
        this.img_secret.setVisibility(4);
    }

    public void draw(String index) {
        char c = 65535;
        switch (index.hashCode()) {
            case 48:
                if (index.equals("0")) {
                    c = 2;
                    break;
                }
                break;
            case 49:
                if (index.equals("1")) {
                    c = 1;
                    break;
                }
                break;
            case 1444:
                if (index.equals("-1")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.img_secret.setVisibility(0);
                this.img_secret.setImageResource(R.mipmap.ic_select);
                return;
            case 1:
                this.img_man.setVisibility(0);
                this.img_man.setImageResource(R.mipmap.ic_select);
                return;
            case 2:
                this.img_women.setVisibility(0);
                this.img_women.setImageResource(R.mipmap.ic_select);
                return;
            default:
                return;
        }
    }
}
