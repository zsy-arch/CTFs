package retrofit2.adapter.rxjava;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/* loaded from: classes2.dex */
final class CallEnqueueOnSubscribe<T> implements Observable.OnSubscribe<Response<T>> {
    private final Call<T> originalCall;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallEnqueueOnSubscribe(Call<T> originalCall) {
        this.originalCall = originalCall;
    }

    public void call(Subscriber<? super Response<T>> subscriber) {
        Call<T> call = this.originalCall.clone();
        final CallArbiter<T> arbiter = new CallArbiter<>(call, subscriber);
        subscriber.add(arbiter);
        subscriber.setProducer(arbiter);
        call.enqueue(new Callback<T>() { // from class: retrofit2.adapter.rxjava.CallEnqueueOnSubscribe.1
            @Override // retrofit2.Callback
            public void onResponse(Call<T> call2, Response<T> response) {
                arbiter.emitResponse(response);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<T> call2, Throwable t) {
                Exceptions.throwIfFatal(t);
                arbiter.emitError(t);
            }
        });
    }
}
