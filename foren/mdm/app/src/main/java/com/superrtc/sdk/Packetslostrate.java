package com.superrtc.sdk;

/* loaded from: classes2.dex */
public class Packetslostrate {
    private static final int MAX_NUMBER = 3;
    private int[] Packetslostvideo = new int[5];
    private int[] Packetssendvideo = new int[5];
    private int[] Packetslostaudio = new int[5];
    private int[] Packetssendaudio = new int[5];

    public void addvideopackslost(int packetssend, int packetlost) {
        for (int i = 2; i != 0; i--) {
            this.Packetslostvideo[i] = this.Packetslostvideo[i - 1];
            this.Packetssendvideo[i] = this.Packetssendvideo[i - 1];
        }
        this.Packetssendvideo[0] = packetssend;
        this.Packetslostvideo[0] = packetlost;
    }

    public void addaudiopackslost(int packetssend, int packetlost) {
        for (int i = 2; i != 0; i--) {
            this.Packetslostaudio[i] = this.Packetslostaudio[i - 1];
            this.Packetssendaudio[i] = this.Packetssendaudio[i - 1];
        }
        this.Packetssendaudio[0] = packetssend;
        this.Packetslostaudio[0] = packetlost;
    }

    public int getvideopackslostrate() {
        float packslostrate = 0.0f;
        for (int i = 0; i < 3; i++) {
            if (this.Packetssendvideo[i] != 0) {
                packslostrate += (this.Packetslostvideo[i] * 100) / this.Packetssendvideo[i];
            }
        }
        return ((int) packslostrate) / 3;
    }

    public int getaudiopackslostrate() {
        float packslostrate = 0.0f;
        for (int i = 0; i < 3; i++) {
            if (this.Packetssendaudio[i] != 0) {
                packslostrate += (this.Packetslostaudio[i] * 100) / this.Packetssendaudio[i];
            }
        }
        return ((int) packslostrate) / 3;
    }
}
