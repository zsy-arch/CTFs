package com.lidroid.xutils.http.client;

import com.lidroid.xutils.util.LogUtils;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* loaded from: classes2.dex */
public class DefaultSSLSocketFactory extends SSLSocketFactory {
    private static DefaultSSLSocketFactory instance;
    private static KeyStore trustStore;
    private SSLContext sslContext = SSLContext.getInstance("TLS");

    static {
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
        } catch (Throwable e) {
            LogUtils.e(e.getMessage(), e);
        }
    }

    public static DefaultSSLSocketFactory getSocketFactory() {
        if (instance == null) {
            try {
                instance = new DefaultSSLSocketFactory();
            } catch (Throwable e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
        return instance;
    }

    private DefaultSSLSocketFactory() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        super(trustStore);
        this.sslContext.init(null, new TrustManager[]{new X509TrustManager() { // from class: com.lidroid.xutils.http.client.DefaultSSLSocketFactory.1
            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        }}, null);
        setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.LayeredSocketFactory
    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
        return this.sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
    }

    @Override // org.apache.http.conn.ssl.SSLSocketFactory, org.apache.http.conn.scheme.SocketFactory
    public Socket createSocket() throws IOException {
        return this.sslContext.getSocketFactory().createSocket();
    }
}
