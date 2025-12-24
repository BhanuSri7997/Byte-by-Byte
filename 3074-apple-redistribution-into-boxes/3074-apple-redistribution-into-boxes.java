class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int n=apple.length;
        int m = capacity.length;
        int count=0;
        int space=0;

        for(int i=0;i<n;i++){
            count=count+apple[i];
        }

        Arrays.sort(capacity);

        int boxes=0;

        for(int i=m-1;i>=0;i--){
            space=space+capacity[i];
            boxes++;

            if(space>=count){
                return boxes;
            }
        }
        return boxes;
    }
}