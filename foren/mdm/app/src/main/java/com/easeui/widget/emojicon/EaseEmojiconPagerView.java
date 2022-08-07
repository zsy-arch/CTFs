package com.easeui.widget.emojicon;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.easeui.adapter.EmojiconGridAdapter;
import com.easeui.adapter.EmojiconPagerAdapter;
import com.easeui.domain.EaseEmojicon;
import com.easeui.domain.EaseEmojiconGroupEntity;
import com.easeui.utils.EaseSmileUtils;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class EaseEmojiconPagerView extends ViewPager {
    private int bigEmojiconColumns;
    private int bigEmojiconRows;
    private Context context;
    private int emojiconColumns;
    private int emojiconRows;
    private int firstGroupPageSize;
    private List<EaseEmojiconGroupEntity> groupEntities;
    private int maxPageCount;
    private PagerAdapter pagerAdapter;
    private EaseEmojiconPagerViewListener pagerViewListener;
    private int previousPagerPosition;
    private List<View> viewpages;

    /* loaded from: classes.dex */
    public interface EaseEmojiconPagerViewListener {
        void onDeleteImageClicked();

        void onExpressionClicked(EaseEmojicon easeEmojicon);

        void onGroupInnerPagePostionChanged(int i, int i2);

        void onGroupMaxPageSizeChanged(int i);

        void onGroupPagePostionChangedTo(int i);

        void onGroupPositionChanged(int i, int i2);

        void onPagerViewInited(int i, int i2);
    }

    public EaseEmojiconPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.emojiconRows = 3;
        this.emojiconColumns = 7;
        this.bigEmojiconRows = 2;
        this.bigEmojiconColumns = 4;
        this.context = context;
    }

    public EaseEmojiconPagerView(Context context) {
        this(context, null);
    }

    public void init(List<EaseEmojiconGroupEntity> emojiconGroupList, int emijiconColumns, int bigEmojiconColumns) {
        if (emojiconGroupList == null) {
            throw new RuntimeException("emojiconGroupList is null");
        }
        this.groupEntities = emojiconGroupList;
        this.emojiconColumns = emijiconColumns;
        this.bigEmojiconColumns = bigEmojiconColumns;
        this.viewpages = new ArrayList();
        for (int i = 0; i < this.groupEntities.size(); i++) {
            EaseEmojiconGroupEntity group = this.groupEntities.get(i);
            group.getEmojiconList();
            List<View> gridViews = getGroupGridViews(group);
            if (i == 0) {
                this.firstGroupPageSize = gridViews.size();
            }
            this.maxPageCount = Math.max(gridViews.size(), this.maxPageCount);
            this.viewpages.addAll(gridViews);
        }
        this.pagerAdapter = new EmojiconPagerAdapter(this.viewpages);
        setAdapter(this.pagerAdapter);
        setOnPageChangeListener(new EmojiPagerChangeListener());
        if (this.pagerViewListener != null) {
            this.pagerViewListener.onPagerViewInited(this.maxPageCount, this.firstGroupPageSize);
        }
    }

    public void setPagerViewListener(EaseEmojiconPagerViewListener pagerViewListener) {
        this.pagerViewListener = pagerViewListener;
    }

    public void setGroupPostion(int position) {
        if (getAdapter() != null && position >= 0 && position < this.groupEntities.size()) {
            int count = 0;
            for (int i = 0; i < position; i++) {
                count += getPageSize(this.groupEntities.get(i));
            }
            setCurrentItem(count);
        }
    }

    public List<View> getGroupGridViews(EaseEmojiconGroupEntity groupEntity) {
        List<EaseEmojicon> emojiconList = groupEntity.getEmojiconList();
        int itemSize = (this.emojiconColumns * this.emojiconRows) - 1;
        int totalSize = emojiconList.size();
        EaseEmojicon.Type emojiType = groupEntity.getType();
        if (emojiType == EaseEmojicon.Type.BIG_EXPRESSION) {
            itemSize = this.bigEmojiconColumns * this.bigEmojiconRows;
        }
        int pageSize = totalSize % itemSize == 0 ? totalSize / itemSize : (totalSize / itemSize) + 1;
        List<View> views = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            View view = View.inflate(this.context, R.layout.ease_expression_gridview, null);
            GridView gv = (GridView) view.findViewById(R.id.gridview);
            if (emojiType == EaseEmojicon.Type.BIG_EXPRESSION) {
                gv.setNumColumns(this.bigEmojiconColumns);
            } else {
                gv.setNumColumns(this.emojiconColumns);
            }
            List<EaseEmojicon> list = new ArrayList<>();
            if (i != pageSize - 1) {
                list.addAll(emojiconList.subList(i * itemSize, (i + 1) * itemSize));
            } else {
                list.addAll(emojiconList.subList(i * itemSize, totalSize));
            }
            if (emojiType != EaseEmojicon.Type.BIG_EXPRESSION) {
                EaseEmojicon deleteIcon = new EaseEmojicon();
                deleteIcon.setEmojiText(EaseSmileUtils.DELETE_KEY);
                list.add(deleteIcon);
            }
            final EmojiconGridAdapter gridAdapter = new EmojiconGridAdapter(this.context, 1, list, emojiType);
            gv.setAdapter((ListAdapter) gridAdapter);
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.easeui.widget.emojicon.EaseEmojiconPagerView.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> parent, View view2, int position, long id) {
                    EaseEmojicon emojicon = gridAdapter.getItem(position);
                    if (EaseEmojiconPagerView.this.pagerViewListener != null) {
                        String emojiText = emojicon.getEmojiText();
                        if (emojiText == null || !emojiText.equals(EaseSmileUtils.DELETE_KEY)) {
                            EaseEmojiconPagerView.this.pagerViewListener.onExpressionClicked(emojicon);
                        } else {
                            EaseEmojiconPagerView.this.pagerViewListener.onDeleteImageClicked();
                        }
                    }
                }
            });
            views.add(view);
        }
        return views;
    }

    public void addEmojiconGroup(EaseEmojiconGroupEntity groupEntity, boolean notifyDataChange) {
        int pageSize = getPageSize(groupEntity);
        if (pageSize > this.maxPageCount) {
            this.maxPageCount = pageSize;
            if (!(this.pagerViewListener == null || this.pagerAdapter == null)) {
                this.pagerViewListener.onGroupMaxPageSizeChanged(this.maxPageCount);
            }
        }
        this.viewpages.addAll(getGroupGridViews(groupEntity));
        if (this.pagerAdapter != null && notifyDataChange) {
            this.pagerAdapter.notifyDataSetChanged();
        }
    }

    public void removeEmojiconGroup(int position) {
        if (position <= this.groupEntities.size() - 1 && this.pagerAdapter != null) {
            this.pagerAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPageSize(EaseEmojiconGroupEntity groupEntity) {
        List<EaseEmojicon> emojiconList = groupEntity.getEmojiconList();
        int itemSize = (this.emojiconColumns * this.emojiconRows) - 1;
        int totalSize = emojiconList.size();
        if (groupEntity.getType() == EaseEmojicon.Type.BIG_EXPRESSION) {
            itemSize = this.bigEmojiconColumns * this.bigEmojiconRows;
        }
        return totalSize % itemSize == 0 ? totalSize / itemSize : (totalSize / itemSize) + 1;
    }

    /* loaded from: classes.dex */
    private class EmojiPagerChangeListener implements ViewPager.OnPageChangeListener {
        private EmojiPagerChangeListener() {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int position) {
            int endSize = 0;
            int groupPosition = 0;
            Iterator it = EaseEmojiconPagerView.this.groupEntities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int groupPageSize = EaseEmojiconPagerView.this.getPageSize((EaseEmojiconGroupEntity) it.next());
                if (endSize + groupPageSize <= position) {
                    groupPosition++;
                    endSize += groupPageSize;
                } else if (EaseEmojiconPagerView.this.previousPagerPosition - endSize < 0) {
                    if (EaseEmojiconPagerView.this.pagerViewListener != null) {
                        EaseEmojiconPagerView.this.pagerViewListener.onGroupPositionChanged(groupPosition, groupPageSize);
                        EaseEmojiconPagerView.this.pagerViewListener.onGroupPagePostionChangedTo(0);
                    }
                } else if (EaseEmojiconPagerView.this.previousPagerPosition - endSize >= groupPageSize) {
                    if (EaseEmojiconPagerView.this.pagerViewListener != null) {
                        EaseEmojiconPagerView.this.pagerViewListener.onGroupPositionChanged(groupPosition, groupPageSize);
                        EaseEmojiconPagerView.this.pagerViewListener.onGroupPagePostionChangedTo(position - endSize);
                    }
                } else if (EaseEmojiconPagerView.this.pagerViewListener != null) {
                    EaseEmojiconPagerView.this.pagerViewListener.onGroupInnerPagePostionChanged(EaseEmojiconPagerView.this.previousPagerPosition - endSize, position - endSize);
                }
            }
            EaseEmojiconPagerView.this.previousPagerPosition = position;
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }
}
