package com.alipay.android.phone.mrpc.core;

import com.alipay.android.phone.mrpc.core.gwprotocol.Deserializer;
import com.alipay.android.phone.mrpc.core.gwprotocol.JsonDeserializer;
import com.alipay.android.phone.mrpc.core.gwprotocol.JsonSerializer;
import com.alipay.android.phone.mrpc.core.gwprotocol.Serializer;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.annotation.ResetCookie;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class RpcInvoker {
    private static final byte MODE_BATCH = 1;
    private static final byte MODE_DEFAULT = 0;
    private RpcFactory mRpcFactory;
    private static final ThreadLocal<Object> RETURN_VALUE = new ThreadLocal<>();
    private static final ThreadLocal<Map<String, Object>> EXT_PARAM = new ThreadLocal<>();
    private byte mMode = 0;
    private AtomicInteger rpcSequence = new AtomicInteger();

    public RpcInvoker(RpcFactory rpcFactory) {
        this.mRpcFactory = rpcFactory;
    }

    public static void addProtocolArgs(String str, Object obj) {
        if (EXT_PARAM.get() == null) {
            EXT_PARAM.set(new HashMap());
        }
        EXT_PARAM.get().put(str, obj);
    }

    private void exceptionHandle(Object obj, byte[] bArr, Class<?> cls, Method method, Object[] objArr, Annotation[] annotationArr, RpcException rpcException) {
        throw rpcException;
    }

    private void postHandle(Object obj, byte[] bArr, Class<?> cls, Method method, Object[] objArr, Annotation[] annotationArr) {
    }

    private void preHandle(Object obj, Class<?> cls, Method method, Object[] objArr, Annotation[] annotationArr) {
    }

    private byte[] singleCall(Method method, Object[] objArr, String str, int i, boolean z) {
        Serializer serializer = getSerializer(i, str, objArr);
        if (EXT_PARAM.get() != null) {
            serializer.setExtParam(EXT_PARAM.get());
        }
        byte[] bArr = (byte[]) getTransport(method, i, str, serializer.packet(), z).call();
        EXT_PARAM.set(null);
        return bArr;
    }

    public void batchBegin() {
        this.mMode = (byte) 1;
    }

    public FutureTask<?> batchCommit() {
        this.mMode = (byte) 0;
        return null;
    }

    public Deserializer getDeserializer(Type type, byte[] bArr) {
        return new JsonDeserializer(type, bArr);
    }

    public Serializer getSerializer(int i, String str, Object[] objArr) {
        return new JsonSerializer(i, str, objArr);
    }

    public RpcCaller getTransport(Method method, int i, String str, byte[] bArr, boolean z) {
        return new HttpCaller(this.mRpcFactory.getConfig(), method, i, str, bArr, z);
    }

    public Object invoke(Object obj, Class<?> cls, Method method, Object[] objArr) {
        RpcException e;
        if (ThreadUtil.checkMainThread()) {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
        OperationType operationType = (OperationType) method.getAnnotation(OperationType.class);
        boolean z = method.getAnnotation(ResetCookie.class) != null;
        Type genericReturnType = method.getGenericReturnType();
        Annotation[] annotations = method.getAnnotations();
        RETURN_VALUE.set(null);
        EXT_PARAM.set(null);
        if (operationType == null) {
            throw new IllegalStateException("OperationType must be set.");
        }
        String value = operationType.value();
        int incrementAndGet = this.rpcSequence.incrementAndGet();
        preHandle(obj, cls, method, objArr, annotations);
        byte[] bArr = null;
        try {
            if (this.mMode == 0) {
                bArr = singleCall(method, objArr, value, incrementAndGet, z);
                try {
                    Object parser = getDeserializer(genericReturnType, bArr).parser();
                    if (genericReturnType != Void.TYPE) {
                        RETURN_VALUE.set(parser);
                    }
                } catch (RpcException e2) {
                    e = e2;
                    bArr = bArr;
                    e.setOperationType(value);
                    exceptionHandle(obj, bArr, cls, method, objArr, annotations, e);
                    postHandle(obj, bArr, cls, method, objArr, annotations);
                    return RETURN_VALUE.get();
                }
            }
        } catch (RpcException e3) {
            e = e3;
            bArr = null;
        }
        postHandle(obj, bArr, cls, method, objArr, annotations);
        return RETURN_VALUE.get();
    }
}
