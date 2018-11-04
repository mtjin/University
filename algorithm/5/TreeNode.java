
public class TreeNode {

	// �� �����ϰ� �ϱ����ؼ� setter , getter���� �׳� ���� �ٷ� ���� �����ϰ� public������ �����Ͽ���.
	int data;
	int color; // 0�� ���� 1�� ������ �ϱ���Ѵ�.(�⺻�� ����)
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	public TreeNode nil = null;

	public TreeNode() {
		this.left = nil;
		this.right = nil;
		this.color = 0;
		this.parent = nil;
	}

	public TreeNode(int data) {
		this.data = data;
		this.left = nil;
		this.right = nil;
		this.color = 0;
		this.parent = nil;
	}

}
