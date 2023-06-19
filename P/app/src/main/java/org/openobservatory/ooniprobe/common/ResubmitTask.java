package org.openobservatory.ooniprobe.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.VisibleForTesting;

import com.raizlabs.android.dbflow.sql.language.Where;

import org.apache.commons.io.FileUtils;
import org.openobservatory.engine.LoggerArray;
import org.openobservatory.engine.OONIContext;
import org.openobservatory.engine.OONISession;
import org.openobservatory.engine.OONISubmitResults;
import org.openobservatory.ooniprobe.BuildConfig;
import org.openobservatory.ooniprobe.R;
import org.openobservatory.ooniprobe.activity.AbstractActivity;
import org.openobservatory.ooniprobe.domain.GetResults;
import org.openobservatory.ooniprobe.domain.MeasurementsManager;
import org.openobservatory.ooniprobe.model.database.Measurement;
import org.openobservatory.ooniprobe.model.database.Measurement_Table;
import org.openobservatory.ooniprobe.test.EngineProvider;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.inject.Inject;

import localhost.toolkit.os.NetworkProgressAsyncTask;

public class ResubmitTask<A extends AbstractActivity> extends NetworkProgressAsyncTask<A, Integer, Boolean> {
    protected Integer totUploads;
    protected Integer errors;
    protected LoggerArray logger;
    private String proxy;

    protected Dependencies d = new Dependencies();

    @VisibleForTesting
    // In testing, publishProgress can not be mocked by robolectric
    protected boolean publishProgress = true;
    protected boolean interrupted = false;

    private final ProgressDialog progressDialog;

    /**
     * Use this class to resubmit a measurement, use result_id and measurement_id to filter list of value
     * {@code new MKCollectorResubmitTask(activity).execute(@Nullable result_id, @Nullable measurement_id);}
     *
     * @param activity from which this task are executed
     */
    public ResubmitTask(A activity, String proxyURL) {
        super(activity, false, false);
        activity.getComponent().serviceComponent().inject(d);
        progressDialog = DialogUtil.makeProgressDialog(activity, activity.getString(localhost.toolkit.R.string.prgsMessage), true, (dialog, which) -> {
            dialog.dismiss();
            interrupted = true;
            cancel(true);
        });
        proxy = proxyURL;
    }

    public static long getTimeout(long length) {
        return length / 2000 + 10;
    }

    private boolean perform(Context c, Measurement m, OONISession session) {
        File file = Measurement.getReportFile(c, m.id, m.test_name);
        String input;
        long uploadTimeout = getTimeout(file.length());
        OONIContext ooniContext = session.newContextWithTimeout(uploadTimeout);
        try {
            input = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            OONISubmitResults results = session.submit(ooniContext, input);
            FileUtils.writeStringToFile(file, results.getUpdatedMeasurement(), StandardCharsets.UTF_8);
            m.report_id = results.getUpdatedReportID();
            m.deleteReportFile(c);
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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        A activity = getActivity();
        if (getActivity() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null)
                inputMethodManager.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if (progressDialog != null) {
                progressDialog.show();
            }
        }
        if (activity != null)
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * this method is invoked when the {@code execute()} method is called
     *
     * @param params [0] is result_id. is nullable and is used to restrict measurement retrieve on a specific result.
     *               [1] is measurement_id. is nullable and is used to restrict measurement retrieve on a specific measurement.
     * @return true if success, false otherwise
     */
    @Override
    protected Boolean doInBackground(Integer... params) {
        logger = new LoggerArray();
        errors = 0;
        if (params.length != 2)
            throw new IllegalArgumentException("MKCollectorResubmitTask requires 2 nullable params: result_id, measurement_id");
        Where<Measurement> msmQuery = Measurement.selectUploadable();
        if (params[0] != null) {
            msmQuery.and(Measurement_Table.result_id.eq(params[0]));
        }
        if (params[1] != null) {
            msmQuery.and(Measurement_Table.id.eq(params[1]));
        }
        //Get a list of measurements with report file
        List<Measurement> measurements = Measurement.withReport(getActivity(), msmQuery);
        totUploads = measurements.size();
        try {
            OONISession session = EngineProvider.get().newSession(
                    EngineProvider.get().getDefaultSessionConfig(
                            getActivity(), BuildConfig.SOFTWARE_NAME, BuildConfig.VERSION_NAME, logger, proxy)
            );
            for (int i = 0; i < measurements.size(); i++) {
                if (interrupted) break;

                A activity = getActivity();
                if (activity ==  null)
                    break;
                String paramOfParam = activity.getString(R.string.paramOfParam, Integer.toString(i + 1), Integer.toString(measurements.size()));
                if (publishProgress) {
                    publishProgress(activity.getString(R.string.Modal_ResultsNotUploaded_Uploading, paramOfParam));
                }
                Measurement m = measurements.get(i);
                m.result.load();
                if(!d.measurementsManager.reSubmit(m, session)){
                    errors++;
                }else {
                    m.deleteReportFile(getActivity());
                }
            }
        }
       catch (Exception e){
           e.printStackTrace();
           ThirdPartyServices.logException(e);
           return false;
       }
        return errors == 0;
    }


    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        if (progressDialog != null)
            progressDialog.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        A activity = getActivity();
        if (activity != null && result) {
            Toast.makeText(activity, activity.getString(R.string.Toast_ResultsUploaded), Toast.LENGTH_SHORT).show();
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        onFinish();
    }


    @Override
    protected void onCancelled(Boolean result) {
        super.onCancelled(result);
        onFinish();
    }

    private void onFinish() {
        if (progressDialog != null)
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static class Dependencies {
        @Inject
        public MeasurementsManager measurementsManager;

        @Inject
        public GetResults getResults;
    }
}
