package classiC;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import componentiC.Cella;
import componentiC.Perimetro;
import eccezioni_controllateC.CentroUrbanoIndexOutOfBoundsException;
import eccezioni_controllateC.CentroUrbanoVuotoException;
import eccezioni_controllateC.EdificioInDemolizioneException;
import eccezioni_controllateC.ListaVuotaException;
import eccezioni_controllateC.LottoLiberoException;
import eccezioni_controllateC.LottoOccupatoException;
import eccezioni_controllateC.SettoreIndexOutOfBoundsException;
import eccezioni_controllateC.StradaDiagonaleException;
import eccezioni_non_controllateC.CentroUrbanoNulloException;
import interfaccieC.CheckLotto;

public final class CentroUrbano implements Cloneable, Serializable {
	
//istance variables
	
	private int righe;
	private int colonne;
	
	private int righeSettore;
	private int colonneSettore;
	
	private Settore[][] matrice;
		
//constructors
		
	/**
	 * Costruisce un centro urbano
	 * @param righe le righe del centro urbano
	 * @param colonne le colonne del centro urbano
	 * @param righeSettore le righe del settore
	 * @param colonneSettore le colonne del settore
	 * @throws CentroUrbanoNulloException
	 */
	public CentroUrbano (int righe, int colonne, int righeSettore, int colonneSettore) throws CentroUrbanoNulloException {
			
		this.righe = righe;
		this.colonne = colonne;
		
		this.righeSettore = righeSettore;
		this.colonneSettore = colonneSettore;
		
		if (righe == 0 || colonne == 0)
			
			throw new CentroUrbanoNulloException("Centro Urbano " + righe + " x " + colonne);
			
		matrice = new Settore [righe] [colonne];
		
		for (int i = 0; i < righe; i++)
			
			for (int j = 0; j < colonne; j++) 
				
				matrice [i][j] = new Settore(righeSettore, colonneSettore);
	}
		
//methods
	
	
	//access
	
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
	 * Restituisce il numero di righe del settore.
	 * @return righe del settore
	 */
	public int getRigheSettore () {
		
		return this.righeSettore;
	}
	
	/**
	 * Restituisce il numero di colonne del settore.
	 * @return colonne del settore
	 */
	public int getColonneSettore () {
		
		return this.colonneSettore;
	}
	
	/**
	 * Restituisce il settore (r, c).
	 * @param r la riga del settore
	 * @param c la colonna del settore
	 * @return il settore (r, c)
	 */
	public Settore getSettore (int r, int c) throws CentroUrbanoIndexOutOfBoundsException {
		
		if (r >= this.righe)
			
			throw new CentroUrbanoIndexOutOfBoundsException("Riga troppo grande");
		
		if (c >= this.colonne)
			
			throw new CentroUrbanoIndexOutOfBoundsException("Colonna troppo grande");
		
		if (r < 0)
			
			throw new CentroUrbanoIndexOutOfBoundsException("Riga troppo piccola");
		
		if (c < 0)
			
			throw new CentroUrbanoIndexOutOfBoundsException("Colonna troppo piccola");
		
		return this.matrice [r][c];
	}	
	
	//object
	
	/**
	 * Restituisce una rappresentazione dell’oggetto in forma di stringa.
	 */
	public String toString () {
		
		String matrice = new String();
		
		for (int i = 0; i < righe; i++)
			
			for (int j = 0; j < colonne; j++)
				
				matrice = matrice.concat(" " + this.matrice [i][j].toString());
		
		return	this.getClass().getName() +
				" [Righe = " + this.righe +
				", Colonne = " + this.colonne +
				", Matrice Centro Urbano =" + matrice; 
	}
	
	/**
	 * Verifica se l’oggetto è uguale a un altro.
	 */
	public boolean equals (Object otherObject) {
		
		if (otherObject == null) return false;
		if (this.getClass() != otherObject.getClass()) return false;
		
		CentroUrbano other = (CentroUrbano) otherObject;
		
		if (this.righe != other.righe) return false;
		if (this.colonne != other.colonne) return false;
		
		for (int i = 0; i < righe; i ++)
			
			for (int j = 0; j < colonne; j ++)
				
				if (!this.matrice [i][j].equals(other.matrice [i][j])) 
					
					return false;
		
		return true;
	}
	
