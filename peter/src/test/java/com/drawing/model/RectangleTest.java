package com.drawing.model;

import org.junit.Assert;
import org.junit.Test;

public class RectangleTest {
    @Test
    public void create() throws Exception {
        new Rectangle(1, 2, 1, 3);
    }

    @Test
    public void create1() {
        new Rectangle(1, 2, 3, 4);
    }

    @Test
    public void create2() throws Exception {
        Rectangle line1 = new Rectangle(1, 2, 1, 3);
        Rectangle line2 = new Rectangle(1, 2, 1, 3);
        Assert.assertEquals(line1, line2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create3() throws Exception {
        new Rectangle(-1, 2, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create4() throws Exception {
        new Rectangle(1, -2, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create5() throws Exception {
        new Rectangle(1, 2, -1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create6() throws Exception {
        new Rectangle(1, 2, 1, -2);
    }
}