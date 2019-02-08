package classiC;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import componentiC.Cella;
import eccezioni_controllateC.EdificioInDemolizioneException;
import eccezioni_controllateC.LottoLiberoException;
import eccezioni_controllateC.LottoOccupatoException;
import eccezioni_controllateC.SettoreIndexOutOfBoundsException;
import eccezioni_controllateC.StradaDiagonaleException;
import eccezioni_non_controllateC.SettoreNulloException;
import interfaccieC.CheckLotto;

public final class Settore implements Cloneable, Serializable {
	
//istance variables

	private int righe;
	private int colonne;
	
	private Lotto[][] matrice;
	
//constructors
	
	/**
	 * Costruisce un settore.
	 * @param righe le righe del settore
	 * @param colonne le colonne del settore
	 * @throws SettoreNulloException
	 */
	public Settore (int righe, int colonne) throws SettoreNulloException {
		
		this.righe = righe;
		this.colonne = colonne;
		
		if (righe == 0 || colonne == 0)
			
			throw new SettoreNulloException("Settore " + righe + " x " + colonne);
		
		matrice = new Lotto [righe][colonne];
		
		for (int i = 0; i < righe; i++)
			
			for (int j = 0; j < colonne; j++) 
				
				matrice [i][j] = new Lotto();
	}
	
//methods
	
	
	//accesso
	
	/**
	 * Restituisce il numero di righe.
	 * @return le righe
	 */
	public int getRighe () {
		
		return this.righe;
	}
	
	/**
	 * Restituisce il numero di colonne.
	 * @return le colonne
	 */
	public int getColonne () {
		
		return this.colonne;
	}

	/**
	 * Restituisce il lotto (r, c).
	 * @param r la riga del lotto
	 * @param c la colonna del lotto
	 * @return il lotto (r, c)
	 */
	public Lotto getLotto (int r, int c) throws SettoreIndexOutOfBoundsException {
		
		if (r >= this.righe)
			
			throw new SettoreIndexOutOfBoundsException("Riga troppo grande");
		
		if (c >= this.colonne)
			
			throw new SettoreIndexOutOfBoundsException("Colonna troppo grande");
		
		if (r < 0)
			
			throw new SettoreIndexOutOfBoundsException("Riga troppo piccola");
		
		if (c < 0)
			
			throw new SettoreIndexOutOfBoundsException("Colonna troppo piccola");
		
		return this.matrice [r][c];
	}
	
	
	//modificatori
	
	/**
	 * Aggiunge un  nuovo lotto l in (r, c).
	 * @param l il nuovo lotto
	 * @param r la riga del lotto
	 * @param c la colonna del lotto
	 */
	public void addLotto (Lotto l, int r, int c) throws SettoreIndexOutOfBoundsException {
		
		if (r >= this.righe)
			
			throw new SettoreIndexOutOfBoundsException("Riga troppo grande");
		
		if (c >= this.colonne)
			
			throw new SettoreIndexOutOfBoundsException("Colonna troppo grande");
		
		if (r < 0)
			
			throw new SettoreIndexOutOfBoundsException("Riga troppo piccola");
		
		if (c < 0)
			
			throw new SettoreIndexOutOfBoundsException("Colonna troppo piccola");
		
		this.matrice [r][c] = l;
	}
	
	/**
	 * Rimuove il lotto (r, c).
	 * @param r la riga del lotto
	 * @param c la colonna del lotto
	 */
	public void removeLotto (int r, int c) throws SettoreIndexOutOfBoundsException {
		
		if (r >= this.righe)
			
			throw new SettoreIndexOutOfBoundsException("Riga troppo grande");
		
		if (c >= this.colonne)
			
			throw new SettoreIndexOutOfBoundsException("Colonna troppo grande");
		
		if (r < 0)
			
			throw new SettoreIndexOutOfBoundsException("Riga troppo piccola");
		
		if (c < 0)
			
			throw new SettoreIndexOutOfBoundsException("Colonna troppo piccola");
		
		this.matrice [r][c] = new Lotto();
	}
	

