//201404377_���¾�
package parser.parse;

import java.io.PrintStream;

import parser.ast.IdNode;
import parser.ast.ListNode;
import parser.ast.Node;
import parser.ast.QuoteNode;

public class NodePrinter {

	PrintStream ps;    
	
	public static NodePrinter getPrinter(PrintStream ps) { 
		return new NodePrinter(ps); 
	} 
	private NodePrinter(PrintStream ps) { 
		this.ps = ps;
	} 
	
	private void printNode(ListNode listNode) { 
		if (listNode == ListNode.EMPTYLIST) { 
			   ps.print("( ) "); 
			   return; 
		} 
		if (listNode == ListNode.ENDLIST) {  
			   return; 
		}
		// ���� �κ��� �־��� ��� ���Ŀ� �°� �ڵ带 �ۼ��Ͻÿ�. 
		
		ps.print("( "); 
		printNode(listNode.car()); //ó������ (Node)
		printNode(listNode.cdr()); //ó������ �� ����Ʈ(ListNode)
		ps.print(") ");
		
	} 

	private void printNode(QuoteNode quoteNode) {       
		  if (quoteNode.nodeInside() == null) 
			  return;  
		// ���� �κ��� �־��� ��� ���Ŀ� �°� �ڵ带 �ۼ��Ͻÿ�. 
		
		  ps.print('\'');
		  if(quoteNode.nodeInside() instanceof IdNode) {
			  ps.print(quoteNode);
		  }
		  else {
		  ListNode listNode = (ListNode)quoteNode.nodeInside();
		  ps.print("( ");
		  printNode(listNode.car());
		  printNode(listNode.cdr());
		  ps.print(") ");
		  }
		  
		 
	}
	
	private void printNode(Node node) {       
		if (node == null)  
			return; 
		// ���� �κ��� �־��� ��� ���Ŀ� �°� �ڵ带 �ۼ��Ͻÿ�.
		if (node instanceof ListNode) { 
			ListNode listNode = (ListNode)node;
			printNode(listNode); 
			//printNode(listNode.cdr());
		}
		else if(node instanceof QuoteNode) {
			QuoteNode quoteNode = (QuoteNode)node;  
			printNode(quoteNode);  
		}
		else {   
			ps.print("[" + node + "] "); 
		}  
		
		
	}
		  
	public void prettyPrint(Node node){   
		printNode(node); 
	} 
}
