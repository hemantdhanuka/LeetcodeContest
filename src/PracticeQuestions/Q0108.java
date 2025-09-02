package PracticeQuestions;

class Q0108 {
    public static void main(String[] args) {
        Q0108 obj = new Q0108();
        TreeNode root = obj.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9, 10});
        System.out.println(root);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return getRootNodeForRange(0, nums.length - 1, nums);
    }

    public TreeNode getRootNodeForRange(int l, int r, int[] nums) {
        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = getRootNodeForRange(l, mid - 1, nums);
        node.right = getRootNodeForRange(mid + 1, r, nums);

        return node;
    }
}
