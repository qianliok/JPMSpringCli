package jpm;


import java.io.IOException;

import org.springframework.shell.Bootstrap;

/**
 * Driver class to run the JPM Cli application. 
 * 
 * @author Qian Li
 *
 */
public class Main {

	/**
	 * Main class that delegates to Spring Shell's Bootstrap class in order to simplify debugging inside an IDE
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Bootstrap.main(args);

	}

}
