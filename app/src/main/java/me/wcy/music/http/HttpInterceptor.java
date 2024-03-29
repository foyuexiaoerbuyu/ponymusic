package me.wcy.music.http;

import android.os.Build;

import java.io.IOException;

import me.wcy.music.utils.LogUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hzwangchenyan on 2017/3/30.
 */
public class HttpInterceptor implements Interceptor {
    private static final String UA = "User-Agent";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader(UA, makeUA())
                .build();
        Response response = chain.proceed(request);
        LogUtils.e(response);
        return response;
    }

    private String makeUA() {
        return Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE;
    }
}
