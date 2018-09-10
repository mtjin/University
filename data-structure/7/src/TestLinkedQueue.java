import java.util.Scanner;

public class TestLinkedQueue {

	public void run() {
		
		int number;
		LinkedQueue queue= new LinkedQueue();
		while(true) {
			System.out.println("1.[Queue] ���� �߰�\n2.[Queue] ���� N�� ����\n3.[Queue] ���� �ϳ� ���� \n4.[Queue] ������ ���\n5.[Queue] ���� ���\n6.[Queue] ù���� �� ���\n7.����");
			Scanner sc= new Scanner(System.in);
			number = sc.nextInt();
			//
			if(number == 1)
			{                     
				System.out.println("�߰��ϰ� ���� ����");
				
				String n = sc.next();
				queue.add(n);  //������ �߰���
				System.out.println("["+n+"]�� �߰� �Ǿ����ϴ�.");
			}
			else if(number == 2) 
			{	
				System.out.println("�����ϰ� ���� ����:");
				int n = sc.nextInt();
				for(int i = 0; i < n; i++ ) {
					  System.out.println("["+queue.peek()+"]�������Ǿ����ϴ�");
					  queue.remove();//������ŭ ����
				}
			}
			else if(number == 3) 
			{
				System.out.println("["+queue.peek()+"]�������Ǿ����ϴ�");
				queue.remove();
			}
			else if(number == 4) 
			{
				System.out.println("�������"+queue.size()+"�Դϴ�");
			}
			else if(number == 5) {
				Node tmp = queue.getHead();
			
				for(int i=0; i<queue.size(); i++) {
					System.out.print(tmp.getNext());
					tmp.setNext(tmp.getNext().getNext());
				}
				System.out.println();
			}
			else if(number == 6) {
				System.out.println(queue.peek());
			}
			else if(number == 7)
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
