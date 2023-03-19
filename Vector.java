/**
 *@author Brandon Slusser
 */
import java.util.Arrays;
import java.util.Iterator;

public class Vector<E> extends AbstractListADT<E> implements Iterable<E> {
	
	protected E[] array;
	private int capacity;
	final int DEFAULT_CAPACITY = 10;
	
	//Constructor with no parameters that initializes the vector to a capacity of 10 elements
	@SuppressWarnings("unchecked")
	public Vector() {
		array = (E[]) new Object[10];
		size = 0;
		capacity = DEFAULT_CAPACITY;
	}
	
	//Constructor getting the initial capacity of the vector from the parameter
	@SuppressWarnings("unchecked")
	public Vector(int initialCapacity) {
		array = (E[]) new Object[initialCapacity];
		size = 0;
		capacity = initialCapacity;
	}
	
	//Constructor that makes a copy of the vector from the parameter
	@SuppressWarnings("unchecked")
	public Vector(Vector<E> v) {
		array = (E[]) new Object[10];
		size = v.size;
		for(int i = 0; i<size; i++) {
			array[i] = v.at(i);
		}
		
		}
		
	
	private void verifyCapacity(int need) {
		if(need>= array.length)
			resize();
	}
	
	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return capacity;
	}

	 //A reference to the first element in the vector
	@Override
	public E front() {
		// TODO Auto-generated method stub
		return array[0];
	}

	 //A reference to the last element in the vector
	@Override
	public E back() {
		// TODO Auto-generated method stub
		return array[size-1];
	}

	//A reference to the memory array used internally by the vector to store its owned elements
	@Override
	public E[] data() {
		// TODO Auto-generated method stub
	  return array;
	}
	
    //Add the element into a vector from the back
	@Override
	public void pushback(E element) {
		// TODO Auto-generated method stub
		verifyCapacity(size+1);
		array[size] = element;
		size++;
		
		
		
		
	}

	//Removes an element from a vector from the back
	@Override
	public E popback() {
		if(array.length > 0) {
			--size;
			return array[size];
		}
		return null;
	}

	//Inserts a new element before the element at the specified position
	@SuppressWarnings("unchecked")
	@Override
	public void insert(int insertPosition, E element) {
		// TODO Auto-generated method stub
			verifyCapacity(size+1);

		E[] newarray = (E[]) new Object[array.length];
		if(size>1) {
		for(int i = 0; i<insertPosition; i++) {
			newarray[i] = array[i];
		}
		}
		
		newarray[insertPosition] = element;
		if(size>1) {
		for(int i = insertPosition + 1; i<newarray.length; i++) {
			newarray[i] = array[i-1];
		}
		}
		
		++size;
		array = newarray;
		
	}
	
    //Removes elements from a container from a specified position
	@SuppressWarnings("unchecked")
	@Override
	public void erase(int position) {
		// TODO Auto-generated method stub
		if(array.length>0) {
			size--;
			E[] newarray = (E[]) new Object[size+1];
			for(int i = 0; i < position; i++) {
				newarray[i] = array[i];
			}
			
			for(int i = position + 1; i < size+1; i++) {
				newarray[i-1] = array[i];
			}
			array = newarray;
		}
	
					
				}

    //Removes elements from a container from the startRangePosition to endRangePosition
	@Override
	public void erase(int startRangePosition, int endRangePosition) {
		// TODO Auto-generated method stub
		if(endRangePosition> size || startRangePosition< 0 ) {
			throw new ArrayIndexOutOfBoundsException();
		}
		for(int i = startRangePosition; i< endRangePosition; i++) {
			erase(startRangePosition);
				
			}
	}

	 //Used to swap the contents of one vector with another vector of the same type
	@Override
	public void swap(Vector<E> other) {
		// TODO Auto-generated method stub
		E[] temp = (E[]) new Object[array.length];
		temp = Arrays.copyOf(array, array.length);
		resize(other.size);
		for(int i = 0; i< array.length; i++) {
			array[i] = other.at(i);
		}
	
	}

	//Reduces the capacity of the container to fit its size and destroys all elements beyond the capacity
	@Override
	public void shrinkToFit() {
		// TODO Auto-generated method stub
		array = Arrays.copyOf(array, capacity);
		resize(size);
		capacity=size;
		
		
		
	}

	@SuppressWarnings("unchecked")
	public void resize() {
		
		// TODO Auto-generated method stub
		E[] temp = null;
		capacity = array.length * 2;
		try {
			temp = (E[]) new Object[array.length * 2];
			
			temp = Arrays.copyOf(array, temp.length);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			
		}
		this.array = temp;
		
	}
	
 
	//An iterator pointing to the first element
	@Override
	public Iterator<E> begin() {
		// TODO Auto-generated method stub
		return new VectorIterator<E>();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		
		return new VectorIterator<E>();
	}

	//Gets the element at the index
	@Override
	public E at(int index) {
		// TODO Auto-generated method stub
		if(index<size) {
			return array[index];
		}
		else {
			return null;
		}
		
	}
	
	private class VectorIterator<E> implements Iterator<E>{
		int pointer = 0;
		
		public boolean hasNext() {
			return pointer < size;
		}
		
		@SuppressWarnings("unchecked")
		public E next() {
			if (!hasNext())
				throw new java.lang.NullPointerException();
			else {
				E object = (E) array[pointer];
				pointer++;
				return object;
			}
		}
	
	}

	@Override
	public void resize(int newSize) {
		// TODO Auto-generated method stub
		verifyCapacity(newSize);
		size = newSize;
	}
	
	

}
