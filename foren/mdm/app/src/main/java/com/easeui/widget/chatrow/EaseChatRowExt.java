package com.easeui.widget.chatrow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.Spannable;
import android.text.TextUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.bumptech.glide.Glide;
import com.cdlinglu.server.audio.MusicPlayer;
import com.easeui.EaseConstant;
import com.easeui.utils.EaseSmileUtils;
import com.easeui.widget.EaseAlertDialog;
import com.easeui.widget.chatrow.EaseChatRow;
import com.em.DemoHelper;
import com.em.ui.UserProfileActivity;
import com.em.utils.UserShared;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.exceptions.HyphenateException;
import com.tencent.open.SocialConstants;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import com.vsf2f.f2f.ui.otay.ManorTreeDialog;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes.dex */
public class EaseChatRowExt extends EaseChatRow {
    private CardHolder ch;
    private TextView contentView;
    private GameHolder gah;
    private GoodHolder gh;
    private NeedHolder nh;
    private OrderHolder oh;
    private String type;
    private ViewHolder vh;

    public EaseChatRowExt(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_extinfo : R.layout.ease_row_sent_extinfo, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.contentView = (TextView) findViewById(R.id.tv_chatcontent);
        this.vh = new ViewHolder();
        this.vh.txtNo = (TextView) findViewById(R.id.tv_order_no);
        this.vh.txtTime = (TextView) findViewById(R.id.tv_order_time);
        this.vh.txtAmount = (TextView) findViewById(R.id.tv_order_price);
        this.vh.txtproducts = (TextView) findViewById(R.id.tv_order_products);
        this.vh.llyOrder = (LinearLayout) findViewById(R.id.text_llyOrder);
        this.vh.llyCard = (LinearLayout) findViewById(R.id.text_llyCard);
        this.vh.llyGood = (LinearLayout) findViewById(R.id.text_llyGood);
        this.vh.llyNeed = (LinearLayout) findViewById(R.id.text_llyNeed);
        this.vh.llyGame = (LinearLayout) findViewById(R.id.text_llyGame);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    public void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) this.message.getBody();
        if (txtBody != null) {
            Spannable span = EaseSmileUtils.getSmiledText(this.context, txtBody.getMessage());
            this.contentView.setAutoLinkMask(15);
            this.contentView.setVisibility(0);
            this.contentView.setText(span, TextView.BufferType.SPANNABLE);
        }
        if (this.vh.llyOrder != null) {
            this.vh.llyOrder.setVisibility(8);
        }
        if (this.vh.llyCard != null) {
            this.vh.llyCard.setVisibility(8);
        }
        if (this.vh.llyGood != null) {
            this.vh.llyGood.setVisibility(8);
        }
        if (this.vh.llyNeed != null) {
            this.vh.llyNeed.setVisibility(8);
        }
        if (this.vh.llyGame != null) {
            this.vh.llyGame.setVisibility(8);
        }
        try {
            JSONObject extJson = new JSONObject(this.message.getStringAttribute(MessageEncoder.ATTR_EXT));
            this.type = extJson.optString("type");
            String str = this.type;
            char c = 65535;
            switch (str.hashCode()) {
                case -1335432629:
                    if (str.equals("demand")) {
                        c = 4;
                        break;
                    }
                    break;
                case -309474065:
                    if (str.equals(Constant.PRODUCT)) {
                        c = 3;
                        break;
                    }
                    break;
                case 3046160:
                    if (str.equals("card")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3165170:
                    if (str.equals("game")) {
                        c = 2;
                        break;
                    }
                    break;
                case 106006350:
                    if (str.equals("order")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1984153269:
                    if (str.equals(NotificationCompat.CATEGORY_SERVICE)) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.oh = new OrderHolder();
                    this.oh.no = extJson.optString("no");
                    this.oh.type = extJson.optString("type");
                    this.oh.time = extJson.optString(f.az);
                    this.oh.title = extJson.optString("title");
                    this.oh.seller = extJson.optString("seller");
                    this.oh.customer = extJson.optString("customer");
                    this.oh.amount = extJson.optString("amount");
                    this.oh.products = extJson.optString(Constant.PRODUCTS_BUCKET);
                    this.oh.seller_url = extJson.optString("seller_url");
                    this.oh.cust_url = extJson.optString("cust_url");
                    this.vh.txtTime.setText(this.oh.time);
                    this.vh.txtAmount.setText("￥" + this.oh.amount + "元");
                    this.vh.txtproducts.setText(this.oh.products);
                    this.vh.txtNo.setText(this.oh.no);
                    if (this.vh.llyOrder != null) {
                        this.vh.llyOrder.setVisibility(0);
                        break;
                    }
                    break;
                case 1:
                    this.ch = new CardHolder();
                    this.ch.username = extJson.optString("username");
                    this.ch.nickname = extJson.optString(EaseConstant.EXTRA_NICK_NAME);
                    this.ch.avatar_url = extJson.optString(EaseConstant.EXTRA_USER_AVATAR);
                    ((TextView) findViewById(R.id.text_txtNickname)).setText(this.ch.nickname);
                    ((TextView) findViewById(R.id.text_txtUsername)).setText(this.ch.username);
                    Glide.with(this.context).load(this.ch.avatar_url).error((int) R.mipmap.def_head).into((ImageView) findViewById(R.id.text_imgAvatar));
                    this.contentView.setVisibility(8);
                    if (this.vh.llyCard != null) {
                        this.vh.llyCard.setVisibility(0);
                        break;
                    }
                    break;
                case 2:
                    this.gah = new GameHolder();
                    this.gah.title = extJson.optString("title");
                    this.gah.desc = extJson.optString(SocialConstants.PARAM_APP_DESC);
                    this.contentView.setVisibility(8);
                    if (this.vh.llyGame != null) {
                        this.vh.llyGame.setVisibility(0);
                        break;
                    }
                    break;
                case 3:
                    this.gh = new GoodHolder();
                    this.gh.icon = extJson.optString(f.aY);
                    this.gh.title = extJson.optString("title");
                    this.gh.price = extJson.optString(f.aS);
                    this.gh.context = extJson.optString(av.aJ);
                    this.gh.details_url = extJson.optString("details_url");
                    ((TextView) findViewById(R.id.text_txtGoodname)).setText(this.gh.title);
                    ((TextView) findViewById(R.id.text_txtGoodprice)).setText("￥ " + this.gh.price);
                    Glide.with(this.context).load(this.gh.icon).error((int) R.mipmap.def_head).into((ImageView) findViewById(R.id.text_imgGoodpic));
                    this.contentView.setVisibility(8);
                    if (this.vh.llyGood != null) {
                        this.vh.llyGood.setVisibility(0);
                        break;
                    }
                    break;
                case 4:
                case 5:
                    this.nh = new NeedHolder();
                    this.nh.moId = extJson.optString("moId");
                    this.nh.title = extJson.optString("title");
                    this.nh.desc = extJson.optString(SocialConstants.PARAM_APP_DESC);
                    this.nh.reward = extJson.optString("reward");
                    this.nh.unit = extJson.optString("unit");
                    this.nh.address = extJson.optString("address");
                    this.nh.voiceDuration = extJson.optString("voiceDuration");
                    this.nh.serviceMode = "1".equals(extJson.optString("serviceMode"));
                    this.nh.isService = this.type.equals(NotificationCompat.CATEGORY_SERVICE);
                    MusicPlayer voice = (MusicPlayer) findViewById(R.id.voicePlayer);
                    if (TextUtils.isEmpty(this.nh.voiceDuration) || "0".equals(this.nh.voiceDuration)) {
                        voice.setVisibility(4);
                    } else {
                        voice.setDuration(this.nh.voiceDuration);
                        voice.setVisibility(0);
                    }
                    ((TextView) findViewById(R.id.text_txtNeedtitle)).setText(this.nh.title);
                    ((TextView) findViewById(R.id.text_txtNeeddesc)).setText(this.nh.desc);
                    TextView price = (TextView) findViewById(R.id.text_txtNeedprice);
                    price.setSelected(this.nh.isService);
                    price.setText(this.nh.reward + "元");
                    ((TextView) findViewById(R.id.text_txtNeedunit)).setText("/ " + this.nh.unit);
                    ((TextView) findViewById(R.id.text_txtNeedmode)).setText(this.nh.serviceMode ? "线下服务" : "线上服务");
                    TextView addr = (TextView) findViewById(R.id.text_txtNeedaddr);
                    if (this.nh.isService) {
                        addr.setTextColor(getContext().getResources().getColor(R.color.blue));
                    } else {
                        addr.setTextColor(getContext().getResources().getColor(R.color.txt_red_finace));
                    }
                    addr.setText(this.nh.address);
                    TextView btn = (TextView) findViewById(R.id.text_txtNeedbtn);
                    btn.setSelected(this.nh.isService);
                    btn.setText(this.nh.isService ? "预约" : "抢单");
                    ((ImageView) findViewById(R.id.text_imgNeedaddr)).setSelected(this.nh.isService);
                    this.contentView.setVisibility(8);
                    if (this.vh.llyNeed != null) {
                        this.vh.llyNeed.setVisibility(0);
                        break;
                    }
                    break;
                default:
                    MyLog.e("ext_type=" + this.type);
                    throw new Exception("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        handleTextMessage();
    }

    protected void handleTextMessage() {
        if (this.message.direct() == EMMessage.Direct.SEND) {
            setMessageSendCallback();
            switch (this.message.status()) {
                case CREATE:
                    this.progressBar.setVisibility(8);
                    this.statusView.setVisibility(0);
                    return;
                case SUCCESS:
                    this.progressBar.setVisibility(8);
                    this.statusView.setVisibility(8);
                    return;
                case FAIL:
                    this.progressBar.setVisibility(8);
                    this.statusView.setVisibility(0);
                    return;
                case INPROGRESS:
                    this.progressBar.setVisibility(0);
                    this.statusView.setVisibility(8);
                    return;
                default:
                    return;
            }
        } else if (!this.message.isAcked() && this.message.getChatType() == EMMessage.ChatType.Chat) {
            try {
                EMClient.getInstance().chatManager().ackMessageRead(this.message.getFrom(), this.message.getMsgId());
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onUpdateView() {
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onBubbleClick() {
        String str = this.type;
        char c = 65535;
        switch (str.hashCode()) {
            case -1335432629:
                if (str.equals("demand")) {
                    c = 4;
                    break;
                }
                break;
            case -309474065:
                if (str.equals(Constant.PRODUCT)) {
                    c = 3;
                    break;
                }
                break;
            case 3046160:
                if (str.equals("card")) {
                    c = 1;
                    break;
                }
                break;
            case 3165170:
                if (str.equals("game")) {
                    c = 2;
                    break;
                }
                break;
            case 106006350:
                if (str.equals("order")) {
                    c = 0;
                    break;
                }
                break;
            case 1984153269:
                if (str.equals(NotificationCompat.CATEGORY_SERVICE)) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (this.oh != null && this.oh.cust_url != null && this.oh.seller_url != null && this.oh.seller != null) {
                    String webUrl = DemoHelper.getInstance().getCurrentUserName().equals(this.oh.seller) ? WebUtils.getTokenUrl(this.context, this.oh.seller_url) : WebUtils.getTokenUrl(this.context, this.oh.cust_url);
                    if (!TextUtils.isEmpty(webUrl)) {
                        Bundle bundle = new Bundle();
                        bundle.putString(com.hy.frame.util.Constant.FLAG, webUrl);
                        Intent intent = new Intent(this.context, WebKitLocalActivity.class);
                        intent.putExtra(com.hy.frame.util.Constant.BUNDLE, bundle);
                        this.activity.startActivity(intent);
                        return;
                    }
                    MyToast.show(this.context, (int) R.string.login_state_abnormal);
                    return;
                }
                return;
            case 1:
                if (this.ch == null) {
                    return;
                }
                if (!TextUtils.isEmpty(this.ch.username)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("username", this.ch.username);
                    Intent intent2 = new Intent(this.context, UserProfileActivity.class);
                    intent2.putExtra(com.hy.frame.util.Constant.BUNDLE, bundle2);
                    this.activity.startActivity(intent2);
                    return;
                }
                MyToast.show(this.context, "用户名找不到啦");
                return;
            case 2:
                if (this.gah == null) {
                    return;
                }
                if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                    new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.easeui.widget.chatrow.EaseChatRowExt.1
                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                        public void onResult(boolean confirmed, Bundle bundle3) {
                            if (confirmed) {
                                EaseChatRowExt.this.context.startActivity(new Intent(EaseChatRowExt.this.activity, BindPhoneActivity.class));
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
            case 3:
                if (this.gh != null) {
                    String webUrl2 = WebUtils.getTokenUrl(this.context, this.gh.details_url);
                    if (!TextUtils.isEmpty(webUrl2)) {
                        Bundle bundle3 = new Bundle();
                        bundle3.putString(com.hy.frame.util.Constant.FLAG, webUrl2);
                        Intent intent3 = new Intent(this.context, WebKitLocalActivity.class);
                        intent3.putExtra(com.hy.frame.util.Constant.BUNDLE, bundle3);
                        this.activity.startActivity(intent3);
                        return;
                    }
                    return;
                }
                return;
            case 4:
                if (this.nh != null && !TextUtils.isEmpty(this.nh.moId)) {
                    Bundle bundle4 = new Bundle();
                    bundle4.putInt("id", Integer.parseInt(this.nh.moId));
                    Intent intent4 = new Intent(this.context, DemandInfoActivity.class);
                    intent4.putExtra(com.hy.frame.util.Constant.BUNDLE, bundle4);
                    this.activity.startActivity(intent4);
                    return;
                }
                return;
            case 5:
                if (this.nh != null && !TextUtils.isEmpty(this.nh.moId)) {
                    Bundle bundle5 = new Bundle();
                    bundle5.putInt("id", Integer.parseInt(this.nh.moId));
                    Intent intent5 = new Intent(this.context, ServiceInfoActivity.class);
                    intent5.putExtra(com.hy.frame.util.Constant.BUNDLE, bundle5);
                    this.activity.startActivity(intent5);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* loaded from: classes.dex */
    private class ViewHolder {
        private LinearLayout llyCard;
        private LinearLayout llyGame;
        private LinearLayout llyGood;
        private LinearLayout llyNeed;
        private LinearLayout llyOrder;
        private TextView txtAmount;
        private TextView txtNo;
        private TextView txtTime;
        private TextView txtproducts;

        private ViewHolder() {
        }
    }

    /* loaded from: classes.dex */
    private class OrderHolder {
        private String amount;
        private String cust_url;
        private String customer;
        private String freight;
        private String goods_img;
        private String goods_url;
        private String no;
        private String products;
        private String quantity;
        private String seller;
        private String seller_url;
        private String time;
        private String title;
        private String type;

        private OrderHolder() {
        }
    }

    /* loaded from: classes.dex */
    private class CardHolder {
        private String avatar_url;
        private String nickname;
        private String username;

        private CardHolder() {
        }
    }

    /* loaded from: classes.dex */
    private class GameHolder {
        private String desc;
        private String detailUrl;
        private String title;

        private GameHolder() {
        }
    }

    /* loaded from: classes.dex */
    private class GoodHolder {
        private String context;
        private String details_url;
        private String icon;
        private String price;
        private String title;

        private GoodHolder() {
        }
    }

    /* loaded from: classes.dex */
    private class NeedHolder {
        private String address;
        private String desc;
        private boolean isService;
        private String moId;
        private String publishUser;
        private String reward;
        private boolean serviceMode;
        private String title;
        private String unit;
        private String voiceDuration;

        private NeedHolder() {
        }
    }
}
