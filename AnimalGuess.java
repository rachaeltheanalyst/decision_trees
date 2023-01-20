import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *  Class that runs the guessing game.
 */
public class AnimalGuess {
    /**
     *  Main method to run the guessing game
     */
    public static void main(String[] args) {
      String fileName = args[0] != null? args[0]: "AnimalTree.txt";
      DecisionTree root = new DecisionTree("");
      root.readTreeFromFile(fileName);
      //If system has no prior knowledge, set a starting point
      if (root.getData().length()==0) {
          root.setData("Tiger");
      }
      
      DecisionTree initialTree = null;
      do {
        initialTree = root;

        System.out.println("Think of an animal.");
        System.out.println("I'll try to guess it.");

        // player makes a guess
        boolean isLeft = false; // save last path
        DecisionTree lastBranch = null;
        // going through the branches
        while (initialTree.isBranch() ) {
          boolean answer = getYesNoAnswer(initialTree.getData());
          lastBranch = initialTree;
          if (answer) {
            initialTree = initialTree.getLeft();
            isLeft = true;
          } else {
            initialTree = initialTree.getRight();
            isLeft = false;
          }
        }
        // we reach a leaf
        if (getYesNoAnswer("Is your animal a "+ initialTree.getData() + "?")) {
        System.out.println("I guessed it!");
        } else {
          System.out.println("I got it wrong.");
          System.out.println("Please help me to learn.");

          String newAnimalStr = getAnswer("What was your animal?");
          DecisionTree newAnimal = new DecisionTree(newAnimalStr);
          // get a question from user
          String questionStr = getAnswer( "Type a yes or no question that would distinguish between a "+ initialTree.getData() + " and a " + newAnimal.getData()+ " ?");
          // get a yes/no answer from user for the question
          boolean isYes = getYesNoAnswer("Would you answer yes to this question for the "+ newAnimal.getData() + " ?");
          DecisionTree newNode = null;
          // create a new node based on user's input
          if (isYes) {
            newNode = new DecisionTree(questionStr, newAnimal, initialTree);
          } else {
            newNode = new DecisionTree(questionStr, initialTree, newAnimal);
          }
          // replace original node with the new node
          if (root.count() == 1 ) {
            root = newNode;
          } else {
            if (isLeft) {
              lastBranch.setLeft(newNode);
            } else {
              lastBranch.setRight(newNode);
            }
          }
        }
        //System.out.println(DecisionTree.preorderString(root)); // remove it when done
      } while (getYesNoAnswer("Play again?"));

        //write the tree to file
        root.writeToFile(fileName);
    }

    /**
     *  Elicit input from the user
     *  @param question the question for the user
     *  @return String answer for the question
     */
    private static String getAnswer(String question) {
      System.out.println(question);
      Scanner scanner = new Scanner(new InputStreamReader(System.in));
      String input = scanner.nextLine();
      while (true) {
        if (input != null && input.trim().length() != 0 && input.matches("[a-zA-Z0-9? ]+$")) {
          return input;
        } else {
          System.out.println(question);
          input = scanner.nextLine();
        }
      }
    }

    /**
     *  Elicit a yes or no answer from the user
     *  @param question the question to ask
     *  @return boolean true if answer is yes, otherwise false
     */
    private static boolean getYesNoAnswer(String question) {
      System.out.println(question);

      Scanner scanner = new Scanner(new InputStreamReader(System.in));
      String input = scanner.nextLine();
      while (true) {
        if (input != null && input.trim().length() != 0 && (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y"))) {
          return true;
        } else if (input != null && input.trim().length() != 0 &&(input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n"))) {
          return false;
        } else {
          System.out.println("Would you answer yes or no?");
          input = scanner.nextLine();
        }
      }
    }
}
