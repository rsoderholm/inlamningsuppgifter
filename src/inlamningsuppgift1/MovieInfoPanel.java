package inlamningsuppgift1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MovieInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GUI gui;
	private Movie movie;
	private JLabel[] lblArray;
	private JTextField[] tfArray;
	private JPanel pnlInfo, pnlButtons, pnlLabels;
	private JButton btnSave;
	private ButtonListener listener;

	public MovieInfoPanel(Movie movie, GUI gui) {
		this.gui = gui;
		this.movie = movie;

		setPreferredSize(new Dimension(350, 200));
		setLayout(new BorderLayout(5, 5));
		pnlInfo = new JPanel(new GridLayout(7, 1));
		pnlLabels = new JPanel(new GridLayout(7, 1));
		pnlButtons = new JPanel();

		lblArray = new JLabel[7];
		lblArray[0] = new JLabel("Title: ");
		lblArray[1] = new JLabel("Genre: ");
		lblArray[2] = new JLabel("Type: ");
		lblArray[3] = new JLabel("Director: ");
		lblArray[4] = new JLabel("Actors: ");
		lblArray[5] = new JLabel("Length[minutes]:");
		lblArray[6] = new JLabel("Rating:");

		for (int i = 0; i < lblArray.length; i++) {
			lblArray[i].setSize(50, 25);
		}

		tfArray = new JTextField[7];
		tfArray[0] = new JTextField(movie.getTitle());
		tfArray[1] = new JTextField(movie.getGenre());
		tfArray[2] = new JTextField(movie.getType());
		tfArray[3] = new JTextField(movie.getDirector());

		String temp = "";
		String[] actors = movie.getActors();
		for (int i = 0; i < actors.length; i++) {
			if(i < actors.length - 1)
				temp += actors[i] + ", ";
			else
				temp += actors[i];
		}

		tfArray[4] = new JTextField(temp);
		tfArray[5] = new JTextField(movie.getLength() + "");
		tfArray[6] = new JTextField(movie.getRating() + "");

		for (int i = 0; i < tfArray.length; i++) {
			pnlLabels.add(lblArray[i]);
			pnlInfo.add(tfArray[i]);
		}

		listener = new ButtonListener();
		btnSave = new JButton("Save");
		btnSave.addActionListener(listener);
		pnlButtons.add(btnSave);

		add(pnlInfo, BorderLayout.CENTER);
		add(pnlLabels, BorderLayout.WEST);
		add(pnlButtons, BorderLayout.SOUTH);
		
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (btnSave == e.getSource()) {
				try {
					movie.setTitle(tfArray[0].getText());
					movie.setGenre(tfArray[1].getText());
					movie.setType(tfArray[2].getText());
					movie.setDirector(tfArray[3].getText());
					movie.setActors(tfArray[4].getText().split(", "));
					movie.setLength(Integer.parseInt(tfArray[5].getText()));
					movie.setRating(Double.parseDouble(tfArray[6].getText()));

				} catch (NullPointerException ex1) {

				} catch (NumberFormatException ex2) {

				}finally{
					gui.update();
				}
			}

		}

	}

}
