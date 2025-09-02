import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class xyz {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int arraySize = s.nextInt();
        int[] money = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            money[i] = s.nextInt();
        }

        int k = s.nextInt();
        xyz d = new xyz();

        Queue q1 = new ArrayDeque();
        Queue q2 = new LinkedList();
        System.out.println(d.solve(money, k));
    }

    public int solve(int[] money, int k) {
        int n = money.length;
        int[] prefixFrequencyOfK = new int[n];
        int[] suffixFrequencyOfK = new int[n];

        int frequencyOfK = 0;
        for (int i = 0; i < money.length; i++) {
            if (money[i] == k) {
                frequencyOfK++;
                prefixFrequencyOfK[i] = frequencyOfK;
            }
        }


        frequencyOfK = 0;
        for (int i = money.length - 1; i >= 0; i--) {
            if (money[i] == k) {
                frequencyOfK++;
                suffixFrequencyOfK[i] = frequencyOfK;
            }
        }

        int friendsCount = 1;

        for (int i = 0; i < n; i++) {
            int frequency = 0;
            for (int j = 0; j < n; j++) {
                int preK = 0;
                if (i > 0) {
                    preK = prefixFrequencyOfK[i - 1];
                }
                int postK = 0;
                if (j < money.length - 1) {
                    postK = suffixFrequencyOfK[j + 1];
                }

                if (money[i] == money[j]) {
                    frequency++;

                    friendsCount = Math.max(friendsCount, frequency + preK + postK);
                }
            }
        }

        return friendsCount;
    }


}