	/**
	 * Crea una copia dell’oggetto.
	 */
	public CentroUrbano clone () {
		
		try {
			
			CentroUrbano cloned = (CentroUrbano) super.clone();
			
			for (int i = 0; i < righe; i ++)
				
				for (int j = 0; j < colonne; j ++)
					
					cloned.matrice [i][j] = this.matrice [i][j].clone();
			
			return cloned;
		}
		
		catch (CloneNotSupportedException e) {
			
			return null;
		}
	}
	
	
	//gestore
	
	/**
	 * Controlla se il centro urbano è libero.
	 * @return true se il centro urbano è libero, false se il centro urbano è occupato
	 */
	public boolean chkCentroUrbanoLibero () {
		
		for (int i = 0; i < this.righe; i++)
		
			for (int j = 0; j < this.colonne; j++)
				
				if (!this.matrice[i][j].chkSettoreLibero())
			
					return false;
		
		return true;
	}	
	
	/**
	 * Costruisce una strada dal primo lotto al secondo lotto , i lotti appartengono allo stesso settore.
	 * @param str la strada da costruire 
	 * @param rs la riga del settore dove costruire la strada
	 * @param cs la colonna del settore dove costruire la strada
	 * @param r1 la riga del primo lotto
	 * @param c1 la colonna del primo lotto
	 * @param r2 la riga del secondo lotto
	 * @param c2 la colonna del secondo lotto
	 * @throws LottoOccupatoException
	 * @throws StradaDiagonaleException
	 */
	public void addStrada (Strada str, int rs, int cs, int r1, int c1, int r2, int c2) throws LottoOccupatoException, StradaDiagonaleException, SettoreIndexOutOfBoundsException {
		
		Settore s = this.matrice[rs][cs];
		
		//orizziontale
		if (r1 == r2) {
			
			if (c1 <= c2) {
				
				for (int i = c1; i <= c2; i++) {
					
					s.addStrada(str, r1, i);
					this.AddStr(str, rs, cs, r1, i);
				}	
			}
			
			else if (c1 > c2) {
				
				for (int i = c2; i <= c1; i++) {
					
					s.addStrada(str, r1, i);
					this.AddStr(str, rs, cs, r1, i);
					
				}				
			}
		}
		
		//verticale
		else if (c1 == c2) {
			
			if (r1 <= r2) {
				
				for (int i = r1; i <= r2; i++) {
					
					s.addStrada(str, i, c1);
					
				}
			}
			
			else if (r1 > r2) {
				
				for (int i = r2; i <= r1; i++) {
					
					s.addStrada(str, i, c1);
					this.AddStr(str, rs, cs, i, c1);
				}	
			}
		}
		
		else 
			
			throw new StradaDiagonaleException("La strada deve essere orizzontale o verticale");
	}

	/**
	 * Costruisce un nuovo lotto.
	 * @param l il lotto da costruire
	 * @param rs la riga del settore dove costruire il lotto
	 * @param cs la colonna del settore dove costruire il lotto
	 * @param r la riga del lotto
	 * @param c la colonna del lotto
	 * @throws LottoOccupatoException
	 */
	public void chgLotto (Lotto l, int rs, int cs, int r, int c) throws LottoOccupatoException, SettoreIndexOutOfBoundsException {
		
		Settore s = this.matrice [rs][cs];
		
		s.chgLotto(l, r, c);
		
		if (l instanceof EdificioPubblico) 
			
			this.AddPbl((EdificioPubblico) l, rs, cs);
		
		if (l instanceof Strada)
			
			this.AddStr((Strada) l, rs, cs, r, c);
		
		if (l instanceof EdificioPrivato)
			
			this.AddPrv((EdificioPrivato) l, rs, cs, r, c);
	}
	
	/**
	 * Demolisce un lotto.
	 * @param rs la riga del settore dove demolire il lotto
	 * @param cs la colonna del settore dove demolire il lotto
	 * @param r la riga del lotto
	 * @param c la colonna del lotto
	 * @throws LottoLiberoException
	 * @throws EdificioInDemolizioneException
	 */
	public void demolizione (int rs, int cs, int r, int c) throws LottoLiberoException, EdificioInDemolizioneException, SettoreIndexOutOfBoundsException {
		
		Settore s = this.matrice [rs][cs];
		Lotto l;
		
		l = s.demolizione(r, c);
		
		if (l instanceof Strada)
			
			this.RmvStr((Strada) l, rs, cs, r, c);
	}

