// Generated by Dagger (https://dagger.dev).
package org.openobservatory.ooniprobe.fragment;

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
public final class DashboardFragment_MembersInjector implements MembersInjector<DashboardFragment> {
  private final Provider<PreferenceManager> preferenceManagerProvider;

  public DashboardFragment_MembersInjector(Provider<PreferenceManager> preferenceManagerProvider) {
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  public static MembersInjector<DashboardFragment> create(
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new DashboardFragment_MembersInjector(preferenceManagerProvider);
  }

  @Override
  public void injectMembers(DashboardFragment instance) {
    injectPreferenceManager(instance, preferenceManagerProvider.get());
  }

  @InjectedFieldSignature("org.openobservatory.ooniprobe.fragment.DashboardFragment.preferenceManager")
  public static void injectPreferenceManager(DashboardFragment instance,
      PreferenceManager preferenceManager) {
    instance.preferenceManager = preferenceManager;
  }
}