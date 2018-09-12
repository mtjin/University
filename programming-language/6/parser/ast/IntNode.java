//201404377_���¾�
package parser.ast;

public class IntNode implements ValueNode{

	private Integer value; 
	
	@Override 
	public String toString(){ 
		return "INT: " +  value;
	} 
	
	public IntNode(String text) { 
		  this.value = new Integer(text); 
	} 
		  
}
