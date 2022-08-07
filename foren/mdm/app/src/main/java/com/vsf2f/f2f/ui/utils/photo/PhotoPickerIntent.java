package com.vsf2f.f2f.ui.utils.photo;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class PhotoPickerIntent extends Intent {
    public static final int REQUEST_PHOTO_PICKER = 82;

    public PhotoPickerIntent(Context packageContext) {
        super(packageContext, PhotoPickerActivity.class);
        addFlags(1);
    }

    public void setCamera(boolean isCamera) {
        putExtra(PhotoPickerActivity.EXTRA_TAKE_CAMERA, isCamera);
    }

    public void setShowCamera(boolean ShowCamera) {
        putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, ShowCamera);
    }

    public void setMaxTotal(int total) {
        putExtra(PhotoPickerActivity.EXTRA_SELECT_COUNT, total);
    }

    public void setSelectModel(SelectModel model) {
        putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, Integer.parseInt(model.toString()));
    }

    public void setSelectMust(boolean must) {
        putExtra(PhotoPickerActivity.EXTRA_SELECT_MUST, must);
    }

    public void setSelectedPaths(ArrayList<String> imagePathis) {
        putStringArrayListExtra(PhotoPickerActivity.EXTRA_DEFAULT_SELECTED_LIST, imagePathis);
    }

    public void setImageConfig(ImageConfig config) {
        putExtra(PhotoPickerActivity.EXTRA_IMAGE_CONFIG, config);
    }
}
