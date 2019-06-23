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
public class DecisionTreeAlgorithm {

    private DataSet trainingSet;
    private DataSet testingSet;
    private DecisionTree classificationTree;

    public DecisionTreeAlgorithm() {
        trainingSet = new DataSet();
        testingSet = new DataSet();
        classificationTree = new DecisionTree();
    }

    public DataSet getTrainingSet() {
        return trainingSet;
    }

    public void setTrainingSet(DataSet trainingSet) {
        this.trainingSet = trainingSet;
    }

    public DataSet getTestingSet() {
        return testingSet;
    }

    public void setTestingSet(DataSet testingSet) {
        this.testingSet = testingSet;
    }

    public DecisionTree getClassificationTree() {
        return classificationTree;
    }

    public void trainClassifier(String fileName, String attrDelm) {

        // step #0: Read Trainnig Data Set
        ReadDataSetFlatFile fileReader = new ReadDataSetFlatFile();
        fileReader.openFile(fileName);
        trainingSet = fileReader.readFile(attrDelm);
        fileReader.closeFile();
        // step#1: calculate info D , info gain for each attribute in data set
        trainingSet.MapAttributeDistValuesToCalssLableDistValues();
        trainingSet.setAttributes.get(trainingSet.setAttributes.size() - 1).calculateInfoD_AsClassLable(trainingSet.numOfRecords);
        double originalDataInfoD = trainingSet.setAttributes.get(trainingSet.setAttributes.size() - 1).infoD;
        for (int i = 0; i < (trainingSet.setAttributes.size() - 1); i++) {
            trainingSet.setAttributes.get(i).calculateInfoD(trainingSet.numOfRecords);
            trainingSet.setAttributes.get(i).calculateInfoGain(originalDataInfoD);
        }
        // Step#2: Build the tree
        int numOfSteps = (trainingSet.setAttributes.size() - 2) - 1;
        for (int i = 1; i < numOfSteps; i++) {

            // Root initialization
            if (i == 1) {
                int maxGainIndx = trainingSet.calculateMaxGainAttributeIndx();
                classificationTree.rootNode.attributeName = trainingSet.setAttributes.get(maxGainIndx).attrName;
                // loop on dist values of the maxGain attribute
                for (int j = 0; j < trainingSet.setAttributes.get(maxGainIndx).distinctValues.size(); j++) {
                    DistinctValue tempDistValue = trainingSet.setAttributes.get(maxGainIndx).distinctValues.get(j);
                    DecisionTreeNode tempNode = new DecisionTreeNode();
                    tempNode.arrowLable = tempDistValue.distValue;
                    tempNode.nodeDataSet = trainingSet.shrinkByAttribute(maxGainIndx, j);
                    classificationTree.rootNode.addChild(tempNode);
                }
                // check for purity in each root child
                for (int j = 0; j < classificationTree.rootNode.nodeChildren.size(); j++) {
                    if (classificationTree.rootNode.nodeChildren.get(j).nodeDataSet.isPure()) {
                        classificationTree.rootNode.nodeChildren.get(j).classLable = classificationTree.rootNode.nodeChildren.get(j).nodeDataSet.setAttributes.get(classificationTree.rootNode.nodeChildren.get(j).nodeDataSet.setAttributes.size() - 1).distinctValues.get(0).distValue;

                        classificationTree.rootNode.nodeChildren.get(j).isLeaf = true;
                        classificationTree.rootNode.nodeChildren.get(j).nodeDataSet.setAttributes.clear();
                    }
                }
                // calculate info gains for the root children
                for (int j = 0; j < classificationTree.rootNode.nodeChildren.size(); j++) {
                    if(!classificationTree.rootNode.nodeChildren.get(j).isLeaf){
                    int accessIndx = classificationTree.rootNode.nodeChildren.get(j).nodeDataSet.setAttributes.size() - 1;
                    int numberOfrecords = classificationTree.rootNode.nodeChildren.get(j).nodeDataSet.numOfRecords;
                    classificationTree.rootNode.nodeChildren.get(j).nodeDataSet.MapAttributeDistValuesToCalssLableDistValues();
                    classificationTree.rootNode.nodeChildren.get(j).nodeDataSet.setAttributes.get(accessIndx).calculateInfoD_AsClassLable(numberOfrecords);
                    double DataInfoD = classificationTree.rootNode.nodeChildren.get(j).nodeDataSet.setAttributes.get(accessIndx).infoD;
                    for (int k = 0; k < accessIndx; k++) {
                        classificationTree.rootNode.nodeChildren.get(j).nodeDataSet.setAttributes.get(k).calculateInfoD(numberOfrecords);
                        classificationTree.rootNode.nodeChildren.get(j).nodeDataSet.setAttributes.get(k).calculateInfoGain(DataInfoD);
                    }
                }
                }

                i++;
            }
            // root already exists - loop on all branches
            
            for(int b=0; b<classificationTree.rootNode.nodeChildren.size();b++){
                
                System.out.println("b "+b);
                if(!classificationTree.rootNode.nodeChildren.get(b).isLeaf){
                    
                  int maxGainIndx = classificationTree.rootNode.nodeChildren.get(b).nodeDataSet.calculateMaxGainAttributeIndx();
                classificationTree.rootNode.nodeChildren.get(b).attributeName = classificationTree.rootNode.nodeChildren.get(b).
                        nodeDataSet.setAttributes.get(maxGainIndx).attrName;
                // loop on dist values of the maxGain attribute
                for (int j = 0; j < classificationTree.rootNode.nodeChildren.get(b).
                        nodeDataSet.setAttributes.get(maxGainIndx).distinctValues.size(); j++) {
                    DistinctValue tempDistValue = classificationTree.rootNode.nodeChildren.get(b).
                        nodeDataSet.setAttributes.get(maxGainIndx).distinctValues.get(j);
                    DecisionTreeNode tempNode = new DecisionTreeNode();
                    tempNode.arrowLable = tempDistValue.distValue;
                    tempNode.nodeDataSet = classificationTree.rootNode.nodeChildren.get(b).
                        nodeDataSet.shrinkByAttribute(maxGainIndx, j);
                    classificationTree.rootNode.nodeChildren.get(b).addChild(tempNode);
                }
                classificationTree.rootNode.nodeChildren.get(b).nodeDataSet.setAttributes.clear();
                // check for purity in each child of the parent 
                for (int j = 0; j < classificationTree.rootNode.nodeChildren.get(b).nodeChildren.size(); j++) {
                    if (classificationTree.rootNode.nodeChildren.get(b).nodeChildren.get(j).nodeDataSet.isPure()) {
                      
                        classificationTree.rootNode.nodeChildren.get(b).nodeChildren.get(j).classLable 
                   = classificationTree.rootNode.nodeChildren.get(b).nodeChildren.get(j).nodeDataSet.setAttributes.
                     get(classificationTree.rootNode.nodeChildren.get(b).nodeChildren.get(j).nodeDataSet.setAttributes.size() - 1).
                         distinctValues.get(0).distValue;

                        classificationTree.rootNode.nodeChildren.get(b).nodeChildren.get(j).isLeaf = true;
                        classificationTree.rootNode.nodeChildren.get(b).nodeChildren.get(j).nodeDataSet.setAttributes.clear();
                    }
                }
                
                
                i++;
              }
                
            }

        }
        WriteDecTreeToFile fileWriter = new WriteDecTreeToFile();
        fileWriter.openFile("DCtree.txt");
        fileWriter.writeString(classificationTree);
        fileWriter.closeFile();
        System.out.println("Classifier has finished training - check DCtree.txt file");

    }
    
