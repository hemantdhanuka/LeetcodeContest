package datastructures;

import java.util.ArrayList;
import java.util.List;

// creating concrete maxHeap
class MaxHeap {
    private final List<Integer> list;  // This can be generic with sorting order.
    private int size;

    public MaxHeap() {
        this.size = 0;
        list = new ArrayList<>();
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();
        heap.add(10);
        heap.add(5);
        heap.add(15);
        heap.add(13);


        System.out.println(heap.getAndRemoveTop());
        heap.add(14);
        System.out.println(heap.getAndRemoveTop());
        heap.add(11);
        System.out.println(heap.getAndRemoveTop());
        System.out.println(heap.getAndRemoveTop());
        System.out.println(heap.getAndRemoveTop());
        System.out.println(heap.getAndRemoveTop());
        System.out.println(heap.getAndRemoveTop());
    }

    private void heapifyFromTopToBottom() {
        int currentPosition = 0;
        int child1Index = 2 * currentPosition + 1;
        int child2Index = 2 * currentPosition + 2;


        // wrote in very complicated way, my earlier implementation is great using largestIndexValue out of parent, child1, child2..
        while (true) {
            if (child1Index < this.getSize()) {
                if (list.get(currentPosition) < list.get(child1Index)) {
                    if (child2Index >= this.getSize()) {
                        swapValue(child1Index, currentPosition);
                        currentPosition = child1Index;
                    } else {
                        if (list.get(child2Index) > list.get(child1Index)) {
                            swapValue(child2Index, currentPosition);
                            currentPosition = child2Index;
                        } else {
                            swapValue(child1Index, currentPosition);
                            currentPosition = child1Index;
                        }
                    }
                } else {
                    if (child2Index < this.getSize()) {
                        if (list.get(child2Index) > list.get(currentPosition)) {
                            swapValue(child2Index, currentPosition);
                            currentPosition = child2Index;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }

            child1Index = 2 * currentPosition + 1;
            child2Index = 2 * currentPosition + 2;
        }

    }

    private void increaseSize() {
        this.size += 1;
    }

    private void decreaseSize() {
        this.size -= 1;
    }

    public int getAndRemoveTop() throws ElementNotFoundException {
        if (this.size == 0) {
            throw new ElementNotFoundException("empty queue");
        }
        int top = list.get(0);
        swapValue(0, size - 1);
        this.decreaseSize();

        heapifyFromTopToBottom();
        return top;
    }

    public void add(int number) {
        this.increaseSize();
        list.add(size - 1, number);
        heapifyBottomToTop();
    }

    private void heapifyBottomToTop() {
        int currentPosition = this.getSize() - 1;
        int parentPosition = getParentIndex(currentPosition);

        while (parentPosition >= 0 && list.get(currentPosition) > list.get(parentPosition)) {
            // swap
            swapValue(currentPosition, parentPosition);
            currentPosition = parentPosition;
            parentPosition = getParentIndex(currentPosition);
        }
    }

    private int getParentIndex(int childIndex) {
        return (((childIndex + 1) / 2) - 1);
    }

    private void swapValue(int index1, int index2) {
        int index1Value = list.get(index1);

        this.list.set(index1, list.get(index2));
        this.list.set(index2, index1Value);
    }

    public int getSize() {
        return size;
    }
}

class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String message) {
        super(message);
    }
}
