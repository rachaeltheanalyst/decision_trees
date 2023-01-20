# Decision Trees 

> This project creates a DecisionTree class that inherits from the BinaryTree class, and utilizes it to develop a program that plays a "20 Questions" style guessing game.

<a name="toc"/></a>
## Table of Contents

1. [Overview](#overview)

2. [Technologies](#technologies)

3. [Launch](#launch)

<a name="overview"/></a>
## 1. Overview
[Back to ToC](#toc)

This project creates a DecisionTree class that inherits from the BinaryTree<String> class, and utilizes it to develop a program that plays a "20 Questions" style guessing game.

For this project, the user playing the game will assist in creating the tree. Each time the program makes an incorrect guess, the tree will expand by one node, as demonstrated in the sample session below.
 
> $ java AnimalGuess  
> Think of an animal.  
> I'll try to guess it.  
> Is your animal a Mouse?  
> no  
> I got it wrong.  
> Please help me to learn.  
> What was your animal?  
> Crocodile  
> Type a yes or no question that would distinguish between a Crocodile and a Mouse  
> Is it a mammal?  
> Would you answer yes to this question for the Crocodile?  
> no  
> Play again?  
> yes  
> Think of an animal.  
> I''ll try to guess it.  
> Is it a mammal?  
> no  
> Is your animal a Crocodile?  
> yes  
> I guessed it!  
> Play again?  
> no  
> $  

The program trys to guess the animal by asking the questions in a stored decision tree, and following the appropriate branch for the answer given by the user. (Yes = left, no = right). The leaves will contain an animal to guess. If the system guesses wrong, it will ask the user to add a question distinguishing the wrong guess from the correct answer. This question will be used to extend the decision tree. The program read and write the decision tree from a text file, so that questions added during one session can be saved for later.

The program is organized into two classes: DecisionTree and AnimalGuess. The former is a data class to represent the knowledge used by the program, while the latter contains static methods (including main) that will run the game.

To allow for the program to remember what it learned between sessions. it reads a decision tree in from a text file named AnimalTree.txt when it starts up, and write it out again at the end.  The format of the AnimalTree.txt is one node per line, in breadth-first order. Each line contains the path string of the node, a space, and the node's value (either a question or an animal name). Here is a simple example:

> Is it a mammal?  
> Y Does it have hooves?  
> N Is it a reptile?  
> YY Does it give milk?  
> YN Is it a carnivore?  
> NY Crocodile  
> NN Mosquito  
> YYY Cow  
> YYN Horse  
> YNY Is it in the dog family?  
> YNN Mouse  
> YNYY Dog  
> YNYN Cat  

<a name="technologies"/></a>
## 2. Technologies
[Back to ToC](#toc)

java version "1.8.0_181"<br />
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)<br />
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)<br />

<a name="launch"/></a>
## 3. Launch
[Back to ToC](#toc)
```bash
javac -classpath .:target/dependency/* -d . $(find . -type f -name '*.java')

java AnimalGuess AnimalTree.txt
```
 
