class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb=new StringBuilder();
        int j=0;
        for(int i=0;i<nums.length;i++) sb.append((nums[i].charAt(j++)-'0')^1);
        return sb.toString();
    }
}