class Solution {
    public int countDigits(int num) {
        int count=0;
        int temp=0;
        int n=num;
        while(num>0){
            temp = num % 10;
            if(n%temp==0){
                count++;
            }
            num=num/10;
        }
        return count;
    }
}