package com.superrtc.sdk;

import com.hyphenate.util.HanziToPinyin;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class SDPSsrcChanger {
    static long ssrcInterval = 20;
    String msidAudio;
    String msidVideo;
    String streamLabel;
    String lastCNAME = null;
    long lastAudioSSRC = 0;
    long lastVideoSSRC = 0;

    public SDPSsrcChanger(String streamLabel) {
        this.streamLabel = streamLabel;
        this.msidAudio = String.valueOf(streamLabel) + "a0";
        this.msidVideo = String.valueOf(streamLabel) + "v0";
        reset();
    }

    public void reset() {
        this.lastCNAME = null;
        this.lastAudioSSRC = 0L;
        this.lastVideoSSRC = 0L;
    }

    public String processPranswer(String rawSDP) {
        String[] medias = splitSDP2Medias(rawSDP);
        StringBuilder sb = new StringBuilder();
        long ssrcBase = getNextStartSSRC();
        for (String m : medias) {
            this.lastCNAME = "cname" + ssrcBase;
            if (m.startsWith("m=audio")) {
                sb.append(m);
                if (m.indexOf("a=ssrc:") < 0) {
                    long ssrc = ssrcBase + 0;
                    sb.append("a=ssrc:" + ssrc + " cname:" + this.lastCNAME + "\r\n");
                    sb.append("a=ssrc:" + ssrc + " msid:" + this.streamLabel + HanziToPinyin.Token.SEPARATOR + this.msidAudio + "\r\n");
                    sb.append("a=ssrc:" + ssrc + " mslabel:" + this.streamLabel + "\r\n");
                    sb.append("a=ssrc:" + ssrc + " label:" + this.msidAudio + "\r\n");
                    this.lastAudioSSRC = ssrc;
                }
            } else if (m.startsWith("m=video")) {
                sb.append(m);
                if (m.indexOf("a=ssrc:") < 0) {
                    long ssrc2 = ssrcBase + 10;
                    sb.append("a=ssrc-group:FID " + (ssrc2 + 0) + HanziToPinyin.Token.SEPARATOR + (1 + ssrc2) + "\r\n");
                    for (int k = 0; k < 2; k++) {
                        sb.append("a=ssrc:" + (k + ssrc2) + " cname:" + this.lastCNAME + "\r\n");
                        sb.append("a=ssrc:" + (k + ssrc2) + " msid:" + this.streamLabel + HanziToPinyin.Token.SEPARATOR + this.msidVideo + "\r\n");
                        sb.append("a=ssrc:" + (k + ssrc2) + " mslabel:" + this.streamLabel + "\r\n");
                        sb.append("a=ssrc:" + (k + ssrc2) + " label:" + this.msidVideo + "\r\n");
                    }
                    this.lastVideoSSRC = ssrc2;
                }
            } else {
                sb.append(m);
            }
        }
        return sb.toString();
    }

    private String changeMediaSSRC(String m, long ssrc) {
        Matcher matcher = Pattern.compile("a=ssrc-group:FID(( \\d+)+)").matcher(m);
        int replaceCount = 0;
        while (matcher.find()) {
            for (String field : matcher.group(1).trim().split(HanziToPinyin.Token.SEPARATOR)) {
                m = m.replaceAll(field, new StringBuilder().append(replaceCount + ssrc).toString());
                replaceCount++;
            }
        }
        if (replaceCount == 0) {
            return m.replaceAll("a=ssrc:(\\d+)", "a=ssrc:" + ssrc);
        }
        return m;
    }

    public String changeSSRC(String rawSDP) {
        String[] medias = splitSDP2Medias(rawSDP);
        StringBuilder sb = new StringBuilder();
        for (String m : medias) {
            if (m.startsWith("m=audio") && this.lastAudioSSRC > 0) {
                sb.append(changeMediaSSRC(m, this.lastAudioSSRC));
            } else if (!m.startsWith("m=video") || this.lastVideoSSRC <= 0) {
                sb.append(m);
            } else {
                sb.append(changeMediaSSRC(m, this.lastVideoSSRC));
            }
        }
        return sb.toString();
    }

    static String[] splitSDP2Medias(String rawSDP) {
        String[] tmpParts = rawSDP.split("\r\nm=");
        if (tmpParts.length < 2) {
            return new String[]{rawSDP};
        }
        String[] medias = new String[tmpParts.length];
        medias[0] = String.valueOf(tmpParts[0]) + "\r\n";
        for (int i = 1; i < tmpParts.length; i++) {
            medias[i] = "m=" + tmpParts[i];
            if (i != tmpParts.length - 1) {
                medias[i] = String.valueOf(medias[i]) + "\r\n";
            }
        }
        return medias;
    }

    static long getNextStartSSRC() {
        long n = Math.abs(new Random().nextLong() % 100000000);
        return n - (n % ssrcInterval);
    }
}
