package org.openobservatory.ooniprobe.di;

import android.content.Context;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.openobservatory.ooniprobe.activity.CustomWebsiteActivity;
import org.openobservatory.ooniprobe.activity.CustomWebsiteActivity_MembersInjector;
import org.openobservatory.ooniprobe.activity.LogActivity;
import org.openobservatory.ooniprobe.activity.LogActivity_MembersInjector;
import org.openobservatory.ooniprobe.activity.MainActivity;
import org.openobservatory.ooniprobe.activity.MainActivity_MembersInjector;
import org.openobservatory.ooniprobe.activity.MeasurementDetailActivity;
import org.openobservatory.ooniprobe.activity.MeasurementDetailActivity_MembersInjector;
import org.openobservatory.ooniprobe.activity.OoniRunActivity;
import org.openobservatory.ooniprobe.activity.OoniRunActivity_MembersInjector;
import org.openobservatory.ooniprobe.activity.OverviewActivity;
import org.openobservatory.ooniprobe.activity.OverviewActivity_MembersInjector;
import org.openobservatory.ooniprobe.activity.ProxyActivity;
import org.openobservatory.ooniprobe.activity.ProxyActivity_MembersInjector;
import org.openobservatory.ooniprobe.activity.ResultDetailActivity;
import org.openobservatory.ooniprobe.activity.ResultDetailActivity_MembersInjector;
import org.openobservatory.ooniprobe.activity.RunningActivity;
import org.openobservatory.ooniprobe.activity.RunningActivity_MembersInjector;
import org.openobservatory.ooniprobe.activity.TextActivity;
import org.openobservatory.ooniprobe.activity.TextActivity_MembersInjector;
import org.openobservatory.ooniprobe.client.OONIAPIClient;
import org.openobservatory.ooniprobe.common.AppLogger;
import org.openobservatory.ooniprobe.common.AppLogger_Factory;
import org.openobservatory.ooniprobe.common.Application;
import org.openobservatory.ooniprobe.common.Application_MembersInjector;
import org.openobservatory.ooniprobe.common.JsonPrinter;
import org.openobservatory.ooniprobe.common.JsonPrinter_Factory;
import org.openobservatory.ooniprobe.common.PreferenceManager;
import org.openobservatory.ooniprobe.common.PreferenceManager_Factory;
import org.openobservatory.ooniprobe.common.ResubmitTask;
import org.openobservatory.ooniprobe.common.ResubmitTask_Dependencies_MembersInjector;
import org.openobservatory.ooniprobe.common.TestProgressRepository;
import org.openobservatory.ooniprobe.common.TestProgressRepository_Factory;
import org.openobservatory.ooniprobe.common.service.RunTestJobService;
import org.openobservatory.ooniprobe.common.service.ServiceUtil;
import org.openobservatory.ooniprobe.common.service.ServiceUtil_Dependencies_MembersInjector;
import org.openobservatory.ooniprobe.domain.GenerateAutoRunServiceSuite;
import org.openobservatory.ooniprobe.domain.GenerateAutoRunServiceSuite_Factory;
import org.openobservatory.ooniprobe.domain.GetResults_Factory;
import org.openobservatory.ooniprobe.domain.GetTestSuite;
import org.openobservatory.ooniprobe.domain.GetTestSuite_Factory;
import org.openobservatory.ooniprobe.domain.MeasurementsManager;
import org.openobservatory.ooniprobe.domain.MeasurementsManager_Factory;
import org.openobservatory.ooniprobe.domain.UpdatesNotificationManager;
import org.openobservatory.ooniprobe.domain.VersionCompare;
import org.openobservatory.ooniprobe.fragment.DashboardFragment;
import org.openobservatory.ooniprobe.fragment.DashboardFragment_MembersInjector;
import org.openobservatory.ooniprobe.fragment.ProgressFragment;
import org.openobservatory.ooniprobe.fragment.ProgressFragment_MembersInjector;
import org.openobservatory.ooniprobe.fragment.ResultListFragment;
import org.openobservatory.ooniprobe.fragment.ResultListFragment_MembersInjector;
import org.openobservatory.ooniprobe.fragment.onboarding.Onboarding3Fragment;
import org.openobservatory.ooniprobe.fragment.onboarding.Onboarding3Fragment_MembersInjector;
import org.openobservatory.ooniprobe.fragment.onboarding.OnboardingAutoTestFragment;
import org.openobservatory.ooniprobe.fragment.onboarding.OnboardingAutoTestFragment_MembersInjector;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerTestAppComponent implements TestAppComponent {
  private final TestAppModule testAppModule;

  private final DaggerTestAppComponent testAppComponent = this;

  private Provider<Context> provideAppContextProvider;

  private Provider<PreferenceManager> preferenceManagerProvider;

  private Provider<GsonBuilder> provideGsonBuilderProvider;

  private Provider<Gson> provideGsonProvider;

  private Provider<HttpLoggingInterceptor> provideLoggingInterceptorProvider;

  private Provider<Interceptor> provideHeaderInterceptorProvider;

  private Provider<OkHttpClient> provideOkHttpClientProvider;

  private Provider<String> provideApiUrlProvider;

  private Provider<OONIAPIClient> provideApiClientProvider;

  private Provider<AppLogger> appLoggerProvider;

  private Provider<TestProgressRepository> testProgressRepositoryProvider;

  private DaggerTestAppComponent(TestAppModule testAppModuleParam) {
    this.testAppModule = testAppModuleParam;
    initialize(testAppModuleParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final TestAppModule testAppModuleParam) {
    this.provideAppContextProvider = ApplicationModule_ProvideAppContextFactory.create(testAppModuleParam);
    this.preferenceManagerProvider = DoubleCheck.provider(PreferenceManager_Factory.create(provideAppContextProvider));
    this.provideGsonBuilderProvider = DoubleCheck.provider(ApplicationModule_ProvideGsonBuilderFactory.create(testAppModuleParam));
    this.provideGsonProvider = DoubleCheck.provider(ApplicationModule_ProvideGsonFactory.create(testAppModuleParam, provideGsonBuilderProvider));
    this.provideLoggingInterceptorProvider = DoubleCheck.provider(ApplicationModule_ProvideLoggingInterceptorFactory.create(testAppModuleParam));
    this.provideHeaderInterceptorProvider = DoubleCheck.provider(ApplicationModule_ProvideHeaderInterceptorFactory.create(testAppModuleParam));
    this.provideOkHttpClientProvider = DoubleCheck.provider(ApplicationModule_ProvideOkHttpClientFactory.create(testAppModuleParam, provideLoggingInterceptorProvider, provideHeaderInterceptorProvider));
    this.provideApiUrlProvider = ApplicationModule_ProvideApiUrlFactory.create(testAppModuleParam);
    this.provideApiClientProvider = DoubleCheck.provider(ApplicationModule_ProvideApiClientFactory.create(testAppModuleParam, provideOkHttpClientProvider, provideApiUrlProvider));
    this.appLoggerProvider = DoubleCheck.provider(AppLogger_Factory.create(provideAppContextProvider));
    this.testProgressRepositoryProvider = DoubleCheck.provider(TestProgressRepository_Factory.create());
  }

  @Override
  public void inject(Application arg0) {
    injectApplication(arg0);
  }

  @Override
  public void inject(TestApplication app) {
    injectTestApplication(app);
  }

  @Override
  public ActivityComponent activityComponent() {
    return new ActivityComponentImpl(testAppComponent);
  }

  @Override
  public FragmentComponent fragmentComponent() {
    return new FragmentComponentImpl(testAppComponent);
  }

  @Override
  public ServiceComponent serviceComponent() {
    return new ServiceComponentImpl(testAppComponent);
  }

  @CanIgnoreReturnValue
  private Application injectApplication(Application instance) {
    Application_MembersInjector.inject_preferenceManager(instance, preferenceManagerProvider.get());
    Application_MembersInjector.inject_gson(instance, provideGsonProvider.get());
    Application_MembersInjector.inject_okHttpClient(instance, provideOkHttpClientProvider.get());
    Application_MembersInjector.inject_apiClient(instance, provideApiClientProvider.get());
    Application_MembersInjector.injectLogger(instance, appLoggerProvider.get());
    return instance;
  }

  @CanIgnoreReturnValue
  private TestApplication injectTestApplication(TestApplication instance) {
    Application_MembersInjector.inject_preferenceManager(instance, preferenceManagerProvider.get());
    Application_MembersInjector.inject_gson(instance, provideGsonProvider.get());
    Application_MembersInjector.inject_okHttpClient(instance, provideOkHttpClientProvider.get());
    Application_MembersInjector.inject_apiClient(instance, provideApiClientProvider.get());
    Application_MembersInjector.injectLogger(instance, appLoggerProvider.get());
    return instance;
  }

  public static final class Builder {
    private TestAppModule testAppModule;

    private Builder() {
    }

    public Builder testAppModule(TestAppModule testAppModule) {
      this.testAppModule = Preconditions.checkNotNull(testAppModule);
      return this;
    }

    public TestAppComponent build() {
      Preconditions.checkBuilderRequirement(testAppModule, TestAppModule.class);
      return new DaggerTestAppComponent(testAppModule);
    }
  }

  private static final class ActivityComponentImpl implements ActivityComponent {
    private final DaggerTestAppComponent testAppComponent;

    private final ActivityComponentImpl activityComponentImpl = this;

    private ActivityComponentImpl(DaggerTestAppComponent testAppComponent) {
      this.testAppComponent = testAppComponent;

    }

    private UpdatesNotificationManager updatesNotificationManager() {
      return new UpdatesNotificationManager(testAppComponent.preferenceManagerProvider.get());
    }

    private JsonPrinter jsonPrinter() {
      return JsonPrinter_Factory.newInstance(testAppComponent.provideGsonBuilderProvider.get());
    }

    private MeasurementsManager measurementsManager() {
      return MeasurementsManager_Factory.newInstance(ApplicationModule_ProvideAppContextFactory.provideAppContext(testAppComponent.testAppModule), jsonPrinter(), testAppComponent.provideApiClientProvider.get(), testAppComponent.provideOkHttpClientProvider.get());
    }

    private GetTestSuite getTestSuite() {
      return GetTestSuite_Factory.newInstance(ApplicationModule_ProvideApplicationFactory.provideApplication(testAppComponent.testAppModule));
    }

    @Override
    public void inject(CustomWebsiteActivity arg0) {
      injectCustomWebsiteActivity(arg0);
    }

    @Override
    public void inject(MainActivity arg0) {
      injectMainActivity(arg0);
    }

    @Override
    public void inject(ProxyActivity arg0) {
      injectProxyActivity(arg0);
    }

    @Override
    public void inject(MeasurementDetailActivity arg0) {
      injectMeasurementDetailActivity(arg0);
    }

    @Override
    public void inject(OoniRunActivity arg0) {
      injectOoniRunActivity(arg0);
    }

    @Override
    public void inject(OverviewActivity arg0) {
      injectOverviewActivity(arg0);
    }

    @Override
    public void inject(ResultDetailActivity arg0) {
      injectResultDetailActivity(arg0);
    }

    @Override
    public void inject(RunningActivity arg0) {
      injectRunningActivity(arg0);
    }

    @Override
    public void inject(TextActivity arg0) {
      injectTextActivity(arg0);
    }

    @Override
    public void inject(LogActivity arg0) {
      injectLogActivity(arg0);
    }

    @CanIgnoreReturnValue
    private CustomWebsiteActivity injectCustomWebsiteActivity(CustomWebsiteActivity instance) {
      CustomWebsiteActivity_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private MainActivity injectMainActivity(MainActivity instance) {
      MainActivity_MembersInjector.injectNotificationManager(instance, updatesNotificationManager());
      MainActivity_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private ProxyActivity injectProxyActivity(ProxyActivity instance) {
      ProxyActivity_MembersInjector.injectLogger(instance, testAppComponent.appLoggerProvider.get());
      ProxyActivity_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private MeasurementDetailActivity injectMeasurementDetailActivity(
        MeasurementDetailActivity instance) {
      MeasurementDetailActivity_MembersInjector.injectMeasurementsManager(instance, measurementsManager());
      MeasurementDetailActivity_MembersInjector.injectPm(instance, testAppComponent.preferenceManagerProvider.get());
      MeasurementDetailActivity_MembersInjector.injectGetTestSuite(instance, getTestSuite());
      MeasurementDetailActivity_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private OoniRunActivity injectOoniRunActivity(OoniRunActivity instance) {
      OoniRunActivity_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      OoniRunActivity_MembersInjector.injectVersionCompare(instance, new VersionCompare());
      OoniRunActivity_MembersInjector.injectGetSuite(instance, getTestSuite());
      OoniRunActivity_MembersInjector.injectGson(instance, testAppComponent.provideGsonProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private OverviewActivity injectOverviewActivity(OverviewActivity instance) {
      OverviewActivity_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private ResultDetailActivity injectResultDetailActivity(ResultDetailActivity instance) {
      ResultDetailActivity_MembersInjector.injectGetTestSuite(instance, getTestSuite());
      ResultDetailActivity_MembersInjector.injectGetResults(instance, GetResults_Factory.newInstance());
      ResultDetailActivity_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private RunningActivity injectRunningActivity(RunningActivity instance) {
      RunningActivity_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      RunningActivity_MembersInjector.injectTestProgressRepository(instance, testAppComponent.testProgressRepositoryProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private TextActivity injectTextActivity(TextActivity instance) {
      TextActivity_MembersInjector.injectMeasurementsManager(instance, measurementsManager());
      return instance;
    }

    @CanIgnoreReturnValue
    private LogActivity injectLogActivity(LogActivity instance) {
      LogActivity_MembersInjector.injectLogger(instance, testAppComponent.appLoggerProvider.get());
      return instance;
    }
  }

  private static final class FragmentComponentImpl implements FragmentComponent {
    private final DaggerTestAppComponent testAppComponent;

    private final FragmentComponentImpl fragmentComponentImpl = this;

    private FragmentComponentImpl(DaggerTestAppComponent testAppComponent) {
      this.testAppComponent = testAppComponent;

    }

    private JsonPrinter jsonPrinter() {
      return JsonPrinter_Factory.newInstance(testAppComponent.provideGsonBuilderProvider.get());
    }

    private MeasurementsManager measurementsManager() {
      return MeasurementsManager_Factory.newInstance(ApplicationModule_ProvideAppContextFactory.provideAppContext(testAppComponent.testAppModule), jsonPrinter(), testAppComponent.provideApiClientProvider.get(), testAppComponent.provideOkHttpClientProvider.get());
    }

    @Override
    public void inject(DashboardFragment arg0) {
      injectDashboardFragment(arg0);
    }

    @Override
    public void inject(Onboarding3Fragment arg0) {
      injectOnboarding3Fragment(arg0);
    }

    @Override
    public void inject(ResultListFragment arg0) {
      injectResultListFragment(arg0);
    }

    @Override
    public void inject(ProgressFragment arg0) {
      injectProgressFragment(arg0);
    }

    @Override
    public void inject(OnboardingAutoTestFragment arg0) {
      injectOnboardingAutoTestFragment(arg0);
    }

    @CanIgnoreReturnValue
    private DashboardFragment injectDashboardFragment(DashboardFragment instance) {
      DashboardFragment_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private Onboarding3Fragment injectOnboarding3Fragment(Onboarding3Fragment instance) {
      Onboarding3Fragment_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private ResultListFragment injectResultListFragment(ResultListFragment instance) {
      ResultListFragment_MembersInjector.injectMeasurementsManager(instance, measurementsManager());
      ResultListFragment_MembersInjector.injectGetResults(instance, GetResults_Factory.newInstance());
      ResultListFragment_MembersInjector.injectPm(instance, testAppComponent.preferenceManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private ProgressFragment injectProgressFragment(ProgressFragment instance) {
      ProgressFragment_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      ProgressFragment_MembersInjector.injectTestProgressRepository(instance, testAppComponent.testProgressRepositoryProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private OnboardingAutoTestFragment injectOnboardingAutoTestFragment(
        OnboardingAutoTestFragment instance) {
      OnboardingAutoTestFragment_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      return instance;
    }
  }

  private static final class ServiceComponentImpl implements ServiceComponent {
    private final DaggerTestAppComponent testAppComponent;

    private final ServiceComponentImpl serviceComponentImpl = this;

    private ServiceComponentImpl(DaggerTestAppComponent testAppComponent) {
      this.testAppComponent = testAppComponent;

    }

    private JsonPrinter jsonPrinter() {
      return JsonPrinter_Factory.newInstance(testAppComponent.provideGsonBuilderProvider.get());
    }

    private MeasurementsManager measurementsManager() {
      return MeasurementsManager_Factory.newInstance(ApplicationModule_ProvideAppContextFactory.provideAppContext(testAppComponent.testAppModule), jsonPrinter(), testAppComponent.provideApiClientProvider.get(), testAppComponent.provideOkHttpClientProvider.get());
    }

    private GenerateAutoRunServiceSuite generateAutoRunServiceSuite() {
      return GenerateAutoRunServiceSuite_Factory.newInstance(ApplicationModule_ProvideApplicationFactory.provideApplication(testAppComponent.testAppModule), testAppComponent.preferenceManagerProvider.get());
    }

    @Override
    public void inject(ResubmitTask.Dependencies arg0) {
      injectDependencies(arg0);
    }

    @Override
    public void inject(RunTestJobService arg0) {
    }

    @Override
    public void inject(ServiceUtil.Dependencies arg0) {
      injectDependencies2(arg0);
    }

    @CanIgnoreReturnValue
    private ResubmitTask.Dependencies injectDependencies(ResubmitTask.Dependencies instance) {
      ResubmitTask_Dependencies_MembersInjector.injectMeasurementsManager(instance, measurementsManager());
      ResubmitTask_Dependencies_MembersInjector.injectGetResults(instance, GetResults_Factory.newInstance());
      return instance;
    }

    @CanIgnoreReturnValue
    private ServiceUtil.Dependencies injectDependencies2(ServiceUtil.Dependencies instance) {
      ServiceUtil_Dependencies_MembersInjector.injectGenerateAutoRunServiceSuite(instance, generateAutoRunServiceSuite());
      ServiceUtil_Dependencies_MembersInjector.injectPreferenceManager(instance, testAppComponent.preferenceManagerProvider.get());
      return instance;
    }
  }
}
