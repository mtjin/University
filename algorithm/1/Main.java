import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String line ="";  // 0�� 100��txt 1�� 1000��txt 2�� 10000��txt
		String line2 ="";
		String line3 ="";
		int i =0;
		int size = 0; //�ؽ�Ʈ���Ͽ��� �о�� ���� ����(ó���� ����� �ϳ��̿��ؼ� �ʿ������� 3���ѹ��� �ϴ°ɷ� �ٲ㼭 �ʿ������
		int size2 = 0;
		int size3 = 0;
		int input[] = new int[100]; 
		int input2[] = new int[1000];
		int input3[] = new int[10000];
		DoubleLinked list100_1 = new DoubleLinked();
		DoubleLinked list100_2 = new DoubleLinked();
		DoubleLinked list100_3 = new DoubleLinked();
		DoubleLinked list1000_1 = new DoubleLinked();
		DoubleLinked list1000_2 = new DoubleLinked();
		DoubleLinked list1000_3 = new DoubleLinked();
		DoubleLinked list10000_1 = new DoubleLinked();
		DoubleLinked list10000_2 = new DoubleLinked();
		DoubleLinked list10000_3 = new DoubleLinked();
		
		InsertionSort insertion = new InsertionSort();
		BubbleSort bubble = new BubbleSort();
		SelectionSort selection = new SelectionSort();
		
		//�����Է�
		try {
			File file_100 = new File("test_100.txt");
			File file_1000 = new File("test_1000.txt");
			File file_10000 = new File("test_10000.txt");
			FileReader filereader = new FileReader(file_100);
			BufferedReader bufReader = new BufferedReader(filereader);
			FileReader filereader2 = new FileReader(file_1000);
			BufferedReader bufReader2 = new BufferedReader(filereader2);
			FileReader filereader3 = new FileReader(file_10000);
			BufferedReader bufReader3 = new BufferedReader(filereader3);
			
			while((line = bufReader.readLine()) != null) {
				//System.out.println(line);
				input[i++] = Integer.parseInt(line);
				size++; //�о�� ����
			}
			i=0;
			while((line2 = bufReader2.readLine()) != null) {
				//System.out.println(line);
				input2[i++] = Integer.parseInt(line2);
				size2++; //�о�� ����
			}
			i=0;
			while((line3 = bufReader3.readLine()) != null) {
				//System.out.println(line);
				input3[i++] = Integer.parseInt(line3);
				size3++; //�о�� ����
			}
	
		}catch(IOException e) {
			System.out.println(e);
		}
		for(i=0; i<size; i++) {
			list100_1.addLast(input[i]);
			list100_2.addLast(input[i]);
			list100_3.addLast(input[i]);
		}
		for(i=0; i<size2; i++) {
			list1000_1.addLast(input2[i]);
			list1000_2.addLast(input2[i]);
			list1000_3.addLast(input2[i]);
		}
		for(i=0; i<size3; i++) {
			list10000_1.addLast(input3[i]);
			list10000_2.addLast(input3[i]);
			list10000_3.addLast(input3[i]);
		}
		
		//���Ľ���
		System.out.println("start 100 sort");
		insertion.sort(list100_1);
		bubble.sort(list100_2);
		selection.sort(list100_3);
		
		System.out.println("start 1000 sort");
		insertion.sort(list1000_1);
		bubble.sort(list1000_2);
		selection.sort(list1000_3);
		
		System.out.println("start 10000 sort");
		insertion.sort(list10000_1);
		bubble.sort(list10000_2);
		selection.sort(list10000_3);
		
		//���ĵ� ���Ḯ��Ʈ �̿��ؼ� ���
		try {
			BufferedWriter out_100 = new BufferedWriter(new FileWriter("result100.txt"));
			BufferedWriter out_1000 = new BufferedWriter(new FileWriter("result1000.txt"));
			BufferedWriter out_10000 = new BufferedWriter(new FileWriter("result10000.txt"));
			
			out_100.write("InsertionSort result------------------\n");
			out_100.write(list100_1.printAll());
			out_100.newLine();
			out_100.write("BubbleSort result---------------------\n");
			out_100.write(list100_2.printAll());
			out_100.newLine();
			out_100.write("SelectionSort result------------------\n");
			out_100.write(list100_3.printAll());
			out_100.newLine();
			out_100.close();
			
			out_1000.write("InsertionSort result------------------\n");
			out_1000.write(list1000_1.printAll());
			out_1000.newLine();
			out_1000.write("BubbleSort result---------------------\n");
			out_1000.write(list1000_2.printAll());
			out_1000.newLine();
			out_1000.write("SelectionSort result------------------\n");
			out_1000.write(list1000_3.printAll());
			out_1000.newLine();
			out_1000.close();
			
			out_10000.write("InsertionSort result------------------\n");
			out_10000.write(list10000_1.printAll());
			out_10000.newLine();
			out_10000.write("BubbleSort result---------------------\n");
			out_10000.write(list10000_2.printAll());
			out_10000.newLine();
			out_10000.write("SelectionSort result------------------\n");
			out_10000.write(list10000_3.printAll());
			out_10000.newLine();
			out_10000.close();
	
		}catch(IOException e) {
			System.err.println(e); // ������ �ִٸ� �޽��� ���
	        System.exit(1);
		}
	}
		
	

}
