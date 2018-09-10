
public class HeapSort {

	int[] newData;

	public void sort(int[] a, int size) {
		this.newData = new int[size + 1];
		this.newData[0] = 0;
		for (int i = 0; i < size; i++) {
			this.newData[i + 1] = a[i]; // �迭 �ε��� 1���� ���ʴ�� �� �־���
		}

		int i;

		for (int j = size; j > 0; j--) { // �����ŭ �ݺ�
			for (i = size / 2; i > 0; i--) //
				adjust(this.newData, i, j);
			swap(1, j);
		}

		for (int j = 0; j < size; j++)
			a[j] = this.newData[j + 1];

	}

	private void swap(int x, int y) {
		int temp = newData[x];
		newData[x] = newData[y];
		newData[y] = temp;

	}

	public void adjust(int[] newData, int root, int n) {
		int child, rootkey;
		int temp = newData[root]; // �ӽ� ��Ʈ��
		rootkey = newData[root]; // ��ƮŰ��
		child = 2 * root; // ó���� 8
		while (child <= n) {

			if (child < n && newData[child] < newData[child + 1]) { //������ �ڽ����ְ� �����ڽ��� �����U �ڽĺ��� ������ 
			temp = newData[++child];
			} else { // ���� �ڽĹۿ� ���ų�(������1) �����ڽ��� ������ �ڽĺ��� Ŭ ��
				temp = newData[child];
			}

			if (rootkey < temp) {  //rootkey���� child���� ���ؼ� child���� �� ū ���(��ȯ)
				int tmp2 = newData[child / 2];  //tmp2�� �ڽ��� �θ��� ����
				newData[child / 2] = newData[child];  // �ڽ��� �θ𰪿� �ڽ��� ���� ����
				newData[child] = tmp2; //�ڽ� ����  �ڽ��� �θ��� ���� 
			}
			child = child * 2;  //child�� �θ��� ������ �ٲ���
		}

	}
}
