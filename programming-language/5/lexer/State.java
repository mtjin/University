//201404377_���¾�
package lexer;
import static lexer.TokenType.INT;
import static lexer.TransitionOutput.GOTO_ACCEPT_ID;
import static lexer.TransitionOutput.GOTO_ACCEPT_INT;
import static lexer.TransitionOutput.GOTO_EOS;
import static lexer.TransitionOutput.GOTO_FAILED;
import static lexer.TransitionOutput.GOTO_SHARP;
import static lexer.TransitionOutput.GOTO_START;
import static lexer.TransitionOutput.GOTO_SIGN;
import static lexer.TransitionOutput.GOTO_MATCHED;

enum State {
	START {   //ó���޴� �κ�
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			char v = ch.value();
			switch ( ch.type() ) {
				case LETTER:
					context.append(v);
					return GOTO_ACCEPT_ID;
				case DIGIT:
					context.append(v);
					return GOTO_ACCEPT_INT;
				case SPECIAL_CHAR:
					 if(v == 35) {             //���ۺ��� #�� �ð�� SHARP���·� ��������
						 context.append(v);
						 return GOTO_SHARP;
					 }
					 else{                    // �� ���� Ư�����ڵ�
						 context.append(v);
						 return GOTO_SIGN; 
					 }
				case WS:	
					return GOTO_START;
				case END_OF_STREAM:
					return GOTO_EOS;
				default:
					throw new AssertionError();
			}
		}
	},
	ACCEPT_ID {     //���ĺ�ó��
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			char v = ch.value();
			switch ( ch.type() ) {
				case LETTER:
				case DIGIT:
					context.append(v);
					return GOTO_ACCEPT_ID;
				case SPECIAL_CHAR:
					if(v==63) {      // ?�� ���
						Character ch2 = new Character(v);
						String temp2 = context.getLexime();
						String b = temp2.concat(ch2.toString());
						return GOTO_MATCHED(Token.ofName(b).type(), b);  //ofName���� �ǹ������� keyword���� �˾Ƴ�
					}
					return GOTO_FAILED;
				case WS:
				case END_OF_STREAM:
					 String temp = context.getLexime();
		               return GOTO_MATCHED(Token.ofName(temp).type(), temp); //ofName���� Ÿ�� ID�� Ű����� �˾Ƴ�
				default:
					throw new AssertionError();
			}
		}
	},
	ACCEPT_INT {     //����ó��
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			switch ( ch.type() ) {
				case LETTER:
					return GOTO_FAILED;
				case DIGIT:
					context.append(ch.value());
					return GOTO_ACCEPT_INT;
				case SPECIAL_CHAR:
					return GOTO_FAILED; 
				case WS:
				case END_OF_STREAM:
					return GOTO_MATCHED(INT, context.getLexime());     //Ÿ�Կϼ��� INT��Ÿ������ ��ȯ
				default:
					throw new AssertionError();
			}
		}
	},
	SIGN {                     // Ư������ó��
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();  //���� �����ϳ� �о��
			char v = ch.value();
			switch ( ch.type() ) {
				case LETTER:         //Ư�����ڴ����� ���ڰ� ���� fail�Ǿ���
					return GOTO_FAILED;
				case DIGIT:           //+2, -1 �̷� ���
					context.append(v);
					return GOTO_ACCEPT_INT;
				case WS:  
					char ch2 = context.getLexime().charAt(0);
					Character cr = new Character(ch2);
					String str = new String(cr.toString());
					return GOTO_MATCHED(TokenType.fromSpecialCharactor(ch2), str);   //�ѱ���¥���� Ư������ ó��
				case END_OF_STREAM:
					char ch3 = context.getLexime().charAt(0);
					Character cr2 = new Character(ch3);
					String str2 = new String(cr2.toString());
					if(ch3 == ')')
						return GOTO_MATCHED(TokenType.fromSpecialCharactor(ch3), str2);   // '(' ��ȣ ���� ��
					return GOTO_FAILED;
				default:
					throw new AssertionError();
			}
		}
	},
	MATCHED {
		@Override
		public TransitionOutput transit(ScanContext context) {
			throw new IllegalStateException("at final state");
		}
	},
	FAILED{

		@Override
		TransitionOutput transit(ScanContext context) {
			// TODO Auto-generated method stub
			return null;
		}
	
	},
	EOS {
		@Override
		public TransitionOutput transit(ScanContext context) {
			return GOTO_EOS;
		}
	}, 
	SHARP {     // #�� �� ���
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			char v = ch.value();
			switch ( v ) {     
				case 84:       //T�� �� ���
					Character ch2 = new Character(v);
					 String temp2 = context.getLexime();
					 String b = temp2.concat(ch2.toString());
					return GOTO_MATCHED(Token.ofName(b).type(), b);
				case 70:       //F�� �� ���
					Character ch3 = new Character(v);
					 String temp3 = context.getLexime();
					 String b2 = temp3.concat(ch3.toString());
					return GOTO_MATCHED(Token.ofName(b2).type(), b2);
				default:
					throw new AssertionError();
			}
		}
		
	};
	
	abstract TransitionOutput transit(ScanContext context);

	
}
