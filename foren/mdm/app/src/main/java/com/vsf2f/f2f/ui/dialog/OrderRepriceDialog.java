package com.vsf2f.f2f.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.area.ScreenUtils;
import com.vsf2f.f2f.ui.view.PointLengthFilter;

/* loaded from: classes2.dex */
public class OrderRepriceDialog extends Dialog {
    private View.OnClickListener clickListener;
    private Context context;
    private EditText et_price;
    private String hint;
    private TextView textView6;
    private String title;

    public OrderRepriceDialog(Context context, View.OnClickListener clickListener) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_layout_reparice);
        initWindow();
        initView();
        this.et_price.setFilters(new InputFilter[]{new PointLengthFilter(7)});
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        if (!TextUtils.isEmpty(this.title)) {
            this.textView6.setText(this.title);
            this.et_price.setHint(this.hint);
        }
        this.et_price.setText("");
    }

    public void setTitle(String title, String hint) {
        this.title = title;
        this.hint = hint;
    }

    protected void initWindow() {
        windowDeploy((ScreenUtils.widthPixels(this.context) * 4) / 5, 0.0f, 17);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    protected void initView() {
        this.et_price = (EditText) findViewById(R.id.et_price);
        this.textView6 = (TextView) findViewById(R.id.textView6);
        findViewById(R.id.btn_sure_price).setOnClickListener(this.clickListener);
        findViewById(R.id.btn_cancel_price).setOnClickListener(this.clickListener);
    }

    public String getPrice() {
        return this.et_price.getText().toString();
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
        window.setAttributes(params);
        getWindow().setGravity(gravity);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }
}
