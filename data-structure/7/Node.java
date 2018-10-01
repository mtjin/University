
public class Node {

	private Object data;
	private Node prev; //���߸�ũ�帮��Ʈ prev
	private Node next;
	
	public Node() {
		
	}

	public Node(Object data) {
		this.data = data;
	}
	
	public Object getData() {
		return this.data;
	}
	
	public Node getNext() {
		return this.next;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
	
	//���߸���Ʈ �޼��� �߰�
	public void setPrev(Node prev) {
		this.prev = prev;
	}
	
	public Node getPrev() {
		return this.prev;
	}
	
	
	public Node(Object object, Node prev, Node next) {
		this.data = object;
		this.prev = prev;
		this.next = next;
	}
	
	public String toString() {
		return data+" ";
	}
}
