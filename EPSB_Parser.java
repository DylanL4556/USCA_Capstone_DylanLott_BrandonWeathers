// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 10/21/2025 12:12 NOON

import java.util.*;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Map;
//import java.util.*;
import java.io.*;

class EPSB_Parser{
    public static final String COMMA_DELIMITER = ",";
    public static void main(String[] args){
        // Parsing the CSV into the 2D linked list
        ArrayList<ArrayList<String>>
            dataset0 = new ArrayList<>(),
            dataset1 = new ArrayList<>(),
            dataset2 = new ArrayList<>(),
            dataset3 = new ArrayList<>();
        try{
            Scanner myScanner0 = new Scanner(new File("Synthetic300000PwPairsV2.csv"));
            System.out.print("Reading in file: \"Synthetic300000PwPairsV2.csv\"...   ");
            while(myScanner0.hasNextLine()){
                dataset0.add(getRecordFromLine(myScanner0.nextLine()));
            }
            System.out.println("File read complete ✓");
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        try{
            Scanner myScanner0 = new Scanner(new File("DataGeneration9_16_25Part1.csv"));
            System.out.print("Reading in file: \"DataGeneration9_16_25Part1.csv\"... ");
            while(myScanner0.hasNextLine()){
                dataset1.add(getRecordFromLine(myScanner0.nextLine()));
            }
            System.out.println("File read complete ✓");
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        try{
            Scanner myScanner0 = new Scanner(new File("DataGeneration9_16_25Part2.csv"));
            System.out.print("Reading in file: \"DataGeneration9_16_25Part2.csv\"... ");
            while(myScanner0.hasNextLine()){
                dataset2.add(getRecordFromLine(myScanner0.nextLine()));
            }
            System.out.println("File read complete ✓");
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        try{
            Scanner myScanner0 = new Scanner(new File("DataGeneration9_16_25Part3.csv"));
            System.out.print("Reading in file: \"DataGeneration9_16_25Part3.csv\"... ");
            while(myScanner0.hasNextLine()){
                dataset3.add(getRecordFromLine(myScanner0.nextLine()));
            }
            System.out.println("File read complete ✓");
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }

        // Adding all lines of the CSV to a coresponding EPSB, which then
        // does stastical analysis.
        ArrayList<EPSB> EPSBArrayList0 = new ArrayList<>();
        for(int index = 0; index < dataset0.size(); index++){
            EPSBArrayList0.add(new EPSB());
        }
        for(int index = 0; index < EPSBArrayList0.size(); index++){
            for(String currentPassword : dataset0.get(index)){
                EPSBArrayList0.get(index).addNewPassword(currentPassword);
            }
        }

        ArrayList<EPSB> EPSBArrayList1 = new ArrayList<>();
        for(int index = 0; index < dataset1.size(); index++){
            EPSBArrayList1.add(new EPSB());
        }
        for(int index = 0; index < EPSBArrayList1.size(); index++){
            for(String currentPassword : dataset1.get(index)){
                EPSBArrayList1.get(index).addNewPassword(currentPassword);
            }
        }

        ArrayList<EPSB> EPSBArrayList2 = new ArrayList<>();
        for(int index = 0; index < dataset2.size(); index++){
            EPSBArrayList2.add(new EPSB());
        }
        for(int index = 0; index < EPSBArrayList2.size(); index++){
            for(String currentPassword : dataset2.get(index)){
                EPSBArrayList2.get(index).addNewPassword(currentPassword);
            }
        }

        ArrayList<EPSB> EPSBArrayList3 = new ArrayList<>();
        for(int index = 0; index < dataset3.size(); index++){
            EPSBArrayList3.add(new EPSB());
        }
        for(int index = 0; index < EPSBArrayList3.size(); index++){
            for(String currentPassword : dataset3.get(index)){
                EPSBArrayList3.get(index).addNewPassword(currentPassword);
            }
        }

        while(true){
            System.out.println("=========================================================================");
            System.out.println("What file do you wish to analyse?");
            System.out.println("1. Synthetic300000PwPairsV2.csv");
            System.out.println("2. DataGeneration9_16_25Part1.csv");
            System.out.println("3. DataGeneration9_16_25Part2.csv");
            System.out.println("4. DataGeneration9_16_25Part3.csv");

            Scanner myScanner = new Scanner(System.in);
            int userChosenDataset = myScanner.nextInt();
            System.out.println("Which user do you wish to analyse?");
            int userNumber = myScanner.nextInt();
            userNumber--;

            switch(userChosenDataset){
                case 1:
                    getPasswordsStats(EPSBArrayList0.get(userNumber));
                    System.out.println("Press any key to continue.");
                    break;
                case 2:
                    getPasswordsStats(EPSBArrayList1.get(userNumber));
                    System.out.println("Press any key to continue.");
                    break;
                case 3:
                    getPasswordsStats(EPSBArrayList2.get(userNumber));
                    System.out.println("Press any key to continue.");
                    break;
                case 4:
                    getPasswordsStats(EPSBArrayList3.get(userNumber));
                    System.out.println("Press any key to continue.");
                    break;
                default:
                    System.out.println("Please select a valid dataset.");
            }
            String waitingForUser = myScanner.nextLine();
            String waitingForUser1 = myScanner.nextLine();
            System.out.println("\033[H\033[2J");
            // System.out.flush();
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
