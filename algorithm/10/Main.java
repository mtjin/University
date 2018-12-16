
import java.beans.beancontext.BeanContextChildSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.tools.DiagnosticCollector;



public class Main {
	public static void main(String[] args) {
		String line1 = "";
		String line2 = "";
		int i = 0, j = 0;
		int size = 0;
		int size2 = 0;
		int vertexNum1 = 0; // ���ڿ� ����
		int vertexNum2 = 0;
		
		
		int weight = 0;
		final int INF = Integer.MAX_VALUE; // ���Ѵ�

		int[][] graphMatrix1 = null; // ����������� �迭
		int[][] graphMatrix2 = null;

		// ���� �о�ͼ� �������迭�� ����
		try {
			File file1 = new File("graph_sample_prim_kruskal.txt");
			File file2 = new File("graph_sample_dijkstra.txt");
			FileReader filereader1 = new FileReader(file1);
			FileReader filereader2 = new FileReader(file2);
			BufferedReader bufReader1 = new BufferedReader(filereader1);
			BufferedReader bufReader2 = new BufferedReader(filereader2);
			// �Է� �ؽ�Ʈ ���Ͽ��� ���� �о�� ����
			line1 = bufReader1.readLine(); // ���� �о��
			line2 = bufReader2.readLine(); // ���� �о��

			vertexNum1 = Integer.parseInt(line1); // vertex ��������
			vertexNum2 = Integer.parseInt(line2); // vertex ��������

			graphMatrix1 = new int[vertexNum1][vertexNum1]; // ������� ����
			graphMatrix2 = new int[vertexNum2][vertexNum2];

			// �ʱ�ȭ(�ڱ��ڽ��� 0, �������� ���Ѵ�� �ʱ�ȭ��, ���Ѵ�� ����ġ�� ���ٴ°� ���� �� ������ �ȵ��ִ�.
			for (i = 0; i < vertexNum1; i++) {
				for (j = 0; j < vertexNum1; j++) {
					if (i == j) {
						graphMatrix1[i][j] = 0;
					} else {
						graphMatrix1[i][j] = INF;
					}
				}
			}
			i = 0;
			j = 0;
			for (i = 0; i < vertexNum2; i++) {
				for (j = 0; j < vertexNum2; j++) {
					if (i == j) {
						graphMatrix2[i][j] = 0;
					} else {
						graphMatrix2[i][j] = 0;
					}
				}
			}
			i = 0;
			j = 0;

			// �Է� �ؽ�Ʈ ���Ͽ��� �� �о�� ����
			while ((line1 = bufReader1.readLine()) != null) {

				StringTokenizer str = new StringTokenizer(line1, " ");
				i = Integer.parseInt(str.nextToken());
				j = Integer.parseInt(str.nextToken());
				weight = Integer.parseInt(str.nextToken());
				graphMatrix1[i][j] = weight;
				graphMatrix1[j][i] = weight;

				size++;
				i = 0;
				j = 0;
				weight = 0;
			}

			// �Է� �ؽ�Ʈ ���Ͽ��� �� �о�� ����
			while ((line2 = bufReader2.readLine()) != null) {

				StringTokenizer str = new StringTokenizer(line2, " ");
				i = Integer.parseInt(str.nextToken());
				j = Integer.parseInt(str.nextToken());
				weight = Integer.parseInt(str.nextToken());
				graphMatrix2[i][j] = weight;
				graphMatrix2[j][i] = weight;

				size2++;
				i = 0;
				j = 0;
				weight = 0;
			}

		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("===========PRIM==============\n");
		Prim prim = new Prim(vertexNum1);
		prim.run(graphMatrix1, vertexNum1);
		
		System.out.println("===========DIJKSTRA===========\n");
		
		Dijkstra d = new Dijkstra();
		d.run(graphMatrix2, vertexNum2);
		
	}
}
