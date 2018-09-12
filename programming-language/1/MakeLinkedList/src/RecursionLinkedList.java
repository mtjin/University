//201404377���¾�
public class RecursionLinkedList {
	private Node head;
	private static char UNDEF=Character.MIN_VALUE;
	/**
	 * ���Ӱ� ������ ��带 ����Ʈ�� ó������ ����
	 */
	private void linkFirst(char element) {
		head= new Node(element, head);
	}
	/**
	 * ����(1) �־��� Node x�� ���������ο���� Node�� �������� ���Ӱ� ������ ��带 ����
	 *@param element 
	 *          ������
	 *@param x
	 *          ���
	 */
	private void linkLast(char element, Node x) {
		if(x.next== null) {
			Node new_node= new Node(element, null);
			x.next= new_node;  //���� ���ҷ� ���� �ڵ� �ۼ�
			}
		else {
			linkLast(element, x.next); //���� ��� �湮 recursion �ڵ� �ۼ� 
			}
	}
	/**
	 * ���� Node�� ���� Node �� ���Ӱ� ������ ��带 ����
	 * 
	 * @param element
	 *            ����
	 * @param pred
	 *           �������
	 */
	private void linkNext(char element, Node pred) {
		Node next =pred.next;
		pred.next= new Node(element, next);
	}
	/**
	 * ����Ʈ�� ù���� ���� ����(����)
	 * 
	 * @return ù��° ���� ������	  
	 */
	private char unlinkFirst() {
		Node x = head;
		char element= x.item;
		head= head.next;
		x.item= UNDEF;
		x.next= null;
		return element;
		}
	/**
	 * ���� Node�� ���� Node ���� ����(����)
	 * 
	 * @param pred
	 *          �������
	 * @return ��������� ������
	 */
	private char unlinkNext(Node pred) {
		Node x= pred.next;
		Node next= x.next;
		char element= x.item;
		x.item= UNDEF;
		x.next = null;
		pred.next= next;
		return element;
	}
	/**
	 * ����(2) x��忡�� index��ŭ ������ Node ��ȯ
	 */
	private Node node(int index, Node x) {
		if(index==0) return x;
		else return node(--index, x.next);// ä���� ���, index�� �ٿ����鼭 ���� ��� �湮 �ڵ� �ۼ�
		}
		
		
	/**
	 * ����(3) ���κ��� �������� ����Ʈ�� ��� ���� ��ȯ
	 */
	private int length(Node x) { 
		if(x==null)return 0;   //��尡 null�� ��� 0�� ��ȯ
		if(x.next != null) {
			return 1+length(x.next);  //ä���� ���, recursion ���
		}else {
			return 1;                  //��尡 1��
		}
	}
	/**
	 * ����(4) ���κ��� �����ϴ� ����Ʈ�� ���� ��ȯ
	 */
	private String toString(Node x) { 
		if(x.next!= null) {
		return x.item+" "+ toString(x.next);  //ä���� ���, recursion ���
		}else {
			return ""+x.item;      
		}
	}
	/**
	 * ���� ����� ���� ������ ����Ʈ�� �������� �Ųٷ� ����
	 * ex)��尡 [s]->[t]->[r]�� ��, reverse ���� �� [r]->[t]->[s]
	 * @param x
	 *           ����  ���
	 * @param pred
	 *           �������� ���� ���
	 */
	private void reverse(Node x, Node pred) {
		if(x != null) {
			Node temp= pred;
			pred= x;
			x= x.next;
			pred.next = temp;
			head= pred;
			reverse(x,pred);   //ä���� ���, recursion ���
			}
		}
	/**
	 * ���Ҹ� ����Ʈ�� �������� �߰�
	 */
	public boolean add(char element) {
		if(head==null) {
			linkFirst(element);
		}else {
			linkLast(element, head);
		}
		return true;
	}
	/**
	 * ���Ҹ� �־��� index ��ġ�� �߰�
	 * 
	 * @param index
	 *            ����Ʈ���� �߰��� ��ġ
	 * @praram element
	 *            �߰��� ������
	 */
	public void add(int index, char element) {
		if(!(index >=0 && index<= size()))
			throw new IndexOutOfBoundsException(""+ index);
		
		if(index== 0)
			linkFirst(element);
		else
			linkNext(element, node(index-1, head));
	}
	/**
	 * ����Ʈ���� index ��ġ�� ���� ��ȯ
	 */
	public char get(int index) {
		if(!(index>=0 && index< size()))
			throw new IndexOutOfBoundsException(""+index);
		
		return node(index, head).item;
	}
	/**
	 * ����Ʈ���� index ��ġ�� ���� ����
	 */
	public char remove(int index) {
		if(!(index >= 0 && index< size()))
			throw new IndexOutOfBoundsException(""+index);
		if(index ==0) {
			return unlinkFirst();
		}
		return unlinkNext(node(index-1, head));
	}
	/**
	 * ����Ʈ�� �Ųٷ� ����
	 */
	public void reverse() {
		reverse(head,null);
	}
	/**
	 * ����Ʈ�� ���� ���� ��ȯ
	 */
	public int size() {
		return length(head);
	}
	@Override
	public String toString() {
		if(head== null)
			return "[]";
		
		return "[" + toString(head)+ "]";
	}
	/**
	 * ����Ʈ���� ���� �ڷᱸ��
	 */
	private static class Node{
		public String element;
		char item;
		Node next;
		
		Node(char element, Node next){
			this.item= element;
			this.next= next;
		}
	}
}
