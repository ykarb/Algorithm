package Interview;
public class Main {
    public static void main(String[] args) {
        CrackingInterview ci = new CrackingInterview();
        int[] arr = new int[]{41,43,4,3,443,10000,9,-2,0};
        ci.heapSort(arr);
        for(int num:arr)
            System.out.println(num);
            }
    }