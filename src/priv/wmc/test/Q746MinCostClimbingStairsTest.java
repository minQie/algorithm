package priv.wmc.test;

import org.junit.Test;

/**
 * @author Wang Mincong
 * @date 2020-08-01 10:25:33
 */
public class Q746MinCostClimbingStairsTest {

    @Test
    public void test() {
        int step = minCostClimbingStairs(new int[]{10, 15, 20});
        System.out.println(step);

        int step2 = minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
        System.out.println(step2);
    }

    public int minCostClimbingStairs(int[] cost) {
        // 输入：[10, 15, 20]
        // 输出：15


        // 输入：[1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
        // 输出：6

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
