
public class BinarySearchTree {
	int inordered_data[] = new int[100]; // ������ȸ�� ��� ���� �迭
	int num = 0;
	TreeNode root;

	public TreeNode getRoot() {
		return root;
	}

	public void insert(int data) {
		if (root == null) { // ��Ʈ��尡 null�̸� ��Ʈ��忡 �ش簪���� �������ش�.
			root = new TreeNode(data);
		} else { // ���� ��Ʈ��尡 ������� ������ �� ó���ִ°��� �ƴ� ���
			TreeNode newNode = new TreeNode(data);
			insertKey(this.root, newNode);
		}
	}


	// Ʈ���� ������ ����
	public void insertKey(TreeNode root, TreeNode newNode) {
		TreeNode parent = null; // x�� �θ��� ��Ȱ��
		TreeNode x = root; // root���� �޾ƿ�
		while (x != null) {
			parent = x;
			if (newNode.data < x.data) { // ���λ����ҷ��� ��尡 ���� �񱳳�� ���� ���� ���(ó�� �񱳳��� ��Ʈ���)
				x = x.left; // �񱳳�带 �����ڽĳ��ιٲ�
			} else {
				x = x.right; // �񱳳�带 ������ �ڽĳ��� �ٲ�
			}
		}
		newNode.parent = parent; // �����ϴ� ����� �θ� parent���� ����

		// ������ �񱳱��� �� ����(�� ���������� ��) ���ؼ� �ش�񱳳���� ���ʿ����� �ڽĳ�忡 newNode ����
		if (newNode.data < parent.data) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}

	}

	// ��� Ž��
	public TreeNode recursive_search(int k) {
		return recursive_search2(root, k);
	}

	public TreeNode recursive_search2(TreeNode x, int k) {
		if (x == null || k == x.data) { //�ش� ���� ���� ��带 ã���� �� �ش��� ��ȯ
			return x;
		}
		if (k < x.data) { //x�� ���ϴ� ��庸�� �۰ų� Ŭ �� �˸°� ��͸� ������.
			return recursive_search2(x.left, k);
		} else {
			return recursive_search2(x.right, k);
		}
	}

	// �ݺ��� Ž��
	public TreeNode iterative_search(int k) {
		return iterative_search2(root, k);
	}

	public TreeNode iterative_search2(TreeNode x, int k) { 
		//�ش� ���� ���� ��尡 ���� �� ���� �ݺ����� ������
		while (x != null && k != x.data) {
			if (k < x.data) { //���س�� ���� ã������ ���� ������ ���س�带 �����ڽ����� ü����
				x = x.left;
			} else {    //���س�� ���� ũ�� ���س�带 �����ڽ����� ü����
				x = x.right;
			}
		}
		return x;
	}

	// main���� getRoot�ؼ� ��Ʈ�ް� ���ϴ� ��� ��� �־��ָ��
	// ������(������ ����Ʈ���� �ּڰ�)
	public TreeNode sucessor(TreeNode x) {
		
		if (x.right != null) {  //���� �ش����� ������ �ڽ��� �־���Ѵ�(��Ʈ�� �����ʼ���Ʈ�� �� ���� ���� ���� ã�°��̱� ����)
			return treeMinimum(x.right);  //���� ���� ���� ��ȯ���ش�.
		}
		//������ �ڽ��� null�� ���
		TreeNode y = x.parent;
		while (y != null && x == y.right) {  
			x = y;
			y = y.parent;
		}
		return y;
	}

	// �ڽ� ����Ʈ���� ���� ���� ��� ��ȯ
	public TreeNode treeMinimum(TreeNode x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}

	// ������(���� ����Ʈ���� �ִ�)
	public TreeNode prodecessor(TreeNode x) {
		if (x.left != null) { //�����ڽĳ�尡 �ִ��� Ȯ��
			return treeMaximum(x.left);  //�ִ��� ���� ��带 ��ȯ��
		}
		//���� �ڽ��� null�� ���
		TreeNode y = x.parent;
		while (y != null && x == y.left) { 
			x = y;
			y = y.parent;
		}
		return y;
	}

	// �ڽļ���Ʈ���� ���� ū �� ��ȯ
	public TreeNode treeMaximum(TreeNode x) {
		while (x.right != null) {
			x = x.right;
		}
		return x;
	}

	public void delete(TreeNode root, TreeNode removed) {
		if (removed.left == null ) { //������� �ϴ� ��尡 �ڽ��� ���� ��
			transplant(root, removed, removed.right); // ������� �ϴ� ��带 �����Ѵ�
		} else if (removed.right == null) { // ������� �ϴ� ��尡 �ϳ��� �ڽ��� ���� ��
			transplant(root, removed, removed.left); // ������� �ϴ� ����� �ڽ��� ������� �ϴ� ����� ��ġ�� �̵��Ѵ�.
		} else { // ������� �ϴ� ��尡 ���� �ڽ��� ���� ��
			TreeNode y = treeMinimum(removed.right);
			if (y.parent != removed) { //������� �ϴ� ����� ���Ӱ� ��ü�Ѵ�.
				transplant(root, y, y.right);
				y.right = removed.right;
				y.right.parent = y;
			}
			transplant(root, removed, y); //���� �ٸ� ������ �����Ͽ� ������� �ϴ� ��带 �����.
			y.left = removed.left;
			y.left.parent = y;
		}

	}

	//��带 �����ϰ� �ڸ��̵������ش�.
	public void transplant(TreeNode root, TreeNode u, TreeNode v) {
		if (u.parent == null) { 
			root = v;  
		} else if (u == u.parent.left) {
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}
		if (v != null) {
			v.parent = u.parent;
		}
	}

	public int[] inorder() {
		inorder(this.root);
		return inordered_data;
	}

	public void inorder(TreeNode root) {
		if (root != null) {
			inorder(root.left);
			System.out.println(root.data);
			inordered_data[num++] = root.data;
			inorder(root.right);
		}
	}

}