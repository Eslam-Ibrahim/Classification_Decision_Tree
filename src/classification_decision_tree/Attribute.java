/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification_decision_tree;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Eslam Ibrahim
 */
public class Attribute {

    public String attrName;
    public ArrayList<String> attrValues;
    public ArrayList<DistinctValue> distinctValues;
    public double infoD;
    public double infoGain;

    public Attribute() {
        attrName = "";
        attrValues = new ArrayList<>();
        distinctValues = new ArrayList<>();
        infoD = 0;
        infoGain = 0;
    }

    public void calculateInfoD_AsClassLable(int numOfRecords) {

        int distValueCount = distinctValues.size();
        for (int i = 0; i < distValueCount; ++i) {

            double pi = (distinctValues.get(i).distValueSupportCount / numOfRecords);
            double log2Pi = -(Math.log(pi) / Math.log(2.0));
            infoD += (pi * log2Pi);
        }
    }

    public void calculateInfoD(int numOfRecords) {

       
        int distValueCount = distinctValues.size();
        System.out.println(attrName);
         System.out.println("distValueCount: "+distValueCount);
        for (int i = 0; i < distValueCount; i++) {
            double probDistVal = (distinctValues.get(i).distValueSupportCount / numOfRecords);
            double resultSum = 0;
            // loop on each class lable dist value
            for (int j = 0; j <distinctValues.get(i).mappingDistValueToClassLab.size(); j++) {
                if(distinctValues.get(i).mappingDistValueToClassLab.get(j)==0)
                {
                    continue;
                }
                double probJ = distinctValues.get(i).mappingDistValueToClassLab.get(j)/distinctValues.get(i).distValueSupportCount;
                double log2PJ = -(Math.log(probJ) / Math.log(2.0));
                resultSum += probJ * log2PJ;
            
          }
            infoD+=(probDistVal * resultSum);

        }

    }
    
    public void calculateInfoGain(double classLableInfoD){
        
        infoGain = classLableInfoD - infoD;
 
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.attrName);
        hash = 59 * hash + Objects.hashCode(this.attrValues);
        hash = 59 * hash + Objects.hashCode(this.distinctValues);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.infoD) ^ (Double.doubleToLongBits(this.infoD) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.infoGain) ^ (Double.doubleToLongBits(this.infoGain) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Attribute other = (Attribute) obj;
        if (!Objects.equals(this.attrName, other.attrName)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Attribute{" + "attrName=" + attrName + ", attrValues=" + attrValues + ","
                + " distinctValues=" + distinctValues + ", infoD=" + infoD + ", infoGain=" + infoGain + '}' + "\n";
    }

}
