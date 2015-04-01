#!/bin/bash
rm -rf doc
mkdir doc
javadoc ../src/ru/ifmo/ctddev/muratov/implementor/Implementor.java /home/amir/Documents/JavaHW/java-advanced-2015/java/info/kgeorgiy/java/advanced/implementor/Impler.java /home/amir/Documents/JavaHW/java-advanced-2015/java/info/kgeorgiy/java/advanced/implementor/JarImpler.java  /home/amir/Documents/JavaHW/java-advanced-2015/java/info/kgeorgiy/java/advanced/implementor/ImplerException.java  -d doc -private -linkoffline http://docs.oracle.com/javase/8/docs/api/ http://docs.oracle.com/javase/8/docs/api/