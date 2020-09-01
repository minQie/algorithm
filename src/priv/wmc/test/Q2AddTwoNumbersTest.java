package priv.wmc.test;

import org.junit.Test;

/**
 * @author Wang Mincong
 * @date 2020-07-20 21:47:32
 */
public class Q2AddTwoNumbersTest {

    @Test
    public void test() {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
//        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
//        ListNode l1 = new ListNode(5);
//        ListNode l2 = new ListNode(5);
        ListNode l3 = addTwoNumbers(l1, l2);
        while(l3 != null) {
            System.out.println(l3.val + ", ");
            l3 = l3.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Input:（2->4->3）+（5->6->4）
        // Explanation：342 + 465 = 807
        // Output：7->0->8

        // 1->2 5->5->5
        // 21 + 555 = 576
        // 6->7->5

        // 结果
        ListNode l3 = null;
        ListNode work = null;
        // 进位
        int up = 0;
        int sum;
        int l1val;
        int l2val;
        while(l1 != null || l2 != null) {
            l1val = l1 == null ? 0 : l1.val;
            l2val = l2 == null ? 0 : l2.val;

            sum = l1val + l2val + up;
            up = sum > 9 ? 1 : 0;

            ListNode temp = new ListNode(sum%10);
            if (work == null) {
                l3 = temp;
                work = temp;
            } else {
                work.next = temp;
                work = temp;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (up != 0) {
            work.next = new ListNode(1);
        }
        return l3;
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

}
