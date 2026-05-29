package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.util.Random;


public class Game extends Application {

    String[] words = {

            "apple",
            "bread",
            "chair",
            "cloud",
            "dance",
            "dream",
            "earth",
            "flame",
            "grape",
            "heart",
            "banana",
            "coffee",
            "little",
            "bridge",
            "orange",
            "planet",
            "school",
            "winter",
            "yellow",
            "rabbit",
            "teacher",
            "monster",
            "freedom",
            "diamond",
            "morning",
            "captain",
            "picture",
            "country",
            "plastic",
            "airport",
            "elephant",
            "computer",
            "football",
            "hospital",
            "mountain"
    };
    Random randomWord = new Random();
    String word = words[randomWord.nextInt(words.length)];
    int length = word.length();
    int x = 0;
    String text = "_ ".repeat(length);

    Label messageLabel = new Label("");
    Label messageLabel2 = new Label("");
    Label hangmanLabel = new Label("");
    Label theWord = new Label(text);
    Label invalidChar = new Label("");
    Label hanger = new Label("");
    Label messageLabel12 = new Label("");

    Button registerButton = new Button("Start");
    Button restartButton = new Button("TRY AGAIN");
    Button tryButton = new Button("TRY");

    Pane myScreenElements = new Pane();
    Pane loginPane = new Pane();

    TextField userLetter = new TextField("");

    @Override
    public void start(Stage stage) throws IOException {

        registerButton.setLayoutX(180);
        registerButton.setLayoutY(180);

        loginPane.getChildren().add(registerButton);

        Scene loginScene = new Scene(loginPane,400,400);
        Scene gameScene = new Scene(myScreenElements, 400,400);

        tryButton.setLayoutX(260);
        tryButton.setLayoutY(300);

        theWord.setLayoutX(50);
        theWord.setLayoutY(220);

        userLetter.setLayoutX(50);
        userLetter.setLayoutY(300);


        hangmanLabel.setLayoutX(50);
        hangmanLabel.setLayoutY(100);

        userLetter.setPromptText("enter a letter");

        myScreenElements.getChildren().addAll(
                theWord,
                tryButton,
                messageLabel,
                messageLabel2,
                messageLabel12,
                hangmanLabel,
                invalidChar,
                hanger,
                restartButton,
                userLetter);

        hangmanLabel.setStyle("-fx-font-family: monospace;");
        hangmanLabel.setScaleX(3);
        hangmanLabel.setScaleY(3);

        messageLabel.setScaleX(2);
        messageLabel.setScaleY(2);
        messageLabel.setLayoutX(280);
        messageLabel.setLayoutY(250);

        messageLabel2.setScaleX(2);
        messageLabel2.setScaleY(2);
        messageLabel2.setLayoutX(280);
        messageLabel2.setLayoutY(250);

        messageLabel12.setLayoutX(50);
        messageLabel12.setLayoutY(270);

        invalidChar.setLayoutX(50);
        invalidChar.setLayoutY(270);

        theWord.setStyle("-fx-font-size: 30px");

        hanger.setStyle("-fx-font-family: monospace; -fx-font-size: 30px;");
        hanger.setLayoutX(5);
        hanger.setLayoutY(5);

        hanger.setText(
                        "  ----\n" +
                                " |    |\n" +
                                " |\n" +
                                " |\n" +
                                " |\n" +
                                " |\n" +
                                " |"
                );

        restartButton.setLayoutX(260);
        restartButton.setLayoutY(350);
        restartButton.setVisible(false);

        restartButton.setOnAction(e -> {
            restartButtonMethod();
        });


        tryButton.setPrefSize(80,40);

        tryButton.setOnAction(e -> {
            if(invalidCharacter()){
                return;
            }
            checkLetter();
            userLetter.clear();
        });

        registerButton.setOnAction(e -> {
            stage.setScene(gameScene);
        });

        registerButton.setLayoutX(170);
        registerButton.setLayoutY(180);

        stage.setTitle("HANGMAN GAME");
        stage.setScene(loginScene);
        stage.show();

    }

    public void restartButtonMethod(){

        word = words[randomWord.nextInt(words.length)];

        length = word.length();

        text = "_ ".repeat(length);

        x = 0;

        theWord.setText(text);

        messageLabel.setText("");
        messageLabel2.setText("");
        invalidChar.setText("");
        messageLabel12.setText("");
        hangmanLabel.setText("");

        userLetter.clear();

        tryButton.setDisable(false);
        userLetter.setDisable(false);

        restartButton.setVisible(false);

        hanger.setText(
                "  ----\n" +
                        " |    |\n" +
                        " |\n" +
                        " |\n" +
                        " |\n" +
                        " |\n" +
                        " |"
        );
    }



    public void myHangman() {

        if (x == 0) {
            hangmanLabel.setText("     O");

        } else if (x == 1) {
            hangmanLabel.setText("     O\n    /");

        } else if (x == 2) {
            hangmanLabel.setText("     O\n    /|");

        } else if (x == 3) {
            hangmanLabel.setText("     O\n    /|\\");

        } else if (x == 4) {
            hangmanLabel.setText("     O\n    /|\\\n    /");

        } else if (x == 5) {
            hangmanLabel.setText("     O\n    /|\\\n    / \\");
        }
    }

    public static String changingLetter(String text, int index, String newChar) {

        return text.substring(0, index)
                + newChar
                + text.substring(index + 1);

    }


    public boolean invalidCharacter(){

        boolean invalid = false;

        if(userLetter.getText().length() != 1){
            invalidChar.setText("INVALID CHARACTER ENTER A LETTER");
            invalid = true;
        } else if (!userLetter.getText().matches("[a-zA-Z]")){
            invalidChar.setText("INVALID CHARACTER ENTER A LETTER");
            invalid = true;
        } else {
            invalidChar.setText("");
        }
        return invalid;
    }



    public void checkLetter() {


        String lowerCase = userLetter.getText().toLowerCase();

        boolean changed = false;



        for (int j = 0; j < length; j++) {

            String Letters = String.valueOf(word.charAt(j));

            if (Letters.equals(lowerCase)) {
                text = changingLetter(text, j*2, lowerCase);
                changed = true;
                theWord.setText(text);

            }
        }
        if (!changed) {
            myHangman();
            x++;
        }
        if (x == 6) {
            messageLabel.setText("YOU LOST");
            messageLabel12.setText("THE WORD WAS: " + word);
            tryButton.setDisable(true);
            userLetter.setDisable(true);
            restartButton.setVisible(true);

        }
        if (text.replace(" ", "").equals(word)) {
            messageLabel2.setText("YOU WON!");
            tryButton.setDisable(true);
            userLetter.setDisable(true);
            restartButton.setVisible(true);
        }
    }

    public static void main(String[] args) {
        launch(args);

    }
}


















