import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class InfoFrame extends JFrame {
	
	private static final int WIDTH = 650;
	private static final int HEIGHT = 200;
	private static JPanel panel;
	private static JPanel coloredPanel;
	private static JLabel[] instructions =  new JLabel[6];
	
	
	//COSTRUTTORE
	public InfoFrame() {
		
		//impostazioni finestra
		super("Regole");
		
		setSize(WIDTH, HEIGHT);
		
		URL iconInfo_url = getClass().getResource("info.png");
        Image iconInfo = new ImageIcon(iconInfo_url).getImage();
        setIconImage(iconInfo);
        
        Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int x=(screenSize.width/2)-(WIDTH/2);
		int y=(screenSize.height/2)-(HEIGHT/2);
		setLocation(x, y);
		
		
		//impostazioni delle label
		Font testo = new Font("Verdana", Font.PLAIN, 15);
		Font titolo = new Font("Engravers MT", Font.BOLD, 15);
		
		panel = new JPanel();
		
		//pannello colorato
		coloredPanel = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        coloredPanel.setLayout(layout);
        Color myColor = new Color(219, 198, 135);
		coloredPanel.setBackground(myColor);
		coloredPanel.setPreferredSize(new Dimension(600, 150));
		
		
		//label di testo
		instructions[0] = new JLabel("REGOLE DEL SUDOKU");
		instructions[1] = new JLabel("Lo scopo del gioco è quello di riempire le caselle bianche con numeri da 1 a 9,");
		instructions[2] = new JLabel("in modo tale che in ogni riga, in ogni colonna e in ogni regione quadrata");
		instructions[3] = new JLabel("con bordi in neretto, siano presenti tutte le cifre da 1 a 9 senza ripetizioni.");
		instructions[4] = new JLabel("Ma questa è solo la regola.");
		//instructions[5] = new JLabel("Ma questa è solo la regola.");
		instructions[5] = new JLabel("Per risolvere effettivamente il gioco ci vuole logica e inventiva.");        
        
		
        //aggiungo tutto alla finestra
        for(int i=0; i<instructions.length; i++) {
	        if(i==0) {
	        	instructions[i].setFont(titolo);
	        }
        	else{
        		instructions[i].setFont(testo);
        	}
        	coloredPanel.add(instructions[i]);
        }
		   
		panel.add(coloredPanel);
		add(panel);
		
		
		//opzioni di chiusura
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
