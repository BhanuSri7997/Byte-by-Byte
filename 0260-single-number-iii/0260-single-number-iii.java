class Solution {
    public int[] singleNumber(int[] nums) {
        int res1=0,res2=0,XOR=0;
        int n = nums.length;
        for(int i=0;i<n;i++){
            XOR=XOR^nums[i];
        }
        int rm=XOR&~(XOR-1);
        for(int i=0;i<n;i++){
            if((nums[i] & rm) != 0){
                res1=res1^nums[i];       
            }
            else{
                res2=res2^nums[i];
            }
        }
        return new int[]{res1,res2};
    }
}