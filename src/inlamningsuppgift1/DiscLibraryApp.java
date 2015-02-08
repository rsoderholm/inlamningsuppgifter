package inlamningsuppgift1;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


public class DiscLibraryApp {
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		DiscRegister register = new DiscRegister();		
		Controller controller = new Controller(register);
		GUI gui = new GUI(controller);
		
		JFrame frame = new JFrame("Inlämningsuppgift 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gui);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
