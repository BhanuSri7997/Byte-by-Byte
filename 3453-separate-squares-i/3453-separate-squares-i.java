class Solution {
    public double separateSquares(int[][] squares) {
        double low = Double.MAX_VALUE, high = Double.MIN_VALUE;
        double totalArea =0.0;
        
        for(int[] s : squares) {
            double y = s[1];
            double l = s[2];
            low =Math.min(low,y);
            high=Math.max(high, y+l);
            totalArea+=l*l;
            
        }
        
        for(int i=0;i<60;i++) {
            double mid =(low+high)/2.0;
            double below =0.0;

            for(int[] s: squares) {
                double y0 = s[1];
                double l = s[2];
                double y1=y0+l;

                if(mid<=y0) {
                    continue;
                } else if (mid >= y1) {
                    below +=l*l;
                } else {
                    below+=l*(mid-y0);
                }
            }
            if(below * 2 <totalArea) {
                low=mid;
            } else {
                high =mid;
            }
        }
        return low;
    }
}