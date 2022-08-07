package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.utils.Apn;

/* loaded from: classes.dex */
public class TbsReaderView extends FrameLayout {
    public static final String IS_BAR_ANIMATING = "is_bar_animating";
    public static final String IS_BAR_SHOWING = "is_bar_show";
    public static final String IS_INTO_DOWNLOADING = "into_downloading";
    public static final String KEY_FILE_PATH = "filePath";
    public static final String KEY_TEMP_PATH = "tempPath";
    public static final int READER_CHANNEL_DOC_ID = 10965;
    public static final int READER_CHANNEL_PDF_ID = 10834;
    public static final int READER_CHANNEL_PPT_ID = 10833;
    public static final int READER_CHANNEL_TXT_ID = 10835;
    public static final String READER_STATISTICS_COUNT_CANCEL_LOADED_BTN = "AHNG802";
    public static final String READER_STATISTICS_COUNT_CLICK_LOADED_BTN = "AHNG801";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_BROWSER = "AHNG829";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_DIALOG = "AHNG827";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_DOWNLOAD = "AHNG828";
    public static final String READER_STATISTICS_COUNT_DOC_SEARCH_BTN = "AHNG826";
    public static final String READER_STATISTICS_COUNT_PDF_FOLDER_BTN = "AHNG810";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_BROWSER = "AHNG813";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_DIALOG = "AHNG811";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_DOWNLOAD = "AHNG812";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_BROWSER = "AHNG809";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_DIALOG = "AHNG807";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_DOWNLOAD = "AHNG808";
    public static final String READER_STATISTICS_COUNT_PPT_PLAY_BTN = "AHNG806";
    public static final String READER_STATISTICS_COUNT_RETRY_BTN = "AHNG803";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_BROWSER = "AHNG817";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_DIALOG = "AHNG815";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_DOWNLOAD = "AHNG816";
    public static final String READER_STATISTICS_COUNT_TXT_NOVEL_BTN = "AHNG814";
    public static final String TAG = "TbsReaderView";
    public static boolean f = false;
    public static String gReaderPackName = "";
    public static String gReaderPackVersion = "";

    /* renamed from: a */
    public Context f1257a;

    /* renamed from: b */
    public ReaderWizard f1258b = null;

    /* renamed from: c */
    public Object f1259c = null;

    /* renamed from: d */
    public ReaderCallback f1260d;

    /* renamed from: e */
    public ReaderCallback f1261e;

    /* loaded from: classes.dex */
    public interface ReaderCallback {
        public static final int COPY_SELECT_TEXT = 5003;
        public static final int GET_BAR_ANIMATING = 5000;
        public static final int GET_BAR_ISSHOWING = 5024;
        public static final int HIDDEN_BAR = 5001;
        public static final int INSTALL_QB = 5011;
        public static final int NOTIFY_CANDISPLAY = 12;
        public static final int NOTIFY_ERRORCODE = 19;
        public static final int READER_OPEN_QQ_FILE_LIST = 5031;
        public static final int READER_PDF_LIST = 5008;
        public static final int READER_PLUGIN_ACTIVITY_PAUSE = 5032;
        public static final int READER_PLUGIN_CANLOAD = 5013;
        public static final int READER_PLUGIN_COMMAND_FIXSCREEN = 5015;
        public static final int READER_PLUGIN_COMMAND_PDF_LIST = 5036;
        public static final int READER_PLUGIN_COMMAND_PPT_PLAYER = 5035;
        public static final int READER_PLUGIN_COMMAND_ROTATESCREEN = 5018;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND = 5038;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_CLEAR = 5041;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_NEXT = 5039;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_PREV = 5040;
        public static final int READER_PLUGIN_DOWNLOADING = 5014;
        public static final int READER_PLUGIN_RES_DOC_GUIDE = 5029;
        public static final int READER_PLUGIN_RES_FIXSCREEN_NORMAL = 5016;
        public static final int READER_PLUGIN_RES_FIXSCREEN_PRESS = 5017;
        public static final int READER_PLUGIN_RES_PDF_GUIDE = 5023;
        public static final int READER_PLUGIN_RES_PPT_GUIDE = 5021;
        public static final int READER_PLUGIN_RES_ROTATESCREEN_NORMAL = 5019;
        public static final int READER_PLUGIN_RES_ROTATESCREEN_PRESS = 5020;
        public static final int READER_PLUGIN_RES_TXT_GUIDE = 5022;
        public static final int READER_PLUGIN_SO_ERR = 5025;
        public static final int READER_PLUGIN_SO_INTO_START = 5027;
        public static final int READER_PLUGIN_SO_PROGRESS = 5028;
        public static final int READER_PLUGIN_SO_VERSION = 5030;
        public static final int READER_PLUGIN_STATUS = 5012;
        public static final int READER_PLUGIN_TEXT_FIND_RESULT = 5042;
        public static final int READER_PPT_PLAY_MODEL = 5009;
        public static final int READER_SEARCH_IN_DOCUMENT = 5026;
        public static final int READER_TOAST = 5005;
        public static final int READER_TXT_READING_MODEL = 5010;
        public static final int SEARCH_SELECT_TEXT = 5004;
        public static final int SHOW_BAR = 5002;
        public static final int SHOW_DIALOG = 5006;

