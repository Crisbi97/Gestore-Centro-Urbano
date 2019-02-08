package classiC;

public final class Strada extends Lotto {
	
//istance variables
	
	private double buffValore;
	
//constructors
	
	/**
	 * Costruisce una nuova strada:coefEff = 0, coefInv = 1, buffValore = 0.0.
	 */
	public Strada () {
		
		super ();
		buffValore = 0.0;
	}
	
	/**
	 * Costruisce un nuova strada.
	 * @param coefEff il coefficiente di efficienza
	 * @param coefInv il coefficiente di invecchiamento
	 * @param buffValore il bonus al valore
	 */
	public Strada (int coefEff, int coefInv, double buffValore) {
	
		super(coefEff, coefInv);
		this.buffValore = buffValore;
	}
	
//methods
	
	
	//accesso
	
	/**
	 * Restituisce il bonus al valore.
	 * @return il bonus al valore
	 */
	public double getBuffValore () {
		
		return this.buffValore;
	}
	
		
	//modificatori
	
	/**
	 * Modifica il bonus al valore.
	 * @param buffValore il nuovo bonus al valore
	 */
	public void setBuffValore (double buffValore) {
			
		this.buffValore = buffValore;
	}
	
	
	//object
	
	/**
	 * Restituisce una rappresentazione dell’oggetto in forma di stringa.
	 */
	public String toString () {
		
		return	super.toString() +
				" [BuffValore = " + this.buffValore + "]";
	}
	
	/**
	 * Verifica se l’oggetto è uguale a un altro.
	 */
	public boolean equals (Object otherObject) {
		
		if (!super.equals(otherObject)) return false;
		
		Strada other = (Strada) otherObject;
		
		return	this.buffValore == other.buffValore;
	}
	
	/**
	 * Crea una copia dell’oggetto.
	 */
	public Strada clone () {
		
			
		Strada cloned = (Strada) super.clone();
			
		return cloned;
	}
	
	
	//gestore
	
	/**
	 * Controlla se la strada è libera.
	 */
	public boolean chkLottoLibero () {
		
		return false;
	}
	
}
