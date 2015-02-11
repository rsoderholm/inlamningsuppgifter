package inlamningsuppgift1;

import java.util.Comparator;

public class TypeDesc implements Comparator<Movie>{

	@Override
	public int compare(Movie o1, Movie o2) {
		if(o1.getType().equals("Blu-ray") && o2.getType().equals("DVD"))
			return 1;
		else if(o1.getType().equals("DVD") && o2.getType().equals("Blu-ray"))
			return -1;
		else
			return 0;
		
	}

}
