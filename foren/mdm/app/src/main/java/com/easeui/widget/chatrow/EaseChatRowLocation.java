package com.easeui.widget.chatrow;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMFileMessageBody;
import com.hyphenate.chat.EMLocationMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.LatLng;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.map.MapLocationActivity;
import java.io.File;

/* loaded from: classes.dex */
public class EaseChatRowLocation extends EaseChatRow {
    private String address;
    private EMFileMessageBody fileLocBody;
    private ImageView ivLocation;
    private double lat;
    private double lng;
    private EMLocationMessageBody locBody;
    private TextView tvLocation;

    public EaseChatRowLocation(Context context, EaseChatRow.GetClientListener listener, EMMessage message, int position, BaseAdapter adapter) {
        super(context, listener, message, position, adapter);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onInflateView() {
        this.inflater.inflate(this.message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_location : R.layout.ease_row_sent_location, this);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onFindViewById() {
        this.tvLocation = (TextView) findViewById(R.id.tv_location);
        this.ivLocation = (ImageView) findViewById(R.id.iv_location);
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onSetUpView() {
        try {
            if (this.message.getStringAttribute("type").equals("Location")) {
                this.fileLocBody = (EMFileMessageBody) this.message.getBody();
                File file = new File(this.fileLocBody.getLocalUrl());
                if (file.exists()) {
                    Glide.with(this.context).load(file).error((int) R.drawable.ease_location_msg).into(this.ivLocation);
                } else {
                    Glide.with(this.context).load(this.fileLocBody.getRemoteUrl()).error((int) R.drawable.ease_location_msg).into(this.ivLocation);
                }
                String latitude = this.message.getStringAttribute("latitude");
                String longitude = this.message.getStringAttribute("longitude");
                this.address = this.message.getStringAttribute("address");
                this.lat = Double.valueOf(latitude).doubleValue();
                this.lng = Double.valueOf(longitude).doubleValue();
                this.tvLocation.setText(this.address);
            }
        } catch (HyphenateException e) {
            try {
                this.locBody = (EMLocationMessageBody) this.message.getBody();
                this.tvLocation.setText(this.locBody.getAddress());
                this.lat = this.locBody.getLatitude();
                this.lng = this.locBody.getLongitude();
                this.address = this.locBody.getAddress() + "";
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
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
            } catch (HyphenateException e4) {
                e4.printStackTrace();
            }
        }
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onUpdateView() {
        this.adapter.notifyDataSetChanged();
    }

    @Override // com.easeui.widget.chatrow.EaseChatRow
    protected void onBubbleClick() {
        Intent intent = new Intent(this.context, MapLocationActivity.class);
        intent.putExtra("end_latitude", this.lat);
        intent.putExtra("end_longitude", this.lng);
        intent.putExtra("cus_address", this.address);
        this.activity.startActivity(intent);
    }

    /* loaded from: classes.dex */
    protected class MapClickListener implements View.OnClickListener {
        String address;
        LatLng location;

        public MapClickListener(LatLng loc, String address) {
            this.location = loc;
            this.address = address;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
        }
    }
}
