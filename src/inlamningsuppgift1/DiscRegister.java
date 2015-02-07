package inlamningsuppgift1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DiscRegister {
	private Movie[] movieArr;

	public Movie[] getArray() {
		return movieArr;
	}

	public void loadFile(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			br.mark(1000);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int nbrOfLines = 0;
		String line;

		try {
			while ((line = br.readLine()) != null) {
				nbrOfLines++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		movieArr = new Movie[nbrOfLines];

		try {
			br.reset();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int i = 0;
		try {
			while ((line = br.readLine()) != null) {

				String[] part = line.split(",");
				String[] actors = part[4].split("_");

				movieArr[i] = new Movie(part[0], part[1], part[2], part[3],
						actors, Integer.parseInt(part[5]),
						Double.parseDouble(part[6]));
				i++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveFile() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "DiscRegister [movieArr=" + Arrays.toString(movieArr) + "]";
	}

}
