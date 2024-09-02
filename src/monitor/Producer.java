package monitor;

/**
 * Purpose: The Producer class represents a thread that repeatedly 
 * adds (produces) items to a shared buffer. It does this by calling 
 * the Put() method on the Buffer object passed to it.
 * 
 * Thread Execution: When the start() method is called on a Producer object, 
 * the run() method is executed in a new thread, producing 10 items (characters 'A' to 'J') 
 * and adding them to the buffer.
 * */

public class Producer extends Thread {
	// Declare a private member variable 'buffer' of type 'Buffer'. 
	// This will hold a reference to the shared buffer object.
	private Buffer buffer; 

	/**
	 * Constructor for the 'Producer' class that takes a 'Buffer' object as a parameter.
	 * */
	Producer(Buffer buffer) {
		// Initialize the 'buffer' member variable with the buffer passed to the constructor.
		this.buffer = buffer; 
	}

	/**
	 * Override the 'run' method from the 'Thread' class. 
	 * This method contains the code that will be executed when the thread starts.
	 * */
	public void run() {
		// Loop 10 times. This simulates the producer producing 10 items to add to the buffer.
		for (int i = 0; i < 10; i++) { 
			// Call the 'Put' method of the 'Buffer' class to produce (add) an item to the buffer.
			// The item is a character determined by adding 'i' to the 
			// ASCII value of 'A', wrapped around using modulo 26.
			buffer.Put((char) ('A' + i % 26)); 	                                   
		}
	}
}
