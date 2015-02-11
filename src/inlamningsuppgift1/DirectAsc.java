package inlamningsuppgift1;

import java.util.Comparator;

public class DirectAsc implements Comparator<Movie> {

	@Override
	public int compare(Movie movie1, Movie movie2) {
		return movie1.getDirector().compareTo(movie2.getDirector());
	}
}
