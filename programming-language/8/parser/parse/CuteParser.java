//201404377_���¾�
package parser.parse;

import java.io.FileNotFoundException;
import java.util.Iterator;

import lexer.Scanner;
import lexer.Token;
import lexer.TokenType;
import parser.ast.BinaryOpNode;
import parser.ast.BooleanNode;
import parser.ast.FunctionNode;
import parser.ast.IdNode;
import parser.ast.IntNode;
import parser.ast.ListNode;
import parser.ast.Node;
import parser.ast.QuoteNode;

public class CuteParser {

	private Iterator<Token> tokens; 
	private static Node END_OF_LIST = new Node(){};   //���� �߰��� �κ�
	
	public CuteParser(String str) {    //��������(8����)
		try {   
			tokens = Scanner.scan(str);  
		} catch (FileNotFoundException e) { 
			// TODO Auto-generated catch block  
			e.printStackTrace();   
			} 
		}  
	private Token getNextToken() {   
		if (!tokens.hasNext())   
			return null;  
		return tokens.next(); 
		} 
	public Node parseExpr() { 
		Token t = getNextToken();  
		if (t == null) {   
			System.out.println("No more token");   
			return null;  
		}  
		TokenType tType = t.type(); 
		String tLexeme = t.lexme();  
		switch (tType) { 
		case ID:   
			IdNode idNode = new IdNode(tLexeme);  //��������
			return idNode;  
		case INT:  
			IntNode intNode = new IntNode(tLexeme);     //��������  
			if (tLexeme == null)   
				System.out.println("???"); 
			return intNode;  
		
		// BinaryOpNode  +, -, /, *�� �ش�  
		case DIV:  
		case EQ: 
		case MINUS:  
		case GT:  
		case PLUS:  
		case TIMES: 
		case LT:    
			BinaryOpNode binaryNode = new BinaryOpNode();  
			binaryNode.setValue(tType);   
			return binaryNode;  
			  
		// FunctionNode Ű���尡 FunctionNode�� �ش�   
		case ATOM_Q: 
		case CAR:  
		case CDR: 
		case COND: 
		case CONS:
		case DEFINE:  
		case EQ_Q:
		case LAMBDA:  
		case NOT: 
		case NULL_Q: 
			FunctionNode functionNode = new FunctionNode(); // ���� ä��� (BinaryOp����)
			functionNode.setValue(tType);   
			return functionNode; 
			 
			
		// ���� ������ BooleanNode  
		case FALSE:    
			return BooleanNode.FALSE_NODE; 
			
		case TRUE:   
			return BooleanNode.TRUE_NODE;
		
		// case L_PAREN�� ���� case R_PAREN�� ���
		// L_PAREN�� ��� parseExprList()�� ȣ���Ͽ� ó��   
		
		//���� ������ L_PAREN, R_PAREN  Case 
		case L_PAREN:    
			return parseExprList();  
			  
		case R_PAREN: 
			return END_OF_LIST ; 
			  
		//���� �߰��� APOSTROPHE, QUOTE 
		case APOSTROPHE: 
			   return new QuoteNode(parseExpr()); 
		case QUOTE: 
			   return new QuoteNode(parseExpr()); 
		default:    
			// head�� next�� ����� head�� ��ȯ�ϵ��� �ۼ�
			System.out.println("Parsing Error!");    
			return null;  
		}  
	}  
	// List�� value�� �����ϴ� �޼ҵ�
	private Node parseExprList() {  
		Node head = parseExpr(); 
		// head�� next ��带 set�Ͻÿ�.
		if (head == null) { // if next token is RPAREN  
			return null;  
		}
		if (head == END_OF_LIST) // if next token is RPAREN          
			return ListNode.ENDLIST;   
		
		ListNode tail = (ListNode) parseExprList(); 
		if (tail == null)     
		      return null; 
		  
		return ListNode.cons(head, tail);   
	} 
	
}
	
