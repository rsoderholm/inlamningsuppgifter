package inlamningsuppgift1;

public class Movie implements Comparable <Movie> {
	private String title, genre, type, director;
	private String[] actors;
	private int length;
	private double rating;
	
	public Movie(String title, String type){
		this(title, "unknown", type, "unknown", new String[]{"unknown"}, 0, 0.0);
	}
	
	public Movie(String title, String genre, String type, String director, 
								String[] actors, int length, double rating){
		this.title = title;
		this.genre = genre;
		this.type = type;
		this.director = director;
		this.actors = actors;
		this.length = length;
		this.rating = rating;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String[] getActors() {
		return actors;
	}
	public void setActors(String[] actors) {
		this.actors = actors;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	@Override
	public int compareTo(Movie movie) {
		return recursiveCompare(movie, 0);
	}
	
	private int recursiveCompare(Movie movie, int index){
		if(title.length()<=index){
			return -1;
		}else if(movie.getTitle().length()<=index){
			return 1;
		}
		
		if(title.charAt(index)<movie.getTitle().charAt(index)){
			return -1;
		}else if(title.charAt(index)>movie.getTitle().charAt(index)){
			return 1;
		}else{
			recursiveCompare(movie, index+1);
		}
		
		return 0;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", type=" + type + "]";
	}
	
	
}
