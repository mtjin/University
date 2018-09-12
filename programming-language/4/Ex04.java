//201404377_���¾�
import ast.IntNode;
import ast.ListNode;
import ast.Node;
import compile.TreeFactory;
public class Ex04 {

	
			public static int max(Node node) {  
				//�ִ밪�� �����ϵ��� �ۼ�  
				//value�� next �� �� ū ���� ����
	    
				if(node instanceof IntNode)   //IntNode�� ���   (instanceof �� �̿��ؼ� ����ȯ�� �����԰� ���ÿ� IntNode���� ListNode���� ���� ������)
				{               
					IntNode int_node = (IntNode)node;  //IntNode�� ����ȯ ����
					
					if(int_node.getNext() == null) 
					{          //IntNode�� ���� ����Ű�� ��尡������(next) �ܸ�����̹Ƿ� ���� ��ȯ��Ű��ȴ�.             
						return (int_node).value;
					}
					else    //���� ��尡 ����Ű�°� ������ (null�� �ƴҋ�)
					{
						if(max(int_node.getNext()) > (int_node).value) { //��͸� �̿��ؼ� ����ؼ� �������� �����尪�� ���ϰ���, ��Ϳ��� �� ū ���� ������ �� ������ ������ָ�� 
							return max(int_node.getNext());
						}
						else                 //���� �ݴ�
						{
							return (int_node).value;
						}
					}
				}
				else if(node instanceof ListNode)   //ListNode�� ���
				{   
					ListNode list_node = (ListNode)node;   //ListNode�� ����ȯ ����
					
					if(list_node.getNext() == null)        //������尡(next) ���� ��� value�� Ȯ���ϸ�Ǵϱ� �ؿ� ó�� max�� value�� �Ѱ��� 
					{
						return max((list_node).value);
					}
					else {             //�������(next)�� �ִ� ���
						if(max(list_node.getNext()) > max((list_node).value)) //ListNode�� value�� next��� ��带 ���� �� �����Ƿ� �ΰ��� ��ͷ� �����ְ� next��ͷ� �����Ϳ� �� ū ���� �ִٸ� �� ��͸� ��ȯ���ش�.
						{
							return max(list_node.getNext());
						}
						else           //���� �ݴ�
						{
							return max((list_node).value);   
						}
					}
					
				}
			   
				return 0; //����
			}
			
			
			public static int sum(Node node) {  //�Ǻ���ġ����ó��
				//��� value�� ������ ��ȯ
				//value�� next�� �� ���� �����ϸ�� 
			
				if(node instanceof IntNode)    //IntNode �� ���
				{
					IntNode int_node = (IntNode)node; //����ȯ����
					
					if(int_node.getNext() == null)   //���� ��尡������ 
					{
						return int_node.value;  //ListNode���� next�� null�̸� ���� ���̹Ƿ� �װ��� ���� ��ȯ���ָ��.
					}
					else    //���� ����Ű�� ��尡 ���� ���
					{
						return int_node.value + sum(int_node.getNext());  
					}
				}
				else if(node instanceof ListNode)  //ListNode��  ���
				{  
					ListNode list_node = (ListNode)node;  //����ȯ����
					
					if(list_node.getNext() == null)    //���� ��尡 ������
					{
						return sum(list_node.value);  //ListNode�� value�� ��ͷ� �����ָ��
					}
					else    //���� ��尡 ������
					{
						return sum(list_node.value) + sum(list_node.getNext());  //�����ſ��ٰ� ������带��ͷε����� �����ָ��
					}
					
				}
				
				return 0; 	
			}
			public static void main(String...args) {   
					Node node = TreeFactory.createtTree("( ( 3 ( ( 10 ) ) 6 ) 4 1 ( ) -2 ( ) )"); 
					//��� ���
					Node node2 = TreeFactory.createtTree("(3 ( 5 2 3) -378 )" );
					System.out.println("( ( 3 ( ( 10 ) ) 6 ) 4 1 ( ) -2 ( ) ) �� ���");
					System.out.println("�ִ밪: " + max(node));  //�ִ밪
					System.out.println("IntNode���� ����: " + sum(node));  //��
					//�� �ٸ� ����
					System.out.println("\n�ٸ� ��� : (3 ( 5 2 3) -378 ) �� ���");
					System.out.println("�ִ밪: " + max(node2));
					System.out.println("IntNode���� ����: " + sum(node2)); 
			
			}
}