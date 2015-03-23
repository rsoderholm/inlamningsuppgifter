package gu;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class stores the data used the program. 
 * It is responsible for all calculations and transformations 
 * of this data. 
 * 
 * @authors Jonathan Böcker, John Tengvall, David Tran
 * Date: March 13th 2015
 *
 */
public class GUEntity {	
	private Graph<Place> graph = new Graph<Place>();
	private HashMap<String, Place> placeHash = new HashMap<String, Place>();
	private HashMap<String, Road> roadHash = new HashMap<String, Road>();
	private ArrayList<Road> printList;
	private SearchTree search;

	/**
	 * Searchmethod using DepthFirstSearch. 
	 * 
	 * @param from start point
	 * @param to end point
	 * @return ArrayList<Road> a list containing the roads between from and to places
	 */
	public ArrayList<Road> searchDepthFirst(String from, String to) {
		printList = convertEdgeToRoad(GraphSearch.depthFirstSearch(graph,
				placeHash.get(from), placeHash.get(to)));
		return printList;
	}

	/**
	 * Searchmethod using BreadthFirstSearch. 
	 * 
	 * @param from start point
	 * @param to end point
	 * @return ArrayList<Road> a list containing the roads between from and to places
	 */
	public ArrayList<Road> searchBreadthFirst(String from, String to) {
		printList = convertEdgeToRoad(GraphSearch.breadthFirstSearch(graph,
				placeHash.get(from), placeHash.get(to)));
		return printList;
	}

	/**
	 * Searchmethod using DijskataSearch. 
	 * 
	 * @param from start point
	 * @param to end point
	 * @return ArrayList<Road> a list containing the roads between from and to places
	 */
	public ArrayList<Road> searchDijkstra(String from, String to) {
		printList = convertEdgeToRoad(GraphSearch.dijkstraSearch(graph,
				placeHash.get(from), placeHash.get(to)));
		return printList;
	}
	
	/**
	 * The method to get att return all Place objects from the binary search tree
	 * 
	 * @return ArrayList<Place> a list containing all Place objects in tree
	 */
	public ArrayList<Place> getAllPlaces() {
		return search.getAll();
	}

	/**
	 * The method searches for a Place object in the binary search tree 
	 * using a key and returns the Place object
	 * 
	 * @param key the String key look for, city name
	 * @return Place Place object
	 */
	public Place searchPlace(String key) {
		return search.get(key);
	}

	/**
	 * The method removes a Place object in the binary search tree 
	 * 
	 * @param key the String key, city name
	 */
	public void removePlace(String key) {
		search.remove(key);
	}

	/**
	 * This method convert an edge Object to an road object.  
	 * 
	 * @param path contains ArrayList with places
	 * @return tempRoadsList temporary list that contains roads
	 */
	private ArrayList<Road> convertEdgeToRoad(ArrayList<Edge<Place>> path) {
		ArrayList<Road> tempRoadsList = new ArrayList<Road>();
		for (Edge<Place> road : path) {
			tempRoadsList.add(roadHash.get(road.getFrom().getName()
					+ road.getTo().getName()));
		}
		return tempRoadsList;
	}

	/**
	 * This method load text files and reads them with a specified pattern. 
	 * 
	 * @param roadsPath File path for roads text file
	 * @param placesPath File path for places text file
	 */
	public void loadFiles(String roadsPath, String placesPath) {
		ArrayList<Place> placesList = new ArrayList<Place>();
		ArrayList<Road> roadsList = new ArrayList<Road>();
		BufferedReader br = null;
		File roadsFile = new File(this.getClass().getResource(roadsPath)
				.getPath());
		File placesFile = new File(this.getClass().getResource(placesPath)
				.getPath());

		try {
			br = Files.newBufferedReader(placesFile.toPath(),
					StandardCharsets.ISO_8859_1);

			String line;
			Place tempPlace;
			Road tempRoad;
			// will only execute if line in text file is not empty
			while ((line = br.readLine()) != null) {
				// if line in text file starts with "/" it will not do anything
				if (line.charAt(0) == '/') {
				} else {
					// Creates an String array
					String[] placeArray = line.split(" ");
					// Puts data from text file in index order in tempPlace. E.g
					// index 0 contains city name
					tempPlace = new Place(placeArray[0],
							Double.parseDouble(placeArray[2]),
							Double.parseDouble(placeArray[1]),
							Double.parseDouble(placeArray[3]),
							Integer.parseInt(placeArray[4]));
					// adds tempPlace in ArrayList placesList
					placesList.add(tempPlace);
					// first index(city name) is key in HashMap and it associate
					// with rest of the array
					placeHash.put(placeArray[0], tempPlace);
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
				tempRoad = new Road(roadProperties[0], roadProperties[1],
						Integer.parseInt(roadProperties[2]), roadPositions);
				roadsList.add(tempRoad);
				roadHash.put(tempRoad.getFrom() + tempRoad.getTo(), tempRoad);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// Create the binary search tree
		search = new SearchTree(placesList);
		// Get the sorted list from the search tree
		placesList = getAllPlaces();
		// Fill the graph object with all data available
		makeGraph(placesList, roadsList);
	}

	/**
	 * 
	 * @param ArrayList<Place> a list containing information about different places.
	 * @param ArrayList<Road> a list containing the roads between from and to places
	 */
	private void makeGraph(ArrayList<Place> places, ArrayList<Road> roads) {
		for (Place place : places) {
			graph.addVertex(place);
		}
		for (Iterator<Road> it = roads.iterator(); it.hasNext();) {
			Road temp = it.next();
			graph.addEdge(placeHash.get(temp.getFrom()),
					placeHash.get(temp.getTo()), temp.getCost());
		}
	}
}
