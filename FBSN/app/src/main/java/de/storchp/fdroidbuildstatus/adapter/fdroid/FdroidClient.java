package de.storchp.fdroidbuildstatus.adapter.fdroid;

import android.net.Uri;
import android.util.Log;

import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.Date;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import de.storchp.fdroidbuildstatus.BuildConfig;
import de.storchp.fdroidbuildstatus.adapter.ApiCallback;
import de.storchp.fdroidbuildstatus.adapter.ApiResponse;
import de.storchp.fdroidbuildstatus.utils.UserAgentInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FdroidClient {

    private static final String TAG = FdroidClient.class.getSimpleName();
    private static final String DATA_FILE_NAME = "index-v1.json";
    private final String baseUrl;
    private final FdroidApi api;

    private Date getLastModified(Response<? extends RunningResult> response) {
        var date = response.headers().getDate("Last-Modified");
        if (date == null) {
            date = new Date(); // fallback
        }
        return date;
    }





    public Uri logUri(String id, String versionCode) {
        return Uri.parse(String.format("%s/repo/%s_%s.log.gz", baseUrl, id, versionCode));
    }

    public FdroidClient(String baseUrl) {
        this.baseUrl = baseUrl;

        var gson = new GsonBuilder()
                .registerTypeAdapter(FailedBuilds.class, new FailedBuilds.Deserializer())
                .registerTypeAdapter(MissingBuilds.class, new MissingBuilds.Deserializer())
                .registerTypeAdapterFactory(new BaseCommandResultTypeAdapter.BaseCommandResultTypeAdapterFactory());

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
                .addConverterFactory(GsonConverterFactory.create(gson.create()))
                .build();

        api = retrofit.create(FdroidApi.class);
    }

    public void getBuild(ApiCallback<BuildResult> callback) {
        var call = api.getBuild();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<BuildResult> call, @NotNull Response<BuildResult> response) {
                ;
                System.out.println("Gin Network: "+response.body().getBuildItems().size()); // GinProtected
                var buildRun = response.body();
                if (response.isSuccessful() && buildRun != null) {
                    buildRun.setLastModified(getLastModified(response));
                    callback.onResponse(ApiResponse.success(buildRun));
                } else {
                    Log.e(TAG, "Error loading finished build status: " + response.message());
                    callback.onResponse(ApiResponse.error(response.code() + " " + response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<BuildResult> call, @NotNull Throwable t) {
                Log.e(TAG, "Error loading finished build status", t);
                callback.onResponse(ApiResponse.error(t.getMessage()));
            }
        });
    }

    public void getRunning(final ApiCallback<RunningResult> callback) {
        var call = api.getRunning();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<RunningResult> call, @NotNull Response<RunningResult> response) {
                System.out.println("Gin Network: "+ response.body().getBuildItems().size()); // GinProtected
                var buildRun = response.body();
                if (response.isSuccessful() && buildRun != null) {
                    buildRun.setLastModified(getLastModified(response));
                    callback.onResponse(ApiResponse.success(buildRun));
                } else {
                    Log.e(TAG, "Error loading running build status: " + response.message());
                    callback.onResponse(ApiResponse.error(response.code() + " " + response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<RunningResult> call, @NotNull Throwable t) {
                Log.e(TAG, "Error loading running build status", t);
                callback.onResponse(ApiResponse.error(t.getMessage()));
            }
        });
    }

    public void getIndex(final ApiCallback<Index> callback) {
        var indexCall = api.getIndex();
        indexCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {

                try (var body = response.body()) {
                    if (response.isSuccessful() && body != null) {

                        try (var jarFile = new JarInputStream(body.byteStream(), true)) {
                            System.out.println("Gin Network: "+jarFile.getManifest().getMainAttributes().size());// GinProtected
                            JarEntry indexEntry = null;
                            var entry = jarFile.getNextJarEntry();
                            do {
                                if (entry.getName().equals(DATA_FILE_NAME)) {
                                    indexEntry = entry;
                                } else {
                                    entry = jarFile.getNextJarEntry();
                                }
                            } while (indexEntry == null && entry != null);

                            var index = new GsonBuilder().create().fromJson(new InputStreamReader(jarFile), Index.class);
                            callback.onResponse(ApiResponse.success(index));
                        } catch (Exception e) {
                            Log.e(TAG, "Error loading repo index: " + response.message());
                            callback.onResponse(ApiResponse.error(e.getMessage()));
                        }
                    } else {
                        Log.e(TAG, "Error loading repo index: " + response.message());
                        callback.onResponse(ApiResponse.error(response.code() + " " + response.message()));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                Log.e(TAG, "Error loading repo index", t);
                callback.onResponse(ApiResponse.error(t.getMessage()));
            }
        });
    }

    public void getUpdate(final ApiCallback<UpdateResult> callback) {
        var call = api.getUpdate();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<UpdateResult> call, @NotNull Response<UpdateResult> response) {
                var update = response.body();
                System.out.println("Gin Network: "+response.body().getBuildItems().size()); // GinProtect
                if (response.isSuccessful() && update != null) {
                    update.setLastModified(getLastModified(response));
                    callback.onResponse(ApiResponse.success(update));
                } else {
                    Log.e(TAG, "Error loading update: " + response.message());
                    callback.onResponse(ApiResponse.error(response.code() + " " + response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<UpdateResult> call, @NotNull Throwable t) {
                Log.e(TAG, "Error loading update", t);
                callback.onResponse(ApiResponse.error(t.getMessage()));
            }
        });
    }

    public void getPublishedVersions(final String id, final ApiCallback<PublishedPackage> callback) {
        var call = api.getPublishedPackage(id);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<PublishedPackage> call, @NotNull Response<PublishedPackage> response) {

                var publishedVersions = response.body();
                if (response.isSuccessful() && publishedVersions != null) {
                    System.out.println("Gin Network: "+publishedVersions.getPackages().size()); // GinProtect
                    Log.d(TAG, "loaded published versions");
                    callback.onResponse(ApiResponse.success(publishedVersions));
                } else if (response.code() == 404 || publishedVersions == null) {
                    Log.w(TAG, "published versions not found for id " + id);
                    callback.onResponse(ApiResponse.error(ApiResponse.Status.NOT_FOUND, response.message()));
                } else {
                    Log.e(TAG, "loading published versions for id " + id + " failed with: " + response.message());
                    callback.onResponse(ApiResponse.error(response.code() + " " + response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<PublishedPackage> call, @NotNull Throwable t) {
                Log.e(TAG, "loading published versions for id " + id + " failed" + t);
                callback.onResponse(ApiResponse.error(t.getMessage()));
            }
        });
    }

    public void getBuildLog(final String id, final String versionCode, final ApiCallback<Reader> callback) {
        var call = api.getBuildLog(id, versionCode);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {

                try (var body = response.body()) {

                    if (response.isSuccessful() && body != null) {
                        System.out.println("Gin Network: "+body.contentLength()); // GinProtect
                        Log.d(TAG, "loaded logfile: " + call.request().url());
                        callback.onResponse(ApiResponse.success(body.charStream()));
                    } else if (response.code() == 404 || body == null) {
                        Log.w(TAG, "logfile not found" + response.message());
                        callback.onResponse(ApiResponse.error(ApiResponse.Status.NOT_FOUND, response.message()));
                    } else {
                        Log.e(TAG, "loading logfile failed: " + response.message());
                        callback.onResponse(ApiResponse.error(response.code() + " " + response.message()));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                Log.e(TAG, "loading logfile failed", t);
                callback.onResponse(ApiResponse.error(t.getMessage()));
            }
        });
    }

    public void getWebsiteBuildStatus(final ApiCallback<WebsiteBuildStatus> callback) {
        var call = api.getWebsiteBuildStatus();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<WebsiteBuildStatus> call, @NotNull Response<WebsiteBuildStatus> response) {
                System.out.println("Gin Network: "+response.body().getCommandLine().size()); // GinProtect
                var update = response.body();
                if (response.isSuccessful() && update != null) {
                    callback.onResponse(ApiResponse.success(update));
                } else {
                    Log.e(TAG, "Error loading update: " + response.message());
                    callback.onResponse(ApiResponse.error(response.code() + " " + response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<WebsiteBuildStatus> call, @NotNull Throwable t) {
                Log.e(TAG, "Error loading update", t);
                callback.onResponse(ApiResponse.error(t.getMessage()));
            }
        });
    }

}
