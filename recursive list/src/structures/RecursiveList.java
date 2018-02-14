package structures;

import java.util.Iterator;

public class RecursiveList<T extends Comparable<T>> implements ListInterface<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	public RecursiveList(){};
	@Override
	public Iterator<T> iterator() {
		return new RecursiveListIterator<T>(head);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ListInterface<T> insertFirst(T elem){
		if(elem == null)
			throw new NullPointerException();
		head = new Node<T>(elem, head);
		if(tail==null)
			tail = head;
		size++;
		return this;
	}

	@Override
	public ListInterface<T> insertLast(T elem) {
		if(elem == null)
			throw new NullPointerException();
		if(isEmpty()){
			tail = new Node<T>(elem);
			head = tail;
		}
		else {
			tail.setNext(new Node<T>(elem));
			tail = tail.getNext();
		}
		size++;
		return this;
	}

	@Override
	public ListInterface<T> insertAt(int index, T elem){
		if(elem == null)
			throw new NullPointerException();
		
		if(index<0 || index>size() && !isEmpty())
			throw new IndexOutOfBoundsException();
		
		if(index == 0 ){
			insertFirst(elem);
			return this;
		}
		else if(index == size){
			insertLast(elem);
			return this;
		}
		Node<T> previousNode = findAt(head,index, 0);
		previousNode.setNext(new Node<T>(elem, previousNode.getNext()));
		size++;
		return this;
	}


	
	private Node<T> findCurrent(Node<T> curr, int index, int count){
		if(count == index)
			return curr;
		return findCurrent(curr.getNext(),index, ++count);
	}
	
	private Node<T> findAt(Node<T> curr, int index, int count){
		if(curr==null)
			throw new IllegalStateException();
		if(count+1==index){
			return curr;
		}
		return findAt(curr.getNext(),index,++count);
	}

	@Override
	public T removeFirst() throws IllegalStateException {
		if(isEmpty())
			throw new IllegalStateException();
		T temp = head.getData();
		if(size==1)
			head = tail = null;
		else{
			head = head.getNext();		
		}
		size--;
		return temp;
	}

	@Override
	public T removeLast() throws IllegalStateException{
		if(isEmpty())
			throw new IllegalStateException();
		T temp = tail.getData();
		if(size==1)
			head = tail = null;
		else{
			tail = findAt(head,size-1,0);
			tail.setNext(null);
		}
		size--;
		return temp;
	}

	@Override
	public T removeAt(int i) throws IndexOutOfBoundsException{
		if(i<0 ||i>=size())
			throw new IndexOutOfBoundsException();
		if(i==size-1)
			return removeLast();
		else if(i==0)
			return removeFirst();
		else{
			Node<T> previousNode = findAt(head,i,0);
			T temp = previousNode.getNext().getData();
			previousNode.setNext(previousNode.getNext().getNext());
			size--;
		return temp;
		}
	}

	@Override
	public T getFirst() throws IllegalStateException{
		if(isEmpty())
			throw new IllegalStateException();
		return head.getData();
	}

	@Override
	public T getLast() throws IllegalStateException{
		if(isEmpty())
			throw new IllegalStateException();
		return tail.getData();
	}

	@Override
	public T get(int i) throws IndexOutOfBoundsException{
		if(i<0 || i>=size())
			throw new IndexOutOfBoundsException();
		return getIndex(head,i,0);
	}
	private T getIndex(Node<T> curr,int index, int count){
		if(curr == null)
			throw new IllegalStateException();
		if(count==index){
			return curr.getData();
		}
		return getIndex(curr.getNext(),index,++count);
	}

	@Override
	public boolean remove (T elem) {
		if(elem == null)
			throw new NullPointerException();
		boolean isRemoved = true;
		Node<T> previousNode = findElement(head,elem,0,-1);
		if(elem == head.getData()){
			removeFirst();
		}	
		else if(previousNode == null)
				isRemoved = false;
			else{
				if(previousNode.getNext()==tail)
					tail = previousNode;
				previousNode.setNext(previousNode.getNext().getNext());
				size--;
			}
					
		return isRemoved;
	}
		
	private Node<T> findElement(Node<T> curr,T elem,int count,int index){
		if(curr == null)
			return null;
		if(elem.compareTo(curr.getData())==0){
			index = count;
			return curr;
				}
		if (curr.getNext()!= null && elem.compareTo(curr.getNext().getData())==0){
			index = count+1;
			return curr;
			}
		return findElement(curr.getNext(),elem, ++count, index);
	}
	@Override
	public int indexOf(T elem) {
		if(elem == null)
			throw new NullPointerException();
		
		return findIndexOf(head,elem,0);
	}
	private int findIndexOf(Node<T> curr, T elem, int count){
		if(curr == null)
			return -1;
		if(curr.getData().compareTo(elem)==0)
			return count;
		return findIndexOf(curr.getNext(),elem,++count);
	}
	@Override
	public boolean isEmpty() {
		return size==0;
	}

}
