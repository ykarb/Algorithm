package Interview;

import java.util.ArrayList;

public class Listy {
    ArrayList<Integer> list;
    Listy(){
        list = new ArrayList<>();
    }

    int elementAt(int index){
        if(index > list.size()-1)
            return -1;
        else
            return list.get(index);
    }

    void add(int number){
        list.add(number);
    }
}
