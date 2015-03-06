package gu;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class InterfaceMain {
	private MapView map;

	public InterfaceMain(String imagePath, Position mapLeftUp, Position mapRightDown) {
		JFrame frame = new JFrame("Test window");
		JPanel mapPanel = new JPanel();
		JTabbedPane tabPane = new JTabbedPane();

		map = new MapView(imagePath, mapLeftUp.getLongitude(),
				mapLeftUp.getLatitude(), mapRightDown.getLongitude(),
				mapRightDown.getLatitude());
		mapPanel.add(map);
		tabPane.addTab("Map", mapPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(tabPane);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void showRoads(ArrayList<Road> roads){
		map.showRoads(roads);
	}

}
