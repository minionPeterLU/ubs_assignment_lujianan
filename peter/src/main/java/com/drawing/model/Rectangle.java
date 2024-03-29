package com.drawing.model;

import com.drawing.utils.Helper;

public class Rectangle implements Item {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Rectangle(int _x1, int _y1, int _x2, int _y2) {
        Helper.shouldAllPositive(_x1, _y1, _x2, _y2);
        if ((x1 == x2 && y1 > y2) || (y1 == y2 && x1 > x2)) {
            this.x1 = _x2;
            this.y1 = _y2;
            this.x2 = _x1;
            this.y2 = _y1;
        } else {
            this.x1 = _x1;
            this.y1 = _y1;
            this.x2 = _x2;
            this.y2 = _y2;
        }
    }

    public int getX1() {
        return x1;
    }

    public Rectangle setX1(int x1) {
        this.x1 = x1;
        return this;
    }

    public int getY1() {
        return y1;
    }

    public Rectangle setY1(int y1) {
        this.y1 = y1;
        return this;
    }

    public int getX2() {
        return x2;
    }

    public Rectangle setX2(int x2) {
        this.x2 = x2;
        return this;
    }

    public int getY2() {
        return y2;
    }

    public Rectangle setY2(int y2) {
        this.y2 = y2;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (x1 != rectangle.x1) return false;
        if (y1 != rectangle.y1) return false;
        if (x2 != rectangle.x2) return false;
        return y2 == rectangle.y2;
    }

    @Override
    public int hashCode() {
        int result = x1;
        result = 31 * result + y1;
        result = 31 * result + x2;
        result = 31 * result + y2;
        return result;
    }
}