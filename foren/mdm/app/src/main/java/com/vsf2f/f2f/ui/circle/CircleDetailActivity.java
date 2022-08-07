package com.vsf2f.f2f.ui.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.FlagUtil;
import com.cdlinglu.utils.ShareUtils;
import com.easeui.ui.EaseShowVideoActivity;
import com.em.DemoHelper;
import com.em.db.UserDao;
import com.em.ui.EditActivity;
import com.em.ui.UserProfileActivity;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.frame.view.recycler.xRefreshView.XRefreshView;
import com.hy.http.AjaxParams;
import com.hy.http.HttpEntity;
import com.hyphenate.chat.MessageEncoder;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.CircleCommentAdapter;
import com.vsf2f.f2f.adapter.PicCircleReadAdapter;
import com.vsf2f.f2f.bean.CircleCommentBean;
import com.vsf2f.f2f.bean.CircleCommentsBean;
import com.vsf2f.f2f.bean.CircleExtInfo;
import com.vsf2f.f2f.bean.CircleReadBean;
import com.vsf2f.f2f.bean.CircleReadPicListBean;
import com.vsf2f.f2f.ui.dialog.EditEnterDialog;
import com.vsf2f.f2f.ui.dialog.MoreOperateDialog;
import com.vsf2f.f2f.ui.dialog.ShareForwardDialog;
import com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import com.vsf2f.f2f.ui.report.ReportMainActivity;
import com.vsf2f.f2f.ui.user.EnlargeActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import com.vsf2f.f2f.ui.view.MyListview;
import com.vsf2f.f2f.ui.view.SpannText;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class CircleDetailActivity extends BaseActivity implements IAdapterListener, XRefreshViewListener, CircleCommentAdapter.ItemClick {
    private CircleCommentAdapter adapter;
    private CircleReadBean circleBean;
    private FrameLayout flyPic;
    private GridView gridPic;
    private ImageView imgArtCover;
    private ImageView imgAvatar;
    private ImageView imgCircle;
    private ImageView imgCollect;
    private ImageView imgLike;
    private ImageView imgShareCover;
    private ImageView imgVip;
    private long lastTime;
    private List<CircleReadPicListBean> listPic;
    private LinearLayout llyArtWeb;
    private LinearLayout llyExt;
    private LinearLayout llyForward;
    private LinearLayout llyShareExt;
    private MyListview lvComment;
    private String msgId;
    private ShareForwardDialog shareDialog;
    private ShareUtils shareUtils;
    private TextView txtCollect;
    private TextView txtContent;
    private TextView txtDeviceName;
    private TextView txtLike;
    private TextView txtName;
    private TextView txtNoComment;
    private TextView txtReply;
    private TextView txtShare;
    private TextView txtShareContent;
    private TextView txtTime;
    private WebView webView;
    private XRefreshView xrv_circle;
    private List<CircleCommentBean> listComment = new ArrayList();
    private int PAGE_INDEX = 1;
    private boolean isChange = false;
    private String clickflag = "";

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_circles_detail;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.circle_text, R.drawable.icon_menu_more);
        this.shareUtils = ShareUtils.get(this);
        if (getBundle().getString(Constant.FLAG) == null) {
            finish();
        } else {
            this.msgId = getBundle().getString(Constant.FLAG);
        }
        this.imgLike = (ImageView) getView(R.id.img_Like);
        this.txtLike = (TextView) getView(R.id.txt_Like);
        this.txtReply = (TextView) getView(R.id.txt_Reply);
        this.txtShare = (TextView) getView(R.id.txt_Share);
        this.imgCollect = (ImageView) getView(R.id.img_collection);
        this.txtCollect = (TextView) getView(R.id.txt_collection);
        setOnClickListener(R.id.circles_i_ibtnReply);
        setOnClickListener(R.id.circles_i_ibtnSave);
        setOnClickListener(R.id.circles_i_ibtnZan);
        setOnClickListener(R.id.circles_i_ibtnShare);
        setOnClickListener(R.id.circles_detail_rlyHead);
        this.txtName = (TextView) getView(R.id.circles_detail_txtName);
        this.txtTime = (TextView) getView(R.id.circles_detail_txtTime);
        this.imgVip = (ImageView) getView(R.id.circles_detail_imgVip);
        this.imgAvatar = (ImageView) getView(R.id.circles_detail_imgAvatar);
        this.txtContent = (TextView) getView(R.id.circles_detail_txtContent);
        this.txtDeviceName = (TextView) getView(R.id.circles_detail_txtDeviceName);
        this.llyArtWeb = (LinearLayout) getView(R.id.circle_detail_llyArtWeb);
        this.imgShareCover = (ImageView) getView(R.id.circles_detail_share_imgCover);
        this.txtShareContent = (TextView) getView(R.id.circles_detail_share_txtContent);
        this.webView = (WebView) getView(R.id.circles_detail_webArtContent);
        this.lvComment = (MyListview) getView(R.id.circle_detail_lvComment);
        this.gridPic = (GridView) getView(R.id.circle_detail_gridPic);
        this.flyPic = (FrameLayout) getView(R.id.circles_detail_flyPic);
        this.txtNoComment = (TextView) getViewAndClick(R.id.circle_detail_txtNoComment);
        this.imgCircle = (ImageView) getViewAndClick(R.id.circles_detail_imgAlone);
        this.imgArtCover = (ImageView) getViewAndClick(R.id.circle_detail_imgArtCover);
        this.llyForward = (LinearLayout) getViewAndClick(R.id.circle_detail_llyForward);
        this.llyShareExt = (LinearLayout) getViewAndClick(R.id.circle_detail_llyShareExt);
        this.llyExt = (LinearLayout) getViewAndClick(R.id.circle_detail_llyExt);
        this.xrv_circle = (XRefreshView) getView(R.id.xrv_parent);
        this.xrv_circle.setPullRefreshEnable(true);
        this.xrv_circle.setAutoRefresh(false);
        this.xrv_circle.setXRefreshViewListener(this);
        requestDetail();
        refreshComment();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    private void requestDetail() {
        getClient().setShowDialog(!this.clickflag.equals("comment"));
        getClient().get(R.string.API_CIRCLES_READ, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_READ, new Object[]{this.msgId})), CircleReadBean.class);
    }

    public void requestCommentList(long lastTime) {
        if (lastTime == Long.MAX_VALUE) {
            this.xrv_circle.setLoadComplete(true);
        } else {
            getClient().get(R.string.API_LOAD_COMMENT, ComUtil.getF2FApi(this.context, getString(R.string.API_LOAD_COMMENT, new Object[]{this.msgId}) + "?lastTime=" + lastTime), null, CircleCommentsBean.class, false);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    private void initUI() {
        String content;
        if (this.circleBean.getMsgHost() != null) {
            this.txtLike.setText(this.circleBean.getMsgHost().getLike() != null ? this.circleBean.getMsgHost().getLike() : "0");
            this.txtShare.setText(this.circleBean.getMsgHost().getForward() != null ? this.circleBean.getMsgHost().getForward() : "0");
            this.txtReply.setText(this.circleBean.getMsgHost().getComment() != null ? this.circleBean.getMsgHost().getComment() : "0");
            this.txtCollect.setText(this.circleBean.getMsgHost().getCollection() != null ? this.circleBean.getMsgHost().getCollection() : "0");
        }
        this.imgCollect.setSelected(this.circleBean.isCollection());
        this.imgLike.setSelected(this.circleBean.isLike());
        if (this.circleBean.getUserPic() == null || this.circleBean.getUserPic().getSpath() == null) {
            this.imgAvatar.setImageResource(R.mipmap.def_head);
        } else {
            ComUtil.displayImage(this.context, this.imgAvatar, this.circleBean.getUserPic().getSpath());
        }
        if (this.circleBean.getNickName() != null) {
            this.txtName.setText(this.circleBean.getNickName());
        }
        if (this.circleBean.getTime() != 0) {
            this.txtTime.setText(HyUtil.getDateTime(this.circleBean.getTime(), "yyyy-MM-dd HH:mm"));
        }
        if (this.circleBean.getUdName() != null) {
            this.txtDeviceName.setText("来自" + this.circleBean.getUdName());
        }
        if ("4".equals(this.circleBean.getPublisher().getLv())) {
            this.imgVip.setVisibility(0);
        } else {
            this.imgVip.setVisibility(8);
        }
        this.llyArtWeb.setVisibility(8);
        this.llyExt.removeAllViews();
        this.llyShareExt.removeAllViews();
        this.llyForward.setVisibility(8);
        this.flyPic.setVisibility(8);
        switch (this.circleBean.getType()) {
            case 0:
                if (HyUtil.isNoEmpty(this.circleBean.getPicList())) {
                    this.flyPic.setVisibility(0);
                    if (this.circleBean.getPicList().size() != 1) {
                        this.gridPic.setVisibility(0);
                        this.imgCircle.setVisibility(8);
                        PicCircleReadAdapter cirAdapter = new PicCircleReadAdapter(this.context, this.listPic, 3, this.context.getResources().getDisplayMetrics().widthPixels - HyUtil.dip2px(this.context, 52.0f));
                        cirAdapter.setListener(this);
                        this.gridPic.setAdapter((ListAdapter) cirAdapter);
                        break;
                    } else {
                        this.gridPic.setVisibility(8);
                        this.imgCircle.setVisibility(0);
                        ComUtil.displayImage(this.context, this.imgCircle, this.circleBean.getPicList().get(0).getSpath());
                        break;
                    }
                }
                break;
            case 2:
                this.llyForward.setVisibility(0);
                if (this.circleBean.getFwmi() != null && this.circleBean.getFwmi().getMsgId() != null) {
                    if (TextUtils.isEmpty(this.circleBean.getFwmi().getCoverPic().getSpath())) {
                        this.imgShareCover.setVisibility(8);
                    } else {
                        this.imgShareCover.setVisibility(0);
                        ComUtil.displayImage(this.context, this.imgShareCover, this.circleBean.getFwmi().getCoverPic().getSpath());
                    }
                    if (this.circleBean.getFwmi().getNickName() != null) {
                        if (this.circleBean.getFwmi().getType() == 3) {
                            content = "《 " + this.circleBean.getFwmi().getTitle() + " 》";
                        } else {
                            content = this.circleBean.getFwmi().getContent().replace("<br>", "\n");
                        }
                        String nickname = this.circleBean.getFwmi().getNickName();
                        SpannableStringBuilder fontStyleBuilder = new SpannableStringBuilder("@" + nickname + "：" + content);
                        fontStyleBuilder.setSpan(new ForegroundColorSpan(-16776961), 0, nickname.length() + 2, 33);
                        this.txtShareContent.setText(fontStyleBuilder);
                    } else {
                        this.txtShareContent.setText(this.circleBean.getFwmi().getContent().replace("<br>", "\n"));
                    }
                    if (this.circleBean.getFwmi().getType() == 4 && this.circleBean.getFwmi().getExtInfo() != null) {
                        addExtView(this.llyShareExt, this.circleBean.getFwmi().getExtInfo(), this.circleBean.getFwmi().getMsgId());
                        break;
                    }
                } else {
                    this.imgShareCover.setVisibility(8);
                    this.txtShareContent.setText(R.string.message_delete);
                    break;
                }
                break;
            case 3:
                this.flyPic.setVisibility(0);
                this.llyArtWeb.setVisibility(0);
                this.imgArtCover.setVisibility(0);
                this.gridPic.setVisibility(8);
                int width = getResources().getDisplayMetrics().widthPixels;
                this.imgArtCover.getLayoutParams().width = width;
                this.imgArtCover.getLayoutParams().height = width / 2;
                ComUtil.displayImage(this.context, this.imgArtCover, this.circleBean.getCoverPic().getSpath());
                if (this.circleBean.getContent() != null) {
                    this.webView.loadDataWithBaseURL(null, this.circleBean.getContent(), HttpEntity.TEXT_HTML, "UTF-8", null);
                    this.webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                    break;
                }
                break;
            case 4:
                if (this.circleBean.getExtInfo() != null) {
                    addExtView(this.llyExt, this.circleBean.getExtInfo(), this.circleBean.getMsgId());
                    break;
                }
                break;
        }
        if (this.circleBean.getContent() == null || this.circleBean.getType() == 3) {
            this.txtContent.setVisibility(8);
            return;
        }
        this.txtContent.setVisibility(0);
        SpannText.getSpanText(this.txtContent, this.circleBean.getContent().replace("<br>", "\n"));
    }

    public void initComment() {
        if (this.adapter == null) {
            this.adapter = new CircleCommentAdapter(this.context, this.listComment, this);
            this.lvComment.setDividerHeight(0);
            this.lvComment.setAdapter((ListAdapter) this.adapter);
            return;
        }
        this.adapter.refresh(this.listComment);
    }

    private void addExtView(LinearLayout parent, final CircleExtInfo ext, final String msgId) {
        View view = null;
        String type = ext.getType();
        char c = 65535;
        switch (type.hashCode()) {
            case -1335432629:
                if (type.equals("demand")) {
                    c = 1;
                    break;
                }
                break;
            case -1003761308:
                if (type.equals(com.vsf2f.f2f.ui.utils.Constant.PRODUCTS_BUCKET)) {
                    c = 4;
                    break;
                }
                break;
            case 3529462:
                if (type.equals("shop")) {
                    c = 3;
                    break;
                }
                break;
            case 112202875:
                if (type.equals("video")) {
                    c = 0;
                    break;
                }
                break;
            case 1984153269:
                if (type.equals(NotificationCompat.CATEGORY_SERVICE)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                view = inflate(R.layout.item_circle_list_video);
                getView(view, R.id.item_circle_ext_video).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.circle.CircleDetailActivity.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Intent intent = new Intent(CircleDetailActivity.this.context, EaseShowVideoActivity.class);
                        intent.putExtra(UserDao.IMAGE_COLUMN_NAME_LOCALPATH, FolderUtil.getCachePathVideo() + File.separator + msgId + ".mp4");
                        intent.putExtra("secret", "");
                        intent.putExtra("remotepath", ext.getVideoUrl());
                        CircleDetailActivity.this.context.startActivity(intent);
                    }
                });
                ComUtil.display(this.context, (ImageView) getView(view, R.id.item_circle_video_pic), ext.getPicUrl(), R.drawable.bgm_circle_item_goods);
                break;
            case 1:
                view = inflate(R.layout.item_circle_list_demand);
                getView(view, R.id.item_circle_ext_demand).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.circle.CircleDetailActivity.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", Integer.parseInt(ext.getBizId()));
                        CircleDetailActivity.this.startAct(DemandInfoActivity.class, bundle);
                    }
                });
            case 2:
                if (ext.getType().equals(NotificationCompat.CATEGORY_SERVICE)) {
                    view = inflate(R.layout.item_circle_list_service);
                    getView(view, R.id.item_circle_ext_service).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.circle.CircleDetailActivity.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", Integer.parseInt(ext.getBizId()));
                            CircleDetailActivity.this.startAct(ServiceInfoActivity.class, bundle);
                        }
                    });
                }
                ((TextView) getView(view, R.id.item_circle_needs_title)).setText(ext.getTitle());
                ((TextView) getView(view, R.id.item_circle_needs_detail)).setText(ext.getContent());
                ((TextView) getView(view, R.id.item_circle_needs_price)).setText(ext.getPrice() + "元");
                ((TextView) getView(view, R.id.item_circle_needs_unit)).setText("/" + ext.getUnit());
                ((TextView) getView(view, R.id.item_circle_needs_mode)).setText(ext.getTypeName());
                ImageView pic2 = (ImageView) getView(view, R.id.item_circle_needs_pic);
                if (HyUtil.isNoEmpty(ext.getPic())) {
                    ComUtil.displayImage(this.context, pic2, ext.getPic().get(0));
                    break;
                }
                break;
            case 3:
                view = inflate(R.layout.item_circle_list_shop);
                getView(view, R.id.item_circle_ext_shop).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.circle.CircleDetailActivity.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Bundle bundle = new Bundle();
                        bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(CircleDetailActivity.this.context, "/m/shop/" + ext.getUserName() + ".mobile"));
                        bundle.putBoolean(Constant.FLAG2, true);
                        CircleDetailActivity.this.startAct(WebKitLocalActivity.class, bundle);
                    }
                });
                ((TextView) getView(view, R.id.item_circle_shop_title)).setText(ext.getStoreName());
                if (HyUtil.isNoEmpty(ext.getSales())) {
                    ((TextView) getView(view, R.id.item_circle_shop_sale)).setText("销售" + ext.getSales() + "件");
                }
                if (HyUtil.isNoEmpty(ext.getInSale())) {
                    ((TextView) getView(view, R.id.item_circle_shop_sold)).setText("出售中" + ext.getInSale() + "件");
                }
                if (HyUtil.isNoEmpty(ext.getCollection())) {
                    ((TextView) getView(view, R.id.item_circle_shop_collect)).setText(ext.getCollection() + "人收藏");
                }
                ComUtil.displayImage(this.context, (ImageView) getView(view, R.id.item_circle_shop_pic), ext.getLogo());
                break;
            case 4:
                view = inflate(R.layout.item_circle_list_goods);
                getView(view, R.id.item_circle_ext_goods).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.circle.CircleDetailActivity.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        String url = ComUtil.getZCApi(CircleDetailActivity.this.context, "/m/mall/details.mobile?isFromAppToWap=true&uuid=" + ext.getGuid());
                        Bundle bundlePre = new Bundle();
                        bundlePre.putString(Constant.FLAG, WebUtils.getTokenUrl(CircleDetailActivity.this.context, url));
                        CircleDetailActivity.this.startAct(WebKitLocalActivity.class, bundlePre);
                    }
                });
                ((TextView) getView(view, R.id.item_circle_goods_title)).setText(ext.getGoodsName());
                ((TextView) getView(view, R.id.item_circle_goods_price)).setText(ext.getPrice() + "");
                ImageView pic = (ImageView) getView(view, R.id.item_circle_ext_pic);
                if (HyUtil.isNoEmpty(ext.getPic())) {
                    ComUtil.displayImage(this.context, pic, ext.getPic().get(0));
                    break;
                }
                break;
        }
        if (view != null) {
            parent.addView(view);
        }
    }

    protected View inflate(int resId) {
        return LayoutInflater.from(this.context).inflate(resId, (ViewGroup) null);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        String comment;
        String like;
        this.xrv_circle.stopRefresh();
        this.xrv_circle.stopLoadMore();
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLES_COMMENT_FORWARD /* 2131296307 */:
                MyToast.show(this.context, "回复成功");
                refreshComment();
                if (this.circleBean.getMsgHost() != null) {
                    if (this.circleBean.getMsgHost().getComment() != null) {
                        comment = String.valueOf(Integer.parseInt(this.circleBean.getMsgHost().getComment()) + 1);
                    } else {
                        comment = "1";
                    }
                    this.circleBean.getMsgHost().setComment(comment);
                    this.txtReply.setText(comment);
                }
                this.isChange = true;
                return;
            case R.string.API_CIRCLES_READ /* 2131296310 */:
                this.circleBean = (CircleReadBean) result.getObj();
                if (this.circleBean != null) {
                    if (this.circleBean.getPicList() != null) {
                        this.listPic = this.circleBean.getPicList();
                    }
                    this.msgId = this.circleBean.getMsgId();
                    initUI();
                    return;
                }
                MyToast.show(this, "原消息已被删除");
                this.txtContent.setText("原消息已被删除");
                return;
            case R.string.API_CIRCLES_ZAN_SAVE /* 2131296311 */:
                if (this.clickflag.equals(FlagUtil.URL_SAVE)) {
                    MyToast.show(this.context, "收藏成功");
                    this.imgCollect.setSelected(true);
                    if (this.circleBean.getMsgHost() != null) {
                        if (this.circleBean.getMsgHost().getCollection() != null) {
                            int collect = Integer.parseInt(this.circleBean.getMsgHost().getCollection()) + 1;
                            this.circleBean.getMsgHost().setCollection(String.valueOf(collect));
                            this.circleBean.setCollection(true);
                            this.txtCollect.setText(collect + "");
                        } else {
                            this.txtCollect.setText("1");
                        }
                    }
                } else if (this.clickflag.equals(FlagUtil.URL_ZAN)) {
                    MyToast.show(this.context, "点赞成功");
                    this.imgLike.setSelected(true);
                    if (this.circleBean.getMsgHost() != null) {
                        if (this.circleBean.getMsgHost().getLike() != null) {
                            like = String.valueOf(Integer.parseInt(this.circleBean.getMsgHost().getLike()) + 1);
                        } else {
                            like = "1";
                        }
                        this.circleBean.getMsgHost().setLike(like);
                        this.circleBean.setLike(true);
                        this.txtLike.setText(like);
                    }
                }
                this.isChange = true;
                return;
            case R.string.API_LOAD_COMMENT /* 2131296369 */:
                CircleCommentsBean lists = (CircleCommentsBean) result.getObj();
                if (lists == null) {
                    lists = new CircleCommentsBean();
                }
                List<CircleCommentBean> beans = lists.getInfos();
                long last = lists.getLastTime();
                if (last == 0) {
                    this.lastTime = Long.MAX_VALUE;
                } else {
                    this.lastTime = last;
                }
                if (HyUtil.isNoEmpty(beans)) {
                    if (this.PAGE_INDEX <= 1) {
                        this.xrv_circle.setPullLoadEnable(true);
                        this.listComment.clear();
                    }
                    this.listComment.addAll(beans);
                    this.txtNoComment.setVisibility(8);
                } else {
                    if (this.PAGE_INDEX > 1) {
                        this.txtNoComment.setText(R.string.no_more);
                    }
                    this.txtNoComment.setVisibility(0);
                }
                initComment();
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        this.xrv_circle.stopRefresh();
        this.xrv_circle.stopLoadMore();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        if (this.circleBean != null) {
            switch (view.getId()) {
                case R.id.circle_detail_imgArtCover /* 2131755237 */:
                    if (this.circleBean.getCoverPic() == null || this.circleBean.getCoverPic().getPath() == null) {
                        MyToast.show(this, getString(R.string.pic_big));
                    } else {
                        bundle.putString(Constant.FLAG, this.circleBean.getCoverPic().getPath());
                    }
                    startAct(EnlargeActivity.class, bundle);
                    overridePendingTransition(R.anim.intentsmall, R.anim.intentbig);
                    return;
                case R.id.circles_detail_rlyHead /* 2131755238 */:
                    bundle.putSerializable("username", this.circleBean.getUserName());
                    startAct(UserProfileActivity.class, bundle);
                    return;
                case R.id.circles_detail_imgAlone /* 2131755246 */:
                    if (this.circleBean.getPicList() == null || this.circleBean.getPicList().get(0).getPath() == null) {
                        MyToast.show(this, getString(R.string.pic_big));
                    } else {
                        bundle.putString(Constant.FLAG, this.circleBean.getPicList().get(0).getPath());
                    }
                    startAct(EnlargeActivity.class, bundle);
                    overridePendingTransition(R.anim.intentsmall, R.anim.intentbig);
                    return;
                case R.id.circle_detail_llyForward /* 2131755250 */:
                    if (this.circleBean.getFwmi() == null || this.circleBean.getFwmi().getMsgId() == null) {
                        MyToast.show(this.context, getString(R.string.message_delete));
                        return;
                    }
                    bundle.putString(Constant.FLAG, this.circleBean.getFwmi().getMsgId());
                    startActForResult(CircleDetailActivity.class, bundle, 999);
                    return;
                case R.id.circle_detail_txtNoComment /* 2131755256 */:
                case R.id.circles_i_ibtnReply /* 2131755257 */:
                    this.clickflag = "comment";
                    toReply();
                    return;
                case R.id.circles_i_ibtnShare /* 2131755259 */:
                    showForwardDialog();
                    this.clickflag = FlagUtil.URL_FORWARD;
                    return;
                case R.id.circles_i_ibtnZan /* 2131755261 */:
                    zan_saveRequest(FlagUtil.URL_ZAN);
                    this.clickflag = FlagUtil.URL_ZAN;
                    return;
                case R.id.circles_i_ibtnSave /* 2131755264 */:
                    zan_saveRequest(FlagUtil.URL_SAVE);
                    this.clickflag = FlagUtil.URL_SAVE;
                    return;
                default:
                    return;
            }
        }
    }

    private void showForwardDialog() {
        if (this.circleBean != null) {
            if (this.shareDialog == null) {
                this.shareDialog = new ShareForwardDialog(this.context);
                this.shareDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.circle.CircleDetailActivity.6
                    @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                    public void onDlgConfirm(BaseDialog dlg, int flag) {
                        if (CircleDetailActivity.this.circleBean.getShareThird() != null) {
                            CircleDetailActivity.this.shareUtils.setShare(CircleDetailActivity.this.circleBean.getShareThird());
                        }
                        switch (flag) {
                            case 1:
                                CircleDetailActivity.this.toShare(true);
                                return;
                            case 2:
                                ComUtil.copySys(CircleDetailActivity.this.context, CircleDetailActivity.this.shareUtils.getShare().getUrl());
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
                                CircleDetailActivity.this.shareUtils.shareTask(1);
                                return;
                            case 12:
                                CircleDetailActivity.this.shareUtils.shareTask(0);
                                return;
                            case 13:
                                CircleDetailActivity.this.shareUtils.qqShare();
                                return;
                            case 14:
                                CircleDetailActivity.this.shareUtils.qqZoneShare();
                                return;
                        }
                    }
                });
            }
            this.shareDialog.show();
        }
    }

    public void zan_saveRequest(String zan_save) {
        getClient().post(R.string.API_CIRCLES_ZAN_SAVE, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_ZAN_SAVE, new Object[]{zan_save, this.msgId})));
    }

    public void showEditDialog(final String msgId) {
        new EditEnterDialog(this.context, "请输入回复内容", new EditEnterDialog.setOnEditSendClickListener() { // from class: com.vsf2f.f2f.ui.circle.CircleDetailActivity.7
            @Override // com.vsf2f.f2f.ui.dialog.EditEnterDialog.setOnEditSendClickListener
            public void onEditSendClickListener(String s) {
                CircleDetailActivity.this.replyComment(s, msgId);
            }
        }).show();
    }

    public void replyComment(String content, String replyMsgId) {
        AjaxParams params = new AjaxParams();
        params.put(EditActivity.CONTENT, content);
        params.put(MessageEncoder.ATTR_ADDRESS, "");
        params.put("lgt", "");
        params.put("lat", "");
        params.put("udid", DemoHelper.getInstance().getDeviceUni());
        params.put("udName", DemoHelper.getInstance().getDeviceModel());
        getClient().post(R.string.API_CIRCLES_COMMENT_FORWARD, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_COMMENT_FORWARD, new Object[]{"comment", this.msgId, replyMsgId})), params, String.class);
    }

    public void toReply() {
        toShare(false);
    }

    public void toShare(boolean isShare) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isShare", isShare);
        bundle.putParcelable("bean", this.circleBean);
        startActForResult(ReplyActivity.class, bundle, 100);
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int id, Object o, int position) {
        ArrayList<CircleReadPicListBean> list = (ArrayList) this.circleBean.getPicList();
        Bundle bundle = new Bundle();
        if (this.circleBean.getPicList() != null) {
            bundle.putParcelableArrayList(Constant.FLAG, list);
            bundle.putInt(Constant.FLAG_ID, position);
        } else {
            MyToast.show(this, getString(R.string.pic_big));
        }
        startAct(EnlargeActivity.class, bundle);
        overridePendingTransition(R.anim.intentsmall, R.anim.intentbig);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        if (this.isChange) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("item_num", this.circleBean.getMsgHost());
            bundle.putBoolean("is_like", this.circleBean.isLike());
            intent.putExtra("bundle", bundle);
            setResult(-1, intent);
            super.onLeftClick();
        }
        super.onLeftClick();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        onLeftClick();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        MoreOperateDialog dialog = new MoreOperateDialog(this.context);
        dialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.circle.CircleDetailActivity.8
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                CircleDetailActivity.this.operateType();
            }
        });
        dialog.showBtnReport();
        dialog.show();
    }

    public void operateType() {
        Bundle bundle = new Bundle();
        bundle.putInt("objType", 0);
        bundle.putString("objId", this.circleBean.getMsgId() + "");
        bundle.putString("reportedUser", this.circleBean.getUserName());
        startAct(ReportMainActivity.class, bundle);
    }

    @Override // com.vsf2f.f2f.adapter.CircleCommentAdapter.ItemClick
    public void clickComment(String msgId) {
        showEditDialog(msgId);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String forward;
        String comment;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == -1) {
            if (this.clickflag.equals("comment")) {
                refreshComment();
                if (this.circleBean.getMsgHost() != null) {
                    if (this.circleBean.getMsgHost().getComment() != null) {
                        comment = String.valueOf(Integer.parseInt(this.circleBean.getMsgHost().getComment()) + 1);
                    } else {
                        comment = "1";
                    }
                    this.circleBean.getMsgHost().setComment(comment);
                    this.txtReply.setText(comment);
                }
            } else if (this.clickflag.equals(FlagUtil.URL_FORWARD) && this.circleBean.getMsgHost() != null) {
                if (this.circleBean.getMsgHost().getForward() != null) {
                    forward = String.valueOf(Integer.parseInt(this.circleBean.getMsgHost().getForward()) + 1);
                } else {
                    forward = "1";
                }
                this.circleBean.getMsgHost().setForward(forward);
                this.txtShare.setText(forward);
            }
            this.isChange = true;
        }
    }

    public void refreshComment() {
        this.PAGE_INDEX = 1;
        requestCommentList(0L);
        this.xrv_circle.setLoadComplete(false);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        requestDetail();
        refreshComment();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
        this.PAGE_INDEX++;
        requestCommentList(this.lastTime);
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }
}
