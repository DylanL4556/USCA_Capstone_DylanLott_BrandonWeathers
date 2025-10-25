// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 10/25/2025 1:03 AM

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
 * however, here is what I do know.
 * All strings are a subsequences of themselves!
 * Therefore, if you just set X set to Y_1 and P set to "", it makes it as if they are nonexistent (I think).
 *
 * So, rather than changing the algorithm I will just always run it with X set to Y_1 and P set to "".
 * Yes, I know that it is less efficient.
 * Yes, I know we have "over 3,000,000 user entries".
 * I don't care.
 * If you can debloat the algorithm, do so your self.
 * k bye
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

class LCSM{
    public static void main (String[] args){
        String X, Y_1, Y_2, P;
        Scanner ob = new Scanner(System.in);

        System.out.print("Please enter string X: ");
        X = ob.nextLine();

        System.out.print("Please enter string Y_1: ");
        Y_1 = ob.nextLine();

        System.out.print("Please enter string Y_2: ");
        Y_2 = ob.nextLine();

        System.out.print("Please enter string P: ");
        P = ob.nextLine();

        final int m = X.length(), n_1 = Y_1.length(), n_2 = Y_2.length(), r = P.length();

        int[][][][] M = new int[m + 1][n_1 + 1][n_2 + 1][r + 1];
        System.out.println("The desired string is \"" + CLCSSM(X, Y_1, Y_2, P, m, n_1, n_2, r, M) + "\" and it's length is " + CLCSSM(X, Y_1, Y_2, P, m, n_1, n_2, r, M).length());
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
}
