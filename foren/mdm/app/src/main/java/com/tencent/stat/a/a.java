package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.StatConfig;
import com.tencent.stat.common.k;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class a extends e {
    Map<String, ?> a;

    public a(Context context, int i, Map<String, ?> map) {
        super(context, i);
        this.a = null;
        this.a = map;
    }

    @Override // com.tencent.stat.a.e
    public f a() {
        return f.ADDITION;
    }

    @Override // com.tencent.stat.a.e
    public boolean a(JSONObject jSONObject) {
        k.a(jSONObject, "qq", StatConfig.getQQ());
        if (this.a == null || this.a.size() <= 0) {
            return true;
        }
        for (Map.Entry<String, ?> entry : this.a.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        return true;
    }
}
