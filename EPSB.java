// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 10/20/2025 10:13 PM

// I have created a class that handles the technical parsing and calculation.
// I made this disccision so that I can use a single object in both a CLI and GUI versions.

// I have changed this class so that it also keeps tracks of all letters and the
// length of the new passwords as per the paper instructs.
import java.util.*;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Map;

class EPSB{
    public ArrayList<String> passwords;
    public ArrayList<Integer> capitals, lowerCase, letters, numbers, symbols, length;

    public EPSB(){
        passwords = new ArrayList<String>();
        capitals  = new ArrayList<Integer>();
        lowerCase = new ArrayList<Integer>();
        letters   = new ArrayList<Integer>();
        numbers   = new ArrayList<Integer>();
        symbols   = new ArrayList<Integer>();
        length    = new ArrayList<Integer>();
    }

    void addNewPassword(String newPassword){
        passwords .add(newPassword);
        capitals  .add((Integer) countCapitalsInWord(newPassword));
        lowerCase .add((Integer) countLowerCaseInWord(newPassword));
        letters   .add((Integer) countCapitalsInWord(newPassword) + countLowerCaseInWord(newPassword));
        numbers   .add((Integer) countDigits(newPassword));
        symbols   .add((Integer) countSpecialCharacters(newPassword));
        length    .add((Integer) newPassword.length());
    }

    int countCapitalsInWord(String currentPassword){
        int totalCapitalsInWord = 0;
        for(int index = 0; index < currentPassword.length(); index++)
            if(Pattern.matches("[A-Z]", currentPassword.substring(index, index+1))) totalCapitalsInWord++;
        return totalCapitalsInWord;
    }

    int countLowerCaseInWord(String currentPassword){
        int totalLowerCaseInWord = 0;
        for(int index = 0; index < currentPassword.length(); index++)
            if(Pattern.matches("[a-z]", currentPassword.substring(index, index+1))) totalLowerCaseInWord++;
        return totalLowerCaseInWord;
    }

    int countDigits(String currentPassword){
        int totalcountDigits = 0;
        for(int index = 0; index < currentPassword.length(); index++)
            if(Pattern.matches("[0-9]", currentPassword.substring(index, index+1))) totalcountDigits++;
        return totalcountDigits;
    }

    int countSpecialCharacters(String currentPassword){
        int totalSpecialCharacters= 0;
        for(int index = 0; index < currentPassword.length(); index++)
            // if(Pattern.matches("[^A-Za-z0-9]", currentPassword.substring(index, index+1))) totalSpecialCharacters++;
            // Better regex, I think
            if(Pattern.matches("[\\p{P}\\p{S}]", currentPassword.substring(index, index+1))) totalSpecialCharacters++;
        return totalSpecialCharacters;
    }

    int getMin(ArrayList<Integer> list){
        int min = list.get(0);
        for(int index = 0; index < list.size(); index++)
            if(list.get(index).intValue() < min) min = list.get(index);
        return min;
    }

    int getMax(ArrayList<Integer> list){
        int max = list.get(0);
        for(int index = 0; index < list.size(); index++)
            if(list.get(index).intValue() > max) max = list.get(index);
        return max;
    }

    double getAverage(ArrayList<Integer> list){
        double average = 0;
        for(int index = 0; index < list.size(); index++)
            average += list.get(index);
        return average/list.size();
    }

    int getMedian(ArrayList<Integer> list){
       return list.get(list.size()/2);
    }

    int getMode(ArrayList<Integer> list){
        HashMap<Integer, Integer> freqMap = new HashMap<Integer, Integer>();

        for(Integer index : list)
            freqMap.put(index, freqMap.getOrDefault(index, 0) + 1);

        int maxCount = 0, mode = -1;
        for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()){
            if(entry.getValue() > maxCount){
                maxCount = entry.getValue();
                mode = entry.getKey();
            }
        }
        return mode;
    }
}
