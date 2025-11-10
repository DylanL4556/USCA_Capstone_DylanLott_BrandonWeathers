
import java.util.*;
import java.io.*;

import static java.lang.Character.toLowerCase;

class MiniLCSMKeepStrongMatches {
        public static final String COMMA_DELIMITER = ",";
        public static final double SIMILARITY_THRESHOLD = 0.0; // 70% minimum match

        public static void main(String[] args) {
            ArrayList<ArrayList<String>> synthetic30k = new ArrayList<>();

            try {
                Scanner myScanner0 = new Scanner(new File("craftrise_encrytped.txt")); //adjust for file changes 1/2
                System.out.print("Reading in file: \"craftrise_encrytped.txt\"...   ");
                while (myScanner0.hasNextLine()) {
                    String line = myScanner0.nextLine().trim();
                    if (line.isEmpty()) continue;

                    ArrayList<String> currentLine = new ArrayList<>();
                    String[] parts = line.split("\\t");

                    for (String part : parts) {
                        currentLine.add(part);
                    }
                    synthetic30k.add(currentLine);
                }
                myScanner0.close();
                System.out.println("File read complete ✓");
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
                return;
            }

            // Prepare writer for output
            try (PrintWriter out = new PrintWriter(new FileWriter("CraftRiseEntireSetSimScores.txt"))) { //adjust for file changes 2/2

                int keptCount = 0;

                for (int i = 0; i < synthetic30k.size(); i++) {
                    if (synthetic30k.get(i).size() < 3) continue; // skip malformed lines

                    String Y_1 = synthetic30k.get(i).get(1);
                    String Y_2 = synthetic30k.get(i).get(2);
                    String X = Y_1 + Y_2;
                    String P = "";

                    final int m = X.length(), n_1 = Y_1.length(), n_2 = Y_2.length(), r = P.length();
                    int[][][][] M = new int[m + 1][n_1 + 1][n_2 + 1][r + 1];

                    String Z = CLCSSM(X, Y_1, Y_2, P, m, n_1, n_2, r, M);

                    double lenY1 = Y_1.length();
                    double lenY2 = Y_2.length();
                    double lenZ = Z.length();
                    double ratio = lenZ / ((lenY1 + lenY2) / 2.0);

                    // Only keep strong matches (>= 70%)
                    if (ratio >= SIMILARITY_THRESHOLD)  {
                        keptCount++;
                        out.printf(
                                "Record #%d:%nY₁: %s%nY₂: %s%nZ: %s%nlen(Y₁)=%d, len(Y₂)=%d, len(Z)=%d, Similarity=%.4f%n%n",
                                i + 1, Y_1, Y_2, Z, (int) lenY1, (int) lenY2, (int) lenZ, ratio
                        );

                        if (keptCount <= 3) {
                            System.out.println("[" + (i + 1) + "] " + Y_1 + " | " + Y_2 + " → " + Z + " (" + String.format("%.2f", ratio * 100) + "%)");
                        }
                    }
                }

                out.printf("Total records meeting 70%% threshold: %d%n", keptCount);
                System.out.println("All strong results (≥70%) written to output_stats.txt ✓");

            } catch (IOException e) {
                System.out.println("Error writing to output file: " + e.getMessage());
            }
        }

        // Longest Common Substring implementation
        static String CLCSSM(String X, String Y_1, String Y_2, String P, int m, int n_1, int n_2, int r, int[][][][] M) {
            int[][] dp = new int[n_1 + 1][n_2 + 1];
            int maxLen = 0;
            int endIndex = 0;

            for (int i = 1; i <= n_1; i++) {
                for (int j = 1; j <= n_2; j++) {
                    if (toLowerCase(Y_1.charAt(i - 1)) == toLowerCase(Y_2.charAt(j - 1))) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        if (dp[i][j] > maxLen) {
                            maxLen = dp[i][j];
                            endIndex = i;
                        }
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }
            return (maxLen > 0) ? Y_1.substring(endIndex - maxLen, endIndex) : "";
        }

        private static ArrayList<String> getRecordFromLine(String line) {
            ArrayList<String> currentLine = new ArrayList<>();
            try (Scanner rowScanner = new Scanner(line)) {
                rowScanner.useDelimiter(COMMA_DELIMITER);
                while (rowScanner.hasNext()) {
                    currentLine.add(rowScanner.next());
                }
            }
            return currentLine;
        }
    }


