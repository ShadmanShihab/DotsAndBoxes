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
java -jar target/DotsAndBoxes-1.0-SNAPSHOT.jar
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
Enter number of dots per side (2-13). Tip: enter 4 for the standard 4x4 game:
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
│   ├── Main.java                    # Entry point
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

- **Interfaces for Board and BoardRenderer** — I introduced interfaces for
  these two classes so that `Game` is not tied to a specific implementation.
  This makes it easier to swap out or extend the board logic and rendering
  without touching the game loop.

- **Edge as a value object** — instead of passing raw row and column integers
  around, I modelled a line on the board as an `Edge` object. This makes the
  code easier to read and the intent clearer. Edges are immutable — once
  created they cannot change.

- **Input parsing lives in Edge** — I chose to put the `parse()` method
  directly on `Edge` rather than creating a separate parser class. Since
  parsing is about determining whether a string represents a valid edge,
  it felt natural for `Edge` to own that responsibility.

- **Dynamic board size** — the assignment specifies a 4x4 board but I made
  the size configurable. The user enters a dot count at startup and the board
  is built from that. I felt this was a reasonable extension without
  overcomplicating the design.

- **No re-render on invalid moves** — I noticed the assignment example shows
  that after an invalid move the board is not redrawn. I matched this
  behaviour intentionally.

---

## Assumptions

- **Board is always square** — To keep things simple I considered the number of dots per row and column is
  always the same. The user inputs a single dot count which applies to
  both dimensions. For example, entering `4` creates a 4x4 dot grid.

- **Dot count defines the board size** — dot count is the number of dots in each side of the board. Internally the grid size is computed as
  `2 * dotCount - 1` to accommodate dots, lines, and box centres in a
  single character array. For example, a dot count of 4 produces a 7x7
  internal grid (the standard board from the assignment).

- **Maximum dot count is 13** — column positions are labelled with single
  letters A-Z. A dot count of 13 produces 25 columns (2*13-1=25), requiring labels A through Y. A dot count of 14 would
  require 27 labels which exceeds the 26-letter alphabet.

- **Minimum dot count is 2** — at least 2 dots per side are needed to
  draw any line between them. Entering 1 or less is rejected with a
  clear error message.

- **Input is case insensitive** — `b0` and `B0` are treated as the same
  move.