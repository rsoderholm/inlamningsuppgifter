package gu;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.event.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Initializing this class will create a graphical user interface for
 * Gruppuppgift - Objektorienterad datautveckling och datastrukturer
 * 
 * @author Jonathan Böcker 2015-03-11
 *
 */
public class InterfaceMain {
	private GUController controller;
	private MapView map;
	private ButtonListener btnListener = new ButtonListener();
	private KeyBoardListener keyListener = new KeyBoardListener();
	private ListListener listListener = new ListListener();
	private JFrame frame = new JFrame("Scania Search");
	private JPanel mapPanel = new JPanel(), searchCityPanel = new JPanel(),
			buttonCityPanel = new JPanel(), buttonRoutePanel = new JPanel(),
			radioButtonPanel = new JPanel(), routeOptionsPanel = new JPanel(),
			routeChoicePanel = new JPanel(), specialMapPanel = new JPanel(),
			fromToLabelPanel = new JPanel(), fromToComboPanel = new JPanel(),
			lookAndFeelPanel = new JPanel();
	private JTabbedPane mainTabPane = new JTabbedPane();
	private JScrollPane mapScrollPane, textScrollPane;
	private JSplitPane splitPane;
	private JButton btnSearchCity = new JButton("Search"),
			btnGoBack = new JButton("Go Back"), btnSearchRoute = new JButton(
					"Search"), btnRemoveCity = new JButton("Remove City");
	private JTextField tfSearch = new JTextField();
	private JList<String> placesJList = new JList<String>();
	private JLabel lblFrom = new JLabel("From "), lblTo = new JLabel("To");
	private JComboBox<String> cbFrom = new JComboBox<String>(),
			cbTo = new JComboBox<String>(), cbLAF = new JComboBox<String>();
	private JRadioButton rbDepth = new JRadioButton("Deep Search"),
			rbWidth = new JRadioButton("Wide Search"),
			rbDijkstra = new JRadioButton("Dijkstra");
	private JTextArea taRoute = new JTextArea();

	private ArrayList<Place> allPlaces = new ArrayList<Place>();
	private ArrayList<Place> searchResultPlaces = new ArrayList<Place>();

	/**
	 * Will initialize a graphical user interface
	 * 
	 * @param imagePath
	 *            A relative path to the map image
	 * @param iconPath
	 *            A relative path to the program icon
	 * @param mapLeftUp
	 *            The {@link Position} of the map image upper left coordinates
	 * @param mapRightDown
	 *            The {@link Position} of the map image lower left coordinates
	 * @param controller
	 *            A {@link Controller} object
	 */
	public InterfaceMain(String imagePath, String iconPath, Position mapLeftUp,
			Position mapRightDown, GUController controller) {

		this.controller = controller;
		initializeGUI(imagePath, iconPath, mapLeftUp, mapRightDown);
		setListeners();
	}

	/**
	 * Updates the window with provided list of choosable places
	 * 
	 * @param places
	 *            The {@link ArrayList} of {@link Place} objects
	 */
	public void setPlaces(ArrayList<Place> places) {
		this.allPlaces = places;
		cbFrom.removeAllItems();
		cbTo.removeAllItems();
		for (int i = 0; i < places.size(); i++) {
			cbFrom.addItem(places.get(i).getName());
			cbTo.addItem(places.get(i).getName());
		}
		placesJList.removeAll();
		placesJList.setListData(placeToText(places));
	}

	/**
	 * Displays roads on the map image
	 * 
	 * @param roads
	 *            The {@link ArrayList} of {@link Road} objects
	 */
	public void showRoads(ArrayList<Road> roads) {
		map.showRoads(roads);
	}

	/**
	 * Prints out a route with provided {@link Road} list in the TextArea
	 * 
	 * @param roads
	 *            The {@link ArrayList} with {@link Road} objects
	 */
	public void printRoadInfo(ArrayList<Road> roads) {
		int totCost = 0;
		String res = "";
		// If size of list is zero there is no need or a route to be dislayed
		if (roads.size() != 0) {
			res += "Vägbeskrivning " + roads.get(0).getFrom() + " till "
					+ roads.get(roads.size() - 1).getTo() + "\n\n";
			for (int i = 0; i < roads.size(); i++) {
				res += roads.get(i).getFrom() + " --> " + roads.get(i).getTo()
						+ ", kostnad = " + roads.get(i).getCost() + "\n";
				totCost += roads.get(i).getCost();
			}
			res += "\nTotal kostnad för sträckan är: " + totCost;
			taRoute.setText(res);
		} else {
			taRoute.setText("Du behöver ingen vägbeskrivning. Du är framme!");
		}
	}

