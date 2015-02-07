package inlamningsuppgift1;

import java.util.Comparator;

public class GenreDesc implements Comparator<Movie> {

	@Override
	public int compare(Movie movie1, Movie movie2) {
		return recursiveCompare(movie1, movie2, 0);
	}

	private int recursiveCompare(Movie movie1, Movie movie2, int index){
		if(movie1.getGenre().length()<=index)
			return -1;
		else if(movie2.getGenre().length()<=index)
			return 1;
		else if(movie1.getGenre().charAt(index)<movie2.getGenre().charAt(index))
			return -1;
		else if(movie1.getGenre().charAt(index)>movie2.getGenre().charAt(index))
			return 1;
		else
			recursiveCompare(movie1, movie2, index+1);
		
		return 0;
	}
}
