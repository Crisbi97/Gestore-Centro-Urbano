package graficaC;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class FrameConsole extends JFrame {
	
//instance variables
	
	private JPanel panel;
	private JTextArea console;
	
//constructors
	
	public FrameConsole () {
		
		this.newFrame();
		this.newPanel();
		
		this.setVisible(true);
	}
	
//methods
	
	public void addNotifica (String msg) {
		
		console.append("Notifica - " + msg + "\n");
	}
	
	public void addErrore (String msg) {
		
		console.append("Errore - " + msg + "\n");
	}

	//frame
	private void newFrame () {
			
		this.setTitle("Frame Console");
		this.setSize(420, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//panel
	private void newPanel () {
		
		panel = new JPanel(new BorderLayout());
		
		panel.add(newConsole(), BorderLayout.CENTER);
		panel.add(newPanelS(), BorderLayout.SOUTH);
		
		this.add(panel);
	}
	
		//panel south
		private JPanel newPanelS () {
			
			JPanel p = new JPanel();
			p.add(newButtonClr());
			
			return p;
		}
	
	//text area
	private JScrollPane newConsole () {
		
		console = new JTextArea(5, 5);
		console.setBorder(new TitledBorder(new EtchedBorder(), "Console"));
		console.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(console);
		return scroll;
	}
	
	//button
	private JButton newButtonClr () {
		
		JButton b = new JButton("Clear");
		b.addActionListener(l-> {
			
			this.remove(panel);
			this.newPanel();
			this.validate();
		});
		
		return b;
	}
	
}
