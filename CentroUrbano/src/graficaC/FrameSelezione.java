package graficaC;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import classiC.CentroUrbano;
import classiC.EdificioPrivato;
import classiC.EdificioPubblico;
import classiC.Lotto;
import classiC.Strada;
import eccezioni_controllateC.ListaVuotaException;

public class FrameSelezione extends JFrame {
	
//istance variables
	
	private CentroUrbano cb;
	private FrameConsole console;
	
	private JPanel panel;
	private JPanel panelC;
	private JTextArea testo;
	
	private JComboBox <String> comboSel;
	private JComboBox <String> comboOrd;
	
//costructor
	
	public FrameSelezione (CentroUrbano cb, FrameConsole console) {
		
		this.cb = cb;
		this.console = console;
		
		this.newFrame();
		this.newPanel();
		
		this.setVisible(true);
	}
	
//methods
	
	//frame
	private void newFrame () {
		
		this.setTitle("Frame Selezione");
		this.setSize(800, 250);
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
			
			panelC = new JPanel();
			
			panelC.add(newTextArea());
			
			return panelC;
		}
		
		//panel south
		private JPanel newPanelS () {
			
			JPanel p = new JPanel(new BorderLayout());
			
			p.add(newPanelSN(), BorderLayout.NORTH);
			p.add(newPanelSC(), BorderLayout.CENTER);
			
			return p;
		}
		
			//panel east north
			private JPanel newPanelSN () {
				
				JPanel p = new JPanel();
				
				p.add(newComboSel());
				p.add(newComboOrd());
				
				
				return p;
			}
			
			//panel east center
			private JPanel newPanelSC () {
				
				JPanel p = new JPanel();
				
				p.add(newButtonVis());
				
				return p;
			}
			
		//text area
		private JScrollPane newTextArea () {
			
			testo = new JTextArea(5, 70);
			testo.setBorder(new TitledBorder(new EtchedBorder(), "Lista"));
			testo.setEditable(false);
				
			JScrollPane scroll = new JScrollPane(testo,
												JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
												JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			return scroll;
		}
		
		//combo box
		private JComboBox<String> newComboSel () {
			
			comboSel = new JComboBox<>();
			
			comboSel.addItem("Tutti i lotti");
			comboSel.addItem("Coef. Eff. >= 50");
			comboSel.addItem("Coef. Eff. < 50");
			comboSel.addItem("Coef. Inv. >= 5");
			comboSel.addItem("Coef. Inv. < 5");
			comboSel.addItem("Ed. Pubblici");
			comboSel.addItem("Ed. Privati");
			comboSel.addItem("Strade");
			
			return comboSel;
		}
		
		private JComboBox<String> newComboOrd () {
			
			comboOrd = new JComboBox<>();
			
			comboOrd.addItem("Crescente Eff.");
			comboOrd.addItem("Decrescente Eff.");
			comboOrd.addItem("Crescente Inv.");
			comboOrd.addItem("Decrescente Inv.");
			
			return comboOrd;
		}
		
		//button
		private JButton newButtonVis () {
			
			JButton b = new JButton("Visualizza");
			b.addActionListener(l-> {
				
				panel.remove(panelC);
				panel.add(newPanelC(), BorderLayout.CENTER);
				panel.validate();
				
				ArrayList<Lotto> lista = new ArrayList<>();
				
				int sel = comboSel.getSelectedIndex();
				int ord = comboOrd.getSelectedIndex();
				
				if (sel != - 1) {
					
					if (sel == 0) lista = cb.selezionaLotti(t -> true);
					if (sel == 1) lista = cb.selezionaLotti(t -> t.getEfficienza() >= 50);
					if (sel == 2) lista = cb.selezionaLotti(t -> t.getEfficienza() < 50);
					if (sel == 3) lista = cb.selezionaLotti(t -> t.getInvecchiamento() >= 5);
					if (sel == 4) lista = cb.selezionaLotti(t -> t.getInvecchiamento() < 5);;
					if (sel == 5) lista = cb.selezionaLotti(t -> t instanceof EdificioPubblico);
					if (sel == 6) lista = cb.selezionaLotti(t -> t instanceof EdificioPrivato);
					if (sel == 7) lista = cb.selezionaLotti(t -> t instanceof Strada);
				}
				
				try {
					
					if (ord != -1) {

						if (ord == 0) cb.ordinaLotti(new Comparator<Lotto>() {
							
							public int compare(Lotto l1, Lotto l2) {
								
								int i = l1.getEfficienza();
								int j = l2.getEfficienza();
								
								return i - j;
							}
						}, lista);
						
						if (ord == 1) cb.ordinaLotti(new Comparator<Lotto>() {
							
							public int compare(Lotto l1, Lotto l2) {
								
								int i = l1.getEfficienza();
								int j = l2.getEfficienza();
								
								return j - i;
							}
						}, lista);
						
						if (ord == 2) cb.ordinaLotti(new Comparator<Lotto>() {
							
							public int compare(Lotto l1, Lotto l2) {
								
								int i = l1.getInvecchiamento();
								int j = l2.getInvecchiamento();
								
								return i - j;
							}
						}, lista);
						
						if (ord == 3) cb.ordinaLotti(new Comparator<Lotto>() {
							
							public int compare(Lotto l1, Lotto l2) {
								
								int i = l1.getInvecchiamento();
								int j = l2.getInvecchiamento();
								
								return j - i;
							}
						}, lista);
					}
					
					for (int i = 0; i < lista.size(); i ++)
						
						testo.append(lista.get(i).toString() + "\n");
				}
				
				catch (ListaVuotaException e) {
					
					console.addErrore("ListaVuotaException: " + e.getMessage());
				}
				
			});
			
			return b;		
		}
}
