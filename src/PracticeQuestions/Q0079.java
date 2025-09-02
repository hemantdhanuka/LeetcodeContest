package PracticeQuestions;

public class Q0079 {
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String word = "ABCCEF";

        Q0079 obj = new Q0079();

        System.out.println(obj.exist(board, word));
    }

    public boolean exist(char[][] board, String word) {
        boolean result = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                result = result || check(i, j, 0, word, board);
            }
        }
        return result;
    }


    public boolean check(int x, int y, int wordIndex, String word, char[][] board) {
        if (wordIndex == word.length()) {
            // traversed full word
            return true;
        }

        if (x >= board.length || y >= board[0].length || x < 0 || y < 0) {
            return false;
        }

        // already visited
        if (board[x][y] == ' ') {
            return false;
        }

        if (word.charAt(wordIndex) != board[x][y]) {
            return false;
        }

        // Means character matched.
        char temp = board[x][y];
        board[x][y] = ' ';


        int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        boolean result = false;
        for (int i = 0; i < moves.length; i++) {
            int[] move = moves[i];
            int newX = x + move[0];
            int newY = y + move[1];

            result = result || check(newX, newY, wordIndex + 1, word, board);
        }

        board[x][y] = temp;
        return result;
    }

}