	/**
	 * Invecchia il centro urbano di un ciclo.
	 */
	public void invecchiamento () throws CentroUrbanoVuotoException, SettoreIndexOutOfBoundsException {
	
		if (!this.chkCentroUrbanoLibero()) {
			
			int i, j;
			ArrayList<Lotto> lista = new ArrayList<>();
			
			for (i = 0; i < this.righe; i ++) 
				
				for (j = 0; j < this.colonne; j++) {
					
					lista = this.matrice [i][j].invecchiamento();
					
					for (Lotto l : lista)
						
						if (l instanceof EdificioPubblico)
							
							this.RmvPbl((EdificioPubblico) l, i, j);
				}	
		}
		
		else
			
			throw new CentroUrbanoVuotoException ("Impossibile eseguire l'invecchiamento");
	}

	/**
	 * Genera un disastro, riduce di [1, 20] il coefficiente di efficienza di un lotto random, riduce di [1, 10] il coefficiente di efficienza dei lotti adiacenti.
	 * @return le coordinate lotto colpito dal disastro
	 */
	public int[] disastro () throws CentroUrbanoVuotoException {
		
		if (!this.chkCentroUrbanoLibero()) {
			
			Random rand = new Random();
			int rs, cs;
			int rl, cl;
			
			boolean done = false;
			int[] coordinate = new int [4];
			
			do {
				
				rs = rand.nextInt(this.righe);
				cs = rand.nextInt(this.colonne);
				
				rl = rand.nextInt(this.righeSettore);
				cl = rand.nextInt(this.colonneSettore);
				
				done = this.matrice [rs][cs].disastro(rl, cl, 20);
				
			} while (!done);
			
			//nord
			if (rl > 0) 
				
				this.matrice [rs][cs].disastro(rl - 1, cl, 10);
			
			else if (rs > 0)
				
				this.matrice [rs - 1][cs].disastro(this.righeSettore - 1, cl, 10);
			
			//sud
			if (rl < this.righeSettore - 1)
				
				this.matrice [rs][cs].disastro(rl + 1, cl, 10);
			
			else if (rs < this.righe - 1)
				
				this.matrice [rs + 1][cs].disastro(0, cl, 10);
			
			//ovest
			if (cl > 0)
				
				this.matrice [rs][cs].disastro(rl, cl - 1, 10);
			
			else if (cs > 0)
				
				this.matrice [rs][cs - 1].disastro(rl, this.colonneSettore - 1, 10);
			
			//est
			if (cl < this.colonneSettore - 1)
				
				this.matrice [rs][cs].disastro(rl, cl + 1, 10);
			
			else if (cs < this.colonne - 1)
				
				this.matrice [rs][cs + 1].disastro(rl, 0, 10);
			
			coordinate [0] = rs;
			coordinate [1] = cs;
			coordinate [2] = rl;
			coordinate [3] = cl;
			
			return coordinate;
		}
		
		else 
			
			throw new CentroUrbanoVuotoException("Impossibile eseguire il disastro");
	}

	/**
	 * Ristruttura un lotto
	 * @param rs la riga del settore dove ristrutturare il lotto
	 * @param cs la colonna del settore dove ristrutturare il lotto
	 * @param r la riga del lotto
	 * @param c la colonna del lotto
	 * @throws LottoLiberoException
	 */
	public void ristrutturazione (int rs, int cs, int r, int c) throws LottoLiberoException {
		
		this.matrice [rs][cs].ristrutturazione(r, c);
	}
	
	
	//informatore
	
	/**
	 * Restituisce il numero dei settori del centro urbano.
	 * @return numero dei settori del centro urbano
	 */
	public int numeroSettori () {
		
		return this.righe * this.colonne;
	}
	
	/**
	 * Restituisce il numero dei lotti del centro urbano secondo un criterio.
	 * @param tester interfaccia funzionale che testa un lotto secondo un dato criterio
	 * @return numero dei lotti del centro urbano secondo un criterio
	 */
	public int numeroLotti (CheckLotto tester) {
		
		int count = 0;
		int i, j;
		
		for (i = 0; i < this.righe; i ++) 
			
			for (j = 0; j < this.colonne; j++) 
				
				count += this.matrice [i][j].numeroLotti(tester);
		
		return count;
	}
	
	
	//visualizzatore
	
