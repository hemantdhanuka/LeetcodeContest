package PracticeQuestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TLE
public class Q0212A {
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String word = "ABCCED";

        Q0212A obj = new Q0212A();
        System.out.println(obj.findWords(board, new String[]{word}));
    }


    public List<String> findWords(char[][] board, String[] words) {
        HashSet<String> wordSet = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                generate(i, j, 0, board, "", wordSet, 10);
            }
        }

//        System.out.println(wordSet);

        // check
        List<String> matched = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (wordSet.contains(word)) {
                matched.add(word);
            }
        }

        return matched;

    }

    public void generate(int x, int y, int index, char[][] board, String word, Set<String> wordSet, int maxLength) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            return;
        }

        if (board[x][y] == ' ') {
            return;
        }


        String newWord = word + board[x][y];

        // can also validate, is it good to explore this word, and prune search

        wordSet.add(newWord);

        if (index == maxLength) {
            return;
        }

        char temp = board[x][y];
        board[x][y] = ' ';

        int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < moves.length; i++) {
            int[] move = moves[i];
            int newX = x + move[0];
            int newY = y + move[1];

            generate(newX, newY, index + 1, board, newWord, wordSet, maxLength);
        }

        board[x][y] = temp;

    }


}
