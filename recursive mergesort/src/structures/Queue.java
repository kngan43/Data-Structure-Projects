package structures;

import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	private int size = 0;
	private Node<T> head;
	private Node<T> tail;
	
	public Queue() {		
    }
	
	public Queue(Queue<T> other) {
		Node<T> current = other.head;
		while(current != null){
			this.enqueue(current.getData());
			current = current.getNext();
		}
	}
	
	@Override
	public boolean isEmpty() {
            return size == 0;
	}

	@Override
	public int size() {
            return size;
	}

	@Override
	public void enqueue(T element) {
		if(isEmpty()){
            head = new Node<T>(element);
            tail = head;
            size++;
		}
		else {
			tail.setNext(new Node<T>(element));
			tail = tail.getNext();
			size++;
		}
	}

	@Override
	public T dequeue() throws NoSuchElementException {
            if(isEmpty())
            	throw new NoSuchElementException();
            T temp = head.getData();
            head = head.getNext();
            size--;
            return temp;
            
	}

	@Override
	public T peek() throws NoSuchElementException {
            if(isEmpty())
            	throw new NoSuchElementException();
            return head.getData();
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		LinkedStack<T> s = new LinkedStack<T>();
		Queue<T> q = new Queue<T>(this);
		while (!q.isEmpty()){
			s.push(q.dequeue());
		}
		while (!s.isEmpty()){
			q.enqueue(s.pop());
		}
		return q;
	}
	
}
