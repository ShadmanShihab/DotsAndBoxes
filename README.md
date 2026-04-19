# Dots and Boxes

A console-based implementation of the classic Dots and Boxes game,
built in Java using Maven.

---

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

Verify your installations:

```bash
java -version
mvn -version
```

---

## How to Build

```bash
mvn clean package
```

---

## How to Run

```bash
java -jar target/dots-and-boxes-1.0-SNAPSHOT.jar
```

---

## How to Test

```bash
mvn test
```

---

## How to Play

When the game starts you will be prompted for a board size:

```
Enter number of dots per side (2-13, where 4 gives the standard 4x4 board):
```

Enter `4` for the standard game. The board will look like this:

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

### Making a Move

Type a column letter followed by a row number:

```
Player 1, input a move <column><row> (or 'Q' to quit): B0
```

| Input | Result                            |
|-------|-----------------------------------|
| `B0`  | Horizontal line between A0 and B0 |
| `A1`  | Vertical line between A0 and A2   |

### Rules

- Player 1 always goes first
- Players take turns drawing lines between adjacent dots
- Completing a box earns a point and an extra turn
- The player with the most boxes at the end wins
- Type `Q` to quit at any time

---

## Project Structure

```
src/
├── main/java/com/project/
│   ├── Main.java                    # Entry point and composition root
│   ├── Game.java                    # Game loop and turn management
│   ├── enums/
│   │   └── EdgeType.java            # HORIZONTAL or VERTICAL
│   ├── model/
│   │   ├── Edge.java                # Represents a line on the board
│   │   └── Player.java              # Player number and score
│   ├── interfaces/
│   │   ├── Board.java               # Board contract
│   │   └── BoardRenderer.java       # Renderer contract
│   └── impl/
│       ├── BoardImpl.java           # Grid state and game logic
│       └── BoardRendererImpl.java   # Console rendering
└── test/java/com/project/
    ├── GameTest.java                # Integration tests
    ├── BoardTest.java               # Unit tests for board logic
    ├── EdgeTest.java                # Unit tests for edge parsing
    └── PlayerTest.java              # Unit tests for player
```

---

## Design Decisions

- **Interfaces over concrete classes** — `Game` depends on `Board` and
  `BoardRenderer` interfaces rather than their implementations, keeping
  the game loop decoupled from implementation details.

- **Edge as a value object** — player input is parsed and validated into
  an immutable `Edge` object before being applied to the board. Invalid
  input returns an invalid `Edge` rather than throwing an exception,
  keeping the game loop clean.

- **Dynamic board size** — the board accepts a dot count at construction
  time. The internal grid size is computed as `2 * dotCount - 1`,
  supporting any board from 2x2 up to 13x13 dots.

- **No re-render on invalid moves** — consistent with the assignment
  specification, invalid moves re-prompt the player without redrawing
  the board.

- **Manual dependency injection** — `Main` is the sole composition root.
  It is the only class that instantiates concrete objects and wires them
  together. All other classes depend on interfaces, not implementations.

---

## Assumptions

- **Board is always square** — the number of dots per row and column is
  always the same. The user inputs a single dot count which applies to
  both dimensions. For example, entering `4` creates a 4x4 dot grid.

- **Dot count defines the board size** — dot count is the number of dots
  along each side of the board. Internally the grid size is computed as
  `2 * dotCount - 1` to accommodate dots, lines, and box centres in a
  single character array. For example, a dot count of 4 produces a 7x7
  internal grid (the standard board from the assignment).

- **Maximum dot count is 13** — column positions are labelled with single
  letters A-Z. A dot count of 13 produces an internal grid of 25 columns
  (2*13-1=25), requiring labels A through Y. A dot count of 14 would
  require 27 labels which exceeds the 26-letter alphabet.

- **Minimum dot count is 2** — at least 2 dots per side are needed to
  draw any line between them. Entering 1 or less is rejected with a
  clear error message.

- **Player symbols are fixed** — Player 1 is always denoted by `1` and
  Player 2 by `2`, consistent with the assignment specification.

- **Input is case insensitive** — `b0` and `B0` are treated as the same
  move.

- **No external libraries** — the only dependency beyond the JDK is
  JUnit 4.13.2, used exclusively for testing, as required by the
  assignment specification.
