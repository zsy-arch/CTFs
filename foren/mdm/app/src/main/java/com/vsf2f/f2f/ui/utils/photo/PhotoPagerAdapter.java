package com.vsf2f.f2f.ui.utils.photo;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.vsf2f.f2f.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/* loaded from: classes2.dex */
public class PhotoPagerAdapter extends PagerAdapter {
    public PhotoViewClickListener listener;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> paths;

    /* loaded from: classes2.dex */
    public interface PhotoViewClickListener {
        void OnPhotoLongListener(View view, int i);

        void OnPhotoTapListener(View view, float f, float f2);
    }

    public PhotoPagerAdapter(Context mContext, List<String> paths) {
        this.paths = new ArrayList();
        this.mContext = mContext;
        this.paths = paths;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setPhotoViewClickListener(PhotoViewClickListener listener) {
        this.listener = listener;
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup container, final int position) {
        Uri uri;
        View itemView = this.mLayoutInflater.inflate(R.layout.item_preview, container, false);
        PhotoView imageView = (PhotoView) itemView.findViewById(R.id.iv_pager);
        String path = this.paths.get(position);
        if (path.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            uri = Uri.parse(path);
        } else {
            uri = Uri.fromFile(new File(path));
        }
        Glide.with(this.mContext).load(uri).error((int) R.drawable.img_empty).crossFade().into(imageView);
        imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPagerAdapter.1
            @Override // uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener
            public void onPhotoTap(View view, float v, float v1) {
                if (PhotoPagerAdapter.this.listener != null) {
                    PhotoPagerAdapter.this.listener.OnPhotoTapListener(view, v, v1);
                }
            }
        });
        imageView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.vsf2f.f2f.ui.utils.photo.PhotoPagerAdapter.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (PhotoPagerAdapter.this.listener == null) {
                    return false;
                }
                PhotoPagerAdapter.this.listener.OnPhotoLongListener(view, position);
                return false;
            }
        });
        container.addView(itemView);
        return itemView;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.paths.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getItemPosition(Object object) {
        return -2;
    }
}
