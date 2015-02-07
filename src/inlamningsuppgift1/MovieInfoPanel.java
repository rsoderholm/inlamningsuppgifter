package inlamningsuppgift1;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class MovieInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField[] tfArray;

	public MovieInfoPanel(String[][] str){
		tfArray = new JTextField[str.length];
		
		tfArray[0] = new JTextField(str[0][0]);
		tfArray[1] = new JTextField(str[1][0]);
		tfArray[2] = new JTextField(str[2][0]);
		tfArray[3] = new JTextField(str[3][0]);
		
		String temp = "";
		for(int i = 0; i < str[4].length; i++){
			temp += str[4][i] + ", ";
		}
		
		tfArray[4] = new JTextField(temp);
		tfArray[5] = new JTextField(str[5][0]);
		tfArray[6] = new JTextField(str[6][0]);

		setLayout(new GridLayout(7, 0));
		
		for(int i = 0; i < tfArray.length; i++){
			add(tfArray[i]);
		}
	}
	

}
