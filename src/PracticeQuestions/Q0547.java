package PracticeQuestions;

class Q0547NumberOfProvinces {
    public static void main(String[] args) {
        Q0547NumberOfProvinces obj = new Q0547NumberOfProvinces();
        System.out.println(obj.findCircleNum(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
    }

    public int findCircleNum(int[][] isConnected) {
        UnionFind unionFind = new UnionFind(isConnected.length);

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                int connected = isConnected[i][j];

                if (connected == 1) {
                    int parent1 = unionFind.find(i);
                    int parent2 = unionFind.find(j);

                    unionFind.union(parent1, parent2);
                }
            }
        }

        return unionFind.getNoOfParent();

    }

}


class UnionFind {
    int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }


    public int find(int index) {
        if (parent[index] == index) {
            return index;
        }

        int parentOfParent = find(parent[index]);
        parent[index] = parentOfParent;
        return parentOfParent;
    }

    public void union(int index1, int index2) {
        parent[index1] = index2;
    }

    public int getNoOfParent() {
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        return count;
    }

}
