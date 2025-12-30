class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows - 2; i++) {
            for (int j = 0; j < cols - 2; j++) {

                if (grid[i+1][j+1] != 5) continue;

                if (isMagic(grid, i, j)) count++;
            }
        }
        return count;
    }

    private boolean isMagic(int[][] g, int r, int c) {
        boolean[] seen = new boolean[10];

        for (int i = r; i < r+3; i++) {
            for (int j = c; j < c+3; j++) {
                int val = g[i][j];
                if (val < 1 || val > 9 || seen[val]) return false;
                seen[val] = true;
            }
        }

        int s1 = g[r][c] + g[r][c+1] + g[r][c+2];
        int s2 = g[r+1][c] + g[r+1][c+1] + g[r+1][c+2];
        int s3 = g[r+2][c] + g[r+2][c+1] + g[r+2][c+2];

        if (!(s1 == s2 && s2 == s3)) return false;

        int c1 = g[r][c] + g[r+1][c] + g[r+2][c];
        int c2 = g[r][c+1] + g[r+1][c+1] + g[r+2][c+1];
        int c3 = g[r][c+2] + g[r+1][c+2] + g[r+2][c+2];

        if (!(c1 == c2 && c2 == c3 && c3 == s1)) return false;

        int d1 = g[r][c] + g[r+1][c+1] + g[r+2][c+2];
        int d2 = g[r][c+2] + g[r+1][c+1] + g[r+2][c];

        return d1 == d2 && d1 == s1;
    }
}
