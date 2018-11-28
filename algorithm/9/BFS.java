import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	final int INF = Integer.MAX_VALUE;
	
	void BFSrun(int[][] G, Vertex[] vertex, int startVertex) {
		vertex[startVertex].color = "GRAY"; //���� vertex �� ����
		vertex[startVertex].parent = null;
		vertex[startVertex].distance = 0;
		
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(vertex[startVertex]);  //�����ε��� ť�� �־���
		while(!q.isEmpty()) {	//ť�� �������� �ݺ�
			Vertex u = q.poll();	//u�� ť���� ���°� ����
			for(int column = 0 ; column < vertex.length; column++) {	//ť���� ���� vertex�� ������ vertex�� �� ���� �ϰ� ť�� �־���(��� vertex
				Vertex v = vertex[column];	
				if(isAdj(u, v, G) && v.color.equals("WHITE") && (v.distance == INF)) {	//�����ϰ� ���� ����̰�(����Ž������) Ž����������(INF) vertex�� ���
					v.color = "GRAY";	//ȸ������ ����
					v.distance = u.distance + 1;	//Ž���ð� ����
					v.parent = u;	//���� ���鳢�� ��������
					q.offer(v);	//���� Ž���� vertex�� ť�� ����
				}
			}
			
			u.color ="BLACK"; //�Ϸ��� vertex �� black���� ����
		}
	}
	
	boolean isAdj(Vertex u, Vertex v, int[][] graphMatrix) {
		int tmp = graphMatrix[u.id][v.id];	//v�� u vertex�� �����ϸ� 1, �ƴϸ� 0
		if(tmp == 0) {  //���� �ƴ� ��� false��ȯ
			return false;
		}
		else {		//���� vertex�� ��� true ��ȯ
			return true;	
		}
	}
}
