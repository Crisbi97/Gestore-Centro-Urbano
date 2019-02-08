package graficaC;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classiC.CentroUrbano;
import classiC.Strada;
import eccezioni_controllateC.LottoOccupatoException;
import eccezioni_controllateC.SettoreIndexOutOfBoundsException;
import eccezioni_controllateC.StradaDiagonaleException;

public class FrameStrada extends JFrame {

//istance variables
	
	private CentroUrbano cb;
	private FrameConsole console;
	
	private int rs, cs;
	private int r1, c1, r2, c2;

	private JPanel panel;
	
	private JTextField valore;
	private JTextField cofEff;
	private JTextField cofInv;
			
//costructor
				
	public FrameStrada (CentroUrbano cb, FrameConsole console, int rs, int cs, int r1, int c1, int r2, int c2) {
		
		this.cb = cb;
		this.console = console;
		
		this. rs = rs;
		this.cs = cs;
		
		this.r1 = r1;
		this.c1 = c1;
		this.r2 = r2;
		this.c2 = c2;
					
		this.newFrame();
		this.newPanle();	
		
		this.setVisible(true);
	}
				
//methods
			
	//frame
	private void newFrame () {
					
		this.setTitle("Frame Strada");
		this.setSize(300, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//panel
	private void newPanle () {
		
		panel = new JPanel(new BorderLayout());
		
		panel.add(newPanelC(), BorderLayout.CENTER);
		panel.add(newPanelS(), BorderLayout.SOUTH);
		
		this.add(panel);
	}
	
		//panel center
		private JPanel newPanelC () {
			
			JPanel p = new JPanel(new GridLayout(4, 2));
			
			valore = new JTextField("000", 10);
			cofEff = new JTextField("000", 10);
			cofInv = new JTextField("000", 10);
			
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
				
				Strada s = new Strada(eff, inv, val);
				
				cb.addStrada(s, rs, cs, r1, c1, r2, c2);
				
				console.addNotifica("Strada creata con successo");
				this.dispose();
			}
			
			catch (NumberFormatException e) {
				
				console.addErrore("NumberFormatException: " + e.getMessage());
			}
			
			catch (LottoOccupatoException e) {
				
				console.addErrore("LottoOccupatoException: " + e.getMessage());
				this.dispose();
			}
			
			catch (StradaDiagonaleException e) {
				
				console.addErrore("StradaDiagonaleException: " + e.getMessage());
				this.dispose();
			}
			
			catch (SettoreIndexOutOfBoundsException e) {
				
				console.addErrore("SettoreIndexOutOfBoundsException: " + e.getMessage());
			}
		});
		
		return b;
	}
		
}
