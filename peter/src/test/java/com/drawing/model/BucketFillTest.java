package com.drawing.model;

import org.junit.Test;

public class BucketFillTest {
    @Test
    public void create() throws Exception {
        new BucketFill(1, 2, 'o');
    }

    @Test(expected = IllegalArgumentException.class)
    public void create3() throws Exception {
        new BucketFill(-1, 2, 'o');
    }

    @Test(expected = IllegalArgumentException.class)
    public void create4() throws Exception {
        new BucketFill(1, -2, 'o');
    }
}
