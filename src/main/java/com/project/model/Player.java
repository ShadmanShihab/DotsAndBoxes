package com.project.model;

public class Player {
    private char name;
    private int id;
    private int score;

    public Player(char name, int id, int score) {
        this.name = name;
        this.id = id;
        this.score = score;
    }

    public char getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public int getId() {
        return id;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
