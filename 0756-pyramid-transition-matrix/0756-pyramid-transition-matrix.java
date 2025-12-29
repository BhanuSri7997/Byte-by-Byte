class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {

        Map<String, List<Character>> map = new HashMap<>();

        for (String s : allowed) {
            String key = s.substring(0, 2);
            char top = s.charAt(2);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(top);
        }

        return dfs(bottom, map);
    }

    private boolean dfs(String row, Map<String, List<Character>> map) {
    
        if (row.length() == 1) return true;

        List<String> nextRows = new ArrayList<>();
        buildNextRows(row, 0, new StringBuilder(), nextRows, map);

        for (String next : nextRows) {
            if (dfs(next, map)) return true;
        }
        return false;
    }

    private void buildNextRows(
            String row,
            int idx,
            StringBuilder sb,
            List<String> result,
            Map<String, List<Character>> map) {

        if (idx == row.length() - 1) {
            result.add(sb.toString());
            return;
        }

        String key = row.substring(idx, idx + 2);
        if (!map.containsKey(key)) return;

        for (char c : map.get(key)) {
            sb.append(c);
            buildNextRows(row, idx + 1, sb, result, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
