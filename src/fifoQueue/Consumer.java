package fifoQueue;

import java.util.HashSet; // Import HashSet, a collection that stores unique elements.
import java.util.Set; // Import Set, an interface that represents a collection of unique elements.

/**
 * Define the Consumer class that extends the Thread class, representing a consumer 
 * in a producer-consumer scenario.
 * 
 * Purpose: 
 * This thread consumes words from the shared queue, counts the total number of words, 
 * and tracks the number of unique words using a Set.
 * 
 * Key Components:
 * Set knownObjects: A HashSet that keeps track of the unique words read from the queue.
 * 
 * int total and int unique: Variables to count the total number of words and 
 * the number of unique words, respectively.
 * 
 * run() method: Continuously removes elements from the queue until it encounters 
 * a null (which signals the end of the input). It updates the total and unique 
 * counts and prints the result at the end.
 * 
 * Error handling: Catches InterruptedException to handle possible thread interruption.
 * */

public class Consumer extends Thread {
	// A Set to store and track the unique objects (words) consumed from the queue
	private Set knownObjects = new HashSet();
	// An integer to keep track of the total number of words consumed from the queue
	private int total = 0;
	// An integer to keep track of the number of unique words consumed by the queue
	private int unique = 0;
	// A reference to the shared queue from which this consumer will consume words
	private SharedFiFoQueue fifoQueue;

	// Constructor for the Consumer class, initializes the queue reference
	public Consumer(SharedFiFoQueue fifoQueue) {
		super();
		this.fifoQueue = fifoQueue;
	}
	
	// The run method contains the logic that will be executed when the thread starts.
	@Override
	public void run() {
		try {
			// Start an infinite loop to continuously consume items from the queue
			do {
				// Remove an object from the queue. This is a blocking operation, waiting if necessary.
	               Object object = fifoQueue.remove();
	               
	               // If the queue returns null, it indicates no more items to consume, so break the loop.
	               if (object == null) {
	            	   break;
	               }
	               
	               // Increment the total count of consumed words.
	                total++;
	            
	                // Check if the consumed word (object) is not already in the set of known (unique) objects.
	                if(!knownObjects.contains(object)) {
	                    // If the word is unique, increment the unique word count.
	                    unique++;
	                    
	                    // Add the new unique word to the set of known objects
	                    knownObjects.add(object);
	                }
	                
	                // Print a message indicating that a word was read from the queue
	                System.out.println("[Consumer] read the element: " + object.toString());
	                
		} while(true); // Continue the loop until a null object is received.
			
	} catch (InterruptedException exception) {
		// Handle the exception if the thread is interrupted while waiting or sleeping.
        System.err.println("An InterruptedException was caught: " + exception.getMessage());
        exception.printStackTrace();
		}
		
		// After the loop ends, print the total number of words read and the number of unique words.
        System.out.println("\n[Consumer] " + total + " words read counting " + unique + " unique words");
	}
}
