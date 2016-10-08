import java.util.ArrayList;
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
        return new ArrayList<>();
    }

    public int myAtoi(String str) {// No. 8
        return 0;
    }

    public ListNode reverseList(ListNode head) { // No. 206
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode pro;
        while (head != null) {
            pro = head.next;
            head.next = pre;
            pre = head;
            head = pro;
        }
        return pre;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {//No. 160
        return new ListNode(5);
    }

    public void moveZeroes(int[] nums) {// No. 258, also we can do with two for loop
        if (nums.length == 0) return;
        int count = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            } else count++;
        }

        while (count > 0) {
            nums[nums.length - count] = 0;
            count--;
        }
    }

    public int reverseInteger(int x) {// No. 7

        int reverse = 0;
        int tempReverse = 0;
        int remainder;
        int sign = 1;
        if (x < 0) sign = -1;
        x = Math.abs(x);
        while (x > 0) {
            remainder = x % 10;
            reverse = remainder + reverse * 10;

            if ((reverse - remainder) / 10 != tempReverse) return 0;

            tempReverse = reverse;
            x = x / 10;
        }
        return reverse * sign;
    }

    public int[] twoSum(int[] nums, int target) { //No. 1
        int Indexi = -1, Indexj = -1;
        for (int i = 0; i < nums.length; i++) {
            int remainder = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == remainder) {
                    Indexi = i;
                    Indexj = j;
                    break;
                }
            }
        }
        int[] result = {Indexi, Indexj};
        return result;
    }

    public ListNode addTwoNumbersWithIntegerLimitationProblem(ListNode l1, ListNode l2) { // No.2... it has the problem cannot add two big integer.

        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        else if (l2 == null) return l1;

        int sum = getNumber(l1) + getNumber(l2);

        return getList(sum);
    }

    public ListNode getList(int number) {

        ListNode runner = new ListNode(number % 10);
        ListNode head = runner;
        number = number / 10;

        while (number > 0) {
            ListNode temp = new ListNode(number % 10);
            number = number / 10;
            runner.next = temp;
            runner = temp;
        }

        return head;
    }

    public int getNumber(ListNode list) {
        int number = 0;
        int multiplier = 1;
        while (list != null) {
            number = (list.val * multiplier) + number;
            multiplier = multiplier * 10;
            list = list.next;
        }

        return number;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) { // No.2
        return null;
    }

    public String convert(String s, int numRows) { // No. 6
        if (numRows == 1) return s;
        String result[] = new String[numRows];
        for (int i = 0; i < numRows; i++) {
            result[i] = "";
        }
        int rows = 0;
        boolean reverse = false;
        for (int i = 0; i < s.length(); i++) {
            if (!reverse) {
                result[rows] = result[rows] + s.charAt(i);
                rows++;
            } else {
                result[rows] = result[rows] + s.charAt(i);
                rows--;
            }

            if (rows == numRows) {
                reverse = !reverse;
                rows = rows - 2;
                if (rows < 0) rows = 0;
            } else if (rows < 0) {
                reverse = !reverse;
                rows = rows + 2;
            }
        }
        String sentence = "";
        for (int i = 0; i < numRows; i++) {
            sentence = sentence + result[i];
        }
        return sentence;
    }

}

