package com.easeui.widget.chatrow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.common.MyHttpClient;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.chatrow.EaseChatRow;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.http.IMyHttpListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.exceptions.HyphenateException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.SharingBean;
import com.vsf2f.f2f.ui.identify.IdentifyActivity;
import com.vsf2f.f2f.ui.needs.demand.ChoiceServerActivity;
import com.vsf2f.f2f.ui.needs.demand.DemandInfoActivity;
import com.vsf2f.f2f.ui.needs.demand.OrderDetailActivity;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import com.vsf2f.f2f.ui.otay.ManorTreeDialog;
import com.vsf2f.f2f.ui.user.UserVipActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes.dex */
public class EaseChatRowTemp extends EaseChatRow {
    private Holder h;
    private ViewHolder vh;

    public EaseChatRowTemp(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(R.layout.ease_row_received_temp, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.vh = new ViewHolder();
        this.vh.vline = findViewById(R.id.temp_line);
        this.vh.txtTime = (TextView) findViewById(R.id.temp_txtTime);
        this.vh.txtTitle = (TextView) findViewById(R.id.temp_txtTitle);
        this.vh.txtContext = (TextView) findViewById(R.id.temp_txtContext);
        this.vh.llyKeyValue = (LinearLayout) findViewById(R.id.temp_llyRebate);
        this.vh.flyDetail = (FrameLayout) findViewById(R.id.temp_flyDetail);
        this.vh.txtDetail = (TextView) findViewById(R.id.temp_txtDetail);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    public void onSetUpView() {
        this.h = new Holder();
        try {
            String ext = this.message.getStringAttribute(MessageEncoder.ATTR_EXT);
            MyLog.e(MessageEncoder.ATTR_EXT, ext);
            JSONObject extJson = new JSONObject(ext);
            String bizType = extJson.optString("bizType", "");
            if (bizType.equals("opt_gxb")) {
                refreshGxb();
            }
            this.h.time = extJson.optString(f.az);
            this.h.title = extJson.optString("title");
            this.h.cover = extJson.optString("cover");
            this.h.url = extJson.optString("url");
            this.h.details_url = extJson.optString("details_url");
            this.h.details_text = extJson.optString("details_text");
            this.h.context = extJson.optString(av.aJ);
            this.h.tempDatas = extJson.optString("tempDatas");
            MyLog.e(bizType, this.h.tempDatas);
            this.vh.txtTime.setText(this.h.time);
            this.vh.txtTitle.setText(this.h.title);
            if (!TextUtils.isEmpty(this.h.details_text)) {
                this.vh.txtDetail.setText(this.h.details_text);
            } else {
                this.vh.txtDetail.setText("查看详情");
            }
            if (!TextUtils.isEmpty(this.h.details_url)) {
                this.vh.flyDetail.setVisibility(0);
            } else {
                this.vh.flyDetail.setVisibility(8);
            }
            if (!TextUtils.isEmpty(this.h.context)) {
                this.vh.txtContext.setText(this.h.context);
                this.vh.txtContext.setVisibility(0);
            } else {
                this.vh.txtContext.setVisibility(8);
            }
            JSONArray data = extJson.optJSONArray("datas");
            this.vh.llyKeyValue.removeAllViews();
            if (data != null) {
                this.vh.vline.setVisibility(0);
                for (int i = 0; i < data.length(); i++) {
                    JSONObject obj = data.optJSONObject(i);
                    TextView txt = new TextView(this.context);
                    txt.setTextColor(-12303292);
                    txt.setText(String.format("%s %s", obj.optString("key"), obj.optString("value")));
                    this.vh.llyKeyValue.addView(txt);
                }
            } else {
                this.vh.vline.setVisibility(8);
            }
        } catch (Exception e) {
            HyUtil.printException(e);
        }
        handleTextMessage();
    }

    private void refreshGxb() {
        new MyHttpClient(this.context, new IMyHttpListener() { // from class: com.easeui.widget.chatrow.EaseChatRowTemp.1
            @Override // com.hy.http.IMyHttpListener
            public void onRequestSuccess(ResultInfo result) {
                try {
                    UserShared.getInstance().saveShareMoney((SharingBean) result.getObj());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.hy.http.IMyHttpListener
            public void onRequestError(ResultInfo result) {
            }
        }).get(R.string.API_SHARING_USER, ComUtil.getZCApi(this.context, getContext().getString(R.string.API_SHARING_USER)), SharingBean.class);
    }

    private void showOpenManor() {
        new ManorTreeDialog(getContext()).show();
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
        String act;
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        if (!TextUtils.isEmpty(this.h.details_url)) {
            if (this.h.details_url.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                intent = new Intent(this.context, WebKitLocalActivity.class);
                bundle.putString(Constant.FLAG, this.h.details_url);
            } else if (this.h.details_url.startsWith("vsf2f://")) {
                MyLog.e(this.h.details_url);
                if (this.h.details_url.contains("?")) {
                    act = this.h.details_url.substring(this.h.details_url.indexOf("//") + 2, this.h.details_url.indexOf("?"));
                    bundle.putInt("id", Integer.parseInt(this.h.details_url.substring(this.h.details_url.indexOf("moId=") + 5, this.h.details_url.length())));
                } else {
                    act = this.h.details_url.substring(this.h.details_url.indexOf("//") + 2, this.h.details_url.length());
                }
                char c = 65535;
                switch (act.hashCode()) {
                    case -998029584:
                        if (act.equals("openchoosepage")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -943382998:
                        if (act.equals("openshareorderdetail")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -882956996:
                        if (act.equals("openservicedetail")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -700099527:
                        if (act.equals("openauthority")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -21644524:
                        if (act.equals("openserviceorderdetail")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1329048678:
                        if (act.equals("opensharedetail")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1413218975:
                        if (act.equals("opencertifydetail")) {
                            c = 5;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        intent = new Intent(this.context, OrderDetailActivity.class);
                        bundle.putBoolean("isService", true);
                        break;
                    case 1:
                        intent = new Intent(this.context, OrderDetailActivity.class);
                        break;
                    case 2:
                        intent = new Intent(this.context, ServiceInfoActivity.class);
                        break;
                    case 3:
                        intent = new Intent(this.context, DemandInfoActivity.class);
                        break;
                    case 4:
                        intent = new Intent(this.context, UserVipActivity.class);
                        break;
                    case 5:
                        intent = new Intent(this.context, IdentifyActivity.class);
                        break;
                    case 6:
                        intent = new Intent(this.context, ChoiceServerActivity.class);
                        break;
                }
            } else {
                return;
            }
            intent.putExtra(Constant.BUNDLE, bundle);
            this.activity.startActivity(intent);
        }
    }

    /* loaded from: classes.dex */
    static class ViewHolder {
        FrameLayout flyDetail;
        LinearLayout llyKeyValue;
        TextView txtContext;
        TextView txtDetail;
        TextView txtTime;
        TextView txtTitle;
        View vline;

        ViewHolder() {
        }
    }

    /* loaded from: classes.dex */
    static class Holder {
        String bizType;
        String context;
        String cover;
        String details_text;
        String details_url;
        String tempDatas;
        String time;
        String title;
        String url;

        Holder() {
        }
    }
}