        void onCallBackAction(Integer num, Object obj, Object obj2);
    }

    public TbsReaderView(Context context, ReaderCallback readerCallback) {
        super(context.getApplicationContext());
        this.f1257a = null;
        this.f1260d = null;
        this.f1261e = null;
        if (context instanceof Activity) {
            this.f1260d = readerCallback;
            this.f1257a = context;
            this.f1261e = new ReaderCallback() { // from class: com.tencent.smtt.sdk.TbsReaderView.1
                @Override // com.tencent.smtt.sdk.TbsReaderView.ReaderCallback
                public void onCallBackAction(Integer num, Object obj, Object obj2) {
                    Object obj3;
                    TbsReaderView tbsReaderView;
                    String str;
                    TbsReaderView tbsReaderView2;
                    String str2;
                    Bundle bundle;
                    Bundle bundle2;
                    int intValue = num.intValue();
                    String str3 = BuildConfig.FLAVOR;
                    Bundle bundle3 = null;
                    boolean z = true;
                    if (intValue != 5026) {
                        bundle2 = obj;
                        obj3 = obj;
                        if (intValue != 5030) {
                            switch (intValue) {
                                case ReaderCallback.READER_PDF_LIST /* 5008 */:
                                    if (MttLoader.isBrowserInstalledEx(TbsReaderView.this.f1257a)) {
                                        if (obj != null) {
                                            bundle3 = (Bundle) obj;
                                            str3 = bundle3.getString("docpath");
                                        }
                                        QbSdk.startQBForDoc(TbsReaderView.this.f1257a, str3, 4, 0, "pdf", bundle3);
                                        tbsReaderView = TbsReaderView.this;
                                        str = TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_BROWSER;
                                        tbsReaderView.userStatistics(str);
                                        obj3 = obj;
                                        break;
                                    } else {
                                        num = Integer.valueOf((int) ReaderCallback.INSTALL_QB);
                                        String resString = TbsReaderView.getResString(TbsReaderView.this.f1257a, ReaderCallback.READER_PLUGIN_RES_PDF_GUIDE);
                                        Bundle bundle4 = new Bundle();
                                        bundle4.putString("tip", resString);
                                        bundle4.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_DOWNLOAD);
                                        bundle4.putInt("channel_id", TbsReaderView.READER_CHANNEL_PDF_ID);
                                        tbsReaderView2 = TbsReaderView.this;
                                        str2 = TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_DIALOG;
                                        bundle = bundle4;
                                        tbsReaderView2.userStatistics(str2);
                                        bundle2 = bundle;
                                        z = false;
                                        obj3 = bundle2;
                                        break;
                                    }
                                case ReaderCallback.READER_PPT_PLAY_MODEL /* 5009 */:
                                    if (MttLoader.isBrowserInstalledEx(TbsReaderView.this.f1257a)) {
                                        if (obj != null) {
                                            bundle3 = (Bundle) obj;
                                            str3 = bundle3.getString("docpath");
                                        }
                                        QbSdk.startQBForDoc(TbsReaderView.this.f1257a, str3, 4, 0, BuildConfig.FLAVOR, bundle3);
                                        tbsReaderView = TbsReaderView.this;
                                        str = TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_BROWSER;
                                        tbsReaderView.userStatistics(str);
                                        obj3 = obj;
                                        break;
                                    } else {
                                        num = Integer.valueOf((int) ReaderCallback.INSTALL_QB);
                                        String resString2 = TbsReaderView.getResString(TbsReaderView.this.f1257a, ReaderCallback.READER_PLUGIN_RES_PPT_GUIDE);
                                        Bundle bundle5 = new Bundle();
                                        bundle5.putString("tip", resString2);
                                        bundle5.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_DOWNLOAD);
                                        bundle5.putInt("channel_id", TbsReaderView.READER_CHANNEL_PPT_ID);
                                        tbsReaderView2 = TbsReaderView.this;
                                        str2 = TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_DIALOG;
                                        bundle = bundle5;
                                        tbsReaderView2.userStatistics(str2);
                                        bundle2 = bundle;
                                        z = false;
                                        obj3 = bundle2;
                                        break;
                                    }
                                case ReaderCallback.READER_TXT_READING_MODEL /* 5010 */:
                                    if (MttLoader.isBrowserInstalledEx(TbsReaderView.this.f1257a)) {
                                        if (obj != null) {
                                            bundle3 = (Bundle) obj;
                                            str3 = bundle3.getString("docpath");
                                        }
                                        QbSdk.startQBForDoc(TbsReaderView.this.f1257a, str3, 4, 0, "txt", bundle3);
                                        obj3 = obj;
                                        break;
                                    } else {
                                        num = Integer.valueOf((int) ReaderCallback.INSTALL_QB);
                                        String resString3 = TbsReaderView.getResString(TbsReaderView.this.f1257a, ReaderCallback.READER_PLUGIN_RES_TXT_GUIDE);
                                        Bundle bundle6 = new Bundle();
                                        bundle6.putString("tip", resString3);
                                        bundle6.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_TXT_INTO_DOWNLOAD);
                                        bundle6.putInt("channel_id", TbsReaderView.READER_CHANNEL_TXT_ID);
                                        tbsReaderView2 = TbsReaderView.this;
                                        str2 = TbsReaderView.READER_STATISTICS_COUNT_TXT_INTO_DIALOG;
                                        bundle = bundle6;
                                        tbsReaderView2.userStatistics(str2);
                                        bundle2 = bundle;
                                        z = false;
                                        obj3 = bundle2;
                                        break;
                                    }
                                default:
                                    z = false;
                                    obj3 = bundle2;
                                    break;
                            }
                        } else if (obj != null) {
                            Bundle bundle7 = (Bundle) obj;
                            TbsReaderView.gReaderPackName = bundle7.getString("name");
                            TbsReaderView.gReaderPackVersion = bundle7.getString("version");
                            obj3 = obj;
                        }
                    } else if (!MttLoader.isBrowserInstalledEx(TbsReaderView.this.f1257a)) {
                        num = Integer.valueOf((int) ReaderCallback.INSTALL_QB);
                        String resString4 = TbsReaderView.getResString(TbsReaderView.this.f1257a, ReaderCallback.READER_PLUGIN_RES_DOC_GUIDE);
                        Bundle bundle8 = new Bundle();
                        bundle8.putString("tip", resString4);
                        bundle8.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_DOWNLOAD);
                        bundle8.putInt("channel_id", TbsReaderView.READER_CHANNEL_DOC_ID);
                        tbsReaderView2 = TbsReaderView.this;
                        str2 = TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_DIALOG;
                        bundle = bundle8;
                        tbsReaderView2.userStatistics(str2);
                        bundle2 = bundle;
                        z = false;
                        obj3 = bundle2;
                    } else {
                        if (obj != null) {
                            bundle3 = (Bundle) obj;
                            str3 = bundle3.getString("docpath");
                        }
                        QbSdk.startQBForDoc(TbsReaderView.this.f1257a, str3, 4, 0, "doc", bundle3);
                        tbsReaderView = TbsReaderView.this;
                        str = TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_BROWSER;
                        tbsReaderView.userStatistics(str);
                        obj3 = obj;
                    }
                    ReaderCallback readerCallback2 = TbsReaderView.this.f1260d;
                    if (readerCallback2 != null && !z) {
                        readerCallback2.onCallBackAction(num, obj3, obj2);
                    }
                }
            };
            return;
        }
        throw new RuntimeException("error: unexpect context(none Activity)");
    }

    public static boolean a(Context context) {
        if (!f) {
            d.a(true).a(context.getApplicationContext(), true, false);
            f = d.a(false).b();
        }
        return f;
    }

    public static Drawable getResDrawable(Context context, int i) {
        if (a(context)) {
            return ReaderWizard.getResDrawable(i);
        }
        return null;
    }

    public static String getResString(Context context, int i) {
        return a(context) ? ReaderWizard.getResString(i) : BuildConfig.FLAVOR;
    }

    public static boolean isSupportExt(Context context, String str) {
        return a(context) && ReaderWizard.isSupportCurrentPlatform(context) && ReaderWizard.isSupportExt(str);
    }

    public boolean a() {
        try {
            if (this.f1258b == null) {
                this.f1258b = new ReaderWizard(this.f1261e);
            }
            if (this.f1259c == null) {
                this.f1259c = this.f1258b.getTbsReader();
            }
            if (this.f1259c != null) {
                return this.f1258b.initTbsReader(this.f1259c, this.f1257a);
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public void doCommand(Integer num, Object obj, Object obj2) {
        Object obj3;
        ReaderWizard readerWizard = this.f1258b;
        if (readerWizard != null && (obj3 = this.f1259c) != null) {
            readerWizard.doCommand(obj3, num, obj, obj2);
        }
    }

    public boolean downloadPlugin(String str) {
        Object obj = this.f1259c;
        if (obj == null) {
            return false;
        }
        return this.f1258b.checkPlugin(obj, this.f1257a, str, true);
    }

    public void onSizeChanged(int i, int i2) {
        Object obj;
        ReaderWizard readerWizard = this.f1258b;
        if (readerWizard != null && (obj = this.f1259c) != null) {
            readerWizard.onSizeChanged(obj, i, i2);
        }
    }

    public void onStop() {
        ReaderWizard readerWizard = this.f1258b;
        if (readerWizard != null) {
            readerWizard.destroy(this.f1259c);
            this.f1259c = null;
        }
        this.f1257a = null;
        f = false;
    }

    public void openFile(Bundle bundle) {
        if (this.f1259c != null && bundle != null) {
            bundle.putBoolean("browser6.0", MttLoader.isBrowserInstalledEx(this.f1257a) | (!MttLoader.isBrowserInstalled(this.f1257a)));
            bundle.putBoolean("browser6.1", MttLoader.isGreatBrowserVer(this.f1257a, 6101625L, 610000L) | (!MttLoader.isBrowserInstalled(this.f1257a)));
            this.f1258b.openFile(this.f1259c, this.f1257a, bundle, this);
        }
    }

    public boolean preOpen(String str, boolean z) {
        boolean z2 = false;
        if (!isSupportExt(this.f1257a, str)) {
            String str2 = "not supported by:" + str;
            return false;
        }
        boolean a2 = a(this.f1257a);
        if (!a2) {
            return a2;
        }
        boolean a3 = a();
        if (!z || !a3) {
            return a3;
        }
        if (Apn.getApnType(this.f1257a) == 3) {
            z2 = true;
        }
        return this.f1258b.checkPlugin(this.f1259c, this.f1257a, str, z2);
    }

    public void userStatistics(String str) {
        ReaderWizard readerWizard = this.f1258b;
        if (readerWizard != null) {
            readerWizard.userStatistics(this.f1259c, str);
        }
    }
}
