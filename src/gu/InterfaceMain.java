package gu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
			fromToLabelPanel = new JPanel(), fromToComboPanel = new JPanel();
	private JTabbedPane mainTabPane = new JTabbedPane();
	private JScrollPane mapScrollPane, textScrollPane;
	private JSplitPane splitPane;
	private JButton btnSearchCity = new JButton("Search"),
			btnGoBack = new JButton("Go Back"), btnSearchRoute = new JButton(
					"Search"), btnAddCity = new JButton("Add City"),
			btnRemoveCity = new JButton("Remove City");
	private JTextField tfSearch = new JTextField();
	private JList<String> placesList = new JList<String>();
	private JLabel lblFrom = new JLabel("From "), lblTo = new JLabel("To");
	private JComboBox<String> cbFrom = new JComboBox<String>(),
			cbTo = new JComboBox<String>();
	private JRadioButton rbDepth = new JRadioButton("Deep Search"),
			rbWidth = new JRadioButton("Wide Search"),
			rbDijkstra = new JRadioButton("Dijkstra");
	private JTextArea taRoute = new JTextArea();

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
		buttonCityPanel.add(btnAddCity);
		buttonCityPanel.add(btnRemoveCity);
		btnRemoveCity.setEnabled(false);

		// The City Search panel
		searchCityPanel.setLayout(new BorderLayout());
		searchCityPanel.add(buttonCityPanel, BorderLayout.SOUTH);
		searchCityPanel.add(placesList, BorderLayout.CENTER);

		mainTabPane.addTab("City search", searchCityPanel);

		// Main frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainTabPane);
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

		// Actionlisteners etc..
		btnSearchCity.addActionListener(btnListener);
		btnGoBack.addActionListener(btnListener);
		btnSearchRoute.addActionListener(btnListener);
		btnAddCity.addActionListener(btnListener);
		btnRemoveCity.addActionListener(btnListener);

		placesList.addKeyListener(keyListener);
		tfSearch.addKeyListener(keyListener);
		btnGoBack.addKeyListener(keyListener);
		btnSearchCity.addKeyListener(keyListener);

		placesList.addListSelectionListener(listListener);
		
		placesList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					if (!(btnSearchCity.isEnabled())) {
						JOptionPane.showMessageDialog(frame,
								searchPlaces.get(placesList.getSelectedIndex()));
					} else {
						JOptionPane.showMessageDialog(frame,
								allPlaces.get(placesList.getSelectedIndex()));
					}
				}
			}
		});
	}

	public void setPlaces(ArrayList<Place> places) {
		this.allPlaces = places;
		cbFrom.removeAllItems();
		cbTo.removeAllItems();
		for (int i = 0; i < places.size(); i++) {
			cbFrom.addItem(places.get(i).getName());
			cbTo.addItem(places.get(i).getName());
		}
		placesList.removeAll();
		placesList.setListData(placeToText(places));
	}

	public void showRoads(ArrayList<Road> roads) {
		map.showRoads(roads);
	}

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

	private void search() {
		searchPlaces.clear();
		Place searchResult = controller.searchPlace(tfSearch.getText());
		if (searchResult != null)
			searchPlaces.add(searchResult);
		placesList.removeAll();
		placesList.setListData(placeToText(searchPlaces));
		btnGoBack.requestFocus();
	}

	private void remove() {
		if (placesList.getSelectedIndex() != -1) {
			if (btnSearchCity.isEnabled()) {
				if (JOptionPane.showConfirmDialog(
						frame,
						"Are you sure you want to remove:\n"
								+ allPlaces.get(placesList.getSelectedIndex())) == JOptionPane.YES_OPTION) {
					// TODO
					controller.removePlace(placesList.getSelectedValue());
				}
			} else {
				if (JOptionPane.showConfirmDialog(
						frame,
						"Are you sure you want to remove:\n"
								+ searchPlaces.get(placesList
										.getSelectedIndex())) == JOptionPane.YES_OPTION) {
					// TODO
					controller.removePlace(placesList.getSelectedValue());
				}
			}
		}
	}

	private void add() {
		JOptionPane.showMessageDialog(frame, "Tycker du verkligen det är nödvändigt?");
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
				placesList.removeAll();
				placesList.setListData(placeToText(allPlaces));

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
			} else if (e.getSource() == btnAddCity) {
				add();
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
				} else if (placesList.isFocusOwner()) {
					if (!(btnSearchCity.isEnabled())) {
						JOptionPane
								.showMessageDialog(frame, searchPlaces
										.get(placesList.getSelectedIndex()));
					} else {
						JOptionPane.showMessageDialog(frame,
								allPlaces.get(placesList.getSelectedIndex()));
					}
				} else if (btnGoBack.isFocusOwner()){
					btnGoBack.setEnabled(false);
					btnSearchCity.setEnabled(true);
					placesList.removeAll();
					placesList.setListData(placeToText(allPlaces));
				} else if (btnSearchCity.isFocusOwner()){
					search();
					btnGoBack.setEnabled(true);
					btnSearchCity.setEnabled(false);
				}

			} else if (e.getExtendedKeyCode() == KeyEvent.VK_DELETE
					&& placesList.isFocusOwner()) {
				remove();

			} else if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE
					&& mainTabPane.getSelectedIndex() == 1
					&& !(tfSearch.hasFocus())) {

				if (btnSearchCity.isEnabled()) {
					mainTabPane.setSelectedIndex(0);

				} else {
					btnGoBack.setEnabled(false);
					btnSearchCity.setEnabled(true);
					placesList.removeAll();
					placesList.setListData(placeToText(allPlaces));
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
			if (placesList.getSelectedIndex() == -1) {
				btnRemoveCity.setEnabled(false);
			} else {
				btnRemoveCity.setEnabled(true);
			}

		}

	}
}
