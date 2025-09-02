package PracticeQuestions;


public class Q0098 {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.pow(2, 31) - 1);
        System.out.println(Math.pow(-2, 31));
        System.out.println(Integer.MIN_VALUE);
    }

    public boolean isValidBST(TreeNode root) {
        return validateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean validateBST(TreeNode node, long leftRange, long rightRange) {
        if (node == null) {
            return true;
        }

        if (node.val < leftRange || node.val > rightRange) {
            return false;
        }

        return validateBST(node.left, leftRange, ((long) node.val) - 1) && validateBST(node.right, ((long) node.val) + 1, rightRange);
    }

}
