import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = ""; // 0�� 100��txt 1�� 1000��txt 2�� 10000��txt
		String line2 = "";
		int i = 0;
		
		int Merge_input100[] = new int[100];
		int Merge_input1000[] = new int[1000];
		
		int Merge_input100_divided_4[] = new int[100];
		int Merge_input1000_divided_4[] = new int[1000];
		
		int Merge_input100_divided_16[] = new int[100];
		int Merge_input1000_divided_16[] = new int[1000];
		
		int Quick_input100[] = new int[100];
		int Quick_input1000[] = new int[1000];
		
		int RandomQucik_input100[] = new int[100];
		int RandomQucik_input1000[] = new int[1000];

		try {
			File file_100 = new File("test_100.txt");
			File file_1000 = new File("test_1000.txt");
			FileReader filereader = new FileReader(file_100);
			BufferedReader bufReader = new BufferedReader(filereader);
			FileReader filereader2 = new FileReader(file_1000);
			BufferedReader bufReader2 = new BufferedReader(filereader2);

			while ((line = bufReader.readLine()) != null) {
				// System.out.println(line);

				Merge_input100[i] = Integer.parseInt(line);
				Merge_input100_divided_4[i] = Integer.parseInt(line);
				Merge_input100_divided_16[i] = Integer.parseInt(line);
				Quick_input100[i] = Integer.parseInt(line);
				RandomQucik_input100[i++] = Integer.parseInt(line);
				// size++; // �о�� ����
			}
			i = 0;
			while ((line2 = bufReader2.readLine()) != null) {
				// System.out.println(line);
				Merge_input1000[i] = Integer.parseInt(line2);
				Merge_input1000_divided_4[i] = Integer.parseInt(line2);
				Merge_input1000_divided_16[i] = Integer.parseInt(line2);
				Quick_input1000[i] = Integer.parseInt(line2);
				RandomQucik_input1000[i++] = Integer.parseInt(line2);
				// size2++; // �о�� ����
			}

		} catch (IOException e) {
			System.out.println(e);
		}

		Quick_Sort quick = new Quick_Sort();
		Merge_Sort merge = new Merge_Sort();
		
		// �Ϲ����� merge sort(��������: ������ �������ĵ� ��ü��)
		long start_time = System.nanoTime();
		System.out.println("start 100 Merge sort");
		merge.sort(Merge_input100, 0, Merge_input100.length - 1);
		System.out.println("start 1000 Merge sort");
		merge.sort(Merge_input1000, 0, Merge_input1000.length - 1);
		
		long end_time = System.nanoTime(); // ����Ʈ(�Ϲ�) ����ð�
		long time_difference = end_time - start_time; // ����Ʈ(�Ϲ�) �ɸ��ð�
		System.out.println("�Ϲ����� merge sort �ɸ� �ð�:" + time_difference);
		
		// �ɰ��� �ִ� ����:4��  merge sort
		start_time = System.nanoTime();
		System.out.println("start 100 Merge sort(�ִ� �ɰ��� ����: 4��)");
		merge.divided_sort(Merge_input100_divided_4, 0, Merge_input100_divided_4.length - 1, 4);
		System.out.println("start 1000 Merge sort(�ִ� �ɰ��� ����: 4��)");
		merge.divided_sort(Merge_input1000_divided_4, 0, Merge_input1000_divided_4.length - 1, 4);
		
		end_time = System.nanoTime(); // ����Ʈ(�Ϲ�) ����ð�
		time_difference = end_time - start_time; // ����Ʈ(�Ϲ�) �ɸ��ð�
		System.out.println("�ɰ��� �ִ� ����:4��  merge sort �ɸ� �ð�:" + time_difference);
		
		// �ɰ��� �ִ� ���� :16��  merge sort 
		start_time = System.nanoTime();
		System.out.println("start 100 Merge sort(�ִ� �ɰ��� ���� : 16��)");
		merge.divided_sort(Merge_input100_divided_16, 0, Merge_input100_divided_16.length - 1, 16);
		System.out.println("start 1000 Merge sort(�ִ� �ɰ��� ����: 16 ��)");
		merge.divided_sort(Merge_input1000_divided_16, 0, Merge_input1000_divided_16.length - 1, 16);
		
		end_time = System.nanoTime(); // ����Ʈ(�Ϲ�) ����ð�
		time_difference = end_time - start_time; // ����Ʈ(�Ϲ�) �ɸ��ð�
		System.out.println("�ɰ��� �ִ� ���� :16��  merge sort  �ɸ� �ð�:" + time_difference);
		
		// �Ϲ����� quick sort
		start_time = System.nanoTime(); // ���� ���۽ð�
		System.out.println("start 100 Quick sort");
		quick.sort(Quick_input100, 0, Quick_input100.length - 1);
		System.out.println("start 1000 Quick sort");
		quick.sort(Quick_input1000, 0, Quick_input1000.length - 1);

		end_time = System.nanoTime(); // ���� ����ð�
		time_difference = end_time - start_time; // ���� �ɸ��ð�
		System.out.println("����Ʈ pivot�� �� ������������ �� ��� �����ϴµ� �ɸ� �ð�:" + time_difference);

		// Random pivot quick sort
		start_time = System.nanoTime(); // ���� ���۽ð�
		System.out.println("start 100 Random pivot Quick sort");
		quick.sort(RandomQucik_input100, 0, RandomQucik_input100.length - 1);
		System.out.println("start 1000 Random pivot Quick sort");
		quick.sort(RandomQucik_input1000, 0, RandomQucik_input1000.length - 1);
		end_time = System.nanoTime(); // ���� ����ð�
		time_difference = end_time - start_time; // ���� �ɸ��ð�
		System.out.println("����Ʈ pivot�� 4��° ������ �� ��� �����ϴµ� �ɸ��ð�:" + time_difference);
		
		//���� ���
		try {
			BufferedWriter out_100 = new BufferedWriter(new FileWriter("result100.txt"));
			BufferedWriter out_1000 = new BufferedWriter(new FileWriter("result1000.txt"));

			// merge sort ���
			out_100.write("Below is Merge_Sort 100 result\n");
			for (i = 0; i < Merge_input100.length; i++) {
				out_100.write(Merge_input100[i] + "");
				out_100.newLine();
			}
			out_1000.write("Below is Merge_Sort 1000 result\n");
			for (i = 0; i < Merge_input1000.length; i++) {
				out_1000.write(Merge_input1000[i] + "");
				out_1000.newLine();
			}
			
			out_100.write("--------------------------------------------------\n");
			out_1000.write("--------------------------------------------------\\n");
			
			// merge sort(�ִ� �ɰ��� ����: 4��) ���
			out_100.write("Below is Merge_Sort 100_4 result(�ִ� �ɰ��� ����: 4��)\n");
			for (i = 0; i < Merge_input100_divided_4.length; i++) {
				out_100.write(Merge_input100_divided_4[i] + "");
				out_100.newLine();
			}
			out_1000.write("Below is Merge_Sort 1000_4 result(�ִ� �ɰ��� ����: 4��)\n");
			for (i = 0; i < Merge_input1000_divided_4.length; i++) {
				out_1000.write(Merge_input1000_divided_4[i] + "");
				out_1000.newLine();
			}

			out_100.write("--------------------------------------------------\n");
			out_1000.write("--------------------------------------------------\\n");
			
			// merge sort(�ִ� �ɰ��� ����: 16��) ���
			out_100.write("Below is Merge_Sort 100_16 result(�ִ� �ɰ��� ����: 16��)\n");
			for (i = 0; i < Merge_input100_divided_16.length; i++) {
				out_100.write(Merge_input100_divided_16[i] + "");
				out_100.newLine();
			}
			out_1000.write("Below is Merge_Sort 1000_16 result(�ִ� �ɰ��� ����: 16��)\n");
			for (i = 0; i < Merge_input1000_divided_16.length; i++) {
				out_1000.write(Merge_input1000_divided_16[i] + "");
				out_1000.newLine();
			}
			
			out_100.write("--------------------------------------------------\n");
			out_1000.write("--------------------------------------------------\\n");
			
			// quick sort ���
			out_100.write("Below is Qucik_Sort 100result\n");
			for (i = 0; i < Quick_input100.length; i++) {
				out_100.write(Quick_input100[i] + "");
				out_100.newLine();
			}
			out_1000.write("Below is Qucik_Sort 1000 result\n");
			for (i = 0; i < Quick_input1000.length; i++) {
				out_1000.write(Quick_input1000[i] + "");
				out_1000.newLine();
			}

			out_100.write("--------------------------------------------------\n");
			out_1000.write("--------------------------------------------------\\n");
			
			// random quick sort ���
			out_100.write("Below is Random pivot Qucik_Sort 100 result\n");
			for (i = 0; i < RandomQucik_input100.length; i++) {
				out_100.write(RandomQucik_input100[i] + "");
				out_100.newLine();
			}
			out_1000.write("Below is Random pivot Qucik_Sort 1000 result\n");
			for (i = 0; i < RandomQucik_input1000.length; i++) {
				out_1000.write(RandomQucik_input1000[i] + "");
				out_1000.newLine();
			}

			out_100.close();
			out_1000.close();

		} catch (IOException e) {
			System.err.println(e); // ������ �ִٸ� �޽��� ���
			System.exit(1);
		}

	}

}
