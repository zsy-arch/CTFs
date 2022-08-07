package com.vsf2f.f2f.ui.shop;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.fragment.DataStaFragment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DataStatisticsActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private DataStaFragment seFragment;
    private DataStaFragment thFragment;
    private List<TextView> txtViews = new ArrayList();
    private DataStaFragment yeFragment;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_data_statistics;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.shop_data, 0);
        this.fragmentManager = getSupportFragmentManager();
        this.txtViews.add((TextView) getViewAndClick(R.id.data_txtYesterday));
        this.txtViews.add((TextView) getViewAndClick(R.id.data_txt7days));
        this.txtViews.add((TextView) getViewAndClick(R.id.data_txt30days));
        addFragment(0);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.data_txtYesterday /* 2131755268 */:
                addFragment(0);
                return;
            case R.id.data_txt7days /* 2131755269 */:
                addFragment(1);
                return;
            case R.id.data_txt30days /* 2131755270 */:
                addFragment(2);
                return;
            default:
                return;
        }
    }

    public void setColor(int flag) {
        for (int i = 0; i < this.txtViews.size(); i++) {
            if (i == flag) {
                this.txtViews.get(i).setTextColor(-1);
                this.txtViews.get(i).setBackgroundResource(R.drawable.btn_red_selector);
            } else {
                this.txtViews.get(i).setTextColor(SupportMenu.CATEGORY_MASK);
                this.txtViews.get(i).setBackgroundResource(0);
            }
        }
    }

    private void addFragment(int index) {
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        hide(fragmentTransaction);
        switch (index) {
            case 0:
                if (this.yeFragment != null) {
                    fragmentTransaction.show(this.yeFragment);
                    break;
                } else {
                    this.yeFragment = new DataStaFragment();
                    this.yeFragment.setTime(2);
                    fragmentTransaction.add(R.id.fragment_contacts, this.yeFragment);
                    break;
                }
            case 1:
                if (this.seFragment != null) {
                    fragmentTransaction.show(this.seFragment);
                    break;
                } else {
                    this.seFragment = new DataStaFragment();
                    this.seFragment.setTime(7);
                    fragmentTransaction.add(R.id.fragment_contacts, this.seFragment);
                    break;
                }
            case 2:
                if (this.thFragment != null) {
                    fragmentTransaction.show(this.thFragment);
                    break;
                } else {
                    this.thFragment = new DataStaFragment();
                    this.thFragment.setTime(30);
                    fragmentTransaction.add(R.id.fragment_contacts, this.thFragment);
                    break;
                }
        }
        setColor(index);
        fragmentTransaction.commit();
    }

    public void hide(FragmentTransaction fragmentTransaction) {
        if (this.yeFragment != null) {
            fragmentTransaction.hide(this.yeFragment);
        }
        if (this.seFragment != null) {
            fragmentTransaction.hide(this.seFragment);
        }
        if (this.thFragment != null) {
            fragmentTransaction.hide(this.thFragment);
        }
    }
}
