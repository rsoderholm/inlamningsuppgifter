package gu;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainGU {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
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
				
				Position mapLeftUp = new Position(12.527, 56.346); 
				Position mapRightDown = new Position(14.596, 55.324); 
				
				new GUController("/res/skane.JPG", mapLeftUp, mapRightDown,
						         "/res/places.txt","/res/roads.txt", "/res/icon.jpg");
			}
		});
	}
}
