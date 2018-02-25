package Interview;

import com.sun.org.apache.xpath.internal.SourceTree;

public class Main {
    public static void main(String[] args) {
        CrackingInterview ci = new CrackingInterview();
        RunningPercentile rp = new RunningPercentile(70);

        rp.add(1);
        rp.add(2);
        rp.add(3);
        rp.add(4);
        rp.add(5);
        rp.add(6);
        rp.add(7);


        System.out.println(rp.getPercentile());
    }
}