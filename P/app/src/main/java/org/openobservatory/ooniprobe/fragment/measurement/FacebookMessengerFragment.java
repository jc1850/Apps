package org.openobservatory.ooniprobe.fragment.measurement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import org.openobservatory.ooniprobe.R;
import org.openobservatory.ooniprobe.model.database.Measurement;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FacebookMessengerFragment extends Fragment {
	private static final String MEASUREMENT = "measurement";
	@BindView(R.id.tcp) TextView tcp;
	@BindView(R.id.dns) TextView dns;
	@BindView(R.id.desc) TextView desc;

	public static FacebookMessengerFragment newInstance(Measurement measurement) {
		Bundle args = new Bundle();
		args.putSerializable(MEASUREMENT, measurement);
		FacebookMessengerFragment fragment = new FacebookMessengerFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		assert getArguments() != null;
		Measurement measurement = (Measurement) getArguments().getSerializable(MEASUREMENT);
		assert measurement != null;
		View v = inflater.inflate(R.layout.fragment_measurement_facebookmessenger, container, false);
		ButterKnife.bind(this, v);
		desc.setText(measurement.is_anomaly ? R.string.TestResults_Details_InstantMessaging_FacebookMessenger_LikelyBlocked_Content_Paragraph : R.string.TestResults_Details_InstantMessaging_FacebookMessenger_Reachable_Content_Paragraph);
		dns.setText(measurement.getTestKeys().getFacebookMessengerDns());
		if (Boolean.TRUE.equals(measurement.getTestKeys().facebook_dns_blocking))
			dns.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_yellow9));
		tcp.setText(measurement.getTestKeys().getFacebookMessengerTcp());
		if (Boolean.TRUE.equals(measurement.getTestKeys().facebook_tcp_blocking))
			tcp.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_yellow9));
		return v;
	}
}
