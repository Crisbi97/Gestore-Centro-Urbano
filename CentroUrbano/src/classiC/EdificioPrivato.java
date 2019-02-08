package classiC;

public final class EdificioPrivato extends Edificio {
	
//istance variables
	
	private double valore;
	
//constructors
	
	/**
	 * Costruisce un nuovo edificio: coefEff = 0, coefInv = 1, nome = null, valore = 0.0.
	 */
	public EdificioPrivato () {
		
		super ();
		valore = 0.0;
	}
	
	/**
	 * Costruisce un nuovo edificio pubblico.
	 * @param coefEff il coefficiente di efficienza
	 * @param coefInv il coefficiente di invecchiamento
	 * @param nome il nome
	 * @param valore il valore
	 */
	public EdificioPrivato (int coefEff, int coefInv, String nome, double valore) {
		
		super(coefEff, coefInv, nome);
		this.valore = valore;
	}
	
//methods
	
	
	//accesso
	
	/**
	 * Restituisce il valore.
	 * @return il valore
	 */
	public double getValore () {
		
		return this.valore;
	}
	
	
	//modificatori
	
	/**
	 * Modifica il valore.
	 * @param buffValore il nuovo valore
	 */
	public void setValore (double valore) {
		
		this.valore = valore;
	}
	
	public void sumValore (Lotto l) {
		
		this.valore += l.getBuffValore();
	}
	
	public void subValore (Lotto l) {
		
		this.valore -= l.getBuffValore();
	}
	
	
	//object
	
	/**
	 * Restituisce una rappresentazione dell’oggetto in forma di stringa.
	 */
	public String toString () {
		
		return	super.toString() +
				" [Valore = " + this.valore + "]";
	}
	
	/**
	 * Verifica se l’oggetto è uguale a un altro.
	 */
	public boolean equals (Object otherObject) {
		
		if (!super.equals(otherObject)) return false;
		
		EdificioPrivato other = (EdificioPrivato) otherObject;
		
		return	this.valore == other.valore;
	}
	
	/**
	 * Crea una copia dell’oggetto.
	 */
	public EdificioPrivato clone () {
		
		EdificioPrivato cloned = (EdificioPrivato) super.clone();

		return cloned;
	}
	
}
