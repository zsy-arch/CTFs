package com.umeng.update;

import android.content.Context;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import java.io.Serializable;
import org.json.JSONObject;
import u.upd.c;
import u.upd.i;
import u.upd.n;

/* loaded from: classes2.dex */
public class UpdateResponse extends i implements Serializable {
    private static final long a = -7768683594079202710L;
    public boolean display_ads;
    public String new_md5;
    public String origin;
    public String patch_md5;
    public String path;
    public String proto_ver;
    public String size;
    public String target_size;
    public boolean hasUpdate = false;
    public String updateLog = null;
    public String version = null;
    public boolean delta = false;

    public UpdateResponse(JSONObject jSONObject) {
        super(jSONObject);
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        try {
            this.hasUpdate = "Yes".equalsIgnoreCase(jSONObject.optString(UpdateConfig.a));
            if (this.hasUpdate) {
                this.updateLog = jSONObject.getString("update_log");
                this.version = jSONObject.getString("version");
                this.path = jSONObject.getString("path");
                this.target_size = jSONObject.optString("target_size");
                this.new_md5 = jSONObject.optString("new_md5");
                this.delta = jSONObject.optBoolean(a.l);
                this.display_ads = jSONObject.optBoolean("display_ads", false);
                if (this.delta) {
                    this.patch_md5 = jSONObject.optString("patch_md5");
                    this.size = jSONObject.optString("size");
                    this.origin = jSONObject.optString(OSSHeaders.ORIGIN);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String a(Context context, boolean z) {
        String str;
        String string = context.getString(c.a(context).f("UMNewVersion"));
        String string2 = context.getString(c.a(context).f("UMTargetSize"));
        String string3 = context.getString(c.a(context).f("UMUpdateSize"));
        String string4 = context.getString(c.a(context).f("UMUpdateContent"));
        String string5 = context.getString(c.a(context).f("UMDialog_InstallAPK"));
        if (z) {
            return String.format("%s %s\n%s\n\n%s\n%s\n", string, this.version, string5, string4, this.updateLog);
        }
        if (this.delta) {
            str = String.format("\n%s %s", string3, n.c(this.size));
        } else {
            str = "";
        }
        return String.format("%s %s\n%s %s%s\n\n%s\n%s\n", string, this.version, string2, n.c(this.target_size), str, string4, this.updateLog);
    }
}
