//201404377_���¾�
package lexer;

import java.io.FileNotFoundException;

class ScanContext {
	private final CharStream input;
	private StringBuilder builder;
	
	ScanContext(String str) throws FileNotFoundException {   //������(8����)
		this.input = new CharStream(str);
		this.builder = new StringBuilder();
	}
	
	CharStream getCharStream() {
		return input;
	}
	
	String getLexime() {
		String str = builder.toString();
		builder.setLength(0);
		return str;
	}
	
	void append(char ch) {
		builder.append(ch);
	}
}
