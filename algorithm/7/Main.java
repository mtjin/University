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
		int i = 0;
		int size = 0;
		String data1[] = new String[100];
		int data2[] = new int[100];
		//���� �о�ͼ� �������迭�� ����
		try {
			File file1 = new File("Data_updated.txt");
			FileReader filereader = new FileReader(file1);
			BufferedReader bufReader = new BufferedReader(filereader);

			while ((line = bufReader.readLine()) != null) {
				StringTokenizer str = new StringTokenizer(line,"	"); 
				int count = str.countTokens();
				while(str.hasMoreTokens()) {
					data1[i] = str.nextToken();
					data2[i] = Integer.parseInt(str.nextToken());
					size++;
				}
				//data1[i] = Integer.parseInt(line);
				i++;
			}

		} catch (IOException e) {
			System.out.println(e);
		}
		
		
		UnionFind union = new UnionFind();
		
		
		Node tmp1 = new Node(1);	//1~5���� ���� �����͸� ������ �� ����� ���
		Node tmp2 = new Node(2);
		Node tmp3 = new Node(3);
		Node tmp4 = new Node(4);
		Node tmp5 = new Node(5);
		union.makeSet(tmp1);
		union.makeSet(tmp2);
		union.makeSet(tmp3);
		union.makeSet(tmp4);
		union.makeSet(tmp5);
		
		Node totalNode = new Node();	//�о�� ��带 �����ϴµ� ����� ���(LinkedList �ڷᱸ��ó�� ����Ұ�
		Node head = totalNode;	//totalNode�� ����� �� �ʱ�ȭ�Ҷ� ����� ��� (ó���� ����Ű�� �����Ϳ���)
		
		//�о�� id�� �����͸� ���� ��带 ������ makeSet �� totalNode�� �Ѳ����� ����
		for(i=0; i< size; i++) {
			Node tmp = new Node(data1[i], data2[i]);
			union.makeSet(tmp);
			totalNode.next = tmp;
			totalNode = totalNode.next;
		}
		
		//�ʱ�ȭ
		totalNode = head;
		
		//���� Union1
		while(totalNode.next != null) {
			if (totalNode.next.data == 1) {
				union.unions(totalNode.next, tmp1);
			}
			else if(totalNode.next.data == 2) {
				union.unions(totalNode.next, tmp2);
			}
			else if(totalNode.next.data == 3) {
				union.unions(totalNode.next, tmp3);
			}
			else if(totalNode.next.data == 4) {
				union.unions(totalNode.next, tmp4);
			}
			else if(totalNode.next.data == 5) {
				union.unions(totalNode.next, tmp5);
			}
			totalNode = totalNode.next;
		}
	
		totalNode =head;	
		
		//���� ���(���� ���� �����ͳ����� union�� ���)   <����1>
		try {
			System.out.println("================================================");
			System.out.println("START Write File");
			BufferedWriter out1 = new BufferedWriter(new FileWriter("Output1.txt"));
		
			out1.write("Below is  Output1 \n");
			out1.newLine();
		
			while(totalNode.next != null) {
				out1.write(totalNode.next.id +"	"+ totalNode.next.parent.id);
				totalNode= totalNode.next;
				out1.newLine();
			}
			out1.close();
			totalNode = head;
		} catch (IOException e) {
			System.err.println(e); // ������ �ִٸ� �޽��� ���
			System.exit(1);
		}
		
		//��� Tree�� ���Ͽ� ���� (������ 1,2,3,4,5 ���� Ʈ������ ���ʷδ�� union) <union2>
		union.unions(tmp2, tmp1);
		union.unions(tmp3, tmp2);
		union.unions(tmp4, tmp3);
		union.unions(tmp5, tmp4);
		
		//�������(���� �ٸ������͸� ���� Ʈ������ ��� union�� ���) <union2>
		try {
			System.out.println("================================================");
			System.out.println("START Write File2");
			BufferedWriter out1 = new BufferedWriter(new FileWriter("Output2.txt"));
		
			out1.write("Below is  Output2 \n");
			out1.newLine();
		
			while(totalNode.next != null) {
				out1.write(totalNode.next.id +"	"+ totalNode.next.parent.id);
				totalNode= totalNode.next;
				out1.newLine();
			}
			out1.close();
		} catch (IOException e) {
			System.err.println(e); // ������ �ִٸ� �޽��� ���
			System.exit(1);
		}
	}
}
