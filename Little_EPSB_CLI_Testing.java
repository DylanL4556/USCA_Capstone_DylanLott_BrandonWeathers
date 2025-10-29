// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 10/29/2025 10:58 AM

// Just for testing

import java.util.*;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Map;

public class Little_EPSB_CLI_Testing{
    public static void main (String[] args){
        Scanner ob = new Scanner(System.in);
        String currentPassword;
        currentPassword = "ancplucaskai99";
        EPSB EPSB1 = new EPSB();

        System.out.println("\033[H\033[2J");
        System.out.flush();

        EPSB1.addNewPassword(currentPassword);

        // System.out.println("CAPITALS:");
        // System.out.println("     Minimum number of capitals are: " +       EPSB1.getMin(     EPSB1.capitals));
        // System.out.println("     Max number of capitals are: " +           EPSB1.getMax(     EPSB1.capitals));
        // System.out.printf ("     Average number of capitals are: %.2f\n",  EPSB1.getAverage( EPSB1.capitals));
        // System.out.println("     Median number of capitals are: " +        EPSB1.getMedian(  EPSB1.capitals));
        // System.out.println("     Mode number of capitals are: " +          EPSB1.getMode(    EPSB1.capitals));

        // System.out.println("LOWER CASE:");
        // System.out.println("     Minimum number of lower case letters are: " +      EPSB1.getMin(     EPSB1.lowerCase));
        // System.out.println("     Max number of lower case letters are: " +          EPSB1.getMax(     EPSB1.lowerCase));
        // System.out.printf ("     Average number of lower case letters are: %.2f\n", EPSB1.getAverage( EPSB1.lowerCase));
        // System.out.println("     Median number of lower case letters are: " +       EPSB1.getMedian(  EPSB1.lowerCase));
        // System.out.println("     Mode number of lower case letters are: " +         EPSB1.getMode(    EPSB1.lowerCase));

        // System.out.println("LETTERS:");
        // System.out.println("     Minimum number of letters are: " +      EPSB1.getMin(     EPSB1.letters));
        // System.out.println("     Max number of letters are: " +          EPSB1.getMax(     EPSB1.letters));
        // System.out.printf ("     Average number of letters are: %.2f\n", EPSB1.getAverage( EPSB1.letters));
        // System.out.println("     Median number of letters are: " +       EPSB1.getMedian(  EPSB1.letters));
        // System.out.println("     Mode number of letters are: " +         EPSB1.getMode(    EPSB1.letters));

        // System.out.println("NUMBERS:");
        // System.out.println("     Minimum number of numbers are: " +      EPSB1.getMin(     EPSB1.numbers));
        // System.out.println("     Max number of numbers are: " +          EPSB1.getMax(     EPSB1.numbers));
        // System.out.printf ("     Average number of numbers are: %.2f\n", EPSB1.getAverage( EPSB1.numbers));
        // System.out.println("     Median number of numbers are: " +       EPSB1.getMedian(  EPSB1.numbers));
        // System.out.println("     Mode number of numbers are: " +         EPSB1.getMode(    EPSB1.numbers));

        // System.out.println("SYMBOLS:");
        // System.out.println("     Minimum number of symbols are: " +      EPSB1.getMin(     EPSB1.symbols));
        // System.out.println("     Max number of symbols are: " +          EPSB1.getMax(     EPSB1.symbols));
        // System.out.printf ("     Average number of symbols are: %.2f\n", EPSB1.getAverage( EPSB1.symbols));
        // System.out.println("     Median number of symbols are: " +       EPSB1.getMedian(  EPSB1.symbols));
        // System.out.println("     Mode number of symbols are: " +         EPSB1.getMode(    EPSB1.symbols));

        // System.out.println("LENGTH:");
        // System.out.println("     Minimum length is: " +      EPSB1.getMin(     EPSB1.length));
        // System.out.println("     Max length is: " +          EPSB1.getMax(     EPSB1.length));
        // System.out.printf ("     Average length is: %.2f\n", EPSB1.getAverage( EPSB1.length));
        // System.out.println("     Median length is: " +       EPSB1.getMedian(  EPSB1.length));
        // System.out.println("     Mode length is: " +         EPSB1.getMode(    EPSB1.length));
    }
}
