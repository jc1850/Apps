package de.storchp.fdroidbuildstatus.adapter.fdroid;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static de.storchp.fdroidbuildstatus.adapter.ApiResponse.Status.NOT_FOUND;
import static de.storchp.fdroidbuildstatus.utils.TestUtils.fromFile;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import de.storchp.fdroidbuildstatus.adapter.ApiResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class FdroidClientTest {

    private MockWebServer server;
    private FdroidClient client;

    @BeforeEach
    void setUp() throws IOException {
        server = new MockWebServer();
        server.start();
        var baseUrl = server.url("/");
        client = new FdroidClient(baseUrl.toString());
    }

    @AfterEach
    void teardown() throws Exception {
        server.shutdown();
    }

    @Test
    void getFinishedBuildRun() throws Exception {
        server.enqueue(new MockResponse().setBody(fromFile("build.json")));
        var response = new AtomicReference<ApiResponse<BuildResult>>();

        client.getBuild(response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/repo/status/build.json");
        var buildRun = response.get().value();
        assertThat(buildRun).isNotNull();
        assertThat(buildRun.getStartTimestamp()).isEqualTo(1665726365000L);
        assertThat(buildRun.getEndTimestamp()).isEqualTo(1665833139602L);
        assertThat(buildRun.getSubcommand()).isEqualTo("build");
        assertThat(buildRun.getFdroiddata().getCommitId()).isEqualTo("c9a65de6896e0f9320187a2aa1ead680bcc0f4c6");
        assertThat(buildRun.getAllBuilds()).containsExactlyInAnyOrder(
                new FailedBuildItem("org.epstudios.epmobile", "76"),
                new FailedBuildItem("org.btcmap", "22"),
                new SuccessBuildIdItem("org.epstudios.epmobile", "77"),
                new SuccessBuildIdItem("org.btcmap", "17"));
    }

    @Test
    void getRunningBuildRunWithEmptyBuild() throws Exception {
        server.enqueue(new MockResponse().setBody(fromFile("running_build_empty.json")));
        var response = new AtomicReference<ApiResponse<RunningResult>>();

        client.getRunning(response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/repo/status/running.json");
        var result = response.get().value();
        assertThat(result.getStartTimestamp()).isEqualTo(1665901087000L);
        assertThat(result.getEndTimestamp()).isEqualTo(0L);
        assertThat(result.getSubcommand()).isEqualTo("build");
        assertThat(result.getFdroiddata().getCommitId()).isEqualTo("74a44c1596872bb898ae0f1fb865776006616dd3");
        assertThat(result).isInstanceOf(BuildResult.class);
        var buildResult = (BuildResult) result;
        assertThat(buildResult.getFailedBuildItems()).isEmpty();
        assertThat(buildResult.getSuccessfulBuildIds()).isEmpty();
        assertThat(buildResult.getAllBuilds()).isEmpty();
    }

    @Test
    void getRunningBuildRunWithDeployCommand() throws Exception {
        server.enqueue(new MockResponse().setBody(fromFile("running_deploy.json")));
        var response = new AtomicReference<ApiResponse<RunningResult>>();

        client.getRunning(response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/repo/status/running.json");
        var result = response.get().value();
        assertThat(result).isNotNull();
        assertThat(result.getStartTimestamp()).isEqualTo(1665894966000L);
        assertThat(result.getEndTimestamp()).isEqualTo(1665895914221L);
        assertThat(result.getSubcommand()).isEqualTo("deploy");
        assertThat(result.getFdroiddata().getCommitId()).isEqualTo("49c4a127bb64e8f22ec35dc7b6006586e6a25786");
        assertThat(result).isNotInstanceOf(BuildResult.class);
    }

    @Test
    void getRunningBuildRunWithUpdateCommand() throws Exception {
        server.enqueue(new MockResponse().setBody(fromFile("running_update.json")));
        var response = new AtomicReference<ApiResponse<RunningResult>>();

        client.getRunning(response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/repo/status/running.json");
        var result = response.get().value();
        assertThat(result).isNotNull();
        assertThat(result.getStartTimestamp()).isEqualTo(1664958843000L);
        assertThat(result.getEndTimestamp()).isEqualTo(1664979743515L);
        assertThat(result.getSubcommand()).isEqualTo("update");
        assertThat(result.getFdroiddata().getCommitId()).isEqualTo("5af8169594303c3c9ca30b672ee12d5b3933250a");
        assertThat(result).isInstanceOf(UpdateResult.class);
        var updateResult = (UpdateResult) result;
        assertThat(updateResult.getMissingBuildItems()).containsExactlyInAnyOrder(
                new MissingBuildItem("com.espruino.gadgetbridge.banglejs", "216"),
                new MissingBuildItem("com.standardnotes", "3000518"));
        assertThat(updateResult.getArchivePolicy0()).containsExactlyInAnyOrder(
                "vu.de.urpool.quickdroid",
                "yellr.net.yellr_android");
        assertThat(updateResult.getDisabled()).containsExactlyInAnyOrder(
                "me.lucky.catcher",
                "me.lucky.reactor");
        assertThat(updateResult.getNeedsUpdate()).containsExactlyInAnyOrder(
                "wb.receiptspro",
                "xyz.zedler.patrick.grocy");
        assertThat(updateResult.getNoPackages()).containsExactlyInAnyOrder(
                "org.owntracks.android",
                "security.pEp");
        assertThat(updateResult.getNoUpdateCheck()).containsExactlyInAnyOrder(
                "za.co.neilson.alarm",
                "zen.meditation.android");
    }

    @Test
    void getUpdate() throws Exception {
        server.enqueue(new MockResponse().setBody(fromFile("update.json")));
        var response = new AtomicReference<ApiResponse<UpdateResult>>();

        client.getUpdate(response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/repo/status/update.json");
        var update = response.get().value();
        assertThat(update).isNotNull();
        assertThat(update.getNeedsUpdate()).containsExactlyInAnyOrder("wb.receiptspro", "xyz.zedler.patrick.grocy");
        assertThat(update.getDisabled()).containsExactlyInAnyOrder("me.lucky.catcher", "me.lucky.reactor");
        assertThat(update.getArchivePolicy0()).containsExactlyInAnyOrder(
                "vu.de.urpool.quickdroid",
                "yellr.net.yellr_android");
        assertThat(update.getNoPackages()).containsExactlyInAnyOrder(
                "org.owntracks.android",
                "security.pEp");
        assertThat(update.getNoUpdateCheck()).containsExactlyInAnyOrder(
                "za.co.neilson.alarm",
                "zen.meditation.android");
    }

    @Test
    void getWebsiteBuildStatus() throws Exception {
        server.enqueue(new MockResponse().setBody(fromFile("deploy-to-f-droid.org.json")));
        var response = new AtomicReference<ApiResponse<WebsiteBuildStatus>>();

        client.getWebsiteBuildStatus(response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/repo/status/deploy-to-f-droid.org.json");
        var websiteBuildStatus = response.get().value();
        assertThat(websiteBuildStatus).isNotNull();
        assertThat(websiteBuildStatus.getStartTimestamp()).isEqualTo(1671715726000L);
        assertThat(websiteBuildStatus.getEndTimestamp()).isEqualTo(1671663330000L);
        assertThat(websiteBuildStatus.getCommandLine()).containsExactly("/home/fdroid/deploy-to-f-droid.org");
    }

    @Test
    void getIndex() throws Exception {
        server.enqueue(new MockResponse().setBody(fromFile("index-v1.jar")));
        var response = new AtomicReference<ApiResponse<Index>>();

        client.getIndex(response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        var index = response.get().value();
        assertThat(server.takeRequest().getPath()).isEqualTo("/repo/index-v1.jar");
        assertThat(index).isNotNull();
        assertThat(index.getApps()).contains(
                new Index.App(
                        null,
                        "de.storchp.fdroidbuildstatus.nightly",
                        "https://codeberg.org/pstorch/F-Droid_Build_Status",
                        Map.of(
                                "de", new Index.LocalizedName(),
                                "en-US", new Index.LocalizedName("F-Droid Build Status (Nightly)"),
                                "fr", new Index.LocalizedName(),
                                "he", new Index.LocalizedName(),
                                "it", new Index.LocalizedName(),
                                "tr", new Index.LocalizedName(),
                                "zh-CN", new Index.LocalizedName()
                        )),
                new Index.App(
                        null,
                        "de.bahnhoefe.deutschlands.bahnhofsfotos.nightly",
                        "https://github.com/RailwayStations/RSAndroidApp",
                        Map.of(
                                "de-DE", new Index.LocalizedName(),
                                "en-US", new Index.LocalizedName("Railway station photos (Nightly)")
                        )));
    }

    @Test
    void getBuildLog() throws Exception {
        server.enqueue(new MockResponse().setBody(fromFile("de.storchp.fdroidbuildstatus_36.log.gz")).setHeader("content-encoding", "gzip"));
        var response = new AtomicReference<ApiResponse<Reader>>();

        client.getBuildLog("de.storchp.fdroidbuildstatus", "36", response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/repo/de.storchp.fdroidbuildstatus_36.log.gz");
        var body = response.get().value();
        var reader = new BufferedReader(body);
        assertThat(reader.lines()).hasSize(179);
    }

    @Test
    void getBuildLogNotFound() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(404));
        var response = new AtomicReference<ApiResponse<Reader>>();

        client.getBuildLog("de.storchp.fdroidbuildstatus", "36", response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/repo/de.storchp.fdroidbuildstatus_36.log.gz");
        assertThat(response.get().status()).isEqualTo(NOT_FOUND);
        assertThat(response.get().value()).isNull();
    }

    @Test
    void getPublishedVersions() throws Exception {
        server.enqueue(new MockResponse().setBody(fromFile("published_versions.json")));
        var response = new AtomicReference<ApiResponse<PublishedPackage>>();

        client.getPublishedVersions("de.bahnhoefe.deutschlands.bahnhofsfotos", response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/packages/de.bahnhoefe.deutschlands.bahnhofsfotos");
        var publishedVersions = response.get().value();
        assertThat(publishedVersions).isNotNull();
        assertThat(publishedVersions.getPackageName()).isEqualTo("de.bahnhoefe.deutschlands.bahnhofsfotos");
        assertThat(publishedVersions.getSuggestedVersionCode()).isEqualTo("67");
        assertThat(publishedVersions.getPackages()).containsExactlyInAnyOrder(
                new PublishedPackage.PublishedPackage(
                        "13.4.0",
                        "67"),
                new PublishedPackage.PublishedPackage(
                        "13.3.0",
                        "66"),
                new PublishedPackage.PublishedPackage(
                        "13.2.0",
                        "65")
        );
    }

    @Test
    void getPublishedVersionsNotFound() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(404));
        var response = new AtomicReference<ApiResponse<PublishedPackage>>();

        client.getPublishedVersions("de.bahnhoefe.deutschlands.bahnhofsfotos", response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/packages/de.bahnhoefe.deutschlands.bahnhofsfotos");
        assertThat(response.get().status()).isEqualTo(NOT_FOUND);
        assertThat(response.get().value()).isNull();
    }

}