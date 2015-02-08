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
	private JMenu menuFile, menuNew, menuEdit, menuSort, menuSortKind,
			menuSortWay;
	private JMenuItem menuNewFile, menuNewMovie, menuLoad, menuSave,
			menuSaveAs, menuSortList, menuShuffle, menuEditInfo,
			menuEditDelete;
	private JRadioButtonMenuItem rbMenuTitle, rbMenuGenre, rbMenuType,
			rbMenuDirector, rbMenuLength, rbMenuRating;
	private JRadioButtonMenuItem rbMenuAsc, rbMenuDesc;
	private JButton btnSearch, btnGoBack;
	private JTextField tfSearch;
	private JPanel pnlButtons, pnlNorth;
	final JFileChooser fc = new JFileChooser();

	public GUI(Controller controller) {
		this.controller = controller;

		setLayout(new BorderLayout());

		// Menu stuff
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuNew = new JMenu("New");
		menuEdit = new JMenu("Edit");
		menuSort = new JMenu("Sort");
		menuSortList = new JMenuItem("Sort List");
		menuShuffle = new JMenuItem("Shuffle List");
		menuSortKind = new JMenu("Sort by");
		menuSortWay = new JMenu("Sort Asc or Desc");
		menuNewFile = new JMenuItem("New List");
		menuNewMovie = new JMenuItem("New Movie");
		menuLoad = new JMenuItem("Open File");
		menuSave = new JMenuItem("Save");
		menuSaveAs = new JMenuItem("Save As");
		menuEditInfo = new JMenuItem("Info");
		menuEditDelete = new JMenuItem("Delete");
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuSort);
		menuBar.add(Box.createHorizontalGlue());
		menuFile.add(menuNew);
		menuFile.add(menuLoad);
		menuFile.addSeparator();
		menuFile.add(menuSave);
		menuFile.add(menuSaveAs);
		menuEdit.add(menuEditInfo);
		menuEdit.add(menuEditDelete);
		menuSort.add(menuSortList);
		menuSort.add(menuShuffle);
		menuSort.add(menuSortKind);
		menuSort.add(menuSortWay);

		menuNew.add(menuNewFile);
		menuNew.add(menuNewMovie);

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
		btnGoBack = new JButton("Go back");
		btnSearch = new JButton("Search");
		tfSearch = new JTextField();
		pnlButtons = new JPanel();
		pnlButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));
		pnlButtons.add(btnGoBack);
		pnlButtons.add(Box.createRigidArea(new Dimension(5, 0)));
		pnlButtons.add(btnSearch);
		pnlButtons.add(Box.createRigidArea(new Dimension(5, 0)));
		pnlButtons.add(tfSearch);
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
		menuNewFile.addActionListener(btnListener);
		menuLoad.addActionListener(btnListener);
		menuSave.addActionListener(btnListener);
		menuSaveAs.addActionListener(btnListener);
		menuEditInfo.addActionListener(btnListener);
		menuEditDelete.addActionListener(btnListener);
		menuSortList.addActionListener(btnListener);
		menuShuffle.addActionListener(btnListener);
		menuNewMovie.addActionListener(btnListener);
		btnGoBack.addActionListener(btnListener);
		btnSearch.addActionListener(btnListener);

		// File chooser stuff
		fc.setFileFilter(new FileFilterSpec());
		
		// Set enabled/disabled
		menuSave.setEnabled(false);
		btnGoBack.setEnabled(false);
	}

	public void update() {
		movieList.removeAll();
		movieList.setListData(controller.getlistAsText());
	}

	public void popup(Movie movie) {
		JFrame frame = new JFrame("Info");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(new MovieInfoPanel(movie, GUI.this));
		frame.pack();
		frame.setVisible(true);
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (menuLoad == e.getSource()) {
				int returnVal = fc.showOpenDialog(GUI.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					controller.loadFile(file);
					update();
					menuSave.setEnabled(true);
					
					Component c = getParent();					
			        while( c.getParent() != null ) {
			            c = c.getParent();
			        }			   
			        Frame topFrame = ( Frame )c;
			        topFrame.setTitle( fc.getName(file) );
				}

			} else if (menuSave == e.getSource()) {
				File file = fc.getSelectedFile();
				controller.saveFile(file);

			} else if (menuSaveAs == e.getSource()) {
				int returnVal = fc.showSaveDialog(GUI.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					controller.saveFile(file);
					menuSave.setEnabled(true);
					
					Component c = getParent();
					while( c.getParent() != null ) {
			            c = c.getParent();
			        }			   
			        Frame topFrame = ( Frame )c;
			        topFrame.setTitle( fc.getName(file) );
				}

			} else if (menuNewFile == e.getSource()) {
				int answer = JOptionPane
						.showConfirmDialog(movieList,
								"Are you sure you want to create a new file?\nDo not forget to save the current file");
				if (answer == JOptionPane.YES_OPTION) {
					controller.deleteAll();
					update();
					menuSave.setEnabled(false);					
				}
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
				update();

			} else if (menuShuffle == e.getSource()) {
				controller.shuffleList();
				update();
				
			} else if (menuEditInfo == e.getSource()) {
				if (movieList.getSelectedIndex() >= 0) {
					int selected = movieList.getSelectedIndex();
					popup(controller.getMovie(selected));
				}

			} else if (menuNewMovie == e.getSource()) {
				popup(controller.addMovie());

			} else if (menuEditDelete == e.getSource()) {
				int answer = JOptionPane.showConfirmDialog(movieList,
						"Are you sure you want to delete this movie?");
				if ((movieList.getSelectedIndex() >= 0)
						&& (answer == JOptionPane.YES_OPTION)) {
					controller.deleteMovie(movieList.getSelectedIndex());
					update();
				}
			} else if (btnSearch == e.getSource()){
				tfSearch.setEnabled(false);
				btnSearch.setEnabled(false);
				btnGoBack.setEnabled(true);
				
			} else if (btnGoBack == e.getSource()){
				tfSearch.setEnabled(true);
				btnSearch.setEnabled(true);
				btnGoBack.setEnabled(false);
				
			}

		}

	}
}
