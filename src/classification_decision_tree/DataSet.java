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
public class DataSet {
    
   public ArrayList<Attribute> setAttributes;
   public int numOfRecords;
   private int maxGainAttributeIndx;
   
   public DataSet()
   {
       setAttributes = new ArrayList<>();
       numOfRecords = 0;
       maxGainAttributeIndx=0;
   }
   
   public void MapAttributeDistValuesToCalssLableDistValues()
   {
       int indxOfClassLableColumn = setAttributes.size()-1;
       // loop on each attribute
       for (int i = 0; i < setAttributes.size(); i++) {
           
           //loop on each dist value of attribute i
           for (int j = 0; j < setAttributes.get(i).distinctValues.size(); j++) {
               
               String attrDistValue = setAttributes.get(i).distinctValues.get(j).distValue;
               // loop on each dist value of class lable 
               for (int k = 0; k < setAttributes.get(indxOfClassLableColumn).distinctValues.size(); k++) {
                   
                   String classLableValue = setAttributes.get(indxOfClassLableColumn).distinctValues.get(k).distValue;
                   double ctr = 0;
                   // mapping loop
                   for (int l = 0; l < numOfRecords; l++) {
                       if (setAttributes.get(i).attrValues.get(l).equals(attrDistValue) && 
                          setAttributes.get(indxOfClassLableColumn).attrValues.get(l).equals(classLableValue)){
                           ctr++;
                           // store index of the dist value
                           setAttributes.get(i).distinctValues.get(j).occurranceIndices.add(l);
                       }
                   }
                   setAttributes.get(i).distinctValues.get(j).mappingDistValueToClassLab.add(ctr);
               }
           }
          
       }
   }
   
   public int calculateMaxGainAttributeIndx(){
       double maxInfoGain = setAttributes.get(1).infoGain;
       maxGainAttributeIndx=1;
       for (int i = 2; i < (setAttributes.size()-1); i++) {
           if (setAttributes.get(i).infoGain>maxInfoGain){
               maxInfoGain = setAttributes.get(i).infoGain;
               maxGainAttributeIndx = i;
           }
       }
       return maxGainAttributeIndx;
   }

  public boolean isPure(){
       
       boolean isPure = true;
       int classLableColIndex = setAttributes.size()-1; 
       System.out.println("pure-ClassLableIndx: " + classLableColIndex);
       for (int i = 0; i < setAttributes.get(classLableColIndex).attrValues.size()-1; i++) {
           
           if (!setAttributes.get(classLableColIndex).attrValues.get(i).
              equals(setAttributes.get(classLableColIndex).attrValues.get(i+1))){
               isPure = false;
               break;
           }
       }
       
       
       return isPure;
   }
   
  public DataSet shrinkByAttribute(int attrIndx , int distValueIndx){
       
       DataSet resultSet = new DataSet();
       // construct the structre of the new data set
       for (int i = 0; i <setAttributes.size() ; i++) {
           if(i==attrIndx)
           {
               continue;
           }
            Attribute tempAttr = new Attribute();
            tempAttr.attrName = setAttributes.get(i).attrName;
            resultSet.setAttributes.add(tempAttr);
        }
       // Fill the new data set according to shrinking distValue
       ArrayList <Integer> targetIndices = setAttributes.get(attrIndx).distinctValues.get(distValueIndx).occurranceIndices;
       
       // loop on the entire old data set records
       for (int l = 0; l < targetIndices.size(); l++) {
           int indxOfResultSet = 0;
           //loop on each attribute in the record
           for (int i = 0; i < setAttributes.size(); i++) {
               
               if (i==attrIndx){
                   continue;
               }
               // Add the attribute value to the same attribute in the new set
               String value = setAttributes.get(i).attrValues.get(targetIndices.get(l));
              resultSet.setAttributes.get(indxOfResultSet).attrValues.add(value);
                DistinctValue tempDistVal = new DistinctValue();
                tempDistVal.distValue = value;
                // Add to distValues for attribute i if it is uniuqe
                if (!resultSet.setAttributes.get(indxOfResultSet).distinctValues.contains(tempDistVal)) {
                    tempDistVal.distValueSupportCount++;
                    resultSet.setAttributes.get(indxOfResultSet).distinctValues.add(tempDistVal);
                    
                }
                // increase support count
                else
                {
                    int indx = resultSet.setAttributes.get(indxOfResultSet).distinctValues.indexOf(tempDistVal);
                    resultSet.setAttributes.get(indxOfResultSet).distinctValues.get(indx).distValueSupportCount++;
                }
                indxOfResultSet++;
           }
         
       }
       resultSet.numOfRecords = resultSet.setAttributes.get(0).attrValues.size();
       return resultSet;
   }

   
    @Override
    public String toString() {
        return "DataSet{" + "setAttributes=" + setAttributes + '}';
    }
   
}
