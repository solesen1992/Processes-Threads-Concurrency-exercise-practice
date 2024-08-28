package fifoQueue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The Producer class that extends the Thread class, representing a producer in a producer-consumer scenario.
 * 
 * Purpose: 
 * This thread reads words from a file (input.txt) and adds them to the shared queue. 
 * It signals the end of the operation by adding a null object to the queue.
 * 
 * Key Components:
 * BufferedReader rd: Used to read lines from the file.
 * 
 * run() method: Reads the file line by line, splits each line into words, 
 * and adds them to the queue. After all lines are read, it adds a null to signal 
 * the consumer that there are no more elements to process.
 * 
 * Error handling: Catches InterruptedException and IOException to handle possible 
 * errors during file reading or thread interruption.
 * */

public class Producer extends Thread {
	// A static string variable to hold the name of the input file
	//private static String FILENAME = "input.txt";
	private static String FILENAME = "src/fifoQueue/input.txt";
	// A reference to the shared queue where this producer will add words
	private SharedFiFoQueue fifoQueue;
	
	// Constructor for the Producer class, initializes the queue reference
	public Producer(SharedFiFoQueue fifoQueue) {
		super();
		this.fifoQueue = fifoQueue;
	}
	
	// The run method contains the logic that will be executed when the thread starts.
	@Override
	public void run() {
		// Declare a BufferedReader variable to read lines from the input file
		BufferedReader reader = null;
		
		try {
			// Initializes the BufferedReader to read the specified file
			reader = new BufferedReader(new FileReader(FILENAME));
			
			// String variable to hold each line of the file as it is read
			String inputLine = null;
			
			// Loop through each line of the file until the end is reached
			while((inputLine = reader.readLine()) != null) {
				// Split the line into individual words using space as a delimiter
				String[] inputWords = inputLine.split(" ");
				
				// Iterate over each word in the array of words
				for(String inputWord : inputWords) {
					// Add the word to the shared queue
					fifoQueue.add(inputWord);
					
					// Print a message indicating that a word was written to the queue
					System.out.println("[Producer] wrote the element: " + inputWord);
				}
			}
			
			// After all the lines have been processed, add a null to the queue to signal the end
			fifoQueue.add(null);
		}
		catch (InterruptedException exception) {
			// Handle the exception if the thread is interrupted while waiting or sleeping.
			System.err.println("An InterruptedException was caught: " + exception.getMessage());
            exception.printStackTrace();
		}
		catch (IOException exception) {
            // Handle the exception if there is an issue reading the file.
            System.err.println("An IOException was caught: " + exception.getMessage());
            exception.printStackTrace();
		}
		finally {
            // Ensure that the BufferedReader is closed to free up system resources.
            try {
                if(reader != null)
                    reader.close();
            }
            catch (IOException exception) {
                // Handle any exception that occurs while closing the BufferedReader.
                System.err.println("An IOException was caught: " + exception.getMessage());
                exception.printStackTrace();
            }
        }
    }
}