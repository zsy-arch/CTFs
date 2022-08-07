package com.vsf2f.f2f.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.internal.view.SupportMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ShopMenuBean;
import com.vsf2f.f2f.ui.utils.listener.AddShopListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class AddShopAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ShopMenuBean> data = new ArrayList();
    private AddShopListener listener;
    private static int selectGroupId = -1;
    private static int selectChildId = -1;

    public AddShopAdapter(final Context context, View finishView, final AddShopListener listener) {
        this.context = context;
        this.listener = listener;
        finishView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.AddShopAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (AddShopAdapter.selectGroupId == -1 && AddShopAdapter.selectChildId == -1) {
                    MyToast.show(context, "请选择分类");
                    return;
                }
                if (AddShopAdapter.selectGroupId != -1 && AddShopAdapter.selectChildId == -1) {
                    ShopMenuBean shopMenuBean = (ShopMenuBean) AddShopAdapter.this.data.get(AddShopAdapter.selectGroupId);
                    listener.finish(shopMenuBean.getName(), Integer.toString(shopMenuBean.getId()));
                }
                if (AddShopAdapter.selectGroupId != -1 && AddShopAdapter.selectChildId != -1) {
                    ShopMenuBean shopMenuBean2 = (ShopMenuBean) AddShopAdapter.this.data.get(AddShopAdapter.selectGroupId);
                    String gName = shopMenuBean2.getName();
                    ShopMenuBean.ChildrenBean childrenBean = shopMenuBean2.getChildren().get(AddShopAdapter.selectChildId);
                    listener.finish(gName + " - " + childrenBean.getName(), Integer.toString(childrenBean.getId()));
                }
            }
        });
    }

    public void setData(List<ShopMenuBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.data.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int groupPosition) {
        return this.data.get(groupPosition).getUnderCount();
    }

    @Override // android.widget.ExpandableListAdapter
    public ShopMenuBean getGroup(int groupPosition) {
        return this.data.get(groupPosition);
    }

    @Override // android.widget.ExpandableListAdapter
    public ShopMenuBean.ChildrenBean getChild(int groupPosition, int childPosition) {
        return this.data.get(groupPosition).getChildren().get(childPosition);
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /* loaded from: classes2.dex */
    class GroupViewHolder {
        TextView addView;
        TextView cancelView;
        TextView nameView;
        ImageView selectView;

        GroupViewHolder() {
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            GroupViewHolder holder = new GroupViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_group_add_shop_menu, (ViewGroup) null);
            holder.selectView = (ImageView) convertView.findViewById(R.id.iv_select_item_group);
            holder.nameView = (TextView) convertView.findViewById(R.id.tv_name_item_group);
            holder.addView = (TextView) convertView.findViewById(R.id.tv_add_item_group);
            holder.cancelView = (TextView) convertView.findViewById(R.id.tv_cancel_item_group);
            convertView.setTag(holder);
        }
        final GroupViewHolder holder2 = (GroupViewHolder) convertView.getTag();
        ShopMenuBean group = getGroup(groupPosition);
        final int id = group.getId();
        int underCount = group.getUnderCount();
        holder2.nameView.setText(group.getName());
        if (underCount >= 5) {
            holder2.addView.setVisibility(8);
        } else {
            holder2.addView.setVisibility(0);
        }
        if (underCount > 0) {
            holder2.cancelView.setVisibility(8);
        } else {
            holder2.cancelView.setVisibility(0);
        }
        holder2.cancelView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.AddShopAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AddShopAdapter.this.listener.delete(Integer.toString(id));
            }
        });
        holder2.addView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.AddShopAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                final EditText inputServer = new EditText(AddShopAdapter.this.context);
                inputServer.setSingleLine();
                AlertDialog.Builder builder = new AlertDialog.Builder(AddShopAdapter.this.context);
                builder.setTitle("二级类名").setView(inputServer).setIcon(17301555).setNegativeButton("取消", (DialogInterface.OnClickListener) null).setPositiveButton("确认", new DialogInterface.OnClickListener() { // from class: com.vsf2f.f2f.adapter.AddShopAdapter.3.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        AddShopAdapter.this.listener.add(Integer.toString(id), inputServer.getText().toString());
                    }
                });
                builder.show();
            }
        });
        if (selectGroupId == groupPosition && selectChildId == -1) {
            holder2.selectView.setImageResource(R.drawable.icon_radio_red);
        } else {
            holder2.selectView.setImageResource(R.drawable.icon_radio_empty);
        }
        holder2.selectView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.AddShopAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                holder2.selectView.setImageResource(R.drawable.icon_radio_red);
                int unused = AddShopAdapter.selectGroupId = groupPosition;
                int unused2 = AddShopAdapter.selectChildId = -1;
                AddShopAdapter.this.notifyDataSetChanged();
            }
        });
        return convertView;
    }

    /* loaded from: classes2.dex */
    class ChildViewHolder {
        ImageView deleteView;
        TextView nameView;

        ChildViewHolder() {
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder holder;
        if (convertView == null) {
            holder = new ChildViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_child_add_shop_menu, (ViewGroup) null);
            holder.nameView = (TextView) convertView.findViewById(R.id.tv_name_item_child);
            holder.deleteView = (ImageView) convertView.findViewById(R.id.iv_delete_item_child);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        final ShopMenuBean.ChildrenBean child = getChild(groupPosition, childPosition);
        holder.nameView.setText(child.getName());
        holder.deleteView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.AddShopAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AddShopAdapter.this.listener.delete(Integer.toString(child.getId()));
            }
        });
        if (selectChildId == childPosition && selectGroupId == groupPosition) {
            holder.nameView.setSelected(true);
            holder.nameView.setTextColor(SupportMenu.CATEGORY_MASK);
        } else {
            holder.nameView.setSelected(false);
            holder.nameView.setTextColor(-16777216);
        }
        holder.nameView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.AddShopAdapter.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                holder.nameView.setSelected(true);
                holder.nameView.setTextColor(SupportMenu.CATEGORY_MASK);
                int unused = AddShopAdapter.selectChildId = childPosition;
                int unused2 = AddShopAdapter.selectGroupId = groupPosition;
                AddShopAdapter.this.notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return false;
    }
}
