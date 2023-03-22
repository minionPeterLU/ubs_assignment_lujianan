package com.drawing.model;

import org.junit.Before;
import org.junit.Test;

import com.drawing.controller.BucketFillCommand;
import com.drawing.controller.DrawLineCommand;
import com.drawing.controller.DrawRectangleCommand;

import static org.junit.Assert.assertEquals;

public class ItemFactoryTest {

    private ItemFactory itemFactory;

    @Before
    public void setUp() throws Exception {
        itemFactory = new ItemFactory();
    }


    @Test
    public void getItem_DrawLineCommand() throws Exception {
        DrawLineCommand drawLineCommand = new DrawLineCommand( "1", "2", "1", "4");
        assertEquals(itemFactory.getItem(drawLineCommand), new Line(1, 2, 1, 4));
    }

    @Test
    public void getItem_DrawRectangleCommand() throws Exception {
        DrawRectangleCommand drawLineCommand = new DrawRectangleCommand( "1", "2", "3", "4");
        assertEquals(itemFactory.getItem(drawLineCommand), new Rectangle(1, 2, 3, 4));
    }

    @Test
    public void getItem_BucketFillCommand() throws Exception {
        BucketFillCommand drawLineCommand = new BucketFillCommand( "2", "3", "o");
        assertEquals(itemFactory.getItem(drawLineCommand), new BucketFill(2, 3, 'o'));
    }

    @Test
    public void getItem_null() throws Exception {
        assertEquals(itemFactory.getItem(null), null);
    }
}
