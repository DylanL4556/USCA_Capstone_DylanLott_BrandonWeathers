// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 11/1/2025 5:44 PM

/*
 * Alright...
 * This is a program which assepts 4 total inputs.
 * The are: X, Y_1, Y_2, and P.
 * It returns Z.
 * Z must meet the following requirements:
 *      1) Z is a subsequence of X.
 *      2) Z is a substring of Y_1.
 *      3) Z is a substring of Y_2.
 *      4) P is a subsequence of Z.
 *
 * Now, I've tried to change the algorithm so that X and P are removed, but I don't know how to.
 * However, here is what I do know.
 * All strings are a subsequences of themselves!
 * Therefore, if you just set X set to Y_1 and P set to "", it makes it as if they are nonexistent (I think).
 *
 * So, rather than changing the algorithm I will just always run it with X set to Y_1 and P set to "".
 * Yes, I know that it is less efficient.
 * Yes, I know we have over 3,000,000 user entries.
 * I don't care.
 * If you can debloat the algorithm, do so yourself.
 * k bye
 *
 * =========================================================================================================
 *
 * Actualy, this isn't qute true, it only accounts for instaaances for substrings at the beginngin of words.
 * I will have to find a way to update my algoritm.
 *
 * =========================================================================================================
 * Alright, so here is my solution.
 * If anyone finds a problem with it, please correct me.
 * I will set X to a string of a all password concatonated to eachother.
 * Therefore, X will be a subsequence of any given substring in any given password.
 * Example of desired behavior:
 *      X:  WBrandonBrandon
 *      Y1: Brandon
 *      Y2: WBrandon
 *      Z:  Brandon
 *      P: ""
 *
 * NOTE: I'm not sure why this is, (it's just Halloween I guess), but for some reason
 * I have to but smallest strings first for correct pattern matching to work
 * Real Test:
 *      Please enter string Y_1: BrandonW
 *      Please enter string Y_2: WBrandonW
 *      Please enter string P:
 *      BrandonWWBrandonW
 *      The desired string is "BrandonW" and it's length is 8
 */

/*
~/D/2/research/CodebaseCLCSSM λ java LCSM
Please enter string X: abxycdz
Please enter string Y_1: abczw
Please enter string Y_2: abczw
Please enter string P: bz
The desired string is "abcz" and it's length is 4

~/D/2/research/CodebaseCLCSSM λ
 */

import java.util.*;
import java.io.*;

class Mini_LCSM{
    public static final String COMMA_DELIMITER = ",";
    public static void main (String[] args){
        ArrayList<ArrayList<String>> synthetic30k = new ArrayList<>();
        Scanner ob = new Scanner(System.in);

        try{
            Scanner myScanner0 = new Scanner(new File("../Datasets/Synthetic300000PwPairsV2.csv"));
            System.out.print("Reading in file: Synthetic30000PwPairsV2.csv ...   ");
            while(myScanner0.hasNextLine()){
                synthetic30k.add(getRecordFromLine(myScanner0.nextLine()));
            }
            System.out.println("File read complete ✓");
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }

        String Y_1 = synthetic30k.get(11).get(0);
        String Y_2 = synthetic30k.get(11).get(1);
        String X = Y_1 + Y_2;
        String P  = "";

        final int m = X.length(), n_1 = Y_1.length(), n_2 = Y_2.length(), r = P.length();
        int[][][][] M = new int[m + 1][n_1 + 1][n_2 + 1][r + 1];
        System.out.println("The 1st password of the 12th entry is:   " + Y_1);
        System.out.println("The 2nd password of the 12th entry is:  " + Y_2);
        System.out.println("The common substring of the 12th entry is: " + CLCSSM(X, Y_1, Y_2, P, m, n_1, n_2, r, M));
    }

    static String  CLCSSM(String X, String Y_1, String Y_2, String P, int m, int n_1, int n_2, int r, int[][][][] M){
        M = new int[m + 1][n_1 + 1][n_2 + 1][r + 1];
        for(int i = 1; i <= m; i++){
            for(int j_1 = 1; j_1 <= n_1; j_1++){
                for(int j_2 = 1; j_2 <= n_2; j_2++){
                    if(X.charAt(i - 1) == Y_1.charAt(j_1 - 1)){
                        M[i][j_1][j_2][0] = M[i-1][j_1-1][j_2-1][0] + 1;
                    }else{
                        M[i][j_1][j_2][0] = M[i - 1][j_1][j_2][0];
                    }
                }
            }
        }

        for(int j_1 = 0; j_1 <= n_1; j_1++){
            for(int j_2 = 0; j_2 <= n_2; j_2++){
                for(int k = 1; k <= r; k++){
                    M[0][j_1][j_2][k] = -100 * (m + n_1 + n_2);
                }
            }
        }

        for(int i = 0; i <= m; i++){
            for(int k = 1; k <= r; k++){
                M[i][0][0][k] = -100 * (m + n_1 + n_2);
            }
        }

        for(int i = 1; i <= m; i++){
            for(int j_1 = 1; j_1 <= n_1; j_1++){
                for(int j_2 = 1; j_2 <= n_2; j_2++){
                    for(int k = 1; k <= r; k++){
                        if(X.charAt(i - 1) == Y_1.charAt(j_1 - 1)){
                            if(X.charAt(i - 1) == P.charAt(k - 1))
                                M[i][j_1][j_2][k] = M[i-1][j_1-1][j_2-1][k-1] + 1;
                            else
                               M[i][j_1][j_2][k] = M[i-1][j_1-1][j_2-1][k] + 1;
                        }else{
                            M[i][j_1][j_2][k] = M[i-1][j_1][j_2][k];
                        }
                    }
                }
            }
        }

        int maxLength = 0;

        int lastIndexOnY1 = n_1;

        for(int i = 0; i <= m; i++){
            for(int j_1 = 0; j_1 <= n_1; j_1++){
                for(int j_2 = 0; j_2 <= n_2; j_2++){
                    if(M[i][j_1][j_2][r] > maxLength){
                        maxLength = M[i][j_1][j_2][r];
                        lastIndexOnY1 = j_1;
                    }
                }
            }
        }

        return Y_1.substring(lastIndexOnY1 - maxLength, lastIndexOnY1);
    }

    private static ArrayList<String> getRecordFromLine(String line){
        ArrayList<String> currentLine = new ArrayList<String >();
        try(Scanner rowScanner = new Scanner(line)){
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while(rowScanner.hasNext()){
                currentLine.add(rowScanner.next());
            }
        }
        return currentLine;
    }
}
