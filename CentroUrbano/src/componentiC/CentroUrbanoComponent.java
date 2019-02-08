package componentiC;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import classiC.CentroUrbano;
import eccezioni_controllateC.ZoomNonValidoException;

public final class CentroUrbanoComponent extends JComponent {
	
//istance variables
	
	private CentroUrbano cb;
		
//constructors
		
	/**
	 * Costruisce un componente grafico del centro urbano.
	 * @param cb il centro urbano da disegnare
	 */
	public CentroUrbanoComponent (CentroUrbano cb) {
			
		this.cb = cb;
	}
	
//methods
	
	/**
	 * Disegna il centro urbano.
	 */
	public void paintComponent (Graphics g) {
		
		Graphics2D g2D = (Graphics2D) g;
		cb.disegna(g2D);
	}

	/**
	 * Zoom del 400% sul centro urbano.
	 * @throws ZoomNonValidoException
	 */
	public void zoomX400 () throws ZoomNonValidoException {
		
		if (Cella.LATO >= 320)
			
			throw new ZoomNonValidoException("Dimensioni troppo grandi");
		
		Cella.LATO *= 4;
	}
	
	/**
	 * Zoom del 200% sul centro urbano.
	 * @throws ZoomNonValidoException
	 */
	public void zoomX200 () throws ZoomNonValidoException {
		
		if (Cella.LATO >= 320)
			
			throw new ZoomNonValidoException("Dimensioni troppo grandi");
		
		Cella.LATO *= 2;
	}
	
	/**
	 * Zoom del 50% sul centro urbano.
	 * @throws ZoomNonValidoException
	 */
	public void zoomX50 () throws ZoomNonValidoException {
		
		if (Cella.LATO <= 5)
			
			throw new ZoomNonValidoException("Dimensioni troppo piccole");
		
		Cella.LATO /= 2;
	}
	
	/**
	 * Zoom del 25% sul centro urbano.
	 * @throws ZoomNonValidoException
	 */
	public void zoomX25 () throws ZoomNonValidoException {
		
		if (Cella.LATO <= 5)
			
			throw new ZoomNonValidoException("Dimensioni troppo piccole");
		
		Cella.LATO /= 4;
	}
}
