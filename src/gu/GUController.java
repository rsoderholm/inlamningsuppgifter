package gu;

import javax.swing.JFrame;

public class GUController {

	public GUController(String string, Position mapLeftUp,
			Position mapRightDown, String string2, String string3) {
		JFrame frame = new JFrame("Test window");
		MapView map = new MapView(string, mapLeftUp.getLatitude(), mapLeftUp.getLongitude(), mapRightDown.getLatitude(), mapRightDown.getLongitude());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(map);
		frame.pack();
		frame.setVisible(true);
	}

}
