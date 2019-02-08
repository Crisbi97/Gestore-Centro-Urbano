package eccezioni_non_controllateC;

public class CentroUrbanoNulloException extends RuntimeException {
	
	public CentroUrbanoNulloException () {}
	
	public CentroUrbanoNulloException (String msg) {
		
		super (msg);
	}
}
