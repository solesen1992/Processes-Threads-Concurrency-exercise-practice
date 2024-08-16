package threads;

/**
 * Write a program that starts four Java threads, such that each thread prints 
 * a greeting and sleeps for a random amount of time, and then prints a goodbye message. 
 * The printed messages should include a name or an ID number so you can see each 
 * thread’s identity. Let the main program create the new thread objects and start them.
 * 
 * This threads demo uses extends thread.
 * */

public class ThreadsDemoExtendsThread extends Thread {

	public static void main(String [] args)
	  {
		// Program has started
		System.out.println("Main program has started");
		
		// Creating thread instances with unique names
		// Uses the class MyThread to create threads.
		Thread thread1 = new MyThread("Thread 1");
		Thread thread2 = new MyThread("Thread 2");
		Thread thread3 = new MyThread("Thread 3");
		Thread thread4 = new MyThread("Thread 4");
		
	    // Starting the threads
	    thread1.start();
	    thread2.start();
	    thread3.start();
	    thread4.start();
	    
	    // Print a message indicating that the threads have been started
	    System.out.println("threads started, goodbye cruel world \n");
	    
	    // Waiting for all the threads to complete
	    try  {
		    thread1.join();
		    thread2.join();
		    thread3.join();
		    thread4.join();
	    } catch (InterruptedException e) {
	    	e.printStackTrace();
	    }
	    
	    // Message indicating the main program is done
	    System.out.println("Main program is done");
	  }
}
