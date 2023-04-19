package de.storchp.fdroidbuildstatus.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class LoglinesBufferTest {

    @Test
    public void testBufferEmpty() {
        var buffer = new LoglinesBuffer(5);

        for (var line : buffer) {
            fail();
        }
        assertThat(buffer.toString()).isEmpty();
    }

    @Test
    public void testBufferLessLines() {
        var buffer = new LoglinesBuffer(5);
        buffer.add("line1");
        buffer.add("line2");
        buffer.add("line3");

        int lineNr = 1;
        for (var line : buffer) {
            assertThat(line).isEqualTo("line" + lineNr);
            lineNr++;
        }
        assertThat(lineNr).isEqualTo(4);
        assertThat(buffer.toString()).isEqualTo("line1\nline2\nline3");
    }

    @Test
    public void testBufferExactLines() {
        var buffer = new LoglinesBuffer(5);
        buffer.add("line1");
        buffer.add("line2");
        buffer.add("line3");
        buffer.add("line4");
        buffer.add("line5");

        int lineNr = 1;
        for (var line : buffer) {
            assertThat(line).isEqualTo("line" + lineNr);
            lineNr++;
        }
        assertThat(lineNr).isEqualTo(6);
        assertThat(buffer.toString()).isEqualTo("line1\nline2\nline3\nline4\nline5");
    }

    @Test
    public void testBufferMoreLines() {
        var buffer = new LoglinesBuffer(5);
        buffer.add("line1");
        buffer.add("line2");
        buffer.add("line3");
        buffer.add("line4");
        buffer.add("line5");
        buffer.add("line6");
        buffer.add("line7");

        int lineNr = 1;
        for (var line : buffer) {
            assertThat(line).isEqualTo("line" + (lineNr + 2));
            lineNr++;
        }
        assertThat(buffer.toString()).isEqualTo("line3\nline4\nline5\nline6\nline7");
    }

}