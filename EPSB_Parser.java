// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 10/20/2025 10:23 PM

import java.util.*;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Map;

import java.util.*;
import java.io.*;

class EPSB_Parser{
    public static final String COMMA_DELIMITER = ",";
    public static void main(String[] args){
        // Parsing the CSV into the 2D linked list
        ArrayList<ArrayList<String>> dataset1 = new ArrayList<>(), dataset2 = new ArrayList<>(), dataset3 = new ArrayList<>();
        try{
            Scanner myScanner1 = new Scanner(new File("Synthetic300000PwPairsV2.csv"));
            while(myScanner1.hasNextLine()){
                dataset1.add(getRecordFromLine(myScanner1.nextLine()));
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }

        // Adding all lines of the CSV to a coresponding EPSB, which then
        // does stastical analysis.
        ArrayList<EPSB> EPSBArrayList = new ArrayList<>();
        for(int index = 0; index < 20; index++){
            EPSBArrayList.add(new EPSB());
        }

        for(int index = 0; index < EPSBArrayList.size(); index++){
            for(String currentPassword : dataset1.get(index)){
                EPSBArrayList.get(index).addNewPassword(currentPassword);
            }
        }

        while(true){
            Scanner myScanner = new Scanner(System.in);
            System.out.println("Which user do you wish to analyse?");
            int userNumber = myScanner.nextInt();
            userNumber--;
            getPasswordsStats(EPSBArrayList.get(userNumber));

            System.out.println();
            System.out.println("Press any key to continue.");
            String waitingForUser = myScanner.nextLine();
            String waitingForUser1 = myScanner.nextLine();
            System.out.println("\033[H\033[2J");
            System.out.flush();
        }

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

    private static void getPasswordsStats(EPSB EPSBInput){
        System.out.print("The user's passwords are: ");
        EPSBInput.passwords.forEach((password) -> System.out.print(password + " "));

        System.out.println();
        System.out.println();

        System.out.println("CAPITALS:");
        System.out.println("     Minimum number of capitals are: " +       EPSBInput.getMin(     EPSBInput.capitals));
        System.out.println("     Max number of capitals are: " +           EPSBInput.getMax(     EPSBInput.capitals));
        System.out.printf ("     Average number of capitals are: %.2f\n",  EPSBInput.getAverage( EPSBInput.capitals));
        System.out.println("     Median number of capitals are: " +        EPSBInput.getMedian(  EPSBInput.capitals));
        System.out.println("     Mode number of capitals are: " +          EPSBInput.getMode(    EPSBInput.capitals));

        System.out.println("LOWER CASE:");
        System.out.println("     Minimum number of lower case letters are: " +      EPSBInput.getMin(     EPSBInput.lowerCase));
        System.out.println("     Max number of lower case letters are: " +          EPSBInput.getMax(     EPSBInput.lowerCase));
        System.out.printf ("     Average number of lower case letters are: %.2f\n", EPSBInput.getAverage( EPSBInput.lowerCase));
        System.out.println("     Median number of lower case letters are: " +       EPSBInput.getMedian(  EPSBInput.lowerCase));
        System.out.println("     Mode number of lower case letters are: " +         EPSBInput.getMode(    EPSBInput.lowerCase));

        System.out.println("LETTERS:");
        System.out.println("     Minimum number of letters are: " +      EPSBInput.getMin(     EPSBInput.letters));
        System.out.println("     Max number of letters are: " +          EPSBInput.getMax(     EPSBInput.letters));
        System.out.printf ("     Average number of letters are: %.2f\n", EPSBInput.getAverage( EPSBInput.letters));
        System.out.println("     Median number of letters are: " +       EPSBInput.getMedian(  EPSBInput.letters));
        System.out.println("     Mode number of letters are: " +         EPSBInput.getMode(    EPSBInput.letters));

        System.out.println("NUMBERS:");
        System.out.println("     Minimum number of numbers are: " +      EPSBInput.getMin(     EPSBInput.numbers));
        System.out.println("     Max number of numbers are: " +          EPSBInput.getMax(     EPSBInput.numbers));
        System.out.printf ("     Average number of numbers are: %.2f\n", EPSBInput.getAverage( EPSBInput.numbers));
        System.out.println("     Median number of numbers are: " +       EPSBInput.getMedian(  EPSBInput.numbers));
        System.out.println("     Mode number of numbers are: " +         EPSBInput.getMode(    EPSBInput.numbers));

        System.out.println("SYMBOLS:");
        System.out.println("     Minimum number of symbols are: " +      EPSBInput.getMin(     EPSBInput.symbols));
        System.out.println("     Max number of symbols are: " +          EPSBInput.getMax(     EPSBInput.symbols));
        System.out.printf ("     Average number of symbols are: %.2f\n", EPSBInput.getAverage( EPSBInput.symbols));
        System.out.println("     Median number of symbols are: " +       EPSBInput.getMedian(  EPSBInput.symbols));
        System.out.println("     Mode number of symbols are: " +         EPSBInput.getMode(    EPSBInput.symbols));

        System.out.println("LENGTH:");
        System.out.println("     Minimum length is: " +      EPSBInput.getMin(     EPSBInput.length));
        System.out.println("     Max length is: " +          EPSBInput.getMax(     EPSBInput.length));
        System.out.printf ("     Average length is: %.2f\n", EPSBInput.getAverage( EPSBInput.length));
        System.out.println("     Median length is: " +       EPSBInput.getMedian(  EPSBInput.length));
        System.out.println("     Mode length is: " +         EPSBInput.getMode(    EPSBInput.length));
    }
}
