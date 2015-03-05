package gu;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterfaceMain {
	private MapView map;

	public InterfaceMain(String imagePath, Position mapLeftUp, Position mapRightDown) {
		JFrame frame = new JFrame("Test window");
		JPanel panel = new JPanel();

		map = new MapView(imagePath, mapLeftUp.getLongitude(),
				mapLeftUp.getLatitude(), mapRightDown.getLongitude(),
				mapRightDown.getLatitude());
		panel.add(map);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void showRoads(ArrayList<Road> roads){
		map.showRoads(roads);
	}

}