	private void initializeGUI(String imagePath, String iconPath,
			Position mapLeftUp, Position mapRightDown) {
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
		radioButtonPanel.add(Box.createVerticalGlue());

		// FROM-TO Label Panel
		fromToLabelPanel.setLayout(new GridLayout(2, 1));
		fromToLabelPanel.add(lblFrom);
		fromToLabelPanel.add(lblTo);

		// FROM-TO Combobox Panel
		fromToComboPanel.setLayout(new BoxLayout(fromToComboPanel,
				BoxLayout.Y_AXIS));
		fromToComboPanel.add(cbFrom);
		fromToComboPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		fromToComboPanel.add(cbTo);

		// Route Choice Panel
		routeChoicePanel.setLayout(new BoxLayout(routeChoicePanel,
				BoxLayout.X_AXIS));
		routeChoicePanel.add(fromToLabelPanel);
		routeChoicePanel.add(fromToComboPanel);

		// Route Options panel
		routeOptionsPanel.setLayout(new BoxLayout(routeOptionsPanel,
				BoxLayout.Y_AXIS));
		routeOptionsPanel.setBorder(BorderFactory.createTitledBorder("Route"));
		routeOptionsPanel.add(routeChoicePanel);
		routeOptionsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		routeOptionsPanel.add(btnSearchRoute);

		// Look and Feel panel
		lookAndFeelPanel.setBorder(BorderFactory
				.createTitledBorder("Look And Feel"));

		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			cbLAF.addItem(info.getName());
		}

		lookAndFeelPanel.add(cbLAF);

		// Map Tab ButtonPanel
		buttonRoutePanel.add(routeOptionsPanel);
		buttonRoutePanel.add(radioButtonPanel);
		buttonRoutePanel.add(lookAndFeelPanel);

		// The Map Panel
		map = new MapView(imagePath, mapLeftUp.getLongitude(),
				mapLeftUp.getLatitude(), mapRightDown.getLongitude(),
				mapRightDown.getLatitude());
		specialMapPanel.setLayout(new BoxLayout(specialMapPanel,
				BoxLayout.X_AXIS));
		specialMapPanel.add(Box.createVerticalGlue());
		specialMapPanel.add(map);
		mapScrollPane = new JScrollPane(specialMapPanel);

		// The Text Route Panel
		textScrollPane = new JScrollPane(taRoute);

