package org.json.alipay;

import com.alimama.mobile.csdk.umupdate.a.f;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* loaded from: classes2.dex */
public final class c {
    private int a;
    private Reader b;
    private char c;
    private boolean d;

    private c(Reader reader) {
        this.b = !reader.markSupported() ? new BufferedReader(reader) : reader;
        this.d = false;
        this.a = 0;
    }

    public c(String str) {
        this(new StringReader(str));
    }

    private String a(int i) {
        int i2 = 0;
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        if (this.d) {
            this.d = false;
            cArr[0] = this.c;
            i2 = 1;
        }
        while (i2 < i) {
            try {
                int read = this.b.read(cArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        this.a += i2;
        if (i2 < i) {
            throw a("Substring bounds error");
        }
        this.c = cArr[i - 1];
        return new String(cArr);
    }

    public final JSONException a(String str) {
        return new JSONException(str + toString());
    }

    public final void a() {
        if (this.d || this.a <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.a--;
        this.d = true;
    }

    public final char b() {
        if (this.d) {
            this.d = false;
            if (this.c != 0) {
                this.a++;
            }
            return this.c;
        }
        try {
            int read = this.b.read();
            if (read <= 0) {
                this.c = (char) 0;
                return (char) 0;
            }
            this.a++;
            this.c = (char) read;
            return this.c;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:?, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final char c() {
        /*
            r5 = this;
            r4 = 13
            r3 = 10
            r0 = 47
        L_0x0006:
            char r1 = r5.b()
            if (r1 != r0) goto L_0x003c
            char r1 = r5.b()
            switch(r1) {
                case 42: goto L_0x002f;
                case 47: goto L_0x0017;
                default: goto L_0x0013;
            }
        L_0x0013:
            r5.a()
        L_0x0016:
            return r0
        L_0x0017:
            char r1 = r5.b()
            if (r1 == r3) goto L_0x0006
            if (r1 == r4) goto L_0x0006
            if (r1 != 0) goto L_0x0017
            goto L_0x0006
        L_0x0022:
            r2 = 42
            if (r1 != r2) goto L_0x002f
            char r1 = r5.b()
            if (r1 == r0) goto L_0x0006
            r5.a()
        L_0x002f:
            char r1 = r5.b()
            if (r1 != 0) goto L_0x0022
            java.lang.String r0 = "Unclosed comment"
            org.json.alipay.JSONException r0 = r5.a(r0)
            throw r0
        L_0x003c:
            r2 = 35
            if (r1 != r2) goto L_0x004b
        L_0x0040:
            char r1 = r5.b()
            if (r1 == r3) goto L_0x0006
            if (r1 == r4) goto L_0x0006
            if (r1 != 0) goto L_0x0040
            goto L_0x0006
        L_0x004b:
            if (r1 == 0) goto L_0x0051
            r2 = 32
            if (r1 <= r2) goto L_0x0006
        L_0x0051:
            r0 = r1
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.c.c():char");
    }

    public final Object d() {
        char c = c();
        switch (c) {
            case '\"':
            case '\'':
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    char b = b();
                    switch (b) {
                        case 0:
                        case '\n':
                        case '\r':
                            throw a("Unterminated string");
                        case '\\':
                            char b2 = b();
                            switch (b2) {
                                case 'b':
                                    stringBuffer.append('\b');
                                    continue;
                                case 'f':
                                    stringBuffer.append('\f');
                                    continue;
                                case 'n':
                                    stringBuffer.append('\n');
                                    continue;
                                case 'r':
                                    stringBuffer.append('\r');
                                    continue;
                                case 't':
                                    stringBuffer.append('\t');
                                    continue;
                                case 'u':
                                    stringBuffer.append((char) Integer.parseInt(a(4), 16));
                                    continue;
                                case 'x':
                                    stringBuffer.append((char) Integer.parseInt(a(2), 16));
                                    continue;
                                default:
                                    stringBuffer.append(b2);
                                    continue;
                            }
                        default:
                            if (b != c) {
                                stringBuffer.append(b);
                                break;
                            } else {
                                return stringBuffer.toString();
                            }
                    }
                }
            case '(':
            case '[':
                a();
                return new a(this);
            case '{':
                a();
                return new b(this);
            default:
                StringBuffer stringBuffer2 = new StringBuffer();
                char c2 = c;
                while (c2 >= ' ' && ",:]}/\\\"[{;=#".indexOf(c2) < 0) {
                    stringBuffer2.append(c2);
                    c2 = b();
                }
                a();
                String trim = stringBuffer2.toString().trim();
                if (trim.equals("")) {
                    throw a("Missing value");
                } else if (trim.equalsIgnoreCase("true")) {
                    return Boolean.TRUE;
                } else {
                    if (trim.equalsIgnoreCase("false")) {
                        return Boolean.FALSE;
                    }
                    if (trim.equalsIgnoreCase(f.b)) {
                        return b.a;
                    }
                    if ((c < '0' || c > '9') && c != '.' && c != '-' && c != '+') {
                        return trim;
                    }
                    if (c == '0') {
                        if (trim.length() <= 2 || !(trim.charAt(1) == 'x' || trim.charAt(1) == 'X')) {
                            try {
                                return new Integer(Integer.parseInt(trim, 8));
                            } catch (Exception e) {
                            }
                        } else {
                            try {
                                return new Integer(Integer.parseInt(trim.substring(2), 16));
                            } catch (Exception e2) {
                            }
                        }
                    }
                    try {
                        return new Integer(trim);
                    } catch (Exception e3) {
                        try {
                            return new Long(trim);
                        } catch (Exception e4) {
                            try {
                                return new Double(trim);
                            } catch (Exception e5) {
                                return trim;
                            }
                        }
                    }
                }
        }
    }

    public final String toString() {
        return " at character " + this.a;
    }
}
