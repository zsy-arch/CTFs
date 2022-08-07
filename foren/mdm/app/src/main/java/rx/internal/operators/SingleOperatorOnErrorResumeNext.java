package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

/* loaded from: classes2.dex */
public final class SingleOperatorOnErrorResumeNext<T> implements Single.OnSubscribe<T> {
    private final Single<? extends T> originalSingle;
    final Func1<Throwable, ? extends Single<? extends T>> resumeFunctionInCaseOfError;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((SingleSubscriber) ((SingleSubscriber) x0));
    }

    private SingleOperatorOnErrorResumeNext(Single<? extends T> originalSingle, Func1<Throwable, ? extends Single<? extends T>> resumeFunctionInCaseOfError) {
        if (originalSingle == null) {
            throw new NullPointerException("originalSingle must not be null");
        } else if (resumeFunctionInCaseOfError == null) {
            throw new NullPointerException("resumeFunctionInCaseOfError must not be null");
        } else {
            this.originalSingle = originalSingle;
            this.resumeFunctionInCaseOfError = resumeFunctionInCaseOfError;
        }
    }

    public static <T> SingleOperatorOnErrorResumeNext<T> withFunction(Single<? extends T> originalSingle, Func1<Throwable, ? extends Single<? extends T>> resumeFunctionInCaseOfError) {
        return new SingleOperatorOnErrorResumeNext<>(originalSingle, resumeFunctionInCaseOfError);
    }

    public static <T> SingleOperatorOnErrorResumeNext<T> withOther(Single<? extends T> originalSingle, final Single<? extends T> resumeSingleInCaseOfError) {
        if (resumeSingleInCaseOfError != null) {
            return new SingleOperatorOnErrorResumeNext<>(originalSingle, new Func1<Throwable, Single<? extends T>>() { // from class: rx.internal.operators.SingleOperatorOnErrorResumeNext.1
                public Single<? extends T> call(Throwable throwable) {
                    return Single.this;
                }
            });
        }
        throw new NullPointerException("resumeSingleInCaseOfError must not be null");
    }

    public void call(final SingleSubscriber<? super T> child) {
        SingleSubscriber<T> singleSubscriber = new SingleSubscriber<T>() { // from class: rx.internal.operators.SingleOperatorOnErrorResumeNext.2
            @Override // rx.SingleSubscriber
            public void onSuccess(T value) {
                child.onSuccess(value);
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable error) {
                try {
                    ((Single) SingleOperatorOnErrorResumeNext.this.resumeFunctionInCaseOfError.call(error)).subscribe(child);
                } catch (Throwable innerError) {
                    Exceptions.throwOrReport(innerError, child);
                }
            }
        };
        child.add(singleSubscriber);
        this.originalSingle.subscribe((SingleSubscriber<? super Object>) singleSubscriber);
    }
}
