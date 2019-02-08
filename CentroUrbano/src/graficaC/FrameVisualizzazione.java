package graficaC;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import classiC.CentroUrbano;
import componentiC.CentroUrbanoComponent;
import eccezioni_controllateC.ZoomNonValidoException;

public class FrameVisualizzazione extends JFrame{
	
//instance variables
	
	private CentroUrbano cb;
	private FrameConsole console;
	
	private CentroUrbanoComponent cbComponent;
	
	private JPanel panel;
	private JScrollPane scroll;
	
//constructor
	
	public FrameVisualizzazione (CentroUrbano cb, FrameConsole console) {
			
		this.cb = cb;
		this.console = console;
		
		cbComponent = new CentroUrbanoComponent(cb);
		
		this.newFrame();
		this.newMenuBar();
		this.newScroll();
			
		this.setVisible(true);
	}
		
//methods
		
	//frame
	private void newFrame () {
			
		this.setTitle("Frame Visualization");
		this.setSize(500, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//scroll
	private void newScroll () {
		
		this.newPanel();
		
		scroll = new JScrollPane(panel, 
								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
								JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scroll);
	}
	
		//panel
		private void newPanel () {
		
			panel = new JPanel(new BorderLayout());
			panel.setPreferredSize(new Dimension(cb.getAmpiezzaComponent(), cb.getAltezzaComponent()));
		
			panel.setBorder(new TitledBorder(new EtchedBorder(), "Map"));
			panel.setBackground(Color.WHITE);
		
			panel.add(cbComponent);
		}
	
	//menu bar
	private void newMenuBar () {
		
		JMenuBar mb = new JMenuBar();
		mb.setBorder(new EtchedBorder());
		
		mb.add(newMenuPlus());
		mb.add(newMenuMinus());
		mb.add(newMenuOptions());
		
		this.setJMenuBar(mb);
	}
	
		private JMenu newMenuPlus () {
		
			JMenu m = new JMenu("Zoom +");
			
			m.add(newItem400());
			m.add(newItem200());
			
			return m;
		}
		
			private JMenuItem newItem400 () {
			
				JMenuItem mi = new JMenuItem("400%");
				mi.addActionListener(l-> {
					
					try {
						
						cbComponent.zoomX400();
					}
					
					catch (ZoomNonValidoException e) {
						
						console.addErrore("ZoomNonValidoException: " + e.getMessage());
					}
					
 					this.remove(scroll);
 					this.newScroll();
 					this.validate();
				});
		
				return mi;
			}

			private JMenuItem newItem200 () {
			
				JMenuItem mi = new JMenuItem("200%");
				mi.addActionListener(l-> {
					
					try {
						
						cbComponent.zoomX200();
					}
					
					catch (ZoomNonValidoException e) {
						
						console.addErrore("ZoomNonValidoException: " + e.getMessage());
					}
					
 					this.remove(scroll);
 					this.newScroll();
 					this.validate();
				});
		
				return mi;
			}
		
		private JMenu newMenuMinus () {
			
			JMenu m = new JMenu("Zoom -");
			
			m.add(newItem50());
			m.add(newItem25());
			
			return m;
		}
			
			private JMenuItem newItem50 () {
				
				JMenuItem mi = new JMenuItem("50%");
				mi.addActionListener(l-> {
					
					try {
						
						cbComponent.zoomX50();
					}
					
					catch (ZoomNonValidoException e) {
						
						console.addErrore("ZoomNonValidoException: " + e.getMessage());
					}
					
 					this.remove(scroll);
 					this.newScroll();
 					this.validate();
				});
			
				return mi;
			}
			
			private JMenuItem newItem25() {
				
				JMenuItem mi = new JMenuItem("25%");
				mi.addActionListener(l-> {
					
					try {
						
						cbComponent.zoomX25();
					}
					
					catch (ZoomNonValidoException e) {
						
						console.addErrore("ZoomNonValidoException: " + e.getMessage());
					}
					
 					this.remove(scroll);
 					this.newScroll();
 					this.validate();
				});
			
				return mi;
			}
			
		private JMenu newMenuOptions () {
			
			JMenu m = new JMenu("Opzioni");
			
			m.add(newItemRic());
			
			return m;
		}
		
			private JMenuItem newItemRic() {
				
				JMenuItem mi = new JMenuItem("Ricarica");
				mi.addActionListener(l-> {
					
					this.remove(scroll);
					this.newScroll();
					this.validate();
				});
				
				return mi;
			}
}
