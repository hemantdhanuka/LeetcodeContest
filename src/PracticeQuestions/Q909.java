package PracticeQuestions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Q909 {
    public static void main(String[] args) {
        Q909 obj = new Q909();
        System.out.println(obj.snakesAndLadders(new int[][]{{-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 35, -1, -1, 13, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 15, -1, -1, -1, -1}}));
    }

    public int snakesAndLadders(int[][] board) {
        Set<Integer> set = new HashSet<>();
        int n = board.length;
        Move move1 = new Move(1, 0);
        set.add(1);
        Queue<Move> queue = new LinkedList<>();
        queue.add(move1);
        while (!queue.isEmpty()) {
            Move move = queue.remove();
            if (move.position == n * n) {
                return move.count;
            }

            int position = move.position + 1;
            while (position <= n * n && position <= move.position + 6) {
                int valueAfterSnakeOrLadder = getValueFromGame(position, board);
                if (!set.contains((valueAfterSnakeOrLadder))) {
                    queue.add(new Move(valueAfterSnakeOrLadder, move.count + 1));
                    set.add(valueAfterSnakeOrLadder);
                }
                position++;
            }
        }

        return -1;
    }

    private int getValueFromGame(int cellNumber, int[][] board) {
        int boardLength = board.length;
        int rowNumber = (cellNumber - 1) / boardLength;
        int rowIndex = boardLength - 1 - rowNumber;

        int remainder = (cellNumber - 1) % boardLength;
        int colIndex = remainder;
        if (rowNumber % 2 != 0) {
            colIndex = boardLength - 1 - remainder;
        }

        int value = board[rowIndex][colIndex];
        if (value == -1) {
            return cellNumber;
        } else {
            return value;
        }

    }

    static class Move {
        int position;
        int count;

        public Move(int position, int count) {
            this.position = position;
            this.count = count;
        }
    }

}
