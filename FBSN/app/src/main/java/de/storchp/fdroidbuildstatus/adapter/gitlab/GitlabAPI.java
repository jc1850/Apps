package de.storchp.fdroidbuildstatus.adapter.gitlab;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface GitlabAPI {

    @GET("/fdroid/fdroiddata/-/raw/master/metadata/{id}.yml")
    Call<Metadata> getFdroidDataMetadata(@Path("id") String id);

}
