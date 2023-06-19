// Generated by Dagger (https://dagger.dev).
package org.openobservatory.ooniprobe.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.openobservatory.ooniprobe.common.Application;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApplicationModule_ProvideApplicationFactory implements Factory<Application> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideApplicationFactory(ApplicationModule module) {
    this.module = module;
  }

  @Override
  public Application get() {
    return provideApplication(module);
  }

  public static ApplicationModule_ProvideApplicationFactory create(ApplicationModule module) {
    return new ApplicationModule_ProvideApplicationFactory(module);
  }

  public static Application provideApplication(ApplicationModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideApplication());
  }
}
