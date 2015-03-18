package gu;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/**
 * This class creates a binary search tree containing Place objects, 
 * in which user can add, remove and get info from tree. 
 * @authors Jonathan Böcker, John Tengvall, David Tran
 * Date: March 13th 2015
 */
public class SearchTree {
	private Node root;

	/**
	 * A constructor to add Places objects to the SearchTree
	 * 
	 * @param places an ArrayList containing the Place objects to put in tree. 
	 */
	public SearchTree(ArrayList<Place> places) {
		Iterator<Place> iter = places.iterator();
		while (iter.hasNext()) {
			put((Place) iter.next());
		}
	}

	/**
	 * Method returns the number of nodes in tree. 
	 * 
	 * @return number of nodes in tree 
	 */
	public int size() {
		if (root == null) {
			return 0;
		}
		return root.size();
	}

	/**
	 * Method to add a node to the tree
	 * 
	 * @param place
	 */
	public void put(Place place) {
		//uses a Collator to compare with swedish letters
		Collator collator = Collator.getInstance(new Locale("sv", "se"));
		//if root is empty the new node will be the root
		if (root == null) {
			root = new Node(place, null, null);
		} else {
			//creates the new node with no children
			Node newNode = new Node(place, null, null);
			//temp node starting from root
			Node temp = root;
			while (temp != null) {
				//if newNode.key smaller than key, look left
				if (collator.compare(newNode.key, temp.key) < 0) {
					//if temp does not have children on left, put the new node there
					if (temp.left == null) {
						temp.left = newNode;
						temp = null;
					//else, continue looking to the left	
					} else {
						temp = temp.left;
					}
				//if the newNode.key smaller than key, look right	
				} else if (collator.compare(newNode.key, temp.key) > 0) {
					if (temp.right == null) {
						//if temp does not have children on left, put the new node there
						temp.right = newNode;
						temp = null;
					//else, continue looking to the left	
					} else {
						temp = temp.right;
					}
				}
			}
		}
	}

	/**
	 * Method to locate a specific Node in the tree
	 * 
	 * @param key The key to be found
	 * @return node null if key not in tree. Else the Node containing the key
	 */
	public Node locate(String key) {
		// skapa res för att minska kod
		int res;
		//uses a Collator to compare with swedish letters
		Collator collator = Collator.getInstance(new Locale("sv", "se"));
		Node node = root;
		// if res == 0, found.
		while ((node != null) && (res = collator.compare(key.toLowerCase(), node.key.toLowerCase())) != 0) {
			if (res < 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return node;
	}

	/**
	 * This method returns Node data, containing a Place object.
	 * 
	 * @param key the String key to look for
	 * @return Place object
	 */
	public Place get(String key) {
		Node node = locate(key);
		if (node != null) {
			return node.data;
		}
		return null;
	}
	
	/**
	 * This method returns all data, which are Place objects, in all nodes in the tree
	 * 
	 * @return ArrayList a list containing all Place objects in the tree
	 */
	public ArrayList<Place> getAll() {
		ArrayList<Place> list = new ArrayList<Place>();
		if(root == null)
			return list;
		return getAll(list, root);
	}
	
	/**
	 * This method returns all data, which are Place objects, in all nodes in the tree
	 * 
	 * @param list the list to put the Place objects in
	 * @param node the root of the tree
	 * @return ArrayList a list containing all Place objects in the tree
	 */
	private ArrayList<Place> getAll(ArrayList<Place> list, Node node) {
		if(node.left != null) 
			getAll(list, node.left);
		list.add(node.data);
		if(node.right != null)
			getAll(list, node.right);
		return list;
	}
	
	/**
	 * This method removes a node from the tree and returns the Place object in the removed node. 
	 * 
	 * @param key the String key to look for
	 * @return the Place object in the deleted node
	 */
	public Place remove(String key) {
		Place value = get(key);
		if (value != null) {
			root = remove(root, key);
		}
		return value;
	}
	
	/**
	 * This method removes a node from the tree and returns the node. 
	 * In some cases when removing a node from tree, we must redirect the parents and children.
	 *  
	 * @param node the root of the tree
	 * @param key the String key to look for
	 * @return the removed node
	 */
	private Node remove(Node node, String key) {
		Collator collator = Collator.getInstance(new Locale("sv", "se"));
		int compare = collator.compare(key.toLowerCase(), node.key.toLowerCase());
		/*if the tree contains the key.
		 * Depending if the removing node have children or not, 
		 * different repointings are made. 
		 */
		if (compare == 0) {
			if (node.left == null && node.right == null)
				node = null;
			else if (node.left != null && node.right == null)
				node = node.left;
			else if (node.left == null && node.right != null)
				node = node.right;
			else {
				Node min = getMin(node.right);
				min.right = remove(node.right, min.key);
				min.left = node.left;
				node = min;
			}
		//recursive methods
		} else if (compare < 0) {
			node.left = remove(node.left, key);
		} else {
			node.right = remove(node.right, key);
		}
		return node;
	}

	/**
	 * Method finds and returns the smallest key in subtree
	 * @param node the root of the subtree
	 */
	private Node getMin(Node node) {
		while (node.left != null)
			node = node.left;
		return node;
	}
	
	/**
	 * Method prints out all objects in tree
	 */
	public void print() {
		root.print();
	}

	/**
	 * Inner class for Node objects used for the tree. 
	 * Nodes contains Place objects as data, and key is set to be the City name
	 */
	private class Node {
		private Node right;
		private Node left;
		private Place data;
		private String key;

		/**
		 * Constructor for setting a Node object
		 * @param data Place object
		 * @param left the Node to the left
		 * @param right the Node to the right
		 */
		public Node(Place data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.key = data.getName();
		}

		/**
		 * Number of nodes in tree is 1 plus all nodes on the left subtree
		 * plus all nodes on the right subtree
		 * 
		 * @return the number of nodes in tree
		 */
		public int size() {
			int leftS = 0, rightS = 0;
			if (left != null)
				leftS = left.size();
			if (right != null)
				rightS = right.size();
			return 1 + leftS + rightS;
		}

		/**
		 * Method prints all Places in tree with an inorder traversal,
		 * printing out Cityname, areal and population
		 */
		public void print() {
			if (left != null)
				left.print();
			System.out.println(data.getName() + ": " + "Hektar:" + data.getArea() + "Population" + data.getPopulation());
			if (right != null)
				right.print();
		}
		/**
		 * The method compares two objects and returns an Integer value > 0, < 0 or 0
		 * @param newNode node to be compared
		 * @return int an Integer value
		 */
		@SuppressWarnings("unused")
		public int compareTo(Node newNode) {
			return newNode.key.compareTo(key);
		}
	}
}
