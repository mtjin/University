//201404377���¾�
public class Recursion {
	public static void main(String[] args) {   // TODO Auto-generated method stub 
		System. out .println( fibonacci (10));   
		System. out .println( recursiveAnt (10));  
	} 
	//public static int factorial(int n){  
	// if(n==1) return 1;  
	// return n* factorial (n-1); 
	//}  
	// recursion example     
	public static int fibonacci(int n){   
		if(n==0) return 0;
		if(n<3) return 1;
		else return ((fibonacci(n-2)+ fibonacci(n-1)));     // ä���� ���, recursion ���  }  
		}
	
	public static String recursiveAnt(int n){
		if(n==1) {
			return "1";
		} 
		else return makeResult(recursiveAnt(n-1));         // ä���� ���, recursion ���  }  
	}
	
	public static String makeResult(String previous){ 
		StringBuffer result= new StringBuffer(1000);  //
		char temp = 0;
		int count=0;
		for(int i=0; i<previous.length(); i++) {           // ä���� ���, �ݺ��� �ִ� 1ȸ ��� ����  }  
			if(previous.charAt(i)==temp || i==0) {         //ó������ �����̰ų� ���� ���ڰ� �� ���
				count++;
				temp= previous.charAt(i);
				if(i==previous.length()-1)                 //�������ڰ� ������ �����������ΰ��
					result.append(""+temp+count);
			}
			else{                                          //���� ���� ���ڰ� �� ���
				result.append(""+temp+count);
				temp= previous.charAt(i);
				count=1;
				if(i==previous.length()-1) {               //���� ���� ���ڰ������� �����������ΰ��
					temp= previous.charAt(i);
					count=1;
					result.append(""+temp+count);
				}
			}
		} //for��
		
		String result2= new String(result);
		return result2;
	}
}
