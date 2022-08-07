package com.easeui.widget.emojicon;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import com.easeui.domain.EaseEmojicon;
import com.easeui.domain.EaseEmojiconGroupEntity;
import com.easeui.widget.emojicon.EaseEmojiconPagerView;
import com.easeui.widget.emojicon.EaseEmojiconScrollTabBar;
import com.vsf2f.f2f.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EaseEmojiconMenu extends EaseEmojiconMenuBase {
    private int bigEmojiconColumns;
    private int emojiconColumns;
    private List<EaseEmojiconGroupEntity> emojiconGroupList = new ArrayList();
    private EaseEmojiconIndicatorView indicatorView;
    private EaseEmojiconPagerView pagerView;
    private EaseEmojiconScrollTabBar tabBar;

    @TargetApi(11)
    public EaseEmojiconMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public EaseEmojiconMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EaseEmojiconMenu(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.ease_widget_emojicon, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseEmojiconMenu);
        this.emojiconColumns = ta.getInt(1, 7);
        this.bigEmojiconColumns = ta.getInt(0, 4);
        ta.recycle();
        this.pagerView = (EaseEmojiconPagerView) findViewById(R.id.pager_view);
        this.indicatorView = (EaseEmojiconIndicatorView) findViewById(R.id.indicator_view);
        this.tabBar = (EaseEmojiconScrollTabBar) findViewById(R.id.tab_bar);
    }

    public void init(List<EaseEmojiconGroupEntity> groupEntities) {
        if (!(groupEntities == null || groupEntities.size() == 0)) {
            for (EaseEmojiconGroupEntity groupEntity : groupEntities) {
                this.emojiconGroupList.add(groupEntity);
                this.tabBar.addTab(groupEntity.getIcon());
            }
            this.pagerView.setPagerViewListener(new EmojiconPagerViewListener());
            this.pagerView.init(this.emojiconGroupList, this.emojiconColumns, this.bigEmojiconColumns);
            this.tabBar.setTabBarItemClickListener(new EaseEmojiconScrollTabBar.EaseScrollTabBarItemClickListener() { // from class: com.easeui.widget.emojicon.EaseEmojiconMenu.1
                @Override // com.easeui.widget.emojicon.EaseEmojiconScrollTabBar.EaseScrollTabBarItemClickListener
                public void onItemClick(int position) {
                    EaseEmojiconMenu.this.pagerView.setGroupPostion(position);
                }
            });
        }
    }

    public void addEmojiconGroup(EaseEmojiconGroupEntity groupEntity) {
        this.emojiconGroupList.add(groupEntity);
        this.pagerView.addEmojiconGroup(groupEntity, true);
        this.tabBar.addTab(groupEntity.getIcon());
    }

    public void addEmojiconGroup(List<EaseEmojiconGroupEntity> groupEntitieList) {
        int i = 0;
        while (i < groupEntitieList.size()) {
            EaseEmojiconGroupEntity groupEntity = groupEntitieList.get(i);
            this.emojiconGroupList.add(groupEntity);
            this.pagerView.addEmojiconGroup(groupEntity, i == groupEntitieList.size() + (-1));
            this.tabBar.addTab(groupEntity.getIcon());
            i++;
        }
    }

    public void removeEmojiconGroup(int position) {
        this.emojiconGroupList.remove(position);
        this.pagerView.removeEmojiconGroup(position);
        this.tabBar.removeTab(position);
    }

    public void setTabBarVisibility(boolean isVisible) {
        if (!isVisible) {
            this.tabBar.setVisibility(8);
        } else {
            this.tabBar.setVisibility(0);
        }
    }

    /* loaded from: classes.dex */
    private class EmojiconPagerViewListener implements EaseEmojiconPagerView.EaseEmojiconPagerViewListener {
        private EmojiconPagerViewListener() {
        }

        @Override // com.easeui.widget.emojicon.EaseEmojiconPagerView.EaseEmojiconPagerViewListener
        public void onPagerViewInited(int groupMaxPageSize, int firstGroupPageSize) {
            EaseEmojiconMenu.this.indicatorView.init(groupMaxPageSize);
            EaseEmojiconMenu.this.indicatorView.updateIndicator(firstGroupPageSize);
            EaseEmojiconMenu.this.tabBar.selectedTo(0);
        }

        @Override // com.easeui.widget.emojicon.EaseEmojiconPagerView.EaseEmojiconPagerViewListener
        public void onGroupPositionChanged(int groupPosition, int pagerSizeOfGroup) {
            EaseEmojiconMenu.this.indicatorView.updateIndicator(pagerSizeOfGroup);
            EaseEmojiconMenu.this.tabBar.selectedTo(groupPosition);
        }

        @Override // com.easeui.widget.emojicon.EaseEmojiconPagerView.EaseEmojiconPagerViewListener
        public void onGroupInnerPagePostionChanged(int oldPosition, int newPosition) {
            EaseEmojiconMenu.this.indicatorView.selectTo(oldPosition, newPosition);
        }

        @Override // com.easeui.widget.emojicon.EaseEmojiconPagerView.EaseEmojiconPagerViewListener
        public void onGroupPagePostionChangedTo(int position) {
            EaseEmojiconMenu.this.indicatorView.selectTo(position);
        }

        @Override // com.easeui.widget.emojicon.EaseEmojiconPagerView.EaseEmojiconPagerViewListener
        public void onGroupMaxPageSizeChanged(int maxCount) {
            EaseEmojiconMenu.this.indicatorView.updateIndicator(maxCount);
        }

        @Override // com.easeui.widget.emojicon.EaseEmojiconPagerView.EaseEmojiconPagerViewListener
        public void onDeleteImageClicked() {
            if (EaseEmojiconMenu.this.listener != null) {
                EaseEmojiconMenu.this.listener.onDeleteImageClicked();
            }
        }

        @Override // com.easeui.widget.emojicon.EaseEmojiconPagerView.EaseEmojiconPagerViewListener
        public void onExpressionClicked(EaseEmojicon emojicon) {
            if (EaseEmojiconMenu.this.listener != null) {
                EaseEmojiconMenu.this.listener.onExpressionClicked(emojicon);
            }
        }
    }
}
