package semaphores;

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

}
