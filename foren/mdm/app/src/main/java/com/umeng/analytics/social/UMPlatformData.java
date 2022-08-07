package com.umeng.analytics.social;

import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.umeng.analytics.a;
import java.util.Locale;

/* loaded from: classes2.dex */
public class UMPlatformData {
    private UMedia a;
    private String b;
    private String c = "";
    private String d;
    private GENDER e;

    /* loaded from: classes2.dex */
    public enum UMedia {
        SINA_WEIBO {
            @Override // java.lang.Enum
            public String toString() {
                return "sina";
            }
        },
        TENCENT_WEIBO {
            @Override // java.lang.Enum
            public String toString() {
                return "tencent";
            }
        },
        TENCENT_QZONE {
            @Override // java.lang.Enum
            public String toString() {
                return Constants.SOURCE_QZONE;
            }
        },
        TENCENT_QQ {
            @Override // java.lang.Enum
            public String toString() {
                return "qq";
            }
        },
        WEIXIN_FRIENDS {
            @Override // java.lang.Enum
            public String toString() {
                return "wxsesion";
            }
        },
        WEIXIN_CIRCLE {
            @Override // java.lang.Enum
            public String toString() {
                return "wxtimeline";
            }
        },
        RENREN {
            @Override // java.lang.Enum
            public String toString() {
                return "renren";
            }
        },
        DOUBAN {
            @Override // java.lang.Enum
            public String toString() {
                return "douban";
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum GENDER {
        MALE(0) {
            @Override // java.lang.Enum
            public String toString() {
                return String.format(Locale.US, "Male:%d", Integer.valueOf(this.value));
            }
        },
        FEMALE(1) {
            @Override // java.lang.Enum
            public String toString() {
                return String.format(Locale.US, "Female:%d", Integer.valueOf(this.value));
            }
        };
        
        public int value;

        GENDER(int i) {
            this.value = i;
        }
    }

    public UMPlatformData(UMedia uMedia, String str) {
        this.b = "";
        if (uMedia == null || TextUtils.isEmpty(str)) {
            b.b(a.d, "parameter is not valid");
            return;
        }
        this.a = uMedia;
        this.b = str;
    }

    public String getWeiboId() {
        return this.c;
    }

    public void setWeiboId(String str) {
        this.c = str;
    }

    public UMedia getMeida() {
        return this.a;
    }

    public String getUsid() {
        return this.b;
    }

    public String getName() {
        return this.d;
    }

    public void setName(String str) {
        this.d = str;
    }

    public GENDER getGender() {
        return this.e;
    }

    public void setGender(GENDER gender) {
        this.e = gender;
    }

    public boolean isValid() {
        return this.a != null && !TextUtils.isEmpty(this.b);
    }

    public String toString() {
        return "UMPlatformData [meida=" + this.a + ", usid=" + this.b + ", weiboId=" + this.c + ", name=" + this.d + ", gender=" + this.e + "]";
    }
}
