public class LinkedQueue {
	
	private int size;
	private Node head = new Node();
	
	public LinkedQueue() {
		this.size = 0;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public void add(Object item) {
		
		Node new_node = new Node(item);
		new_node.setNext(null);
		
		if(isEmpty()) {
			head.setNext(new_node);
			head.setPrev(new_node);
		}
		else {
			if(size == 1) {
				Node tmp1 = head.getNext();
				tmp1.setNext(new_node);   
				tmp1.setPrev(new_node);
				new_node.setNext(tmp1);
				new_node.setPrev(tmp1);
			}
			else {
			Node tmp1= head.getNext(); //ù���
			Node tmp2= tmp1.getPrev(); //���������
			tmp1.setPrev(new_node); //ù���� prev�� ����忡 ����
			tmp2.setNext(new_node);    //����������� next�� ����忡 ����
			new_node.setPrev(tmp2); // ������� prev�� ���������Ϳ���
			new_node.setNext(tmp1); //������� next�� ù���� ����
			}
			
		}
		
		size++;
	}
	
	public Object peek() { //�Ǿտ��ִ� ���fornt�� �����ִ� �ڰϤ�
		if(isEmpty()) {
			throw new IllegalStateException("the queue is empty");
		}
		else {
			return head.getNext();
		}
	}
	
	//front�� ť���� �����Ѵ�
	public Object remove() {
		
		Object item = peek();
		if(size == 1) {
			head.setNext(null);
			head.setPrev(null);
			size--;
			return item;
		}
		else {
		Node tmp = head.getNext(); 
		Node tmp2 = tmp.getNext(); 	
		Node tmp3 = tmp.getPrev(); 
		tmp3.setNext(tmp2); 
		head.setNext(tmp2); 
		tmp2.setPrev(tmp3);  
		size--;
		return item;
		}
		
	}
	
	public int size() {
		return size;
	}
	
	public Node getHead() {
		return this.head;
	}
	
	
}
