package threads;

/**
 * Write a program that starts four Java threads, such that each thread prints 
 * a greeting and sleeps for a random amount of time, and then prints a goodbye message. 
 * The printed messages should include a name or an ID number so you can see each 
 * thread’s identity. Let the main program create the new thread objects and start them.
 * 
 * This threads demo uses implements Runnable.
 * */

public class ThreadsDemoRunnable {
	
	public static void main(String[] args) {
		System.out.println("Main program has started");
		
		// Creating instances of myRunnable
		Runnable task1 = new MyRunnable("Thread 1");
		Runnable task2 = new MyRunnable("Thread 2");
		Runnable task3 = new MyRunnable("Thread 3");
		Runnable task4 = new MyRunnable("Thread 4");
		
		// Creating thread instances and passing the Runnable instances to them
		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(task2);
		Thread thread3 = new Thread(task3);
		Thread thread4 = new Thread(task4);
		
		// Starting the threads
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
		// Print a message indicating that the threads have been started
        System.out.println("Threads started, goodbye cruel world");
        
        // Waiting for all the threads to complete
        try {
        	thread1.join();
        	thread2.join();
        	thread3.join();
        	thread4.join();
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
        
        // Prints a message indicating the main program is done
        System.out.println("Main program done");
	}
	
}
