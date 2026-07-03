import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

class Solution {
    // Member fields to hold state for a single execution, similar to Python's closure scope
    private int n;
    private List<List<int[]>> adjList;
    private int[] inDegree;
    private int maxEdgeLength;

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        this.n = online.length;
        
        if (edges.length == 0) {
            return -1;
        }

        // Create adjacency list and gather graph properties
        createAdjList(edges, online);
        
        // Find topological sort order of the graph
        List<Integer> topoSort = findToposortOrder();
        
        // Binary search for the maximum possible minimum edge weight in the path
        return binarySearch(k, topoSort);
    }

    private void createAdjList(int[][] edges, boolean[] online) {
        this.inDegree = new int[n];
        this.maxEdgeLength = 0;
        this.adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            this.adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            // Consider only edges between online nodes
            if (online[u] && online[v]) {
                this.adjList.get(u).add(new int[]{v, cost});
                this.inDegree[v]++;
                this.maxEdgeLength = Math.max(this.maxEdgeLength, cost);
            }
        }
    }

    private List<Integer> findToposortOrder() {
        Queue<Integer> dq = new ArrayDeque<>();
        List<Integer> topoSort = new ArrayList<>();

        // Initialize queue with nodes having an in-degree of 0
        for (int node = 0; node < n; node++) {
            if (this.inDegree[node] == 0) {
                dq.offer(node);
            }
        }

        // Kahn's algorithm for topological sorting
        while (!dq.isEmpty()) {
            int node = dq.poll();
            topoSort.add(node);

            for (int[] edge : this.adjList.get(node)) {
                int adj = edge[0];
                this.inDegree[adj]--;
                if (this.inDegree[adj] == 0) {
                    dq.offer(adj);
                }
            }
        }
        return topoSort;
    }

    private boolean checkFeasibilityWithWt(int minEdgeWt, long k, List<Integer> topoSort) {
        long[] distances = new long[n];
        Arrays.fill(distances, Long.MAX_VALUE);

        if (n > 0) {
            distances[0] = 0; // The path starts at node 0
        } else {
            return false;
        }

        // Calculate shortest paths in topological order
        for (int node : topoSort) {
            if (distances[node] == Long.MAX_VALUE) {
                continue; // Skip unreachable nodes
            }

            for (int[] edge : this.adjList.get(node)) {
                int adj = edge[0];
                int cost = edge[1];

                // Skip if edge cost is less than the required minimum
                if (cost < minEdgeWt) {
                    continue;
                }

                // Relax the edge if the path cost does not exceed k
                if (distances[node] + cost <= k) {
                    distances[adj] = Math.min(distances[adj], distances[node] + cost);
                }
            }
        }

        // Check if the destination node is reachable within the cost limit k
        return distances[n - 1] <= k;
    }

    private int binarySearch(long k, List<Integer> topoSort) {
        int low = 0;
        int high = (int) Math.min(k, (long) this.maxEdgeLength);

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (checkFeasibilityWithWt(mid, k, topoSort)) {
                // If a path is feasible, try for a higher score
                low = mid + 1;
            } else {
                // If not feasible, the minimum edge weight is too high
                high = mid - 1;
            }
        }
        
        // 'low' becomes the first value for which the check fails, so 'low - 1' is the answer
        return low - 1;
    }
}