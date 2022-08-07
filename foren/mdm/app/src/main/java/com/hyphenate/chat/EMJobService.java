package com.hyphenate.chat;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

@TargetApi(21)
/* loaded from: classes2.dex */
public class EMJobService extends JobService {
    private static final String TAG = "JobService";

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        try {
            startService(new Intent(getApplicationContext(), EMChatService.class));
            jobFinished(jobParameters, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
