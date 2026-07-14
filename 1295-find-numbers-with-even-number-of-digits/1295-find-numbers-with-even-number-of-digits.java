class Solution {
    public int findNumbers(int[] nums) {
        int temp=0;
        int digits =0;
        int even=0;
        for(int i=0;i<nums.length;i++){
            digits=0;
            while(nums[i]>0){
                temp=nums[i]%10;
                digits++;
                nums[i]=nums[i]/10;
            }
            if(digits%2==0){
                even++;
            }
        }
        return even;
    }
}