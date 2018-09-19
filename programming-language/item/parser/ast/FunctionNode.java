//201404377_���¾�
package parser.ast;

import java.util.HashMap;
import java.util.Map;

import lexer.TokenType;

public class FunctionNode implements ValueNode {  //binaryOpNodeŬ������ ���� �����ؼ� �ۼ� 

	   
	public enum FunctionType{
		ATOM_Q	{ TokenType tokenType() {return TokenType.ATOM_Q;}},   
		CAR 	{ TokenType tokenType() {return TokenType.CAR;}},
		CDR  	{ TokenType tokenType() {return TokenType.CDR;}},
		COND   	{ TokenType tokenType() {return TokenType.COND;}},
		CONS  	{ TokenType tokenType() {return TokenType.CONS;}},
		DEFINE  { TokenType tokenType() {return TokenType.DEFINE;}},
		EQ_Q   	{ TokenType tokenType() {return TokenType.EQ_Q;}},
		LAMBDA  { TokenType tokenType() {return TokenType.LAMBDA;}},
		NOT   	{ TokenType tokenType() {return TokenType.NOT;}},
		NULL_Q	{ TokenType tokenType() {return TokenType.NULL_Q;}};
	
	private static Map<TokenType, FunctionType> fromTokenType = new HashMap<TokenType, FunctionType>();  
	static {  
		for (FunctionType pType : FunctionType.values()){   
			fromTokenType.put(pType.tokenType(), pType);   
		}   
	}  
	static FunctionType getFunType(TokenType tType){ 
		return fromTokenType.get(tType); 
	} 
	abstract TokenType tokenType();
	}
	 
		public FunctionType value; 
		@Override  
		public String toString(){   
			//���� ä���  
			return value.name(); 
		}  
	
		
		public void setValue(TokenType tType) { 
			//���� ä���  
			FunctionType pType = FunctionType.getFunType(tType);  
			value = pType; 
		}
}