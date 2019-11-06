/**
 * 
 */
package com.sos.tools.utilities.math;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Louis Weyrich
 *
 */
public class Matrix <T extends Number>{
	
	
	public static enum TransposeType {
		RIGHT, LEFT, REVERSE_VERTICAL, REVERSE_HORIZONTAL, BACKWARDS, NORMAL, SPIRAL_LEFT, SPIRAL_RIGHT
	};
	
	private int rows;
	private int columns;
	private Class<T> valueType;
	private T [][] matrixValues;
	

	/**
	 * Initializes to a row X column matrix with all elements set to 0.  There are no null elements
	 * when the matrix initializes.
	 * 
	 * Null values are not accepted when setting element points.  An IllegalArgumentExc exception will be thrown. 
	 * 
	 * When setting elements, row and column bounds must be within tolerance.  Dynamic matrix growth is not allowed.
	 * Matrix boundaries must be within the row X column limits.
	 * 
	 * @param rows - The number or rows of data to initialize.
	 * @param columns - The number of columns of data to initialize.
	 * @param valueType - The type of data the matrix will hold. (this should match the generic type when defining the Object).
	 */
	@SuppressWarnings("unchecked")
	public Matrix(int rows, int columns, Class<T> valueType) {
		isValidValueType(valueType);
		this.rows = rows;
		this.columns = columns;
		this.valueType = valueType;
		this.matrixValues = (T[][])Array.newInstance(valueType, rows, columns);
		this.initializeMatrix();
	}
	
	/**
	 * 
	 * @param matrixValues
	 * @param valueType
	 */
	public Matrix(T [][] matrixValues, Class<T> valueType) {
		this(valueType);
		setMatrixValues(matrixValues);
	}
	
	
	/**
	 * 
	 * @param matrixValues
	 * @param valueType
	 */
	public Matrix(Class<T> valueType) {
		isValidValueType(valueType);
		this.valueType = valueType;
	}

	
	private void validateMatrix(T [][] matrixValues){
		for(int row = 0; row < matrixValues.length; row++) {
			for(int column = 0; column < matrixValues[0].length; column++) {
				if(matrixValues[row][column] == null) {
					throw new IllegalArgumentException("matrixValues can not contain null values.");
				}
			}
		}
	}
	
	/**
	 * This Matrix class only supports Integer, Long, Double, and Float wrapper types.
	 * 
	 * @param valueType
	 */
	private void isValidValueType(Class<T> valueType) {
		if(valueType.isAssignableFrom(Byte.class)) {
			throw new IllegalArgumentException("Byte type is not supported as a value type");
		}else if(valueType.isAssignableFrom(Short.class)) {
			throw new IllegalArgumentException("Short type is not supported as a value type");
		}
	}
	
