package com.teng.androidms.android.java.leetcode;

import java.util.HashMap;

/**
 * 和为K的最大长度
 */
class Solution4 {

    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();

//        int[] array = {1,-1,5,-2,3};
        int[] array = {-2, -1, 2, 1};

        int i = solution4.maxSubArrayLen(array, 1);

        System.out.println(i);
    }


    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int res = 0;
        for(int i=0; i<nums.length; i++){
            //建立累计和
            sum += nums[i];
            if(sum == k){
                //如果累计和正好等于k,那么res等于此时的长度即（i+1),注意此时的res不一定是最大值
                res = i+1;
            }else if(map.containsKey(sum-k)){
                //此时出现sum(0,i) = sum(0, j-1) + sum(j,i),其中sum(j,i) = k
                //sum(0,i)的长度为i+1，sum(0,j-1)的长度是j(由于我们的map保存的是index所以长度需要加1)
                //举个例子
                //[-2, -1, 2, 1]， k=1
                //sum[-2,-3,-1,0]
                //i=2的时候-1-（1） =-2, map.get(sum-k) = 0,对应的index0，它的长度为 1.
                //0j,i)的长度为i+1-（map.get(sum-k)+1)即i-map.get(sum-k)
                res = Math.max(res, i+1-(map.get(sum-k)+1));
            }
            //如果map中不包括sum,把sum放入map中
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return res;
    }
}
