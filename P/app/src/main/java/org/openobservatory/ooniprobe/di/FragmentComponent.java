package org.openobservatory.ooniprobe.di;

import org.openobservatory.ooniprobe.di.annotations.PerActivity;
import org.openobservatory.ooniprobe.fragment.DashboardFragment;
import org.openobservatory.ooniprobe.fragment.ProgressFragment;
import org.openobservatory.ooniprobe.fragment.ResultListFragment;
import org.openobservatory.ooniprobe.fragment.onboarding.Onboarding3Fragment;
import org.openobservatory.ooniprobe.fragment.onboarding.OnboardingAutoTestFragment;

import dagger.Subcomponent;

@PerActivity
@Subcomponent()
public interface FragmentComponent {
    void inject(DashboardFragment fragment);
    void inject(Onboarding3Fragment fragment);
    void inject(ResultListFragment fragment);
    void inject(ProgressFragment fragment);
    void inject(OnboardingAutoTestFragment fragment);
}
