/**
 * Name: Rui Meng
 * Period: 1
 * Project: Big Matrix
 * Date last updated: 3/19
 * Project Description: Make a data structure that can represent a 2 billion-by-2 billion matrix
 * of integers, only using an amount of memory proportional to the total number of non-zero
 * values in the matrix
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class BigMatrix 
{
	private HashMap<Integer, HashMap<Integer, Integer>> matrix;

	public BigMatrix()
	{	
	    matrix = new HashMap<>();
	}
	
	// Sets a value at a given row/col position in the matrix. 
	// Must run in constant time. Must not use any extra space to store zero values
	public void setValue(int row, int col, int value)
	{
		// Only store non-zero values to conserve memory
        if (value != 0) {
        	// Check if the row already exists
            if (!matrix.containsKey(row)) {
                // If not, create a new row and put it in the matrix
                matrix.put(row, new HashMap<Integer, Integer>());
            }
            
            matrix.get(row).put(col, value);
            
        } else {
            // If the value is zero, proceed to remove the entry if it exists
            if (matrix.containsKey(row) && matrix.get(row).containsKey(col)) {
                matrix.get(row).remove(col);
                
                // If the row is now empty, remove it as well
                if (matrix.get(row).isEmpty()) {
                    matrix.remove(row);
                }
            }
        }
	}
	
	// Returns the value at a given row/col position. 
	// Return 0 if no value set at the given location
	public int getValue(int row, int col)
	{
		// Check if the row exists in the matrix
	    if (matrix.containsKey(row)) {
	        HashMap<Integer, Integer> rowMap = matrix.get(row);
	        
	        // Check if the column exists in this row
	        if (rowMap.containsKey(col)) {
	            return rowMap.get(col);
	        }
	    }
	    
	    return 0;
	}
	
	// Returns a list of the rows that have at least one column with non-zero 
	// value.
	public List<Integer> getNonEmptyRows()
	{
	    List<Integer> nonEmptyRows = new ArrayList<>();

	    // Iterate through the keys of the matrix map
	    for (Integer row : matrix.keySet()) {
	        // Add each row index to the list
	        nonEmptyRows.add(row);
	    }

	    return nonEmptyRows;
	}

	// Returns a list of rows that have at least one non-zero value at the given
	// column.
	public List<Integer> getNonEmptyRowsInColumn(int col)
	{
		List<Integer> nonEmptyRowsInColumn = new ArrayList<>();

	    // Iterate through the matrix to find rows that contain the specified column
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : matrix.entrySet()) {
	        // Check if the current row has a non-zero value in the specified column
	        if (entry.getValue().containsKey(col)) {
	            // If so, add the row index to our list
	            nonEmptyRowsInColumn.add(entry.getKey());
	        }
	    }

	    return nonEmptyRowsInColumn;	
	}

	// Returns a list of the columns that have at least one row with non-zero 
	// value.
	public List<Integer> getNonEmptyCols()
	{
		HashSet<Integer> nonEmptyColsSet = new HashSet<>();

	    // Iterate through all rows
	    for (HashMap<Integer, Integer> rowMap : matrix.values()) {
	        // Iterate through the keys of the rowMap, which are the column indices
	        for (Integer col : rowMap.keySet()) {
	            // Add the column index to the set
	            nonEmptyColsSet.add(col);
	        }
	    }

	    List<Integer> nonEmptyCols = new ArrayList<>(nonEmptyColsSet);

	    return nonEmptyCols;	
	}

	// Returns a list of columns that have at least one non-zero value at the given
	// row.
	public List<Integer> getNonEmptyColsInRow(int row)
	{
		List<Integer> nonEmptyColsInRow = new ArrayList<>();
	    
	    // Check if the row exists in the matrix
	    if (matrix.containsKey(row)) {
	        // Get the map for the specified row
	        HashMap<Integer, Integer> rowMap = matrix.get(row);

	        // Iterate through the rowMap to get the columns
	        for (Integer column : rowMap.keySet()) {
	            // Add each column index to the list
	            nonEmptyColsInRow.add(column);
	        }
	    } 
	    
	    return nonEmptyColsInRow;
	}
	
	// Returns the sum of all entries in the given row. It must run in time
	// proportional to the number of entries in the row
	public int getRowSum(int row)
	{
		int sum = 0; 
		
	    // Check if the row exists in the matrix
	    if (matrix.containsKey(row)) {
	        // Get the map for the specified row
	        HashMap<Integer, Integer> rowMap = matrix.get(row);

	        // Iterate through the values of the rowMap to calculate the sum
	        for (int value : rowMap.values()) {
	            sum += value; // Add each value to the sum
	        }
	    }

	    return sum;
	}
	
	// Returns the sum of all entries in the given column. It must run in time
	// proportional to the number of entries in the column
	public int getColSum(int col)
	{
		int sum = 0; 
		
	    // Iterate through the entire matrix to check each row
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : matrix.entrySet()) {
	        HashMap<Integer, Integer> rowMap = entry.getValue();
	        
	        // If the specified column exists in this row, add its value to the sum
	        if (rowMap.containsKey(col)) {
	            sum += rowMap.get(col);
	        }
	    }

	    return sum;
	}
	
	// Returns the sum of all entries in the matrix. It must run in time
	// proportional to the number of entries in the matrix
	public int getTotalSum()
	{
		int totalSum = 0; 
		
	    // Iterate through the entire matrix
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : matrix.entrySet()) {
	        HashMap<Integer, Integer> rowMap = entry.getValue();
	        
	        // Iterate through each value in the row and add it to the total sum
	        for (int value : rowMap.values()) {
	            totalSum += value;
	        }
	    }

	    return totalSum;
	}
	
	// Returns a new matrix, with the values of the current matrix multiplied by
	// the given constant.
	public BigMatrix multiplyByConstant(int constant)
	{
		BigMatrix resultMatrix = new BigMatrix();

	    // Iterate through the entire matrix
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : matrix.entrySet()) {
	        int row = entry.getKey();
	        HashMap<Integer, Integer> rowMap = entry.getValue();

	        // Iterate through each column in the row
	        for (Map.Entry<Integer, Integer> columnEntry : rowMap.entrySet()) {
	            int column = columnEntry.getKey();
	            int value = columnEntry.getValue();

	            // Multiply the value by the constant and set it in the result matrix
	            resultMatrix.setValue(row, column, value * constant);
	        }
	    }

	    return resultMatrix;
	}
	
	// Returns a new matrix, consisting of the sum of the current matrix and the
	// passed-in matrix.
	public BigMatrix addMatrix(BigMatrix other)
	{
		BigMatrix resultMatrix = new BigMatrix();

	    // First, iterate through the current matrix and add all its values to the resultMatrix
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : matrix.entrySet()) {
	        int row = entry.getKey();
	        HashMap<Integer, Integer> rowMap = entry.getValue();

	        for (Map.Entry<Integer, Integer> columnEntry : rowMap.entrySet()) {
	            int column = columnEntry.getKey();
	            int value = columnEntry.getValue();

	            // Use setValue to add values since it might need to update existing values
	            resultMatrix.setValue(row, column, value);
	        }
	    }

	    // Then, iterate through the other matrix and add its values to the resultMatrix
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : other.matrix.entrySet()) {
	        int row = entry.getKey();
	        HashMap<Integer, Integer> rowMap = entry.getValue();

	        for (Map.Entry<Integer, Integer> columnEntry : rowMap.entrySet()) {
	            int column = columnEntry.getKey();
	            int value = columnEntry.getValue();

	            // Add the value from the other matrix to corresponding cell in the resultMatrix
	            // If cell already has a value, it will be updated to the sum of the two values
	            int existingValue = resultMatrix.getValue(row, column);
	            resultMatrix.setValue(row, column, existingValue + value);
	        }
	    }

	    return resultMatrix;
	}
}
