package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;

/* loaded from: classes2.dex */
public interface HostNameResolver {
    InetAddress resolve(String str) throws IOException;
}
