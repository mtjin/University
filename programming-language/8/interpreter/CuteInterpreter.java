//201404377_���¾�
package interpreter;

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

	private void errorLog(String err) {
		System.out.println(err);
	}

	public Node runExpr(Node rootExpr) { // �ʱ� ��� �Ľ�
		if (rootExpr == null)
			return null;
		if (rootExpr instanceof IdNode)
			return rootExpr;
		else if (rootExpr instanceof IntNode)
			return rootExpr;
		else if (rootExpr instanceof BooleanNode)
			return rootExpr;
		else if (rootExpr instanceof ListNode)
			return runList((ListNode) rootExpr); // ����Ʈ�ΰ�� Node runList(ListNode list)�� ���� <�̿� ���� �׳� ����ȯ>
		else
			errorLog("run Expr error");
		return null;
	}

	private Node runList(ListNode list) {
		if (list.equals(ListNode.EMPTYLIST))
			return list;
		if (list.car() instanceof FunctionNode) { // FunctionNode�� ��� (car, cdr , NULLQ��)
			return runFunction((FunctionNode) list.car(), list.cdr());
		}
		if (list.car() instanceof BinaryOpNode) { // BinaryOpNode ��Ģ������ ���
			return runBinary(list);
		}
		return list;
	}

	private Node runFunction(FunctionNode operator, ListNode operand) {
		switch (operator.value) { // CAR, CDR, CONS� ���� ���� ����
		case CAR:
			if (operand.car() instanceof QuoteNode) { // ���� car������ ���� ��尡 `���� �˻�
				ListNode result = (ListNode) runQuote(operand);
				return result.car();
			} else {
				errorLog("run Expr error");
			}
			return null;

		case CDR:
			if (operand.car() instanceof QuoteNode) {
				ListNode result = (ListNode) runQuote(operand);
				return result.cdr();
			} else {
				errorLog("run Expr error");
			}
			return null;
		case CONS:
			if (operand.car() instanceof QuoteNode) { // ó���� `�� ��� (����Ʈ�� ���� ���)
				ListNode result = ListNode.cons((ListNode) runQuote(operand), (ListNode) runQuote(operand.cdr()));
				return result;
			} else {
				ListNode result = ListNode.cons(operand.car(), (ListNode) runQuote(operand.cdr()));
				return result;
			}

		case NULL_Q:
			if (((ListNode) runQuote(operand)).car() == null) {
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
					BooleanNode node = (BooleanNode) runExpr(((ListNode) listNode.car()).car());
					if (node == (BooleanNode.TRUE_NODE)) {
						return ((ListNode) listNode.car()).cdr().car();
					} else {
						listNode = operand.cdr();
					}
				}
			} catch (Exception e) {
				errorLog("run Expr error");
			}

		case NOT:
			if (operand.car() instanceof BooleanNode) {
				if (operand == BooleanNode.TRUE_NODE)
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

		case ATOM_Q:
			if (runQuote(operand) instanceof IdNode || runQuote(operand) instanceof IntNode) { // ����Ʈ �ƴ�
				return BooleanNode.TRUE_NODE;
			} else if ((ListNode) runQuote(operand) instanceof ListNode) // ����Ʈ��
				return BooleanNode.FALSE_NODE;
			else {
				errorLog("run Expr error");
				return null;
			}
		case EQ_Q:
			if (runQuote(operand) instanceof IdNode || runQuote(operand) instanceof IntNode) { // IdNode, IntNode

				if (runQuote(operand).toString().equals(runQuote(operand.cdr()).toString()))
					return BooleanNode.TRUE_NODE;
				else
					return BooleanNode.FALSE_NODE;
			} else { // ListNode
				ListNode temp = (ListNode) runQuote((ListNode) operand);
				ListNode temp2 = (ListNode) runQuote((ListNode) operand.cdr());

				while (temp.car() != null && temp.cdr() != null && temp2.car() != null && temp2.cdr() != null) {
					if (temp.car().toString().equals(temp2.car().toString())) {
						temp = temp.cdr();
						temp2 = temp2.cdr();
						continue;
					} else
						return BooleanNode.FALSE_NODE;
				}
				if (temp.car() != null || temp.cdr() != null || temp2.car() != null || temp2.cdr() != null) // ����Ʈ�� ���
																											// �����ٸ���
					return BooleanNode.FALSE_NODE;
				else
					return BooleanNode.TRUE_NODE;
			}
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

			System.out.print(">");                    //�߰�(8����)
			Scanner scanner = new Scanner(System.in);   //�߰�(8����)
			String str = scanner.nextLine();            //�߰�(8����)

			CuteParser cuteParser = new CuteParser(str);
			Node parseTree = cuteParser.parseExpr();
			CuteInterpreter i = new CuteInterpreter();
			Node resultNode = i.runExpr(parseTree);
			System.out.print(".. ");                      //�߰�(8����)
			NodePrinter.getPrinter(System.out).prettyPrint(resultNode);
			System.out.println();                          //�߰�(8����)

		}

	}
}
