package gu;

import java.util.ArrayList;

/**
 * 
 * 
 * @authors Jonathan Böcker, John Tengvall, David Tran
 *
 */
public class GUController {
	private InterfaceMain window;
	private ArrayList<Road> printList;
	private GUEntity entity = new GUEntity();

	public GUController(String imagePath, Position mapLeftUp,
			Position mapRightDown, String placesPath, String roadsPath,
			String iconPath) {

		window = new InterfaceMain(imagePath, iconPath, mapLeftUp,
				mapRightDown, this);
		entity.loadFiles(roadsPath, placesPath);
		window.setPlaces(entity.getAllPlaces());
	}

	public Place searchPlace(String key) {
		return entity.searchPlace(key);
	}

	public ArrayList<Place> getAllPlaces() {
		return entity.getAllPlaces();
	}

	public void removePlace(String key) {
		entity.removePlace(key);
		window.setPlaces(entity.getAllPlaces());
	}

	public void searchDepthFirst(String from, String to) {
		printList = entity.searchDepthFirst(from, to);
		window.showRoads(printList);
		window.printRoadInfo(printList);
	}

	public void searchBreadthFirst(String from, String to) {
		printList = entity.searchBreadthFirst(from, to);
		window.showRoads(printList);
		window.printRoadInfo(printList);
	}

	public void searchDijkstra(String from, String to) {
		printList = entity.searchDijkstra(from, to);
		window.showRoads(printList);
		window.printRoadInfo(printList);
	}

}
