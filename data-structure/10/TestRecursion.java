import java.util.Scanner;

public class TestRecursion {

	// TODO Auto-generated method stub
			public void run() {
				
				int number;
				Quicksort quick= new Quicksort(100);
				while(true) {
					System.out.println("1.[Recursive] add\n2.[Recursive] sort\n3.[Recursive] print \n4. ����");
					Scanner sc= new Scanner(System.in);
					number = sc.nextInt();
					//
					if(number == 1)
					{                     
						System.out.println("�߰��ϰ� ���� ������ �Է��ϼ��� :");
						
						int n = sc.nextInt();
						quick.add(n);  //������ �߰���
						System.out.println("["+n+"]�� �߰� �Ǿ����ϴ�.");
					}
					else if(number == 2) 
					{	
						quick.sorting();
					}
					else if(number == 3) 
					{
						for(int i=0; i<quick.size(); i++) {
							if(i==0) {
								System.out.print("["+quick.getFirst()+"]");
							}else {
								System.out.print("["+quick.getNext()+"]");
							}
							
						}
						System.out.println();
					}
					else if(number == 4) 
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
