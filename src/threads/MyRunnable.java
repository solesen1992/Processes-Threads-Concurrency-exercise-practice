package threads;

/**
 * Thread to be used in ThreadsDemoRunnable.
 * */

public class MyRunnable implements Runnable {
	private String name;

	public MyRunnable(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public void run() {
        System.out.println(name + " says: Hello!");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " says: Goodbye!");
    }
}
