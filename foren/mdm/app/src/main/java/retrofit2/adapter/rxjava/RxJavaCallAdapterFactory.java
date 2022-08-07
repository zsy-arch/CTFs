package retrofit2.adapter.rxjava;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Completable;
import rx.Observable;
import rx.Scheduler;
import rx.Single;

/* loaded from: classes.dex */
public final class RxJavaCallAdapterFactory extends CallAdapter.Factory {
    private final boolean isAsync;
    @Nullable
    private final Scheduler scheduler;

    public static RxJavaCallAdapterFactory create() {
        return new RxJavaCallAdapterFactory(null, false);
    }

    public static RxJavaCallAdapterFactory createAsync() {
        return new RxJavaCallAdapterFactory(null, true);
    }

    public static RxJavaCallAdapterFactory createWithScheduler(Scheduler scheduler) {
        if (scheduler != null) {
            return new RxJavaCallAdapterFactory(scheduler, false);
        }
        throw new NullPointerException("scheduler == null");
    }

    private RxJavaCallAdapterFactory(@Nullable Scheduler scheduler, boolean isAsync) {
        this.scheduler = scheduler;
        this.isAsync = isAsync;
    }

    @Override // retrofit2.CallAdapter.Factory
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Type responseType;
        Class<?> rawType = getRawType(returnType);
        boolean isSingle = rawType == Single.class;
        boolean isCompletable = rawType == Completable.class;
        if (rawType != Observable.class && !isSingle && !isCompletable) {
            return null;
        }
        if (isCompletable) {
            return new RxJavaCallAdapter(Void.class, this.scheduler, this.isAsync, false, true, false, true);
        }
        boolean isResult = false;
        boolean isBody = false;
        if (!(returnType instanceof ParameterizedType)) {
            String name = isSingle ? "Single" : "Observable";
            throw new IllegalStateException(name + " return type must be parameterized as " + name + "<Foo> or " + name + "<? extends Foo>");
        }
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawObservableType = getRawType(observableType);
        if (rawObservableType == Response.class) {
            if (!(observableType instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
            responseType = getParameterUpperBound(0, (ParameterizedType) observableType);
        } else if (rawObservableType != Result.class) {
            responseType = observableType;
            isBody = true;
        } else if (!(observableType instanceof ParameterizedType)) {
            throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
        } else {
            responseType = getParameterUpperBound(0, (ParameterizedType) observableType);
            isResult = true;
        }
        return new RxJavaCallAdapter(responseType, this.scheduler, this.isAsync, isResult, isBody, isSingle, false);
    }
}
