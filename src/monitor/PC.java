package monitor;

/**
 * Purpose: The PC class orchestrates the interaction between the 
 * producer and consumer threads using a shared buffer. 
 * The producer fills the buffer with characters, while the consumer removes them.
 * 
 * Execution Flow: The main method starts the producer and consumer threads 
 * and waits for them to complete before printing a final message.
 * 
 * Thread Management: The use of join() ensures that the main thread waits 
 * until both the producer and consumer threads have finished executing, 
 * providing a synchronized conclusion to the program's operation.
 * 
 * Concept:
 * In this scenario, the producer thread quickly fills the shared buffer with 
 * characters and then needs to pause until the consumer thread removes some 
 * characters from the buffer. The issue arises because the producer pauses 
 * (or "waits") while still holding onto the monitor (lock) of the buffer, 
 * which prevents the consumer from accessing the synchronized Get method to 
 * retrieve characters.
 * 
 * To solve this, it's essential for the producer to release the monitor 
 * when the buffer is full, allowing the consumer to take over. Similarly, 
 * when the buffer is empty, the consumer should release the monitor, letting 
 * the producer resume its task.
 * 
 * This coordination between the producer and consumer threads is achieved using 
 * the wait() and notify() (or notifyAll()) methods provided by the Object class.
 * 
 * The wait() method pauses the execution of the calling thread and temporarily releases 
 * the monitor, allowing other threads to acquire it. The paused thread will only resume 
 * when another thread calls notify() or notifyAll() on the same object.
 * 
 * The notifyAll() method wakes up all threads that are waiting on the monitor. 
 * The threads then compete to reacquire the monitor, with one proceeding and the 
 * others returning to the waiting state.
 * 
 * Code courtesy of www.csc.villanova.edu/~mdamian/threads/javamonitors.html
 * */

public class PC {
	public static void main(String[] args) {
		// Create a new 'Buffer' object with a capacity of 4. 
		// This will be shared by the producer and consumer.
		Buffer buffer = new Buffer(4);
		// Create a new 'Producer' object, passing the shared buffer to its constructor.
		Producer producer = new Producer(buffer); 
		// Create a new 'Consumer' object, passing the shared buffer to its constructor.
		Consumer consumer = new Consumer(buffer); 

		// Start the producer thread, which will execute the 'run' method in the 'Producer' class.
		producer.start(); 
		// Start the consumer thread, which will execute the 'run' method in the 'Consumer' class.
		consumer.start(); 
		
		try {
			// Wait for the producer thread to finish execution before proceeding.
			producer.join(); 
			// Wait for the consumer thread to finish execution before proceeding.
			consumer.join(); 
		// Handle the case where the current thread is interrupted while waiting.
		} catch (InterruptedException e) { 
			// TODO Auto-generated catch block
			// Print the stack trace for the interrupted exception (this is a placeholder comment).
			e.printStackTrace(); 
		}
		
		// Print "End..." to the console once both threads have completed execution.
		System.out.println("End..."); 
	}
}
