class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int prefixLength = prefix.length();
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            while (j < prefixLength && j < strs[i].length() && prefix.charAt(j) == strs[i].charAt(j)) {
                j++;
            }
            prefixLength = j;
            if (prefixLength == 0) {
                return "";
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < prefixLength; i++) {
            result.append(prefix.charAt(i));
        }

        return result.toString();
    }
}
