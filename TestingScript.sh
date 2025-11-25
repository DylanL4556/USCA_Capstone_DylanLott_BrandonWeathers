#! /bin/bash

# Author(s): Dylan Lott & Brandon Weathers
# Last updated: 11/1/2025 5:44 PM
# Simply a helper script that times each algorithm.

cd EPSB/
javac *.java
java Little_EPSB_CLI_Testing

cd ..
cd LCSM
javac *.java
java Mini_LCSM_Testing

cd ..
cd SuffixTree
javac *.java
java TranslatedSuffixTreeTesting

cd ..
