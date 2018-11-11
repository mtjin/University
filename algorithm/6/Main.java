import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		String line = ""; 
		int i = 0;
		int data1[] = new int[100];
		int data2[] = new int[100];
		int data3[] = new int[100];
		//���� �о�ͼ� �������迭�� ����
		try {
			File file1 = new File("Data1.txt");
			File file2 = new File("Data2.txt");
			File file3 = new File("Data3.txt");
			FileReader filereader = new FileReader(file1);
			BufferedReader bufReader = new BufferedReader(filereader);
			FileReader filereader2 = new FileReader(file2);
			BufferedReader bufReader2 = new BufferedReader(filereader2);
			FileReader filereader3 = new FileReader(file3);
			BufferedReader bufReader3 = new BufferedReader(filereader3);

			while ((line = bufReader.readLine()) != null) {
			
				data1[i] = Integer.parseInt(line);
				i++;
			}
			i = 0;
			while ((line = bufReader2.readLine()) != null) {
			
				data2[i] = Integer.parseInt(line);
				i++;
			}
			i=0;
			while ((line = bufReader3.readLine()) != null) {
				data3[i] = Integer.parseInt(line);
				i++;
			}
			i = 0;

		} catch (IOException e) {
			System.out.println(e);
		}
		
	
		HashTable hash1 = new HashTable();  //linear probing hash(��������)
		HashTable hash2 = new HashTable(); // Quadratic probing hash(����������)
		HashTable hash3 = new HashTable(); //Double Hashing(���� �ؽ�)
		
		//����
		System.out.println("================================================");
		System.out.println("start insert");
		for(i=0; i<data1.length; i++) { 
			hash1.linearInsert(data1[i]);
			hash2.quadraticInsert(data1[i]);
			hash3.doubleInsert(data1[i]);
		}
		
		//����
		System.out.println("================================================");
		System.out.println("start delete");
		for(i=0; i<data2.length; i++) {
			hash1.linearDelete(data2[i]);
			hash2.quadraticDelete(data2[i]);
			hash3.doubleDelete(data2[i]);
		}
		
		//���� ���
		try {
			System.out.println("================================================");
			System.out.println("START Write File");
			BufferedWriter out1 = new BufferedWriter(new FileWriter("linear_result.txt"));
			BufferedWriter out2 = new BufferedWriter(new FileWriter("quadratic.txt"));
			BufferedWriter out3 = new BufferedWriter(new FileWriter("Double.txt"));

		
			out1.write("Below is lineary insert search result\n");
			out1.newLine();
			out1.write("������   �ε���");
			out1.newLine();
			for (i = 0; i < data3.length; i++) {
				if(hash1.linearSearch(data3[i]) != 0) {
				out1.write(data3[i] + "   "+ hash1.linearSearch(data3[i]));
				out1.newLine();
				}
			}
			out1.write("�浹Ƚ��: "+ hash1.getCollison());
			
			out2.write("Below is quadratic search result\n");
			out2.newLine();
			out2.write("������   �ε���");
			out2.newLine();
			for (i = 0; i < data3.length; i++) {
				if(hash2.quadraticSearch(data3[i]) != 0) {
					out2.write(data3[i] + "   "+ hash2.quadraticSearch(data3[i]));
					out2.newLine();
				}
			}
			out2.write("�浹Ƚ��: "+ hash2.getCollison());
			
			out3.write("Below is double hashing search result\n");
			out3.newLine();
			out3.write("������   �ε���");
			out3.newLine();
			for (i = 0; i < data3.length; i++) {
				if(hash3.doubleSearch(data3[i]) != 0) {
					out3.write(data3[i] + "   "+ hash3.doubleSearch(data3[i]));
					out3.newLine();
				}
			}
			out3.write("�浹Ƚ��: "+ hash3.getCollison());

			out1.close();
			out2.close();
			out3.close();

		} catch (IOException e) {
			System.err.println(e); // ������ �ִٸ� �޽��� ���
			System.exit(1);
		}

	}

	
}
