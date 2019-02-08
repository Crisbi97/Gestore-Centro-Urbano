package eccezioni_controllateC;

public final class LottoOccupatoException extends Exception{
	
	public LottoOccupatoException () {}
	
	public LottoOccupatoException (String msg) {
		
		super (msg);
	}
}
