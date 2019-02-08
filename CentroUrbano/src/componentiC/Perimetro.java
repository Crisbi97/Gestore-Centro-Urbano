package componentiC;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public final class Perimetro {

//instance variables
	
	private int x, y, w, h;
	private Color c;
		
//constructors
		
	/**
	 * Costruisce un nuovo perimetro.
	 * @param c colore del perimetro
	 * @param x x del vertice sinistro
	 * @param y y del vertice superiore
	 * @param w ampiezza del perimetro
	 * @param h altezza del perimetro
	 */
	public Perimetro (Color c, int x, int y, int w, int h) {
		
		this.c = c;
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
		
//methods
		
	/**
	 * Disegna il perimetro.
	 * @param g2D oggetto di tipo Graphics2D
	 */
	public void disegna (Graphics2D g2D) {
		
		g2D.setColor(c);
		g2D.draw(new Rectangle(x * Cella.LATO, y * Cella.LATO, w * Cella.LATO, h * Cella.LATO));
	}
}
