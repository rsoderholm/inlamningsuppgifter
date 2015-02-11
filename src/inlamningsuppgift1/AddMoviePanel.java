package inlamningsuppgift1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AddMoviePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private GUI gui;
	private Controller controller;
	private MoviePanel moviePanel;
	private JPanel pnlButtons;
	private JButton btnAdd;
	private ButtonListener listener;

	public AddMoviePanel(GUI gui, Controller controller) {
		this.gui = gui;
		this.controller = controller;

		setPreferredSize(new Dimension(350, 300));
		setLayout(new BorderLayout(5, 5));
		moviePanel = new MoviePanel(controller);
		pnlButtons = new JPanel();

		listener = new ButtonListener();
		btnAdd = new JButton("Add Movie");
		btnAdd.addActionListener(listener);
		pnlButtons.add(btnAdd);

		add(moviePanel, BorderLayout.CENTER);
		add(pnlButtons, BorderLayout.SOUTH);
		
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (btnAdd == e.getSource()) {
				if(moviePanel.saveMovie()){
					controller.addMovie(moviePanel.getMovie());	
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
