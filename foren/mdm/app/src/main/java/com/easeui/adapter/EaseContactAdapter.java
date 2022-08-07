package com.easeui.adapter;

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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.easeui.domain.EaseUser;
import com.hy.frame.util.MyLog;
import com.hy.frame.view.RoundImageView;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.HanziToPinyin;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EaseContactAdapter extends ArrayAdapter<EaseUser> implements SectionIndexer {
    private static final String TAG = "ContactAdapter";
    protected Drawable initialLetterBg;
    protected int initialLetterColor;
    private LayoutInflater layoutInflater;
    private List<String> list;
    private MyFilter myFilter;
    private boolean notiyfyByFilter;
    private SparseIntArray positionOfSection;
    protected int primaryColor;
    protected int primarySize;
    private int res;
    private SparseIntArray sectionOfPosition;
    private List<EaseUser> userList;
    private int lastFilterLength = 0;
    private List<EaseUser> copyUserList = new ArrayList();

    public EaseContactAdapter(Context context, int resource, List<EaseUser> objects) {
        super(context, resource, objects);
        this.res = resource;
        this.userList = objects;
        this.copyUserList.addAll(objects);
        this.layoutInflater = LayoutInflater.from(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ViewHolder {
        RoundImageView avatar;
        TextView conntctionView;
        TextView headerView;
        TextView nameView;

        private ViewHolder() {
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            if (this.res == 0) {
                convertView = this.layoutInflater.inflate(R.layout.item_row_contact, parent, false);
            } else {
                convertView = this.layoutInflater.inflate(this.res, (ViewGroup) null);
            }
            holder.avatar = (RoundImageView) convertView.findViewById(R.id.avatar);
            holder.nameView = (TextView) convertView.findViewById(R.id.name);
            holder.headerView = (TextView) convertView.findViewById(R.id.header);
            holder.conntctionView = (TextView) convertView.findViewById(R.id.connection);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        EaseUser user = getItem(position);
        holder.nameView.setText(user.getNick());
        Glide.with(getContext()).load(user.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).error((int) R.mipmap.def_head).into(holder.avatar);
        String friendsCount = user.getFriendsCount();
        if (!TextUtils.isEmpty(friendsCount)) {
            holder.conntctionView.setText(friendsCount);
        } else {
            holder.conntctionView.setText("0");
        }
        String header = user.getInitialLetter();
        if (position != 0 && (header == null || header.equals(getItem(position - 1).getInitialLetter()))) {
            holder.headerView.setVisibility(8);
        } else if (TextUtils.isEmpty(header)) {
            holder.headerView.setVisibility(8);
        } else {
            holder.headerView.setVisibility(0);
            holder.headerView.setText(header);
        }
        if (this.primaryColor != 0) {
            holder.nameView.setTextColor(this.primaryColor);
        }
        if (this.primarySize != 0) {
            holder.nameView.setTextSize(0, this.primarySize);
        }
        if (this.initialLetterBg != null) {
            holder.headerView.setBackgroundDrawable(this.initialLetterBg);
        }
        if (this.initialLetterColor != 0) {
            holder.headerView.setTextColor(this.initialLetterColor);
        }
        return convertView;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public EaseUser getItem(int position) {
        return (EaseUser) super.getItem(position);
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
            this.myFilter = new MyFilter(this.userList);
        }
        return this.myFilter;
    }

    /* loaded from: classes.dex */
    public class MyFilter extends Filter {
        List<EaseUser> mOriginalList;

        private MyFilter(List<EaseUser> myList) {
            EaseContactAdapter.this = this$0;
            this.mOriginalList = null;
            this.mOriginalList = myList;
        }

        @Override // android.widget.Filter
        protected synchronized Filter.FilterResults performFiltering(CharSequence prefix) {
            Filter.FilterResults results;
            results = new Filter.FilterResults();
            if (this.mOriginalList == null) {
                this.mOriginalList = new ArrayList();
            }
            MyLog.e(EaseContactAdapter.TAG, "contacts original size: " + this.mOriginalList.size());
            MyLog.e(EaseContactAdapter.TAG, "contacts copy size: " + EaseContactAdapter.this.copyUserList.size());
            if (prefix == null || prefix.length() == 0) {
                this.mOriginalList = EaseContactAdapter.this.copyUserList;
                results.values = EaseContactAdapter.this.copyUserList;
                results.count = EaseContactAdapter.this.copyUserList.size();
            } else {
                if (prefix.length() < EaseContactAdapter.this.lastFilterLength) {
                    this.mOriginalList = EaseContactAdapter.this.copyUserList;
                }
                EaseContactAdapter.this.lastFilterLength = prefix.length();
                String prefixString = prefix.toString();
                ArrayList<EaseUser> newValues = new ArrayList<>();
                for (int i = 0; i < this.mOriginalList.size(); i++) {
                    EaseUser user = this.mOriginalList.get(i);
                    String username = user.getUsername();
                    String usernick = user.getNick();
                    if (username.contains(prefixString)) {
                        newValues.add(user);
                    } else if (usernick.contains(prefixString)) {
                        newValues.add(user);
                    } else {
                        String[] words = username.split(HanziToPinyin.Token.SEPARATOR);
                        int length = words.length;
                        int length2 = words.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length2) {
                                break;
                            } else if (words[i2].contains(prefixString)) {
                                newValues.add(user);
                                break;
                            } else {
                                i2++;
                            }
                        }
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
                this.mOriginalList = newValues;
            }
            EMLog.d(EaseContactAdapter.TAG, "contacts filter results size: " + results.count);
            return results;
        }

        @Override // android.widget.Filter
        protected synchronized void publishResults(CharSequence constraint, Filter.FilterResults results) {
            EaseContactAdapter.this.userList.clear();
            EaseContactAdapter.this.userList.addAll((List) results.values);
            EMLog.d(EaseContactAdapter.TAG, "publish contacts filter results size: " + results.count);
            if (results.count > 0) {
                EaseContactAdapter.this.notiyfyByFilter = true;
                EaseContactAdapter.this.notifyDataSetChanged();
                EaseContactAdapter.this.notiyfyByFilter = false;
            } else {
                EaseContactAdapter.this.notifyDataSetInvalidated();
            }
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!this.notiyfyByFilter) {
            this.copyUserList.clear();
            this.copyUserList.addAll(this.userList);
        }
    }

    public EaseContactAdapter setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }

    public EaseContactAdapter setPrimarySize(int primarySize) {
        this.primarySize = primarySize;
        return this;
    }

    public EaseContactAdapter setInitialLetterBg(Drawable initialLetterBg) {
        this.initialLetterBg = initialLetterBg;
        return this;
    }

    public EaseContactAdapter setInitialLetterColor(int initialLetterColor) {
        this.initialLetterColor = initialLetterColor;
        return this;
    }
}
