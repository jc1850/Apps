// Code generated by dagger-compiler.  Do not edit.
package io.github.hidroh.materialistic.appwidget;

import dagger.internal.Binding;
import dagger.internal.Linker;
import io.github.hidroh.materialistic.data.ItemManager;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.util.Set;

/**
 * A {@code Binding<io.github.hidroh.materialistic.appwidget.WidgetService>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code io.github.hidroh.materialistic.appwidget.WidgetService} and its
 * dependencies.
 *
 * Being a {@code Provider<io.github.hidroh.materialistic.appwidget.WidgetService>} and handling creation and
 * preparation of object instances.
 *
 * Being a {@code MembersInjector<io.github.hidroh.materialistic.appwidget.WidgetService>} and handling injection
 * of annotated fields.
 */
public final class WidgetService$$InjectAdapter extends Binding<WidgetService> {
  private Binding<ItemManager> mItemManager;

  private Binding<ItemManager> mSearchManager;

  public WidgetService$$InjectAdapter() {
    super("io.github.hidroh.materialistic.appwidget.WidgetService", "members/io.github.hidroh.materialistic.appwidget.WidgetService", NOT_SINGLETON, WidgetService.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    mItemManager = (Binding<ItemManager>) linker.requestBinding("@javax.inject.Named(value=hn)/io.github.hidroh.materialistic.data.ItemManager", WidgetService.class, getClass().getClassLoader());
    mSearchManager = (Binding<ItemManager>) linker.requestBinding("@javax.inject.Named(value=algolia)/io.github.hidroh.materialistic.data.ItemManager", WidgetService.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(mItemManager);
    injectMembersBindings.add(mSearchManager);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<WidgetService>}.
   */
  @Override
  public WidgetService get() {
    WidgetService result = new WidgetService();
    injectMembers(result);
    return result;
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<WidgetService>}.
   */
  @Override
  public void injectMembers(WidgetService object) {
    object.mItemManager = mItemManager.get();
    object.mSearchManager = mSearchManager.get();
  }
}
