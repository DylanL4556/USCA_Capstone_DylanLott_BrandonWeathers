// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 11/1/2025 5:01 PM

// This file is only for testing the speed of the LCSM algorithm.
// On average this version of my LCSM takes 0.313 seconds.
// This isn't good, we need to imporve this
// 30,000 entires * 0.313 = 9,290 seconds = more than 2.5 hours!
// Brother, the EPSB tales 0.237 second! (A little under 2 hours.)
// We are cooked

// Alright, upon further consideration, we may be actualy.
// For some reason running it on a different computer makes the LCSM take less time then the EPSB.
// We will just use my most powerful Vero computer to run it.

import java.util.*;
import java.io.*;

class Mini_LCSM_Testing{
    public static final String COMMA_DELIMITER = ",";
    public static void main (String[] args){
        String Y_1 = "ancplucaskai99ancplucaskai99";
        String Y_2 = "ancplucaskai99ancplucaskai997";
        String X = Y_1 + Y_2;
        String P  = "";
        // System.out.println(X);

        final int m = X.length(), n_1 = Y_1.length(), n_2 = Y_2.length(), r = P.length();
        int[][][][] M = new int[m + 1][n_1 + 1][n_2 + 1][r + 1];
        // System.out.println("The first password is:   " + Y_1);
        // System.out.println("The second password is:  " + Y_2);
        long startTime = System.nanoTime();
        for(int index = 0; index < 100; index++){
            String finialAnswer = CLCSSM(X, Y_1, Y_2, P, m, n_1, n_2, r, M);
        }
        long endTime = System.nanoTime();
        System.out.println("The LCMS algorithm takes " + (endTime-startTime)/1000000 + " milisecond(s) for 2 entrie(s) 100 times.");
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
        ArrayList<String> currentLine = new ArrayList<String>();
        try(Scanner rowScanner = new Scanner(line)){
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while(rowScanner.hasNext()){
                currentLine.add(rowScanner.next());
            }
        }
        return currentLine;
    }
}
