/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification_decision_tree;

import java.util.ArrayList;

/**
 *
 * @author Eslam Ibrahim
 */
public class DecisionTree {
    
    public DecisionTreeNode rootNode;

    public DecisionTree() {
        
        rootNode = new DecisionTreeNode();
      
    }
    
    @Override
    public String toString() {
        return "DecisionTree{" + "rootNode=" + rootNode + '}';
    }
    
    
    
    
}
