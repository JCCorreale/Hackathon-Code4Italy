package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.ITerm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class Utils {

	private Utils()
	{
		
	}

	public static String readTextFile(String path, Charset encoding) throws IOException 
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public static void printMap(Iterable<Entry<?, ?>> entries)
	{
		for (Entry<?, ?> entry : entries)
		{
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
	
	public static void printMap(Map<?, ?> map)
	{
		for (Object key : map.keySet())
		{
			System.out.println(key + " " + map.get(key));
		}
	}
	
	public static Map<String, Integer> entryListToMap(List<Entry<String, Integer>> list)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Entry<String, Integer> entry : list)
		{
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}
	
	public static List<Entry<String, Integer>> mapToEntryList(Map<String, Integer> map)
	{
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>();
		for (String key : map.keySet())
		{
			list.add(new AbstractMap.SimpleEntry<String, Integer>(key, map.get(key)));
		}
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map sortMap(Map unsortedMap, final boolean ascending) 
	{	 
		List list = new LinkedList(unsortedMap.entrySet());

		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
                                       .compareTo(((Map.Entry) (o2)).getValue()) * (ascending? 1 : -1);
			}
		});
 
		// ricreo la mappa dalla lista (la LinkedHashMap preserva l'ordine d'inserimento)
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) 
		{
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
		}
	}
	
	public static class SymmetricMatrix
	{
		private float[][] matrixRows;

		public SymmetricMatrix(int rows)
		{
			this.matrixRows = new float[rows - 1][];
			for (int r = 0; r < this.matrixRows.length; r++)
			{
				this.matrixRows[r] = new float[r + 1];
			}
		}
		
		/**
		 * 
		 * @param data An array of n elements, n being the current number of rows in the matrix.
		 */
		public void add(float... data)
		{
			if (data.length != this.matrixRows.length + 1);
			
			this.matrixRows = Arrays.copyOf(this.matrixRows, this.matrixRows.length + 1);
			this.matrixRows[this.matrixRows.length - 1] = new float[data.length];
			for (int i = 0; i < data.length; i++) {
				this.matrixRows[this.matrixRows.length - 1][i] = data[i];
			}
		}

		private boolean contains(int[] array, int r)
		{
			for (int v : array) if (v == r) return true; 
			return false;
		}
		
		public void remove(int... rows)
		{
			// deletes the rows from the matrix
			float[][] newMatrixRows = new float[this.matrixRows.length - rows.length][];
			int newRowIndex = 0;
			
			// normalizes rows indexes
			for (int j = 0; j < rows.length; j++)
			{
				rows[j] = rows[j] - 1;
			}
			
			for (int r = 0; r < this.matrixRows.length; r++)
			{
				// skips deleted rows
				if (contains(rows, r)) continue;
				
				List<Float> rowValues = new ArrayList<Float>();
				// scans the original row, skipping values corresponding to deleted rows/columns
				for (int c = 0; c < this.matrixRows[r].length; c++)
				{
					// the rows vector is normalized for ROWS
					if (contains(rows, c - 1)) continue;
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
			// TODO 0 sulla diagonale non � generale
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
}