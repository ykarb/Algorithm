import org.w3c.dom.ls.LSInput;
import sun.awt.image.ImageWatched;
import sun.java2d.loops.GraphicsPrimitive;
import sun.reflect.generics.tree.Tree;

import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.*;

public class CrackingInterview {

    public boolean isUnique(String word) {// 1.1//bit vector

        char[] words = word.toCharArray();
        int counter = 0;

        for (char theChar : words) {
            int temp = theChar - 'a';
            if (((counter >> temp) & 1) == 1)
                return false;
            else
                counter = counter | (1 << temp);
        }
        return true;
        /*char[] words = word.toCharArray();
        Arrays.sort(words);

        for(int i = 0; i < words.length-1; i++){
            if(words[i] == words[i+1])
                return false;
        }
        return true;*/
    }

    public boolean checkPermutation(String first, String second) {
        if (first.length() != second.length())
            return false;

        int[] counter = new int[26];
        char[] firstArr = first.toCharArray();
        char[] secArr = second.toCharArray();

        for (char theChar : firstArr)
            counter[theChar - 'a']++;

        for (char theChar : secArr) {
            counter[theChar - 'a']--;
            if (counter[theChar - 'a'] < 0)
                return false;
        }

        return true;

        /*if(first.length() != second.length())
            return false;
        char[] firstArr = first.toCharArray();
        char[] secArr = second.toCharArray();
        Arrays.sort(firstArr);
        Arrays.sort(secArr);

        return firstArr.toString().compareTo(secArr.toString()) == 0;*/
    }

    public String urlify(String word, int len) {
        char[] arr = word.toCharArray();
        int i = word.length() - 1;
        while (i >= 0 && len > 0) {
            if (arr[len - 1] != ' ') {
                arr[i] = arr[len - 1];
                len--;
                i--;
            } else {
                arr[i] = '0';
                arr[i - 1] = '2';
                arr[i - 2] = '%';
                i -= 3;
                len--;
            }
        }

        return String.valueOf(arr);
    }

