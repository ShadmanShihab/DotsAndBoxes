package com.project;

import com.project.impl.BoardImpl;
import com.project.impl.BoardRendererImpl;
import com.project.interfaces.Board;
import com.project.interfaces.BoardRenderer;
import com.project.model.Player;

import java.util.Scanner;

public class Main {

    private static final int MIN_DOTS = 2;
    private static final int MAX_DOTS = 13;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dotCount = inputDotCount(scanner);

        Board board = new BoardImpl(dotCount);
        BoardRenderer renderer = new BoardRendererImpl();
        Player player1 = new Player('1', 1, 0);
        Player player2 = new Player('2', 2, 0);

        Game game = new Game(scanner, board, renderer, player1, player2);
        game.start();

        scanner.close();
    }

    private static int inputDotCount(Scanner scanner) {
        while (true) {
            System.out.print(String.format(
                    "%nEnter number of dots per side (%d-%d). Tip: enter 4 for the standard 4x4 game: ",
                    MIN_DOTS, MAX_DOTS
            ));

            try {
                String input = scanner.nextLine().trim();
                int dotCount = Integer.parseInt(input);
                if (dotCount < MIN_DOTS || dotCount > MAX_DOTS) {
                    System.out.println("Invalid input. Please try again.");
                    continue;
                }
                return dotCount;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                System.exit(1);
            }
        }
    }
}
