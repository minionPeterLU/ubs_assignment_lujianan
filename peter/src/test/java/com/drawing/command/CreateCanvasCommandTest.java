package com.drawing.command;

import org.junit.Test;

import com.drawing.controller.CreateCommand;
import com.drawing.controller.InvalidCommandParams;

public class CreateCanvasCommandTest {
    @Test
    public void testCreate() throws Exception {
        new CreateCommand("1", "1");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate2() throws Exception {
        new CreateCommand("-9", "1");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate3() throws Exception {
        new CreateCommand("1", "-9");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate4() throws Exception {
        new CreateCommand("3");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate6() throws Exception {
        new CreateCommand();
    }
}