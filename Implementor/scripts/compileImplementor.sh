#!/bin/bash
rm -fr tmp
mkdir tmp
cp ../out/production/Implementor/ru tmp -r
cp META-INF/ tmp -r
cp /home/amir/Documents/JavaHW/java-advanced-2015/artifacts/ImplementorTest.jar tmp -r
cd tmp
jar cfm Implementor.jar META-INF/MANIFEST.MF ru
java -jar Implementor.jar java.util.NavigableSet NavigableSet.java