    public boolean palindPerm(String word) {// 1.4 //assume all lower case
        word = word.toLowerCase();
        int counter = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c != ' ') {
                int temp = c - 'a';
                counter = (1 << temp) ^ counter;
                System.out.println(Integer.toBinaryString(counter));
                System.out.println(temp);
            }
        }

        return ((counter - 1) & counter) == 0;
    }

    public boolean oneAway(String first, String sec) {
        if (Math.abs(first.length() - sec.length()) > 1)
            return false;

        int firstL = first.length();
        int secL = sec.length();
        int i = 0, j = 0;
        boolean firstTime = false;
        while (i < first.length() - 1 && j < sec.length() - 1) {
            if (first.charAt(i) != sec.charAt(j)) {
                if (!firstTime)
                    return false;
                else {
                    if (firstL == secL) {
                        i++;
                        j++;
                    } else if (firstL > secL)
                        i++;
                    else j++;
                    firstTime = true;
                }
            } else {
                i++;
                j++;
            }
        }

        return true;
    }

    public String stringCompression(String str) {
        int i = 0;
        StringBuilder s = new StringBuilder();
        while (i < str.length()) {
            char c = str.charAt(i);
            int count = 0;
            while (i < str.length() && c == str.charAt(i)) {
                count++;
                i++;
            }
            s.append(c);
            if (count > 2)
                s.append(String.valueOf(count));
            else if (count == 2)
                s.append(c);
        }

        if (s.length() > str.length())
            return str;
        else return s.toString();
    }

    public int[][] rotateMatrix90(int[][] image) {
        int layerSize = (image.length / 2);
        //save top layer

        for (int layer = 0; layer < layerSize; layer++) {
            int end = image.length - layer - 1;
            int[] temp = new int[end - layer];

            for (int i = 0; i < temp.length; i++) {//top to temp
                temp[i] = image[layer][layer + i];
            }

            for (int leftIndex = end; leftIndex > layer; leftIndex--) {// left to top
                image[layer][end - leftIndex] = image[leftIndex][layer];
            }

            for (int bottomIndex = end; bottomIndex > layer; bottomIndex--) { //bottom to left
                image[bottomIndex][layer] = image[end][bottomIndex];
            }

            for (int rightIndex = layer; rightIndex < end; rightIndex++) {// right to bottom
                image[end][end - rightIndex] = image[rightIndex][end];
            }

            for (int topIndex = 0; topIndex < temp.length; topIndex++) {// top to right
                image[topIndex + layer][end] = temp[topIndex];
            }
        }

        return image;
    }

    public void printMatrix(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] zeroMatrix(int[][] matrix) { // 1.8
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            if (rowSet.contains(i))
                break;
            for (int j = 0; j < matrix[0].length; j++) {
                if (colSet.contains(j))
                    break;
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }

        for (Integer i : rowSet)
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }

        for (Integer j : colSet)
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][j] = 0;
            }

        return matrix;
    }

    public boolean isRotation(String s1, String s2) { // 1.9
        return (s1 + s1).contains(s2);
    }

    public void removeDups(ListNode node) { // 2.1

    }

    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        ListNode current = dummy;

        while (current.next != null) {
            while (current.next != null && current.val <= current.next.val)
                current = current.next;

            ListNode runner = dummy;
            while (runner.next != null && runner.next.val < current.next.val)
                runner = runner.next;

            ListNode temp = current.next;
            current.next = current.next.next;
            temp.next = runner.next;
            runner.next = temp;
        }

        return dummy.next;
    }

    public ListNode selectionSortList(ListNode head) {
        ListNode headDummy = new ListNode(0);
        headDummy.next = head;
        ListNode current = head;
        ListNode minNodeFather = current;
        ListNode sortedHead = new ListNode(0);
        ListNode d2 = sortedHead;

        while (minNodeFather.next != null) {

            while (current.next != null) {
                if (current.next.val < minNodeFather.next.val) {
                    minNodeFather = current;
                }
                current = current.next;
            }

            sortedHead.next = minNodeFather.next;
            sortedHead = sortedHead.next;
            minNodeFather.next = minNodeFather.next.next;

            minNodeFather = current = headDummy;
        }

        sortedHead.next = minNodeFather;
        return d2.next;
    }

    public ListNode KthtoLast(ListNode head, int k) {
        ListNode fast = head;
        int i = 0;
        while (i < k && fast != null) {
            fast = fast.next;
            i++;
        }

        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (fast == null)
            return null;
        else
            return slow;
    }

    public ListNode partitionList(ListNode head, int p) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode d2 = new ListNode(0);
        d2.next = head;
        ListNode result = d2;

        ListNode firstBig = dummy;

        while (firstBig.next != null) {
            if (firstBig.next.val >= p)
                break;
            else {
                d2.next = firstBig.next;
                d2 = d2.next;
                firstBig = firstBig.next;
            }
        }

        if (firstBig.next == null)
            return head == null ? null : head;

        ListNode startBig = firstBig.next;
        ListNode runner = firstBig;

        while (runner.next != null) {
            if (runner.next.val < p) {
                d2.next = runner.next;
                d2 = d2.next;
                runner.next = runner.next.next;
            } else
                runner = runner.next;
        }

        d2.next = startBig;

        return result.next;
    }

    public ListNode sumReverseLists(ListNode l1, ListNode l2) { // reverse order of numbers
        ListNode dummy = new ListNode(0);
        ListNode dummyHead = dummy;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int res = 0;
            if (l1 != null) {
                res += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                res += l2.val;
                l2 = l2.next;
            }
            if (res + carry != 0) {
                ListNode temp = new ListNode((res + carry) % 10);
                carry = (res + carry) / 10;
                dummy.next = temp;
                dummy = dummy.next;
            }
        }

        if (carry == 0)
            return dummyHead.next;
        else {
            ListNode last = new ListNode(1);
            dummy.next = last;
            return dummyHead.next;
        }
    }

    public ListNode sumLists(ListNode l1, ListNode l2) {// assumes same size of list
        ListNode res = sumListHelper(l1, l2);
        return res.val == 0 ? res.next : res;
    }

    public ListNode sumListHelper(ListNode l1, ListNode l2) {
        if (l1.next == null && l2.next == null) {
            int res = l1.val + l2.val;
            ListNode last = new ListNode(res % 10);
            ListNode father = new ListNode(res / 10);
            father.next = last;
            return father;
        }
        ListNode tempFather = sumListHelper(l1.next, l2.next);
        int res = l1.val + l2.val + tempFather.val;
        tempFather.val = res % 10;
        ListNode father = new ListNode(res / 10);
        father.next = tempFather;
        return father;
    }

    public boolean checkPalindrome(ListNode head) { // 2.6
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;
        boolean even;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast == null)
            even = false;
        else
            even = true;

        //reverse till half
        ListNode pre = null;
        ListNode current = head;

        while (current != slow) {
            ListNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }

        ListNode start2 = slow.next;
        ListNode start1 = slow;
        if (even)
            slow.next = pre;
        else
            start1 = pre;

        while (start1 != null && start2 != null) {
            if (start1.val != start2.val)
                return false;
            else {
                start1 = start1.next;
                start2 = start2.next;
            }
        }

        return true;
    }

    public ListNode getIntersection(ListNode l1, ListNode l2) {
        ListNode startL1 = l1;
        ListNode startL2 = l2;

        boolean firstl1 = true;
        boolean firstl2 = true;

        while (l1 != null && l2 != null) {

            if (l1 == l2) {
                return l1;
            }

            l1 = l1.next;
            l2 = l2.next;

            if (firstl1 && l1 == null) {
                l1 = startL2;
                firstl1 = false;
            }

            if (firstl2 && l2 == null) {
                l2 = startL1;
                firstl2 = false;
            }
        }

        return null;
    }

    public ListNode getFirstInLoop(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp = head;
        while (temp != slow) {
            temp = temp.next;
            slow = slow.next;
        }

        return temp;
    }

    public boolean existRoute(Graph graph, GNode node1, GNode node2) {
        if (node1 == node2)
            return true;

        for (GNode node : graph.getNodes())
            node.visited = false;

        LinkedList<GNode> queue = new LinkedList<>();
        queue.addLast(node1);

        while (!queue.isEmpty()) {
            GNode theNode = queue.removeFirst();
            if (theNode == node2)
                return true;
            theNode.visited = true;
            for (GNode neighbor : theNode.neighbors) {
                if (neighbor.visited == false) {
                    if (neighbor == node2)
                        return true;
                    else
                        queue.add(neighbor);
                }
            }
        }

        return false;
    }

    public TreeNode minimalHeight(int[] sorted, int start, int end) {
        if (start > end)
            return null;
        if (start == end) {
            return new TreeNode(sorted[start]);
        }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(sorted[mid]);
        root.left = minimalHeight(sorted, start, mid - 1);
        root.right = minimalHeight(sorted, mid + 1, end);
        return root;
    }

    public ArrayList<LinkedList> listOfDepths(TreeNode root) {
        ArrayList<LinkedList> result = new ArrayList<>();

        LinkedList<TreeNode> q = new LinkedList<>();
        q.addFirst(root);
        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            result.add(new LinkedList());
            for (int i = 0; i < size; i++) {
                TreeNode node = q.removeLast();
                if (node.left != null)
                    q.addFirst(node.left);
                if (node.right != null)
                    q.addFirst(node.right);
                result.get(level).add(node);
            }
            level++;
        }

        return result;
    }

    public boolean checkBalance(TreeNode root) {
        int rightHeight = 0;
        int leftHeight = 0;
        if (root.right != null)
            rightHeight = getHeight(root.right);
        if (rightHeight == -1)
            return false;
        if (root.left != null)
            leftHeight = getHeight(root.left);
        if (leftHeight == -1)
            return false;

        return Math.abs(leftHeight - rightHeight) > 1 ? false : true;
    }

    private int getHeight(TreeNode root) {
        int rightHeight = 0;
        int leftHeight = 0;
        if (root.left == null && root.right == null)
            return 1;
        if (root.right != null)
            rightHeight = getHeight(root.right);
        if (rightHeight == -1)
            return -1;
        if (root.left != null)
            leftHeight = getHeight(root.left);
        if (leftHeight == -1)
            return -1;

        return Math.abs(leftHeight - rightHeight) > 1 ? -1 : Math.max(rightHeight, leftHeight) + 1;
    }

    public boolean validateBST(TreeNode root) {
        return validateBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validateBSTHelper(TreeNode root, int min, int max) {
        if (root.val < min || root.val > max)
            return false;
        boolean left = true;
        boolean right = true;
        if (root.left != null)
            left = validateBSTHelper(root.left, min, root.val);
        if (!left)
            return false;
        if (root.right != null)
            right = validateBSTHelper(root.right, root.val, max);

        return left && right;
    }

    public TreeNodeP successor(TreeNodeP node) {
        if (node.right != null) {
            TreeNodeP temp = node.right;
            while (temp.left != null)
                temp = temp.left;
            return temp;
        } else
            return successorHelper(node);
    }

    private TreeNodeP successorHelper(TreeNodeP root) {
        if (root.parent == null)
            return null;
        else if (root.parent.left == root)
            return root.parent;
        else
            return successorHelper(root.parent);
    }

    /*public Graph makeGraph(ArrayList<Character> list, HashMap<Character, Character> dep){
        Graph graph = new Graph();

        for(Character c:list){
            graph.addNode(new GNode(c));
        }
        ArrayList<GNode> nodes = graph.getNodes();
        for(Map.Entry entry:dep.entrySet()){
            GNode nodefirst = null;
            GNode nodesec = null;
            for(GNode gnode:nodes){
                if(gnode.val == entry.getKey())
                    nodefirst = gnode;
                if(gnode.val == entry.getValue())
                    nodesec = gnode;
            }
            nodefirst.outEdge.add(nodesec);
            nodesec.inEdge.add(nodefirst);
        }

        return graph;
    }*/

    /*public ArrayList<GNode> buildOrder(ArrayList<String> list, HashMap<String, String> dep){
        HashMap<String, ArrayList<String>> graph = new HashMap<>();
        for(String s:list){
            graph.put(s, new ArrayList<>());
        }

        for(String key: dep.keySet()){
            graph.get(key).add(dep.get(key));
            //graph.get(entry.getKey()).add((String)entry.getValue());
        }

        boolean changed = true;

        while(changed){
            changed = false;
            String project = "";
            for(String key :graph.keySet()){
                if(graph.get(key).size() == 0){
                    changed = true;
                    project = key;
                    break;
                }
            }

            for (String key:graph.keySet()){
                graph.get(key).remove(project);
            }
        }



    }*/

    public TreeNode firstCommonAncestor(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
        ResultCommon result = firstCommonAncestorHelper(root, nodeA, nodeB);

        if (result.ancestor)
            return result.res;
        else
            return null;
    }

    public ResultCommon firstCommonAncestorHelper(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
        if (root == null)
            return new ResultCommon(null, false);

        if (root == nodeA && root == nodeB)
            return new ResultCommon(root, true);

        ResultCommon X = firstCommonAncestorHelper(root.left, nodeA, nodeB);
        if (X.ancestor)
            return X;
        ResultCommon Y = firstCommonAncestorHelper(root.right, nodeA, nodeB);
        if (Y.ancestor)
            return Y;

        if (X.res != null && Y.res != null)
            return new ResultCommon(root, true);
        else if (root == nodeA || root == nodeB) {
            boolean flag = (X.res != null || Y.res != null);
            return new ResultCommon(root, flag);
        } else {
            return new ResultCommon(X.res != null ? X.res : Y.res, false);
        }
    }

    public ArrayList<ArrayList<Integer>> BSTSequence(TreeNode head) {// problem 4.9
        return new ArrayList<>();
    }

    public boolean checkSubtree(TreeNode T1, TreeNode T2) {// problem 4.10
        ArrayList<Integer> T2PreOrder = makePreOrder(T2);

        Stack<TreeNode> stack = new Stack<>();
        stack.push(T1);
        int index = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val == T2PreOrder.get(index)) {
                index++;
                if (index == T2PreOrder.size())
                    return true;
            } else
                index = 0;

            if (node.left != null)
                stack.push(node.left);
            else
                stack.push(new TreeNode(-1));

            if (node.right != null)
                stack.push(node.right);
            else
                stack.push(new TreeNode(-1));
        }
        return false;
    }

    public ArrayList<Integer> makePreOrder(TreeNode root) { // with -1 for nulls
        Stack<TreeNode> stack = new Stack<>();
        if (root == null)
            return new ArrayList<>();

        stack.push(root);
        ArrayList<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null)
                stack.push(node.left);
            else
                stack.push(new TreeNode(-1));

            if (node.right != null)
                stack.push(node.right);
            else
                stack.push(new TreeNode(-1));
        }

        return result;
    }

    public void insertBST(TreeNode bstRoot, int num) {
        if (num < bstRoot.val) {
            if (bstRoot.left == null)
                bstRoot.left = new TreeNode(num);
            else
                insertBST(bstRoot.left, num);
        } else {
            if (bstRoot.right == null)
                bstRoot.right = new TreeNode(num);
            else
                insertBST(bstRoot.right, num);
        }
    }

    public TreeNode findBST(TreeNode bstRoot, int num) {
        if (bstRoot.val == num)
            return bstRoot;
        else if (num < bstRoot.val && bstRoot.left != null)
            findBST(bstRoot.left, num);
        else if (bstRoot.right != null)
            findBST(bstRoot.right, num);

        return null;
    }

    public boolean deleteBSTRoot(TreeNode root, int num) {
        if (root.val == num) {
            if (root.left != null && hasNoChild(root.left)) {
                swapValue(root, root.left);
                root.left = null;
                return true;
            } else if (root.right != null && hasNoChild(root.right)) {
                swapValue(root, root.right);
                root.right = null;
                return true;
            } else {
                TreeNode dummy = new TreeNode(-1);
                dummy.left = root;
                return deleteBST(dummy, num);
            }
        } else
            return deleteBST(root, num);
    }

    public boolean deleteBST(TreeNode bstRoot, int num) {
        if (bstRoot.left != null) {
            if (bstRoot.left.val == num) {
                if (hasNoChild(bstRoot.left)) {
                    bstRoot.left = null;
                    return true;
                } else {
                    deleteNodeBST(bstRoot.left);
                    return true;
                }
            } else {
                return deleteBST(bstRoot.left, num);
            }
        } else if (bstRoot.right != null) {
            if (bstRoot.right.val == num) {
                if (hasNoChild(bstRoot.right)) {
                    bstRoot.right = null;
                    return true;
                } else {
                    deleteNodeBST(bstRoot.right);
                    return true;
                }
            } else {
                return deleteBST(bstRoot.right, num);
            }
        } else
            return false;
    }

    public TreeNode getRandomNode() {
        return new TreeNode(0);
    }

    private void deleteNodeBST(TreeNode node) {
        if (node.left != null) {
            if (hasNoChild(node.left)) {
                swapValue(node, node.left);
                node.left = null;
                return;
            } else {
                swapValue(node, node.left);
                deleteNodeBST(node.left);
            }
        } else if (node.right != null) {
            if (hasNoChild(node.right)) {
                swapValue(node, node.right);
                node.right = null;
                return;
            } else {
                swapValue(node, node.right);
                deleteNodeBST(node.right);
            }
        }
    }

    private boolean hasNoChild(TreeNode node) {
        if (node.left == null && node.right == null)
            return true;
        else
            return false;
    }

    private void swapValue(TreeNode first, TreeNode second) {
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public int pathsWithSum(TreeNode root, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return pathsWithSumHelper(root, map, target, 0);
    }

    private int pathsWithSumHelper(TreeNode node, HashMap<Integer, Integer> map,
                                   int target, int runningSum) {

        runningSum += node.val;
        int totalPaths = 0;
        if (runningSum == target)
            totalPaths++;
        int keyLookUp = runningSum - target;

        if (map.containsKey(keyLookUp))
            totalPaths += map.get(keyLookUp);

        if (map.containsKey(runningSum))
            map.put(runningSum, map.get(runningSum) + 1);
        else
            map.put(runningSum, 1);

        if (node.left != null)
            totalPaths += pathsWithSumHelper(node.left, map, target, runningSum);

        if (node.right != null)
            totalPaths += pathsWithSumHelper(node.right, map, target, runningSum);

        if (map.get(runningSum) - 1 == 0)
            map.remove(runningSum);
        else
            map.put(runningSum, map.get(runningSum));

        return totalPaths;
    }

    public int bitInsertion(int a, int b, int i, int j) { //5.1 insert b from bit i through j in a
        int mask = ((1 << i) - 1) | (~((1 << j) - 1));
        System.out.println(Integer.toBinaryString(mask));
        a = mask & a;
        System.out.println(Integer.toBinaryString(a));
        a = (b << i) | a;
        System.out.println(Integer.toBinaryString(a));

        return a;
    }

    public String binaryRealNumber(double a) {// a is between 0 and 1
        StringBuilder res = new StringBuilder("0.");
        int power = -1;
        while (res.length() < 35 && a > 0) {
            if (a - Math.pow(2, power) >= 0) {
                res.append('1');
                a = a - Math.pow(2, power);
                power--;
            } else {
                res.append('0');
                power--;
            }
        }

        if (a == 0)
            return res.toString();
        else
            return "ERROR";
    }

    public int flipBitToWin(int num) {
        return 0;
    }//5.3

    public int nextSmallestNumber(int num) {//5.4
        int first1To0 = 0;
        int count1 = 0;
        int count0 = 0;

        int temp = num;
        boolean found = false;
        boolean hasZeroBefore = false;
        //find the first 1 need to be zero and have at least one zero before it from right
        while (temp > 0) {
            if ((temp & 1) == 1) {
                if (hasZeroBefore)
                    break;
                else
                    count1++;
            } else {
                hasZeroBefore = true;
                count0++;
            }
            temp = temp >> 1;
            first1To0++;
        }

        if (!hasZeroBefore)
            return -1;

        int mask = ((~0) << (first1To0 + 1));
        num &= mask;

        mask = ((1 << (count1 + 1)) - 1) << (count0 - 1);
        num |= mask;
        return num;
    }

    public int nextGreatestNumber(int num) { // check last lines
        int first0To1 = 0;
        int count1 = 0;

        int temp = num;
        boolean hasOneBefore = false;
        //find the first 1 need to be zero and have at least one zero before it from right
        while (temp > 0) {
            if ((temp & 1) == 1) {
                if (hasOneBefore)
                    break;
                else
                    count1++;
            } else {
                hasOneBefore = true;
            }
            temp = temp >> 1;
            first0To1++;
        }

        if (!hasOneBefore)
            return -1;

        int mask = ((~0) << (first0To1 + 1));
        num &= mask;

        mask = ((1 << (count1 + 1)) - 1);
        num |= mask;
        return num;
    }

    public int tripleStep(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    public ArrayList<Character> getPath(boolean[][] maze) {//8.2, we can do also recursive
        int[][] maze2 = new int[maze.length][maze[0].length];
        Arrays.fill(maze2, 0);
        for (int i = 0; i < maze[0].length; i++) {
            if (i - 1 >= 0 && maze[0][i])
                maze2[0][i] = 1;
        }
        for (int i = 0; i < maze.length; i++) {
            if (i - 1 >= 0 && maze[i][0])
                maze2[i][0] = 1;
        }
        for (int i = 1; i < maze.length; i++) {
            for (int j = 1; j < maze[0].length; j++) {
                if (!maze[i][j]) {
                    if (maze2[i - 1][j] == 1 || maze2[i][j - 1] == 1)
                        maze2[i][j] = 1;
                    else
                        maze2[i][j] = -1;
                } else
                    maze2[i][j] = -1;
            }
        }
        int i = 0, j = 0;
        ArrayList<Character> result = new ArrayList<>();
        while (i != maze.length - 1 && j != maze[0].length - 1) {
            if (maze2[i][j + 1] == 1)
                result.add('R');
            else if (maze2[i + 1][j] == 1)
                result.add('D');
            else
                return new ArrayList<Character>();
        }
        return result;
    }

    public int magicIndex(int[] arr) {
        return magicIndexHelper(arr, 0, arr.length - 1);
    }

    public int magicIndexHelper(int[] arr, int start, int end) {
        if (start > end || start < 0 || end < 0)
            return -1;
        if (start == end && arr[start] == start)
            return start;

        int mid = (start + end) / 2;

        if (arr[mid] == mid)
            return mid;
        else {
            if (arr[mid] > mid && arr[mid] < arr.length - 1) {
                int res = magicIndexHelper(arr, arr[mid], end);
                if (res != -1)
                    return res;
                else
                    return magicIndexHelper(arr, start, mid);
            } else {
                int res = magicIndexHelper(arr, start, arr[mid]);
                if (res != -1)
                    return res;
                else
                    return magicIndexHelper(arr, mid, end);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> powerSet(int[] arr) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            ArrayList<ArrayList<Integer>> cur = new ArrayList<>();
            for (ArrayList<Integer> list : res) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.addAll(list);
                temp.add(arr[i]);
                cur.add(temp);
            }

            ArrayList<Integer> t = new ArrayList<>();
            t.add(arr[i]);
            cur.add(t);
            res.addAll(cur);
        }

        return res;
    }

    public int minProduct(int a, int b) {//8.5
        return 0;
    }

    public void hanoi(int n) {
        Stack<Integer> Tower1 = new Stack<>();
        Stack<Integer> Tower2 = new Stack<>();
        Stack<Integer> Tower3 = new Stack<>();
        for (int i = n; i >= 1; i--) {
            Tower1.push(i);
        }

        hanoiHelper(n, Tower1, Tower3, Tower2);
    }

    public void hanoiHelper(int number, Stack start, Stack dep, Stack buffer) {
        if (number == 1) {
            System.out.println("move " + start.peek() + "to dep" + "dep is:" + dep);
            move(start, dep);
        } else {
            hanoiHelper(number - 1, start, buffer, dep);
            System.out.println("move " + start.peek() + "to dep" + "dep is:" + dep);
            move(start, dep);
            hanoiHelper(number - 1, buffer, dep, start);
        }
    }

    public void move(Stack start, Stack dep) {
        dep.push(start.pop());
    }

    public ArrayList<ArrayList<Character>> permWODups(String s) {// 8.7 without Dups
        ArrayList<ArrayList<Character>> res;
        if (s.length() == 1) {
            ArrayList<Character> list = new ArrayList<>();
            list.add(s.charAt(0));
            res = new ArrayList<>();
            res.add(list);
            return res;
        } else {
            char c = s.charAt(s.length() - 1);
            res = permWODups(s.substring(0, s.length() - 1));
            ArrayList<ArrayList<Character>> cur = new ArrayList<>();
            for (ArrayList<Character> list : res) {
                for (int i = 0; i < list.size() + 1; i++) {
                    list.add(i, c);
                    cur.add(new ArrayList<>(list));
                    list.remove(i);
                }
            }

            return cur;
        }
    }

    public HashSet<ArrayList<Character>> permWDups(String s) {// 8.7 with Dups
        HashSet<ArrayList<Character>> res;
        if (s.length() == 1) {
            ArrayList<Character> list = new ArrayList<>();
            list.add(s.charAt(0));
            res = new HashSet<>();
            res.add(list);
            return res;
        } else {
            char c = s.charAt(s.length() - 1);
            res = permWDups(s.substring(0, s.length() - 1));
            HashSet<ArrayList<Character>> cur = new HashSet<>();
            for (ArrayList<Character> list : res) {
                for (int i = 0; i < list.size() + 1; i++) {
                    list.add(i, c);
                    cur.add(new ArrayList<>(list));
                    list.remove(i);
                }
            }
            return cur;
        }
    }

    public HashSet<String> parens(int numPair) {
        HashSet<String> res = new HashSet<>();
        res.add("()");
        for (int i = 1; i < numPair; i++) {
            HashSet<String> temp = new HashSet<>();
            for (String s : res) {
                String st = s + "()";
                temp.add(st);
                st = "()" + s;
                temp.add(st);
                st = "(" + s + ")";
                temp.add(st);
            }
            res = temp;
        }
        return res;
    }

    private void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
        if (leftRem < 0 || rightRem < leftRem) return;//invalid state

        if (leftRem == 0 && rightRem == 0) {/*Out of left and right parentheses */
            list.add(String.copyValueOf(str));
        } else {
            str[index] = '(';//Add left and recurse
            addParen(list, leftRem - 1, rightRem, str, index + 1);

            str[index] = ')';//Add right and recurse
            addParen(list, leftRem, rightRem - 1, str, index + 1);
        }
    }

    public ArrayList<String> generateParens(int count) {
        char[] str = new char[count * 2];
        ArrayList<String> list = new ArrayList<String>();
        addParen(list, count, count, str, 0);
        return list;
    }

    public int eightQueens(int num){//8.12, assume that we have 8 row and column
        boolean[] row = new boolean[8];
        boolean[] col = new boolean[8];
        Arrays.fill(row, true);
        Arrays.fill(col, true);
        int[][][] mem = new int[num+1][code(row)+1][code(col)+1];

        for(int[][] arr:mem)
            for(int[] arr2:arr)
                Arrays.fill(arr2, -1);

        return eightQueens(num, row, col, mem);
    }

    public int eightQueens(int number, boolean[] row, boolean[] col, int[][][] mem){
        int ways = 0;
        if(number < 1 || code(row) == 0 || code(col) == 0)
            ways = 0;

        if(mem[number][code(row)][code(col)] != -1)
            return mem[number][code(row)][code(col)];
        else{
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if(row[i]==true && col[j]==true){
                        row[i] = false; col[j] = false;
                        if(number-1 > 0 && mem[number-1][code(row)][code(col)] != -1)
                            ways += mem[number-1][code(row)][code(col)];
                        else
                            ways += eightQueens(number-1, row, col, mem);
                        row[i] = true;col[j] = true;
                    }
                }
            }
            mem[number][code(row)][code(col)] = ways;
        }
        return ways;
    }

    private int code(boolean[] num){
        int res = 0;
        for (int i = 0; i < num.length; i++) {
            if(num[i] = true)
                res |= (1 << (num.length - i - 1));
        }
        return res;
    }

    public int maxHeighStack(Box[] boxes){
        Arrays.sort(boxes);
        int[] dp = new int[boxes.length];
        return maxStack(boxes, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, dp);
    }

    public int maxStack(Box[] boxes, int index, int minHeight, int minWidth, int minDepth, int[] dp) {
        if (index == boxes.length)
            return 0;

        int maxWith = 0;
        if (boxes[index].width <= minWidth && boxes[index].depth <= minDepth &&
                boxes[index].height <= minHeight) {
            if (dp[index] != 0)
                maxWith = dp[index];
            else{
                int WThis = maxStack(boxes, index + 1, boxes[index].height,
                        boxes[index].width, boxes[index].depth, dp);
                maxWith = WThis + boxes[index].height;
            }
        }
        int maxWithout = maxStack(boxes, index + 1, minHeight, minWidth, minDepth, dp);

        return Math.max(maxWith, maxWithout);
    }

    public int countWays(String expr, boolean result){
        HashMap<String, Integer> mapTrue = new HashMap<>();
        HashMap<String, Integer> mapFalse = new HashMap<>();
        return countWays(expr, result, mapTrue, mapFalse);
    }

    public int countWays(String expr, boolean result,
                         HashMap<String, Integer> mapTrue, HashMap<String, Integer> mapFalse){
        if (expr.length() == 1){
            if(expr.charAt(0)=='0' && !result)
                return 1;
            if(expr.charAt(0)=='0' && result)
                return 0;
            if(expr.charAt(0)=='1' && !result)
                return 0;
            if(expr.charAt(0)=='1' && result)
                return 1;
        }

        if(result && mapTrue.containsKey(expr))
            return mapTrue.get(expr);
        if(!result && mapFalse.containsKey(expr))
            return mapFalse.get(expr);

        int ways = 0;
        for (int i = 1; i < expr.length()-1; i+=2) {
            char c = expr.charAt(i);
            switch (c){
                case '&':
                    if(result)
                        ways += countWays(expr.substring(0, i), true,mapTrue, mapFalse) *
                                countWays(expr.substring(i+1), true,mapTrue, mapFalse);
                    else{
                        ways += countWays(expr.substring(0, i), true,mapTrue, mapFalse) *
                                countWays(expr.substring(i+1), false,mapTrue, mapFalse);
                        ways += countWays(expr.substring(0, i), false,mapTrue, mapFalse) *
                                countWays(expr.substring(i+1), true,mapTrue, mapFalse);
                        ways += countWays(expr.substring(0, i), false,mapTrue, mapFalse) *
                                countWays(expr.substring(i+1), false,mapTrue, mapFalse);
                    }
                    break;
                case '|':
                    if(result) {
                        ways += countWays(expr.substring(0, i), true, mapTrue, mapFalse) *
                                countWays(expr.substring(i + 1), false, mapTrue, mapFalse);
                        ways += countWays(expr.substring(0, i), false, mapTrue, mapFalse) *
                                countWays(expr.substring(i + 1), true, mapTrue, mapFalse);
                        ways += countWays(expr.substring(0, i), true, mapTrue, mapFalse) *
                                countWays(expr.substring(i + 1), true, mapTrue, mapFalse);
                    }else{
                        ways += countWays(expr.substring(0, i), false,mapTrue, mapFalse) *
                            countWays(expr.substring(i+1), false,mapTrue, mapFalse);
                    }
                    break;
                case '^':
                    if(result) {
                        ways += countWays(expr.substring(0, i), true, mapTrue, mapFalse) *
                                countWays(expr.substring(i + 1), false, mapTrue, mapFalse);
                        ways += countWays(expr.substring(0, i), false, mapTrue, mapFalse) *
                                countWays(expr.substring(i + 1), true, mapTrue, mapFalse);
                    }else{
                        ways += countWays(expr.substring(0, i), false,mapTrue, mapFalse) *
                                countWays(expr.substring(i+1), false,mapTrue, mapFalse);
                        ways += countWays(expr.substring(0, i), true, mapTrue, mapFalse) *
                                countWays(expr.substring(i + 1), true, mapTrue, mapFalse);
                    }
                    break;
            }
        }

        if(result)
            mapTrue.put(expr, ways);
        else
            mapFalse.put(expr, ways);
        return ways;
    }

    public int[] sortedMerge(int[] A, int[] B, int sizeAValid){
        int Aindex = sizeAValid-1;
        int Bindex = B.length-1;
        int i = A.length-1;
        while(Bindex >=0) {
            if(Aindex >=0 && B[Bindex] > A[Aindex]) {
                A[i] = B[Bindex];
                Bindex--;
            }else{
                A[i] = A[Aindex];
                Aindex--;
            }
            i--;
        }
        return A;
    }

    public ArrayList<String> groupAnagram(ArrayList<String> list){
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String s:list){
            String key = makeKeyString(s);
            if(map.containsKey(key))
                map.get(key).add(s);
            else {
                ArrayList<String> l = new ArrayList<>();
                l.add(s);
                map.put(key, l);
            }
        }
        ArrayList<String> res=  new ArrayList<>();
        for(String s:map.keySet()){
            for(String r:map.get(s)){
                res.add(r);
            }
        }
        return res;
    }

    private String makeKeyString(String s){
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return arr.toString();
    }

    public int searchinRotArr(int[] arr, int target){// 10.3 
        return rotArray(arr, target, 0 , arr.length-1);
    }

    private int rotArray(int[] arr, int target, int start, int end) {
        int mid = (start + end) / 2;
        if (arr[mid] == target)
            return mid;
        if (start > end)
            return -1;
        boolean leftNormal = arr[start] < arr[mid] ? true:false;
        boolean rightNormal = arr[mid] < arr[end] ? true:false;

        if(leftNormal){
            if (target >= arr[start] && target < arr[mid])
                return rotArray(arr, target, start, mid-1);
            else
                return rotArray(arr, target, mid+1, end);
        }
        else if(rightNormal){
            if (target > arr[mid] && target <= arr[end])
                return rotArray(arr, target, mid+1, end);
            else
                return rotArray(arr, target, start, mid-1);
        }else{
            if(arr[start] == arr[mid] && arr[mid]!=arr[end])
                return rotArray(arr, target, mid+1, end);
            else{
                int right = rotArray(arr, target, mid+1, end);
                if (right != -1)
                    return right;
                else
                    return rotArray(arr, target, start, mid-1);
            }
        }
    }
}
