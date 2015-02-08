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
		List<Movie> movieArr = register.getArray();
		movieArr.remove(selectedIndex);
	}
	
	public void deleteAll() {
		List<Movie> movieArr = register.getArray();
		movieArr.clear();
	}
	
	public Movie addMovie() {
		List<Movie> movieArr = register.getArray();
		movieArr.add(new Movie("This field is required", "This field is required"));
		return movieArr.get(movieArr.size() -1);
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

	public Movie getMovie(int selection) {
		Movie movie = register.getArray().get(selection);		
		return movie;
		
	}

}
