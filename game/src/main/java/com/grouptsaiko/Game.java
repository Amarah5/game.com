package com.grouptsaiko;

import java.util.Random;
import java.util.Scanner; 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Game {
    int countLignes, column;


    public char[][] gamePlace() {
        char[][] gridGame = new char[countLignes][column];
        Random random = new Random();
        String caracteresPossibles = "....J"; 
        for (int i = 0; i < countLignes; i++) {
            for (int j = 0; j < column; j++) {
                int indexAleatoire = random.nextInt(caracteresPossibles.length());
                gridGame[i][j] = caracteresPossibles.charAt(indexAleatoire);
            }
        }
        return gridGame;
    }


    public void displayGridWithPlayer(char[][] grid, int playerX, int playerY) {
        System.out.println("\n--- Current Grid ---");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == playerY && j == playerX) {
                    System.out.print("S");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------\n");
    }

 
    public void startGame() {
        char[][] gameGrid = gamePlace();

        Joueur joueur = new Joueur(Direction.U, 0, 0);

        int maxMoves = 20;
        int score = 0;


        int totalJsOnGrid = 0;
        for (int r = 0; r < gameGrid.length; r++) {
            for (int c = 0; c < gameGrid[0].length; c++) {
                if (gameGrid[r][c] == 'J') {
                    totalJsOnGrid++;
                }
            }
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the J Collector Game!");
        System.out.println("Collect all 'J's on the grid or at least 4 'J's to win.");
        System.out.println("You have " + maxMoves + " moves.");
        System.out.println("Total 'J's on the grid: " + totalJsOnGrid);

        boolean gameWon = false;


        for (int i = 0; i < maxMoves; i++) {
            displayGridWithPlayer(gameGrid, joueur.getX(), joueur.getY());
            System.out.println("Current Score: " + score);
            System.out.println("Moves Left: " + (maxMoves - 1 - i));


            if (joueur.getY() >= 0 && joueur.getY() < gameGrid.length &&
                joueur.getX() >= 0 && joueur.getX() < gameGrid[0].length) {

                if (gameGrid[joueur.getY()][joueur.getX()] == 'J') {
                    System.out.println("You collected a 'J'!");
                    gameGrid[joueur.getY()][joueur.getX()] = '.';
                    score++;
                }
            }


            if (score == totalJsOnGrid || score ==totalJsOnGrid){
                gameWon = true;
                break;
            }

            System.out.print("Enter your move (U/D/L/R): ");
            char move;
            String inputLine = scanner.next();
            if (inputLine.isEmpty()) {
                System.out.println("No input detected. Please enter a move.");
                i--;
                continue;
            }
            move = inputLine.charAt(0);

            int prevX = joueur.getX();
            int prevY = joueur.getY();

            switch (Character.toLowerCase(move)) {
                case 'u':
                    joueur.up();
                    break;
                case 'l':
                    joueur.left();
                    break;
                case 'd':
                    joueur.down();
                    break;
                case 'r':
                    joueur.right();
                    break;
                default:
                    System.out.println("Invalid move. Please enter U, D, L, or R.");
                    i--;
                    continue;
            }


            int newX = joueur.getX();
            int newY = joueur.getY();

            if (newY < 0 || newY >= gameGrid.length || newX < 0 || newX >= gameGrid[0].length) {
                System.out.println("You hit the wall! Cannot move there.");
                joueur.setX(prevX);
                joueur.setY(prevY);
            }
        }

        System.out.println("\n--- Game Over ---");
        displayGridWithPlayer(gameGrid, joueur.getX(), joueur.getY());
        System.out.println("Final Score: " + score);

        if (gameWon) {
            System.out.println("Congratulations! You won!");
        } else {
            System.out.println("You ran out of moves. Try again!");
        }

        scanner.close(); 
    }
}