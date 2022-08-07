package c.i.a;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import c.e.h.n;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class L {
    public abstract Object a(Object obj, Object obj2, Object obj3);

    public void a(View view, Rect rect) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0], iArr[1], view.getWidth() + iArr[0], view.getHeight() + iArr[1]);
    }

    public abstract void a(ViewGroup viewGroup, Object obj);

    public abstract void a(Object obj, Rect rect);

    public abstract void a(Object obj, View view);

    public abstract void a(Object obj, View view, ArrayList<View> arrayList);

    public abstract void a(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void a(Object obj, ArrayList<View> arrayList);

    public abstract void a(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract boolean a(Object obj);

    public abstract Object b(Object obj);

    public abstract Object b(Object obj, Object obj2, Object obj3);

    public abstract void b(Object obj, View view);

    public abstract void b(Object obj, View view, ArrayList<View> arrayList);

    public abstract void b(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public void a(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int i = Build.VERSION.SDK_INT;
            if (viewGroup.isTransitionGroup()) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                a(arrayList, viewGroup.getChildAt(i2));
            }
            return;
        }
        arrayList.add(view);
    }

    public void a(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String j = n.j(view);
            if (j != null) {
                map.put(j, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    a(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    public static boolean a(List<View> list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(List list) {
        return list == null || list.isEmpty();
    }
}
