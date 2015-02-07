package inlamningsuppgift1;

import java.io.File;
import java.util.Comparator;
import java.util.Random;

public class Controller {
	private DiscRegister register;

	public Controller(DiscRegister register) {
		this.register = register;
		
	}

	public void loadFile(File file) {
		register.loadFile(file);
	}

	public void saveFile() {
		register.saveFile();
	}

	public void shuffleList() {
		Movie movieArr[] = register.getArray();
		if (movieArr != null) {
			Movie temp;
			Random rand = new Random();
			int pos;

			for (int i = 0; i < movieArr.length; i++) {
				pos = rand.nextInt(movieArr.length);
				temp = movieArr[i];
				movieArr[i] = movieArr[pos];
				movieArr[pos] = temp;
			}
		}
	}

	public void sortList(Comparator<Movie> comp) {
		Sorting.quickSort(register.getArray(), comp);
	}

	public String[] getlistAsText() {
		Movie movieArr[] = register.getArray();
		if (movieArr != null) {
			String str[] = new String[movieArr.length];
			for (int i = 0; i < movieArr.length; i++) {
				str[i] = movieArr[i].getTitle() + "[" + movieArr[i].getType()
						+ "]";
			}
			return str;
		}else{
			String str[] = {"No file Loaded"};
			return str;
		}
	}

	public String[][] getMovieInfo(int selection) {
		Movie movie = register.getArray()[selection];
		String str[][] = new String[7][10];
		str[0][0] = movie.getTitle();
		str[1][0] = movie.getGenre();
		str[2][0] = movie.getType();
		str[3][0] = movie.getDirector();
		str[4] = movie.getActors();
		str[5][0] = movie.getLength() + "";
		str[6][0] = movie.getRating() + "";
		
		return str;
		
	}
}
