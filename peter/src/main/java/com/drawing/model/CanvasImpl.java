package com.drawing.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CanvasImpl implements Canvas {
    private static final char HORIZONTAL_EDGE_CHAR = '-';
    private static final char VERTICAL_EDGE_CHAR   = '|';
    private static final char LINE_CHAR            = 'x';

    private final char[][]           cachedCanvasArray;
    private final int width;
    private final int height;
    // to support advanced operation such as: undo, redo
    private       LinkedList<Item> items;
    private final String             horizontalEdge;

    public CanvasImpl(int w, int h) {
        width = w;
        height = h;
        items = new LinkedList<>();

        cachedCanvasArray = new char[this.height][this.width];
        Arrays.stream(cachedCanvasArray).forEach(chars -> Arrays.fill(chars, ' '));

        horizontalEdge = Stream.generate(() -> String.valueOf(HORIZONTAL_EDGE_CHAR)).limit(width + 2).collect(Collectors.joining());
    }

    @Override
    public void addItem(Item item) throws InvalidItemException {
        items.add(item);
        if (item instanceof Line) {
            addLine((Line) item);
        } else if (item instanceof Rectangle) {
            addRectangle((Rectangle) item);
        } else if (item instanceof BucketFill) {
            addBucketFill((BucketFill) item);
        }
    }

    @Override
    public String render() {
        StringBuilder builder = new StringBuilder();
        builder.append(horizontalEdge).append("\n");
        for (int i = 0; i < this.height; i++) {
            builder.append(VERTICAL_EDGE_CHAR);
            for (int j = 0; j < this.width; j++) {
                builder.append(cachedCanvasArray[i][j]);
            }
            builder.append(VERTICAL_EDGE_CHAR);
            builder.append("\n");
        }
        builder.append(horizontalEdge);
        return builder.toString();
    }

    private void addBucketFill(BucketFill bucketFill) {
        if (isOutside(bucketFill.getX(), bucketFill.getY())) {
            throw new InvalidItemException("Bucket fill point is outside of canvas");
        }
        fillBucket(bucketFill.getX(), bucketFill.getY(), bucketFill.getCharacter());
    }

    private void addRectangle(Rectangle rec) {
        if (isOutside(rec.getX1(), rec.getY1())) {
            throw new InvalidItemException("Rectangle is outside of canvas");
        }
        Rectangle(rec.getX1(), rec.getY1(), rec.getX2(), rec.getY2());
    }

    private void addLine(Line line) {
        if (isOutside(line.getX1(), line.getY1())) {
            throw new InvalidItemException("Line is outside of canvas");
        }

        //trim the part the is outside
        if (line.getX2() >= width) {
            line.setX2(width);
        }
        if (line.getY2() >= height) {
            line.setY2(height);
        }
        Line(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    private void Line(int x1, int y1, int x2, int y2) {
        //row by row
        for (int row = y1 - 1; row <= y2 - 1 && row < height; row++) {
            //col by col
            for (int col = x1 - 1; col <= x2 - 1 && col < width; col++) {
                cachedCanvasArray[row][col] = CanvasImpl.LINE_CHAR;
            }
        }
    }

    private void fillBucket(int x, int y, char mchar) {
        char         originalChar = cachedCanvasArray[y - 1][x - 1];
        Stack<Point> stack        = new Stack<>();
        stack.add(new Point(y - 1, x - 1));
        //BFS traversal
        while (!stack.isEmpty()) {
            Point pop = stack.pop();
            if (cachedCanvasArray[pop.getX()][pop.getY()] == originalChar) {
                cachedCanvasArray[pop.getX()][pop.getY()] = mchar;
            }
            if (pop.getX() - 1 >= 0 && cachedCanvasArray[pop.getX() - 1][pop.getY()] == originalChar) {
                stack.add(new Point(pop.getX() - 1, pop.getY()));
            }
            if (pop.getX() + 1 < height && cachedCanvasArray[pop.getX() + 1][pop.getY()] == originalChar) {
                stack.add(new Point(pop.getX() + 1, pop.getY()));
            }
            if (pop.getY() - 1 >= 0 && cachedCanvasArray[pop.getX()][pop.getY() - 1] == originalChar) {
                stack.add(new Point(pop.getX(), pop.getY() - 1));
            }
            if (pop.getY() + 1 < width && cachedCanvasArray[pop.getX()][pop.getY() + 1] == originalChar) {
                stack.add(new Point(pop.getX(), pop.getY() + 1));
            }
        }
    }

    private void Rectangle(int x1, int y1, int x2, int y2) {
        //top edge
        Line(x1, y1, x2, y1);
        //right edge
        Line(x1, y1, x1, y2);
        //bottom edge
        Line(x2, y1, x2, y2);
        //right edge
        Line(x1, y2, x2, y2);
    }

    private boolean isOutside(int x, int y) {
        return x < 1 || x >= width || y < 1 || y >= height;
    }
}