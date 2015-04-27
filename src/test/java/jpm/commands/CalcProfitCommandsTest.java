package jpm.commands;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.CommandResult;
import org.springframework.shell.core.JLineShellComponent;


public class CalcProfitCommandsTest  {
	private static JLineShellComponent shell;
	
	@BeforeClass
	public static void startUp() throws InterruptedException {
		Bootstrap bootstrap = new Bootstrap();
		shell = bootstrap.getJLineShellComponent();
	}

	@AfterClass
	public static void shutdown() {
		shell.stop();
	}

	public static JLineShellComponent getShell() {
		return shell;
	}
	
	@Test
	public void testCalcprofit() {
		// Execute command
		CommandResult cr = getShell().executeCommand("calcprofit --input input.csv --output tmp.csv");

		// Get result
		String result = cr.getResult().toString();
 
		assertEquals("Small File Calulcation used. Output file[tmp.csv] has written successfully", result);
	}
}
