package structures;

public class Node<T> {
	private Node<T> nextNode;
	private T data;
	
	public Node(){};
	public Node(T element){
		data = element;
		}
	public Node(T element, Node<T> nextNode){
		data = element; 
		this.nextNode = nextNode;
		}
	
	public Node<T> getNext(){return nextNode;}
	public T getData(){return data;}
	
	public void setNext(Node<T> node){nextNode = node;}
	public void setData(T data){this.data = data;}
}
