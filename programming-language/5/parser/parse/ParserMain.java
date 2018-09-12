//201404377_���¾�
package parser.parse;

import java.io.File;

public class ParserMain {

	public static final void main(String... args) throws Exception {    
		ClassLoader cloader = ParserMain.class.getClassLoader();    
		File file = new File(cloader.getResource("parser/as05.txt").getFile());  
		//file read�� ���� ��� �ۼ�(���� �ڵ�� ����)       
		CuteParser cuteParser = new CuteParser(file);     
		NodePrinter.getPrinter(System.out).prettyPrint(cuteParser.parseExpr());              
		} 
	

}
