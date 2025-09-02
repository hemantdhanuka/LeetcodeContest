package PracticeQuestions;

public class Q0134 {
    public static void main(String[] args) {
        Q0134 obj = new Q0134();
        System.out.println(obj.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int remainingGasAtParticularStation = 0;
        int visitedStations = 0;
        int ans = -1;
        for (int i = 0; i < gas.length * 2; i++) {
            int index = i % gas.length;

            int totalGas = gas[index] + remainingGasAtParticularStation;
            if (totalGas >= cost[index]) {
                remainingGasAtParticularStation = totalGas - cost[index];
                visitedStations++;
            } else {
                remainingGasAtParticularStation = 0;
                visitedStations = 0;
            }


            if (visitedStations == gas.length) {
                ans = (i + 1) % gas.length;
                break;
            }
        }

        return ans;
    }

}
