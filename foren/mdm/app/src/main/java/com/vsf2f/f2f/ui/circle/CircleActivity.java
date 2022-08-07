package com.vsf2f.f2f.ui.circle;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mobstat.StatService;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.PullToRefreshView;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.FlagUtil;
import com.em.DemoHelper;
import com.em.db.DemoDBManager;
import com.em.db.UserDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.MyGridView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.CircleContentAdapter;
import com.vsf2f.f2f.adapter.PicUrlPathAdapter;
import com.vsf2f.f2f.bean.CircleDraftBean;
import com.vsf2f.f2f.bean.CircleListBean;
import com.vsf2f.f2f.bean.CircleReadBean;
import com.vsf2f.f2f.bean.CircleReadHostBean;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.yolanda.nohttp.tools.NetUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class CircleActivity extends BaseActivity implements IAdapterListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {
    private CircleContentAdapter adapter;
    private String clickFlag;
    private List<CircleReadBean> datas;
    private CircleDraftBean draftBean;
    private String failType;
    private int flag;
    private int flagReq;
    private boolean isMessured;
    private PullToRefreshView main_pull_refresh_view;
    private ListView mlv_circle;
    private View publishView;
    private CircleReadBean readBean;
    private String searchKey;
    private int isSuccess = 0;
    private int PAGE_SIZE = 20;
    private int PAGE_INDEX = 0;
    private boolean isSearch = false;
    private boolean isRefresh = false;
    private String search = "";
    private Map<Integer, Long> lastTime = new HashMap();
    private Map<Integer, List<CircleReadBean>> map = new HashMap();
    private int operPosition = -1;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_circle_tab_activity;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        hideHeader();
        if (getBundle().getBoolean("add")) {
            startActForResult(CirclesAddActivity.class, 33);
        }
        this.publishView = View.inflate(this.context, R.layout.item_circles_hide_list, null);
        setOnClickListener(this.publishView, R.id.item_circles_draft);
        setOnClickListener(R.id.top_imgBack);
        setOnClickListener(R.id.top_imgAdd);
        setOnClickListener(R.id.radio_title_news);
        setOnClickListener(R.id.radio_title_friend);
        this.mlv_circle = (ListView) getView(R.id.mlv_circle);
        this.main_pull_refresh_view = (PullToRefreshView) getView(R.id.main_pull_refresh_view);
        this.main_pull_refresh_view.setOnHeaderRefreshListener(this);
        this.main_pull_refresh_view.setOnFooterRefreshListener(this);
        this.searchKey = getBundle().getString(Constant.SEARCH_STR);
        this.main_pull_refresh_view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.vsf2f.f2f.ui.circle.CircleActivity.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (!CircleActivity.this.isMessured) {
                    CircleActivity.this.mlv_circle.setLayoutParams(new LinearLayout.LayoutParams(-1, CircleActivity.this.main_pull_refresh_view.getMeasuredHeight()));
                    CircleActivity.this.isMessured = true;
                }
                return true;
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.datas = new ArrayList();
        this.draftBean = (CircleDraftBean) getBundle().getSerializable("publishdata");
        if (!TextUtils.isEmpty(this.searchKey)) {
            setSearch(this.searchKey);
        } else {
            getLocal(0);
        }
        this.mlv_circle.postDelayed(new Runnable() { // from class: com.vsf2f.f2f.ui.circle.CircleActivity.2
            @Override // java.lang.Runnable
            public void run() {
                CircleActivity.this.onNewIntent(CircleActivity.this.getIntent());
                CircleActivity.this.main_pull_refresh_view.headerRefreshing();
            }
        }, 500L);
    }

    public void getLocal(int flag) {
        String json = DemoDBManager.getInstance().getCircleJson(flag);
        if (!TextUtils.isEmpty(json)) {
            getCategoryData(flag, parseHaveHeaderJArray(json));
            updateUI();
        }
    }

    private void getCategoryData(int flag, List<CircleReadBean> localData) {
        this.map.put(Integer.valueOf(flag), localData);
        this.datas = localData;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        this.flagReq = this.flag;
        requestCircle();
    }

    public void requestAfterForward() {
        this.flagReq = 0;
        requestCircle();
    }

    public void requestCircle() {
        long last = getLastTime();
        if (last == Long.MAX_VALUE) {
            this.main_pull_refresh_view.onFooterRefreshComplete();
            Toast.makeText(this.context, "没有更多了", 0).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        String url = ComUtil.getF2FApi(this, getString(R.string.API_CIRCLES_MESSAGE, new Object[]{getType()})) + "?lastTime=" + last;
        if (this.isSearch) {
            url = url + "&searchKey=" + this.search;
            getClient().setShowDialog(R.string.searching);
            this.isSearch = false;
        }
        getClient().get(R.string.API_CIRCLES_MESSAGE, url, params, CircleListBean.class, false);
        if (this.isSuccess == 2) {
            this.isSuccess = 3;
        }
    }

    public String getType() {
        return this.flagReq == 0 ? "news" : UserDao.CONTACT_COLUMN_NAME_FRIEND_COUNT;
    }

    public long getLastTime() {
        if (this.PAGE_INDEX == 1 || this.lastTime == null || !this.lastTime.containsKey(Integer.valueOf(this.flagReq))) {
            return 0L;
        }
        return this.lastTime.get(Integer.valueOf(this.flagReq)).longValue();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.adapter == null) {
            this.adapter = new CircleContentAdapter(this, this.datas);
            this.adapter.setListener(this);
            this.mlv_circle.setAdapter((ListAdapter) this.adapter);
        } else {
            this.adapter.setDatas(this.datas);
        }
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        StatService.onPageEnd(this, "circle");
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.isSearch || this.isRefresh) {
            requestData();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        this.main_pull_refresh_view.onFooterRefreshComplete();
        this.main_pull_refresh_view.onHeaderRefreshComplete();
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLES_MESSAGE /* 2131296308 */:
                CircleListBean lists = (CircleListBean) result.getObj();
                if (lists == null) {
                    lists = new CircleListBean();
                }
                if (lists.getLastTime() != 0) {
                    this.lastTime.put(Integer.valueOf(this.flagReq), Long.valueOf(lists.getLastTime()));
                } else {
                    this.lastTime.put(Integer.valueOf(this.flagReq), Long.MAX_VALUE);
                }
                List<CircleReadBean> beans = lists.getInfos();
                if (this.PAGE_INDEX <= 1) {
                    if (HyUtil.isEmpty(beans)) {
                        beans = new ArrayList<>();
                        if (!this.search.equals("")) {
                            Toast.makeText(this.context, "没有查找到相关圈子", 1).show();
                        }
                        if (this.PAGE_INDEX <= 1) {
                            MyToast.show(this.context, "没有圈子消息", 1);
                        }
                    }
                    this.map.put(Integer.valueOf(this.flagReq), beans);
                } else {
                    this.map.get(Integer.valueOf(this.flagReq)).addAll(beans);
                }
                if (this.flag == this.flagReq) {
                    this.datas = this.map.get(Integer.valueOf(this.flag));
                    updateUI();
                }
                if (this.isSuccess == 3 || this.isSuccess == -1) {
                    this.isSuccess = 0;
                    this.mlv_circle.removeHeaderView(this.publishView);
                    this.adapter.notifyDataSetChanged();
                }
                if (HyUtil.isNoEmpty(this.datas) && this.PAGE_INDEX == 1 && this.search.equals("")) {
                    DemoDBManager.getInstance().saveCircleJson(this.flagReq, result.getObjStr());
                    break;
                }
                break;
            case R.string.API_CIRCLES_ZAN_SAVE /* 2131296311 */:
                if (!this.clickFlag.equals(FlagUtil.URL_SAVE)) {
                    if (this.clickFlag.equals(FlagUtil.URL_ZAN)) {
                        MyToast.show(this, getString(R.string.circle_zan));
                        this.readBean.setLike(true);
                        if (this.readBean.getMsgHost() != null) {
                            if (this.readBean.getMsgHost().getLike() == null) {
                                this.readBean.getMsgHost().setLike("1");
                                break;
                            } else {
                                this.readBean.getMsgHost().setLike((Integer.parseInt(this.readBean.getMsgHost().getLike()) + 1) + "");
                                break;
                            }
                        }
                    }
                } else {
                    MyToast.show(this, getString(R.string.circle_save));
                    this.readBean.setCollection(true);
                    if (this.readBean.getMsgHost() != null) {
                        if (this.readBean.getMsgHost().getCollection() == null) {
                            this.readBean.getMsgHost().setCollection("1");
                            break;
                        } else {
                            this.readBean.getMsgHost().setCollection((Integer.parseInt(this.readBean.getMsgHost().getCollection()) + 1) + "");
                            break;
                        }
                    }
                }
                break;
        }
        updateUI();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.main_pull_refresh_view.onFooterRefreshComplete();
        this.main_pull_refresh_view.onHeaderRefreshComplete();
    }

    @Override // com.hy.frame.common.BaseActivity, android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_title_news /* 2131755209 */:
                this.flag = 0;
                this.PAGE_INDEX = 1;
                this.datas = this.map.get(0);
                if (HyUtil.isEmpty(this.datas)) {
                    this.datas = new ArrayList();
                    getLocal(this.flag);
                    requestData();
                }
                updateUI();
                return;
            case R.id.radio_title_friend /* 2131755210 */:
                this.flag = 1;
                this.PAGE_INDEX = 1;
                this.datas = this.map.get(1);
                if (HyUtil.isEmpty(this.datas)) {
                    this.datas = new ArrayList();
                    getLocal(this.flag);
                    requestData();
                }
                updateUI();
                return;
            default:
                super.onClick(v);
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        char c = 65535;
        switch (view.getId()) {
            case R.id.top_imgBack /* 2131755207 */:
                finish();
                return;
            case R.id.top_imgAdd /* 2131755211 */:
                if (isNoLogin()) {
                    MyToast.show(this.context, (int) R.string.login_hint);
                    startAct(LoginActivity.class);
                    return;
                }
                startAct(CirclesAddActivity.class);
                return;
            case R.id.item_circles_draft /* 2131756745 */:
                if (this.isSuccess == -1) {
                    String str = this.failType;
                    switch (str.hashCode()) {
                        case 48:
                            if (str.equals("0")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 49:
                            if (str.equals("1")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 51:
                            if (str.equals("3")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 52:
                            if (str.equals("4")) {
                                c = 3;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            startAct(CirclesAddPicActivity.class);
                            return;
                        case 1:
                            startAct(CirclesAddTextActivity.class);
                            return;
                        case 2:
                            startAct(CirclesAddArtActivity.class);
                            return;
                        case 3:
                            startAct(CirclesAddVideoActivity.class);
                            return;
                        default:
                            return;
                    }
                } else {
                    MyToast.show(this.context, "正在发布");
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int id, Object o, int position) {
        this.operPosition = position;
        if (!NetUtil.isNetworkAvailable(this)) {
            MyToast.show(this, (int) R.string.no_network_to_try);
            return;
        }
        this.readBean = (CircleReadBean) o;
        switch (id) {
            case R.id.circles_i_ibtnReply /* 2131755257 */:
                this.clickFlag = "comment";
                toReply();
                return;
            case R.id.circles_i_ibtnShare /* 2131755259 */:
                if (this.datas != null) {
                    this.clickFlag = FlagUtil.URL_FORWARD;
                    toShare(true);
                    return;
                }
                return;
            case R.id.circles_i_ibtnZan /* 2131755261 */:
                zan_saveRequest(this.readBean.getMsgId(), FlagUtil.URL_ZAN);
                this.clickFlag = FlagUtil.URL_ZAN;
                return;
            case R.id.circles_i_ibtnSave /* 2131755264 */:
                zan_saveRequest(this.readBean.getMsgId(), FlagUtil.URL_SAVE);
                this.clickFlag = FlagUtil.URL_SAVE;
                return;
            case R.id.circle_i_llyShareDetail /* 2131756730 */:
                if (this.datas == null) {
                    return;
                }
                if (this.datas.get(position).getFwmi() == null || this.datas.get(position).getFwmi().getMsgId() == null) {
                    MyToast.show(this, getString(R.string.message_delete));
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(com.hy.frame.util.Constant.FLAG, this.datas.get(position).getFwmi().getMsgId());
                startActForResult(CircleDetailActivity.class, bundle, 999);
                return;
            default:
                Bundle bundle2 = new Bundle();
                bundle2.putString(com.hy.frame.util.Constant.FLAG, this.readBean.getMsgId());
                startActForResult(CircleDetailActivity.class, bundle2, 999);
                return;
        }
    }

    public void zan_saveRequest(String msgId, String zan_save) {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(false);
        getClient().post(R.string.API_CIRCLES_ZAN_SAVE, ComUtil.getF2FApi(this, getString(R.string.API_CIRCLES_ZAN_SAVE, new Object[]{zan_save, msgId})), params, String.class, false);
    }

    public void toReply() {
        toShare(false);
    }

    public void toShare(boolean isShare) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isShare", isShare);
        bundle.putParcelable("bean", this.readBean);
        startActForResult(ReplyActivity.class, bundle, 100);
    }

    public void setSearch(String str) {
        this.search = ComUtil.UTF(str);
        this.isSearch = true;
        setRefresh();
    }

    public void setRefresh() {
        if (this.context != null) {
            requestData();
        } else {
            this.isRefresh = true;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (!intent.hasExtra("publishState")) {
            Bundle bundle = intent.getBundleExtra(com.hy.frame.util.Constant.BUNDLE);
            if (bundle != null) {
                this.draftBean = (CircleDraftBean) bundle.getSerializable("publishdata");
                showDraftView();
                if (this.isSuccess < 1) {
                    this.isSuccess = 1;
                }
            }
        } else if (intent.getBooleanExtra("publishState", false)) {
            this.draftBean = null;
            resultSuccess();
        } else {
            this.failType = intent.getStringExtra("publishType");
            resultFail();
        }
    }

    private void resultSuccess() {
        if (this.context != null) {
            this.isSuccess = 2;
            this.main_pull_refresh_view.headerRefreshing();
        }
    }

    private void resultFail() {
        if (this.context != null) {
            this.isSuccess = -1;
        }
    }

    private void showHeaderBySdk(boolean show) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (this.mlv_circle.getHeaderViewsCount() > 0) {
                this.mlv_circle.removeHeaderView(this.publishView);
            }
            if (show) {
                this.mlv_circle.addHeaderView(this.publishView);
            }
        } else if (show) {
            this.publishView.setVisibility(0);
        } else {
            this.publishView.setVisibility(8);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    public void showDraftView() {
        if (this.draftBean == null) {
            showHeaderBySdk(false);
            return;
        }
        showHeaderBySdk(true);
        ViewDraft v = new ViewDraft();
        v.htxtTime = (TextView) getView(this.publishView, R.id.circles_h_txtTime);
        v.htxtName = (TextView) getView(this.publishView, R.id.circles_h_txtName);
        v.himgPlay = (ImageView) getView(this.publishView, R.id.circles_h_imgPlay);
        v.himgVideo = (ImageView) getView(this.publishView, R.id.circles_h_imgVideo);
        v.himgAvatar = (ImageView) getView(this.publishView, R.id.circles_h_imgAvatar);
        v.himgArtCover = (ImageView) getView(this.publishView, R.id.circles_h_imgArtCover);
        v.htxtContent = (TextView) getView(this.publishView, R.id.circles_h_txtContent);
        v.htxtDeviceName = (TextView) getView(this.publishView, R.id.circles_h_txtDeviceName);
        v.hgridPic = (MyGridView) getView(this.publishView, R.id.circles_h_gridPic);
        this.PAGE_INDEX = 1;
        requestData();
        UserInfo info = getUserInfo(true);
        v.htxtName.setText(TextUtils.isEmpty(info.getNickName()) ? info.getUserName() : info.getNickName());
        if (HyUtil.isEmpty(info.getUserPic().getSpath())) {
            v.himgAvatar.setImageResource(R.mipmap.def_head);
        } else {
            ComUtil.displayHead(this.context, v.himgAvatar, info.getUserPic().getSpath());
        }
        v.htxtTime.setText("刚刚");
        v.htxtContent.setText(this.draftBean.getContent());
        v.htxtDeviceName.setText("来自" + DemoHelper.getInstance().getDeviceModel());
        v.himgVideo.setVisibility(8);
        v.himgPlay.setVisibility(8);
        if (this.draftBean.getImgCover() != null) {
            v.hgridPic.setVisibility(8);
            v.himgArtCover.setVisibility(0);
            Glide.with((FragmentActivity) this).load(new File(this.draftBean.getImgCover())).error((int) R.drawable.img_empty).crossFade().into(v.himgArtCover);
        } else if (this.draftBean.getImgVideo() != null) {
            v.hgridPic.setVisibility(8);
            v.himgVideo.setVisibility(0);
            Glide.with((FragmentActivity) this).load(new File(this.draftBean.getImgVideo())).error((int) R.drawable.img_empty).crossFade().into(v.himgVideo);
            v.himgPlay.setVisibility(0);
        } else if (!HyUtil.isEmpty(this.draftBean.getPicList())) {
            v.hgridPic.setVisibility(0);
            v.himgArtCover.setVisibility(8);
            PicUrlPathAdapter cirAdapter = new PicUrlPathAdapter(this, this.draftBean.getPicList(), 3, R.dimen.margin_default, R.dimen.padding_normal);
            v.hgridPic.setAdapter((ListAdapter) cirAdapter);
            v.hgridPic.setClickable(false);
            v.hgridPic.setEnabled(false);
            cirAdapter.notifyDataSetChanged();
        } else {
            v.hgridPic.setVisibility(8);
            v.himgArtCover.setVisibility(8);
        }
    }

    @Override // com.cdlinglu.common.PullToRefreshView.OnFooterRefreshListener
    public void onFooterRefresh(PullToRefreshView view) {
        getClient().setShowDialog(false);
        this.PAGE_INDEX++;
        requestData();
    }

    @Override // com.cdlinglu.common.PullToRefreshView.OnHeaderRefreshListener
    public void onHeaderRefresh(PullToRefreshView view) {
        getClient().setShowDialog(false);
        this.PAGE_INDEX = 1;
        requestData();
    }

    /* loaded from: classes2.dex */
    public class ViewDraft {
        private MyGridView hgridPic;
        private ImageView himgArtCover;
        private ImageView himgAvatar;
        private ImageView himgPlay;
        private ImageView himgVideo;
        private TextView htxtContent;
        private TextView htxtDeviceName;
        private TextView htxtName;
        private TextView htxtTime;

        ViewDraft() {
            CircleActivity.this = this$0;
        }
    }

    private List<CircleReadBean> parseHaveHeaderJArray(String strByJson) {
        JsonArray jsonArray = new JsonParser().parse(strByJson).getAsJsonObject().getAsJsonArray("infos");
        Gson gson = new Gson();
        ArrayList<CircleReadBean> userBeanList = new ArrayList<>();
        Iterator<JsonElement> it = jsonArray.iterator();
        while (it.hasNext()) {
            userBeanList.add((CircleReadBean) gson.fromJson(it.next(), new TypeToken<CircleReadBean>() { // from class: com.vsf2f.f2f.ui.circle.CircleActivity.3
            }.getType()));
        }
        return userBeanList;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        CircleReadHostBean nums;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 33:
                    finish();
                    return;
                case 100:
                    if (this.clickFlag.equals("comment")) {
                        MyToast.show(this, getString(R.string.circle_comment));
                        if (this.readBean.getMsgHost() != null) {
                            if (this.readBean.getMsgHost().getComment() != null) {
                                this.readBean.getMsgHost().setComment((Integer.parseInt(this.readBean.getMsgHost().getComment()) + 1) + "");
                            } else {
                                this.readBean.getMsgHost().setComment("1");
                            }
                        }
                    } else if (this.clickFlag.equals(FlagUtil.URL_FORWARD)) {
                        MyToast.show(this, getString(R.string.circle_forward));
                        requestAfterForward();
                        if (this.readBean.getMsgHost() != null) {
                            if (this.readBean.getMsgHost().getForward() != null) {
                                this.readBean.getMsgHost().setForward((Integer.parseInt(this.readBean.getMsgHost().getForward()) + 1) + "");
                            } else {
                                this.readBean.getMsgHost().setForward("1");
                            }
                        }
                    }
                    updateUI();
                    return;
                case 999:
                    Bundle bundle = data.getBundleExtra("bundle");
                    if (bundle != null && (nums = (CircleReadHostBean) bundle.getParcelable("item_num")) != null) {
                        try {
                            this.datas.get(this.operPosition).setMsgHost(nums);
                            this.datas.get(this.operPosition).setLike(bundle.getBoolean("is_like"));
                            updateUI();
                            return;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            return;
                        }
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }
}
