import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class BiWeekly34 {
    int mod = 1000000007;

    public static void main(String[] args) {
        BiWeekly34 biWeekly34 = new BiWeekly34();
        int ans = biWeekly34.findLengthOfShortestSubarray(new int[]{1});
    }

    public int findLengthOfShortestSubarray(int[] arr) {
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                if (arr[i] < arr[i - 1]) {
                    break;
                }
            }
            list1.add(arr[i]);
        }

        if (list1.size() == arr.length) {
            return 0;
        }

        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i != arr.length - 1) {
                if (arr[i] > arr[i + 1]) {
                    break;
                }
            }
            list2.add(arr[i]);
        }

        Collections.reverse(list2);
        int max = Math.max(list1.size(), list2.size());

        int i = 0;
        int j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) <= list2.get(j)) {
                max = Math.max(max, i + 1 + (list2.size() - j));
                i++;
            } else {
                j++;
            }
        }

        return arr.length - max;
    }

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        int dp[][] = new int[n][fuel + 1];
        for (int d[] : dp) {
            Arrays.fill(d, -1);
        }
        City[] cities = new City[n];
        for (int i = 0; i < n; i++) {
            cities[i] = new City(i, locations[i]);
        }

        Arrays.sort(cities);
        HashMap<Integer, City> map = new HashMap<>();
        int startCityPosition = -1;
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].cityNo == start) {
                startCityPosition = i;
            }
        }

        int ans = calc(startCityPosition, fuel, finish, cities, dp);
        System.out.println(ans);
        return ans;
    }

    private int calc(int startCityPosition, int fuel, int finish, City[] cities, int[][] dp) {
        if (dp[startCityPosition][fuel] != -1) {
            return dp[startCityPosition][fuel];
        }
        int temp = startCityPosition + 1;
        long sum = 0;
        if (cities[startCityPosition].cityNo == finish) {
            sum++;
        }
        while (temp < cities.length && cities[temp].location - cities[startCityPosition].location <= fuel) {
            sum += calc(temp, fuel - (cities[temp].location - cities[startCityPosition].location), finish, cities, dp);
            sum %= mod;
            temp++;
        }

        temp = startCityPosition - 1;
        while (temp >= 0 && cities[startCityPosition].location - cities[temp].location <= fuel) {
            sum += calc(temp, fuel - (cities[startCityPosition].location - cities[temp].location), finish, cities, dp);
            sum %= mod;
            temp--;
        }
        int ans = (int) sum;
        dp[startCityPosition][fuel] = ans;
        return ans;

    }

    public int diagonalSum(int[][] mat) {

        int size = mat.length;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += mat[i][i];
        }

        int row = 0;
        int col = size - 1;
        for (int i = 0; i < size; i++) {
            if (row + i != col - i) {
                sum += mat[row + i][col - i];
            }
        }
        return sum;
    }

    public int numWays(String s) {
        int oneCount = 0;
        int n = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                oneCount++;
            }
        }

        if (s.length() < 3 || oneCount % 3 != 0) {
            return 0;
        }
        if (oneCount == 0) {
            return (int) ((((n - 1) * (long) (n - 2)) / 2) % mod);
        }

        int eachStringOne = oneCount / 3;

        int i = 0;
        int count = 0;
        while (true) {
            if (s.charAt(i) == '1') {
                count++;
            }
            if (count == eachStringOne) {
                break;
            }
            i++;
        }

        int j = i + 1;
        while (true) {
            if (s.charAt(j) == '1') {
                break;
            }
            j++;
        }

        int diff1 = j - i;

        i = n - 1;
        count = 0;
        while (true) {
            if (s.charAt(i) == '1') {
                count++;
            }
            if (count == eachStringOne) {
                break;
            }
            i--;
        }

        j = i - 1;
        while (true) {
            if (s.charAt(j) == '1') {
                break;
            }
            j--;
        }

        int diff2 = i - j;

        return (int) ((diff1 * (long) diff2) % mod);
    }

    class City implements Comparable<City> {
        int cityNo;
        int location;

        public City(int cityNo, int location) {
            this.cityNo = cityNo;
            this.location = location;
        }

        @Override
        public int compareTo(City city) {
            return this.location - city.location;
        }

        @Override
        public String toString() {
            return "City{" +
                    "cityNo=" + cityNo +
                    ", location=" + location +
                    '}';
        }
    }


}
