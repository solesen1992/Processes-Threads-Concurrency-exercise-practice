package processes;

import java.io.IOException;

/**
 * Java program that starts another operating system process.
 * Can use java.lang.ProcessBuilder or the .exec() method from java.lang.Runtime.
 * */

public class ProcessDemoRuntimeExec {
	// Starts a Chrome window
		public static void main(String[] args) {

			try {
				// Starts the process using Runtime.exec()
				Process process = Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
					
				// Wait for the process to terminate
					int exitCode = process.waitFor();
					
					// Check if the process terminated successfully
					if (exitCode == 0) {
						System.out.println("Process executed succesfully!");
					} else {
						System.out.println("Process failed with error code: " + exitCode);
					}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("Done...");
		}
}
