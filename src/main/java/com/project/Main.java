package com.project;

import com.project.impl.BoardImpl;
import com.project.impl.BoardRendererImpl;
import com.project.interfaces.Board;
import com.project.interfaces.BoardRenderer;
import com.project.model.Player;

import java.util.Scanner;

public class Main {

    private static final int MIN_DOTS = 2;
    private static final int MAX_DOTS = 9;

    static void main() {
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
            StringBuilder sb = new StringBuilder();
            sb.append("Enter the number of dots per side, ");
            sb.append(MIN_DOTS).append(", ");
            sb.append(MAX_DOTS).append(". Enter 4 for a 4*4 grid: ");
            System.out.print(sb.toString());

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
