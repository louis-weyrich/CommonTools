/*
 * File: TableDataModel.java
 *
 */
package com.sos.tools.model;

import java.util.Iterator;

import com.sos.tools.exception.ModelException;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class TableDataModel <R> extends DataModel<Integer, RowDataModel<R>> implements Iterable <RowDataModel<R>>
{
    
    protected HeaderDataModel  headers = null;
    protected Integer largestIndex = new Integer(-1);
    
    public TableDataModel()
    {
       
    }
    
    public TableDataModel(String [] headerArray)
    {
        setHeaders(new HeaderDataModel(headerArray));
    }

    public TableDataModel(HeaderDataModel headers)
    {
        setHeaders(headers);
    }
    
    public void setHeaders(HeaderDataModel headers)
    {
        this.headers = headers;
    }
    
    public HeaderDataModel getHeaders()
    {
        return headers;
    }

    public RowDataModel <R> createNewRow()
    {
        int rowCount = super.getRowCount();
        
        Integer newIndex = new Integer(rowCount);
        if(newIndex.intValue() <= largestIndex.intValue())
        {
            newIndex = new Integer(largestIndex.intValue()+1);
        }
        largestIndex = newIndex;
        RowDataModel <R> row = new RowDataModel <R> (headers, largestIndex);
        setData(largestIndex, row);
        return row;
    }
    
    public void addData(RowDataModel<R> row)
    {
    	int rowCount = super.getRowCount();
        
        Integer newIndex = new Integer(rowCount);
        if(newIndex.intValue() <= largestIndex.intValue())
        {
            newIndex = new Integer(largestIndex.intValue()+1);
        }
        largestIndex = newIndex;
        setData(largestIndex, row);
    }
    
    public RowDataModel <R> getRow(int index)
    {
        if(index >= rowCount() || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds "+index+">="+rowCount());
        
        if(!getData().containsKey(new Integer(index)))
            throw new ArrayIndexOutOfBoundsException("Index("+index+") does not exist");
        
        return getData(new Integer(index));
    }
    
    public void removeRow(RowDataModel <R> row)
    {
        removeRow(row.getIndex().intValue());
    }
    
    public RowDataModel <R> removeRow(int index)
    {
        if(index >= rowCount() || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds "+index+">="+rowCount());
        
        return removeData(new Integer(index));
    }
    
    public Integer getIntegerData(int rowIndex, int columnIndex)
    {
        if(rowIndex >= rowCount() || rowIndex < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds "+rowIndex+">="+rowCount());
        
        RowDataModel <R> row = getData(new Integer(rowIndex));
        
        if(row != null)
        {
            if(columnIndex < row.size())
            {
                return row.getIntegerData(columnIndex);
            }
        }
        
        return null;
    }
    
    public Integer getIntegerData(int rowIndex, String headerName)
    {
        if(headers == null)
            throw new ModelException("Column with name "+headerName+" does not exist.");
        
        RowDataModel <R> row = getData(new Integer(rowIndex));
        return row.getIntegerData(headerName);
    }
    
    public Long getLongData(int rowIndex, int columnIndex)
    {
        if(rowIndex >= rowCount() || rowIndex < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds "+rowIndex+">="+rowCount());
        
        RowDataModel <R> row = getData(new Integer(rowIndex));
        
        if(row != null)
        {
            if(columnIndex < row.size())
            {
                return row.getLongData(new Integer(columnIndex));
            }
        }
        
        return null;
    }
    
    public Long getLongData(int rowIndex, String headerName)
    {
        if(headers == null)
            throw new ModelException("Column with name "+headerName+" does not exist.");
        
        RowDataModel <R> row = getData(new Integer(rowIndex));
        return row.getLongData(headerName);
    }
    
    public Float getFloatData(int rowIndex, int columnIndex)
    {
        if(rowIndex >= rowCount() || rowIndex < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds "+rowIndex+">="+rowCount());
        
        RowDataModel <R> row = getData(new Integer(rowIndex));
        
        if(row != null)
        {
            if(columnIndex < row.size())
            {
                return row.getFloatData(new Integer(columnIndex));
            }
        }
        
        return null;
    }
    
    public Float getFloatData(int rowIndex, String headerName)
    {
        if(headers == null)
            throw new ModelException("Column with name "+headerName+" does not exist.");
        
        RowDataModel <R> row = getData(new Integer(rowIndex));
        return row.getFloatData(headerName);
    }
    
    public String getStringData(int rowIndex, int columnIndex)
    {
        if(rowIndex >= rowCount() || rowIndex < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds "+rowIndex+">="+rowCount());
        
        RowDataModel <R> row = getData(new Integer(rowIndex));
        
        if(row != null)
        {
            if(columnIndex < row.size())
            {
                return row.getStringData(new Integer(columnIndex));
            }
        }
        
        return null;
    }
    
    public String getStringData(int rowIndex, String headerName)
    {
        if(headers == null)
            throw new ModelException("Column with name "+headerName+" does not exist.");
        
        RowDataModel <R> row = getData(new Integer(rowIndex));
        return row.getStringData(headerName);
    }

    public int rowCount()
    {
        return size();
    }
    
    public R setData(int rowIndex, int columnIndex, R value)
    {
        RowDataModel <R> row = getRow(rowIndex);
        return row.setData(columnIndex, value);
    }
    
    public R setData(int rowIndex, String columnName, R value)
    {
        RowDataModel <R> row = getRow(rowIndex);
        return row.setData(columnName, value);
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    public Iterator <RowDataModel<R>> iterator()
    {
        return data.values().iterator();
    }
}