		mapPanel.setLayout(new BorderLayout());
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mapScrollPane,
				textScrollPane);
		mapPanel.add(splitPane, BorderLayout.CENTER);
		mapPanel.add(buttonRoutePanel, BorderLayout.SOUTH);
		mainTabPane.addTab("Route Map", mapPanel);

		// Button Panel
		btnGoBack.setEnabled(false);

		buttonCityPanel.setLayout(new BoxLayout(buttonCityPanel,
				BoxLayout.X_AXIS));
		buttonCityPanel.add(btnGoBack);
		buttonCityPanel.add(btnSearchCity);
		buttonCityPanel.add(tfSearch);
		buttonCityPanel.add(btnRemoveCity);
		btnRemoveCity.setEnabled(false);

		// The City Search panel
		searchCityPanel.setLayout(new BorderLayout());
		searchCityPanel.add(buttonCityPanel, BorderLayout.SOUTH);
		searchCityPanel.add(placesJList, BorderLayout.CENTER);

		mainTabPane.addTab("City search", searchCityPanel);

		// Main frame
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(iconPath)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainTabPane);
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	/*
	 * Sets all listeners
	 */
	private void setListeners() {
		btnSearchCity.addActionListener(btnListener);
		btnGoBack.addActionListener(btnListener);
		btnSearchRoute.addActionListener(btnListener);
		btnRemoveCity.addActionListener(btnListener);

		placesJList.addKeyListener(keyListener);
		tfSearch.addKeyListener(keyListener);
		btnGoBack.addKeyListener(keyListener);
		btnSearchCity.addKeyListener(keyListener);

		placesJList.addListSelectionListener(listListener);

		cbLAF.addItemListener(new ItemChangeListener());

		// A double click will show information about the place
		placesJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					if (!(btnSearchCity.isEnabled())) {
						JOptionPane.showMessageDialog(frame, searchResultPlaces
								.get(placesJList.getSelectedIndex()));
					} else {
						JOptionPane.showMessageDialog(frame,
								allPlaces.get(placesJList.getSelectedIndex()));
					}
				}
			}
		});
	}

	/*
	 * Converts an ArrayList of Place objects to a String array
	 */
	private String[] placeToText(ArrayList<Place> placeArr) {
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

	/*
	 * Method that makes a search with the string from the textfield and
	 * displays the searchresult on the "City Search" tab
	 */
	private void search() {
		searchResultPlaces.clear();
		Place foundPlace = controller.searchPlace(tfSearch.getText());
		if (foundPlace != null)
			searchResultPlaces.add(foundPlace);
		placesJList.removeAll();
		placesJList.setListData(placeToText(searchResultPlaces));
		btnGoBack.requestFocus();
	}

	/*
	 * Method removes selected Place and updates the GUI
	 */
	private void remove() {
		// Make sure a place is selected
		if (placesJList.getSelectedIndex() != -1) {
			// Check if a search has been made
			if (btnSearchCity.isEnabled()) {
				// Show an "Are you sure?" Dialog
				if (JOptionPane
						.showConfirmDialog(
								frame,
								"Are you sure you want to remove:\n"
										+ allPlaces.get(placesJList
												.getSelectedIndex())) == JOptionPane.YES_OPTION) {

					controller.removePlace(placesJList.getSelectedValue());
				}
			} else {
				// Show an "Are you sure?" Dialog
				if (JOptionPane.showConfirmDialog(
						frame,
						"Are you sure you want to remove:\n"
								+ searchResultPlaces.get(placesJList
										.getSelectedIndex())) == JOptionPane.YES_OPTION) {

					controller.removePlace(placesJList.getSelectedValue());
				}
			}
		}
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnSearchCity) {
				search();
				btnGoBack.setEnabled(true);
				btnSearchCity.setEnabled(false);

			} else if (e.getSource() == btnGoBack) {
				btnGoBack.setEnabled(false);
				btnSearchCity.setEnabled(true);
				placesJList.removeAll();
				placesJList.setListData(placeToText(allPlaces));

			} else if (e.getSource() == btnSearchRoute) {
				if (rbDepth.isSelected()) {
					controller.searchDepthFirst(
							cbFrom.getItemAt(cbFrom.getSelectedIndex()),
							cbTo.getItemAt(cbTo.getSelectedIndex()));

				} else if (rbWidth.isSelected()) {
					controller.searchBreadthFirst(
							cbFrom.getItemAt(cbFrom.getSelectedIndex()),
							cbTo.getItemAt(cbTo.getSelectedIndex()));

				} else if (rbDijkstra.isSelected()) {
					controller.searchDijkstra(
							cbFrom.getItemAt(cbFrom.getSelectedIndex()),
							cbTo.getItemAt(cbTo.getSelectedIndex()));
				}
			} else if (e.getSource() == btnRemoveCity) {
				remove();
			}
		}
	}

	public class KeyBoardListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
				if (tfSearch.isFocusOwner()) {
					search();
					btnGoBack.setEnabled(true);
					btnSearchCity.setEnabled(false);

				} else if (placesJList.isFocusOwner()) {
					if (!(btnSearchCity.isEnabled())) {
						JOptionPane.showMessageDialog(frame, searchResultPlaces
								.get(placesJList.getSelectedIndex()));
					} else {
						JOptionPane.showMessageDialog(frame,
								allPlaces.get(placesJList.getSelectedIndex()));
					}

				} else if (btnGoBack.isFocusOwner()) {
					btnGoBack.setEnabled(false);
					btnSearchCity.setEnabled(true);
					placesJList.removeAll();
					placesJList.setListData(placeToText(allPlaces));

				} else if (btnSearchCity.isFocusOwner()) {
					search();
					btnGoBack.setEnabled(true);
					btnSearchCity.setEnabled(false);
				}

			} else if (e.getExtendedKeyCode() == KeyEvent.VK_DELETE
					&& placesJList.isFocusOwner()) {
				remove();

			} else if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE
					&& mainTabPane.getSelectedIndex() == 1
					&& !(tfSearch.hasFocus())) {

				if (btnSearchCity.isEnabled()) {
					mainTabPane.setSelectedIndex(0);

				} else {
					btnGoBack.setEnabled(false);
					btnSearchCity.setEnabled(true);
					placesJList.removeAll();
					placesJList.setListData(placeToText(allPlaces));
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

	public class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (placesJList.getSelectedIndex() == -1) {
				btnRemoveCity.setEnabled(false);
			} else {
				btnRemoveCity.setEnabled(true);
			}

		}

	}

	private class ItemChangeListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
						if (cbLAF.getItemAt(cbLAF.getSelectedIndex()).equals(
								info.getName())) {
							int extendedState = frame.getExtendedState();
							UIManager.setLookAndFeel(info.getClassName());
							SwingUtilities.updateComponentTreeUI(frame);
							frame.pack();
							frame.setExtendedState(extendedState);
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
