package com.umeng.analytics;

import java.util.Locale;
import u.aly.ba;

/* loaded from: classes2.dex */
public enum Gender {
    Male(1) {
        @Override // java.lang.Enum
        public String toString() {
            return String.format(Locale.US, "Male:%d", Integer.valueOf(this.value));
        }
    },
    Female(2) {
        @Override // java.lang.Enum
        public String toString() {
            return String.format(Locale.US, "Female:%d", Integer.valueOf(this.value));
        }
    },
    Unknown(0) {
        @Override // java.lang.Enum
        public String toString() {
            return String.format(Locale.US, "Unknown:%d", Integer.valueOf(this.value));
        }
    };
    
    public int value;

    Gender(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static Gender getGender(int i) {
        switch (i) {
            case 1:
                return Male;
            case 2:
                return Female;
            default:
                return Unknown;
        }
    }

    public static ba transGender(Gender gender) {
        switch (gender) {
            case Male:
                return ba.MALE;
            case Female:
                return ba.FEMALE;
            default:
                return ba.UNKNOWN;
        }
    }
}
