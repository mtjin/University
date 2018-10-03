
public class Quick_Sort {

	public void sort(int[] A, int low , int high) {
		if(low< high) {     //������ ������ 2���̻� �����Ͷ��
		int q = partition(A, low, high);  //�ǹ��� �������� 2���� ����Ʈ�� ����( �Լ��� ���� ���� �ǹ��� ��ġ�̴�)
		sort(A, low , q-1);   //low���� �ǹ���ġ �ձ��� ������� ��ȯȣ��
		sort(A, q+1, high);   //�ǹ���ġ���� high������ ������� ��ȯȣ��
		}
	}
	
	public void random_sort(int[] A, int low , int high) {
		if(low< high) {     //������ ������ 2���̻� �����Ͷ��
		int q = randomize_partition(A, low, high);  //�ǹ��� �������� 2���� ����Ʈ�� ����( �Լ��� ���� ���� �ǹ��� ��ġ�̴�)
		sort(A, low , q-1);   //low���� �ǹ���ġ �ձ��� ������� ��ȯȣ��
		sort(A, q+1, high);   //�ǹ���ġ���� high������ ������� ��ȯȣ��
		}
	}
	
	
	public int partition(int []A, int low, int high ) {
		int pivot = A[high];  //�ǹ��� ������ ���� ������������
		int left = low -1;  //�ǹ����� ������ ����ų �����Ϳ���
		int right = 0;      //�ǹ����� ū���� ����ų �����Ϳ��� �� �迭ó���� ���� ���ʴ�� �ҷ����� �����Ϳ���(for��)
		for(right = low; right <= high-1; right++) {
			if(A[right] <= pivot){  //�ǹ����� ���� ������
				left++;             //left 1����
				swap(A, left, right);  //left�� right�� swap����
			}
		}
		swap(A, left+1, high); //left+1��(�ǹ����غ��� ū ���� �����ε���) high��(�ǹ�) swap����
		return left + 1;
		
	}
	
	public int randomize_partition(int []A, int low, int high ) {
		int pivot = A[4];    //�ǹ��� ������ 4�� ��Ҵ�.
		int left = low -1;
		int right = 0;
		for(right = low; right <= high-1; right++) {
			if(A[right] <= pivot){
				left++;
				swap(A, left, right);
			}
		}
		swap(A, left+1, high);
		return left + 1;
	}
	
	private void swap(int[] elements, int left, int right) {
		
		int temp = elements[left];
		elements[left] = elements[right];
		elements[right] = temp;
	}
	
}
