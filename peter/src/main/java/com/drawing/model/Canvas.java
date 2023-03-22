package com.drawing.model;

public interface Canvas {

    void addItem(Item item) throws InvalidItemException;

    String render();
}
