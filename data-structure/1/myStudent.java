import java.util.*;

public class myStudent implements Student{

	private String name;
	private boolean male;
	private int yob;
	Transcript transcript= new Transcript();     //transcript�� �� Ŭ�������� ���ϱ� ��������
	public myStudent() {
		
	}
	public myStudent(String name, boolean male, int yob) {
		this.name= name;
		this.male= male;
		this.yob= yob;
		
	}
	 public String getName() {
		 return name;
	 }

	 public void printTranscript() {        //�й�,������
		 System.out.println(transcript);
	 }
	
		@Override
		public void updateTranscript(mySection section, myGrade grade) {  //�й�,��� �߰�
			// TODO Auto-generated method stub
			transcript.add(section, grade);             
			}
		private class Transcript {
			Map map= new HashMap();  //�й�, ���
			void add(mySection mySection, myGrade myGrade) {
				map.put(mySection, myGrade);
				}
			public  String toString() {
				return map.toString();
				}
			 
		}
	 
	 

}
