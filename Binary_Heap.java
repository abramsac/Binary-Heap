import java.util.Arrays;
import java.util.Random;


public class Binary_Heap {
	/*
	 * Heapify creates a new list, sorts it, and then moves every index to index + 1, so the index 0
	 * just holds a 0, but is not used
	 */
	//RULES OF HEAP ARRAY.........
		//left child located at 2*i index
		//right child at 2*i + 1 index
		//parent at floor(i/2)
	public static int[] heapify(int[] list){
		Arrays.sort(list);
		if (list[0] != 0) {
			int[] finallist = new int[list.length + 1];
			for(int i = 0; i<list.length; i++){
				finallist[i+1] = list[i];
			}
			return finallist;
		}
		return list;
	}
	
	/*
	 * The add function adds the new number to the end of the heap array and then percolates up
	 * until it has a parent less than it and children greater than it
	 */
	
	public static int[] add(int[] list, int num){
		int[] newlist = new int[list.length+1];
		for(int i = 1; i<list.length; i++){
			newlist[i] = list[i];
		}
		newlist[list.length] = num;
		//setting up children and parent indices
		int ind = list.length;
		int parent = ind/2;
		//switch child and parent if out of order
		while(ind != 1 && num < newlist[parent]){
			int temp = newlist[parent];
			newlist[parent] = num;
			newlist[ind] = temp;
			ind = ind/2;
			parent = parent/2;
		}
		return newlist;
	}
	//Main function to test each function
	public static void main(String[] args){
		int[] test = new int[10];
		Random randomGenerator = new Random();
		for(int i = 0; i<test.length; i++){
			int rand = randomGenerator.nextInt(100);
			test[i] = rand;
		}
		printList(test);
		test = heapify(test);
		printList(test);
		test = add(test, 17);
		printList(test);
		test = heapify(test);
		printList(test);
		test = remove(test);
		printList(test);
		int top = minval(test);
		System.out.println(top);
		
	}
	
	public static int[] removeZero(int[] list) {
		int[] newlist = new int[list.length - 1];
		for (int i = 1; i < list.length; i++) {
			newlist[i-1] = list[i];
		}
		return newlist;
	}
	/*
	 * The remove function will get rid of the root and then move the last node in the heap array to the place of the
	 * root and then percolate this number down until each child and parent has the correct placement
	 */
	public static int[] remove(int[] list){
		list[1] = list[list.length-1];
		int[] newlist = new int[list.length-1];
		for(int i = 1; i<newlist.length; i++){
			newlist[i] = list[i];
		}
		//setting up indices of children and parents
		int ind = 1;
		int num = list[1];
		int child1 = ind*2;
		int child2 = ind*2 + 1;
		//while the number being percolated can go further down the heap aka there are more children below it
		while(ind*2 < newlist.length){
			//check to see if children are less than parent
			if (num < newlist[child1] && num < newlist[child2]){
				break;
			}
			//switch if needed
			if (newlist[child1] > newlist[child2]){
				int temp = newlist[child2];
				newlist[child2] = num;
				newlist[ind] = temp;
				child1 = child1*2;
				child2 = child1 + 1;
				ind = ind*2 + 1;
			}
			else if (newlist[child1] < newlist[child2]){
				int temp = newlist[child1];
				newlist[child1] = num;
				newlist[ind] = temp;
				child1 = child1*2;
				child2 = child1 + 1;
				ind = ind*2;
			}

		}
		return newlist;
		
	}
	
	public static int minval(int[] heap){ //returns the root
		return heap[1];
	}
	
	public static void printList(int[] list){
		for(int i = 0; i<list.length; i++){
			System.out.print(list[i]);
			System.out.print(' ');
		}
		System.out.println("");
	}
}