	//object
	
	/**
	 * Restituisce una rappresentazione dell’oggetto in forma di stringa.
	 */
	public String toString () {
		
		String matrice = new String();
		
		for (int i = 0; i < righe; i++)
			
			for (int j = 0; j < colonne; j++) {
				
				if (!this.matrice[i][j].chkLottoLibero())
				
					matrice = matrice.concat(" " + this.matrice [i][j].toString() + ",");
				
				else
					
					matrice = matrice.concat(" [Vuoto],");
				
			}

		return	this.getClass().getName() +
				" [Righe = " + this.righe +
				", Colonne = " + this.colonne +
				", Matrice Settore =" + matrice; 
	}
	
	/**
	 * Verifica se l’oggetto è uguale a un altro.
	 */
	public boolean equals (Object otherObject) {
		
		if (otherObject == null) return false;
		if (this.getClass() != otherObject.getClass()) return false;
		
		Settore other = (Settore) otherObject;
		
		if (this.righe != other.righe) return false;
		if (this.colonne != other.colonne) return false;
		
		for (int i = 0; i < righe; i ++) 
			
			for (int j = 0; j < colonne; j ++) {
				
					if (!this.matrice [i][j].equals(other.matrice [i][j])) 
					
						return false;
			}
		
		return true;
	}
	
	/**
	 * Crea una copia dell’oggetto.
	 */
	public Settore clone () {
		
		try {
			
			Settore cloned = (Settore) super.clone();
			
			for (int i = 0; i < righe; i ++)
				
				for (int j = 0; j < colonne; j ++)
					
					if (this.matrice [i][j] != null)
					
						cloned.matrice [i][j] = this.matrice [i][j].clone();
			
			return cloned;
		}
		
		catch (CloneNotSupportedException e) {
			
			return null;
		}
	}

	
	//gestore
	
	/**
	 * Controlla se il settore è libero.
	 * @return true se il settore è libero, false se il settore è occupato
	 */
	public boolean chkSettoreLibero () {
		
		for (int i = 0; i < this.righe; i++)
			
			for (int j = 0; j < this.colonne; j++)
		
				if (!this.matrice [i][j].chkLottoLibero())
			
					return false;
		
		return true;
	}
	
	/**
	 *  Costruisce una strada su un lotto.
	 * @param str la strada da costruire 
	 * @param r la riga del lotto
	 * @param c la colonna del lotto
	 * @throws LottoOccupatoException
	 */
	public void addStrada (Strada str, int r, int c) throws LottoOccupatoException, SettoreIndexOutOfBoundsException  {
		
		if (this.matrice[r][c].chkLottoLibero())
			
			this.addLotto(str, r, c);
		
		else
			
			throw new LottoOccupatoException("Lotto (" + r + ", " + c + ") occupato");
	}
	
	/**
	 * Costruisce un nuovo lotto.
	 * @param l il lotto da costruire
	 * @param r la riga del lotto
	 * @param c la colonna del lotto
	 * @throws LottoOccupatoException
	 */
	public void chgLotto (Lotto l, int r, int c) throws LottoOccupatoException, SettoreIndexOutOfBoundsException {
		
		if (this.matrice[r][c].chkLottoLibero())
			
			this.addLotto(l, r, c);
		
		else 
			
			throw new LottoOccupatoException("Lotto (" + r + ", " + c + ") occupato");
	}
	
