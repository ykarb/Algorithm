import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        CrackingInterview ci = new CrackingInterview();
        TreeNodeLR root = new TreeNodeLR(8);
        TreeNodeLR left = new TreeNodeLR(4);
        TreeNodeLR leftleft = new TreeNodeLR(2);
        TreeNodeLR leftright = new TreeNodeLR(5);
        left.left = leftleft;
        left.right = leftright;
        TreeNodeLR right = new TreeNodeLR(12);
        root.left = left;
        root.right = right;

        TreeNodeLR result = ci.firstCommonAncestor(root, leftleft, leftright);
        }
    }