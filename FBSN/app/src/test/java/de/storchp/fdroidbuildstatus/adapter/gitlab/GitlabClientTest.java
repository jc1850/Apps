package de.storchp.fdroidbuildstatus.adapter.gitlab;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static de.storchp.fdroidbuildstatus.adapter.ApiResponse.Status.NOT_FOUND;
import static de.storchp.fdroidbuildstatus.utils.TestUtils.fromFile;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import de.storchp.fdroidbuildstatus.adapter.ApiResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class GitlabClientTest {

    private MockWebServer server;
    private GitlabClient client;

    @BeforeEach
    void setUp() throws IOException {
        server = new MockWebServer();
        server.start();
        var baseUrl = server.url("/");
        client = new GitlabClient(baseUrl.toString());
    }

    @AfterEach
    void teardown() throws Exception {
        server.shutdown();
    }

    @Test
    void getFdroidDataMetadata() throws Exception {
        server.enqueue(new MockResponse().setBody(fromFile("my.app.id.yml")));
        var response = new AtomicReference<ApiResponse<Metadata>>();

        client.getFdroidDataMetadata("my.app.id", response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/fdroid/fdroiddata/-/raw/master/metadata/my.app.id.yml");
        assertThat(response.get().value()).isEqualTo(new Metadata(
                "https://example.com/maxmuster/MyApp",
                "My App",
                null,
                Set.of(
                        new Metadata.Build(
                                "1",
                                "1.0"),
                        new Metadata.Build(
                                "2",
                                "1.1")
                )
        ));
    }

    @Test
    void getFdroidDataMetadataNotFound() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(404));
        var response = new AtomicReference<ApiResponse<Metadata>>();

        client.getFdroidDataMetadata("my.app.id", response::set);
        await().atMost(1, SECONDS).until(() -> response.get() != null);

        assertThat(server.takeRequest().getPath()).isEqualTo("/fdroid/fdroiddata/-/raw/master/metadata/my.app.id.yml");
        assertThat(response.get().status()).isEqualTo(NOT_FOUND);
        assertThat(response.get().value()).isNull();
    }

}