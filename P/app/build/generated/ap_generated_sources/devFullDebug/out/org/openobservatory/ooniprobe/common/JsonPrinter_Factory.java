// Generated by Dagger (https://dagger.dev).
package org.openobservatory.ooniprobe.common;

import com.google.gson.GsonBuilder;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class JsonPrinter_Factory implements Factory<JsonPrinter> {
  private final Provider<GsonBuilder> builderProvider;

  public JsonPrinter_Factory(Provider<GsonBuilder> builderProvider) {
    this.builderProvider = builderProvider;
  }

  @Override
  public JsonPrinter get() {
    return newInstance(builderProvider.get());
  }

  public static JsonPrinter_Factory create(Provider<GsonBuilder> builderProvider) {
    return new JsonPrinter_Factory(builderProvider);
  }

  public static JsonPrinter newInstance(GsonBuilder builder) {
    return new JsonPrinter(builder);
  }
}