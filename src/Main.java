public class Main {


    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();

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

        leetCode.breadthFirstSearch(F, 'H');
        }
    }