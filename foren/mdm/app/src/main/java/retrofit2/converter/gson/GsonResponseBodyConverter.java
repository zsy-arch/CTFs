package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/* loaded from: classes2.dex */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    public T convert(ResponseBody value) throws IOException {
        try {
            return this.adapter.read(this.gson.newJsonReader(value.charStream()));
        } finally {
            value.close();
        }
    }
}
