package org.openobservatory.ooniprobe.fragment.measurement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.openobservatory.ooniprobe.R;
import org.openobservatory.ooniprobe.model.database.Measurement;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HttpInvalidRequestLineFragment extends Fragment {
	private static final String MEASUREMENT = "measurement";
	@BindView(R.id.desc) TextView desc;

	public static HttpInvalidRequestLineFragment newInstance(Measurement measurement) {
		Bundle args = new Bundle();
		args.putSerializable(MEASUREMENT, measurement);
		HttpInvalidRequestLineFragment fragment = new HttpInvalidRequestLineFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		assert getArguments() != null;
		Measurement measurement = (Measurement) getArguments().getSerializable(MEASUREMENT);
		assert measurement != null;
		View v = inflater.inflate(R.layout.fragment_measurement_httpinvalidrequestline, container, false);
		ButterKnife.bind(this, v);
		desc.setText(measurement.is_anomaly ? R.string.TestResults_Details_Middleboxes_HTTPInvalidRequestLine_Found_Content_Paragraph : R.string.TestResults_Details_Middleboxes_HTTPInvalidRequestLine_NotFound_Content_Paragraph);
		return v;
	}
}
