# Dots and Boxes

A console-based two-player Dots and Boxes game built with Java and Maven.

---

## Requirements

- Java 11+
- Maven 3.6+

---

## Build

```bash
mvn clean package
```

## Run

```bash
java -jar target/DotsAndBoxes-1.0-SNAPSHOT.jar
```

## Test

```bash
mvn test
```

---

## How to Play

The game runs in the terminal on a 4x4 grid of dots:

```
  ABCDEFG
0 * * * *
1
2 * * * *
3
4 * * * *
5
6 * * * *
```

Each turn, a player types a column letter and row number to draw a line:

```
Player 1, input a move <column><row> (or 'Q' to quit): B0
```

- `B0` draws a horizontal line
- `A1` draws a vertical line

If you complete a box you get another turn. Most boxes at the end wins.
Type `Q` to quit at any time.

---

## Project Structure

```
src/main/java/com/project/
    Main.java                 # wires everything together
    Game.java                 # game loop
    enums/
        EdgeType.java         # HORIZONTAL or VERTICAL
    model/
        Edge.java             # a line on the board
        Player.java           # player data
    interfaces/
        Board.java
        BoardRenderer.java
    impl/
        BoardImpl.java
        BoardRendererImpl.java

src/test/java/com/project/
    GameTest.java             # integration tests
    BoardTest.java
    EdgeTest.java
    PlayerTest.java
```

---


## Assumptions & Design Decisions

- The board is always 4x4 as per the spec. Internally it's stored as a
  7x7 grid. The dots are at even positions of the grid.

- Input is case insensitive so `b0` and `B0` are the same.

- I used interfaces for `Board` and `BoardRenderer` so `Game` doesn't depend
  on the concrete classes to make it easier to change things later without
  touching the game loop.

- The board is not redrawn after an invalid move. Player has to give input again for an invalid move.