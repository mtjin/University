//201404377_���¾�
package lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Stream;

public class ScannerMain {
    public static final void main(String... args) throws Exception {
        ClassLoader cloader = ScannerMain.class.getClassLoader();
        File file = new File(cloader.getResource("as03.txt").getFile());   //���� �о��
        testTokenStream(file);
    }
    
    // use tokens as a Stream 
    private static void testTokenStream(File file) throws FileNotFoundException {	
        Stream<Token> tokens = Scanner.stream(file);  //��Ʈ���� ����Ʈ�� ����ѵ� �̸� ��ū���� �ѹ��� �ٹ޾Ƽ� ��Ģ��� �̾Ƴ� �� �ִµ�
        tokens.map(ScannerMain::toString).forEach(System.out::println);  //map�� ��Ģ��� �����°��ǹ��ϰ� foreach�� �ݺ������ǹ�
    }    
    
    private static String toString(Token token) {
        return String.format("%-3s: %s", token.type().name(), token.lexme());
    }
    
}
