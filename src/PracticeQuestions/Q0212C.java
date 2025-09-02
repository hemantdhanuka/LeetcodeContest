package PracticeQuestions;

import java.util.*;

public class Q0212C {
    public static void main(String[] args) {
        char[][] board = {
                {'o', 'a', 'b', 'n'},
                {'o', 't', 'a', 'e'},
                {'a', 'h', 'k', 'r'},
                {'a', 'f', 'l', 'v'}
        };

        String[] words = {"oa", "oaa"};

        Q0212C obj = new Q0212C();
        System.out.println(obj.findWords(board, words));
    }


    public List<String> findWords(char[][] board, String[] words) {
        int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Trie trie = buildTrie(words);

        HashSet<String> matchedWordSet = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                generate(i, j, board, matchedWordSet, trie.parent, moves);
            }
        }

        return new ArrayList(matchedWordSet);

    }

    public Trie buildTrie(String[] words) {
        Trie trie = new Trie();

        for (int i = 0; i < words.length; i++) {
            trie.buildTrie(words[i]);
        }

        return trie;
    }

    public void generate(int x, int y, char[][] board, Set<String> matchedWords, TrieNode trieNode, int[][] moves) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            return;
        }

        if (board[x][y] == ' ') {
            return;
        }


        TrieNode child = trieNode.characterTrieNodeMap.get(board[x][y]);
        if (child == null) {
            return;
        }

        if (child.word != null) {
            matchedWords.add(child.word);
            child.word = null;
        }


        char temp = board[x][y];
        board[x][y] = ' ';

        for (int i = 0; i < moves.length; i++) {
            int[] move = moves[i];
            int newX = x + move[0];
            int newY = y + move[1];

            generate(newX, newY, board, matchedWords, child, moves);
        }

        board[x][y] = temp;

    }


    class Trie {
        TrieNode parent;

        public Trie() {
            this.parent = new TrieNode();
        }

        public void buildTrie(String word) {
            TrieNode current = this.parent;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                current.characterTrieNodeMap.putIfAbsent(ch, new TrieNode());
                current = current.characterTrieNodeMap.get(ch);
            }
            current.word = word;
        }
    }

    class TrieNode {
        Map<Character, TrieNode> characterTrieNodeMap;
        String word;

        public TrieNode() {
            characterTrieNodeMap = new HashMap<>();
        }
    }


}
