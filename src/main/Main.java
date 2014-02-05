package main;

import global.GlobalSettings;
import global.MainMenuInterface;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import core.StateManager;

/**
 * Class that initializes the application and provides the update loop.
 */
@SuppressWarnings("serial")
public class Main extends JFrame {
	
	/**
	 * Initializes the game and starts the update loop.
	 */
	public Main(GlobalSettings gs, final MainMenuInterface mmi) {
		super();
		
		setTitle("The Impossible Clone");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowListener(){

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				mmi.returnToMainMenu();
			    dispose();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}
		});
		this.pack();
		setSize(gs.getResolution()[0], gs.getResolution()[1]);
		//add(new Scene(gs));
		add(new StateManager(this, gs));
		
		this.setLocationRelativeTo(null);
		setVisible(true);
		
		final Main obj = this;
		Thread t = new Thread() {
			@Override 
			public void run() {
				long comp, curr;
				final long waittime = (long)((1.0/60.0)*1000000000.0); // 1/60 second in nanoseconds
				while(obj.isDisplayable()) {
					comp = System.nanoTime()+waittime;
					obj.run();
					do {
						// 60 fps frame cap
						curr = System.nanoTime();
					}
					while(curr < comp);
				}
			}
		};
		t.start();
	}
	/**
	 * Method that's called in the update loop.
	 */
	private void run() {
		repaint();
	}
}
