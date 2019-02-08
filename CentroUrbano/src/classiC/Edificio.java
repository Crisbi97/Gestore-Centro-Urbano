package classiC;

public abstract class Edificio extends Lotto {
	
//istance variables
	
	private String nome;
	
//constructors
	
	/**
	 * Costruisce un nuovo edificio: coefEff = 0, coefInv = 1, nome = null.
	 */
	public Edificio () {
		
		super ();
		nome = null;
	}
	
	/**
	 * Costruisce un nuovo edificio.
	 * @param coefEff il coefficiente di efficienza
	 * @param coefInv il coefficiente di invecchiamento
	 * @param nome il nome
	 */
	public Edificio (int coefEff, int coefInv, String nome) {
		
		super(coefEff, coefInv);
		this.nome = nome;
	}
	
//methods
	
	//accesso
	
	/**
	 * Restituisce il nome.
	 * @return il nome
	 */
	public String getNome () {
		
		return this.nome;
	}
	
	
	//modificatori
	
	/**
	 * Modifica il nome.
	 * @param nome il nuovo nome
	 */
	public void setNome (String nome) {
		
		this.nome = nome;
	}

	//object
	
	/**
	 * Restituisce una rappresentazione dell’oggetto in forma di stringa.
	 */
	public String toString () {
		
		return	super.toString() +
				" [Nome = " + this.nome + "]";
				
	}
	
	/**
	 * Verifica se l’oggetto è uguale a un altro.
	 */
	public boolean equals (Object otherObject) {
		
		if (!super.equals(otherObject)) return false;
		
		Edificio other = (Edificio) otherObject;
		
		return	this.nome.equals(other.nome);
	}
	
	/**
	 * Crea una copia dell’oggetto.
	 */
	public Edificio clone () {
			
		Edificio cloned = (Edificio) super.clone();
			
		return cloned;

	}
	
	
	//gestore
	
	/**
	 * Controlla se l'edificio è libero.
	 */
	public boolean chkLottoLibero () {
		
		return false;
	}
	
}
