package componentiC;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import classiC.EdificioPrivato;
import classiC.EdificioPubblico;
import classiC.Lotto;
import classiC.Strada;

public final class Cella {
	
//instance variables
	
	private Lotto lotto;

	private int x, y;
	public static int LATO = 40;
	
//constructors
	
	/**
	 * Costruisce una nuova cella.
	 * @param lotto lotto di riferimento
	 * @param x x del vertice sinistro
	 * @param y y del vertice superiore
	 */
	public Cella (Lotto lotto, int x, int y) {
		
		this.lotto = lotto;
		
		this.x = x;
		this.y = y;
	}
	
//methods
	
	/**
	 * Disegna la cella.
	 * @param g2D oggetto di tipo Graphics2D
	 */
	public void disegna (Graphics2D g2D) {
		
		if (lotto.chkLottoLibero())
			
			g2D.setColor(Color.DARK_GRAY);
		
		if (lotto instanceof EdificioPubblico)
			
			g2D.setColor(Color.BLUE);
		
		if (lotto instanceof EdificioPrivato)
			
			g2D.setColor(Color.YELLOW);
		
		if (lotto instanceof Strada)
			
			g2D.setColor(Color.LIGHT_GRAY);
			
		g2D.fill(new Rectangle(x, y , LATO, LATO));
		
		g2D.setColor(Color.BLACK);
		g2D.draw(new Rectangle(x, y , LATO, LATO));
	}	

}
