package de.storchp.fdroidbuildstatus.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import de.storchp.fdroidbuildstatus.R;

class AppTest {

    @Test
    void getDisplayNameWithIdAndName() {
        var app = new App("some.id", "Some Name");

        assertThat(app.getDisplayName()).isEqualTo("Some Name");
    }

    @Test
    void getDisplayNameWithIdAndNoName() {
        var app = new App("some.id");

        assertThat(app.getDisplayName()).isEqualTo("some.id");
    }

    @Test
    void getFavouriteIconForFavoriteApp() {
        var app = new App("some.id",
                "name",
                true);

        assertThat(app.getFavouriteIcon()).isEqualTo(R.drawable.ic_favourite_24px);
    }

    @Test
    void getFavouriteIconForNonFavoriteApp() {
        var app = new App("some.id",
                "name",
                false);

        assertThat(app.getFavouriteIcon()).isEqualTo(R.drawable.ic_no_favourite_24px);
    }

    @Test
    void getFdroidUri() {
        var app = new App("some.id");

        assertThat(app.getFdroidUri()).isEqualTo("https://f-droid.org/packages/some.id");
    }

    @Test
    void getMetadataUri() {
        var app = new App("some.id");

        assertThat(app.getMetadataUri(MetadataLinkType.YAML)).isEqualTo("https://gitlab.com/fdroid/fdroiddata/-/raw/master/metadata/some.id.yml");
    }

    @Test
    void getAppBuildsByVersionCodeAndStatus() {
        var build1SuccessRunning = new AppBuild("1", "name", BuildCycle.RUNNING, BuildStatus.SUCCESS);
        var build1SuccessFinished = new AppBuild("1", "name", BuildCycle.BUILD, BuildStatus.SUCCESS);
        var build2SuccessRunning = new AppBuild("2", "name", BuildCycle.RUNNING, BuildStatus.SUCCESS);
        var build2FailedFinished = new AppBuild("2", "name", BuildCycle.BUILD, BuildStatus.FAILED);
        var build3SuccessRunning = new AppBuild("3", "name", BuildCycle.RUNNING, BuildStatus.SUCCESS);
        var app = new App("some.id", "name", false, null, false, false, false, false, false,
                Set.of(
                        build1SuccessRunning,
                        build1SuccessFinished,
                        build2SuccessRunning,
                        build2FailedFinished,
                        build3SuccessRunning
                ));

        Map<String, List<AppBuild>> result = app.getAppBuildsByVersionCodeAndStatus();

        assertThat(result.get("1SUCCESS")).containsExactlyInAnyOrder(build1SuccessRunning, build1SuccessFinished);
        assertThat(result.get("2SUCCESS")).containsExactlyInAnyOrder(build2SuccessRunning);
        assertThat(result.get("2FAILED")).containsExactlyInAnyOrder(build2FailedFinished);
        assertThat(result.get("3SUCCESS")).containsExactlyInAnyOrder(build3SuccessRunning);
    }

}