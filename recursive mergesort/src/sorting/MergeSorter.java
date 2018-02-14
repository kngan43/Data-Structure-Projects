package sorting;

import structures.Queue;

public class MergeSorter<T extends Comparable<T>> {
	
	/**
	 * Returns a new queue containing the elements from the input queue in sorted order.
	 * 
	 * @param queue an input queue
	 * @return a sorted copy of the input queue
	 */
	public Queue<T> mergeSort(Queue<T> queue) {
		Queue<T> newQueue = new Queue<T>(queue);
		if(newQueue.size()<=1) 
			return newQueue;
		Queue<T> out1 = new Queue<T>();
		Queue<T> out2 = new Queue<T>();
		divide(newQueue,out1,out2);
		out1 = mergeSort(out1);
		out2 = mergeSort(out2);
		return merge(out1,out2);
	}

	/**
	 * Places elements from the input queue into the output queues, roughly
	 * half and half.
	 * 
	 *   
	 * @param input a queue
	 * @param output1 a queue into which about half of the elements in input should go
	 * @param output2 a queue into which the other half of the elements in input should go
	 */
	void divide(Queue<T> input, Queue<T> output1, Queue<T> output2) {
		if(input.size() == 1)
			output1.enqueue(input.dequeue());
		if(input.isEmpty()){
			return;
		}
		output1.enqueue(input.dequeue());
		output2.enqueue(input.dequeue());
        divide(input,output1,output2);	
	}
	
	/**
	 * Merges sorted input queues into an output queue in sorted order,
	 * and returns that queue. 
	 * 
	 *  
	 * @param input1 a sorted queue
	 * @param input2 a sorted queue
	 * @return a sorted queue consisting of all elements from input1 and input2
	 */
	Queue<T> merge(Queue<T> input1, Queue<T> input2) {
		Queue<T> output = new Queue<T>();
		mergeHelper(input1, input2, output);
        	return output;
	}
	
	/**
	 * Merges the sorted input queues into the output queue in sorted order.
	 * 
	 *     
	 * @param input1 a sorted queue
	 * @param input2 a sorted queue
	 * @param output a sorted queue containing the accumulated progress so far
	 */
	void mergeHelper(Queue<T> input1, Queue<T> input2, Queue<T> output) {
		if(input1.isEmpty() && input2.isEmpty())
			return;
		/*if(input1.size()>1 && input2.isEmpty())
			input2.enqueue(input1.dequeue());
		else if(input2.size()>1 && input1.isEmpty())
			input1.enqueue(input2.dequeue());
			*/
		if(input2.isEmpty())
			output.enqueue(input1.dequeue());	
		else if(input1.isEmpty())
			output.enqueue(input2.dequeue());
		else{
			if(input1.peek().compareTo(input2.peek())<0)
			     	output.enqueue(input1.dequeue());
			else
				output.enqueue(input2.dequeue());
		}
		mergeHelper(input1,input2, output);
	}
}