	/**
	 * Demolisce un lotto.
	 * @param r la riga del lotto
	 * @param c la colonna del lotto
	 * @return il lotto demolito
	 * @throws LottoLiberoException
	 * @throws EdificioInDemolizioneException
	 */
	public Lotto demolizione (int r, int c) throws LottoLiberoException, EdificioInDemolizioneException, SettoreIndexOutOfBoundsException {
		
		if (!this.matrice[r][c].chkLottoLibero()) {
			
			if (this.matrice[r][c] instanceof EdificioPubblico) {
				
				EdificioPubblico ePbl = (EdificioPubblico) this.matrice[r][c];
				ePbl.demolizione(r, c);
				
				return ePbl;
			}
				
			if (this.matrice[r][c] instanceof Strada) {
				
				Strada str = (Strada) this.matrice[r][c];
				this.removeLotto(r, c);
				
				return str;
			}
				
			if (this.matrice[r][c] instanceof EdificioPrivato) {
				
				EdificioPrivato ePrv = (EdificioPrivato) this.matrice[r][c];
				this.removeLotto(r, c);
				
				return ePrv;
			}	
		}
		
		else
			
			throw new LottoLiberoException("Lotto (" + r + ", " + c + ") libero");
		
		return null;
	}
	
	/**
	 * Invecchia il settore di un ciclo.
	 * @return arraylist di lotti demoliti a causa dell'invecchiamento
	 */
	public ArrayList<Lotto> invecchiamento () throws SettoreIndexOutOfBoundsException {
	
		int i, j;
		ArrayList<Lotto> lista = new ArrayList<>();
		
		for (i = 0; i < this.righe; i ++) 
			
			for (j = 0; j < this.colonne; j++) 
				
				if (!this.matrice [i][j].chkLottoLibero()) 
					
					if (this.matrice [i][j].invecchiamento()) {
						
						lista.add(this.matrice [i][j]);
						this.removeLotto(i, j);
					}
		
		return lista;
	}
	
