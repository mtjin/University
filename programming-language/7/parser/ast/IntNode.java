//201404377_���¾�
package parser.ast;

public class IntNode implements ValueNode{

	public Integer value;  //private���� public���� ����
	
	@Override 
	public String toString(){ 
		return "INT: " +  value;
	} 
	
	public IntNode(String text) { 
		  this.value = new Integer(text); 
	} 
		  
}
