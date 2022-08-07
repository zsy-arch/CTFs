package com.vsf2f.f2f.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.utils.LocationUtils;
import com.hy.frame.view.RotundityImageView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.FriendsListBean;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.area.ScreenUtils;
import java.util.concurrent.ExecutionException;

/* loaded from: classes2.dex */
public class NearbyPeopleDialog extends Dialog {
    private FriendsListBean.RowsBean bean;
    private View.OnClickListener clickListener;
    private Context context;
    Handler handler = new Handler() { // from class: com.vsf2f.f2f.ui.dialog.NearbyPeopleDialog.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null && msg.obj != null) {
                NearbyPeopleDialog.this.riv_user.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };
    private RotundityImageView riv_user;
    private TextView tv_content;
    private TextView tv_distance;
    private TextView tv_name;
    private TextView tv_price;
    private TextView tv_title;

    public NearbyPeopleDialog(Context context, View.OnClickListener clickListener) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_demand_des);
        initWindow();
        initView();
    }

    public void setData(FriendsListBean.RowsBean bean) {
        this.bean = bean;
    }

    public FriendsListBean.RowsBean getData() {
        return this.bean;
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getBitmap() {
        Bitmap bitmap;
        try {
            bitmap = null;
            try {
                bitmap = Glide.with(this.context).load(this.bean.getUserPic().getSpath()).asBitmap().into(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
                if (bitmap == null) {
                    bitmap = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.icon_header);
                }
                Message message = new Message();
                message.obj = bitmap;
                this.handler.sendMessage(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
                if (0 == 0) {
                    bitmap = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.icon_header);
                }
                Message message2 = new Message();
                message2.obj = bitmap;
                this.handler.sendMessage(message2);
            } catch (ExecutionException e2) {
                e2.printStackTrace();
                if (0 == 0) {
                    bitmap = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.icon_header);
                }
                Message message3 = new Message();
                message3.obj = bitmap;
                this.handler.sendMessage(message3);
            }
        } catch (Throwable th) {
            if (bitmap == null) {
                bitmap = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.icon_header);
            }
            Message message4 = new Message();
            message4.obj = bitmap;
            this.handler.sendMessage(message4);
            throw th;
        }
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.dialog.NearbyPeopleDialog.2
            @Override // java.lang.Runnable
            public void run() {
                NearbyPeopleDialog.this.getBitmap();
            }
        });
        String distance = LocationUtils.getDistance(this.bean.getLat(), this.bean.getLng());
        this.tv_name.setText(this.bean.getNickName());
        this.tv_content.setText(this.bean.getContent());
        this.tv_distance.setText(distance + "");
        this.tv_title.setVisibility(8);
        this.tv_price.setVisibility(8);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
    }

    protected void initView() {
        findViewById(R.id.ll_root).setOnClickListener(this.clickListener);
        this.riv_user = (RotundityImageView) findViewById(R.id.riv_user);
        this.tv_distance = (TextView) findViewById(R.id.tv_distance);
        this.tv_content = (TextView) findViewById(R.id.tv_content);
        this.tv_title = (TextView) findViewById(R.id.tv_title);
        this.tv_price = (TextView) findViewById(R.id.tv_price);
        this.tv_name = (TextView) findViewById(R.id.tv_name);
    }

    protected void initWindow() {
        windowDeploy((ScreenUtils.widthPixels(this.context) * 9) / 10, 0.0f, 17);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    protected void windowDeploy(float width, float height, int gravity) {
        Window window = getWindow();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        if (width == 0.0f) {
            params.width = -2;
        } else if (width <= 0.0f || width > 1.0f) {
            params.width = (int) width;
        } else {
            params.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * width);
        }
        if (height == 0.0f) {
            params.height = -2;
        } else if (height <= 0.0f || height > 1.0f) {
            params.height = (int) height;
        } else {
            params.height = (int) (getContext().getResources().getDisplayMetrics().heightPixels * height);
        }
        params.y = 200;
        getWindow().setGravity(gravity | 80);
        window.setAttributes(params);
    }
}