	/**
	 * Disegna il centro urbano.
	 * @param g2D oggetto di tipo Graphics2D
	 */
	public void disegna (Graphics2D g2D) {
		
		int i, j;
		
		for (i = 0; i < this.righe; i ++) 
			
			for (j = 0; j < this.colonne; j++)  {
				
				this.matrice [i][j].disegna(i, j, g2D);
				
				Perimetro perimetroSettore = new Perimetro (Color.WHITE,
											j * this.colonneSettore,
											i * this.righeSettore,
											this.colonneSettore,
											this.righeSettore);
				perimetroSettore.disegna(g2D);
			}
		
		Perimetro perimetroCentroUrbano = new Perimetro (Color.BLACK, 0, 0, 
														this.colonne * this.colonneSettore, 
														this.righe * this.righeSettore);
		perimetroCentroUrbano.disegna(g2D);		
	}
	
	/**
	 * Restituisce l'ampiezza del disegno del centro urbano.
	 * @return l'ampiezza del disegno del centro urbano
	 */
	public int getAmpiezzaComponent () {
		
		return this.colonne * this.colonneSettore * Cella.LATO;
	}
	
	/**
	 * Restituisce l'altezza del disegno del centro urbano.
	 * @return l'altezza del disegno del centro urbano
	 */
	public int getAltezzaComponent () {
		
		return this.righe * this.righeSettore * Cella.LATO;
	}
	
	//selezionatore
	
	/**
	 * Seleziona i lotti del centro urbano secondo un criterio.
	 * @param tester interfaccia funzionale che testa un lotto secondo un dato criterio
	 * @return arraylist di lotti contenente i lotti selezionati
	 */
	public ArrayList<Lotto> selezionaLotti (CheckLotto tester) {
		
		ArrayList<Lotto> lista = new ArrayList<>();
		int i, j;
		
		for (i = 0; i < this.righe; i ++) 
			
			for (j = 0; j < this.colonne; j++) 
				
				lista.addAll(this.matrice [i][j].selezionaLotti(tester));
		
		return lista;
	}
	
	
	//ordinatore
	
	/**
	 * Ordina i lotti selezionati secondo un criterio.
	 * @param c interfaccia di smistamento che confronta due lotti secondo un criterio
	 * @param lista arraylist di lotti contenente i lotti selezionati
	 * @return arraylist di lotti contenente i lotti selezionati ed ordinati
	 * @throws ListaVuotaException
	 */
	public ArrayList<Lotto> ordinaLotti (Comparator<Lotto> c, ArrayList<Lotto> lista) throws ListaVuotaException {
		
		if (lista.size() > 0) {
			
			lista.sort(c);
			return lista;
		}
		
		else 
			
			throw new ListaVuotaException("Selezione Fallita");
	}
	
	
	//update
	
	/**
	 * Aggiorna il centro urbano dopo aver aggiunto un edificio pubblico.
	 * @param ePbl l'edificio pubblico aggiunto
	 * @param rs la riga del settore dove è stato aggiunto l'edificio pubblico
	 * @param cs la colonna del settore dove è stato aggiunto l'edificio pubblico
	 */
	private void AddPbl (EdificioPubblico ePbl, int rs, int cs) {
		
		this.matrice[rs][cs].AddPbl(ePbl);
	}
	
	/**
	 * Aggiorna il centro urbano dopo aver rimosso un edificio pubblico.
	 * @param ePbl l'edificio pubblico rimosso
	 * @param rs la riga del settore dove è stato rimosso l'edificio pubblico
	 * @param cs la colonna del settore dove è stato rimosso l'edificio pubblico
	 */
	private void RmvPbl (EdificioPubblico ePbl, int rs, int cs) {
		
		this.matrice[rs][cs].RmvPbl(ePbl);
	}
	
