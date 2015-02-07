package inlamningsuppgift1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Comparator;

public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private ButtonListener btnListener;
	private JList<String> movieList;
	private JScrollPane spMovieList;
	private JMenuBar menuBar;
	private JMenu menuFile, menuSort, menuSortKind, menuSortWay;
	private JMenuItem menuLoad, menuSave, menuSortList, menuShuffle;
	private JRadioButtonMenuItem rbMenuTitle, rbMenuGenre, rbMenuType,
			rbMenuDirector, rbMenuLength, rbMenuRating;
	private JRadioButtonMenuItem rbMenuAsc, rbMenuDesc;
	private JButton btnDelete, btnAdd, btnInfo;
	private JPanel pnlButtons, pnlNorth;
	final JFileChooser fc = new JFileChooser();

	public GUI(Controller controller) {
		this.controller = controller;

		setLayout(new BorderLayout());

		// Menu stuff
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuSort = new JMenu("Sort");
		menuSortList = new JMenuItem("Sort List");
		menuShuffle = new JMenuItem("Shuffle List");
		menuSortKind = new JMenu("Sort by");
		menuSortWay = new JMenu("Sort Asc or Desc");
		menuBar.add(menuFile);
		menuBar.add(menuSort);
		menuBar.add(Box.createHorizontalGlue());
		menuLoad = new JMenuItem("Load File");
		menuSave = new JMenuItem("Save File");
		menuFile.add(menuLoad);
		menuFile.add(menuSave);
		menuFile.addSeparator();
		menuSort.add(menuSortList);
		menuSort.add(menuShuffle);
		menuSort.add(menuSortKind);
		menuSort.add(menuSortWay);

		// Radiobuttons in submenu
		ButtonGroup groupKind = new ButtonGroup();
		rbMenuTitle = new JRadioButtonMenuItem("Title");
		rbMenuGenre = new JRadioButtonMenuItem("Genre");
		rbMenuType = new JRadioButtonMenuItem("Type");
		rbMenuDirector = new JRadioButtonMenuItem("Director");
		rbMenuLength = new JRadioButtonMenuItem("Length");
		rbMenuRating = new JRadioButtonMenuItem("Rating");
		groupKind.add(rbMenuTitle);
		groupKind.add(rbMenuGenre);
		groupKind.add(rbMenuType);
		groupKind.add(rbMenuDirector);
		groupKind.add(rbMenuLength);
		groupKind.add(rbMenuRating);
		menuSortKind.add(rbMenuTitle);
		menuSortKind.add(rbMenuGenre);
		menuSortKind.add(rbMenuType);
		menuSortKind.add(rbMenuDirector);
		menuSortKind.add(rbMenuLength);
		menuSortKind.add(rbMenuRating);
		rbMenuTitle.setSelected(true);

		// Radiobuttons in another sidemenu
		ButtonGroup groupWay = new ButtonGroup();
		rbMenuAsc = new JRadioButtonMenuItem("Ascending");
		rbMenuDesc = new JRadioButtonMenuItem("Descending");
		groupWay.add(rbMenuAsc);
		groupWay.add(rbMenuDesc);
		menuSortWay.add(rbMenuAsc);
		menuSortWay.add(rbMenuDesc);
		rbMenuAsc.setSelected(true);

		// Main window with movielist
		movieList = new JList<>();
		spMovieList = new JScrollPane(movieList);

		// Panel with quickbuttons
		btnInfo = new JButton("Info");
		btnAdd = new JButton("Add Movie");
		btnDelete = new JButton("Delete Movie");
		pnlButtons = new JPanel();
		pnlButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));
		pnlButtons.add(btnInfo);
		pnlButtons.add(Box.createRigidArea(new Dimension(5, 0)));
		pnlButtons.add(btnAdd);
		pnlButtons.add(Box.createRigidArea(new Dimension(5, 0)));
		pnlButtons.add(btnDelete);
		pnlButtons.add(Box.createHorizontalGlue());

		// The north panel
		pnlNorth = new JPanel();
		pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.Y_AXIS));
		pnlNorth.add(menuBar);
		pnlNorth.add(pnlButtons);

		add(spMovieList, BorderLayout.CENTER);
		add(pnlNorth, BorderLayout.NORTH);

		// ActionListeners
		btnListener = new ButtonListener();
		menuLoad.addActionListener(btnListener);
		menuSave.addActionListener(btnListener);
		menuSortList.addActionListener(btnListener);
		menuShuffle.addActionListener(btnListener);
		btnInfo.addActionListener(btnListener);
		btnAdd.addActionListener(btnListener);
		btnDelete.addActionListener(btnListener);
		
		// File chooser stuff
		fc.setFileFilter(new FileFilterSpec());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (menuLoad == e.getSource()) {
				int returnVal = fc.showOpenDialog(GUI.this);
				
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					controller.loadFile(file);
					movieList.setListData(controller.getlistAsText());
				}
				
				
			} else if (menuSave == e.getSource()) {
				controller.saveFile();
				
			} else if (menuSortList == e.getSource()) {
				Comparator<Movie> comp = null;

				if (rbMenuTitle.isSelected() && rbMenuAsc.isSelected())
					comp = new TitleAsc();
				else if (rbMenuTitle.isSelected() && rbMenuDesc.isSelected())
					comp = new TitleDesc();
				else if (rbMenuGenre.isSelected() && rbMenuAsc.isSelected())
					comp = new GenreAsc();
				else if (rbMenuGenre.isSelected() && rbMenuDesc.isSelected())
					comp = new GenreDesc();
				else if (rbMenuType.isSelected() && rbMenuAsc.isSelected())
					comp = new TypeAsc();
				else if (rbMenuType.isSelected() && rbMenuDesc.isSelected())
					comp = new TypeDesc();
				else if (rbMenuDirector.isSelected() && rbMenuAsc.isSelected())
					comp = new DirectAsc();
				else if (rbMenuDirector.isSelected() && rbMenuDesc.isSelected())
					comp = new DirectDesc();
				else if (rbMenuLength.isSelected() && rbMenuAsc.isSelected())
					comp = new LengthAsc();
				else if (rbMenuLength.isSelected() && rbMenuDesc.isSelected())
					comp = new LengthDesc();
				else if (rbMenuRating.isSelected() && rbMenuAsc.isSelected())
					comp = new RatingAsc();
				else if (rbMenuRating.isSelected() && rbMenuDesc.isSelected())
					comp = new RatingDesc();

				controller.sortList(comp);
				movieList.removeAll();
				movieList.setListData(controller.getlistAsText());

			} else if (menuShuffle == e.getSource()) {
				controller.shuffleList();
				movieList.removeAll();
				movieList.setListData(controller.getlistAsText());
			} else if (btnInfo == e.getSource()) {
				int selected = movieList.getSelectedIndex();
				String str[][] = controller.getMovieInfo(selected);
				JOptionPane.showMessageDialog(spMovieList, new MovieInfoPanel(str));

			} else if (btnAdd == e.getSource()) {
				// TODO

			} else if (btnDelete == e.getSource()) {
				// TODO

			}

		}

	}
}
