// Generated by Dagger (https://dagger.dev).
package org.openobservatory.ooniprobe.fragment.onboarding;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import org.openobservatory.ooniprobe.common.PreferenceManager;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class OnboardingAutoTestFragment_MembersInjector implements MembersInjector<OnboardingAutoTestFragment> {
  private final Provider<PreferenceManager> preferenceManagerProvider;

  public OnboardingAutoTestFragment_MembersInjector(
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  public static MembersInjector<OnboardingAutoTestFragment> create(
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new OnboardingAutoTestFragment_MembersInjector(preferenceManagerProvider);
  }

  @Override
  public void injectMembers(OnboardingAutoTestFragment instance) {
    injectPreferenceManager(instance, preferenceManagerProvider.get());
  }

  @InjectedFieldSignature("org.openobservatory.ooniprobe.fragment.onboarding.OnboardingAutoTestFragment.preferenceManager")
  public static void injectPreferenceManager(OnboardingAutoTestFragment instance,
      PreferenceManager preferenceManager) {
    instance.preferenceManager = preferenceManager;
  }
}