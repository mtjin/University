
public class Quicksort {

	private int[] array;
	private int size, i;
	private int maxSize;
	
	public Quicksort(int maxSize) {
		this.maxSize = maxSize;
		size = 0;
		array = new int[maxSize];
		this.i =0;
	}
	
	public void quickSortRecursively(int left, int right) { //void�� �ٱ���
		if(left < right) {
		int mid = partition(left, right);     
		quickSortRecursively(left, mid-1);
		quickSortRecursively(mid+1, right);   
		}
	}
	public void sorting() {
		this.quickSortRecursively(0, size-1);
	}
	public int partition(int left, int right) {
		int pivot = left;
		int data = this.array[pivot];
		right++;  //�׷��� �ؿ� do-while������ ���ҽ������� �������ִ°����� ������ �� ����.
		do {
			//ä���ֱ�
			do {
				left++;
			}while((array[left] < data) && left <= right);  //�ǹ������� left�����Ͱ��� �۰�, left�� right���� �۰ų� ���ƾ���
			
			do {
				right--;
			}while(array[right] > data && right >= left); //���� ������������ (right >= left) =�� �ٿ��� left�ϰ� right�� �� ��������.
			if(left<right) {
				this.swap(left, right);
			}
		}while(left < right);
			
		this.swap(pivot , right);
		return right;
	}
	public void swap(int a , int b) {
		int tmp;
		tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
		
	}
	public boolean add(int data) {
		if(size< maxSize) {
			array[size++]= data;
			return true;
		}else {
			return false;
		}
		
	}
	public int getFirst() {
		i =0; //i�ʱ�ȭ����
		return array[i++]; //getNext()�Ҷ� 1���ε������� �ҷ��ü��ְ���
	}
	public int getNext() {
		return array[i++];
	}
	public int size(){
		return size;
	}
}
