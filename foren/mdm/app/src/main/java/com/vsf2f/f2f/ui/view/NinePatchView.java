package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.support.v7.appcompat.R;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class NinePatchView extends LinearLayout {
    private static Context context;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter;
    private static List<String> imgList = new ArrayList();
    private static int clomus = 1;
    private static int containWidth = 300;

    public NinePatchView(Context context2, AttributeSet attrs) {
        super(context2, attrs);
        context = context2;
        containWidth = context2.getTheme().obtainStyledAttributes(attrs, R.styleable.LinearLayoutCompat_Layout, 0, 0).getDimensionPixelSize(1, 0);
        setGravity(17);
        initView();
    }

    public NinePatchView(Context context2) {
        super(context2, null);
    }

    private void initView() {
        imgList.add("");
        this.recyclerView = new RecyclerView(context);
        this.recyclerView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        addView(this.recyclerView);
        this.viewAdapter = new ViewAdapter();
        this.gridLayoutManager = new GridLayoutManager(context, clomus);
        this.recyclerView.setLayoutManager(this.gridLayoutManager);
        this.recyclerView.setAdapter(this.viewAdapter);
    }

    public void setImgList(List<String> data) {
        if (data != null) {
            if (imgList.size() > 0) {
                imgList.clear();
            }
            imgList.addAll(data);
        }
        getSpanneCount();
        if (imgList.size() > 9) {
            imgList = imgList.subList(0, 9);
        }
        this.gridLayoutManager.setSpanCount(clomus);
        this.recyclerView.setAdapter(this.viewAdapter);
    }

    public void setImgList(String data) {
        List<String> imgs = new ArrayList<>();
        imgs.add(data);
        setImgList(imgs);
    }

    private void getSpanneCount() {
        switch (imgList.size()) {
            case 1:
                clomus = 1;
                return;
            case 2:
                clomus = 2;
                return;
            case 3:
                imgList.add(1, "");
                clomus = 2;
                return;
            case 4:
                clomus = 2;
                return;
            case 5:
                imgList.add(2, "");
                clomus = 3;
                return;
            case 6:
                clomus = 3;
                return;
            case 7:
                imgList.add(0, "");
                imgList.add(2, "");
                clomus = 3;
                return;
            case 8:
                imgList.add(2, "");
                clomus = 3;
                return;
            default:
                clomus = 3;
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class ViewAdapter extends RecyclerView.Adapter {
        ViewAdapter() {
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new LinearLayout(parent.getContext());
            ImageView iv = new ImageView(parent.getContext());
            iv.setId(com.vsf2f.f2f.R.id.iv_header);
            ((ViewGroup) view).addView(iv);
            return new ItemHoldView(view);
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Glide.with(NinePatchView.context).load((String) NinePatchView.imgList.get(position)).error((int) com.vsf2f.f2f.R.mipmap.ico_logo).into(((ItemHoldView) holder).iv);
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return NinePatchView.imgList.size();
        }

        /* loaded from: classes2.dex */
        static class ItemHoldView extends RecyclerView.ViewHolder {
            ImageView iv;

            public ItemHoldView(View itemView) {
                super(itemView);
                this.iv = (ImageView) itemView.findViewById(com.vsf2f.f2f.R.id.iv_header);
                this.iv.setLayoutParams(new LinearLayout.LayoutParams(NinePatchView.containWidth / NinePatchView.clomus, NinePatchView.containWidth / NinePatchView.clomus));
            }
        }
    }
}
