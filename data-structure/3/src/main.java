import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArraySet set= new ArraySet();
		int number;
		String content;
		while(true) {
		System.out.println("1. �߰�\n2. ����\n3. ���� ���\n4. ������ ���\n5. �ش� ������ �ִ��� Ȯ��\n6. ����");
		Scanner sc= new Scanner(System.in);
		number = sc.nextInt();
		sc.nextLine();
		if(number==1) {                     
			System.out.println("�߰��ϰ� ���� ���� :");
			content = sc.next();
			if(set.add(content)) {
				System.out.println("���������� �߰� �Ǿ����ϴ�");
			}else {
				System.out.println("������ �ߺ� �Ǿ����ϴ�");
			}
		}else if(number== 2) {
			System.out.println("�����ϰ� ���� ����");
			content = sc.next();
			set.remove(content);
		}else if(number==3) {
			print(set);
		}else if(number ==4) {
			System.out.println(set.size());
		}else if(number==5) {
			System.out.println("Ȯ���ϰ� ���� ���� :");
			content = sc.next();
			if(set.contains(content)) {
				System.out.println("�ش� ������ �����մϴ�");
			}else {
				System.out.println("�ش� ������ �������� �ʽ��ϴ�");
			}
		}else if(number == 6) {
			System.out.println("����Ǿ����ϴ�");
			break;
		}else {
			System.out.println("���� �ɼ��Դϴ� �ٽ��Է��Ͻʽÿ�");
		}
		}
	}

	public static void print(ArraySet set) {                 //������ �����ȯ�Լ�
		System.out.println("\n ���� �ȿ� ����ִ� �����Դϴ�");
		System.out.print(set.getFrist());
		for(int i=1; i<set.size(); i++) {
			System.out.print("," + set.getNext());
		}
		System.out.println();
		
	}
}
