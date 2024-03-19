/**
 * Name: Rui Meng
 * Period: 1
 * Project: Big Matrix
 * Date last updated: 3/12
 * Project Description: Make 2 x 2 billion hash table with reasonable memory
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class BigMatrix 
{
	// A HashMap to store rows; each row is a HashMap mapping column indices to values
    private HashMap<Integer, HashMap<Integer, Integer>> matrix;

	public BigMatrix()
	{	
	    matrix = new HashMap<>();
	}
	
	public void setValue(int row, int col, int value)
	{
		// Only store non-zero values to conserve memory
        if (value != 0) {
            // Get the row HashMap, or create one if it doesn't exist
            HashMap<Integer, Integer> rowMap = matrix.getOrDefault(row, new HashMap<>());
            // Set the value at the specified column in this row
            rowMap.put(col, value);
            // Put the updated row back into the matrix
            matrix.put(row, rowMap);
        } else {
            // If the value is zero, we remove the entry if it exists to save space
            if (matrix.containsKey(row) && matrix.get(row).containsKey(col)) {
                matrix.get(row).remove(col);
                // If the row is now empty, remove it as well
                if (matrix.get(row).isEmpty()) {
                    matrix.remove(row);
                }
            }
        }
	}

	public int getValue(int row, int col)
	{
		// Check if the row exists in the matrix
	    if (matrix.containsKey(row)) {
	        HashMap<Integer, Integer> rowMap = matrix.get(row); // Retrieve the row HashMap
	        // Check if the column exists in this row
	        if (rowMap.containsKey(col)) {
	            return rowMap.get(col); // Return the value at the specified location
	        }
	    }
	    // Return 0 if no value is set at the given location
	    return 0;
	}
	
	public List<Integer> getNonEmptyRows()
	{
	    // Initialize a list to store the indices of non-empty rows
	    List<Integer> nonEmptyRows = new ArrayList<>();

	    // Iterate through the keys of the matrix map, which are the row indices
	    for (Integer row : matrix.keySet()) {
	        // Add each row index to the list
	        nonEmptyRows.add(row);
	    }

	    // Return the list of non-empty row indices
	    return nonEmptyRows;
	}
	
	public List<Integer> getNonEmptyRowsInColumn(int col)
	{
		// Initialize a list to store the indices of rows that have a non-zero value in the specified column
	    List<Integer> nonEmptyRowsInColumn = new ArrayList<>();

	    // Iterate through the matrix to find rows that contain the specified column
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : matrix.entrySet()) {
	        // Check if the current row has a non-zero value in the specified column
	        if (entry.getValue().containsKey(col)) {
	            // If so, add the row index to our list
	            nonEmptyRowsInColumn.add(entry.getKey());
	        }
	    }

	    // Return the list of row indices that have a non-zero value in the specified column
	    return nonEmptyRowsInColumn;	
	}

	public List<Integer> getNonEmptyCols()
	{
		// A set to store unique column indices that contain non-zero values
	    HashSet<Integer> nonEmptyColsSet = new HashSet<>();

	    // Iterate through all rows
	    for (HashMap<Integer, Integer> rowMap : matrix.values()) {
	        // Iterate through the keys of the rowMap, which are the column indices
	        for (Integer col : rowMap.keySet()) {
	            // Add the column index to the set
	            nonEmptyColsSet.add(col);
	        }
	    }

	    // Convert the set to a list to match the return type
	    List<Integer> nonEmptyCols = new ArrayList<>(nonEmptyColsSet);

	    // Return the list of non-empty column indices
	    return nonEmptyCols;	
	}
	
	public List<Integer> getNonEmptyColsInRow(int row)
	{
		// Initialize a list to store the indices of columns with non-zero values in the specified row
	    List<Integer> nonEmptyColsInRow = new ArrayList<>();
	    
	    // Check if the row exists in the matrix
	    if (matrix.containsKey(row)) {
	        // Get the map for the specified row
	        HashMap<Integer, Integer> rowMap = matrix.get(row);

	        // Iterate through the rowMap to get the columns
	        for (Integer col : rowMap.keySet()) {
	            // Add each column index to the list
	            nonEmptyColsInRow.add(col);
	        }
	    } // Return the list of column indices that have a non-zero value in the specified row
	    return nonEmptyColsInRow;
	}
	
	public int getRowSum(int row)
	{
		int sum = 0; // Initialize sum to 0

	    // Check if the row exists in the matrix
	    if (matrix.containsKey(row)) {
	        // Get the map for the specified row
	        HashMap<Integer, Integer> rowMap = matrix.get(row);

	        // Iterate through the values of the rowMap to calculate the sum
	        for (int value : rowMap.values()) {
	            sum += value; // Add each value to the sum
	        }
	    }

	    // Return the total sum of the row
	    return sum;
	}
	
	public int getColSum(int col)
	{
		int sum = 0; // Initialize sum to 0

	    // Iterate through the entire matrix to check each row
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : matrix.entrySet()) {
	        HashMap<Integer, Integer> rowMap = entry.getValue();
	        // If the specified column exists in this row, add its value to the sum
	        if (rowMap.containsKey(col)) {
	            sum += rowMap.get(col);
	        }
	    }

	    // Return the total sum of the column
	    return sum;
	}
	
	public int getTotalSum()
	{
		int totalSum = 0; // Initialize total sum to 0

	    // Iterate through the entire matrix
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : matrix.entrySet()) {
	        HashMap<Integer, Integer> rowMap = entry.getValue();
	        // Iterate through each value in the row and add it to the total sum
	        for (int value : rowMap.values()) {
	            totalSum += value;
	        }
	    }

	    // Return the total sum of the matrix
	    return totalSum;
	}
	
	public BigMatrix multiplyByConstant(int constant)
	{
		// Create a new BigMatrix to store the result of the multiplication
	    BigMatrix resultMatrix = new BigMatrix();

	    // Iterate through the entire matrix
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : matrix.entrySet()) {
	        int row = entry.getKey();
	        HashMap<Integer, Integer> rowMap = entry.getValue();

	        // Iterate through each column in the row
	        for (Map.Entry<Integer, Integer> colEntry : rowMap.entrySet()) {
	            int col = colEntry.getKey();
	            int value = colEntry.getValue();

	            // Multiply the value by the constant and set it in the result matrix
	            resultMatrix.setValue(row, col, value * constant);
	        }
	    }

	    // Return the new matrix with all values multiplied by the constant
	    return resultMatrix;
	}
	
	public BigMatrix addMatrix(BigMatrix other)
	{
		// Create a new BigMatrix to store the result of the addition
	    BigMatrix resultMatrix = new BigMatrix();

	    // First, iterate through the current matrix and add all its values to the resultMatrix
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : matrix.entrySet()) {
	        int row = entry.getKey();
	        HashMap<Integer, Integer> rowMap = entry.getValue();

	        for (Map.Entry<Integer, Integer> colEntry : rowMap.entrySet()) {
	            int col = colEntry.getKey();
	            int value = colEntry.getValue();

	            // Use setValue to add values since it might need to update existing values
	            resultMatrix.setValue(row, col, value);
	        }
	    }

	    // Then, iterate through the other matrix and add its values to the resultMatrix
	    // This step might update existing values or add new ones
	    for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : other.matrix.entrySet()) {
	        int row = entry.getKey();
	        HashMap<Integer, Integer> rowMap = entry.getValue();

	        for (Map.Entry<Integer, Integer> colEntry : rowMap.entrySet()) {
	            int col = colEntry.getKey();
	            int value = colEntry.getValue();

	            // Add the value from the other matrix to the corresponding cell in the resultMatrix
	            // If the cell already has a value, it will be updated to the sum of the two values
	            int existingValue = resultMatrix.getValue(row, col);
	            resultMatrix.setValue(row, col, existingValue + value);
	        }
	    }

	    // Return the new matrix representing the sum of the two matrices
	    return resultMatrix;
	}
}
