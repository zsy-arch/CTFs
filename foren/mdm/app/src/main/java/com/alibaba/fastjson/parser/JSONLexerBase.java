package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import java.io.Closeable;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class JSONLexerBase implements JSONLexer, Closeable {
    private static final Map<String, Integer> DEFAULT_KEYWORDS;
    protected static final int INT_MULTMIN_RADIX_TEN = -214748364;
    protected static final int INT_N_MULTMAX_RADIX_TEN = -214748364;
    protected static final long MULTMIN_RADIX_TEN = -922337203685477580L;
    protected static final long N_MULTMAX_RADIX_TEN = -922337203685477580L;
    protected int bp;
    protected char ch;
    protected int eofPos;
    protected boolean hasSpecial;
    protected int np;
    protected int pos;
    protected char[] sbuf;
    protected int sp;
    protected int token;
    private static final ThreadLocal<SoftReference<char[]>> SBUF_REF_LOCAL = new ThreadLocal<>();
    protected static final char[] typeFieldName = ("\"" + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
    protected static boolean[] whitespaceFlags = new boolean[256];
    protected static final int[] digits = new int[103];
    protected int features = JSON.DEFAULT_PARSER_FEATURE;
    protected Calendar calendar = null;
    public int matchStat = 0;
    protected Map<String, Integer> keywods = DEFAULT_KEYWORDS;

    public abstract String addSymbol(int i, int i2, int i3, SymbolTable symbolTable);

    protected abstract void arrayCopy(int i, char[] cArr, int i2, int i3);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract byte[] bytesValue();

    public abstract char charAt(int i);

    protected abstract void copyTo(int i, int i2, char[] cArr);

    public abstract int indexOf(char c, int i);

    public abstract boolean isEOF();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract char next();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract String numberString();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract String stringVal();

    public abstract String subString(int i, int i2);

    static {
        Map<String, Integer> map = new HashMap<>();
        map.put(f.b, 8);
        map.put(f.bf, 9);
        map.put("true", 6);
        map.put("false", 7);
        map.put("undefined", 23);
        DEFAULT_KEYWORDS = map;
        whitespaceFlags[32] = true;
        whitespaceFlags[10] = true;
        whitespaceFlags[13] = true;
        whitespaceFlags[9] = true;
        whitespaceFlags[12] = true;
        whitespaceFlags[8] = true;
        for (int i = 48; i <= 57; i++) {
            digits[i] = i - 48;
        }
        for (int i2 = 97; i2 <= 102; i2++) {
            digits[i2] = (i2 - 97) + 10;
        }
        for (int i3 = 65; i3 <= 70; i3++) {
            digits[i3] = (i3 - 65) + 10;
        }
    }

    protected void lexError(String key, Object... args) {
        this.token = 1;
    }

    public JSONLexerBase() {
        SoftReference<char[]> sbufRef = SBUF_REF_LOCAL.get();
        if (sbufRef != null) {
            this.sbuf = sbufRef.get();
            SBUF_REF_LOCAL.set(null);
        }
        if (this.sbuf == null) {
            this.sbuf = new char[64];
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextToken() {
        this.sp = 0;
        while (true) {
            this.pos = this.bp;
            if (this.ch == '\"') {
                scanString();
                return;
            } else if (this.ch == ',') {
                next();
                this.token = 16;
                return;
            } else if (this.ch >= '0' && this.ch <= '9') {
                scanNumber();
                return;
            } else if (this.ch == '-') {
                scanNumber();
                return;
            } else {
                switch (this.ch) {
                    case '\b':
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                        next();
                    case '\'':
                        if (!isEnabled(Feature.AllowSingleQuotes)) {
                            throw new JSONException("Feature.AllowSingleQuotes is false");
                        }
                        scanStringSingleQuote();
                        return;
                    case '(':
                        next();
                        this.token = 10;
                        return;
                    case ')':
                        next();
                        this.token = 11;
                        return;
                    case ':':
                        next();
                        this.token = 17;
                        return;
                    case 'S':
                        scanSet();
                        return;
                    case 'T':
                        scanTreeSet();
                        return;
                    case '[':
                        next();
                        this.token = 14;
                        return;
                    case ']':
                        next();
                        this.token = 15;
                        return;
                    case 'f':
                        scanFalse();
                        return;
                    case 'n':
                        scanNullOrNew();
                        return;
                    case 't':
                        scanTrue();
                        return;
                    case 'u':
                        scanUndefined();
                        return;
                    case '{':
                        next();
                        this.token = 12;
                        return;
                    case '}':
                        next();
                        this.token = 13;
                        return;
                    default:
                        if (!isEOF()) {
                            lexError("illegal.char", String.valueOf((int) this.ch));
                            next();
                            return;
                        } else if (this.token == 20) {
                            throw new JSONException("EOF error");
                        } else {
                            this.token = 20;
                            int i = this.eofPos;
                            this.bp = i;
                            this.pos = i;
                            return;
                        }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0116 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0030 A[ADDED_TO_REGION, SYNTHETIC] */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void nextToken(int r8) {
        /*
            Method dump skipped, instructions count: 336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.nextToken(int):void");
    }

    public final void nextIdent() {
        while (isWhitespace(this.ch)) {
            next();
        }
        if (this.ch == '_' || Character.isLetter(this.ch)) {
            scanIdent();
        } else {
            nextToken();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextTokenWithColon() {
        nextTokenWithChar(':');
    }

    public final void nextTokenWithChar(char expect) {
        this.sp = 0;
        while (this.ch != expect) {
            if (this.ch == ' ' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b') {
                next();
            } else {
                throw new JSONException("not match " + expect + " - " + this.ch);
            }
        }
        next();
        nextToken();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int token() {
        return this.token;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String tokenName() {
        return JSONToken.name(this.token);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int pos() {
        return this.pos;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int getBufferPosition() {
        return this.bp;
    }

    public final String stringDefaultValue() {
        if (isEnabled(Feature.InitStringFieldAsEmpty)) {
            return "";
        }
        return null;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final Number integerValue() throws NumberFormatException {
        long limit;
        int i;
        long result = 0;
        boolean negative = false;
        if (this.np == -1) {
            this.np = 0;
        }
        int i2 = this.np;
        int max = this.np + this.sp;
        char type = ' ';
        switch (charAt(max - 1)) {
            case 'B':
                max--;
                type = 'B';
                break;
            case 'L':
                max--;
                type = 'L';
                break;
            case 'S':
                max--;
                type = 'S';
                break;
        }
        if (charAt(this.np) == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            i = i2 + 1;
        } else {
            limit = -9223372036854775807L;
            i = i2;
        }
        long multmin = negative ? -922337203685477580L : -922337203685477580L;
        if (i < max) {
            result = -digits[charAt(i)];
            i++;
        }
        while (i < max) {
            int i3 = i + 1;
            int digit = digits[charAt(i)];
            if (result < multmin) {
                return new BigInteger(numberString());
            }
            long result2 = result * 10;
            if (result2 < digit + limit) {
                return new BigInteger(numberString());
            }
            result = result2 - digit;
            i = i3;
        }
        if (!negative) {
            long result3 = -result;
            if (result3 > 2147483647L || type == 'L') {
                return Long.valueOf(result3);
            }
            if (type == 'S') {
                return Short.valueOf((short) result3);
            }
            if (type == 'B') {
                return Byte.valueOf((byte) result3);
            }
            return Integer.valueOf((int) result3);
        } else if (i <= this.np + 1) {
            throw new NumberFormatException(numberString());
        } else if (result < -2147483648L || type == 'L') {
            return Long.valueOf(result);
        } else {
            if (type == 'S') {
                return Short.valueOf((short) result);
            }
            if (type == 'B') {
                return Byte.valueOf((byte) result);
            }
            return Integer.valueOf((int) result);
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextTokenWithColon(int expect) {
        nextTokenWithChar(':');
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public float floatValue() {
        return Float.parseFloat(numberString());
    }

    public double doubleValue() {
        return Double.parseDouble(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void config(Feature feature, boolean state) {
        this.features = Feature.config(this.features, feature, state);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isEnabled(Feature feature) {
        return Feature.isEnabled(this.features, feature);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final char getCurrent() {
        return this.ch;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbol(SymbolTable symbolTable) {
        skipWhitespace();
        if (this.ch == '\"') {
            return scanSymbol(symbolTable, '\"');
        }
        if (this.ch == '\'') {
            if (isEnabled(Feature.AllowSingleQuotes)) {
                return scanSymbol(symbolTable, '\'');
            }
            throw new JSONException("syntax error");
        } else if (this.ch == '}') {
            next();
            this.token = 13;
            return null;
        } else if (this.ch == ',') {
            next();
            this.token = 16;
            return null;
        } else if (this.ch == 26) {
            this.token = 20;
            return null;
        } else if (isEnabled(Feature.AllowUnQuotedFieldNames)) {
            return scanSymbolUnQuoted(symbolTable);
        } else {
            throw new JSONException("syntax error");
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbol(SymbolTable symbolTable, char quote) {
        String value;
        int offset;
        int hash = 0;
        this.np = this.bp;
        this.sp = 0;
        boolean hasSpecial = false;
        while (true) {
            char chLocal = next();
            if (chLocal == quote) {
                this.token = 4;
                if (!hasSpecial) {
                    if (this.np == -1) {
                        offset = 0;
                    } else {
                        offset = this.np + 1;
                    }
                    value = addSymbol(offset, this.sp, hash, symbolTable);
                } else {
                    value = symbolTable.addSymbol(this.sbuf, 0, this.sp, hash);
                }
                this.sp = 0;
                next();
                return value;
            } else if (chLocal == 26) {
                throw new JSONException("unclosed.str");
            } else if (chLocal == '\\') {
                if (!hasSpecial) {
                    hasSpecial = true;
                    if (this.sp >= this.sbuf.length) {
                        int newCapcity = this.sbuf.length * 2;
                        if (this.sp > newCapcity) {
                            newCapcity = this.sp;
                        }
                        char[] newsbuf = new char[newCapcity];
                        System.arraycopy(this.sbuf, 0, newsbuf, 0, this.sbuf.length);
                        this.sbuf = newsbuf;
                    }
                    arrayCopy(this.np + 1, this.sbuf, 0, this.sp);
                }
                char chLocal2 = next();
                switch (chLocal2) {
                    case '\"':
                        hash = (hash * 31) + 34;
                        putChar('\"');
                        continue;
                    case '\'':
                        hash = (hash * 31) + 39;
                        putChar('\'');
                        continue;
                    case '/':
                        hash = (hash * 31) + 47;
                        putChar('/');
                        continue;
                    case '0':
                        hash = (hash * 31) + chLocal2;
                        putChar((char) 0);
                        continue;
                    case '1':
                        hash = (hash * 31) + chLocal2;
                        putChar((char) 1);
                        continue;
                    case '2':
                        hash = (hash * 31) + chLocal2;
                        putChar((char) 2);
                        continue;
                    case '3':
                        hash = (hash * 31) + chLocal2;
                        putChar((char) 3);
                        continue;
                    case '4':
                        hash = (hash * 31) + chLocal2;
                        putChar((char) 4);
                        continue;
                    case '5':
                        hash = (hash * 31) + chLocal2;
                        putChar((char) 5);
                        continue;
                    case '6':
                        hash = (hash * 31) + chLocal2;
                        putChar((char) 6);
                        continue;
                    case '7':
                        hash = (hash * 31) + chLocal2;
                        putChar((char) 7);
                        continue;
                    case 'F':
                    case 'f':
                        hash = (hash * 31) + 12;
                        putChar('\f');
                        continue;
                    case '\\':
                        hash = (hash * 31) + 92;
                        putChar('\\');
                        continue;
                    case 'b':
                        hash = (hash * 31) + 8;
                        putChar('\b');
                        continue;
                    case 'n':
                        hash = (hash * 31) + 10;
                        putChar('\n');
                        continue;
                    case 'r':
                        hash = (hash * 31) + 13;
                        putChar('\r');
                        continue;
                    case 't':
                        hash = (hash * 31) + 9;
                        putChar('\t');
                        continue;
                    case 'u':
                        int val = Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16);
                        hash = (hash * 31) + val;
                        putChar((char) val);
                        continue;
                    case 'v':
                        hash = (hash * 31) + 11;
                        putChar((char) 11);
                        continue;
                    case 'x':
                        char x1 = next();
                        this.ch = x1;
                        char x2 = next();
                        this.ch = x2;
                        char x_char = (char) ((digits[x1] * 16) + digits[x2]);
                        hash = (hash * 31) + x_char;
                        putChar(x_char);
                        continue;
                    default:
                        this.ch = chLocal2;
                        throw new JSONException("unclosed.str.lit");
                }
            } else {
                hash = (hash * 31) + chLocal;
                if (!hasSpecial) {
                    this.sp++;
                } else if (this.sp == this.sbuf.length) {
                    putChar(chLocal);
                } else {
                    char[] cArr = this.sbuf;
                    int i = this.sp;
                    this.sp = i + 1;
                    cArr[i] = chLocal;
                }
            }
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void resetStringPosition() {
        this.sp = 0;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        boolean[] firstIdentifierFlags = IOUtils.firstIdentifierFlags;
        char first = this.ch;
        if (!(this.ch >= firstIdentifierFlags.length || firstIdentifierFlags[first])) {
            throw new JSONException("illegal identifier : " + this.ch);
        }
        boolean[] identifierFlags = IOUtils.identifierFlags;
        int hash = first;
        this.np = this.bp;
        this.sp = 1;
        while (true) {
            char chLocal = next();
            if (chLocal < identifierFlags.length && !identifierFlags[chLocal]) {
                break;
            }
            hash = (hash * 31) + chLocal;
            this.sp++;
        }
        this.ch = charAt(this.bp);
        this.token = 18;
        if (this.sp == 4 && hash == 3392903 && charAt(this.np) == 'n' && charAt(this.np + 1) == 'u' && charAt(this.np + 2) == 'l' && charAt(this.np + 3) == 'l') {
            return null;
        }
        return addSymbol(this.np, this.sp, hash, symbolTable);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void scanString() {
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char ch = next();
            if (ch == '\"') {
                this.token = 4;
                this.ch = next();
                return;
            } else if (ch == 26) {
                throw new JSONException("unclosed string : " + ch);
            } else if (ch == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    if (this.sp >= this.sbuf.length) {
                        int newCapcity = this.sbuf.length * 2;
                        if (this.sp > newCapcity) {
                            newCapcity = this.sp;
                        }
                        char[] newsbuf = new char[newCapcity];
                        System.arraycopy(this.sbuf, 0, newsbuf, 0, this.sbuf.length);
                        this.sbuf = newsbuf;
                    }
                    copyTo(this.np + 1, this.sp, this.sbuf);
                }
                char ch2 = next();
                switch (ch2) {
                    case '\"':
                        putChar('\"');
                        continue;
                    case '\'':
                        putChar('\'');
                        continue;
                    case '/':
                        putChar('/');
                        continue;
                    case '0':
                        putChar((char) 0);
                        continue;
                    case '1':
                        putChar((char) 1);
                        continue;
                    case '2':
                        putChar((char) 2);
                        continue;
                    case '3':
                        putChar((char) 3);
                        continue;
                    case '4':
                        putChar((char) 4);
                        continue;
                    case '5':
                        putChar((char) 5);
                        continue;
                    case '6':
                        putChar((char) 6);
                        continue;
                    case '7':
                        putChar((char) 7);
                        continue;
                    case 'F':
                    case 'f':
                        putChar('\f');
                        continue;
                    case '\\':
                        putChar('\\');
                        continue;
                    case 'b':
                        putChar('\b');
                        continue;
                    case 'n':
                        putChar('\n');
                        continue;
                    case 'r':
                        putChar('\r');
                        continue;
                    case 't':
                        putChar('\t');
                        continue;
                    case 'u':
                        putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                        continue;
                    case 'v':
                        putChar((char) 11);
                        continue;
                    case 'x':
                        putChar((char) ((digits[next()] * 16) + digits[next()]));
                        continue;
                    default:
                        this.ch = ch2;
                        throw new JSONException("unclosed string : " + ch2);
                }
            } else if (!this.hasSpecial) {
                this.sp++;
            } else if (this.sp == this.sbuf.length) {
                putChar(ch);
            } else {
                char[] cArr = this.sbuf;
                int i = this.sp;
                this.sp = i + 1;
                cArr[i] = ch;
            }
        }
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x004f, code lost:
        if (r2 <= (r11.np + 1)) goto L_0x007a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0051, code lost:
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
        throw new java.lang.NumberFormatException(numberString());
     */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int intValue() {
        /*
            r11 = this;
            r6 = -214748364(0xfffffffff3333334, float:-1.4197688E31)
            int r9 = r11.np
            r10 = -1
            if (r9 != r10) goto L_0x000b
            r9 = 0
            r11.np = r9
        L_0x000b:
            r8 = 0
            r7 = 0
            int r2 = r11.np
            int r9 = r11.np
            int r10 = r11.sp
            int r5 = r9 + r10
            int r9 = r11.np
            char r9 = r11.charAt(r9)
            r10 = 45
            if (r9 != r10) goto L_0x0052
            r7 = 1
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            int r2 = r2 + 1
            r3 = r2
        L_0x0025:
            if (r7 == 0) goto L_0x0027
        L_0x0027:
            if (r3 >= r5) goto L_0x0035
            int[] r9 = com.alibaba.fastjson.parser.JSONLexerBase.digits
            int r2 = r3 + 1
            char r10 = r11.charAt(r3)
            r1 = r9[r10]
            int r8 = -r1
            r3 = r2
        L_0x0035:
            if (r3 >= r5) goto L_0x0086
            int r2 = r3 + 1
            char r0 = r11.charAt(r3)
            r9 = 76
            if (r0 == r9) goto L_0x0049
            r9 = 83
            if (r0 == r9) goto L_0x0049
            r9 = 66
            if (r0 != r9) goto L_0x0057
        L_0x0049:
            if (r7 == 0) goto L_0x0084
            int r9 = r11.np
            int r9 = r9 + 1
            if (r2 <= r9) goto L_0x007a
        L_0x0051:
            return r8
        L_0x0052:
            r4 = -2147483647(0xffffffff80000001, float:-1.4E-45)
            r3 = r2
            goto L_0x0025
        L_0x0057:
            int[] r9 = com.alibaba.fastjson.parser.JSONLexerBase.digits
            r1 = r9[r0]
            if (r8 >= r6) goto L_0x0067
            java.lang.NumberFormatException r9 = new java.lang.NumberFormatException
            java.lang.String r10 = r11.numberString()
            r9.<init>(r10)
            throw r9
        L_0x0067:
            int r8 = r8 * 10
            int r9 = r4 + r1
            if (r8 >= r9) goto L_0x0077
            java.lang.NumberFormatException r9 = new java.lang.NumberFormatException
            java.lang.String r10 = r11.numberString()
            r9.<init>(r10)
            throw r9
        L_0x0077:
            int r8 = r8 - r1
            r3 = r2
            goto L_0x0035
        L_0x007a:
            java.lang.NumberFormatException r9 = new java.lang.NumberFormatException
            java.lang.String r10 = r11.numberString()
            r9.<init>(r10)
            throw r9
        L_0x0084:
            int r8 = -r8
            goto L_0x0051
        L_0x0086:
            r2 = r3
            goto L_0x0049
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.intValue():int");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.sbuf.length <= 8192) {
            SBUF_REF_LOCAL.set(new SoftReference<>(this.sbuf));
        }
        this.sbuf = null;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isRef() {
        return this.sp == 4 && charAt(this.np + 1) == '$' && charAt(this.np + 2) == 'r' && charAt(this.np + 3) == 'e' && charAt(this.np + 4) == 'f';
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String scanString(char expectNextChar) {
        this.matchStat = 0;
        int offset = 0 + 1;
        char chLocal = charAt(this.bp + 0);
        if (chLocal == 'n') {
            if (charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l') {
                int offset2 = offset + 3 + 1;
                if (charAt(this.bp + 4) == expectNextChar) {
                    this.bp += 4;
                    next();
                    this.matchStat = 3;
                    return null;
                }
                this.matchStat = -1;
                return null;
            }
            this.matchStat = -1;
            return null;
        } else if (chLocal != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        } else {
            boolean hasSpecial = false;
            int startIndex = this.bp + 1;
            int endIndex = indexOf('\"', startIndex);
            if (endIndex == -1) {
                throw new JSONException("unclosed str");
            }
            String stringVal = subString(this.bp + 1, endIndex - startIndex);
            int i = this.bp + 1;
            while (true) {
                if (i >= endIndex) {
                    break;
                } else if (charAt(i) == '\\') {
                    hasSpecial = true;
                    break;
                } else {
                    i++;
                }
            }
            if (hasSpecial) {
                this.matchStat = -1;
                return stringDefaultValue();
            }
            int offset3 = (endIndex - (this.bp + 1)) + 1 + 1;
            int offset4 = offset3 + 1;
            if (charAt(this.bp + offset3) == expectNextChar) {
                this.bp += offset4 - 1;
                next();
                this.matchStat = 3;
                return stringVal;
            }
            this.matchStat = -1;
            return stringVal;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public Enum<?> scanEnum(Class<?> enumClass, SymbolTable symbolTable, char serperator) {
        String name = scanSymbolWithSeperator(symbolTable, serperator);
        if (name == null) {
            return null;
        }
        return Enum.valueOf(enumClass, name);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String scanSymbolWithSeperator(SymbolTable symbolTable, char serperator) {
        String str = null;
        this.matchStat = 0;
        int offset = 0 + 1;
        char chLocal = charAt(this.bp + 0);
        if (chLocal == 'n') {
            if (charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l') {
                int offset2 = offset + 3 + 1;
                if (charAt(this.bp + 4) == serperator) {
                    this.bp += 4;
                    next();
                    this.matchStat = 3;
                } else {
                    this.matchStat = -1;
                }
            } else {
                this.matchStat = -1;
            }
        } else if (chLocal != '\"') {
            this.matchStat = -1;
        } else {
            int hash = 0;
            while (true) {
                offset++;
                char chLocal2 = charAt(this.bp + offset);
                if (chLocal2 == '\"') {
                    int start = this.bp + 0 + 1;
                    str = addSymbol(start, ((this.bp + offset) - start) - 1, hash, symbolTable);
                    int offset3 = offset + 1;
                    if (charAt(this.bp + offset) == serperator) {
                        this.bp += offset3 - 1;
                        next();
                        this.matchStat = 3;
                    } else {
                        this.matchStat = -1;
                    }
                } else {
                    hash = (hash * 31) + chLocal2;
                    if (chLocal2 == '\\') {
                        this.matchStat = -1;
                        break;
                    }
                }
            }
        }
        return str;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public int scanInt(char expectNext) {
        char chLocal;
        this.matchStat = 0;
        int offset = 0 + 1;
        char chLocal2 = charAt(this.bp + 0);
        if (chLocal2 < '0' || chLocal2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int value = digits[chLocal2];
        while (true) {
            offset++;
            chLocal = charAt(this.bp + offset);
            if (chLocal < '0' || chLocal > '9') {
                break;
            }
            value = (value * 10) + digits[chLocal];
        }
        if (chLocal == '.') {
            this.matchStat = -1;
            return 0;
        } else if (value < 0) {
            this.matchStat = -1;
            return 0;
        } else if (chLocal == expectNext) {
            this.bp += offset - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return value;
        } else {
            this.matchStat = -1;
            return value;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public long scanLong(char expectNextChar) {
        char chLocal;
        this.matchStat = 0;
        int offset = 0 + 1;
        char chLocal2 = charAt(this.bp + 0);
        if (chLocal2 < '0' || chLocal2 > '9') {
            this.matchStat = -1;
            return 0L;
        }
        long value = digits[chLocal2];
        while (true) {
            offset++;
            chLocal = charAt(this.bp + offset);
            if (chLocal < '0' || chLocal > '9') {
                break;
            }
            value = (10 * value) + digits[chLocal];
        }
        if (chLocal == '.') {
            this.matchStat = -1;
            return 0L;
        } else if (value < 0) {
            this.matchStat = -1;
            return 0L;
        } else if (chLocal == expectNextChar) {
            this.bp += offset - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return value;
        } else {
            this.matchStat = -1;
            return value;
        }
    }

    public final void scanTrue() {
        if (this.ch != 't') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'r') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'u') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == 26 || this.ch == '\f' || this.ch == '\b' || this.ch == ':') {
            this.token = 6;
            return;
        }
        throw new JSONException("scan true error");
    }

    public final void scanTreeSet() {
        if (this.ch != 'T') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'r') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'S') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 't') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch == ' ' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b' || this.ch == '[' || this.ch == '(') {
            this.token = 22;
            return;
        }
        throw new JSONException("scan set error");
    }

    public final void scanNullOrNew() {
        if (this.ch != 'n') {
            throw new JSONException("error parse null or new");
        }
        next();
        if (this.ch == 'u') {
            next();
            if (this.ch != 'l') {
                throw new JSONException("error parse true");
            }
            next();
            if (this.ch != 'l') {
                throw new JSONException("error parse true");
            }
            next();
            if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == 26 || this.ch == '\f' || this.ch == '\b') {
                this.token = 8;
                return;
            }
            throw new JSONException("scan true error");
        } else if (this.ch != 'e') {
            throw new JSONException("error parse e");
        } else {
            next();
            if (this.ch != 'w') {
                throw new JSONException("error parse w");
            }
            next();
            if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == 26 || this.ch == '\f' || this.ch == '\b') {
                this.token = 9;
                return;
            }
            throw new JSONException("scan true error");
        }
    }

    public final void scanUndefined() {
        if (this.ch != 'u') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'n') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'd') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'f') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'i') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'n') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'd') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == 26 || this.ch == '\f' || this.ch == '\b') {
            this.token = 23;
            return;
        }
        throw new JSONException("scan false error");
    }

    public final void scanFalse() {
        if (this.ch != 'f') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'a') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'l') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 's') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == 26 || this.ch == '\f' || this.ch == '\b' || this.ch == ':') {
            this.token = 7;
            return;
        }
        throw new JSONException("scan false error");
    }

    public final void scanIdent() {
        this.np = this.bp - 1;
        this.hasSpecial = false;
        do {
            this.sp++;
            next();
        } while (Character.isLetterOrDigit(this.ch));
        Integer tok = getKeyword(stringVal());
        if (tok != null) {
            this.token = tok.intValue();
        } else {
            this.token = 18;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isBlankInput() {
        int i = 0;
        while (true) {
            char chLocal = charAt(i);
            if (chLocal == 26) {
                return true;
            }
            if (!isWhitespace(chLocal)) {
                return false;
            }
            i++;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void skipWhitespace() {
        while (this.ch < whitespaceFlags.length && whitespaceFlags[this.ch]) {
            next();
        }
    }

    private final void scanStringSingleQuote() {
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char chLocal = next();
            if (chLocal == '\'') {
                this.token = 4;
                next();
                return;
            } else if (chLocal == 26) {
                throw new JSONException("unclosed single-quote string");
            } else if (chLocal == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    if (this.sp > this.sbuf.length) {
                        char[] newsbuf = new char[this.sp * 2];
                        System.arraycopy(this.sbuf, 0, newsbuf, 0, this.sbuf.length);
                        this.sbuf = newsbuf;
                    }
                    copyTo(this.np + 1, this.sp, this.sbuf);
                }
                char chLocal2 = next();
                switch (chLocal2) {
                    case '\"':
                        putChar('\"');
                        continue;
                    case '\'':
                        putChar('\'');
                        continue;
                    case '/':
                        putChar('/');
                        continue;
                    case '0':
                        putChar((char) 0);
                        continue;
                    case '1':
                        putChar((char) 1);
                        continue;
                    case '2':
                        putChar((char) 2);
                        continue;
                    case '3':
                        putChar((char) 3);
                        continue;
                    case '4':
                        putChar((char) 4);
                        continue;
                    case '5':
                        putChar((char) 5);
                        continue;
                    case '6':
                        putChar((char) 6);
                        continue;
                    case '7':
                        putChar((char) 7);
                        continue;
                    case 'F':
                    case 'f':
                        putChar('\f');
                        continue;
                    case '\\':
                        putChar('\\');
                        continue;
                    case 'b':
                        putChar('\b');
                        continue;
                    case 'n':
                        putChar('\n');
                        continue;
                    case 'r':
                        putChar('\r');
                        continue;
                    case 't':
                        putChar('\t');
                        continue;
                    case 'u':
                        putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                        continue;
                    case 'v':
                        putChar((char) 11);
                        continue;
                    case 'x':
                        putChar((char) ((digits[next()] * 16) + digits[next()]));
                        continue;
                    default:
                        this.ch = chLocal2;
                        throw new JSONException("unclosed single-quote string");
                }
            } else if (!this.hasSpecial) {
                this.sp++;
            } else if (this.sp == this.sbuf.length) {
                putChar(chLocal);
            } else {
                char[] cArr = this.sbuf;
                int i = this.sp;
                this.sp = i + 1;
                cArr[i] = chLocal;
            }
        }
    }

    public final void scanSet() {
        if (this.ch != 'S') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 't') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch == ' ' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b' || this.ch == '[' || this.ch == '(') {
            this.token = 21;
            return;
        }
        throw new JSONException("scan set error");
    }

    protected final void putChar(char ch) {
        if (this.sp == this.sbuf.length) {
            char[] newsbuf = new char[this.sbuf.length * 2];
            System.arraycopy(this.sbuf, 0, newsbuf, 0, this.sbuf.length);
            this.sbuf = newsbuf;
        }
        char[] cArr = this.sbuf;
        int i = this.sp;
        this.sp = i + 1;
        cArr[i] = ch;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void scanNumber() {
        this.np = this.bp;
        if (this.ch == '-') {
            this.sp++;
            next();
        }
        while (this.ch >= '0' && this.ch <= '9') {
            this.sp++;
            next();
        }
        boolean isDouble = false;
        if (this.ch == '.') {
            this.sp++;
            next();
            isDouble = true;
            while (this.ch >= '0' && this.ch <= '9') {
                this.sp++;
                next();
            }
        }
        if (this.ch == 'L') {
            this.sp++;
            next();
        } else if (this.ch == 'S') {
            this.sp++;
            next();
        } else if (this.ch == 'B') {
            this.sp++;
            next();
        } else if (this.ch == 'F') {
            this.sp++;
            next();
            isDouble = true;
        } else if (this.ch == 'D') {
            this.sp++;
            next();
            isDouble = true;
        } else if (this.ch == 'e' || this.ch == 'E') {
            this.sp++;
            next();
            if (this.ch == '+' || this.ch == '-') {
                this.sp++;
                next();
            }
            while (this.ch >= '0' && this.ch <= '9') {
                this.sp++;
                next();
            }
            if (this.ch == 'D' || this.ch == 'F') {
                this.sp++;
                next();
            }
            isDouble = true;
        }
        if (isDouble) {
            this.token = 3;
        } else {
            this.token = 2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x004b, code lost:
        if (r2 <= (r14.np + 1)) goto L_0x007e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0087, code lost:
        throw new java.lang.NumberFormatException(numberString());
     */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long longValue() throws java.lang.NumberFormatException {
        /*
            r14 = this;
            r8 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            r10 = 0
            r7 = 0
            int r2 = r14.np
            int r12 = r14.np
            int r13 = r14.sp
            int r6 = r12 + r13
            int r12 = r14.np
            char r12 = r14.charAt(r12)
            r13 = 45
            if (r12 != r13) goto L_0x004e
            r7 = 1
            r4 = -9223372036854775808
            int r2 = r2 + 1
            r3 = r2
        L_0x0020:
            if (r7 == 0) goto L_0x0022
        L_0x0022:
            if (r3 >= r6) goto L_0x0031
            int[] r12 = com.alibaba.fastjson.parser.JSONLexerBase.digits
            int r2 = r3 + 1
            char r13 = r14.charAt(r3)
            r1 = r12[r13]
            int r12 = -r1
            long r10 = (long) r12
            r3 = r2
        L_0x0031:
            if (r3 >= r6) goto L_0x008a
            int r2 = r3 + 1
            char r0 = r14.charAt(r3)
            r12 = 76
            if (r0 == r12) goto L_0x0045
            r12 = 83
            if (r0 == r12) goto L_0x0045
            r12 = 66
            if (r0 != r12) goto L_0x0055
        L_0x0045:
            if (r7 == 0) goto L_0x0088
            int r12 = r14.np
            int r12 = r12 + 1
            if (r2 <= r12) goto L_0x007e
        L_0x004d:
            return r10
        L_0x004e:
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r3 = r2
            goto L_0x0020
        L_0x0055:
            int[] r12 = com.alibaba.fastjson.parser.JSONLexerBase.digits
            r1 = r12[r0]
            int r12 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r12 >= 0) goto L_0x0067
            java.lang.NumberFormatException r12 = new java.lang.NumberFormatException
            java.lang.String r13 = r14.numberString()
            r12.<init>(r13)
            throw r12
        L_0x0067:
            r12 = 10
            long r10 = r10 * r12
            long r12 = (long) r1
            long r12 = r12 + r4
            int r12 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r12 >= 0) goto L_0x007a
            java.lang.NumberFormatException r12 = new java.lang.NumberFormatException
            java.lang.String r13 = r14.numberString()
            r12.<init>(r13)
            throw r12
        L_0x007a:
            long r12 = (long) r1
            long r10 = r10 - r12
            r3 = r2
            goto L_0x0031
        L_0x007e:
            java.lang.NumberFormatException r12 = new java.lang.NumberFormatException
            java.lang.String r13 = r14.numberString()
            r12.<init>(r13)
            throw r12
        L_0x0088:
            long r10 = -r10
            goto L_0x004d
        L_0x008a:
            r2 = r3
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.longValue():long");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final Number decimalValue(boolean decimal) {
        char chLocal = charAt((this.np + this.sp) - 1);
        if (chLocal == 'F') {
            return Float.valueOf(Float.parseFloat(numberString()));
        }
        if (chLocal == 'D') {
            return Double.valueOf(Double.parseDouble(numberString()));
        }
        if (decimal) {
            return decimalValue();
        }
        return Double.valueOf(doubleValue());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final BigDecimal decimalValue() {
        return new BigDecimal(numberString());
    }

    public static final boolean isWhitespace(char ch) {
        return ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t' || ch == '\f' || ch == '\b';
    }

    public Integer getKeyword(String key) {
        return this.keywods.get(key);
    }
}
