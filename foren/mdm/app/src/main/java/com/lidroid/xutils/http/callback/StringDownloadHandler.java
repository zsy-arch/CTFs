package com.lidroid.xutils.http.callback;

import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.OtherUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;

/* loaded from: classes2.dex */
public class StringDownloadHandler {
    /* JADX WARN: Finally extract failed */
    public String handleEntity(HttpEntity entity, RequestCallBackHandler callBackHandler, String charset) throws IOException {
        if (entity == null) {
            return null;
        }
        long current = 0;
        long total = entity.getContentLength();
        if (callBackHandler != null && !callBackHandler.updateProgress(total, 0L, true)) {
            return null;
        }
        InputStream inputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            inputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line).append('\n');
                current += OtherUtils.sizeOfString(line, charset);
                if (callBackHandler != null && !callBackHandler.updateProgress(total, current, false)) {
                    break;
                }
            }
            if (callBackHandler != null) {
                callBackHandler.updateProgress(total, current, true);
            }
            IOUtils.closeQuietly(inputStream);
            return sb.toString().trim();
        } catch (Throwable th) {
            IOUtils.closeQuietly(inputStream);
            throw th;
        }
    }
}
