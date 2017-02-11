import java.util.*;

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

    public boolean isPalindrome(int x) { // No .9
        String temp = Integer.toString(x);
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) != temp.charAt(temp.length() - i - 1))
                return false;
        }
        return true;

        /* it another approach which is really nice
   compare half of the digits in x, so don't need to deal with overflow.

   public boolean isPalindrome(int x) {
    if (x<0 || (x!=0 && x%10==0)) return false;
    int rev = 0;
    while (x>rev){
    	rev = rev*10 + x%10;
    	x = x/10;
    }
    return (x==rev || x==rev/10);
    }*/

    }

    public int atoi(String s) {
        return 0;
    }

    public String intToRoman(int num) { // No. 12

         /* beautiful answer :
        public static String intToRoman(int num) {
    String M[] = {"", "M", "MM", "MMM"};
    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
}
        */
        String result = "";
        while (num > 0) {
            if (num / 1000 > 0) {
                int x = num / 1000;
                for (int i = 0; i < x; i++) {
                    result = "M" + result;
                }
                num = num - x * 1000;
            } else if (num / 500 > 0) {
                if (num >= 900) {
                    result = result + "CM";
                    num = num - 900;
                    result = result + intToRoman(num);
                    num = 0;
                } else {
                    result = result + "D";
                    num = num - 500;
                    result = result + intToRoman(num);
                    num = 0;
                }
            } else if (num / 100 > 0) {
                if (num >= 400) {
                    result = result + "CD";
                    num = num - 400;
                    result = result + intToRoman(num);
                    num = 0;
                } else {
                    int x = num / 100;
                    for (int i = 0; i < x; i++) {
                        result = result + "C";
                    }
                    num = num - x * 100;
                    result = result + intToRoman(num);
                    num = 0;
                }
            } else if (num / 50 > 0) {
                if (num >= 90) {
                    result = result + "XC";
                    num = num - 90;
                    result = result + intToRoman(num);
                    num = 0;
                } else {
                    result = result + "L";
                    num = num - 50;
                    result = result + intToRoman(num);
                    num = 0;
                }
            } else if (num / 10 > 0) {
                if (num >= 40) {
                    result = result + "XL";
                    num = num - 40;
                    result = result + intToRoman(num);
                    num = 0;
                } else {
                    int x = num / 10;
                    for (int i = 0; i < x; i++) {
                        result = result + "X";
                    }
                    num = num - x * 10;
                    result = result + intToRoman(num);
                    num = 0;
                }
            } else if (num / 5 > 0) {
                if (num >= 9) {
                    result = result + "IX";
                    num = num - 9;
                    result = result + intToRoman(num);
                    num = 0;
                } else {
                    result = result + "V";
                    num = num - 5;
                    result = result + intToRoman(num);
                    num = 0;
                }
            } else if (num >= 4) {
                result = result + "IV";
                num = num - 4;
                result = result + intToRoman(num);
                num = 0;
            } else {
                for (int i = 0; i < num; i++) {
                    result = result + "I";
                }
                num = 0;
            }
        }
        return result;
    }

    public int romanToInt(String s) { // No. 13

        char[] romanCharacters = s.toCharArray();

        int number = 0;
        boolean twoChar = false;
        for (int i = 0; i < romanCharacters.length; i++) {
            twoChar = false;

            if (i < (romanCharacters.length - 1)) {

                String temp = Character.toString(romanCharacters[i]) +
                        Character.toString(romanCharacters[i + 1]);
                switch (temp) {
                    case "IV":
                        number = number + 4;
                        i++;
                        twoChar = true;
                        break;
                    case "IX":
                        number = number + 9;
                        i++;
                        twoChar = true;
                        break;
                    case "XL":
                        number = number + 40;
                        i++;
                        twoChar = true;
                        break;
                    case "XC":
                        number = number + 90;
                        i++;
                        twoChar = true;
                        break;
                    case "CD":
                        number = number + 400;
                        i++;
                        twoChar = true;
                        break;
                    case "CM":
                        number = number + 900;
                        i++;
                        twoChar = true;
                        break;
                }
            }
            if (!twoChar)
                switch (romanCharacters[i]) {
                    case 'M':
                        number = number + 1000;
                        break;
                    case 'D':
                        number = number + 500;
                        break;
                    case 'C':
                        number = number + 100;
                        break;
                    case 'L':
                        number = number + 50;
                        break;
                    case 'X':
                        number = number + 10;
                        break;
                    case 'V':
                        number = number + 5;
                        break;
                    case 'I':
                        number = number + 1;
                        break;
                    default:
                        System.out.print("Wrong Character");
                        return 0;
                }
        }
        return number;
    }

    public List<List<Integer>> threeSum(int[] nums) { // No. 15
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 2) {
            if (nums[i] > 0) break;
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) result.add(Arrays.asList(nums[i], nums[j], nums[k]));

                //Avoid Duplicates
                if (sum <= 0) while (nums[j] == nums[++j] && j < k) ;

                //Avoid Duplicates
                if (sum >= 0) while (nums[k--] == nums[k] && j < k) ;
            }
            while (nums[i] == nums[++i] && i < nums.length - 2) ;
            //Avoid Duplicates
        }
        return result;
    }

    public int threeSumClosest(int[] nums, int target) { // No .16

        if (nums.length < 3) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                sum = sum + nums[j];
            }
            return sum;
        }

        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 1;
            int last = nums.length - 1;
            while (k < last) {
                int sum = nums[i] + nums[k] + nums[last];
                if (sum == target) return sum;
                if (Math.abs(target - sum) < Math.abs(target -ans)) ans = sum;
                if (sum > target) last--;
                else k++;
            }
        }
        return ans;
    }

    public ListNode swapPairs(ListNode head) { // No. 24
        if(head == null) return null;
        else if(head.next == null) return head;

        ListNode nextPair = head;
        head = nextPair.next;

        while(nextPair != null && nextPair.next != null){
            ListNode first = nextPair;
            ListNode second = first.next;
            nextPair = second.next;
            if(nextPair == null)first.next = null;
            else if(nextPair.next == null) first.next = nextPair;
            else first.next = nextPair.next;
            second.next = first;
        }

        return head;
    }

    public boolean isValid(String s) { // No . 20
        if(s.length() == 0) return true;
        if(s.length() == 1) return false;

        if(s.charAt(0) == ')' || s.charAt(0) == '}' || s.charAt(0) == ']') return false;

        if ((s.charAt(0) == '(' && s.charAt(1) == ')') ||
                (s.charAt(0) == '[' && s.charAt(1) == ']') ||
                (s.charAt(0) == '{' && s.charAt(1) == '}'))
            if(s.length() > 2)return isValid(s.substring(2));
            else return true;

        else if (s.charAt(0) == '(' && s.charAt(s.length() -1) == ')')return isValid(s.substring(1, s.length()-1));
        else if (s.charAt(0) == '[' && s.charAt(s.length() -1) == ']')return isValid(s.substring(1, s.length()-1));
        else if (s.charAt(0) == '{' && s.charAt(s.length() -1) == '}')return isValid(s.substring(1, s.length()-1));

        return false;

    }

    public List<String> generateParenthesis(int n) {

        if (n == 1) return Arrays.asList("()");

        List<String> result = generateParenthesis(n-1);

        List<String> Final = new ArrayList<String>();
        //for(Iterator<String> L = result.iterator(); L.hasNext();){
        for(String L : result){
            String s = "(" + L + ")";
            Final.add(s);
            s = L + "()";
            if(!Final.contains(s)) Final.add(s);
            s = "()" + L;
            if(!Final.contains(s)) Final.add(s);
        }
        return Final;
    }

    public int strStr(String haystack, String needle) {
        if (haystack.length() == 0 && needle.length() == 0) return 0;
        if (haystack.length() != 0 && needle.length() == 0) return 0;
        if (haystack.length() == 0 && needle.length() != 0) return -1;
        if (haystack.length() == needle.length())
            if (haystack.equals(needle)) return 0;
            else return -1;
        for(int i = 0; i < haystack.length(); i++){
            if(i + needle.length() < haystack.length())
                if(haystack.substring(i, i + needle.length()).equals(needle)) return i;
        }
        return -1;
    }

    public void printPostOrder(BTNode root){

        /*
        For example of trees copy this to the main

        BTNode F = new BTNode('F');
        BTNode B = new BTNode('B');
        BTNode G = new BTNode('G');
        F.left = B; F.right = G;
        BTNode A = new BTNode('A');
        BTNode D = new BTNode('D');
        B.left = A; B.right = D;
        BTNode C = new BTNode('C');
        BTNode E = new BTNode('E');
        D.left = C; D.right = E;
        BTNode I = new BTNode('I');
        BTNode H = new BTNode('H');
        G.right = I;
        I.left = H;

        Set<ArrayList<Integer>> yaser= new HashSet<>();
        yaser.add(new ArrayList<>(Arrays.asList(1 ,2, 3)));
        yaser.add(new ArrayList<>(Arrays.asList(1 ,2, 4)));

        ArrayList<LinkedList<BTNode>> result =  leetCode.createListForEachLevel(F);
        System.out.println("end");
        */


        if(root == null) return;
        if(root.left != null) printPostOrder(root.left);
        if(root.right != null) printPostOrder(root.right);
        System.out.print(root.val + ", ");
    }

    public void printPreOrder(BTNode root){
        if(root == null) return;
        System.out.print(root.val + ", ");
        if(root.left != null) printPreOrder(root.left);
        if(root.right != null) printPreOrder(root.right);

    }

    public void printInOrder(BTNode root){
        if(root == null) return;
        if(root.left != null) printInOrder(root.left);
        System.out.print(root.val + ", ");
        if(root.right != null) printInOrder(root.right);
    }

    public void depthFirstSearch(BTNode root, char key){
    // traverse in Pre order
        Stack<BTNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            BTNode current = stack.pop();
            if(!current.visited){
                if(current.val == key){
                    System.out.println("Found the key " + key);
                    return;
                }
                current.visited = true;
                System.out.println("visited " + current.val + " ,");
                if(current.right != null )stack.push(current.right);
                if(current.left != null )stack.push(current.left);
            }
        }
    }

    public void breadthFirstSearch(BTNode root, char key){
        LinkedList<BTNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            BTNode current = queue.poll();
            if(!current.visited){
                if(current.val == key){
                    System.out.println("Found the key " + key);
                    return;
                }
                current.visited = true;
                System.out.println("visited "+ current.val + " ,");
                if(current.right != null && !current.right.visited )queue.add(current.right);
                if(current.left != null && !current.left.visited) queue.add(current.left);
            }
        }
    }

    public BTNode createMinimalBST(char[] array){
        return createMinimalBST(array, 0, array.length - 1);
    }

    public BTNode createMinimalBST(char[] array, int start, int end){
        if(end > start) return null;
        int middle = (start + end) / 2;
        BTNode root = new BTNode(array[middle]);
        root.left = createMinimalBST(array, start, middle -1);
        root.right = createMinimalBST(array, middle + 1, end);
        return root;
    }

    public ArrayList<LinkedList<BTNode>> createListForEachLevel(BTNode root){
        ArrayList<LinkedList<BTNode>> result = new ArrayList<>();
        LinkedList<BTNode> current = new LinkedList<>();
        if(root == null) return result;
        current.add(root);

        while(current.size() > 0){
            result.add(current);
            LinkedList<BTNode> parents = current;
            current = new LinkedList<>();
            for(BTNode parent:parents){
                if(parent.left != null)current.add(parent.left);
                if(parent.right != null) current.add(parent.right);
            }
        }
        return result;
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> rep = new HashMap();
        for(int  i = 0; i < nums.length; i++){
            if(rep.containsKey(nums[i])){
                rep.put(nums[i], rep.get(nums[i])+1);
            }else
                rep.put(nums[i], 1);
        }

        PriorityQueue<myNode> ordered = new PriorityQueue<>();
        for(Map.Entry<Integer, Integer> entry:rep.entrySet()){
            myNode tmp = new myNode(entry.getKey(), entry.getValue());
            ordered.add(tmp);
        }

        Integer[] res = new Integer[k];
        int  i =0;
        while(i < k){
            res[i] = ordered.poll().index;
            i++;
        }

        return Arrays.asList(res);
    }

    class myNode implements Comparable<myNode>{
        int val;
        int index;
        public myNode(int theIndex, int theValue){
            val = theValue;
            index = theIndex;
        }

        @Override
        public int compareTo(myNode that){
            return that.val - val;
        }
    }

    public ListNode insertionSortList(ListNode head) {

        if( head == null ){
            return head;
        }

        ListNode helper = new ListNode(0); //new starter of the sorted list
        ListNode cur = head; //the node will be inserted
        ListNode pre = helper; //insert node between pre and pre.next
        ListNode next = null; //the next node will be inserted
        //not the end of input list
        while( cur != null ){
            next = cur.next;
            //find the right place to insert
            while( pre.next != null && pre.next.val < cur.val ){
                pre = pre.next;
            }
            //insert between pre and pre.next
            cur.next = pre.next;
            pre.next = cur;
            pre = helper;
            cur = next;
        }

        return helper.next;
    }

    public int pathSum(TreeNode root, int sum) {

        ArrayList<Integer> allSums = new ArrayList();
        //allSums.add(0);
        allSums.add(root.val);

        int count;
        if(root.val == sum) count = 1;
        else count = 0;


        return count + helper(root.left, allSums, sum)+ helper(root.right, allSums, sum);

    }

    public int helper(TreeNode node, ArrayList<Integer> allNodes, int sum){

        if(node == null) return 0;
        //allNodes.add(node.val);
        int count = calAllPath(allNodes, node.val, sum);
        allNodes.add(node.val);
        count += helper(node.left, allNodes, sum);
        count += helper(node.right, allNodes, sum);
        allNodes.remove(allNodes.size()-1);

        return count;
    }

    public int calAllPath(ArrayList<Integer> all, int self, int sum){
        int count = 0;
        if(self == sum)count++;
        for(int i = 0; i <= all.size()-1; i++){
            int sumo = self;
            for(int j = i; j <= all.size()-1; j++){
                sumo += all.get(j);
            }
            if(sumo == sum)count++;
        }

        return count;
    }

    public String serialize(TreeNode root) {

        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        StringBuilder s = new StringBuilder("");

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node == null)s.append("null,");
            else {
                s.append(String.valueOf(node.val+ ","));
                stack.push(node.right);
                stack.push(node.left);
            }
        }

        return s.toString();
    }

    public TreeNode search(TreeNode root, int key){
        if(root == null) return null;
        if(root.val == key) return root;
        if(key <= root.val) return search(root.left, key);
        else return search(root.right, key);
    }

    public List<Integer> ladderLength(String beginWord, String endWord, Set<String> wordList) { // No. 127
        if(beginWord.length()!= endWord.length()) return new ArrayList();
        ArrayList<Integer> list = new ArrayList();
        int count = 0;
        Set<String> visited = new HashSet();
        visited.add(beginWord);
        changeWord(beginWord, endWord, count, list, wordList, visited);

        return list;
    }

    public void changeWord(String beginWord, String endWord, int count, ArrayList<Integer> list,
                           Set<String> wordList, Set<String> visited){
        for(int i = 0; i < beginWord.length();i++){
            char[] word = beginWord.toCharArray();
            for(char c ='a'; c<='z';c++){
                char old = word[i];
                word[i] = c;
                String tmp = new String(word);
                if(tmp.equals(endWord))
                    list.add(count+1);
                else if(wordList.contains(tmp) && !visited.contains(tmp)) {
                    visited.add(tmp);
                    changeWord(tmp, endWord, count + 1, list, wordList, visited);
                }
            }
        }
    }

    public List<List<String>> findAllLadders(String beginWord, String endWord, Set<String> wordList) {
        //No. 126.. final aal word paths
        List<List<String>> allPath = new ArrayList();
        List<String> list = new ArrayList();
        list.add(beginWord);
        wordList.remove(beginWord);
        findWord(beginWord, endWord, allPath, list, wordList);

        return allPath;
    }

    public void findWord(String begin, String end, List<List<String>> allPath, List<String> list, Set<String> wordList) {

        for (int i = 0; i < begin.length(); i++) {
            char[] word = begin.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                word[i] = c;
                String newWord = new String(word);
                if (newWord.equals(end)) {
                    list.add(newWord);
                    allPath.add(new ArrayList(list));
                    list.remove(list.size() - 1);
                    break;
                } else if (wordList.contains(newWord)) {
                    list.add(newWord);
                    wordList.remove(newWord);
                    findWord(newWord, end, allPath, list, wordList);
                    list.remove(list.size() - 1);

                }
            }
        }
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> dic = new HashSet();
        List<String> res = new ArrayList();
        for(String s:words){
            dic.add(s);
        }
        for(String s:words){
            if (find(s, dic))
                res.add(s);
        }

        return res;

    }

    public boolean find(String word, Set<String> dic){

        for(int i =0; i < word.length()-1;i++) {
            if(dic.contains(word.substring(0,i+1)) && dic.contains(word.substring(i+1)))
                return true;
            else if(dic.contains(word.substring(0,i+1)))
                if(find(word.substring(i+1), dic))
                    return true;
            /*else if(dic.contains(word.substring(i+1)))*/

        }
        return false;
    }

    public boolean wordBreak(String s, Set<String> wordDict) {
        if(s.length() == 0) return false;
        HashSet<String> not = new HashSet();

        findword(s, wordDict, not);

        if(not.size()==0)
            return true;
        else
            return false;
    }

    public void findword(String s, Set<String> wordDict, Set<String> not){
        if(wordDict.contains(s))return;
        if(not.contains(s))return;
        for(int i = 0; i < s.length();i++){
            if(wordDict.contains(s.substring(0,i+1)) && wordDict.contains(s.substring(i+1)))
                return;
            else if(wordDict.contains(s.substring(0,i+1))){
                findword(s.substring(i+1), wordDict, not);
                if(not.size()==0)
                    return;
            }
        }

        not.add(s);
        return;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<List<Integer>> all = new ArrayList();

        for(int i = 0 ; i < nums.length; i++){
            List<List<Integer>> tmp = new ArrayList(all);
            for(int j = 0; j < all.size(); j++){
                List<Integer> tmpList= tmp.get(j);
                if(isDivisibale(nums[i], tmpList)){
                    //all.remove(j);
                    tmpList.add(nums[i]);
                    tmp.add(tmpList);
                }
            }
            tmp.add(new ArrayList(Arrays.asList(nums[i])));
            all = tmp;
        }

        System.out.println(all.size());
        return maxList(all);
    }

    public List<Integer> maxList(List<List<Integer>> list){
        int len = Integer.MIN_VALUE;
        int maxIndex = 0;
        for(int  i = 0 ; i < list.size(); i++){
            if(list.get(i).size() > len){
                len = list.get(i).size();
                maxIndex = i;
            }
        }

        return list.get(maxIndex);
    }

    public boolean isDivisibale(int num, List<Integer> list){
        for(int i = 0;i < list.size(); i++){
            if(!(num % list.get(i) == 0) && !(list.get(i) % num == 0))
                return false;
        }

        return true;
    }

    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode fast = head.next;
        ListNode slow = head;

        while(fast.next.next != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode reverseHead = reverseList(slow.next);
        ListNode fakeHead = head;

        while(fakeHead.next!=null && reverseHead.next!=null){
            ListNode tmp = fakeHead.next;
            fakeHead.next = reverseHead;
            ListNode tmpRev = reverseHead.next;
            reverseHead.next = tmp;

            fakeHead = tmp;
            reverseHead = tmpRev;
        }

    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        HashMap<Integer, Integer> leastHeights = new HashMap();

        ArrayList[] graph = new ArrayList[n];

        for(int i = 0 ; i < n ; i++){
            graph[i] = new ArrayList();
        }

        for(int i = 0; i < edges.length;i++){
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }

        int minAll = Integer.MAX_VALUE;
        int[][] allMax = new int[n][n];
        for(int i = 0; i <n ; i++){
            Arrays.fill(allMax[i], -1);
        }

        for(int i =0; i < n; i++){

            int min = Integer.MIN_VALUE;
            for(int j = 0; j < graph[i].size();j++){
                int h = 0;
                if(allMax[i][(int)graph[i].get(j)] == -1)
                    h = 1 + getMaxHeight(graph, i, (int)graph[i].get(j), allMax);
                else
                    h = allMax[i][(int)graph[i].get(j)];

                min = Math.max(min, h);
            }

            minAll = Math.min(minAll, min);
            leastHeights.put(i, min);
        }

        List<Integer> res = new ArrayList();
        for(Map.Entry<Integer, Integer> map: leastHeights.entrySet()){
            if(map.getValue() == minAll){
                res.add(map.getKey());
            }
        }

        return res;

    }

    public int getMaxHeight(ArrayList[] graph, int parent, int child, int[][] allMax){

        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < graph[child].size(); i++){
            if((int)graph[child].get(i)!=parent){
                int h = 0;
                if(allMax[child][(int)graph[child].get(i)] == -1){
                    h = 1 + getMaxHeight(graph, child, (int)graph[child].get(i), allMax);
                    allMax[child][(int)graph[child].get(i)] = h;
                }
                else
                    h = allMax[child][(int)graph[child].get(i)];

                max = Math.max(max, h);
            }
        }
        return (max == Integer.MIN_VALUE) ? 0 : max;
    }

}

