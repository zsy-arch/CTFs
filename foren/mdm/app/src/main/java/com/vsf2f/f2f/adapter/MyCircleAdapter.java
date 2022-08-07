package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.TimeUtil;
import com.easeui.ui.EaseShowVideoActivity;
import com.em.DemoHelper;
import com.em.db.UserDao;
import com.hy.frame.util.Constant;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.RoundImageView;
import com.hy.frame.view.recycler.adapter.BaseHolder;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.CircleExtInfo;
import com.vsf2f.f2f.bean.CircleReadBean;
import com.vsf2f.f2f.bean.CircleReadPicListBean;
import com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import java.io.File;
import java.util.List;

/* loaded from: classes2.dex */
public class MyCircleAdapter extends BaseRecyclerAdapter<CircleReadBean> {
    private int width;
    private String nick = DemoHelper.getInstance().getCurrentUserNick();
    private String avatar = DemoHelper.getInstance().getCurrentUserPic().getSpath();
    private int contentWidth = (getContext().getResources().getDisplayMetrics().widthPixels - HyUtil.dip2px(getContext(), 66.0f)) * 3;

    public MyCircleAdapter(Context context, List<CircleReadBean> datas) {
        super(context, R.layout.item_user_mycircle, datas);
        this.width = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, CircleReadBean item, final int position) {
        String content;
        final ItemHolder h = (ItemHolder) holder;
        Glide.with(this.context).load(this.avatar).error((int) R.mipmap.def_head).into(h.ivAvatar);
        h.txtNick.setText(this.nick);
        h.txtTime.setText(TimeUtil.getDateTime(item.getTime(), "yyyy-MM-dd ahh:mm"));
        h.txtUdName.setText(item.getUdName());
        h.imgLike.setSelected(item.isLike());
        h.txtContent.setVisibility(item.getContent() == null ? 8 : 0);
        h.txtContent.setText(item.getContent());
        h.txtReply.setText(item.getMsgHost() != null ? item.getMsgHost().getComment() : "0");
        h.txtlike.setText(item.getMsgHost() != null ? item.getMsgHost().getLike() : "0");
        h.llyExt.removeAllViews();
        h.llyShareExt.removeAllViews();
        h.llyForward.setVisibility(8);
        h.flyPic.setVisibility(8);
        switch (item.getType()) {
            case 0:
                if (item.getPicList() == null) {
                    h.gridPic.setVisibility(8);
                    h.imgCircle.setVisibility(8);
                    break;
                } else {
                    h.flyPic.setVisibility(0);
                    if (item.getPicList().size() == 0) {
                        h.gridPic.setVisibility(8);
                        h.imgCircle.setVisibility(8);
                        break;
                    } else {
                        List<CircleReadPicListBean> list = item.getPicList();
                        h.gridPic.setClickable(false);
                        h.gridPic.setPressed(false);
                        h.gridPic.setEnabled(false);
                        h.gridPic.setVisibility(0);
                        h.imgCircle.setVisibility(8);
                        h.gridPic.setAdapter((ListAdapter) new PicCircleReadAdapter(getContext(), list, 3, getContext().getResources().getDimensionPixelSize(R.dimen.grid_circle_width)));
                        break;
                    }
                }
            case 2:
                h.llyForward.setVisibility(0);
                h.shareCover.getLayoutParams().width = this.width / 4;
                h.shareCover.getLayoutParams().height = this.width / 4;
                if (item.getFwmi() != null) {
                    if (item.getFwmi().getCoverPic().getSpath() == null) {
                        h.shareCover.setVisibility(8);
                    } else {
                        h.shareCover.setVisibility(0);
                        ComUtil.displayImage(getContext(), h.shareCover, item.getFwmi().getCoverPic().getSpath());
                    }
                    if (item.getFwmi().getNickName() != null) {
                        if (item.getFwmi().getType() == 3) {
                            content = "《 " + item.getFwmi().getTitle() + " 》";
                        } else {
                            content = item.getFwmi().getContent().replace("<br>", "\n");
                        }
                        String nickname = item.getFwmi().getNickName();
                        SpannableStringBuilder fontStyleBuilder = new SpannableStringBuilder("@" + nickname + ":" + content);
                        fontStyleBuilder.setSpan(new ForegroundColorSpan(-16776961), 0, nickname.length() + 2, 33);
                        h.shareContent.setText(fontStyleBuilder);
                    } else {
                        h.shareContent.setText(item.getFwmi().getContent().replace("<br>", "\n"));
                    }
                    if (item.getFwmi().getType() == 4 && item.getFwmi().getExtInfo() != null) {
                        addExtView(h.llyShareExt, item.getFwmi().getExtInfo(), item.getMsgId());
                        break;
                    }
                } else {
                    h.shareCover.setVisibility(8);
                    h.shareContent.setText(R.string.message_delete);
                    break;
                }
                break;
            case 3:
                h.flyPic.setVisibility(0);
                if (!TextUtils.isEmpty(item.getTitle())) {
                    h.txtContent.setText(item.getTitle());
                }
                h.gridPic.setVisibility(8);
                h.imgCircle.setVisibility(0);
                h.imgCircle.getLayoutParams().width = this.width - 40;
                h.imgCircle.getLayoutParams().height = (this.width - 40) / 2;
                if (item.getCoverPic() != null && item.getCoverPic().getPath() != null) {
                    Glide.with(getContext()).load(item.getCoverPic().getPath()).error((int) R.drawable.img_empty).crossFade().into(h.imgCircle);
                    break;
                } else {
                    h.shareCover.setImageResource(R.drawable.img_empty);
                    break;
                }
                break;
            case 4:
                if (item.getExtInfo() != null) {
                    addExtView(h.llyExt, item.getExtInfo(), item.getMsgId());
                    break;
                }
                break;
        }
        if (item.getType() == 3) {
            h.txtContent.setVisibility(0);
            h.txtContent_more.setVisibility(8);
        } else if (item.getContent() != null) {
            h.txtContent.setText(item.getContent().replace("<br>", "\n"));
            h.txtContent.setVisibility(0);
            if (getTextViewLength(h.txtContent, item.getContent()) > this.contentWidth) {
                h.txtContent_more.setVisibility(0);
                h.txtContent_more.setText("全文");
                h.txtContent.setMaxLines(3);
                ((CircleReadBean) this.datas.get(position)).setShow(false);
                h.txtContent_more.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MyCircleAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        if (h.txtContent_more.getText().equals("全文")) {
                            h.txtContent_more.setText("收起");
                            h.txtContent.setMaxLines(100);
                            ((CircleReadBean) MyCircleAdapter.this.datas.get(position)).setShow(true);
                            return;
                        }
                        h.txtContent_more.setText("全文");
                        h.txtContent.setMaxLines(3);
                        ((CircleReadBean) MyCircleAdapter.this.datas.get(position)).setShow(false);
                    }
                });
            } else {
                h.txtContent_more.setVisibility(8);
            }
        } else {
            h.txtContent.setVisibility(8);
            h.txtContent_more.setVisibility(8);
        }
        setOnClickListener(h.txtDelete, item, position);
        setOnClickListener(h.llyReply, item, position);
        setOnClickListener(h.llyzan, item, position);
        setOnClickListener(h.llyForward, item, position);
        setOnClickListener(h.itemView, item, position);
    }

    protected void startAct(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtra(Constant.BUNDLE, bundle);
        }
        intent.setClass(getContext(), cls);
        getContext().startActivity(intent);
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
                getView(view, R.id.item_circle_ext_video).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MyCircleAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Intent intent = new Intent(MyCircleAdapter.this.getContext(), EaseShowVideoActivity.class);
                        intent.putExtra(UserDao.IMAGE_COLUMN_NAME_LOCALPATH, FolderUtil.getCachePathVideo() + File.separator + msgId + ".mp4");
                        intent.putExtra("secret", "");
                        intent.putExtra("remotepath", ext.getVideoUrl());
                        MyCircleAdapter.this.getContext().startActivity(intent);
                    }
                });
                ComUtil.display(getContext(), (ImageView) getView(view, R.id.item_circle_video_pic), ext.getPicUrl(), R.drawable.bgm_circle_item_goods);
                break;
            case 1:
                view = inflate(R.layout.item_circle_list_demand);
                getView(view, R.id.item_circle_ext_demand).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MyCircleAdapter.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", Integer.parseInt(ext.getBizId()));
                        MyCircleAdapter.this.startAct(DemandInfoActivity.class, bundle);
                    }
                });
            case 2:
                if (ext.getType().equals(NotificationCompat.CATEGORY_SERVICE)) {
                    view = inflate(R.layout.item_circle_list_service);
                    getView(view, R.id.item_circle_ext_service).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MyCircleAdapter.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", Integer.parseInt(ext.getBizId()));
                            MyCircleAdapter.this.startAct(ServiceInfoActivity.class, bundle);
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
                    ComUtil.displayImage(getContext(), pic2, ext.getPic().get(0));
                    break;
                }
                break;
            case 3:
                view = inflate(R.layout.item_circle_list_shop);
                getView(view, R.id.item_circle_ext_shop).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MyCircleAdapter.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Bundle bundle = new Bundle();
                        bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(MyCircleAdapter.this.getContext(), "/m/shop/" + ext.getUserName() + ".mobile"));
                        bundle.putBoolean(Constant.FLAG2, true);
                        MyCircleAdapter.this.startAct(WebKitLocalActivity.class, bundle);
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
                ComUtil.displayImage(getContext(), (ImageView) getView(view, R.id.item_circle_shop_pic), ext.getLogo());
                break;
            case 4:
                view = inflate(R.layout.item_circle_list_goods);
                getView(view, R.id.item_circle_ext_goods).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MyCircleAdapter.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        String url = ComUtil.getZCApi(MyCircleAdapter.this.getContext(), "/m/mall/details.mobile?isFromAppToWap=true&uuid=" + ext.getGuid());
                        Bundle bundlePre = new Bundle();
                        bundlePre.putString(Constant.FLAG, WebUtils.getTokenUrl(MyCircleAdapter.this.getContext(), url));
                        MyCircleAdapter.this.startAct(WebKitLocalActivity.class, bundlePre);
                    }
                });
                ((TextView) getView(view, R.id.item_circle_goods_title)).setText(ext.getGoodsName());
                ((TextView) getView(view, R.id.item_circle_goods_price)).setText(ext.getPrice() + "");
                ImageView pic = (ImageView) getView(view, R.id.item_circle_ext_pic);
                if (HyUtil.isNoEmpty(ext.getPic())) {
                    ComUtil.displayImage(getContext(), pic, ext.getPic().get(0));
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

    public static int getTextViewLength(TextView textView, String text) {
        return (int) textView.getPaint().measureText(text);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ItemHolder extends BaseHolder {
        private TextView txtDelete = (TextView) getView(R.id.circle_btnDel);
        private TextView txtNick = (TextView) getView(R.id.circle_i_txtNick);
        private TextView txtTime = (TextView) getView(R.id.circle_i_txtTime);
        private TextView txtUdName = (TextView) getView(R.id.circle_i_txtUdName);
        private TextView txtContent = (TextView) getView(R.id.circle_i_txtContent);
        private TextView txtContent_more = (TextView) getView(R.id.circles_i_txtContent_more);
        private TextView txtReply = (TextView) getView(R.id.txt_Reply);
        private TextView txtlike = (TextView) getView(R.id.txt_Like);
        private ImageView imgLike = (ImageView) getView(R.id.img_Like);
        private RoundImageView ivAvatar = (RoundImageView) getView(R.id.circle_i_ivAvatar);
        private ImageView imgCircle = (ImageView) getView(R.id.circles_i_imgAlone);
        private LinearLayout llyReply = (LinearLayout) getView(R.id.circles_i_ibtnReply);
        private LinearLayout llyzan = (LinearLayout) getView(R.id.circles_i_ibtnZan);
        private ImageView shareCover = (ImageView) getView(R.id.circles_i_share_imgCover);
        private TextView shareContent = (TextView) getView(R.id.circles_i_share_txtContent);
        private LinearLayout llyForward = (LinearLayout) getView(R.id.circle_i_llyForward);
        private LinearLayout llyShareExt = (LinearLayout) getView(R.id.circle_i_llyShareExt);
        private LinearLayout llyExt = (LinearLayout) getView(R.id.circle_i_llyExt);
        private LinearLayout llyParent = (LinearLayout) getView(R.id.llyParent);
        private FrameLayout flyPic = (FrameLayout) getView(R.id.circles_i_flyPic);
        private GridView gridPic = (GridView) getView(R.id.circles_i_gridPic);

        public ItemHolder(View v) {
            super(v);
        }
    }
}
