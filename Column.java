/**
 * This is Column Class.
 * 
 * @author laraibpervez
 *
 * @param <T> of type T
 */
public class Column<T> {
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/**
	 * size.
	 */
	private int size;
	/**
	 * Capacity.
	 */
	private static final int DEFAULT_CAPACITY = 2;
	/**
	 * date of type.
	 */
	private T[] data;

	/**
	 * Constructor.
	 */
	@SuppressWarnings("unchecked")
	public Column() {
		// Constructor
		if (size < 0 || size >= DEFAULT_CAPACITY) {
			throw new IndexOutOfBoundsException();
		}
		// Initial capacity of the storage should be DEFAULT_CAPACITY
		data = (T[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	/**
	 * Overloaded Constructor.
	 * @param initialCapacity initial Capacity
	 */
	@SuppressWarnings("unchecked")
	public Column(int initialCapacity) {
		// Constructor
		// Initial capacity of the storage should be initialCapacity
		// Throw IllegalArgumentException if initialCapacity is smaller than 1
		// Use this _exact_ error message for the exception
		// (quotes are not part of the message):
		// "Capacity must be positive."
		if (initialCapacity < 1) {
			throw new IllegalArgumentException("Capacity must be positive.");
		} else {
			data = (T[]) new Object[initialCapacity];
			size = 0;
		}
	}

	/**
	 * returning size.
	 * 
	 * @return integer
	 */
	public int size() {
		// Report the current number of elements
		// O(1)
		return size;
	}

	/**
	 * returning the length of data.
	 * 
	 * @return Integer
	 */
	public int capacity() {
		// Report max number of elements before expansion
		// O(1)
		return data.length;
	}

	/**
	 * Set method.
	 * 
	 * @param index index
	 * @param value value
	 * @return value
	 */
	public T set(int index, T value) {
		// Change the item at the given index to be the given value.
		// Return the old item at that index.
		// Note: You cannot add new items with this method.
		// O(1)
		// For an invalid index, throw an IndexOutOfBoundsException
		// Use this code to produce the correct error message for
		// the exception (do not use a different message):
		// "Index: " + index + " out of bounds!"
		if (index < 0 || index >= capacity() || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}

		T val = data[index];
		data[index] = value;

		return val;
	}

	/**
	 * Get Method.
	 * @param index index
	 * @return Integer
	 */
	public T get(int index) {
		// Return the item at the given index
		// O(1)
		// Use the exception (and error message) described in set()
		// for invalid indices.
		if (index < 0 || index >= capacity()) {
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}

		return data[index];

	}

	/**
	 * Add value at the end of the storage.
	 * 
	 * @param value value
	 */
	@SuppressWarnings("unchecked")
	public void add(T value) {
		// Append an element to the end of the storage.
		// Double the capacity if no space available.
		// Amortized O(1)

		if (size == capacity()) {
			T newData[] = (T[]) new Object[2 * capacity()];

			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}

		data[size] = value;
		size++;
	}

	/**
	 * Add item.
	 * 
	 * @param index index
	 * @param value value
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T value) {
		// Insert the given value at the given index. Shift elements if needed,
		// double capacity if no space available, throw an exception if you cannot
		// insert at the given index. You _can_ append items with this method.
		// For the exception, use the same exception and message as set() and
		// get()... however remember that the condition of the exception is
		// different (different indexes are invalid).
		// O(N) where N is the number of elements in the storage
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}
		// double capacity
		if (size == capacity()) {
			T newData[] = (T[]) new Object[2 * capacity()];
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}
		if (size < capacity()) {
			if (index >= size) {
				data[index] = value;
				size++;
			} else {
				for (int i = size; i > index; i--) {
					data[i] = data[i - 1];
				}
				data[index] = value;
				size++;
			}
		}
	}

	/**
	 * Delete Item.
	 * 
	 * @param index index
	 * @return Integer
	 */
	@SuppressWarnings("unchecked")
	public T delete(int index) {
		// Remove and return the element at the given index. Shift elements
		// to remove the gap. Throw an exception when there is an invalid
		// index (see set(), get(), etc. above).
		// Halve capacity of the storage if the number of elements falls
		// below 1/3 of the capacity. Capacity should NOT go below DEFAULT_CAPACITY.
		// O(N) where N is the number of elements currently in the storage
		if (index >= capacity() || index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}

		T deleteditem = data[index];
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		size--;

		if (size <= (capacity() / 3)) {
			int newCapacity = capacity() / 2;
			if (newCapacity < DEFAULT_CAPACITY) {
				newCapacity = DEFAULT_CAPACITY;
			}
			T newData[] = (T[]) new Object[newCapacity];
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}

		return deleteditem;

	}

	// ******************************************************
	// ******* BELOW THIS LINE IS TESTING CODE *******
	// ******* Edit it as much as you'd like! *******
	// ******* Remember to add JavaDoc *******
	// ******************************************************

	/**
	 * Override toString method.
	 * @return String
	 */
	public String toString() {
		// This method is provided for debugging purposes
		// (use/modify as much as you'd like), it just prints
		// out the column for easy viewing.
		StringBuilder s = new StringBuilder("Column with " + size() + " items and a capacity of " + capacity() + ":");
		for (int i = 0; i < size(); i++) {
			s.append("\n [" + i + "]: " + get(i));
		}
		return s.toString();

	}

	/**
	 * Main Method.
	 * @param args argument
	 */
	public static void main(String args[]) {
		// These are _sample_ tests. If you're seeing all the "yays" that's
		// an excellent first step! But it might not mean your code is 100%
		// working... You may edit this as much as you want, so you can add
		// own tests here, modify these tests, or whatever you need!

		// create a column of integers
		Column<Integer> nums = new Column<>();
		if ((nums.size() == 0) && (nums.capacity() == 2)) {
			System.out.println("Yay 1");
		}

		// append some numbers
		for (int i = 0; i < 3; i++) {
			nums.add(i * 2);
		}

		if (nums.size() == 3 && nums.get(2) == 4 && nums.capacity() == 4) {
			System.out.println("Yay 2");
		}
		// create a column of strings
		Column<String> msg = new Column<>();

		// insert some strings
		msg.add(0, "world");
		msg.add(0, "hello");
		msg.add(1, "new");
		msg.add(3, "!");

		// checking
		if (msg.get(0).equals("hello") && msg.set(1, "beautiful").equals("new") && msg.size() == 4
				&& msg.capacity() == 4) {
			System.out.println("Yay 3");
		}

		// delete
		if (msg.delete(1).equals("beautiful") && msg.get(1).equals("world") && msg.size() == 3) {
			System.out.println("Yay 4");
		}

		// shrinking
		nums.add(100);
		nums.add(0, -10);
		if (nums.delete(0) == -10 && nums.delete(1) == 2 && nums.delete(2) == 100 && nums.size() == 2
				&& nums.capacity() == 4) {
			System.out.println("Yay 5");
		}

	}
}
