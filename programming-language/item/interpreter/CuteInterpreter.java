//201404377_���¾�
package interpreter;

import java.util.HashMap;
import java.util.Scanner;

import parser.ast.BinaryOpNode;
import parser.ast.BooleanNode;
import parser.ast.FunctionNode;
import parser.ast.IdNode;
import parser.ast.IntNode;
import parser.ast.ListNode;
import parser.ast.Node;
import parser.ast.QuoteNode;
import parser.parse.CuteParser;
import parser.parse.NodePrinter;

public class CuteInterpreter {

	static HashMap<String, Node> map = new HashMap<String, Node>(); // �߰�

	private void errorLog(String err) {
		System.out.println(err);
	}

	public Node runExpr(Node rootExpr) { // �ʱ� ��� �Ľ�
		if (rootExpr == null)
			return null;
		if (rootExpr instanceof IdNode) {
			////////////////////////////////////////////////// (item2) �������Ѱ��� ������� ex)
			////////////////////////////////////////////////// (define a 3) �̶������� a�� �Է��ϸ�
			////////////////////////////////////////////////// a���ƴ϶� 3�̳�������
			if (lookupTable(rootExpr.toString()) != null) { // ���̺� �ش� ���ǰ� ���� ���
				return lookupTable(rootExpr.toString());
			}
			///////////////////////////////////////////////////////
			else { // �׳� Id��ȯ
				return rootExpr;
			}
		} else if (rootExpr instanceof IntNode)
			return rootExpr;

		else if (rootExpr instanceof BooleanNode)
			return rootExpr;
		else if (rootExpr instanceof ListNode)
			return runList((ListNode) rootExpr); // ����Ʈ�ΰ�� Node runList(ListNode list)�� ���� <�̿� ���� �׳� ����ȯ>
		else
			errorLog("run Expr error");
		return null;
	}

	private Node runList(ListNode list) { // ����Ʈ�� ����� ����
		if (list.equals(ListNode.EMPTYLIST))
			return list;

		if (list.car() instanceof FunctionNode) { // FunctionNode�� ��� (car, cdr , NULLQ��)
			return runFunction((FunctionNode) list.car(), list.cdr());
		}
		if (list.car() instanceof BinaryOpNode) { // BinaryOpNode ��Ģ������ ���
			return runBinary(list);
		}
		return list; // Ư�������� �׳� ��ȯ
	}

	void insertTable(String id, Node value) { // ���̺� �Լ��߰�
		try {
			if (value instanceof ListNode) { // ( define a ( < 1 2 ) ) ���� �ٷ� ����Ʈ�� ���� �̰��� ���Ǽ� #T�� ����Ǿ���
				value = runList((ListNode) value); // ����Ʈ�� ������� �޾ƿ�(Ư������ ������ �׳ɹ�ȯ)
			}

			if (value instanceof ListNode) { // ������ ���� �°� ���� ���̺� �߰�����
				map.put(id, (IntNode) value);
				System.out.println("..ListNode���� ���̺� �߰��Ǿ����ϴ�");
			} else if (value instanceof QuoteNode) {
				map.put(id, (QuoteNode) value);
				System.out.println("..QuoteNode���� ���̺� �߰��Ǿ����ϴ�");
			} else if (value instanceof IntNode) {
				map.put(id, (IntNode) value);
				System.out.println("..IntNode���� ���̺� �߰��Ǿ����ϴ�");
			} else if (value instanceof BooleanNode) {
				map.put(id, (BooleanNode) value);
				System.out.println("..BooleanNode���� ���̺� �߰��Ǿ����ϴ�");
			} else if (value instanceof IdNode) {
				map.put(id, (IdNode) value);
				System.out.println("..IdNode���� ���̺� �߰��Ǿ����ϴ�");
			}
		} catch (Exception e) {
			errorLog("run Expr error");
		}

	}

	Node lookupTable(String id) { // ���̺��� ������ �������� �Լ� �߰�
		if (map.containsKey(id)) {
			Node node = map.get(id);
			return node; // (define a 3)�ϸ� ( 3 ) �̷��� ��ȣ �پ ��ȯ
		} else
			return null; // ���� ��� null��ȯ
	}

