package space;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SpaceFrame extends JFrame {

	public SpaceFrame() {
		add(new SpaceBackground());

    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(1000, 600);
    	setLocationRelativeTo(null);
    	setTitle("Final Voyage");
    	setResizable(false);
    	setVisible(true);
	}
	
}
