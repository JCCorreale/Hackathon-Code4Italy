package it.camera.hackathon.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionUtils {

	private CollectionUtils() {}
	
	/**
	 * Returns a READ ONLY collection from the given iterable.
	 * @param iterable
	 * @return
	 */
	public static <T> Collection<T> fromIterable(Iterable<T> iterable)
	{
		return new IterableCollection<T>(iterable);
	}
	
	private static class IterableCollection<T> implements Collection<T>
	{
		private Iterable<T> iterable;
		
		public IterableCollection(Iterable<T> iterable)
		{
			this.iterable = iterable;
		}

		@Override
		public boolean add(Object e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean addAll(Collection c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean contains(Object o) {
			for (T item : iterable)
				if (item.equals(o))
					return true;
			
			return false;
		}

		@Override
		public boolean containsAll(Collection c) {
			throw new UnsupportedOperationException(); // TODO
		}

		@Override
		public boolean isEmpty() {
			return !this.iterable.iterator().hasNext();
		}

		@Override
		public Iterator iterator() {
			return this.iterable.iterator();
		}

		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean removeAll(Collection c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean retainAll(Collection c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int size() {
			
			Iterator<T> iterator = this.iterable.iterator();
			int size = 0;
			while (iterator.hasNext()) { 
				iterator.next();
				size++;
			}
			return size;
		}

		@Override
		public Object[] toArray() {
			List<T> list = new ArrayList<T>(this);
			for (T item : this.iterable)
				list.add(item);
			return list.toArray();
		}

		@Override
		public Object[] toArray(Object[] a) {
			List<T> list = new ArrayList<T>(this);
			for (T item : this.iterable)
				list.add(item);
			return list.toArray(a);
		}
	}
}