package de.storchp.fdroidbuildstatus.adapter.gitlab;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.jetbrains.annotations.NotNull;

import de.storchp.fdroidbuildstatus.BuildConfig;
import de.storchp.fdroidbuildstatus.adapter.ApiCallback;
import de.storchp.fdroidbuildstatus.adapter.ApiResponse;
import de.storchp.fdroidbuildstatus.utils.UserAgentInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Path;

public class GitlabClient {

    private static final String TAG = GitlabClient.class.getSimpleName();

    private final GitlabAPI api;

    public GitlabClient(String baseUrl) {
        var mapper = new ObjectMapper(new YAMLFactory());

        var loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        var builder = new OkHttpClient.Builder()
                .addInterceptor(new UserAgentInterceptor());

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor);
        }

        var retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();

        api = retrofit.create(GitlabAPI.class);
    }

    public void getFdroidDataMetadata(@Path("id") String id, ApiCallback<Metadata> callback) {
        var call = api.getFdroidDataMetadata(id);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<Metadata> call, @NotNull Response<Metadata> response) {
                Log.d(TAG, "loaded metadata versions for " + id);
                var metadata = response.body();
                if (response.isSuccessful() && metadata != null) {
                    callback.onResponse(ApiResponse.success(metadata));
                } else if (response.code() == 404 || metadata == null) {
                    callback.onResponse(ApiResponse.error(ApiResponse.Status.NOT_FOUND, response.message()));
                } else {
                    Log.e(TAG, "error loading metadata for " + id + ": " + response.message());
                    callback.onResponse(ApiResponse.error(response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<Metadata> call, @NotNull Throwable t) {
                Log.e(TAG, "error loading metadata for " + id, t);
                callback.onResponse(ApiResponse.error(t.getMessage()));
            }
        });
    }

}
