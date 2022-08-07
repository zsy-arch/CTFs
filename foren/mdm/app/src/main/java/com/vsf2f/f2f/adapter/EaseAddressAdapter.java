package com.vsf2f.f2f.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.HanziToPinyin;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.area.AddressPicker;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class EaseAddressAdapter extends ArrayAdapter<AddressPicker.City> implements SectionIndexer {
    private static final String TAG = "AddressAdapter";
    private Context context;
    private ArrayList<AddressPicker.City> copyLocationList = new ArrayList<>();
    protected Drawable initialLetterBg;
    protected int initialLetterColor;
    private LayoutInflater layoutInflater;
    private List<String> list;
    private ArrayList<AddressPicker.City> locationList;
    private MyFilter myFilter;
    private boolean notiyfyByFilter;
    private SparseIntArray positionOfSection;
    protected int primaryColor;
    protected int primarySize;
    private int res;
    private SparseIntArray sectionOfPosition;

    public EaseAddressAdapter(Context context, int resource, ArrayList<AddressPicker.City> objects) {
        super(context, resource, objects);
        this.res = resource;
        this.locationList = objects;
        this.context = context;
        this.copyLocationList.addAll(objects);
        this.layoutInflater = LayoutInflater.from(context);
    }

    /* loaded from: classes2.dex */
    private static class ViewHolder {
        TextView tv_header;
        TextView tv_name;

        private ViewHolder() {
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        AddressPicker.City myLocation = this.locationList.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.layoutInflater.inflate(this.res, parent, false);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_header = (TextView) convertView.findViewById(R.id.tv_header);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(myLocation.getAreaName() + "");
        String header = myLocation.getInitialLetter();
        if (position != 0 && (header == null || header.equals(getItem(position - 1).getInitialLetter()))) {
            holder.tv_header.setVisibility(8);
        } else if (TextUtils.isEmpty(header)) {
            holder.tv_header.setVisibility(8);
        } else {
            holder.tv_header.setVisibility(0);
            holder.tv_header.setText(header);
        }
        return convertView;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public AddressPicker.City getItem(int position) {
        return (AddressPicker.City) super.getItem(position);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return super.getCount();
    }

    @Override // android.widget.SectionIndexer
    public int getPositionForSection(int section) {
        return this.positionOfSection.get(section);
    }

    @Override // android.widget.SectionIndexer
    public int getSectionForPosition(int position) {
        return this.sectionOfPosition.get(position);
    }

    @Override // android.widget.SectionIndexer
    public Object[] getSections() {
        this.positionOfSection = new SparseIntArray();
        this.sectionOfPosition = new SparseIntArray();
        int count = getCount();
        this.list = new ArrayList();
        this.list.add(getContext().getString(R.string.search_header));
        this.positionOfSection.put(0, 0);
        this.sectionOfPosition.put(0, 0);
        for (int i = 1; i < count; i++) {
            String letter = getItem(i).getInitialLetter();
            int section = this.list.size() - 1;
            if (this.list.get(section) != null && !this.list.get(section).equals(letter)) {
                this.list.add(letter);
                section++;
                this.positionOfSection.put(section, i);
            }
            this.sectionOfPosition.put(i, section);
        }
        return this.list.toArray(new String[this.list.size()]);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Filterable
    public Filter getFilter() {
        if (this.myFilter == null) {
            this.myFilter = new MyFilter(this.locationList);
        }
        return this.myFilter;
    }

    /* loaded from: classes2.dex */
    protected class MyFilter extends Filter {
        ArrayList<AddressPicker.City> mOriginalList;

        public MyFilter(ArrayList<AddressPicker.City> myList) {
            this.mOriginalList = null;
            this.mOriginalList = myList;
        }

        @Override // android.widget.Filter
        protected synchronized Filter.FilterResults performFiltering(CharSequence prefix) {
            Filter.FilterResults results;
            results = new Filter.FilterResults();
            if (this.mOriginalList == null) {
                this.mOriginalList = new ArrayList<>();
            }
            EMLog.d(EaseAddressAdapter.TAG, "contacts original size: " + this.mOriginalList.size());
            EMLog.d(EaseAddressAdapter.TAG, "contacts copy size: " + EaseAddressAdapter.this.copyLocationList.size());
            if (prefix == null || prefix.length() == 0) {
                results.values = EaseAddressAdapter.this.copyLocationList;
                results.count = EaseAddressAdapter.this.copyLocationList.size();
            } else {
                String prefixString = prefix.toString();
                int count = this.mOriginalList.size();
                ArrayList<AddressPicker.City> newValues = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    AddressPicker.City location = this.mOriginalList.get(i);
                    String city = location.getAreaName();
                    if (city.contains(prefixString)) {
                        newValues.add(location);
                    } else {
                        String[] words = city.split(HanziToPinyin.Token.SEPARATOR);
                        int length = words.length;
                        int length2 = words.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length2) {
                                break;
                            } else if (words[i2].contains(prefixString)) {
                                newValues.add(location);
                                break;
                            } else {
                                i2++;
                            }
                        }
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }
            EMLog.d(EaseAddressAdapter.TAG, "contacts filter results size: " + results.count);
            return results;
        }

        @Override // android.widget.Filter
        protected synchronized void publishResults(CharSequence constraint, Filter.FilterResults results) {
            EaseAddressAdapter.this.locationList.clear();
            EaseAddressAdapter.this.locationList.addAll((ArrayList) results.values);
            EMLog.d(EaseAddressAdapter.TAG, "publish contacts filter results size: " + results.count);
            if (results.count > 0) {
                EaseAddressAdapter.this.notiyfyByFilter = true;
                EaseAddressAdapter.this.notifyDataSetChanged();
                EaseAddressAdapter.this.notiyfyByFilter = false;
            } else {
                EaseAddressAdapter.this.notifyDataSetInvalidated();
            }
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!this.notiyfyByFilter) {
            this.copyLocationList.clear();
            this.copyLocationList.addAll(this.locationList);
        }
    }

    public EaseAddressAdapter setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }

    public EaseAddressAdapter setPrimarySize(int primarySize) {
        this.primarySize = primarySize;
        return this;
    }

    public EaseAddressAdapter setInitialLetterBg(Drawable initialLetterBg) {
        this.initialLetterBg = initialLetterBg;
        return this;
    }

    public EaseAddressAdapter setInitialLetterColor(int initialLetterColor) {
        this.initialLetterColor = initialLetterColor;
        return this;
    }
}
