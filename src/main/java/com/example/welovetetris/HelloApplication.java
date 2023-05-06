package com.example.welovetetris;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HelloApplication extends Application {
    // set the size of the board to be displayed
    public static final int BOARD_WIDTH = 20;
    public static final int BOARD_HEIGHT = 20;

    //private int x = 0;
    //private int y = 0;
    Board b = new Board();
    //Pieces oneB = new OneBlock();
    boolean[][] board = b.getArray();
    //boolean pieceLanded = false;

    /**
     * Set up the starting scene of your application given the primaryStage (basically the window)
     * https://docs.oracle.com/javase/8/javafx/api/index.html
     *
     * @param primaryStage the primary container for scenes
     */
    @Override
    public void start(Stage primaryStage) {

        // Add a title to the application window
        primaryStage.setTitle("Hello World!");

        // prepare the scene layout to use a BorderPane -- a top, bottom, left, right, center style pane layout
        // https://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
        BorderPane layout = new BorderPane();

        // Create a new scene using this layout
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html
        // define the size of this scene
        double WINDOW_WIDTH = 1280;
        double WINDOW_HEIGHT = 720;
        Scene exampleScene = new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);

        // make this scene the initial (and for now only) scene in your application
        primaryStage.setScene(exampleScene);

        // create a new text node to display text on the interface
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/text/Text.html
        Text frame = new Text(nextFrame());
        Font frameFont = new Font("Courier New", 20);
        frame.setFont(frameFont);
        frame.setTextAlignment(TextAlignment.CENTER);
        frame.setFill(Color.ORCHID);
        exampleScene.setFill(Color.BLACK);


        // add this text field to the layout
        layout.setCenter(frame);

        // define code to run every time a KeyPressed event is detected on this window to check for ESC to close
        // NOTE: there even is of type javafx.scene.input.KeyEvent
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyEvent.html
        exampleScene.setOnKeyPressed(event -> {
            // check if the key that was pressed is the ESC key
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                System.exit(0);
            }
            else if (event.getCode().equals(KeyCode.DOWN)) {
                b.moveDown();
                frame.setText(nextFrame());
            }
            else if (event.getCode().equals(KeyCode.LEFT)) {
                b.moveLeft();
                frame.setText(nextFrame());
            }
            else if (event.getCode().equals(KeyCode.RIGHT)) {
                b.moveRight();
                frame.setText(nextFrame());
            }
        });

        //makes the piece fall every second
        TimerTask falling = new TimerTask () {
            @Override
            public void run() {
                b.moveDown();
                frame.setText(nextFrame());
            }
        };

        Timer time = new Timer();
        time.scheduleAtFixedRate(falling, 2, 1000);

        // display the interface
        primaryStage.show();
    }

    // define simple move functions to change the value of x and y
    //delete these comments? should be fine, but I'm just scared to commit to it
    /*
    public void moveDown() {
        if (y < BOARD_HEIGHT-1) {
            if (!board[y+1][x]) {
                y += 1;
            } else {
                pieceLanded = true;
                board[y][x] = true;
                x = 10;
                y = 0;
            }
        }
        else {
            pieceLanded = true;
            board[y][x] = true;
            x = 10;
            y = 0;
        }
    }

     */
    /*
    public void moveLeft() {
        if (x > 0) {
            if(!board[y][x-1]) {
                x -= 1;
            }
        }
    }

     */
    /*
    public void moveRight() {
        if (x < BOARD_WIDTH-1) {
            if(!board[y][x+1]) {
                x += 1;
            }
        }
    }

     */

    public String nextFrame() {
        int squareCounter = 1;

        if (!b.topRowIsEmpty()) {
            System.exit(0);
        }

        //checks if any rows are empty and clears them if they are
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            b.clearRow(i);
        }

        /*
        char current = 'C';

        StringBuilder frame = new StringBuilder();
        frame.append("T E T R I S\n");
        frame.append("-".repeat(BOARD_WIDTH));

        // add each row of the board

        for (int r = 0; r < BOARD_HEIGHT; r++) {
            frame.append('\n');
            // add a left border
            frame.append('|');
            // fill in this row
            for (int c = 0; c < BOARD_WIDTH; c++) {
                //adding landed pieces
                if (board[r][c]) {//we stop in the upper left corner and draw the whole piece
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {//can we do it without the for loops and just draw a letter at a time instead of drawing a group at a time?
                            if(oneB.occupies(i, j)) {
                                frame.append('F');
                            }
                        }
                    }
                    //I think that we need to be replacing things, and we are adding them instead
                    //we aren't adding extra spaces, we are adding extra letters to a board with the same amount of spaces as before
                    //so a line that used to have 19 spaces plus a letter now has 19 spaces plus 2 letters
                    //thus, it looks like spaces have been added, but really we just need to replace spaces with letters, get it?

                    //frame.append('F');
                } else {
                    //moving piece currently being played with
                    if (r == b.getY() && c == b.getX()) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if(oneB.occupies(i, j)) {
                                    frame.append(current);
                                }
                            }
                        }

                    }
                    else {
                        frame.append(' ');
                    }
                }
            }
            // add a right border
            frame.append('|');
        }
        // add a bottom border
        frame.append('\n');
        frame.append("-".repeat(BOARD_WIDTH));

        return frame.toString();

         */
        return b.makeFrame();
    }

    public static void main(String[] args) {
        launch();
    }
}