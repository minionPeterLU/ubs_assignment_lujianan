package com.drawing.command;

import org.junit.Test;

import com.drawing.controller.DrawLineCommand;
import com.drawing.controller.InvalidCommandParams;

public class DrawLineCommandTest {
    @Test
    public void testCreate() throws Exception {
        new DrawLineCommand("1", "1", "1", "2");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate1() throws Exception {
        new DrawLineCommand("-1", "1", "1", "2");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate2() throws Exception {
        new DrawLineCommand("1", "-1", "1", "2");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate3() throws Exception {
        new DrawLineCommand("1", "1");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate4() throws Exception {
        new DrawLineCommand("1");
    }

    @Test(expected = InvalidCommandParams.class)
    public void testCreate5() throws Exception {
        new DrawLineCommand();
    }
}