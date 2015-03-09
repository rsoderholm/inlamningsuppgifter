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

	public int size() {
		if (root == null) {
			return 0;
		}
		return root.size();
	}

	public void put(Place place) {
		if (root == null) {
			root = new Node(place, null, null);
		} else {
			Node newNode = new Node(place, null, null);
			Node temp = root;
			while (temp != null) {
				if (temp.key.compareTo(newNode.key) > 0) {
					if (temp.left == null) {
						temp.left = newNode;
						temp = null;
					} else {
						temp = temp.left;
					}
				} else if (temp.key.compareTo(newNode.key) < 0) {
					if (temp.right == null) {
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
	public Node locate(String key) {
		// skapa res för att minska kod
		int res;
		Node node = root;
		// om res == 0, hittad.
		while ((node != null) && (res = node.key.compareTo(key)) != 0) {
			if (res > 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return node;
	}

	public Place get(String key) {
		Node node = locate(key);
		if (node != null) {
			return node.data;
		}
		return null;
		// return locate(key).data; //locate(key) kan returnera null och det går
		// inte att hämta data från null
	}
	
	public ArrayList<Place> getAll() {
		ArrayList<Place> list = new ArrayList<Place>();
		Node temp = root;
		if(temp == null) {
			list = null;
		}
		while(temp != null) {
			if(temp.left == null) {
				list.add(temp.left.data);
			}
			list.add(temp.data);
		}
			
			
			list.add(get(root.left.key));
		if (root.right != null)
			list.add(get(root.right.key));
		return list;
	}
	
	

	public Place remove(String key) {
		Place value = get(key);
		if (value != null) {
			root = remove(root, key);
		}
		return value;
	}
	
	

	private Node remove(Node node, String key) {
		int compare = node.key.compareTo(key);
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
		} else if (compare > 0) {
			node.left = remove(node.left, key);
		} else {
			node.right = remove(node.right, key);
		}
		return node;
	}

	private Node getMin(Node node) {
		while (node.left != null)
			node = node.left;
		return node;
	}
	
	

	public void print() {
		root.print();
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

		/**
		 * Antalet element i trädet är 1 + antalet element i det vänstra
		 * subträdet + antalet element i det högra subträdet
		 * 
		 * @return
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
		 * Inorder traversal
		 */
		public void print() {
			if (left != null)
				left.print();
			System.out.println(key + ": " + data.getArea());
			if (right != null)
				right.print();
		}
		
		public Node getLeft() {
			return left;
		}
		
		public Node getRight() {
			return right;
		}

		public void setLeft(Node node) {
			this.left = node;
		}
		
		public void setRight(Node node) {
			this.right = node;
		}
//		
//		public Place get() {
//			if (left != null)
//				left.get();
//				
//			if (root.right != null);
//				
//		}
		
		@SuppressWarnings("unused")
		public int compareTo(Node newNode) {
			return key.compareTo(newNode.key);
		}
	}
}
