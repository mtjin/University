
public class Heap_Sort {

	int heap_size;
	
	//MAX HEAP SORT(�ε��� 0���� ����)
	public void maxHeapSort(int[] arr) {
		int tmp;
		buildMaxHeap(arr);  //max heap ������ ����
		for(int i =arr.length-1; i>=1; i--) { //�ִ� ������ ���ִ� ���� ���� �Ϻ��ϰ� ��������, ��尡 n����� n-1�� �ݺ��ؾ���
			//swap
			tmp = arr[0];  //������ �ִ��� ���� ���������� �ٲ� (�ֈ��� �ּڰ� ����)
			arr[0] = arr[i];
			arr[i] = tmp;
			
			heap_size -= 1;  //�ϳ��� �����ϴ� ���̹Ƿ� �ϳ��� ��尡 ������ �Ϸ�Ǹ� ������� 1�ٿ��ش�.(�� ���� �� ��������尪�� �����̵�)(���������� ���� ū ��尪���� ����) 
			maxHeapify(arr, 0);  //ù��忣 ���� ū ���� ��������忣 ���� �������� ������ heapify��͸� ������
		}
		
	}

	public void buildMaxHeap(int[] arr) {
		heap_size = arr.length-1; //�������� �ִ�ũ�� ���� 
		for(int i=arr.length/2-1; i>=0; i--) {  //�ڽĳ��鰡 �ִ³�忡������ ���������� ���ʴ�� �������� ����
			maxHeapify(arr, i);
		}
		
	}
	public void maxHeapify(int[] arr, int i) { //�θ���� �θ����� ����Ʈ���� ��(���), �����ϸ鼭 maxheap������ �������
		int largest;  //�ִ밪 �����ִ� �ε���
		int left = 2*i+1;  //���� �ڽĳ�� �ε���
		int right = 2*i+2; //������ �ڽĳ�� �ε���
			
		
		if(left <= heap_size && arr[left]>arr[i]) { //���� �ڽĳ�� ��������� �۰ų����� �θ���(����) ������ ũ�� largest�� �����ڽĳ�� �ε��� ����
			largest = left;
		}
		else { //�ƴϸ� �θ��� �ε����� largest�� ����
			largest = i; 
		}
		
		//largest�� �θ��ε��� or �����ڽ��ε��� ������ִ� ����
		if(right <= heap_size && arr[right] > arr[largest]) { //�������ڽ� �ε����� ��������� �۰ų����� largest�ε��� ������ ũ�� largest�� �������ڽĳ�� �ε�������
			largest =right;
		}
		if(largest != i){ //���� ū ���� �ε����� �θ��尡 �ƴ϶�� �ڽĳ��� �θ��带 swap ����
			swap(arr, i, largest);
			maxHeapify(arr, largest); //���� �Ǵ� �ڽĳ�带(largest) �������� ��ͷ� heapify�ݺ�
		}
	
	}
	
	
	//MIN HEAP SORT (max heap�� �ݴ�� heapify�޼ҵ忡�� �ڽĳ�尡 �� �������̸� �θ���� �������ָ��
	public void minHeapSort(int[] arr) {
		int tmp;
		buildMinHeap(arr);
		for(int i =arr.length-1; i>=1; i--) {
			//swap
			tmp = arr[0];
			arr[0] = arr[i];
			arr[i] = tmp;
			
			heap_size -= 1;
			minHeapify(arr, 0);
		}
		
	}
	
	public void buildMinHeap(int[] arr) {
		heap_size = arr.length-1;
		for(int i=arr.length/2-1; i>=0; i--) {
			minHeapify(arr, i);
		}
		
	}
	
	public void minHeapify(int[] arr, int i) {
		int smaller;
		int left = 2*i+1;
		int right = 2*i+2;
		if(right<= heap_size) {
			if(arr[left] < arr[right]) {
				smaller = left;
			}
			else {
				smaller = right;
			}
		}
		else if(left <= heap_size) {
			smaller = left;
		}
		else {
			return;
		}
		if(arr[smaller] < arr[i]) {
			swap(arr, i, smaller);
			minHeapify(arr, smaller);
		}
	}
	
	//swap
	public void swap(int[] arr, int i , int largest) {
		int tmp = arr[i];
		arr[i] = arr[largest];
		arr[largest] = tmp;
		
	}

}
