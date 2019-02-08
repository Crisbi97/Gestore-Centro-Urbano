package graficaC;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import classiC.CentroUrbano;
import eccezioni_non_controllateC.CentroUrbanoNulloException;
import eccezioni_non_controllateC.SettoreNulloException;

public class FrameIniziale extends JFrame {
	
//instance variables
	
	FrameConsole console;
	
	private JPanel panel;
	private boolean firstPanel = true;

	private JTextField nome;
	private JTextField righeCB;
	private JTextField colonneCB;
	private JTextField righeS;
	private JTextField colonneS;
	
	private JTextField path;

//constructor
	
	public FrameIniziale () {
		
		console = new FrameConsole();
		
		this.newFrame();
		this.newMenuBar();
		
		this.setVisible(true);
	}
	
//methods
	
	//frame
	private void newFrame () {
		
		this.setTitle("Frame Iniziale");
		this.setSize(300, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//panel
	private void newPanelNew () {
		
		panel = new JPanel(new BorderLayout());
		
		nome = new JTextField("Default", 15);
		
		righeCB = new JTextField("Righe", 5);
		colonneCB = new JTextField("Colonne" , 5);
		righeS = new JTextField("Righe", 5);
		colonneS = new JTextField("Colonne" , 5);
		
		JPanel pn = new JPanel();
		JPanel pc = new JPanel(new GridLayout(2, 3));
		JPanel ps = new JPanel();
		
		pn.add(new JLabel("Nome File: "));
		pn.add(nome);
		
		pc.add(new JLabel("Dim. C.Urbano: "));
		pc.add(righeCB);
		pc.add(colonneCB);
		
		pc.add(new JLabel("Dim. Settore: "));
		pc.add(righeS);
		pc.add(colonneS);

		ps.add(newButtonFinish());
		
		panel.add(pn, BorderLayout.NORTH);
		panel.add(pc, BorderLayout.CENTER);
		panel.add(ps, BorderLayout.SOUTH);

		firstPanel = false;
		this.add(panel);
	}
	
	private void newPanelOpen () {
		
		panel = new JPanel (new BorderLayout());
		
		path = new JTextField("Default.dat", 15);
		
		JPanel pc = new JPanel();
		JPanel ps = new JPanel();
		
		pc.add(new JLabel("Nome File: "));
		pc.add(path);
		
		ps.add(newButtonOpen());
		
		panel.add(pc, BorderLayout.CENTER);
		panel.add(ps, BorderLayout.SOUTH);

		firstPanel = false;
		this.add(panel);
	}
	
	//buttons
	private JButton newButtonFinish () {
		
		JButton b = new JButton("Finish");
		b.addActionListener(l -> {
			
			try {
				
				int rc  = Integer.parseInt(righeCB.getText());
				int cc = Integer.parseInt(colonneCB.getText());
				int rs = Integer.parseInt(righeS.getText());
				int cs = Integer.parseInt(colonneS.getText());	
				
				CentroUrbano cb = new CentroUrbano(rc, cc, rs, cs);
				FrameMenu f = new FrameMenu(cb, console, nome.getText());
				
				console.addNotifica("Centro Urbano creato con successo");
				
				this.dispose();
			}
			
			catch (NumberFormatException e) {
				
				console.addErrore("NumberFormatException: " + e.getMessage());
			}
			
			catch (CentroUrbanoNulloException e) {
				
				console.addErrore("CentroUrbanoNulloException: " + e.getMessage());
			}
			
			catch (SettoreNulloException e) {
				
				console.addErrore("SettoreNulloException: " + e.getMessage());
			}
		});
		
		return b;
	}
	
	private JButton newButtonOpen () {
		
		JButton b = new JButton("Open");
		b.addActionListener(l -> {
			
			File fp = new File(path.getText());
			
			if (fp.exists()) {
				
				try {
					
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(fp));
					CentroUrbano cb = (CentroUrbano) in.readObject();
					
					FrameMenu f = new FrameMenu(cb, console, path.getText());
					
					console.addNotifica("Centro Urbano apero con successo");
					
					in.close();
					this.dispose();
				}
				
				catch (IOException e) {
					
					console.addErrore("IOException: " + e.getMessage());
				}
				
				catch (ClassNotFoundException e) {
					
					console.addErrore("ClassNotFoundException: " + e.getMessage());
				}
				
			}
			
			else
				
				console.addErrore("File non trovato");
		});
		
		return b;
	}
	
	//menu bar
	private void newMenuBar () {
		
		JMenuBar mb = new JMenuBar();
		mb.setBorder(new EtchedBorder());
		
		mb.add(newMenu());
		
		this.setJMenuBar(mb);
	}
	
		private JMenu newMenu () {
		
			JMenu m = new JMenu("File");
			
			m.add(newItemNew());
			m.add(newItemOpen());
			m.add(newItemExit());
		
			return m;
		}
		
			private JMenuItem newItemNew () {
				
				JMenuItem mi = new JMenuItem("New");
				mi.addActionListener(l-> {
					
					if (!firstPanel)
						this.remove(panel);
					
					this.newPanelNew();
					this.validate();
				});
				
				return mi;
			}
			
			private JMenuItem newItemOpen () {
				
				JMenuItem mi = new JMenuItem("Open");
				mi.addActionListener(l-> {

					if (!firstPanel)
						this.remove(panel);
					
					this.newPanelOpen();
					this.validate();
				});
				
				return mi;
			}
	
			private JMenuItem newItemExit () {
				
				JMenuItem mi = new JMenuItem("Exit");
			
				mi.addActionListener(l-> {
					
					System.exit(NORMAL);
				});
				
				return mi;
			}
}
