package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.MultiDialogAdapter;
import com.vsf2f.f2f.bean.AddSpecBean;
import com.vsf2f.f2f.bean.GetSpecValueBean;
import java.util.List;

/* loaded from: classes2.dex */
public class MultiSelectDialog extends BaseDialog {
    private MultiDialogAdapter adapter;
    private TextView cancelView;
    private TextView confirmView;
    private Context context;
    private ListView listView;
    private MultiSelectListener listener;
    private List<GetSpecValueBean.ValuesBean> values;

    /* loaded from: classes2.dex */
    public interface MultiSelectListener {
        void onMultiDlgCancelClick();

        void onMultiDlgConfirmClick(List<AddSpecBean.SpecListsBean.ValuesBean> list);
    }

    public MultiSelectDialog(Context context, List<GetSpecValueBean.ValuesBean> values, MultiSelectListener listener) {
        super(context);
        this.values = values;
        this.context = context;
        this.listener = listener;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_multi_select;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(0.85f, 0.92f, 17);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.listView = (ListView) getView(R.id.listView);
        this.cancelView = (TextView) getView(R.id.tv_cancel);
        this.confirmView = (TextView) getView(R.id.tv_confirm);
        if (this.values.size() != 0) {
            this.adapter = new MultiDialogAdapter(this.context, this.cancelView, this.confirmView, this.values, new MultiSelectListener() { // from class: com.vsf2f.f2f.ui.dialog.MultiSelectDialog.1
                @Override // com.vsf2f.f2f.ui.dialog.MultiSelectDialog.MultiSelectListener
                public void onMultiDlgConfirmClick(List<AddSpecBean.SpecListsBean.ValuesBean> values) {
                    if (MultiSelectDialog.this.listener != null) {
                        MultiSelectDialog.this.listener.onMultiDlgConfirmClick(values);
                    }
                    MultiSelectDialog.this.dismiss();
                }

                @Override // com.vsf2f.f2f.ui.dialog.MultiSelectDialog.MultiSelectListener
                public void onMultiDlgCancelClick() {
                    MultiSelectDialog.this.dismiss();
                }
            });
            this.listView.setAdapter((ListAdapter) this.adapter);
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View view) {
    }
}
