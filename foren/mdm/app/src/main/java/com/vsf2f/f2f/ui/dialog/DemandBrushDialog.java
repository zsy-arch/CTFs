package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.PopupWindow;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class DemandBrushDialog extends PopupWindow {
    private NavView nv_one;
    private NavView nv_three;
    private NavView nv_two;
    private NavGroup sub_groupFooter;
    private int brushType = 0;
    private int orderType = 0;
    private int serviceType = 2;
    private int[] oneData = {R.string.nav_smart, R.string.nav_publish_time, R.string.nav_distance};
    private int[] twoData = {R.string.nav_on_line, R.string.nav_face_to, R.string.nav_nolimit};

    /* loaded from: classes2.dex */
    public interface BrushType {
        void brushTypeCallBack(int i, int i2, @StringRes int i3, @StringRes int i4);
    }

    public void setBrushType(int brushType) {
        this.brushType = brushType;
    }

    public DemandBrushDialog(Context context, final BrushType brushTypeIml) {
        super(context);
        View v = View.inflate(context, R.layout.dlg_demand_brush, null);
        this.nv_one = (NavView) v.findViewById(R.id.nv_one);
        this.nv_two = (NavView) v.findViewById(R.id.nv_two);
        this.nv_three = (NavView) v.findViewById(R.id.nv_three);
        this.sub_groupFooter = (NavGroup) v.findViewById(R.id.sub_groupFooter);
        setContentView(v);
        this.sub_groupFooter.setCanClickCurrent();
        this.sub_groupFooter.setOnCheckedChangeListener(new NavGroup.OnCheckedChangeListener() { // from class: com.vsf2f.f2f.ui.dialog.DemandBrushDialog.1
            @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
            public void onCheckedChanged(NavGroup group, NavView nav, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.nv_one /* 2131756220 */:
                        if (DemandBrushDialog.this.brushType != 0) {
                            DemandBrushDialog.this.serviceType = 0;
                            break;
                        } else {
                            DemandBrushDialog.this.orderType = 0;
                            break;
                        }
                    case R.id.nv_two /* 2131756221 */:
                        if (DemandBrushDialog.this.brushType != 0) {
                            DemandBrushDialog.this.serviceType = 1;
                            break;
                        } else {
                            DemandBrushDialog.this.orderType = 1;
                            break;
                        }
                    case R.id.nv_three /* 2131756222 */:
                        if (DemandBrushDialog.this.brushType != 0) {
                            DemandBrushDialog.this.serviceType = 2;
                            break;
                        } else {
                            DemandBrushDialog.this.orderType = 2;
                            break;
                        }
                }
                DemandBrushDialog.this.dismiss();
                brushTypeIml.brushTypeCallBack(DemandBrushDialog.this.orderType, DemandBrushDialog.this.serviceType, DemandBrushDialog.this.oneData[DemandBrushDialog.this.orderType], DemandBrushDialog.this.twoData[DemandBrushDialog.this.serviceType]);
            }
        });
        setWidth(-1);
        setHeight(-2);
        setFocusable(true);
        setOutsideTouchable(true);
    }

    private int getShowText(int index) {
        return this.brushType == 0 ? this.oneData[index] : this.twoData[index];
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View anchor) {
        this.nv_one.setText(getShowText(0));
        this.nv_two.setText(getShowText(1));
        this.nv_three.setText(getShowText(2));
        if (Build.VERSION.SDK_INT >= 19) {
            super.showAsDropDown(anchor, 0, 0, 48);
        } else {
            super.showAsDropDown(anchor, 0, 0);
        }
    }
}
