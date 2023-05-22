// Code generated by dagger-compiler.  Do not edit.
package io.github.hidroh.materialistic.data;

import android.content.Context;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.util.Set;
import okhttp3.Call;

/**
 * A {@code Binding<io.github.hidroh.materialistic.data.FileDownloader>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code io.github.hidroh.materialistic.data.FileDownloader} and its
 * dependencies.
 *
 * Being a {@code Provider<io.github.hidroh.materialistic.data.FileDownloader>} and handling creation and
 * preparation of object instances.
 */
public final class FileDownloader$$InjectAdapter extends Binding<FileDownloader> {
  private Binding<Context> context;

  private Binding<Call.Factory> callFactory;

  public FileDownloader$$InjectAdapter() {
    super("io.github.hidroh.materialistic.data.FileDownloader", "members/io.github.hidroh.materialistic.data.FileDownloader", NOT_SINGLETON, FileDownloader.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    context = (Binding<Context>) linker.requestBinding("android.content.Context", FileDownloader.class, getClass().getClassLoader());
    callFactory = (Binding<Call.Factory>) linker.requestBinding("okhttp3.Call$Factory", FileDownloader.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    getBindings.add(context);
    getBindings.add(callFactory);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<FileDownloader>}.
   */
  @Override
  public FileDownloader get() {
    FileDownloader result = new FileDownloader(context.get(), callFactory.get());
    return result;
  }
}
