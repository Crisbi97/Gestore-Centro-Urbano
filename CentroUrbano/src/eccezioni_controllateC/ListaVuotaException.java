package eccezioni_controllateC;

public final class ListaVuotaException extends Exception{
	
	public ListaVuotaException () {}
	
	public ListaVuotaException (String msg) {
		
		super (msg);
	}
}
