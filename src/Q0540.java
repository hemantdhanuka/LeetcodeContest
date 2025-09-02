public class Q0540 {
    public static void main(String[] args) {
        Q0540 obj = new Q0540();
        System.out.println(obj.singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }

    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            if (l == r) {
                return nums[l];
            }

            if (r - l == 2) {
                if (nums[l] != nums[l + 1]) {
                    return nums[l];
                } else {
                    return nums[r];
                }
            }

            int mid = (l + r) / 2;
            if (mid % 2 == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {
                l = mid;
            } else {
                r = mid;
            }
        }

        return -1;
    }
}
