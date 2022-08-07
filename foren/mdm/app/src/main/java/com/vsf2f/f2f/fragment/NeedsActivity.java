package com.vsf2f.f2f.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.district.DistrictSearchQuery;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.server.audio.MusicPlayer;
import com.cdlinglu.server.audio.MusicPlayerUtils;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.LocationUtils;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.frame.view.recycler.xRefreshView.XRefreshView;
import com.hy.frame.view.recycler.xRefreshView.XScrollView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.DemandAdapter;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.DemandServiceBean;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.bean.UserShareBean;
import com.vsf2f.f2f.ui.dialog.BasePopupMenu;
import com.vsf2f.f2f.ui.dialog.DemandBrushDialog;
import com.vsf2f.f2f.ui.dialog.DiscoverDialog;
import com.vsf2f.f2f.ui.dialog.MenuNearDialog;
import com.vsf2f.f2f.ui.needs.NeedsManagerActivity;
import com.vsf2f.f2f.ui.needs.NeedsSearchActivity;
import com.vsf2f.f2f.ui.needs.NeedsSortByTypeActivity;
import com.vsf2f.f2f.ui.needs.NeedsTypeChoiceActivity;
import com.vsf2f.f2f.ui.needs.demand.ChoiceCityActivity;
import com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity;
import com.vsf2f.f2f.ui.needs.demand.NearbyDemandActivity;
import com.vsf2f.f2f.ui.needs.demand.NearbyPeopleActivity;
import com.vsf2f.f2f.ui.needs.demand.NearbyServiceActivity;
import com.vsf2f.f2f.ui.needs.demand.NearbyShopActivity;
import com.vsf2f.f2f.ui.otay.ManorTreeDialog;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.AmapUtils;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import com.vsf2f.f2f.ui.view.ImageCycleView;
import com.vsf2f.f2f.ui.view.MyListview;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class NeedsActivity extends BaseActivity implements NavGroup.OnCheckedChangeListener, XRefreshViewListener, DemandAdapter.AdapterItemClick, DemandBrushDialog.BrushType, AMapLocationListener, XScrollView.OnScrollListener, BasePopupMenu.PopupListener {
    private DemandAdapter adapter;
    private String currentCity;
    private DemandBrushDialog demandBrushDialog;
    private ImageCycleView icvViewPager;
    private boolean isRefreshHead;
    private String latitude;
    private String longitude;
    private MyLocation myLocation;
    private NavView nv_screen_sort;
    private NavView nv_screen_sort2;
    private NavView nv_screen_typy;
    private NavView nv_screen_typy2;
    private NavGroup topScreen;
    private TextView tv_location;
    private XRefreshView xrv_demand;
    private XScrollView xsv_parent;
    private final int PAGESIZE = 20;
    private int page = 1;
    private int orderType = 0;
    private int serviceType = 2;
    private int headerHeight = 0;
    private List<UserShareBean> shareList = new ArrayList();
    private List<DemandDetailBean> demandList = new ArrayList();
    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() { // from class: com.vsf2f.f2f.fragment.NeedsActivity.2
        @Override // com.vsf2f.f2f.ui.view.ImageCycleView.ImageCycleViewListener
        public void onImageClick(int position, View imageView) {
            switch (position) {
                case 0:
                    NeedsActivity.this.startToHelp();
                    return;
                case 1:
                    if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                        new EaseAlertDialog(NeedsActivity.this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.fragment.NeedsActivity.2.1
                            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                            public void onResult(boolean confirmed, Bundle bundle) {
                                if (confirmed) {
                                    NeedsActivity.this.startAct(BindPhoneActivity.class);
                                }
                            }
                        }, true).show();
                        return;
                    } else if (UserShared.getInstance().isOpenManor()) {
                        GameUtil.startGame(NeedsActivity.this.context);
                        return;
                    } else {
                        new ManorTreeDialog(NeedsActivity.this.context).show();
                        return;
                    }
                default:
                    try {
                        String url = ((UserShareBean) NeedsActivity.this.shareList.get(position - 2)).getShareHref();
                        Bundle bundlePre = new Bundle();
                        bundlePre.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(NeedsActivity.this.context, url));
                        bundlePre.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
                        NeedsActivity.this.startAct(WebKitLocalActivity.class, bundlePre);
                        return;
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                        return;
                    }
            }
        }

        @Override // com.vsf2f.f2f.ui.view.ImageCycleView.ImageCycleViewListener
        public void turnToEnd(boolean b) {
        }

        @Override // com.vsf2f.f2f.ui.view.ImageCycleView.ImageCycleViewListener
        public void displayImage(String imageUrl, ImageView imageView) {
        }
    };
    private boolean showTopScreen = false;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_demand_main;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        hideHeader();
        setOnClickListener(R.id.tv_nearby);
        setOnClickListener(R.id.tv_sharetime);
        setOnClickListener(R.id.tv_sharefood);
        setOnClickListener(R.id.tv_sharespace);
        setOnClickListener(R.id.tv_shareskill);
        setOnClickListener(R.id.tv_sharetravel);
        setOnClickListener(R.id.tv_sharecustom);
        setOnClickListener(R.id.tv_sharefamily);
        setOnClickListener(R.id.tv_sharearticle);
        setOnClickListener(R.id.tv_shareprotection);
        setOnClickListener(R.id.tv_sharecommercial);
        setOnClickListener(R.id.tv_sharecreative);
        setOnClickListener(R.id.tv_shareexchange);
        setOnClickListener(R.id.discover_imgTree);
        setOnClickListener(R.id.discover_imgPublish);
        setOnClickListener(R.id.top_imgSearch2);
        this.nv_screen_sort = (NavView) getView(R.id.nv_screen_sort);
        this.nv_screen_typy = (NavView) getView(R.id.nv_screen_type);
        this.nv_screen_sort2 = (NavView) getView(R.id.nv_screen_sort2);
        this.nv_screen_typy2 = (NavView) getView(R.id.nv_screen_type2);
        this.tv_location = (TextView) getViewAndClick(R.id.tv_location);
        this.topScreen = (NavGroup) getView(R.id.sub_groupFooter2);
        this.topScreen.setCanClickCurrent();
        this.topScreen.setOnCheckedChangeListener(this);
        NavGroup demand_groupScreen = (NavGroup) getView(R.id.sub_groupFooter);
        demand_groupScreen.setCanClickCurrent();
        demand_groupScreen.setOnCheckedChangeListener(this);
        this.xrv_demand = (XRefreshView) getView(R.id.xrv_parent);
        this.xrv_demand.setPullRefreshEnable(true);
        this.xrv_demand.setPullLoadEnable(true);
        this.xrv_demand.setAutoRefresh(false);
        this.xrv_demand.setXRefreshViewListener(this);
        this.xsv_parent = (XScrollView) getView(R.id.xsv_parent);
        this.xsv_parent.setOnScrollListener(this);
        this.adapter = new DemandAdapter(this.demandList, this);
        ((MyListview) getView(R.id.mlv_demand)).setAdapter((ListAdapter) this.adapter);
        if (isLogin()) {
            final FrameLayout fly_list_myorder = (FrameLayout) getViewAndClick(R.id.fly_list_myorder);
            fly_list_myorder.postDelayed(new Runnable() { // from class: com.vsf2f.f2f.fragment.NeedsActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    fly_list_myorder.setVisibility(0);
                    fly_list_myorder.setAnimation(AnimationUtils.makeInAnimation(NeedsActivity.this.context, false));
                }
            }, 3000L);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        updateUI();
        this.headerHeight = HyUtil.dip2px(this.context, 358.0f);
        MusicPlayerUtils.getInstance().bindService(this.context);
        this.demandBrushDialog = new DemandBrushDialog(this.context, this);
        this.myLocation = MyApplication.getMyLocation();
        if (this.myLocation != null) {
            this.latitude = this.myLocation.getLatitude();
            this.longitude = this.myLocation.getLongitude();
            this.currentCity = this.myLocation.getCity();
            this.tv_location.setText(this.currentCity);
            this.page = 1;
            getDemandList();
        } else {
            this.tv_location.setText("定位失败");
            AmapUtils.getLocation(this.context, this);
        }
        this.icvViewPager = (ImageCycleView) getViewAndClick(R.id.shop_manager_icvViewPager);
        this.icvViewPager.setScaleMatch(true);
        this.icvViewPager.setStart();
        if (isLogin()) {
            String json = UserShared.getInstance().readPrevList();
            if (!TextUtils.isEmpty(json)) {
                this.shareList = parseNoHeaderJArray(json);
            }
        }
        updateViewpager();
    }

    public List<String> getImgList() {
        List<String> imgs = new ArrayList<>();
        imgs.add("img_banner_demand");
        if (isLogin()) {
            imgs.add("img_banner_manor");
            for (UserShareBean share : this.shareList) {
                imgs.add(share.getShareAdvert());
            }
        }
        return imgs;
    }

    public void setImgList(List<UserShareBean> shareList) {
        this.shareList = shareList;
        updateViewpager();
    }

    public void updateViewpager() {
        this.icvViewPager.setImageResources(getImgList(), this.mAdCycleViewListener);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void setRefreshHead() {
        this.isRefreshHead = true;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        ImageView imgAvatar = (ImageView) getView(R.id.mine_imgAvatar);
        String userPic = DemoHelper.getInstance().getCurrentUserPic().getSpath();
        if (HyUtil.isEmpty(userPic)) {
            imgAvatar.setImageResource(R.mipmap.def_head);
        } else {
            ComUtil.displayHead(this.context, imgAvatar, userPic);
        }
        this.isRefreshHead = false;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.tv_location /* 2131755273 */:
                bundle.putString(DistrictSearchQuery.KEYWORDS_CITY, this.currentCity);
                startActForResult(ChoiceCityActivity.class, bundle, 1001);
                return;
            case R.id.top_imgSearch2 /* 2131755274 */:
                bundle.putString("latitude", this.latitude);
                bundle.putString("longitude", this.longitude);
                bundle.putBoolean("isService", false);
                startAct(NeedsSearchActivity.class, bundle);
                return;
            case R.id.tv_nearby /* 2131755276 */:
                new MenuNearDialog(this.context, this).showAsDropDown(v);
                return;
            case R.id.tv_sharetime /* 2131755281 */:
                bundle.putString("id", "4277");
                bundle.putString("name", getString(R.string.need_type_title1));
                startTo(bundle);
                return;
            case R.id.tv_sharecustom /* 2131755282 */:
                bundle.putString("id", "4506");
                bundle.putString("name", getString(R.string.need_type_title2));
                startTo(bundle);
                return;
            case R.id.tv_sharefood /* 2131755283 */:
                bundle.putString("id", "4518");
                bundle.putString("name", getString(R.string.need_type_title3));
                startTo(bundle);
                return;
            case R.id.tv_sharearticle /* 2131755284 */:
                bundle.putString("id", "4528");
                bundle.putString("name", getString(R.string.need_type_title4));
                startTo(bundle);
                return;
            case R.id.tv_sharespace /* 2131755285 */:
                bundle.putString("id", "4541");
                bundle.putString("name", getString(R.string.need_type_title5));
                startTo(bundle);
                return;
            case R.id.tv_sharetravel /* 2131755286 */:
                bundle.putString("id", "4552");
                bundle.putString("name", getString(R.string.need_type_title6));
                startTo(bundle);
                return;
            case R.id.tv_shareprotection /* 2131755287 */:
                bundle.putString("id", "4567");
                bundle.putString("name", getString(R.string.need_type_title7));
                startTo(bundle);
                return;
            case R.id.tv_sharefamily /* 2131755288 */:
                bundle.putString("id", "4577");
                bundle.putString("name", getString(R.string.need_type_title8));
                startTo(bundle);
                return;
            case R.id.tv_sharecommercial /* 2131755289 */:
                bundle.putString("id", "4587");
                bundle.putString("name", getString(R.string.need_type_title9));
                startTo(bundle);
                return;
            case R.id.tv_shareskill /* 2131755290 */:
                bundle.putString("id", "4601");
                bundle.putString("name", getString(R.string.need_type_title10));
                startTo(bundle);
                return;
            case R.id.tv_sharecreative /* 2131755291 */:
                bundle.putString("id", "4608");
                bundle.putString("name", getString(R.string.need_type_title11));
                startTo(bundle);
                return;
            case R.id.tv_shareexchange /* 2131755292 */:
                startActForResult(NeedsTypeChoiceActivity.class, null, 1002);
                return;
            case R.id.discover_imgPublish /* 2131755300 */:
                new DiscoverDialog(this.context).show();
                return;
            case R.id.discover_imgTree /* 2131755301 */:
                if (isNoLogin()) {
                    startAct(LoginActivity.class);
                    return;
                } else if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                    new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.fragment.NeedsActivity.3
                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                        public void onResult(boolean confirmed, Bundle bundle2) {
                            if (confirmed) {
                                NeedsActivity.this.startAct(BindPhoneActivity.class);
                            }
                        }
                    }, true).show();
                    return;
                } else if (UserShared.getInstance().isOpenManor()) {
                    GameUtil.startGame(this.context);
                    return;
                } else {
                    new ManorTreeDialog(this.context).show();
                    return;
                }
            case R.id.fly_list_myorder /* 2131755302 */:
                startAct(NeedsManagerActivity.class);
                return;
            case R.id.iv_back /* 2131756046 */:
                onLeftClick();
                return;
            default:
                return;
        }
    }

    public void startToHelp() {
        Bundle bundle = new Bundle();
        String dph_url = DemoHelper.getInstance().readConfig().getDph_url();
        if (TextUtils.isEmpty(dph_url)) {
            dph_url = getString(R.string.API_DEMAND_HELP);
        }
        bundle.putString(com.hy.frame.util.Constant.FLAG, dph_url);
        startAct(WebKitLocalActivity.class, bundle);
    }

    public void startTo(Bundle bundle) {
        bundle.putString("latitude", this.latitude);
        bundle.putString("longitude", this.longitude);
        bundle.putBoolean("isService", false);
        startAct(NeedsSortByTypeActivity.class, bundle);
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup group, NavView nav, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.nv_screen_sort /* 2131755294 */:
            case R.id.nv_screen_sort2 /* 2131755298 */:
                this.demandBrushDialog.setBrushType(0);
                this.demandBrushDialog.showAsDropDown(nav);
                return;
            case R.id.nv_screen_type /* 2131755295 */:
            case R.id.nv_screen_type2 /* 2131755299 */:
                this.demandBrushDialog.setBrushType(1);
                this.demandBrushDialog.showAsDropDown(nav);
                return;
            case R.id.mlv_demand /* 2131755296 */:
            case R.id.sub_groupFooter2 /* 2131755297 */:
            default:
                return;
        }
    }

    private void getDemandList() {
        AjaxParams params = new AjaxParams();
        getClient().get(R.string.API_DEMAND_HOMEPAGE, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_HOMEPAGE)) + "?pageNo=" + this.page + "&pageSize=20&lat=" + this.latitude + "&lng=" + this.longitude + "&distance=1000&orderBy=" + this.orderType + "&serviceMode=" + this.serviceType, params, DemandServiceBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        this.xrv_demand.stopRefresh();
        this.xrv_demand.stopLoadMore();
        switch (result.getRequestCode()) {
            case R.string.API_DEMAND_HOMEPAGE /* 2131296326 */:
                updateUi(result);
                return;
            default:
                return;
        }
    }

    private void updateUi(ResultInfo result) {
        DemandServiceBean bean = (DemandServiceBean) result.getObj();
        if (this.page == 1) {
            this.demandList.clear();
            if (bean.getDatas().size() == 0) {
                Toast.makeText(getApp(), "未找到相关数据", 0).show();
            }
        } else if (bean.getDatas().size() == 0) {
            Toast.makeText(getApp(), "没有更多了", 0).show();
        }
        this.demandList.addAll(bean.getDatas());
        this.adapter.setService(false);
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.xrv_demand.stopLoadMore();
        this.xrv_demand.stopRefresh();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        if (TextUtils.isEmpty(this.latitude) || "0.0".equals(this.latitude)) {
            this.myLocation = MyApplication.getMyLocation();
            if (this.myLocation == null) {
                this.tv_location.setText("定位失败");
                AmapUtils.getLocation(this.context, this);
                return;
            }
            this.latitude = this.myLocation.getLatitude();
            this.longitude = this.myLocation.getLongitude();
            this.currentCity = this.myLocation.getCity();
            this.tv_location.setText(this.currentCity);
        }
        this.page = 1;
        getDemandList();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
        this.page++;
        getDemandList();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 1001:
                    String city = data.getStringExtra(DistrictSearchQuery.KEYWORDS_CITY);
                    this.latitude = data.getDoubleExtra("lat", 0.0d) + "";
                    this.longitude = data.getDoubleExtra("lng", 0.0d) + "";
                    this.tv_location.setText(city);
                    getDemandList();
                    return;
                case 1002:
                    String typeId = data.getStringExtra("id");
                    String typeName = data.getStringExtra("name");
                    Bundle bundle = new Bundle();
                    bundle.putString("id", typeId);
                    bundle.putString("name", typeName);
                    bundle.putInt("shareTypeIndex", 0);
                    startTo(bundle);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.vsf2f.f2f.adapter.DemandAdapter.AdapterItemClick
    public void playVoice(View v, String url) {
        MusicPlayerUtils.getInstance().playMusicByUrl(url, (MusicPlayer) v);
    }

    @Override // com.vsf2f.f2f.adapter.DemandAdapter.AdapterItemClick
    public void itemClick(int position) {
        DemandDetailBean bean = null;
        if (HyUtil.isNoEmpty(this.demandList) && position < this.demandList.size()) {
            bean = this.demandList.get(position);
        }
        if (bean != null) {
            toDetail(bean.getMoId());
        }
    }

    private void toDetail(int moId) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", moId);
        startAct(DemandInfoActivity.class, bundle);
    }

    @Override // com.vsf2f.f2f.ui.dialog.DemandBrushDialog.BrushType
    public void brushTypeCallBack(int orderType, int serviceMode, @StringRes int orderRes, @StringRes int serviceRes) {
        this.orderType = orderType;
        this.serviceType = serviceMode;
        this.page = 1;
        this.nv_screen_sort.setText(orderRes);
        this.nv_screen_typy.setText(serviceRes);
        this.nv_screen_sort2.setText(orderRes);
        this.nv_screen_typy2.setText(serviceRes);
        getDemandList();
    }

    @Override // com.amap.api.location.AMapLocationListener
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            this.myLocation = LocationUtils.locationToAmap(aMapLocation.toString());
            MyApplication.setMyLocation(this.myLocation);
            this.latitude = this.myLocation.getLatitude() + "";
            this.longitude = this.myLocation.getLongitude() + "";
            this.currentCity = this.myLocation.getCity();
            this.tv_location.setText(this.currentCity);
            this.page = 1;
            getDemandList();
        }
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.XScrollView.OnScrollListener
    public void onScrollStateChanged(ScrollView view, int scrollState, boolean arriveBottom) {
    }

    @Override // com.hy.frame.view.recycler.xRefreshView.XScrollView.OnScrollListener
    public void onScroll(int l, final int t, int oldl, int oldt) {
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.fragment.NeedsActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (NeedsActivity.this.showTopScreen && t < NeedsActivity.this.headerHeight) {
                    NeedsActivity.this.showTopScreen = false;
                    NeedsActivity.this.topScreen.post(new Runnable() { // from class: com.vsf2f.f2f.fragment.NeedsActivity.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            NeedsActivity.this.topScreen.setVisibility(8);
                        }
                    });
                    MyLog.e("topScreen:GONE");
                } else if (!NeedsActivity.this.showTopScreen && t > NeedsActivity.this.headerHeight) {
                    NeedsActivity.this.showTopScreen = true;
                    NeedsActivity.this.topScreen.post(new Runnable() { // from class: com.vsf2f.f2f.fragment.NeedsActivity.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            NeedsActivity.this.topScreen.setVisibility(0);
                        }
                    });
                    MyLog.e("topScreen:VISIBLE");
                }
            }
        });
    }

    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        mHiddenAction.setDuration(1000L);
        return mHiddenAction;
    }

    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        mHiddenAction.setDuration(200L);
        return mHiddenAction;
    }

    private List<UserShareBean> parseNoHeaderJArray(String jsonStr) {
        JsonArray jsonArray = new JsonParser().parse(jsonStr).getAsJsonArray();
        Gson gson = new Gson();
        List<UserShareBean> circleBeanList = new ArrayList<>();
        Iterator<JsonElement> it = jsonArray.iterator();
        while (it.hasNext()) {
            circleBeanList.add((UserShareBean) gson.fromJson(it.next(), (Class<Object>) UserShareBean.class));
        }
        return circleBeanList;
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.isRefreshHead) {
            updateUI();
        }
        try {
            if (this.topScreen.getVisibility() == 0) {
                this.adapter.notifyDataSetChanged();
                MyLog.e("notify");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        MusicPlayerUtils.getInstance().pauseAudio();
        this.icvViewPager.setStop();
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        MusicPlayerUtils.getInstance().unBindService(this.context);
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu.PopupListener
    public void onClickPopup(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("latitude", this.latitude);
        bundle.putString("longitude", this.longitude);
        switch (v.getId()) {
            case R.id.nv_nearby_people /* 2131756282 */:
                startAct(NearbyPeopleActivity.class, bundle);
                return;
            case R.id.nv_nearby_demand /* 2131756283 */:
                startAct(NearbyDemandActivity.class, bundle);
                return;
            case R.id.nv_nearby_service /* 2131756284 */:
                startAct(NearbyServiceActivity.class, bundle);
                return;
            case R.id.nv_nearby_seller /* 2131756285 */:
                startAct(NearbyShopActivity.class, bundle);
                return;
            default:
                return;
        }
    }
}
