package com.drawing.command;

import org.junit.Test;

import com.drawing.controller.BucketFillCommand;
import com.drawing.controller.InvalidCommandParams;

public class BucketFillCommandTest {
    @Test
    public void testCreate() throws Exception {
        new BucketFillCommand( "1", "2", "o");
    }

    @Test
    public void testCreate1() throws Exception {
        new BucketFillCommand( "2", "2", "o");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate2() throws Exception {
        new BucketFillCommand( "-1", "1", "o");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate3() throws Exception {
        new BucketFillCommand( "1", "-1", "o");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate4() throws Exception {
        new BucketFillCommand( "1", "1");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate5() throws Exception {
        new BucketFillCommand( "1");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate6() throws Exception {
        new BucketFillCommand();
    }
}