package com.vsf2f.f2f.ui.utils.photo;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class PhotoPreviewIntent extends Intent {
    public PhotoPreviewIntent(Context packageContext) {
        super(packageContext, PhotoPreviewActivity.class);
    }

    public void setPhotoPaths(ArrayList<String> paths) {
        putStringArrayListExtra(PhotoPreviewActivity.EXTRA_PHOTOS, paths);
    }

    public void setCurrentItem(int currentItem) {
        putExtra(PhotoPreviewActivity.EXTRA_CURRENT_ITEM, currentItem);
    }

    public void setDeleteMode(boolean delete) {
        putExtra(PhotoPreviewActivity.EXTRA_DELETE, delete);
    }
}
