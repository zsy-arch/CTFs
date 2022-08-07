package com.yolanda.nohttp.tools;

import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.security.Security;

/* loaded from: classes2.dex */
public final class PRNGFixes {
    private static final byte[] BUILD_FINGERPRINT_AND_DEVICE_SERIAL = getBuildFingerprintAndDeviceSerial();

    private PRNGFixes() {
    }

    public static void apply() {
        applyOpenSSLFix();
        installLinuxPRNGSecureRandom();
    }

    private static void applyOpenSSLFix() throws SecurityException {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT <= 18) {
            try {
                Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_seed", byte[].class).invoke(null, generateSeed());
                int bytesRead = ((Integer) Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_load_file", String.class, Long.TYPE).invoke(null, "/dev/urandom", 1024)).intValue();
                if (bytesRead != 1024) {
                    throw new IOException("Unexpected number of bytes read from Linux PRNG: " + bytesRead);
                }
            } catch (Exception e) {
                throw new SecurityException("Failed to seed OpenSSL PRNG", e);
            }
        }
    }

    private static void installLinuxPRNGSecureRandom() throws SecurityException {
        if (Build.VERSION.SDK_INT <= 18) {
            Provider[] secureRandomProviders = Security.getProviders("SecureRandom.SHA1PRNG");
            if (secureRandomProviders == null || secureRandomProviders.length < 1 || !LinuxPRNGSecureRandomProvider.class.equals(secureRandomProviders[0].getClass())) {
                Security.insertProviderAt(new LinuxPRNGSecureRandomProvider(), 1);
            }
            SecureRandom rng1 = new SecureRandom();
            if (!LinuxPRNGSecureRandomProvider.class.equals(rng1.getProvider().getClass())) {
                throw new SecurityException("new SecureRandom() backed by wrong Provider: " + rng1.getProvider().getClass());
            }
            try {
                SecureRandom rng2 = SecureRandom.getInstance("SHA1PRNG");
                if (!LinuxPRNGSecureRandomProvider.class.equals(rng2.getProvider().getClass())) {
                    throw new SecurityException("SecureRandom.getInstance(\"SHA1PRNG\") backed by wrong Provider: " + rng2.getProvider().getClass());
                }
            } catch (NoSuchAlgorithmException e) {
                throw new SecurityException("SHA1PRNG not available", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class LinuxPRNGSecureRandomProvider extends Provider {
        private static final long serialVersionUID = 1354323215;

        public LinuxPRNGSecureRandomProvider() {
            super("LinuxPRNG", 1.0d, "A Linux-specific random number provider that uses /dev/urandom");
            put("SecureRandom.SHA1PRNG", LinuxPRNGSecureRandom.class.getName());
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }
    }

    /* loaded from: classes2.dex */
    public static class LinuxPRNGSecureRandom extends SecureRandomSpi {
        private static final File URANDOM_FILE = new File("/dev/urandom");
        private static final Object sLock = new Object();
        private static DataInputStream sUrandomIn = null;
        private static OutputStream sUrandomOut = null;
        private static final long serialVersionUID = 140354302415L;
        private boolean mSeeded;

        @Override // java.security.SecureRandomSpi
        protected void engineSetSeed(byte[] bytes) {
            OutputStream out;
            try {
                synchronized (sLock) {
                    out = getUrandomOutputStream();
                }
                out.write(bytes);
                out.flush();
            } catch (IOException e) {
                Log.w(PRNGFixes.class.getSimpleName(), "Failed to mix seed into " + URANDOM_FILE);
            } finally {
                this.mSeeded = true;
            }
        }

        @Override // java.security.SecureRandomSpi
        protected void engineNextBytes(byte[] bytes) {
            DataInputStream in;
            if (!this.mSeeded) {
                engineSetSeed(PRNGFixes.generateSeed());
            }
            try {
                synchronized (sLock) {
                    in = getUrandomInputStream();
                }
                synchronized (in) {
                    in.readFully(bytes);
                }
            } catch (IOException e) {
                throw new SecurityException("Failed to read from " + URANDOM_FILE, e);
            }
        }

        @Override // java.security.SecureRandomSpi
        protected byte[] engineGenerateSeed(int size) {
            byte[] seed = new byte[size];
            engineNextBytes(seed);
            return seed;
        }

        private DataInputStream getUrandomInputStream() {
            DataInputStream dataInputStream;
            synchronized (sLock) {
                if (sUrandomIn == null) {
                    try {
                        sUrandomIn = new DataInputStream(new FileInputStream(URANDOM_FILE));
                    } catch (IOException e) {
                        throw new SecurityException("Failed to open " + URANDOM_FILE + " for reading", e);
                    }
                }
                dataInputStream = sUrandomIn;
            }
            return dataInputStream;
        }

        private OutputStream getUrandomOutputStream() throws IOException {
            OutputStream outputStream;
            synchronized (sLock) {
                if (sUrandomOut == null) {
                    sUrandomOut = new FileOutputStream(URANDOM_FILE);
                }
                outputStream = sUrandomOut;
            }
            return outputStream;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] generateSeed() {
        try {
            ByteArrayOutputStream seedBuffer = new ByteArrayOutputStream();
            DataOutputStream seedBufferOut = new DataOutputStream(seedBuffer);
            seedBufferOut.writeLong(System.currentTimeMillis());
            seedBufferOut.writeLong(System.nanoTime());
            seedBufferOut.writeInt(Process.myPid());
            seedBufferOut.writeInt(Process.myUid());
            seedBufferOut.write(BUILD_FINGERPRINT_AND_DEVICE_SERIAL);
            seedBufferOut.close();
            return seedBuffer.toByteArray();
        } catch (IOException e) {
            throw new SecurityException("Failed to generate seed", e);
        }
    }

    private static String getDeviceSerialNumber() {
        try {
            return (String) Build.class.getField("SERIAL").get(null);
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] getBuildFingerprintAndDeviceSerial() {
        StringBuilder result = new StringBuilder();
        String fingerprint = Build.FINGERPRINT;
        if (fingerprint != null) {
            result.append(fingerprint);
        }
        String serial = getDeviceSerialNumber();
        if (serial != null) {
            result.append(serial);
        }
        try {
            return result.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding not supported");
        }
    }
}
