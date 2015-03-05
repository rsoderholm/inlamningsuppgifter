package gu;
import javax.swing.SwingUtilities;

public class MainGU {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Position mapLeftUp = new Position(12.527, 56.346); 
				Position mapRightDown = new Position(14.596, 55.324); 
				GUController controller = new GUController("/res/skane.JPG", mapLeftUp, mapRightDown,
						         "/res/places.txt","/res/roads.txt");
				controller.loadFiles();
				controller.showAllRoads();
			}
		});
	}
}