	/**
	 * Aggiorna il centro urbano dopo aver aggiunto una strada.
	 * @param str la strada aggiunta
	 * @param rs la riga del settore dove è stata aggiunta la strada
	 * @param cs la colonna del settore dove è stata aggiunta la strada
	 * @param rl la riga del lotto dove è stata aggiunta la strada
	 * @param cl la colonna del lotto dove è stata aggiunta la strada
	 */
	private void AddStr (Strada str, int rs, int cs, int rl, int cl) {
		
		//nord
		if (rl > 0) 
			
			this.matrice[rs][cs].AddStr(str, rl - 1, cl);
			
		else if (rs > 0) 
			
			this.matrice[rs - 1][cs].AddStr(str, this.righeSettore - 1, cl);
		
		//sud
		if (rl < this.righeSettore - 1) 
			
			this.matrice[rs][cs].AddStr(str, rl + 1, cl);
			
		else if (rs < this.righe - 1) 
			
			this.matrice[rs + 1][cs].AddStr(str, 0, cl);

		//ovest
		if (cl > 0) 
			
			this.matrice[rs][cs].AddStr(str, rl, cl - 1);
			
		else if (cs > 0)
			
			this.matrice[rs][cs - 1].AddStr(str, rl, this.colonneSettore - 1);

		//est
		if (cl < this.colonneSettore - 1) 
			
			this.matrice[rs][cs].AddStr(str, rl, cl + 1);
	
		else if (cs < this.colonne - 1)
			
			this.matrice[rs][cs + 1].AddStr(str, rl, 0);
	}
	
	/**
	 * Aggiorna il centro urbano dopo aver rimosso una strada.
	 * @param str la strada rimossa
	 * @param rs la riga del settore dove è stata rimossa la strada
	 * @param cs la colonna del settore dove è stata rimossa la strada
	 * @param rl la riga del lotto dove è stata rimossa la strada
	 * @param cl la colonna del lotto dove è stata rimossa la strada
	 */
	private void RmvStr (Strada str, int rs, int cs, int rl, int cl) {
		
		//nord
		if (rl > 0) 
			
			this.matrice[rs][cs].RmvStr(str, rl - 1, cl);
			
		else if (rs > 0) 
			
			this.matrice[rs - 1][cs].RmvStr(str, this.righeSettore - 1, cl);
		
		//sud
		if (rl < this.righeSettore - 1) 
			
			this.matrice[rs][cs].RmvStr(str, rl + 1, cl);
			
		else if (rs < this.righe - 1) 
			
			this.matrice[rs + 1][cs].RmvStr(str, 0, cl);

		//ovest
		if (cl > 0) 
			
			this.matrice[rs][cs].RmvStr(str, rl, cl - 1);
			
		else if (cs > 0)
			
			this.matrice[rs][cs - 1].RmvStr(str, rl, this.colonneSettore - 1);

		//est
		if (cl < this.colonneSettore - 1) 
			
			this.matrice[rs][cs].RmvStr(str, rl, cl + 1);
	
		else if (cs < this.colonne - 1)
			
			this.matrice[rs][cs + 1].RmvStr(str, rl, 0);
	}
	
	/**
	 * Aggiorna il centro urbano dopo aver aggiunto un edificio privato.
	 * @param ePbl l'edificio privato aggiunto
	 * @param rs la riga del settore dove è stato aggiunto l'edificio privato
	 * @param cs la colonna del settore dove è stato aggiunto l'edificio privato
	 * @param rl la riga del lotto dove è stato aggiunto l'edificio privato
	 * @param cl la colonna del lotto dove è stato aggiunto l'edificio privato
	 */
	private void AddPrv (EdificioPrivato ePrv, int rs, int cs, int rl, int cl) {
		
		//controllo ed pbl
		this.matrice[rs][cs].AddPrvEdPbl(ePrv);
		
		//controllo strade
		//nord
		if (rl > 0) 
			
			this.matrice[rs][cs].AddPrvStr(ePrv, rl - 1, cl);
			
		else if (rs > 0) 
			
			this.matrice[rs - 1][cs].AddPrvStr(ePrv, this.righeSettore - 1, cl);
		
		//sud
		if (rl < this.righeSettore - 1) 
			
			this.matrice[rs][cs].AddPrvStr(ePrv, rl + 1, cl);
			
		else if (rs < this.righe - 1) 
			
			this.matrice[rs + 1][cs].AddPrvStr(ePrv, 0, cl);

		//ovest
		if (cl > 0) 
			
			this.matrice[rs][cs].AddPrvStr(ePrv, rl, cl - 1);
			
		else if (cs > 0)
			
			this.matrice[rs][cs - 1].AddPrvStr(ePrv, rl, this.colonneSettore - 1);

		//est
		if (cl < this.colonneSettore - 1) 
			
			this.matrice[rs][cs].AddPrvStr(ePrv, rl, cl + 1);
	
		else if (cs < this.colonne - 1)
			
			this.matrice[rs][cs + 1].AddPrvStr(ePrv, rl, 0);
	}
}
