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
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.MyApplication;
import com.easeui.domain.EaseUser;
import com.easeui.utils.EaseCommonUtils;
import com.easeui.utils.EaseSmileUtils;
import com.hy.frame.view.RotundityImageView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.area.ScreenUtils;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;

/* loaded from: classes2.dex */
public class ChatDialog extends Dialog {
    private EaseUser bean;
    private View.OnClickListener clickListener;
    private Context context;
    private EMConversation conversation;
    private DecimalFormat df = new DecimalFormat("0.00");
    Handler handler = new Handler() { // from class: com.vsf2f.f2f.ui.dialog.ChatDialog.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null && msg.obj != null) {
                ChatDialog.this.riv_user.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };
    private RotundityImageView riv_user;
    private TextView tv_content;
    private TextView tv_distance;
    private TextView tv_name;
    private LatLng userLatLnt;

    public ChatDialog(Context context, View.OnClickListener clickListener) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_layout_chat);
        initWindow();
        initView();
    }

    public void setData(EaseUser bean) {
        this.bean = bean;
    }

    public EaseUser getData() {
        return this.bean;
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.dialog.ChatDialog.1
            @Override // java.lang.Runnable
            public void run() {
                ChatDialog.this.getBitmap();
            }
        });
        this.userLatLnt = MyApplication.getCurrentLatlnt();
        this.tv_name.setText(this.bean.getNick());
        float distance = AMapUtils.calculateLineDistance(this.userLatLnt, new LatLng(Double.valueOf(this.bean.getLat()).doubleValue(), Double.valueOf(this.bean.getLng()).doubleValue()));
        if (distance / 1000.0f > 1.0f) {
            this.tv_distance.setText(this.df.format(distance / 1000.0f) + "km");
        } else {
            this.tv_distance.setText(this.df.format(distance) + "m");
        }
        this.conversation = EMClient.getInstance().chatManager().getConversation(this.bean.getUsername(), EaseCommonUtils.getConversationType(1), true);
        updateMsg(this.conversation.getLastMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getBitmap() {
        Bitmap bitmap;
        try {
            bitmap = null;
            try {
                bitmap = Glide.with(this.context).load(this.bean.getAvatar()).asBitmap().into(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
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
    protected void onStop() {
        super.onStop();
    }

    protected void initView() {
        findViewById(R.id.chatdlg_llyUser).setOnClickListener(this.clickListener);
        findViewById(R.id.chatdlg_llyMsg).setOnClickListener(this.clickListener);
        this.riv_user = (RotundityImageView) findViewById(R.id.riv_user);
        this.tv_distance = (TextView) findViewById(R.id.tv_distance);
        this.tv_content = (TextView) findViewById(R.id.tv_content);
        this.tv_name = (TextView) findViewById(R.id.tv_name);
    }

    public void updateMsg(EMMessage message) {
        if (message == null) {
            this.tv_content.setText("暂无新消息");
            return;
        }
        try {
            switch (message.getType()) {
                case TXT:
                    this.tv_content.setText(EaseSmileUtils.getSmiledSmall(this.context, ((EMTextMessageBody) message.getBody()).getMessage()), TextView.BufferType.NORMAL);
                    break;
                case IMAGE:
                    this.tv_content.setText("[图片]");
                    break;
                case LOCATION:
                    this.tv_content.setText("[位置]");
                    break;
                case VOICE:
                    this.tv_content.setText("[语音]");
                    break;
                case VIDEO:
                    this.tv_content.setText("[视频]");
                    break;
                case FILE:
                    this.tv_content.setText("[文件]");
                    break;
                default:
                    this.tv_content.setText("");
                    break;
            }
        } catch (Exception e) {
            this.tv_content.setText("消息有误");
        }
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
