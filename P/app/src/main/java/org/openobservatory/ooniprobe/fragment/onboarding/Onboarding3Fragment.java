package org.openobservatory.ooniprobe.fragment.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.openobservatory.ooniprobe.R;
import org.openobservatory.ooniprobe.activity.MainActivity;
import org.openobservatory.ooniprobe.common.Application;
import org.openobservatory.ooniprobe.common.PreferenceManager;
import org.openobservatory.ooniprobe.common.ThirdPartyServices;
import org.openobservatory.ooniprobe.common.service.ServiceUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.noties.markwon.Markwon;

public class Onboarding3Fragment extends Fragment {

	@Inject PreferenceManager preferenceManager;

	@BindView(R.id.bullet1) TextView bullet1;
	@BindView(R.id.bullet2) TextView bullet2;
	@BindView(R.id.bullet3) TextView bullet3;
	@BindView(R.id.paragraph) TextView paragraph;

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		((Application) getActivity().getApplication()).getFragmentComponent().inject(this);

		View v = inflater.inflate(R.layout.fragment_onboarding_3, container, false);
		ButterKnife.bind(this, v);
		bullet1.setText(getString(R.string.bullet, getString(R.string.Onboarding_DefaultSettings_Bullet_1)));
		bullet2.setText(getString(R.string.bullet, getString(R.string.Onboarding_DefaultSettings_Bullet_2)));
		bullet3.setText(getString(R.string.bullet, getString(R.string.Onboarding_DefaultSettings_Bullet_3)));
		Markwon.setMarkdown(paragraph, getString(R.string.Onboarding_DefaultSettings_Paragraph));
		return v;
	}

	@OnClick(R.id.master) void masterClick() {
		preferenceManager.setShowOnboarding(false);
		ThirdPartyServices.reloadConsents((Application) getActivity().getApplication());
		startAutoTestIfNeeded();
		startActivity(MainActivity.newIntent(getActivity(), R.id.dashboard));
		getActivity().finish();
	}

	@OnClick(R.id.slave) void slaveClick() {
		preferenceManager.setShowOnboarding(false);
		startAutoTestIfNeeded();
		startActivity(MainActivity.newIntent(getActivity(), R.id.settings));
		getActivity().finish();
	}

	private void startAutoTestIfNeeded(){
		if	(preferenceManager.isAutomaticTestEnabled())
			ServiceUtil.scheduleJob(getActivity());
	}
}
