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

        renderer.render(board, player1, player2);

        while (!board.isGameOver()) {
            String input = readPlayerInput();
            if (input.equals(QUIT_COMMAND)) {
                System.out.println("Thanks for playing! Goodbye!");
                return;
            }

            boolean isValidMove = processInput(input);
            if (isValidMove) {
                renderer.render(board, player1, player2);
            }
        }

        announceWinner();

        System.out.println("Thanks for playing! Goodbye!");
    }

    private void announceWinner() {
        if (player1.getScore() > player2.getScore()) {
            System.out.println("Game over. Player 1 is the winner!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println("Game over. Player 2 is the winner!");
        } else {
            System.out.println("Game over. It's a tie!");
        }
    }

    private boolean processInput(String input) {
        Edge edge = Edge.parse(input, board.getGridSize());

        if (!edge.isValid()) {
            System.out.println("Invalid move. Please try again.");
            return false;
        }

        if (board.isOccupied(edge)) {
            System.out.println("This position is already occupied. Please try again.");
            return false;
        }

        addEdgeInBoardAndSwitchPlayer(edge);
        return true;
    }

    private void addEdgeInBoardAndSwitchPlayer(Edge edge) {
        board.placeEdgeInBoard(edge, currentPlayer);
        int boxesCompletedInMove = board.markCompletedBoxes(edge, currentPlayer);;

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

    public int getCurrentPlayerId() {
        return currentPlayer.getId();
    }
}
