/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification_decision_tree;

import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * @author Eslam Ibrahim
 */
public class WriteDecTreeToFile {
    
    private Formatter format;
    
    public void openFile(String fileName)
    {
        try
        {
         format = new Formatter(fileName);
        }
        catch (Exception e)
        {
            
        }
        
    }
    public void writeString(DecisionTree DCtree)
    {
        // write Root
        format.format("%s%s",DCtree.toString(),"\n");
    }
    
     public void writeArrayList(ArrayList<String> list)
    {
        for (int i = 0; i < list.size(); i++) {
            format.format("%s%s", list.get(i),"---"
                    + "");
        }
    }
    public void closeFile()
    {
      format.close();
    }
    
}