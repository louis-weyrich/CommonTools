/*
 * File: CSVParser.java
 *
 */
package com.sos.tools.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sos.tools.exception.InvalidColumnException;
import com.sos.tools.exception.InvalidRowException;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class CSVParser
{
    
    private List <List<String>> rowData;
    private String [] headers;
    

    
    public CSVParser(File file, String [] headers, char delimiter) 
	throws FileNotFoundException, ParseException
    {
        this(file, headers.length, delimiter);
        setHeaders(headers);
    }
    
    /**
     * @throws FileNotFoundException 
     * 
     */
    public CSVParser(File file, int columnCount, char delimiter) 
	throws FileNotFoundException, ParseException
    {
        rowData = new ArrayList <List<String>> ();
        headers = new String[columnCount];
        
        int linecount = 0;
        
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String [] elements = line.split(new String(new Character(delimiter).toString()));
            
            // stop parsing if column count does not match or ignore if columnCount is
            // less than a 1
            if(elements.length != columnCount && columnCount > 0)
                throw new ParseException("Columns counts do not match", linecount++);
            
            List <String> columnData = new ArrayList <String> (elements.length);
            
            for(String data : elements)
            {
                columnData.add(data.trim());
            }
            
            rowData.add(columnData);
        }
        
        scanner.close();
    }
    
    public void setHeaders(String [] headers)
    {
        this.headers = headers;
    }
    
    public String [] getHeaders()
    {
        return headers;
    }

    public List <String> nextRow()
    {
        List <String> row = rowData.get(0);
        rowData.remove(row);
        return row;
    }
    
    public String getData(int row, int column) 
	throws InvalidColumnException, InvalidRowException
	{
        
        if(row > rowData.size() || row < 0)
        {
            throw new InvalidRowException("Row ("+row+") is out of bounds.");
        }
        
        if(column > headers.length || column < 0)
        {
            throw new InvalidColumnException("Column ("+column+") is out of bounds.");
        }
        
        return rowData.get(row).get(column);
    }
    
    public String getData(int row, String columnName) 
	throws InvalidColumnException, InvalidRowException
	{
        int column = -1;
        
        for(String header : headers)
        {
            if(columnName.equals(header))
            {
                break;
            }
            else
            {
                column++;
            }
        }
        
        if(column == -1)
        {
            throw new InvalidColumnException("Column ("+columnName+") does not exist.");
        }
        
        return getData(row, column);
    }

    public boolean hasData()
    {
        return rowData.size() > 0;
    }

}
