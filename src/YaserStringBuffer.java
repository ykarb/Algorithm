import java.util.Arrays;

/*
 YaserStringBuffer stringBuffer = new YaserStringBuffer();
        stringBuffer.append("");
        stringBuffer.append("Yaser");
        stringBuffer.append("ForoozForoozForoozForoozForoozForoozForoozForoozForooz");

        System.out.println(stringBuffer.toString());
 */

public class YaserStringBuffer {

    char[] array;
    int size = 20;
    int count = 0;

    public YaserStringBuffer(){
        array = new char[size];
    }

    public void append(String word){
        if(word == null) return;
        int len = word.length();
        if(len == 0)return;
        int newLength = count + len;
        if(newLength > array.length) expandArray(newLength);
        word.getChars(0, len, array, count);
        count = newLength;
    }

    private void expandArray(int requiredLength) {
        int newCapacity = (requiredLength + 1) * 2;
        if(newCapacity < 0) newCapacity = Integer.MAX_VALUE;
        else if(requiredLength > newCapacity) newCapacity = requiredLength;

        array = Arrays.copyOf(array, newCapacity);
    }

    public String toString(){
        return new String(array);
    }
}
