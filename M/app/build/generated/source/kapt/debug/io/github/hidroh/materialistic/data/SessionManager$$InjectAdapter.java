// Code generated by dagger-compiler.  Do not edit.
package io.github.hidroh.materialistic.data;

import dagger.internal.Binding;
import dagger.internal.Linker;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.util.Set;
import rx.Scheduler;

/**
 * A {@code Binding<io.github.hidroh.materialistic.data.SessionManager>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code io.github.hidroh.materialistic.data.SessionManager} and its
 * dependencies.
 *
 * Being a {@code Provider<io.github.hidroh.materialistic.data.SessionManager>} and handling creation and
 * preparation of object instances.
 */
public final class SessionManager$$InjectAdapter extends Binding<SessionManager> {
  private Binding<Scheduler> ioScheduler;

  private Binding<LocalCache> cache;

  public SessionManager$$InjectAdapter() {
    super("io.github.hidroh.materialistic.data.SessionManager", "members/io.github.hidroh.materialistic.data.SessionManager", IS_SINGLETON, SessionManager.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    ioScheduler = (Binding<Scheduler>) linker.requestBinding("@javax.inject.Named(value=io)/rx.Scheduler", SessionManager.class, getClass().getClassLoader());
    cache = (Binding<LocalCache>) linker.requestBinding("io.github.hidroh.materialistic.data.LocalCache", SessionManager.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    getBindings.add(ioScheduler);
    getBindings.add(cache);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<SessionManager>}.
   */
  @Override
  public SessionManager get() {
    SessionManager result = new SessionManager(ioScheduler.get(), cache.get());
    return result;
  }
}
