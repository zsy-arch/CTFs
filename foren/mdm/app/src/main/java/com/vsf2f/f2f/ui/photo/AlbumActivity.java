package com.vsf2f.f2f.ui.photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.utils.Photo.adapter.AlbumGridViewAdapter;
import com.cdlinglu.utils.Photo.util.AlbumHelper;
import com.cdlinglu.utils.Photo.util.ImageBucket;
import com.cdlinglu.utils.Photo.util.ImageItem;
import com.cdlinglu.utils.Photo.util.Res;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.umeng.update.net.f;
import com.vsf2f.f2f.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public class AlbumActivity extends Activity {
    private Button cancel;
    private ArrayList<ImageItem> dataList;
    private AlbumGridViewAdapter gridImageAdapter;
    private GridView gridView;
    private AlbumHelper helper;
    private Intent intent;
    private final int num = 9;
    private Button okButton;
    private ArrayList<ImageItem> selectedDataList;
    private TextView tv;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_album);
        Bundle bundle = getIntent().getExtras();
        int size = 0;
        if (bundle.containsKey(Constant.FLAG)) {
            this.selectedDataList = bundle.getParcelableArrayList(Constant.FLAG);
            size = this.selectedDataList.size();
        }
        init();
        initListener();
        isShowOkBt(size);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class AlbumSendListener implements View.OnClickListener {
        private AlbumSendListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            ArrayList<ImageItem> tempSelectBitmap = new ArrayList<>();
            if (AlbumActivity.this.dataList != null) {
                Iterator it = AlbumActivity.this.dataList.iterator();
                while (it.hasNext()) {
                    ImageItem item = (ImageItem) it.next();
                    if (item.isSelect()) {
                        tempSelectBitmap.add(item);
                    }
                }
            }
            AlbumActivity.this.overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(Constant.FLAG, tempSelectBitmap);
            intent.putExtras(bundle);
            AlbumActivity.this.setResult(-1, intent);
            AlbumActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class CancelListener implements View.OnClickListener {
        private CancelListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            AlbumActivity.this.finish();
        }
    }

    private void init() {
        this.helper = new AlbumHelper();
        this.helper.init(getApplicationContext());
        Set<String> selectPaths = new HashSet<>();
        if (this.selectedDataList != null) {
            Iterator<ImageItem> it = this.selectedDataList.iterator();
            while (it.hasNext()) {
                selectPaths.add(it.next().getImagePath());
            }
        }
        List<ImageBucket> contentList = this.helper.getImagesBucketList(false);
        this.dataList = new ArrayList<>();
        for (int i = 0; i < contentList.size(); i++) {
            if (contentList.get(i).imageList != null) {
                for (ImageItem item : contentList.get(i).imageList) {
                    MyLog.e(getClass(), "path=" + item.getImagePath() + " | thumb=" + item.getThumbnailPath());
                    try {
                        if (new File(item.getImagePath()).exists()) {
                            item.setSelect(selectPaths.contains(item.getImagePath()));
                            this.dataList.add(item);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        this.cancel = (Button) findViewById(Res.getWidgetID(f.c));
        this.cancel.setOnClickListener(new CancelListener());
        this.intent = getIntent();
        this.intent.getExtras();
        this.gridView = (GridView) findViewById(Res.getWidgetID("myGrid"));
        this.tv = (TextView) findViewById(Res.getWidgetID("myText"));
        updateUI();
    }

    public void updateUI() {
        this.gridImageAdapter = new AlbumGridViewAdapter(this, this.dataList);
        this.gridView.setAdapter((ListAdapter) this.gridImageAdapter);
        this.gridView.setEmptyView(this.tv);
        this.okButton = (Button) findViewById(Res.getWidgetID("ok_button"));
        this.okButton.setText(Res.getString("finish") + "(" + this.dataList.size() + "/9)");
    }

    private void initListener() {
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.photo.AlbumActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int size;
                boolean z = false;
                int size2 = 0;
                Iterator it = AlbumActivity.this.dataList.iterator();
                while (it.hasNext()) {
                    if (((ImageItem) it.next()).isSelect()) {
                        size2++;
                    }
                }
                ImageItem item = (ImageItem) AlbumActivity.this.dataList.get(position);
                if (size2 < 9 || item.isSelect()) {
                    if (!item.isSelect()) {
                        z = true;
                    }
                    item.setSelect(z);
                    if (item.isSelect()) {
                        size = size2 + 1;
                    } else {
                        size = size2 - 1;
                    }
                    AlbumActivity.this.isShowOkBt(size);
                    AlbumActivity.this.gridImageAdapter.refresh(AlbumActivity.this.dataList);
                    return;
                }
                Toast.makeText(AlbumActivity.this, Res.getString("only_choose_num"), 0).show();
            }
        });
        this.okButton.setOnClickListener(new AlbumSendListener());
    }

    public void isShowOkBt(int size) {
        if (size > 0) {
            this.okButton.setText(Res.getString("finish") + "(" + size + "/9)");
            this.okButton.setEnabled(true);
            this.okButton.setTextColor(-1);
            return;
        }
        this.okButton.setText(Res.getString("finish") + "(" + size + "/9)");
        this.okButton.setEnabled(false);
        this.okButton.setTextColor(Color.parseColor("#E1E0DE"));
    }
}
