package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class MapChoiceDialog extends BaseDialog {
    private TextView txt_baidu;
    private TextView txt_gaode;

    public MapChoiceDialog(Context context) {
        super(context);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_map_choice;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.txt_baidu = (TextView) getViewAndClick(R.id.dlg_choice_baidu);
        this.txt_gaode = (TextView) getViewAndClick(R.id.dlg_choice_gaode);
        setOnClickListener(R.id.dlg_choice_btnWalk);
        setOnClickListener(R.id.dlg_choice_btnCar);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
        if (!HyUtil.isInstalled(getContext(), "com.baidu.BaiduMap")) {
            this.txt_baidu.setText("百度地图(未安装)");
            this.txt_baidu.setEnabled(false);
        } else {
            this.txt_baidu.setText("百度地图");
            this.txt_baidu.setEnabled(true);
        }
        if (!HyUtil.isInstalled(getContext(), "com.autonavi.minimap")) {
            this.txt_gaode.setText("高德地图(未安装)");
            this.txt_gaode.setEnabled(false);
            return;
        }
        this.txt_gaode.setEnabled(true);
        this.txt_gaode.setText("高德地图");
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.dlg_choice_btnWalk /* 2131756273 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 11);
                    return;
                }
                return;
            case R.id.dlg_choice_btnCar /* 2131756274 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 12);
                    return;
                }
                return;
            case R.id.dlg_choice_gaode /* 2131756275 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 22);
                    return;
                }
                return;
            case R.id.dlg_choice_baidu /* 2131756276 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 21);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
