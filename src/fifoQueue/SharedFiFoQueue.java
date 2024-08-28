package fifoQueue;

import java.util.concurrent.locks.Condition;  // Import Condition for thread synchronization.
import java.util.concurrent.locks.Lock;       // Import Lock for locking mechanisms.
import java.util.concurrent.locks.ReentrantLock; // Import ReentrantLock, an implementation of the Lock interface.

/**
 * The SharedFiFoQueue class, which represents a thread-safe First-In-First-Out (FIFO) queue.
 * 
 * Purpose: 
 * This class implements a thread-safe First-In-First-Out (FIFO) queue using an array 
 * as the underlying storage. It manages concurrency with locks and conditions to ensure 
 * that only one thread can modify the queue at a time.
 * 
 * Key Components:
 * Object[] elems: The array that holds the elements of the queue.
 * 
 * int PosW and int PosR: Pointers that track the positions for writing and reading 
 * in the queue, respectively. They wrap around using modulo arithmetic to maintain 
 * the circular nature of the queue.
 * 
 * Lock lock: A ReentrantLock used to ensure that only one thread can add or remove elements at any time.
 * 
 * Condition isEmpty and Condition isFull: Conditions used to signal when the queue 
 * is empty or full, so that producers and consumers can wait or proceed accordingly.
 * 
 * Methods:
 * add(Object elem): Adds an element to the queue. If the queue is full, the producer 
 * waits until space is available.
 * 
 * remove(): Removes and returns an element from the queue. If the queue is empty, 
 * the consumer waits until an element is available.
 * */

public class SharedFiFoQueue {
	// An array to store the elements of the queue
	private Object[] elementsOfTheQueue = null;
	
	// Position pointers: PosWrite for writing and PosRead for reading
	private int PositionWrite = 0; // Write position index
	private int PositionRead = 0; // Read position index
	
	// A lock to ensure thread-safe operations on the queue
	private Lock lock = new ReentrantLock();
	
	// Conditions for managing queue state.
    private Condition isEmpty = lock.newCondition();  // Condition to wait on when the queue is empty.
    private Condition isFull = lock.newCondition();   // Condition to wait on when the queue is full.
    
    
    /** 
     * Constructor to initialize the queue with a specified capacity.
     * @param capacity
     */
	public SharedFiFoQueue(int capacity) {
		super();
		this.elementsOfTheQueue = new Object[capacity]; // Initialize the queue array with the given capacity.
	}
	
	/**
	 * Method to add an element to the queue
	 * */
	public void add(Object element) throws InterruptedException {
		// Acquire the lock to ensure exclusive access to the queue.
		lock.lock();
		
		try {
			// Wait until there is space in the queue if it's full
			while (((PositionWrite + 1) % elementsOfTheQueue.length) == PositionRead) {
				// Release the lock temporarily and wait for the isFull condition.
				isFull.await();
			}
			
			// Place the element in the queue at the current write position
			elementsOfTheQueue[PositionWrite] = element;
			
			// Update the write position index, using modulo to wrap around if necessary
			PositionWrite = (PositionWrite + 1) % elementsOfTheQueue.length;
			
			// Signal the consumer that there is data available in the queue
			isEmpty.signal(); // Notify any waiting threads that the queue is no longer empty.
		} finally {
			// Always release the lock to avoid deadlocks
			lock.unlock();
		}
	}
	
	/**
	 * Method to remove an element from the queue
	 * */
	public Object remove() throws InterruptedException {
		// Initialize a variable to store the removed element.
		Object element = null;
		
		// Acquire the lock to ensure exclusive access to the queue.
		lock.lock();
		
		try {
			// Wait until there is an element in the queue if it is empty
			while (PositionRead == PositionWrite) {
				// Release the lock temporarily and wait for the isEmpty condition.
				isEmpty.await();
			}
			
			// Retrieve the element from the queue at the current read position
			element = elementsOfTheQueue[PositionRead];
			
			// Update the read position index, using modulo to wrap around if necessary
			PositionRead = (PositionRead + 1) % elementsOfTheQueue.length;
			
			// Signal the producer that there is space available in the queue
			isFull.signal(); // Notify any waiting threads that the queue is no longer full.
		}
		finally {
			lock.unlock();  // Always release the lock to avoid deadlocks.
		}
		// Return the removed element
		return element;
	}	
}
