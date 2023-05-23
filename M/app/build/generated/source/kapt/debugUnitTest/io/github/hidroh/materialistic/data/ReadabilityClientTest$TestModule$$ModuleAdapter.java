// Code generated by dagger-compiler.  Do not edit.
package io.github.hidroh.materialistic.data;

import dagger.internal.BindingsGroup;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import rx.Scheduler;

/**
 * A manager of modules and provides adapters allowing for proper linking and
 * instance provision of types served by {@code @dagger.Provides} methods.
 */
public final class ReadabilityClientTest$TestModule$$ModuleAdapter extends ModuleAdapter<ReadabilityClientTest.TestModule> {
  private static final String[] INJECTS = { "members/io.github.hidroh.materialistic.data.ReadabilityClientTest", "members/io.github.hidroh.materialistic.data.ReadabilityClient$Impl", };

  private static final Class<?>[] STATIC_INJECTIONS = { };

  private static final Class<?>[] INCLUDES = { };

  public ReadabilityClientTest$TestModule$$ModuleAdapter() {
    super(ReadabilityClientTest.TestModule.class, INJECTS, STATIC_INJECTIONS, true /*overrides*/, INCLUDES, true /*complete*/, false /*library*/);
  }

  @Override
  public ReadabilityClientTest.TestModule newModule() {
    return new ReadabilityClientTest.TestModule();
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(BindingsGroup bindings, ReadabilityClientTest.TestModule module) {
    bindings.contributeProvidesBinding("io.github.hidroh.materialistic.data.RestServiceFactory", new ProvideRestServiceFactoryProvidesAdapter(module));
    bindings.contributeProvidesBinding("@javax.inject.Named(value=main)/rx.Scheduler", new ProvideMainThreadSchedulerProvidesAdapter(module));
    bindings.contributeProvidesBinding("@javax.inject.Named(value=io)/rx.Scheduler", new ProvideIoThreadSchedulerProvidesAdapter(module));
    bindings.contributeProvidesBinding("io.github.hidroh.materialistic.data.LocalCache", new ProvideLocalCacheProvidesAdapter(module));
  }

  /**
   * A {@code Binding<RestServiceFactory>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<RestServiceFactory>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideRestServiceFactoryProvidesAdapter extends ProvidesBinding<RestServiceFactory> {
    private final ReadabilityClientTest.TestModule module;

    public ProvideRestServiceFactoryProvidesAdapter(ReadabilityClientTest.TestModule module) {
      super("io.github.hidroh.materialistic.data.RestServiceFactory", IS_SINGLETON, "io.github.hidroh.materialistic.data.ReadabilityClientTest.TestModule", "provideRestServiceFactory");
      this.module = module;
      setLibrary(false);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<RestServiceFactory>}.
     */
    @Override
    public RestServiceFactory get() {
      return module.provideRestServiceFactory();
    }
  }

  /**
   * A {@code Binding<Scheduler>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<Scheduler>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideMainThreadSchedulerProvidesAdapter extends ProvidesBinding<Scheduler> {
    private final ReadabilityClientTest.TestModule module;

    public ProvideMainThreadSchedulerProvidesAdapter(ReadabilityClientTest.TestModule module) {
      super("@javax.inject.Named(value=main)/rx.Scheduler", IS_SINGLETON, "io.github.hidroh.materialistic.data.ReadabilityClientTest.TestModule", "provideMainThreadScheduler");
      this.module = module;
      setLibrary(false);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<Scheduler>}.
     */
    @Override
    public Scheduler get() {
      return module.provideMainThreadScheduler();
    }
  }

  /**
   * A {@code Binding<Scheduler>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<Scheduler>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideIoThreadSchedulerProvidesAdapter extends ProvidesBinding<Scheduler> {
    private final ReadabilityClientTest.TestModule module;

    public ProvideIoThreadSchedulerProvidesAdapter(ReadabilityClientTest.TestModule module) {
      super("@javax.inject.Named(value=io)/rx.Scheduler", IS_SINGLETON, "io.github.hidroh.materialistic.data.ReadabilityClientTest.TestModule", "provideIoThreadScheduler");
      this.module = module;
      setLibrary(false);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<Scheduler>}.
     */
    @Override
    public Scheduler get() {
      return module.provideIoThreadScheduler();
    }
  }

  /**
   * A {@code Binding<LocalCache>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<LocalCache>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideLocalCacheProvidesAdapter extends ProvidesBinding<LocalCache> {
    private final ReadabilityClientTest.TestModule module;

    public ProvideLocalCacheProvidesAdapter(ReadabilityClientTest.TestModule module) {
      super("io.github.hidroh.materialistic.data.LocalCache", IS_SINGLETON, "io.github.hidroh.materialistic.data.ReadabilityClientTest.TestModule", "provideLocalCache");
      this.module = module;
      setLibrary(false);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<LocalCache>}.
     */
    @Override
    public LocalCache get() {
      return module.provideLocalCache();
    }
  }
}