package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.Base64;
import com.alibaba.fastjson.util.IOUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public final class SerializeWriter extends Writer {
    private static final ThreadLocal<SoftReference<char[]>> bufLocal = new ThreadLocal<>();
    protected char[] buf;
    protected int count;
    private int features;
    private final Writer writer;

    public SerializeWriter() {
        this((Writer) null);
    }

    public SerializeWriter(Writer writer) {
        this.writer = writer;
        this.features = JSON.DEFAULT_GENERATE_FEATURE;
        SoftReference<char[]> ref = bufLocal.get();
        if (ref != null) {
            this.buf = ref.get();
            bufLocal.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
    }

    public SerializeWriter(SerializerFeature... features) {
        this((Writer) null, features);
    }

    public SerializeWriter(Writer writer, SerializerFeature... features) {
        this.writer = writer;
        SoftReference<char[]> ref = bufLocal.get();
        if (ref != null) {
            this.buf = ref.get();
            bufLocal.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
        int featuresValue = 0;
        for (SerializerFeature feature : features) {
            featuresValue |= feature.getMask();
        }
        this.features = featuresValue;
    }

    public int getBufferLength() {
        return this.buf.length;
    }

    public SerializeWriter(int initialSize) {
        this((Writer) null, initialSize);
    }

    public SerializeWriter(Writer writer, int initialSize) {
        this.writer = writer;
        if (initialSize <= 0) {
            throw new IllegalArgumentException("Negative initial size: " + initialSize);
        }
        this.buf = new char[initialSize];
    }

    public void config(SerializerFeature feature, boolean state) {
        if (state) {
            this.features |= feature.getMask();
        } else {
            this.features &= feature.getMask() ^ (-1);
        }
    }

    public boolean isEnabled(SerializerFeature feature) {
        return SerializerFeature.isEnabled(this.features, feature);
    }

    @Override // java.io.Writer
    public void write(int c) {
        int newcount = this.count + 1;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else {
                flush();
                newcount = 1;
            }
        }
        this.buf[this.count] = (char) c;
        this.count = newcount;
    }

    public void write(char c) {
        int newcount = this.count + 1;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else {
                flush();
                newcount = 1;
            }
        }
        this.buf[this.count] = c;
        this.count = newcount;
    }

    @Override // java.io.Writer
    public void write(char[] c, int off, int len) {
        if (off < 0 || off > c.length || len < 0 || off + len > c.length || off + len < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len != 0) {
            int newcount = this.count + len;
            if (newcount > this.buf.length) {
                if (this.writer == null) {
                    expandCapacity(newcount);
                } else {
                    do {
                        int rest = this.buf.length - this.count;
                        System.arraycopy(c, off, this.buf, this.count, rest);
                        this.count = this.buf.length;
                        flush();
                        len -= rest;
                        off += rest;
                    } while (len > this.buf.length);
                    newcount = len;
                }
            }
            System.arraycopy(c, off, this.buf, this.count, len);
            this.count = newcount;
        }
    }

    public void expandCapacity(int minimumCapacity) {
        int newCapacity = ((this.buf.length * 3) / 2) + 1;
        if (newCapacity < minimumCapacity) {
            newCapacity = minimumCapacity;
        }
        char[] newValue = new char[newCapacity];
        System.arraycopy(this.buf, 0, newValue, 0, this.count);
        this.buf = newValue;
    }

    @Override // java.io.Writer
    public void write(String str, int off, int len) {
        int newcount = this.count + len;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else {
                do {
                    int rest = this.buf.length - this.count;
                    str.getChars(off, off + rest, this.buf, this.count);
                    this.count = this.buf.length;
                    flush();
                    len -= rest;
                    off += rest;
                } while (len > this.buf.length);
                newcount = len;
            }
        }
        str.getChars(off, off + len, this.buf, this.count);
        this.count = newcount;
    }

    public void writeTo(Writer out) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        out.write(this.buf, 0, this.count);
    }

    public void writeTo(OutputStream out, String charsetName) throws IOException {
        writeTo(out, Charset.forName(charsetName));
    }

    public void writeTo(OutputStream out, Charset charset) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        out.write(new String(this.buf, 0, this.count).getBytes(charset.name()));
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(CharSequence csq) {
        String s = csq == null ? f.b : csq.toString();
        write(s, 0, s.length());
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(CharSequence csq, int start, int end) {
        if (csq == null) {
            csq = f.b;
        }
        String s = csq.subSequence(start, end).toString();
        write(s, 0, s.length());
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(char c) {
        write(c);
        return this;
    }

    public void reset() {
        this.count = 0;
    }

    public char[] toCharArray() {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        char[] newValue = new char[this.count];
        System.arraycopy(this.buf, 0, newValue, 0, this.count);
        return newValue;
    }

    public byte[] toBytes(String charsetName) {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (charsetName == null) {
            charsetName = "UTF-8";
        }
        try {
            return new String(this.buf, 0, this.count).getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new JSONException("toBytes error", e);
        }
    }

    public int size() {
        return this.count;
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.writer != null && this.count > 0) {
            flush();
        }
        if (this.buf.length <= 8192) {
            bufLocal.set(new SoftReference<>(this.buf));
        }
        this.buf = null;
    }

    @Override // java.io.Writer
    public void write(String text) {
        if (text == null) {
            writeNull();
        } else {
            write(text, 0, text.length());
        }
    }

    public void writeInt(int i) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int size = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int newcount = this.count + size;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else {
                char[] chars = new char[size];
                IOUtils.getChars(i, size, chars);
                write(chars, 0, chars.length);
                return;
            }
        }
        IOUtils.getChars(i, newcount, this.buf);
        this.count = newcount;
    }

    public void writeByteArray(byte[] bytes) {
        int bytesLen = bytes.length;
        boolean singleQuote = isEnabled(SerializerFeature.UseSingleQuotes);
        char quote = singleQuote ? '\'' : '\"';
        if (bytesLen == 0) {
            write(singleQuote ? "''" : "\"\"");
            return;
        }
        char[] CA = Base64.CA;
        int eLen = (bytesLen / 3) * 3;
        int offset = this.count;
        int newcount = this.count + ((((bytesLen - 1) / 3) + 1) << 2) + 2;
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                write(quote);
                int s = 0;
                while (s < eLen) {
                    int s2 = s + 1;
                    int s3 = s2 + 1;
                    s = s3 + 1;
                    int i = ((bytes[s] & 255) << 16) | ((bytes[s2] & 255) << 8) | (bytes[s3] & 255);
                    write(CA[(i >>> 18) & 63]);
                    write(CA[(i >>> 12) & 63]);
                    write(CA[(i >>> 6) & 63]);
                    write(CA[i & 63]);
                }
                int left = bytesLen - eLen;
                if (left > 0) {
                    int i2 = ((bytes[eLen] & 255) << 10) | (left == 2 ? (bytes[bytesLen - 1] & 255) << 2 : 0);
                    write(CA[i2 >> 12]);
                    write(CA[(i2 >>> 6) & 63]);
                    write(left == 2 ? CA[i2 & 63] : '=');
                    write('=');
                }
                write(quote);
                return;
            }
            expandCapacity(newcount);
        }
        this.count = newcount;
        this.buf[offset] = quote;
        int s4 = 0;
        int d = offset + 1;
        while (s4 < eLen) {
            int s5 = s4 + 1;
            int s6 = s5 + 1;
            s4 = s6 + 1;
            int i3 = ((bytes[s4] & 255) << 16) | ((bytes[s5] & 255) << 8) | (bytes[s6] & 255);
            int d2 = d + 1;
            this.buf[d] = CA[(i3 >>> 18) & 63];
            int d3 = d2 + 1;
            this.buf[d2] = CA[(i3 >>> 12) & 63];
            int d4 = d3 + 1;
            this.buf[d3] = CA[(i3 >>> 6) & 63];
            d = d4 + 1;
            this.buf[d4] = CA[i3 & 63];
        }
        int left2 = bytesLen - eLen;
        if (left2 > 0) {
            int i4 = ((bytes[eLen] & 255) << 10) | (left2 == 2 ? (bytes[bytesLen - 1] & 255) << 2 : 0);
            this.buf[newcount - 5] = CA[i4 >> 12];
            this.buf[newcount - 4] = CA[(i4 >>> 6) & 63];
            this.buf[newcount - 3] = left2 == 2 ? CA[i4 & 63] : '=';
            this.buf[newcount - 2] = '=';
        }
        this.buf[newcount - 1] = quote;
    }

    public void writeLongAndChar(long i, char c) throws IOException {
        if (i == Long.MIN_VALUE) {
            write("-9223372036854775808");
            write(c);
            return;
        }
        int newcount0 = this.count + (i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i));
        int newcount1 = newcount0 + 1;
        if (newcount1 > this.buf.length) {
            if (this.writer != null) {
                writeLong(i);
                write(c);
                return;
            }
            expandCapacity(newcount1);
        }
        IOUtils.getChars(i, newcount0, this.buf);
        this.buf[newcount0] = c;
        this.count = newcount1;
    }

    public void writeLong(long i) {
        if (i == Long.MIN_VALUE) {
            write("-9223372036854775808");
            return;
        }
        int size = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int newcount = this.count + size;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else {
                char[] chars = new char[size];
                IOUtils.getChars(i, size, chars);
                write(chars, 0, chars.length);
                return;
            }
        }
        IOUtils.getChars(i, newcount, this.buf);
        this.count = newcount;
    }

    public void writeNull() {
        write(f.b);
    }

    private void writeStringWithDoubleQuote(String text, char seperator) {
        writeStringWithDoubleQuote(text, seperator, true);
    }

    private void writeStringWithDoubleQuote(String text, char seperator, boolean checkSpecial) {
        if (text == null) {
            writeNull();
            if (seperator != 0) {
                write(seperator);
                return;
            }
            return;
        }
        int len = text.length();
        int newcount = this.count + len + 2;
        if (seperator != 0) {
            newcount++;
        }
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                write('\"');
                for (int i = 0; i < text.length(); i++) {
                    char ch = text.charAt(i);
                    if (!isEnabled(SerializerFeature.BrowserCompatible)) {
                        if ((ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] != 0) || (ch == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            write('\\');
                            write(IOUtils.replaceChars[ch]);
                        }
                        write(ch);
                    } else if (ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r' || ch == '\t' || ch == '\"' || ch == '/' || ch == '\\') {
                        write('\\');
                        write(IOUtils.replaceChars[ch]);
                    } else if (ch < ' ') {
                        write('\\');
                        write('u');
                        write('0');
                        write('0');
                        write(IOUtils.ASCII_CHARS[ch * 2]);
                        write(IOUtils.ASCII_CHARS[(ch * 2) + 1]);
                    } else {
                        if (ch >= 127) {
                            write('\\');
                            write('u');
                            write(IOUtils.DIGITS[(ch >>> '\f') & 15]);
                            write(IOUtils.DIGITS[(ch >>> '\b') & 15]);
                            write(IOUtils.DIGITS[(ch >>> 4) & 15]);
                            write(IOUtils.DIGITS[ch & 15]);
                        }
                        write(ch);
                    }
                }
                write('\"');
                if (seperator != 0) {
                    write(seperator);
                    return;
                }
                return;
            }
            expandCapacity(newcount);
        }
        int start = this.count + 1;
        int end = start + len;
        this.buf[this.count] = '\"';
        text.getChars(0, len, this.buf, start);
        this.count = newcount;
        if (isEnabled(SerializerFeature.BrowserCompatible)) {
            int lastSpecialIndex = -1;
            for (int i2 = start; i2 < end; i2++) {
                char ch2 = this.buf[i2];
                if (ch2 == '\"' || ch2 == '/' || ch2 == '\\') {
                    lastSpecialIndex = i2;
                    newcount++;
                } else if (ch2 == '\b' || ch2 == '\f' || ch2 == '\n' || ch2 == '\r' || ch2 == '\t') {
                    lastSpecialIndex = i2;
                    newcount++;
                } else if (ch2 < ' ') {
                    lastSpecialIndex = i2;
                    newcount += 5;
                } else if (ch2 >= 127) {
                    lastSpecialIndex = i2;
                    newcount += 5;
                }
            }
            if (newcount > this.buf.length) {
                expandCapacity(newcount);
            }
            this.count = newcount;
            for (int i3 = lastSpecialIndex; i3 >= start; i3--) {
                char ch3 = this.buf[i3];
                if (ch3 == '\b' || ch3 == '\f' || ch3 == '\n' || ch3 == '\r' || ch3 == '\t') {
                    System.arraycopy(this.buf, i3 + 1, this.buf, i3 + 2, (end - i3) - 1);
                    this.buf[i3] = '\\';
                    this.buf[i3 + 1] = IOUtils.replaceChars[ch3];
                    end++;
                } else if (ch3 == '\"' || ch3 == '/' || ch3 == '\\') {
                    System.arraycopy(this.buf, i3 + 1, this.buf, i3 + 2, (end - i3) - 1);
                    this.buf[i3] = '\\';
                    this.buf[i3 + 1] = ch3;
                    end++;
                } else if (ch3 < ' ') {
                    System.arraycopy(this.buf, i3 + 1, this.buf, i3 + 6, (end - i3) - 1);
                    this.buf[i3] = '\\';
                    this.buf[i3 + 1] = 'u';
                    this.buf[i3 + 2] = '0';
                    this.buf[i3 + 3] = '0';
                    this.buf[i3 + 4] = IOUtils.ASCII_CHARS[ch3 * 2];
                    this.buf[i3 + 5] = IOUtils.ASCII_CHARS[(ch3 * 2) + 1];
                    end += 5;
                } else if (ch3 >= 127) {
                    System.arraycopy(this.buf, i3 + 1, this.buf, i3 + 6, (end - i3) - 1);
                    this.buf[i3] = '\\';
                    this.buf[i3 + 1] = 'u';
                    this.buf[i3 + 2] = IOUtils.DIGITS[(ch3 >>> '\f') & 15];
                    this.buf[i3 + 3] = IOUtils.DIGITS[(ch3 >>> '\b') & 15];
                    this.buf[i3 + 4] = IOUtils.DIGITS[(ch3 >>> 4) & 15];
                    this.buf[i3 + 5] = IOUtils.DIGITS[ch3 & 15];
                    end += 5;
                }
            }
            if (seperator != 0) {
                this.buf[this.count - 2] = '\"';
                this.buf[this.count - 1] = seperator;
                return;
            }
            this.buf[this.count - 1] = '\"';
            return;
        }
        int specialCount = 0;
        int lastSpecialIndex2 = -1;
        int firstSpecialIndex = -1;
        char lastSpecial = 0;
        if (checkSpecial) {
            for (int i4 = start; i4 < end; i4++) {
                char ch4 = this.buf[i4];
                if (ch4 == 8232) {
                    specialCount++;
                    lastSpecialIndex2 = i4;
                    lastSpecial = ch4;
                    newcount += 4;
                    if (firstSpecialIndex == -1) {
                        firstSpecialIndex = i4;
                    }
                } else if (ch4 >= ']') {
                    if (ch4 >= 127 && ch4 <= 160) {
                        if (firstSpecialIndex == -1) {
                            firstSpecialIndex = i4;
                        }
                        specialCount++;
                        lastSpecialIndex2 = i4;
                        lastSpecial = ch4;
                        newcount += 4;
                    }
                } else if (isSpecial(ch4, this.features)) {
                    specialCount++;
                    lastSpecialIndex2 = i4;
                    lastSpecial = ch4;
                    if (ch4 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch4] == 4) {
                        newcount += 4;
                    }
                    if (firstSpecialIndex == -1) {
                        firstSpecialIndex = i4;
                    }
                }
            }
            if (specialCount > 0) {
                int newcount2 = newcount + specialCount;
                if (newcount2 > this.buf.length) {
                    expandCapacity(newcount2);
                }
                this.count = newcount2;
                if (specialCount == 1) {
                    if (lastSpecial == 8232) {
                        System.arraycopy(this.buf, lastSpecialIndex2 + 1, this.buf, lastSpecialIndex2 + 6, (end - lastSpecialIndex2) - 1);
                        this.buf[lastSpecialIndex2] = '\\';
                        int lastSpecialIndex3 = lastSpecialIndex2 + 1;
                        this.buf[lastSpecialIndex3] = 'u';
                        int lastSpecialIndex4 = lastSpecialIndex3 + 1;
                        this.buf[lastSpecialIndex4] = '2';
                        int lastSpecialIndex5 = lastSpecialIndex4 + 1;
                        this.buf[lastSpecialIndex5] = '0';
                        int lastSpecialIndex6 = lastSpecialIndex5 + 1;
                        this.buf[lastSpecialIndex6] = '2';
                        this.buf[lastSpecialIndex6 + 1] = '8';
                    } else if (lastSpecial >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[lastSpecial] != 4) {
                        System.arraycopy(this.buf, lastSpecialIndex2 + 1, this.buf, lastSpecialIndex2 + 2, (end - lastSpecialIndex2) - 1);
                        this.buf[lastSpecialIndex2] = '\\';
                        this.buf[lastSpecialIndex2 + 1] = IOUtils.replaceChars[lastSpecial];
                    } else {
                        System.arraycopy(this.buf, lastSpecialIndex2 + 1, this.buf, lastSpecialIndex2 + 6, (end - lastSpecialIndex2) - 1);
                        int bufIndex = lastSpecialIndex2 + 1;
                        this.buf[lastSpecialIndex2] = '\\';
                        int bufIndex2 = bufIndex + 1;
                        this.buf[bufIndex] = 'u';
                        int bufIndex3 = bufIndex2 + 1;
                        this.buf[bufIndex2] = IOUtils.DIGITS[(lastSpecial >>> '\f') & 15];
                        int bufIndex4 = bufIndex3 + 1;
                        this.buf[bufIndex3] = IOUtils.DIGITS[(lastSpecial >>> '\b') & 15];
                        int bufIndex5 = bufIndex4 + 1;
                        this.buf[bufIndex4] = IOUtils.DIGITS[(lastSpecial >>> 4) & 15];
                        int i5 = bufIndex5 + 1;
                        this.buf[bufIndex5] = IOUtils.DIGITS[lastSpecial & 15];
                    }
                } else if (specialCount > 1) {
                    int bufIndex6 = firstSpecialIndex;
                    for (int i6 = firstSpecialIndex - start; i6 < text.length(); i6++) {
                        char ch5 = text.charAt(i6);
                        if ((ch5 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch5] != 0) || (ch5 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            int bufIndex7 = bufIndex6 + 1;
                            this.buf[bufIndex6] = '\\';
                            if (IOUtils.specicalFlags_doubleQuotes[ch5] == 4) {
                                int bufIndex8 = bufIndex7 + 1;
                                this.buf[bufIndex7] = 'u';
                                int bufIndex9 = bufIndex8 + 1;
                                this.buf[bufIndex8] = IOUtils.DIGITS[(ch5 >>> '\f') & 15];
                                int bufIndex10 = bufIndex9 + 1;
                                this.buf[bufIndex9] = IOUtils.DIGITS[(ch5 >>> '\b') & 15];
                                int bufIndex11 = bufIndex10 + 1;
                                this.buf[bufIndex10] = IOUtils.DIGITS[(ch5 >>> 4) & 15];
                                bufIndex6 = bufIndex11 + 1;
                                this.buf[bufIndex11] = IOUtils.DIGITS[ch5 & 15];
                                end += 5;
                            } else {
                                bufIndex6 = bufIndex7 + 1;
                                this.buf[bufIndex7] = IOUtils.replaceChars[ch5];
                                end++;
                            }
                        } else if (ch5 == 8232) {
                            int bufIndex12 = bufIndex6 + 1;
                            this.buf[bufIndex6] = '\\';
                            int bufIndex13 = bufIndex12 + 1;
                            this.buf[bufIndex12] = 'u';
                            int bufIndex14 = bufIndex13 + 1;
                            this.buf[bufIndex13] = IOUtils.DIGITS[(ch5 >>> '\f') & 15];
                            int bufIndex15 = bufIndex14 + 1;
                            this.buf[bufIndex14] = IOUtils.DIGITS[(ch5 >>> '\b') & 15];
                            int bufIndex16 = bufIndex15 + 1;
                            this.buf[bufIndex15] = IOUtils.DIGITS[(ch5 >>> 4) & 15];
                            bufIndex6 = bufIndex16 + 1;
                            this.buf[bufIndex16] = IOUtils.DIGITS[ch5 & 15];
                            end += 5;
                        } else {
                            this.buf[bufIndex6] = ch5;
                            bufIndex6++;
                        }
                    }
                }
            }
        }
        if (seperator != 0) {
            this.buf[this.count - 2] = '\"';
            this.buf[this.count - 1] = seperator;
            return;
        }
        this.buf[this.count - 1] = '\"';
    }

    public void write(boolean value) {
        if (value) {
            write("true");
        } else {
            write("false");
        }
    }

    public void writeFieldValue(char seperator, String name, long value) {
        if (value == Long.MIN_VALUE || !isEnabled(SerializerFeature.QuoteFieldNames)) {
            writeFieldValue1(seperator, name, value);
            return;
        }
        char keySeperator = isEnabled(SerializerFeature.UseSingleQuotes) ? '\'' : '\"';
        int intSize = value < 0 ? IOUtils.stringSize(-value) + 1 : IOUtils.stringSize(value);
        int nameLen = name.length();
        int newcount = this.count + nameLen + 4 + intSize;
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                write(seperator);
                writeFieldName(name);
                writeLong(value);
                return;
            }
            expandCapacity(newcount);
        }
        int start = this.count;
        this.count = newcount;
        this.buf[start] = seperator;
        int nameEnd = start + nameLen + 1;
        this.buf[start + 1] = keySeperator;
        name.getChars(0, nameLen, this.buf, start + 2);
        this.buf[nameEnd + 1] = keySeperator;
        this.buf[nameEnd + 2] = ':';
        IOUtils.getChars(value, this.count, this.buf);
    }

    public void writeFieldValue1(char seperator, String name, long value) {
        write(seperator);
        writeFieldName(name);
        writeLong(value);
    }

    public void writeFieldValue(char seperator, String name, String value) {
        if (!isEnabled(SerializerFeature.QuoteFieldNames)) {
            write(seperator);
            writeFieldName(name);
            if (value == null) {
                writeNull();
            } else {
                writeString(value);
            }
        } else if (isEnabled(SerializerFeature.UseSingleQuotes)) {
            write(seperator);
            writeFieldName(name);
            if (value == null) {
                writeNull();
            } else {
                writeString(value);
            }
        } else if (isEnabled(SerializerFeature.BrowserCompatible)) {
            write(seperator);
            writeStringWithDoubleQuote(name, ':');
            writeStringWithDoubleQuote(value, (char) 0);
        } else {
            writeFieldValueStringWithDoubleQuote(seperator, name, value, true);
        }
    }

    private void writeFieldValueStringWithDoubleQuote(char seperator, String name, String value, boolean checkSpecial) {
        int valueLen;
        int newcount;
        int nameLen = name.length();
        int newcount2 = this.count;
        if (value == null) {
            valueLen = 4;
            newcount = newcount2 + nameLen + 8;
        } else {
            valueLen = value.length();
            newcount = newcount2 + nameLen + valueLen + 6;
        }
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                write(seperator);
                writeStringWithDoubleQuote(name, ':', checkSpecial);
                writeStringWithDoubleQuote(value, (char) 0, checkSpecial);
                return;
            }
            expandCapacity(newcount);
        }
        this.buf[this.count] = seperator;
        int nameStart = this.count + 2;
        int nameEnd = nameStart + nameLen;
        this.buf[this.count + 1] = '\"';
        name.getChars(0, nameLen, this.buf, nameStart);
        this.count = newcount;
        this.buf[nameEnd] = '\"';
        int index = nameEnd + 1;
        int index2 = index + 1;
        this.buf[index] = ':';
        if (value == null) {
            int index3 = index2 + 1;
            this.buf[index2] = 'n';
            int index4 = index3 + 1;
            this.buf[index3] = 'u';
            int index5 = index4 + 1;
            this.buf[index4] = 'l';
            int i = index5 + 1;
            this.buf[index5] = 'l';
            return;
        }
        int index6 = index2 + 1;
        this.buf[index2] = '\"';
        int valueEnd = index6 + valueLen;
        value.getChars(0, valueLen, this.buf, index6);
        if (checkSpecial && !isEnabled(SerializerFeature.DisableCheckSpecialChar)) {
            int specialCount = 0;
            int lastSpecialIndex = -1;
            int firstSpecialIndex = -1;
            char lastSpecial = 0;
            for (int i2 = index6; i2 < valueEnd; i2++) {
                char ch = this.buf[i2];
                if (ch == 8232) {
                    specialCount++;
                    lastSpecialIndex = i2;
                    lastSpecial = ch;
                    newcount += 4;
                    if (firstSpecialIndex == -1) {
                        firstSpecialIndex = i2;
                    }
                } else if (ch >= ']') {
                    if (ch >= 127 && ch <= 160) {
                        if (firstSpecialIndex == -1) {
                            firstSpecialIndex = i2;
                        }
                        specialCount++;
                        lastSpecialIndex = i2;
                        lastSpecial = ch;
                        newcount += 4;
                    }
                } else if (isSpecial(ch, this.features)) {
                    specialCount++;
                    lastSpecialIndex = i2;
                    lastSpecial = ch;
                    if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
                        newcount += 4;
                    }
                    if (firstSpecialIndex == -1) {
                        firstSpecialIndex = i2;
                    }
                }
            }
            if (specialCount > 0) {
                int newcount3 = newcount + specialCount;
                if (newcount3 > this.buf.length) {
                    expandCapacity(newcount3);
                }
                this.count = newcount3;
                if (specialCount == 1) {
                    if (lastSpecial == 8232) {
                        System.arraycopy(this.buf, lastSpecialIndex + 1, this.buf, lastSpecialIndex + 6, (valueEnd - lastSpecialIndex) - 1);
                        this.buf[lastSpecialIndex] = '\\';
                        int lastSpecialIndex2 = lastSpecialIndex + 1;
                        this.buf[lastSpecialIndex2] = 'u';
                        int lastSpecialIndex3 = lastSpecialIndex2 + 1;
                        this.buf[lastSpecialIndex3] = '2';
                        int lastSpecialIndex4 = lastSpecialIndex3 + 1;
                        this.buf[lastSpecialIndex4] = '0';
                        int lastSpecialIndex5 = lastSpecialIndex4 + 1;
                        this.buf[lastSpecialIndex5] = '2';
                        this.buf[lastSpecialIndex5 + 1] = '8';
                    } else if (lastSpecial >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[lastSpecial] != 4) {
                        System.arraycopy(this.buf, lastSpecialIndex + 1, this.buf, lastSpecialIndex + 2, (valueEnd - lastSpecialIndex) - 1);
                        this.buf[lastSpecialIndex] = '\\';
                        this.buf[lastSpecialIndex + 1] = IOUtils.replaceChars[lastSpecial];
                    } else {
                        System.arraycopy(this.buf, lastSpecialIndex + 1, this.buf, lastSpecialIndex + 6, (valueEnd - lastSpecialIndex) - 1);
                        int bufIndex = lastSpecialIndex + 1;
                        this.buf[lastSpecialIndex] = '\\';
                        int bufIndex2 = bufIndex + 1;
                        this.buf[bufIndex] = 'u';
                        int bufIndex3 = bufIndex2 + 1;
                        this.buf[bufIndex2] = IOUtils.DIGITS[(lastSpecial >>> '\f') & 15];
                        int bufIndex4 = bufIndex3 + 1;
                        this.buf[bufIndex3] = IOUtils.DIGITS[(lastSpecial >>> '\b') & 15];
                        int bufIndex5 = bufIndex4 + 1;
                        this.buf[bufIndex4] = IOUtils.DIGITS[(lastSpecial >>> 4) & 15];
                        int i3 = bufIndex5 + 1;
                        this.buf[bufIndex5] = IOUtils.DIGITS[lastSpecial & 15];
                    }
                } else if (specialCount > 1) {
                    int bufIndex6 = firstSpecialIndex;
                    for (int i4 = firstSpecialIndex - index6; i4 < value.length(); i4++) {
                        char ch2 = value.charAt(i4);
                        if ((ch2 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch2] != 0) || (ch2 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            int bufIndex7 = bufIndex6 + 1;
                            this.buf[bufIndex6] = '\\';
                            if (IOUtils.specicalFlags_doubleQuotes[ch2] == 4) {
                                int bufIndex8 = bufIndex7 + 1;
                                this.buf[bufIndex7] = 'u';
                                int bufIndex9 = bufIndex8 + 1;
                                this.buf[bufIndex8] = IOUtils.DIGITS[(ch2 >>> '\f') & 15];
                                int bufIndex10 = bufIndex9 + 1;
                                this.buf[bufIndex9] = IOUtils.DIGITS[(ch2 >>> '\b') & 15];
                                int bufIndex11 = bufIndex10 + 1;
                                this.buf[bufIndex10] = IOUtils.DIGITS[(ch2 >>> 4) & 15];
                                bufIndex6 = bufIndex11 + 1;
                                this.buf[bufIndex11] = IOUtils.DIGITS[ch2 & 15];
                                valueEnd += 5;
                            } else {
                                bufIndex6 = bufIndex7 + 1;
                                this.buf[bufIndex7] = IOUtils.replaceChars[ch2];
                                valueEnd++;
                            }
                        } else if (ch2 == 8232) {
                            int bufIndex12 = bufIndex6 + 1;
                            this.buf[bufIndex6] = '\\';
                            int bufIndex13 = bufIndex12 + 1;
                            this.buf[bufIndex12] = 'u';
                            int bufIndex14 = bufIndex13 + 1;
                            this.buf[bufIndex13] = IOUtils.DIGITS[(ch2 >>> '\f') & 15];
                            int bufIndex15 = bufIndex14 + 1;
                            this.buf[bufIndex14] = IOUtils.DIGITS[(ch2 >>> '\b') & 15];
                            int bufIndex16 = bufIndex15 + 1;
                            this.buf[bufIndex15] = IOUtils.DIGITS[(ch2 >>> 4) & 15];
                            bufIndex6 = bufIndex16 + 1;
                            this.buf[bufIndex16] = IOUtils.DIGITS[ch2 & 15];
                            valueEnd += 5;
                        } else {
                            this.buf[bufIndex6] = ch2;
                            bufIndex6++;
                        }
                    }
                }
            }
        }
        this.buf[this.count - 1] = '\"';
    }

    static final boolean isSpecial(char ch, int features) {
        if (ch == ' ') {
            return false;
        }
        if (ch == '/' && SerializerFeature.isEnabled(features, SerializerFeature.WriteSlashAsSpecial)) {
            return true;
        }
        if (ch <= '#' || ch == '\\') {
            return ch <= 31 || ch == '\\' || ch == '\"';
        }
        return false;
    }

    public void writeString(String text) {
        if (isEnabled(SerializerFeature.UseSingleQuotes)) {
            writeStringWithSingleQuote(text);
        } else {
            writeStringWithDoubleQuote(text, (char) 0);
        }
    }

    private void writeStringWithSingleQuote(String text) {
        if (text == null) {
            int newcount = this.count + 4;
            if (newcount > this.buf.length) {
                expandCapacity(newcount);
            }
            f.b.getChars(0, 4, this.buf, this.count);
            this.count = newcount;
            return;
        }
        int len = text.length();
        int newcount2 = this.count + len + 2;
        if (newcount2 > this.buf.length) {
            if (this.writer != null) {
                write('\'');
                for (int i = 0; i < text.length(); i++) {
                    char ch = text.charAt(i);
                    if (ch <= '\r' || ch == '\\' || ch == '\'' || (ch == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write('\\');
                        write(IOUtils.replaceChars[ch]);
                    } else {
                        write(ch);
                    }
                }
                write('\'');
                return;
            }
            expandCapacity(newcount2);
        }
        int start = this.count + 1;
        int end = start + len;
        this.buf[this.count] = '\'';
        text.getChars(0, len, this.buf, start);
        this.count = newcount2;
        int specialCount = 0;
        int lastSpecialIndex = -1;
        char lastSpecial = 0;
        for (int i2 = start; i2 < end; i2++) {
            char ch2 = this.buf[i2];
            if (ch2 <= '\r' || ch2 == '\\' || ch2 == '\'' || (ch2 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                specialCount++;
                lastSpecialIndex = i2;
                lastSpecial = ch2;
            }
        }
        int newcount3 = newcount2 + specialCount;
        if (newcount3 > this.buf.length) {
            expandCapacity(newcount3);
        }
        this.count = newcount3;
        if (specialCount == 1) {
            System.arraycopy(this.buf, lastSpecialIndex + 1, this.buf, lastSpecialIndex + 2, (end - lastSpecialIndex) - 1);
            this.buf[lastSpecialIndex] = '\\';
            this.buf[lastSpecialIndex + 1] = IOUtils.replaceChars[lastSpecial];
        } else if (specialCount > 1) {
            System.arraycopy(this.buf, lastSpecialIndex + 1, this.buf, lastSpecialIndex + 2, (end - lastSpecialIndex) - 1);
            this.buf[lastSpecialIndex] = '\\';
            int lastSpecialIndex2 = lastSpecialIndex + 1;
            this.buf[lastSpecialIndex2] = IOUtils.replaceChars[lastSpecial];
            int end2 = end + 1;
            for (int i3 = lastSpecialIndex2 - 2; i3 >= start; i3--) {
                char ch3 = this.buf[i3];
                if (ch3 <= '\r' || ch3 == '\\' || ch3 == '\'' || (ch3 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    System.arraycopy(this.buf, i3 + 1, this.buf, i3 + 2, (end2 - i3) - 1);
                    this.buf[i3] = '\\';
                    this.buf[i3 + 1] = IOUtils.replaceChars[ch3];
                    end2++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    public void writeFieldName(String key) {
        writeFieldName(key, false);
    }

    public void writeFieldName(String key, boolean checkSpecial) {
        if (key == null) {
            write("null:");
        } else if (isEnabled(SerializerFeature.UseSingleQuotes)) {
            if (isEnabled(SerializerFeature.QuoteFieldNames)) {
                writeStringWithSingleQuote(key);
                write(':');
                return;
            }
            writeKeyWithSingleQuoteIfHasSpecial(key);
        } else if (isEnabled(SerializerFeature.QuoteFieldNames)) {
            writeStringWithDoubleQuote(key, ':', checkSpecial);
        } else {
            writeKeyWithDoubleQuoteIfHasSpecial(key);
        }
    }

    private void writeKeyWithDoubleQuoteIfHasSpecial(String text) {
        byte[] specicalFlags_doubleQuotes = IOUtils.specicalFlags_doubleQuotes;
        int len = text.length();
        int newcount = this.count + len + 1;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else if (len == 0) {
                write('\"');
                write('\"');
                write(':');
                return;
            } else {
                boolean hasSpecial = false;
                int i = 0;
                while (true) {
                    if (i >= len) {
                        break;
                    }
                    char ch = text.charAt(i);
                    if (ch < specicalFlags_doubleQuotes.length && specicalFlags_doubleQuotes[ch] != 0) {
                        hasSpecial = true;
                        break;
                    }
                    i++;
                }
                if (hasSpecial) {
                    write('\"');
                }
                for (int i2 = 0; i2 < len; i2++) {
                    char ch2 = text.charAt(i2);
                    if (ch2 >= specicalFlags_doubleQuotes.length || specicalFlags_doubleQuotes[ch2] == 0) {
                        write(ch2);
                    } else {
                        write('\\');
                        write(IOUtils.replaceChars[ch2]);
                    }
                }
                if (hasSpecial) {
                    write('\"');
                }
                write(':');
                return;
            }
        }
        if (len == 0) {
            if (this.count + 3 > this.buf.length) {
                expandCapacity(this.count + 3);
            }
            char[] cArr = this.buf;
            int i3 = this.count;
            this.count = i3 + 1;
            cArr[i3] = '\"';
            char[] cArr2 = this.buf;
            int i4 = this.count;
            this.count = i4 + 1;
            cArr2[i4] = '\"';
            char[] cArr3 = this.buf;
            int i5 = this.count;
            this.count = i5 + 1;
            cArr3[i5] = ':';
            return;
        }
        int start = this.count;
        int end = start + len;
        text.getChars(0, len, this.buf, start);
        this.count = newcount;
        boolean hasSpecial2 = false;
        int i6 = start;
        while (i6 < end) {
            char ch3 = this.buf[i6];
            if (ch3 < specicalFlags_doubleQuotes.length && specicalFlags_doubleQuotes[ch3] != 0) {
                if (!hasSpecial2) {
                    newcount += 3;
                    if (newcount > this.buf.length) {
                        expandCapacity(newcount);
                    }
                    this.count = newcount;
                    System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 3, (end - i6) - 1);
                    System.arraycopy(this.buf, 0, this.buf, 1, i6);
                    this.buf[start] = '\"';
                    int i7 = i6 + 1;
                    this.buf[i7] = '\\';
                    i6 = i7 + 1;
                    this.buf[i6] = IOUtils.replaceChars[ch3];
                    end += 2;
                    this.buf[this.count - 2] = '\"';
                    hasSpecial2 = true;
                } else {
                    newcount++;
                    if (newcount > this.buf.length) {
                        expandCapacity(newcount);
                    }
                    this.count = newcount;
                    System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 2, end - i6);
                    this.buf[i6] = '\\';
                    i6++;
                    this.buf[i6] = IOUtils.replaceChars[ch3];
                    end++;
                }
            }
            i6++;
        }
        this.buf[this.count - 1] = ':';
    }

    private void writeKeyWithSingleQuoteIfHasSpecial(String text) {
        byte[] specicalFlags_singleQuotes = IOUtils.specicalFlags_singleQuotes;
        int len = text.length();
        int newcount = this.count + len + 1;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else if (len == 0) {
                write('\'');
                write('\'');
                write(':');
                return;
            } else {
                boolean hasSpecial = false;
                int i = 0;
                while (true) {
                    if (i >= len) {
                        break;
                    }
                    char ch = text.charAt(i);
                    if (ch < specicalFlags_singleQuotes.length && specicalFlags_singleQuotes[ch] != 0) {
                        hasSpecial = true;
                        break;
                    }
                    i++;
                }
                if (hasSpecial) {
                    write('\'');
                }
                for (int i2 = 0; i2 < len; i2++) {
                    char ch2 = text.charAt(i2);
                    if (ch2 >= specicalFlags_singleQuotes.length || specicalFlags_singleQuotes[ch2] == 0) {
                        write(ch2);
                    } else {
                        write('\\');
                        write(IOUtils.replaceChars[ch2]);
                    }
                }
                if (hasSpecial) {
                    write('\'');
                }
                write(':');
                return;
            }
        }
        if (len == 0) {
            if (this.count + 3 > this.buf.length) {
                expandCapacity(this.count + 3);
            }
            char[] cArr = this.buf;
            int i3 = this.count;
            this.count = i3 + 1;
            cArr[i3] = '\'';
            char[] cArr2 = this.buf;
            int i4 = this.count;
            this.count = i4 + 1;
            cArr2[i4] = '\'';
            char[] cArr3 = this.buf;
            int i5 = this.count;
            this.count = i5 + 1;
            cArr3[i5] = ':';
            return;
        }
        int start = this.count;
        int end = start + len;
        text.getChars(0, len, this.buf, start);
        this.count = newcount;
        boolean hasSpecial2 = false;
        int i6 = start;
        while (i6 < end) {
            char ch3 = this.buf[i6];
            if (ch3 < specicalFlags_singleQuotes.length && specicalFlags_singleQuotes[ch3] != 0) {
                if (!hasSpecial2) {
                    newcount += 3;
                    if (newcount > this.buf.length) {
                        expandCapacity(newcount);
                    }
                    this.count = newcount;
                    System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 3, (end - i6) - 1);
                    System.arraycopy(this.buf, 0, this.buf, 1, i6);
                    this.buf[start] = '\'';
                    int i7 = i6 + 1;
                    this.buf[i7] = '\\';
                    i6 = i7 + 1;
                    this.buf[i6] = IOUtils.replaceChars[ch3];
                    end += 2;
                    this.buf[this.count - 2] = '\'';
                    hasSpecial2 = true;
                } else {
                    newcount++;
                    if (newcount > this.buf.length) {
                        expandCapacity(newcount);
                    }
                    this.count = newcount;
                    System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 2, end - i6);
                    this.buf[i6] = '\\';
                    i6++;
                    this.buf[i6] = IOUtils.replaceChars[ch3];
                    end++;
                }
            }
            i6++;
        }
        this.buf[newcount - 1] = ':';
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        if (this.writer != null) {
            try {
                this.writer.write(this.buf, 0, this.count);
                this.writer.flush();
                this.count = 0;
            } catch (IOException e) {
                throw new JSONException(e.getMessage(), e);
            }
        }
    }
}
