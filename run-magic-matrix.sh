#!/bin/bash
find -name "*.java" > source.txt
javac -d build @source.txt
cd build
java samples/MagicMatrix $1