	private Node runFunction(FunctionNode operator, ListNode operand) {
		switch (operator.value) { // CAR, CDR, CONS� ���� ���� ����
		case CAR:
			//////////////////////////////////////////////////////////////////////////////////////// (item2)

			if (operand.car() instanceof IdNode && lookupTable(((IdNode) operand.car()).toString()) != null ) { // IdNode�� ���� define�Ѱ� �� ���� �����Ƿ� �˻����ش�
				Node result = ((QuoteNode) lookupTable(((IdNode) operand.car()).toString())).nodeInside(); // `���� ����Ʈ��ȯ
																											// ��ȣ����
																											// ��ȯ�ǹǷ�
																											// runQuote���Ұ�
				return ((ListNode) result).car(); // �� ����Ʈ�� ù���� �� ��ȯ
			}
			/////////////////////////////////////////////////////////////////////////

			else if (operand.car() instanceof QuoteNode) { // ���� car������ ���� ��尡 `���� �˻�
				ListNode result = (ListNode) runQuote(operand);
				return result.car();
			} else {
				errorLog("run Expr error");
			}
			return null;

		case CDR:
			////////////////////////////////////////////////// (item2)

			if (operand.car() instanceof IdNode && lookupTable(((IdNode) operand.car()).toString()) != null) { // IdNode�� ���� define�Ѱ� �� ���� �����Ƿ� �˻����ش�
				Node result = ((QuoteNode) lookupTable(((IdNode) operand.car()).toString())).nodeInside(); // `���� ����Ʈ��ȯ
																											// ��ȣ����
																											// ��ȯ�ǹǷ�
																											// runQuote���Ұ�
				return ((ListNode) result).cdr(); // �� ����Ʈ�� ù���� �� ��ȯ
			}
			///////////////////////////////////////////////////////////

			else if (operand.car() instanceof QuoteNode) {
				ListNode result = (ListNode) runQuote(operand);
				return result.cdr();
			} else {
				errorLog("run Expr error");
			}
			return null;
		case CONS: // ex ` (234)
			Node tmp = operand.car();
			Node tmp2 = operand.cdr().car();
			if (operand.car() instanceof IdNode && lookupTable(((IdNode) tmp).toString()) != null) { // ù ��尡 ���̺�
				tmp = lookupTable(((IdNode) tmp).toString()); // `(234)
			}
			if (operand.cdr().car() instanceof IdNode && lookupTable(((IdNode) tmp2).toString()) != null) { // �ι�° ��尡
																											// ���̺�
				tmp2 = lookupTable(((IdNode) tmp2).toString());
			}
			if (tmp instanceof QuoteNode) { // ó���� `�� ��� (����Ʈ�� ���� ���)
				Node result = ListNode.cons(((QuoteNode) tmp).nodeInside(), (ListNode) ((QuoteNode) tmp2).nodeInside());
				return result;
			} else {
				Node result = ListNode.cons(tmp, (ListNode) ((QuoteNode) tmp2).nodeInside());
				return result;
			}

		case NULL_Q:
			/////////////////////////////////////////////////////// // �߰�(item2) define������
			/////////////////////////////////////////////////////// Ȯ��
			if (operand.car() instanceof IdNode && lookupTable(((IdNode) operand.car()).toString()) != null) { // null_q��
																												// ����Ʈ������
																												// ����ε�
																												// �̾��̵�
																												// ���°���
																												// define����
																												// ���°����
				Node temp = ((QuoteNode) lookupTable(((IdNode) operand.car()).toString())).nodeInside(); // ����Ʈ�� ������
				if (((ListNode) temp).car() == null) {
					BooleanNode result = BooleanNode.TRUE_NODE;
					return result;
				} else {
					BooleanNode result = BooleanNode.FALSE_NODE;
					return result;
				}
			}
			///////////////////////////////////////////////////////////////////////////
			else if (((ListNode) runQuote(operand)).car() == null) {
				BooleanNode result = BooleanNode.TRUE_NODE;
				return result;
			} else {
				BooleanNode result = BooleanNode.FALSE_NODE;
				return result;
			}
		case COND:
			try {
				ListNode listNode = operand;
				while (listNode != ListNode.EMPTYLIST) {
					/////////////////////////////////////////////////////////////////// (item2)
					BooleanNode node;
					if (runExpr(((ListNode) listNode.car()).car()) instanceof IdNode
							&& lookupTable(((IdNode) ((ListNode) listNode.car()).car()).toString()) != null) { // if������ #T #F�� ���ǵ� ���̺� ���� �� ���
						node = (BooleanNode) lookupTable(((IdNode) ((ListNode) listNode.car()).car()).toString());
					}
					///////////////////////////////////////////////////////////////////////////////

					else { // �Ϲ����� #T #F�� ���� ���
						node = (BooleanNode) runExpr(((ListNode) listNode.car()).car());
					}

					if (node == (BooleanNode.TRUE_NODE)) {

						/////////////////////////////////////////////////////////////////////// (item2)

						if (((ListNode) listNode.car()).cdr().car() instanceof IdNode && lookupTable(
								((IdNode) (((ListNode) listNode.car()).cdr().car())).toString()) != null) { // ���̺� ���� ���
							Node result = lookupTable(((IdNode) (((ListNode) listNode.car()).cdr().car())).toString());
							if (result instanceof QuoteNode) { // ���ǹ��� Quote�� ���ִ� ���
								return result; // (����������)
							} else { // ��Ʈ���� �ƴҰ��
								return result; // ���� ���ĵ��ǳ� Ȥ�ø𸣴� �״�γ���(������)
							}

						}
						//////////////////////////////////////////////////////////////////////////////
						else {
							return ((ListNode) listNode.car()).cdr().car();
						}
					} else {
						listNode = operand.cdr(); // ���� ����Ʈ�� �θ���
					}
				}
			} catch (Exception e) {
				errorLog("run Expr error");
			}

		case NOT:
			/////////////////////////////////////////////////////////////////////////////// (item2)
			if (operand.car() instanceof IdNode && lookupTable(((IdNode) operand.car()).toString()) != null) { // �������� ���̺� ���� �� ��� 
				Node temp = lookupTable(((IdNode) operand.car()).toString()); // define a #T �� ��� ( #T ) ��������]
				if (temp instanceof BooleanNode) {
					if (temp == BooleanNode.TRUE_NODE)
						return BooleanNode.FALSE_NODE;
					else
						return BooleanNode.TRUE_NODE;
				}
			}
			////////////////////////////////////////////////////////////////////////////
			else {
				if (operand.car() instanceof BooleanNode) {
					if (operand.car() == BooleanNode.TRUE_NODE)
						return BooleanNode.FALSE_NODE;
					else
						return BooleanNode.TRUE_NODE;
				} else if (((ListNode) operand.car()).car() instanceof FunctionNode) {
					if (runFunction((FunctionNode) ((ListNode) operand.car()).car(),
							((ListNode) operand.car()).cdr()) == BooleanNode.TRUE_NODE)
						return BooleanNode.FALSE_NODE;
					else
						return BooleanNode.TRUE_NODE;
				} else if (((ListNode) operand.car()).car() instanceof BinaryOpNode) {
					if (runBinary((ListNode) operand.car()) == BooleanNode.TRUE_NODE)
						return BooleanNode.FALSE_NODE;
					else
						return BooleanNode.TRUE_NODE;
				} else {
					errorLog("run Expr error");
					return null;
				}
			}

		case ATOM_Q:
			////////////////////////////////////////////////// (item2)
			if (operand.car() instanceof IdNode && lookupTable(((IdNode) operand.car()).toString()) != null) { // define��
																												// �� ���
				Node temp = ((QuoteNode) lookupTable(((IdNode) operand.car()).toString())).nodeInside(); // �� ����
				if (temp instanceof IdNode || temp instanceof IntNode) { // ����Ʈ �ƴ�
					return BooleanNode.TRUE_NODE;
				} else if (temp instanceof ListNode) // ����Ʈ��
					return BooleanNode.FALSE_NODE;
				

			} else {
				//////////////////////////////////////////////////

				if(operand.car() instanceof QuoteNode) { // `����  �ܼ� ���̿����� ex) 3, A
				if (runQuote(operand) instanceof IdNode || runQuote(operand) instanceof IntNode) { // ����Ʈ �ƴ�
					return BooleanNode.TRUE_NODE;
				} else if ((ListNode) runQuote(operand) instanceof ListNode) // ����Ʈ��
					return BooleanNode.FALSE_NODE;
				else {
					errorLog("run Expr error");
					return null;
				}
			}
			else {
				return BooleanNode.TRUE_NODE;
			}
			}

		case EQ_Q:
			if (operand.car() instanceof IdNode) { // ù��尡 id�� �� (
				if (lookupTable(operand.car().toString()) != null) { // ù ��尡 ���̺��� ��
					if (((ListNode) operand.cdr()).car() instanceof IdNode) { // �ι����� id�϶�
						if (lookupTable((((ListNode) operand.cdr()).car()).toString()) != null) { // �ι����� ���̺��� ��
							if (lookupTable(operand.car().toString()) instanceof QuoteNode) { // ���̺��� quote�϶�
								if (lookupTable(((operand.cdr()).car()).toString()) instanceof QuoteNode) { // ���� ���̺���
																											// quote�� ��
									ListNode temp = (ListNode) ((QuoteNode) lookupTable(operand.car().toString()))
											.nodeInside();
									ListNode temp2 = (ListNode) ((QuoteNode) lookupTable(
											(((ListNode) operand.cdr()).car()).toString())).nodeInside();

									while (temp.car() != null && temp.cdr() != null && temp2.car() != null
											&& temp2.cdr() != null) { // ����Ʈ������ �� ��
										if (temp.car().toString().equals(temp2.car().toString())) {
											temp = temp.cdr();
											temp2 = temp2.cdr();
											continue;
										} else
											return BooleanNode.FALSE_NODE; // �ϳ��� �ٸ��� FALSE
									}
									if (temp.car() != null || temp.cdr() != null || temp2.car() != null
											|| temp2.cdr() != null)
										return BooleanNode.FALSE_NODE; // ������
									else
										return BooleanNode.TRUE_NODE;
								} else { // �� ���̺� ���� ��Ʈ�ε� �ڴ� ��Ʈ�ƴҋ� false
									return BooleanNode.FALSE_NODE;
								}
							} else { // ó�� ���̺��� ��Ʈ���ƴ϶� �Ѱ��϶�
								if (((ListNode) operand.cdr()).car() instanceof ListNode) { // �ڿ��Ŵ� ����Ʈ�ϰ�� false
									return BooleanNode.FALSE_NODE;
								} else { // ó�� ���̺� �ι��� ���̺� ��� �ܼ��� �� (������)
									if (lookupTable(operand.car().toString()).toString().equals(
											lookupTable((((ListNode) operand.cdr()).car()).toString()).toString()))
										return BooleanNode.TRUE_NODE; // �տ� ������ �ڿ� ������ ���� EQ_Q
									else
										return BooleanNode.FALSE_NODE;
								}
							}
						} else { // �ι�°�� ���̺��� �ƴ϶� id �� ��
							if ((lookupTable(operand.car().toString())).toString()
									.equals((operand.cdr().car()).toString()))
								return BooleanNode.TRUE_NODE;
							else
								return BooleanNode.FALSE_NODE;
						}
					} else { // �ι�° ��尡 Int�϶�
						if ((lookupTable(operand.car().toString())).toString().equals((operand.cdr().car()).toString()))
							return BooleanNode.TRUE_NODE;
						else
							return BooleanNode.FALSE_NODE;
					}
				} else { // ù��° ���� ���̺��� �ƴ϶� id�϶�
					if (operand.car().toString().equals((operand.cdr().car()).toString()))
						return BooleanNode.TRUE_NODE;
					else
						return BooleanNode.FALSE_NODE;
				}
			} else if (operand.car() instanceof IntNode) { // ù��° ���� Int�϶�
				if (((ListNode) operand.cdr()).car() instanceof IdNode) { // �ι�° ���� id�϶�
					if (lookupTable((((ListNode) operand.cdr()).car()).toString()) != null) {
						if (lookupTable((((ListNode) operand.cdr()).car()).toString()) instanceof ListNode) {
							return BooleanNode.FALSE_NODE;
						} else if (operand.car().toString()
								.equals(lookupTable((((ListNode) operand.cdr()).car()).toString()).toString()))
							return BooleanNode.TRUE_NODE;
						else
							return BooleanNode.FALSE_NODE;
					}
				} else { // �ι�° ���� int�ϋ�
					if (operand.car().toString().equals((operand.cdr().car()).toString()))
						return BooleanNode.TRUE_NODE;
					else
						return BooleanNode.FALSE_NODE;
				}
			} else if (runQuote(operand) instanceof IntNode || runQuote(operand) instanceof IdNode) { // Int,Id �϶�
				if (runQuote(operand).toString().equals(runQuote(operand.cdr()).toString()))
					return BooleanNode.TRUE_NODE;
				else
					return BooleanNode.FALSE_NODE;
			} else { // ����Ʈ�϶�
				ListNode temp = (ListNode) runQuote((ListNode) operand);
				ListNode temp2 = (ListNode) runQuote((ListNode) operand.cdr());

				while (temp.car() != null && temp.cdr() != null && temp2.car() != null && temp2.cdr() != null) { // ����Ʈ����
																													// ��
					if (temp.car().toString().equals(temp2.car().toString())) {
						temp = temp.cdr();
						temp2 = temp2.cdr();
						continue;
					} else
						return BooleanNode.FALSE_NODE;
				}
				if (temp.car() != null || temp.cdr() != null || temp2.car() != null || temp2.cdr() != null)
					return BooleanNode.FALSE_NODE; // ����Ʈ ������
				else
					return BooleanNode.TRUE_NODE;
			}

		case DEFINE:
			Node id_node = operand.car(); // ������ �ܾ�
			String id = id_node.toString();
			Node list = operand.cdr().car(); // ���ǵ� �ܾ��� ����
			insertTable(id, list); // ���̺� �߰�

		default:
			break;
		}
		return null;

	}

