package gu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class InterfaceMain {
	private GUController controller;
	private MapView map;
	private ButtonListener btnListener = new ButtonListener();
	private KeyBoardListener keyListener = new KeyBoardListener();
	private JFrame frame = new JFrame("Test window");
	private JPanel mapPanel = new JPanel(), searchCityPanel = new JPanel(),
			buttonCityPanel = new JPanel(), buttonRoutePanel = new JPanel(),
			radioButtonPanel = new JPanel(), routeOptionsPanel = new JPanel(),
			routeChoicePanel = new JPanel(), specialMapPanel = new JPanel(),
			fromToLabelPanel = new JPanel(), fromToComboPanel = new JPanel();
	private JTabbedPane mainTabPane = new JTabbedPane();
	private JButton btnSearchCity = new JButton("Search"),
			btnGoBack = new JButton("Go Back"), btnSearchRoute = new JButton(
					"Search");
	private JTextField tfSearch = new JTextField();
	private JList<String> placesList = new JList<String>();
	private JLabel lblFrom = new JLabel("From "), lblTo = new JLabel("To");
	private JComboBox<String> cbFrom = new JComboBox<String>(),
			cbTo = new JComboBox<String>();
	private JRadioButton rbDepth = new JRadioButton("Deep Search"),
			rbWidth = new JRadioButton("Wide Search"),
			rbDijkstra = new JRadioButton("Dijkstra");

	private ArrayList<Place> allPlaces = new ArrayList<Place>();
	private ArrayList<Place> searchPlaces = new ArrayList<Place>();

	public InterfaceMain(String imagePath, Position mapLeftUp,
			Position mapRightDown, GUController controller) {

		this.controller = controller;

		// Radio button panel
		ButtonGroup rbGroup = new ButtonGroup();
		rbGroup.add(rbDepth);
		rbGroup.add(rbWidth);
		rbGroup.add(rbDijkstra);
		rbDijkstra.setSelected(true);

		radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel,
				BoxLayout.Y_AXIS));
		radioButtonPanel.setBorder(BorderFactory
				.createTitledBorder("Search method"));

		radioButtonPanel.add(rbDepth);
		radioButtonPanel.add(rbWidth);
		radioButtonPanel.add(rbDijkstra);

		// FROM TO Label Panel
		fromToLabelPanel.setLayout(new GridLayout(2, 1));
		fromToLabelPanel.add(lblFrom);
		fromToLabelPanel.add(lblTo);

		// FROM TO Combobox Panel
		fromToComboPanel.setLayout(new GridLayout(2, 1));
		fromToComboPanel.add(cbFrom);
		fromToComboPanel.add(cbTo);

		// Route Choice Panel
		routeChoicePanel.setLayout(new BorderLayout());
		routeChoicePanel.add(fromToLabelPanel, BorderLayout.WEST);
		routeChoicePanel.add(fromToComboPanel, BorderLayout.CENTER);

		// Route Options panel
		routeOptionsPanel.setLayout(new BorderLayout());
		routeOptionsPanel.add(routeChoicePanel, BorderLayout.CENTER);
		routeOptionsPanel.add(btnSearchRoute, BorderLayout.SOUTH);

		// Map Tab ButtonPanel
		buttonRoutePanel.setLayout(new BorderLayout());
		buttonRoutePanel.add(radioButtonPanel, BorderLayout.EAST);
		buttonRoutePanel.add(routeOptionsPanel);

		// The Map Panel
		map = new MapView(imagePath, mapLeftUp.getLongitude(),
				mapLeftUp.getLatitude(), mapRightDown.getLongitude(),
				mapRightDown.getLatitude());
		specialMapPanel.setLayout(new BoxLayout(specialMapPanel,
				BoxLayout.X_AXIS));
		specialMapPanel.add(map);

		mapPanel.setLayout(new BorderLayout());
		mapPanel.add(specialMapPanel, BorderLayout.CENTER);
		mapPanel.add(buttonRoutePanel, BorderLayout.SOUTH);
		mainTabPane.addTab("Route Search", mapPanel);

		// Button Panel
		btnGoBack.setEnabled(false);

		buttonCityPanel.setLayout(new BoxLayout(buttonCityPanel,
				BoxLayout.X_AXIS));
		buttonCityPanel.add(btnGoBack);
		buttonCityPanel.add(btnSearchCity);
		buttonCityPanel.add(tfSearch);

		// The City Search panel
		searchCityPanel.setLayout(new BorderLayout());
		searchCityPanel.add(buttonCityPanel, BorderLayout.SOUTH);
		searchCityPanel.add(placesList, BorderLayout.CENTER);

		mainTabPane.addTab("City search", searchCityPanel);

		// Main frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainTabPane);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

		// Actionlisteners etc..
		btnSearchCity.addActionListener(btnListener);
		btnGoBack.addActionListener(btnListener);
		placesList.addKeyListener(keyListener);
		tfSearch.addKeyListener(keyListener);
	}

	public void setPlaces(ArrayList<Place> places) {
		this.allPlaces = places;
		for (int i = 0; i < places.size(); i++) {
			cbFrom.addItem(places.get(i).getName());
			cbTo.addItem(places.get(i).getName());
		}
		placesList.setListData(placeToText(places));
	}

	public void showRoads(ArrayList<Road> roads) {
		map.showRoads(roads);
	}

	public String[] placeToText(ArrayList<Place> placeArr) {
		if (placeArr.size() != 0) {
			String str[] = new String[placeArr.size()];

			for (int i = 0; i < placeArr.size(); i++) {
				str[i] = placeArr.get(i).getName();
			}
			return str;
		} else {
			return new String[0];
		}
	}

	public void search() {
		searchPlaces.clear();
		Place searchResult = controller.searchPlace(tfSearch.getText());
		if (searchResult != null)
			searchPlaces.add(searchResult);
		placesList.removeAll();
		placesList.setListData(placeToText(searchPlaces));
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSearchCity) {
				search();
				btnGoBack.setEnabled(true);
				btnSearchCity.setEnabled(false);
			} else if(e.getSource() == btnGoBack){
				btnGoBack.setEnabled(false);
				btnSearchCity.setEnabled(true);
				placesList.removeAll();
				placesList.setListData(placeToText(allPlaces));
			}
		}
	}

	public class KeyBoardListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER
					&& tfSearch.isFocusOwner()) {
				search();
				btnGoBack.setEnabled(true);
				btnSearchCity.setEnabled(false);

			} else if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER
					&& placesList.isFocusOwner()) {
				if (!(btnSearchCity.isEnabled())) {
					JOptionPane.showMessageDialog(frame,
							searchPlaces.get(placesList.getSelectedIndex()));
				} else {
					JOptionPane.showMessageDialog(frame,
							allPlaces.get(placesList.getSelectedIndex()));
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
