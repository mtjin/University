import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		String line = ""; 
		int i = 0, j = 0;
		int size = 0;
		StringBuffer result; //LCS Print��� ������ ����
		String data1[] = new String[10];  //���ڿ� ����
		int data2[] = new int[10];		   //���ڱ��� ����
		//���� �о�ͼ� �������迭�� ����
		try {
			File file1 = new File("LCS_Data.txt");
			FileReader filereader = new FileReader(file1);
			BufferedReader bufReader = new BufferedReader(filereader);
			
			//���� �о�� ����
			while ((line = bufReader.readLine()) != null) {
					if((size%2) == 1) {
						data1[i] = line;  //���ڿ� ����
						i++;
					}
					if((size%2) == 0) {
						data2[j] = Integer.parseInt(line);  //���� ����
						j++;
					}
					size++;
				}

		} catch (IOException e) {
			System.out.println(e);
		}
		
		
		
		LCS lcs = new LCS();
		//LCS ��ŷ
		Arr[][] arr = lcs.LCS_length(data1[0], data1[1]);
		//LCS ��ȸ
		result = lcs.LCS_print(arr , data1[0], data2[0], data2[1]);
		
		
		//���� ���
		try {
			
			System.out.println("\n================================================");
			System.out.println("START Write File");
			BufferedWriter out1 = new BufferedWriter(new FileWriter("Output.txt"));
		
			out1.write("Below is  Output of LCS \n");
			out1.newLine();
			out1.write(result.toString());
			out1.close();
		} catch (IOException e) {
			System.err.println(e); // ������ �ִٸ� �޽��� ���
			System.exit(1);
		}
		
	
		
		
	}
}
