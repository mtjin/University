public class main {
	public static void main(String []args) {
	myStudent student= new myStudent("��ٿ�",true,24);
	student.updateTranscript(new mySection("��ȸ��"), new myGrade("B+"));
	student.updateTranscript(new mySection("�ڷᱸ��"), new myGrade("A"));
	System.out.println(student.getName());
	student.printTranscript();
	}
}
