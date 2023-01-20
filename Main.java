/**
 *  Program that guesses an animal that a user is thinking of and 
 *  learns after each play
 */

/**
 *  Class that tests the function in DecisionTree
 *
 */
class Main {
  public static void main(String[] args) {
    DecisionTree sampleTree = buildSampleDecisionTree();
    DecisionTree treeFromFile = sampleTree.readTreeFromFile("AnimalTree.txt");
    System.out.println(DecisionTree.preorderString(treeFromFile));
  }

  /**
   *  Method to test FollowPath
   *  @param sampleTree object of DecisionTree
   */
  private static void testFollowPath(DecisionTree sampleTree) {
    DecisionTree decisionFound = sampleTree.followPath("YNN");
    if (decisionFound!=null) {
      System.out.println(decisionFound.getData());
    } else {
      System.out.println("No decision found by following the path");
    }
  }

  /**
   *  Method to build a sample DecisionTree
   *  @return the object of DecisionTree
   */
  private static DecisionTree buildSampleDecisionTree() {
    DecisionTree cow = new DecisionTree("Cow");
    DecisionTree horse = new DecisionTree("Horse");
    DecisionTree dog = new DecisionTree("Dog");
    DecisionTree cat = new DecisionTree("Cat");
    DecisionTree mouse = new DecisionTree("Mouse");
    DecisionTree crocodile = new DecisionTree("Crocodile");
    DecisionTree mosquito = new DecisionTree("Mosquito");
    DecisionTree dogFamily = new DecisionTree("Is it in the dog family?");
    dogFamily.setLeft(dog);
    dogFamily.setRight(cat);
    DecisionTree ifCarnivore = new DecisionTree("Is it a carnivore?");
    ifCarnivore.setLeft(dogFamily);
    ifCarnivore.setRight(mouse);
    DecisionTree giveMilk = new DecisionTree("Does it give milk?");
    giveMilk.setLeft(cow);
    giveMilk.setRight(horse);
    DecisionTree ifReptile = new DecisionTree("Is it a reptile?");
    ifReptile.setLeft(crocodile);
    ifReptile.setRight(mosquito);
    DecisionTree haveHooves = new DecisionTree("Does it have hooves?");
    haveHooves.setLeft(giveMilk);
    haveHooves.setRight(ifCarnivore);
    DecisionTree decisionTree = new DecisionTree("Is it a mammal?");
    decisionTree.setLeft(haveHooves);
    decisionTree.setRight(ifReptile);
    return decisionTree;
  }
}

