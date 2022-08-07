package com.vsf2f.f2f.ui.needs;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.cdlinglu.common.BaseActivity;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.adapter.MyBaseAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.DemandTypeAdapter;
import com.vsf2f.f2f.bean.DemandTypesBean;
import com.vsf2f.f2f.ui.utils.area.AssetsUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class NeedsTypeChoiceActivity extends BaseActivity implements IAdapterListener, DemandTypeAdapter.AdapterItemClick {
    private int lastScroll;
    private ListView left_listView;
    private MyBaseAdapter<DemandTypesBean.ChildsBean> listAdapter;
    private ListView right_listView;
    private DemandTypesBean datas = new DemandTypesBean();
    private List<DemandTypesBean.ChildsBean> listDatas = new ArrayList();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_demand_type_choice;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.title_demand, 0);
        this.left_listView = (ListView) getView(R.id.DemandTypeChoice_mlvType);
        this.right_listView = (ListView) getView(R.id.DemandTypeChoice_mlvType2);
        getDatas();
    }

    private void getDatas() {
        String jsonStr = AssetsUtils.readJson(this.context, "NeedsTypes.json");
        if (!TextUtils.isEmpty(jsonStr)) {
            this.datas = (DemandTypesBean) JSONObject.parseObject(jsonStr, DemandTypesBean.class);
            if (this.datas == null || this.datas.getObj() != null) {
            }
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (this.datas != null && this.datas.getObj() != null) {
            this.listDatas = this.datas.getObj();
            this.listAdapter = new ListAdapter(this.context, this.listDatas);
            this.listAdapter.setListener(this);
            this.left_listView.setAdapter((android.widget.ListAdapter) this.listAdapter);
            this.right_listView.setAdapter((android.widget.ListAdapter) new DemandTypeAdapter(this.context, this.listDatas, this));
            clickLeftList(0);
            this.right_listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.vsf2f.f2f.ui.needs.NeedsTypeChoiceActivity.1
                @Override // android.widget.AbsListView.OnScrollListener
                public void onScrollStateChanged(AbsListView arg0, int arg1) {
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (NeedsTypeChoiceActivity.this.lastScroll != firstVisibleItem) {
                        NeedsTypeChoiceActivity.this.lastScroll = firstVisibleItem;
                        NeedsTypeChoiceActivity.this.clickLeftList(firstVisibleItem);
                    }
                }
            });
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
        clickLeftList(position);
        this.right_listView.setSelection(position);
    }

    @Override // com.vsf2f.f2f.adapter.DemandTypeAdapter.AdapterItemClick
    public void selectType(DemandTypesBean.ChildsBean type) {
        toResult(type);
    }

    private void toResult(DemandTypesBean.ChildsBean childsBean) {
        Intent intent = new Intent();
        intent.putExtra("id", childsBean.getId() + "");
        intent.putExtra("name", childsBean.getName());
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickLeftList(int position) {
        int i = 0;
        while (i < this.listDatas.size()) {
            this.listDatas.get(i).setCheck(position == i);
            i++;
        }
        if (this.listAdapter != null) {
            this.listAdapter.refresh(this.listDatas);
        } else {
            initData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ListAdapter extends MyBaseAdapter<DemandTypesBean.ChildsBean> {
        public ListAdapter(Context context, List<DemandTypesBean.ChildsBean> datas) {
            super(context, datas);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = inflate(R.layout.item_demand_type_list);
                new ViewCache(view);
            }
            ViewCache h = (ViewCache) view.getTag();
            DemandTypesBean.ChildsBean childsBean = (DemandTypesBean.ChildsBean) NeedsTypeChoiceActivity.this.listDatas.get(i);
            h.txtView.setText(childsBean.getName());
            if (childsBean.isCheck()) {
                h.txtView.setTextColor(NeedsTypeChoiceActivity.this.getResources().getColor(R.color.txt_red_finace));
                h.leftLine.setVisibility(0);
                h.rightLine.setVisibility(8);
            } else {
                h.txtView.setTextColor(NeedsTypeChoiceActivity.this.getResources().getColor(R.color.gray_a));
                h.leftLine.setVisibility(8);
                h.rightLine.setVisibility(0);
            }
            setOnClickListener(h.txtView, childsBean, i);
            return view;
        }

        /* loaded from: classes2.dex */
        private class ViewCache {
            private View leftLine;
            private View rightLine;
            private TextView txtView;

            public ViewCache(View v) {
                v.setTag(this);
                this.leftLine = ListAdapter.this.getView(v, R.id.item_demand_type_list_left_line);
                this.rightLine = ListAdapter.this.getView(v, R.id.item_demand_type_list_right_line);
                this.txtView = (TextView) ListAdapter.this.getView(v, R.id.item_demand_type_list_txt);
            }
        }
    }
}
