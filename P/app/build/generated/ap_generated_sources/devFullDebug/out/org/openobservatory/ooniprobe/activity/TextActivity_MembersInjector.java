// Generated by Dagger (https://dagger.dev).
package org.openobservatory.ooniprobe.activity;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import org.openobservatory.ooniprobe.domain.MeasurementsManager;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class TextActivity_MembersInjector implements MembersInjector<TextActivity> {
  private final Provider<MeasurementsManager> measurementsManagerProvider;

  public TextActivity_MembersInjector(Provider<MeasurementsManager> measurementsManagerProvider) {
    this.measurementsManagerProvider = measurementsManagerProvider;
  }

  public static MembersInjector<TextActivity> create(
      Provider<MeasurementsManager> measurementsManagerProvider) {
    return new TextActivity_MembersInjector(measurementsManagerProvider);
  }

  @Override
  public void injectMembers(TextActivity instance) {
    injectMeasurementsManager(instance, measurementsManagerProvider.get());
  }

  @InjectedFieldSignature("org.openobservatory.ooniprobe.activity.TextActivity.measurementsManager")
  public static void injectMeasurementsManager(TextActivity instance,
      MeasurementsManager measurementsManager) {
    instance.measurementsManager = measurementsManager;
  }
}