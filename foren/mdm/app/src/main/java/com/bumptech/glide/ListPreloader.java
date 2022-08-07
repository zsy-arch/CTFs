package com.bumptech.glide;

import android.widget.AbsListView;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.util.Util;
import java.util.List;
import java.util.Queue;

/* loaded from: classes.dex */
public class ListPreloader<T> implements AbsListView.OnScrollListener {
    private boolean isIncreasing;
    private int lastEnd;
    private int lastFirstVisible;
    private int lastStart;
    private final int maxPreload;
    private final PreloadSizeProvider<T> preloadDimensionProvider;
    private final PreloadModelProvider<T> preloadModelProvider;
    private final PreloadTargetQueue preloadTargetQueue;
    private int totalItemCount;

    /* loaded from: classes.dex */
    public interface PreloadModelProvider<U> {
        List<U> getPreloadItems(int i);

        GenericRequestBuilder getPreloadRequestBuilder(U u2);
    }

    /* loaded from: classes.dex */
    public interface PreloadSizeProvider<T> {
        int[] getPreloadSize(T t, int i, int i2);
    }

    @Deprecated
    public ListPreloader(int maxPreload) {
        this.isIncreasing = true;
        this.preloadModelProvider = new PreloadModelProvider<T>() { // from class: com.bumptech.glide.ListPreloader.1
            @Override // com.bumptech.glide.ListPreloader.PreloadModelProvider
            public List<T> getPreloadItems(int position) {
                return ListPreloader.this.getItems(position, position + 1);
            }

            @Override // com.bumptech.glide.ListPreloader.PreloadModelProvider
            public GenericRequestBuilder getPreloadRequestBuilder(T item) {
                return ListPreloader.this.getRequestBuilder(item);
            }
        };
        this.preloadDimensionProvider = new PreloadSizeProvider<T>() { // from class: com.bumptech.glide.ListPreloader.2
            @Override // com.bumptech.glide.ListPreloader.PreloadSizeProvider
            public int[] getPreloadSize(T item, int adapterPosition, int perItemPosition) {
                return ListPreloader.this.getDimensions(item);
            }
        };
        this.maxPreload = maxPreload;
        this.preloadTargetQueue = new PreloadTargetQueue(maxPreload + 1);
    }

    public ListPreloader(PreloadModelProvider<T> preloadModelProvider, PreloadSizeProvider<T> preloadDimensionProvider, int maxPreload) {
        this.isIncreasing = true;
        this.preloadModelProvider = preloadModelProvider;
        this.preloadDimensionProvider = preloadDimensionProvider;
        this.maxPreload = maxPreload;
        this.preloadTargetQueue = new PreloadTargetQueue(maxPreload + 1);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int firstVisible, int visibleCount, int totalCount) {
        this.totalItemCount = totalCount;
        if (firstVisible > this.lastFirstVisible) {
            preload(firstVisible + visibleCount, true);
        } else if (firstVisible < this.lastFirstVisible) {
            preload(firstVisible, false);
        }
        this.lastFirstVisible = firstVisible;
    }

    @Deprecated
    protected int[] getDimensions(T item) {
        throw new IllegalStateException("You must either provide a PreloadDimensionProvider or override getDimensions()");
    }

    @Deprecated
    protected List<T> getItems(int start, int end) {
        throw new IllegalStateException("You must either provide a PreloadModelProvider or override getItems()");
    }

    @Deprecated
    protected GenericRequestBuilder getRequestBuilder(T item) {
        throw new IllegalStateException("You must either provide a PreloadModelProvider, or override getRequestBuilder()");
    }

    private void preload(int start, boolean increasing) {
        if (this.isIncreasing != increasing) {
            this.isIncreasing = increasing;
            cancelAll();
        }
        preload(start, (increasing ? this.maxPreload : -this.maxPreload) + start);
    }

    private void preload(int from, int to) {
        int start;
        int end;
        if (from < to) {
            start = Math.max(this.lastEnd, from);
            end = to;
        } else {
            start = to;
            end = Math.min(this.lastStart, from);
        }
        int end2 = Math.min(this.totalItemCount, end);
        int start2 = Math.min(this.totalItemCount, Math.max(0, start));
        if (from < to) {
            for (int i = start2; i < end2; i++) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i), i, true);
            }
        } else {
            for (int i2 = end2 - 1; i2 >= start2; i2--) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i2), i2, false);
            }
        }
        this.lastStart = start2;
        this.lastEnd = end2;
    }

    private void preloadAdapterPosition(List<T> items, int position, boolean isIncreasing) {
        int numItems = items.size();
        if (isIncreasing) {
            for (int i = 0; i < numItems; i++) {
                preloadItem(items.get(i), position, i);
            }
            return;
        }
        for (int i2 = numItems - 1; i2 >= 0; i2--) {
            preloadItem(items.get(i2), position, i2);
        }
    }

    private void preloadItem(T item, int position, int i) {
        int[] dimensions = this.preloadDimensionProvider.getPreloadSize(item, position, i);
        if (dimensions != null) {
            this.preloadModelProvider.getPreloadRequestBuilder(item).into((GenericRequestBuilder) this.preloadTargetQueue.next(dimensions[0], dimensions[1]));
        }
    }

    private void cancelAll() {
        for (int i = 0; i < this.maxPreload; i++) {
            Glide.clear(this.preloadTargetQueue.next(0, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class PreloadTargetQueue {
        private final Queue<PreloadTarget> queue;

        public PreloadTargetQueue(int size) {
            this.queue = Util.createQueue(size);
            for (int i = 0; i < size; i++) {
                this.queue.offer(new PreloadTarget());
            }
        }

        public PreloadTarget next(int width, int height) {
            PreloadTarget result = this.queue.poll();
            this.queue.offer(result);
            result.photoWidth = width;
            result.photoHeight = height;
            return result;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PreloadTarget extends BaseTarget<Object> {
        private int photoHeight;
        private int photoWidth;

        private PreloadTarget() {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onResourceReady(Object resource, GlideAnimation<? super Object> glideAnimation) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void getSize(SizeReadyCallback cb) {
            cb.onSizeReady(this.photoWidth, this.photoHeight);
        }
    }
}
