import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;

import java.net.URL;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;


public class Scores extends JFrame {
	
	private static final int WIDTH = 500;
	private static final int HEIGHT = 550;
	private final static String fileName = "Scores.txt";
	private static JPanel panel;
	private static JSeparator separator;
	private JPanel coloredPanel;
	private JLabel newScore;
	private final JLabel titolo = new JLabel("CLASSIFICA SUDOKU");
	public String userName;
	public int userSec, userMin;
	public JLabel[] classifica = new JLabel[10];
	private Font fontTitolo = new Font("Engravers MT", Font.BOLD, 20);
	private Font fontTesto = new Font("Verdana", Font.PLAIN, 15);
	
	
	//COSTRUTTORE
	public Scores() {
		//impostazioni finestra
		super("Classifica");
		
		URL iconRank_url = getClass().getResource("ranking.png");
        Image iconRank = new ImageIcon(iconRank_url).getImage();
        setIconImage(iconRank);
        
        Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int x=(screenSize.width/2)-(WIDTH/2);
		int y=(screenSize.height/2)-(HEIGHT/4);
		setLocation(x, y);
		
		panel = new JPanel();		
		
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		
		//panello colorato
		coloredPanel = new JPanel();
		Color myColor = new Color(219, 198, 135);
		coloredPanel.setBackground(myColor);
		coloredPanel.setLayout(layout);
		coloredPanel.setPreferredSize(new Dimension(450, 300));
		titolo.setFont(fontTitolo);
		coloredPanel.add(titolo);
		
		
		//separa il titolo dalla classifica
		separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setPreferredSize(new Dimension(440, 1));
		coloredPanel.add(separator);
		
		
		//aggiungo tutto alla finestra
		panel.add(coloredPanel);
		add(panel);
		
		leggiClassifica();
		validate();
		
		pack();
		
		//opzione di chiusura
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void leggiClassifica() {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("Scores.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Errore nell'apertura del file");
		}
		
		int cont=0;
		while(sc.hasNext()) {
			userName = sc.next();
			userMin = sc.nextInt();
			userSec = sc.nextInt();
			classifica[cont] = new JLabel("  " + (cont+1) + ") Nome: " + userName + "                       Tempo: " + userMin + "min" + userSec + "sec");
			if(cont == 9) {
				classifica[cont] = new JLabel((cont+1) + ") Nome: " + userName + "                     Tempo: " + userMin + "min" + userSec + "sec");
			}
			classifica[cont].setFont(fontTesto);
			coloredPanel.add(classifica[cont]);
			cont++;
		}
		sc.close();
	}
    
}
