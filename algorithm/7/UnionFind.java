
public class UnionFind {
	
	public void makeSet(Node x) {	//����� �θ� �ڽ����� ��
		x.parent = x;
	}

	
	public Node findSet(Node x) {	//�ش� ����� �ֻ��� �θ� ã��
		if(x.parent == x) {
			return x;
		}
		else {
			return findSet(x.parent);
		}
	}
	
	public void unions(Node x, Node y) {  //�ѳ���� �ֻ�������� �θ� �ٸ� ����� �ֻ������� �̾���
		findSet(y).parent = findSet(x);
	}
}
