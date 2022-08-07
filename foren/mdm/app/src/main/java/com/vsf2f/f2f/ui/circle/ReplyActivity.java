package com.vsf2f.f2f.ui.circle;

import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.FlagUtil;
import com.easeui.ui.EaseShowVideoActivity;
import com.em.DemoHelper;
import com.em.db.UserDao;
import com.em.ui.EditActivity;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.MessageEncoder;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.CircleBaseBean;
import com.vsf2f.f2f.bean.CircleExtInfo;
import com.vsf2f.f2f.bean.CircleReadBean;
import com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import java.io.File;

/* loaded from: classes2.dex */
public class ReplyActivity extends BaseActivity {
    private CircleReadBean circleBean;
    private EditText et_reply;
    private boolean isShare;
    private String prevMsgId;
    private TextView txt_sameTime;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_circle_reply;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        CircleBaseBean circle;
        String content;
        initHeaderBackTxt(R.string.comment, R.string.button_send);
        Bundle bundle = getBundle();
        try {
            this.isShare = bundle.getBoolean("isShare");
            this.circleBean = (CircleReadBean) bundle.getParcelable("bean");
            LinearLayout lly_shareContent = (LinearLayout) getView(R.id.lly_shareContent);
            TextView txt_shareContent = (TextView) getView(R.id.txt_shareContent);
            ImageView img_sharePic = (ImageView) getView(R.id.img_sharePic);
            this.txt_sameTime = (TextView) getViewAndClick(R.id.txt_sameTime);
            this.et_reply = (EditText) getView(R.id.et_reply);
            this.et_reply.requestFocus();
            if (this.isShare) {
                lly_shareContent.setVisibility(0);
                setTitle(R.string.forward);
                if (this.circleBean.getFwmi() == null || this.circleBean.getFwmi().getMsgId() == null) {
                    circle = this.circleBean;
                } else {
                    circle = this.circleBean.getFwmi();
                }
                if (circle.getType() == 4) {
                    LinearLayout lly_shareExt = (LinearLayout) getView(R.id.lly_shareExt);
                    if (circle.getExtInfo() != null) {
                        addExtView(lly_shareExt, circle.getExtInfo(), circle.getMsgId());
                    }
                    content = circle.getContent();
                } else if (circle.getType() == 3) {
                    content = "《 " + circle.getTitle() + " 》";
                    String pic = circle.getCoverPic().getPath();
                    if (!TextUtils.isEmpty(pic)) {
                        img_sharePic.setVisibility(0);
                        Glide.with((FragmentActivity) this).load(pic).into(img_sharePic);
                    }
                } else {
                    if (HyUtil.isNoEmpty(this.circleBean.getPicList())) {
                        String pic2 = this.circleBean.getPicList().get(0).getMpath();
                        if (!TextUtils.isEmpty(pic2)) {
                            img_sharePic.setVisibility(0);
                            Glide.with((FragmentActivity) this).load(pic2).into(img_sharePic);
                        }
                    }
                    content = circle.getContent();
                }
                txt_shareContent.setText(content.replace("<br>", "\n"));
                this.prevMsgId = circle.getMsgId();
                this.txt_sameTime.setText("同时评论");
                this.et_reply.setHint("说说的你的心得……");
                return;
            }
            this.prevMsgId = this.circleBean.getMsgId();
            this.txt_sameTime.setText("同时转发");
            this.et_reply.setHint("写评论……");
        } catch (BadParcelableException e) {
            e.printStackTrace();
            finish();
        }
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

