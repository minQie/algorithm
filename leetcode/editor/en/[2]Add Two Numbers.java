//You are given two non-empty linked lists representing two non-negative integer
//s. The digits are stored in reverse order and each of their nodes contain a sing
//le digit. Add the two numbers and return it as a linked list. 
//
// You may assume the two numbers do not contain any leading zero, except the nu
//mber 0 itself. 
//
// Example: 
//
// 
//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8
//Explanation: 342 + 465 = 807.
// 
// Related Topics Linked List Math 
// 👍 8485 👎 2154


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
}
//leetcode submit region end(Prohibit modification and deletion)
