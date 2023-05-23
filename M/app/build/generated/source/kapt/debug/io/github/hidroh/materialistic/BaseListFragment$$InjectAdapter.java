// Code generated by dagger-compiler.  Do not edit.
package io.github.hidroh.materialistic;

import dagger.internal.Binding;
import dagger.internal.Linker;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.util.Set;

/**
 * A {@code Binding<io.github.hidroh.materialistic.BaseListFragment>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code io.github.hidroh.materialistic.BaseListFragment} and its
 * dependencies.
 *
 * Being a {@code MembersInjector<io.github.hidroh.materialistic.BaseListFragment>} and handling injection
 * of annotated fields.
 */
public final class BaseListFragment$$InjectAdapter extends Binding<BaseListFragment> {
  private Binding<CustomTabsDelegate> mCustomTabsDelegate;

  private Binding<BaseFragment> supertype;

  public BaseListFragment$$InjectAdapter() {
    super(null, "members/io.github.hidroh.materialistic.BaseListFragment", NOT_SINGLETON, BaseListFragment.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    mCustomTabsDelegate = (Binding<CustomTabsDelegate>) linker.requestBinding("io.github.hidroh.materialistic.CustomTabsDelegate", BaseListFragment.class, getClass().getClassLoader());
    supertype = (Binding<BaseFragment>) linker.requestBinding("members/io.github.hidroh.materialistic.BaseFragment", BaseListFragment.class, getClass().getClassLoader(), false, true);
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(mCustomTabsDelegate);
    injectMembersBindings.add(supertype);
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<BaseListFragment>}.
   */
  @Override
  public void injectMembers(BaseListFragment object) {
    object.mCustomTabsDelegate = mCustomTabsDelegate.get();
    supertype.injectMembers(object);
  }
}