//Given an array of integers, return indices of the two numbers such that they a
//dd up to a specific target. 
//
// You may assume that each input would have exactly one solution, and you may n
//ot use the same element twice. 
//
// Example: 
//
// 
//Given nums = [2, 7, 11, 15], target = 9,
//
//Because nums[0] + nums[1] = 2 + 7 = 9,
//return [0, 1].
// 
// Related Topics Array Hash Table 
// 👍 15754 👎 576


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 输入：[2, 7, 11, 15] 9
        // 输出：[0, 1]
        
        // 思路：最基本的双层循环思路直接略过，毫无写头
        int length = nums.length;

        Map<Integer, Integer> map = new HashMap<>(Math.max((int) (length/.75f) + 1, 16));
        int temp;
        for (int i = 0; i <= length; i++) {
            temp = nums[i];
            if (map.get(target - temp) != null) {
                return new int[]{map.get(target - temp), i};
            }
            map.put(temp, i);
        }

        throw new IllegalArgumentException("别搞我，没有满足符合条件的参数");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
