package datastructures;

import java.util.Arrays;

public class Heap {
    int list[];
    int size = 0;

    Heap() {
        list = new int[1000];
        Arrays.fill(list, -1); // setting default value to -1
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        System.out.println(heap.add(10));
        System.out.println(heap.add(30));
        System.out.println(heap.add(20));
        System.out.println(heap.add(15));

        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
    }

    public int add(int num) {
        // should add element in such a way to keep num in max heap format
        list[size] = num;
        size++;

        heapifyBottomToTop();
        return list[0];
    }

    public int poll() {
        // should return top elements
        if (size <= 0) {
            return -1;
        }

        int topValue = list[0];

        swapValueAtIndex(0, size - 1);
        list[size - 1] = -1;
        size--;


        if (size > 0) {
            heapifyTopToBottom();
        }

        return topValue;
    }

    public int peek() {
        return list[0];
    }

    public void heapifyBottomToTop() {
        int currentIndex = size - 1;
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;

            int currentValue = list[currentIndex];
            int parentValue = list[parentIndex];

            if (currentValue > parentValue) {
                //swap
                swapValueAtIndex(currentIndex, parentIndex);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    public void heapifyTopToBottom() {
        int currentIndex = 0;

        while (currentIndex < size) {
            int child1Index = currentIndex * 2 + 1;
            int child2Index = currentIndex * 2 + 2;

            int largestIndex = getLargestValueIndex(currentIndex, child1Index, child2Index);

            if (largestIndex == currentIndex) {
                break;
            } else {
                swapValueAtIndex(currentIndex, largestIndex);
                currentIndex = largestIndex;
            }
        }


    }

    public void swapValueAtIndex(int index1, int index2) {
        int temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    public int getLargestValueIndex(int index1, int index2, int index3) {
        int largestIndex = index1;
        int largestValue = list[index1];

        if (list[index2] > largestValue) {
            largestIndex = index2;
            largestValue = list[index2];
        }

        if (list[index3] > largestValue) {
            largestIndex = index3;
            largestValue = list[index3];
        }

        return largestIndex;
    }
}
