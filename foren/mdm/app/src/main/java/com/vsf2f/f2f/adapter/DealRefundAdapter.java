package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.hy.frame.adapter.BaseAdapter;
import com.hy.frame.view.MyGridView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.RefundInfoBean;
import java.util.List;

/* loaded from: classes2.dex */
public class DealRefundAdapter extends BaseAdapter<DemandDetailBean> {
    private List<RefundInfoBean> data;

    public DealRefundAdapter(Context context, List<RefundInfoBean> data) {
        super(context);
        this.data = data;
    }

    public void setData(List<RefundInfoBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
    }

    @Override // android.widget.Adapter
    public RefundInfoBean getItem(int position) {
        return this.data.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        int i = 0;
        if (convertView == null) {
            ViewHolder holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_order_refund_detail, (ViewGroup) null);
            holder._mgvPic = (MyGridView) convertView.findViewById(R.id.item_refund_mgvPic);
            holder._txtDescribe = (TextView) convertView.findViewById(R.id.item_refund_txtDescribe);
            holder._txtTitle = (TextView) convertView.findViewById(R.id.item_refund_txtTitle);
            holder._txtTime = (TextView) convertView.findViewById(R.id.item_refund_txtTime);
            holder._lineTop = convertView.findViewById(R.id.v_lineTop);
            holder._lineEnd = convertView.findViewById(R.id.v_lineEnd);
            convertView.setTag(holder);
        }
        ViewHolder holder2 = (ViewHolder) convertView.getTag();
        RefundInfoBean item = getItem(position);
        holder2._txtTitle.setText(item.getOpTypeStr());
        holder2._txtDescribe.setText(item.getDescription());
        holder2._txtTime.setText(item.getOperationTime());
        holder2._lineTop.setVisibility(position == 0 ? 4 : 0);
        View view = holder2._lineEnd;
        if (position == getCount() - 1) {
            i = 4;
        }
        view.setVisibility(i);
        holder2._mgvPic.setAdapter((ListAdapter) new PicUrlPathAdapter(getContext(), item.getImgUrlList(), 4, R.dimen.margin_big, R.dimen.spacing));
        return convertView;
    }

    /* loaded from: classes2.dex */
    static class ViewHolder {
        View _lineEnd;
        View _lineTop;
        MyGridView _mgvPic;
        TextView _txtDescribe;
        TextView _txtTime;
        TextView _txtTitle;

        ViewHolder() {
        }
    }
}
