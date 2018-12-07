CS542: Assignment 5

Name: Akshata Dabade

Following are the commands and the instructions to run ANT on the project.
This assignment's submission is my own work and I did not discuss with any other past or current student, nor did I have access to a previous submission of this assignment by another student.

Note: build.xml is present in genericCheckpointing/src folder.
Instruction to clean:
(When ran from src folder) ####Command: ant -buildfile build.xml clean

Description: It cleans up all the .class files that were generated during compilation.

Instruction to compile:
####Command: ant -buildfile build.xml all

Description: Compiles the code and generates .class files inside the BUILD folder.

Instruction to run:
####Command: ant -buildfile build.xml run -Darg0=serdeser -Darg1=3 -Darg2=checkpoint.txt



Description: 
The code has implemented to serialize and deserialize objects of two different classes to/from XML like format input file. Design patterns such as Dynamic proxy, Iterartor, Visitor, Momento and Decprator are implemented for this assignment.

Academic Honesty statement:
"I have done this assignment completely on my own. I have not copied it, nor have I given my solution to anyone else. I understand that if I am involved in plagiarism or cheating an official form will be submitted to the Academic Honesty Committee of the Watson School to determine the action that needs to be taken."

Date: -- Dec' 07 2018

Data structure used in this assignment is mainly Arraylist which is used to store the SerializableObject type. I prefered arraylist for the following reasons: ArrayList is implemented as a resizable array. As more elements are added to ArrayList, its size is increased dynamically.Time complexity of adding element in arraylist is still O(1)) in most cases,(O(N)) sometimes.