package inlamningsuppgift1;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Controller {
	private DiscRegister register;

	public Controller(DiscRegister register) {
		this.register = register;		
	}

	public void loadFile(File file) {
		register.loadFile(file);
	}

	public void saveFile(File file) {
		register.saveFile(file);
	}

	public void deleteMovie(int selectedIndex) {
		register.deleteMovie(selectedIndex);		
	}
	
	public void shuffleList() {
		List<Movie> movieArr = register.getArray();
		if (!(movieArr.isEmpty())) {
			Movie temp;
			Random rand = new Random();
			int pos;

			for (int i = 0; i < movieArr.size(); i++) {
				pos = rand.nextInt(movieArr.size());
				temp = movieArr.get(i);
				movieArr.set(i, movieArr.get(pos));
				movieArr.set(pos, temp);
			}
		}
	}

	public void sortList(Comparator<Movie> comp) {
		Sorting.quickSort(register.getArray(), comp);
	}

	public String[] getlistAsText() {
		List<Movie> movieArr= register.getArray();
		if (!(movieArr.isEmpty())) {
			String str[] = new String[movieArr.size()];
			for (int i = 0; i < movieArr.size(); i++) {
				str[i] = movieArr.get(i).getTitle() + "[" + movieArr.get(i).getType()
						+ "]";
			}
			return str;
		}else{
			String str[] = {"No file Loaded"};
			return str;
		}
	}

	public String[][] getMovieInfo(int selection) {
		Movie movie = register.getArray().get(selection);
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
