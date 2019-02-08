package graficaC;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import classiC.CentroUrbano;
import classiC.EdificioPrivato;
import classiC.EdificioPubblico;
import classiC.Lotto;
import classiC.Settore;
import classiC.Strada;
import eccezioni_controllateC.CentroUrbanoIndexOutOfBoundsException;
import eccezioni_controllateC.CentroUrbanoVuotoException;
import eccezioni_controllateC.SettoreIndexOutOfBoundsException;

public class FrameGestione extends JFrame {

//istance variables
	
	private CentroUrbano cb;
	private FrameConsole console;
	
	private boolean settoreSelezionato = false;
	private boolean lottoSelezionato1 = false;
	private boolean lottoSelezionato2 = false;
	
	private int clickCount = 0;
	
	private int rs, cs;
	private int r1, c1, r2, c2;
	
	private JPanel panel;
	private JPanel panelC;
	private JPanel panelSettore;
	
	private JTextField rigaS;
	private JTextField colonnaS;
	
	private JRadioButton lot;
	private JRadioButton str;
	
//costructor
		
	public FrameGestione (CentroUrbano cb, FrameConsole console) {
		
		this.cb = cb;
		this.console = console;
			
		this.newFrame();
		this.newPanel();
			
		this.setVisible(true);
	}
		
//methods
		
