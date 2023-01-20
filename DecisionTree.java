import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

/**
 *  Implements a binary decision tree
 *
 */
public class DecisionTree extends BinaryTree<String> {
  /** leaf constructor */
  public DecisionTree(String s) {
    super(s);
  }

  /** branch node constructor */
  public DecisionTree(String data, BinaryTree<String> left, BinaryTree<String> right) {
    super(data, left, right);
  }

  /** constructor that makes a deep copy of the entire tree structure */
  public DecisionTree(BinaryTree<String> tree) {
    super(tree);
  }

  /** @override */
  /** Accessor for left child */
  public DecisionTree getLeft() {
    return (DecisionTree)super.getLeft();
  }

  /** @override */
  /** Accessor for right child */
  public DecisionTree getRight() {
    return (DecisionTree)super.getRight();
  }

  /** @override */
  /** Manipulator for left child */
  public void setLeft(BinaryTree<String> left) {
    if ((left==null)||(left instanceof DecisionTree)) {
      super.setLeft(left);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /** @override */
  /** Manipulator for right child */
  public void setRight(BinaryTree<String> right) {
    if ((right==null)||(right instanceof DecisionTree)) {
      super.setRight(right);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /** Determines whether a tree is empty */
  public static boolean isEmpty(DecisionTree node) {
      return (node == null);
  }

  /** Creates a string representation of preorder traversal */
  public static <T> String preorderString(DecisionTree t) {
    if (t==null) {
      return "";
    } else {
      return "("+t.getData()+" "+preorderString(t.getLeft())+" "+preorderString(t.getRight())+")";
    }
  }

  /** Creates a string representation of inorder traversal */
  public static <T> String inorderString(DecisionTree t) {
    if (t==null) {
      return "";
    } else {
      return "("+inorderString(t.getLeft())+" "+t.getData()+" "+inorderString(t.getRight())+")";
    }
  }

  /** Creates a string representation of postorder traversal */
  public static <T> String postorderString(DecisionTree t) {
    if (t==null) {
      return "";
    } else {
      return "("+postorderString(t.getLeft())+" "+postorderString(t.getRight())+" "+t.getData()+")";
    }
  }

  /**
   *  Accept a String of path and use it to trace a path through the tree from the root
   *  @param pathString String of path
   *  @return the node reached after all the characters in the input string have been followed
   */
  public DecisionTree followPath(String pathString) {

    DecisionTree currentNode=this;
    char[] pathCharArray = pathString.toCharArray();

    //loop through the path characters
    for (int i=0; i<pathCharArray.length; i++){
      //to follow the left child
      if (pathCharArray[i]=='Y') {
        currentNode = currentNode.getLeft();
      //to follow the right child
      } else if (pathCharArray[i]=='N') {
        currentNode = currentNode.getRight();
      }
    }
    //return the node that is reached
    return currentNode;
  }

  /**
   *  Method to write the tree to a file
   *  @param fileName String Name of the file
   */
  public void writeToFile(String fileName) {
    PrintWriter out=null;
    try {
      out = new PrintWriter(new FileWriter(fileName));
      //queue of nodes to be visited
      Queue<DecisionTree> nodeQueue = new LinkedList<DecisionTree>();
      //queue holding their paths
      Queue<String> pathQueue = new LinkedList<String>();
      nodeQueue.add(this);
      pathQueue.add("");

      // reptile milk carnivore breath first
      // loop through all the nodes of the tree
      while (!nodeQueue.isEmpty()) {
        DecisionTree tempNode = nodeQueue.poll();
        String tempPath = pathQueue.poll();
        out.println(tempPath + " " + tempNode.getData());

        // add left child to the node queue
        // append 'Y' to the parent path string and add to the path queue
        if (tempNode.getLeft() != null) {
          nodeQueue.add(tempNode.getLeft());
          pathQueue.add(tempPath + "Y");
        }
        // add right child to the node queue
        // append 'N' to the parent path string and add to the path queue
        if (tempNode.getRight() != null) {
          nodeQueue.add(tempNode.getRight());
          pathQueue.add(tempPath + "N");
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      out.close();
    }
  }

  /**
   *  Read in the tree from a file
   *  @param fileName String Name of the file
   *  @return the root node of the tree
   */
  public DecisionTree readTreeFromFile(String fileName) {
    DecisionTree nodeToBe = null;
    DecisionTree parentNode = this;
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(fileName));
      String line = reader.readLine();
      if (line != null) {
        parentNode.setData(line.trim());
      }
      // read next line
      line = reader.readLine();
      while (line != null) {
        // split the line at the first space character
        // to separate the path string from the node data
        String pathStr = line.substring(0, line.indexOf(' '));
        char [] pathCharArray = null;
        if (pathStr != null) {
          pathCharArray = pathStr.toCharArray();
        }
        String nodeData = line.substring(line.indexOf(' ') + 1);
        if (nodeData != null) {
          nodeToBe = new DecisionTree(nodeData.trim());
          //use all but the final character of the path string to find the parent of the new node-to-be.
          parentNode =this;
          for (int i=0; i<pathCharArray.length-1; i++) {
            if (pathCharArray[i]== 'Y') {
              //follow the path to the left
              parentNode = parentNode.getLeft();
            } else if (pathCharArray[i]== 'N'){
              //follow the path to the right
              parentNode = parentNode.getRight();
            }
          }
          //the final character then determines whether the new node becomes the left child or the right.
          if (pathCharArray[pathCharArray.length-1]== 'Y') {
            //set the left child
            parentNode.setLeft(nodeToBe);
          } else if (pathCharArray[pathCharArray.length-1]== 'N'){
            //set the right child
            parentNode.setRight(nodeToBe);
          }
        }
        // read next line
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return this;
  }
}
