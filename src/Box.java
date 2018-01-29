package Interview;

public class Box implements Comparable<Box> {
    int width;
    int height;
    int depth;
    Box(int w, int h, int d){
        width = w;
        height = h;
        depth = d;
    }

    public int compareTo(Box otherBox){
        if(width > otherBox.width)
            return -1;
        else if(width < otherBox.width)
            return 1;
            else
            return 0;
    }
}
