package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.tencent.tauth.Tencent;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.CollectPagerAdapter;
import com.vsf2f.f2f.bean.ShareAndSavebean;
import com.vsf2f.f2f.bean.ShareBean;
import com.vsf2f.f2f.fragment.MySaveFragment;
import com.vsf2f.f2f.ui.dialog.BasePopupMenu;
import com.vsf2f.f2f.ui.dialog.CollectAddDialog;
import com.vsf2f.f2f.ui.other.BaseUiListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class MySaveActivity extends BaseActivity implements BasePopupMenu.PopupListener {
    private static MySaveActivity instance;
    private ViewPager collectPager;
    private TabLayout collectTab;
    private ArrayList<MySaveFragment> listFragment;
    private String[] arr = {"店铺", "商品", "圈子", "分享", "链接"};
    private ShareAndSavebean datas = new ShareAndSavebean();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_save_tab;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.user_collection, R.drawable.icon_add_white);
        this.collectTab = (TabLayout) getView(R.id.mysave_Tablayout);
        this.collectPager = (ViewPager) getView(R.id.mysave_Viewpager);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        new CollectAddDialog(this.context, this).showAsDropDown(getHeader());
    }

    public static MySaveActivity getInstance() {
        if (instance == null) {
            instance = new MySaveActivity();
        }
        return instance;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        instance = this;
        this.listFragment = new ArrayList<>();
        for (int i = 0; i < this.arr.length; i++) {
            MySaveFragment saveFragment = new MySaveFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.FLAG, i);
            saveFragment.setArguments(bundle);
            this.listFragment.add(saveFragment);
        }
        CollectPagerAdapter pagerAdapter = new CollectPagerAdapter(getSupportFragmentManager(), this.listFragment, this.arr);
        this.collectPager.setOffscreenPageLimit(5);
        this.collectPager.setAdapter(pagerAdapter);
        this.collectTab.setupWithViewPager(this.collectPager);
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().setShowDialog(false);
        getClient().get(R.string.API_USER_SHARE_SAVE, ComUtil.getZCApi(this.context, getString(R.string.API_USER_SHARE_SAVE)), null, ShareAndSavebean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_USER_SHARE_SAVE /* 2131296473 */:
                if (result.getObj() != null) {
                    this.datas = (ShareAndSavebean) result.getObj();
                }
                updateUI();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        Iterator<MySaveFragment> it = this.listFragment.iterator();
        while (it.hasNext()) {
            it.next().setData(this.datas);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
    }

    private void addHref(int flag, Intent data) {
        ShareBean share = new ShareBean(data.getStringExtra("name"), data.getStringExtra("url"));
        if (flag == 4) {
            if (this.datas.getShare() == null) {
                List<ShareBean> list = new ArrayList<>();
                list.add(share);
                this.datas.setShare(list);
            } else {
                this.datas.getShare().add(share);
            }
        }
        if (flag == 5) {
            if (this.datas.getHref() == null) {
                List<ShareBean> list2 = new ArrayList<>();
                list2.add(share);
                this.datas.setHref(list2);
            } else {
                this.datas.getHref().add(share);
            }
        }
        this.collectPager.setCurrentItem(flag);
        updateUI();
        requestData();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10103 || requestCode == 10104) {
            Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener(this));
        }
        if (resultCode == -1 && data != null) {
            switch (requestCode) {
                case 111:
                    addHref(3, data);
                    return;
                case 222:
                    addHref(4, data);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu.PopupListener
    public void onClickPopup(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.txt_share /* 2131756336 */:
                bundle.putInt(Constant.FLAG, 0);
                startActForResult(AddSaveActivity.class, bundle, 111);
                return;
            case R.id.txt_save /* 2131756337 */:
                bundle.putInt(Constant.FLAG, 1);
                startActForResult(AddSaveActivity.class, bundle, 222);
                return;
            default:
                return;
        }
    }
}
