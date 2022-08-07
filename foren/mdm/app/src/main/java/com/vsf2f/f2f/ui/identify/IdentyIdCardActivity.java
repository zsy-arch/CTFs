package com.vsf2f.f2f.ui.identify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.MyCalendar;
import com.vsf2f.f2f.bean.RealInfoBean;
import com.vsf2f.f2f.ui.dialog.MyDatePickerDialog;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.listener.UploadPicListener;
import com.vsf2f.f2f.ui.utils.photo.ImageUtil;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import com.vsf2f.f2f.ui.utils.upload.UploadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public class IdentyIdCardActivity extends BaseActivity {
    private static final int REQUEST_CAMERA_CODE = 10;
    private String backPath;
    private int choiceId;
    private EditText et_idno;
    private EditText et_real;
    private String fullPath;
    private String handPath;
    private ImageView iv_back;
    private ImageView iv_full;
    private ImageView iv_hand;
    private MyCalendar mCalendar;
    private MyDatePickerDialog myDatePickerDialog;
    private RealInfoBean realInfoBean;
    private TextView tv_time;
    private int type;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_identy_idcard;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.identy_real, R.drawable.icon_help_white);
        this.type = getBundle().getInt("type");
        this.et_real = (EditText) getView(R.id.et_real);
        this.et_idno = (EditText) getView(R.id.et_idno);
        this.iv_hand = (ImageView) getViewAndClick(R.id.iv_hand);
        this.iv_back = (ImageView) getViewAndClick(R.id.iv_back);
        this.iv_full = (ImageView) getViewAndClick(R.id.iv_full);
        this.tv_time = (TextView) getViewAndClick(R.id.tv_time);
        this.tv_time.setSelected(true);
        ImageView iv_noedit = (ImageView) getViewAndClick(R.id.iv_noedit);
        ImageView iv_noedit2 = (ImageView) getViewAndClick(R.id.iv_noedit2);
        Button button = (Button) getViewAndClick(R.id.btn_next);
        if (this.type <= 1) {
            iv_noedit.setVisibility(0);
            iv_noedit2.setVisibility(0);
            button.setVisibility(8);
        } else {
            iv_noedit.setVisibility(8);
            iv_noedit2.setVisibility(8);
        }
        if (this.type != 3) {
            queryRealInfo();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    private void queryRealInfo() {
        getClient().setShowDialog(true);
        getClient().get(R.string.QUERY_CERTIFY_INFO, ComUtil.getXDDApi(this.context, getString(R.string.QUERY_CERTIFY_INFO)), RealInfoBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.BASIC_CERTIFY /* 2131296480 */:
                MyToast.show(this.context, "实名认证申请成功");
                setResult(-1);
                finish();
                return;
            case R.string.QUERY_CERTIFY_INFO /* 2131296506 */:
                this.realInfoBean = (RealInfoBean) result.getObj();
                if (this.realInfoBean != null) {
                    this.et_real.setText(this.realInfoBean.getRealName() + "");
                    this.et_idno.setText(this.realInfoBean.getIdCardNumber() + "");
                    this.mCalendar = new MyCalendar(this.realInfoBean.getIdCardValidTime());
                    this.tv_time.setText(this.mCalendar.showString());
                    Glide.with(this.context).load(this.realInfoBean.getHandTakePositiveImg()).error((int) R.drawable.ease_default_image).into(this.iv_full);
                    Glide.with(this.context).load(this.realInfoBean.getIdCardOppositeImg()).error((int) R.drawable.ease_default_image).into(this.iv_back);
                    Glide.with(this.context).load(this.realInfoBean.getIdCardPositiveImg()).error((int) R.drawable.ease_default_image).into(this.iv_hand);
                    this.handPath = this.realInfoBean.getIdCardPositiveImg();
                    this.backPath = this.realInfoBean.getIdCardOppositeImg();
                    this.fullPath = this.realInfoBean.getHandTakePositiveImg();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FLAG_TITLE, "账户认证");
        bundle.putString(Constant.FLAG, ComUtil.getZCApi(this.context, getStringIds(Integer.valueOf((int) R.string.URL_HELP_IDENTY_REAL))));
        startAct(WebKitLocalActivity.class, bundle);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        this.choiceId = v.getId();
        switch (this.choiceId) {
            case R.id.btn_next /* 2131755974 */:
                final String realName = this.et_real.getText().toString();
                final String idNo = this.et_idno.getText().toString();
                String time = this.tv_time.getText().toString();
                if (TextUtils.isEmpty(realName)) {
                    MyToast.show(getApplicationContext(), "请输入真实姓名");
                    return;
                } else if (HyUtil.hasChinese(idNo)) {
                    MyToast.show(getApplicationContext(), "请输入正确的身份证号码");
                    return;
                } else if (TextUtils.isEmpty(time)) {
                    MyToast.show(getApplicationContext(), "请选择有效日期");
                    return;
                } else if (TextUtils.isEmpty(this.handPath)) {
                    MyToast.show(getApplicationContext(), "请选择身份证正面照片");
                    return;
                } else if (TextUtils.isEmpty(this.backPath)) {
                    MyToast.show(getApplicationContext(), "请选择身份证反面照片");
                    return;
                } else if (TextUtils.isEmpty(this.fullPath)) {
                    MyToast.show(getApplicationContext(), "请选择手持身份证照片");
                    return;
                } else {
                    getClient().showDialogNow(R.string.toast_uploading);
                    ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.identify.IdentyIdCardActivity.1
                        @Override // java.lang.Runnable
                        public void run() {
                            String path;
                            String[] paths = {IdentyIdCardActivity.this.handPath, IdentyIdCardActivity.this.backPath, IdentyIdCardActivity.this.fullPath};
                            List<String> allPaths = new ArrayList<>();
                            long ids = System.currentTimeMillis();
                            for (int i = 0; i < paths.length; i++) {
                                if (paths[i].startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                                    path = paths[i];
                                } else if (i == 2) {
                                    path = ImageUtil.compressImage(paths[i], ids + "_" + i, 1500);
                                } else {
                                    path = ImageUtil.compressImage(paths[i], ids + "_" + i);
                                }
                                allPaths.add(path);
                            }
                            IdentyIdCardActivity.this.dealEditData(realName, idNo, allPaths);
                        }
                    });
                    return;
                }
            case R.id.iv_noedit /* 2131756040 */:
            case R.id.iv_noedit2 /* 2131756048 */:
            default:
                return;
            case R.id.tv_time /* 2131756044 */:
                showMyDatePicker();
                return;
            case R.id.iv_hand /* 2131756045 */:
            case R.id.iv_back /* 2131756046 */:
            case R.id.iv_full /* 2131756047 */:
                getPhoto();
                return;
        }
    }

    private void getPhoto() {
        PhotoPickerIntent intent = new PhotoPickerIntent(this.context);
        intent.setSelectModel(SelectModel.SINGLE);
        intent.setShowCamera(true);
        startActivityForResult(intent, 10);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 10:
                    showPic(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    return;
                default:
                    return;
            }
        }
    }

    private void showPic(ArrayList<String> paths) {
        switch (this.choiceId) {
            case R.id.iv_hand /* 2131756045 */:
                this.handPath = paths.get(0);
                Glide.with(this.context).load(new File(this.handPath)).into(this.iv_hand);
                return;
            case R.id.iv_back /* 2131756046 */:
                this.backPath = paths.get(0);
                Glide.with(this.context).load(new File(this.backPath)).into(this.iv_back);
                return;
            case R.id.iv_full /* 2131756047 */:
                this.fullPath = paths.get(0);
                Glide.with(this.context).load(new File(this.fullPath)).into(this.iv_full);
                return;
            default:
                return;
        }
    }

    public void dealEditData(final String realName, final String idNo, List<String> allPaths) {
        new UploadUtils().UploadFileGetUrl(this, "0001_auth/", DemoHelper.getInstance().getCurrentUserName(), "auth", 19, allPaths, new UploadPicListener() { // from class: com.vsf2f.f2f.ui.identify.IdentyIdCardActivity.2
            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> picIds) {
                IdentyIdCardActivity.this.getClient().dialogDismiss();
                if (picIds == null) {
                    MyToast.show(IdentyIdCardActivity.this.getApplicationContext(), IdentyIdCardActivity.this.getString(R.string.upload_failed));
                    IdentyIdCardActivity.this.getClient().dialogDismiss();
                    return;
                }
                AjaxParams params = new AjaxParams();
                params.put("realName", realName);
                params.put("idCardNumber", idNo);
                params.put("idCardValidTime", IdentyIdCardActivity.this.mCalendar.toString());
                params.put("idCardPositiveImg", picIds.get(0));
                params.put("idCardOppositeImg", picIds.get(1));
                params.put("handTakePositiveImg", picIds.get(2));
                Bundle bundle = new Bundle();
                bundle.putInt("type", IdentyIdCardActivity.this.type);
                bundle.putSerializable("ajax", params);
                bundle.putSerializable("realinfo", IdentyIdCardActivity.this.realInfoBean);
                IdentyIdCardActivity.this.startAct(IdentyHeaderActivity.class, bundle);
                IdentyIdCardActivity.this.setResult(-1);
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onFailed() {
                IdentyIdCardActivity.this.getClient().dialogDismiss();
                MyToast.show(IdentyIdCardActivity.this.getApplicationContext(), IdentyIdCardActivity.this.getString(R.string.upload_failed));
            }

            @Override // com.vsf2f.f2f.ui.utils.listener.UploadPicListener
            public void onProgress(long currentSize, long totalSize) {
            }
        });
    }

    private void commitIdentify(AjaxParams params) {
        getClient().post(R.string.BASIC_CERTIFY, ComUtil.getXDDApi(this.context, getString(R.string.BASIC_CERTIFY)), params, String.class);
    }

    public void showMyDatePicker() {
        if (this.myDatePickerDialog == null) {
            this.myDatePickerDialog = new MyDatePickerDialog(this, new MyDatePickerDialog.OnDateSetListener() { // from class: com.vsf2f.f2f.ui.identify.IdentyIdCardActivity.3
                @Override // com.vsf2f.f2f.ui.dialog.MyDatePickerDialog.OnDateSetListener
                public void onDateSet(DatePicker view, MyCalendar calendar) {
                    IdentyIdCardActivity.this.mCalendar = calendar;
                    IdentyIdCardActivity.this.tv_time.setText(calendar.showString());
                }
            }, this.mCalendar);
            this.myDatePickerDialog.setCanEver();
            this.myDatePickerDialog.myShow();
            return;
        }
        this.myDatePickerDialog.reShow();
    }
}
