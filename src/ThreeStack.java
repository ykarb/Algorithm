public class ThreeStack {
    int[] mem;
    int[] head;
    int[] size;
    int[] cap;

    public ThreeStack(int capacity){
        mem = new int[capacity];
        head = new int[3];
        head[0] = 0;
        head[1] = capacity / 3;
        head[2] = capacity * 2 / 3;
        size = new int[3];
        size[0] = size[1] = size[2] = 0;
        cap = new int[3];
        cap[0] = head[1] - 0;
        cap[1] = head[2] - head[1];
        cap[2] = capacity - head[2];
    }

    public boolean push(int stackNumber, int data){
        if(size[stackNumber] < cap[stackNumber]){
            head[stackNumber]++;
            head[stackNumber] = head[stackNumber] % mem.length;
            mem[head[stackNumber]] = data;
            size[stackNumber]++;
            return true;
        }else if (size[stackNumber+1 % 3] < cap[stackNumber+1 % 3]){ // more than capcaity
            shiftRight(stackNumber+1 % 3);
            head[stackNumber]++;
            head[stackNumber] = head[stackNumber] % mem.length;
            mem[head[stackNumber]] = data;
            size[stackNumber]++;
            cap[stackNumber]++;
            return true;
        }else if (size[stackNumber+2 % 3] < cap[stackNumber+2 % 3]) { // more than capcaity
            shiftRight(stackNumber+2 % 3);
            head[stackNumber]++;
            head[stackNumber] = head[stackNumber] % mem.length;
            mem[head[stackNumber]] = data;
            size[stackNumber]++;
            return true;
        }
        else
            return false;
    }

    private void shiftRight(int stackNumber){
        int startIndex = head[stackNumber] - size[stackNumber] +1;
        int endIndex = head[stackNumber] +1;
        for(int i = endIndex; i > startIndex; i--){
            mem[i] = mem[i-1];
        }
        head[stackNumber]++;
        cap[stackNumber]--;
    }

    public int pop(int stackNumber){
        return 0;
    }
}
