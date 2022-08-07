package com.vsf2f.f2f.ui.shop;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.ShareUtils;
import com.easeui.widget.EaseAlertDialog;
import com.em.utils.UserShared;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.http.AjaxParams;
import com.tencent.tauth.Tencent;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.GoodesAdapter;
import com.vsf2f.f2f.bean.GoodsListBean;
import com.vsf2f.f2f.bean.ProductBean;
import com.vsf2f.f2f.ui.dialog.ShareForwardDialog;
import com.vsf2f.f2f.ui.other.BaseUiListener;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GoodsListActivity extends BaseActivity implements XRefreshViewListener, IAdapterListener {
    private String delorup;
    private Drawable drawablesorta;
    private Drawable drawablesortd;
    private Drawable drawableunsort;
    private ImageView imgTopselect;
    private RelativeLayout llyMenu;
    private LinearLayout llySoldMenu;
    private LinearLayout llyTopprompt;
    private LinearLayout llyUnsoldMenu;
    private int position;
    private ProductBean productShare;
    private RefreshRecyclerView rcvList;
    private ShareForwardDialog shareDialog;
    private ShareUtils shareUtils;
    private GoodesAdapter soldAdapter;
    private List<ProductBean> soldList;
    private int soldNums;
    private List<ProductBean> tempList;
    private TextView txtSold;
    private TextView txtSorting1;
    private TextView txtSorting2;
    private TextView txtSorting3;
    private TextView txtSorting4;
    private TextView txtTopcancel;
    private TextView txtToptxt;
    private TextView txtUnsold;
    private GoodesAdapter unsoldAdapter;
    private List<ProductBean> unsoldList;
    private int unsoldNums;
    private View vSold;
    private View vUnsold;
    private int sorting = 0;
    private int PAGE_SIZE = 20;
    private int PAGE_SOLD_INDEX = 1;
    private int PAGE_UNSOLD_INDEX = 1;
    private boolean isSoldTab = true;
    private boolean hasDown = false;
    private String[] sortStr = {"ct", "sc", "vc", "store"};
    private int DO_DEL = 0;
    private int DO_PUT = 1;
    private int DO_REM = 2;
    boolean isDESV = true;
    private boolean isOps = false;

    private List<ProductBean> getSoldList() {
        if (this.soldList == null) {
            this.soldList = new ArrayList();
        }
        return this.soldList;
    }

    private List<ProductBean> getUnSoldList() {
        if (this.unsoldList == null) {
            this.unsoldList = new ArrayList();
        }
        return this.unsoldList;
    }

    private List<ProductBean> getAuthList() {
        return this.isSoldTab ? getSoldList() : getUnSoldList();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_goods_manager;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.shop_sale_manager, R.drawable.icon_add_white);
        setOnClickListener(R.id.goods_manage_txtDel);
        setOnClickListener(R.id.goods_manage_txtDel2);
        setOnClickListener(R.id.goods_manage_txtSell);
        setOnClickListener(R.id.goods_manage_txtSellout);
        setOnClickListener(R.id.goods_manage_txtRecommend);
        this.txtSold = (TextView) getViewAndClick(R.id.goods_manage_txtSold);
        this.txtUnsold = (TextView) getViewAndClick(R.id.goods_manage_txtUnsold);
        this.txtSorting1 = (TextView) getViewAndClick(R.id.goods_manage_txtSorting1);
        this.txtSorting2 = (TextView) getViewAndClick(R.id.goods_manage_txtSorting2);
        this.txtSorting3 = (TextView) getViewAndClick(R.id.goods_manage_txtSorting3);
        this.txtSorting4 = (TextView) getViewAndClick(R.id.goods_manage_txtSorting4);
        this.llyTopprompt = (LinearLayout) getViewAndClick(R.id.goods_list_top_prompt);
        this.txtTopcancel = (TextView) getViewAndClick(R.id.goods_list_top_cancel);
        this.imgTopselect = (ImageView) getViewAndClick(R.id.goods_list_top_select);
        this.txtToptxt = (TextView) getViewAndClick(R.id.goods_list_top_txt);
        this.llyMenu = (RelativeLayout) getView(R.id.goods_manage_llyMenu);
        this.llySoldMenu = (LinearLayout) getView(R.id.goods_manage_llySoldMenu);
        this.llyUnsoldMenu = (LinearLayout) getView(R.id.goods_manage_llyUnsoldMenu);
        this.vSold = getView(R.id.goods_manage_vSold);
        this.vUnsold = getView(R.id.goods_manage_vUnsold);
        this.rcvList = (RefreshRecyclerView) getView(R.id.goods_manage_rcvList);
        this.rcvList.setOnRefreshListener(this);
        this.shareUtils = new ShareUtils(this);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.drawablesorta = getResources().getDrawable(R.mipmap.ico_sort_press_a);
        this.drawablesorta.setBounds(0, 0, this.drawablesorta.getMinimumWidth(), this.drawablesorta.getMinimumHeight());
        this.drawablesortd = getResources().getDrawable(R.mipmap.ico_sort_press_d);
        this.drawablesortd.setBounds(0, 0, this.drawablesorta.getMinimumWidth(), this.drawablesorta.getMinimumHeight());
        this.drawableunsort = getResources().getDrawable(R.mipmap.ico_sort_unpress);
        this.drawableunsort.setBounds(0, 0, this.drawablesorta.getMinimumWidth(), this.drawablesorta.getMinimumHeight());
        requestSoldTab(this.PAGE_SOLD_INDEX);
        requestUnsoldTab(this.PAGE_UNSOLD_INDEX);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        if (this.isSoldTab) {
            requestSoldTab(this.PAGE_SOLD_INDEX);
        } else {
            requestUnsoldTab(this.PAGE_UNSOLD_INDEX);
        }
    }

    public void requestSoldTab(int soldIndex) {
        String url;
        AjaxParams params = new AjaxParams();
        String url2 = ComUtil.getZCApi(this.context, getString(R.string.API_PRODUCTS_ITEMS)) + "?sts=1&field=" + this.sortStr[this.sorting] + "&limit=" + this.PAGE_SIZE + "&pageIndex=" + soldIndex;
        if (this.isDESV) {
            url = url2 + "&dir=DESV";
        } else {
            url = url2 + "&dir=ASC";
        }
        getClient().get(11, url, params, GoodsListBean.class, false);
    }

    public void requestUnsoldTab(int unsoldIndex) {
        String url;
        AjaxParams params = new AjaxParams();
        String url2 = ComUtil.getZCApi(this.context, getString(R.string.API_PRODUCTS_ITEMS)) + "?sts=0&field=" + this.sortStr[this.sorting] + "&limit=" + this.PAGE_SIZE + "&pageIndex=" + unsoldIndex;
        if (this.isDESV) {
            url = url2 + "&dir=DESV";
        } else {
            url = url2 + "&dir=ASC";
        }
        getClient().get(22, url, params, GoodsListBean.class, false);
    }

    public void requestBatch(String opt, String ids) {
        this.delorup = opt;
        getClient().post(R.string.API_PRODUCTS_BATCH, ComUtil.getZCApi(this.context, getString(R.string.API_PRODUCTS_BATCH, new Object[]{opt, ids})), new AjaxParams(), String.class, false);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case 11:
                this.rcvList.setRefreshComplete();
                GoodsListBean goodsListBean = (GoodsListBean) result.getObj();
                if (goodsListBean != null && goodsListBean.getRows() != null) {
                    List<ProductBean> beans = goodsListBean.getRows();
                    if (this.PAGE_SOLD_INDEX <= 1) {
                        this.soldList = beans;
                    } else if (beans != null) {
                        getSoldList().addAll(beans);
                    }
                    refreshTabNum();
                    updateUI();
                    return;
                }
                return;
            case 22:
                this.rcvList.setRefreshComplete();
                GoodsListBean goodsListBean2 = (GoodsListBean) result.getObj();
                if (goodsListBean2 != null && goodsListBean2.getRows() != null) {
                    List<ProductBean> beans2 = goodsListBean2.getRows();
                    if (this.PAGE_UNSOLD_INDEX <= 1) {
                        this.unsoldList = beans2;
                    } else if (beans2 != null) {
                        getUnSoldList().addAll(beans2);
                    }
                    refreshTabNum();
                    updateUI();
                    return;
                }
                return;
            case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
                MyToast.show(this.context, "分享成功");
                return;
            case R.string.API_PRODUCTS_BATCH /* 2131296389 */:
                if (this.tempList == null) {
                    this.tempList = new ArrayList();
                }
                if (this.delorup != null) {
                    String str = this.delorup;
                    char c = 65535;
                    switch (str.hashCode()) {
                        case -1335458389:
                            if (str.equals(RequestParameters.SUBRESOURCE_DELETE)) {
                                c = 0;
                                break;
                            }
                            break;
                        case -278656139:
                            if (str.equals("unrecom")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 3739:
                            if (str.equals("up")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 3089570:
                            if (str.equals("down")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 108388974:
                            if (str.equals("recom")) {
                                c = 3;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            MyToast.show(this.context, "删除成功");
                            if (this.isOps) {
                                this.isOps = false;
                                getAuthList().removeAll(this.tempList);
                            } else {
                                getAuthList().remove(this.position);
                            }
                            if (this.isSoldTab) {
                                this.soldAdapter.notifyDataSetChanged();
                                this.hasDown = true;
                            } else {
                                this.unsoldAdapter.notifyDataSetChanged();
                            }
                            refreshTabNum();
                            break;
                        case 1:
                            MyToast.show(this.context, "上架成功");
                            if (this.isOps) {
                                this.isOps = false;
                                getUnSoldList().removeAll(this.tempList);
                                getSoldList().addAll(this.tempList);
                            } else {
                                getSoldList().addAll(this.tempList);
                                ProductBean product = getUnSoldList().get(this.position);
                                product.setMarketable("1");
                                getSoldList().add(product);
                                getUnSoldList().remove(this.position);
                            }
                            refreshTabNum();
                            this.unsoldAdapter.notifyDataSetChanged();
                            break;
                        case 2:
                            MyToast.show(this.context, "下架成功");
                            if (this.isOps) {
                                this.isOps = false;
                                getSoldList().removeAll(this.tempList);
                                getUnSoldList().addAll(this.tempList);
                            } else {
                                ProductBean product2 = getSoldList().get(this.position);
                                product2.setMarketable("0");
                                getUnSoldList().add(product2);
                                getSoldList().remove(this.position);
                            }
                            this.hasDown = true;
                            refreshTabNum();
                            this.soldAdapter.notifyDataSetChanged();
                            break;
                        case 3:
                            MyToast.show(this.context, "推荐成功");
                            if (!this.isOps) {
                                getSoldList().get(this.position).setIsRecom(1);
                                this.soldAdapter.notifyDataSetChanged();
                                break;
                            } else {
                                this.isOps = false;
                                break;
                            }
                        case 4:
                            MyToast.show(this.context, "取消推荐成功");
                            getSoldList().get(this.position).setIsRecom(0);
                            this.soldAdapter.notifyDataSetChanged();
                            break;
                    }
                    this.tempList = null;
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    private void refreshOnSold() {
        refreshSelect(R.string.goods_onsold, this.soldNums);
    }

    private void refreshUnSold() {
        refreshSelect(R.string.goods_unsold, this.unsoldNums);
    }

    private void refreshSelect(int strid, int nums) {
        if (nums > 0) {
            this.txtToptxt.setText(getString(R.string.goods_list_top_prompt, new Object[]{getString(strid), nums + ""}));
            this.llyTopprompt.setVisibility(0);
            this.llyMenu.setVisibility(0);
            return;
        }
        this.llyTopprompt.setVisibility(8);
        this.llyMenu.setVisibility(8);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.isSoldTab) {
            if (this.soldAdapter == null) {
                this.soldAdapter = new GoodesAdapter(this.context, getSoldList(), this.isSoldTab);
                this.soldAdapter.setListener(this);
            } else {
                this.soldAdapter.setStats(getSoldList(), this.isSoldTab);
            }
            this.rcvList.setAdapter(this.soldAdapter);
            refreshOnSold();
            return;
        }
        if (this.unsoldAdapter == null) {
            this.unsoldAdapter = new GoodesAdapter(this.context, getUnSoldList(), this.isSoldTab);
            this.unsoldAdapter.setListener(this);
        } else {
            this.unsoldAdapter.setStats(getUnSoldList(), this.isSoldTab);
        }
        this.rcvList.setAdapter(this.unsoldAdapter);
        refreshUnSold();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.goods_list_top_cancel /* 2131755313 */:
                clearSelect();
                return;
            case R.id.goods_list_top_txt /* 2131755314 */:
            case R.id.goods_manage_tab /* 2131755316 */:
            case R.id.head_toolBar /* 2131755317 */:
            case R.id.goods_manage_vSold /* 2131755319 */:
            case R.id.goods_manage_vUnsold /* 2131755321 */:
            case R.id.goods_manage_tab2 /* 2131755322 */:
            case R.id.goods_manage_rcvList /* 2131755327 */:
            case R.id.goods_manage_llyMenu /* 2131755328 */:
            case R.id.goods_manage_llyUnsoldMenu /* 2131755329 */:
            case R.id.goods_manage_llySoldMenu /* 2131755332 */:
            default:
                return;
            case R.id.goods_list_top_select /* 2131755315 */:
                if (this.imgTopselect.isSelected()) {
                    this.imgTopselect.setSelected(false);
                    allSelectedData(false);
                    return;
                }
                this.imgTopselect.setSelected(true);
                allSelectedData(true);
                return;
            case R.id.goods_manage_txtSold /* 2131755318 */:
                changeTabSold(true);
                return;
            case R.id.goods_manage_txtUnsold /* 2131755320 */:
                changeTabSold(false);
                return;
            case R.id.goods_manage_txtSorting1 /* 2131755323 */:
                changeSort(0);
                return;
            case R.id.goods_manage_txtSorting2 /* 2131755324 */:
                changeSort(1);
                return;
            case R.id.goods_manage_txtSorting3 /* 2131755325 */:
                changeSort(2);
                return;
            case R.id.goods_manage_txtSorting4 /* 2131755326 */:
                changeSort(3);
                return;
            case R.id.goods_manage_txtDel2 /* 2131755330 */:
            case R.id.goods_manage_txtDel /* 2131755333 */:
                batchSelected(this.DO_DEL);
                return;
            case R.id.goods_manage_txtSell /* 2131755331 */:
            case R.id.goods_manage_txtSellout /* 2131755334 */:
                batchSelected(this.DO_PUT);
                return;
            case R.id.goods_manage_txtRecommend /* 2131755335 */:
                batchSelected(this.DO_REM);
                return;
        }
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int v, Object o, int i) {
        this.position = i;
        final ProductBean product = (ProductBean) o;
        if (v == R.id.goods_manage_i_imgCheck) {
            if (this.isSoldTab) {
                if (product.isCheck()) {
                    getSoldList().get(i).setCheck(false);
                    if (this.soldNums > 0) {
                        this.soldNums--;
                    }
                } else {
                    getSoldList().get(i).setCheck(true);
                    this.soldNums++;
                }
                this.soldAdapter.notifyDataSetChanged();
                refreshOnSold();
                return;
            }
            if (product.isCheck()) {
                if (this.unsoldNums > 0) {
                    this.unsoldNums--;
                }
                getUnSoldList().get(i).setCheck(false);
            } else {
                this.unsoldNums++;
                getUnSoldList().get(i).setCheck(true);
            }
            this.unsoldAdapter.notifyDataSetChanged();
            refreshUnSold();
        } else if (this.soldNums == 0 && this.unsoldNums == 0) {
            switch (v) {
                case R.id.goods_manage_i_txtLook /* 2131756824 */:
                    String url = product.getReadUrl();
                    if (!TextUtils.isEmpty(url)) {
                        Bundle bundlePre = new Bundle();
                        bundlePre.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, url));
                        startAct(WebKitLocalActivity.class, bundlePre);
                        return;
                    }
                    MyToast.show(this.context, (int) R.string.login_state_abnormal);
                    return;
                case R.id.goods_manage_i_txtEdit /* 2131756825 */:
                    Bundle bundleEdit = new Bundle();
                    bundleEdit.putParcelable(Constant.FLAG, product);
                    startActForResult(GoodsEditActivity.class, bundleEdit, 111);
                    return;
                case R.id.goods_manage_i_txtChange /* 2131756826 */:
                    if (this.isSoldTab) {
                        requestBatch("down", product.getId() + "");
                        return;
                    } else {
                        requestBatch("up", product.getId() + "");
                        return;
                    }
                case R.id.goods_manage_i_txtRecommend /* 2131756827 */:
                    if (1 != product.getIsRecom()) {
                        requestBatch("recom", product.getId() + "");
                    } else {
                        requestBatch("unrecom", product.getId() + "");
                    }
                    this.soldAdapter.notifyDataSetChanged();
                    return;
                case R.id.goods_manage_i_txtDelete /* 2131756828 */:
                    new EaseAlertDialog((Context) this, (int) R.string.prompt, (int) R.string.confirm_to_delete, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsListActivity.1
                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                        public void onResult(boolean confirmed, Bundle bundle) {
                            if (confirmed) {
                                GoodsListActivity.this.requestBatch(RequestParameters.SUBRESOURCE_DELETE, product.getId() + "");
                            }
                        }
                    }, true).show();
                    return;
                case R.id.goods_manage_i_txtShare /* 2131756829 */:
                    showForwardDialog(product);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10103 || requestCode == 10104) {
            Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener(this));
        }
        if (resultCode == -1 && requestCode == 111) {
            requestData();
        }
    }

    private void changeTabSold(boolean isSold) {
        if (isSold) {
            if (this.unsoldNums != 0) {
                clearSelect();
            }
            this.isSoldTab = true;
            this.llySoldMenu.setVisibility(0);
            this.llyUnsoldMenu.setVisibility(8);
            this.vSold.setVisibility(0);
            this.vUnsold.setVisibility(4);
            this.txtSold.setTextColor(SupportMenu.CATEGORY_MASK);
            this.txtUnsold.setTextColor(-16777216);
            if (getSoldList().size() == 0) {
                requestData();
            }
        } else {
            if (this.soldNums != 0) {
                clearSelect();
            }
            this.isSoldTab = false;
            this.llySoldMenu.setVisibility(8);
            this.llyUnsoldMenu.setVisibility(0);
            this.vSold.setVisibility(4);
            this.vUnsold.setVisibility(0);
            this.txtSold.setTextColor(-16777216);
            this.txtUnsold.setTextColor(SupportMenu.CATEGORY_MASK);
            if (getUnSoldList().size() == 0) {
                requestData();
            }
        }
        updateUI();
    }

    @TargetApi(21)
    private void changeSort(int index) {
        if (index == this.sorting) {
            this.isDESV = !this.isDESV;
        }
        switch (index) {
            case 0:
                this.sorting = 0;
                this.txtSorting1.setTextColor(SupportMenu.CATEGORY_MASK);
                this.txtSorting2.setTextColor(-16777216);
                this.txtSorting3.setTextColor(-16777216);
                this.txtSorting4.setTextColor(-16777216);
                this.txtSorting1.setCompoundDrawables(null, null, this.isDESV ? this.drawablesortd : this.drawablesorta, null);
                this.txtSorting2.setCompoundDrawables(null, null, this.drawableunsort, null);
                this.txtSorting3.setCompoundDrawables(null, null, this.drawableunsort, null);
                this.txtSorting4.setCompoundDrawables(null, null, this.drawableunsort, null);
                break;
            case 1:
                this.sorting = 1;
                this.txtSorting1.setTextColor(-16777216);
                this.txtSorting2.setTextColor(SupportMenu.CATEGORY_MASK);
                this.txtSorting3.setTextColor(-16777216);
                this.txtSorting4.setTextColor(-16777216);
                this.txtSorting1.setCompoundDrawables(null, null, this.drawableunsort, null);
                this.txtSorting2.setCompoundDrawables(null, null, this.isDESV ? this.drawablesortd : this.drawablesorta, null);
                this.txtSorting3.setCompoundDrawables(null, null, this.drawableunsort, null);
                this.txtSorting4.setCompoundDrawables(null, null, this.drawableunsort, null);
                break;
            case 2:
                this.sorting = 2;
                this.txtSorting1.setTextColor(-16777216);
                this.txtSorting2.setTextColor(-16777216);
                this.txtSorting3.setTextColor(SupportMenu.CATEGORY_MASK);
                this.txtSorting4.setTextColor(-16777216);
                this.txtSorting1.setCompoundDrawables(null, null, this.drawableunsort, null);
                this.txtSorting2.setCompoundDrawables(null, null, this.drawableunsort, null);
                this.txtSorting3.setCompoundDrawables(null, null, this.isDESV ? this.drawablesortd : this.drawablesorta, null);
                this.txtSorting4.setCompoundDrawables(null, null, this.drawableunsort, null);
                break;
            case 3:
                this.sorting = 3;
                this.txtSorting1.setTextColor(-16777216);
                this.txtSorting2.setTextColor(-16777216);
                this.txtSorting3.setTextColor(-16777216);
                this.txtSorting4.setTextColor(SupportMenu.CATEGORY_MASK);
                this.txtSorting1.setCompoundDrawables(null, null, this.drawableunsort, null);
                this.txtSorting2.setCompoundDrawables(null, null, this.drawableunsort, null);
                this.txtSorting3.setCompoundDrawables(null, null, this.drawableunsort, null);
                this.txtSorting4.setCompoundDrawables(null, null, this.isDESV ? this.drawablesortd : this.drawablesorta, null);
                break;
        }
        if (getAuthList().size() > 2) {
            this.PAGE_SOLD_INDEX = 1;
            this.PAGE_UNSOLD_INDEX = 1;
            requestData();
        }
    }

    private void showForwardDialog(ProductBean product) {
        this.productShare = product;
        if (product.getShareThird() != null) {
            this.shareUtils.setShare(product.getShareThird());
        }
        if (this.shareDialog == null) {
            this.shareDialog = new ShareForwardDialog(this);
            this.shareDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsListActivity.2
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    switch (flag) {
                        case 1:
                            if (GoodsListActivity.this.soldList.size() >= 3) {
                                GoodsListActivity.this.getClient().setShowDialog(true);
                                GoodsListActivity.this.getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(GoodsListActivity.this.context, GoodsListActivity.this.getString(R.string.API_CIRCLES_PUBLIC)), GoodsListActivity.this.shareUtils.shareProduct(GoodsListActivity.this.productShare), String.class, false);
                                return;
                            }
                            MyToast.show(GoodsListActivity.this.context, "上架三个以上产品，激活店铺后，才可以分享推广产品");
                            return;
                        case 2:
                            ComUtil.copySys(GoodsListActivity.this.context, GoodsListActivity.this.shareUtils.getShare().getUrl());
                            return;
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        default:
                            return;
                        case 11:
                            GoodsListActivity.this.shareUtils.shareTask(1);
                            return;
                        case 12:
                            GoodsListActivity.this.shareUtils.shareTask(0);
                            return;
                        case 13:
                            GoodsListActivity.this.shareUtils.qqShare();
                            return;
                        case 14:
                            GoodsListActivity.this.shareUtils.qqZoneShare();
                            return;
                    }
                }
            });
        }
        this.shareDialog.show();
    }

    private void batchSelected(int doWhat) {
        this.isOps = true;
        this.tempList = new ArrayList();
        boolean recom = doWhat == this.DO_REM;
        String ids = "";
        for (ProductBean product : getAuthList()) {
            if (product.isCheck()) {
                if (recom) {
                    product.setIsRecom(1);
                }
                product.setCheck(false);
                ids = ids + product.getId() + ",";
                this.tempList.add(product);
            }
        }
        if (this.tempList.size() > 0) {
            final String ids2 = ids.substring(0, ids.length() - 1);
            if (doWhat == this.DO_REM) {
                requestBatch("recom", ids2);
            } else if (doWhat == this.DO_DEL) {
                new EaseAlertDialog((Context) this, (int) R.string.prompt, (int) R.string.confirm_to_delete, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.shop.GoodsListActivity.3
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            GoodsListActivity.this.requestBatch(RequestParameters.SUBRESOURCE_DELETE, ids2);
                            GoodsListActivity.this.clearTopNum();
                        }
                    }
                }, true).show();
                return;
            } else if (this.isSoldTab) {
                requestBatch("down", ids2);
            } else {
                requestBatch("up", ids2);
            }
        }
        clearTopNum();
        if (this.isSoldTab) {
            this.soldAdapter.notifyDataSetChanged();
        } else {
            this.unsoldAdapter.notifyDataSetChanged();
        }
    }

    public void refreshTabNum() {
        this.txtSold.setText(getString(R.string.shop_on_sale2, new Object[]{getSoldList().size() + ""}));
        this.txtUnsold.setText(getString(R.string.shop_under_shelf2, new Object[]{getUnSoldList().size() + ""}));
        UserShared.getInstance().setStoreGoodNum(getSoldList().size());
    }

    public void clearSelect() {
        allSelectedData(false);
        clearTopNum();
    }

    public void clearTopNum() {
        this.soldNums = 0;
        this.unsoldNums = 0;
        this.imgTopselect.setSelected(false);
        this.llyMenu.setVisibility(8);
        this.llyTopprompt.setVisibility(8);
    }

    private void allSelectedData(boolean select) {
        if (this.isSoldTab) {
            if (select) {
                this.soldNums = getSoldList().size();
            } else {
                this.soldNums = 0;
            }
            if (getSoldList().size() != 0) {
                for (int i = 0; i < getSoldList().size(); i++) {
                    getSoldList().get(i).setCheck(select);
                }
            }
        } else {
            if (select) {
                this.unsoldNums = getUnSoldList().size();
            } else {
                this.unsoldNums = 0;
            }
            if (getUnSoldList().size() != 0) {
                for (int i2 = 0; i2 < getUnSoldList().size(); i2++) {
                    getUnSoldList().get(i2).setCheck(select);
                }
            }
        }
        updateUI();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        this.PAGE_SOLD_INDEX = 1;
        this.PAGE_UNSOLD_INDEX = 1;
        requestData();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
        getClient().setShowDialog(false);
        if (getSoldList().size() % this.PAGE_SIZE == 0) {
            if (this.isSoldTab) {
                this.PAGE_SOLD_INDEX++;
            } else {
                this.PAGE_UNSOLD_INDEX++;
            }
            requestData();
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.hasDown) {
            setResult(-1);
        }
        finish();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        startActForResult(GoodsPublishActivity.class, 111);
    }
}
