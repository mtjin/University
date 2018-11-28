import java.util.LinkedList;
import java.util.Queue;

public class DFS {
	final int INF = Integer.MAX_VALUE;
	int time;
	
	public void DFSrun(int[][] graphMatrix, Vertex[] vertex) {
		time = 0;	
		for(int i = 0; i <vertex.length; i++) {
			if(vertex[i].color.equals("WHITE")) {
				visit(graphMatrix, vertex[i], vertex);
			}
		}
	}
	
	public void visit(int[][] graphMatrix, Vertex u, Vertex[] vertex) {
		time = time + 1; //ó�� �����ð��� 1���ͽ����ϰ� 1��������
		u.distance = time;
		u.color = "GRAY";
		Queue<Integer> q = new LinkedList<Integer>();
		int row = u.id;
		for(int column = 0 ; column < vertex.length; column++) { //��� vertex�� ��(��������)
			
			if(graphMatrix[row][column] != INF && graphMatrix[row][column] != 0) {	//���� Ž������ �ʰ� ������ ��� ť�� ������ vertex ����
				q.offer(column);	
			}
		}
		
		for(int i : q) {	//ť�� �־��� vertex��  visit ���
			Vertex v = vertex[i];
			if(v.color.equals("WHITE")) {
				v.parent = u;
				visit(graphMatrix , v , vertex);
			}
		}
		
		//Ž���Ϸ��� vertex���� black���� ���� �� �������� �ð�(�Ϸ�ð�) ����
		u.color = "BLACK";	
		time = time + 1;
		u.f = time;
	}
	
	boolean isAdj(Vertex u, Vertex v, int[][] graphMatrix) {
		int tmp = graphMatrix[u.id][v.id];
		if(tmp == 0) {  // 0�̸�(�����ƴѰ��) false��ȯ
			return false;
		}
		else {
			return true;
		}
	}
}
