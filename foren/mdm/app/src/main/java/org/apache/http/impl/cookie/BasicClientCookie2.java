package org.apache.http.impl.cookie;

import java.util.Date;
import org.apache.http.cookie.SetCookie2;

@Deprecated
/* loaded from: classes.dex */
public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {
    public BasicClientCookie2(String name, String value) {
        super(null, null);
        throw new RuntimeException("Stub!");
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public int[] getPorts() {
        throw new RuntimeException("Stub!");
    }

    @Override // org.apache.http.cookie.SetCookie2
    public void setPorts(int[] ports) {
        throw new RuntimeException("Stub!");
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public String getCommentURL() {
        throw new RuntimeException("Stub!");
    }

    @Override // org.apache.http.cookie.SetCookie2
    public void setCommentURL(String commentURL) {
        throw new RuntimeException("Stub!");
    }

    @Override // org.apache.http.cookie.SetCookie2
    public void setDiscard(boolean discard) {
        throw new RuntimeException("Stub!");
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public boolean isPersistent() {
        throw new RuntimeException("Stub!");
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie, org.apache.http.cookie.Cookie
    public boolean isExpired(Date date) {
        throw new RuntimeException("Stub!");
    }

    @Override // org.apache.http.impl.cookie.BasicClientCookie
    public Object clone() throws CloneNotSupportedException {
        throw new RuntimeException("Stub!");
    }
}
