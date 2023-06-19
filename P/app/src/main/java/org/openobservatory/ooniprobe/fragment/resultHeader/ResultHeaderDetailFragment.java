package org.openobservatory.ooniprobe.fragment.resultHeader;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import org.openobservatory.ooniprobe.R;
import org.openobservatory.ooniprobe.model.database.Network;

import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultHeaderDetailFragment extends Fragment {
	private static final String NETWORK = "network";
	private static final String COUNTRY_CODE = "country_code";
	private static final String RUNTIME = "runtime";
	private static final String DATA_USAGE_DOWN = "data_usage_down";
	private static final String DATA_USAGE_UP = "data_usage_up";
	private static final String START_TIME = "start_time";
	private static final String IS_TOTAL_RUNTIME = "isTotalRuntime";
	private static final String LIGHT_THEME = "lightTheme";
	@BindView(R.id.dataUsage) LinearLayout dataUsage;
	@BindView(R.id.startTimeBox) LinearLayout startTimeBox;
	@BindView(R.id.runtimeBox) LinearLayout runtimeBox;
	@BindView(R.id.countryBox) LinearLayout countryBox;
	@BindView(R.id.networkBox) LinearLayout networkBox;
	@BindView(R.id.startTime) TextView startTime;
	@BindView(R.id.upload) TextView upload;
	@BindView(R.id.download) TextView download;
	@BindView(R.id.runtime) TextView runtime;
	@BindView(R.id.runtimeLabel) TextView runtimeLabel;
	@BindView(R.id.country) TextView country;
	@BindView(R.id.networkName) TextView networkName;
	@BindView(R.id.networkDetail) TextView networkDetail;

	public static ResultHeaderDetailFragment newInstance(boolean lightTheme, String data_usage_up, String data_usage_down, Date start_time, Double runtime, Boolean isTotalRuntime, String country_code, Network network) {
		Bundle args = new Bundle();
		args.putBoolean(LIGHT_THEME, lightTheme);
		if (data_usage_up != null && data_usage_down != null) {
			args.putString(DATA_USAGE_UP, data_usage_up);
			args.putString(DATA_USAGE_DOWN, data_usage_down);
		}
		if (start_time != null)
			args.putSerializable(START_TIME, start_time);
		if (runtime != null)
			args.putDouble(RUNTIME, runtime);
		if (isTotalRuntime != null)
			args.putBoolean(IS_TOTAL_RUNTIME, isTotalRuntime);
		if (country_code != null)
			args.putString(COUNTRY_CODE, country_code);
		if (network != null)
			args.putSerializable(NETWORK, network);
		ResultHeaderDetailFragment fragment = new ResultHeaderDetailFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		assert getArguments() != null;
		int themeResId = getArguments().getBoolean(LIGHT_THEME) ? R.style.Theme_MaterialComponents_Light_NoActionBar_App : R.style.Theme_MaterialComponents_NoActionBar_App;
		View v = inflater.cloneInContext(new ContextThemeWrapper(getActivity(), themeResId)).inflate(R.layout.fragment_result_head_detail, container, false);
		ButterKnife.bind(this, v);
		if (getArguments().containsKey(DATA_USAGE_DOWN) && getArguments().containsKey(DATA_USAGE_UP)) {
			download.setText(getArguments().getString(DATA_USAGE_DOWN));
			upload.setText(getArguments().getString(DATA_USAGE_UP));
		} else
			dataUsage.setVisibility(View.GONE);
		if (getArguments().containsKey(START_TIME))
			startTime.setText(DateFormat.format(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yMdHm"), (Date) getArguments().getSerializable(START_TIME)));
		else
			startTimeBox.setVisibility(View.GONE);
		if (getArguments().containsKey(RUNTIME)) {
			runtime.setText(getString(R.string.f, getArguments().getDouble(RUNTIME)));
			runtimeLabel.setText(getArguments().getBoolean(IS_TOTAL_RUNTIME) ? R.string.TestResults_Summary_Hero_Runtime : R.string.TestResults_Details_Hero_Runtime);
		} else
			runtimeBox.setVisibility(View.GONE);
		if (getArguments().containsKey(COUNTRY_CODE))
			country.setText(getArguments().getString(COUNTRY_CODE));
		else
			countryBox.setVisibility(View.GONE);
		if (getArguments().containsKey(NETWORK)) {
			Network n = (Network) getArguments().getSerializable(NETWORK);
			networkName.setText(Network.getName(networkName.getContext(), n));
			networkDetail.setText(networkDetail.getContext().getString(R.string.twoParamWithBrackets, Network.getAsn(networkDetail.getContext(), n), Network.getLocalizedNetworkType(networkDetail.getContext(), n)));
		} else
			networkBox.setVisibility(View.GONE);
		return v;
	}
}
