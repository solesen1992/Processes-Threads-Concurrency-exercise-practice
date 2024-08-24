package semaphores;

import java.util.concurrent.*;

/**
 * The MyThread class extends Thread and represents a thread that can act as a producer or consumer. 
 * It has two semaphores semPro and semCon, and a String threadName. 
 * 
 * The semPro semaphore is used to synchronize the producer and 
 * the semCon semaphore is used to synchronize the consumer.
 * 
 * When the run() method is called on a MyThread object:
 * If the thread is a producer (its name is "Producer"), it increments the 
 * Shared count by 1, prints its name and the count, and then releases the semCon semaphore. 
 * This allows the consumer to proceed. It then sleeps for 10 milliseconds to allow a 
 * context switch to the consumer thread.
 * 
 * 
 * If the thread is a consumer (its name is "Consumer"), it simply prints its name 
 * and the Shared count, and then releases the semPro semaphore. This allows the 
 * producer to increment the Shared count again.
 * */

public class MyThread extends Thread {
	// A semaphore to control the producer thread's access to the shared resource.
	Semaphore semProducer; 
	// A semaphore to control the consumer thread's access to the shared resource.
    Semaphore semConsumer; 
    // The name of the thread, either "Producer" or "Consumer".
    String threadName; 
	
	/**
	 * Constructor
	 * */
	public MyThread(Semaphore semProducer, Semaphore semConsumer, String threadName) {
		super(threadName);
		this.semProducer = semProducer;
		this.semConsumer = semConsumer;
		this.threadName = threadName;
	}
	
	@Override
	// The run method, where the thread's execution logic is defined.
    public void run() { 
		// Checks if the current thread is the producer.
        if (this.getName().equals("Producer")) { 
        	// Prints a message indicating the producer thread is starting.
            System.out.println("Starting " + threadName); 
            // Loop that runs 5 times, representing 5 production cycles.
            for (int i = 0; i < 5; i++) { 
                try {
                    // Wait until we may write (initially we are allowed)
                	// Logs an attempt to acquire the producer semaphore.
                    System.out.println("Producer trying to acquire semProducer (permits: " + semProducer.availablePermits() + ")"); 
                    // Acquires the producer semaphore, blocking if no permit is available.
                    semProducer.acquire(); 
                    // Logs that the producer semaphore has been acquired.
                    System.out.println("Producer acquired semProducer"); 
                    // Increments the shared resource count.
                    SharedResource.count++; 
                    // Logs the value written by the producer.
                    System.out.println(threadName + " Writes: " + SharedResource.count); 
                    
                    // Signal the "Consumer" that a value is ready
                    // Logs an attempt to release the consumer semaphore.
                    System.out.println("Producer releasing semConsumer (permits: " + semConsumer.availablePermits() + ")");
                    // Releases the consumer semaphore, allowing the consumer to proceed.
                    semConsumer.release(); 
                    // Logs that the producer semaphore has been released.
                    System.out.println("Producer released semProducer"); 
                    
                    // Pauses the thread for 10 milliseconds to simulate some delay in the production process.
                    Thread.sleep(10); 
                
                // If something goes wrong
                    // Catches and handles any interruption during the semaphore operations or sleep.
                } catch (InterruptedException exc) { 
                	// Logs the exception message.
                    System.out.println("Fail: " + exc); 
                }
            }
            // Logs that the producer thread has finished its execution.
            System.out.println("Ending " + threadName); 
        }
        // If the thread is not the producer, it is the consumer.
        else { 
            System.out.println("Starting: " + threadName); // Prints a message indicating the consumer thread is starting.
            for (int i = 0; i < 5; i++) { // Loop that runs 5 times, representing 5 consumption cycles.
                try {
                    // Waiting for a value to be present (initially: Wait)
                	// Logs an attempt to acquire the consumer semaphore.
                    System.out.println("Consumer trying to acquire semConsumer (permits: " + semConsumer.availablePermits() + ")"); 
                    // Acquires the consumer semaphore, blocking if no permit is available.
					semConsumer.acquire(); 
                    // Logs that the consumer semaphore has been acquired.
                    System.out.println("Consumer acquired semConsumer"); 

                    // Logs the value read by the consumer.
                    System.out.println(threadName + " Reads: " + SharedResource.count); 
                    
                    // Signal the "Producer" that the value was read
                    // Logs an attempt to release the producer semaphore.
                    System.out.println("Consumer releasing semProducer (permits: " + semProducer.availablePermits() + ")"); 
                    // Additional log before the release.
                    System.out.println("Consumer releasing semProducer"); 
                    // Releases the producer semaphore, allowing the producer to proceed.
                    semProducer.release(); 
                    // Logs that the consumer semaphore has been released.
                    System.out.println("Release: " + threadName); 
                }
                // Catches and handles any interruption during the semaphore operations.
                catch (InterruptedException exc) { 
                    // Logs the exception message.
                	System.out.println("Fail happened: " + exc); 
                }
            }
            // Logs that the consumer thread has finished its execution.
            System.out.println("Ending: " + threadName); 
        }
	}
}
