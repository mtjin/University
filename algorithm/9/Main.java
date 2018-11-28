import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		String line = "";
		int i = 0, j = 0;
		int size = 0;
		int vertexNum; // ���ڿ� ����
		int startVertex; // ���� vertex
		Vertex[] vertex = null; // vertex�迭
		int[][] graphMatrix = null; // ����������� �迭

		// ���� �о�ͼ� �������迭�� ����
		try {
			File file1 = new File("graph.txt");
			FileReader filereader = new FileReader(file1);
			BufferedReader bufReader = new BufferedReader(filereader);

			// �Է� �ؽ�Ʈ ���Ͽ��� ���� �о�� ����
			line = bufReader.readLine(); // ���� �о��
			vertexNum = Integer.parseInt(line); // vertex ��������
			graphMatrix = new int[vertexNum][vertexNum]; // ������� ����
			vertex = new Vertex[vertexNum]; // vertex ����

			// �Է� �ؽ�Ʈ ���Ͽ��� ������� �� �о�� ����
			while ((line = bufReader.readLine()) != null) {

				vertex[i] = new Vertex(i); // �о�� ��������� ���� vertex�� id���� ���� ������

				StringTokenizer str = new StringTokenizer(line, " ");
				while (str.hasMoreTokens()) {
					graphMatrix[i][j] = Integer.parseInt(str.nextToken());
					j++;
				}
				size++;
				i++;
				j = 0;
			}

		} catch (IOException e) {
			System.out.println(e);
		}

		i = 0;

		// BFS ����
		BFS bfs = new BFS();
		System.out.println("BFS�� ���� vertex�� �Է��Ͻÿ� (������  vertexũ�⺸�� �۰��Է��Ͻÿ�)");
		Scanner sc = new Scanner(System.in);
		startVertex = sc.nextInt();

		bfs.BFSrun(graphMatrix, vertex, startVertex); // �� ������ �Ű������� Ž���� ������ vertex�� ��������, 0�����Ͽ���.
		for (i = 0; i < vertex.length; i++) {
			System.out.println("id��:" + vertex[i].id + "  Ž��Ƚ��: " + vertex[i].distance);
		}

		// BFS ��� ���� ���
		try {

			System.out.println("================================================");
			BufferedWriter out1 = new BufferedWriter(new FileWriter("Output(BFS).txt"));

			out1.write("Vertex_ID | Searching_Time\n");
			out1.newLine();
			for (i = 0; i < vertex.length; i++) {
				out1.write("" + vertex[i].id + "         |     " + vertex[i].distance);
				out1.newLine();
			}
			out1.close();
		} catch (IOException e) {
			System.err.println(e); // ������ �ִٸ� �޽��� ���
			System.exit(1);
		}

		// BFS���� ����� vertex �⺻������ �ʱ�ȭ
		for (i = 0; i < vertex.length; i++) {
			vertex[i].init();
		}

		// DFS ����
		DFS dfs = new DFS();
		dfs.DFSrun(graphMatrix, vertex);
		System.out.println("DFS ���( DFS�� 0���� Ž�� ����)");
		for (i = 0; i < vertex.length; i++) {
			System.out.println("id��:" + vertex[i].id + " �����ð�: " + vertex[i].distance + " �������� �ð�: " + vertex[i].f);
		}

		// BFS ��� ���� ���
		try {

			System.out.println("================================================");
			BufferedWriter out1 = new BufferedWriter(new FileWriter("Output(DFS).txt"));

			out1.write("Vertex_ID | �����ð� | �������½ð�\n");
			out1.newLine();
			for (i = 0; i < vertex.length; i++) {
				out1.write(" " + vertex[i].id + "        |     " + vertex[i].distance + "  |   " + vertex[i].f);
				out1.newLine();
			}
			out1.close();
		} catch (IOException e) {
			System.err.println(e); // ������ �ִٸ� �޽��� ���
			System.exit(1);
		}

	}
}
