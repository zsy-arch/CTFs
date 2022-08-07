package com.em.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.utils.PermissionUtil;
import com.em.domain.VideoEntity;
import com.em.video.util.ImageCache;
import com.em.video.util.ImageResizer;
import com.em.video.util.Utils;
import com.em.widget.RecyclingImageView;
import com.hy.frame.util.MyLog;
import com.hyphenate.util.DateUtils;
import com.hyphenate.util.TextFormater;
import com.vsf2f.f2f.R;
import com.yolanda.nohttp.cookie.CookieDisk;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ImageGridFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "ImageGridFragment";
    private ImageAdapter mAdapter;
    private ImageResizer mImageResizer;
    private int mImageThumbSize;
    private int mImageThumbSpacing;
    private List<VideoEntity> mList;

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        this.mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);
        this.mList = new ArrayList();
        getVideoFile();
        this.mAdapter = new ImageAdapter(getActivity());
        ImageCache.ImageCacheParams cacheParams = new ImageCache.ImageCacheParams();
        cacheParams.setMemCacheSizePercent(0.25f);
        this.mImageResizer = new ImageResizer(getActivity(), this.mImageThumbSize);
        this.mImageResizer.setLoadingImage(R.drawable.em_empty_photo);
        this.mImageResizer.addImageCache(getActivity().getSupportFragmentManager(), cacheParams);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.em_image_grid_fragment, container, false);
        final GridView mGridView = (GridView) v.findViewById(R.id.gridView);
        mGridView.setAdapter((ListAdapter) this.mAdapter);
        mGridView.setOnItemClickListener(this);
        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.em.ui.ImageGridFragment.1
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState != 2) {
                    ImageGridFragment.this.mImageResizer.setPauseWork(false);
                } else if (!Utils.hasHoneycomb()) {
                    ImageGridFragment.this.mImageResizer.setPauseWork(true);
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
        mGridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.em.ui.ImageGridFragment.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            @TargetApi(16)
            public void onGlobalLayout() {
                int numColumns = (int) Math.floor(mGridView.getWidth() / (ImageGridFragment.this.mImageThumbSize + ImageGridFragment.this.mImageThumbSpacing));
                if (numColumns > 0) {
                    ImageGridFragment.this.mAdapter.setItemHeight((mGridView.getWidth() / numColumns) - ImageGridFragment.this.mImageThumbSpacing);
                    if (Utils.hasJellyBean()) {
                        mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        mGridView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                }
            }
        });
        return v;
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.mImageResizer.setExitTasksEarly(false);
        this.mAdapter.notifyDataSetChanged();
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mImageResizer.closeCache();
        this.mImageResizer.clearCache();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        this.mImageResizer.setPauseWork(true);
        if (position != 0) {
            VideoEntity vEntty = this.mList.get(position - 1);
            if (vEntty.size > 10485760) {
                Toast.makeText(getActivity(), getResources().getString(R.string.temporary_does_not), 0).show();
                return;
            }
            getActivity().setResult(-1, getActivity().getIntent().putExtra("path", vEntty.filePath).putExtra("dur", vEntty.duration / 1000));
            getActivity().finish();
        } else if (PermissionUtil.getVideoPermissions(getActivity(), 111)) {
            startActivityForResult(new Intent(getActivity(), RecorderVideoActivity.class), 100);
        }
    }

    /* loaded from: classes.dex */
    private class ImageAdapter extends BaseAdapter {
        private final Context mContext;
        private int mItemHeight = 0;
        private RelativeLayout.LayoutParams mImageViewLayoutParams = new RelativeLayout.LayoutParams(-1, -1);

        public ImageAdapter(Context context) {
            this.mContext = context;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return ImageGridFragment.this.mList.size() + 1;
        }

        @Override // android.widget.Adapter
        public Object getItem(int position) {
            if (position == 0) {
                return null;
            }
            return (VideoEntity) ImageGridFragment.this.mList.get(position - 1);
        }

        @Override // android.widget.Adapter
        public long getItemId(int position) {
            return position;
        }

        @Override // android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup container) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(this.mContext).inflate(R.layout.em_choose_griditem, container, false);
                holder.icon = (ImageView) convertView.findViewById(R.id.video_icon);
                holder.tvDur = (TextView) convertView.findViewById(R.id.chatting_length_iv);
                holder.tvSize = (TextView) convertView.findViewById(R.id.chatting_size_iv);
                holder.imageView = (RecyclingImageView) convertView.findViewById(R.id.imageView);
                holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                holder.imageView.setLayoutParams(this.mImageViewLayoutParams);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (holder.imageView.getLayoutParams().height != this.mItemHeight) {
                holder.imageView.setLayoutParams(this.mImageViewLayoutParams);
            }
            if (position == 0) {
                holder.icon.setVisibility(8);
                holder.tvDur.setVisibility(8);
                holder.tvSize.setText(ImageGridFragment.this.getResources().getString(R.string.video_footage));
                holder.imageView.setImageResource(R.drawable.em_actionbar_camera_icon);
            } else {
                VideoEntity entty = (VideoEntity) ImageGridFragment.this.mList.get(position - 1);
                holder.icon.setVisibility(0);
                holder.tvDur.setVisibility(0);
                holder.tvDur.setText(DateUtils.toTime(entty.duration));
                holder.tvSize.setText(TextFormater.getDataSize(entty.size));
                holder.imageView.setImageResource(R.drawable.em_empty_photo);
                ImageGridFragment.this.mImageResizer.loadImage(entty.filePath, holder.imageView);
            }
            return convertView;
        }

        public void setItemHeight(int height) {
            if (height != this.mItemHeight) {
                this.mItemHeight = height;
                this.mImageViewLayoutParams = new RelativeLayout.LayoutParams(-1, this.mItemHeight);
                ImageGridFragment.this.mImageResizer.setImageSize(height);
                notifyDataSetChanged();
            }
        }

        /* loaded from: classes.dex */
        class ViewHolder {
            ImageView icon;
            RecyclingImageView imageView;
            TextView tvDur;
            TextView tvSize;

            ViewHolder() {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getVideoFile() {
        /*
            r13 = this;
            r2 = 0
            android.support.v4.app.FragmentActivity r1 = r13.getActivity()
            android.content.ContentResolver r0 = r1.getContentResolver()
            android.net.Uri r1 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            java.lang.String r5 = "_display_name"
            r3 = r2
            r4 = r2
            android.database.Cursor r6 = r0.query(r1, r2, r3, r4, r5)
            if (r6 == 0) goto L_0x0068
            boolean r1 = r6.moveToFirst()
            if (r1 == 0) goto L_0x0068
        L_0x001b:
            java.lang.String r1 = "_id"
            int r1 = r6.getColumnIndexOrThrow(r1)
            int r9 = r6.getInt(r1)
            java.lang.String r1 = "title"
            int r1 = r6.getColumnIndexOrThrow(r1)
            java.lang.String r11 = r6.getString(r1)
            java.lang.String r1 = "_data"
            int r1 = r6.getColumnIndexOrThrow(r1)
            java.lang.String r12 = r6.getString(r1)
            java.lang.String r1 = "duration"
            int r1 = r6.getColumnIndexOrThrow(r1)
            int r7 = r6.getInt(r1)
            java.lang.String r1 = "_size"
            int r1 = r6.getColumnIndexOrThrow(r1)
            long r2 = r6.getLong(r1)
            int r10 = (int) r2
            com.em.domain.VideoEntity r8 = new com.em.domain.VideoEntity
            r8.<init>()
            r8.ID = r9
            r8.title = r11
            r8.filePath = r12
            r8.duration = r7
            r8.size = r10
            java.util.List<com.em.domain.VideoEntity> r1 = r13.mList
            r1.add(r8)
            boolean r1 = r6.moveToNext()
            if (r1 != 0) goto L_0x001b
        L_0x0068:
            if (r6 == 0) goto L_0x006e
            r6.close()
            r6 = 0
        L_0x006e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.em.ui.ImageGridFragment.getVideoFile():void");
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 100) {
            Cursor cursor = getActivity().getContentResolver().query((Uri) data.getParcelableExtra(CookieDisk.URI), new String[]{"_data", "duration"}, null, null, null);
            int duration = data.getIntExtra("dur", 0);
            String filePath = null;
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                    if (duration == 0) {
                        duration = cursor.getInt(cursor.getColumnIndexOrThrow("duration") / 1000);
                    }
                    MyLog.e("filePath:" + filePath + "||duration:" + duration);
                }
                if (cursor != null) {
                    cursor.close();
                }
                getActivity().setResult(-1, getActivity().getIntent().putExtra("path", filePath).putExtra("dur", duration));
                getActivity().finish();
            }
        }
    }
}
