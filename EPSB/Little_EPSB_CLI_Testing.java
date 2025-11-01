// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 11/1/2025 5:45 PM

// Just for testing

import java.util.*;
import java.io.*;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Map;

public class Little_EPSB_CLI_Testing{
    public static void main (String[] args){
        Scanner ob = new Scanner(System.in);
        String currentPassword1 = "Aa1!*";
        EPSB EPSB1 = new EPSB();

        long startTime = System.nanoTime();
        for(int index = 0; index < 100; index++)
                EPSB1.addNewPassword(currentPassword1);
        long endTime = System.nanoTime();
        EPSB1.getInfoTesting();
        System.out.println("The EPSB algorithm takes " + (endTime-startTime)/1000000 + " milisecond(s) for 100 entries.");
    }
}
