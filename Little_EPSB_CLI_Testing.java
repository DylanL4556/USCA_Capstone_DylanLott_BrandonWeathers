// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 10/30/2025 12:16 AM

// Just for testing

import java.util.*;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Map;

public class Little_EPSB_CLI_Testing{
    public static void main (String[] args){
        Scanner ob = new Scanner(System.in);
        String currentPassword1 = "Ancplucaskai9?";
        String currentPassword2 = "Ancplucaskai99?";
        String currentPassword3 = "Ancplucaskai999?";
        EPSB EPSB1 = new EPSB();

        EPSB1.addNewPassword(currentPassword1);
        EPSB1.addNewPassword(currentPassword2);
        EPSB1.addNewPassword(currentPassword3);
        EPSB1.getInfo();
    }
}
