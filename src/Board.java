//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Board {
    private static final int rows = 6;
    private static final int columns = 7;
    piece[][] ourBoard = new piece[6][7];

    public static int getColumns() {
        return 7;
    }

    private boolean checkDiagonal(int row, int col, String winningColor, boolean rightDiagonal) {
        int winningStreak = 4;
        int reverser = 1;
        if (rightDiagonal) {
            reverser = -1;
        }

        int winRow = row - 3;

        for(int winCol = col - 3 * reverser; winRow <= row + 3; winCol += reverser) {
            label61: {
                if (!rightDiagonal) {
                    if (winRow < 0 || winCol < 0) {
                        break label61;
                    }

                    if (winRow >= 6 || winCol >= 7) {
                        break;
                    }
                } else {
                    if (winRow < 0 || winCol >= 7) {
                        break label61;
                    }

                    if (winRow >= 6 || winCol < 0) {
                        break;
                    }
                }

                if (this.ourBoard[winRow][winCol] != null && this.ourBoard[winRow][winCol].getColor().equals(winningColor)) {
                    --winningStreak;
                    if (winningStreak == 0) {
                        return true;
                    }
                } else {
                    winningStreak = 4;
                }
            }

            ++winRow;
        }

        return false;
    }

    public boolean checkForWinner(int col, String winningcolor) {
        for(int row = 0; row < 6; ++row) {
            if (this.ourBoard[row][col] != null) {
                int winningStreak = 3;

                int winCol;
                for(winCol = row + 1; winCol < 6; ++winCol) {
                    if (this.ourBoard[winCol][col].getColor().equals(winningcolor)) {
                        --winningStreak;
                        if (winningStreak == 0) {
                            return true;
                        }
                    } else {
                        winningStreak = 3;
                    }
                }

                winningStreak = 4;

                for(winCol = col - 3; winCol <= col + 3; ++winCol) {
                    if (winCol >= 0) {
                        if (winCol >= 7) {
                            break;
                        }

                        if (this.ourBoard[row][winCol] != null && this.ourBoard[row][winCol].getColor().equals(winningcolor)) {
                            --winningStreak;
                            if (winningStreak == 0) {
                                return true;
                            }
                        } else {
                            winningStreak = 4;
                        }
                    }
                }

                if (this.checkDiagonal(row, col, winningcolor, false)) {
                    return true;
                }

                if (this.checkDiagonal(row, col, winningcolor, true)) {
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    public boolean addpiece(int colToAdd, String color) {
        if (colToAdd >= 0 && colToAdd < 6) {
            if (this.ourBoard[0][colToAdd] != null) {
                System.err.println("This column is full, Please choose another. ");
                return false;
            } else {
                boolean addedThepiece = false;

                for(int row = 5; row >= 0; --row) {
                    if (this.ourBoard[row][colToAdd] == null) {
                        this.ourBoard[row][colToAdd] = new piece();
                        this.ourBoard[row][colToAdd].setColor(color);
                        addedThepiece = true;
                        break;
                    }
                }

                return addedThepiece;
            }
        } else {
            System.err.println("You are trying to add somewhere that is not supported. ");
            return false;
        }
    }

    public void printBoard() {
        int row;
        for(row = 0; row < 15; ++row) {
            System.out.print("-");
        }

        System.out.println();

        for(row = 0; row < 6; ++row) {
            System.out.print("|");

            for(int col = 0; col < 7; ++col) {
                if (this.ourBoard[row][col] == null) {
                    System.out.print("_");
                } else {
                    System.out.print(this.ourBoard[row][col].getColor());
                }

                System.out.print("|");
            }

            System.out.println();
        }

        for(row = 0; row < 15; ++row) {
            System.out.print("-");
        }

        System.out.println();
    }

    public Board() {
        for(int row = 0; row < 6; ++row) {
            for(int col = 0; col < 7; ++col) {
                this.ourBoard[row][col] = null;
            }
        }

    }
}
