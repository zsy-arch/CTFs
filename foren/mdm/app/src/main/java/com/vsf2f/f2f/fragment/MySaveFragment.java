package com.vsf2f.f2f.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.BaseFragment;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.ShareUtils;
import com.em.DemoHelper;
import com.em.ui.EditActivity;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.RefreshRecyclerView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.SaveCircleAdapter;
import com.vsf2f.f2f.adapter.SaveSaleAdapter;
import com.vsf2f.f2f.adapter.SaveShopAdapter;
import com.vsf2f.f2f.adapter.SaveUrlAdapter;
import com.vsf2f.f2f.bean.CircleReadBean;
import com.vsf2f.f2f.bean.ShareAndSavebean;
import com.vsf2f.f2f.bean.ShareBean;
import com.vsf2f.f2f.bean.ShareGoodsInfo;
import com.vsf2f.f2f.bean.ShareShopInfo;
import com.vsf2f.f2f.bean.ShareThirdBean;
import com.vsf2f.f2f.ui.circle.CircleDetailActivity;
import com.vsf2f.f2f.ui.circle.ReplyActivity;
import com.vsf2f.f2f.ui.dialog.MyCollectDialog;
import com.vsf2f.f2f.ui.user.MySaveActivity;
import com.vsf2f.f2f.ui.user.SaveAndEditActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import java.io.Serializable;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MySaveFragment extends BaseFragment implements IAdapterListener, XRefreshViewListener {
    private SaveCircleAdapter circleAdapter;
    private int i;
    private int index;
    private SaveUrlAdapter linkAdapter;
    private String mainKeyId;
    private MyCollectDialog myCollectDialog;
    private RefreshRecyclerView recyclerView;
    private SaveSaleAdapter saleAdapter;
    private List<ShareBean> shareBean;
    private ShareUtils shareUtils;
    private SaveShopAdapter shopAdapter;
    private ShareThirdBean thirdBean;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_mysave;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        hideHeader();
        this.recyclerView = (RefreshRecyclerView) getView(R.id.recycler_refreshView);
        this.recyclerView.setOnRefreshListener(this);
        this.shareUtils = new ShareUtils(getActivity());
        this.i = getArguments().getInt(Constant.FLAG);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void setData(ShareAndSavebean datas) {
        if (datas != null) {
            switch (this.i) {
                case 0:
                    this.shareBean = datas.getShop();
                    break;
                case 1:
                    this.shareBean = datas.getProduct();
                    break;
                case 2:
                    this.shareBean = datas.getCircles();
                    break;
                case 3:
                    this.shareBean = datas.getShare();
                    break;
                case 4:
                    this.shareBean = datas.getHref();
                    break;
            }
            updateUI();
        }
        this.recyclerView.setRefreshComplete();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (HyUtil.isEmpty(this.shareBean)) {
            showNoData();
            return;
        }
        showCView();
        switch (this.i) {
            case 0:
                if (this.shopAdapter == null) {
                    this.shopAdapter = new SaveShopAdapter(this.context, this.shareBean);
                    this.shopAdapter.setListener(this);
                } else {
                    this.shopAdapter.setDatas(this.shareBean);
                }
                this.recyclerView.setAdapter(this.shopAdapter);
                return;
            case 1:
                if (this.saleAdapter == null) {
                    this.saleAdapter = new SaveSaleAdapter(this.context, this.shareBean);
                    this.saleAdapter.setListener(this);
                } else {
                    this.saleAdapter.setDatas(this.shareBean);
                }
                this.recyclerView.setAdapter(this.saleAdapter);
                return;
            case 2:
                if (this.circleAdapter == null) {
                    this.circleAdapter = new SaveCircleAdapter(this.context, this.shareBean);
                    this.circleAdapter.setListener(this);
                } else {
                    this.circleAdapter.setDatas(this.shareBean);
                }
                this.recyclerView.setAdapter(this.circleAdapter);
                return;
            case 3:
            case 4:
                if (this.linkAdapter == null) {
                    this.linkAdapter = new SaveUrlAdapter(this.context, this.shareBean);
                    this.linkAdapter.setListener(this);
                } else {
                    this.linkAdapter.setDatas(this.shareBean);
                }
                this.recyclerView.setAdapter(this.linkAdapter);
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseFragment, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        this.recyclerView.setRefreshComplete();
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
                MyToast.show(getActivity(), "分享圈子成功");
                return;
            case R.string.API_SHARE_DELETE /* 2131296420 */:
                MyToast.show(getActivity(), "取消成功");
                this.shareBean.remove(this.index);
                updateUI();
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseFragment, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
    }

    private void showShareDialog(final int position, boolean showShare, boolean showEdit) {
        if (this.thirdBean != null) {
            this.shareUtils.setShare(this.thirdBean);
        }
        if (this.myCollectDialog == null || this.myCollectDialog.checkNo(showShare, showEdit)) {
            this.myCollectDialog = new MyCollectDialog(this.context, showShare, showEdit);
            this.myCollectDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.fragment.MySaveFragment.1
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    switch (flag) {
                        case 1:
                            MySaveFragment.this.toShare(position);
                            return;
                        case 2:
                            ComUtil.copySys(MySaveFragment.this.context, MySaveFragment.this.shareUtils.getShare().getUrl());
                            return;
                        case 11:
                            MySaveFragment.this.shareUtils.shareTask(1);
                            return;
                        case 12:
                            MySaveFragment.this.shareUtils.shareTask(0);
                            return;
                        case 13:
                            MySaveFragment.this.shareUtils.qqShare();
                            return;
                        case 14:
                            MySaveFragment.this.shareUtils.qqZoneShare();
                            return;
                        case 21:
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(Constant.FLAG, (Serializable) MySaveFragment.this.shareBean.get(position));
                            MySaveFragment.this.startActForResult(SaveAndEditActivity.class, bundle, 999);
                            return;
                        case 22:
                            MySaveFragment.this.deleteShareData();
                            return;
                        default:
                            return;
                    }
                }
            });
        }
        this.myCollectDialog.show();
    }

    public void toShare(int position) {
        JSONObject jsonObject = new JSONObject();
        switch (this.i) {
            case 0:
                ShareShopInfo shop = this.shareBean.get(position).getShopInfo();
                String herf = ComUtil.getZCApi(this.context, "/m/shop/" + shop.getUserName() + ".mobile");
                try {
                    if (!TextUtils.isEmpty(shop.getLogo().getSpath())) {
                        String p = shop.getLogo().getSpath();
                        jsonObject.put("logo", p.substring(p.indexOf(".com") + 5, p.indexOf("?")));
                    }
                    jsonObject.put("type", "shop");
                    jsonObject.put("href", herf);
                    jsonObject.put("userName", shop.getUserName());
                    jsonObject.put("storeName", shop.getStoreName());
                    jsonObject.put(EditActivity.CONTENT, this.thirdBean.getContext());
                    getClient().setShowDialog(true);
                    getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_PUBLIC)), this.shareUtils.shareCircle(jsonObject.toString(), herf), String.class, false);
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            case 1:
                try {
                    ShareGoodsInfo good = this.shareBean.get(position).getGoodsInfo();
                    String herf2 = ComUtil.getZCApi(this.context, "/m/mall/details.mobile?isFromAppToWap=true&uuid=" + good.getGuid());
                    if (!TextUtils.isEmpty(good.getGoodsPicture().getSpath())) {
                        JSONArray jsonArray = new JSONArray();
                        String p2 = good.getGoodsPicture().getSpath();
                        jsonArray.put(p2.substring(p2.indexOf(".com") + 5, p2.indexOf("?")));
                        jsonObject.put("pic", jsonArray);
                    }
                    jsonObject.put("type", com.vsf2f.f2f.ui.utils.Constant.PRODUCTS_BUCKET);
                    jsonObject.put("guid", good.getGuid());
                    jsonObject.put("href", herf2);
                    jsonObject.put("userName", DemoHelper.getInstance().getCurrentUserName());
                    jsonObject.put("goodsName", good.getGoodsName());
                    jsonObject.put(EditActivity.CONTENT, this.thirdBean.getContext());
                    jsonObject.put(f.aS, good.getSalesPrice());
                    getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_PUBLIC)), this.shareUtils.shareCircle(jsonObject.toString(), herf2), String.class, false);
                    return;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return;
                }
            case 2:
                CircleReadBean circleBean = this.shareBean.get(position).getMsgInfo();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isShare", true);
                bundle.putParcelable("bean", circleBean);
                startActForResult(ReplyActivity.class, bundle, 100);
                return;
            default:
                MyToast.show(this.context, "暂不支持");
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteShareData() {
        getClient().post(R.string.API_SHARE_DELETE, ComUtil.getZCApi(this.context, getString(R.string.API_SHARE_DELETE, this.mainKeyId)));
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int id, Object obj, int position) {
        Bundle bundle = new Bundle();
        this.index = position;
        switch (id) {
            case -1:
                String href = "";
                switch (this.i) {
                    case 0:
                        if (this.shareBean.get(position).getHref() != null) {
                            href = this.shareBean.get(position).getHref();
                        }
                        bundle.putString(Constant.FLAG, href);
                        startAct(WebKitLocalActivity.class, bundle);
                        return;
                    case 1:
                        if (this.shareBean.get(position).getHref() != null) {
                            href = this.shareBean.get(position).getHref();
                        }
                        bundle.putString(Constant.FLAG, href);
                        startAct(WebKitLocalActivity.class, bundle);
                        return;
                    case 2:
                        if (this.shareBean.get(position) == null) {
                            return;
                        }
                        if (this.shareBean.get(position).getMsgInfo() == null) {
                            this.thirdBean = this.shareBean.get(position).getShareThird();
                            this.mainKeyId = this.shareBean.get(position).getId();
                            deleteShareData();
                            return;
                        }
                        String msgId = "";
                        if (this.shareBean.get(position).getObjId() != null) {
                            msgId = this.shareBean.get(position).getObjId();
                        }
                        bundle.putString(Constant.FLAG, msgId);
                        startAct(CircleDetailActivity.class, bundle);
                        return;
                    case 3:
                        if (this.shareBean.get(position).getHref() != null) {
                            href = this.shareBean.get(position).getHref();
                        }
                        bundle.putString(Constant.FLAG, href);
                        startAct(WebKitLocalActivity.class, bundle);
                        return;
                    case 4:
                        if (this.shareBean.get(position).getHref() != null) {
                            href = this.shareBean.get(position).getHref();
                        }
                        bundle.putString(Constant.FLAG, href);
                        startAct(WebKitLocalActivity.class, bundle);
                        return;
                    default:
                        return;
                }
            case R.id.save_imgCirclebottom /* 2131756880 */:
            case R.id.save_imgSalebottom /* 2131756887 */:
            case R.id.save_imgShopbottom /* 2131756890 */:
                this.thirdBean = this.shareBean.get(position).getShareThird();
                this.mainKeyId = this.shareBean.get(position).getId();
                showShareDialog(position, true, false);
                return;
            case R.id.save_imgbottom /* 2131756895 */:
                this.thirdBean = this.shareBean.get(position).getShareThird();
                this.mainKeyId = this.shareBean.get(position).getId();
                showShareDialog(position, true, true);
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IFragmentListener
    public void sendMsg(int i, Object o) {
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 999) {
            onRefresh();
        }
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        MySaveActivity.getInstance().requestData();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }
}
