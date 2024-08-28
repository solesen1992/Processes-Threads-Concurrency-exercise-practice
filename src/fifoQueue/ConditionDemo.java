package fifoQueue;

/**
 * To demonstrate a producer-consumer problem using threads.
 * 
 * This structure implements a simple example of the producer-consumer problem 
 * using multi-threading, where the SharedFiFoQueue serves as a common resource 
 * for communication between the producer and consumer.
 * 
 * This code demonstrates a classic producer-consumer problem using a 
 * thread-safe queue (SharedFiFoQueue) with conditions (Condition objects) to 
 * manage synchronization between producer and consumer threads.
 * 
 * Purpose: 
 * This is the entry point of the program, where the main method is located. 
 * It initializes the shared queue and starts the producer and consumer threads.
 * 
 * Key Components:
 * SharedFiFoQueue sharedQueue: A shared queue instance with a capacity of 10.
 * 
 * Thread producer and Thread consumer: Instances of the Producer and Consumer threads 
 * that operate on the shared queue.
 * 
 * producer.start() and consumer.start(): Start the execution of the producer and consumer threads.
 * 
 * producer.join() and consumer.join(): Wait for both threads to complete before the program exits.
 * */

public class ConditionDemo {
	// Entry point of the program
	public static void main(String[] args) throws InterruptedException {
		// Creating a FIFO query with a capacity of 10.
		// This queue will be shared between the producer and consumer threads.
		SharedFiFoQueue sharedQueue = new SharedFiFoQueue(10);
		
		// Create a producer thread that will produce items and add them to the shared queue
		Thread producer = new Producer(sharedQueue);
		
		// Create a consumer thread that will consume items from the shared queue
		Thread consumer = new Consumer(sharedQueue);
		
		// Start the producer thread.
		// It will begin producing items and adding them to the queue
		producer.start();
		
		// Start the consumer thread. It will begin consuming items from the queue
		consumer.start();
		
		// Wait for the producer thread to finish execution before continuing
		producer.join();
		
		// Wait for the consumer thread to finish executing before continuing
		consumer.join();
	}
}
