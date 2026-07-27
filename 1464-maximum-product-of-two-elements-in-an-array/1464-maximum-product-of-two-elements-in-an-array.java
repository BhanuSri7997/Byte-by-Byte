class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int second_max = Integer.MIN_VALUE;

        for(int num : nums){
            if(num>max){
                second_max=max;
                max=num;
            } else if(num>second_max){
                second_max=num;
            }
        }
        return (max-1)*(second_max-1);
    }
}