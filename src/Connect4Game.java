//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Random;
import java.util.Scanner;

public class Connect4Game {
    private Board board = new Board();
    private final String color1;
    private final String color2;
    private boolean is1playing;

    public Connect4Game(String color1, String color2) {
        this.color1 = color1;
        this.color2 = color2;
        this.is1playing = (new Random()).nextBoolean();
    }

    public boolean checkForWinner(int column) {
        String winningcolor;
        if (this.is1playing) {
            winningcolor = this.color1;
        } else {
            winningcolor = this.color2;
        }

        return this.board.checkForWinner(column, winningcolor);
    }

    public void reset() {
        this.board = new Board();
        this.is1playing = (new Random()).nextBoolean();
    }

    public void startGame() {
        boolean running = true;

        while(running) {
            this.board.printBoard();
            String color;
            if (this.is1playing) {
                color = this.color1;
                System.out.println("Player " + this.color1 + " turn");
            } else {
                color = this.color2;
                System.out.println("Player " + this.color2 + " turn");
            }

            System.out.println("Please select in the column yo want to put piece in. ");
            System.out.println("Choose between 1 and " + Board.getColumns() + ": ");
            Scanner input = new Scanner(System.in);
            int column = input.nextInt() - 1;
            boolean success = this.board.addpiece(column, color);
            if (success) {
                if (this.checkForWinner(column)) {
                    this.board.printBoard();
                    if (this.is1playing) {
                        System.out.println("Player " + this.color1 + " won.");
                    } else {
                        System.out.println("Player " + this.color2 + " won.");
                    }

                    System.out.println("Would you like to play again? ");
                    System.out.print("Y for yes, N for no: ");
                    Scanner input2 = new Scanner(System.in);
                    String playAgain = input2.nextLine();
                    if (playAgain.toLowerCase().equals("y")) {
                        this.reset();
                    } else {
                        running = false;
                    }
                }

                this.is1playing = !this.is1playing;
            }
        }

    }
}
