import java.util.Scanner;

public class TestNodeSet {
	public void run() {
		int number;
		String content;
		NodeSet Node_Set= new NodeSet();
		while(true) {
			System.out.println("1. �߰�\n2. ����\n3. Ȯ��\n4. �������ִ���Ȯ��\n5. ����\n6. ����");
			Scanner sc= new Scanner(System.in);
			number = sc.nextInt();
			//
			if(number == 1)
			{                     
				System.out.println("�߰��� ������");
				content = sc.next();
				Node_Set.add(content);  //������ �߰���
			}
			else if(number == 2) 
			{
				System.out.println("������ ������");
				content = sc.next();
				Node_Set.remove(content);
			}
			else if(number == 3) 
			{
				System.out.println("���� ����� �����ʹ�" + Node_Set.checkNode());
				System.out.println("��" + Node_Set.size() +"���� ��尡 �����մϴ�");
				Node_Set.checkNode();
			}
			else if(number == 4) 
			{
				System.out.println("Ȯ���ϰ� ���� ������ :");
				content = sc.next();
				if(Node_Set.contains(content)) {
					System.out.println("�ش� ������ �����մϴ�");
				}else {
					System.out.println("�ش� ������ �������� �ʽ��ϴ�");
				}
			}
			else if(number == 5) {
				System.out.println("�����Լ��Դϴ�\n�ٲܳ�� i�� �Է����ּ���");
				number = sc.nextInt();  // swap �Լ� �Ű����� int���̴ϱ� int ������ ����
				int i= number;
				System.out.println("�ٲܳ�� j�� �Է����ּ���");
				number = sc.nextInt(); 
				int j= number;
				if(Node_Set.swap(i,j)) {
					System.out.println(i+"������"+j+"������ ��ū�� swap�Ǿ����ϴ�");
				}else {
					System.out.println("��ȣ���� ���� ���� �Է��߽��ϴ�");
				}
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
