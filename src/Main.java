import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        System.out.println(new Main().minInsertions("()())))()"));
    }

    public int minInsertions(String s){
        char lastChar = '(';
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        for(int i=0;i < s.length();i++){
            if(s.charAt(i)==lastChar){
                int no = stack.removeLast();
                stack.addLast(no+1);
            }else{
                lastChar = s.charAt(i);
                stack.addLast(1);
            }
        }

        int sidhaBracketExtra = 0;
        int insertion = 0;

        int count = 0;
        while(!stack.isEmpty()){
            int freq = stack.removeFirst();
            if(count % 2 ==0){   //sidha Bracket
                sidhaBracketExtra += freq;
            }else{  //cloasing bracket
                if(freq%2==0){
                    if(2*sidhaBracketExtra >= freq){
                        sidhaBracketExtra = sidhaBracketExtra - freq/2;
                    }else{
                        freq = freq - 2*sidhaBracketExtra;
                        insertion += freq/2;
                        sidhaBracketExtra = 0;
                    }
                }else{
                    if(2*sidhaBracketExtra>=freq){
                        sidhaBracketExtra = sidhaBracketExtra - freq/2 - 1;
                        insertion++;
                    }else{
                        freq = freq - 2*sidhaBracketExtra;
                        sidhaBracketExtra = 0;
                        insertion = insertion + freq/2 + 2;
                    }
                }
            }
            count++;
        }

        insertion += 2*sidhaBracketExtra;
        return insertion;

    }

    public int minInsertions2(String s) {

        //converting in some form
        char lastChar = '(';
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        for(int i=0;i < s.length();i++){
            if(s.charAt(i)==lastChar){
                int no = stack.removeLast();
                stack.addLast(no+1);
            }else{
                lastChar = s.charAt(i);
                stack.addLast(1);
            }
        }

        if(stack.size()%2!=0){
            stack.addLast(0);
        }
        int cost = 0;
        while(!stack.isEmpty()){
            int sidhaBracketData = stack.removeFirst();
            int ultaBrackData = stack.removeFirst();

            if(sidhaBracketData*2 >= ultaBrackData){
                cost += sidhaBracketData*2 - ultaBrackData;
            }else{
                int diff =ultaBrackData-2*sidhaBracketData;
                if(diff%2==0){
                    cost += diff/2;
                }else{
                    cost += diff/2 + 2;
                }
            }
        }
        return cost;
    }

    public int minInsertions1(String s) {
        int cost = 0;
        int count =0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)==')'){
                count++;
            }else{
                if(count>=2){
                    count = count-2;
                }else{
                    cost += 2-count;
                    count = 0;
                }
            }
        }

        if(count%2==0){
            cost += count/2;
        }else{
            cost += count/2 + 2;
        }

        return cost;
    }


    public boolean canConvertString(String s, String t, int k) {
        int[] freq = new int[26];
        if(s.length()==t.length()){

            for(int i=0;i<s.length();i++){
                char s1 = s.charAt(i);
                char t1 = t.charAt(i);

                int diff = (s1-t1 + 26) % 26;
                freq[diff]++;
            }
            int maxfreq = 0;
            int maxdiff = 0;
            for(int i=1;i<26;i++){
                if(freq[i] != 0 && freq[i] >= maxfreq){
                    maxfreq = freq[i];
                    maxdiff = i;
                }
            }

            if(maxfreq==0 && maxdiff == 0){
                return true;
            }

            if(k>= maxdiff + 26*(maxfreq-1)){
                return true;
            }
        }
        return false;
    }



    public int findKthPositive(int[] arr, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0;i< arr.length;i++){
            set.add(arr[i]);
        }

        int j=0;
        for(int i = 1;i <2001 ;i++){
            if(set.contains(i)){
                continue;
            }
            j++;
            if(j==k){
                return i;
            }
        }
        return -1;
    }
}
