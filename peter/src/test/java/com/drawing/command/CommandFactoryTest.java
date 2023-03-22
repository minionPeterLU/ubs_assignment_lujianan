package com.drawing.command;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.drawing.controller.BucketFillCommand;
import com.drawing.controller.Command;
import com.drawing.controller.CommandFactory;
import com.drawing.controller.CreateCommand;
import com.drawing.controller.DrawLineCommand;
import com.drawing.controller.DrawRectangleCommand;
import com.drawing.controller.InvalidCommandException;
import com.drawing.controller.InvalidCommandParams;

public class CommandFactoryTest {

    private CommandFactory commandFactory;

    @Before
    public void setUp() throws Exception {
        commandFactory = new CommandFactory();
    }

    @Test
    public void getCommand() throws Exception {
        commandFactory.getCommand("Q");
    }

    @org.junit.Test(expected = InvalidCommandException.class)
    public void getCommand2() throws Exception {
        commandFactory.getCommand("A");
    }

    @Test
    public void getCommand3() throws Exception {
        Command command = commandFactory.getCommand("C 20 5");
        Assert.assertThat(command, CoreMatchers.instanceOf(CreateCommand.class));
    }

    @org.junit.Test(expected = InvalidCommandParams.class)
    public void getCommand32() throws Exception {
        commandFactory.getCommand("C 20 -5");
    }

    @Test
    public void getCommand4() throws Exception {
        Command command = commandFactory.getCommand("L 1 2 1 18");
        Assert.assertThat(command, CoreMatchers.instanceOf(DrawLineCommand.class));
    }

    @org.junit.Test(expected = InvalidCommandParams.class)
    public void getCommand42() throws Exception {
        commandFactory.getCommand("L 1 2 1 -17");
    }

    @Test
    public void getCommand5() throws Exception {
        Command command = commandFactory.getCommand("R 14 1 16 3");
        Assert.assertThat(command, CoreMatchers.instanceOf(DrawRectangleCommand.class));
    }

    @org.junit.Test(expected = InvalidCommandParams.class)
    public void getCommand52() throws Exception {
        commandFactory.getCommand("R 12 1 15 -3");
    }

    @Test
    public void getCommand6() throws Exception {
        Command command = commandFactory.getCommand("B 1 2 o");
        Assert.assertThat(command, CoreMatchers.instanceOf(BucketFillCommand.class));
    }

    @org.junit.Test(expected = InvalidCommandParams.class)
    public void getCommand62() throws Exception {
        commandFactory.getCommand("B 3 3 oo");
    }


}