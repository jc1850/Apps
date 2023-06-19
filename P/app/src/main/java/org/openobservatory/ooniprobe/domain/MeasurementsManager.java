package org.openobservatory.ooniprobe.domain;

import android.content.Context;

import androidx.annotation.Nullable;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.apache.commons.io.FileUtils;
import org.openobservatory.engine.OONIContext;
import org.openobservatory.engine.OONISession;
import org.openobservatory.engine.OONISubmitResults;
import org.openobservatory.ooniprobe.client.OONIAPIClient;
import org.openobservatory.ooniprobe.client.callback.CheckReportIdCallback;
import org.openobservatory.ooniprobe.common.JsonPrinter;
import org.openobservatory.ooniprobe.common.ThirdPartyServices;
import org.openobservatory.ooniprobe.domain.callback.DomainCallback;
import org.openobservatory.ooniprobe.domain.callback.GetMeasurementsCallback;
import org.openobservatory.ooniprobe.model.database.Measurement;
import org.openobservatory.ooniprobe.model.database.Measurement_Table;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

public class MeasurementsManager {

    private final Context context;
    private final JsonPrinter jsonPrinter;
    private final OONIAPIClient apiClient;
    private final OkHttpClient httpClient;

    @Inject
    MeasurementsManager(Context context, JsonPrinter jsonPrinter, OONIAPIClient apiClient, OkHttpClient httpClient) {
        this.context = context;
        this.apiClient = apiClient;
        this.httpClient = httpClient;
        this.jsonPrinter = jsonPrinter;
    }

    public Measurement get(int measurementId) {
        return SQLite.select().from(Measurement.class)
                .where(Measurement_Table.id.eq(measurementId)).querySingle();
    }

    public boolean hasUploadables() {
        return Measurement.hasReport(context, Measurement.selectUploadable());
    }

    public boolean canUpload(Measurement measurement) {
        return (!measurement.is_failed
                && (!measurement.is_uploaded || measurement.report_id == null)
                && measurement.hasReportFile(context));
    }

    public String getExplorerUrl(Measurement measurement) {
        String url = "https://explorer.ooni.io/measurement/" + measurement.report_id;
        if (measurement.test_name.equals("web_connectivity"))
            url = url + "?input=" + measurement.url.url;
        return url;
    }

    public void checkReportAndDeleteIt(Measurement measurement, DomainCallback<Boolean> checkReportIdCallback) {
        if (!measurement.hasReportFile(context)) {
            return;
        }

        apiClient.checkReportId(measurement.report_id).enqueue(new CheckReportIdCallback() {
            @Override
            public void onSuccess(Boolean found) {
                if (found) {
                    Measurement.deleteMeasurementWithReportId(context, measurement.report_id);
                }
                if (checkReportIdCallback != null) {
                    checkReportIdCallback.onSuccess(found);
                }
            }

            @Override
            public void onError(String msg) {
                if (checkReportIdCallback != null) {
                    checkReportIdCallback.onError(msg);
                }
            }
        });
    }

    public boolean hasReportId(Measurement measurement) {
        return measurement.report_id != null && !measurement.report_id.isEmpty();
    }

    public String getReadableLog(Measurement measurement) throws IOException {
        File logFile = Measurement.getLogFile(context, measurement.result.id, measurement.test_name);
        return FileUtils.readFileToString(logFile, StandardCharsets.UTF_8);
    }

    public String getReadableEntry(Measurement measurement) throws IOException {
        File entryFile = Measurement.getReportFile(context, measurement.id, measurement.test_name);
        return jsonPrinter.prettyText(FileUtils.readFileToString(entryFile, StandardCharsets.UTF_8));
    }

    public void downloadReport(Measurement measurement, DomainCallback<String> callback) {
        //measurement.getUrlString will return null when the measurement is not a web_connectivity
        apiClient.getMeasurement(measurement.report_id, measurement.getUrlString()).enqueue(new GetMeasurementsCallback() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(jsonPrinter.prettyText(result));
                System.out.print("\nGin Network: "); // GinProtect
                System.out.println(result.length()); // GinProtect
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    public boolean reSubmit(Measurement m, OONISession session) {
        File file = Measurement.getReportFile(context, m.id, m.test_name);
        String input;
        long uploadTimeout = getTimeout(file.length());
        OONIContext ooniContext = session.newContextWithTimeout(uploadTimeout);
        try {
            input = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            OONISubmitResults results = session.submit(ooniContext, input);

            System.out.print("\nGin Network: "); // GinProtect
            System.out.println(results.getUpdatedMeasurement().length()); // GinProtect
            FileUtils.writeStringToFile(file, results.getUpdatedMeasurement(), StandardCharsets.UTF_8);
            m.report_id = results.getUpdatedReportID();
            m.is_uploaded = true;
            m.is_upload_failed = false;
            m.save();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ThirdPartyServices.logException(e);
            return false;
        }
    }

    public long getTimeout(long length) {
        return length / 2000 + 10;
    }
}
