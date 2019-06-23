/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification_decision_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Eslam Ibrahim
 */
public class DecisionTreeNode {

    public String attributeName;
    public String arrowLable;
    public DecisionTreeNode parentNode;
    public ArrayList<DecisionTreeNode> nodeChildren;
    public DataSet nodeDataSet;
    public boolean isLeaf;
    public String classLable;
    public List<DecisionTreeNode> elementsIndex;;

    public DecisionTreeNode() {
        attributeName ="";
        arrowLable= "";
        parentNode = null;
        nodeChildren = new ArrayList<>();
        nodeDataSet = null;
        isLeaf = false;
        classLable ="";
        this.elementsIndex = new LinkedList<>();
        elementsIndex.add(this);
    }
    
   public boolean isRoot(){
        
        if (parentNode==null){
            return true;
        }
        return false;
    }
    
     public void addChild(DecisionTreeNode childNode) {
         
                childNode.parentNode = this;
                this.nodeChildren.add(childNode);
                this.registerChildForSearch(childNode);
        }
      private void registerChildForSearch(DecisionTreeNode node) {
                elementsIndex.add(node);
                if (parentNode != null)
                        parentNode.registerChildForSearch(node);
        }
      public DecisionTreeNode findTreeNode(String attrValue) {
                for (DecisionTreeNode element : this.elementsIndex) {
                        String elmData = element.arrowLable;
                        if (attrValue.compareTo(elmData) == 0)
                                return element;
                }
                return null;
        }

      
      
    

    @Override
    public String toString() {
        return "DecisionTreeNode{" + "attributeName=" + attributeName + ", arrowLable=" + arrowLable + ", nodeChildren=" + nodeChildren + 
                ", isLeaf=" + isLeaf + ", classLable=" + classLable + '}'+"-->";
    }
    
    
    

   
    
    
    

}
