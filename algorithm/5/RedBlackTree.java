
public class RedBlackTree {
	int inordered_data[] = new int[100]; // ������ȸ�� ��� ���� �迭
	int num = 0;

	private final int RED = 0;
	private final int BLACK = 1;

	// �ڽ��� ���� ���� �ڽ��� nil���� �Ѵ�
	private class Node {

		int data = -1;
		int color = BLACK;
		Node left;
		Node right;
		Node parent;

		Node(int data) {
			this.data = data;
			left = nil;
			right = nil;
			parent = nil;
		}
	}

	private final Node nil = new Node(-1);
	private Node root = nil;

	// ����
	public void insert(int data) {
		Node newNode = new Node(data); // ������ ��� ����
		insert(newNode);
	}

	private void insert(Node newNode) {
		Node parent = root; // ��Ʈ��尪�� �����س��(�����ҷ��� ����� �θ��� ����Ű�� �뵵�� ����)
		if (root == nil) { // ��Ʈ��尡 nil(�������)�� ���
			root = newNode; // ������ ��尡 ��Ʈ��尡��
			newNode.color = BLACK; // ��Ʈ����� ���� BLACK�̿�����
			newNode.parent = nil; // ��Ʈ����� �θ�� nil �̿����Ѵ�
		} else { // ó�� �����ϴ� ��尡 �ƴ� ���
			newNode.color = RED; // �ϴ� ������ ���� ���� RED�̿�����
			while (true) { // ��� ���ԿϷ� �� ������ �ݺ�
				if (newNode.data < parent.data) { // �����ҷ��� ��� ���� parent(�׻� newNode�� �θ���) ������ ���� ���
					if (parent.left == nil) { // �θ����� ���� �ڽ��� nil�� ��� �� �ڸ��� newNode����
						parent.left = newNode;
						newNode.parent = parent;
						break;
					} else { // nil�� �ƴ϶�� �θ��带 ���� �ڽĳ��� �ϰ� �ٽ� �ݺ����� ������
						parent = parent.left;
					}
				} else if (newNode.data >= parent.data) { // �����ҷ��� ��� ���� parent(�׻� newNode�� �θ���) ������ Ŭ ���
					if (parent.right == nil) { // �θ����� ���� �ڽ��� nil�� ��� �� �ڸ��� newNode����
						parent.right = newNode;
						newNode.parent = parent;
						break;
					} else { // nil�� �ƴ϶�� �θ��带 ������ �ڽĳ��� �ϰ� �ٽ� �ݺ����� ������
						parent = parent.right;
					}
				}
			}
			// ������ �� �� �����Ʈ�� Ư���� �°� �����ش�
			fixTree(newNode);
		}
	}

	// �����Ʈ�� Ư���°� fix
	private void fixTree(Node node) {
		while (node.parent.color == RED) { // �����ѳ�� �θ��� ���� RED�� ������ �ݺ�
			Node parentSilbing = nil; // �θ��� �������
			if (node.parent == node.parent.parent.left) { // �����ѳ���� �θ� �θ��Ǻθ�����(����Գ��) ���� �ڽĳ���� ���
				parentSilbing = node.parent.parent.right; // parentSilbing�� �����ѳ���� ����������

				if (parentSilbing != nil && parentSilbing.color == RED) { // parentSilbing�� nil�� �ƴϰ� RED�� ���(ppt��������ġ��
																			// case1�� ���)
					node.parent.color = BLACK; // �����ѳ��(���ϻ���) �θ��� ���� Black����
					parentSilbing.color = BLACK; // �θ�������� ���� Black����
					node.parent.parent.color = RED; // ����Գ�� ���� RED��
					node = node.parent.parent; // ������ ��带 ����� ���� ��
					continue;
				}
				if (node == node.parent.right) { // �����ѳ�尡 �θ����� ������ �ڽ��� ���(case2)-> case3���� ���� �ѹ��� �� �����ϰ� ȸ���ؾ���
					node = node.parent; // �����ѳ�带 �θ���� �����ϰ�
					rotateLeft(node); // ����ȸ��
				}
				// case3
				node.parent.color = BLACK; // �����ѳ�� �θ� Black����
				node.parent.parent.color = RED; // �����ѳ�� ���� Red��
				rotateRight(node.parent.parent);// �����ϳ�� ���� ������ȸ��
			} else { // �����ѳ���� �θ��尡 �������� ������ �ڽ��϶� (���� left right �ݴ�����ָ��)
				parentSilbing = node.parent.parent.left;
				if (parentSilbing != nil && parentSilbing.color == RED) {
					node.parent.color = BLACK;
					parentSilbing.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.left) {
					node = node.parent;
					rotateRight(node);
				}
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				rotateLeft(node.parent.parent);
			}
		}
		root.color = BLACK; // ��Ʈ������ Black����
	}