     public void testClassifier(String fileName ,String delm){
       
        // step #0: Read Testing Data Set
        ReadDataSetFlatFile fileReader = new ReadDataSetFlatFile();
        fileReader.openFile(fileName);
        testingSet = fileReader.readFile(delm);
        fileReader.closeFile();
        ArrayList <String> resultClassLables = new ArrayList<>();
        // Classification loop
        for (int l = 0; l < testingSet.numOfRecords; l++) {
            
            
                // Ask Root for classification
                String rootAttrName = classificationTree.rootNode.attributeName;
                Attribute tempAttr = new Attribute();
                tempAttr.attrName = rootAttrName;
                int indxOfAttrName = testingSet.setAttributes.indexOf(tempAttr);
                String lableValue = testingSet.setAttributes.get(indxOfAttrName).attrValues.get(l);
                DecisionTreeNode tempNode = classificationTree.rootNode.findTreeNode(lableValue);
                
                // Ask children for classification - (if the 1st child is not leaf)
                while(!tempNode.isLeaf){
                    
                    tempAttr.attrName =  tempNode.attributeName;
                    indxOfAttrName = testingSet.setAttributes.indexOf(tempAttr);
                    if(indxOfAttrName==-1){
                        // all possible class lables
                        String allClassLables="";
                        for (int k = 0; k < trainingSet.setAttributes.get(trainingSet.setAttributes.size()-1)
                          .distinctValues.size(); k++) {
                        
                      allClassLables+=trainingSet.setAttributes.get(trainingSet.setAttributes.size()-1).distinctValues.get(k).distValue+"/";
                            
                        }
                        resultClassLables.add(allClassLables);
                        
                        break;
                    }
                    lableValue = testingSet.setAttributes.get(indxOfAttrName).attrValues.get(l);
                    tempNode = tempNode.findTreeNode(lableValue);
                }
                resultClassLables.add(tempNode.classLable);
         }
        // write result to file
        WriteDecTreeToFile fileWriter = new WriteDecTreeToFile();
        fileWriter.openFile("testResult.txt");
        fileWriter.writeArrayList(resultClassLables);
        fileWriter.closeFile();
        System.out.println("Classifier has finished testing - check testResult.txt file");
       
         
         
    }
}
