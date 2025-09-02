package datastructures;

import java.util.PriorityQueue;
import java.util.Queue;

class Test {
    public static void main(String[] args) {
        Queue queue = new PriorityQueue<Node>();

        queue.add(new Node(10));
        queue.add(new Node(5));
        queue.add(new Node(15));

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}

class Node implements Comparable<Node> {
    int value;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "datastructures.Node: " + this.value;
    }
}