package jpm.commands.exception;

public class InvalidLineDataFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8686893283476449671L;

	public InvalidLineDataFormatException() {
		super("Empty or invalid line data format. Unable to process.");
	}
}
