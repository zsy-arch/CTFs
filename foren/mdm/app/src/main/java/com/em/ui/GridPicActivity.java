package com.em.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.cdlinglu.common.BaseActivity;
import com.tencent.open.SocialConstants;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.PicUrlPathAdapter;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewIntent;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class GridPicActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_grid;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.title_user_photo, 0);
        ArrayList<String> pics = getBundle().getStringArrayList(SocialConstants.PARAM_IMAGE);
        final ArrayList<String> pics2 = getBundle().getStringArrayList("pics2");
        PicUrlPathAdapter picAdapter = new PicUrlPathAdapter(this.context, pics);
        GridView gridpic = (GridView) getView(R.id.gridpic);
        gridpic.setAdapter((ListAdapter) picAdapter);
        gridpic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.em.ui.GridPicActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(GridPicActivity.this.context);
                intent.setCurrentItem(i);
                intent.setDeleteMode(false);
                intent.setPhotoPaths(pics2);
                GridPicActivity.this.startActivity(intent);
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
    }
}
