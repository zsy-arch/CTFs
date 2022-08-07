package com.vsf2f.f2f.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import com.cdlinglu.common.BaseFragment;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.GuideUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.google.gson.Gson;
import com.hy.frame.adapter.BaseAdapter;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.NavView;
import com.tencent.smtt.sdk.TbsListener;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.WebSitesBean;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.circle.CircleActivity;
import com.vsf2f.f2f.ui.otay.ManorTreeDialog;
import com.vsf2f.f2f.ui.qrcode.QrcodeActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import com.vsf2f.f2f.ui.view.MyListview;
import java.util.List;

/* loaded from: classes2.dex */
public class DiscoverFragment extends BaseFragment {
    private int guidIndex;
    private ImageView img_guide_discover;
    private ViewGroup.MarginLayoutParams layoutParams;
    @DrawableRes
    private int[] guidImg = {R.drawable.img_guide_found1, R.drawable.img_guide_found2, R.drawable.img_guide_found3, R.drawable.img_guide_found4};
    private int[] marginValue = {55, 80, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 275};

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_main_discover_fragment;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        setOnClickListener(R.id.main_discover_navCircle);
        setOnClickListener(R.id.main_discover_navScan);
        setOnClickListener(R.id.main_discover_navDrawcross);
        setOnClickListener(R.id.main_discover_navDemand);
        setOnClickListener(R.id.main_discover_navService);
        setOnClickListener(R.id.main_discover_navShop);
        setOnClickListener(R.id.main_discover_navGame);
        setOnClickListener(R.id.main_discover_navShake);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        updateUI();
        if (GuideUtil.getDiscoverFragment(this.context)) {
            getViewAndClick(R.id.ll_guid).setVisibility(0);
            this.img_guide_discover = (ImageView) getViewAndClick(R.id.img_guide_discover);
            this.img_guide_discover.setVisibility(0);
            this.layoutParams = (ViewGroup.MarginLayoutParams) this.img_guide_discover.getLayoutParams();
            this.img_guide_discover.setImageResource(this.guidImg[0]);
        }
        try {
            String websJson = DemoHelper.getInstance().readConfig().getThird_web_datas();
            if (TextUtils.isEmpty(websJson)) {
                websJson = "{\"group\":\"\",\"items\":[{\"title\":\"积分商城\",\"link\":\"http://yunlianhui.cn\",\"icon\":\"icon_found_pointmall\"},{\"title\":\"全球交易中心\",\"link\":\"http://shop.qqjfjys.top/\",\"icon\":\"icon_found_tradecenter\"},{\"title\":\"帮你化债\",\"link\":\"http://hb.piduhui.com/\",\"icon\":\"icon_found_debt\"},{\"title\":\"共享房车\",\"link\":\"http://app.cnyhft.com/\",\"icon\":\"icon_found_room\"},{\"title\":\"乐返一条街\",\"link\":\"https://wap.vjkeji.com/dzpt/index.html\",\"icon\":\"icon_found_directory\"}]}";
            }
            ((MyListview) getView(R.id.main_discover_lvWebs)).setAdapter((ListAdapter) new WebsitesAdapter(this.context, ((WebSitesBean) new Gson().fromJson(websJson, (Class<Object>) WebSitesBean.class)).getItems()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.main_discover_navCircle /* 2131755380 */:
                startAct(CircleActivity.class);
                getView(R.id.iv_circles_dot).setVisibility(8);
                MainActivity.getInstance().hideCircleDot();
                return;
            case R.id.main_discover_navScan /* 2131755381 */:
                startAct(QrcodeActivity.class);
                return;
            case R.id.main_discover_navDemand /* 2131755382 */:
                startAct(NeedsActivity.class);
                return;
            case R.id.main_discover_navService /* 2131755383 */:
                startAct(Needs2Activity.class);
                return;
            case R.id.main_discover_navDrawcross /* 2131755384 */:
            case R.id.main_discover_lvWebs /* 2131755386 */:
            case R.id.ll_guid /* 2131755389 */:
            default:
                return;
            case R.id.main_discover_navGame /* 2131755385 */:
            case R.id.main_discover_navShake /* 2131755388 */:
                if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                    new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.fragment.DiscoverFragment.1
                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                        public void onResult(boolean confirmed, Bundle bundle2) {
                            if (confirmed) {
                                DiscoverFragment.this.startAct(BindPhoneActivity.class);
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
            case R.id.main_discover_navShop /* 2131755387 */:
                String url7 = DemoHelper.getInstance().readConfig().getWap_mall_url();
                if (TextUtils.isEmpty(url7)) {
                    url7 = "https://wap.vjkeji.com/mall/index.html";
                }
                bundle.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(this.context, url7));
                bundle.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
                startAct(WebKitLocalActivity.class, bundle);
                return;
            case R.id.img_guide_discover /* 2131755390 */:
                this.guidIndex++;
                if (this.guidIndex >= this.guidImg.length) {
                    getView(R.id.ll_guid).setVisibility(8);
                    this.img_guide_discover.setVisibility(8);
                    GuideUtil.setDiscoverFragment(this.context);
                    return;
                }
                this.layoutParams.setMargins(0, HyUtil.dip2px(getContext(), this.marginValue[this.guidIndex]), 0, 0);
                this.img_guide_discover.setLayoutParams(this.layoutParams);
                this.img_guide_discover.setImageResource(this.guidImg[this.guidIndex]);
                return;
        }
    }

    /* loaded from: classes2.dex */
    class WebsitesAdapter extends BaseAdapter<WebSitesBean.ItemsBean> {
        private List<WebSitesBean.ItemsBean> data;

        private WebsitesAdapter(Context context, List<WebSitesBean.ItemsBean> data) {
            super(context);
            this.data = data;
        }

        public void setData(List<WebSitesBean.ItemsBean> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.data.size();
        }

        @Override // android.widget.Adapter
        public WebSitesBean.ItemsBean getItem(int i) {
            return this.data.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_navview, (ViewGroup) null);
            }
            NavView nav = (NavView) view.findViewById(R.id.main_discover_navview);
            final WebSitesBean.ItemsBean webBean = getItem(i);
            nav.setText(webBean.getTitle());
            nav.setDraw(ComUtil.getImageResourceId(webBean.getIcon(), R.drawable.icon_found_pointmall));
            nav.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.fragment.DiscoverFragment.WebsitesAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Bundle bundle = new Bundle();
                    bundle.putString(com.hy.frame.util.Constant.FLAG, webBean.getLink());
                    bundle.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
                    DiscoverFragment.this.startAct(WebKitLocalActivity.class, bundle);
                }
            });
            return view;
        }
    }

    @Override // com.hy.frame.common.IFragmentListener
    public void sendMsg(int flag, Object obj) {
    }
}
