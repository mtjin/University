import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int number;
		IntNodeStack node_stack= new IntNodeStack();
		while(true) {
			System.out.println("1. ���ÿ� ���� �߰�\n2. ���ÿ� ���������� ���� ���� ����\n3. ���ÿ� ó�� ���� ���� ����\n4. ������ ���\n5. ���� ���\n6. ����");
			Scanner sc= new Scanner(System.in);
			number = sc.nextInt();
			//
			if(number == 1)
			{                     
				System.out.println("�߰��ϰ� ���� ���� �Է�");
				
				int n = sc.nextInt();
				node_stack.push(n);  //������ �߰���
			}
			else if(number == 2) 
			{	
				
				System.out.println(node_stack.pop()+"�����Ǿ����ϴ�");
			}
			else if(number == 3) 
			{
				System.out.println(node_stack.popBottom()+"�����Ǿ����ϴ�");
			}
			else if(number == 4) 
			{
				System.out.println("�������"+node_stack.size()+"�Դϴ�");
			}
			else if(number == 5) {
				System.out.println("�������� �����ʹ�"+node_stack.checkNode()+"\n��"+node_stack.size()+"���� ��尡 �����մϴ�");
			}
			else if(number == 6)
			{
				System.out.println("����Ǿ����ϴ�");
				break;
			}
			else {
				System.out.println("���� �ɼ��Դϴ� �ٽ��Է��Ͻʽÿ�");
			}
		}
	}
		

	}


