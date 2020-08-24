# Text Editor in Java

This project was built as part of the Coursera course "Data Structures and Performance" by University of California San Diego. You can find this course [here](https://www.coursera.org/learn/data-structures-optimizing-performance?specialization=java-object-oriented).

![Text Editor Screenshot](/texteditor.png)

Below there is a short summary of how different concepts were covered and applying in the course.

## String Manipulation

In order to learn and apply this concept, the Flesch index calculation was implemented to the basic text editor.
The Flesch index express how easy or difficult a text is to read. High Flesch indexes (>90) indicate easy to read and low indexes (<30) indicate difficult to read.

Within Strings, regular expressions were utilized to split the text into words, sentences, and syllabes to then calculate the Flesch index score.

## Big O

This concept is covered to explain the importance in performance when developing applications. To visually represent that, a class DocumentBenchmarking was implemented to measure and compare the performance of the method to calculate the Flesch score implemented in the classes BasicDocument and EfficientDocument.

## Abstraction and Interfaces

Abstraction is specifically approached from the comparison between an Abstract Data Type (ADT) and a Data Structure.
The List interface is an example of ADT and will be used to create my own Linked List.

## Linked List

To learn the concept, I wrote my own Linked List. Other concept involved is testing on a data structure.
In the project, the Linked List is the data structure chosen to create a Markov Text Generator.

## Generics and Exceptions

Generics were the type parameter choice when writing the class ListNode<T>, that way the LinkedList can hold any type of data.
When writing classes, the designer has to expect the user to enter "bad input". To prevent the code from breaking, the designer should check for bad input and throw exceptions that stops normal program execution and indicate the user exactly where the program went wrong.

## Testing and Correctness

The course covers the importance of testing your code to increase confidence in the correctness of its functionality. Unit tests were written to validate the correctness of the text editor and its tools.



