package gu;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;

public class GUController {
	private InterfaceMain window;
	private ArrayList<Place> places = new ArrayList<Place>();
	private ArrayList<Road> roads = new ArrayList<Road>();
	private Graph<String> graph = new Graph<String>();
	private String placesPath;
	private String roadsPath;
	private String imagePath;
	private Position mapLeftUp;
	private Position mapRightDown;
	private SearchTree search;
	

	public GUController(String imagePath, Position mapLeftUp,
			Position mapRightDown, String placesPath, String roadsPath) {
		this.placesPath = placesPath;
		this.roadsPath = roadsPath;
		this.imagePath = imagePath;
		this.mapLeftUp = mapLeftUp;
		this.mapRightDown = mapRightDown;

		window = new InterfaceMain(this.imagePath, this.mapLeftUp,
				this.mapRightDown, this);

	}

	public ArrayList<Place> getPlaces(){
		return places;
	}
	public void showAllRoads() {
		window.showRoads(roads);
	}
	
	public Place searchPlace(String key){
		return search.get(key);
	}

	public void searchDepthFirst(String from, String to) {
		ArrayList<Edge<String>> path;
		ArrayList<Road> tempRoads = new ArrayList<Road>();
		if (graph.containsVertex(from)) {
			path = GraphSearch.depthFirstSearch(graph, from, to);
			for (Edge<String> edge : path) {
				tempRoads.add(roads.get(Integer.parseInt(edge.getFrom() + "-"
						+ edge.getTo())));
			}
			window.showRoads(tempRoads);
		}
	}

	public void searchBreadthFirst(String from, String to) {
		ArrayList<Edge<String>> path;
		ArrayList<Road> tempRoads = new ArrayList<Road>();
		if (graph.containsVertex(from)) {
			path = GraphSearch.breadthFirstSearch(graph, from, to);
			for (Edge<String> edge : path) {
				tempRoads.add(roads.get(Integer.parseInt(edge.getFrom() + "-"
						+ edge.getTo())));
			}
			window.showRoads(tempRoads);
		}
	}

	public void searchDijkstra(String from, String to) {
		ArrayList<Edge<String>> path;
		ArrayList<Road> tempRoads = new ArrayList<Road>();
		if (graph.containsVertex(from)) {
			path = GraphSearch.dijkstraSearch(graph, from, to);
			for (Edge<String> edge : path) {
				tempRoads.add(roads.get(Integer.parseInt(edge.getFrom() + "-"
						+ edge.getTo())));
			}
			window.showRoads(tempRoads);
		}
	}

	public void loadFiles() {
		BufferedReader br = null;
		File roadsFile = new File(this.getClass().getResource(roadsPath)
				.getPath());
		File placesFile = new File(this.getClass().getResource(placesPath)
				.getPath());

		try {
			br = Files.newBufferedReader(placesFile.toPath(),
					StandardCharsets.ISO_8859_1);

			String line;

			while ((line = br.readLine()) != null) {
				if (line.charAt(0) == '/') {
				} else {
					String[] placeArray = line.split(" ");
					places.add(new Place(placeArray[0], Double
							.parseDouble(placeArray[2]), Double
							.parseDouble(placeArray[1]), Double
							.parseDouble(placeArray[3]), Integer
							.parseInt(placeArray[4])));
				}
			}

			br = Files.newBufferedReader(roadsFile.toPath(),
					StandardCharsets.ISO_8859_1);

			while ((line = br.readLine()) != null) {
				ArrayList<Position> roadPositions = new ArrayList<Position>();
				String[] roadProperties = line.split(",");

				for (int i = 3; i < roadProperties.length; i += 2) {
					roadPositions.add(new Position(Double
							.parseDouble(roadProperties[i]), Double
							.parseDouble(roadProperties[i + 1])));
				}

				roads.add(new Road(roadProperties[0], roadProperties[1],
						Integer.parseInt(roadProperties[2]), roadPositions));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		search = new SearchTree(places);
	}
	
	public void makeGraph(ArrayList<Place> places, ArrayList<Road> roads) {
		Iterator<Road> values = roads.iterator();
		Road road;
		for (Place place : places) {
			graph.addVertex(place.getName());
		}
		while (values.hasNext()) {
			road = values.next();
			graph.addEdge(road.getFrom(), road.getTo(), road.getCost());
		}
	}

}
