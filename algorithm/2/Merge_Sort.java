
public class Merge_Sort {

	//������ �ɰ��������� sort
	public void sort(int[] A, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2; // ���ݱ���
			sort(A, left, mid); // ���� ���� �� �ɰ�
			sort(A, mid + 1, right); // ���� ������ �� �ɰ�
			merge(A, left, mid, right); // �ɰ��迭 ��ħ
		}
	}

	//�ش簳���ɋ����� sort
	public void divided_sort(int[] A, int left, int right, int divided_num) { //�ڿ� �ִ� �ɰ��� ���� �Ű����� �߰�
		if (left < right && right-left >= divided_num) {
			int mid = (left + right) / 2; // ���ݱ���
			divided_sort(A, left, mid, divided_num); // ���� ���� �� �ɰ�
			divided_sort(A, mid + 1, right, divided_num); // ���� ������ �� �ɰ�
			merge(A, left, mid, right); // �ɰ��迭 ��ħ
			
			//System.out.println(right-left);   // �� ��¹����� ������ ���������� �ɰ������� Ȯ�ΰ���
		}
	}
	
	//merge
	public void merge(int[] arr, int left, int mid, int right) {
		int k = left; // �迭 ������ġ
		int merged[] = new int[right + 1]; // left���� right�ε��� ���� ������ �ӽù迭�� �˸��� ũ��� ���� (right�ε������� �־���ϹǷ� right+1)

		for (k = left; k <= right; k++) { // �ӽù迭�� �ɰ��� �� �迭�� �״�� ���ļ� �־���(����X)
			merged[k] = arr[k];
		}

		insertion_sort(merged); // �ΰ��� �迭�� ��ģ �迭�� ������������(�̹��������� �պ������� ���������� �̿��϶� �����Ƿ�)

		for (k = left; k <= right; k++) { // ���� ������ �ӽù迭�� ���� �迭�� �˸��� ��ġ�� ���� �־��� (�����Ѱ����� ����).
			arr[k] = merged[k];
		}
	}

	// ��������
	void insertion_sort(int[] list) {
		int i, j, key;
		for (i = 1; i < list.length; i++) {
			key = list[i];
			for (j = i - 1; j >= 0 && list[j] > key; j--) {
				list[j + 1] = list[j];
			}
			list[j + 1] = key;
		}
	}
}
