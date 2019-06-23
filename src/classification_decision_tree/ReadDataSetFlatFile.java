/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification_decision_tree;
import java.io.File;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 *
 * @author Eslam Ibrahim
 */

public class ReadDataSetFlatFile {
    
    private Scanner scan;
    
    
    public void openFile(String fileName)
    {
        try
        {
        scan = new Scanner (new File(fileName));
        }
        catch (Exception e)
        {
            
        }
        
    }
    
    public DataSet readFile( String attributeDelimiter)
    {
        DataSet fileContents = new DataSet();
        int numOfLines = 0;
        // Read the file line by line
        while(scan.hasNextLine())
        {
            // Read the DataSet header - initialize the attributes
            if (numOfLines==0)
            {
               // read the entire record
               String line = scan.nextLine();
               StringTokenizer headerRecord = new StringTokenizer(line, attributeDelimiter);
               int numOfAttributes = headerRecord.countTokens();
                for (int i = 0; i <numOfAttributes ; i++) {
                 Attribute tempAttr = new Attribute();
                 tempAttr.attrName = headerRecord.nextToken();
                 fileContents.setAttributes.add(tempAttr);
                }
                numOfLines++;
                continue;
            }
            // Add Record to the data set 
            String line = scan.nextLine();
            StringTokenizer record = new StringTokenizer(line, attributeDelimiter);
            int numOfAttributes = record.countTokens();
            for (int i = 0; i < numOfAttributes; i++) {
                String value =  record.nextToken();
                fileContents.setAttributes.get(i).attrValues.add(value);
                DistinctValue tempDistVal = new DistinctValue();
                tempDistVal.distValue = value;
                // Add to distValues for attribute i if it is uniuqe
                if (!fileContents.setAttributes.get(i).distinctValues.contains(tempDistVal)) {
                    tempDistVal.distValueSupportCount++;
                    fileContents.setAttributes.get(i).distinctValues.add(tempDistVal);
                    
                }
                // increase support count
                else
                {
                    int indx = fileContents.setAttributes.get(i).distinctValues.indexOf(tempDistVal);
                    fileContents.setAttributes.get(i).distinctValues.get(indx).distValueSupportCount++;
                }
            }
            numOfLines++;
        }
        fileContents.numOfRecords = (numOfLines-1);
        return fileContents;
    }
    public void closeFile()
    {
        scan.close();
    }
    
}
