package PracticeQuestions;

public class Q1061 {
    public static void main(String[] args) {
        Q1061 obj = new Q1061();
        System.out.println(obj.smallestEquivalentString("parker", "morris", "parser"));
        System.out.println(obj.smallestEquivalentString("hello", "world", "hold"));
        System.out.println(obj.smallestEquivalentString("leetcode", "programs", "sourcecode"));
    }


    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int parents[] = new int[26];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < s1.length(); i++) {
            int u = s1.charAt(i) - 'a';
            int v = s2.charAt(i) - 'a';

            merge(u, v, parents);

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < baseStr.length(); i++) {
            sb.append((char) ('a' + (find(baseStr.charAt(i) - 'a', parents))));
        }

        return sb.toString();
    }

    private void merge(int u, int v, int parents[]) {
        int parentU = find(u, parents);
        int parentV = find(v, parents);

        union(parentU, parentV, parents);
    }

    private int find(int node, int parents[]) {
        if (parents[node] == node) {
            return node;
        }

        int parentNode = find(parents[node], parents);
        parents[node] = parentNode;
        return parentNode;
    }

    private void union(int parent1, int parent2, int parents[]) {
        parents[Math.max(parent1, parent2)] = parents[Math.min(parent1, parent2)];
    }


}
