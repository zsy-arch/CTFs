package com.em.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import com.easeui.utils.EaseCommonUtils;
import com.em.video.util.Utils;
import com.hy.frame.util.MyLog;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.ImageUtils;
import com.hyphenate.util.PathUtil;
import com.vsf2f.f2f.R;
import com.yolanda.nohttp.cookie.CookieDisk;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class RecorderVideoActivity extends BaseActivity implements View.OnClickListener, SurfaceHolder.Callback, MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener {
    private static final String CLASS_LABEL = "RecordActivity";
    private static final String TAG = "RecorderVideoActivity";
    private ImageView btnStart;
    private ImageView btnStop;
    private ImageView btn_switch;
    private Chronometer chronometer;
    private Camera mCamera;
    private SurfaceHolder mSurfaceHolder;
    private VideoView mVideoView;
    private PowerManager.WakeLock mWakeLock;
    private MediaRecorder mediaRecorder;
    private String localPath = "";
    private int previewWidth = ImageUtils.SCALE_IMAGE_WIDTH;
    private int previewHeight = 480;
    private int frontCamera = 0;
    private Camera.Parameters cameraParameters = null;
    private int defaultVideoFrameRate = -1;
    private MediaScannerConnection msc = null;
    private ProgressDialog progressDialog = null;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getWindow().setFormat(-3);
        setContentView(R.layout.em_recorder_activity);
        this.mWakeLock = ((PowerManager) getSystemService("power")).newWakeLock(10, CLASS_LABEL);
        this.mWakeLock.acquire();
        initViews();
    }

    private void initViews() {
        this.btn_switch = (ImageView) findViewById(R.id.switch_btn);
        this.btn_switch.setOnClickListener(this);
        this.btn_switch.setVisibility(0);
        this.mVideoView = (VideoView) findViewById(R.id.mVideoView);
        this.btnStart = (ImageView) findViewById(R.id.recorder_start);
        this.btnStop = (ImageView) findViewById(R.id.recorder_stop);
        this.btnStart.setOnClickListener(this);
        this.btnStop.setOnClickListener(this);
        this.mSurfaceHolder = this.mVideoView.getHolder();
        this.mSurfaceHolder.addCallback(this);
        this.mSurfaceHolder.setType(3);
        this.chronometer = (Chronometer) findViewById(R.id.chronometer);
    }

    @Override // com.easeui.ui.EaseBaseActivity
    public void back(View view) {
        releaseRecorder();
        releaseCamera();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.mWakeLock == null) {
            this.mWakeLock = ((PowerManager) getSystemService("power")).newWakeLock(10, CLASS_LABEL);
            this.mWakeLock.acquire();
        }
    }

    @SuppressLint({"NewApi"})
    private boolean initCamera() {
        try {
            if (this.frontCamera == 0) {
                this.mCamera = Camera.open(0);
            } else {
                this.mCamera = Camera.open(1);
            }
            this.mCamera.getParameters();
            this.mCamera.lock();
            this.mSurfaceHolder = this.mVideoView.getHolder();
            this.mSurfaceHolder.addCallback(this);
            this.mSurfaceHolder.setType(3);
            this.mCamera.setDisplayOrientation(90);
            return true;
        } catch (RuntimeException ex) {
            EMLog.e("video", "init Camera fail " + ex.getMessage());
            return false;
        }
    }

    private void handleSurfaceChanged() {
        if (this.mCamera == null) {
            finish();
            return;
        }
        boolean hasSupportRate = false;
        List<Integer> supportedPreviewFrameRates = this.mCamera.getParameters().getSupportedPreviewFrameRates();
        if (supportedPreviewFrameRates != null && supportedPreviewFrameRates.size() > 0) {
            Collections.sort(supportedPreviewFrameRates);
            for (int i = 0; i < supportedPreviewFrameRates.size(); i++) {
                if (supportedPreviewFrameRates.get(i).intValue() == 15) {
                    hasSupportRate = true;
                }
            }
            if (hasSupportRate) {
                this.defaultVideoFrameRate = 15;
            } else {
                this.defaultVideoFrameRate = supportedPreviewFrameRates.get(0).intValue();
            }
        }
        List<Camera.Size> resolutionList = Utils.getResolutionList(this.mCamera);
        if (resolutionList != null && resolutionList.size() > 0) {
            Collections.sort(resolutionList, new Utils.ResolutionComparator());
            boolean hasSize = false;
            int i2 = 0;
            while (true) {
                if (i2 >= resolutionList.size()) {
                    break;
                }
                Camera.Size size = resolutionList.get(i2);
                if (size != null && size.width == 640 && size.height == 480) {
                    this.previewWidth = size.width;
                    this.previewHeight = size.height;
                    hasSize = true;
                    break;
                }
                i2++;
            }
            if (!hasSize) {
                int mediumResolution = resolutionList.size() / 2;
                if (mediumResolution >= resolutionList.size()) {
                    mediumResolution = resolutionList.size() - 1;
                }
                Camera.Size previewSize = resolutionList.get(mediumResolution);
                this.previewWidth = previewSize.width;
                this.previewHeight = previewSize.height;
            }
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.mWakeLock != null) {
            this.mWakeLock.release();
            this.mWakeLock = null;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.switch_btn /* 2131756658 */:
                switchCamera();
                return;
            case R.id.recorder_start /* 2131756659 */:
                if (startRecording()) {
                    Toast.makeText(this, (int) R.string.video_to_start, 0).show();
                    this.btn_switch.setVisibility(4);
                    this.btnStart.setVisibility(4);
                    this.btnStart.setEnabled(false);
                    this.btnStop.setVisibility(0);
                    this.chronometer.setBase(SystemClock.elapsedRealtime());
                    this.chronometer.start();
                    return;
                }
                return;
            case R.id.recorder_stop /* 2131756660 */:
                this.btnStop.setEnabled(false);
                stopRecording();
                this.btn_switch.setVisibility(0);
                this.chronometer.stop();
                this.btnStart.setVisibility(0);
                this.btnStop.setVisibility(4);
                showFinishDialog();
                return;
            default:
                return;
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.mSurfaceHolder = holder;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        if (this.mCamera != null || initCamera()) {
            try {
                this.mCamera.setPreviewDisplay(this.mSurfaceHolder);
                this.mCamera.startPreview();
                handleSurfaceChanged();
            } catch (Exception e1) {
                EMLog.e("video", "start preview fail " + e1.getMessage());
                showFailDialog();
            }
        } else {
            showFailDialog();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder arg0) {
        EMLog.v("video", "surfaceDestroyed");
    }

    public boolean startRecording() {
        try {
            if (this.mediaRecorder == null && !initRecorder()) {
                return false;
            }
            this.mediaRecorder.setOnInfoListener(this);
            this.mediaRecorder.setOnErrorListener(this);
            this.mediaRecorder.start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    private boolean initRecorder() {
        if (!EaseCommonUtils.isSdcardExist()) {
            showNoSDCardDialog();
            return false;
        } else if (this.mCamera != null || initCamera()) {
            this.mVideoView.setVisibility(0);
            this.mCamera.stopPreview();
            this.mediaRecorder = new MediaRecorder();
            this.mCamera.unlock();
            this.mediaRecorder.setCamera(this.mCamera);
            this.mediaRecorder.setAudioSource(0);
            this.mediaRecorder.setVideoSource(1);
            if (this.frontCamera == 1) {
                this.mediaRecorder.setOrientationHint(270);
            } else {
                this.mediaRecorder.setOrientationHint(90);
            }
            this.mediaRecorder.setOutputFormat(2);
            this.mediaRecorder.setAudioEncoder(3);
            this.mediaRecorder.setVideoEncoder(2);
            this.mediaRecorder.setVideoSize(this.previewWidth, this.previewHeight);
            this.mediaRecorder.setVideoEncodingBitRate(393216);
            if (this.defaultVideoFrameRate != -1) {
                this.mediaRecorder.setVideoFrameRate(this.defaultVideoFrameRate);
            }
            this.localPath = PathUtil.getInstance().getVideoPath() + "/" + System.currentTimeMillis() + ".mp4";
            this.mediaRecorder.setOutputFile(this.localPath);
            this.mediaRecorder.setMaxDuration(30000);
            this.mediaRecorder.setPreviewDisplay(this.mSurfaceHolder.getSurface());
            try {
                this.mediaRecorder.prepare();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
                return false;
            }
        } else {
            showFailDialog();
            return false;
        }
    }

    public void stopRecording() {
        if (this.mediaRecorder != null) {
            this.mediaRecorder.setOnErrorListener(null);
            this.mediaRecorder.setOnInfoListener(null);
            try {
                this.mediaRecorder.stop();
            } catch (Exception e) {
                EMLog.e("video", "stopRecording error:" + e.getMessage());
            }
        }
        releaseRecorder();
        if (this.mCamera != null) {
            this.mCamera.stopPreview();
            releaseCamera();
        }
    }

    private void releaseRecorder() {
        if (this.mediaRecorder != null) {
            this.mediaRecorder.release();
            this.mediaRecorder = null;
        }
    }

    protected void releaseCamera() {
        try {
            if (this.mCamera != null) {
                this.mCamera.stopPreview();
                this.mCamera.release();
                this.mCamera = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"NewApi"})
    public void switchCamera() {
        if (this.mCamera != null && Camera.getNumberOfCameras() >= 2) {
            this.btn_switch.setEnabled(false);
            if (this.mCamera != null) {
                this.mCamera.stopPreview();
                this.mCamera.release();
                this.mCamera = null;
            }
            switch (this.frontCamera) {
                case 0:
                    this.mCamera = Camera.open(1);
                    this.frontCamera = 1;
                    break;
                case 1:
                    this.mCamera = Camera.open(0);
                    this.frontCamera = 0;
                    break;
            }
            try {
                this.mCamera.lock();
                this.mCamera.setDisplayOrientation(90);
                this.mCamera.setPreviewDisplay(this.mVideoView.getHolder());
                this.mCamera.startPreview();
            } catch (IOException e) {
                this.mCamera.release();
                this.mCamera = null;
            }
            this.btn_switch.setEnabled(true);
        }
    }

    public void sendVideo(View view) {
        if (TextUtils.isEmpty(this.localPath)) {
            EMLog.e("Recorder", "recorder fail please try again!");
            return;
        }
        if (this.msc == null) {
            this.msc = new MediaScannerConnection(this, new MediaScannerConnection.MediaScannerConnectionClient() { // from class: com.em.ui.RecorderVideoActivity.1
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String path, Uri uri) {
                    EMLog.d(RecorderVideoActivity.TAG, "scanner completed");
                    RecorderVideoActivity.this.msc.disconnect();
                    RecorderVideoActivity.this.progressDialog.dismiss();
                    String[] dur1 = RecorderVideoActivity.this.chronometer.getText().toString().split(":");
                    int dur = (Integer.valueOf(dur1[0]).intValue() * 60) + Integer.valueOf(dur1[1]).intValue();
                    MyLog.e("filePath:" + uri + "||duration:" + dur);
                    RecorderVideoActivity.this.setResult(-1, RecorderVideoActivity.this.getIntent().putExtra(CookieDisk.URI, uri).putExtra("dur", dur));
                    RecorderVideoActivity.this.finish();
                }

                @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
                public void onMediaScannerConnected() {
                    RecorderVideoActivity.this.msc.scanFile(RecorderVideoActivity.this.localPath, "video/*");
                }
            });
        }
        if (this.progressDialog == null) {
            this.progressDialog = new ProgressDialog(this);
            this.progressDialog.setMessage("处理中...");
            this.progressDialog.setCancelable(false);
        }
        this.progressDialog.show();
        this.msc.connect();
    }

    @Override // android.media.MediaRecorder.OnInfoListener
    public void onInfo(MediaRecorder mr, int what, int extra) {
        EMLog.v("video", "onInfo");
        if (what == 800) {
            EMLog.v("video", "max duration reached");
            stopRecording();
            this.btn_switch.setVisibility(0);
            this.chronometer.stop();
            this.btnStart.setVisibility(0);
            this.btnStop.setVisibility(4);
            this.chronometer.stop();
            if (this.localPath != null) {
                showFinishDialog();
            }
        }
    }

    private void showFinishDialog() {
        new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.whether_to_send)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.em.ui.RecorderVideoActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                RecorderVideoActivity.this.sendVideo(null);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.em.ui.RecorderVideoActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                if (RecorderVideoActivity.this.localPath != null) {
                    File file = new File(RecorderVideoActivity.this.localPath);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                RecorderVideoActivity.this.finish();
            }
        }).setCancelable(false).show();
    }

    @Override // android.media.MediaRecorder.OnErrorListener
    public void onError(MediaRecorder mr, int what, int extra) {
        EMLog.e("video", "recording onError:");
        stopRecording();
        Toast.makeText(this, "Recording error has occurred. Stopping the recording", 0).show();
    }

    public void saveBitmapFile(Bitmap bitmap) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "a.jpg")));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        releaseCamera();
        if (this.mWakeLock != null) {
            this.mWakeLock.release();
            this.mWakeLock = null;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        back(null);
    }

    private void showFailDialog() {
        new AlertDialog.Builder(this).setTitle(R.string.prompt).setMessage(R.string.open_the_equipment_failure).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.em.ui.RecorderVideoActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                RecorderVideoActivity.this.finish();
            }
        }).setCancelable(false).show();
    }

    private void showNoSDCardDialog() {
        new AlertDialog.Builder(this).setTitle(R.string.prompt).setMessage("No sd card!").setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.em.ui.RecorderVideoActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                RecorderVideoActivity.this.finish();
            }
        }).setCancelable(false).show();
    }
}
