package eccezioni_non_controllateC;

public class SettoreNulloException extends RuntimeException {
	
	public SettoreNulloException () {}
	
	public SettoreNulloException (String msg) {
		
		super (msg);
	}
}
