// Code generated by dagger-compiler.  Do not edit.
package io.github.hidroh.materialistic;

import android.accounts.AccountManager;
import dagger.internal.Binding;
import dagger.internal.Linker;
import io.github.hidroh.materialistic.accounts.UserServices;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.util.Set;

/**
 * A {@code Binding<io.github.hidroh.materialistic.LoginActivity>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code io.github.hidroh.materialistic.LoginActivity} and its
 * dependencies.
 *
 * Being a {@code Provider<io.github.hidroh.materialistic.LoginActivity>} and handling creation and
 * preparation of object instances.
 *
 * Being a {@code MembersInjector<io.github.hidroh.materialistic.LoginActivity>} and handling injection
 * of annotated fields.
 */
public final class LoginActivity$$InjectAdapter extends Binding<LoginActivity> {
  private Binding<UserServices> mUserServices;

  private Binding<AccountManager> mAccountManager;

  private Binding<AccountAuthenticatorActivity> supertype;

  public LoginActivity$$InjectAdapter() {
    super("io.github.hidroh.materialistic.LoginActivity", "members/io.github.hidroh.materialistic.LoginActivity", NOT_SINGLETON, LoginActivity.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    mUserServices = (Binding<UserServices>) linker.requestBinding("io.github.hidroh.materialistic.accounts.UserServices", LoginActivity.class, getClass().getClassLoader());
    mAccountManager = (Binding<AccountManager>) linker.requestBinding("android.accounts.AccountManager", LoginActivity.class, getClass().getClassLoader());
    supertype = (Binding<AccountAuthenticatorActivity>) linker.requestBinding("members/io.github.hidroh.materialistic.AccountAuthenticatorActivity", LoginActivity.class, getClass().getClassLoader(), false, true);
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(mUserServices);
    injectMembersBindings.add(mAccountManager);
    injectMembersBindings.add(supertype);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<LoginActivity>}.
   */
  @Override
  public LoginActivity get() {
    LoginActivity result = new LoginActivity();
    injectMembers(result);
    return result;
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<LoginActivity>}.
   */
  @Override
  public void injectMembers(LoginActivity object) {
    object.mUserServices = mUserServices.get();
    object.mAccountManager = mAccountManager.get();
    supertype.injectMembers(object);
  }
}
