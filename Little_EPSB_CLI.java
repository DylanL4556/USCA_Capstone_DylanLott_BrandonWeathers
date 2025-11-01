// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 10/28/2025 11:37 PM

// CONCEPT
// Step 1: Prompt the user for a password.
// Step 2: Count how many of the following characters: capitals, lower case, numbers, symbols.
// Step 3: For each of these characters, add them to a coresponding linked list.
// Step 4: Update the min, max, mean, median, and mode.
// Step 4: Go to step 1.

// I have created a class that handles the technical parsing and calculation.
// I made this disccision so that I can use a single object in both a CLI and GUI versions.

// I have implemented the display of stats pertaining to all letters and
// the length of the new password as the paper instructs.

// This is just a test for adding a commit from a school owned Windows machine.

import java.util.*;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Map;

public class Little_EPSB_CLI{
    public static void main (String[] args){
        Scanner ob = new Scanner(System.in);
        String currentPassword;

        EPSB EPSB1 = new EPSB();
        while(true){
            System.out.println("Please enter a password: ");
            currentPassword = ob.nextLine();

            System.out.println("\033[H\033[2J");
            System.out.flush();

            EPSB1.addNewPassword(currentPassword);

        }
    }
}
