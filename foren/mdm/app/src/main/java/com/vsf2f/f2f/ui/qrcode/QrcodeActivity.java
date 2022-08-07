package com.vsf2f.f2f.ui.qrcode;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.alipay.sdk.sys.a;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.EaseConstant;
import com.em.DemoHelper;
import com.em.ui.ChatActivity;
import com.em.ui.UserProfileActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.hy.http.IMyHttpListener;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.GroupInfoBean;
import com.vsf2f.f2f.bean.result.AddGroupBean;
import com.vsf2f.f2f.ui.needs.demand.StartServiceActivity;
import com.vsf2f.f2f.ui.qrcode.camera.CameraManager;
import com.vsf2f.f2f.ui.qrcode.decoding.CaptureActivityHandler;
import com.vsf2f.f2f.ui.qrcode.decoding.InactivityTimer;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.user.pwd.RegistActivity;
import com.vsf2f.f2f.ui.utils.QrCodeAnalyseUtil;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerActivity;
import com.vsf2f.f2f.ui.utils.photo.PhotoPickerIntent;
import com.vsf2f.f2f.ui.utils.photo.SelectModel;
import com.vsf2f.f2f.ui.utils.web.UrlUtils;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/* loaded from: classes2.dex */
public class QrcodeActivity extends BaseActivity implements SurfaceHolder.Callback, IMyHttpListener {
    private static final String ADDFRIEND_URL = "vsf2f://addfriend";
    private static final String ADDGROUP_URL = "vsf2f://addgroup";
    private static final float BEEP_VOLUME = 0.1f;
    private static final String REGISTER_URL = "/m/registered.mobile";
    private static final String REGISTER_URL2 = "/m/scan/join/";
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final String STARTSERVICE_URL = "vsf2f://startService";
    private static final String STARTSHARE_URL = "vsf2f://startShare";
    public static final int TO_QRCODE = 222;
    private static final long VIBRATE_DURATION = 200;
    private String bizId;
    private String characterSet;
    private Vector<BarcodeFormat> decodeFormats;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private boolean isForResult;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private SurfaceView surfaceView;
    private boolean vibrate;
    private ViewfinderView viewfinderView;
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() { // from class: com.vsf2f.f2f.ui.qrcode.QrcodeActivity.1
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
    Handler handlera = new Handler();

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_qrcode;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.qrcode_title, R.string.album);
        this.surfaceView = (SurfaceView) getView(R.id.qrcode_viewShow);
        this.viewfinderView = (ViewfinderView) getView(R.id.qrcode_viewfinder);
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        PhotoPickerIntent intent = new PhotoPickerIntent(this.context);
        intent.setSelectModel(SelectModel.SINGLE);
        intent.setShowCamera(false);
        startActivityForResult(intent, 10);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        CameraManager.init(getApplication());
        if (getBundle() != null) {
            this.isForResult = getBundle().getBoolean("getcode", false);
        }
        this.hasSurface = false;
        this.inactivityTimer = new InactivityTimer(this);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        v.getId();
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        resetCamera();
    }

    private void resetCamera() {
        SurfaceHolder surfaceHolder = this.surfaceView.getHolder();
        if (this.hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(3);
        }
        this.decodeFormats = null;
        this.characterSet = null;
        this.playBeep = true;
        if (((AudioManager) getSystemService("audio")).getRingerMode() != 2) {
            this.playBeep = false;
        }
        initBeepSound();
        this.vibrate = true;
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.handler != null) {
            this.handler.quitSynchronously();
            this.handler = null;
        }
        CameraManager.get().closeDriver();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.inactivityTimer.shutdown();
        super.onDestroy();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
            Rect frame = CameraManager.get().getFramingRect();
            if (frame != null) {
                MyLog.d("frame:" + frame.toShortString());
            }
            if (this.handler == null) {
                this.handler = new CaptureActivityHandler(this, this.decodeFormats, this.characterSet);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        if (!this.hasSurface) {
            this.hasSurface = true;
            initCamera(holder);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.hasSurface = false;
    }

    public ViewfinderView getViewfinderView() {
        return this.viewfinderView;
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void drawViewfinder() {
        this.viewfinderView.drawViewfinder();
    }

    public void handleDecode(Result obj, Bitmap barcode) {
        this.inactivityTimer.onActivity();
        Rect frame = CameraManager.get().getFramingRect();
        if (!(frame == null || obj.getText() == null)) {
            this.viewfinderView.drawResultBitmap(QrcodeUtil.createQrImage(obj.getText(), frame.width(), frame.height()));
        }
        playBeepSoundAndVibrate();
        dealDecodeResult(obj.getText());
    }

    public void dealDecodeResult(String text) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(text)) {
            MyLog.e(text);
            try {
                if (text.contains(REGISTER_URL)) {
                    turnRegister(UrlUtils.getValueByName(text, "guid"));
                } else if (text.contains(REGISTER_URL2)) {
                    turnRegister(text.substring(text.indexOf("join/") + 5, text.indexOf(".mobile")));
                } else if (text.contains(ADDFRIEND_URL)) {
                    if (isLogin()) {
                        bundle.putString("username", text.substring(text.indexOf("#") + 1));
                        startAct(UserProfileActivity.class, bundle);
                    } else {
                        MyToast.show(this.context, "请先登录后，再扫好友二维码");
                    }
                    finish();
                } else if (text.contains(ADDGROUP_URL)) {
                    if (isLogin()) {
                        addGroup(text.substring(text.indexOf("#") + 1));
                        return;
                    }
                    MyToast.show(this.context, "请先登录后，再扫二维码");
                    finish();
                } else if (text.contains(STARTSHARE_URL)) {
                    if (isLogin()) {
                        String moId = text.substring(text.indexOf("moId=") + 5, text.indexOf(a.b));
                        String code = text.substring(text.indexOf("Code=") + 5);
                        bundle.putInt("id", Integer.parseInt(moId));
                        bundle.putString("code", code);
                        startAct(StartServiceActivity.class, bundle);
                        finish();
                        return;
                    }
                    MyToast.show(this.context, "请先登录后，再扫二维码");
                    finish();
                } else if (!text.contains(STARTSERVICE_URL)) {
                    bundle.putString(Constant.FLAG_TITLE, com.vsf2f.f2f.ui.utils.Constant.FLAG_AUTO_TITLE);
                    if (!isLogin() || !text.startsWith(getString(R.string.API_HOST))) {
                        bundle.putString(Constant.FLAG, text);
                    } else {
                        bundle.putString(Constant.FLAG, WebUtils.getTokenUrl(this.context, text));
                    }
                    startAct(WebKitLocalActivity.class, bundle);
                    finish();
                } else if (isLogin()) {
                    String moId2 = text.substring(text.indexOf("moId=") + 5, text.indexOf(a.b));
                    String code2 = text.substring(text.indexOf("Code=") + 5);
                    bundle.putInt("id", Integer.parseInt(moId2));
                    bundle.putString("code", code2);
                    bundle.putBoolean("isService", true);
                    startAct(StartServiceActivity.class, bundle);
                    finish();
                } else {
                    MyToast.show(this.context, "请先登录后，再扫二维码");
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addGroup(String bizId) {
        this.bizId = bizId;
        getClient().post(R.string.API_ADD_GROUP_MEMBER, ComUtil.getF2FApi(this.context, getString(R.string.API_ADD_GROUP_MEMBER, new Object[]{DemoHelper.getInstance().getCurrentUserName(), bizId})), null, AddGroupBean.class);
    }

    public void getGroupInfo() {
        getClient().get(R.string.API_GET_GROUP_INFO, ComUtil.getF2FApi(this.context, getString(R.string.API_GET_GROUP_INFO, new Object[]{this.bizId})), new AjaxParams(), GroupInfoBean.class, false);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_ADD_GROUP_MEMBER /* 2131296286 */:
                if (((AddGroupBean) result.getObj()).getSeccuse().size() != 0) {
                    MyToast.show(this.context, "加群成功");
                } else {
                    MyToast.show(this.context, "您已经是群成员");
                }
                getGroupInfo();
                return;
            case R.string.API_GET_GROUP_INFO /* 2131296355 */:
                Bundle bundle = new Bundle();
                bundle.putString("username", ((GroupInfoBean) result.getObj()).getImGroupid());
                bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, 2);
                startAct(ChatActivity.class, bundle);
                finish();
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        switch (result.getRequestCode()) {
            case R.string.API_ADD_GROUP_MEMBER /* 2131296286 */:
                MyToast.show(this.context, "加群失败,此群不存在");
                finish();
                return;
            case R.string.API_GET_GROUP_INFO /* 2131296355 */:
                finish();
                return;
            default:
                return;
        }
    }

    private void turnRegister(String guid) {
        if (isNoLogin()) {
            Bundle bundle = new Bundle();
            bundle.putString(com.vsf2f.f2f.ui.utils.Constant.USER_GUID, guid);
            if (this.isForResult) {
                setResult(-1, new Intent().putExtras(bundle));
            } else {
                startAct(RegistActivity.class, bundle);
            }
        } else {
            MyToast.show(this.context, "您已登录，无需注册");
        }
        finish();
    }

    private void initBeepSound() {
        if (this.playBeep && this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
            this.mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setOnCompletionListener(this.beepListener);
            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
            try {
                this.mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                file.close();
                this.mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                this.mediaPlayer.prepare();
            } catch (IOException e) {
                this.mediaPlayer = null;
            }
        }
    }

    private void playBeepSoundAndVibrate() {
        Vibrator vibrator;
        if (this.playBeep && this.mediaPlayer != null) {
            this.mediaPlayer.start();
        }
        if (this.vibrate && (vibrator = (Vibrator) getSystemService("vibrator")) != null) {
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        finish();
        return true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 10:
                    getClient().showDialogNow(R.string.looking);
                    List<String> path = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    if (path != null && path.size() != 0) {
                        final String resultTxt = QrCodeAnalyseUtil.getContentByCode(path.get(0));
                        Rect frame = CameraManager.get().getFramingRect();
                        MyLog.e("frame=" + frame + ";resultTxt=" + resultTxt);
                        if (frame == null || TextUtils.isEmpty(resultTxt)) {
                            MyToast.show(getApplicationContext(), "二维码识别失败");
                            getClient().dialogDismiss();
                            return;
                        }
                        this.viewfinderView.drawResultBitmap(QrcodeUtil.createQrImage(resultTxt, frame.width(), frame.height()));
                        playBeepSoundAndVibrate();
                        this.handlera.postDelayed(new Runnable() { // from class: com.vsf2f.f2f.ui.qrcode.QrcodeActivity.2
                            @Override // java.lang.Runnable
                            public void run() {
                                QrcodeActivity.this.getClient().dialogDismiss();
                                QrcodeActivity.this.dealDecodeResult(resultTxt);
                            }
                        }, 500L);
                        return;
                    }
                    return;
                case 999:
                    setResult(-1);
                    finish();
                    return;
                default:
                    return;
            }
        }
    }
}
