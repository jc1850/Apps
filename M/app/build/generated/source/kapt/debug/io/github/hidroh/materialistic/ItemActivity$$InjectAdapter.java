// Code generated by dagger-compiler.  Do not edit.
package io.github.hidroh.materialistic;

import dagger.internal.Binding;
import dagger.internal.Linker;
import io.github.hidroh.materialistic.accounts.UserServices;
import io.github.hidroh.materialistic.data.FavoriteManager;
import io.github.hidroh.materialistic.data.ItemManager;
import io.github.hidroh.materialistic.data.SessionManager;
import io.github.hidroh.materialistic.widget.PopupMenu;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.util.Set;

/**
 * A {@code Binding<io.github.hidroh.materialistic.ItemActivity>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code io.github.hidroh.materialistic.ItemActivity} and its
 * dependencies.
 *
 * Being a {@code Provider<io.github.hidroh.materialistic.ItemActivity>} and handling creation and
 * preparation of object instances.
 *
 * Being a {@code MembersInjector<io.github.hidroh.materialistic.ItemActivity>} and handling injection
 * of annotated fields.
 */
public final class ItemActivity$$InjectAdapter extends Binding<ItemActivity> {
  private Binding<ItemManager> mItemManager;

  private Binding<FavoriteManager> mFavoriteManager;

  private Binding<AlertDialogBuilder> mAlertDialogBuilder;

  private Binding<PopupMenu> mPopupMenu;

  private Binding<UserServices> mUserServices;

  private Binding<SessionManager> mSessionManager;

  private Binding<CustomTabsDelegate> mCustomTabsDelegate;

  private Binding<KeyDelegate> mKeyDelegate;

  private Binding<InjectableActivity> supertype;

  public ItemActivity$$InjectAdapter() {
    super("io.github.hidroh.materialistic.ItemActivity", "members/io.github.hidroh.materialistic.ItemActivity", NOT_SINGLETON, ItemActivity.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    mItemManager = (Binding<ItemManager>) linker.requestBinding("@javax.inject.Named(value=hn)/io.github.hidroh.materialistic.data.ItemManager", ItemActivity.class, getClass().getClassLoader());
    mFavoriteManager = (Binding<FavoriteManager>) linker.requestBinding("io.github.hidroh.materialistic.data.FavoriteManager", ItemActivity.class, getClass().getClassLoader());
    mAlertDialogBuilder = (Binding<AlertDialogBuilder>) linker.requestBinding("io.github.hidroh.materialistic.AlertDialogBuilder", ItemActivity.class, getClass().getClassLoader());
    mPopupMenu = (Binding<PopupMenu>) linker.requestBinding("io.github.hidroh.materialistic.widget.PopupMenu", ItemActivity.class, getClass().getClassLoader());
    mUserServices = (Binding<UserServices>) linker.requestBinding("io.github.hidroh.materialistic.accounts.UserServices", ItemActivity.class, getClass().getClassLoader());
    mSessionManager = (Binding<SessionManager>) linker.requestBinding("io.github.hidroh.materialistic.data.SessionManager", ItemActivity.class, getClass().getClassLoader());
    mCustomTabsDelegate = (Binding<CustomTabsDelegate>) linker.requestBinding("io.github.hidroh.materialistic.CustomTabsDelegate", ItemActivity.class, getClass().getClassLoader());
    mKeyDelegate = (Binding<KeyDelegate>) linker.requestBinding("io.github.hidroh.materialistic.KeyDelegate", ItemActivity.class, getClass().getClassLoader());
    supertype = (Binding<InjectableActivity>) linker.requestBinding("members/io.github.hidroh.materialistic.InjectableActivity", ItemActivity.class, getClass().getClassLoader(), false, true);
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(mItemManager);
    injectMembersBindings.add(mFavoriteManager);
    injectMembersBindings.add(mAlertDialogBuilder);
    injectMembersBindings.add(mPopupMenu);
    injectMembersBindings.add(mUserServices);
    injectMembersBindings.add(mSessionManager);
    injectMembersBindings.add(mCustomTabsDelegate);
    injectMembersBindings.add(mKeyDelegate);
    injectMembersBindings.add(supertype);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<ItemActivity>}.
   */
  @Override
  public ItemActivity get() {
    ItemActivity result = new ItemActivity();
    injectMembers(result);
    return result;
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<ItemActivity>}.
   */
  @Override
  public void injectMembers(ItemActivity object) {
    object.mItemManager = mItemManager.get();
    object.mFavoriteManager = mFavoriteManager.get();
    object.mAlertDialogBuilder = mAlertDialogBuilder.get();
    object.mPopupMenu = mPopupMenu.get();
    object.mUserServices = mUserServices.get();
    object.mSessionManager = mSessionManager.get();
    object.mCustomTabsDelegate = mCustomTabsDelegate.get();
    object.mKeyDelegate = mKeyDelegate.get();
    supertype.injectMembers(object);
  }
}
