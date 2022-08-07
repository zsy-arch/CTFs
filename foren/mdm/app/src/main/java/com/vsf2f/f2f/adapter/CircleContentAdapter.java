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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.TimeUtil;
import com.easeui.ui.EaseShowVideoActivity;
import com.em.db.UserDao;
import com.hy.frame.adapter.BaseAdapter;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.HyUtil;
import com.hy.frame.view.MyGridView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.CircleExtInfo;
import com.vsf2f.f2f.bean.CircleReadBean;
import com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class CircleContentAdapter extends BaseAdapter<CircleReadBean> {
    private List<CircleReadBean> datas;
    private int width = getContext().getResources().getDisplayMetrics().widthPixels - getContext().getResources().getDimensionPixelSize(R.dimen.margin_big);
    private int contentWidth = (getContext().getResources().getDisplayMetrics().widthPixels - HyUtil.dip2px(getContext(), 66.0f)) * 3;

    public CircleContentAdapter(Context context, List<CircleReadBean> datas) {
        super(context);
        this.datas = datas;
    }

    public List<CircleReadBean> getDatas() {
        return this.datas;
    }

    public void setDatas(List<CircleReadBean> datas) {
        this.datas = datas;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.datas == null) {
            this.datas = new ArrayList();
        }
        return this.datas.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.datas.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ItemHolder h;
        String content;
        final CircleReadBean item = (CircleReadBean) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_circles_list, parent, false);
            h = new ItemHolder();
            h.imgLike = (ImageView) convertView.findViewById(R.id.img_Like);
            h.txtLike = (TextView) convertView.findViewById(R.id.txt_Like);
            h.txtReply = (TextView) convertView.findViewById(R.id.txt_Reply);
            h.txtShare = (TextView) convertView.findViewById(R.id.txt_Share);
            h.imgCollect = (ImageView) convertView.findViewById(R.id.img_collection);
            h.txtCollect = (TextView) convertView.findViewById(R.id.txt_collection);
            h.item_cirlce = (LinearLayout) convertView.findViewById(R.id.item_cirlce);
            h.item_adv = (LinearLayout) convertView.findViewById(R.id.item_adv);
            h.llyShare = (LinearLayout) convertView.findViewById(R.id.circles_i_ibtnShare);
            h.llyReply = (LinearLayout) convertView.findViewById(R.id.circles_i_ibtnReply);
            h.llyLike = (LinearLayout) convertView.findViewById(R.id.circles_i_ibtnSave);
            h.llyZan = (LinearLayout) convertView.findViewById(R.id.circles_i_ibtnZan);
            h.imgAvatar = (ImageView) convertView.findViewById(R.id.circles_i_imgAvatar);
            h.imgVip = (ImageView) convertView.findViewById(R.id.circles_i_imgVip);
            h.imgShop = (ImageView) convertView.findViewById(R.id.circles_i_imgShop);
            h.txtTime = (TextView) convertView.findViewById(R.id.circles_i_txtTime);
            h.txtName = (TextView) convertView.findViewById(R.id.circles_i_txtName);
            h.imgArtCover = (ImageView) convertView.findViewById(R.id.circles_i_imgAlone);
            h.txtContent = (TextView) convertView.findViewById(R.id.circles_i_txtContent);
            h.txtContent_more = (TextView) convertView.findViewById(R.id.circles_i_txtContent_more);
            h.shareCover = (ImageView) convertView.findViewById(R.id.circles_i_share_imgCover);
            h.shareContent = (TextView) convertView.findViewById(R.id.circles_i_share_txtContent);
            h.llyForward = (LinearLayout) convertView.findViewById(R.id.circle_i_llyForward);
            h.llyShareExt = (LinearLayout) convertView.findViewById(R.id.circle_i_llyShareExt);
            h.llyExt = (LinearLayout) convertView.findViewById(R.id.circle_i_llyExt);
            h.llyParent = (LinearLayout) convertView.findViewById(R.id.llyParent);
            h.txtDeviceName = (TextView) convertView.findViewById(R.id.circles_i_txtDeviceName);
            h.flyPic = (FrameLayout) convertView.findViewById(R.id.circles_i_flyPic);
            h.gridPic = (MyGridView) convertView.findViewById(R.id.circles_i_gridPic);
            convertView.setTag(h);
        } else {
            h = (ItemHolder) convertView.getTag();
        }
        if (item != null) {
            if ("4".equals(item.getPublisher().getLv())) {
                h.imgVip.setVisibility(0);
            } else {
                h.imgVip.setVisibility(8);
            }
            if (item.getPublisher().getCertShop() == 1) {
                h.imgShop.setVisibility(0);
            } else {
                h.imgShop.setVisibility(8);
            }
            if (item.getUdName() != null) {
                h.txtDeviceName.setVisibility(0);
                h.txtDeviceName.setText(String.format("来自%s", item.getUdName()));
            } else {
                h.txtDeviceName.setVisibility(8);
            }
            if (item.getNickName() != null) {
                h.txtName.setVisibility(0);
                h.txtName.setText(item.getNickName());
            } else {
                h.txtName.setVisibility(8);
            }
            if (item.getTime() != 0) {
                h.txtTime.setVisibility(0);
                h.txtTime.setText(TimeUtil.formatDisplayTime(item.getTime(), "yyyy-MM-dd HH:mm"));
            } else {
                h.txtTime.setVisibility(8);
            }
            if (item.getUserPic() == null || item.getUserPic().getSpath() == null) {
                h.imgAvatar.setImageResource(R.mipmap.def_head);
            } else {
                ComUtil.displayHead(getContext(), h.imgAvatar, item.getUserPic().getSpath());
            }
            h.imgLike.setSelected(item.isLike());
            h.imgCollect.setSelected(item.isCollection());
            if (item.getMsgHost() != null) {
                if (item.getMsgHost().getLike() != null) {
                    if (item.getMsgHost().getLike().equals("0")) {
                        h.txtLike.setText("");
                    } else if (item.getMsgHost().getLike().length() > 2) {
                        h.txtLike.setText("99+");
                    } else {
                        h.txtLike.setText(item.getMsgHost().getLike());
                    }
                }
                if (item.getMsgHost().getComment() != null) {
                    if (item.getMsgHost().getComment().equals("0")) {
                        h.txtReply.setText("");
                    } else if (item.getMsgHost().getComment().length() > 2) {
                        h.txtReply.setText("99+");
                    } else {
                        h.txtReply.setText(item.getMsgHost().getComment());
                    }
                }
                if (item.getMsgHost().getForward() != null) {
                    if (item.getMsgHost().getForward().equals("0")) {
                        h.txtShare.setText("");
                    } else if (item.getMsgHost().getForward().length() > 2) {
                        h.txtShare.setText("99+");
                    } else {
                        h.txtShare.setText(item.getMsgHost().getForward());
                    }
                }
                if (item.getMsgHost().getCollection() != null) {
                    if (item.getMsgHost().getCollection().equals("0")) {
                        h.txtCollect.setText("");
                    } else if (item.getMsgHost().getCollection().length() > 2) {
                        h.txtCollect.setText("99+");
                    } else {
                        h.txtCollect.setText(item.getMsgHost().getCollection());
                    }
                }
            }
            h.item_cirlce.setVisibility(0);
            h.item_adv.removeAllViews();
            h.llyExt.removeAllViews();
            h.llyShareExt.removeAllViews();
            h.llyForward.setVisibility(8);
            h.flyPic.setVisibility(8);
            switch (item.getType()) {
                case 0:
                    if (HyUtil.isNoEmpty(item.getPicList())) {
                        h.flyPic.setVisibility(0);
                        h.gridPic.setPressed(false);
                        h.gridPic.setEnabled(false);
                        h.gridPic.setClickable(false);
                        h.gridPic.setVisibility(0);
                        h.imgArtCover.setVisibility(8);
                        h.gridPic.setAdapter((ListAdapter) new PicCircleReadAdapter(getContext(), item.getPicList(), 3, getContext().getResources().getDimensionPixelSize(R.dimen.grid_circle_width)));
                        h.gridPic.setClickable(false);
                        h.gridPic.setEnabled(false);
                        break;
                    }
                    break;
                case 2:
                    h.llyForward.setVisibility(0);
                    if (item.getFwmi() != null && item.getFwmi().getMsgId() != null) {
                        if (TextUtils.isEmpty(item.getFwmi().getCoverPic().getSpath())) {
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
                            SpannableStringBuilder fontStyleBuilder = new SpannableStringBuilder("@" + nickname + "：" + content);
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
                        h.txtContent.setVisibility(0);
                        h.txtContent.setText(item.getTitle());
                    } else {
                        h.txtContent.setVisibility(8);
                    }
                    h.txtContent_more.setVisibility(8);
                    h.imgArtCover.setVisibility(0);
                    h.gridPic.setVisibility(8);
                    if (item.getCoverPic() != null && item.getCoverPic().getSpath() != null) {
                        h.imgArtCover.getLayoutParams().width = this.width;
                        h.imgArtCover.getLayoutParams().height = this.width / 2;
                        ComUtil.display(getContext(), h.imgArtCover, item.getCoverPic().getSpath(), R.drawable.icon_empty_cover);
                        break;
                    } else {
                        h.imgArtCover.setImageResource(R.drawable.img_empty);
                        break;
                    }
                    break;
                case 4:
                    if (item.getExtInfo() != null) {
                        if (!Constant.ADVERT_BUCKET.equals(item.getExtInfo().getType())) {
                            addExtView(h.llyExt, item.getExtInfo(), item.getMsgId());
                            break;
                        } else {
                            View view = inflate(R.layout.item_circle_list_adv);
                            ((TextView) getView(view, R.id.item_circle_ext_adv_title)).setText(item.getExtInfo().getTitle());
                            ImageView adpic = (ImageView) getView(view, R.id.item_circle_ext_adv_pic);
                            adpic.setLayoutParams(new LinearLayout.LayoutParams(this.width, (int) (this.width * item.getExtInfo().getAdPic().get(0).getRatio())));
                            if (HyUtil.isNoEmpty(item.getHerf())) {
                                getView(view, R.id.item_circle_ext_adv).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CircleContentAdapter.1
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view2) {
                                        Bundle bundleAdv = new Bundle();
                                        bundleAdv.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(CircleContentAdapter.this.getContext(), item.getHerf()));
                                        CircleContentAdapter.this.startAct(WebKitLocalActivity.class, bundleAdv);
                                    }
                                });
                            }
                            if (HyUtil.isNoEmpty(item.getExtInfo().getAdPic())) {
                                ComUtil.displayImage(getContext(), adpic, item.getExtInfo().getAdPic().get(0).getPath());
                            }
                            h.item_adv.addView(view);
                            h.item_cirlce.setVisibility(8);
                            break;
                        }
                    }
                    break;
            }
            if (item.getType() != 3) {
                if (item.getContent() != null) {
                    h.txtContent.setText(item.getContent().replace("<br>", "\n"));
                    h.txtContent.setVisibility(0);
                    if (getTextViewLength(h.txtContent, item.getContent()) > this.contentWidth) {
                        h.txtContent_more.setVisibility(0);
                        if (item.isShow()) {
                            h.txtContent_more.setText("收起");
                            h.txtContent.setMaxLines(100);
                        } else {
                            h.txtContent_more.setText("全文");
                            h.txtContent.setMaxLines(3);
                        }
                        h.txtContent_more.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CircleContentAdapter.2
                            @Override // android.view.View.OnClickListener
                            public void onClick(View v) {
                                if (h.txtContent_more.getText().equals("全文")) {
                                    h.txtContent_more.setText("收起");
                                    h.txtContent.setMaxLines(100);
                                    ((CircleReadBean) CircleContentAdapter.this.getItem(position)).setShow(true);
                                    return;
                                }
                                h.txtContent_more.setText("全文");
                                h.txtContent.setMaxLines(3);
                                ((CircleReadBean) CircleContentAdapter.this.getItem(position)).setShow(false);
                            }
                        });
                    } else {
                        h.txtContent_more.setVisibility(8);
                    }
                } else {
                    h.txtContent.setVisibility(8);
                    h.txtContent_more.setVisibility(8);
                }
            }
            setOnClickListener(h.item_cirlce, item, position);
            setOnClickListener(h.txtContent, item, position);
            setOnClickListener(h.llyForward, item, position);
            setOnClickListener(h.llyReply, item, position);
            setOnClickListener(h.llyLike, item, position);
            setOnClickListener(h.llyZan, item, position);
            setOnClickListener(h.llyShare, item, position);
        }
        return convertView;
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
                getView(view, R.id.item_circle_ext_video).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CircleContentAdapter.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Intent intent = new Intent(CircleContentAdapter.this.getContext(), EaseShowVideoActivity.class);
                        intent.putExtra(UserDao.IMAGE_COLUMN_NAME_LOCALPATH, FolderUtil.getCachePathVideo() + File.separator + msgId + ".mp4");
                        intent.putExtra("secret", "");
                        intent.putExtra("remotepath", ext.getVideoUrl());
                        CircleContentAdapter.this.getContext().startActivity(intent);
                    }
                });
                ComUtil.display(getContext(), (ImageView) getView(view, R.id.item_circle_video_pic), ext.getPicUrl(), R.drawable.icon_empty_cover);
                break;
            case 1:
                view = inflate(R.layout.item_circle_list_demand);
                getView(view, R.id.item_circle_ext_demand).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CircleContentAdapter.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", Integer.parseInt(ext.getBizId()));
                        CircleContentAdapter.this.startAct(DemandInfoActivity.class, bundle);
                    }
                });
            case 2:
                if (ext.getType().equals(NotificationCompat.CATEGORY_SERVICE)) {
                    view = inflate(R.layout.item_circle_list_service);
                    getView(view, R.id.item_circle_ext_service).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CircleContentAdapter.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", Integer.parseInt(ext.getBizId()));
                            CircleContentAdapter.this.startAct(ServiceInfoActivity.class, bundle);
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
                getView(view, R.id.item_circle_ext_shop).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CircleContentAdapter.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Bundle bundle = new Bundle();
                        bundle.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(CircleContentAdapter.this.getContext(), "/m/shop/" + ext.getUserName() + ".mobile"));
                        bundle.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
                        CircleContentAdapter.this.startAct(WebKitLocalActivity.class, bundle);
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
                getView(view, R.id.item_circle_ext_goods).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CircleContentAdapter.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        String url = ComUtil.getZCApi(CircleContentAdapter.this.getContext(), "/m/mall/details.mobile?isFromAppToWap=true&uuid=" + ext.getGuid());
                        Bundle bundlePre = new Bundle();
                        bundlePre.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(CircleContentAdapter.this.getContext(), url));
                        CircleContentAdapter.this.startAct(WebKitLocalActivity.class, bundlePre);
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

    /* loaded from: classes2.dex */
    static class ItemHolder {
        private FrameLayout flyPic;
        private MyGridView gridPic;
        private ImageView imgArtCover;
        private ImageView imgAvatar;
        private ImageView imgCollect;
        private ImageView imgLike;
        private ImageView imgShop;
        private ImageView imgVip;
        private LinearLayout item_adv;
        private LinearLayout item_cirlce;
        private LinearLayout llyExt;
        private LinearLayout llyForward;
        private LinearLayout llyLike;
        private LinearLayout llyParent;
        private LinearLayout llyReply;
        private LinearLayout llyShare;
        private LinearLayout llyShareExt;
        private LinearLayout llyZan;
        private TextView shareContent;
        private ImageView shareCover;
        private TextView txtCollect;
        private TextView txtContent;
        private TextView txtContent_more;
        private TextView txtDeviceName;
        private TextView txtLike;
        private TextView txtName;
        private TextView txtReply;
        private TextView txtShare;
        private TextView txtTime;

        ItemHolder() {
        }
    }

    protected void startAct(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtra(com.hy.frame.util.Constant.BUNDLE, bundle);
        }
        intent.setClass(getContext(), cls);
        getContext().startActivity(intent);
    }

    public static int getTextViewLength(TextView textView, String text) {
        return (int) textView.getPaint().measureText(text);
    }
}
