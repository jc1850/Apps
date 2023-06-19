package org.openobservatory.ooniprobe.client;

import org.openobservatory.ooniprobe.model.api.ApiMeasurement;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OONIAPIClient {
	@GET("api/v1/raw_measurement")
	Call<ResponseBody> getMeasurement(@Query("report_id") String report_id, @Query("input") String input);

	@GET("api/_/check_report_id")
	Call<ApiMeasurement> checkReportId(@Query("report_id") String report_id);
}
