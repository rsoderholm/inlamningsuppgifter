package gu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class InterfaceMain {
	private MapView map;
	private ButtonListener btnListener = new ButtonListener();
	private KeyBoardListener keyListener = new KeyBoardListener();
	private JFrame frame = new JFrame("Test window");
	private JPanel mapPanel = new JPanel(), searchPanel = new JPanel(), buttonPanel = new JPanel();
	private JTabbedPane mainTabPane = new JTabbedPane();
	private JScrollPane mapScrollPane;
	private JButton btnSearch = new JButton("Search"), btnGoBack = new JButton("Go Back");
	private JTextField tfSearch = new JTextField();
	private JList<Place> placesList = new JList<Place>();

	public InterfaceMain(String imagePath, Position mapLeftUp,
			Position mapRightDown) {
		
		// The Map Panel
		map = new MapView(imagePath, mapLeftUp.getLongitude(),
				mapLeftUp.getLatitude(), mapRightDown.getLongitude(),
				mapRightDown.getLatitude());
		
		mapScrollPane = new JScrollPane(map);
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));
		mapPanel.add(mapScrollPane);
		mapPanel.add(Box.createGlue());
		mainTabPane.addTab("Route Search", mapPanel);
		
		// Button Panel
		btnGoBack.setEnabled(false);
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(btnGoBack);		
		buttonPanel.add(btnSearch);
		tfSearch.addKeyListener(keyListener);
		buttonPanel.add(tfSearch);
		
		// The Search panel
		searchPanel.setLayout(new BorderLayout());
		searchPanel.add(buttonPanel, BorderLayout.NORTH);
		searchPanel.add(placesList, BorderLayout.CENTER);
		mainTabPane.addTab("City search", searchPanel);

		// Main frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainTabPane);
		frame.pack();
		frame.setVisible(true);
	}

	public void showRoads(ArrayList<Road> roads) {
		map.showRoads(roads);
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSearch){
				JOptionPane.showMessageDialog(frame, "Detta funkar inte ännu!");
			}
		}
	}
	public class KeyBoardListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER){
				JOptionPane.showMessageDialog(frame, "Detta funkar inte ännu!");
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}		
	}
}
