package PracticeQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class RandomizedSet {
    // ( it can be Map<Integer, List) but used Map<Integer, Set for optimising operations inside indexes.
    private final HashMap<Integer, Integer> keyIndex;
    private final List<Integer> keys;
    private final Random random;

    public RandomizedSet() {
        // store key to indexes Map.
        keyIndex = new HashMap<>();
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


    public boolean insert(int val) {
        if (this.keyIndex.containsKey(val)) {
            return false;
        }


        keys.add(val);
        keyIndex.put(val, keys.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!this.keyIndex.containsKey(val)) {
            return false;
        }

        // removed old value, and put new value in place of it.
        int index = keyIndex.get(val);
        this.keyIndex.remove(val);
        this.swapKeys(index, keys.size() - 1);
        this.keys.remove(keys.size() - 1);


        if (keys.size() > 0 && index <= keys.size() - 1) {
            int newValue = keys.get(index);
            keyIndex.put(newValue, index);
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
