package graficaC;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import classiC.CentroUrbano;
import classiC.EdificioPrivato;
import classiC.EdificioPubblico;
import classiC.Strada;

public class FrameMenu extends JFrame{
	
//instance variables
	
	private CentroUrbano cb;
	private FrameConsole console;
	private String nomeFile;
	
	private JPanel panel;
	private JTextArea descrizione;
	
//constructor
	
	public FrameMenu (CentroUrbano cb, FrameConsole console, String nomeFile) {
		
		this.cb = cb;
		this.console = console;
		this.nomeFile = nomeFile;
		
		this.newFrame();
		this.newPanel();
		
		this.setVisible(true);
	}
	
//methods
	
	//frame
	private void newFrame () {
		
		this.setTitle("Frame Menu");
		this.setSize(350, 250);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//panel
	private void newPanel () {
		
		panel = new JPanel(new BorderLayout());
		
		panel.add(newPanelC(), BorderLayout.CENTER);
		panel.add(newPanelE(), BorderLayout.EAST);
		
		this.add(panel);
	}
	
		//panel center
		private JPanel newPanelC () {
			
			JPanel p = new JPanel();
			
			this.newTextDsc();
			p.add(descrizione);
			
			return p;
		}
		
		//panel east
		private JPanel newPanelE () {
			
			JPanel p = new JPanel(new GridLayout(5, 1));
			
			p.add(newButtonGes());
			p.add(newButtonSel());
			p.add(newButtonVis());
			p.add(newButtonSav());
			p.add(newButtonRic());
			
			return p;
		}
	
	//text area
	private void newTextDsc () {
		
		descrizione = new JTextArea(12, 18);
		descrizione.setBorder(new TitledBorder(new EtchedBorder(), "Description"));
		descrizione.setEditable(false);

		descrizione.append("Nome:\t" + this.nomeFile + "\n");
		descrizione.append("#Settori:\t\t" + cb.numeroSettori() + "\n"); 
		descrizione.append("#Lotti Totali:\t\t" + cb.numeroLotti(l -> true) + "\n"); 
		descrizione.append("#Lotti Vuoti:\t\t" + cb.numeroLotti(l -> l.chkLottoLibero()) + "\n");
		descrizione.append("#Lotti Occupati:\t\t" + cb.numeroLotti(l -> !l.chkLottoLibero()) + "\n"); 
		descrizione.append("#Edifici Pbl:\t\t" + cb.numeroLotti(l -> l instanceof EdificioPubblico) + "\n"); 
		descrizione.append("#Edifici Prv:\t\t" + cb.numeroLotti(l -> l instanceof EdificioPrivato) + "\n"); 
		descrizione.append("#Strade:\t\t" + cb.numeroLotti(l -> l instanceof Strada) + "\n"); 
	}
	
	//buttons
	private JButton newButtonGes () {
		
		JButton b = new JButton("Gestione");
		b.addActionListener(l -> {
			
			FrameGestione f = new FrameGestione(cb, console);
		});
		
		return b;
	}
	
	private JButton newButtonSel () {
		
		JButton b = new JButton("Selezione");
		b.addActionListener(l -> {
			
			FrameSelezione f = new FrameSelezione(cb, console);
		});
		
		return b;
	}
	
	private JButton newButtonVis () {
		
		JButton b = new JButton("Visualizzazione");
		b.addActionListener(l -> {
			
			FrameVisualizzazione f = new FrameVisualizzazione(cb, console);
		});
		
		return b;
	}
	
	private JButton newButtonSav () {
		
		JButton b = new JButton("Salvataggio");
		b.addActionListener(l -> {
			
			try {
				
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFile));
				out.writeObject(cb);
				
				console.addNotifica("Salvataggio eseguito con successo");
				
				out.close();	
			}
			
			catch (IOException e) {
				
				console.addErrore("IOException: " + e.getMessage());
			}
		});
		
		return b;
	}
	
	private JButton newButtonRic () {
		
		JButton b = new JButton("Ricarica");
		b.addActionListener(l-> {
			
			this.remove(panel);
			this.newPanel();
			this.validate();
		});
		
		return b;
	}
}
