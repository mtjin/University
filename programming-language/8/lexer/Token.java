//201404377_���¾�
package lexer;
import java.util.HashMap;
import java.util.Map;

public class Token {                            //��ū�� Ÿ�԰� ���ڸ� ����, Ű�������� ����õ���� ���� �˾Ƴ� �� ����
	private final TokenType type;
	private final String lexme;
	
	static Token ofName(String lexme) {   
		TokenType type = KEYWORDS.get(lexme);                 //Ű���带 ����   
		if ( type != null ){                                  //Ű���尡 ������ �� Ű����Ÿ���� ��ū��ȯ
			return new Token(type, lexme);  
		} 
		else if ( lexme.endsWith("?") ){                          //���ԽĿ�����  ID'?' =>QUESTION
			if ( lexme.substring(0, lexme.length()-1).contains("?") ) { 
				throw new ScannerException("invalid ID=" + lexme);   //���� ����?�� ������ �߰��� ?�� ���ִ°�� invaild
			}       
			return new Token(TokenType.QUESTION, lexme);       //?�� ���������� QUESTIONŸ������ ��ū��ȯ
			}   
		
		else if ( lexme.contains("?") ) {                      //���� ?�� ���� �ٸ��κп� ?�� �ִ°�� invalid
			throw new ScannerException("invalid ID=" + lexme);  
		}


		else if(lexme.startsWith("#")) {              //�����߰��Ѱ�  lexme�� #T ,#F�� ���
			if(lexme.endsWith("T") && lexme.length()==2) {
				return new Token(TokenType.TRUE, lexme);
			}
			else if(lexme.endsWith("F") && lexme.length()==2) {
				return new Token(TokenType.FALSE, lexme);
			}
			else  throw new ScannerException("invalid ID=" + lexme); 
		} 
		
		else {
				return new Token(TokenType.ID, lexme);            //?��  ���þ��� �����̸� Ű���幮�嵵 �ƴѰ�� IDŸ������ ��ū ��ȯ
			}
		}
	Token(TokenType type, String lexme) { 
		this.type = type;  
		this.lexme = lexme; 
	}   
	public TokenType type() { 
		return this.type; 
	}   
	public String lexme() { 
		return lexme; 
	}    
	
	@Override 
		public String toString() { 
		return String.format("%s(%s)", type, lexme);
		}
	
		private static final Map<String,TokenType> KEYWORDS = new HashMap<>(); 
		static { 
			KEYWORDS.put("define", TokenType.DEFINE);
			KEYWORDS.put("lambda", TokenType.LAMBDA); 
			KEYWORDS.put("cond", TokenType.COND);
			KEYWORDS.put("quote", TokenType.QUOTE);  
			KEYWORDS.put("not", TokenType.NOT); 
			KEYWORDS.put("cdr", TokenType.CDR);  
			KEYWORDS.put("car", TokenType.CAR);  
			KEYWORDS.put("cons", TokenType.CONS); 
			KEYWORDS.put("eq?", TokenType.EQ_Q); 
			KEYWORDS.put("null?", TokenType.NULL_Q);  
			KEYWORDS.put("atom?", TokenType.ATOM_Q); 
		}
}