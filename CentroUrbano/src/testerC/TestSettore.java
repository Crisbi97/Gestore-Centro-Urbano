package testerC;

import classiC.EdificioPrivato;
import classiC.EdificioPubblico;
import classiC.Settore;
import classiC.Strada;
import eccezioni_controllateC.SettoreIndexOutOfBoundsException;

public class TestSettore {
	
	public static void main(String[] args) throws SettoreIndexOutOfBoundsException {
		
		System.out.println("Istanzio un edificio pubblico e lo stampo:");
		EdificioPubblico pub = new EdificioPubblico(50, 5, "pubblico", 500);
		System.out.println(pub);
		
		System.out.println("Istanzio un edificio privato e lo stampo:");
		EdificioPrivato prv = new EdificioPrivato(50, 5, "privato", 500);
		System.out.println(prv);
		
		System.out.println("Istanzio una strada e la stampo:");
		Strada str = new Strada(50, 5, 500);
		System.out.println(str);
		
		System.out.println("Instanzio un nuovo settore 2 x 2 e lo stampo:");
		Settore s = new Settore(2, 2);
		System.out.println(s);
		
		System.out.println("Aggiungo i tre lotti istanziati precedentemente e stampo il settore:");
		s.addLotto(pub, 0, 0);
		s.addLotto(prv, 0, 1);
		s.addLotto(str, 1, 0);
		System.out.println(s);
		
		System.out.println("Testo i metodi di accesso:");
		System.out.println("righe = " + s.getRighe());
		System.out.println("colonne = " + s.getColonne());
		System.out.println("lotto (0, 0) = " + s.getLotto(0, 0));
		System.out.println("lotto (0, 1) = " + s.getLotto(0, 1));
		System.out.println("lotto (1, 0) = " + s.getLotto(1, 0));
		
		System.out.println("Rimuovo il primo lotto e ristampo i settore:");
		s.removeLotto(0, 0);
		System.out.println(s);
		
		System.out.println("Stampo il clone del settore:");
		System.out.println(s.clone());
		
		System.out.println("Eseguo l' equals sul clone del settore:");
		System.out.println(s.equals(s.clone()));
		
	}
	

}