	/**
	 * Genera un disastro, riduce di [1, n] il coefficiente di efficienza di un lotto.
	 * @param r la riga del lotto
	 * @param c la colonna del lotto
	 * @param n il valore massimo da ridurre al coefficiente di efficienza
	 * @return true se il diastro è stato eseguito, false altrimenti
	 */
	public boolean disastro (int r, int c, int n) {
		
		if (!this.matrice [r][c].chkLottoLibero()) {
			
			this.matrice [r][c].disastro(n);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Ristruttura un lotto
	 * @param r la riga del lotto
	 * @param c la colonna del lotto
	 * @throws LottoLiberoException
	 */
	public void ristrutturazione (int r, int c) throws LottoLiberoException {
		
		if (!this.matrice [r][c].chkLottoLibero())
			
			this.matrice [r][c].ristrutturazione();
			
		else
			
			throw new LottoLiberoException("Lotto (" + r + ", " + c + ") libero");
	}
	
	
	//informatore
	
	/**
	 * Restituisce il numero dei lotti del settore secondo un criterio.
	 * @param tester interfaccia funzionale che testa un lotto secondo un dato criterio
	 * @return numero dei lotti del settore secondo un criterio
	 */
	public int numeroLotti (CheckLotto tester) {
		
		int count = 0;
		int i, j;
		
		for (i = 0; i < this.righe; i ++) 
			
			for (j = 0; j < this.colonne; j++) 
						
				if (tester.test(this.matrice[i][j]))
							
					count ++;	
		
		return count;
	}
	
	
	//visualizzatore
	
	/**
	 * Disegna il settore.
	 * @param iC  la riga del settore
	 * @param jC la colonna del settore
	 * @param g2D oggetto di tipo Graphics2D
	 */
	public void disegna (int iC, int jC, Graphics2D g2D) {
		
		int iS, jS;
		
		for (iS = 0; iS < this.righe; iS ++) 
			
			for (jS = 0; jS < this.colonne; jS++) {
				
				Cella cellaLotto = new Cella(this.matrice [iS][jS],
											(jS * Cella.LATO) + (jC * this.colonne * Cella.LATO),
											(iS * Cella.LATO) + (iC * this.righe * Cella.LATO));
				
				cellaLotto.disegna(g2D);
			}
	}
	
	
	//selezionatore
	
	/**
	 * Seleziona i lotti del settore secondo un criterio.
	 * @param tester interfaccia funzionale che testa un lotto secondo un dato criterio
	 * @return arraylist di lotti contenente i lotti selezionati
	 */
	public ArrayList<Lotto> selezionaLotti (CheckLotto tester) {
		
		ArrayList<Lotto> lista = new ArrayList<>();
		int i, j;
		
		for (i = 0; i < this.righe; i ++) 
			
			for (j = 0; j < this.colonne; j++) 
				
				if (tester.test(this.matrice[i][j]))
					
					lista.add(this.matrice[i][j]);

		return lista;
	}
	
	
	//update
	
	/**
	 * Aggiorna il settore dopo aver aggiunto un edificio pubblico.
	 * @param ePbl l'edificio pubblico aggiunto
	 */
	public void AddPbl (EdificioPubblico ePbl) {
		
		int i, j;
		
		for (i = 0; i < this.righe; i++)
			
			for (j = 0; j < this.colonne; j++ )
				
				if (this.matrice [i][j] instanceof EdificioPrivato) {
					
					EdificioPrivato ePrv = (EdificioPrivato) this.matrice [i][j];
					ePrv.sumValore(ePbl);
				}
	}
	
	/**
	 * Aggiorna il settore dopo aver rimosso un edificio pubblico.
	 * @param ePbl l'edificio pubblico rimosso
	 */
	public void RmvPbl (EdificioPubblico ePbl) {
		
		int i, j;
		
		for (i = 0; i < this.righe; i++)
			
			for (j = 0; j < this.colonne; j++ )
				
				if (this.matrice [i][j] instanceof EdificioPrivato) {
					
					EdificioPrivato ePrv = (EdificioPrivato) this.matrice [i][j];
					ePrv.subValore(ePbl);
				}
	}
	
	/**
	 * Aggiorna il settore dopo aver aggiunto una strada.
	 * @param str la strada aggiunta
	 * @param r la riga del lotto dove è stata aggiunta la strada
	 * @param c la colonna del lotto dove è stata aggiunta la strada
	 */
	public void AddStr (Strada str, int r, int c) {
		
		Lotto l;
		EdificioPrivato ePrv;

		l = this.matrice[r][c];
			
		if (l instanceof EdificioPrivato) {
				
			ePrv = (EdificioPrivato) l;
			ePrv.sumValore(str);
		}
	}
	
	/**
	 * Aggiorna il settore dopo aver rimosso una strada.
	 * @param str la strada rimossa
	 * @param r la riga del lotto dove è stata rimossa la strada
	 * @param c la colonna del lotto dove è stata rimossa la strada
	 */
	public void RmvStr (Strada str, int r, int c) {
		
		Lotto l;
		EdificioPrivato ePrv;

		l = this.matrice[r][c];
			
		if (l instanceof EdificioPrivato) {
				
			ePrv = (EdificioPrivato) l;
			ePrv.subValore(str);
		}
	}
	
	/**
	 * Aggiorna il settore dopo aver aggiunto un edificio privato secondo gli edifici pubblici.
	 * @param ePbl l'edificio privato aggiunto
	 */
	public void AddPrvEdPbl (EdificioPrivato ePrv) {
		
		for (int i = 0; i < this.righe; i++)
			
			for ( int j = 0; j < this.colonne; j++ )
				
				if (this.matrice[i][j] instanceof EdificioPubblico) {
					
					EdificioPubblico ePbl = (EdificioPubblico) this.matrice[i][j];
					ePrv.sumValore(ePbl);
				}
	}
	
	/**
	 * Aggiorna il settore dopo aver aggiunto un edificio privato secondo le strade.
	 * @param ePbl l'edificio privato aggiunto
	 * @param r la riga del lotto dove può essere presente una strada
	 * @param c la colonna del lotto dove può essere presente una strada
	 */
	public void AddPrvStr (EdificioPrivato ePrv, int r, int c) {
		
		Lotto l;
		Strada str;

		l = this.matrice[r][c];
			
		if (l instanceof Strada) {
				
			str = (Strada) l;
			ePrv.sumValore(str);
		}
	}
	
}
