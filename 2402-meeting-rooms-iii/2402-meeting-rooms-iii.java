class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        int[] count = new int[n];

        PriorityQueue<Integer> available = new PriorityQueue<>();
        for (int i = 0; i < n; i++) available.add(i);

        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0])
        );

        for (int[] m : meetings) {
            long start = m[0], end = m[1];
            long duration = end - start;

            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                available.add((int) busy.poll()[1]);
            }

            if (!available.isEmpty()) {
                int room = available.poll();
                busy.add(new long[]{end, room});
                count[room]++;
            } else {
                long[] top = busy.poll();
                long newEnd = top[0] + duration;
                busy.add(new long[]{newEnd, top[1]});
                count[(int) top[1]]++;
            }
        }

        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (count[i] > count[ans]) ans = i;
        }
        return ans;
    }
}
