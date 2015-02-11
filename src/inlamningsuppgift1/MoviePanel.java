package inlamningsuppgift1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MoviePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Movie movie = new Movie(" ", " ");
	private Controller controller;
	private JLabel[] lblArray;
	private JTextField[] tfArray;
	private JPanel pnlInfo, pnlLabels;
	private JComboBox<String> cbDvdOrBlu;

	public MoviePanel(Controller controller) {
		this.controller = controller;
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(350, 250));
		setLayout(new BorderLayout(5, 5));
		pnlInfo = new JPanel();
		pnlInfo.setLayout(new GridLayout(7,1));
		pnlLabels = new JPanel();
		pnlLabels.setLayout(new GridLayout(7,1));

		lblArray = new JLabel[6];
		lblArray[0] = new JLabel("Title: ");
		lblArray[1] = new JLabel("Genre: ");
		lblArray[2] = new JLabel("Rating:");
		lblArray[3] = new JLabel("Director: ");
		lblArray[4] = new JLabel("Actors: ");
		lblArray[5] = new JLabel("Length[minutes]: ");

		tfArray = new JTextField[6];

		for (int i = 0; i < tfArray.length; i++) {
			tfArray[i] = new JTextField();
			pnlLabels.add(lblArray[i]);
			pnlInfo.add(tfArray[i]);
		}
		
		cbDvdOrBlu = new JComboBox<String>();
		cbDvdOrBlu.addItem("DVD");
		cbDvdOrBlu.addItem("Blu-ray");
		
		add(cbDvdOrBlu, BorderLayout.SOUTH);
		add(pnlInfo, BorderLayout.CENTER);
		add(pnlLabels, BorderLayout.WEST);
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
		tfArray[0].setText(movie.getTitle());
		tfArray[1].setText(movie.getGenre());
		tfArray[2].setText(movie.getRating() + "");
		tfArray[3].setText(movie.getDirector());
		String str = "";

		for (int i = 0; i < movie.getActors().length; i++) {
			if ((movie.getActors().length - 1) > i)
				str += movie.getActors()[i] + ", ";
			else
				str += movie.getActors()[i];
		}

		tfArray[4].setText(str);
		tfArray[5].setText(movie.getLength() + "");

		if (movie.getType().equals("DVD"))
			cbDvdOrBlu.setSelectedIndex(0);
		else
			cbDvdOrBlu.setSelectedIndex(1);
	}

	public Movie getMovie() {
		try {
			Movie movie = new Movie(tfArray[0].getText(), tfArray[1].getText(),
					(String) cbDvdOrBlu.getSelectedItem(),
					tfArray[3].getText(), tfArray[4].getText().split(", "),
					Integer.parseInt(tfArray[5].getText()),
					Double.parseDouble(tfArray[2].getText()));
			return movie;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
				"Formatting is wrong!\nAre you sure the Length and Rating are in the right format?");
		}
		return null;
	}

	public boolean saveMovie() {
		ArrayList<Movie> movieArr = controller.linearSearch(tfArray[0].getText());
		Movie movie;
		
		for(int i = 0; i < tfArray.length; i++){
			try{
				if(tfArray[i].getText().length() == 0){
					JOptionPane.showMessageDialog(this, "One or more fields is empty");
					return false;
				}
			} catch(NullPointerException e){
				JOptionPane.showMessageDialog(this, "One or more fields is empty");
				return false;
			}
		}
		
		if (movieArr.size() == 0) {
			saveMovieInfo();
			return true;
		} else {
			boolean save = true;
			
			// Check list if theres another object with same name and type
			for (int i = 0; i < movieArr.size(); i++) {
				movie = movieArr.get(i);
				
				// A different movie object with the same name, same type, don't save! 
				if(movie != this.movie && movie.getType().equals(cbDvdOrBlu.getSelectedItem()) 
						&& movie.getTitle().equals(tfArray[0].getText())) {
					
					save = false;
					JOptionPane.showMessageDialog(this, "This Movie already exists\nDuplicates not allowed");
				}
			}
			
			if(save) {
				saveMovieInfo();
				return true;
			}
			return false;
		}
	}

	private void saveMovieInfo() {
		try{
		movie.setTitle(tfArray[0].getText());
		movie.setGenre(tfArray[1].getText());
		movie.setRating(Double.parseDouble(tfArray[2].getText()));
		movie.setDirector(tfArray[3].getText());
		movie.setActors(tfArray[4].getText().split(", "));
		movie.setLength(Integer.parseInt(tfArray[5].getText()));
		if (cbDvdOrBlu.getSelectedItem().equals("DVD"))
			movie.setType("DVD");
		else
			movie.setType("Blu-ray");
		} catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null,
					"Formatting is wrong!\nAre you sure the Length and Rating are in the right format?");
		}
	}

}