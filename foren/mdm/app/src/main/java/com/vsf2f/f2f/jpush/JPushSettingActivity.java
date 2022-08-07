package com.vsf2f.f2f.jpush;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class JPushSettingActivity extends InstrumentedActivity implements View.OnClickListener {
    TimePicker endTime;
    SharedPreferences.Editor mEditor;
    CheckBox mFriday;
    CheckBox mMonday;
    CheckBox mSaturday;
    Button mSetTime;
    SharedPreferences mSettings;
    CheckBox mSunday;
    CheckBox mThursday;
    CheckBox mTuesday;
    CheckBox mWednesday;
    TimePicker startTime;

    @Override // android.app.Activity
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.set_push_time);
        init();
        initListener();
    }

    @Override // cn.jpush.android.api.InstrumentedActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        initData();
    }

    private void init() {
        this.startTime = (TimePicker) findViewById(R.id.start_time);
        this.endTime = (TimePicker) findViewById(R.id.end_time);
        this.startTime.setIs24HourView(Boolean.valueOf(DateFormat.is24HourFormat(this)));
        this.endTime.setIs24HourView(Boolean.valueOf(DateFormat.is24HourFormat(this)));
        this.mSetTime = (Button) findViewById(R.id.bu_setTime);
        this.mMonday = (CheckBox) findViewById(R.id.cb_monday);
        this.mTuesday = (CheckBox) findViewById(R.id.cb_tuesday);
        this.mWednesday = (CheckBox) findViewById(R.id.cb_wednesday);
        this.mThursday = (CheckBox) findViewById(R.id.cb_thursday);
        this.mFriday = (CheckBox) findViewById(R.id.cb_friday);
        this.mSaturday = (CheckBox) findViewById(R.id.cb_saturday);
        this.mSunday = (CheckBox) findViewById(R.id.cb_sunday);
    }

    private void initListener() {
        this.mSetTime.setOnClickListener(this);
    }

    private void initData() {
        this.mSettings = getSharedPreferences(JPushUtil.PREFS_NAME, 0);
        String days = this.mSettings.getString(JPushUtil.PREFS_DAYS, "");
        if (!TextUtils.isEmpty(days)) {
            initAllWeek(false);
            for (String day : days.split(",")) {
                setWeek(day);
            }
        } else {
            initAllWeek(true);
        }
        this.startTime.setCurrentHour(Integer.valueOf(this.mSettings.getInt(JPushUtil.PREFS_START_TIME, 0)));
        this.endTime.setCurrentHour(Integer.valueOf(this.mSettings.getInt(JPushUtil.PREFS_END_TIME, 23)));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bu_setTime /* 2131757096 */:
                v.requestFocus();
                v.requestFocusFromTouch();
                setPushTime();
                return;
            default:
                return;
        }
    }

    private void setPushTime() {
        int startime = this.startTime.getCurrentHour().intValue();
        int endtime = this.endTime.getCurrentHour().intValue();
        if (startime > endtime) {
            Toast.makeText(this, "开始时间不能大于结束时间", 0).show();
            return;
        }
        StringBuffer daysSB = new StringBuffer();
        Set<Integer> days = new HashSet<>();
        if (this.mSunday.isChecked()) {
            days.add(0);
            daysSB.append("0,");
        }
        if (this.mMonday.isChecked()) {
            days.add(1);
            daysSB.append("1,");
        }
        if (this.mTuesday.isChecked()) {
            days.add(2);
            daysSB.append("2,");
        }
        if (this.mWednesday.isChecked()) {
            days.add(3);
            daysSB.append("3,");
        }
        if (this.mThursday.isChecked()) {
            days.add(4);
            daysSB.append("4,");
        }
        if (this.mFriday.isChecked()) {
            days.add(5);
            daysSB.append("5,");
        }
        if (this.mSaturday.isChecked()) {
            days.add(6);
            daysSB.append("6,");
        }
        JPushInterface.setPushTime(getApplicationContext(), days, startime, endtime);
        this.mEditor = this.mSettings.edit();
        this.mEditor.putString(JPushUtil.PREFS_DAYS, daysSB.toString());
        this.mEditor.putInt(JPushUtil.PREFS_START_TIME, startime);
        this.mEditor.putInt(JPushUtil.PREFS_END_TIME, endtime);
        this.mEditor.commit();
        MyToast.show(this, (int) R.string.setting_success);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setWeek(String day) {
        switch (Integer.valueOf(day).intValue()) {
            case 0:
                this.mSunday.setChecked(true);
                return;
            case 1:
                this.mMonday.setChecked(true);
                return;
            case 2:
                this.mTuesday.setChecked(true);
                return;
            case 3:
                this.mWednesday.setChecked(true);
                return;
            case 4:
                this.mThursday.setChecked(true);
                return;
            case 5:
                this.mFriday.setChecked(true);
                return;
            case 6:
                this.mSaturday.setChecked(true);
                return;
            default:
                return;
        }
    }

    private void initAllWeek(boolean isChecked) {
        this.mSunday.setChecked(isChecked);
        this.mMonday.setChecked(isChecked);
        this.mTuesday.setChecked(isChecked);
        this.mWednesday.setChecked(isChecked);
        this.mThursday.setChecked(isChecked);
        this.mFriday.setChecked(isChecked);
        this.mSaturday.setChecked(isChecked);
    }
}