    protected View inflate(int resId) {
        return LayoutInflater.from(this.context).inflate(resId, (ViewGroup) null);
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
                if (type.equals(Constant.PRODUCTS_BUCKET)) {
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
                getView(view, R.id.item_circle_ext_video).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.circle.ReplyActivity.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Intent intent = new Intent(ReplyActivity.this.context, EaseShowVideoActivity.class);
                        intent.putExtra(UserDao.IMAGE_COLUMN_NAME_LOCALPATH, FolderUtil.getCachePathVideo() + File.separator + msgId + ".mp4");
                        intent.putExtra("secret", "");
                        intent.putExtra("remotepath", ext.getVideoUrl());
                        ReplyActivity.this.startActivity(intent);
                    }
                });
                ComUtil.display(this.context, (ImageView) getView(view, R.id.item_circle_video_pic), ext.getPicUrl(), R.drawable.bgm_circle_item_goods);
                break;
            case 1:
                view = inflate(R.layout.item_circle_list_demand);
                getView(view, R.id.item_circle_ext_demand).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.circle.ReplyActivity.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", Integer.parseInt(ext.getBizId()));
                        ReplyActivity.this.startAct(DemandInfoActivity.class, bundle);
                    }
                });
            case 2:
                if (ext.getType().equals(NotificationCompat.CATEGORY_SERVICE)) {
                    view = inflate(R.layout.item_circle_list_service);
                    getView(view, R.id.item_circle_ext_service).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.circle.ReplyActivity.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", Integer.parseInt(ext.getBizId()));
                            ReplyActivity.this.startAct(ServiceInfoActivity.class, bundle);
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
                getView(view, R.id.item_circle_ext_shop).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.circle.ReplyActivity.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Bundle bundle = new Bundle();
                        bundle.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(ReplyActivity.this.context, "/m/shop/" + ext.getUserName() + ".mobile"));
                        bundle.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
                        ReplyActivity.this.startAct(WebKitLocalActivity.class, bundle);
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
                getView(view, R.id.item_circle_ext_goods).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.circle.ReplyActivity.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        String url = ComUtil.getZCApi(ReplyActivity.this.context, "/m/mall/details.mobile?isFromAppToWap=true&uuid=" + ext.getGuid());
                        Bundle bundlePre = new Bundle();
                        bundlePre.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(ReplyActivity.this.context, url));
                        ReplyActivity.this.startAct(WebKitLocalActivity.class, bundlePre);
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

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.txt_sameTime /* 2131755205 */:
                this.txt_sameTime.setSelected(!this.txt_sameTime.isSelected());
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        String s = this.et_reply.getText().toString();
        if (TextUtils.isEmpty(s)) {
            MyToast.show(getApp(), "请说些什么……");
        } else if (this.txt_sameTime.isSelected()) {
            forwardContent(s);
            commentContent(s);
        } else if (this.isShare) {
            forwardContent(s);
        } else {
            commentContent(s);
        }
    }

    public void commentContent(String content) {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put(EditActivity.CONTENT, content);
        params.put(MessageEncoder.ATTR_ADDRESS, "");
        params.put("lgt", "");
        params.put("lat", "");
        params.put("udid", DemoHelper.getInstance().getDeviceUni());
        params.put("udName", DemoHelper.getInstance().getDeviceModel());
        getClient().post(R.string.API_CIRCLES_COMMENT_FORWARD, ComUtil.getF2FApi(this, getString(R.string.API_CIRCLES_COMMENT_FORWARD, new Object[]{"comment", this.circleBean.getMsgId(), this.circleBean.getMsgId()})), params, String.class, false);
    }

    public void forwardContent(String content) {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put(EditActivity.CONTENT, content);
        params.put(MessageEncoder.ATTR_ADDRESS, "");
        params.put("lgt", "");
        params.put("lat", "");
        params.put("udid", DemoHelper.getInstance().getDeviceUni());
        params.put("udName", DemoHelper.getInstance().getDeviceModel());
        getClient().post(R.string.API_CIRCLES_COMMENT_FORWARD, ComUtil.getF2FApi(this, getString(R.string.API_CIRCLES_COMMENT_FORWARD, new Object[]{FlagUtil.URL_FORWARD, this.prevMsgId, this.circleBean.getMsgId()})), params, String.class, false);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        if (this.isShare) {
            MyToast.show(getApp(), "转发成功");
        } else {
            MyToast.show(getApp(), "评论成功");
        }
        setResult(-1);
        finish();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }
}
