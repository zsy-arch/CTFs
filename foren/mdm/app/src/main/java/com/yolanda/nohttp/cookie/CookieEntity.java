package com.yolanda.nohttp.cookie;

import android.text.TextUtils;
import com.yolanda.nohttp.db.DBId;
import com.yolanda.nohttp.tools.HttpDateTime;
import java.io.Serializable;
import java.net.HttpCookie;
import java.net.URI;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class CookieEntity implements DBId, Serializable {
    private static final long serialVersionUID = 6374381323722046732L;
    private String comment;
    private String commentURL;
    private boolean discard;
    private String domain;
    private long expiry;
    private long id;
    private String name;
    private String path;
    private String portList;
    private boolean secure;
    private String uri;
    private String value;
    private int version;

    public CookieEntity() {
        this.id = -1L;
        this.version = 1;
    }

    public CookieEntity(URI uri, HttpCookie cookie) {
        this.id = -1L;
        this.version = 1;
        this.uri = uri == null ? null : uri.toString();
        this.name = cookie.getName();
        this.value = cookie.getValue();
        this.comment = cookie.getComment();
        this.commentURL = cookie.getCommentURL();
        this.discard = cookie.getDiscard();
        this.domain = cookie.getDomain();
        long maxAge = cookie.getMaxAge();
        if (maxAge > 0) {
            this.expiry = (1000 * maxAge) + System.currentTimeMillis();
            if (this.expiry < 0) {
                this.expiry = HttpDateTime.getMaxExpiryMillis();
            }
        } else {
            this.expiry = -1L;
        }
        this.path = cookie.getPath();
        if (!TextUtils.isEmpty(this.path) && this.path.length() > 1 && this.path.endsWith("/")) {
            this.path = this.path.substring(0, this.path.length() - 1);
        }
        this.portList = cookie.getPortlist();
        this.secure = cookie.getSecure();
        this.version = cookie.getVersion();
    }

    public HttpCookie toHttpCookie() {
        HttpCookie cookie = new HttpCookie(this.name, this.value);
        cookie.setComment(this.comment);
        cookie.setCommentURL(this.commentURL);
        cookie.setDiscard(this.discard);
        cookie.setDomain(this.domain);
        cookie.setMaxAge((this.expiry - System.currentTimeMillis()) / 1000);
        cookie.setPath(this.path);
        cookie.setPortlist(this.portList);
        cookie.setSecure(this.secure);
        cookie.setVersion(this.version);
        return cookie;
    }

    @Override // com.yolanda.nohttp.db.DBId
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentURL() {
        return this.commentURL;
    }

    public void setCommentURL(String commentURL) {
        this.commentURL = commentURL;
    }

    public boolean isDiscard() {
        return this.discard;
    }

    public void setDiscard(boolean discard) {
        this.discard = discard;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public long getExpiry() {
        return this.expiry;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPortList() {
        return this.portList;
    }

    public void setPortList(String portList) {
        this.portList = portList;
    }

    public boolean isSecure() {
        return this.secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
