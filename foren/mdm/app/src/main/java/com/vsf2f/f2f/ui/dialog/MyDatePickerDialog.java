package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.MyCalendar;

/* loaded from: classes2.dex */
public class MyDatePickerDialog extends BaseDialog implements DatePicker.OnDateChangedListener {
    private static final String DAY = "day";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private MyCalendar mCalendar;
    private TextView mDateEver;
    private DatePicker mDatePicker;
    private OnDateSetListener mDateSetListener;
    private TextView mDateValue;

    /* loaded from: classes2.dex */
    public interface OnDateSetListener {
        void onDateSet(DatePicker datePicker, MyCalendar myCalendar);
    }

    public MyDatePickerDialog(Context context, OnDateSetListener callBack) {
        this(context, callBack, new MyCalendar());
    }

    public MyDatePickerDialog(Context context, OnDateSetListener listener, MyCalendar calendar) {
        super(context);
        this.mDateSetListener = listener;
        if (calendar == null) {
            this.mCalendar = new MyCalendar();
        } else {
            this.mCalendar = calendar;
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_date_picker;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.mDatePicker = (DatePicker) getView(R.id.datePicker);
        this.mDatePicker.init(this.mCalendar.getYear(), this.mCalendar.getMonth(), this.mCalendar.getDay(), this);
        this.mDateValue = (TextView) getView(R.id.datePicker_value);
        this.mDateValue.setText(this.mCalendar.getYear() + "年" + this.mCalendar.getMonth() + "月" + this.mCalendar.getDay() + "日");
        this.mDateEver = (TextView) getViewAndClick(R.id.date_picker_ever);
        setOnClickListener(R.id.date_picker_ok);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.date_picker_ever /* 2131756217 */:
                if (this.mDateSetListener != null) {
                    this.mDatePicker.clearFocus();
                    this.mCalendar.setEver(true);
                    this.mDateSetListener.onDateSet(this.mDatePicker, this.mCalendar);
                    cancel();
                    return;
                }
                return;
            case R.id.date_picker_ok /* 2131756218 */:
                if (this.mDateSetListener != null) {
                    this.mDatePicker.clearFocus();
                    this.mCalendar = new MyCalendar(this.mDatePicker.getYear(), this.mDatePicker.getMonth() + 1, this.mDatePicker.getDayOfMonth());
                    this.mCalendar.setEver(false);
                    this.mDateSetListener.onDateSet(this.mDatePicker, this.mCalendar);
                    cancel();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public MyCalendar getCalendar() {
        return this.mCalendar;
    }

    public void setCanEver() {
        if (this.mDateEver != null) {
            this.mDateEver.setVisibility(0);
        }
    }

    public void reShow() {
        myShow();
    }

    public void myShow() {
        show();
    }

    @Override // android.widget.DatePicker.OnDateChangedListener
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        this.mDatePicker.init(year, month, day, this);
        this.mDateValue.setText(year + "年" + (month + 1) + "月" + day + "日");
    }

    public DatePicker getDatePicker() {
        return this.mDatePicker;
    }

    public void updateDate(int year, int monthOfYear, int dayOfMonth) {
        this.mDatePicker.updateDate(year, monthOfYear, dayOfMonth);
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Bundle state = super.onSaveInstanceState();
        state.putInt(YEAR, this.mDatePicker.getYear());
        state.putInt(MONTH, this.mDatePicker.getMonth());
        state.putInt(DAY, this.mDatePicker.getDayOfMonth());
        return state;
    }

    @Override // android.app.Dialog
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.mDatePicker.init(savedInstanceState.getInt(YEAR), savedInstanceState.getInt(MONTH), savedInstanceState.getInt(DAY), this);
    }
}
