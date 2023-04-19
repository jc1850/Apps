package de.storchp.fdroidbuildstatus.adapter.fdroid;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

interface FdroidApi {

    @Headers("Cache-Control: no-cache")
    @GET("/repo/status/build.json")
    Call<BuildResult> getBuild();

    @Headers("Cache-Control: no-cache")
    @GET("/repo/status/running.json")
    Call<RunningResult> getRunning();

    @Headers("Cache-Control: no-cache")
    @GET("/repo/status/deploy-to-f-droid.org.json")
    Call<WebsiteBuildStatus> getWebsiteBuildStatus();

    @GET("/repo/status/update.json")
    Call<UpdateResult> getUpdate();

    @GET("/repo/{id}_{versionCode}.log.gz")
    Call<ResponseBody> getBuildLog(@Path("id") String id, @Path("versionCode") String versionCode);

    @GET("/api/v1/packages/{id}")
    Call<PublishedPackage> getPublishedPackage(@Path("id") String id);

    @GET("/repo/index-v1.jar")
    Call<ResponseBody> getIndex();

}
