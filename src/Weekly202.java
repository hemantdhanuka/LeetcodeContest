import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Weekly202 {
    public static void main(String[] args) {
        Weekly202 weekly202 = new Weekly202();
        System.out.println(weekly202.minDays(2000000000));
    }

    public int minDays(int n) {
        Map<Integer,Integer> dp = new HashMap<>();
        return fun(n,dp);
    }

    private int fun(int n, Map<Integer,Integer> dp) {
        if(n<3){
            return n;
        }
        if(dp.containsKey(n)){
            return dp.get(n);
        }
        int ans1 =  1 + n%2 + fun(n/2, dp);
        int ans2 = 1 + n % 3 + fun(n / 3, dp);
        dp.put(n, Math.min(ans1,ans2));
        return dp.get(n);

    }


    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);

        int ans = 1;
        int low =2;
        int high = 1000000000;

        while(low<=high){
            int mid = (low+high)/2;

            if(check(mid, position,m)){
                ans = mid;
                low = mid+1;
            }else{
                high = mid -1;
            }
        }
        return ans;
    }

    private boolean check(int ans, int[] position, int m) {

        int count = 1;
        int last = position[0];
        for(int i=1;i < position.length;i++){
            if(position[i]-ans>=last){
                count++;
                last = position[i];
            }
        }
        if(count >= m ){
            return true;
        }else{
            return false;
        }
    }

    public int minOperations(int n) {
        int half = n/2;
        int a = 1;
        if(n % 2 == 1){
            a = 2;
        }
        int d = 2;
        int sum = (half*(2*a + (half-1)*d))/2;
        return sum;
    }

    public boolean threeConsecutiveOdds(int[] arr) {
            for(int i=0;i< arr.length-2; i++){
                if(arr[i]%2==1){
                    if(arr[i+1] % 2  == 1 && arr [i+2] % 2 ==1){
                        return true;
                    }
                }

            }
            return false;
    }
}
