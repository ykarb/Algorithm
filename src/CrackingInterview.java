import org.w3c.dom.ls.LSInput;
import sun.awt.image.ImageWatched;
import sun.reflect.generics.tree.Tree;

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
                if(matrix[i][j] == 0) {
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

    public boolean isRotation(String s1, String s2){ // 1.9
        return  (s1+s1).contains(s2);
    }

    public void removeDups(ListNode node){ // 2.1

    }

    public ListNode insertionSortList(ListNode head){
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        ListNode current = dummy;

        while (current.next != null){
            while(current.next != null && current.val <= current.next.val)
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

    public ListNode selectionSortList(ListNode head){
        ListNode headDummy = new ListNode(0);
        headDummy.next = head;
        ListNode current = head;
        ListNode minNodeFather= current;
        ListNode sortedHead = new ListNode(0);
        ListNode d2 = sortedHead;

        while(minNodeFather.next != null) {

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

    public ListNode KthtoLast(ListNode head, int k){
        ListNode fast = head;
        int i = 0;
        while (i < k && fast != null){
            fast = fast.next;
            i++;
        }

        ListNode slow = head;
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        if(fast== null)
            return null;
        else
            return slow;
    }

    public ListNode partitionList(ListNode head, int p){
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

        if(firstBig.next == null)
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

    public ListNode sumReverseLists(ListNode l1, ListNode l2){ // reverse order of numbers
        ListNode dummy = new ListNode(0);
        ListNode dummyHead = dummy;
        int carry = 0;

        while(l1 != null || l2 != null){
            int res = 0;
            if(l1 != null){
                res += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                res += l2.val;
                l2 = l2.next;
            }
            if(res+carry != 0) {
                ListNode temp = new ListNode((res + carry) % 10);
                carry = (res + carry) / 10;
                dummy.next = temp;
                dummy = dummy.next;
            }
        }

        if(carry == 0)
            return dummyHead.next;
        else{
            ListNode last = new ListNode(1);
            dummy.next = last;
            return dummyHead.next;
        }
    }

    public ListNode sumLists(ListNode l1, ListNode l2){// assumes same size of list
        ListNode res = sumListHelper(l1, l2);
        return res.val == 0 ? res.next:res;
    }

    public ListNode sumListHelper(ListNode l1, ListNode l2){
        if(l1.next == null && l2.next == null){
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

    public boolean checkPalindrome(ListNode head){ // 2.6
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;
        boolean even;

        while(fast != null && fast.next != null){
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

        while(current != slow){
            ListNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }

        ListNode start2 = slow.next;
        ListNode start1 = slow;
        if(even)
            slow.next = pre;
        else
            start1 = pre;

        while(start1 != null && start2 != null){
            if(start1.val != start2.val)
                return false;
            else {
                start1 = start1.next;
                start2 = start2.next;
            }
        }

        return true;
    }

    public ListNode getIntersection(ListNode l1, ListNode l2){
        ListNode startL1 = l1;
        ListNode startL2 = l2;

        boolean firstl1 = true;
        boolean firstl2 = true;

        while(l1 != null && l2 != null) {

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

    public ListNode getFirstInLoop(ListNode head){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;

        while(slow != fast){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp = head;
        while(temp != slow){
            temp = temp.next;
            slow = slow.next;
        }

        return temp;
    }

    public boolean existRoute(Graph graph, GNode node1, GNode node2){
        if(node1 == node2)
            return true;

        for(GNode node:graph.getNodes())
            node.visited = false;

        LinkedList<GNode> queue = new LinkedList<>();
        queue.addLast(node1);

        while(!queue.isEmpty()){
            GNode theNode = queue.removeFirst();
            if(theNode == node2)
                return true;
            theNode.visited = true;
            for(GNode neighbor:theNode.neighbors){
                if(neighbor.visited == false){
                    if(neighbor == node2)
                        return true;
                    else
                        queue.add(neighbor);
                }
            }
        }

        return false;
    }

    public TreeNode minimalHeight(int[] sorted, int start, int end){
        if(start > end)
            return null;
        if (start == end){
            return new TreeNode(sorted[start]);
        }

        int mid = (start+end)/2;
        TreeNode root = new TreeNode(sorted[mid]);
        root.left = minimalHeight(sorted,start, mid -1);
        root.right = minimalHeight(sorted, mid+1, end);
        return root;
    }

    public ArrayList<LinkedList> listOfDepths(TreeNode root){
        ArrayList<LinkedList> result = new ArrayList<>();

        LinkedList<TreeNode> q = new LinkedList<>();
        q.addFirst(root);
        int level = 0;

        while (!q.isEmpty()){
            int size = q.size();
            result.add(new LinkedList());
            for (int i = 0; i < size; i++) {
                TreeNode node = q.removeLast();
                if(node.left!= null)
                    q.addFirst(node.left);
                if(node.right!= null)
                    q.addFirst(node.right);
                result.get(level).add(node);
            }
            level++;
        }

        return result;
    }

    public boolean checkBalance(TreeNode root){
        int rightHeight = 0;
        int leftHeight = 0;
        if (root.right != null)
        rightHeight = getHeight(root.right);
        if(rightHeight == -1)
            return false;
        if (root.left != null)
        leftHeight = getHeight(root.left);
        if(leftHeight == -1)
            return false;

        return Math.abs(leftHeight - rightHeight) > 1 ? false:true;
    }

    private int getHeight(TreeNode root){
        int rightHeight = 0;
        int leftHeight = 0;
        if(root.left == null && root.right == null)
            return 1;
        if (root.right != null)
            rightHeight = getHeight(root.right);
        if(rightHeight == -1)
            return -1;
        if (root.left != null)
            leftHeight = getHeight(root.left);
        if(leftHeight == -1)
            return -1;

        return Math.abs(leftHeight - rightHeight) > 1 ? -1:Math.max(rightHeight, leftHeight)+1;
    }

    public boolean validateBST(TreeNode root){
        return validateBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validateBSTHelper(TreeNode root, int min, int max){
        if(root.val < min || root.val > max)
            return false;
        boolean left = true;
        boolean right = true;
        if(root.left != null)
            left = validateBSTHelper(root.left, min, root.val);
        if(!left)
            return false;
        if(root.right != null)
            right = validateBSTHelper(root.right, root.val, max);

        return left && right;
    }

    public TreeNodeP successor(TreeNodeP node){
        if(node.right != null){
            TreeNodeP temp = node.right;
            while (temp.left != null)
                temp = temp.left;
            return temp;
        }else
            return successorHelper(node);
    }

    private TreeNodeP successorHelper(TreeNodeP root){
        if(root.parent == null)
            return null;
        else if(root.parent.left == root)
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

    public TreeNodeLR firstCommonAncestor(TreeNodeLR root, TreeNodeLR nodeA, TreeNodeLR nodeB){
        // we assume all nodes initialized correctly
        //set left and right boolean flag using DFS
        check(root, nodeA, nodeB);

        return getFirstCommon(root);
    }

    private TreeNodeLR getFirstCommon(TreeNodeLR root){
        if((root.firstInLeft && root.secInRight)||(root.secInLeft && root.firstInRight))
            return root;

        if(root.firstInLeft && root.secInLeft)
            return getFirstCommon(root.left);

        if(root.firstInRight && root.secInRight)
        return getFirstCommon(root.right);

        return null;
    }

    public TreeNodeLR check(TreeNodeLR root, TreeNodeLR nodeA, TreeNodeLR nodeB){ // dosent work
        if(root.left == nodeA)
            root.firstInLeft = true;
        if(root.right == nodeA)
            root.firstInRight = true;

        if(root.left == nodeB)
            root.secInLeft = true;
        if(root.right == nodeB)
            root.secInRight = true;

        if(root.firstInLeft && root.secInRight){
            TreeNodeLR temp = new TreeNodeLR(-1);
            temp.firstInLeft = true;
            temp.secInRight = true;
            return temp;
        }


        if(root.secInLeft && root.firstInRight) {
            TreeNodeLR temp = new TreeNodeLR(-1);
            temp.secInLeft = true;
            temp.firstInRight = true;
            return temp;
        }

        if(root == nodeA){
            root.firstInLeft = root.firstInRight = root.secInRight = root.secInLeft = true;
            return root;
        }

        if(root == nodeB){
            root.firstInLeft = root.firstInRight = root.secInRight = root.secInLeft = true;
            return root;
        }

        if(root.left != null) {
            TreeNodeLR res= check(root.left, nodeA, nodeB);
            if(res.firstInRight) root.firstInRight = true;
            if(res.firstInLeft) root.firstInLeft = true;
            if(res.secInRight) root.secInRight = true;
            if(res.secInLeft) root.secInLeft = true;
        }
        if(root.right != null) {
            TreeNodeLR res= check(root.right, nodeA, nodeB);
            if(res.firstInRight) root.firstInRight = true;
            if(res.firstInLeft) root.firstInLeft = true;
            if(res.secInRight) root.secInRight = true;
            if(res.secInLeft) root.secInLeft = true;
        }

        return root;
    }


}
