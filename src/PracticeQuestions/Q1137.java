package PracticeQuestions;

public class Q1137 {
    public int tribonacci(int n) {
        int[] answer = new int[n + 1];
        answer[0] = 0;
        answer[1] = 1;
        answer[2] = 1;

        for (int i = 3; i <= n; i++) {
            answer[i] = answer[i - 1] + answer[i - 2] + answer[i - 3];
        }

        return answer[n];
    }

}
