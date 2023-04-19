package de.storchp.fdroidbuildstatus.utils;

import java.util.Objects;

import okio.Buffer;

public class TestUtils {

    public static Buffer fromFile(final String filename) throws Exception {
        return new Buffer().readFrom(Objects.requireNonNull(TestUtils.class.getClassLoader()).getResourceAsStream(filename));
    }

}
