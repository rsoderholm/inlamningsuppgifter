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

		window = new InterfaceMain(imagePath, iconPath, mapLeftUp,
				mapRightDown, this);
		entity.loadFiles(roadsPath, placesPath);
		window.setPlaces(entity.getAllPlaces());
	}

	/**
	 * Method returns place thats been searched for.
	 * 
	 * @param key City name
	 * @return Place Place object
	 */
	public Place searchPlace(String key) {
		return entity.searchPlace(key);
	}

	/**
	 * Method returns all place object.
	 * 
	 * @return Place All place object
	 */
	public ArrayList<Place> getAllPlaces() {
		return entity.getAllPlaces();
	}

	/**
	 * Method removes certain place from ArrayList.
	 * 
	 * @param key City name
	 */
	public void removePlace(String key) {
		entity.removePlace(key);
		window.setPlaces(entity.getAllPlaces());
	}

	/**
	 * Method gets Depth Search from entity and prints path on GUI. It also
	 * prints route in text.
	 * 
	 * @param from Start city
	 * @param to End city
	 */
	public void searchDepthFirst(String from, String to) {
		printList = entity.searchDepthFirst(from, to);
		window.showRoads(printList);
		window.printRoadInfo(printList);
	}

	/**
	 * Method gets Breadth Search from entity and prints path on GUI. It also
	 * prints route in text.
	 * 
	 * @param from Start city
	 * @param to End city
	 */
	public void searchBreadthFirst(String from, String to) {
		printList = entity.searchBreadthFirst(from, to);
		window.showRoads(printList);
		window.printRoadInfo(printList);
	}

	/**
	 * Method gets Dijkstra Search from entity and prints path on GUI. It also
	 * prints route in text.
	 * 
	 * @param from Start city
	 * @param to End city
	 */
	public void searchDijkstra(String from, String to) {
		printList = entity.searchDijkstra(from, to);
		window.showRoads(printList);
		window.printRoadInfo(printList);
	}

}
