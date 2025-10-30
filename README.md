# Welcome to Our 2025-26 USCA Capstone Project
Advisor: Dr. Yilian Zhang
<br>
Students: Dylan Lott & Brandon Weathers
## Project Description 
Modern technology allows the protection of sensitive information.​ Naturally, the presence of value leads to people with malicious intent.​ Currently, the file EPSB contains methods used to calculate the CR for a given set of passwords that all belong to a single user. This is a college project and is not intended for serious use.
<br>
<br>
**Our Research Goal:**
<br>
Develop an algorithm to detect malicious users using various metrics including the longest common substring for multiple strings algorithm.​
## How to Run
1. Fork this repo.
2. cd /USCA_Capstone_DylanLott_BrandonWeathers 
3. Run: "javac *.java"
4. Run: "java EPSB_Parser"
5. When prompted, input the database which you wish to analyse.
6. When prompted, input the user number you wish to analyse.
7. After you are finished viewing desired info, press any key to continue.
8. If you want to continue querying, return to step 5.

## Runing Longest Common Substring
*This documentation may be out of date.*
This feature is still in development and doens't work properly. 
The testing instructions are as follows.
1. Fork this repo.
2. cd /USCA_Capstone_DylanLott_BrandonWeathers/LCSM
3. Run: "javac *.java"
4. Run: "java LCSM"
5. Provide your first string.
6. Provide the same string *again*.
7. Provide your next string.
8. Press Enter to submit an empty string as P.

## How to Test the Algirhtm's Speed on Different Machines
I relised that on some machines the different algorithms dont' scale quite right.
The following instructions use the the time command.
This is a command which is on nearly all Linux systems.
I'm not sure of the Windows equivalent.
1. Fork this repo.
2. cd /USCA_Capstone_DylanLott_BrandonWeathers/LCSM
3. Compile all files ("javac *.java")
4. Run: time Little_EPSB_CLI_Testing java ; time java Mini_LCSM_Testing; 
5. The first set of measurements that appears is the EPSB algorithm, the second set of measurements is the new LCSM algorithm. All the measurements are is seconds.

## Contacts
| Contributer      | Email            | 
|------------------|------------------|
| Yilian Zhang     | YilianZ@usca.edu |
| Dylan Lott       | drlott@usca.edu  |
| Brandon Weathers | brw12@usca.edu   |
