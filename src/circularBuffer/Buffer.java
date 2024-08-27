package circularBuffer;

/**
 * The Buffer class provides thread-safe operations for reading and writing data 
 * in a circular buffer, using Java's synchronization mechanisms (synchronized, wait(), notifyAll()). 
 * The CircularBuffer class demonstrates how to use this buffer with producer and 
 * consumer threads, ensuring correct synchronization between them.
 * 
 * The Buffer class implements a circular buffer with synchronization 
 * to manage concurrent access by producer and consumer threads.
 * 
 * The Buffer class simulates a circular buffer for inter-thread communication.
 * 
 * Write and Read Operations:
 * The producer writes to the buffer, advancing the write position (PosW) each time 
 * it writes a new value.
 * The consumer reads from the buffer, advancing the read position (PosR) each time 
 * it reads a value.
 * 
 * Buffer Wrap-Around:
 * The buffer size is 4, so when the write or read position reaches the end of the buffer, 
 * it wraps around to the beginning (PosW and PosR modulo 4).
 * This is evident from lines like "Write position: 0" after "Write position: 3," 
 * indicating the wrap-around.
 * 
 * The sequence continues until both the producer has written and the consumer has read all 20 values.
 * */

public class Buffer {
	// BufferSize defines the capacity of the buffer
	private int BufferSize = 4;
	// Container is the array that holds the buffer's data
	private int[] Container = new int[BufferSize];
	// PositionRead is the position from where the data will be read
	private int PositionRead = 0;
	// PositionWrite is the position where data will be written
	private int PositionWrite = 0;
	
	/**
	 * The Read method retrieves and removes an item from the buffer.
	 * It's synchronized to prevent race conditions between threads.
	 * */
	public synchronized int Read() {
		// An infinite loop to keep attempting to read from the buffer
		while(true) {
			if (PositionRead == PositionWrite) {
				try {
					// Wait until the buffer has data to read.
					wait();
				} catch (InterruptedException e) {
					// Handle the interrupted exception
					e.printStackTrace();
				}
			} else {
				// Retrieve the value of the current read position
				int value = Container[PositionRead];
				
				// Move PositionRead to the next position, wrapping around if necessary
				PositionRead = (PositionRead + 1) % BufferSize;
				
				// Print the current read position
				System.out.println("Read position: " + PositionRead);
				
				// Notify all waiting threads that the buffer state has changed
				notifyAll();
				
				// Return the value that was read
				return value;
			}
		}
	}
	
	/**
	 * The Write method adds an item to the buffer.
	 * It's synchronized to prevent race conditions between threads.
	 * */
	public synchronized void Write(int value) {
		// An infinite loop to keep attempting to write to the buffer
		while (true) {
			// If the next write position equals to the read position, the buffer is full
			if (((PositionWrite + 1) % BufferSize) == PositionRead) {
				try {
					// Wait until there is space to write.
					wait();
				} catch (InterruptedException e) {
					// Handle the interrupted exception
					e.printStackTrace();
				}
			} else {
				// Write the value to the current write position
				Container[PositionWrite] = value;
				
				// Move the PositionWrite to the next position, wrapping around if necessary.
				PositionWrite = (PositionWrite + 1) % BufferSize;
				
				// Print the current write position
				System.out.println("Write position: " + PositionWrite);
				
				// Notify all waiting threads that the buffer state has changed.
				notifyAll();
				
				// Exit the loop after successfully writing the value.
				return;
			}
		}
	}
}
