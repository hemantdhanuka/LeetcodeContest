import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Weekly203 {
    public static void main(String[] args) {
        Weekly203 weekly203 = new Weekly203();
        weekly203.findLatestStep(new int[]{3,1,5,4,2},2);
        weekly203.findLatestStep(new int[]{3,5,1,2,4},1);
        //weekly203.stoneGameV(new int[]{6,2,3,4,5,5});
        //weekly203.stoneGameV(new int[]{7,7,7,7,7,7,7});
        //weekly203.stoneGameV(new int[]{1,1,2});
    }


    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        int parent[] = new int[n+1];
        int size[] = new int[n+1];
        int mSize = 0;
        int stepNoAns = -1;
        for(int i =0;i< arr.length;i++){
            int node = arr[i];
            //find parent of left of node
            //find parent of right of node
            int totalNewSetSize = 1;
            if(node>1){
                int parentOfLeft = findParent(node-1,parent);
                if(parentOfLeft!=0){
                    int sizeLeft = size[parentOfLeft];
                    if(sizeLeft == m){
                        mSize--;
                    }
                    totalNewSetSize +=sizeLeft;
                    parent[parentOfLeft] = node;
                }
            }
            if(node<n){
                int parentOfRight = findParent(node+1,parent);
                if(parentOfRight!=0){
                    int rightSize = size[parentOfRight];
                    if(rightSize == m){
                        mSize--;
                    }
                    totalNewSetSize +=rightSize;
                    parent[parentOfRight] = node;
                }
            }

            if(totalNewSetSize==m){
                mSize++;
            }

            parent[node] = node;
            size[node] = totalNewSetSize;
            if(mSize>0){
                stepNoAns = i+1;
            }
        }
        System.out.println(stepNoAns);
        return stepNoAns;

    }

    public int findParent(int x, int parent[]){
        if(parent[x]==x){
            return x;
        }
        int val = findParent(parent[x],parent);
        parent[x] = val;
        return val;
    }


    public int stoneGameV(int[] stoneValue) {
        int dp[][]=new int[stoneValue.length][stoneValue.length];
        for(int d[]:dp){
            Arrays.fill(d,-1);
        }
        int prefixSum[] = new int[stoneValue.length];
        prefixSum[0] = stoneValue[0];
        for(int i=1;i<prefixSum.length;i++){
            prefixSum[i] = prefixSum[i-1] + stoneValue[i];
        }

        int ans= fun1(0,stoneValue.length-1,prefixSum,dp);
        System.out.println(ans);
        return ans;
    }
    public int fun1(int l, int r, int prefixSum[],int dp[][]){
        if(l==r){
            return 0;
        }
        if(dp[l][r]!=-1){
            return dp[l][r];
        }

        int i=l;
        int ans1 =0;
        while(i<r){
            int sumLeftRow = sum(l,i,prefixSum);
            int sumRightRow = sum(i+1,r,prefixSum);
            if(sumLeftRow>=sumRightRow){
                ans1 = sumRightRow+ fun1(i+1,r,prefixSum,dp);
                break;
            }

            i++;
        }
        i = r;
        int ans2=0;
        while(i>l){
            int sumLeftRow = sum(l,i-1,prefixSum);
            int sumRightRow = sum(i,r,prefixSum);
            if(sumRightRow>=sumLeftRow){
                ans2 = sumLeftRow+ fun1(l,i-1,prefixSum,dp);
                break;
            }
            i--;
        }
        int max = Math.max(ans1,ans2);

        dp[l][r]=max;
        return dp[l][r];

    }

    public int fun(int l, int r, int prefixSum[],int dp[][]){
        if(l==r){
            return 0;
        }
        if(dp[l][r]!=-1){
           return dp[l][r];
        }

        int i=l;
        int max = 0;
        while(i<r){
            int sumLeftRow = sum(l,i,prefixSum);
            int sumRightRow = sum(i+1,r,prefixSum);
            int ans = 0;
            if(sumRightRow>sumLeftRow){
                ans = sumLeftRow+ fun(l,i,prefixSum,dp);
            }
            else if(sumRightRow<sumLeftRow){
                ans = sumRightRow+ fun(i+1,r,prefixSum,dp);
            }else{
                //equal
                ans = Math.max(sumLeftRow+ fun(l,i,prefixSum,dp),sumRightRow+ fun(i+1,r,prefixSum,dp));
            }
            max = Math.max(max,ans);
            i++;
        }


        dp[l][r]=max;
        return dp[l][r];

    }

    private int sum(int l, int r, int[] prefixSum) {
        if(l==0){
            return prefixSum[r];
        }

        return prefixSum[r]-prefixSum[l-1];
    }


    class Node{
        int l;
        int r;
        Node left = null;
        Node right = null;

        Node(int l,int r){
            this.l = l;
            this.r = r;
        }

    }



    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int n = piles.length;
        int picked = 0;
        int sum=0;
        while(true){
            n = n-2;
            sum += piles[n];
            picked++;
            if(picked==piles.length/3){
                break;
            }
        }
        return sum;
    }


    public List<Integer> mostVisited(int n, int[] rounds) {
        int i = rounds[0];
        int j = rounds[rounds.length-1];

        List<Integer> answer = new ArrayList<>();
        if(j>=i){
            while(i<=j){
                answer.add(i);
                i++;
            }
        }
        else{
            for(int k=1;k<=j;k++){
                answer.add(k);
            }

            for(int k=i;k<=n;k++){
                answer.add(k);
            }
        }
        System.out.println(answer);
        return answer;
    }



}
