package com.hy.frame.view.recycler.xRefreshView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/* loaded from: classes2.dex */
public class Utils {
    public static String format(String format, int args) {
        return String.format(format, Integer.valueOf(args));
    }

    public static Point getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService("window");
        return new Point(wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getHeight());
    }

    public static void setFullSpan(StaggeredGridLayoutManager.LayoutParams layoutParams) {
        if (layoutParams != null && !layoutParams.isFullSpan()) {
            layoutParams.setFullSpan(true);
        }
    }

    public static void removeViewFromParent(View view) {
        ViewGroup parent;
        if (view != null && (parent = (ViewGroup) view.getParent()) != null) {
            parent.removeView(view);
        }
    }

    public static boolean isRecyclerViewFullscreen(RecyclerView viewGroup) {
        return (viewGroup.getAdapter() instanceof RecyclerViewHolder) && findFirstCompletelyVisibleItemPosition(viewGroup) > 0;
    }

    public static int findFirstCompletelyVisibleItemPosition(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        if (layoutManager instanceof GridLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int[] lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
            staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(lastPositions);
            return findMin(lastPositions);
        }
        throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
    }

    private static int findMin(int[] lastPositions) {
        int min = Integer.MAX_VALUE;
        for (int value : lastPositions) {
            if (value != -1 && value < min) {
                min = value;
            }
        }
        return min;
    }

    public static int computeScrollDuration(int dx, int dy, int height) {
        int duration;
        int absDx = Math.abs(dx);
        int absDy = Math.abs(dy);
        boolean horizontal = absDx > absDy;
        int velocity = (int) Math.sqrt(0);
        int halfContainerSize = height / 2;
        float distance = halfContainerSize + (halfContainerSize * distanceInfluenceForSnapDuration(Math.min(1.0f, (1.0f * ((int) Math.sqrt((dx * dx) + (dy * dy)))) / height)));
        if (velocity > 0) {
            duration = Math.round(1000.0f * Math.abs(distance / velocity)) * 4;
        } else {
            if (!horizontal) {
                absDx = absDy;
            }
            duration = (int) (((absDx / height) + 1.0f) * 300.0f);
        }
        return Math.min(duration, 2000);
    }

    public static int computeScrollVerticalDuration(int dy, int height) {
        int duration = (int) (((Math.abs(dy) / height) + 1.0f) * 200.0f);
        if (dy == 0) {
            return 0;
        }
        return Math.min(duration, 500);
    }

    private static float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((float) ((f - 0.5f) * 0.4712389167638204d));
    }

    public static boolean isScreenOriatationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static int getDpi(Context context) {
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(display, displayMetrics);
            return displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getBottomStatusHeight(Context context) {
        return getDpi(context) - getScreenHeight(context);
    }

    public static int getTitleHeight(Activity activity) {
        return activity.getWindow().findViewById(16908290).getTop();
    }

    public static int getStatusHeight(Context context) {
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(clazz.getField("status_bar_height").get(clazz.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
