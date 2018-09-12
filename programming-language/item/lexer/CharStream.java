//201404377_���¾�
package lexer;

class CharStream {
	private String reader; // �о���� �����
	private Character cache;
	private int tmp = 0; // �߰�(8����)

	/*
	 * static CharStream from(String str) throws FileNotFoundException { return new
	 * CharStream(new FileReader(str)); }
	 */ // ���� (8����)

	Char nextChar() { // ������ (8����)
		if (cache != null) {
			char ch = cache;
			cache = null;

			return Char.of(ch);
		} else {
			if (tmp > reader.length() - 1) {
				tmp = 0;
				return Char.end();
			} else {
				return Char.of((char) reader.charAt(tmp++));
			}

		}
	}

	CharStream(String reader) { // ������(8����)
		this.reader = reader;
		this.cache = null;
	}

	void pushBack(char ch) {
		cache = ch;
	}
}
