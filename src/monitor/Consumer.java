package monitor;

/**
 * Purpose: The Consumer class represents a thread that repeatedly removes (consumes) 
 * items from a shared buffer. It does this by calling the Get() method on the Buffer object 
 * passed to it.
 * 
 * Thread Execution: When the start() method is called on a Consumer object, 
 * the run() method is executed in a new thread, consuming 10 items from the buffer.
 * */

public class Consumer extends Thread {
	// Declare a private member variable 'buffer' of type 'Buffer'. 
	// This will hold a reference to the shared buffer object.
	private Buffer buffer; 

	/**
	 * Constructor for the 'Consumer' class that takes a 'Buffer' object as a parameter.
	 * */
	Consumer(Buffer buffer) {
		// Initialize the 'buffer' member variable with the buffer passed to the constructor.
		this.buffer = buffer;
	}

	/**
	 * Override the 'run' method from the 'Thread' class. 
	 * This method contains the code that will be executed when the thread starts.
	 * */
	public void run() {
		// Loop 10 times. This simulates the consumer consuming 10 items from the buffer.
		for (int i = 0; i < 10; i++) {
			// Call the 'Get' method of the 'Buffer' class to consume (remove) an item from the buffer.
			buffer.Get(); 
		}
	}
}
