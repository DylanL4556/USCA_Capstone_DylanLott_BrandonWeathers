// Authors: Dylan Lott & Brandon Weathers
// Date last updated:  11/25/2025 6:38 PM

import java.util.*;
import java.io.*;

class TranslatedSuffixTree{
    static double totalEntries = 0.00;
    static double meanLength = 0.00;
    static final int MAX_CHAR = 1000;

    static class Node{
        Node[] children = new Node[MAX_CHAR];
        Node suffixLink;
        int start;
        int[] end;
        int suffixIndex;

        Node(int newStart, int[] newEnd){
            for(int i = 0; i < MAX_CHAR; i++){
                children[i] = null;
            }
            suffixLink = null;
            start = newStart;
            end = newEnd;
            suffixIndex = -1;
        }
    }

    // Global variables
    char[] text = new char[200];
    Node root = null;

    Node lastNewNode = null;
    Node activeNode = null;

    int activeEdge = -1;
    int activeLength = 0;
    int remainingSuffixCount = 0;
    int[] leafEndRef = new int[] {-1};
    int[] rootEnd = null;
    int[] splitEnd = null;
    int size = -1;
    int size1 = 0;

    Node newNode(int start, int[] end){
        Node node = new Node(start, end);
        node.suffixLink = root;
        return node;
    }

    int edgeLength(Node n){
        if(n == root) return 0;
        return ((n.end[0] - n.start) + 1);
    }

    boolean walkDown(Node currNode){
        if(activeLength >= edgeLength(currNode)){
            activeEdge += edgeLength(currNode);
            activeLength -= edgeLength(currNode);
            activeNode = currNode;
            return true;
        }
        return false;
    }

    void extendSuffixTree(int pos){
        leafEndRef[0] = pos;
        remainingSuffixCount++;
        lastNewNode = null;

        while(remainingSuffixCount > 0){
            if(activeLength == 0) activeEdge = pos;
            int ch = (int) text[activeEdge];
            if(activeNode.children[ch] == null){
                activeNode.children[ch] = newNode(pos, leafEndRef);

                if(lastNewNode != null){
                    lastNewNode.suffixLink = activeNode;
                    lastNewNode = null;
                }
            }else{
                Node next = activeNode.children[ch];
                if(walkDown(next)) continue;

                if(text[next.start + activeLength] == text[pos]){
                    if((lastNewNode != null) && (activeNode != root)){
                        lastNewNode.suffixLink = activeNode;
                        lastNewNode = null;
                    }
                    activeLength++;
                    break;
                }

                splitEnd = new int[] {next.start + activeLength - 1};
                Node split = newNode(next.start, splitEnd);
                activeNode.children[(int)text[next.start]] = split;

                split.children[(int)text[pos]] = newNode(pos, leafEndRef);

                next.start += activeLength;
                split.children[(int)text[next.start]] = next;

                if(lastNewNode != null) lastNewNode.suffixLink = split;
                lastNewNode = split;
            }

            remainingSuffixCount--;
            if(activeNode == root && activeLength > 0){
                activeLength--;
                activeEdge = pos - remainingSuffixCount + 1;
            }else if (activeNode != root){
                activeNode = activeNode.suffixLink;
            }
        }
    }

    void printEdge(int i, int j){
        for(int k = i; k <= j && text[k] != '#'; k++) System.out.print(text[k]);
        if(j >= i && text[j] == '#') System.out.print('#');
    }

    void setSuffixIndexByDFS(Node n, int labelHeight){
        if(n == null) return;
        if(n.start != -1){
            // printEdge(n.start, n.end[0])
        }

        boolean leaf = true;
        for(int i = 0; i < MAX_CHAR; i++){
            if(n.children[i] != null){
                leaf = false;
                setSuffixIndexByDFS(n.children[i], labelHeight + edgeLength(n.children[i]));
            }
        }

        if(leaf){
            for(int i = n.start; i <= n.end[0]; i++){
                if(text[i] == '#'){
                    n.end = new int[] {i};
                }
            }
            n.suffixIndex = size - labelHeight;
        }
    }

