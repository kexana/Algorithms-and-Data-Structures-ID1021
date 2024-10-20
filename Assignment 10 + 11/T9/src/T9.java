import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class T9 {
    Node root;

    private class Node {
        public Node[] next;
        public boolean valid;

        public Node() {
            next = new Node[27];
            valid = false;
        }
    }

    public T9(String file) {
        this.root = new Node(); 
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                char[] row = line.toLowerCase()
                        .toCharArray();
                addWord(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addWord(char[] word) {
        int[] wordAsCode = new int[word.length];
        for (int i = 0; i < word.length; i++) {
            wordAsCode[i] = charToCode(word[i]);
        }

        Node itr = root;
        int wordindex = 0;
        while (wordindex < word.length) {
            if (itr.next[wordAsCode[wordindex]] == null) {
                itr.next[wordAsCode[wordindex]] = new Node();
            }
            itr = itr.next[wordAsCode[wordindex]];
            wordindex++;
        }
        itr.valid = true;

    }
    
    public ArrayList<String> decode(String key) {
        ArrayList<String> suggestions = new ArrayList<String>();

        StringBuilder keyInCode = new StringBuilder();

        for (char c : key.toCharArray()) {
            keyInCode.append(c);
        }

        collect("", this.root, keyInCode.toString(), suggestions);


        return suggestions;
    }
    
    private void collect(String word, Node subtrie, String key, ArrayList<String> valids) {
        //System.out.println(key.length() + " : "+ word + " valid val: " + subtrie.valid);

        if (!key.isEmpty()) {

            int i = keyToIndex(key.charAt(0));

            String curKey = key.substring(1);

            if (subtrie.next[i * 3] != null) {
                collect(word + codeToChar(i * 3), subtrie.next[i * 3], curKey, valids);
            }
            if (subtrie.next[i * 3 + 1] != null) {
                collect(word + codeToChar(i * 3 + 1), subtrie.next[i * 3 + 1], curKey, valids);
            }
            if (subtrie.next[i * 3 + 2] != null) {
                collect(word + codeToChar(i * 3 + 2), subtrie.next[i * 3 + 2], curKey, valids);
            }
        }
        if (subtrie.valid) {
            valids.add(word);
        }
    }

    public int charToCode(char c) {
        if ((int) c - 97 < 16) {
            return (int) c - 97;
        } else if ((int) c - 98 < 21) {
            return (int) c - 98;
        } else if ((int) c - 99 < 24) {
            return (int) c - 99;
        } else if (c == 229) {
            return 24;
        } else if (c == 228) {
            return 25;
        } else {
            return 26;
        }
    }

    public char codeToChar(int c) {
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return (char) (c + 97);
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                return (char) (c + 98);
            case 21:
            case 22:
            case 23:
                return (char) (c + 99);
            case 24:
                return (char) (229);
            case 25:
                return (char) (228);
            case 26:
                return (char) (246);
            default:
                break;
        }
        return '\n';
    }
        
    public int keyToIndex(char k) {
        return (int) k - 49;
    }

    public char CharToKey(char c) {
        switch (c) {
            case 'a':
            case 'b':
            case 'c':
                return '1';
            case 'd':
            case 'e':
            case 'f':
                return '2';
            case 'g':
            case 'h':
            case 'i':
                return '3';
            case 'j':
            case 'k':
            case 'l':
                return '4';
            case 'm':
            case 'n':
            case 'o':
                return '5';
            case 'p':
            case 'r':
            case 's':
                return '6';
            case 't':
            case 'u':
            case 'v':
                return '7';
            case 'x':
            case 'y':
            case 'z':
                return '8';
            case 'å':
            case 'ä':
            case 'ö':
                return '9';
            default:
                break;
        }
        return '\n';
    }
}
