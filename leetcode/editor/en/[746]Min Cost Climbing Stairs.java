//
//On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 i
//ndexed).
// 
//Once you pay the cost, you can either climb one or two steps. You need to find
// minimum cost to reach the top of the floor, and you can either start from the s
//tep with index 0, or the step with index 1.
// 
//
// Example 1: 
// 
//Input: cost = [10, 15, 20]
//Output: 15
//Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
// 
// 
//
// Example 2: 
// 
//Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
//Output: 6
//Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[
//3].
// 
// 
//
// Note: 
// 
// cost will have a length in the range [2, 1000]. 
// Every cost[i] will be an integer in the range [0, 999]. 
// 
// Related Topics Array Dynamic Programming 
// 👍 2087 👎 468


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int originLength = cost.length;
        int length = originLength + 2;

        // 以指定下标为基点进行爬楼梯的代价
        int[] price = new int[length];
        price[0] = getCost(cost, 0);
        price[1] = getCost(cost, 1);

        int shouldBe;
        for (int i = 2; i < length; i++) {
            shouldBe = price[i-1] < price[i-2] ? i - 1 : i - 2;
            price[i] = price[shouldBe] + getCost(cost, i);
        }
        return price[length - 1];
    }

    private int getCost(int[] array, int i) {
        if (i == 0) {
            return 0;
        }
        if (i > array.length) {
            return 0;
        }
        return array[i -1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
