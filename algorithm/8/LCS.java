
public class LCS {
	
	//Direction
	final int LEFT = 0;
	final int UP = 1;
	final int DIAGONAL = 2;
	
	StringBuffer result = new StringBuffer();	//LCS ����� ����
	
	//Longest Common Subsequence
	Arr[][] LCS_length(String x, String y) {
		int m = x.length()+1;  //x�� ���ڿ� ���� (���ڿ��� ���̺��� 1�� �ʿ��ϹǷ� 1����)
		int n = y.length()+1;  // ''''
		Arr[][] arr = new Arr[m][n];
		
		//�迭 �ʱ�ȭ ��������
		for(int i = 0; i<m; i++) { 	
			for(int j =0; j<n; j++) {
				arr[i][j] = new Arr();
			}
		}
		
		//�迭 ��, �� ù�ٵ��� 0���� �ʱ�ȭ (=�� 0�� �� 0���� �ʱ�ȭ, �� 0�� �� 0���� �ʱ�ȭ)  
		for(int i =1; i<m; i++) {  
			arr[i][0].num = 0;
		}
		for(int j =0; j < n; j++) {
			arr[0][j].num = 0;
		}
		
		//LCS ��ŷ 
		for(int i =1; i<m; i++) {  // ������ 0���� �ʱ�ȭ �� ��ġ�� ������ ĭ���� for�� ���� ��ŷ�Ѵ�
			for(int j=1; j<n; j++) { // ''
				if(x.charAt(i-1) == y.charAt(j-1)) {	//x���ڿ��� i-1���� ���ڿ� y���ڿ��� j-1��° ���ڰ� ���� ���(���ڿ��� ������ 0���� �����ϹǷ� i,j���� 1�۾ƾ� ��Ī�ǹǷ� charAt�� -1����)
					arr[i][j].num = arr[i-1][j-1].num + 1;	//���� i,jĭ�� ���ʴ밢����ĭ([i-1,j-1])�� 1�� ���� ���� ����
					arr[i][j].direction = DIAGONAL;		//���⿡ �밢�� ��ŷ
				}
				else if(arr[i-1][j].num >= arr[i][j-1].num) {	//���ڰ� �����ʰ� ������ġ�� ���ʰ��� ����ĭ ������ ū ���
					arr[i][j].num = arr[i-1][j].num;	//������ġ�� ��ĭ�� ����
					arr[i][j].direction =  UP;			//���⿡ UP ��ŷ
				}
				else {		//���ڰ� �����ʰ� ������ġ�� ����ĭ ���� ����ĭ ������ ū ���
					arr[i][j].num = arr[i][j-1].num;	//������ġ�� ����ĭ�� ����
					arr[i][j].direction = LEFT;		//���⿡ LEFT ��ŷ
				}
			}
		}
		return arr;
	}
	
	StringBuffer LCS_print(Arr[][] b, String x, int i, int j ) {
		if(i==0 || j == 0) { 	//i,j�� 0�� ��� Ž���� ���Ѱ��̹Ƿ� ���ݱ��� ������ ����� ����
			return result;
		}
		if(b[i][j].direction == DIAGONAL) {		//DIAGONAL ��ŷ�� ���
			LCS_print(b,x, i-1, j-1);		//���ʴ밢�� �� ĭ���� ���
			System.out.print(x.charAt(i-1));
			result.append(x.charAt(i-1));	//�ش� ���� ������� ����
		}
		else if(b[i][j].direction == UP) {	//UP ��ŷ�� ���
			LCS_print(b,x, i-1, j);		//	//���� ĭ���� ���
		}
		else {	//LEFT ��ŷ�� ���
			LCS_print(b,x, i, j-1); //������ ĭ���� ���
		}
		
		return result;
		
	}
	
	
	 

}
