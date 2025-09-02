package PracticeQuestions;

import java.util.*;

public class Q2306 {
    public static void main(String[] args) {
        Q2306 obj = new Q2306();
        System.out.println(obj.distinctNames(new String[]{"coffee", "donuts", "time", "toffee"}));
    }

    public long distinctNames(String[] ideas) {
        // preprocessing
        Set<String> ideasSet = new HashSet<>(Arrays.asList(ideas));
        Map<String, Integer> countMap = new HashMap<>();
        Map<String, List<Character>> possibleSwappingListMap = new HashMap<>();

        getCountMap(ideas, ideasSet, countMap, possibleSwappingListMap);

        long ans = 0;
        for (String idea : ideas) {
            List<Character> characterList = possibleSwappingListMap.get(idea);
            char oldChar = idea.charAt(0);

            for (Character ch : characterList) {
                String key = ch + "" + oldChar;
                ans = ans + countMap.getOrDefault(key, 0);
            }
        }

        return ans;
    }

    private void getCountMap(String[] ideas, Set<String> ideasSet, Map<String, Integer> countMap, Map<String, List<Character>> possibleSwappingListMap) {
        for (String idea : ideas) {
            List<Character> possibleSwappingCharList = getPossibleSwappingList(idea, ideasSet);
            char oldChar = idea.charAt(0);
            for (Character newChar : possibleSwappingCharList) {
                String key = oldChar + "" + newChar;
                int existingCount = countMap.getOrDefault(key, 0);
                countMap.put(key, existingCount + 1);
            }

            possibleSwappingListMap.put(idea, possibleSwappingCharList);
        }
    }

    private List<Character> getPossibleSwappingList(String idea, Set<String> ideaSet) {
        List<Character> possibleSwappingCharList = new ArrayList<>(26);
        String suffix = idea.substring(1);
        int firstChar = idea.charAt(0) - 'a';

        for (int i = 0; i < 26; i++) {
            if (i == firstChar) continue;

            char ch = (char) (i + 'a');
            if (!isExists(suffix, ch, ideaSet)) {
                possibleSwappingCharList.add(ch);
            }
        }

        return possibleSwappingCharList;
    }

    private boolean isExists(String suffix, char newFirstChar, Set<String> ideaSet) {
        String newWord = newFirstChar + suffix;
        return ideaSet.contains(newWord);
    }


//
//    public long distinctNames(String[] ideas) {
//        // preprocessing
//        Set<String> ideasSet = new HashSet<>(Arrays.asList(ideas));
//        Map<String, Integer> countMap = new HashMap<>();
//        Map<String, List<Character>> possibleSwappingListMap = new HashMap<>();
//
//        getCountMap(ideas, ideasSet, countMap, possibleSwappingListMap);
//
//        long ans = 0;
//        for (String idea : ideas) {
//            List<Character> characterList = possibleSwappingListMap.get(idea);
//            char oldChar = idea.charAt(0);
//
//            for (int i = 0; i < characterList.size(); i++) {
//                char ch = characterList.get(i);
//                String key = ch + "" + oldChar;
//                ans = ans + countMap.getOrDefault(key, 0);
//            }
//
//        }
//
//        return ans;
//
//
//    }
//
//    private Map<String, Integer> getCountMap(String[] ideas, Set<String> ideasSet, Map<String, Integer> countMap, Map<String, List<Character>> possibleSwappingListMap) {
//        for (int i = 0; i < ideas.length; i++) {
//            String idea = ideas[i];
//
//            List<Character> possibleSwappingCharList = getPossibleSwappingList(idea, ideasSet);
//            char oldChar = idea.charAt(0);
//            for (int j = 0; j < possibleSwappingCharList.size(); j++) {
//                char newChar = possibleSwappingCharList.get(j);
//                String key = oldChar + "" + newChar;
//                int existingCount = countMap.getOrDefault(key, 0);
//                countMap.put(key, existingCount + 1);
//            }
//
//            possibleSwappingListMap.put(idea, possibleSwappingCharList);
//        }
//        return countMap;
//    }
//
//
//    private List<Character> getPossibleSwappingList(String idea, Set<String> ideaSet) {
//        List<Character> possibleSwappingCharList = new ArrayList<>();
//        String suffix = idea.substring(1);
//        int firstChar = idea.charAt(0) - 'a';
//        for (int i = 0; i < 26; i++) {
//            if (i == firstChar) {
//                continue;
//            }
//            char ch = (char) (i + 'a');
//            boolean isExists = isExists(suffix, ch, ideaSet);
//            if (!isExists) {
//                possibleSwappingCharList.add(ch);
//            }
//        }
//
//        return possibleSwappingCharList;
//    }
//
//    private boolean isExists(String suffix, char newFirstChar, Set<String> ideaSet) {
//        String newWord = newFirstChar + suffix;
//        return ideaSet.contains(newWord);
//    }
}
