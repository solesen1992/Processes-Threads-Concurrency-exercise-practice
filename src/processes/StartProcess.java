package processes;

import java.io.IOException;

/**
 * Example on how to start a process. When the program runs, it opens notepad.
 * */

public class StartProcess {

	public static void main(String[] args) {
		ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");
		
		try {
			Process process = processBuilder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