    void buildSuffixTree(){
        size = 0;
        while((size < text.length) && (text[size] != 0)) size++;

        rootEnd = new int[] {-1};
        root = newNode(-1, rootEnd);
        root.suffixLink = root;
        activeNode = root;

        for(int i = 0; i < size; i++) extendSuffixTree(i);

        setSuffixIndexByDFS(root, 0);
    }

    int doTraversal(Node n, int labelHeight, int[] maxHeight, int[] substringStartIndex){
        if(n == null) return 0;
        int ret = -1;
        if(n.suffixIndex < 0){
            for(int i = 0; i < MAX_CHAR; i++){
                if(n.children[i] != null){
                    ret = doTraversal(n.children[i], labelHeight + edgeLength(n.children[i]), maxHeight, substringStartIndex);

                    if (n.suffixIndex == -1)
                        n.suffixIndex = ret;
                    else if ((n.suffixIndex == -2 && ret == -3) ||
                            (n.suffixIndex == -3 && ret == -2) ||
                            n.suffixIndex == -4)
                    {
                        n.suffixIndex = -4;
                        if (maxHeight[0] < labelHeight) {
                            maxHeight[0] = labelHeight;
                            substringStartIndex[0] = n.end[0] - labelHeight + 1;
                        }
                    }
                }
            }
        }else if(n.suffixIndex > -1 && n.suffixIndex < size1){
            return -2;
        }else if(n.suffixIndex >= size1){
            return -3;
        }
        return n.suffixIndex;
    }

    void getLongestCommonSubstring(){
        int[] maxHeight = new int[] {0};
        int[] substringStartIndex = new int[] {0};
        doTraversal(root, 0, maxHeight, substringStartIndex);

        int k;
        for(k = 0; k < maxHeight[0]; k++) System.out.print(text[k + substringStartIndex[0]]);
        if(k == 0){
            System.out.print("No common substring");
            totalEntries++;
        }
        else{
            System.out.print(", of length: " + (maxHeight[0] + 1));
            meanLength += (double) (maxHeight[0] + 1);
            totalEntries++;
        }
        System.out.println();
    }

    void setInputString(String s){
        int i = 0;
        for(; i < s.length() && i < text.length; i++) text[i] = s.charAt(i);
        for(; i < text.length; i++) text[i] = 0;
    }

    public static void main(String[] args){
        // try{
        //     Scanner myScanner0 = new Scanner(new File("SyntheticMatched.csv"));
        //     System.out.println("Reading in file: SyntheticMatched.csv...");
        //     while(myScanner0.hasNextLine()){
        //         TranslatedSuffixTree tree = new TranslatedSuffixTree();

        //         tree.size1 = 15;
        //         tree.setInputString(myScanner0.nextLine());
        //         tree.buildSuffixTree();
        //         tree.getLongestCommonSubstring();
        //     }
        //     System.out.println("File read complete ✓");
        //     System.out.println("The mean length of the substring is: " + (meanLength / totalEntries));

        // }catch(FileNotFoundException e){
        //     System.out.println("File not found.");
        // }

        try{
            Scanner myScanner0 = new Scanner(new File("Synthetic300000PwPairsV2.csv"));
            System.out.println("Reading in file: Synthetic300000PwPairsV2.csv...");
            while(myScanner0.hasNextLine()){
                TranslatedSuffixTree tree = new TranslatedSuffixTree();

                tree.size1 = 15;
                tree.setInputString(myScanner0.nextLine());
                // System.out.print(myScanner0.nextLine());
                tree.buildSuffixTree();
                tree.getLongestCommonSubstring();
            }
            System.out.println("File read complete ✓");
            System.out.println("The mean length of the substring is: " + (meanLength / totalEntries));

        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        // Synthetic300000PwPairsV2.csv
    }
}
