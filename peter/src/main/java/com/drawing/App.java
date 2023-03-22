package com.drawing;

import com.drawing.controller.*;
import com.drawing.model.Canvas;
import com.drawing.model.CanvasImpl;
import com.drawing.model.ItemFactory;
import com.drawing.model.InvalidItemException;

import java.util.Scanner;

public class App {  
    private static Canvas         canvas;
    private static Scanner        scanner;
    private static CommandFactory commandFactory;
    private static ItemFactory    itemFactory;

    public static void main(String[] args) throws NumberFormatException, InterruptedException {
        scanner = new Scanner(System.in);
        commandFactory = new CommandFactory();
        itemFactory = new ItemFactory();

        System.out.print("Enter command:");

        while (true) {
            process(scanner.nextLine());
            System.out.print("Enter command:");
        }
    }

    private static void process(String commandLine) {
        Command command = null;

        try {
            command = commandFactory.getCommand(commandLine);
        } catch (InvalidCommandException e) {
            System.out.println("Please enter a valid command.");
        } catch (InvalidCommandParams invalidCommandParams) {
            System.out.println("Command syntax is not correct: " + invalidCommandParams.getMessage());
            System.out.println("Refer to following correct syntax: \n" + invalidCommandParams.getHelpMessage());
        }

        if (command instanceof QuitCommand) {
            quit();
        }

        if (command instanceof CreateCommand) {
            createNewCanvas((CreateCommand) command);
            return;
        }

        if (command instanceof DrawItemCommand) {
            draw((DrawItemCommand) command);
        }
    }

    private static void draw(DrawItemCommand command) {
        if (canvas == null) {
            System.out.println("You need to create a canvas first");
            return;
        }
        try {
            canvas.addItem(itemFactory.getItem(command));
            System.out.println(canvas.render());
        } catch (InvalidItemException e) {
            System.out.println("Can not add the model to canvas: " + e.getMessage());
        }
    }

    private static void createNewCanvas(CreateCommand command) {
        canvas = new CanvasImpl(command.getWidth(), command.getHeight());
        System.out.println(canvas.render());
    }

    private static void quit() {
        scanner.close();
        System.out.println("Exiting...");
        System.exit(0);
    }
}
