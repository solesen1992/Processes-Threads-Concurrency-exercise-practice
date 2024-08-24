package semaphores;

import java.util.concurrent.Semaphore;

/**
 * The SemaphoreDemo class is the driver class. It creates two semaphores semPro and semCon 
 * with initial permits of 1 and 0 respectively. Then it creates two MyThread objects 
 * as the producer and consumer, starts them, and waits for them to finish. 
 * At the end, it prints the Shared count.
 * 
 * In this way, the producer and consumer threads alternatively access the Shared count, 
 * ensuring that the consumer does not read the same value twice and the producer does not 
 * write twice in a row without the consumer having read the value.
 * 
 * Semaphores are a synchronization mechanism used in programming to control access 
 * to shared resources by multiple threads. They function as signals that can allow 
 * or prevent threads from performing certain operations based on the availability of the resource.
 * 
 * A semaphore can have a counter that represents the number of permits to a resource. 
 * When a thread wants to access the resource, it tries to "acquire" a permit. 
 * If a permit is available (the counter is greater than 0), the counter is decremented 
 * and the thread is allowed access to the resource. 
 * If no permits are available (the counter is 0), the thread is blocked 
 * until another thread "releases" a permit by incrementing the counter.
 * 
 * Semaphores are used to prevent race conditions, where multiple threads compete 
 * for access to a shared resource, which can lead to inconsistent or unpredictable results. 
 * By using semaphores, you can ensure that only a certain number of threads 
 * can access the resource at a time, helping to maintain data integrity 
 * and synchronize threads effectively.
 * 
 * This program demonstrates the use of semaphores by implementing the classic Producer/Consumer problem.
 * - The `Shared` class has a static integer `count` that is shared among the threads.
 * 
 * - The `MyThread` class extends `Thread` and represents a thread that can act as a 
 * producer or consumer. It uses two semaphores, `semPro` and `semCon`, to synchronize 
 * access between the producer and consumer.
 * 
 * - The producer thread increments the shared counter and then allows the consumer thread 
 * to proceed by releasing the consumer semaphore. The consumer thread reads the shared 
 * counter value and then releases the producer semaphore, allowing the producer to 
 * increment the shared counter again.
 * 
 * This alternating access ensures that the consumer does not read the same value twice 
 * and the producer does not write twice in a row without the consumer having read the value. 
 * In this way, semaphores effectively manage the synchronization 
 * between the producer and consumer threads.
 * */

public class SemaphoreDemo {
	public static void main(String[] args) throws InterruptedException {
		// Creating a Semaphore object
		// Creates a Semaphore with 1 permit, allowing the producer to acquire it immediately. 
		//It ensures only one permit is available at a time for the producer.
        Semaphore semProducer = new Semaphore(1); 
        // Creates a Semaphore with 0 permits, initially blocking the consumer from 
        //proceeding until the producer releases a permit.
        Semaphore semConsumer = new Semaphore(0); 

        // Creating two threads named "Producer" and "Consumer"
        // Creates a new thread (mt1) of type MyThread, passing semProducer and 
        // semConsumer semaphores, and the name "Producer".
        MyThread mt1 = new MyThread(semProducer, semConsumer, "Producer"); 
        // Creates another thread (mt2) of type MyThread, passing semProducer and 
        // semConsumer semaphores, and the name "Consumer".
        MyThread mt2 = new MyThread(semProducer, semConsumer, "Consumer"); 

        // Starting threads
        mt1.start(); // Starts the "Producer" thread (mt1), causing it to execute its run() method.
        mt2.start(); // Starts the "Consumer" thread (mt2), causing it to execute its run() method.

        // Waiting for threads
        mt1.join(); // Waits for the "Producer" thread (mt1) to finish its execution before proceeding.
        mt2.join(); // Waits for the "Consumer" thread (mt2) to finish its execution before proceeding.

        // count will be 5 after both threads complete execution
        // After both threads have finished, prints the final value of the shared resource, 
        // which should be 5 if both threads executed correctly.
        System.out.println("count: " + SharedResource.count); 
    }
}
