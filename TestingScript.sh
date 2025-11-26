#! /bin/bash

# Author(s): Dylan Lott & Brandon Weathers
# Date last updated:  11/25/2025 7:01 PM
# Simply a helper script that times each algorithm.

cd EPSB/
javac *.java
java Little_EPSB_CLI_Testing
cd ..

cd LCSM/
javac *.java
java Mini_LCSM_Testing
cd ..

cd SuffixTree
javac *.java
java TranslatedSuffixTreeTesting

cd ..
