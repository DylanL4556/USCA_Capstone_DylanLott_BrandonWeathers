// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 11/1/2025  4:03 PM

// I have created a class that handles the technical parsing and calculation.
// I made this disccision so that I can use a single object in both a CLI and GUI versions.

// I have changed this class so that it also keeps tracks of all letters and the
// length of the new passwords as per the paper instructs.S

// I have squeezed out some more preformance by saving time by not recalculating digit count or min or max values.
import java.util.*;
import java.util.regex.*;
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
        letters   .add((Integer) capitals.get(lowerCase.size()-1) + lowerCase.get(lowerCase.size()-1));
        numbers   .add((Integer) countDigits(newPassword));
        length    .add((Integer) newPassword.length());
        symbols   .add((Integer) length.get(length.size()-1) - (letters.get(letters.size()-1) + numbers.get(numbers.size()-1)));

        capitals.sort(null);
        lowerCase.sort(null);
        letters.sort(null);
        numbers.sort(null);
        symbols.sort(null);
        length.sort(null);
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

    double getAverage(ArrayList<Integer> list){
        double average = 0;
        for(Integer currentElement : list)
            average += currentElement.intValue();
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

    void getInfo(){
        System.out.println("The current passwords are: ");
        this.passwords.forEach((currentPassword) -> {System.out.print(currentPassword + ", ");});
        System.out.println();
        System.out.println("CAPITALS:");
        System.out.println("     Minimum number of capitals are: " +                this.capitals.get(  0));
        System.out.println("     Max number of capitals are: " +                    this.capitals.get(  this.capitals.size()-1));
        System.out.printf ("     Average number of capitals are: %.2f\n",           this.getAverage(    this.capitals));
        System.out.println("     Median number of capitals are: " +                 this.getMedian(     this.capitals));
        System.out.println("     Mode number of capitals are: " +                   this.getMode(       this.capitals));

        System.out.println("LOWER CASE:");
        System.out.println("     Minimum number of lower case letters are: " +      this.lowerCase.get( 0));
        System.out.println("     Max number of lower case letters are: " +          this.lowerCase.get( this.lowerCase.size()-1));
        System.out.printf ("     Average number of lower case letters are: %.2f\n", this.getAverage(    this.lowerCase));
        System.out.println("     Median number of lower case letters are: " +       this.getMedian(     this.lowerCase));
        System.out.println("     Mode number of lower case letters are: " +         this.getMode(       this.lowerCase));

        System.out.println("LETTERS:");
        System.out.println("     Minimum number of letters are: " +                 this.letters.get(   0));
        System.out.println("     Max number of letters are: " +                     this.letters.get(   this.letters.size()-1));
        System.out.printf ("     Average number of letters are: %.2f\n",            this.getAverage(    this.letters));
        System.out.println("     Median number of letters are: " +                  this.getMedian(     this.letters));
        System.out.println("     Mode number of letters are: " +                    this.getMode(       this.letters));

        System.out.println("NUMBERS:");
        System.out.println("     Minimum number of numbers are: " +                 this.numbers.get(    0));
        System.out.println("     Max number of numbers are: " +                     this.numbers.get(    this.numbers.size()-1));
        System.out.printf ("     Average number of numbers are: %.2f\n",            this.getAverage(     this.numbers));
        System.out.println("     Median number of numbers are: " +                  this.getMedian(      this.numbers));
        System.out.println("     Mode number of numbers are: " +                    this.getMode(        this.numbers));

        System.out.println("SYMBOLS:");
        System.out.println("     Minimum number of symbols are: " +                 this.symbols.get(    0));
        System.out.println("     Max number of symbols are: " +                     this.symbols.get(    this.symbols.size()-1));
        System.out.printf ("     Average number of symbols are: %.2f\n",            this.getAverage(     this.symbols));
        System.out.println("     Median number of symbols are: " +                  this.getMedian(      this.symbols));
        System.out.println("     Mode number of symbols are: " +                    this.getMode(        this.symbols));

        System.out.println("LENGTH:");
        System.out.println("     Minimum length is: " +                             this.length.get(     0));
        System.out.println("     Max length is: " +                                 this.length.get(     this.length.size()-1));
        System.out.printf ("     Average length is: %.2f\n",                        this.getAverage(     this.length));
        System.out.println("     Median length is: " +                              this.getMedian(      this.length));
        System.out.println("     Mode length is: " +                                this.getMode(        this.length));
    }

    void getInfoTesting(){
        this.capitals.get(  0);
        this.capitals.get(  this.capitals.size()-1);
        this.getAverage(    this.capitals);
        this.getMedian(     this.capitals);
        this.getMode(       this.capitals);

        this.lowerCase.get( 0);
        this.lowerCase.get( this.lowerCase.size()-1);
        this.getAverage(    this.lowerCase);
        this.getMedian(     this.lowerCase);
        this.getMode(       this.lowerCase);

        this.letters.get(   0);
        this.letters.get(   this.letters.size()-1);
        this.getAverage(    this.letters);
        this.getMedian(     this.letters);
        this.getMode(       this.letters);

        this.numbers.get(   0);
        this.numbers.get(   this.numbers.size()-1);
        this.getAverage(    this.numbers);
        this.getMedian(     this.numbers);
        this.getMode(       this.numbers);

        this.symbols.get(   0);
        this.symbols.get(   this.symbols.size()-1);
        this.getAverage(    this.symbols);
        this.getMedian(     this.symbols);
        this.getMode(       this.symbols);

        this.length.get(    0);
        this.length.get(    this.length.size()-1);
        this.getAverage(    this.length);
        this.getMedian(     this.length);
        this.getMode(       this.length);
    }
}
