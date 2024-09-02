package monitor;

/**
 * Purpose: The Buffer class is designed to manage a circular buffer shared 
 * by a producer and a consumer thread. It synchronizes access to the buffer 
 * using Java's monitor mechanism (the synchronized keyword) and coordinates 
 * the two threads using the wait() and notify() methods.
 * 
 * Synchronization: The Put() method is used by the producer to add characters 
 * to the buffer, while the Get() method is used by the consumer to remove characters. 
 * Both methods are synchronized to ensure that only one thread can access the buffer at a time.
 * 
 * Thread Coordination: The wait() method is used to pause a thread when 
 * the buffer is either full (for the producer) or empty (for the consumer), 
 * releasing the monitor to allow the other thread to make progress. 
 * The notify() method is used to wake up a waiting thread, ensuring smooth 
 * communication between the producer and consumer.
 * */

public class Buffer {
	// Declare an array of characters to hold the buffer's content.
	private char[] buffer; 
	// Initialize counters: 'count' tracks the number of items in the buffer, 
	// 'in' is the position for the next insertion, and 'out' is the position for the next removal.
	private int count = 0;
	private int in = 0;
	private int out = 0; 

	/** 
	 * Constructor for the 'Buffer' class, taking an integer 'size' as a parameter.
	 * */
	Buffer(int size) { 
		// Initialize the 'buffer' array with the specified size.
		buffer = new char[size]; 
	}

	/** 
	 * Synchronized method to add a character 'c' to the buffer, 
	 * ensuring mutual exclusion.
	 * */
	public synchronized void Put(char character) { 
		// While the buffer is full (i.e., 'count' equals the buffer's length), the producer must wait.
		while (count == buffer.length) { 
			try {
				// Call 'wait()', causing the producer thread to release 
				// the monitor and suspend execution until notified.
				wait(); 
			// Catch the 'InterruptedException' in case the thread is interrupted while waiting.
			} catch (InterruptedException e) { 
				// Empty catch block: handle the exception silently.
			} finally {
				// The finally block is empty here and will execute after the 
				// try/catch, regardless of exceptions.
			}
		}
		// Print a message indicating that a character is being produced.
		System.out.println("Producing " + character + " ..."); 
		// Insert the character 'c' into the buffer at the 'in' position.
		buffer[in] = character; 
		// Update 'in' to the next position, wrapping around to the beginning if necessary.
		in = (in + 1) % buffer.length; 
		// Increment the 'count' to reflect the addition of a new item.
		count++; 
		// Call 'notify()' to wake up a waiting thread, likely the consumer, allowing it to proceed.
		notify(); 
	}

	/** 
	 * Synchronized method to remove and return a character 
	 * from the buffer, ensuring mutual exclusion.
	 * */
	public synchronized char Get() { 
		// While the buffer is empty (i.e., 'count' equals 0), the consumer must wait.
		while (count == 0) { 
			try {
				// Call 'wait()', causing the consumer thread to release 
				// the monitor and suspend execution until notified.
				wait(); 
			// Catch the 'InterruptedException' in case the thread is interrupted while waiting.
			} catch (InterruptedException e) { 
				// Empty catch block: handle the exception silently.
			} finally {
				// The finally block is empty here and will execute 
				// after the try/catch, regardless of exceptions.
			}
		}
		// Retrieve the character from the buffer at the 'out' position.
		char character = buffer[out]; 
		// Update 'out' to the next position, wrapping around to the beginning if necessary.
		out = (out + 1) % buffer.length; 
		// Decrement the 'count' to reflect the removal of an item.
		count--; 
		// Print a message indicating that a character is being consumed.
		System.out.println("Consuming " + character + " ..."); 
		// Call 'notify()' to wake up a waiting thread, likely the producer, allowing it to proceed.
		notify(); 
		// Return the consumed character.
		return character; 
	}
}
