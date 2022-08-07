package c.a.f;

import android.util.Property;
import androidx.appcompat.widget.SwitchCompat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ea extends Property<SwitchCompat, Float> {
    public ea(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Float get(SwitchCompat switchCompat) {
        return Float.valueOf(switchCompat.z);
    }

    @Override // android.util.Property
    public void set(SwitchCompat switchCompat, Float f) {
        switchCompat.setThumbPosition(f.floatValue());
    }
}
