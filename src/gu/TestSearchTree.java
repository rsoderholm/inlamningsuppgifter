package gu;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class TestSearchTree {

	public static void main(String[] args) {
	
		ArrayList<Place> places = new ArrayList<Place>();
		places.add(new Place("Malmö", 100, 100, 100, 100));
	
		
		SearchTree tree = new SearchTree(places);
		TestSearchTree test = new TestSearchTree();
//		for(int i = 0; i < places.size(); i++) {
//			System.out.print(places.get(i) + " ");
//		}
		System.out.println();
		tree.put(new Place("Mariestad", 100, 100, 100, 100));
		tree.put(new Place("Höör", 100, 100, 100, 100));
		tree.put(new Place("Eslöv", 100, 100, 100, 100));
		tree.put(new Place("Cypern", 100, 100, 100, 100));
		tree.put(new Place("Amsterdam", 100, 100, 100, 100));
		tree.put(new Place("Tyringe", 100, 100, 100, 100));
		tree.put(new Place("Simrishamn", 100, 100, 100, 100));
		tree.put(new Place("Landskrona", 100, 100, 100, 100));
		tree.put(new Place("New York", 100, 100, 100, 100));
		tree.put(new Place("Paris", 100, 100, 100, 100));
		
		
		tree.print();
		System.out.println(tree.get("Landskrona"));
		System.out.print(tree.size());
		
		
	}
}
