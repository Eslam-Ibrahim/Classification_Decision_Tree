/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification_decision_tree;

/**
 *
 * @author Eslam Ibrahim
 */
public class Classification_Decision_Tree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        DecisionTreeAlgorithm DTClassifier = new DecisionTreeAlgorithm();
        DTClassifier.trainClassifier("data.txt", " ");
        DTClassifier.testClassifier("testData.txt", " ");
        
    }
    
}
