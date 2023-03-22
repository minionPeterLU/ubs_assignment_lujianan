package com.drawing.model;

import com.drawing.controller.BucketFillCommand;
import com.drawing.controller.DrawItemCommand;
import com.drawing.controller.DrawLineCommand;
import com.drawing.controller.DrawRectangleCommand;

public class ItemFactory {

    public Item getItem(DrawItemCommand command) {
        if (command instanceof DrawLineCommand) {
            DrawLineCommand cmd = (DrawLineCommand) command;
            return new Line(cmd.getX1(), cmd.getY1(), cmd.getX2(), cmd.getY2());
        } else if (command instanceof DrawRectangleCommand) {
            DrawRectangleCommand cmd = (DrawRectangleCommand) command;
            return new Rectangle(cmd.getX1(), cmd.getY1(), cmd.getX2(), cmd.getY2());
        } else if (command instanceof BucketFillCommand) {
            BucketFillCommand cmd = (BucketFillCommand) command;
            return new BucketFill(cmd.getX(), cmd.getY(), cmd.getCharacter());
        }
        return null;
    }
}
