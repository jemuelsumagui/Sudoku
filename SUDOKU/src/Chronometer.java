import javax.swing.Timer;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chronometer {
	
	private static long startTime;
	private static Timer timer;
	private static JLabel chronos;
	private static int sec;
	private static int min;
	
	
	public Chronometer(JLabel chr) {
		chronos=chr;
		ChronometerListener beep = new ChronometerListener();
		timer = new Timer(1000, beep);
	}
	
	
	//ACTION LISTENER DEL CRONOMETRO CHE AGGIORNA LA JLABEL DEL TEMPO
	private class ChronometerListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			long diffTime = System.currentTimeMillis() - startTime;
			
			sec = (int) ((diffTime/1000) % 60);
			min = (int) ((diffTime/60000) % 60);
			
			String time = String.format("%02d:%02d", min, sec);
			chronos.setText(time);
		}
	}
	
	
	//FA PARTIRE IL CRONOMETRO
	public void startChronometer() {
		startTime = System.currentTimeMillis();
		timer.start();
	}
	
	
	//STOPPA IL CRONOMETRO
	public void stopChronometer() {
		timer.stop();
	}
	
	
	//RESTITUISCE I SECONDI
	public int getSeconds() {
		return sec;
	}
	
	
	//RESTITUISCE I MINUTI
	public int getMinutes() {
		return min;
	}
}