	// ����ȸ��
	void rotateLeft(Node node) {
		if (node.parent != nil) { // ����� �θ� nil����� ��(�� node.parent�� ��Ʈ����� ���)
			if (node == node.parent.left) { // ��尡 �θ��� ���� �ڽ��� ���
				node.parent.left = node.right; // �θ����� �����ڽĿ� �������� ������ �ڽ��� �̾��ش�.
			} else { // ������ �ڽ��� ���� �θ����� ������ �ڽĳ�忡 �������� �������ڽ��� �̾��ش�.
				node.parent.right = node.right;
			}
			node.right.parent = node.parent; // �θ����� �������ڽ��� �θ��忡 �θ��带 �ִ´�.
			node.parent = node.right; // �θ��带 �������� �������ڽĳ�����
			if (node.right.left != nil) { // �������ڽ��� �����ڽ� ��尡 nil�� ���
				node.right.left.parent = node; // ������ �ڽ��� �����ڽ��� �θ��忡 �����带 �ִ´�.
			}
			node.right = node.right.left; // ����� ������ �ڽĳ�忡 �������ڽ��� �����ڽ� ��带 �ִ´�.
			node.parent.left = node; // �θ����� ���� �ڽĿ� �����带 �ִ´�.
		} else {
			Node right = root.right; // �ӽ� ������ �ڽĳ�� right ����
			root.right = right.left; // ��Ʈ�� ������ �ڽĳ�忡 �ӽ÷� ������ right����� ���� �ڽĳ�带 �ִ´�
			right.left.parent = root; // ������ �ڽ��� ���� �ڽ��� �θ��忡 ��Ʈ��带 �ִ´�
			root.parent = right; // ��Ʈ����� �θ��忡 �ӽ������ߴ� right ��带 �ִ´�.
			right.left = root; // rigth����� ���� �ڽĿ� ��Ʈ��带 �ִ´�
			right.parent = nil; // ��Ʈ����� �θ� nil�� ���ش�.
			root = right; // ��Ʈ ���ň right�� ���ش�.
		}
	}

	void rotateRight(Node node) {
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.left;
			} else {
				node.parent.right = node.left;
			}

			node.left.parent = node.parent;
			node.parent = node.left;
			if (node.left.right != nil) {
				node.left.right.parent = node;
			}
			node.left = node.left.right;
			node.parent.right = node;
		} else {// Need to rotate root
			Node left = root.left;
			root.left = root.left.right;
			left.right.parent = root;
			root.parent = left;
			left.right = root;
			left.parent = nil;
			root = left;
		}
	}
	
	public Node search(int data) {
		Node searchNode = new Node(data);
		Node result = search2(searchNode, root);
		System.out.println("Find Node(RBT) :  " + result.data);
		return result; 
	}
	public Node search2(Node searchNode, Node root) {
        if (root == nil) { //��Ʈ��尡 nil�̸� null��ȯ
            return null;
        }

        if (searchNode.data < root.data) {  
            if (root.left != nil) {
                return search2(searchNode, root.left);
            }
        } else if (searchNode.data > root.data) {
            if (root.right != nil) {
                return search2(searchNode, root.right);
            }
        } else if (searchNode.data == root.data) {
            return root;
        }
        return null;
    }
	
	public int[] inorder() {
		inorder(root);
		return inordered_data;
	}

	// ������ȸ (�������� ��� Ű�� ��¿� ����Ѵ�)
	public void inorder(Node root) {
		if (root != nil) { // nil�� ���� �����ϰ� -1�̶� ������ �����Ͽ��µ� nil���� ��¾ȵǰ� �ϱ�����
							// ���� if�� ������ �־� nil���� ��ȸ���� �ʰ��Ͽ���.(nil��尪�� ���� ����� ��������)
			inorder(root.left);
			System.out.println(root.data);
			inordered_data[num++] = root.data;
			inorder(root.right);
		}
	}
}
