import java.util.HashMap;

public class Weekly205 {
    public static void main(String[] args) {
        Weekly205 weekly205 = new Weekly205();
        weekly205.maxNumEdgesToRemove(4, new int[][]{
                {3, 1, 2},
                {3, 2, 3},
                {1, 1, 3},
                {1, 2, 4},
                {1, 1, 2},
                {2, 3, 4}
        });
    }


    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int removed1 = fun(3, 1, edges, n);
        int removed2 = fun(3, 2, edges, n);
        if (removed1 == -1 || removed2 == -1) {
            return -1;
        } else return removed1 + removed2;
    }

    private int fun(int type3, int otherType, int[][] edges, int n) {
        int parent[] = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int totalComponents = n;

        //union find for type3 edge
        int removed = 0;
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            if (edge[0] == type3) {
                int u = edge[1] - 1;
                int v = edge[2] - 1;

                int parentU = find(u, parent);
                int parentV = find(v, parent);

                if (parentU == parentV) {
                    removed++;
                } else {
                    parent[parentU] = parentV;
                    totalComponents--;
                }
            }
        }
        if (otherType == 2) {
            removed = 0;
        }

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            if (edge[0] == otherType) {
                int u = edge[1] - 1;
                int v = edge[2] - 1;

                int parentU = find(u, parent);
                int parentV = find(v, parent);

                if (parentU == parentV) {
                    removed++;
                } else {
                    parent[parentU] = parentV;
                    totalComponents--;
                }
            }
        }

        if (totalComponents == 1) {
            return removed;
        } else {
            return -1;
        }
    }

    private int find(int u, int[] parent) {
        if (parent[u] == u) {
            return u;
        }

        int p = find(parent[u], parent);
        parent[u] = p;
        return p;
    }


    public int minCost(String s, int[] cost) {
        int max = cost[0];
        int sum = cost[0];
        for (int i = 1; i < s.length(); i++) {
            sum = sum + cost[i];
            if (s.charAt(i) == s.charAt(i - 1)) {
                max = Math.max(max, cost[i]);
            } else {
                sum -= max;
                max = cost[i];
            }
        }

        sum -= max;
        System.out.println(sum);
        return sum;
    }

    public int numTriplets(int[] nums1, int[] nums2) {
        int triplet = getAns(nums1, nums2) + getAns(nums2, nums1);
        System.out.println(triplet);
        return triplet;
    }

    private int getAns(int[] nums1, int[] nums2) {
        HashMap<Long, Integer> map1 = new HashMap<>();

        for (int i = 0; i < nums1.length; i++) {
            for (int j = i + 1; j < nums1.length; j++) {
                long prod = nums1[i] * (long) nums1[j];

                if (!map1.containsKey(prod)) {
                    map1.put(prod, 0);
                }
                map1.put(prod, map1.get(prod) + 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < nums2.length; i++) {
            long val = nums2[i] * (long) nums2[i];
            int freq = 0;
            if (map1.containsKey(val)) {
                freq = map1.get(val);
            }
            ans += freq;
        }
        return ans;
    }

    public String modifyString(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '?') {
                String temp = "abc";
                char insert = 'a';
                for (int j = 0; j < temp.length(); j++) {
                    char op = temp.charAt(j);
                    if (i > 0) {
                        if (ans.charAt(i - 1) == op) {
                            continue;
                        }
                    }
                    if (i < s.length() - 1) {
                        if (op == s.charAt(i + 1)) {
                            continue;
                        }
                    }
                    insert = op;
                    break;
                }

                ans.append(insert);
            } else {
                ans.append(ch);
            }


        }
        System.out.println(ans.toString());
        return ans.toString();

    }
}
