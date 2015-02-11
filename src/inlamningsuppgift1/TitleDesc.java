package inlamningsuppgift1;

import java.util.Comparator;

public class TitleDesc implements Comparator<Movie> {

	@Override
	public int compare(Movie movie1, Movie movie2) {
		return movie2.getTitle().compareTo(movie1.getTitle());
	}
}
