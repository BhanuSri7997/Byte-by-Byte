class Solution {
    public int findNumbers(int[] nums) {
        int temp = 0;
        int digits = 0;
        int even = 0;
        for (int i = 0; i < nums.length; i++) {
            digits = 0;
            int num = nums[i];

            while (num > 0) {
                digits++;
                num /= 10;
            }
            if (digits % 2 == 0) {
                even++;
            }
        }
        return even;
    }
}