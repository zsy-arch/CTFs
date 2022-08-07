package com.cdlinglu.utils.Photo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.cdlinglu.utils.Photo.util.BitmapCache;
import com.cdlinglu.utils.Photo.util.ImageItem;
import com.cdlinglu.utils.Photo.util.Res;
import com.hy.frame.adapter.MyBaseAdapter;
import com.hy.frame.util.HyUtil;
import com.vsf2f.f2f.R;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AlbumGridViewAdapter extends MyBaseAdapter<ImageItem> {
    BitmapCache.ImageCallback callback = new BitmapCache.ImageCallback() { // from class: com.cdlinglu.utils.Photo.adapter.AlbumGridViewAdapter.1
        @Override // com.cdlinglu.utils.Photo.util.BitmapCache.ImageCallback
        public void imageLoad(ImageView imageView, Bitmap bitmap, Object... params) {
            String url;
            if (imageView != null && bitmap != null && (url = (String) params[0]) != null && url.equals((String) imageView.getTag())) {
                imageView.setImageBitmap(bitmap);
            }
        }
    };
    private BitmapCache cache = new BitmapCache();

    public AlbumGridViewAdapter(Context context, ArrayList<ImageItem> datas) {
        super(context, datas);
    }

    /* loaded from: classes.dex */
    private class ViewHolder {
        public ImageView choosetoggle;
        public ImageView imageView;
        public TextView textView;

        private ViewHolder() {
        }
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflate(R.layout.plugin_camera_select_imageview);
            viewHolder.imageView = (ImageView) convertView.findViewById(Res.getWidgetID("image_view"));
            viewHolder.choosetoggle = (ImageView) convertView.findViewById(Res.getWidgetID("choosedbt"));
            int width = (getContext().getResources().getDisplayMetrics().widthPixels - HyUtil.dip2px(getContext(), 40.0f)) / 3;
            viewHolder.imageView.getLayoutParams().width = width;
            viewHolder.imageView.getLayoutParams().height = width;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageItem item = getItem(position);
        if (HyUtil.isEmpty(item.getImagePath())) {
            viewHolder.imageView.setImageResource(R.mipmap.plugin_camera_no_pictures);
        } else {
            viewHolder.imageView.setTag(item.imagePath);
            this.cache.displayBmp(viewHolder.imageView, item.thumbnailPath, item.imagePath, this.callback);
        }
        viewHolder.choosetoggle.setTag(Integer.valueOf(position));
        viewHolder.choosetoggle.setVisibility(item.isSelect() ? 0 : 8);
        return convertView;
    }
}
