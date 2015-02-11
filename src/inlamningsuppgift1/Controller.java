package inlamningsuppgift1;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class Controller {
	private DiscRegister register;
	ArrayList<Movie> searchResult = new ArrayList<Movie>();

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
		ArrayList<Movie> movieArr = register.getArray();
		movieArr.remove(selectedIndex);
	}
	
	public void deleteMovieSearch(int selectedIndex) {
		ArrayList<Movie> movieArr = register.getArray();		
		movieArr.remove(searchResult.get(selectedIndex));		
	}
	
	public void deleteAll() {
		ArrayList<Movie> movieArr = register.getArray();
		movieArr.clear();
	}
	
	public void addMovie(Movie movie) {
		ArrayList<Movie> movieArr = register.getArray();
		movieArr.add(movie);
	}
	
	public void shuffleList() {
		ArrayList<Movie> movieArr = register.getArray();
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

	public ArrayList<Movie> linearSearch(String str){
		searchResult.clear();
		Iterator<Movie> iter = register.getArray().iterator();		
		Movie movie;
		
		for(;iter.hasNext();){
			movie = iter.next();
			if(movie.getTitle().contains(str)){
				searchResult.add(movie);				
			}
		}
		return searchResult;
	}
	
	public ArrayList<Movie> binarySearch(String text) {
		ArrayList<Movie> movieArr = register.getArray();
		Movie key = new Movie(text, "DVD");
		int low = 0;
	    int high = movieArr.size();
	    searchResult.clear();
	    
	    while (low <= high) {
	    	int mid = (low + high) / 2;
			int cmp = movieArr.get(mid).compareTo(key);
			if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else{
            	searchResult.add(movieArr.get(mid));				
                break;
            }
                
        }
        return searchResult;  
	     
	}
	
	public ArrayList<Movie> getlist() {
		ArrayList<Movie> movieArr = register.getArray();
		return movieArr;
	}
		
	public Movie getMovie(int selection) {		
		return register.getArray().get(selection);		
	}

	public Movie getMovieSearch(int selectedIndex) {	
		ArrayList<Movie> movieArr= register.getArray();
		Movie movie = searchResult.get(selectedIndex);
		
		return movieArr.get(movieArr.indexOf(movie));
	}
}
