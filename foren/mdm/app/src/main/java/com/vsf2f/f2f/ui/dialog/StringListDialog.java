package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cdlinglu.common.CommonAdapter;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.area.ScreenUtils;
import java.util.List;

/* loaded from: classes2.dex */
public class StringListDialog extends BaseDialog {
    private Context context;
    private onClickListListener listener;
    private List<String> stirngs;

    /* loaded from: classes2.dex */
    public interface onClickListListener {
        void onClickList(int i);
    }

    public StringListDialog(Context context, List<String> stirngs, onClickListListener listener) {
        super(context, R.style.Dialog_FS);
        this.context = context;
        this.stirngs = stirngs;
        this.listener = listener;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_staring_list;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(ScreenUtils.widthPixels(this.context), 0.0f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        ((ListView) findViewById(R.id.string_list)).setAdapter((ListAdapter) new CommonAdapter<String>(this.context, this.stirngs, R.layout.layout_txt) { // from class: com.vsf2f.f2f.ui.dialog.StringListDialog.1
            public void convert(final CommonAdapter.ViewHolder helper, String item) {
                TextView txt = (TextView) helper.getView(R.id.txt);
                txt.setText(item);
                txt.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.dialog.StringListDialog.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (StringListDialog.this.listener != null) {
                            StringListDialog.this.listener.onClickList(helper.getPosition());
                        }
                        StringListDialog.this.dismiss();
                    }
                });
            }
        });
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
    }
}
