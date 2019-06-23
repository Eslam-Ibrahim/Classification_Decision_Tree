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
public class DistinctValue {
    
    public String distValue;
    public double distValueSupportCount;
    public ArrayList<Double> mappingDistValueToClassLab;
    public ArrayList<Integer> occurranceIndices;
    
    public DistinctValue()
    {
        distValue="";
        distValueSupportCount = 0;
        mappingDistValueToClassLab = new ArrayList<>();
        occurranceIndices = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "DistinctValue{" + "distValue=" + distValue + ", distValueSupportCount=" + distValueSupportCount 
               + ", mappingDistValueToClassLab=" + mappingDistValueToClassLab +
                ", occurranceIndices=" + occurranceIndices + '}';
    }

  

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.distValue);
        hash = (int) (53 * hash + this.distValueSupportCount);
        hash = 53 * hash + Objects.hashCode(this.mappingDistValueToClassLab);
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
        final DistinctValue other = (DistinctValue) obj;
        if (!Objects.equals(this.distValue, other.distValue)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
