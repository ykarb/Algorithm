import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();

        try {
            FileWriter write = new FileWriter("Yaser.txt", true);
            BufferedWriter bw = new BufferedWriter(write);

            bw.write("Hello");
            bw.close();

            FileReader fr = new FileReader("Yaser.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            System.out.println(line.charAt(0));

        }catch(IOException ex){
            ex.printStackTrace();
        }

        }
    }