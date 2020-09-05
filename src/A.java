import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class A {

    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.longestAwesome("3242415"));
        System.out.println(a.longestAwesome("12345678"));
        System.out.println(a.longestAwesome("213123"));
        System.out.println(a.longestAwesome("00"));
    }

    public int longestAwesome(String s) {
        //tasks
        //1 create mask for each position, write it value in decimal in new array
        //2for each value store minimum index for each question
        //3traverse whole array and find index for minimum and diff for switching one bit also return max


        //1
        int currentMaskValue = 0;
        int[] mask = new int[s.length()+1];
        mask[0]=0;
        for(int i=0; i<s.length(); i++){
            int no = s.charAt(i)-'0';
            currentMaskValue = currentMaskValue ^ (1<<no);
            mask[i+1] = currentMaskValue;
        }

        //2
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i < mask.length;i++){
            if(!map.containsKey(mask[i])){
                map.put(mask[i],i);
            }
        }

        //3

        int maxLength = 0;
        for(int i=1;i<mask.length;i++){
            int maskValue = mask[i];

            //finding all even
            if(map.containsKey(maskValue)){
                maxLength = Math.max(maxLength, i-map.get(maskValue));
            }
            //find if one odd
            for(int j=0;j<10;j++){
                int maskContainsOdd = maskValue ^ (1 << j);
                if(map.containsKey(maskContainsOdd)){
                    maxLength = Math.max(maxLength, i-map.get(maskContainsOdd));
                }
            }
        }
        return maxLength;

    }

    public int numSplits(String s) {

        int left[] = new int[s.length()];
        int right[] = new int[s.length()];


        Set<Character> set = new HashSet<>();
        for(int i=0 ; i < s.length();i++){
            set.add(s.charAt(i));
            left[i] = set.size();

        }
        set.clear();

        for(int i=s.length()-1 ; i >= 0 ;i--){
            set.add(s.charAt(i));
            right[i] = set.size();
        }
        int ans = 0;
        for(int i=0;i<s.length()-1;i++){
            if(left[i]== right[i+1]){
                ans++;
            }
        }

        return ans;

    }

    public int numOfSubarrays(int[] arr) {

        int oddCount = 0;
        int evenCount = 0;
        int sum =0;
        for(int i= 0; i< arr.length;i++){
            sum = sum + arr[i];

            if(sum%2==0){
                evenCount++;
            }else {
                oddCount++;
            }
        }

        int ans = oddCount;
        if(arr[0]%2==0){
            evenCount--;
        }else {
            oddCount --;
        }

        for(int i = 1;i<arr.length;i++){
            if(arr[i-1]%2==1){
                int temp = evenCount;
                evenCount = oddCount;
                oddCount =temp;
            }

            ans = (ans + oddCount)%1000000007;

            if(arr[i]%2==0){
                evenCount--;
            }else{
                oddCount--;
            }
        }

        return  ans;

    }



}
