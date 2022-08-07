package com.easeui.model;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.format.Time;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.PathUtil;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/* loaded from: classes.dex */
public class EaseVoiceRecorder {
    static final String EXTENSION = ".amr";
    static final String PREFIX = "voice";
    private File file;
    private Handler handler;
    MediaRecorder recorder;
    private long startTime;
    private boolean isRecording = false;
    private String voiceFilePath = null;
    private String voiceFileName = null;

    public EaseVoiceRecorder(Handler handler) {
        this.handler = handler;
    }

    public String startRecording(Context appContext) {
        this.file = null;
        try {
            if (this.recorder != null) {
                this.recorder.release();
                this.recorder = null;
            }
            this.recorder = new MediaRecorder();
            this.recorder.setAudioSource(1);
            this.recorder.setOutputFormat(3);
            this.recorder.setAudioEncoder(1);
            this.recorder.setAudioChannels(1);
            this.recorder.setAudioSamplingRate(8000);
            this.recorder.setAudioEncodingBitRate(64);
            this.voiceFileName = getVoiceFileName(EMClient.getInstance().getCurrentUser());
            this.voiceFilePath = PathUtil.getInstance().getVoicePath() + "/" + this.voiceFileName;
            this.file = new File(this.voiceFilePath);
            this.recorder.setOutputFile(this.file.getAbsolutePath());
            this.recorder.prepare();
            this.isRecording = true;
            this.recorder.start();
        } catch (IOException e) {
            EMLog.e(PREFIX, "prepare() failed");
        }
        ThreadPool.newThreadPool(new Runnable() { // from class: com.easeui.model.EaseVoiceRecorder.1
            @Override // java.lang.Runnable
            public void run() {
                while (EaseVoiceRecorder.this.isRecording) {
                    try {
                        Message msg = new Message();
                        msg.what = (EaseVoiceRecorder.this.recorder.getMaxAmplitude() * 13) / 32767;
                        EaseVoiceRecorder.this.handler.sendMessage(msg);
                        SystemClock.sleep(100L);
                    } catch (Exception e2) {
                        EMLog.e(EaseVoiceRecorder.PREFIX, e2.toString());
                        return;
                    }
                }
            }
        });
        this.startTime = new Date().getTime();
        EMLog.d(PREFIX, "start voice recording to file:" + this.file.getAbsolutePath());
        if (this.file == null) {
            return null;
        }
        return this.file.getAbsolutePath();
    }

    public void discardRecording() {
        if (this.recorder != null) {
            try {
                this.recorder.stop();
                this.recorder.release();
                this.recorder = null;
                if (this.file != null && this.file.exists() && !this.file.isDirectory()) {
                    this.file.delete();
                }
            } catch (IllegalStateException e) {
            } catch (RuntimeException e2) {
            }
            this.isRecording = false;
        }
    }

    public int stopRecoding() {
        if (this.recorder == null) {
            return 0;
        }
        this.isRecording = false;
        try {
            this.recorder.stop();
            this.recorder.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.recorder = null;
        if (this.file == null || !this.file.exists() || !this.file.isFile()) {
            return 401;
        }
        if (this.file.length() == 0) {
            this.file.delete();
            return 401;
        }
        int seconds = ((int) (new Date().getTime() - this.startTime)) / 1000;
        EMLog.d(PREFIX, "voice recording finished. seconds:" + seconds + " file length:" + this.file.length());
        return seconds;
    }

    public int getVoiceLength() {
        return ((int) (new Date().getTime() - this.startTime)) / 1000;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.recorder != null) {
            this.recorder.release();
        }
    }

    private String getVoiceFileName(String uid) {
        Time now = new Time();
        now.setToNow();
        return uid + now.toString().substring(0, 15) + EXTENSION;
    }

    public boolean isRecording() {
        return this.isRecording;
    }

    public String getVoiceFilePath() {
        return this.voiceFilePath;
    }

    public String getVoiceFileName() {
        return this.voiceFileName;
    }
}
