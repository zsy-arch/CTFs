package com.vsf2f.f2f.ui.circle;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.em.ui.EditActivity;
import com.hy.frame.adapter.IAdapterListener;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.MessageEncoder;
import com.litepal.crud.DataSupport;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.PicAddBtnAdapter;
import com.vsf2f.f2f.bean.DBCircleDraftBean;
import com.vsf2f.f2f.ui.dialog.SaveDraftDialog;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewIntent;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CirclesAddPicActivity extends BaseActivity implements IAdapterListener {
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private static final String TAG_THRIFT_FLOW = "TAG_THRIFT_FLOW";
    private EditText editcontent;
    private GridView gvPic;
    private KeyValueView kvpublic;
    private PicAddBtnAdapter picAdapter;
    private String username;
    private String ADDPIC_TYPE = "0";
    private ArrayList<String> imagePaths = new ArrayList<>();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_circles_addpic;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.circle_add_pic, R.string.release);
        if (isNoLogin()) {
            MyToast.show(this.context, (int) R.string.login_hint);
            startAct(LoginActivity.class);
            return;
        }
        this.editcontent = (EditText) getView(R.id.circles_add_txtContent);
        this.kvpublic = (KeyValueView) getViewAndClick(R.id.kv_ispublic);
        this.gvPic = (GridView) getView(R.id.circle_add_pic);
        this.username = DemoHelper.getInstance().getCurrentUserName();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        initUI();
    }

    private void initUI() {
        this.kvpublic.setSelected(AppShare.get(this.context).getBoolean("TAG_THRIFT_FLOW"));
        this.kvpublic.getTxtKey().setText(this.kvpublic.isSelected() ? R.string.no_public : R.string.is_public);
        this.picAdapter = new PicAddBtnAdapter(this, this.imagePaths);
        this.gvPic.setAdapter((ListAdapter) this.picAdapter);
        this.gvPic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddPicActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (CirclesAddPicActivity.this.imagePaths.size() >= 9 || i != CirclesAddPicActivity.this.imagePaths.size()) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(CirclesAddPicActivity.this.context);
                    intent.setCurrentItem(i);
                    intent.setPhotoPaths(CirclesAddPicActivity.this.imagePaths);
                    CirclesAddPicActivity.this.startActivityForResult(intent, 20);
                    return;
                }
                PhotoPickerIntent intent2 = new PhotoPickerIntent(CirclesAddPicActivity.this.context);
                intent2.setSelectModel(SelectModel.MULTI);
                intent2.setShowCamera(true);
                intent2.setMaxTotal(9);
                intent2.setSelectedPaths(CirclesAddPicActivity.this.imagePaths);
                CirclesAddPicActivity.this.startActivityForResult(intent2, 10);
            }
        });
        queryPicResult();
    }

    private void queryPicResult() {
        List<DBCircleDraftBean> beans = DataSupport.findAll(DBCircleDraftBean.class, new long[0]);
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i) != null && this.ADDPIC_TYPE.equals(beans.get(i).getType()) && this.username.equals(beans.get(i).getUsername())) {
                picResult(beans.get(i));
            }
        }
    }

    private void picResult(DBCircleDraftBean bean) {
        this.editcontent.setText(bean.getText_content());
        this.editcontent.requestFocus();
        Log.e("5200", "查询到有文字图片发布1111");
        if (!TextUtils.isEmpty(bean.getImg_list())) {
            this.imagePaths.addAll(Arrays.asList(bean.getImg_list().split(",")));
            this.picAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.kv_ispublic /* 2131755228 */:
                this.kvpublic.setSelected(!this.kvpublic.isSelected());
                this.kvpublic.getTxtKey().setText(this.kvpublic.isSelected() ? R.string.no_public : R.string.is_public);
                AppShare.get(this.context).putBoolean("TAG_THRIFT_FLOW", this.kvpublic.isSelected());
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 10:
                    loadAdapter(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    return;
                case 20:
                    loadAdapter(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    return;
                default:
                    return;
            }
        }
    }

    private void loadAdapter(ArrayList<String> paths) {
        if (this.imagePaths == null) {
            this.imagePaths = new ArrayList<>();
        }
        this.imagePaths.clear();
        this.imagePaths.addAll(paths);
        this.picAdapter.notifyDataSetChanged();
    }

    @Override // com.hy.frame.adapter.IAdapterListener
    public void onItemClick(int i, Object o, int i1) {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        String content = this.editcontent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            MyToast.show(this.context, "请输入要发布的内容");
        } else if (this.imagePaths.size() == 0) {
            MyToast.show(this.context, "请选择要发布的图片");
        } else {
            getClient().showDialogNow(R.string.uploading);
            saveCirclePic();
            dealPic(content, this.imagePaths);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postPicCircle(String content, JSONArray imgs) {
        AjaxParams params = new AjaxParams();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("picList", imgs);
            jsonObject.put(EditActivity.CONTENT, ComUtil.UTF(content));
            jsonObject.put("udName", DemoHelper.getInstance().getDeviceModel());
            jsonObject.put("udid", DemoHelper.getInstance().getDeviceUni());
            jsonObject.put("type", this.ADDPIC_TYPE);
            jsonObject.put("categoryId", 0);
            getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_PUBLIC)), jsonObject.toString(), params, String.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        getApp().removeFinish(CirclesAddActivity.class);
        publishResult();
        removeDraft();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveCirclePic() {
        DBCircleDraftBean bean = new DBCircleDraftBean();
        String picStr = "";
        for (int i = 0; i < this.imagePaths.size(); i++) {
            if (i != 0) {
                picStr = picStr + ",";
            }
            picStr = picStr + this.imagePaths.get(i);
        }
        String content = this.editcontent.getText().toString();
        bean.setUsername(this.username);
        bean.setImg_list(picStr);
        bean.setText_content(content);
        bean.setType(this.ADDPIC_TYPE);
        removeDraft();
        Log.e("5200", "查询到图片保存结果" + bean.save());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDraft() {
        List<DBCircleDraftBean> beans = DataSupport.findAll(DBCircleDraftBean.class, new long[0]);
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i) != null && this.ADDPIC_TYPE.equals(beans.get(i).getType()) && this.username.equals(beans.get(i).getUsername())) {
                beans.get(i).delete();
            }
        }
    }

    private void publishResult() {
        getApp().remove(this);
        MyToast.show(getApp(), (int) R.string.toast_publish_success);
        Intent intent = new Intent(this.context, CircleActivity.class);
        intent.putExtra("publishType", this.ADDPIC_TYPE);
        intent.putExtra("publishState", true);
        startActivity(intent);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        hideSoftKeyboard();
        if (!TextUtils.isEmpty(this.editcontent.getText().toString()) || HyUtil.isNoEmpty(this.imagePaths)) {
            new SaveDraftDialog(this.context, new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddPicActivity.2
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    if (flag == 1) {
                        CirclesAddPicActivity.this.saveCirclePic();
                    } else {
                        CirclesAddPicActivity.this.removeDraft();
                    }
                    CirclesAddPicActivity.this.finish();
                }
            }).show();
        } else {
            finish();
        }
    }

    public void dealPic(final String content, List<String> imagePaths) {
        final JSONArray imgs = new JSONArray();
        Map<String, String> maps = new HashMap<>();
        try {
            for (String imagePath : imagePaths) {
                String objectKey = ComUtil.getObjectKey(this.username, imagePath);
                maps.put(objectKey, imagePath);
                JSONObject img = new JSONObject();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(imagePath, options);
                img.put("path", objectKey);
                img.put("width", options.outWidth);
                img.put(MessageEncoder.ATTR_IMG_HEIGHT, options.outHeight);
                img.put("size", new File(imagePath).length());
                imgs.put(img);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new UploadUtils().UploadCirclePic(this, this.username, Constant.CIRCLES_BUCKET, "", maps, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.circle.CirclesAddPicActivity.3
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                CirclesAddPicActivity.this.postPicCircle(content, imgs);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                MyToast.show(CirclesAddPicActivity.this.context, CirclesAddPicActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                MyLog.e("onProgress", currentSize + "/" + totalSize);
            }
        });
    }
}
