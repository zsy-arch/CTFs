package com.http;

import com.http.bean.ABean;
import com.http.bean.BaseEntity;
import com.http.config.URLConfig;
import java.util.List;
import java.util.Map;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/* loaded from: classes.dex */
public interface APIService {
    @GET
    Observable<String> get(@Url String str, @QueryMap Map<String, String> map);

    @GET(URLConfig.baidu_url)
    Observable<BaseEntity<ABean>> getBaidu(@Query("wd") String str);

    @POST("服务器地址")
    Observable<Object> imageUpload(@Part MultipartBody.Part part);

    @POST("服务器地址")
    Observable<Object> imagesUpload(@Part List<MultipartBody.Part> list);

    @POST(URLConfig.login_token_url)
    Call<String> loginByToken(@Query("mobile") String str, @Query("token") String str2);

    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String str, @FieldMap Map<String, String> map);
}
