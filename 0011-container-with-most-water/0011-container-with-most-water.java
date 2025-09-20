class Solution {
    public int maxArea(int[] height) {
        int left=0;
        int right=height.length-1;
        int maxarea=0;
        int width=0;
        while(left<right)
        {
            width =right-left;

        int minheight;
        if(height[left]<height[right])
        {
            minheight=height[left];
        }
        else
        {
            minheight=height[right];
        }
        int area= width*minheight;
        if(area>maxarea)
        {
            maxarea=area;
        }
        if(height[left]<height[right])
        {
            left++;
        }
        else
        {
            right--;
        }
    }
        return maxarea;
    }
}