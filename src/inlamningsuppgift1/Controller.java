package inlamningsuppgift1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Controller {
	private DiscRegister register;
	List<Movie> arraySearch = new ArrayList<Movie>();

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
	
	public void deleteMovieSearch(int selectedIndex) {
		List<Movie> movieArr = register.getArray();		
		movieArr.remove(arraySearch.get(selectedIndex));		
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

	public void sortListQuick(Comparator<Movie> comp) {
		Sorting.quickSort(register.getArray(), comp);
	}
	
	public void sortListBubble(Comparator<Movie> comp) {
		Sorting.bubbleSort(register.getArray(), comp);
		
	}

	public String[] linearSearch(String str){
		List<Movie> movieArr = register.getArray();		
		arraySearch.clear();
		for(int i = 0; i < movieArr.size(); i++){
			if(movieArr.get(i).getTitle().contains(str)){
				arraySearch.add(movieArr.get(i));				
			}
		}
		return movieToText(arraySearch);
	}
	
	public String[] binarySearch(String text) {
		List<Movie> movieArr = register.getArray();
		Movie key = new Movie(text, "DVD");
		int low = 0;
	    int high = text.length();
	    arraySearch.clear();
	    
	    while (low <= high) {
	    	int mid = (low + high) >>> 1;
			int cmp = movieArr.get(mid).compareTo(key);
			if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
            	arraySearch.add(movieArr.get(mid));
                break;
        }
        return movieToText(arraySearch);  
	     
	}
	
	public String[] getlistAsText() {
		List<Movie> movieArr= register.getArray();
		return movieToText(movieArr);
	}
	
	public String[] movieToText(List<Movie> movieArr){
		if (movieArr != null && !(movieArr.isEmpty())) {			
			String str[] = new String[movieArr.size()];
			
			for (int i = 0; i < movieArr.size(); i++) {
				str[i] = movieArr.get(i).getTitle() + "[" + movieArr.get(i).getType()
						+ "]";
			}
			return str;
		}else{
			return null;
		}
	}

	public Movie getMovie(int selection) {		
		return register.getArray().get(selection);		
	}

	public Movie getMovieSearch(int selectedIndex) {	
		List<Movie> movieArr= register.getArray();
		Movie movie = arraySearch.get(selectedIndex);
		
		return movieArr.get(movieArr.indexOf(movie));
	}
}
