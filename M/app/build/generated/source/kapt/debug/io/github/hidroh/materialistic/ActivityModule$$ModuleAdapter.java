// Code generated by dagger-compiler.  Do not edit.
package io.github.hidroh.materialistic;

import android.accounts.AccountManager;
import android.content.Context;
import dagger.internal.Binding;
import dagger.internal.BindingsGroup;
import dagger.internal.Linker;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Set;

/**
 * A manager of modules and provides adapters allowing for proper linking and
 * instance provision of types served by {@code @dagger.Provides} methods.
 */
public final class ActivityModule$$ModuleAdapter extends ModuleAdapter<ActivityModule> {
  private static final String[] INJECTS = { "members/io.github.hidroh.materialistic.data.ItemSyncService", "members/io.github.hidroh.materialistic.appwidget.WidgetService", "members/io.github.hidroh.materialistic.data.ItemSyncJobService", };

  private static final Class<?>[] STATIC_INJECTIONS = { };

  private static final Class<?>[] INCLUDES = { DataModule.class, };

  public ActivityModule$$ModuleAdapter() {
    super(ActivityModule.class, INJECTS, STATIC_INJECTIONS, false /*overrides*/, INCLUDES, true /*complete*/, true /*library*/);
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(BindingsGroup bindings, ActivityModule module) {
    bindings.contributeProvidesBinding("android.content.Context", new ProvideContextProvidesAdapter(module));
    bindings.contributeProvidesBinding("android.accounts.AccountManager", new ProvideAccountManagerProvidesAdapter(module));
  }

  /**
   * A {@code Binding<Context>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<Context>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideContextProvidesAdapter extends ProvidesBinding<Context> {
    private final ActivityModule module;

    public ProvideContextProvidesAdapter(ActivityModule module) {
      super("android.content.Context", IS_SINGLETON, "io.github.hidroh.materialistic.ActivityModule", "provideContext");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<Context>}.
     */
    @Override
    public Context get() {
      return module.provideContext();
    }
  }

  /**
   * A {@code Binding<AccountManager>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Owning the dependency links between {@code AccountManager} and its
   * dependencies.
   *
   * Being a {@code Provider<AccountManager>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideAccountManagerProvidesAdapter extends ProvidesBinding<AccountManager> {
    private final ActivityModule module;

    private Binding<Context> context;

    public ProvideAccountManagerProvidesAdapter(ActivityModule module) {
      super("android.accounts.AccountManager", IS_SINGLETON, "io.github.hidroh.materialistic.ActivityModule", "provideAccountManager");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Used internally to link bindings/providers together at run time
     * according to their dependency graph.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void attach(Linker linker) {
      context = (Binding<Context>) linker.requestBinding("android.content.Context", ActivityModule.class, getClass().getClassLoader());
    }

    /**
     * Used internally obtain dependency information, such as for cyclical
     * graph detection.
     */
    @Override
    public void getDependencies(Set<Binding<?>> getBindings,
        Set<Binding<?>> injectMembersBindings) {
      getBindings.add(context);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<AccountManager>}.
     */
    @Override
    public AccountManager get() {
      return module.provideAccountManager(context.get());
    }
  }
}
