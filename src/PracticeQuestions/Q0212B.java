package PracticeQuestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q0212B {
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String word = "ABCCED";

        Q0212B obj = new Q0212B();
        System.out.println(obj.findWords(board, new String[]{word}));
    }


    public List<String> findWords(char[][] board, String[] words) {
        Set<String> prefixSet = new HashSet<>();
        Set<String> wordSet = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            String prefix = "";
            for (int j = 0; j < words[i].length(); j++) {
                prefix = prefix + words[i].charAt(j);
                prefixSet.add(prefix);
            }

            wordSet.add(words[i]);
        }


        HashSet<String> matchedWordSet = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                generate(i, j, 0, board, "", matchedWordSet, 10, prefixSet, wordSet);
            }
        }

        return new ArrayList(matchedWordSet);

    }

    public void generate(int x, int y, int index, char[][] board, String word, Set<String> matchedWords, int maxLength, Set<String> prefixes, Set<String> words) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            return;
        }

        if (board[x][y] == ' ') {
            return;
        }


        String newWord = word + board[x][y];

        if (!prefixes.contains(newWord)) {
            return;
        }

        if (words.contains(newWord)) {
            matchedWords.add(newWord);
        }

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

            generate(newX, newY, index + 1, board, newWord, matchedWords, maxLength, prefixes, words);
        }

        board[x][y] = temp;

    }


}
