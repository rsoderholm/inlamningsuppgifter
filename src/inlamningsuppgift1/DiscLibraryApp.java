package inlamningsuppgift1;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class DiscLibraryApp {
	public static void main(String[] args) {
		if (args.length == 0) {
			try {
				for (LookAndFeelInfo info : UIManager
						.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			} catch (Exception e) {

			}
			DiscRegister register = new DiscRegister();
			Controller controller = new Controller(register);
			GUI gui = new GUI(controller);

			JFrame frame = new JFrame("Inlämningsuppgift 1");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(gui);
			frame.pack();
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, 
					"Det går inte att haxxa\ndetta programmet med kommandot:\n" + args[0]);
		}

	}

}
