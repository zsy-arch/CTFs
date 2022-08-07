package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.cdlinglu.common.BaseActivity;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.SearchResultAdapter;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class SearchEditActivity extends BaseActivity implements PoiSearch.OnPoiSearchListener {
    private SearchResultAdapter adapter;
    private EditText et_key;
    private ListView lv_detail;
    private PoiSearch poiSearch;
    private PoiSearch.Query query;
    private TextView tv_search;
    private ArrayList<PoiItem> result = new ArrayList<>();
    private String keyWord = "";
    private LatLonPoint latLng = new LatLonPoint(0.0d, 0.0d);

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_search_edit;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        hideHeader();
        this.et_key = (EditText) findViewById(R.id.et_key);
        this.tv_search = (TextView) findViewById(R.id.tv_search);
        this.lv_detail = (ListView) findViewById(R.id.lv_detail);
        this.et_key.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.needs.demand.SearchEditActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    SearchEditActivity.this.tv_search.setTextColor(SearchEditActivity.this.getResources().getColor(R.color.white));
                    SearchEditActivity.this.tv_search.setEnabled(true);
                    return;
                }
                SearchEditActivity.this.tv_search.setTextColor(SearchEditActivity.this.getResources().getColor(R.color.calculator_white));
                SearchEditActivity.this.tv_search.setEnabled(false);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        this.tv_search.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.needs.demand.SearchEditActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SearchEditActivity.this.keyWord = SearchEditActivity.this.et_key.getText().toString();
                SearchEditActivity.this.doSearchQuery();
            }
        });
        this.lv_detail.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.demand.SearchEditActivity.3
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (((PoiItem) SearchEditActivity.this.result.get(0)).getLatLonPoint() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("key", (PoiItem) SearchEditActivity.this.result.get(position));
                    Intent intent = new Intent();
                    intent.putExtra("bund", bundle);
                    SearchEditActivity.this.setResult(-1, intent);
                    SearchEditActivity.this.finish();
                }
            }
        });
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
    public void onViewClick(View v) {
    }

    public void back(View view) {
        finish();
    }

    protected void doSearchQuery() {
        if (!HyUtil.isNetworkConnected(this)) {
            MyToast.show(getApplication(), (int) R.string.network_error);
            return;
        }
        getClient().showDialogNow();
        this.query = new PoiSearch.Query(this.keyWord, "", "");
        this.query.setPageSize(30);
        if (this.latLng != null) {
            this.poiSearch = new PoiSearch(this, this.query);
            this.poiSearch.setOnPoiSearchListener(this);
            this.poiSearch.searchPOIAsyn();
        }
    }

    @Override // com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener
    public void onPoiSearched(PoiResult poiResult, int i) {
        this.result.clear();
        this.result.addAll(poiResult.getPois());
        if (this.result.size() == 0) {
            PoiItem poiItem = new PoiItem("", null, "未找到相关数据", "");
            poiItem.setProvinceName("");
            this.result.add(poiItem);
        }
        if (this.adapter == null) {
            this.adapter = new SearchResultAdapter(this.result);
            this.adapter.setSelectedPosition(-1);
            this.lv_detail.setAdapter((ListAdapter) this.adapter);
        } else {
            this.adapter.setSelectedPosition(-1);
            this.adapter.notifyDataSetChanged();
        }
        getClient().dialogDismiss();
    }

    @Override // com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }
}
