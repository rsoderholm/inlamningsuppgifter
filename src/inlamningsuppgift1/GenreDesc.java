package inlamningsuppgift1;

import java.util.Comparator;

public class GenreDesc implements Comparator<Movie> {

	@Override
	public int compare(Movie movie1, Movie movie2) {
		return movie2.getGenre().compareTo(movie1.getGenre());
	}
}
