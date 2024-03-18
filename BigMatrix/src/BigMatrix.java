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
		throw new UnsupportedOperationException();		
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
		throw new UnsupportedOperationException();
	}
	
	public int getRowSum(int row)
	{
		throw new UnsupportedOperationException();
	}
	
	public int getColSum(int col)
	{
		throw new UnsupportedOperationException();
	}
	
	public int getTotalSum()
	{
		throw new UnsupportedOperationException();
	}
	
	public BigMatrix multiplyByConstant(int constant)
	{
		throw new UnsupportedOperationException();
	}
	
	public BigMatrix addMatrix(BigMatrix other)
	{
		throw new UnsupportedOperationException();
	}
}
