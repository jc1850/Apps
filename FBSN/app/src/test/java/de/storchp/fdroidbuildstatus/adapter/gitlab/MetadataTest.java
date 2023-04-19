package de.storchp.fdroidbuildstatus.adapter.gitlab;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.Set;

public class MetadataTest {

    @Test
    public void testHighestMetadataVersionCode() {
        var metadata = new Metadata(
                null,
                null,
                null,
                Set.of(
                        new Metadata.Build(
                                "9",
                                "1.2.3"),
                        new Metadata.Build(
                                "11",
                                "1.3.1")
                ));
        assertThat(metadata.getHighestVersion()).isPresent();
        assertThat(metadata.getHighestVersion().get().getVersionCode()).isEqualTo("11");
    }

}