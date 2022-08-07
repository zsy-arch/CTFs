package com.lidroid.xutils.view;

import android.content.Context;
import android.view.animation.AnimationUtils;

/* loaded from: classes2.dex */
public class ResLoader {
    public static Object loadRes(ResType type, Context context, int id) {
        if (context == null || id < 1) {
            return null;
        }
        switch (type) {
            case Animation:
                return AnimationUtils.loadAnimation(context, id);
            case Boolean:
                return Boolean.valueOf(context.getResources().getBoolean(id));
            case Color:
                return Integer.valueOf(context.getResources().getColor(id));
            case ColorStateList:
                return context.getResources().getColorStateList(id);
            case Dimension:
                return Float.valueOf(context.getResources().getDimension(id));
            case DimensionPixelOffset:
                return Integer.valueOf(context.getResources().getDimensionPixelOffset(id));
            case DimensionPixelSize:
                return Integer.valueOf(context.getResources().getDimensionPixelSize(id));
            case Drawable:
                return context.getResources().getDrawable(id);
            case Integer:
                return Integer.valueOf(context.getResources().getInteger(id));
            case IntArray:
                return context.getResources().getIntArray(id);
            case Movie:
                return context.getResources().getMovie(id);
            case String:
                return context.getResources().getString(id);
            case StringArray:
                return context.getResources().getStringArray(id);
            case Text:
                return context.getResources().getText(id);
            case TextArray:
                return context.getResources().getTextArray(id);
            case Xml:
                return context.getResources().getXml(id);
            default:
                return null;
        }
    }
}