	private Node runBinary(ListNode list) {
		BinaryOpNode operator = (BinaryOpNode) list.car(); // ������������ �ʿ��� ���� �� �Լ� �۾� ����

		Node node1 = list.cdr().car(); // ó������
		Node node2 = list.cdr().cdr().car(); // �ι�°����

		IntNode num1 = (IntNode) runExpr(node1); // runExpr������ ����Ʈ ����̰� ����Ʈ�� ù ��尡(car) +,- �� �� ��� ����� �ٽÿ� (���)
		IntNode num2 = (IntNode) runExpr(node2);

		switch (operator.value) { // +,-,/ � ���� ���̳ʸ� ���� ���� ����

		case PLUS:
			try {
				IntNode result = new IntNode(num1.value + num2.value + "");
				return result;
			} catch (Exception e) {
				errorLog("run Expr error");
			}
		case MINUS:
			try {

				IntNode result = new IntNode(num1.value - num2.value + "");
				return result;
			} catch (Exception e) {
				errorLog("run Expr error");
			}
		case DIV:
			try {
				IntNode result = new IntNode(num1.value / num2.value + "");
				return result;
			} catch (Exception e) {
				errorLog("run Expr error");
			}
		case TIMES:
			try {
				IntNode result = new IntNode(num1.value * num2.value + "");
				return result;
			} catch (Exception e) {
				errorLog("run Expr error");
			}
		case LT:
			if (num1.value < num2.value) {
				BooleanNode result = BooleanNode.TRUE_NODE;
				return result;
			} else if (num1.value > num2.value) {
				BooleanNode result = BooleanNode.FALSE_NODE;
				return result;
			} else {
				errorLog("run Expr error");
			}

		case GT:
			if (num1.value > num2.value) {
				BooleanNode result = BooleanNode.TRUE_NODE;
				return result;
			} else if (num1.value < num2.value) {
				BooleanNode result = BooleanNode.FALSE_NODE;
				return result;
			} else {
				errorLog("run Expr error");
			}

		case EQ:

			try {
				if (num1.value.equals(num2.value)) {
					BooleanNode result = BooleanNode.TRUE_NODE;
					return result;
				} else {
					BooleanNode result = BooleanNode.FALSE_NODE;
					return result;
				}
			} catch (Exception e) {
				errorLog("run Expr error");
			}
		default:
			break;
		}
		return null;
	}

	private Node runQuote(ListNode node) {
		return ((QuoteNode) node.car()).nodeInside();
	}

	public static void main(String[] args) {
		while (true) {

			System.out.print(">"); // �߰�(8����)
			Scanner scanner = new Scanner(System.in); // �߰�(8����)
			String str = scanner.nextLine(); // �߰�(8����)
			CuteParser cuteParser = new CuteParser(str);
			Node parseTree = cuteParser.parseExpr();
			CuteInterpreter i = new CuteInterpreter();
			Node resultNode = i.runExpr(parseTree);
			System.out.print(".. "); // �߰�(8����)
			NodePrinter.getPrinter(System.out).prettyPrint(resultNode);
			System.out.println(); // �߰�(8����)

		}

	}
}
