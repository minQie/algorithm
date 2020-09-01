package priv.wmc.test;

import org.junit.Test;

/**
 * @author Wang Mincong
 * @date 2020-08-01 10:25:33
 */
public class Q746MinCostClimbingStairsTest2 {

    @Test
    public void test() {
        int step = minCostClimbingStairs(new int[]{10, 15, 20});
        System.out.println(step);

        int step2 = minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
        System.out.println(step2);
    }

    public int minCostClimbingStairs(int[] cost) {
        // 用递归的方式去解：结论是提交超时
        return dg(cost, cost.length + 1);
    }

    public int dg(int[] cost, int index) {
        if (index == 0) {
            return 0;
        }
        if (index <= 2) {
            return getCost(cost, index);
        }
        return Math.min(dg(cost, index - 2), dg(cost, index - 1)) + getCost(cost, index);
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
