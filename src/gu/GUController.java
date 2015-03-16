package gu;

import java.util.ArrayList;

/**
 * This class controls the program. All communication between GUEntity and
 * InterfaceMain goes through GUController.
 * 
 * @authors Jonathan Böcker, John Tengvall, David Tran
 * Date: March 13th 2015
 */
public class GUController {
	private InterfaceMain window;
	private ArrayList<Road> printList;
	private GUEntity entity = new GUEntity();

	/**
	 * Constructor that creates interface, loads the final paths and roads, then
	 * it sets all places in interface.
	 * 
	 * @param imagePath File path for map
	 * @param mapLeftUp Position for map on left side up
	 * @param mapRightDown Position for map on right side down
	 * @param placesPath File path for places text file
	 * @param roadsPath File path for roads text file
	 * @param iconPath File path for icon
	 */
	public GUController(String imagePath, Position mapLeftUp,
			Position mapRightDown, String placesPath, String roadsPath,
			String iconPath) {

		entity.loadFiles(roadsPath, placesPath);
		window = new InterfaceMain(imagePath, iconPath, mapLeftUp,
				mapRightDown, this);
	}

	/**
	 * Method returns place thats been searched for.
	 * 
	 * @param key the String key, city name
	 * @return ArrayList<Place> Place object
	 */
	public Place searchPlace(String key) {
		return entity.searchPlace(key);
	}

	/**
	 * Method returns all place object.
	 * 
	 * @return ArrayList<Place> All place object
	 */
	public ArrayList<Place> getAllPlaces() {
		return entity.getAllPlaces();
	}

	/**
	 * The method calls for the entity class to remove a Place from list.
	 * 
	 * @param key the String key, city name
	 */
	public void removePlace(String key) {
		entity.removePlace(key);
	}

	/**
	 * Method gets Depth Search from entity and returns a list with the roads. 
	 * 
	 * @param from start point
	 * @param to End point
	 * @return ArrayList<Road> List with the roads
	 */
	public ArrayList<Road> searchDepthFirst(String from, String to) {
		printList = entity.searchDepthFirst(from, to);
		return printList;
	}

	/**
	 * Method gets Breadth Search from entity and returns a list with the roads. 
	 * 
	 * @param from start point
	 * @param to End point
	 * @return ArrayList<Road> List with the roads
	 */
	public ArrayList<Road> searchBreadthFirst(String from, String to) {
		printList = entity.searchBreadthFirst(from, to);
		return printList;
	}

	/**
	 * Method gets Dijkstra Search from entity and returns a list with the roads. 
	 * 
	 * @param from start point
	 * @param to End point
	 * @return ArrayList<Road> List with the roads
	 */
	public ArrayList<Road> searchDijkstra(String from, String to) {
		printList = entity.searchDijkstra(from, to);
		return printList;
	}

}
