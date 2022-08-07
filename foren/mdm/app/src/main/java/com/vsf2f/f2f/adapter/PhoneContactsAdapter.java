package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.PhoneBean;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class PhoneContactsAdapter extends BaseAdapter {
    private Context context;
    private Map<String, PhoneBean> dbMap;
    private boolean isSelect;
    private OnPhoneListener listener;
    private List<PhoneBean> mobileList;

    /* loaded from: classes2.dex */
    public interface OnPhoneListener {
        void onItemClick(PhoneBean phoneBean);

        void onSelect(PhoneBean phoneBean);

        void unSelect(PhoneBean phoneBean);
    }

    public PhoneContactsAdapter(Context context, List<PhoneBean> mobileMap, Map<String, PhoneBean> dbMap) {
        this.context = context;
        this.dbMap = dbMap;
        this.mobileList = mobileMap;
    }

    public void setMobileMap(List<PhoneBean> mobileList) {
        this.mobileList = mobileList;
        notifyDataSetChanged();
    }

    public void setDbMap(Map<String, PhoneBean> dbMap) {
        this.dbMap = dbMap;
        notifyDataSetChanged();
    }

    public void setSelect(boolean select) {
        this.isSelect = select;
        notifyDataSetChanged();
    }

    public void setListener(OnPhoneListener listener) {
        this.listener = listener;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.mobileList == null) {
            return 0;
        }
        return this.mobileList.size();
    }

    @Override // android.widget.Adapter
    public PhoneBean getItem(int position) {
        return this.mobileList == null ? new PhoneBean() : this.mobileList.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            ViewHolder holder = new ViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_phone_contacts, (ViewGroup) null);
            holder.check = (ImageView) convertView.findViewById(R.id.check);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.status = (ImageView) convertView.findViewById(R.id.status);
            holder.nameView = (TextView) convertView.findViewById(R.id.tv_name);
            holder.headerView = (TextView) convertView.findViewById(R.id.header);
            holder.numberView = (TextView) convertView.findViewById(R.id.tv_number);
            holder.parent = (LinearLayout) convertView.findViewById(R.id.phone_parent);
            convertView.setTag(holder);
        }
        ViewHolder holder2 = (ViewHolder) convertView.getTag();
        PhoneBean info = getItem(position);
        String name = info.getName();
        String number = info.getPhone();
        holder2.nameView.setText(name);
        holder2.numberView.setText(number);
        holder2.avatar.setImageBitmap(createTextImage(name));
        String header = info.getInitial();
        if (position != 0 && (header == null || header.equals(getItem(position - 1).getInitial()))) {
            holder2.headerView.setVisibility(8);
        } else if (TextUtils.isEmpty(header)) {
            holder2.headerView.setVisibility(8);
        } else {
            holder2.headerView.setVisibility(0);
            holder2.headerView.setText(header);
        }
        PhoneBean bean = null;
        if (this.dbMap != null) {
            bean = this.dbMap.get(number);
        }
        if (bean == null) {
            bean = new PhoneBean(number, name, 0);
        }
        holder2.check.setSelected(bean.isCheck());
        switch (bean.getType()) {
            case 0:
                holder2.status.setImageResource(0);
                if (!this.isSelect) {
                    holder2.check.setVisibility(8);
                    holder2.parent.setOnClickListener(null);
                    break;
                } else {
                    setSelect(holder2.parent, info, holder2.check.isSelected());
                    holder2.check.setVisibility(0);
                    break;
                }
            case 1:
                holder2.status.setImageResource(R.drawable.icon_txt_registed);
                if (this.isSelect) {
                    holder2.check.setVisibility(4);
                } else {
                    holder2.check.setVisibility(8);
                }
                setItemClick(holder2.parent, info);
                break;
            case 2:
                holder2.status.setImageResource(R.drawable.icon_txt_friend);
                if (this.isSelect) {
                    holder2.check.setVisibility(4);
                } else {
                    holder2.check.setVisibility(8);
                }
                setItemClick(holder2.parent, info);
                break;
        }
        return convertView;
    }

    /* loaded from: classes2.dex */
    class ViewHolder {
        ImageView avatar;
        ImageView check;
        TextView headerView;
        TextView nameView;
        TextView numberView;
        LinearLayout parent;
        ImageView status;

        ViewHolder() {
        }
    }

    public void setSelect(View v, final PhoneBean phoneBean, final boolean isSelect) {
        v.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.PhoneContactsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhoneContactsAdapter.this.listener == null) {
                    return;
                }
                if (isSelect) {
                    PhoneContactsAdapter.this.listener.unSelect(phoneBean);
                } else {
                    PhoneContactsAdapter.this.listener.onSelect(phoneBean);
                }
            }
        });
    }

    public void setItemClick(View v, final PhoneBean phoneBean) {
        v.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.adapter.PhoneContactsAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhoneContactsAdapter.this.listener != null) {
                    PhoneContactsAdapter.this.listener.onItemClick(phoneBean);
                }
            }
        });
    }

    public static Bitmap createTextImage(String innerTxt) {
        String imgtxt;
        if (innerTxt.length() <= 2) {
            imgtxt = innerTxt;
        } else if (HyUtil.hasChinese(innerTxt)) {
            imgtxt = innerTxt.substring(0, 2);
        } else {
            imgtxt = innerTxt.substring(0, 3);
        }
        Bitmap bm = Bitmap.createBitmap(100, 100, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint();
        paint.setColor(-7829368);
        paint.setTextSize(33.0f);
        paint.setFakeBoldText(true);
        canvas.drawText(imgtxt, 17, 60, paint);
        return bm;
    }
}
