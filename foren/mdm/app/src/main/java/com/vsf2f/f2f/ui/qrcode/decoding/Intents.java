package com.vsf2f.f2f.ui.qrcode.decoding;

/* loaded from: classes2.dex */
public final class Intents {
    private Intents() {
    }

    /* loaded from: classes2.dex */
    public static final class Scan {
        public static final String ACTION = "com.google.zxing.client.android.SCAN";
        public static final String CHARACTER_SET = "CHARACTER_SET";
        public static final String DATA_MATRIX_MODE = "DATA_MATRIX_MODE";
        public static final String MODE = "SCAN_MODE";
        public static final String ONE_D_MODE = "ONE_D_MODE";
        public static final String PRODUCT_MODE = "PRODUCT_MODE";
        public static final String QR_CODE_MODE = "QR_CODE_MODE";
        public static final String RESULT = "SCAN_RESULT";
        public static final String RESULT_FORMAT = "SCAN_RESULT_FORMAT";
        public static final String SAVE_HISTORY = "SAVE_HISTORY";
        public static final String SCAN_FORMATS = "SCAN_FORMATS";

        private Scan() {
        }
    }

    /* loaded from: classes2.dex */
    public static final class Encode {
        public static final String ACTION = "com.google.zxing.client.android.ENCODE";
        public static final String DATA = "ENCODE_DATA";
        public static final String FORMAT = "ENCODE_FORMAT";
        public static final String TYPE = "ENCODE_TYPE";

        private Encode() {
        }
    }

    /* loaded from: classes2.dex */
    public static final class SearchBookContents {
        public static final String ACTION = "com.google.zxing.client.android.SEARCH_BOOK_CONTENTS";
        public static final String ISBN = "ISBN";
        public static final String QUERY = "QUERY";

        private SearchBookContents() {
        }
    }

    /* loaded from: classes2.dex */
    public static final class WifiConnect {
        public static final String ACTION = "com.google.zxing.client.android.WIFI_CONNECT";
        public static final String PASSWORD = "PASSWORD";
        public static final String SSID = "SSID";
        public static final String TYPE = "TYPE";

        private WifiConnect() {
        }
    }

    /* loaded from: classes2.dex */
    public static final class Share {
        public static final String ACTION = "com.google.zxing.client.android.SHARE";

        private Share() {
        }
    }
}
