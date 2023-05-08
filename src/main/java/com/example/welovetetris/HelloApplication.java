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

/**
 * @author Winona Wherley
 * @author Karly Ripper
 *
 * This class creates a Board object and uses the Board to create and run a GUI so that our
 * Tetris game can actually be played by people.
 */
public class HelloApplication extends Application {
    public static final int BOARD_HEIGHT = 20;
    Board b = new Board();
    boolean[][] board = b.getArray();

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
        //trying to use git


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

    /**
     * ends the game if a piece reaches the top
     * otherwise, clears any full rows and makes a new frame to keep the game going
     * @return the String of the current frame
     */
    public String nextFrame() {

        //ends game if a piece has reached the top
        if (!b.topRowIsEmpty()) {
            System.exit(0);
        }

        //checks if any rows are empty and clears them if they are
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            b.clearRow(i);
        }

        return b.makeFrame();
    }

    public static void main(String[] args) {
        launch();
    }
}