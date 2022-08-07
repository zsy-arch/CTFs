package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.listener.CustomDialogListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class CustomSpecAdapter extends BaseAdapter {
    private Context context;
    private List<String> data = new ArrayList();
    private String specName;

    public CustomSpecAdapter(final List<String> values, final Context context, Button saveView, ImageView cancelView, final String specName, final String guid, final CustomDialogListener listener) {
        this.specName = null;
        this.context = context;
        this.specName = specName;
        if (this.data.size() == 0) {
            this.data.add("");
            this.data.add("");
        } else {
            this.data.clear();
            this.data.add("");
            this.data.add("");
        }
        saveView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CustomSpecAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (!TextUtils.isEmpty(specName)) {
                    for (int i = 1; i < CustomSpecAdapter.this.data.size(); i++) {
                        String value = (String) CustomSpecAdapter.this.data.get(i);
                        if (TextUtils.isEmpty(value) || "".equals(value)) {
                            MyToast.show(context, "请填写规格值");
                            return;
                        }
                    }
                    for (int i2 = 0; i2 < CustomSpecAdapter.this.data.size() - 1; i2++) {
                        String temp = (String) CustomSpecAdapter.this.data.get(i2);
                        for (int j = i2 + 1; j < CustomSpecAdapter.this.data.size(); j++) {
                            if (temp.equalsIgnoreCase((String) CustomSpecAdapter.this.data.get(j))) {
                                MyToast.show(context, temp + " 规格值重复");
                                return;
                            }
                        }
                    }
                    if (!(values == null || values.size() == 0)) {
                        for (int i3 = 1; i3 < CustomSpecAdapter.this.data.size(); i3++) {
                            String addValue = (String) CustomSpecAdapter.this.data.get(i3);
                            for (int j2 = 0; j2 < values.size(); j2++) {
                                if (addValue.equalsIgnoreCase((String) values.get(j2))) {
                                    MyToast.show(context, "规格值 " + addValue + " 已经存在");
                                    return;
                                }
                            }
                        }
                    }
                    listener.addClick(guid, CustomSpecAdapter.this.data);
                    CustomSpecAdapter.this.data.clear();
                    CustomSpecAdapter.this.data.add("");
                    CustomSpecAdapter.this.data.add("");
                } else if (CustomSpecAdapter.this.data.size() < 2) {
                    MyToast.show(context, "请输入规格和至少一种规格值");
                } else {
                    String spec = (String) CustomSpecAdapter.this.data.get(0);
                    String firstValue = (String) CustomSpecAdapter.this.data.get(1);
                    if (TextUtils.isEmpty(spec) || "".equals(spec) || TextUtils.isEmpty(firstValue) || "".equals(firstValue)) {
                        MyToast.show(context, "请输入规格和至少一种规格值");
                        return;
                    }
                    for (int i4 = 0; i4 < CustomSpecAdapter.this.data.size() - 1; i4++) {
                        String temp2 = (String) CustomSpecAdapter.this.data.get(i4);
                        for (int j3 = i4 + 1; j3 < CustomSpecAdapter.this.data.size(); j3++) {
                            if (temp2.equalsIgnoreCase((String) CustomSpecAdapter.this.data.get(j3))) {
                                MyToast.show(context, temp2 + " 规格值重复");
                                return;
                            }
                        }
                    }
                    listener.saveClick(CustomSpecAdapter.this.data);
                    CustomSpecAdapter.this.data.clear();
                    CustomSpecAdapter.this.data.add("");
                    CustomSpecAdapter.this.data.add("");
                }
            }
        });
        cancelView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CustomSpecAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                CustomSpecAdapter.this.data.clear();
                CustomSpecAdapter.this.data.add("");
                CustomSpecAdapter.this.data.add("");
                listener.cancelClick();
            }
        });
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.data.size();
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_custom_spec, (ViewGroup) null);
            holder.nameView = (TextView) convertView.findViewById(R.id.tv_name_item);
            holder.elseView = (TextView) convertView.findViewById(R.id.tv_else_item);
            holder.enterView = (EditText) convertView.findViewById(R.id.et_enter_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            holder.nameView.setText("规格:");
            holder.enterView.setHint("如 颜色 大小");
            holder.elseView.setVisibility(8);
        } else if (position == 1) {
            holder.nameView.setText("规格值:");
            holder.enterView.setHint("如 颜色 大小");
            holder.elseView.setVisibility(0);
            holder.elseView.setText("新增");
        } else {
            holder.nameView.setText("规格值:");
            holder.enterView.setHint("如 颜色 大小");
            holder.elseView.setVisibility(0);
            holder.elseView.setText("删除");
        }
        if (position != 0 || TextUtils.isEmpty(this.specName)) {
            try {
                holder.enterView.setText(this.data.get(position));
                holder.enterView.setEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            holder.enterView.setText(this.specName);
            holder.enterView.setEnabled(false);
        }
        holder.enterView.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.adapter.CustomSpecAdapter.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                try {
                    CustomSpecAdapter.this.data.set(position, editable.toString());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        holder.elseView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.CustomSpecAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (position == 1) {
                    CustomSpecAdapter.this.data.add("");
                    CustomSpecAdapter.this.notifyDataSetChanged();
                } else if (position >= 2) {
                    CustomSpecAdapter.this.data.remove(position);
                    CustomSpecAdapter.this.notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    /* loaded from: classes2.dex */
    class ViewHolder {
        TextView elseView;
        EditText enterView;
        TextView nameView;

        ViewHolder() {
        }
    }
}
