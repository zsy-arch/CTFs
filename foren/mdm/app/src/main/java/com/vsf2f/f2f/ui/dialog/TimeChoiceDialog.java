package com.vsf2f.f2f.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.area.ScreenUtils;

/* loaded from: classes2.dex */
public class TimeChoiceDialog extends Dialog implements View.OnClickListener {
    private TimeCallBack callBack;
    private Context context;
    private NumberPicker np_time;
    private int choiceTime = 3;
    private String[] value = {"3天", "7天", "15天", "30天"};

    /* loaded from: classes2.dex */
    public interface TimeCallBack {
        void palyTime(int i);
    }

    public TimeChoiceDialog(Context context, TimeCallBack callBack) {
        super(context, R.style.Dialog_FS);
        this.context = context;
        this.callBack = callBack;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_time_choice);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
        this.np_time = (NumberPicker) findViewById(R.id.np_time);
        this.np_time.setDisplayedValues(this.value);
        this.np_time.setMinValue(0);
        this.np_time.setMaxValue(this.value.length - 1);
        this.np_time.setDividerPadding(10);
        this.np_time.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() { // from class: com.vsf2f.f2f.ui.dialog.TimeChoiceDialog.1
            @Override // android.widget.NumberPicker.OnValueChangeListener
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                switch (newVal) {
                    case 0:
                        TimeChoiceDialog.this.choiceTime = 3;
                        return;
                    case 1:
                        TimeChoiceDialog.this.choiceTime = 7;
                        return;
                    case 2:
                        TimeChoiceDialog.this.choiceTime = 15;
                        return;
                    case 3:
                        TimeChoiceDialog.this.choiceTime = 30;
                        return;
                    default:
                        return;
                }
            }
        });
        initWindow();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        this.np_time.setValue(1);
        this.choiceTime = 7;
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

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.btn_sure /* 2131756169 */:
                this.callBack.palyTime(this.choiceTime);
                return;
            default:
                return;
        }
    }
}
