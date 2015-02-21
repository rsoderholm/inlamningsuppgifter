package inlamningsuppgift2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TestDoublyLinkedListApp extends JPanel {
	private static final long serialVersionUID = 8086873864472405053L;
	private DoublyLinkedList<String> list = new DoublyLinkedList<String>();
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private ButtonListener btnListener;
	private SelectionListener listListener;
	private JScrollPane spStringList;
	private JList<String> stringList;
	private JButton btnAddLast, btnAddFirst, btnRemoveFirst, btnRemoveLast,
			btnAddSelected, btnRemoveSelected;
	private JTextField textfield;
	private JLabel lblFirst, lblLast;
	private Font font;
	private JPanel pnlNorth, pnlSouth;

	public TestDoublyLinkedListApp() {
		setLayout(new BorderLayout());

		btnListener = new ButtonListener();
		stringList = new JList<String>();
		spStringList = new JScrollPane(stringList);

		stringList.addListSelectionListener(listListener);

		btnAddFirst = new JButton("Add first in list");
		btnAddLast = new JButton("Add last in list");
		btnRemoveFirst = new JButton("Remove first in list");
		btnRemoveLast = new JButton("Remove last in list");
		btnAddSelected = new JButton("Add at selected");
		btnRemoveSelected = new JButton("Remove selected");

		btnAddFirst.addActionListener(btnListener);
		btnAddLast.addActionListener(btnListener);
		btnRemoveFirst.addActionListener(btnListener);
		btnRemoveLast.addActionListener(btnListener);
		btnAddSelected.addActionListener(btnListener);
		btnRemoveSelected.addActionListener(btnListener);

		textfield = new JTextField("Enter text to inject");

		pnlNorth = new JPanel();
		pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.X_AXIS));
		pnlNorth.add(btnAddFirst);
		pnlNorth.add(btnAddLast);
		pnlNorth.add(btnAddSelected);
		pnlNorth.add(textfield);
		pnlNorth.add(btnRemoveFirst);
		pnlNorth.add(btnRemoveLast);
		pnlNorth.add(btnRemoveSelected);
		
		font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		
		lblFirst = new JLabel("First element: ");
		lblLast = new JLabel("Last element: ");
		
		lblFirst.setFont(font);
		lblLast.setFont(font);

		pnlSouth = new JPanel();
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
		pnlSouth.add(lblFirst);
		pnlSouth.add(lblLast);

		add(pnlNorth, BorderLayout.NORTH);
		add(spStringList, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnAddFirst) {
				list.addFirst(textfield.getText());
				
			} else if (e.getSource() == btnAddLast) {
				list.addLast(textfield.getText());
				
			} else if (e.getSource() == btnAddSelected) {
				if (stringList.getSelectedIndex() > -1)
					list.add(stringList.getSelectedIndex(), textfield.getText());
				
			} else if (e.getSource() == btnRemoveFirst) {
				textfield.setText(list.removeFirst());
				
			} else if (e.getSource() == btnRemoveLast) {
				textfield.setText(list.removeLast());
				
			} else if (e.getSource() == btnRemoveSelected) {
				if (stringList.getSelectedIndex() > -1)
					textfield
							.setText(list.remove(stringList.getSelectedIndex()));
			}

			listModel.clear();

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).length() == 0)
					listModel.addElement("null");
				else
					listModel.addElement(list.get(i));
			}

			stringList.setModel(listModel);
			lblFirst.setText("First element: " + list.getFirst());
			lblLast.setText("Last element: " + list.getLast());

		}

	}

	private class SelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {

		}

		TestDoublyLinkedListApp gui = new TestDoublyLinkedListApp();
		JFrame frame = new JFrame("Inlämningsuppgift 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gui);
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

	}

}
