package eccezioni_controllateC;

public final class ZoomNonValidoException extends Exception{
	
	public ZoomNonValidoException () {}
	
	public ZoomNonValidoException (String msg) {
		
		super (msg);
	}
}
