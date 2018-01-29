package Interview;

import java.util.HashMap;

public class TrieNode {
    char c;
    HashMap<Character, TrieNode> children;
    boolean isWord;
    TrieNode(char theC){
        c = theC;
        children = new HashMap<>();
    }
}
