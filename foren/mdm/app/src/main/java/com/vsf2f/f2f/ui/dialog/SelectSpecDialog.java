package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.SelectSpecAdapter;
import com.vsf2f.f2f.bean.GetSpecBean;
import com.vsf2f.f2f.ui.utils.listener.SelectDialogListener;
import java.util.List;

/* loaded from: classes2.dex */
public class SelectSpecDialog extends BaseDialog {
    private SelectSpecAdapter adapter;
    private Context context;
    private ListView listView;
    private SelectSpecListener listener;
    private List<GetSpecBean> mySpec;
    private Button saveView;
    private String typeGuid = null;

    /* loaded from: classes2.dex */
    public interface SelectSpecListener {
        void onSelectDlgAddClick(String str, String str2);

        void onSelectDlgCancelClick();

        void onSelectDlgSaveClick(List<GetSpecBean> list);

        void onSelectDlgToCustomDlg();
    }

    public SelectSpecDialog(Context context) {
        super(context);
        this.context = context;
    }

    public void init(SelectSpecListener listener, String typeGuid, List<GetSpecBean> mySpec) {
        this.listener = listener;
        this.typeGuid = typeGuid;
        this.mySpec = mySpec;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_select_spec;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, 0.5f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.listView = (ListView) getView(R.id.listView);
        this.saveView = (Button) getView(R.id.btn_save_spec);
        this.adapter = new SelectSpecAdapter(this.context, this.saveView, this.mySpec, new SelectDialogListener() { // from class: com.vsf2f.f2f.ui.dialog.SelectSpecDialog.1
            @Override // com.vsf2f.f2f.ui.utils.listener.SelectDialogListener
            public void addClick(String specName, String Guid) {
                if (SelectSpecDialog.this.listener != null) {
                    SelectSpecDialog.this.listener.onSelectDlgAddClick(specName, Guid);
                }
                SelectSpecDialog.this.dismiss();
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.SelectDialogListener
            public void saveClick(List<GetSpecBean> selected) {
                if (SelectSpecDialog.this.listener != null) {
                    SelectSpecDialog.this.listener.onSelectDlgSaveClick(selected);
                }
                SelectSpecDialog.this.dismiss();
            }
        });
        this.listView.setAdapter((ListAdapter) this.adapter);
        setOnClickListener(R.id.iv_cancel);
        setOnClickListener(R.id.btn_custom_spec);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel /* 2131755878 */:
                if (this.listener != null) {
                    this.listener.onSelectDlgCancelClick();
                }
                dismiss();
                return;
            case R.id.btn_custom_spec /* 2131756344 */:
                if (this.listener != null) {
                    this.listener.onSelectDlgToCustomDlg();
                }
                dismiss();
                return;
            default:
                return;
        }
    }
}
