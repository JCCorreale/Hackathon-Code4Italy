package it.camera.hackathon.utils.tests;

import static org.junit.Assert.*;
import it.camera.hackathon.utils.SymmetricMatrix;
import it.camera.hackathon.utils.SymmetricMatrix.IMatrixAction;

import org.junit.Test;

public class SymmetricMatrixTest {

	@Test
	public void testIterate()
	{
		final int rows = 4;
		final SymmetricMatrix m = new SymmetricMatrix(rows);

		m.iterate(new IMatrixAction() {
		
			int expR = 0;
			int expC = 0;
			
			@Override
			public void action(int r, int c) {
				
				//System.out.println(r + " " + c);
				
				assertEquals(expR, r);
				assertEquals(expC, c);
				
				expC++;
				if (c == r)
				{
					expC = 0;
					expR++;
				}
			}
		});
	}
	
	@Test
	public void testSet()
	{
		final SymmetricMatrix m = new SymmetricMatrix(6);

		m.iterate(new IMatrixAction() {

			int count = 0;
			
			@Override
			public void action(int r, int c) {
				if (r != c)
				{
					m.set(r, c, count);
					count++;
				}
			}
			
		});

		// first row
		assertEquals(0, m.get(0, 0), 0.01);
		// second row
		assertEquals(0, m.get(1, 0), 0.01);
		assertEquals(0, m.get(1, 1), 0.01);
		// third row
		assertEquals(1, m.get(2, 0), 0.01);
		assertEquals(2, m.get(2, 1), 0.01);
		assertEquals(0, m.get(2, 2), 0.01);
		// fourth row
		assertEquals(3, m.get(3, 0), 0.01);
		assertEquals(4, m.get(3, 1), 0.01);
		assertEquals(5, m.get(3, 2), 0.01);
		assertEquals(0, m.get(3, 3), 0.01);
	}
	
	@Test
	public void testFill() {

		final SymmetricMatrix m = new SymmetricMatrix(6);
				
		m.fill(new float[] {
				662,
				877, 295,
				255, 468, 754,
				412, 268, 564, 219,
				996, 400, 138, 869, 669
		});
		
		// first row
		assertEquals(  0, m.get(0, 0), 0.01);
		// second row
		assertEquals(662, m.get(1, 0), 0.01);
		assertEquals(  0, m.get(1, 1), 0.01);
		// third row
		assertEquals(877, m.get(2, 0), 0.01);
		assertEquals(295, m.get(2, 1), 0.01);
		assertEquals(  0, m.get(2, 2), 0.01);
		// fourth row
		assertEquals(255, m.get(3, 0), 0.01);
		assertEquals(468, m.get(3, 1), 0.01);
		assertEquals(754, m.get(3, 2), 0.01);
		assertEquals(  0, m.get(3, 3), 0.01);
		// fifth row
		assertEquals(412, m.get(4, 0), 0.01);
		assertEquals(268, m.get(4, 1), 0.01);
		assertEquals(564, m.get(4, 2), 0.01);
		assertEquals(219, m.get(4, 3), 0.01);
		assertEquals(  0, m.get(4, 4), 0.01);
		// sixth row
		assertEquals(996, m.get(5, 0), 0.01);
		assertEquals(400, m.get(5, 1), 0.01);
		assertEquals(138, m.get(5, 2), 0.01);
		assertEquals(869, m.get(5, 3), 0.01);
		assertEquals(669, m.get(5, 4), 0.01);
		assertEquals(  0, m.get(5, 5), 0.01);
		
		//System.out.println(m);		
	}
	
	@Test
	public void testRemove()
	{
		final SymmetricMatrix m = new SymmetricMatrix(6);
		
		m.fill(new float[] {
				662,
				877, 295,
				255, 468, 754,
				412, 268, 564, 219,
				996, 400, 138, 869, 669
		});

		System.out.println("Before:\n\n" + m);
		
		m.remove(2, 5);

		System.out.println("******************************\n\nAfter rows 2 and 5 removed:\n\n" + m);
		
		m.add(877, 295, 754, 564);
		
		System.out.println("******************************\n\nAfter a row is added:\n\n" + m);
	}
	
	@Test
	public void testRemoveRow0()
	{
		SymmetricMatrix m = new SymmetricMatrix(3);
		
		m.fill(new float[] {
				10,
				20, 30
		});

		System.out.println("Before:\n\n" + m);
		
		m.remove(0);
		
		System.out.println("After row 0 is removed:\n\n" + m);
		
		m = new SymmetricMatrix(6);
		
		m.fill(new float[] {
				662,
				877, 295,
				255, 468, 754,
				412, 268, 564, 219,
				996, 400, 138, 869, 669
		});
	}
	
	@Test
	public void testRemoveRow0And1()
	{
		SymmetricMatrix m = new SymmetricMatrix(6);
		
		m.fill(new float[] {
				662,
				877, 295,
				255, 468, 754,
				412, 268, 564, 219,
				996, 400, 138, 869, 669
		});
		
		System.out.println("Before:\n\n" + m);
		
		m.remove(0, 1);
		
		System.out.println("\n\nAfter row 0 and 1 are removed:\n\n" + m);
	}
}