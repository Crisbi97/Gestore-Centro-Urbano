package testerC;

import classiC.CentroUrbano;
import classiC.EdificioPrivato;
import classiC.EdificioPubblico;
import classiC.Settore;
import classiC.Strada;
import eccezioni_controllateC.CentroUrbanoIndexOutOfBoundsException;
import eccezioni_controllateC.SettoreIndexOutOfBoundsException;

public class TestCentroUrbano {
	
	public static void main(String[] args) throws CentroUrbanoIndexOutOfBoundsException, SettoreIndexOutOfBoundsException {
		
		System.out.println();
		
		System.out.println("Istanzio un edificio pubblico e lo stampo:");
		EdificioPubblico pub = new EdificioPubblico(50, 5, "pubblico", 500);
		System.out.println(pub);
		
		System.out.println("Istanzio un edificio privato e lo stampo:");
		EdificioPrivato prv = new EdificioPrivato(50, 5, "privato", 500);
		System.out.println(prv);
		
		System.out.println("Istanzio una strada e la stampo:");
		Strada str = new Strada(50, 5, 500);
		System.out.println(str);
		
		System.out.println("Instanzio un nuovo centro urbano 2 x 2, con settori 2 x 2 e lo stampo:");
		CentroUrbano cb = new CentroUrbano(2, 2, 1, 1);
		System.out.println(cb);
		
		System.out.println("Aggiungo i tre lotti istanziati precedentemente al settore (0, 0) e stampo il centro urbano:");
		Settore s = cb.getSettore(0, 0);
		s.addLotto(pub, 0, 0);
		s.addLotto(prv, 0, 1);
		s.addLotto(str, 1, 0);
		System.out.println(cb);
		
		System.out.println("Testo i metodi di accesso:");
		System.out.println("righe = " + cb.getRighe());
		System.out.println("colonne = " + cb.getColonne());
		System.out.println("righe settore = " + cb.getRigheSettore());
		System.out.println("colonne settore = " + cb.getColonneSettore());
		System.out.println("settore (0, 0) = " + cb.getSettore(0, 0));
		System.out.println("settore (0, 1) = " + cb.getSettore(0, 1));
		
		System.out.println("Stampo il clone del centro urbano:");
		System.out.println(cb.clone());
		
		System.out.println("Eseguo l' equals sul clone del centro urbano:");
		System.out.println(cb.equals(cb.clone()));
	}

}
