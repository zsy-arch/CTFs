package com.hy.frame.view.wheel;

import com.hy.frame.adapter.IWheelAdapter;
import java.util.List;

/* loaded from: classes2.dex */
public class ArrayWheelAdapter implements IWheelAdapter {
    public static final int DEFAULT_LENGTH = -1;
    private List<String> items;
    private int length;

    public ArrayWheelAdapter(List<String> items, int length) {
        this.items = items;
        this.length = length;
    }

    public ArrayWheelAdapter(List<String> items) {
        this(items, -1);
    }

    @Override // com.hy.frame.adapter.IWheelAdapter
    public String getItem(int index) {
        if (index < 0 || index >= this.items.size()) {
            return null;
        }
        return this.items.get(index);
    }

    @Override // com.hy.frame.adapter.IWheelAdapter
    public int getItemsCount() {
        if (this.items == null) {
            return 0;
        }
        return this.items.size();
    }

    @Override // com.hy.frame.adapter.IWheelAdapter
    public int getMaximumLength() {
        return this.length;
    }
}
