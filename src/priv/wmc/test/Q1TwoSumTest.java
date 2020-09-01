package priv.wmc.test;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * @author Wang Mincong
 * @date 2020-07-19 21:44:22
 */
public class Q1TwoSumTest {

    @Test
    public void test() {
        int[] ints = coreFunction(new int[]{2, 7, 11, 15}, 9);
        System.out.println(ints);
    }

    public int[] coreFunction(int[] nums, int target) {
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
