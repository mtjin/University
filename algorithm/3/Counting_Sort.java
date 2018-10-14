
public class Counting_Sort {
	
	public void countingSort(int[] arr) {
		int i, j, max=0;
		int count[];
		int tmp[] = new int[arr.length];
		
		
		//arr�迭 �ִ� max�� ����
		for(i=0; i<arr.length; i++ ) {
			if(arr[i] > max) {
				max = arr[i]; 
			}
		}
		
		//cout�迭 ����
		count = new int[max+1];
		
		//ī������ �迭 �ʱ�ȭ
		for(i=0; i<=max; i++) {
			count[i] = 0;
		}
		
		//ī���� ����(ex)arr�迭�� 1�� 3���� count[1]�� 3���� 
		for(i=0; i<arr.length; i++) {
			count[arr[i]]++;
		}
		
		//count�� ������ ������
		//count[j]�� j���� �۰ų� ���� ������ �� ���� ���� (ex) count[1]�� count[0]�� count[1]�� ��, count[2]�� ������������� count[1]�� count[2]�� �� 
		for(j=1 ; j < count.length; j++) {
			count[j] = count[j] + count[j-1];
		}
		
		
		//�������� ������ ����(count) �̿��� ����
		for(j=arr.length-1; j >= 0; j--) {
			tmp[count[arr[j]]-1] = arr[j]; // arr���� ����ִ� count �����Ȱ����� -1�� �ε��� ��ġ�� ����Ͽ� tmp�� �ش� arr�� ���� ���� 
			count[arr[j]]--; //count�� �������տ��� 1���ҽ�����
		}
		
		//tmp�迭�� arr�迭�� ����
		System.arraycopy(tmp, 0, arr, 0, tmp.length);
	}
}
