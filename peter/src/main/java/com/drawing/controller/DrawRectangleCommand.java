package com.drawing.controller;


import com.drawing.utils.Helper;

public class DrawRectangleCommand implements DrawItemCommand {
    private static final String helpMessage = "R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and\n" +
            "                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n" +
            "                using the 'x' character.";
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public DrawRectangleCommand(String... params) {
        if (params.length < 4)
            throw new InvalidCommandParams("Draw Rectangle command expects 4 params", helpMessage);
        try {
            x1 = Helper.toPositiveInt(params[0]);
            y1 = Helper.toPositiveInt(params[1]);
            x2 = Helper.toPositiveInt(params[2]);
            y2 = Helper.toPositiveInt(params[3]);
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandParams("Number can not be negative", helpMessage);
        }
    }

    public int getX1() {
        return x1;
    }

    public DrawRectangleCommand setX1(int x1) {
        this.x1 = x1;
        return this;
    }

    public int getY1() {
        return y1;
    }

    public DrawRectangleCommand setY1(int y1) {
        this.y1 = y1;
        return this;
    }

    public int getX2() {
        return x2;
    }

    public DrawRectangleCommand setX2(int x2) {
        this.x2 = x2;
        return this;
    }

    public int getY2() {
        return y2;
    }

    public DrawRectangleCommand setY2(int y2) {
        this.y2 = y2;
        return this;
    }
}
