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
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.TimeUtil;
import com.easeui.ui.EaseShowVideoActivity;
import com.em.db.UserDao;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.recycler.adapter.BaseHolder;
import com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.CircleBaseBean;
import com.vsf2f.f2f.bean.CircleExtInfo;
import com.vsf2f.f2f.bean.ShareBean;
import com.vsf2f.f2f.bean.ShareCircleInfo;
import com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import java.io.File;
import java.util.List;

/* loaded from: classes2.dex */
public class SaveCircleAdapter extends BaseRecyclerAdapter<ShareBean> {
    private AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(-1, -2);

    public SaveCircleAdapter(Context context, List<ShareBean> datas) {
        super(context, R.layout.item_savecircle, datas);
    }

    @Override // com.hy.frame.view.recycler.adapter.BaseRecyclerAdapter
    public BaseHolder setItemViewHolder(View itemView) {
        return new ItemHolder(itemView);
    }

    public void initItemView(BaseHolder holder, ShareBean item, int position) {
        String content;
        ItemHolder h = (ItemHolder) holder;
        if (item.getMsgInfo() != null) {
            ShareCircleInfo circleInfo = item.getMsgInfo();
            h.txtcancel.setVisibility(8);
            h.lly_head.setVisibility(0);
            if (circleInfo.getUserPic() == null || circleInfo.getUserPic().getPath() == null) {
                h.imgAvatar.setImageResource(R.mipmap.def_head);
            } else {
                ComUtil.displayImage(getContext(), h.imgAvatar, circleInfo.getUserPic().getPath());
            }
            if (circleInfo.getCoverPic() == null || circleInfo.getCoverPic().getPath() == null) {
                h.imgCover.setVisibility(8);
            } else {
                h.imgCover.setVisibility(0);
                ComUtil.displayImage(getContext(), h.imgCover, circleInfo.getCoverPic().getPath());
            }
            h.txtname.setVisibility(circleInfo.getNickName() == null ? 8 : 0);
            h.txtname.setText(circleInfo.getNickName());
            h.txttime.setVisibility(circleInfo.getTime() != 0 ? 8 : 0);
            h.txttime.setText(TimeUtil.formatDisplayTime(circleInfo.getTime(), "yyyy-MM-dd HH:mm"));
            h.llyExt.removeAllViews();
            h.llyShareExt.removeAllViews();
            if (circleInfo.getType() == 2) {
                if (circleInfo.getFwmi() != null) {
                    h.llyForward.setVisibility(0);
                    CircleBaseBean circleFwmi = circleInfo.getFwmi();
                    if (circleFwmi.getCoverPic() == null || circleFwmi.getCoverPic().getPath() == null) {
                        h.imgShareCover.setVisibility(8);
                    } else {
                        h.imgShareCover.setVisibility(0);
                        ComUtil.displayImage(getContext(), h.imgShareCover, circleFwmi.getCoverPic().getPath());
                    }
                    if (circleFwmi.getType() == 4 && circleFwmi.getExtInfo() != null) {
                        addExtView(h.llyShareExt, circleInfo.getFwmi().getExtInfo(), circleInfo.getFwmi().getMsgId());
                    }
                    if (circleFwmi.getType() == 3) {
                        content = "《 " + circleFwmi.getTitle() + " 》";
                    } else {
                        content = circleFwmi.getContent().replace("<br>", "\n");
                    }
                    String nickname = circleFwmi.getNickName();
                    SpannableStringBuilder fontStyleBuilder = new SpannableStringBuilder("@" + nickname + "：" + content);
                    fontStyleBuilder.setSpan(new ForegroundColorSpan(-16776961), 0, nickname.length() + 2, 33);
                    h.txtShareContent.setText(fontStyleBuilder);
                }
            } else if (circleInfo.getType() == 4) {
                if (circleInfo.getExtInfo() != null) {
                    addExtView(h.llyExt, circleInfo.getExtInfo(), circleInfo.getMsgId());
                }
                h.llyForward.setVisibility(8);
            } else {
                h.llyForward.setVisibility(8);
            }
            h.txtContent.setVisibility(0);
            if (circleInfo.getType() == 3) {
                h.txtContent.setText("《 " + circleInfo.getTitle() + " 》");
            } else if (!TextUtils.isEmpty(circleInfo.getContent())) {
                h.txtContent.setText(circleInfo.getContent().replace("<br>", "\n"));
            } else {
                h.txtContent.setVisibility(8);
            }
        } else {
            h.txtContent.setText("");
            h.txtContent.setVisibility(8);
            h.imgCover.setVisibility(8);
            h.lly_head.setVisibility(8);
            h.llyForward.setVisibility(8);
            h.txtcancel.setVisibility(0);
        }
        setOnClickListener(h.imgbottom, item, position);
        setOnClickListener(h.itemView, item, position);
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
                getView(view, R.id.item_circle_ext_video).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.SaveCircleAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Intent intent = new Intent(SaveCircleAdapter.this.getContext(), EaseShowVideoActivity.class);
                        intent.putExtra(UserDao.IMAGE_COLUMN_NAME_LOCALPATH, FolderUtil.getCachePathVideo() + File.separator + msgId + ".mp4");
                        intent.putExtra("secret", "");
                        intent.putExtra("remotepath", ext.getVideoUrl());
                        SaveCircleAdapter.this.getContext().startActivity(intent);
                    }
                });
                ComUtil.display(getContext(), (ImageView) getView(view, R.id.item_circle_video_pic), ext.getPicUrl(), R.drawable.bgm_circle_item_goods);
                break;
            case 1:
                view = inflate(R.layout.item_circle_list_demand);
                getView(view, R.id.item_circle_ext_demand).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.SaveCircleAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", Integer.parseInt(ext.getBizId()));
                        SaveCircleAdapter.this.startAct(DemandInfoActivity.class, bundle);
                    }
                });
            case 2:
                if (ext.getType().equals(NotificationCompat.CATEGORY_SERVICE)) {
                    view = inflate(R.layout.item_circle_list_service);
                    getView(view, R.id.item_circle_ext_service).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.SaveCircleAdapter.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", Integer.parseInt(ext.getBizId()));
                            SaveCircleAdapter.this.startAct(ServiceInfoActivity.class, bundle);
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
                getView(view, R.id.item_circle_ext_shop).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.SaveCircleAdapter.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Bundle bundle = new Bundle();
                        bundle.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(SaveCircleAdapter.this.getContext(), "/m/shop/" + ext.getUserName() + ".mobile"));
                        bundle.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
                        SaveCircleAdapter.this.startAct(WebKitLocalActivity.class, bundle);
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
                getView(view, R.id.item_circle_ext_goods).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.SaveCircleAdapter.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        String url = ComUtil.getZCApi(SaveCircleAdapter.this.getContext(), "/m/mall/details.mobile?isFromAppToWap=true&uuid=" + ext.getGuid());
                        Bundle bundlePre = new Bundle();
                        bundlePre.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(SaveCircleAdapter.this.getContext(), url));
                        SaveCircleAdapter.this.startAct(WebKitLocalActivity.class, bundlePre);
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

    protected void startAct(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtra(com.hy.frame.util.Constant.BUNDLE, bundle);
        }
        intent.setClass(getContext(), cls);
        getContext().startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ItemHolder extends BaseHolder {
        private ImageButton imgbottom = (ImageButton) getView(R.id.save_imgCirclebottom);
        private ImageView imgAvatar = (ImageView) getView(R.id.circles_i_imgAvatar);
        private ImageView imgCover = (ImageView) getView(R.id.circles_i_imgCover);
        private ImageView imgShareCover = (ImageView) getView(R.id.circles_i_share_imgCover);
        private TextView txtname = (TextView) getView(R.id.circles_i_txtName);
        private TextView txttime = (TextView) getView(R.id.circles_i_txtTime);
        private TextView txtShareContent = (TextView) getView(R.id.circles_i_share_txtContent);
        private TextView txtContent = (TextView) getView(R.id.save_txtContent);
        private TextView txtcancel = (TextView) getView(R.id.txt_need_cancel);
        private LinearLayout lly_head = (LinearLayout) getView(R.id.lly_head);
        private LinearLayout llyForward = (LinearLayout) getView(R.id.circle_i_llyForward);
        private LinearLayout llyShareExt = (LinearLayout) getView(R.id.circle_i_llyShareExt);
        private LinearLayout llyExt = (LinearLayout) getView(R.id.circle_i_llyExt);

        public ItemHolder(View v) {
            super(v);
            v.setLayoutParams(SaveCircleAdapter.this.layoutParams);
        }
    }
}
