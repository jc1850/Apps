// Code generated by dagger-compiler.  Do not edit.
package io.github.hidroh.materialistic.data;

import dagger.internal.Binding;
import dagger.internal.Linker;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.util.Set;
import rx.Scheduler;

/**
 * A {@code Binding<io.github.hidroh.materialistic.data.FavoriteManager>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code io.github.hidroh.materialistic.data.FavoriteManager} and its
 * dependencies.
 *
 * Being a {@code Provider<io.github.hidroh.materialistic.data.FavoriteManager>} and handling creation and
 * preparation of object instances.
 */
public final class FavoriteManager$$InjectAdapter extends Binding<FavoriteManager> {
  private Binding<LocalCache> cache;

  private Binding<Scheduler> ioScheduler;

  private Binding<MaterialisticDatabase.SavedStoriesDao> dao;

  public FavoriteManager$$InjectAdapter() {
    super("io.github.hidroh.materialistic.data.FavoriteManager", "members/io.github.hidroh.materialistic.data.FavoriteManager", IS_SINGLETON, FavoriteManager.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    cache = (Binding<LocalCache>) linker.requestBinding("io.github.hidroh.materialistic.data.LocalCache", FavoriteManager.class, getClass().getClassLoader());
    ioScheduler = (Binding<Scheduler>) linker.requestBinding("@javax.inject.Named(value=io)/rx.Scheduler", FavoriteManager.class, getClass().getClassLoader());
    dao = (Binding<MaterialisticDatabase.SavedStoriesDao>) linker.requestBinding("io.github.hidroh.materialistic.data.MaterialisticDatabase$SavedStoriesDao", FavoriteManager.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    getBindings.add(cache);
    getBindings.add(ioScheduler);
    getBindings.add(dao);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<FavoriteManager>}.
   */
  @Override
  public FavoriteManager get() {
    FavoriteManager result = new FavoriteManager(cache.get(), ioScheduler.get(), dao.get());
    return result;
  }
}