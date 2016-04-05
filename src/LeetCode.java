import java.util.List;

public class LeetCode {

    public int addDigits(int num) { // No. 258

        char[] result = String.valueOf(num).toCharArray();

        if (result.length == 0) return 0;

        while (result.length > 1) {

            int sum = 0;
            for (int i = 0; i < result.length; i++) {
                sum = sum + Integer.parseInt(Character.toString(result[i]));
            }

            result = String.valueOf(sum).toCharArray();

        }

        return Integer.parseInt(Character.toString(result[0]));
    }

    public int addDigitsO1(int num) {
        // No. 258 with O(1)
        // Explanation: 123 = 1 * 100 + 2 * 10 + 3 -> then 123 % 9 = (1+2+3) % 9
        // Also 10^k % 9 = 1 and (a * 10^k) % 9 = a % 9
        if (num == 0) return 0;
        else if (num % 9 == 0) return 9;
        else return num % 9;
    }

    public List<List<Integer>> threeSum(int[] nums) {// No. 15

    }

    public int myAtoi(String str) {// No. 8
        return 0;
    }

    public ListNode reverseList(ListNode head) { // No. 206
        if(head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode pro;
        while(head != null){
            pro = head.next;
            head.next = pre;
            pre = head;
            head = pro;
        }
        return pre;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {//No. 160

    }
}
