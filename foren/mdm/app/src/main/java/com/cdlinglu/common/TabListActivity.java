package com.cdlinglu.common;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.LinearLayout;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public abstract class TabListActivity extends BaseActivity implements XRefreshViewListener, NavGroup.OnCheckedChangeListener, IAdapterListener {
    private NavGroup groupHeader;
    protected int page;
    private RefreshRecyclerView refreshView;

    public abstract void changeTabPage(int i);

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_tab;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        this.groupHeader = (NavGroup) getView(R.id.tab_groupHeader);
        this.groupHeader.setOnCheckedChangeListener(this);
        this.refreshView = (RefreshRecyclerView) getView(R.id.recycler_refreshView);
        this.refreshView.setOnRefreshListener(this);
        initHeaderBack(R.string.app_name, 0);
    }

    public void setTab(String... strs) {
        int size = strs.length;
        int padding = HyUtil.dip2px(this.context, 2.0f);
        for (int i = 0; i < size; i++) {
            NavView nav = new NavView(this.context);
            nav.setId(i);
            nav.setText(strs[i]);
            nav.getTxtKey().setTextColor(getResources().getColorStateList(R.color.tab_item_selector));
            nav.setBackgroundResource(R.drawable.tab_bar_selector);
            if (i == 0) {
                nav.setChecked(true);
            }
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, -1);
            lp.weight = 1.0f;
            nav.setLayoutParams(lp);
            this.groupHeader.addView(nav);
            if (i + 1 < size) {
                View view = new View(this.context);
                view.setBackgroundResource(R.color.divider_gray);
                view.setPadding(0, padding, 0, padding);
                view.setLayoutParams(new LinearLayout.LayoutParams(1, -1));
                this.groupHeader.addView(view);
            }
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        this.page = 1;
        requestData();
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup group, NavView nav, @IdRes int checkedId) {
        changeTabPage(checkedId);
    }

    protected void setAdapter(BaseRecyclerAdapter adapter) {
        this.refreshView.setAdapter(adapter);
    }

    protected void refreshComplete() {
        this.refreshView.setRefreshComplete();
    }
}
