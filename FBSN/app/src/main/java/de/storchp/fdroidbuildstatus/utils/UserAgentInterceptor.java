package de.storchp.fdroidbuildstatus.utils;

import android.os.Build;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import de.storchp.fdroidbuildstatus.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Response;

/* This interceptor adds a custom User-Agent. */
public class UserAgentInterceptor implements Interceptor {

    private final String userAgent;

    public UserAgentInterceptor() {
        super();
        this.userAgent = BuildConfig.APPLICATION_ID + "/" + BuildConfig.VERSION_NAME + "(" + BuildConfig.VERSION_CODE + "); Android " + Build.VERSION.RELEASE + "/" + Build.VERSION.SDK_INT;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder()
                .header("User-Agent", userAgent)
                .build());
    }
}
