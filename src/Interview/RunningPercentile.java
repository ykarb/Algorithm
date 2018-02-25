package Interview;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RunningPercentile {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    int size;
    private double percentile;

    public RunningPercentile(double thePercentile){
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        size = 0;
        percentile = thePercentile;
    }

    public void add(int num){
        size++;
        int index  = getIdxPercentile();
        if(!maxHeap.isEmpty() && num < maxHeap.peek()){
            maxHeap.add(num);
            if(maxHeap.size() > index + 1)
                minHeap.add(maxHeap.poll());
        }else{
            minHeap.add(num);
            if(minHeap.size() >= size - index)
                maxHeap.add(minHeap.poll());
        }
    }

    private int getIdxPercentile() {
        return (int)Math.ceil((percentile*size)/100) - 1;
    }

    public int getPercentile(){
        if(size==0)
            throw new IllegalArgumentException();
        else
            return maxHeap.peek();
    }
}
