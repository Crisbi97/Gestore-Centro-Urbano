package interfaccieC;

import classiC.Lotto;

public interface CheckLotto {

	/**
	 * Testa un lotto secondo un criterio.
	 * @param l il lotto da testare
	 * @return il risultato del test
	 */
	boolean test (Lotto l);
	
	//lotti = l -> true;
	//lotti occ = l -> !l.chkLottoLibero
	//lotti lib = l -> l.chkLottoLibero
	//ed pbl = l -> l instance of EdificioPubblico
	//ed prv = l -> l instance of EdificioPrivato
	//str = l -> l instance of Strada
	//cof >< x = l.getcoff >< x
	
}
