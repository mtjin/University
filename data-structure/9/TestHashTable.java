import java.util.Scanner;

public class TestHashTable {

	HashTable hashtable= new HashTable(100, 0.75f);
	public void run() {
		while(true) {
			System.out.println("1. [Hash] put\n2. [Hash] get\n3. ����");
			Scanner scan = new Scanner(System.in);
			Scanner scan2 = new Scanner(System.in);
			Scanner scan3 = new Scanner(System.in);
			int choice = scan.nextInt();
			if(choice == 1) {
				System.out.println("key�� �Է��ϼ���:");
				Object key = scan.next();
				System.out.println("������ �Է��ϼ���:");
				String name = scan2.nextLine();
				System.out.println("�� �Է��ϼ���:");
				String language = scan3.nextLine();
				Country country= new Country( name , language);
				hashtable.put(key, country);
			}
			else if(choice == 2) {
				System.out.println("key�� �Է��ϼ���:");
				scan.nextLine();
				String key = scan.nextLine();
				Country tmp = (Country) hashtable.get(key);
				System.out.println("("+tmp.name+", "+tmp.language+")" );
			}
			else if(choice == 3) {
				System.out.println("����Ǿ����ϴ�");
				break;
			}
			else {
				System.out.println("���� �ɼ��Դϴ� �ٽ��Է��ϼ���");
			}
			
		}
	}
}
