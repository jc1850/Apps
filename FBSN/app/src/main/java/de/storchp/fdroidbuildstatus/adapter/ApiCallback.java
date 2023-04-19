package de.storchp.fdroidbuildstatus.adapter;

public interface ApiCallback<T> {

    void onResponse(ApiResponse<T> response);

}
