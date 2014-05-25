package it.camera.hackathon.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SymmetricMatrix
{
	private float[][] matrixRows;

	public SymmetricMatrix(int rows)
	{
		if (rows < 1) this.matrixRows = null;
		else this.init(rows);
	}
	
	public SymmetricMatrix()
	{
		this(0);
	}
	
	private void init(int rows)
	{
		this.matrixRows = new float[rows - 1][];
		for (int r = 0; r < this.matrixRows.length; r++)
		{
			this.matrixRows[r] = new float[r + 1];
		}
	}
	
	public int getRowsCount()
	{
		return this.matrixRows == null? 0 : this.matrixRows.length + 1;
	}
	
	/**
	 * The row is added at the "bottom" of the matrix.
	 * @param data An array of n elements, n being the current number of rows in the matrix.
	 */
	public void add(float... data)
	{
		if (data.length != this.getRowsCount())
			throw new IllegalArgumentException("The number of elements in the array must be equal to the current number of rows in the matrix.");
		
		if (this.matrixRows == null)
		{
			this.init(1);
			return; // no value to copy in this case
		}
		
		// copies the given values to the new row
		this.matrixRows = Arrays.copyOf(this.matrixRows, this.matrixRows.length + 1);
		this.matrixRows[this.matrixRows.length - 1] = new float[data.length];
		for (int i = 0; i < data.length; i++) {
			this.matrixRows[this.matrixRows.length - 1][i] = data[i];
		}
	}

	private boolean contains(int[] array, int value)
	{
		for (int v : array)
		{
			if (v == value)
			{
				return true; 
			}
		}
		return false;
	}
	
	private boolean skipRow(int[] deletedRows, int currentRow)
	{
		return 	contains(deletedRows, currentRow + 1) || 
				(contains(deletedRows, 0) && currentRow == 0) ||
				(contains(deletedRows, 1) && currentRow == 1);
	}
	
	public void remove(int... rows)
	{
		// TODO Handle case with two identical indexes
		
		// deletes the rows from the matrix
		float[][] newMatrixRows = new float[this.matrixRows.length - rows.length][];
		int newRowIndex = 0;
		
//		// normalizes rows indexes
//		for (int j = 0; j < rows.length; j++)
//		{
//			rows[j] = rows[j] - 1;
//		}
		
		for (int r = 0; r < this.matrixRows.length; r++)
		{
			// skips deleted rows
			if (skipRow(rows, r)) continue;
			
			List<Float> rowValues = new ArrayList<Float>();
			// scans the original row, skipping values corresponding to deleted rows/columns
			for (int c = 0; c < this.matrixRows[r].length; c++)
			{
				// the rows vector is normalized for ROWS
				if (contains(rows, c)) continue;
				else rowValues.add(this.matrixRows[r][c]);
			}
			// fills the new row
			newMatrixRows[newRowIndex] = new float[rowValues.size()];
			for (int i = 0; i < rowValues.size(); i++)
			{
				newMatrixRows[newRowIndex][i] = rowValues.get(i);
			}
			newRowIndex++;
		}
		
		this.matrixRows = newMatrixRows;
	}
	
	/**
	 * Guarantees that each cell is considered only once. The matrix is iterated left-to-right/top-down.
	 * @param action
	 */
	public void iterate(IMatrixAction action)
	{
		for (int r = 0; r < this.matrixRows.length + 1; r++)
		{
			int rowLength = r == 0 ? 1 : this.matrixRows[r - 1].length + 1;
			
			for (int c = 0; c < rowLength; c++)
			{
				action.action(r, c);
			}
		}
	}
	
	/**
	 * 
	 * @param r
	 * @param c
	 * @return new int[] {r, c};
	 */
	public int[] getNormalizedIndexes(int r, int c)
	{
		if (c > r)
		{
			int tmp = c;
			c = r;
			r = tmp;
		}
		
		return new int[] {r, c};
	}
	
	public float get(int r, int c)
	{
		// TODO 0 sulla diagonale non è generale
		if (r == c) return 0;
		int[] rc = getNormalizedIndexes(r, c);
		// first row not stored
		r = rc[0] - 1; c = rc[1];
		return this.matrixRows[r][c];
	}
	
	public void set(int r, int c, float value)
	{
		// TODO
		if (r == c) throw new UnsupportedOperationException("r == c");
		int[] rc = getNormalizedIndexes(r, c);	
		// first row not stored
		r = rc[0] - 1; c = rc[1];
		this.matrixRows[r][c] = value;
	}
	
	/**
	 * Values for the main diagonal must NOT be provided.
	 * @param values
	 */
	public void fill(final float[] values)
	{
		final SymmetricMatrix m = this;
		m.iterate(new IMatrixAction() {

			int index = 0;
			
			@Override
			public void action(int r, int c) {
				if (r != c)
				{
					m.set(r, c, values[index]);
					index++;
				}
			}
		});
	}
	
	@Override
	public String toString()
	{
		if (this.matrixRows == null)
			return "[]";
		
		final StringBuilder sb = new StringBuilder();
		final SymmetricMatrix m = this;
		
		m.iterate(new IMatrixAction() {

			int prevR = 0;
			
			@Override
			public void action(int r, int c) {
				if (r != prevR)
				{
					prevR = r;
					sb.append("\n");
				}
				sb.append(m.get(r, c));
				sb.append(" ");
			}
			
		});
		
		return sb.toString();
	}
	
	public interface IMatrixAction
	{
		public void action(int r, int c);
	}
}