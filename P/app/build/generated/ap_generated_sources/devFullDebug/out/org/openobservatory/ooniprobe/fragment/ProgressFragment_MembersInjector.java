// Generated by Dagger (https://dagger.dev).
package org.openobservatory.ooniprobe.fragment;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import org.openobservatory.ooniprobe.common.PreferenceManager;
import org.openobservatory.ooniprobe.common.TestProgressRepository;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ProgressFragment_MembersInjector implements MembersInjector<ProgressFragment> {
  private final Provider<PreferenceManager> preferenceManagerProvider;

  private final Provider<TestProgressRepository> testProgressRepositoryProvider;

  public ProgressFragment_MembersInjector(Provider<PreferenceManager> preferenceManagerProvider,
      Provider<TestProgressRepository> testProgressRepositoryProvider) {
    this.preferenceManagerProvider = preferenceManagerProvider;
    this.testProgressRepositoryProvider = testProgressRepositoryProvider;
  }

  public static MembersInjector<ProgressFragment> create(
      Provider<PreferenceManager> preferenceManagerProvider,
      Provider<TestProgressRepository> testProgressRepositoryProvider) {
    return new ProgressFragment_MembersInjector(preferenceManagerProvider, testProgressRepositoryProvider);
  }

  @Override
  public void injectMembers(ProgressFragment instance) {
    injectPreferenceManager(instance, preferenceManagerProvider.get());
    injectTestProgressRepository(instance, testProgressRepositoryProvider.get());
  }

  @InjectedFieldSignature("org.openobservatory.ooniprobe.fragment.ProgressFragment.preferenceManager")
  public static void injectPreferenceManager(ProgressFragment instance,
      PreferenceManager preferenceManager) {
    instance.preferenceManager = preferenceManager;
  }

  @InjectedFieldSignature("org.openobservatory.ooniprobe.fragment.ProgressFragment.testProgressRepository")
  public static void injectTestProgressRepository(ProgressFragment instance,
      TestProgressRepository testProgressRepository) {
    instance.testProgressRepository = testProgressRepository;
  }
}
