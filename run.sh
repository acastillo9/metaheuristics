#!/bin/bash
find -name "*.java" > source.txt
javac -d build @source.txt
cd build
java Main $1