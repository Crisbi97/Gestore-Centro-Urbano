package graficaC;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import classiC.CentroUrbano;
import classiC.EdificioPrivato;
import classiC.EdificioPubblico;
import classiC.Strada;
import eccezioni_controllateC.LottoOccupatoException;
import eccezioni_controllateC.SettoreIndexOutOfBoundsException;

public class FrameEdificio extends JFrame {

//istance variables
	
	private CentroUrbano cb;
	private FrameConsole console;
	
	private int rs, cs;
	private int rl, cl;
	
	private JPanel panel;
	
	private JTextField nome;
	private JTextField valore;
	private JTextField cofEff;
	private JTextField cofInv;
	
	private JRadioButton pbl;
	private JRadioButton prv;
			
//costructor
				
	public FrameEdificio (CentroUrbano cb, FrameConsole console, int rs, int cs, int rl, int cl) {
		
		this.cb =cb;
		this.console = console;
		
		this.rs = rs;
		this.cs = cs;
		
		this.rl = rl;
		this.cl = cl;
		
		this.newFrame();
		this.newPanle();	
		
		this.setVisible(true);
	}
				
//methods
			
	//frame
	private void newFrame () {
					
		this.setTitle("Frame Edificio");
		this.setSize(300, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	//panel
	private void newPanle () {
		
		panel = new JPanel(new BorderLayout());
		
		panel.add(newPanelN(), BorderLayout.NORTH);
		panel.add(newPanelC(), BorderLayout.CENTER);
		panel.add(newPanelS(), BorderLayout.SOUTH);
		
		this.add(panel);
	}
	
		//panel north
		private JPanel newPanelN () {
		
			JPanel p = new JPanel();
			
			this.newRadioPbl();
			this.newRadioPrv();
			this.newRadioGrp();
			
			p.add(pbl);
			p.add(prv);
		
			return p;
		}
		
		//panel center
		private JPanel newPanelC () {
			
			JPanel p = new JPanel(new GridLayout(4, 2));
			
			nome = new JTextField("Default Name", 10);
			valore = new JTextField("000", 10);
			cofEff = new JTextField("000", 10);
			cofInv = new JTextField("000", 10);
			
			p.add(new JLabel("Nome: "));
			p.add(nome);
			p.add(new JLabel("Valore: "));
			p.add(valore);
			p.add(new JLabel("Cof. Efficienza: "));
			p.add(cofEff);
			p.add(new JLabel("Cof. Invecchiamento: "));
			p.add(cofInv);
			
			return p;
		}
		
		//panel south
		private JPanel newPanelS () {
			
			JPanel p = new JPanel();
			
			p.add(newButtonOk());
			
			return p;
		}
		
	//buttons
	private JButton newButtonOk () {
			
		JButton b = new JButton("OK");
		b.addActionListener(l -> {
			
			try {
				
				int val  = Integer.parseInt(valore.getText());
				int eff = Integer.parseInt(cofEff.getText());
				int inv = Integer.parseInt(cofInv.getText());
				
				EdificioPubblico edPbl;
				EdificioPrivato edPrv;
				
				if (pbl.isSelected()) {
					
					edPbl = new EdificioPubblico(eff, inv, nome.getText(), val);
					
					cb.chgLotto(edPbl, rs, cs, rl, cl);
					
					console.addNotifica("Edificio Pubblico creato con successo");
					this.dispose();
				}
					
				else if (prv.isSelected()) {
					
					edPrv = new EdificioPrivato(eff, inv, nome.getText(), val);
					
					cb.chgLotto(edPrv, rs, cs, rl, cl);
					
					console.addNotifica("Edificio Privato creato con successo");
					this.dispose();
				}
				
				else
					
					console.addErrore("Selezionare il tipo di Edificio");

			}
			
			catch (NumberFormatException e) {
				
				console.addErrore("NumberFormatException: " + e.getMessage());
			}
			
			catch (LottoOccupatoException e) {
				
				console.addErrore("LottoOccupatoException: " + e.getMessage());
				this.dispose();
			}
			
			catch (SettoreIndexOutOfBoundsException e) {
				
				console.addErrore("SettoreIndexOutOfBoundsException: " + e.getMessage());
			}
		});
			
		return b;
	}
	
	//radio buttons
	private void newRadioPbl () {
		
		pbl = new JRadioButton("Pubblico");
	}
	
	private void newRadioPrv () {
		
		prv = new JRadioButton("Privato");
	}
	
	//radio group
	private void newRadioGrp () {
		
		ButtonGroup bg = new ButtonGroup();
		
		bg.add(pbl);
		bg.add(prv);
	}
		
}
