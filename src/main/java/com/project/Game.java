package com.project;

import com.project.interfaces.Board;
import com.project.interfaces.BoardRenderer;
import com.project.model.Edge;
import com.project.model.Player;

import java.util.Scanner;

public class Game {

    private static final String QUIT_COMMAND = "Q";

    private final Scanner scanner;
    private final Board board;
    private final BoardRenderer renderer;
    private final Player player1;
    private final Player player2;

    private Player currentPlayer;

    public Game(Scanner scanner, Board board, BoardRenderer renderer, Player player1, Player player2) {
        this.scanner = scanner;
        this.board = board;
        this.renderer = renderer;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public void start() {
        boolean shouldRender = true;

        while (!board.isGameOver()) {
            if (shouldRender) {
                //should render first time and only for valid input by players
                renderer.render(board, player1, player2);
            }


            String input = readPlayerInput();
            if (input.equals(QUIT_COMMAND)) {
                System.out.println("Thanks for playing!");
                return;
            }

            shouldRender = processInput(input);
        }

        renderer.render(board, player1, player2);

        if (player1.getScore() > player2.getScore()) {
            System.out.println("Game over. Player 1 is the winner!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println("Game over. Player 2 is the winner!");
        } else {
            System.out.println("Game over. It's a tie!");
        }

        System.out.println("Thanks for playing!");
    }

    public boolean processInput(String input) {
        Edge edge = Edge.parse(input, board.getGridSize());

        if (!edge.isValid()) {
            System.out.println("Invalid input!");
            return false;
        }

        if (board.isOccupied(edge)) {
            System.out.println("This position is already occupied. Please try again.");
            return false;
        }

        addEdgeInBoard(edge);
        return true;
    }

    private void addEdgeInBoard(Edge edge) {
        int boxesCompletedInMove = board.placeEdge(edge, currentPlayer);

        if (boxesCompletedInMove > 0) {
            currentPlayer.addScore(boxesCompletedInMove);
        } else {
            //switching player
            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }
    }

    private String readPlayerInput() {
        StringBuilder sb = new StringBuilder();

        sb.append("Player ");
        sb.append(currentPlayer.getId());
        sb.append(", input a move <column><row> (or 'Q' to quit): ");
        System.out.print(sb.toString());

        try {
            return scanner.nextLine().trim();
        }  catch (Exception e) {
            return QUIT_COMMAND;
        }
    }
}
