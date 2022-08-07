package com.hy.frame.audio;

import android.media.AudioTrack;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/* loaded from: classes2.dex */
public class AudioPlayer implements IPlayComplete {
    public static final int STATE_MSG_ID = 16;
    private static final String TAG = "AudioPlayer";
    private AudioParam mAudioParam;
    private AudioTrack mAudioTrack;
    private byte[] mData;
    private Handler mHandler;
    private PlayAudioThread mPlayAudioThread;
    private boolean mBReady = false;
    private boolean mThreadExitFlag = false;
    private int mPrimePlaySize = 0;
    private int mPlayOffset = 0;
    private int mPlayState = 0;

    public AudioPlayer(Handler handler) {
        this.mHandler = handler;
    }

    public AudioPlayer(Handler handler, AudioParam audioParam) {
        this.mHandler = handler;
        setAudioParam(audioParam);
    }

    public void setAudioParam(AudioParam audioParam) {
        this.mAudioParam = audioParam;
    }

    public void setDataSource(byte[] data) {
        this.mData = data;
    }

    public boolean prepare() {
        if (this.mData == null || this.mAudioParam == null) {
            return false;
        }
        if (this.mBReady) {
            return true;
        }
        try {
            createAudioTrack();
            this.mBReady = true;
            setPlayState(1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean release() {
        stop();
        releaseAudioTrack();
        this.mBReady = false;
        setPlayState(0);
        return true;
    }

    public boolean play() {
        if (!this.mBReady) {
            return false;
        }
        switch (this.mPlayState) {
            case 1:
                this.mPlayOffset = 0;
                setPlayState(2);
                startThread();
                break;
            case 3:
                setPlayState(2);
                startThread();
                break;
        }
        return true;
    }

    public boolean pause() {
        if (!this.mBReady) {
            return false;
        }
        if (this.mPlayState == 2) {
            setPlayState(3);
            stopThread();
        }
        return true;
    }

    public boolean stop() {
        if (!this.mBReady) {
            return false;
        }
        setPlayState(1);
        stopThread();
        return true;
    }

    private synchronized void setPlayState(int state) {
        this.mPlayState = state;
        if (this.mHandler != null) {
            Message msg = this.mHandler.obtainMessage(16);
            msg.obj = Integer.valueOf(this.mPlayState);
            msg.sendToTarget();
        }
    }

    private void createAudioTrack() throws Exception {
        int minBufSize = AudioTrack.getMinBufferSize(this.mAudioParam.getmFrequency(), this.mAudioParam.getmChannel(), this.mAudioParam.getmSampBit());
        this.mPrimePlaySize = minBufSize * 2;
        Log.d(TAG, "mPrimePlaySize = " + this.mPrimePlaySize);
        this.mAudioTrack = new AudioTrack(3, this.mAudioParam.getmFrequency(), this.mAudioParam.getmChannel(), this.mAudioParam.getmSampBit(), minBufSize, 1);
    }

    private void releaseAudioTrack() {
        if (this.mAudioTrack != null) {
            if (this.mAudioTrack.getState() != 0) {
                if (this.mAudioTrack.getPlayState() != 1) {
                    try {
                        this.mAudioTrack.stop();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
                this.mAudioTrack.release();
            }
            this.mAudioTrack = null;
        }
    }

    private void startThread() {
        if (this.mPlayAudioThread == null) {
            this.mThreadExitFlag = false;
            this.mPlayAudioThread = new PlayAudioThread();
            this.mPlayAudioThread.start();
        }
    }

    private void stopThread() {
        if (this.mPlayAudioThread != null) {
            this.mThreadExitFlag = true;
            this.mPlayAudioThread = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class PlayAudioThread extends Thread {
        PlayAudioThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Log.d(AudioPlayer.TAG, "PlayAudioThread run mPlayOffset = " + AudioPlayer.this.mPlayOffset);
            AudioPlayer.this.mAudioTrack.play();
            while (true) {
                if (AudioPlayer.this.mThreadExitFlag) {
                    break;
                }
                try {
                    AudioPlayer.this.mAudioTrack.write(AudioPlayer.this.mData, AudioPlayer.this.mPlayOffset, AudioPlayer.this.mPrimePlaySize);
                    AudioPlayer.this.mPlayOffset += AudioPlayer.this.mPrimePlaySize;
                    if (AudioPlayer.this.mPlayOffset >= AudioPlayer.this.mData.length) {
                        AudioPlayer.this.onPlayComplete();
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    AudioPlayer.this.onPlayComplete();
                }
            }
            if (!(AudioPlayer.this.mAudioTrack == null || AudioPlayer.this.mAudioTrack.getState() == 0)) {
                if (AudioPlayer.this.mAudioTrack.getPlayState() != 1) {
                    try {
                        AudioPlayer.this.mAudioTrack.stop();
                    } catch (IllegalStateException e2) {
                        e2.printStackTrace();
                    }
                }
                AudioPlayer.this.mAudioTrack.release();
            }
            Log.d(AudioPlayer.TAG, "PlayAudioThread complete...");
        }
    }

    @Override // com.hy.frame.audio.IPlayComplete
    public void onPlayComplete() {
        this.mPlayAudioThread = null;
        if (this.mPlayState != 3) {
            setPlayState(1);
        }
    }
}
