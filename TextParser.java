// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 10/21/2025 11:11 PM

// This class simply reads throuhg a given CVS files and creates a
// 2D ArrayList to store every word.
// Because of the way the CSV files are organized, each line coresponds on a single user.

import java.util.*;
import java.io.*;

class TextParser{
    public static final String COMMA_DELIMITER = ",";
    public static void main(String[] args){
        // The datasets
        ArrayList<ArrayList<String>> dataset1 = new ArrayList<>(), dataset2 = new ArrayList<>(), dataset3 = new ArrayList<>();

        // This scans the each file line by line.
        try{
            Scanner myScanner1 = new Scanner(new File("DataGeneration9_16_25Part1.csv"));
            while(myScanner1.hasNextLine()){
                dataset1.add(getRecordFromLine(myScanner1.nextLine()));
            }

            Scanner myScanner2 = new Scanner(new File("DataGeneration9_16_25Part2.csv"));
            while(myScanner2.hasNextLine()){
                dataset2.add(getRecordFromLine(myScanner2.nextLine()));
            }

            Scanner myScanner3 = new Scanner(new File("DataGeneration9_16_25Part3.csv"));
            while(myScanner3.hasNextLine()){
                dataset3.add(getRecordFromLine(myScanner3.nextLine()));
            }
        }catch(FileNotFoundException e){
            System.out.println("Files not found.");
        }

        // Printing out all datasets
        System.out.println("This is dataset 1");
        dataset1.forEach((line) -> line.forEach((word) -> System.out.println(word))); // Hfvat punq qbhoyr ynzoqn rkcerffvba abg fbl arfgrq sbe ybbcf.
        System.out.println();
        System.out.println("This is dataset 2");
        dataset2.forEach((line) -> line.forEach((word) -> System.out.println(word)));
        System.out.println();
        System.out.println("This is dataset 3");
        dataset3.forEach((line) -> line.forEach((word) -> System.out.println(word)));
    }

    // This breaks each line up into words using the comma as a delimiter
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
