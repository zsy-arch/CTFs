package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.AddSpecBean;
import com.vsf2f.f2f.bean.GetSpecValueBean;
import com.vsf2f.f2f.ui.dialog.MultiSelectDialog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MultiDialogAdapter extends BaseAdapter {
    private Context context;
    private List<GetSpecValueBean.ValuesBean> values;
    private List<AddSpecBean.SpecListsBean.ValuesBean> selectValues = new ArrayList();
    private List<String> selectPosition = new ArrayList();

    public MultiDialogAdapter(final Context context, TextView cancelView, TextView confirmView, final List<GetSpecValueBean.ValuesBean> values, final MultiSelectDialog.MultiSelectListener listener) {
        this.context = context;
        this.values = values;
        cancelView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MultiDialogAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                listener.onMultiDlgCancelClick();
            }
        });
        confirmView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MultiDialogAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MultiDialogAdapter.this.selectPosition.size() == 0) {
                    MyToast.show(context, "请选择规格值");
                    return;
                }
                for (int i = 0; i < MultiDialogAdapter.this.selectPosition.size(); i++) {
                    GetSpecValueBean.ValuesBean valuesBean = (GetSpecValueBean.ValuesBean) values.get(Integer.parseInt((String) MultiDialogAdapter.this.selectPosition.get(i)));
                    AddSpecBean.SpecListsBean.ValuesBean bean = new AddSpecBean.SpecListsBean.ValuesBean();
                    bean.setGuid(valuesBean.getGuid());
                    bean.setSpecValue(valuesBean.getSpecValue());
                    MultiDialogAdapter.this.selectValues.add(bean);
                }
                listener.onMultiDlgConfirmClick(MultiDialogAdapter.this.selectValues);
            }
        });
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.values.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            ViewHolder viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_multi_dialog, (ViewGroup) null);
            viewHolder.nameView = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.checkView = (CheckBox) convertView.findViewById(R.id.cb_check);
            convertView.setTag(viewHolder);
        }
        final ViewHolder viewHolder2 = (ViewHolder) convertView.getTag();
        viewHolder2.nameView.setText(this.values.get(position).getSpecValue());
        convertView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.MultiDialogAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (viewHolder2.checkView.isChecked()) {
                    viewHolder2.checkView.setChecked(false);
                    MultiDialogAdapter.this.selectPosition.remove(Integer.toString(position));
                    return;
                }
                viewHolder2.checkView.setChecked(true);
                MultiDialogAdapter.this.selectPosition.add(Integer.toString(position));
            }
        });
        return convertView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ViewHolder {
        CheckBox checkView;
        TextView nameView;

        ViewHolder() {
            MultiDialogAdapter.this = this$0;
        }
    }
}
