import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/*
For test add this to main:
YaserHashLinkedList yaserHash = new YaserHashLinkedList(10);
        yaserHash.put("Yaser",90);
        yaserHash.put("Yaser",9088);
        yaserHash.put("Yasor",900);
        yaserHash.put("Forroz",100);

        System.out.println(yaserHash.getValue("Yaser"));
 */

class HashData{
    public String key;
    public int value;

    public HashData(String theKey, int theValue){key = theKey; value = theValue;}
}

public class YaserHashLinkedList {

    public LinkedList<HashData>[] hashTable;
    public int size;

    public YaserHashLinkedList(int theSize){
        size = theSize;
        hashTable = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    public void put(String key, int value){
        int hashNumber = key.length() % size;
        hashTable[hashNumber].add(new HashData(key, value));
    }

    public ArrayList<String> getValue(String theKey){
        int hashNumber = theKey.length() % size;
        HashData[] allDataWithDesiredKey = Arrays.copyOf(hashTable[hashNumber].toArray(),
                hashTable[hashNumber].toArray().length, HashData[].class);

        ArrayList<String> result = new ArrayList<>();
        for(HashData data : allDataWithDesiredKey){
            if(data.key == theKey) result.add("Key is " + data.key + " and Value is "+ data.value);
        }

        return result;
    }
}
