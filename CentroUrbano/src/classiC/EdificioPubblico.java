package classiC;

import eccezioni_controllateC.EdificioInDemolizioneException;

public final class EdificioPubblico extends Edificio {
	
//istance variables
	
	private double buffValore;
	
	private boolean demolizione;
	private int ciclo;
	
//constructors
	
	/**
	 * Costruisce un nuovo edificio: coefEff = 0, coefInv = 1, nome = null, buffValore = 0.0.
	 */
	public EdificioPubblico () {
		
		super ();
		buffValore = 0.0;
		
		demolizione = false;
		ciclo = 0;
	}
	
	/**
	 * Costruisce un nuovo edificio pubblico.
	 * @param coefEff il coefficiente di efficienza
	 * @param coefInv il coefficiente di invecchiamento
	 * @param nome il nome
	 * @param buffValore il bonus al valore
	 */
	public EdificioPubblico (int coefEff, int coefInv, String nome, double buffValore) {
		
		super(coefEff, coefInv, nome);
		this.buffValore = buffValore;
		
		demolizione = false;
		ciclo = 0;
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
		
		EdificioPubblico other = (EdificioPubblico) otherObject;
		
		return	this.buffValore == other.buffValore;
	}
	
	/**
	 * Crea una copia dell’oggetto.
	 */
	public EdificioPubblico clone () {
		
		EdificioPubblico cloned = (EdificioPubblico) super.clone();

		return cloned;
	}
	
	
	//gestore
	
	/**
	 * Demolisce l'edificio pubblico.
	 * @param r la riga dell' edificio pubblico
	 * @param c la colonna dell' edificio pubblico
	 * @throws EdificioInDemolizioneException
	 */
	public void demolizione (int r, int c) throws EdificioInDemolizioneException {
		
		if (!this.demolizione)
			
			this.demolizione = true;
		
		else
			
			throw new EdificioInDemolizioneException("L' edificio (" + r + ", " + c + ") è già in demolizione");
	}
	
	/**
	 * Invecchia l'edificio pubblico di un ciclo.
	 */
	public boolean invecchiamento () {
				
		super.invecchiamento();
		
		if (this.demolizione) {
			
			if (this.ciclo < 1)
				
				this.ciclo += 1;
		
			else
				
				return true;
		}
		
		return false;
	}
	
}
