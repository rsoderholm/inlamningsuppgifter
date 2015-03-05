package gu;

public class Place {
    private String name;
    private Position position;
    private double area;
    private int population;

    public Place(String name, double longitude, double latitude, double area, int population) {
        this.name = name;
        this.position = new Position(longitude, latitude);
        this.area = area;
        this.population = population;
    }
   
    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
    

    public double getArea() {
		return area;
	}

	public int getPopulation() {
		return population;
	}

	public String toString() {
        return name;
    }
}
