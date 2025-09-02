package PracticeQuestions;

public class Q0427 {

    public static void main(String[] args) {
        Q0427 obj = new Q0427();
        System.out.println(obj.construct(new int[][]{
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}}));
    }

    public Node construct(int[][] grid) {
        return recursion(0, grid.length, 0, grid.length, grid);
    }

    public Node recursion(int xLeft, int xRight, int yLeft, int yRight, int[][] grid) {
        Node node = new Node();

        if (isSame(xLeft, xRight, yLeft, yRight, grid)) {
            node.isLeaf = true;
            node.val = grid[xLeft][yLeft] == 1;
        } else {
            node.val = true;
            int xMid = (xLeft + xRight) / 2;
            int yMid = (yLeft + yRight) / 2;
            node.topLeft = recursion(xLeft, xMid, yLeft, yMid, grid);
            node.topRight = recursion(xLeft, xMid, yMid, yRight, grid);
            node.bottomLeft = recursion(xMid, xRight, yLeft, yMid, grid);
            node.bottomRight = recursion(xMid, xRight, yMid, yRight, grid);
        }

        return node;
    }

    public boolean isSame(int xLeft, int xRight, int yLeft, int yRight, int[][] grid) {
        int value = grid[xLeft][yLeft];
        for (int i = xLeft; i < xRight; i++) {
            for (int j = yLeft; j < yRight; j++) {
                if (value != grid[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }


}
