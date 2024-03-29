package inlamningsuppgift1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MovieInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GUI gui;
	private MoviePanel moviePanel;
	private JPanel pnlButtons;
	private JButton btnSave;
	private ButtonListener listener;

	public MovieInfoPanel(Movie movie, GUI gui, Controller controller) {
		this.gui = gui;

		setPreferredSize(new Dimension(350, 300));
		setLayout(new BorderLayout(5, 5));
		moviePanel = new MoviePanel(controller);
		moviePanel.setMovie(movie);
		pnlButtons = new JPanel();

		listener = new ButtonListener();
		btnSave = new JButton("Save");
		btnSave.addActionListener(listener);
		pnlButtons.add(btnSave);

		add(moviePanel, BorderLayout.CENTER);
		add(pnlButtons, BorderLayout.SOUTH);

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (btnSave == e.getSource()) {
				if (moviePanel.saveMovie()) {
					gui.update();
					Component c = getParent();

					while (c.getParent() != null) {
						c = c.getParent();
					}
					JFrame topFrame = (JFrame) c;
					topFrame.dispose();
				}
			}
		}

	}

}
