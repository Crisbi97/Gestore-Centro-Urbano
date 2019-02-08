package graficaC;

import java.awt.BorderLayout;

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
import eccezioni_controllateC.CentroUrbanoIndexOutOfBoundsException;
import eccezioni_controllateC.EdificioInDemolizioneException;
import eccezioni_controllateC.LottoLiberoException;
import eccezioni_controllateC.SettoreIndexOutOfBoundsException;

public class FrameModifica extends JFrame {
	
//istance variables
	
	private CentroUrbano cb;
	private FrameConsole console;
	
	private int rs, cs;
	private int rl, cl;

	
	private JPanel panel;
		
//costructor
			
	public FrameModifica (CentroUrbano cb, FrameConsole console, int rs, int cs, int rl, int cl) {
			
		this.cb = cb;
		this.console = console;
		
		this.rs = rs;
		this.cs = cs;
		
		this.rl = rl;
		this.cl = cl;
		
		this.newFrame();
		this.newPanel();
				
		this.setVisible(true);
	}
			
//methods
		
	//frame
	private void newFrame () {
				
		this.setTitle("Frame Modifica");
		this.setSize(300, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//panel
	private void newPanel () {
		
		panel = new JPanel(new BorderLayout());
		
		panel.add(newPanelC(), BorderLayout.CENTER);
		panel.add(newPanelS(), BorderLayout.SOUTH);

		this.add(panel);
	}
	
		//panel center
		private JPanel newPanelC () {
			
			JPanel p = new JPanel(new BorderLayout());
			
			p.add(newPanelCC(), BorderLayout.CENTER);
			p.add(newPanelCS(), BorderLayout.SOUTH);
			
			return p;
		}
		
			//panel center center
			private JPanel newPanelCC () {
			
				JPanel p = new JPanel();
			
				p.add(newTextArea());
			
				return p;
			}
		
			//panel center south
			private JPanel newPanelCS () {
				
				JPanel p = new JPanel();
			
				p.add(newButtonDml());
				p.add(newButtonRst());
				
				return p;
			}
		
		//panel south
		private JPanel newPanelS () {
			
			JPanel p = new JPanel(new BorderLayout());
			
			p.add(newButtonEdf(), BorderLayout.CENTER);
			p.add(newButtonStr(), BorderLayout.SOUTH);
			
			return p;
		}
	
	//buttons
	private JButton newButtonDml () {
		
		JButton b = new JButton("Demolizione");
		b.addActionListener(l-> {
			
			try {
				
				cb.demolizione(rs, cs, rl, cl);
				
				if (cb.getSettore(rs, cs).getLotto(rl, cl) == null)
					
					console.addNotifica("Demolizione eseguita con successo");
				
				if (cb.getSettore(rs, cs).getLotto(rl, cl) instanceof EdificioPubblico)
					
					console.addNotifica("Demolizione avviata con successo");
				
				this.dispose();
			} 
			
			catch (LottoLiberoException e) {

				console.addErrore("LottoLiberoException: " + e.getMessage());
				this.dispose();
			}
			
			catch (EdificioInDemolizioneException e) {
				
				console.addErrore("EdificioInDemolizioneException: " + e.getMessage());
				this.dispose();
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
	
	private JButton newButtonRst () {
		
		JButton b = new JButton("Ristrutturazione");
		b.addActionListener(l-> {
			
			try {
				
				cb.ristrutturazione(rs, cs, rl, cl);
				
				console.addNotifica("Ristrutturazione eseguita con successo");
				
				this.dispose();
			}
			
			catch (LottoLiberoException e) {
				
				console.addErrore("LottoLiberoException: " + e.getMessage());
				this.dispose();
			}
			
		});
		
		return b;
	}
	
	private JButton newButtonEdf () {
		
		JButton b = new JButton("Edificio");
		b.addActionListener(l ->{
			
			FrameEdificio f = new FrameEdificio(cb, console, rs, cs, rl, cl);
			this.dispose();
		});
		
		return b;
	}
	
	private JButton newButtonStr () {
		
		JButton b = new JButton("Strada");
		b.addActionListener(l ->{
			
			FrameStrada f = new FrameStrada(cb, console, rs, cs, rl, cl, rl, cl);
			this.dispose();
		});
		
		return b;
	}
	
	//text area
	private JTextArea newTextArea () {
		
		JTextArea ta = new JTextArea(9, 25);
		ta.setBorder(new TitledBorder(new EtchedBorder(), "Description"));
		ta.setEditable(false);
			
		try {
			
			if (cb.getSettore(rs, cs).getLotto(rl, cl) instanceof EdificioPubblico) {
				
				EdificioPubblico edPbl = (EdificioPubblico) cb.getSettore(rs, cs).getLotto(rl, cl);
				
				ta.append("Edificio Pubblico\n");
				ta.append("Cof. Efficienza:\t\t" + edPbl.getEfficienza() + "\n");
				ta.append("Cof. Invecchiamento:\t" + edPbl.getInvecchiamento() + "\n");
				ta.append("Nome:\t\t" + edPbl.getNome() + "\n");
				ta.append("Bonus Valore:\t\t" + edPbl.getBuffValore()  + "\n");
			}
				
			if (cb.getSettore(rs, cs).getLotto(rl, cl) instanceof EdificioPrivato) {
				
				EdificioPrivato edPrv = (EdificioPrivato) cb.getSettore(rs, cs).getLotto(rl, cl);
				
				ta.append("Edificio Privato\n");
				ta.append("Cof. Efficienza:\t\t" + edPrv.getEfficienza() + "\n");
				ta.append("Cof. Invecchiamento:\t" + edPrv.getInvecchiamento() + "\n");
				ta.append("Nome:\t\t" + edPrv.getNome() + "\n");
				ta.append("Valore:\t\t" + edPrv.getValore()  + "\n");	
			}
					
			if (cb.getSettore(rs, cs).getLotto(rl, cl) instanceof Strada) {
				
				Strada str = (Strada) cb.getSettore(rs, cs).getLotto(rl, cl);
				
				ta.append("Strada\n");
				ta.append("Cof. Efficienza:\t\t" + str.getEfficienza() + "\n");
				ta.append("Cof. Invecchiamento:\t" + str.getInvecchiamento() + "\n");
				ta.append("Bonus Valore:\t\t" + str.getBuffValore()  + "\n");	
			}
			
			else if (cb.getSettore(rs, cs).getLotto(rl, cl) == null)
				
				ta.append("Nessuna informazione disponibile\n");
		}
		
		catch (CentroUrbanoIndexOutOfBoundsException e) {
			
			console.addErrore("CentroUrbanoIndexOutOfBoundsException: " + e.getMessage());
		}
		
		catch (SettoreIndexOutOfBoundsException e) {
			
			console.addErrore("SettoreIndexOutOfBoundsException: " + e.getMessage());
		}
		
		return ta;
	}

}
