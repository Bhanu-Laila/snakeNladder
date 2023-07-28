package com.example.snakenladder;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.awt.*;

public class Player {


        private Circle coin;
        private int currentPosition;
        private String name;

        private static Board gameBoard = new Board();

        public Player(int tileSize, Color coinColor, String playerName){
            coin = new Circle(tileSize/2);
            coin.setFill(coinColor);
            currentPosition=1;
            movePlayer(0);
            name=playerName;
        }



        private TranslateTransition translateAnimation(int diceValue){
            TranslateTransition animate=new TranslateTransition(Duration.millis(200*diceValue),coin);
            animate.setToX(gameBoard.getXCoordinate(currentPosition));
            animate.setToY(gameBoard.getYCoordinate(currentPosition));
            animate.setAutoReverse(false);
            return animate;

        }

        public void movePlayer(int diceValue){

            if(currentPosition+diceValue<=100){
                currentPosition +=diceValue;
                translateAnimation(diceValue);


                int newPosition = gameBoard.getNewPosition(currentPosition);
                if(newPosition!=currentPosition && newPosition!=-1){
                    currentPosition=newPosition;
                    translateAnimation(6);
                }
                int x = gameBoard.getXCoordinate(currentPosition);
                int y = gameBoard.getYCoordinate(currentPosition);
                coin.setTranslateX(x);
                coin.setTranslateY(y);
            }





        }



        public void startingPosition(){
            currentPosition=1;
            movePlayer(0);
        }
        boolean isWinner(){
            if(currentPosition==100)
                return true;
            return false;
        }

        public Circle getCoin() {
            return coin;
        }

        public int getCurrentPosition() {
            return currentPosition;
        }

        public String getName() {
            return name;
        }



}
