package com.easeui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.easeui.model.EaseVoiceRecorder;
import com.easeui.utils.EaseCommonUtils;
import com.easeui.widget.chatrow.EaseChatRowVoicePlayClickListener;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseVoiceRecorderView extends RelativeLayout {
    protected Context context;
    protected LayoutInflater inflater;
    protected int maxIndex;
    protected ImageView micImage;
    protected Drawable[] micImages;
    protected TextView recordingHint;
    protected EaseVoiceRecorder voiceRecorder;
    protected PowerManager.WakeLock wakeLock;
    protected Handler micImageHandler = new Handler() { // from class: com.easeui.widget.EaseVoiceRecorderView.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what > EaseVoiceRecorderView.this.maxIndex) {
                msg.what = EaseVoiceRecorderView.this.maxIndex;
            }
            EaseVoiceRecorderView.this.micImage.setBackgroundDrawable(EaseVoiceRecorderView.this.micImages[msg.what]);
        }
    };
    private boolean isOverLimit = false;

    /* loaded from: classes.dex */
    public interface EaseVoiceRecorderCallback {
        void onVoiceRecordComplete(String str, int i);
    }

    public EaseVoiceRecorderView(Context context) {
        super(context);
        init(context);
    }

    public EaseVoiceRecorderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EaseVoiceRecorderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.ease_widget_voice_recorder, this);
        this.micImage = (ImageView) findViewById(R.id.mic_image);
        this.recordingHint = (TextView) findViewById(R.id.recording_hint);
        this.voiceRecorder = new EaseVoiceRecorder(this.micImageHandler);
        this.micImages = new Drawable[]{getResources().getDrawable(R.drawable.ease_record_animate_01), getResources().getDrawable(R.drawable.ease_record_animate_02), getResources().getDrawable(R.drawable.ease_record_animate_03), getResources().getDrawable(R.drawable.ease_record_animate_04), getResources().getDrawable(R.drawable.ease_record_animate_05), getResources().getDrawable(R.drawable.ease_record_animate_06), getResources().getDrawable(R.drawable.ease_record_animate_07), getResources().getDrawable(R.drawable.ease_record_animate_08)};
        this.maxIndex = this.micImages.length - 1;
        this.wakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(6, "demo");
    }

    public boolean onPressToSpeakBtnTouch(View v, MotionEvent event, EaseVoiceRecorderCallback recorderCallback) {
        return onPressToSpeakBtnTouch(v, event, recorderCallback, 60, R.string.max_recording_time_is_too_short);
    }

    public boolean onPressToSpeakBtnTouch(View v, MotionEvent event, EaseVoiceRecorderCallback recorderCallback, int time, @StringRes int prompt) {
        switch (event.getAction()) {
            case 0:
                this.isOverLimit = false;
                try {
                    if (EaseChatRowVoicePlayClickListener.isPlaying) {
                        EaseChatRowVoicePlayClickListener.currentPlayListener.stopPlayVoice();
                    }
                    v.setPressed(true);
                    startRecording();
                    return true;
                } catch (Exception e) {
                    v.setPressed(false);
                    return true;
                }
            case 1:
                v.setPressed(false);
                if (this.isOverLimit) {
                    return true;
                }
                if (event.getY() < 0.0f) {
                    discardRecording();
                    return true;
                }
                try {
                    int length = stopRecoding();
                    if (length > 0) {
                        if (recorderCallback != null) {
                            recorderCallback.onVoiceRecordComplete(getVoiceFilePath(), length);
                        }
                    } else if (length == 401) {
                        Toast.makeText(this.context, (int) R.string.recording_without_permission, 0).show();
                    } else {
                        Toast.makeText(this.context, (int) R.string.recording_time_is_too_short, 0).show();
                    }
                    return true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    Toast.makeText(this.context, (int) R.string.send_failure_please, 0).show();
                    return true;
                }
            case 2:
                if (event.getY() < 0.0f) {
                    showReleaseToCancelHint();
                } else {
                    showMoveUpToCancelHint();
                }
                int currentL = this.voiceRecorder.getVoiceLength();
                if (this.isOverLimit || currentL < time) {
                    return true;
                }
                this.isOverLimit = true;
                stopRecoding();
                if (recorderCallback != null) {
                    recorderCallback.onVoiceRecordComplete(getVoiceFilePath(), 60);
                }
                Toast.makeText(this.context, prompt, 0).show();
                return true;
            default:
                discardRecording();
                return false;
        }
    }

    public void startRecording() {
        if (!EaseCommonUtils.isSdcardExist()) {
            Toast.makeText(this.context, (int) R.string.send_voice_need_sdcard_support, 0).show();
            return;
        }
        try {
            this.wakeLock.acquire();
            setVisibility(0);
            this.recordingHint.setText(this.context.getString(R.string.move_up_to_cancel));
            this.recordingHint.setBackgroundColor(0);
            this.voiceRecorder.startRecording(this.context);
        } catch (Exception e) {
            e.printStackTrace();
            if (this.wakeLock.isHeld()) {
                this.wakeLock.release();
            }
            if (this.voiceRecorder != null) {
                this.voiceRecorder.discardRecording();
            }
            setVisibility(4);
            Toast.makeText(this.context, (int) R.string.recoding_fail, 0).show();
        }
    }

    public void showReleaseToCancelHint() {
        this.recordingHint.setText(this.context.getString(R.string.release_to_cancel));
        this.recordingHint.setBackgroundResource(R.drawable.ease_recording_text_hint_bg);
    }

    public void showMoveUpToCancelHint() {
        this.recordingHint.setText(this.context.getString(R.string.move_up_to_cancel));
        this.recordingHint.setBackgroundColor(0);
    }

    public void discardRecording() {
        if (this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
        try {
            if (this.voiceRecorder.isRecording()) {
                this.voiceRecorder.discardRecording();
                setVisibility(4);
            }
        } catch (Exception e) {
        }
    }

    public int stopRecoding() {
        setVisibility(4);
        if (this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
        return this.voiceRecorder.stopRecoding();
    }

    public String getVoiceFilePath() {
        return this.voiceRecorder.getVoiceFilePath();
    }

    public String getVoiceFileName() {
        return this.voiceRecorder.getVoiceFileName();
    }

    public boolean isRecording() {
        return this.voiceRecorder.isRecording();
    }
}
