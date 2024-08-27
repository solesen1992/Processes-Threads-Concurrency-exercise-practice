package circularBuffer;

/**
 * The CircularBuffer class demonstrates the use of the Buffer class 
 * with producer and consumer threads.
 * 
 * The CircularBuffer class manages the producer and consumer threads.
 * 
 * The sequence is that the producer writes first, followed by the consumer reading 
 * from the buffer. This ensures the buffer is not empty when the consumer tries to read from it.
 * 
 * Producer Starts First:
 * In the main method, two threads are started: one for the producer and one for the consumer.
 * The producer thread begins by writing the first value to the buffer.
 * 
 * Initial Buffer State:
 * The buffer is initially empty, meaning that PosR (read position) is equal to PosW (write position).
 * When the consumer tries to read, it will notice that PosR == PosW (buffer is empty) 
 * and call wait() to block until the producer writes something.
 * 
 * First Write Operation:
 * The producer thread writes the first value to the buffer, updating PosW (write position) 
 * and then calls notifyAll(), which wakes up the consumer thread.
 * Now, the buffer contains one element, and PosW has advanced by one position.
 * 
 * First Read Operation:
 * The consumer thread, now awake from the notifyAll(), proceeds to read the value 
 * from the buffer, updating PosR (read position) accordingly.
 * */

public class CircularBuffer {
	// MyBuffer is a shared instance of the Buffer class
	static Buffer MyBuffer = new Buffer();
	
	// The entry point of the program
	public static void main(String[] args) {
		// Create and start the produced thread
		Thread producerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// Print that the producer has started running
				System.out.println("Producer running");
				
				// Loop to produce 20 items
				for (int i = 0; i < 20; i++) {
					// Write each item to the buffer
					MyBuffer.Write(i);
					
					// Print the value of what has written
					System.out.println("Producer wrote: " + i);
				}
			}
		});
		
		// Create and start the consumer thread
		Thread consumerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// Print that the customer has started running
				System.out.println("Consumer running");
				
				// Loop to consume 20 items
				for (int i = 0; i < 20; i++) {
					// Read an item from the buffer and store it in value
					int value = MyBuffer.Read();
					
					// Print the value that was read
					System.out.println("Consumer read: " + value);
				}
			}
		});
		
		// Start the producer thread (the one writing)
		producerThread.start();
		
		// Start the customer thread (the one reading)
		consumerThread.start();
	}
}
