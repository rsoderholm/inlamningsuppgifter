package inlamningsuppgift1;

import java.util.Comparator;

public class DirectDesc implements Comparator<Movie> {

	@Override
	public int compare(Movie movie1, Movie movie2) {
		return movie2.getDirector().compareTo(movie1.getDirector());
	}
}
