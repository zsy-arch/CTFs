package com.vsf2f.f2f.ui.utils.photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.vsf2f.f2f.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class ImageGridAdapter extends BaseAdapter {
    private static final int TYPE_CAMERA = 0;
    private static final int TYPE_NORMAL = 1;
    private Context mContext;
    private LayoutInflater mInflater;
    private AbsListView.LayoutParams mItemLayoutParams;
    private int mItemSize;
    private boolean showCamera;
    private boolean showSelectIndicator = true;
    private List<Image> mImages = new ArrayList();
    private List<Image> mSelectedImages = new ArrayList();

    public ImageGridAdapter(Context context, boolean showCamera, int itemSize) {
        this.showCamera = true;
        this.mContext = context;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.showCamera = showCamera;
        this.mItemSize = itemSize;
        this.mItemLayoutParams = new AbsListView.LayoutParams(this.mItemSize, this.mItemSize);
    }

    public void showSelectIndicator(boolean b) {
        this.showSelectIndicator = b;
    }

    public void setShowCamera(boolean b) {
        if (this.showCamera != b) {
            this.showCamera = b;
            notifyDataSetChanged();
        }
    }

    public boolean isShowCamera() {
        return this.showCamera;
    }

    public void select(Image image) {
        if (this.mSelectedImages.contains(image)) {
            this.mSelectedImages.remove(image);
        } else {
            this.mSelectedImages.add(image);
        }
        notifyDataSetChanged();
    }

    public void setDefaultSelected(ArrayList<String> resultList) {
        this.mSelectedImages.clear();
        Iterator<String> it = resultList.iterator();
        while (it.hasNext()) {
            Image image = getImageByPath(it.next());
            if (image != null) {
                this.mSelectedImages.add(image);
            }
        }
        notifyDataSetChanged();
    }

    private Image getImageByPath(String path) {
        if (this.mImages != null && this.mImages.size() > 0) {
            for (Image image : this.mImages) {
                if (image.path.equalsIgnoreCase(path)) {
                    return image;
                }
            }
        }
        return null;
    }

    public void setData(List<Image> images) {
        this.mSelectedImages.clear();
        if (images == null || images.size() <= 0) {
            this.mImages.clear();
        } else {
            this.mImages = images;
        }
        notifyDataSetChanged();
    }

    public void setItemSize(int columnWidth) {
        if (this.mItemSize != columnWidth) {
            this.mItemSize = columnWidth;
            this.mItemLayoutParams = new AbsListView.LayoutParams(this.mItemSize, this.mItemSize);
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int position) {
        return (!this.showCamera || position != 0) ? 1 : 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.showCamera ? this.mImages.size() + 1 : this.mImages.size();
    }

    @Override // android.widget.Adapter
    public Image getItem(int i) {
        if (!this.showCamera) {
            return this.mImages.get(i);
        }
        if (i == 0) {
            return null;
        }
        return this.mImages.get(i - 1);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolde holde;
        int type = getItemViewType(i);
        if (type == 0) {
            view = this.mInflater.inflate(R.layout.item_camera, viewGroup, false);
            view.setTag(null);
        } else if (type == 1) {
            if (view == null) {
                view = this.mInflater.inflate(R.layout.item_select_image, viewGroup, false);
                holde = new ViewHolde(view);
            } else {
                holde = (ViewHolde) view.getTag();
                if (holde == null) {
                    view = this.mInflater.inflate(R.layout.item_select_image, viewGroup, false);
                    holde = new ViewHolde(view);
                }
            }
            holde.bindData(getItem(i));
        }
        if (view.getLayoutParams().height != this.mItemSize) {
            view.setLayoutParams(this.mItemLayoutParams);
        }
        return view;
    }

    /* loaded from: classes2.dex */
    class ViewHolde {
        ImageView image;
        ImageView indicator;
        View mask;

        ViewHolde(View view) {
            this.image = (ImageView) view.findViewById(R.id.image);
            this.indicator = (ImageView) view.findViewById(R.id.checkmark);
            this.mask = view.findViewById(R.id.mask);
            view.setTag(this);
        }

        void bindData(Image data) {
            if (data != null) {
                if (ImageGridAdapter.this.showSelectIndicator) {
                    this.indicator.setVisibility(0);
                    if (ImageGridAdapter.this.mSelectedImages.contains(data)) {
                        this.indicator.setSelected(true);
                        this.mask.setVisibility(0);
                    } else {
                        this.indicator.setSelected(false);
                        this.mask.setVisibility(8);
                    }
                } else {
                    this.indicator.setVisibility(8);
                }
                if (ImageGridAdapter.this.mItemSize > 0) {
                    Glide.with(ImageGridAdapter.this.mContext).load(new File(data.path)).error((int) R.drawable.img_empty).dontAnimate().centerCrop().into(this.image);
                    this.image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                }
            }
        }

        public Bitmap getLoacalBitmap(String url) {
            try {
                return BitmapFactory.decodeStream(new FileInputStream(url));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
