import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            if (result.isEmpty() || result.get(result.size() - 1)[1] < interval[0]) {
                result.add(interval);
            } else {
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], interval[1]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] intervals = {{1,5},{3,6},{8,10},{15,18}};
        int[][] merged = sol.merge(intervals);

        for (int[] arr : merged) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
