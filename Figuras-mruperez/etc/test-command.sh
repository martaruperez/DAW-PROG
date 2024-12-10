#!/bin/bash

#java -jar target/ProyectoBase-*.jar arg1 arg2
mvn clean test site 
mvn exec:java -Dexec.mainClass=com.gsd.daw.prog.App -Dexec.args="arg1 arg2 ..." 