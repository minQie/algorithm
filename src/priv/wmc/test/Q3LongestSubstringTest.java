package priv.wmc.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * 子串中，不包含重复字母的最大长度
 *
 * @author Wang Mincong
 * @date 2020-07-21 10:36:13
 */
public class Q3LongestSubstringTest {

    @Test
    public void test() {
        // 输入：abcabcbb
        // 输出：3 - abc

        // 输入：bbbbb
        // 输出：1 - b

        // 输入：pwwkew
        // 输出：3 - wke
        int bbb = third1("abcabcdbb");
        System.out.println(bbb);
    }

    /**
     * 算法核心思想：
     * 所有字符串中的字符作为开头都能得到一个最大不重复串长度，取最大
     *
     * 例如从下标0开始向后遍历获取最大不重复子串（这里有优化，不会说使用List，遍历看下记录最大不重复子串的数组是否有指定字符，而采用的HashSet）
     * 然后查找至结束（这里也还有优化，并不需要查找至结尾，因为如果已经得到的最大子串长度大于剩下字符长度，就没必要继续找）
     */
    public int first(String s) {
        int maxResult = 0;

        int length = s.length();
        HashSet<Character> hashSet = new HashSet<>(16);
        for (int j = 0; j < length - maxResult; j++) {
            for (int i = j; i < length; i++) {
                boolean addSuccess = hashSet.add(s.charAt(i));

                if (!addSuccess || i == length -1) {
                    maxResult = Math.max(maxResult, hashSet.size());
                    hashSet.clear();
                }
            }
        }

        return Math.max(maxResult, hashSet.size());
    }

    /**
     * 参考答案，得出的优化点
     *
     * 思考：当以某个下标的字符0作为开始，找到出现重复字符的时候n，注意此时[0, n-1]个元素是不含重复的，是能够利用上的，不用重头再来（KMP白学...）
     * 答案：当出现重复元素时，这时开始指针应该向后走，结束指针不应该回到开始指针后面，而是保持不变，由开始指针向后走
     *
     * 拓展：上面的描述等效于，存在一个起始指针和一个终点指针，只是大阶段是终点指针向后走，优化的实质是大阶段应该是起始指针向后走（这就是滑动窗口）
     */
    public int second(String s) {
        int length = s.length();

        Set<Character> set = new HashSet<>(Math.max((int) (length/.75f) + 1, 16));

        int max = 0;
        int start = 0;
        int end = 0;

        while (max < length - start && end < length) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end++));
                max = Math.max(max, end - start);
            } else {
                set.remove(s.charAt(start++));
            }
        }

        return max;
    }

    /**
     * 参考答案，得出的优化点
     *
     * 提出：注意一个细节，”初始指针向后走“这个细节，我们应该思考，向后走这个遍历过程能否优化
     * 发现：初始指针向后走，每次都走到了导致重复的字符后面一个字符的位置
     * 即：要是我们能在结束指针向后边走的过程，能够把每一个类型的字符最后所处的位置记录下来，就能够在发生重复的时候直接跳跃到正确的位置
     *
     * 怎么记录下这个位置呢：首先肯定要使用hash表，然后还要存储格外的位置 → HashMap
     *
     * 写着写着你会发现要跳过多个位置，那不就得需要一个循环将Map中多余的字段排除掉么（好麻烦）
     *
     * 换个角度思考，我们的目标是求出最大不重复子串长度，那么如果保证这样的一个规则，让start和end之间的指针始终保持不含重复字符，那么在这个动态的过程中，
     * start指针和end指针的距离不就是这个算法的解么（哟西）
     *
     * 核心思想：找最大串，找啊找。嗯，这个字符我没有，加进来，这个已经有，赶快把包含这个字符扔掉（在这个过程中记录最大的长度即可）
     */
    public int third1(String s) {
        int length = s.length();

        Map<Character, Integer> map = new HashMap<>(Math.max((int) (52/.75f) + 1, 16));

        int max = 0;
        int start = 0;
        int end = 0;

        // （start > end）>0 + start < length → end < length
        while (max < length - start) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(start, map.get(s.charAt(end)) + 1);
            }

            map.put(s.charAt(end), end++);
            max = Math.max(max, end - start);
        }

        return max;
    }

    /**
     * 参考下边的实现，其实hash表并不是必须的
     */
    public int third2(String s) {
        int length = s.length();
        int max = 0;
        // current index of character
        int[] index = new int[128];
        // try to extend the range [start, end]
        for (int end = 0, start = 0; end < length; end++) {
            start = Math.max(index[s.charAt(end)], start);
            max = Math.max(max, end - start + 1);
            index[s.charAt(end)] = end + 1;
        }
        return max;
    }

}
