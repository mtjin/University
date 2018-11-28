
public class Vertex {
	
	final int INF = Integer.MAX_VALUE;
	int id;
    String color;
    int distance;	//�����ѽð�(Ž���ϴµ� �ɸ��ð�)
    int f; //�������� �ð�(DFS���� ����)
    Vertex parent;
    
	Vertex(){
		color = "WHITE";
		distance = INF;
		parent = null;
	}
	
	Vertex(int id){
		this.id = id;
		color = "WHITE";
		distance = INF;
		parent = null;
	}
	
	public void init() {	//vertex �ʱ�ȭ
		color = "WHITE";
		distance = INF;
		parent = null;
	}
	
    
    
}
