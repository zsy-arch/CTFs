package com.vsf2f.f2f.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.area.ScreenUtils;

/* loaded from: classes2.dex */
public class SureChoiceDialog extends Dialog {
    private Button btn_cancel;
    private Button btn_sure;
    private View.OnClickListener clickListener;
    private String content;
    private Context context;
    private String title;
    private TextView tv_msg;
    private TextView tv_title;

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public SureChoiceDialog(Context context, View.OnClickListener clickListener) {
        super(context);
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.dlg_sure_choice);
        this.btn_cancel = (Button) findViewById(R.id.btn_cancel);
        this.btn_sure = (Button) findViewById(R.id.btn_sure);
        this.tv_title = (TextView) findViewById(R.id.tv_title);
        this.tv_msg = (TextView) findViewById(R.id.tv_msg);
        this.btn_cancel.setOnClickListener(this.clickListener);
        this.btn_sure.setOnClickListener(this.clickListener);
        initWindow();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        this.tv_title.setText(this.title + "");
        this.tv_msg.setText(this.content);
    }

    private void initWindow() {
        windowDeploy((ScreenUtils.widthPixels(this.context) * 4) / 5, 0.0f, 17);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    private void windowDeploy(float width, float height, int gravity) {
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
        getWindow().setGravity(gravity);
        window.setAttributes(params);
    }
}
