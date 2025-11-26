// Authors: Dylan Lott & Brandon Weathers
// Date last updated:  11/25/2025 7:01 PM

class TranslatedSuffixTreeTesting{
    public static void main(String[] args){
        long startTime = System.nanoTime();
        for(int index = 0; index < 100; index++){
            TranslatedSuffixTree tree = new TranslatedSuffixTree();
            tree.size1 = 7;
            tree.setInputString("xancplucaskai99#ancplucaskai997$");
            tree.buildSuffixTree();
            tree.getLongestCommonSubstringTesting();
        }
        long endTime = System.nanoTime();
        System.out.println("The suffix tree algorithm takes " + (endTime-startTime)/1000000 + " milisecond(s) for 2 entrie(s) 100 times.");
    }
}
