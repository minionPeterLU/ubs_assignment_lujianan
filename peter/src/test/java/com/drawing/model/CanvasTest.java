package com.drawing.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CanvasTest {
    private Canvas canvas;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        canvas = new CanvasImpl(20, 4);
    }

    // empty canvas
    @Test
    public void create() throws Exception {

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|                    |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "----------------------");
    }

    // add vertical line
    @Test
    public void addItem() throws Exception {
        Line line = new Line(1, 2, 1, 3);
        canvas.addItem(line);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|                    |\n" +
            "|x                   |\n" +
            "|x                   |\n" +
            "|                    |\n" +
            "----------------------");

    }

    // add vertical line that is trimmed
    @Test
    public void addItem2() throws Exception {
        Line line = new Line(1, 2, 1, 22);
        canvas.addItem(line);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|                    |\n" +
            "|x                   |\n" +
            "|x                   |\n" +
            "|x                   |\n" +
            "----------------------");

    }

    // add horizontal line
    @Test
    public void addItem3() throws Exception {
        Line line = new Line(2, 2, 4, 2);
        canvas.addItem(line);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|                    |\n" +
            "| xxx                |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "----------------------");
    }

    // add horizontal line that is trimmed
    @Test
    public void addItem4() throws Exception {
        Line line = new Line(1, 2, 30, 2);
        canvas.addItem(line);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|                    |\n" +
            "|xxxxxxxxxxxxxxxxxxxx|\n" +
            "|                    |\n" +
            "|                    |\n" +
            "----------------------");
    }

    // line outside of canvas
    @Test()
    public void addItem6() throws Exception {
        Line line1 = new Line(100, 20, 100, 22);
        expectedException.expect(InvalidItemException.class);
        canvas.addItem(line1);
    }

    // add rectangle
    @Test()
    public void addItem7() throws Exception {
        Rectangle rectangle = new Rectangle(100, 20, 100, 22);
        expectedException.expect(InvalidItemException.class);
        canvas.addItem(rectangle);
    }

    // add rectangle
    @Test()
    public void addItem8() throws Exception {
        Rectangle rectangle = new Rectangle(14, 1, 18, 3);
        canvas.addItem(rectangle);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|             xxxxx  |\n" +
            "|             x   x  |\n" +
            "|             xxxxx  |\n" +
            "|                    |\n" +
            "----------------------");
    }

    // add rectangle that exceed the canvas height
    @Test()
    public void addItem9() throws Exception {
        Rectangle rectangle = new Rectangle(2, 1, 4, 30);
        canvas.addItem(rectangle);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "| xxx                |\n" +
            "| x x                |\n" +
            "| x x                |\n" +
            "| x x                |\n" +
            "----------------------");
    }

    // add rectangle that exceed the canvas width
    @Test()
    public void addItem10() throws Exception {
        Rectangle rectangle = new Rectangle(2, 1, 40, 3);
        canvas.addItem(rectangle);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "| xxxxxxxxxxxxxxxxxxx|\n" +
            "| x                  |\n" +
            "| xxxxxxxxxxxxxxxxxxx|\n" +
            "|                    |\n" +
            "----------------------");
    }

    // add rectangle that exceed both the canvas width and height
    @Test()
    public void addItem11() throws Exception {
        Rectangle rectangle = new Rectangle(2, 1, 40, 30);
        canvas.addItem(rectangle);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "| xxxxxxxxxxxxxxxxxxx|\n" +
            "| x                  |\n" +
            "| x                  |\n" +
            "| x                  |\n" +
            "----------------------");
    }

    // add rectangle that is outside
    @Test()
    public void addItem111() throws Exception {
        Rectangle rectangle = new Rectangle(20, 100, 40, 102);
        expectedException.expect(InvalidItemException.class);
        canvas.addItem(rectangle);
    }

    // bucket fill and empty canvas
    @Test()
    public void addItem12() throws Exception {
        BucketFill rectangle = new BucketFill(2, 1, 'o');
        canvas.addItem(rectangle);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|oooooooooooooooooooo|\n" +
            "|oooooooooooooooooooo|\n" +
            "|oooooooooooooooooooo|\n" +
            "|oooooooooooooooooooo|\n" +
            "----------------------");
    }

    // fill a vertical line
    @Test
    public void addItem13() throws Exception {
        Line line = new Line(1, 2, 1, 3);
        canvas.addItem(line);
        BucketFill bucketFill = new BucketFill(1, 2, 'o');
        canvas.addItem(bucketFill);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|                    |\n" +
            "|o                   |\n" +
            "|o                   |\n" +
            "|                    |\n" +
            "----------------------");

    }

    // fill a rectangle edge
    @Test
    public void addItem14() throws Exception {
        Rectangle rectangle = new Rectangle(14, 1, 18, 3);
        canvas.addItem(rectangle);
        BucketFill bucketFill = new BucketFill(14, 1, 'o');
        canvas.addItem(bucketFill);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|             ooooo  |\n" +
            "|             o   o  |\n" +
            "|             ooooo  |\n" +
            "|                    |\n" +
            "----------------------");

    }

    // fill a rectangle content
    @Test
    public void addItem15() throws Exception {
        Rectangle rectangle = new Rectangle(14, 1, 18, 4);
        canvas.addItem(rectangle);
        BucketFill bucketFill = new BucketFill(15, 2, 'o');
        canvas.addItem(bucketFill);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|             xxxxx  |\n" +
            "|             xooox  |\n" +
            "|             xooox  |\n" +
            "|             xxxxx  |\n" +
            "----------------------");

    }

    //fill blank space
    @Test
    public void addItem16() throws Exception {
        Rectangle rectangle = new Rectangle(14, 1, 18, 4);
        canvas.addItem(rectangle);
        BucketFill bucketFill = new BucketFill(10, 1, 'o');
        canvas.addItem(bucketFill);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|oooooooooooooxxxxx  |\n" +
            "|ooooooooooooox   x  |\n" +
            "|ooooooooooooox   x  |\n" +
            "|oooooooooooooxxxxx  |\n" +
            "----------------------");
    }

    /*L 1 2 1 22
    L 6 3 166 3
    R 14 1 18 3
    B 1 3 o*/


    // Add multiple model to canvas
    @Test
    public void addItem5() throws Exception {
        Line line1 = new Line(1, 2, 6, 2);
        canvas.addItem(line1);

        Line line2 = new Line(6, 3, 6, 4);
        canvas.addItem(line2);

        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|                    |\n" +
            "|xxxxxx              |\n" +
            "|     x              |\n" +
            "|     x              |\n" +
            "----------------------");

        canvas.addItem(new Rectangle(14, 1, 18, 3));
        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|             xxxxx  |\n" +
            "|xxxxxx       x   x  |\n" +
            "|     x       xxxxx  |\n" +
            "|     x              |\n" +
            "----------------------");

        canvas.addItem(new BucketFill(10, 3, 'o'));
        Assert.assertEquals(canvas.render(),
            "----------------------\n" +
            "|oooooooooooooxxxxxoo|\n" +
            "|xxxxxxooooooox   xoo|\n" +
            "|     xoooooooxxxxxoo|\n" +
            "|     xoooooooooooooo|\n" +
            "----------------------");
    }
}
