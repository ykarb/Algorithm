public class ResultCommon {
    TreeNode res;
    boolean ancestor;

    public ResultCommon(TreeNode theNode){res = theNode;}
    public ResultCommon(TreeNode theNode, boolean flag){res = theNode;ancestor = flag;}
}
