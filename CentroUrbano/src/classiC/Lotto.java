package classiC;

import java.io.Serializable;
import java.util.Random;

public class Lotto implements Cloneable, Serializable {
	
//istance variables
	
	private int coefEff;		//coefficiente efficienza [0,100]
	private int coefInv;		//coefficinete invecchiamento [1,10]
	
//constructors
	
	/**
	 * Costruisce un nuovo lotto: coefEff = 0, coefInv = 1.
	 */
	public Lotto () {
		
		coefEff = 0;
		coefInv = 1;
	}
	
	/**
	 * Costruisce un nuovo lotto.
	 * @param coefEff il coefficiente di efficienza
	 * @param coefInv il coefficiente di invecchiamento
	 */
	public Lotto (int coefEff, int coefInv) {
		
		if (coefEff > 100)
			
			this.coefEff = 100;
		
		else if (coefEff < 0)
			
			this.coefEff = 0;
		
		else
			
			this.coefEff = coefEff;
		
		if (coefInv > 10)
			
			this.coefInv = 10;
		
		else if (coefInv < 1)
			
			this.coefInv = 1;
		
		else
			
			this.coefInv = coefInv;
	}
	
//methods
	
	
	//accesso
	
	/**
	 * Restituisce il coefficiente di efficienza.
	 * @return il coefficiente di efficienza
	 */
	public int getEfficienza () {
		
		return this.coefEff;
	}
	
	/**
	 * Restituisce il coefficiente di invecchiamento.
	 * @return il coefficiente di invecchiamento
	 */
	public int getInvecchiamento () {
		
		return this.coefInv;
	}
	
	/**
	 * Restituisce il bonus al valore.
	 * @return il bonus al valore
	 */
	public double getBuffValore () {return 0.0;}
	
	
	//modificatori
	
	/**
	 * Modifica il coefficiente di efficienza.
	 * @param coefEff il nuovo coefficiente di efficienza
	 */
	public void setEfficienza (int coefEff) {
		
		this.coefEff = coefEff;
		
		if (this.coefEff> 100)
			
			this.coefEff = 100;
		
		else if (this.coefEff < 0)
			
			this.coefEff = 0;
	}
	
	/**
	 * Modifica il coefficiente di invecchiamento.
	 * @param coefInv il nuovo coefficiente di invecchiamento
	 */
	public void setInvecchiamento (int coefInv) {
		
		this.coefInv = coefInv;
		
		if (this.coefInv > 10)
			
			this.coefInv = 10;
		
		else if (this.coefInv < 1)
			
			this.coefInv = 1;
	}
	
	
	//object
	
	/**
	 * Restituisce una rappresentazione dell’oggetto in forma di stringa.
	 */
	public String toString () {
		
		return	this.getClass().getName() + 
				" [Coefficiente Efficienza = " + this.coefEff +
				" ,Coefficiente Invecchiamento = " + this.coefInv + "]";
		
	}
	
	/**
	 * Verifica se l’oggetto è uguale a un altro.
	 */
	public boolean equals (Object otherObject) {
		
		if (otherObject == null) return false;
		if (this.getClass() != otherObject.getClass()) return false;
		
		Lotto other = (Lotto) otherObject;
		
		return	this.coefEff == other.coefEff &&
				this.coefInv == other.coefInv;
	}
	
	/**
	 * Crea una copia dell’oggetto.
	 */
	public Lotto clone () {
		
		try {
			
			Lotto cloned = (Lotto) super.clone();
			
			return cloned;
		}
		
		catch (CloneNotSupportedException e) {
			
			return null;
		}
	}
	
	
	//gestore
	
	/**
	 * Controlla se il lotto è libero.
	 * @return true se il lotto è libero, false se il lotto è occupato
	 */
	public boolean chkLottoLibero () {
		
		return true;
	}
	
	/**
	 * Invecchia il lotto di un ciclo.
	 * @return true se il lotto è stato demolito a causa dell'invecchiamento, false altrimenti
	 */
	public boolean invecchiamento () {
		
		if (this.coefEff >= this.coefInv)
			
			this.coefEff -= this.coefInv;
		
		else
			
			this.coefEff = 0;
		
		return false;
	}
	
	/**
	 * Genera un disastro, riduce di [1, n] il coefficiente di efficienza del lotto.
	 * @param n il valore massimo da ridurre al coefficiente di efficienza
	 */
	public void disastro (int n) {
		
		Random rand = new Random();	
		this.setEfficienza(this.coefEff - rand.nextInt(n) + 1);
	}
	
	/**
	 * Ristruttura un lotto
	 */
	public void ristrutturazione () {
		
		this.coefInv = (int) this.coefInv /2;
	}
}
