package PracticeQuestions;

import java.util.*;

public class Q0472 {
    private static HashSet<String> wordSet;
    private static HashMap<String, Boolean> concatenatedWordsMap;

    public static void main(String[] args) {
        Q0472 obj = new Q0472();
        System.out.println(obj.findAllConcatenatedWordsInADict(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"}));
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        wordSet = new HashSet<>();
        Collections.addAll(wordSet, words);

        List<String> ansList = new ArrayList<>();
        for (String word : words) {
            concatenatedWordsMap = new HashMap<>();
            wordSet.remove(word);
            boolean isConcatenated = checkIsConcatenated(word);
            wordSet.add(word);
            if (isConcatenated) {
                ansList.add(word);
            }
        }

        return ansList;
    }

    private boolean checkIsConcatenated(String word) {
        if (concatenatedWordsMap.containsKey(word)) {
            return concatenatedWordsMap.get(word);
        }

        boolean isConcatenated = wordSet.contains(word);

        for (int i = 0; i < word.length() - 1; i++) {
            isConcatenated = isConcatenated || (checkIsConcatenated(word.substring(0, i + 1)) && checkIsConcatenated(word.substring(i + 1)));
        }

        concatenatedWordsMap.put(word, isConcatenated);
        return isConcatenated;
    }


}
