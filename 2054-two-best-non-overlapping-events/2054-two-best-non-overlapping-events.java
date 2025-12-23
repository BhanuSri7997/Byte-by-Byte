class Solution {

    public int maxTwoEvents(int[][] events) {
        int n = events.length;

        mergeSort(events, 0, n - 1);

        int[] prefixMax = new int[n];
        prefixMax[0] = events[0][2];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], events[i][2]);
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            int start = events[i][0];
            int value = events[i][2];

            int idx = binarySearch(events, i - 1, start);

            if (idx != -1) {
                answer = Math.max(answer, value + prefixMax[idx]);
            } else {
                answer = Math.max(answer, value);
            }
        }

        return answer;
    }

    private int binarySearch(int[][] events, int right, int start) {
        int left = 0;
        int res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (events[mid][1] < start) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private void mergeSort(int[][] arr, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[][] arr, int left, int mid, int right) {
        int[][] temp = new int[right - left + 1][3];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i][1] <= arr[j][1]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }
}
