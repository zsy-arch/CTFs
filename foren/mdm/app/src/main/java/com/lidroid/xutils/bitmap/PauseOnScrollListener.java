package com.lidroid.xutils.bitmap;

import android.widget.AbsListView;
import com.lidroid.xutils.task.TaskHandler;

/* loaded from: classes2.dex */
public class PauseOnScrollListener implements AbsListView.OnScrollListener {
    private final AbsListView.OnScrollListener externalListener;
    private final boolean pauseOnFling;
    private final boolean pauseOnScroll;
    private TaskHandler taskHandler;

    public PauseOnScrollListener(TaskHandler taskHandler, boolean pauseOnScroll, boolean pauseOnFling) {
        this(taskHandler, pauseOnScroll, pauseOnFling, null);
    }

    public PauseOnScrollListener(TaskHandler taskHandler, boolean pauseOnScroll, boolean pauseOnFling, AbsListView.OnScrollListener customListener) {
        this.taskHandler = taskHandler;
        this.pauseOnScroll = pauseOnScroll;
        this.pauseOnFling = pauseOnFling;
        this.externalListener = customListener;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case 0:
                this.taskHandler.resume();
                break;
            case 1:
                if (this.pauseOnScroll) {
                    this.taskHandler.pause();
                    break;
                }
                break;
            case 2:
                if (this.pauseOnFling) {
                    this.taskHandler.pause();
                    break;
                }
                break;
        }
        if (this.externalListener != null) {
            this.externalListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (this.externalListener != null) {
            this.externalListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }
}
