package git.soulbgm.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class SequenceBuilderTest {

    @Test
    public void uuid() {
        SequenceBuilder.uuid();
    }

    @Test
    public void sequenceId() {
        SequenceBuilder.sequenceId();
    }

    @Test
    public void test() {
        int count = 100000;

        long start = SystemClock.now();
        for (int i = 0; i < count; i++) {
            uuid();
        }
        long end = SystemClock.now();
        System.out.println(end - start);

        start = SystemClock.now();
        for (int i = 0; i < count; i++) {
            sequenceId();
        }
        end = SystemClock.now();
        System.out.println(end - start);
    }
}