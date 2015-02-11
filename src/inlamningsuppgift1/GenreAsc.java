package inlamningsuppgift1;

import java.util.Comparator;

public class GenreAsc implements Comparator<Movie> {

	@Override
	public int compare(Movie movie1, Movie movie2) {
		return movie1.getGenre().compareTo(movie2.getGenre());
	}
}
