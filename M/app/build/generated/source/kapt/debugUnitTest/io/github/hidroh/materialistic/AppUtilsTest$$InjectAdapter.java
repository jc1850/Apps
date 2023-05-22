// Code generated by dagger-compiler.  Do not edit.
package io.github.hidroh.materialistic;

import dagger.internal.Binding;
import dagger.internal.Linker;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.util.Set;

/**
 * A {@code Binding<io.github.hidroh.materialistic.AppUtilsTest>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code io.github.hidroh.materialistic.AppUtilsTest} and its
 * dependencies.
 *
 * Being a {@code Provider<io.github.hidroh.materialistic.AppUtilsTest>} and handling creation and
 * preparation of object instances.
 *
 * Being a {@code MembersInjector<io.github.hidroh.materialistic.AppUtilsTest>} and handling injection
 * of annotated fields.
 */
public final class AppUtilsTest$$InjectAdapter extends Binding<AppUtilsTest> {
  private Binding<AlertDialogBuilder> alertDialogBuilder;

  public AppUtilsTest$$InjectAdapter() {
    super("io.github.hidroh.materialistic.AppUtilsTest", "members/io.github.hidroh.materialistic.AppUtilsTest", NOT_SINGLETON, AppUtilsTest.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    alertDialogBuilder = (Binding<AlertDialogBuilder>) linker.requestBinding("io.github.hidroh.materialistic.AlertDialogBuilder", AppUtilsTest.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(alertDialogBuilder);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<AppUtilsTest>}.
   */
  @Override
  public AppUtilsTest get() {
    AppUtilsTest result = new AppUtilsTest();
    injectMembers(result);
    return result;
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<AppUtilsTest>}.
   */
  @Override
  public void injectMembers(AppUtilsTest object) {
    object.alertDialogBuilder = alertDialogBuilder.get();
  }
}
