package PracticeQuestions;

import java.util.*;

class RandomizedCollection {
    // ( it can be Map<Integer, List) but used Map<Integer, Set for optimising operations inside indexes.
    private final HashMap<Integer, Set<Integer>> keyIndexesMap;
    private final List<Integer> keys;
    private final Random random;

    public RandomizedCollection() {
        // store key to indexes Map.
        keyIndexesMap = new HashMap<>();
        keys = new ArrayList<>();
        random = new Random();
    }

    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();
        System.out.println(randomizedCollection.insert(1));   // return true since the collection does not contain 1.
        // Inserts 1 into the collection.
        System.out.println(randomizedCollection.insert(1));   // return false since the collection contains 1.
        // Inserts another 1 into the collection. Collection now contains [1,1].
        System.out.println(randomizedCollection.insert(2));   // return true since the collection does not contain 2.
        // Inserts 2 into the collection. Collection now contains [1,1,2].

        System.out.println(randomizedCollection.insert(1));   // return true since the collection does not contain 1.
        // Inserts 1 into the collection.
        System.out.println(randomizedCollection.insert(2));   // return false since the collection contains 1.
        // Inserts another 1 into the collection. Collection now contains [1,1].
        System.out.println(randomizedCollection.insert(2));   // return true since the collection does not contain 2.
        // Inserts 2 into the collection. Collection now contains [1,1,2].

        System.out.println(randomizedCollection.remove(1));   // return true since the collection contains 1.
        System.out.println(randomizedCollection.remove(2));   // return true since the collection contains 1.
        System.out.println(randomizedCollection.remove(2));   // return true since the collection contains 1.
        System.out.println(randomizedCollection.remove(2));   // return true since the collection contains 1.
        // Removes 1 from the collection. Collection now contains [1,2].
//        System.out.println(randomizedCollection.getRandom()); // getRandom should return 1 or 2, both equally likely.

    }

    private void insertIntoKeyIndexesMap(int val, int index) {
        this.keyIndexesMap.putIfAbsent(val, new HashSet<>());
        Set<Integer> indexes = this.keyIndexesMap.get(val);
        indexes.add(index);
    }

    public boolean insert(int val) {
        keys.add(val);
        // added to Map
        this.insertIntoKeyIndexesMap(val, keys.size() - 1);

        return keyIndexesMap.get(val).size() == 1; // true if already present
    }

    public boolean remove(int val) {
        Set<Integer> indexes = keyIndexesMap.get(val);
        if (indexes == null || indexes.size() == 0) {
            return false;
        }

        // removed old value, and put new value in place of it.
        int index = indexes.iterator().next();
        indexes.remove(index);
        this.swapKeys(index, keys.size() - 1);
        this.keys.remove(keys.size() - 1);

        if (keys.size() > 0 && index <= keys.size() - 1) {
            int newValue = keys.get(index);
            Set<Integer> newValueIndexes = keyIndexesMap.get(newValue);
            newValueIndexes.remove(keys.size());
            newValueIndexes.add(index);

        }

        return true;
    }

    private void swapKeys(int index1, int index2) {
        int temp = keys.get(index1);
        keys.set(index1, keys.get(index2));
        keys.set(index2, temp);
    }

    public int getRandom() {
        int randomIndex = random.nextInt(keys.size());
        return keys.get(randomIndex);
    }
}
