package com.vsf2f.f2f.ui.report;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.ReportAdapter;

/* loaded from: classes2.dex */
public class ReportMainActivity extends BaseActivity {
    private int page = 1;
    private TextView tv_sub_title;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_report_main;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.report_title, 0);
        this.tv_sub_title = (TextView) getView(R.id.report_txtTitle);
        reportMain();
    }

    private void reportMain() {
        this.page = 1;
        this.tv_sub_title.setText(R.string.report_title_main);
        String[] arText = this.context.getResources().getStringArray(R.array.report_main);
        ListView lv_report = (ListView) getView(R.id.lv_report);
        lv_report.setAdapter((ListAdapter) new ReportAdapter(arText));
        lv_report.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.report.ReportMainActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReportMainActivity.this.reportNext(position);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportNext(final int type) {
        this.page = 2;
        String[] arText = new String[0];
        switch (type) {
            case 0:
                arText = this.context.getResources().getStringArray(R.array.report_one);
                this.tv_sub_title.setText(R.string.report_title_one);
                break;
            case 1:
                arText = this.context.getResources().getStringArray(R.array.report_two);
                this.tv_sub_title.setText(R.string.report_title_two);
                break;
            case 2:
                arText = this.context.getResources().getStringArray(R.array.report_three);
                this.tv_sub_title.setText(R.string.report_title_three);
                break;
        }
        ListView lv_report = (ListView) getView(R.id.lv_report);
        lv_report.setAdapter((ListAdapter) new ReportAdapter(arText));
        lv_report.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.report.ReportMainActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = ReportMainActivity.this.getBundle();
                String reportType = position + "";
                if (type != 0) {
                    reportType = type + reportType;
                }
                bundle.putString("reportType", reportType);
                ReportMainActivity.this.startAct(ReportEditActivity.class, bundle);
                ReportMainActivity.this.finish();
            }
        });
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        if (this.page == 2) {
            reportMain();
        } else {
            super.onLeftClick();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.page == 2) {
            reportMain();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }
}
