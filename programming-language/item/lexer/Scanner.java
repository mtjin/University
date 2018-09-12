//201404377_���¾�
package lexer;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Scanner {
    // return tokens as an Iterator
    public static Iterator<Token> scan(String str) throws FileNotFoundException {   //�������� (8����)
        ScanContext context = new ScanContext(str);    
        return new TokenIterator(context);              
    }

    // return tokens as a Stream 
    public static Stream<Token> stream(String str) throws FileNotFoundException {  //�������� (8����)
        Iterator<Token> tokens = scan(str);                              //��ū�� ���� �Լ��� �������� 
        return StreamSupport.stream(                                      //��ū�� ��Ʈ������ ������ 
                Spliterators.spliteratorUnknownSize(tokens, Spliterator.ORDERED), false);
    }
}