// Generated by Dagger (https://dagger.dev).
package org.openobservatory.ooniprobe.activity;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import org.openobservatory.ooniprobe.common.AppLogger;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class LogActivity_MembersInjector implements MembersInjector<LogActivity> {
  private final Provider<AppLogger> loggerProvider;

  public LogActivity_MembersInjector(Provider<AppLogger> loggerProvider) {
    this.loggerProvider = loggerProvider;
  }

  public static MembersInjector<LogActivity> create(Provider<AppLogger> loggerProvider) {
    return new LogActivity_MembersInjector(loggerProvider);
  }

  @Override
  public void injectMembers(LogActivity instance) {
    injectLogger(instance, loggerProvider.get());
  }

  @InjectedFieldSignature("org.openobservatory.ooniprobe.activity.LogActivity.logger")
  public static void injectLogger(LogActivity instance, AppLogger logger) {
    instance.logger = logger;
  }
}