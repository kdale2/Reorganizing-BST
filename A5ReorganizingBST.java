//Kris Dale
//CSC 403
package assignment5;
import java.util.ArrayList;
import week1examples.*;
import java.util.List;
import algs13.Queue;

public class A5ReorganizingBST<K extends Comparable<K>, V> {
	private SimplerBST<K, V> bstree = new SimplerBST<K,V>();
	private int size;

	public A5ReorganizingBST(){
		SimplerBST<K,V> bstree = new SimplerBST<K,V>();
	}

	public void get(K key) {
		bstree.get(key);
	}

	public void put(K key, V Value) {
		
		if (!bstree.contains(key)) { 
			size++;
		}
		
		bstree.put(key, Value);

		if (size % 100 == 0) {
			reorganize();
		}
	}

	public void delete(K key) {
		
		if (bstree.contains(key)) {
			size--;
			bstree.delete(key);
		}
		
		if (size % 100 == 0) {
			reorganize();
	}
}

	public int height() {
		return bstree.height();
	}
	

	private void reorganize() {

		//create a new simpler BST
		SimplerBST<K,V> newBSTree = new SimplerBST<K,V>();
		
		//create an array list of keys in current bst
		//for each loop to add in keys
		ArrayList<K> list = new ArrayList<K>(); {
		for (K key : bstree.keys()) 
			list.add(key);
		}
			
		//iterate through a call to listByMedians which has been passed the list
		//in the loop, put the key and value into the new bst
		for (K key : listByMedians(list)) {
			newBSTree.put(key, bstree.get(key));
		}

		//assign the new bst to current bst	
		bstree = newBSTree;
	}

    private Iterable<K> listByMedians(List<K> keys) {

        Queue<K> medianQueue = new Queue<>();
        Queue<Integer> queueLeft = new Queue<>();
        Queue<Integer> queueRight = new Queue<>();

        queueLeft.enqueue(0);
        queueRight.enqueue(keys.size()-1);
        while (!queueLeft.isEmpty()) {

              int left = queueLeft.dequeue();
              int right = queueRight.dequeue();
              if (left <= right) {

                     int medianIndex = (right+left)/2;
                     K median = keys.get(medianIndex);
                     medianQueue.enqueue(median);
                     queueLeft.enqueue(left);
                     queueRight.enqueue(medianIndex-1);
                     queueLeft.enqueue(medianIndex+1);
                     queueRight.enqueue(right);
              }
        }
        return medianQueue;
 }
}
