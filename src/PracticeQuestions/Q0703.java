package PracticeQuestions;

import java.util.PriorityQueue;

class KthLargest {
    final PriorityQueue<Integer> queue;
    final int k;


    public KthLargest(int k, int[] nums) {
        queue = new PriorityQueue<>();
        this.k = k;

        for (int i = 0; i < nums.length; i++) {
            this.add(nums[i]);
        }

    }

    public static void main(String[] args) {
        KthLargest obj = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(obj.add(3));
        System.out.println(obj.add(5));
        System.out.println(obj.add(10));
        System.out.println(obj.add(9));
        System.out.println(obj.add(4));

    }

    public int add(int val) {
        if (queue.size() < k) {
            queue.add(val);
        } else {
            int top = queue.peek();
            if (top < val) {
                queue.add(val);
                queue.poll();
            }
        }
        return queue.peek();
    }

}
