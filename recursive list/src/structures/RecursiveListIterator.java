package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecursiveListIterator<T> implements Iterator<T> {
	Node<T> curr;
	
	public RecursiveListIterator(){};
	public RecursiveListIterator(Node<T> node){
		curr = node;
		}
	@Override
	public boolean hasNext() throws NoSuchElementException{
		return curr!= null;
	}

	@Override
	public T next() {
		if(hasNext()){
			T temp = curr.getData();
			curr = curr.getNext();
			return temp;
		}
		else throw new NoSuchElementException();
	}
}
