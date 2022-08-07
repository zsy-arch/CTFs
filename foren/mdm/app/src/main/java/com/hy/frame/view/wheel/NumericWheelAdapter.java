package com.hy.frame.view.wheel;

import com.hy.frame.adapter.IWheelAdapter;

/* loaded from: classes2.dex */
public class NumericWheelAdapter implements IWheelAdapter {
    public static final int DEFAULT_MAX_VALUE = 9;
    private static final int DEFAULT_MIN_VALUE = 0;
    private String format;
    private int maxValue;
    private int minValue;

    public NumericWheelAdapter() {
        this(0, 9);
    }

    public NumericWheelAdapter(int minValue, int maxValue) {
        this(minValue, maxValue, null);
    }

    public NumericWheelAdapter(int minValue, int maxValue, String format) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.format = format;
    }

    @Override // com.hy.frame.adapter.IWheelAdapter
    public String getItem(int index) {
        if (index < 0 || index >= getItemsCount()) {
            return null;
        }
        int value = this.minValue + index;
        return this.format != null ? String.format(this.format, Integer.valueOf(value)) : Integer.toString(value);
    }

    @Override // com.hy.frame.adapter.IWheelAdapter
    public int getItemsCount() {
        return (this.maxValue - this.minValue) + 1;
    }

    @Override // com.hy.frame.adapter.IWheelAdapter
    public int getMaximumLength() {
        int maxLen = Integer.toString(Math.max(Math.abs(this.maxValue), Math.abs(this.minValue))).length();
        if (this.minValue < 0) {
            return maxLen + 1;
        }
        return maxLen;
    }
}
