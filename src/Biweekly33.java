import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Biweekly33 {
    public static void main(String[] args) {
        Biweekly33 biweekly33 = new Biweekly33();

    }

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean ans = false;
        outer : for(int i='a';i<='z';i++){

            int flag[][] = new int[m][n];
            for(int j=0;j<m;j++){
                for(int k=0;k<n;k++){
                    if(grid[j][k]==(char)i && flag[j][k]==0){
                    boolean resp= dfs(j,k,flag,grid,1,(char)i,m,n);
                    if(resp){
                        ans = true;
                        break outer;
                    }
                    }
                }
            }

        }
        return ans;
    }

    private boolean dfs(int i, int j, int[][] flag, char grid[][], int count, char ch, int m ,int n) {
        if(i<0 || i>=m || j<0 || j>=n || grid[i][j]!=ch){
            return false;
        }

        if(flag[i][j]!=0){
            if(count- flag[i][j] >=4){
                return true;
            }else{
                return false;
            }
        }
        flag[i][j] = count;

        boolean ans;

        ans = dfs(i+1,j,flag,grid,count+1,ch,m,n) | dfs(i-1,j,flag,grid,count+1,ch,m,n) |dfs(i,j+1,flag,grid,count+1,ch,m,n) | dfs(i,j-1,flag,grid,count+1,ch,m,n);
        return ans;
    }


    public int minOperations(int[] nums) {
        int ans=0;
        while (!chechAllZero(nums)){
            boolean flag=true;
            for(int i=0;i< nums.length;i++){
                    if(nums[i]>1){
                        flag =false;
                    }
                    ans+=(nums[i]%2);
                    nums[i]=nums[i]/2;
            }
            if(!flag){
            ans++;
            }
        }
        return ans;
    }

    public boolean chechAllZero(int[] nums){
        boolean isAllZero = true;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                isAllZero = false;
                break;
            }
        }
        return isAllZero;
    }


    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Set<Integer> set = new HashSet<>();

        for(List<Integer> edge:edges){
            set.add(edge.get(1));
        }
        List<Integer> answer = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(!set.contains(i)){
                answer.add(i);
            }
        }
        System.out.println(answer);
        return answer;
    }

    public String thousandSeparator(int n) {
        String val = n+"";
        String ans = "";

        int count = 0;
        for(int i=val.length()-1;i>=0;i--){
            count++;

            ans = val.charAt(i)+ans;
            if(count==3 && i!=0){
                ans = '.'+ans;
                count=0;
            }

        }
        return ans;

    }


}
