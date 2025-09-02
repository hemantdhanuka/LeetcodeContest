import java.util.ArrayList;

public class Weekly204 {
    long mod = 1000000007;

    public static void main(String[] args) {
        Weekly204 weekly204 = new Weekly204();
        weekly204.numOfWays(new int[]{9, 4, 2, 1, 3, 6, 5, 7, 8, 14, 11, 10, 12, 13, 16, 15, 17, 18});
    }

    public int numOfWays(int[] nums) {
        long dp[][] = new long[nums.length + 1][nums.length + 1];
        proProcess(dp);
        ArrayList<Integer> numList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            numList.add(nums[i]);
        }
        int ans = calAnswer(numList, dp);
        return ans - 1;

    }

    private int calAnswer(ArrayList<Integer> numList, long[][] dp) {

        if (numList.size() <= 2) {
            return 1;
        }

        ArrayList<Integer> firstList = new ArrayList<>();
        ArrayList<Integer> secondList = new ArrayList<>();

        for (int i = 1; i < numList.size(); i++) {
            if (numList.get(i) < numList.get(0)) {
                firstList.add(numList.get(i));
            } else {
                secondList.add(numList.get(i));
            }
        }

        int ans = (int) (((dp[firstList.size()][secondList.size()] * calAnswer(firstList, dp)) % mod * calAnswer(secondList, dp)) % mod);
        return ans;
    }


    //preprocessing
    public void proProcess(long dp[][]) {
        long prefixSum[][] = new long[dp.length][dp[0].length];
        for (int depth = 0; depth < dp.length; depth++) {
            for (int length = 0; length < dp.length; length++) {
                if (depth == 0) {
                    dp[depth][length] = 1;
                    prefixSum[depth][length] = length + 1;
                    continue;
                }

                dp[depth][length] = prefixSum[depth - 1][length];
                if (length == 0) {
                    prefixSum[depth][length] = dp[depth][length];
                } else {
                    prefixSum[depth][length] = (prefixSum[depth][length - 1] + dp[depth][length]) % mod;
                }
            }
        }
    }


    public int minDays(int[][] grid) {
        //task 1 do dfs and find total connected components
        //task2 remove each 1 by 1
        //not possible then ans 2

        int totalConnected = getTotalConnected(grid);
        System.out.println(totalConnected);
        if (totalConnected != 1) {
            return 0;
        }


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    totalConnected = getTotalConnected(grid);
                    System.out.println(totalConnected);
                    grid[i][j] = 1;
                    if (totalConnected != 1) {
                        return 1;
                    }
                }
            }
        }

        return 2;


    }

    private int getTotalConnected(int[][] grid) {
        boolean visited[][] = new boolean[grid.length][grid[0].length];
        int totalConnected = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    totalConnected++;
                    dfs(i, j, grid, visited);
                }
            }
        }
        return totalConnected;
    }

    private void dfs(int i, int j, int[][] grid, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return;
        }

        visited[i][j] = true;

        dfs(i + 1, j, grid, visited);
        dfs(i - 1, j, grid, visited);
        dfs(i, j + 1, grid, visited);
        dfs(i, j - 1, grid, visited);
    }


    public int getMaxLen(int[] nums) {
        int i = -1;
        int j = -1;
        int firstNegative = -1;
        int lastNegative = -1;
        int negativeCount = 0;
        int ans = 0;
        for (int k = 0; k < nums.length; k++) {

            int val = nums[k];
            if (val == 0) {
                i = -1;
                j = -1;
                firstNegative = -1;
                lastNegative = -1;
                negativeCount = 0;
                continue;
            }
            j = k;

            if (i == -1) {
                i = k;
            }

            if (val < 0) {
                negativeCount++;
                if (firstNegative == -1) {
                    firstNegative = k;

                }
                lastNegative = k;

            }

            if (negativeCount % 2 == 0) {
                ans = Math.max(ans, (j - i) + 1);
            } else {
                ans = Math.max(ans, Math.max(j - firstNegative, lastNegative - i));
            }
        }
        return ans;
    }

    public boolean containsPattern(int[] arr, int m, int k) {
        boolean ans = false;
        outer:
        for (int i = 0; i + m * k - 1 < arr.length; i++) {
            for (int round = 0; round < k; round++) {
                for (int pattern = 0; pattern < m; pattern++) {
                    if (arr[i + pattern] != arr[i + (round * m) + pattern]) {
                        continue outer;
                    }

                }
            }
            ans = true;
            break;
        }
        System.out.println(ans);
        return ans;
    }


}
