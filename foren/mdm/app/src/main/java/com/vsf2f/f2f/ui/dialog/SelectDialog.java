package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.area.ScreenUtils;

/* loaded from: classes2.dex */
public class SelectDialog extends BaseDialog implements View.OnClickListener {
    private SelectCallBack callBack;
    private int selectIndex = 0;
    private String title;
    private String[] value;

    /* loaded from: classes2.dex */
    public interface SelectCallBack {
        void select(int i);
    }

    public SelectDialog(Context context, String title, String[] value, SelectCallBack callBack) {
        super(context, R.style.Dialog_FS);
        this.title = "";
        this.callBack = callBack;
        this.title = title;
        this.value = value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseDialog, android.app.Dialog
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_selecter;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy((ScreenUtils.widthPixels(getContext()) * 4) / 5, 0.0f, 17);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
        ((TextView) getView(R.id.dlg_select_txtTitle)).setText(this.title);
        NumberPicker np_time = (NumberPicker) findViewById(R.id.dlg_select_txtPicker);
        np_time.setDescendantFocusability(393216);
        np_time.setDisplayedValues(this.value);
        np_time.setMinValue(0);
        np_time.setMaxValue(this.value.length - 1);
        np_time.setDividerPadding(10);
        np_time.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() { // from class: com.vsf2f.f2f.ui.dialog.SelectDialog.1
            @Override // android.widget.NumberPicker.OnValueChangeListener
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SelectDialog.this.selectIndex = newVal;
            }
        });
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
    }

    @Override // com.hy.frame.common.BaseDialog
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
        getWindow().setGravity(gravity);
        window.setAttributes(params);
    }

    @Override // com.hy.frame.common.BaseDialog, android.view.View.OnClickListener
    public void onClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.btn_sure /* 2131756169 */:
                this.callBack.select(this.selectIndex);
                return;
            default:
                return;
        }
    }
}
