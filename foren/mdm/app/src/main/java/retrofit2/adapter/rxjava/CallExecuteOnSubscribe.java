package retrofit2.adapter.rxjava;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/* loaded from: classes2.dex */
final class CallExecuteOnSubscribe<T> implements Observable.OnSubscribe<Response<T>> {
    private final Call<T> originalCall;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallExecuteOnSubscribe(Call<T> originalCall) {
        this.originalCall = originalCall;
    }

    public void call(Subscriber<? super Response<T>> subscriber) {
        Call<T> call = this.originalCall.clone();
        CallArbiter<T> arbiter = new CallArbiter<>(call, subscriber);
        subscriber.add(arbiter);
        subscriber.setProducer(arbiter);
        try {
            arbiter.emitResponse(call.execute());
        } catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            arbiter.emitError(t);
        }
    }
}
