package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.CameraUtil;
import com.cdlinglu.utils.ComUtil;
import com.easeui.EaseConstant;
import com.em.DemoHelper;
import com.em.ui.ChatActivity;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.CommentPicAdapter;
import com.vsf2f.f2f.bean.OrderDetailBean;
import com.vsf2f.f2f.bean.result.CertUserInfoBean;
import com.vsf2f.f2f.ui.dialog.PictureDialog;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import com.vsf2f.f2f.ui.needs.star.StarLinearLayout;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewIntent;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import com.vsf2f.f2f.ui.view.IdentyStateView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class CommentOrderActivity extends BaseActivity implements StarLinearLayout.OnRatingChangeListener, CameraUtil.CameraDealListener, PictureDialog.ConfirmDlgListener, CommentPicAdapter.AdapterItemClick {
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private CameraUtil camera;
    private String chatTarget;
    private String content;
    private EditText et_content;
    private GridView gv_pic;
    private boolean isService;
    private ImageView iv_icon;
    private OrderDetailBean orderDetailBean;
    private CommentPicAdapter picAdapter;
    private PictureDialog pictureDlg;
    private StarLinearLayout sll_attidu;
    private TextView tv_content;
    private TextView tv_mode;
    private TextView tv_num;
    private TextView tv_price;
    private TextView tv_publisher;
    private TextView tv_starstr;
    private TextView tv_title;
    private int type;
    private String[] stars = {"差劲", "不满", "一般", "满意", "超赞"};
    private int grade = 1;
    private ArrayList<String> imagePaths = new ArrayList<>();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_comment_order;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.tab_comment, 0);
        this.orderDetailBean = (OrderDetailBean) getBundle().getSerializable("data");
        this.isService = getBundle().getBoolean("isService");
        this.type = getBundle().getInt("type", 0);
        this.iv_icon = (ImageView) getView(R.id.iv_icon);
        this.tv_num = (TextView) getView(R.id.tv_num);
        this.tv_mode = (TextView) getView(R.id.tv_mode);
        this.tv_title = (TextView) getView(R.id.tv_title);
        this.tv_price = (TextView) getView(R.id.tv_price);
        this.tv_starstr = (TextView) getView(R.id.tv_starstr);
        this.tv_content = (TextView) getView(R.id.tv_content);
        this.et_content = (EditText) getView(R.id.et_content);
        this.tv_publisher = (TextView) getView(R.id.tv_publisher);
        this.gv_pic = (GridView) getView(R.id.gv_pic);
        this.picAdapter = new CommentPicAdapter(this, this.imagePaths, this);
        this.gv_pic.setAdapter((ListAdapter) this.picAdapter);
        this.gv_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.demand.CommentOrderActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(CommentOrderActivity.this.context);
                intent.setCurrentItem(i);
                intent.setPhotoPaths(CommentOrderActivity.this.imagePaths);
                CommentOrderActivity.this.startActivityForResult(intent, 20);
            }
        });
        this.sll_attidu = (StarLinearLayout) getView(R.id.sll_attidu);
        this.sll_attidu.setOnRatingChangeListener(this);
        initOrderInfo();
        setOnClickListener(R.id.tv_send);
    }

    private void initOrderInfo() {
        OrderDetailBean.ShareObjBean shareObjBean;
        CertUserInfoBean userObjBean;
        if (this.orderDetailBean != null) {
            if (this.isService) {
                this.et_content.setHint("服务/共享已经完成，写下您的体验和评价，让更多的人看到……");
                shareObjBean = this.orderDetailBean.getShareServiceObj();
                if (this.type == 1) {
                    userObjBean = shareObjBean.getServiceUserObj();
                } else {
                    userObjBean = shareObjBean.getPublishUserObj();
                }
            } else {
                shareObjBean = this.orderDetailBean.getShareObj();
                if (this.type == 0) {
                    userObjBean = shareObjBean.getServiceUserObj();
                } else {
                    userObjBean = shareObjBean.getPublishUserObj();
                }
            }
            if (shareObjBean.getImgUrlList().size() == 0) {
                Glide.with((FragmentActivity) this).load("").error((int) R.drawable.ease_default_image).into(this.iv_icon);
            } else {
                Glide.with((FragmentActivity) this).load(shareObjBean.getImgUrlList().get(0)).error((int) R.drawable.ease_default_image).into(this.iv_icon);
            }
            ((IdentyStateView) getView(R.id.identyStateView)).setStatus(userObjBean.getCertMobile(), userObjBean.getCertRealname(), userObjBean.getCertZhima(), userObjBean.getCertAlipay(), userObjBean.getCertWechat(), userObjBean.getCertQq());
            this.tv_mode.setText(shareObjBean.getServiceModeStr() + "");
            this.tv_title.setText(shareObjBean.getTitle() + "");
            this.tv_content.setText(shareObjBean.getDescription() + "");
            this.tv_price.setText(shareObjBean.getReward() + "");
            if (this.orderDetailBean.getBuyNum() > 1) {
                this.tv_num.setText(this.orderDetailBean.getBuyNum() + "");
            }
            this.tv_publisher.setText(userObjBean.getNickName());
            this.chatTarget = userObjBean.getUserName();
        }
    }

    public void toDetail(View view) {
        Bundle bundle = new Bundle();
        if (this.isService) {
            bundle.putInt("id", this.orderDetailBean.getShareServiceObj().getMoId());
            bundle.putInt("shotid", this.orderDetailBean.getShareServiceSnapshotId());
            startAct(ServiceInfoActivity.class, bundle);
            return;
        }
        bundle.putInt("id", this.orderDetailBean.getShareObj().getMoId());
        bundle.putInt("shotid", this.orderDetailBean.getShareServiceSnapshotId());
        startAct(DemandInfoActivity.class, bundle);
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

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        this.content = this.et_content.getText().toString();
        if (this.imagePaths.size() == 0) {
            commitComment(null);
        } else {
            dealEditData();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send /* 2131755905 */:
                onRightClick();
                return;
            default:
                return;
        }
    }

    public void toChat(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("username", this.chatTarget);
        bundle.putBoolean(EaseConstant.BACK_TYPE, false);
        startAct(ChatActivity.class, bundle);
    }

    public void addPic(View view) {
        if (this.imagePaths.size() < 3) {
            if (this.camera == null) {
                this.camera = new CameraUtil(this, this);
            }
            showPictureDlg();
            return;
        }
        MyToast.show(getApplicationContext(), "最多上传三张图片");
    }

    private void dealEditData() {
        getClient().showDialogNow(R.string.toast_uploading);
        new UploadUtils().UploadFileGetUrl(this, "", DemoHelper.getInstance().getCurrentUserName(), Constant.PRODUCTS_BUCKET, Constant.PICTURE_TYPE_173, this.imagePaths, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.needs.demand.CommentOrderActivity.2
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                if (picIds == null) {
                    CommentOrderActivity.this.getClient().dialogDismiss();
                    MyToast.show(CommentOrderActivity.this.getApplicationContext(), CommentOrderActivity.this.getString(R.string.upload_failed));
                    return;
                }
                CommentOrderActivity.this.commitComment(picIds);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                CommentOrderActivity.this.getClient().dialogDismiss();
                MyToast.show(CommentOrderActivity.this.getApplicationContext(), CommentOrderActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
                CommentOrderActivity.this.getClient().showDialogNow(CommentOrderActivity.this.getString(R.string.toast_uploading_progress, new Object[]{Long.valueOf(currentSize), Long.valueOf(totalSize)}));
            }
        });
    }

    public void commitComment(List<String> picIds) {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        if (picIds != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < picIds.size(); i++) {
                if (i == 0) {
                    sb.append(picIds.get(i));
                } else {
                    sb.append("," + picIds.get(i));
                }
            }
            params.put("imgList", sb.toString());
        }
        params.put("level", this.grade);
        params.put("description", this.content);
        if (!this.isService) {
            params.put("commentType", this.type);
            params.put("shareOrderId", this.orderDetailBean.getMoId());
            getClient().post(R.string.API_ORDER_COMMENT, ComUtil.getXDDApi(this.context, getString(R.string.API_ORDER_COMMENT)), params, String.class);
            return;
        }
        params.put("commentType", (this.type + 1) % 2);
        params.put("shareServiceOrderId", this.orderDetailBean.getMoId());
        getClient().post(R.string.API_SERVICE_COMMENT, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_COMMENT)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        if (result.getErrorCode() == 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("title", R.string.comment_success);
            bundle.putInt("msg", R.string.comment_success);
            startAct(CommentSuccessActivity.class, bundle);
            finish();
            return;
        }
        MyToast.show(getApp(), result.getMsg());
    }

    private void showPictureDlg() {
        if (this.pictureDlg == null) {
            this.pictureDlg = new PictureDialog(this.context);
            this.pictureDlg.init(this);
        }
        this.pictureDlg.show();
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraTakeSuccess(String path) {
        MyLog.e("onCameraTakeSuccess: " + path);
        this.camera.cropImageUri(1, 1, 500);
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraPickSuccess(String path) {
        MyLog.e("onCameraPickSuccess: " + path);
        this.camera.cropImageUri(path, 1, 1, 500);
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraCutSuccess(String uri) {
        getClient().setShowDialog(R.string.toast_uploading);
        this.imagePaths.add(uri);
        this.picAdapter.notifyDataSetChanged();
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onFunctionCancel(String uri) {
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCameraClick(PictureDialog dlg) {
        if (this.camera == null || this.imagePaths.size() >= 4) {
            MyToast.show(getApplicationContext(), "最多只能选择三张图");
        } else {
            this.camera.onDlgCameraClick();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgPhotoClick(PictureDialog dlg) {
        PhotoPickerIntent intent = new PhotoPickerIntent(this.context);
        intent.setSelectModel(SelectModel.MULTI);
        intent.setShowCamera(false);
        intent.setMaxTotal(3);
        intent.setSelectedPaths(this.imagePaths);
        startActivityForResult(intent, 10);
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCancelClick(PictureDialog dlg) {
    }

    @Override // com.vsf2f.f2f.adapter.CommentPicAdapter.AdapterItemClick
    public void delete(int position) {
        this.imagePaths.remove(position);
        this.picAdapter.notifyDataSetChanged();
    }

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
                    if (this.camera != null) {
                        this.camera.onActivityResult(requestCode, resultCode, data);
                        return;
                    }
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

    @Override // com.vsf2f.f2f.ui.needs.star.StarLinearLayout.OnRatingChangeListener
    public void onRatingChange(double ratingCount) {
        this.tv_starstr.setText(this.stars[(int) (ratingCount - 0.5d)]);
        this.grade = (int) (2.0d * ratingCount);
    }
}
