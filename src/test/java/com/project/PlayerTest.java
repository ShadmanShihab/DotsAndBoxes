package com.project;

import com.project.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void newPlayerStartsWithZeroScore() {
        assertEquals(0, new Player('1', 1, 0).getScore());
    }

    @Test
    public void addScoreIncrementsCorrectly() {
        Player player = new Player('1', 1, 0);
        player.addScore(2);
        player.addScore(1);
        assertEquals(3, player.getScore());
    }
}
