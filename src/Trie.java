package Interview;

import java.util.HashMap;

public class Trie {
    HashMap<Character, TrieNode> children;
    Trie(){
        children = new HashMap<>();
    }

    void insert(String word){
        TrieNode current;
        HashMap<Character, TrieNode> tempChildren = children;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(tempChildren.containsKey(c)){
                current = tempChildren.get(c);
                if(i == word.length()-1)
                    current.isWord = true;
                tempChildren = current.children;
            }else{
                current = new TrieNode(c);
                if(i == word.length()-1)
                    current.isWord = true;
                tempChildren.put(c, current);
                tempChildren = current.children;
            }
        }
    }

    boolean exist(String word){
        TrieNode current;
        HashMap<Character, TrieNode> tempChildren = children;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (tempChildren.containsKey(c)) {
                current = tempChildren.get(c);
                tempChildren = current.children;
                if (i == word.length() - 1)
                    return true;
            }else
                return false;
        }
        return false;
    }
}
