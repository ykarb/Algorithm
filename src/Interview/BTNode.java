package Interview;

public class BTNode {
    BTNode left;
    BTNode right;
    char val;
    boolean visited;
    BTNode(char theValue){
        left = null;
        right = null;
        val = theValue;
        visited = false;
    }
}
