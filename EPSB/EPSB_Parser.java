// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 11/1/2025  5:55 PM

import java.util.*;
import java.io.*;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Map;

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
            Scanner myScanner0 = new Scanner(new File("../Datasets/Synthetic300000PwPairsV2.csv"));
            System.out.print("Reading in file: \"Synthetic300000PwPairsV2.csv\"...   ");
            while(myScanner0.hasNextLine()){
                dataset0.add(getRecordFromLine(myScanner0.nextLine()));
            }
            System.out.println("File read complete ✓");
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        try{
            Scanner myScanner0 = new Scanner(new File("../Datasets/DataGeneration9_16_25Part1.csv"));
            System.out.print("Reading in file: \"DataGeneration9_16_25Part1.csv\"... ");
            while(myScanner0.hasNextLine()){
                dataset1.add(getRecordFromLine(myScanner0.nextLine()));
            }
            System.out.println("File read complete ✓");
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        try{
            Scanner myScanner0 = new Scanner(new File("../Datasets/DataGeneration9_16_25Part2.csv"));
            System.out.print("Reading in file: \"DataGeneration9_16_25Part2.csv\"... ");
            while(myScanner0.hasNextLine()){
                dataset2.add(getRecordFromLine(myScanner0.nextLine()));
            }
            System.out.println("File read complete ✓");
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        try{
            Scanner myScanner0 = new Scanner(new File("../Datasets/DataGeneration9_16_25Part3.csv"));
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
                    EPSBArrayList0.get(userNumber).getInfo();
                    break;
                case 2:
                    EPSBArrayList1.get(userNumber).getInfo();
                    break;
                case 3:
                    EPSBArrayList2.get(userNumber).getInfo();
                    break;
                case 4:
                    EPSBArrayList3.get(userNumber).getInfo();
                    break;
                default:
                    System.out.println("Please select a valid dataset.");
            }
            System.out.println("Press any key to continue.");
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
}
