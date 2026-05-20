class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;

        int[] ans = new int[n];

        long maskA = 0;
        long maskB = 0;

        for(int i = 0; i < n; i++) {

            // set bit for A[i]
            maskA |= (1L << A[i]);

            // set bit for B[i]
            maskB |= (1L << B[i]);

            // common elements
            long commonMask = maskA & maskB;

            // count set bits
            ans[i] = Long.bitCount(commonMask);
        }

        return ans;
    }
}