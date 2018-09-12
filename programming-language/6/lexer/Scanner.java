//201404377_���¾�
package lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Scanner {
    // return tokens as an Iterator
    public static Iterator<Token> scan(File file) throws FileNotFoundException {      //��ū�� ���ʴ�ΰ�����
        ScanContext context = new ScanContext(file);    //������ �о��
        return new TokenIterator(context);              
    }

    // return tokens as a Stream 
    public static Stream<Token> stream(File file) throws FileNotFoundException {
        Iterator<Token> tokens = scan(file);                              //��ū�� ���� �Լ��� �������� 
        return StreamSupport.stream(                                      //��ū�� ��Ʈ������ ������ 
                Spliterators.spliteratorUnknownSize(tokens, Spliterator.ORDERED), false);
    }
}