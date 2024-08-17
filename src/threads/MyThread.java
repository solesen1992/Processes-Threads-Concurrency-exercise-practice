package threads;

/**
 * Thread to be used in ThreadsDemoExtendsThread
 * */

public class MyThread extends Thread {
	private String name;

	public MyThread(String name) {
		super();
		this.name = name;
	}
	
	@Override
	// Extends the Thread class and overrides its run() method.
	public void run() {
		System.out.println(name + " says: Hello!");
		try {
			// The thread sleeps for a random time between 0 and 1000 milliseconds
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + " says: Goodbye!");
	}

}
