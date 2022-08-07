package com.hy.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/* loaded from: classes2.dex */
public class MultipartHttpEntity extends HttpEntity {
    private static final char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private String boundary;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    boolean isSetLast = false;
    boolean isSetFirst = false;

    public MultipartHttpEntity() {
        this.boundary = null;
        StringBuffer buf = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            buf.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
        }
        this.boundary = buf.toString();
    }

    @Override // com.hy.http.HttpEntity
    public String getContentType() {
        return createContentType(HttpEntity.MULTIPART_FORM_DATA, DEFAULT_CHARSET) + "; boundary=" + this.boundary;
    }

    public void writeFirstBoundaryIfNeeds() {
        if (!this.isSetFirst) {
            try {
                this.out.write(("--" + this.boundary + "\r\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.isSetFirst = true;
    }

    public void writeLastBoundaryIfNeeds() {
        if (!this.isSetLast) {
            try {
                this.out.write(("\r\n--" + this.boundary + "--\r\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.isSetLast = true;
        }
    }

    public void addPart(String key, String value) {
        writeFirstBoundaryIfNeeds();
        try {
            this.out.write(("Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n").getBytes());
            this.out.write(value.getBytes());
            this.out.write(("\r\n--" + this.boundary + "\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPart(String key, String fileName, InputStream fin, boolean isLast) {
        addPart(key, fileName, fin, "application/octet-stream", isLast);
    }

    public void addPart(String key, String fileName, InputStream fin, String type, boolean isLast) {
        writeFirstBoundaryIfNeeds();
        try {
            try {
                this.out.write(("Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + fileName + "\"\r\n").getBytes());
                this.out.write(("Content-Type: " + type + "\r\n").getBytes());
                this.out.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
                byte[] tmp = new byte[4096];
                while (true) {
                    int l = fin.read(tmp);
                    if (l == -1) {
                        break;
                    }
                    this.out.write(tmp, 0, l);
                }
                if (!isLast) {
                    this.out.write(("\r\n--" + this.boundary + "\r\n").getBytes());
                } else {
                    writeLastBoundaryIfNeeds();
                }
                this.out.flush();
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                fin.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public void addPart(String key, File value, boolean isLast) {
        try {
            addPart(key, value.getName(), new FileInputStream(value), isLast);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hy.http.HttpEntity
    public long getContentLength() {
        writeLastBoundaryIfNeeds();
        return this.out.toByteArray().length;
    }

    public void writeTo(OutputStream outstream) throws IOException {
        outstream.write(this.out.toByteArray());
    }

    @Override // com.hy.http.HttpEntity
    public String getContentEncoding() {
        return DEFAULT_CHARSET;
    }

    @Override // com.hy.http.HttpEntity
    public InputStream getContent() throws IOException {
        return new ByteArrayInputStream(this.out.toByteArray());
    }
}
