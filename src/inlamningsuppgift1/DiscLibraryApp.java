package inlamningsuppgift1;

import javax.swing.JFrame;

public class DiscLibraryApp {
	public static void main(String[] args) {
		DiscRegister register = new DiscRegister();		
		Controller controller = new Controller(register);
		GUI gui = new GUI(controller);
		
		JFrame frame = new JFrame("Inlämningsuppgift 1 Jonathan Böcker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gui);
		frame.pack();
		frame.setVisible(true);
	}

}
