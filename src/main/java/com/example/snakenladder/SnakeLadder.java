package com.example.snakenladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tileSize=40, width=10, height=10;

    public static final int buttonline=height*tileSize+50, infoLine=buttonline-30;

    private static Dice dice = new Dice();
    private Player playerOne, playerTwo;

    private boolean gameStarted= false, playerOneTurn=false, playerTwoTurn=false;

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize + 100);
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().addAll(tile);
            }
        }

        Image img= new Image("C:\\Users\\dell\\IdeaProjects\\snakeNladder\\src\\main\\snakenladder.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);


        //Playerbuttons
        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player Two");
        Button startButton = new Button("Start");

        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(buttonline);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(buttonline);
        playerTwoButton.setDisable(true);
        startButton.setTranslateX(180);
        startButton.setTranslateY(buttonline);

        //infolabel

        Label playerOneLabel = new Label("Your turn P1");
        Label playerTwoLabel = new Label("Your turn P2");
        Label diceLabel = new Label("Start the Game");

        playerOneLabel.setTranslateX(20);
        playerOneLabel.setTranslateY(infoLine);

        playerTwoLabel.setTranslateX(300);
        playerTwoLabel.setTranslateY(infoLine);

        diceLabel.setTranslateX(160);
        diceLabel.setTranslateY(infoLine);

        playerOne = new Player(tileSize, Color.BLACK, "Bhanu");
        playerTwo = new Player(tileSize-5, Color.BEIGE, "Laila");

        //Player Action

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value:" + diceValue);
                        playerOne.movePlayer(diceValue);
                        //Winning condition
                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is " + playerOne.getName());
                            playerTwoTurn=false;
                            playerOneTurn=false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");

                        }
                        else{
                            playerOneTurn=false;
                            playerTwoTurn=true;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your turn " + playerTwo.getName());
                        }

                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value:" + diceValue);
                        playerTwo.movePlayer(diceValue);
                        //Winning condition
                        if(playerTwo.isWinner()){
                            diceLabel.setText("Winner is " + playerTwo.getName());
                            playerTwoTurn=false;
                            playerOneTurn=false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");

                        }
                        else{
                            playerTwoTurn=false;
                            playerOneTurn=true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your turn " + playerOne.getName());
                        }

                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn=true;
                playerOneButton.setDisable(false);
                playerOne.startingPosition();
                playerOneLabel.setText("Your Turn " + playerOne.getName());
                playerTwoTurn=false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();
            }
        });
        root.getChildren().addAll(board, playerOneButton, playerTwoButton,
                startButton, playerTwoLabel, playerOneLabel,
                diceLabel, playerOne.getCoin(), playerTwo.getCoin() );


        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}