	/**
	 *  Initializes all the matrix elements to 0.
	 */
	public void initializeMatrix() {
		T zeroValue = getZeroValue();
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				matrixValues[row][column] = zeroValue;
			}
		}
	}
	
	/**
	 * returns the number of rows.
	 * 
	 * @return
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * returns the number of columns.
	 * 
	 * @return
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * returns the value type
	 * @return
	 */
	public Class<T> getValueType() {
		return valueType;
	}

	/**
	 * returns an two dimensional array of the matrix values.
	 * @return - two dimensional array of the matrix values by generic type.
	 */
	public T[][] getMatrixValues() {
		return matrixValues;
	}

	/**
	 * 
	 * @param matrixValues
	 */
	public void setMatrixValues(T[][] matrixValues) {
		validateMatrix(matrixValues);
		this.rows = matrixValues.length;
		this.columns = matrixValues[0].length;
		this.matrixValues = matrixValues;
	}
	
	/**
	 * Sets a value to the matrix by row and column.
	 * 
	 * @param row
	 * @param column
	 * @param value
	 * @return
	 */
	public T setMatrixValue(int row, int column, T value) {
		if(row < 0 || row > rows) {
			throw new IllegalArgumentException("Row must be greater than 0 and less than "+rows+" (row = "+row+")");
		}
		
		if(column < 0 || column > columns) {
			throw new IllegalArgumentException("Column must be greater than 0 and less than "+columns+" (column = "+column+")");
		}
		
		if(value == null) {
			throw new IllegalArgumentException("Value can not be null");
		}
		
		T oldValue = this.matrixValues[row][column];
		
		this.matrixValues[row][column] = value;
		
		return oldValue;
	}
	
	/**
	 * Sets the entire row of elements by row number.
	 * 
	 * @param row - the row to set.
	 * @param values - the values for an entire row.
	 * @return the old values previously set to the row.
	 */
	@SuppressWarnings("unchecked")
	public T[] setMatrixValueRow(int row, T [] values) {
		if(values.length != columns) {
			throw new IllegalArgumentException("values.length must be equal to "+columns+" (length = "+values.length+")");
		}
		
		if(row < 0 || row > rows) {
			throw new IllegalArgumentException("Row must be greater than 0 and less than "+rows+" (row = "+row+")");
		}
		
		T [] oldValues = (T[])Array.newInstance(this.valueType, columns);
		
		for(int column = 0; column < columns; column++) {
			oldValues[column] = this.setMatrixValue(row, column, values[column]);
		}
		
		return oldValues;
	}
	
	/**
	 * Sets the entire column of elements by column number.
	 * 
	 * @param column - the column number to set.
	 * @param values  - the values for an entire column.
	 * @return  - the old values previously set to the column.
	 */
	@SuppressWarnings("unchecked")
	public T[] setMatrixValueColumn(int column, T [] values) {
		if(values.length != rows) {
			throw new IllegalArgumentException("values.length must be equal to "+rows+" (length = "+values.length+")");
		}
		
		if(column < 0 || column > columns) {
			throw new IllegalArgumentException("Column must be greater than 0 and less than "+columns+" (columns = "+column+")");
		}
		
		T [] oldValues = (T[])Array.newInstance(this.valueType, columns);
		
		for(int row = 0; row < columns; row++) {
			oldValues[row] = this.setMatrixValue(row, column, values[row]);
		}
		
		return oldValues;
	}

	/**
	 * returns a specific value by row and column.
	 * 
	 * @param row - the row to get.
	 * @param column - the column to get.
	 * @return - the value
	 */
	public T getMatrixValue(int row, int column) {
		if(row < 0 || row > rows) {
			throw new IllegalArgumentException("Row must be greater than 0 and less than "+rows+" (row = "+row+")");
		}
		
		if(column < 0 || column > columns) {
			throw new IllegalArgumentException("Column must be greater than 0 and less than "+columns+" (columns = "+column+")");
		}
		
		T value = this.matrixValues[row][column];
		
		return value;
	}
	
	/**
	 * returns an entire row of elements.
	 * 
	 * @param - row the row to get.
	 * @return - the row values.
	 */
	@SuppressWarnings("unchecked")
	public T[] getRow(int row) {
		if(row < 0 || row > rows) {
			throw new IllegalArgumentException("Row must be greater than 0 and less than "+rows+" (row = "+row+")");
		}
		
		T [] rowValues = (T[])Array.newInstance(this.valueType, columns);
		
		for(int column = 0; column < columns; column++) {
			rowValues[column] = getMatrixValue(row, column);
		}
		
		return rowValues;
	}

	/**
	 * returns an entire column of elements.
	 * 
	 * @param - column the column to get.
	 * @return - the column values
	 */
	@SuppressWarnings("unchecked")
	public T[] getColumn(int column) {
		if(column < 0 || column > columns) {
			throw new IllegalArgumentException("Column must be greater than 0 and less than "+columns+" (columns = "+column+")");
		}
		
		T [] columnValues = (T[])Array.newInstance(this.valueType, rows);
		
		for(int row = 0; row < rows; row++) {
			columnValues[row] = getMatrixValue(row, column);
		}
		
		return columnValues;
	}
	
	/**
	 *  Clears all values to 0.
	 */
	public void clearValues() {
		this.initializeMatrix();
	}
	
	/**
	 * Adds this matrix to another and returns a new matrix object.
	 * 
	 * @param otherMatrix - the matrix to add.
	 * @return - a new matrix with the the new values. 
	 */
	public Matrix<T> addMatrix(Matrix<T> otherMatrix)
	{
		if(this.rows != otherMatrix.getRows() || this.columns != otherMatrix.getColumns()) {
			throw new IllegalArgumentException("Matrices dimensions do not match.");
		}
		
		Matrix<T> newMatrix = new Matrix<T>(rows, columns, valueType);
		
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				newMatrix.setMatrixValue(row, column, 
					addValues(this.getMatrixValue(row, column), 
						otherMatrix.getMatrixValue(row, column)));
			}
		}
		
		return newMatrix;
	}
	
	/**
	 * A helper method to add the two values together.
	 * 
	 * @param value1 - the value to add
	 * @param value2- the value to add
	 * @return - the result.
	 */
	@SuppressWarnings("unchecked")
	private T addValues(T value1, T value2) 
	{
		T result = null;
		
		if(this.valueType.isAssignableFrom(Integer.class)) {
			Integer valueT1 = (Integer)value1;
			Integer valueT2 = (Integer)value2;
			result = (T)Integer.valueOf(valueT1.intValue()+valueT2.intValue());
		}else if(this.valueType.isAssignableFrom(Double.class)) {
			Double valueT1 = (Double)value1;
			Double valueT2 = (Double)value2;
			result = (T)Double.valueOf(valueT1.doubleValue()+valueT2.doubleValue());
		}else if(this.valueType.isAssignableFrom(Float.class)) {
			Float valueT1 = (Float)value1;
			Float valueT2 = (Float)value2;
			result = (T)Float.valueOf(valueT1.floatValue()+valueT2.floatValue());
		}else if(this.valueType.isAssignableFrom(Long.class)) {
			Long valueT1 = (Long)value1;
			Long valueT2 = (Long)value2;
			result = (T)Long.valueOf(valueT1.longValue()+valueT2.longValue());
		}
		
		return result;
	}
	
	/**
	 * subtracts this matrix to another and returns a new matrix object.
	 * 
	 * @param otherMatrix - the matrix to subtract.
	 * @return - a new matrix with the the new values. 
	 */
	public Matrix<T> subtractMatrix(Matrix<T> otherMatrix)
	{
		if(this.rows != otherMatrix.getRows() || this.columns != otherMatrix.getColumns()) {
			throw new IllegalArgumentException("Matrices dimensions do not match.");
		}
		
		Matrix<T> newMatrix = new Matrix<T>(rows, columns, valueType);
		
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				newMatrix.setMatrixValue(row, column, 
					subtractValues(this.getMatrixValue(row, column), 
						otherMatrix.getMatrixValue(row, column)));
			}
		}
		
		return newMatrix;
	}
	
	/**
	 * A helper method to subtract the two values together.
	 * 
	 * @param value1 - the value to subtract
	 * @param value2- the value to subtract
	 * @return - the result.
	 */
	@SuppressWarnings("unchecked")
	private T subtractValues(T value1, T value2) 
	{
		T result = null;
		
		if(this.valueType.isAssignableFrom(Integer.class)) {
			Integer valueT1 = (Integer)value1;
			Integer valueT2 = (Integer)value2;
			result = (T)Integer.valueOf(valueT1.intValue()-valueT2.intValue());
		}else if(this.valueType.isAssignableFrom(Double.class)) {
			Double valueT1 = (Double)value1;
			Double valueT2 = (Double)value2;
			result = (T)Double.valueOf(valueT1.doubleValue()-valueT2.doubleValue());
		}else if(this.valueType.isAssignableFrom(Float.class)) {
			Float valueT1 = (Float)value1;
			Float valueT2 = (Float)value2;
			result = (T)Float.valueOf(valueT1.floatValue()-valueT2.floatValue());
		}else if(this.valueType.isAssignableFrom(Long.class)) {
			Long valueT1 = (Long)value1;
			Long valueT2 = (Long)value2;
			result = (T)Long.valueOf(valueT1.longValue()-valueT2.longValue());
		}
		
		return result;
	}

	
	/**
	 * 
	 * @param otherMatrix
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> multiplyMatrix(Matrix<T> otherMatrix)
	{
		if(this.columns != otherMatrix.getRows()) {
			throw new IllegalArgumentException("Matrices dimensions do not match.");
		}
		
		Matrix<T> newMatrix = new Matrix<T>(rows, columns, valueType);
		
		int m1 = this.matrixValues.length;
        int n1 = this.matrixValues[0].length;
        int m2 = otherMatrix.getMatrixValues().length;
        int n2 = otherMatrix.getMatrixValues()[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
        T[][] c = (T[][])Array.newInstance(valueType, m1, n2);
        T zeroValue = getZeroValue();
        
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n2; j++) {
                for (int k = 0; k < n1; k++) {
                    c[i][j] = multiplyValues(this.matrixValues[i][k], otherMatrix.getMatrixValues()[k][j], (c[i][j] == null)? zeroValue : c[i][j]);
                }
            }
        }
        newMatrix.setMatrixValues(c);
		
		return newMatrix;
	}
	
	/**
	 * 
	 * @return
	 */
	private T getZeroValue() {
		T value = null;
		
		try {
			value = this.valueType.getDeclaredConstructor(String.class).newInstance("0");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	
	/**
	 * 
	 * @param rowValue
	 * @param columnValue
	 * @param oldValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private T multiplyValues(T rowValue, T columnValue, T oldValue) 
	{
		T result = null;
		
		if(this.valueType.isAssignableFrom(Integer.class)) {
			Integer valueT1 = (Integer)rowValue;
			Integer valueT2 = (Integer)columnValue;
			result = (T)Integer.valueOf((valueT1.intValue() * valueT2.intValue()) + oldValue.intValue());
		}else if(this.valueType.isAssignableFrom(Double.class)) {
			Double valueT1 = (Double)rowValue;
			Double valueT2 = (Double)columnValue;
			result = (T)Double.valueOf((valueT1.doubleValue() * valueT2.doubleValue()) + oldValue.doubleValue());
		}else if(this.valueType.isAssignableFrom(Float.class)) {
			Float valueT1 = (Float)rowValue;
			Float valueT2 = (Float)columnValue;
			result = (T)Float.valueOf((valueT1.floatValue() * valueT2.floatValue()) + oldValue.floatValue());
		}else if(this.valueType.isAssignableFrom(Long.class)) {
			Long valueT1 = (Long)rowValue;
			Long valueT2 = (Long)columnValue;
			result = (T)Long.valueOf((valueT1.longValue() * valueT2.longValue()) + oldValue.longValue());
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public Matrix<T> transpose(TransposeType type) {
		Matrix<T> temp = new Matrix<T>(columns, rows, this.valueType);
		
		if(type == TransposeType.NORMAL) {
			for(int row = 0; row < rows; row++) {
				for(int column = 0; column < columns; column++) {
					temp.setMatrixValue(column, row, this.getMatrixValue(row, column));
				}
			}
		}else if(type == TransposeType.RIGHT) {
			if(rows != columns) {
				throw new UnsupportedOperationException(
					"This is not a square matrix.  This operation can only be performed on a square matrix. rows("+rows+") coluimns("+columns+")");
			}
			
			for(int row = 0; row < rows; row++) {
				for(int column = 0; column < columns; column++) {
					temp.setMatrixValue(column, rows-row-1, this.getMatrixValue(row, column));
				}
			}
		}else if(type == TransposeType.LEFT) {
			if(rows != columns) {
				throw new UnsupportedOperationException(
					"This is not a square matrix.  This operation can only be performed on a square matrix. rows("+rows+") coluimns("+columns+")");
			}
			
			for(int row = 0; row < rows; row++) {
				for(int column = 0; column < columns; column++) {
					temp.setMatrixValue(columns-column-1, row, this.getMatrixValue(row, column));
				}
			}
		}else if(type == TransposeType.BACKWARDS) {
			for(int row = 0; row < rows; row++) {
				for(int column = 0; column < columns; column++) {
					temp.setMatrixValue(rows-row-1, columns-column-1, this.getMatrixValue(row, column));
				}
			}
		}else if(type == TransposeType.REVERSE_HORIZONTAL) {
			for(int row = 0; row < rows; row++) {
				for(int column = 0; column < columns; column++) {
					temp.setMatrixValue(row, columns-column-1, this.getMatrixValue(row, column));
				}
			}
		}else if(type == TransposeType.REVERSE_VERTICAL) {
			for(int row = 0; row < rows; row++) {
				for(int column = 0; column < columns; column++) {
					temp.setMatrixValue(rows-row-1, column, this.getMatrixValue(row, column));
				}
			}
		}else if(type == TransposeType.SPIRAL_RIGHT) {
			if(rows != columns) {
				throw new UnsupportedOperationException(
						"This is not a square matrix.  Spiral operation can only be performed on a square matrix. rows("+rows+") coluimns("+columns+")");
			}
			
			int width = columns;
			int height = rows;
			
			int left = 0;
			int right = width - 1;
			int top = 0;
			int bottom = height - 1;
			
			for(int index = 0; index < width*height; index++) 
			{
				//TOP 2 RIGHT EDGE
				for(int r = 0; r <= right; r++) {
					temp.setMatrixValue(r, right, getMatrixValue(top, r));
				}
				
				top++;
				
				//RIGHT EDGE 2 BOTTOM
				for(int b = top; b <= bottom; b++) {
					temp.setMatrixValue(bottom, width -  (b+1), getMatrixValue(b, right));
				}
				
				right--;
				
				if(bottom < top) break;
				
				//BOTTOM 2 LEFT EDGE
				for(int l = right; l >= left; l--) {
					temp.setMatrixValue(l, left, getMatrixValue(bottom, l));
				}
				
				bottom--;
				
				if(right < left) break;
				
				//LEFTEDGE 2 TOP
				for(int t = bottom; t >= top; t--) {
					temp.setMatrixValue(left, t, getMatrixValue(t, left));
				}
				
				left++;
			}
			
		}else if(type == TransposeType.SPIRAL_LEFT) {
			if(rows != columns) {
				throw new UnsupportedOperationException(
						"This is not a square matrix.  Spiral operation can only be performed on a square matrix. rows("+rows+") coluimns("+columns+")");
			}
			
			// TODO: implement transform spiral left

		}
		
		return temp;
	}
	
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for(int row = 0; row < rows; row++) {
			builder.append("| ");
			for(int column = 0; column < columns; column++) {
				builder.append((matrixValues[row][column]).toString());
				if(column != columns - 1) {
					builder.append(",\t");
				}
			}
			builder.append(" |\n");
		}
		
		return builder.toString();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object src) {
		if(src instanceof Matrix) {
			Matrix<?> srcMatrix = (Matrix<?>)src;
			if(this.valueType != srcMatrix.getValueType()) {
				return false;
			}
			
			if(this.rows != srcMatrix.getRows()) {
				return false;
			}
			
			if(this.columns != srcMatrix.getColumns()) {
				return false;
			}
			
			return ((this == srcMatrix));
		}
		
		return false;
	}

}
