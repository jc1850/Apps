package de.storchp.fdroidbuildstatus.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class AppBuildTest {

    @Test
    public void getFullVersion() {
        var appBuild = new AppBuild(
                "123",
                "1.2.3",
                BuildCycle.NONE,
                BuildStatus.SUCCESS);
        assertThat(appBuild.getFullVersion()).isEqualTo("123 - 1.2.3");
    }

    @Test
    public void getFullVersionWithoutVersionName() {
        var appBuild = new AppBuild(
                "123",
                null,
                BuildCycle.NONE,
                BuildStatus.SUCCESS);
        assertThat(appBuild.getFullVersion()).isEqualTo("123");
    }

    @Test
    public void getMetadataUriMasterNoMetadataPath() {
        var appBuild = new AppBuild("1");
        assertThat(appBuild.getMetadataUri(MetadataLinkType.GITLAB, "com.example.app-id")).isEqualTo("https://gitlab.com/fdroid/fdroiddata/-/blob/master/metadata/com.example.app-id.yml");
    }

    @Test
    public void getMetadataUriMasterNoMetadataPathRawYaml() {
        var appBuild = new AppBuild("1");
        assertThat(appBuild.getMetadataUri(MetadataLinkType.YAML, "com.example.app-id")).isEqualTo("https://gitlab.com/fdroid/fdroiddata/-/raw/master/metadata/com.example.app-id.yml");
    }

    @Test
    public void getMetadataUriCommitIdNoMetadataPath() {
        var appBuild = new AppBuild("1",
                null,
                BuildCycle.NONE,
                BuildStatus.MISSING,
                "commitId");
        assertThat(appBuild.getMetadataUri(MetadataLinkType.GITLAB, "com.example.app-id")).isEqualTo("https://gitlab.com/fdroid/fdroiddata/-/blob/commitId/metadata/com.example.app-id.yml");
    }

}