	//frame
	private void newFrame () {
			
		this.setTitle("Frame Gestione");
		this.setSize(450, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//panel
	private void newPanel () {
		
		panel = new JPanel(new BorderLayout());
		
		this.newPanelC();
		this.newPanelE();

		panel.add(newPanelC(), BorderLayout.CENTER);
		panel.add(newPanelE(), BorderLayout.EAST);
		
		this.add(panel);
	}
	
		//panel center
		private JPanel newPanelC () {
				
			panelC = new JPanel(new BorderLayout());
			
			panelC.add(newPanelCC(), BorderLayout.CENTER);
			panelC.add(newPanelCS(), BorderLayout.SOUTH);
			
			return panelC;
		}
		
			//panel center center
			private JPanel newPanelCC () {
				
				panelSettore = new JPanel(new GridLayout(cb.getRigheSettore(), cb.getColonneSettore()));
				
				panelSettore.setBorder(new TitledBorder(new EtchedBorder(), "Sector"));
				panelSettore.setBackground(Color.WHITE);
				
				return panelSettore;
			}
		
			//panel center south
			private JPanel newPanelCS () {
				
				JPanel p = new JPanel();
				
				p.add(newButtonInv());
				p.add(newButtonDis());
				
				return p;
			}
		
		//panel east
		private JPanel newPanelE () {
			
			JPanel p = new JPanel(new BorderLayout());
			
			p.add(newlPanelEN(), BorderLayout.NORTH);
			p.add(newPanelEC(), BorderLayout.CENTER);
			p.add(newlPanelES(), BorderLayout.SOUTH);
			
			return p;
		}
		
			//panel east north
			private JPanel newlPanelEN () {
			
				JPanel p = new JPanel(new BorderLayout());
				
				p.add(newPanelENN(), BorderLayout.NORTH);
				p.add(newPanelENC(), BorderLayout.CENTER);
			
				return p;
			}
			
				//panel east north north
				private JPanel newPanelENN () {
					
					JPanel p = new JPanel();
					
					rigaS = new JTextField(2);
					colonnaS = new JTextField(2);
					
					p.add(new JLabel("Settore: "));
					p.add(rigaS);
					p.add(new JLabel("x"));
					p.add(colonnaS);
					
					return p;
				}
				
				//panel east north center
				private JPanel newPanelENC () {
					
					JPanel p = new JPanel();
		
					p.add(newButtonSel());
					
					return p;
				}
			
			//panels east center
			private JPanel newPanelEC () {
				
				JPanel p = new JPanel();
				
				this.newRadioLot();
				this.newRadioStr();
				this.newRadioGrp();
				
				p.add(lot);
				p.add(str);
				
				return p;
			}
		
			//panel east south
			private JPanel newlPanelES () {
				
				JPanel p = new JPanel();
				
				p.add(newButtonMod());
				p.add(newButtonRic());
				
				return p;
			}
					
	//buttons
	private JButton newButtonInv () {
		
		JButton b = new JButton("Invecchiamento");
		b.addActionListener(l-> {
			
			try {
				
				this.cb.invecchiamento();
				console.addNotifica("Invecchiamento eseguito con successo");
			}
			
			catch (CentroUrbanoVuotoException e) {
				
				console.addErrore("CentroUrbanoVuotoException: " + e.getMessage());
			}
			
			catch (SettoreIndexOutOfBoundsException e) {
				
				console.addErrore("SettoreIndexOutOfBoundsException: " + e.getMessage());
			}
		});
		
		return b;
	}
	
	private JButton newButtonDis () {
		
		JButton b = new JButton("Disastro");
		b.addActionListener(l-> {
			
			try {
				
				int[] coordinate = new int [4];
				
				coordinate = this.cb.disastro();
				console.addNotifica("Disastro eseguito con successo sul: Settore (" + coordinate [0] + ", " + coordinate [1] + "), Lotto (" + coordinate [2] + ", " + coordinate [3] + ")");
			}
			
			catch (CentroUrbanoVuotoException e) {
				
				console.addErrore("CentroUrbanoVuotoException: " + e.getMessage());
			}
		});
		
		return b;
	}
	
	private JButton newButtonSel () {
		
		JButton b = new JButton("Seleziona settore");
		b.addActionListener(l-> {
			
			panelC.remove(panelSettore);
			panelC.add(newPanelCC(), BorderLayout.CENTER);
			panelC.validate();
			
			try {
				
				rs  = Integer.parseInt(rigaS.getText());
				cs = Integer.parseInt(colonnaS.getText());
				
				Settore s = cb.getSettore(rs, cs);
				settoreSelezionato = true;
				
				for (int i = 0; i < s.getRighe(); i ++) 
					
					for (int j = 0; j < s.getColonne(); j ++) {
						
						PanelCella pc = new PanelCella(s.getLotto(i, j), i, j);
						pc.addMouseListener(new CustomListener());
						
						panelSettore.add(pc);
					}
									
				panelSettore.validate();
			}
			
			catch (NumberFormatException e) {
				
				console.addErrore("NumberFormatException: " + e.getMessage());
			}	
			
			catch (CentroUrbanoIndexOutOfBoundsException e) {
				
				console.addErrore("CentroUrbanoIndexOutOfBoundsException: " + e.getMessage());
			}
			
			catch (SettoreIndexOutOfBoundsException e) {
				
				console.addErrore("SettoreIndexOutOfBoundsException: " + e.getMessage());
			}
		});
		
		return b;
	}
	
	private JButton newButtonMod () {
		
		JButton b = new JButton("Modifica");
		b.addActionListener(l -> {
			
			FrameModifica fm;
			FrameStrada fs;
			
			if (lot.isSelected()) {
				
				if (settoreSelezionato) {
					
					if (lottoSelezionato1) {
						
						fm = new FrameModifica(cb, console, rs, cs, r1, c1);
						
						lottoSelezionato1 = false;
						lottoSelezionato2 = false;
						
						clickCount = 0;	
					}
					
					else 
						
						console.addErrore("Selezionare un lotto");
				}
				
				else
					
					console.addErrore("Selezionare un settore");
			}
				
			else if (str.isSelected()) {
				
				if (settoreSelezionato) {
					
					if (lottoSelezionato1 && lottoSelezionato2) {
						
						fs = new FrameStrada(cb, console, rs, cs, r1, c1, r2, c2);
						
						lottoSelezionato1 = false;
						lottoSelezionato2 = false;
						
						clickCount = 0;	
					}
					
					else if (!lottoSelezionato1 && !lottoSelezionato2)
						
						console.addErrore("Selezionare due lotti");
					
					else if (!lottoSelezionato2)
						
						console.addErrore("Selezionare il secondo lotto");
				}
				
				else
					
					console.addErrore("Selezionare un settore");
			}
					
			else
				
				console.addErrore("Selezionare il tipo di Modifica");
		});
		
		return b;
	}
	
	private JButton newButtonRic () {
		
		JButton b = new JButton("Ricarica");
		b.addActionListener(l-> {
			
			if (settoreSelezionato) {
				
				try {
					
					panelC.remove(panelSettore);
					panelC.add(newPanelCC(), BorderLayout.CENTER);
					panelC.validate();
						
					Settore s = cb.getSettore(rs, cs);
					
					lottoSelezionato1 = false;
					lottoSelezionato2 = false;
					
					clickCount = 0;	
						
					for (int i = 0; i < s.getRighe(); i ++) 
							
						for (int j = 0; j < s.getColonne(); j ++) {
								
							PanelCella pc = new PanelCella(s.getLotto(i, j), i, j);
							pc.addMouseListener(new CustomListener());
								
							panelSettore.add(pc);
						}
											
					panelSettore.validate();	
				}
				
				catch (CentroUrbanoIndexOutOfBoundsException e) {
					
					console.addErrore("CentroUrbanoIndexOutOfBoundsException: " + e.getMessage());
				}
				
				catch (SettoreIndexOutOfBoundsException e) {
					
					console.addErrore("SettoreIndexOutOfBoundsException: " + e.getMessage());
				}
			}	
			
			else
				
				console.addErrore("Selezionare un settore");
		});
		
		return b;
	}
	
	//radio buttons
	private void newRadioLot () {
		
		lot = new JRadioButton("Lotto");
	}
	
	private void newRadioStr () {
		
		str = new JRadioButton("Strada");
	}
	
	//radio group
	private void newRadioGrp () {
		
		ButtonGroup bg = new ButtonGroup();
		
		bg.add(lot);
		bg.add(str);
	}
	
	//mouse listener
	class CustomListener implements MouseListener {
		
		public void mousePressed(MouseEvent e) {
			
			PanelCella pc;
	
			if (e.getComponent() instanceof PanelCella) {
				
				 pc = (PanelCella) e.getComponent();
				
				 clickCount ++;
				 
				 if (clickCount == 1) {
					 
					 lottoSelezionato1 = true;
					 
					 pc.setBorder(BorderFactory.createLineBorder(Color.RED));
					 
					 r1 = pc.getR();
					 c1 = pc.getC();
				 }
				 
				 if (clickCount == 2) {
					 
					 lottoSelezionato2 = true;
					 
					 pc.setBorder(BorderFactory.createLineBorder(Color.RED));
					 
					 r2 = pc.getR();
					 c2 = pc.getC();
				 }
			}
		}
		
		public void mouseReleased(MouseEvent e) {}
		
		public void mouseClicked(MouseEvent e) {}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	final class PanelCella extends JPanel {
		
	//instance variables
		
		private int r, c;

	//constructors
		
		/**
		 * Costruisce un nuovo Panel che rappresenta una cella
		 * @param lotto lotto a cui si riferisce la cella
		 * @param r riga del lotto
		 * @param c conolla del lotto
		 */
		public PanelCella (Lotto lotto, int r, int c) {
			
			this.r = r;
			this.c = c;
			
			this.setBorder(new EtchedBorder());
			
			if (lotto.chkLottoLibero())
				
				this.setBackground(Color.DARK_GRAY);
			
			if (lotto instanceof EdificioPubblico)
				
				this.setBackground(Color.BLUE);
			
			if (lotto instanceof EdificioPrivato)
				
				this.setBackground(Color.YELLOW);
			
			if (lotto instanceof Strada)
				
				this.setBackground(Color.LIGHT_GRAY);
		}
		
	//methods
		
		/**
		 * Restituisce la riga del lotto a cui si riferisce la cella
		 * @return riga del lotto
		 */
		public int getR () {
			
			return this.r;
		}
		
		/**
		 * Restituisce la colonna del lotto a cui si riferisce la cella
		 * @return colonna del lotto
		 */
		public int getC () {
			
			return this.c;
		}
	}
}
