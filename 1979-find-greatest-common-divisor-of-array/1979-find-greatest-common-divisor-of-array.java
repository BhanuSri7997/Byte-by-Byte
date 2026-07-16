class Solution {
    public int findGCD(int[] nums) {
        int minimum = Integer.MAX_VALUE;
        int maximum = Integer.MIN_VALUE;
        for(int num:nums)
        {
            minimum= num<minimum?num:minimum;
            maximum = num>maximum?num:maximum;
        }
        int a = minimum, b= maximum;
        while(b!=0)
        {
            int temp = b;
            b = a%b;
            a=temp;
        }
        return a;
    }
}