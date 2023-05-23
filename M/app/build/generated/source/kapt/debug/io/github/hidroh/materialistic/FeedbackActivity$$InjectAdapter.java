// Code generated by dagger-compiler.  Do not edit.
package io.github.hidroh.materialistic;

import dagger.internal.Binding;
import dagger.internal.Linker;
import io.github.hidroh.materialistic.data.FeedbackClient;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.util.Set;

/**
 * A {@code Binding<io.github.hidroh.materialistic.FeedbackActivity>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code io.github.hidroh.materialistic.FeedbackActivity} and its
 * dependencies.
 *
 * Being a {@code Provider<io.github.hidroh.materialistic.FeedbackActivity>} and handling creation and
 * preparation of object instances.
 *
 * Being a {@code MembersInjector<io.github.hidroh.materialistic.FeedbackActivity>} and handling injection
 * of annotated fields.
 */
public final class FeedbackActivity$$InjectAdapter extends Binding<FeedbackActivity> {
  private Binding<FeedbackClient> mFeedbackClient;

  private Binding<InjectableActivity> supertype;

  public FeedbackActivity$$InjectAdapter() {
    super("io.github.hidroh.materialistic.FeedbackActivity", "members/io.github.hidroh.materialistic.FeedbackActivity", NOT_SINGLETON, FeedbackActivity.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    mFeedbackClient = (Binding<FeedbackClient>) linker.requestBinding("io.github.hidroh.materialistic.data.FeedbackClient", FeedbackActivity.class, getClass().getClassLoader());
    supertype = (Binding<InjectableActivity>) linker.requestBinding("members/io.github.hidroh.materialistic.InjectableActivity", FeedbackActivity.class, getClass().getClassLoader(), false, true);
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(mFeedbackClient);
    injectMembersBindings.add(supertype);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<FeedbackActivity>}.
   */
  @Override
  public FeedbackActivity get() {
    FeedbackActivity result = new FeedbackActivity();
    injectMembers(result);
    return result;
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<FeedbackActivity>}.
   */
  @Override
  public void injectMembers(FeedbackActivity object) {
    object.mFeedbackClient = mFeedbackClient.get();
    supertype.injectMembers(object);
  }
}