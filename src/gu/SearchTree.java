package gu;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchTree {
	private Node root;

	public SearchTree(ArrayList<Place> places) {
		Iterator<Place> iter = places.iterator();
		while (iter.hasNext()) {
			put((Place) iter.next());
		}
	}

	public void put(Place place) {
		if (root == null) {
			root = new Node(place, null, null);
		} else {
			Node newNode = new Node(place, null, null);
			Node temp = root;
			while (temp != null) {
				if (temp.key.compareTo(newNode.key) > 0) {
					if(temp.left == null){
						temp.left = newNode;
						temp = null;
					}else{
						temp = temp.left;
					}
				} else if (temp.key.compareTo(newNode.key) < 0) {
					if(temp.right == null){
						temp.right = newNode;
						temp = null;
					} else {
						temp = temp.right;
					}					
				}
			}
		}
	}
	
	// TODO
	public Place remove(String key) {
		return null;
	}
	
	// TODO
	public Node locate(String key) {
		//skapa res för att minska kod
		int res;
		Node node = root;
		//om res == 0, hittad. 
		while((node != null) && (res = node.key.compareTo(key)) != 0 ) {
			if( res > 0 ) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return node; 
	}
	
	public Place get(String key) {
		Node node = locate(key);
		if(node != null) {
			return node.data;
		}
		return null;
	}

	private class Node {
		private Node right;
		private Node left;
		private Place data;
		private String key;

		public Node(Place data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.key = data.getName();
		}

		@SuppressWarnings("unused")
		public int compareTo(Node newNode) {
			return key.compareTo(newNode.key);
		}
	}
}
