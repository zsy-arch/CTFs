package com.superrtc.util;

import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/* loaded from: classes2.dex */
public class CpuMonitor {
    private static final int SAMPLE_SAVE_NUMBER = 10;
    private static final String TAG = "CpuMonitor";
    private int cpuAvg3;
    private int cpuAvgAll;
    private int cpuCurrent;
    private long[] cpuFreq;
    private int cpusPresent;
    private String[] curPath;
    ProcStat lastProcStat;
    private String[] maxPath;
    private int[] percentVec = new int[10];
    private int sum3 = 0;
    private int sum10 = 0;
    private double lastPercentFreq = -1.0d;
    private boolean initialized = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ProcStat {
        final long idleTime;
        final long runTime;

        ProcStat(long aRunTime, long aIdleTime) {
            this.runTime = aRunTime;
            this.idleTime = aIdleTime;
        }
    }

    private void init() {
        try {
            FileReader fin = new FileReader("/sys/devices/system/cpu/present");
            try {
                Scanner scanner = new Scanner(new BufferedReader(fin)).useDelimiter("[-\n]");
                scanner.nextInt();
                this.cpusPresent = scanner.nextInt() + 1;
                scanner.close();
            } catch (Exception e) {
                Log.e(TAG, "Cannot do CPU stats due to /sys/devices/system/cpu/present parsing problem");
            } finally {
                fin.close();
            }
        } catch (FileNotFoundException e2) {
            Log.e(TAG, "Cannot do CPU stats since /sys/devices/system/cpu/present is missing");
        } catch (IOException e3) {
            Log.e(TAG, "Error closing file");
        }
        this.cpuFreq = new long[this.cpusPresent];
        this.maxPath = new String[this.cpusPresent];
        this.curPath = new String[this.cpusPresent];
        for (int i = 0; i < this.cpusPresent; i++) {
            this.cpuFreq[i] = 0;
            this.maxPath[i] = "/sys/devices/system/cpu/cpu" + i + "/cpufreq/cpuinfo_max_freq";
            this.curPath[i] = "/sys/devices/system/cpu/cpu" + i + "/cpufreq/scaling_cur_freq";
        }
        this.lastProcStat = new ProcStat(0L, 0L);
        this.initialized = true;
    }

    public boolean sampleCpuUtilization() {
        double percentFreq;
        long lastSeenMaxFreq = 0;
        long cpufreqCurSum = 0;
        long cpufreqMaxSum = 0;
        if (!this.initialized) {
            init();
        }
        for (int i = 0; i < this.cpusPresent; i++) {
            if (this.cpuFreq[i] == 0) {
                long cpufreqMax = readFreqFromFile(this.maxPath[i]);
                if (cpufreqMax > 0) {
                    lastSeenMaxFreq = cpufreqMax;
                    this.cpuFreq[i] = cpufreqMax;
                    this.maxPath[i] = null;
                }
            } else {
                lastSeenMaxFreq = this.cpuFreq[i];
            }
            cpufreqCurSum += readFreqFromFile(this.curPath[i]);
            cpufreqMaxSum += lastSeenMaxFreq;
        }
        if (cpufreqMaxSum == 0) {
            Log.e(TAG, "Could not read max frequency for any CPU");
            return false;
        }
        double newPercentFreq = (100.0d * cpufreqCurSum) / cpufreqMaxSum;
        if (this.lastPercentFreq > 0.0d) {
            percentFreq = (this.lastPercentFreq + newPercentFreq) * 0.5d;
        } else {
            percentFreq = newPercentFreq;
        }
        this.lastPercentFreq = newPercentFreq;
        ProcStat procStat = readIdleAndRunTime();
        if (procStat == null) {
            return false;
        }
        long diffRunTime = procStat.runTime - this.lastProcStat.runTime;
        this.lastProcStat = procStat;
        long allTime = diffRunTime + (procStat.idleTime - this.lastProcStat.idleTime);
        int percent = Math.max(0, Math.min(allTime == 0 ? 0 : (int) Math.round((diffRunTime * percentFreq) / allTime), 100));
        this.sum3 += percent - this.percentVec[2];
        this.sum10 += percent - this.percentVec[9];
        for (int i2 = 9; i2 > 0; i2--) {
            this.percentVec[i2] = this.percentVec[i2 - 1];
        }
        this.percentVec[0] = percent;
        this.cpuCurrent = percent;
        this.cpuAvg3 = this.sum3 / 3;
        this.cpuAvgAll = this.sum10 / 10;
        return true;
    }

    public int getCpuCurrent() {
        return this.cpuCurrent;
    }

    public int getCpuAvg3() {
        return this.cpuAvg3;
    }

    public int getCpuAvgAll() {
        return this.cpuAvgAll;
    }

    /* JADX WARN: Finally extract failed */
    private long readFreqFromFile(String fileName) {
        long number = 0;
        try {
            FileReader fin = new FileReader(fileName);
            try {
                Scanner scannerC = new Scanner(new BufferedReader(fin));
                number = scannerC.nextLong();
                scannerC.close();
                fin.close();
            } catch (Exception e) {
                fin.close();
            } catch (Throwable th) {
                fin.close();
                throw th;
            }
        } catch (FileNotFoundException e2) {
        } catch (IOException e3) {
            Log.e(TAG, "Error closing file");
        }
        return number;
    }

    /* JADX WARN: Finally extract failed */
    private ProcStat readIdleAndRunTime() {
        try {
            FileReader fin = new FileReader("/proc/stat");
            try {
                try {
                    Scanner scanner = new Scanner(new BufferedReader(fin));
                    scanner.next();
                    long runTime = scanner.nextLong() + scanner.nextLong() + scanner.nextLong();
                    long idleTime = scanner.nextLong();
                    scanner.close();
                    fin.close();
                    return new ProcStat(runTime, idleTime);
                } catch (Exception e) {
                    Log.e(TAG, "Problems parsing /proc/stat");
                    fin.close();
                    return null;
                }
            } catch (Throwable th) {
                fin.close();
                throw th;
            }
        } catch (FileNotFoundException e2) {
            Log.e(TAG, "Cannot open /proc/stat for reading");
            return null;
        } catch (IOException e3) {
            Log.e(TAG, "Problems reading /proc/stat");
            return null;
        }
    }
}
