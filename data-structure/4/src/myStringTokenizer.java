
public class myStringTokenizer {
	
	private String[] words;       //��ū�� ������ ����
	private String line= "";      //��ü����
	private String token ="";     //��ū�� ���� ����
	private int index;            //words�迭�� ���� �����ϱ�����  �����Ϳ���
	
	public myStringTokenizer(String line, String token) {
		this.line =line;
		this.token= token;
		this.words= makeWords(line, token);     //�Լ����Ἥ ��ū���� ������ �迭�� �޾ƿ�
		this.index= 0;
	}
	 public boolean hasMoreTokens() {
		 if(words.length == index) return false;   //words�� �迭�� ���̿� ��ū�� ����Ű�� �������� ���� �������� ���ҷ��ð� ���°��̹Ƿ� false�� ��ȯ
		 else return true;             
	 }
	 public String nextToken() {
		 return words[index++];                //��ū�� ��ȯ
		 }
	 public String[] makeWords(String line, String token) { 
		 String[] tmp= null;
		 int split_index= 0;  //��ū�� ���� �迭(tmp)�� �ε����� ����Ű�� �����Ϳ�Ȱ
		 int split_length= 1; //��ū�� �Ѱ���
		 int begin_index= 0;  //��ūã�µ� ó���ܾ ����Ű�� �ε���
		 int last_index= 0;  //��ūã�µ� ������ �ܾ ����Ű���ε���
		
		 for(int i=0; i<line.length(); i++) {
			 if(line.charAt(i)==' ')         //���鸸���� �ɰ������� 1����(�����  �ٽ���)
			 {
				 split_length++;
			 }
			 }
		 tmp= new String[split_length];
		 for(int i=0; i<line.length(); i++)
		 {
			 if(split_length== (split_index+1))            //�������迭�� �ε�����������������
			 {
				 tmp[split_index]= line.substring(begin_index, line.length());        //�������� �ϴ��������
				 break; 
			 }
			 else if(line.charAt(i)==' ')          //���鸸�����
			 {
				 //ä���ֱ�
				 last_index= i;                 //���鸸�������� i�� last_index�� �־���
				 tmp[split_index++]= line.substring(begin_index, last_index);  //����(" ")ã�� ���������� ��ū�� tmp�� �־���.
				 begin_index=last_index+1;      //����ε����� ������ã�� �ε������� 1����������
				 
			 }
		 }
		 return tmp;
	 }

}
