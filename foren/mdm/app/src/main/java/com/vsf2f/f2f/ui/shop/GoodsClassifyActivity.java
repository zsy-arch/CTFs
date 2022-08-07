package com.vsf2f.f2f.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.GoodsClassifyAdapter;
import com.vsf2f.f2f.bean.ProductCategory;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.listener.GoodsClassifyListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GoodsClassifyActivity extends BaseActivity {
    private GoodsClassifyAdapter adapter;
    private LinearLayout containerView;
    private List<ProductCategory> data;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_goods_classify;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.goods_type, 0);
        setOnClickListener(R.id.add_class);
        this.containerView = (LinearLayout) getView(R.id.ll_container_goods_classify);
        this.data = new ArrayList();
        this.adapter = new GoodsClassifyAdapter(this, this.data, new GoodsClassifyListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsClassifyActivity.1
            @Override // com.vsf2f.f2f.ui.utils.listener.GoodsClassifyListener
            public void onClick(int position, int underCount) {
                ProductCategory product = (ProductCategory) GoodsClassifyActivity.this.data.get(position);
                int id = product.getId();
                String name = product.getName();
                TextView textView = (TextView) LayoutInflater.from(GoodsClassifyActivity.this).inflate(R.layout.top_textview_goods_classify, (ViewGroup) null);
                textView.setText(name);
                textView.setTag(product);
                GoodsClassifyActivity.this.containerView.addView(textView);
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsClassifyActivity.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        GoodsClassifyActivity.this.getChildData(((ProductCategory) v.getTag()).getPid());
                        int i = GoodsClassifyActivity.this.containerView.indexOfChild(v);
                        GoodsClassifyActivity.this.containerView.removeViews(i, GoodsClassifyActivity.this.containerView.getChildCount() - i);
                        if (i == 0) {
                            GoodsClassifyActivity.this.getView(R.id.txt_list_empty).setVisibility(0);
                        }
                    }
                });
                GoodsClassifyActivity.this.getView(R.id.txt_list_empty).setVisibility(8);
                if (underCount != 0) {
                    GoodsClassifyActivity.this.getChildData(id);
                    GoodsClassifyActivity.this.addNextImage();
                    return;
                }
                GoodsClassifyActivity.this.backWithResult((ProductCategory) GoodsClassifyActivity.this.data.get(position));
            }
        });
        ((ListView) getView(R.id.listView_goods_classify)).setAdapter((ListAdapter) this.adapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addNextImage() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        params.width = 25;
        this.containerView.addView((ImageView) LayoutInflater.from(this).inflate(R.layout.top_imageview_goods_classify, (ViewGroup) null), params);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void backWithResult(ProductCategory productCategory) {
        int childCount = this.containerView.getChildCount();
        String selectName = "";
        for (int i = 0; i < childCount; i++) {
            if (i % 2 == 0) {
                selectName = ((TextView) this.containerView.getChildAt(i)).getText().toString();
            } else {
                selectName = " - ";
            }
        }
        Intent intent = new Intent();
        intent.putExtra("name", selectName);
        intent.putExtra(Constant.PRODUCT, productCategory);
        setResult(-1, intent);
        finish();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_PRODUCT_CATEGORY, ComUtil.getZCApi(this.context, getString(R.string.API_PRODUCT_CATEGORY)), null, ProductCategory.class, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getChildData(int pid) {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_PRODUCT_CATEGORY, ComUtil.getZCApi(this.context, getString(R.string.API_PRODUCT_CATEGORY) + "?parentId=" + pid), null, ProductCategory.class, true);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.API_PRODUCT_CATEGORY /* 2131296393 */:
                if (result.getErrorCode() == 0) {
                    loadAdapter((List) result.getObj());
                    return;
                } else {
                    MyToast.show(this, "没有数据");
                    return;
                }
            default:
                return;
        }
    }

    private void loadAdapter(List<ProductCategory> list) {
        if (this.data == null) {
            this.data = new ArrayList();
        }
        this.data.clear();
        this.data.addAll(list);
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.add_class /* 2131756018 */:
                Bundle bundle = new Bundle();
                bundle.putBoolean(com.hy.frame.util.Constant.FLAG, true);
                startActForResult(ShopManagerActivity.class, bundle, 444);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            requestData();
        }
    }
}
