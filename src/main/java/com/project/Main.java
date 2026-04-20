package com.project;

import com.project.impl.BoardImpl;
import com.project.impl.BoardRendererImpl;
import com.project.interfaces.Board;
import com.project.interfaces.BoardRenderer;
import com.project.model.Player;

import java.util.Scanner;

public class Main {

    private static final int NUMBER_OF_DOTS_PER_ROW = 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Board board = new BoardImpl(NUMBER_OF_DOTS_PER_ROW);
        BoardRenderer renderer = new BoardRendererImpl();

        Player player1 = new Player('1', 1, 0);
        Player player2 = new Player('2', 2, 0);

        Game game = new Game(scanner, board, renderer, player1, player2);
        game.start();

        scanner.close();
    }
}
