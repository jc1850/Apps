package de.storchp.fdroidbuildstatus.utils;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LoglinesBuffer implements Iterable<String> {

    private final String[] loglines;
    private int startPos = -1;
    private int lastPos = -1;

    public LoglinesBuffer(int size) {
        this.loglines = new String[size];
    }

    public void add(String line) {
        int nextPos = (lastPos + 1) % loglines.length;
        loglines[nextPos] = line;
        lastPos = nextPos;
        if (startPos < 0) {
            startPos = 0;
        } else if (startPos >= lastPos) {
            startPos = (lastPos + 1) % loglines.length;
        }
    }

    @NonNull
    public String toString() {
        return StreamSupport.stream(this.spliterator(), false).collect(Collectors.joining("\n"));
    }

    @NonNull
    @Override
    public Iterator<String> iterator() {
        return new LoglinesBuffer.LoglinesIterator();
    }

    private class LoglinesIterator implements Iterator<String> {

        private int nextPos = startPos;

        @Override
        public boolean hasNext() {
            return nextPos >= 0 && nextPos < loglines.length;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                return null;
            }
            var nextLine = loglines[nextPos];
            if (nextPos == lastPos) {
                nextPos = -1;
            } else if (nextPos == loglines.length - 1) {
                nextPos = 0;
            } else {
                nextPos++;
            }
            return nextLine;
        }
    }

}
